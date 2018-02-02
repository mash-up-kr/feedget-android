package kr.mashup.feedget.ui.creation.detail.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.CardCreationFeedbackBinding;
import kr.mashup.feedget.model.dummy.Feedback;


public class CreationFeedbackViewHolder extends RecyclerView.ViewHolder{

    private CardCreationFeedbackBinding binding;

    public CreationFeedbackViewHolder(CardCreationFeedbackBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public void setData(Feedback feedback){
        binding.setFeedback(feedback);
    }
}
