package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;

/**
 * 借书
 * Created by Relish on 2016/7/6.
 */
public class BorrowBookRequest extends BaseJsonRequest<Void> {

    String bookName;
    String username;

    public BorrowBookRequest(String username, String bookName) {
        this.bookName = bookName;
        this.username = username;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "borrowBookServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("sessionId", App.user.getSessionId());
        requestJson.put("admin_id", App.user.getId());
        requestJson.put("book_name", bookName);
        requestJson.put("user_name", username);
        return requestJson;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
