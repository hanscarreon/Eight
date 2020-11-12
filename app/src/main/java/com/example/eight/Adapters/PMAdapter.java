package com.example.eight.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eight.ModelList.PlayerMediaModel;
import com.example.eight.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PMAdapter extends RecyclerView.Adapter<PMAdapter.ViewHolder> {

    private Context context;
    private List<PlayerMediaModel.Datum> pmmd;
    private PlayerMediaModel.Datum pmm;

    public PMAdapter(Context context, List pmmd){
        this.context = context;
        this.pmmd = pmmd;
    }

    @NonNull
    @Override
    public PMAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_media_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PMAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(pmmd.get(position));
        pmm = pmmd.get(position);

        holder.title.setText(pmm.getTitle());
        holder.subTitle.setText(pmm.getSubTitle());
        holder.source.setText("Source: youtube.com");

        holder.vids.getSettings().setJavaScriptEnabled(true);
        holder.vids.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        String url = pmm.getSourceUrl();
        String vidUrl = url.replace("watch?v=","embed/");
        holder.vids.loadUrl(vidUrl);
        holder.vids.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading (WebView view, String url){
                //True if the host application wants to leave the current WebView and handle the url itself, otherwise return false.

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return pmmd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        WebView vids;
        TextView title;
        TextView subTitle;
        TextView source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vids = itemView.findViewById(R.id.pmVideo);
            title = itemView.findViewById(R.id.pmTitle);
            subTitle = itemView.findViewById(R.id.pmSubtitle);
            source = itemView.findViewById(R.id.pmSource);
        }
    }
}
