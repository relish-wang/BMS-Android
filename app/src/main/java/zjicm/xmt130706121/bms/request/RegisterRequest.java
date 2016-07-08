package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import zjicm.xmt130706121.bms.base.BaseJsonRequest;

/**
 * Created by Relish on 2016/7/5.
 */
public class RegisterRequest extends BaseJsonRequest<Void> {

    String name;
    String pwd;

    public RegisterRequest(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "registerServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("name", name);
        requestJson.put("pwd", pwd);
        return requestJson;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
