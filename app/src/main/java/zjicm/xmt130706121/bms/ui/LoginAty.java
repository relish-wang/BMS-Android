package zjicm.xmt130706121.bms.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.base.AppActionBar;
import zjicm.xmt130706121.bms.base.BaseAty;
import zjicm.xmt130706121.bms.base.BaseRequest;
import zjicm.xmt130706121.bms.request.LoginRequest;

/**
 * Created by Relish on 2016/7/7.
 */
public class LoginAty extends BaseAty {

    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etPwd)
    EditText etPwd;


    @Override
    protected int layoutResId() {
        return R.layout.aty_login;
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.hideBtnBack();
        appActionBar.setTitleText("登录");
        appActionBar.setCustomText("注册");
        appActionBar.setCustomOnClick((View v) -> goActivityForResult(RegisterAty.class, 0x11));
    }

    @Override
    protected void initViews() {
        etName.setText(App.user.getName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
            etName.setText(data.getStringExtra("name"));
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btnLogin)
    public void login() {
        String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showMessage("用户名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showMessage("密码不得为空！");
            return;
        }
        LoginRequest request = new LoginRequest(name, pwd);
        request.setOnResponseListener(new BaseRequest.OnResponseListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                showLoading(false);
                goActivity(MainAty.class);
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
