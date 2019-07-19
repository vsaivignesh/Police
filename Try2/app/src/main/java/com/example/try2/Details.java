package com.example.try2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class    Details extends AppCompatActivity {
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        desc = findViewById(R.id.Desc);
        getDData();
    }

    private void getDData() {
        Retrofit retroData = new Retrofit.Builder()
                .baseUrl("https://data.police.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DetailsInterface gD = retroData.create(DetailsInterface.class);
        if (getIntent().hasExtra("Position")) {
            Call<getDetails> call = gD.callDetails(getIntent().getStringExtra("Position"));
            call.enqueue(new Callback<getDetails>() {
                @Override
                public void onResponse(Call<getDetails> call, Response<getDetails> response) {
                    getDetails detialsPol = response.body();
                    String message="";
                    message+="Description:" + detialsPol.getDescription()+"\n";
                    message+="ID:" +detialsPol.getId()+"\n";
                    message+="Name:"+detialsPol.getName()+"\n";
                    message+="Telephone:"+detialsPol.getTelephone()+"\n";
                    message+="URL:"+detialsPol.getUrl()+"\n";
                    desc.setText(message);
                }

                @Override
                public void onFailure(Call<getDetails> call, Throwable t) {
                    Toast.makeText(Details.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
        } else {
            Toast.makeText(this, "Didnt get name", Toast.LENGTH_SHORT).show();
        }


    }
}
