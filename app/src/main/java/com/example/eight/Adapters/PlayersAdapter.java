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

import com.example.eight.ModelList.PlayerModel;
import com.example.eight.R;
import com.example.eight.ViewClasses.PlayerVid;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {

    private Context context;
    private List<PlayerModel.Datum> lpm;
    private PlayerModel.Datum pm;
    public static String playerid;
    public static String img,names,pos,num;

    public PlayersAdapter(Context context, List lpm){
        this.context = context;
        this.lpm = lpm;
    }

    @NonNull
    @Override
    public PlayersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.players,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayersAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(lpm.get(position));
        pm = lpm.get(position);

        if(pm.getHeight() != null || pm.getAge() != null || pm.getShirtNumber() != null){

        }

        //id = pm.getId().toString();
        holder.height.setText(nullable(pm.getHeight()));
        holder.age.setText(nullable(pm.getAge()));
        holder.num.setText(nullable(pm.getShirtNumber()));
        holder.name.setText(pm.getName());
        if(pm.getMainTeam() != null)
        {
            holder.team.setText(pm.getMainTeam().getName());
        }
        holder.pos.setText(pm.getPosition());
        holder.nation.setText(pm.getNationalityCode());
        Picasso.get().load(pm.getPhoto()).into(holder.playerImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            for(int i=0; i<lpm.size();i++) {
                if (holder.name.getText() == lpm.get(i).getName()) {
                    playerid = lpm.get(i).getId().toString();
                    img = lpm.get(i).getPhoto();
                    names = lpm.get(i).getName();
                    pos = lpm.get(i).getPosition();
                    num = lpm.get(i).getShirtNumber().toString();
                    Intent media = new Intent(context, PlayerVid.class);
                    media.putExtra("playerid", String.valueOf(playerid));
                    context.startActivity(media);
                }
            }
            }
        });
    }

    public String nullable(String object){
        object = (object == null) ?  "" : object;
        return object;
    }
    public String nullable(Integer object){
        object = (object == null) ?  0 : object;
        return object.toString();
    }
    public String nullable(Float object){
        object = (object == null) ?  0 : object;
        return object.toString();
    }

    @Override
    public int getItemCount() {
        return lpm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView playerImg;
        TextView nation,height,age,pos,team,num,name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            playerImg = itemView.findViewById(R.id.playerImg);
            nation = itemView.findViewById(R.id.nation);
            height = itemView.findViewById(R.id.height);
            age = itemView.findViewById(R.id.age);
            pos = itemView.findViewById(R.id.pos);
            team = itemView.findViewById(R.id.team);
            num = itemView.findViewById(R.id.num);
            name = itemView.findViewById(R.id.name);
        }
    }
}
