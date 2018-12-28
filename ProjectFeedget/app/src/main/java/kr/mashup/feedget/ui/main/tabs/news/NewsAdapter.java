package kr.mashup.feedget.ui.main.tabs.news;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kr.mashup.feedget.R;
import kr.mashup.feedget.entity.Notification;

public class NewsAdapter extends RecyclerView.Adapter{

    private ArrayList<Notification> items;

    public NewsAdapter(){
        this.items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.card_news,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NewsViewHolder)holder).setData(
                items.get(position)
        );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addList(List<Notification> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
