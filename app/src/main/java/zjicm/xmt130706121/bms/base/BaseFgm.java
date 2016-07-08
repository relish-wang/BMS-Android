package zjicm.xmt130706121.bms.base;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.utils.AppToast;

/**
 * @author 王鑫
 */

public abstract class BaseFgm extends Fragment implements BaseView {
    private static final String TAG = BaseFgm.class.getSimpleName();

    public BaseFgm() {
        super();
    }


    private boolean isLoading;
    private View loadingView;
    private View contentView;
    protected View noMoreDataView;

    private ObjectAnimator contentViewAnimator;

    protected abstract int layoutResId();

    protected void initPresenter() {
    }

    protected abstract void initView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resId = layoutResId();
        if (resId != 0) {
            FrameLayout flRoot = (FrameLayout) inflater.inflate(R.layout.fgm_base, container, false);
            loadingView = flRoot.findViewById(R.id.tvLoading);
            noMoreDataView = flRoot.findViewById(R.id.tvNoMoreData);
            contentView = inflater.inflate(layoutResId(), container, false);
            contentViewAnimator = ObjectAnimator.ofFloat(contentView, "alpha", 0f, 1f);
            contentViewAnimator.setDuration(500);
            flRoot.addView(contentView);
            ButterKnife.bind(this, contentView);

            initPresenter();

            initView();

            return flRoot;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void showLoading(boolean shouldLoading) {
        if (shouldLoading && !isLoading) {
            // 显示加载界面
            isLoading = true;
            contentView.setVisibility(View.INVISIBLE);
            loadingView.setVisibility(View.VISIBLE);
        } else if (!shouldLoading && isLoading) {
            //　隐藏加载界面
            isLoading = false;
            loadingView.setVisibility(View.INVISIBLE);
            contentViewAnimator.start();
            contentView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        AppToast.showShort(message);
    }

    @Override
    public void update() {

    }

    public void goActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    public void goActivity(Class<?> cls, Bundle... bundles) {
        Intent intent = new Intent(getActivity(), cls);
        for (Bundle bundle : bundles) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        startActivityForResult(intent, requestCode);
    }

    public void goActivityForResult(Class<?> cls, int requestCode, Bundle... bundles) {
        Intent intent = new Intent(getActivity(), cls);
        for (Bundle bundle : bundles) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void noMoreData() {
        noMoreDataView.setVisibility(View.VISIBLE);
    }

    public void showData() {
        noMoreDataView.setVisibility(View.INVISIBLE);
    }


}
