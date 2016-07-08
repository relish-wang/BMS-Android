package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;

/**
 * 添书
 * Created by Relish on 2016/7/6.
 */
public class AddBookRequest extends BaseJsonRequest<Void> {

    String name;
    Integer inventory;

    public AddBookRequest(String name, Integer inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "addBookServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("sessionId", App.user.getSessionId());
        requestJson.put("name",name);
        requestJson.put("inventory",inventory);
        return requestJson;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
