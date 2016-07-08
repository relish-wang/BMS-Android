package zjicm.xmt130706121.bms.base;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zjicm.xmt130706121.bms.R;


/**
 * Created by r3lis on 2016/3/31.
 */
public class AppActionBar extends RelativeLayout {


    View contentView;
    RelativeLayout rlBack;
    TextView tvTitle;
    TextView tvCustom;


    public AppActionBar(Context context) {
        super(context);
        initViews(context);
    }

    public AppActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public AppActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        contentView = LayoutInflater.from(context).inflate(R.layout.actionbar, this)
                .findViewById(R.id.rlActionBarContent);
        rlBack = (RelativeLayout) contentView.findViewById(R.id.rlBack);
        tvTitle = (TextView) contentView.findViewById(R.id.tvTitle);
        tvCustom = (TextView) contentView.findViewById(R.id.tvCustom);
        tvTitle.setTextColor(ContextCompat.getColor(context,R.color.white));
        tvCustom.setTextColor(ContextCompat.getColor(context,R.color.white));
    }

    public void setBackOnClick(OnClickListener onClick) {
        rlBack.setOnClickListener(onClick);
    }

    public void setCustomText(String text) {
        tvCustom.setVisibility(View.VISIBLE);
        tvCustom.setText(text);
    }

    public void setCustomOnClick(OnClickListener onClick) {
        tvCustom.setOnClickListener(onClick);
    }

    public void setTitleText(String text){
        tvTitle.setText(text);
    }

    /**
     * 获得actionbar的标题文本
     *
     * @return title actionbar的标题
     */
    public String getActionBarTitle() {
        return tvTitle.getText().toString();
    }

    /**
     * 获得actionbar的标题文本
     *
     * @return text 自定义文本的文字
     */
    public String getCustomText() {
        if (tvCustom.getVisibility() != View.VISIBLE) {
            return "";
        }
        return tvCustom.getText().toString();
    }

    public void hideBtnBack() {
        rlBack.setVisibility(View.INVISIBLE);
    }

    public void hideBtnCustom() {
        tvCustom.setVisibility(View.INVISIBLE);
    }

    public void hide() {
        this.setVisibility(View.GONE);
    }

    public void show() {
        this.setVisibility(View.VISIBLE);
    }

}

