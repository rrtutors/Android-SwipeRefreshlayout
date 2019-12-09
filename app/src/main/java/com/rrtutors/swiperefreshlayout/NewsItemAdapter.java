package com.rrtutors.swiperefreshlayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rrtutors.swiperefreshlayout.apis.News;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder> {

    List<News>listNews;
    NewsItemAdapter()
    {
        listNews=new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_title.setText(listNews.get(position).getName());
        holder.txt_desc.setText(listNews.get(position).getDescription());
        holder.txt_country.setText(listNews.get(position).getCountry().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public void addNews(List<News>listNew)
    {
        listNews.clear();
        listNews.addAll(listNew);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_title;
        TextView txt_desc;
        TextView txt_country;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_desc=itemView.findViewById(R.id.txt_desc);
            txt_country=itemView.findViewById(R.id.txt_country);
        }
    }
}
