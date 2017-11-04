package kr.mashup.projectnoname.ui.main.tabs.creation;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.stream.Stream;

import kr.mashup.projectnoname.model.Creation;

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
        recyclerView.setLayoutManager(
                new GridLayoutManager(
                        view.getContext(),
                        LIST_GRID_COLUM_SIZE
                )
        );
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
            creation.description = "동화책 편집디자인";
            creation.title = "동화책 편집디자인";
            creation.feedbackCount = 3;
            dataList.add(creation);
        }

        return dataList;
    }
}