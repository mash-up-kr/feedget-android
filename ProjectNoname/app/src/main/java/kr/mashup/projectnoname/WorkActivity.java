package kr.mashup.projectnoname;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kr.mashup.projectnoname.databinding.ActivityWorkBinding;

public class WorkActivity extends AppCompatActivity {


    ViewPager mViewPager;
    LinearLayout mSliderDotspannel;
    private int dotsCount;
    private ArrayList<ImageView> dots;
    RecyclerView mRecyclerView;
    ArrayList<FeedbackData> mDatas;
    LinearLayoutManager mLayoutManager;
    FeedbackViewAdapter mFeedbackViewAdapter;
    TextView mSendTextView;

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
            params.setMargins(26,0,26,0);
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


        mRecyclerView= findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setNestedScrollingEnabled(true);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDatas = new ArrayList<>();

        mDatas.add(new FeedbackData("익명", "솔직히 이건 가나다라마바사 아자차카. 카카!! 가나다라 마밥일이삼사 오육 굿굿"));
        mDatas.add(new FeedbackData("익명", "너무 초짜 티나는데요? 서점 가서 좀더.. 랄랄라 하"));
        mDatas.add(new FeedbackData("익명", "상단의 레이아웃을 조금 더 가가가가나나 다다. ㅎㅎㅎ 가나 다라마바사 아자자 하하하하하 카카카카 하면 좋을 것 같아요!"));

        mFeedbackViewAdapter = new FeedbackViewAdapter(mDatas);
        mRecyclerView.setAdapter(mFeedbackViewAdapter);

        mSendTextView = findViewById(R.id.sendTextView);
        mSendTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        });



    }




}
