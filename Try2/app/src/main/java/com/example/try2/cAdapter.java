package com.example.try2;

import android.content.Context;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

public class cAdapter extends RecyclerView.Adapter<cAdapter.ViewHolder> {
    private ArrayList<Crime> aList;
    private Context mcontext;


    public cAdapter(Context context, ArrayList<Crime> cList){
        this.aList=cList;
        this.mcontext=context;
        setHasStableIds(TRUE);


    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        return new cAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        viewHolder.id.setText("category: " + aList.get(i).getCategory());
        viewHolder.name.setText("Persistent ID: "+aList.get(i).getPersistentId());
        viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,cDetails.class);
                intent.putExtra("Cat",aList.get(i).getCategory());
                intent.putExtra("Context",aList.get(i).getContext());
                intent.putExtra("Lt",aList.get(i).getLocationType());
                intent.putExtra("Lst",aList.get(i).getLocationSubtype());
                intent.putExtra("mon",aList.get(i).getMonth());
                intent.putExtra("PID",aList.get(i).getPersistentId());
                intent.putExtra("ID",aList.get(i).getId());
                mcontext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return aList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id,name;
        CardView cardview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.ID);
            name=itemView.findViewById((R.id.names));
            cardview=itemView.findViewById(R.id.Cardview);
        }
    }
}
