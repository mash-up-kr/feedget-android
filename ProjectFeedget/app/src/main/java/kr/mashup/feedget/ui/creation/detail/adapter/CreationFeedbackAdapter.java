package kr.mashup.feedget.ui.creation.detail.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.mashup.feedget.R;
import kr.mashup.feedget.model.dummy.Feedback;
import kr.mashup.feedget.ui.creation.detail.viewholder.CreationFeedbackViewHolder;

public class CreationFeedbackAdapter extends RecyclerView.Adapter<CreationFeedbackViewHolder> {

    private ArrayList<Feedback> dataList;

    public CreationFeedbackAdapter() {
        dataList = new ArrayList<>();
    }

    @Override
    public CreationFeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CreationFeedbackViewHolder viewHolder = new CreationFeedbackViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.card_creation_feedback,
                        parent,
                        false
                )
        );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CreationFeedbackViewHolder holder, int position) {
        holder.setData(
                dataList.get(position)
        );
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public void addList(ArrayList<Feedback> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }
}
