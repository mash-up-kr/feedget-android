package kr.mashup.feedget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.mashup.feedget.ui.main.tabs.creation.CreationFragment;

public class MyPageFragment extends Fragment {

    Fragment[] arrFragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_my_page, null);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initLayout();

        ViewPager viewPager = getView().findViewById(R.id.vp_pager);
        TabLayout tabLayout = getView().findViewById(R.id.tl_tabs);

        arrFragments = new Fragment[2];
        arrFragments[0] = new CreationFragment();
        arrFragments[1] = new CreationFragment();

        MainPagerAdapter adapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(), arrFragments);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initLayout() {
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) getView().findViewById(R.id.collapsing_toolbar);
        ctl.setTitle("마이페이지");

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    class MainPagerAdapter extends FragmentPagerAdapter {

        Fragment[] arrFragments;

        public MainPagerAdapter(FragmentManager fm, Fragment[] arrFragments) {
            super(fm);
            this.arrFragments = arrFragments;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "게시글";
                case 1:
                    return "남긴 피드백";


                default:
                    return "";
            }
        }

        @Override
        public Fragment getItem(int position) {
            return arrFragments[position];
        }

        @Override
        public int getCount() {
            return arrFragments.length;
        }
    }

}
