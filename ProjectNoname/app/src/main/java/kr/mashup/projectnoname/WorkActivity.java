package kr.mashup.projectnoname;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kr.mashup.projectnoname.databinding.ActivityWorkBinding;

public class WorkActivity extends AppCompatActivity {


    ViewPager mViewPager;
    LinearLayout mSliderDotspannel;
    private int dotsCount;
    private ArrayList<ImageView> dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ActivityWorkBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_work);

        mViewPager = binding.workViewPager;
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(this);

        binding.workViewPager.setAdapter(imagePagerAdapter);

        mSliderDotspannel = binding.sliderDots;

        dotsCount = imagePagerAdapter.getCount();
        dots = new ArrayList<>(dotsCount);

        for (int i=0; i< dotsCount; i++) {
            dots.add(new ImageView(this));
            dots.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            mSliderDotspannel.addView(dots.get(i),params);

        }

        dots.get(0).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0; i<dotsCount; i++) {
                    dots.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }
                dots.get(position).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }




}
