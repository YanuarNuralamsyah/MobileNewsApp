package id.example.newsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.example.newsapp.R;
import id.example.newsapp.model.DataNews;
import id.example.newsapp.ui.NewsActivity;
import id.example.newsapp.util.Constans;

public class NewsAdapter extends RecyclerView.Adapter {
    public static ArrayList<DataNews> pdflist;
    public static ArrayList<DataNews> mFiltereadList;
    public Context context;
    public NewsAdapter(ArrayList<DataNews> pdflist, Context context) {
        this.pdflist = pdflist;
        this.context = context;
        mFiltereadList = pdflist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof ViewHolder) {
            final DataNews webList = mFiltereadList.get(position);
            ((ViewHolder) holder).txtTitle.setText(webList.getTitle());
            Picasso.get()
                    .load(webList.getLink_image())
                    .into(((ViewHolder) holder).imgPost);

            ((ViewHolder) holder).layClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constans.TITLE = webList.getTitle();
                    Constans.IMAGE = webList.getLink_image();
                    Constans.DESCRIPTION = webList.getDescription();
                    Intent intent = new Intent(context, NewsActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    public int getItemCount() {
        return mFiltereadList == null ? 0 : mFiltereadList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle, txtShort, txtStart, txtDiscount, txtPrice;
        public ImageView imgPost, imgBookmrk;
        public CardView layClick;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPost = itemView.findViewById(R.id.imgPost);
            layClick = itemView.findViewById(R.id.layClick);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
