package kr.mashup.projectnoname.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.IntFunction;

import kr.mashup.projectnoname.ui.main.tabs.creation.CreationFragment;

public class MainPresenter implements Contract.Presenter {

    protected Contract.View view;

    private final String[] TEST_CATEGORIES = {
            "전체", "디자인", "미술", "공예", "요리"
    };

    private MainPagerAdapter pagerAdapter;

    public MainPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void initTabPager(ViewPager pager, TabLayout tabLayout) {
        pagerAdapter = new MainPagerAdapter(view.getSupportFragmentManager());

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

    private String[] getCategories() {
        return TEST_CATEGORIES;
    }
}
