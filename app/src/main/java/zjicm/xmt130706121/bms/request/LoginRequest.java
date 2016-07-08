package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;
import zjicm.xmt130706121.bms.entity.User;
import zjicm.xmt130706121.bms.utils.AppPreference;

/**
 * 登录请求
 * Created by Relish on 2016/7/4.
 */
public class LoginRequest extends BaseJsonRequest<Integer> {


    public static final String LOGIN_METHOD_NAME = "loginServlet";

    private String name;
    private String pwd;

    public LoginRequest(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }


    @NonNull
    @Override
    protected String methodName() {
        return LOGIN_METHOD_NAME;
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("name", name);
        requestJson.put("pwd", pwd);
        return requestJson;
    }

    @Override
    protected Integer parseResponse(@NonNull JSONObject response) throws JSONException {
        JSONObject data = response.getJSONObject("data");
        User user = new User();
        user.setSessionId(data.getString("sessionId"));
        user.setId(data.getInt("id"));
        user.setName(data.getString("name"));
        user.setType(data.getInt("type"));

        AppPreference.put("name", user.getName());
        AppPreference.put("pwd", user.getPwd());

        App.user = user;
        return data.getInt("type");
    }

}
