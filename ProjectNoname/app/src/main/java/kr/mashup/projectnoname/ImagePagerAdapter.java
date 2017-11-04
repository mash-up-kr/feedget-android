package kr.mashup.projectnoname;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import kr.mashup.projectnoname.databinding.ItemWorkBinding;

/**
 * Created by ichaeeun on 2017. 11. 4..
 */

public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Integer> images = Arrays.asList(R.drawable.jihyeon2, R.drawable.jihyeon, R.drawable.jihyeon1);

    public ImagePagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        ItemWorkBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_work, container, false);

        binding.setImage(images.get(position));

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(binding.getRoot(), 0);
        return binding.getRoot();

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }


}
