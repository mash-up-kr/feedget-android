package kr.mashup.feedget.ui.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import kr.mashup.feedget.EmptyFragment;
import kr.mashup.feedget.R;
import kr.mashup.feedget.ui.main.feed.FeedFragment;

/**
 * Created by ichaeeun on 2018. 1. 6..
 */

public class MainActivity extends AppCompatActivity {

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
                        Fragment emptyFragment = new Fragment();
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

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        FeedFragment feedFragment = new FeedFragment();
        fragmentTransaction.replace(R.id.container, feedFragment);
        fragmentTransaction.commit();
    }

}
