package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private ArrayList<NewsItem> mNewsItems;


    final private ListItemClickListener mOnClickListener;


    public NewsRecyclerViewAdapter(ArrayList<NewsItem> items, ListItemClickListener listener) {
        this.mNewsItems = items;
        this.mOnClickListener = listener;

    }


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    @Override
    public int getItemCount() {
        return this.mNewsItems.size();
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, final int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NewsViewHolder viewHolder = new NewsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        NewsItem currentNewsItem = mNewsItems.get(position);

        holder.title.setText("Title: " + currentNewsItem.title);
        holder.description.setText("Description: " + currentNewsItem.description);
        holder.time.setText("Date: " + currentNewsItem.publishedAt);

    }


    class NewsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView title;
        private TextView description;
        private TextView time;

        public NewsViewHolder(View itemView) {

            super(itemView);

            title =  itemView.findViewById(R.id.news_article_title);
            description = itemView.findViewById(R.id.news_article_description);
            time = itemView.findViewById(R.id.news_article_time);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            System.out.println(clickedPosition);
            mOnClickListener.onListItemClick(clickedPosition);
        }


    }



}
