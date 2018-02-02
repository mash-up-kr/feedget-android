package kr.mashup.feedget.ui.main.feed.tabs.creation;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import kr.mashup.feedget.model.dummy.Creation;
import kr.mashup.feedget.ui.register.register_content.RegisterActivity;

public class CreationPresenter implements Contract.Presenter {

    private final int LIST_GRID_COLUM_SIZE = 2;
    private CreationListAdapter adapter;
    private Contract.View view;

    private FeedPagerAdapter pagerAdapter;

    public CreationPresenter(Contract.View view) {
        this.view = view;
    }

    private final String[] TEST_CATEGORIES = {
            "전체", "디자인", "회화", "공예", "글", "기타"
    };


    @Override
    public void initRecyclerView(RecyclerView recyclerView, FloatingActionButton write) {
        adapter = new CreationListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (dy > 0 ||dy<0 && write.isShown())
                    write.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    write.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    public void requestCreationList(String category) {
        adapter.addListToTail(getDummyList());
    }

    private ArrayList<Creation> getDummyList(){
        ArrayList<Creation> dataList = new ArrayList<>();
        for(int i=0;i<50;i++){
            Creation creation = new Creation();
            creation.creationId = i;
            creation.description = "과제용 카페 박람회 포스터 리디자인 중 사용될 일러고 동화적인 느낌으로 진행했어요. 폰트나 구도가 확신이 안 가는데 피드백 부탁드려요. 폰트나g 구도가 확신이 안 가네요";
            creation.title = "동화책 편집디자인";
            creation.feedbackCount = 3;
            dataList.add(creation);
        }

        return dataList;
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
    public void setWriteClickEvent(View view) {
        view.setOnClickListener(__ -> {
                    Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                    view.getContext().startActivity(intent);
                }
        );
    }



    private String[] getCategories() {
        return TEST_CATEGORIES;
    }
}
