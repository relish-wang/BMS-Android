package zjicm.xmt130706121.bms.ui.activity;

import android.text.TextUtils;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.base.AppActionBar;
import zjicm.xmt130706121.bms.base.BaseAty;
import zjicm.xmt130706121.bms.base.BaseRequest;
import zjicm.xmt130706121.bms.request.AddBookRequest;

/**
 * Created by Relish on 2016/7/7.
 */
public class AddBookAty extends BaseAty {

    @Bind(R.id.etBookName)
    EditText etBookName;
    @Bind(R.id.etInventory)
    EditText etInventory;

    @Override
    protected int layoutResId() {
        return R.layout.aty_add_book;
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setTitleText("添加书籍");

    }

    @Override
    protected void initViews() {
        etInventory.setText(String.valueOf(1));
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btnAddBook)
    public void addBook() {
        String name = etBookName.getText().toString();
        String inventory = etInventory.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showMessage("书名不得为空！");
            return;
        }
        if (TextUtils.isEmpty(inventory)) {
            showMessage("库存不得为空！");
            return;
        }
        AddBookRequest request = new AddBookRequest(name, Integer.parseInt(inventory));
        request.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showLoading(false);
                showMessage(name + "添加成功！");
                setResult(0x44);
                finish();
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
}
