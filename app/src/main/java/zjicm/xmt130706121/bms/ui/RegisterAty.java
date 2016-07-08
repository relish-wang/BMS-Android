package zjicm.xmt130706121.bms.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.base.AppActionBar;
import zjicm.xmt130706121.bms.base.BaseAty;
import zjicm.xmt130706121.bms.base.BaseRequest;
import zjicm.xmt130706121.bms.request.RegisterRequest;

/**
 * Created by Relish on 2016/7/7.
 */
public class RegisterAty extends BaseAty {

    @Bind(R.id.etName)
    EditText etName;
    @Bind(R.id.etPwd)
    EditText etPwd;
    @Bind(R.id.etRepeadPwd)
    EditText etRepeadPwd;


    @Override
    protected int layoutResId() {
        return R.layout.aty_register;
    }

    @Override
    protected void initActionBar(AppActionBar appActionBar) {
        appActionBar.setTitleText("注册");
    }

    @Override
    protected void initViews() {

    }

    @SuppressWarnings("unused")
    @OnClick(R.id.btnRegister)
    public void register() {
        final String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        String repeatPwd = etRepeadPwd.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showMessage("用户名不得为空！");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showMessage("密码不得为空");
            return;
        }
        if (!TextUtils.equals(pwd, repeatPwd)) {
            showMessage("两次密码输入不一致！");
            return;
        }
        RegisterRequest registerRequest = new RegisterRequest(name, pwd);
        registerRequest.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showLoading(false);
                Intent data = new Intent();
                data.putExtra("name", name);
                setResult(0x11, data);
                showMessage("欢迎" + name + "加入！");
                finish();
            }

            @Override
            public void onFail(String message) {
                showLoading(false);
                showMessage("注册失败");
            }
        });
        registerRequest.request();
        showLoading(true);

    }

}
