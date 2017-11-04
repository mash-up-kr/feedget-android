package kr.mashup.projectnoname;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ichaeeun on 2017. 11. 5..
 */

public class FeedbackViewHolder extends RecyclerView.ViewHolder{

    public TextView contentTextView;
    public TextView ownerTextView;

    public FeedbackViewHolder(View itemView) {
        super(itemView);
        contentTextView = itemView.findViewById(R.id.contentTextView);
        ownerTextView = itemView.findViewById(R.id.ownerTextView);


    }
}
