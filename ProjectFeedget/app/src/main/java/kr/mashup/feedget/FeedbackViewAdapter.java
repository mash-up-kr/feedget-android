package kr.mashup.feedget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.mashup.feedget.model.Creation;

/**
 * Created by ichaeeun on 2017. 11. 5..
 */

public class FeedbackViewAdapter extends RecyclerView.Adapter<FeedbackViewHolder>{

    ArrayList<FeedbackData> mDatas;

    public FeedbackViewAdapter(ArrayList<FeedbackData> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent,false);
        FeedbackViewHolder viewHolder = new FeedbackViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        holder.contentTextView.setText(mDatas.get(position).title);
        holder.ownerTextView.setText(mDatas.get(position).owner);
    }

    @Override
    public int getItemCount() {
        return (mDatas != null) ? mDatas.size() : 0;
    }
}
