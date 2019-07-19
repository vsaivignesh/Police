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
import java.util.List;

import static java.lang.Boolean.TRUE;

public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {
    private ArrayList<Example> aList;
    private Context mcontext;


    public RAdapter(Context context, ArrayList<Example> EList){
        this.aList=EList;
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
        return new RAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.name.setText("Name: "+aList.get(i).getName());
        viewHolder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,Details.class);
                intent.putExtra("Position", aList.get(i).getId());
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
