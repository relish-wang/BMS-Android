package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;
import zjicm.xmt130706121.bms.entity.Book;

/**
 * 改书
 * Created by Relish on 2016/7/6.
 */
public class ModifyBookRequest extends BaseJsonRequest<Void> {
    Book b;

    public ModifyBookRequest(Book b) {
        this.b = b;
    }

    @NonNull
    @Override
    protected String methodName() {
        return "modifyBookServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("sessionId", App.user.getSessionId());
        requestJson.put("book_id", b.getId());
        requestJson.put("name", b.getName());
        requestJson.put("inventory", b.getInventory());
        return requestJson;
    }

    @Override
    protected Void parseResponse(@NonNull JSONObject response) throws JSONException {
        return null;
    }
}
