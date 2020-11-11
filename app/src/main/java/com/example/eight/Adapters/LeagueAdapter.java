package com.example.eight.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eight.ModelList.LeaguesModel;
import com.example.eight.R;
import com.example.eight.ViewClasses.LeagueEvent;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.ViewHolder> {

    private Context context;
    private List<LeaguesModel.Datum> lmd;
    private LeaguesModel.Datum md;
    public static String leagueID;

    public LeagueAdapter(Context context, List lmd){
        this.context = context;
        this.lmd = lmd;
    }

    @NonNull
    @Override
    public LeagueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leagues,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final LeagueAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(lmd.get(position));
        md = lmd.get(position);

        if(md.getHost() != null){
            holder.country.setText(md.getHost().getCountry());
        }
        //leagueID = lmd.get(position).getId().toString();
        holder.name.setText(md.getName());
        holder.start.setText("Start: "+md.getStartDate());
        holder.end.setText("End: "+md.getEndDate());
        Picasso.get().load(md.getLogo()).into(holder.leagueImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<lmd.size();i++){
                    if(holder.name.getText() == lmd.get(i).getName()){
                        leagueID = lmd.get(i).getId().toString();
                        Intent leagueEvent = new Intent(context, LeagueEvent.class);
                        leagueEvent.putExtra("leagueId",String.valueOf(leagueID));
                        context.startActivity(leagueEvent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount()  {
        return lmd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView leagueImg;
        TextView name,start,end,country,div,numRounds;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            leagueImg = itemView.findViewById(R.id.leagueImg);
            name = itemView.findViewById(R.id.lName);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            country = itemView.findViewById(R.id.country);
        }
    }
}
