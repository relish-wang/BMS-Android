package zjicm.xmt130706121.bms.request;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import zjicm.xmt130706121.bms.base.BaseJsonRequest;

/**
 * Created by Relish on 2016/7/7.
 */
public class ViewUsernameRequest extends BaseJsonRequest<ArrayList<String>> {
    @NonNull
    @Override
    protected String methodName() {
        return "viewUsernameServlet";
    }

    @NonNull
    @Override
    protected JSONObject postRequest(JSONObject json) throws JSONException {
        return json;
    }

    @Override
    protected ArrayList<String> parseResponse(@NonNull JSONObject response) throws JSONException {
        JSONArray data = response.getJSONArray("data");
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            names.add(data.getJSONObject(i).getString("name"));
        }
        return names;
    }
}
