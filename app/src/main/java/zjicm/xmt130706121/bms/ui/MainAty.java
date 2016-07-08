package zjicm.xmt130706121.bms.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnPageChange;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.base.AppActionBar;
import zjicm.xmt130706121.bms.base.BaseAty;
import zjicm.xmt130706121.bms.base.BaseFgm;
import zjicm.xmt130706121.bms.entity.Book;
import zjicm.xmt130706121.bms.ui.activity.AddBookAty;
import zjicm.xmt130706121.bms.ui.activity.BorrowBookAty;
import zjicm.xmt130706121.bms.ui.fragment.BooksFgm;
import zjicm.xmt130706121.bms.ui.fragment.BorrowsFgm;

public class MainAty extends BaseAty {

    @Bind(R.id.vp)
    ViewPager vp;
    int currentPage = 0;

    ArrayList<BaseFgm> fragments;
    AppActionBar mAppActionBar;

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        this.mAppActionBar = appActionBar;
        appActionBar.hideBtnBack();
        appActionBar.setTitleText("图书列表");
        appActionBar.setCustomText("+");
        appActionBar.setCustomOnClick((View v) -> {
            switch (currentPage) {
                case 0:
                    goActivityForResult(AddBookAty.class, 0x44);
                    break;
                case 1:
                    goActivityForResult(BorrowBookAty.class,0x55);
                    break;
            }
        });
    }

    @Override
    protected void initViews() {
        fragments = new ArrayList<>();
        fragments.add(new BooksFgm());
        fragments.add(new BorrowsFgm());
//        fragments.add(new ReturnsFgm());

        vp.setAdapter(new VpAdapter(getSupportFragmentManager()));
    }

    @SuppressWarnings("unused")
    @OnPageChange(R.id.vp)
    void onPageSelected(int position) {
        select(position);
    }

    private void select(int position) {
        currentPage = position;
        switch (position) {
            case 0://首页
                vp.setCurrentItem(0);
                mAppActionBar.setTitleText("图书列表");
                mAppActionBar.setCustomText("+");
                break;
            case 1://借书列表
                vp.setCurrentItem(1);
                mAppActionBar.setTitleText("借书记录");
                mAppActionBar.setCustomText("借书");
                break;
//            case 2://还书列表:
//
//                vp.setCurrentItem(2);
//                break;
        }
    }

    class VpAdapter extends FragmentPagerAdapter {

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0x22:
                Book b = (Book) data.getSerializableExtra("book");
                int position = data.getIntExtra("position", -1);
                ((BooksFgm) fragments.get(0)).updateBook(position, b);
                break;
            case 0x33:
                int p = data.getIntExtra("position", -1);
                ((BorrowsFgm) fragments.get(1)).updateRecord(p);
                break;
            case 0x44:
                ((BooksFgm) fragments.get(0)).requestData();
                break;
            case 0x55:
                ((BorrowsFgm) fragments.get(1)).requestData();
                break;
        }
    }
}
