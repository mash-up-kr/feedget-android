package kr.mashup.feedget.ui.main.feed.tabs.creation;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.mashup.feedget.R;
import kr.mashup.feedget.model.Creation;

public class CreationListAdapter extends RecyclerView.Adapter<CreationViewHolder> {

    private ArrayList<Creation> dataList;

    public CreationListAdapter() {
        super();

        init();
    }

    private void init() {
        dataList = new ArrayList<>();
    }

    @Override
    public CreationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        return new CreationViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.card_main_creation,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(CreationViewHolder holder, int position) {
        if (isVaildPosition(position) == false) {
            throw new IndexOutOfBoundsException("position이 올바르지 않습니다. position : " + position);
        }

        holder.setData(dataList.get(position));
    }

    private boolean isVaildPosition(int position) {
        if (dataList == null) {
            throw new NullPointerException("position을 계산할 수 없는 상황입니다.");
        }

        return position >= 0 && position < dataList.size();
    }

    @Override
    public int getItemCount() {
        if(dataList == null) return 0;
        return dataList.size();
    }

    public void addListToTail(ArrayList dataList){
        this.dataList.addAll(dataList);
    }

}
