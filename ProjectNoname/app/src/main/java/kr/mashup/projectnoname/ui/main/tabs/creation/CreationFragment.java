package kr.mashup.projectnoname.ui.main.tabs.creation;

import kr.mashup.projectnoname.ui.base.BaseFragment;

public class CreationFragment extends BaseFragment<Contract.Presenter> implements Contract.View{

    private String category;

    @Override
    protected Contract.Presenter buildPresenter() {
        return new CreationPresenter(this);
    }

    public CreationFragment setCategory(String category){
        this.category = category;
        return this;
    }
}
