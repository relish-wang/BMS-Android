package zjicm.xmt130706121.bms.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * viewHolder基础类
 *
 * @author 王鑫
 *         Created by 鑫 on 2015/11/9.
 */
public abstract class BaseHolder {

    public BaseHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
