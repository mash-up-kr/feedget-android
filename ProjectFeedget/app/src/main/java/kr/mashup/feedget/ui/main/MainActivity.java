package kr.mashup.feedget.ui.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.observers.DisposableSingleObserver;
import kotlin.Pair;
import kr.mashup.feedget.EmptyFragment;
import kr.mashup.feedget.R;
import kr.mashup.feedget.domain.interactor.usecases.GetCategories;
import kr.mashup.feedget.domain.interactor.usecases.GetCreations;
import kr.mashup.feedget.domain.interactor.usecases.Register;
import kr.mashup.feedget.entity.Category;
import kr.mashup.feedget.entity.Creation;
import kr.mashup.feedget.entity.SignIn;
import kr.mashup.feedget.ui.main.feed.FeedFragment;
import kr.mashup.feedget.ui.main.tabs.news.NewsFragment;

/**
 * Created by ichaeeun on 2018. 1. 6..
 */

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector  {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    GetCreations getCreations;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {

            case R.id.feed:
                FeedFragment feedFragment = new FeedFragment();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.container, feedFragment);
                fragmentTransaction1.commit();
                return true;
            case R.id.alarm:
                NewsFragment emptyFragment = new NewsFragment();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.container, emptyFragment);
                fragmentTransaction2.commit();
                return true;
            case R.id.myPage:
                Fragment emptyFragment2 = new Fragment();
                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.container, emptyFragment2);
                fragmentTransaction3.commit();
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        FeedFragment feedFragment = new FeedFragment();
        fragmentTransaction.replace(R.id.container, feedFragment);
        fragmentTransaction.commit();

        getCreations.execute(new DisposableSingleObserver<Pair<? extends Long, ? extends List<? extends Creation>>>() {
            @Override
            public void onSuccess(Pair<? extends Long, ? extends List<? extends Creation>> pair) {
                Long s = pair.component1();
                List<Creation> list = (List<Creation>) pair.component2();
                for (Creation item : list) {
                    Log.e("item", item.getTitle());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        }, new GetCreations.Params("", "", ""));
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
