package zjicm.xmt130706121.bms.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.base.AppActionBar;
import zjicm.xmt130706121.bms.base.BaseAty;
import zjicm.xmt130706121.bms.base.BaseRequest;
import zjicm.xmt130706121.bms.entity.Record4Admin;
import zjicm.xmt130706121.bms.request.ReturnBookRequest;

/**
 * Created by Relish on 2016/7/7.
 */
public class BorrowAty extends BaseAty {

    @Bind(R.id.tvRecordId)
    TextView tvRecordId;
    @Bind(R.id.tvBorrowTime)
    TextView tvBorrowTime;
    @Bind(R.id.tvBookId)
    TextView tvBookId;
    @Bind(R.id.tvBookName)
    TextView tvBookName;
    @Bind(R.id.tvInventory)
    TextView tvInventory;
    @Bind(R.id.tvUserId)
    TextView tvUserId;
    @Bind(R.id.tvUsername)
    TextView tvUsername;

    boolean isReturned = false;
    int position = -1;
    Record4Admin record;

    @Override
    protected int layoutResId() {
        return R.layout.aty_borrow;
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int p = bundle.getInt("position");
                Record4Admin r = (Record4Admin) bundle.getSerializable("record");
                if (p == -1 || r == null) {
                    finish();
                }
                position = p;
                record = r;
            } else {
                finish();
            }
        } else {
            finish();
        }
        appActionBar.setBackOnClick((View v) -> back());
        appActionBar.setTitleText("借书记录");
    }

    @Override
    protected void initViews() {
        Log.e("post","tvRecordId = "+tvRecordId);
        Log.e("post","record = "+record);
        tvRecordId.setText(String.valueOf(record.getId()));
        tvBorrowTime.setText(record.getBorrowTime());
        tvBookId.setText(String.valueOf(record.getBook().getId()));
        tvBookName.setText(record.getBook().getName());
        tvInventory.setText(String.valueOf(record.getBook().getInventory()));
        tvUserId.setText(String.valueOf(record.getUser().getId()));
        tvUsername.setText(record.getUser().getName());
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btnReturnBook)
    public void returnBook() {
        ReturnBookRequest request = new ReturnBookRequest(record.getId());
        request.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showLoading(false);
                showMessage("归还成功！");
                isReturned = true;
                back();
            }

            @Override
            public void onFail(String message) {
                showLoading(false);
                showMessage("网络连接失败");
            }
        });
        request.request();
        showLoading(true);
    }

    private void back() {
        if (isReturned) {
            Intent data = new Intent();
            data.putExtra("position", position);
            setResult(0x33, data);
            finish();
        } else {
            finish();
        }
    }
}
