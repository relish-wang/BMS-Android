package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;

/**
 * 退出登录
 * Created by Relish on 2016/7/5.
 */
public class LogoutRequest extends BaseJsonRequest<Void> {
    @NonNull
    @Override
    protected String methodName() {
        return "logoutServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("sessionId", App.user.getSessionId());
        return requestJson;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
