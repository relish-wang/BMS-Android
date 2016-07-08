package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.base.BaseJsonRequest;
import zjicm.xmt130706121.bms.entity.Book;

/**
 * 获取书籍列表
 * Created by Relish on 2016/7/6.
 */
public class ViewBooksRequest extends BaseJsonRequest<ArrayList<Book>> {

    @NonNull
    @Override
    protected String methodName() {
        return "viewBookServlet";
    }

    @Override
    protected JSONObject postRequest(JSONObject requestJson) throws JSONException {
        return requestJson;
    }

    @Override
    protected ArrayList<Book> parseResponse(@NonNull JSONObject response) throws JSONException {
        ArrayList<Book> books = new ArrayList<>();
        JSONArray arr = response.getJSONArray("data");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject json = arr.getJSONObject(i);
            Book book = new Book();
            book.setId(json.getInt("id"));
            book.setName(json.getString("name"));
            book.setInventory(json.getInt("inventory"));
            books.add(book);
        }
        return books;
    }
}
