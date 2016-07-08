package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;
import zjicm.xmt130706121.bms.entity.Book;
import zjicm.xmt130706121.bms.entity.Record4Admin;
import zjicm.xmt130706121.bms.entity.User;
import zjicm.xmt130706121.bms.utils.TimeUtils;

/**
 * 借书列表
 * Created by Relish on 2016/7/6.
 */
public class ViewBorrowRequest extends BaseJsonRequest<ArrayList<Record4Admin>> {


    @NonNull
    @Override
    protected String methodName() {
        return "viewBorrowServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        requestJson.put("sessionId", App.user.getSessionId());
        return requestJson;
    }

    @Override
    protected ArrayList<Record4Admin> parseResponse(@NonNull JSONObject response) throws JSONException {
        ArrayList<Record4Admin> records = new ArrayList<>();
        JSONArray arr = response.getJSONArray("data");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject jo = arr.getJSONObject(i);
            Record4Admin record = new Record4Admin();

            User u = new User();
            u.setId(jo.getInt("user_id"));
            u.setName(jo.getString("user_name"));
            record.setUser(u);

            Book b = new Book();
            b.setId(jo.getInt("book_id"));
            b.setName(jo.getString("book_name"));
            b.setInventory(jo.getInt("inventory"));
            record.setBook(b);

            record.setId(jo.getInt("record_id"));
            record.setBorrowTime(TimeUtils.longToTimeHMFormat(jo.getJSONObject("borrow_time").getLong("time")));

            records.add(record);
        }
        return records;
    }
}
