package kr.mashup.feedget.ui.creation.detail.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kr.mashup.feedget.R;
import kr.mashup.feedget.databinding.CardCreationImageBinding;


public class CreationImagePagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> imageList;

    public CreationImagePagerAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setImageList(List<String> imageList){
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (imageList == null) {
            return 0;
        }
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CardCreationImageBinding binding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.card_creation_image,
                container,
                false
        );
        binding.setImage(imageList.get(position));

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(binding.getRoot());
        return binding.getRoot();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
