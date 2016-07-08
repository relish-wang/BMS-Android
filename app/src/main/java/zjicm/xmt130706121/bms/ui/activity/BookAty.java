package zjicm.xmt130706121.bms.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.base.AppActionBar;
import zjicm.xmt130706121.bms.base.BaseAty;
import zjicm.xmt130706121.bms.base.BaseRequest;
import zjicm.xmt130706121.bms.entity.Book;
import zjicm.xmt130706121.bms.request.ModifyBookRequest;

/**
 * Created by Relish on 2016/7/7.
 */
public class BookAty extends BaseAty {

    Book book;

    boolean isView = true;

    int position = -1;

    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etInventory)
    EditText etInventory;

    @Bind(R.id.tvBookId)
    TextView tvId;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvInventory)
    TextView tvInventory;
    boolean isModified = false;

    @Override
    protected int layoutResId() {
        return R.layout.aty_book;
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                position = bundle.getInt("position");
                Book b = (Book) bundle.getSerializable("book");
                if (b != null) {
                    book = new Book();
                    book.setId(b.getId());
                    book.setName(b.getName());
                    book.setInventory(b.getInventory());
                } else {
                    finish();
                }
            }
        }
        appActionBar.setBackOnClick((View v) -> onBackPressed());
        appActionBar.setTitleText(book.getName());
        appActionBar.setCustomText("修改");
        appActionBar.setCustomOnClick((View v) -> {
            if (isView) {
                tvInventory.setVisibility(View.GONE);
                tvName.setVisibility(View.GONE);
                etInventory.setVisibility(View.VISIBLE);
                etName.setVisibility(View.VISIBLE);
                appActionBar.setCustomText("确认");
                isView = !isView;
            } else {
                String name = etName.getText().toString();
                String inventory = etInventory.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    showMessage("书名不得为空！");
                    return;
                }
                if (TextUtils.isEmpty(inventory)) {
                    showMessage("库存不得为空！");
                    return;
                }
                book.setName(name);
                book.setInventory(Integer.parseInt(inventory));

                ModifyBookRequest request = new ModifyBookRequest(book);
                request.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showLoading(false);
                        tvInventory.setVisibility(View.VISIBLE);
                        tvName.setVisibility(View.VISIBLE);
                        etInventory.setVisibility(View.GONE);
                        etName.setVisibility(View.GONE);
                        book.setName(name);
                        book.setInventory(Integer.parseInt(inventory));
                        tvName.setText(name);
                        tvInventory.setText(inventory);
                        etName.setText(name);
                        etName.setHint(name);
                        etInventory.setText(inventory);
                        etInventory.setHint(inventory);
                        appActionBar.setCustomText("修改");
                        isView = !isView;
                        isModified = true;
                    }

                    @Override
                    public void onFail(String message) {
                        showLoading(false);
                        showMessage("网络无法连接");
                    }
                });
                request.request();
                showLoading(true);
            }
        });
    }

    @Override
    protected void initViews() {
        tvId.setText(String.valueOf(book.getId()));
        tvName.setText(book.getName());
        tvInventory.setText(String.valueOf(book.getInventory()));
        etName.setText(book.getName());
        etInventory.setText(String.valueOf(book.getInventory()));
    }

    @Override
    public void onBackPressed() {
        if (isModified) {
            Intent intent = new Intent();
            intent.putExtra("book", book);
            intent.putExtra("position", position);
            setResult(0x22, intent);
            finish();
        } else {
            finish();
        }
    }
}
