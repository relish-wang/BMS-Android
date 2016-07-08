package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;

/**
 * Created by Relish on 2016/7/6.
 */
public class DeleteBookRequest extends BaseJsonRequest<Void>{

    int bookId;

    public DeleteBookRequest(int bookId) {
        this.bookId = bookId;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "deleteBookServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("sessionId", App.user.getSessionId());
        requestJson.put("book_id",bookId);
        return requestJson;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
