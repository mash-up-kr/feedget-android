package kr.mashup.feedget.ui.main.tabs.news;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kr.mashup.feedget.databinding.CardNewsBinding;
import kr.mashup.feedget.entity.Notification;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private CardNewsBinding binding;

    public NewsViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());

        this.binding = (CardNewsBinding) binding;
    }

    public void setData(Notification notification) {

    }


}
