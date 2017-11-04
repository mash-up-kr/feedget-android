package kr.mashup.projectnoname.ui.main.tabs.creation;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kr.mashup.projectnoname.R;
import kr.mashup.projectnoname.databinding.CardMainCreationBinding;
import kr.mashup.projectnoname.model.Creation;

public class CreationViewHolder extends RecyclerView.ViewHolder {

    private CardMainCreationBinding binding;

    public CreationViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());

        this.binding = (CardMainCreationBinding) binding;
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

}
