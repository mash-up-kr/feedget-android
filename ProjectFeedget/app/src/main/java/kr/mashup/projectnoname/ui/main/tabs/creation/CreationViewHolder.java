package kr.mashup.projectnoname.ui.main.tabs.creation;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import kr.mashup.projectnoname.R;
import kr.mashup.projectnoname.WorkActivity;
import kr.mashup.projectnoname.databinding.CardMainCreationBinding;
import kr.mashup.projectnoname.model.Creation;

public class CreationViewHolder extends RecyclerView.ViewHolder {

    private CardMainCreationBinding binding;

    public CreationViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());

        this.binding = (CardMainCreationBinding) binding;

        binding.getRoot().setOnClickListener(__ -> {
            startWorkActivity();
        });
    }

    public void setData(Creation creation) {
        binding.setCreation(creation);

        bindRandomDummyImage();
    }

    private void bindRandomDummyImage() {
        binding.image.setImageResource(
                new int[]{
                        R.drawable.test_img_1,
                        R.drawable.test_img_2,
                        R.drawable.test_img_3
                }[(int) (Math.random() * 3)]
        );
    }

    private void startWorkActivity() {
        Intent intent = new Intent(binding.getRoot().getContext(), WorkActivity.class);
        binding.getRoot().getContext().startActivity(intent);
    }

}
