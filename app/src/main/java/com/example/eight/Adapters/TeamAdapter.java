package com.example.eight.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eight.ModelList.PlayerModel;
import com.example.eight.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private Context context;
    private List<PlayerModel.Datum> lpm;
    private PlayerModel.Datum pm;
    String id;

    public TeamAdapter(Context context, List lpm){
        this.context = context;
        this.lpm = lpm;
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(lpm.get(position));
        pm = lpm.get(position);

        if(pm.getMainTeam() != null) {
            Picasso.get().load(pm.getMainTeam().getLogo()).into(holder.teamImage);
            holder.teamTitle.setText(pm.getMainTeam().getName());
        }
    }

    @Override
    public int getItemCount() {
        return lpm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView teamImage;
        TextView teamTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            teamImage = itemView.findViewById(R.id.teamImg);
            teamTitle = itemView.findViewById(R.id.teamTitle);
        }
    }
}
