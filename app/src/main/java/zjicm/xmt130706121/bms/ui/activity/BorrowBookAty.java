package zjicm.xmt130706121.bms.ui.activity;

import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.base.AppActionBar;
import zjicm.xmt130706121.bms.base.BaseAty;
import zjicm.xmt130706121.bms.base.BaseRequest;
import zjicm.xmt130706121.bms.entity.Book;
import zjicm.xmt130706121.bms.request.BorrowBookRequest;
import zjicm.xmt130706121.bms.request.ViewBooksRequest;
import zjicm.xmt130706121.bms.request.ViewUsernameRequest;

/**
 * Created by Relish on 2016/7/7.
 */
public class BorrowBookAty extends BaseAty {


    @Bind(R.id.spBookName)
    Spinner spBookName;
    @Bind(R.id.spUsername)
    Spinner spUsername;

    ArrayList<String> bookNames;
    ArrayList<String> usernames;


    @Override
    protected int layoutResId() {
        return R.layout.aty_borrow_book;
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setTitleText("借书");
    }

    @Override
    protected void initViews() {
        ViewBooksRequest viewBooksRequest = new ViewBooksRequest();
        viewBooksRequest.setOnResponseListener(new BaseRequest.OnResponseListener<ArrayList<Book>>() {
            @Override
            public void onSuccess(ArrayList<Book> books) {
                showLoading(false);
                bookNames = new ArrayList<>();
                for (Book book : books) {
                    bookNames.add(book.getName());
                }

                // 建立数据源
                String[] bookNameArr = new String[bookNames.size()];
                for (int i = 0; i < bookNames.size(); i++) {
                    bookNameArr[i] = bookNames.get(i);
                }
                // 建立Adapter并且绑定数据源
                ArrayAdapter<String> bookNameAdapter =
                        new ArrayAdapter<>(
                                BorrowBookAty.this,
                                android.R.layout.simple_spinner_item,
                                bookNameArr);
                //绑定 Adapter到控件
                spBookName.setAdapter(bookNameAdapter);
            }

            @Override
            public void onFail(String message) {
                showLoading(false);
            }
        });
        viewBooksRequest.request();
        showLoading(true);


        ViewUsernameRequest viewUsernameRequest = new ViewUsernameRequest();
        viewUsernameRequest.setOnResponseListener(new BaseRequest.OnResponseListener<ArrayList<String>>() {
            @Override
            public void onSuccess(ArrayList<String> names) {
                showLoading(false);
                usernames = new ArrayList<>();
                usernames.addAll(names);

                // 建立Adapter并且绑定数据源
                ArrayAdapter<String> usernameAdapter =
                        new ArrayAdapter<>(
                                BorrowBookAty.this,
                                android.R.layout.simple_spinner_item,
                                usernames);
                //绑定 Adapter到控件
                spUsername.setAdapter(usernameAdapter);
            }

            @Override
            public void onFail(String message) {
                showLoading(false);
            }
        });
        viewUsernameRequest.request();
        showLoading(true);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btnBorrowBook)
    public void borrowBook() {

        String bookName = spBookName.getSelectedItem().toString();
        String username = spUsername.getSelectedItem().toString();
        if (TextUtils.isEmpty(bookName)) {
            showMessage("书名不得为空！");
            return;
        }
        if (TextUtils.isEmpty(username)) {
            showMessage("借阅者姓名不得为空！");
            return;
        }
        BorrowBookRequest request = new BorrowBookRequest(username, bookName);
        request.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showLoading(false);
                showMessage("借阅成功！");
                setResult(0x55);
                finish();
            }

            @Override
            public void onFail(String message) {
                showLoading(false);
                showMessage(message);
            }
        });
        request.request();
        showLoading(true);
    }
}
