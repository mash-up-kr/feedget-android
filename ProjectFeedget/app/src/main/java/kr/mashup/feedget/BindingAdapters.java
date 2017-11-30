package kr.mashup.feedget;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by ichaeeun on 2017. 11. 4..
 */

public class BindingAdapters {
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, int url) {
        view.setImageResource(url);
    }
}
