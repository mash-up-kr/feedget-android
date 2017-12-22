package kr.mashup.feedget.ui.main.tabs.creation;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import kr.mashup.feedget.model.dummy.Creation;

public class CreationPresenter implements Contract.Presenter {

    private final int LIST_GRID_COLUM_SIZE = 2;
    private CreationListAdapter adapter;
    private Contract.View view;

    public CreationPresenter(Contract.View view) {
        this.view = view;
    }

    @Override
    public void initRecyclerView(RecyclerView recyclerView) {
        adapter = new CreationListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
}
