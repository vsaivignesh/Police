package com.example.try2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ArrayList dataPolice=new ArrayList();
    private RecyclerView rView;
    EditText editTextSearch, lat,meridian;
    RAdapter adapter;
    ArrayList<String> useList;
    public MainActivity activity;
    String latitude;
    String longitude;
    Button button, avs;
    DatabaseHelper myDb2;
    EditText month;


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activity=MainActivity.this;
        super.onCreate(savedInstanceState);
        SQLiteDatabase.OpenParams oP = new SQLiteDatabase.OpenParams.Builder()
                .build();
        myDb2=new DatabaseHelper(this,oP);
        setContentView(R.layout.activity_main);
        rView=findViewById(R.id.RecyclerView);
        editTextSearch = findViewById(R.id.editText);
        lat=findViewById(R.id.Latitude);
        meridian=findViewById(R.id.Longditude);
        month=findViewById(R.id.Date);
        button=findViewById(R.id.Button);
        avs=findViewById(R.id.Fvs);
        rView.setLayoutManager(new LinearLayoutManager(this));
        getdata();
        avs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDb2.getData();
                if(res.getCount()==0){
                    Toast.makeText(activity, "No favs", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Category: "+res.getString(1)+"\n");
                    buffer.append("Persistent_ID: "+res.getString(2)+"\n\n  ");

                }
                showMessage("Favs", buffer.toString());

            }
        });
        addTextListener();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitude=lat.getText().toString();
                longitude=meridian.getText().toString();
                String month2=month.getText().toString();


                if(latitude != null && longitude!=null){
                    Intent latLong=new Intent(activity, LatLong.class);
                    latLong.putExtra("Lat", latitude);
                    latLong.putExtra("Long",longitude);
                    latLong.putExtra("Month",month2);
                    activity.startActivity(latLong);}
                else{
                    Toast.makeText(MainActivity.this, "Enter lat and long values", Toast.LENGTH_SHORT).show();}
            }
        });


    }


    public void showMessage(String Title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(message);
        builder.show();

    }

    private void addTextListener() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                s=s.toString().toLowerCase();
                final ArrayList filteredList=new ArrayList<>();
                if(useList!=null){
                for(int i=0;i<useList.size(); i++){
                    final String text=useList.get(i).toLowerCase();
                    if(text.contains(s)){
                        filteredList.add(dataPolice.get(i));
                    }
                }
                rView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter=new RAdapter(MainActivity.this,filteredList);
                rView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void getdata() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://data.police.uk")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONInterface jsonInterface=retrofit.create(JSONInterface.class);
        Call<List<Example>> call=jsonInterface.getdata();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                dataPolice=new ArrayList(response.body());
                useList=new ArrayList<>();
                for(int i=0;i<response.body().size();i++){
                useList.add(response.body().get(i).getName());}
                adapter=new RAdapter(MainActivity.this, dataPolice);
                rView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
