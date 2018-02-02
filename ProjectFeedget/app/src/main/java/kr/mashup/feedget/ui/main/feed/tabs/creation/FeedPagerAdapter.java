package kr.mashup.feedget.ui.main.feed.tabs.creation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FeedPagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] fragments;

    public FeedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(Fragment[] fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (isVaildPosition(position) == false) {
            throw new IndexOutOfBoundsException("position이 올바르지 않습니다. position : " + position);
        }

        return fragments[position];
    }

    private boolean isVaildPosition(int position) {
        if (fragments == null) {
            throw new NullPointerException("position을 계산할 수 없는 상황입니다.");
        }

        return position >= 0 && position < fragments.length;
    }

    @Override
    public int getCount() {
        if (fragments == null) return 0;
        return fragments.length;
    }
}
