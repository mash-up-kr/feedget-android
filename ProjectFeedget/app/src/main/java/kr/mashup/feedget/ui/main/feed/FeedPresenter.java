package kr.mashup.feedget.ui.main.feed;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import kr.mashup.feedget.R;
import kr.mashup.feedget.ui.main.feed.tabs.creation.CreationFragment;
import kr.mashup.feedget.ui.main.feed.tabs.creation.FeedPagerAdapter;
import kr.mashup.feedget.ui.register.RegisterActivity;

public class FeedPresenter implements Contract.Presenter {

    protected Contract.View view;

    private final String[] TEST_CATEGORIES = {
            "전체", "디자인", "회화", "공예", "글", "기타"
    };

    private FeedPagerAdapter pagerAdapter;

    public FeedPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void initTabPager(ViewPager pager, TabLayout tabLayout) {
        pagerAdapter = new FeedPagerAdapter(view.getSupportFragmentManager());

        String[] categories = getCategories();
        Fragment[] fragments = new Fragment[categories.length];

        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];

            tabLayout.addTab(tabLayout.newTab().setText(category));
            fragments[i] = new CreationFragment().setCategory(category);
        }

        pagerAdapter.setFragments(fragments);
        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initSpinnerSort(Spinner spinnerViewType) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.main_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerViewType.setAdapter(adapter);
    }

    @Override
    public void initSpinnerViewType(Spinner spinnerSort) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.main_sort, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(adapter);
    }


    private String[] getCategories() {
        return TEST_CATEGORIES;
    }
}
