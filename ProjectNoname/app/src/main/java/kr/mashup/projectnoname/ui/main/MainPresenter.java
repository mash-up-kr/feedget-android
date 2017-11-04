package kr.mashup.projectnoname.ui.main;

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
    public void initPager(ViewPager pager) {
        pagerAdapter = new MainPagerAdapter(view.getSupportFragmentManager());
        pagerAdapter.setFragments(
                Stream.of(getCategories())
                        .map((Function<String, Fragment>) category -> new CreationFragment().setCategory(category))
                        .toArray(value -> new Fragment[value])
        );
        pager.setAdapter(pagerAdapter);
    }

    private String[] getCategories() {
        return TEST_CATEGORIES;
    }
}
