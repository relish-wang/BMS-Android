package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;

/**
 * 还书
 * Created by Relish on 2016/7/6.
 */
public class ReturnBookRequest extends BaseJsonRequest<Void> {

    int recordId;

    public ReturnBookRequest(int recordId) {
        this.recordId = recordId;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "returnBookServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("sessionId", App.user.getSessionId());
        requestJson.put("record_id", recordId);
        return requestJson;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
