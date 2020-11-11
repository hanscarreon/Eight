package com.example.eight.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eight.ModelList.PlayerMediaModel;
import com.example.eight.R;
import com.example.eight.ViewClasses.Players;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerMediaAdapter extends RecyclerView.Adapter<PlayerMediaAdapter.ViewHolder> {

    private Context context;
    private List<PlayerMediaModel.Datum> pmmd;
    private PlayerMediaModel.Datum pmm;
    Dialog myDialog;
    WebView pmView;
    TextView vidTitle;
    String vidUrl;
    Button btnExit;
    public static String img,names,pos,num;

    public PlayerMediaAdapter(Context context, List pmmd){
        this.context = context;
        this.pmmd = pmmd;
    }

    @NonNull
    @Override
    public PlayerMediaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playermedia,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayerMediaAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(pmmd.get(position));
        pmm = pmmd.get(position);
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.playervideo);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pmView = myDialog.findViewById(R.id.pmVideo);
        vidTitle = myDialog.findViewById(R.id.vidtitle);
        btnExit = myDialog.findViewById(R.id.btnExit);

        pmView.getSettings().setJavaScriptEnabled(true);
        pmView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        holder.title.setText(pmm.getTitle());
        holder.subTitle.setText(pmm.getSubTitle());
        Picasso.get().load(pmm.getThumbnailUrl()).into(holder.imageView);

        img = pmm.getSource().getPhoto();
        names = pmm.getSource().getName();
        pos = pmm.getSource().getPosition();
        num = pmm.getSource().getShirtNumber().toString();



        holder.btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<pmmd.size(); i++){
                    if(holder.title.getText() == pmmd.get(i).getTitle()){
                        String title = pmmd.get(i).getTitle();
                        String url = pmmd.get(i).getUrl();
                        vidUrl = url.replace("watch?v=","embed/");
                        vidTitle.setText(title);
                        pmView.loadUrl(vidUrl);
                        pmView.setWebViewClient(new WebViewClient() {
                            public boolean shouldOverrideUrlLoading (WebView view, String url){
                                //True if the host application wants to leave the current WebView and handle the url itself, otherwise return false.

                                return true;
                            }
                        });

                        myDialog.show();

                        btnExit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myDialog.dismiss();
                            }
                        });

                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return pmmd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,subTitle;
        Button btnWatch;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subtitle);
            btnWatch = itemView.findViewById(R.id.watch);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
