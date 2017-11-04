package kr.mashup.projectnoname.ui.main.tabs.creation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.mashup.projectnoname.R;
import kr.mashup.projectnoname.databinding.FragmentMainCreationBinding;
import kr.mashup.projectnoname.ui.base.BaseFragment;

public class CreationFragment extends BaseFragment<Contract.Presenter> implements Contract.View{

    private String category;
    private FragmentMainCreationBinding binding;

    @Override
    protected Contract.Presenter buildPresenter() {
        return new CreationPresenter(this);
    }

    public CreationFragment setCategory(String category){
        this.category = category;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = (FragmentMainCreationBinding) createView(inflater, R.layout.fragment_main_creation, container);

        init();
        return binding.getRoot();
    }

    private void init(){
        presenter.initRecyclerView(binding.recyclerView);

        presenter.requestCreationList(category);
    }
}
