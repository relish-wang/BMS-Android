package zjicm.xmt130706121.bms.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import zjicm.xmt130706121.bms.App;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.utils.AppLog;
import zjicm.xmt130706121.bms.utils.OkHttpInvoker;

public abstract class BaseFileRequest<T> extends BaseRequest<T> {

    @NonNull
    protected abstract JSONObject json() throws JSONException;

    @Nullable
    protected abstract byte[] voice();

    @NonNull
    @Override
    public Response<T> getResponse() {
        Response<T> response = new Response<>();

        // 检查url
        String url = BASE_URL + methodName();
        if (TextUtils.isEmpty(url)) {
            AppLog.e("BaseFileRequest", "RequestTask", "InValid url!! url=null or url=\"\"");
            response.errorMessage = App.CONTEXT.getString(R.string.error_url);
            return response;
        }

        // 检查json
        JSONObject json = null;
        try {
            json = json();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (json == null) {
            AppLog.e("BaseFileRequest", "RequestTask", "InValid json!! json=null or package json error");
            response.errorMessage = App.CONTEXT.getString(R.string.error_data_encapsulation);
            return response;
        }

        // 发送请求
        String strResponse;
        try {
            strResponse = OkHttpInvoker.postFiles(url, json.toString(), voice());
        } catch (IOException e) {
            e.printStackTrace();
            response.errorMessage = App.CONTEXT.getString(R.string.error_network_connection);
            return response;
        }

        // 解析返回数据
        JSONObject jsonResponse;
        try {
            jsonResponse = new JSONObject(strResponse);

            int resultCode = jsonResponse.getInt("resultCode");
            response.isSuccess = resultCode == 0;
            if (response.isSuccess) { // 获取信息成功
                response.data = parseResponse(jsonResponse);
                return response;
            } else if (resultCode == 3) { // 获取信息失败，session过期

//                // 重新登录
//                if (LoginRequest.reLogin(User.getUsername(), User.getPassword())) { // 重新登录成功
//                    // 重新连接服务
//                    strResponse = OkHttpInvoker.postFiles(url, json.toString(), voice());
//                    jsonResponse = new JSONObject(strResponse);
//                    resultCode = jsonResponse.getInt("resultCode");
//                    response.isSuccess = resultCode == 0;
//                    if (response.isSuccess) { // 重新连接服务并获取信息成功
//                        response.data = parseResponse(jsonResponse);
//                        return response;
//                    } else { // 重新连接服务，但获取信息失败
//                        response.errorMessage = parseErrorMessage(resultCode);
//                        if (response.errorMessage == null) {
//                            response.errorMessage = jsonResponse.getString("resultMessage");
//                        }
//                        return response;
//                    }
//                } else { // 重新登录失败，清空activity堆栈，跳转到登录界面
//                    AppToast.showShort("用户信息失效，请重新登录");
//                    AppContext.clearActivitiesWithout(LoginAty.class.getSimpleName());
//                    response.errorMessage = "用户信息失效，请重新登录";
                return response;
//                }

            } else { // 获取信息失败，其他原因
                response.errorMessage = parseErrorMessage(resultCode);
                if (response.errorMessage == null) {
                    response.errorMessage = jsonResponse.getString("resultMessage");
                }
                return response;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            response.isSuccess = false;
            response.errorMessage = App.CONTEXT.getString(R.string.error_data_parse);
            return response;
        }
//        catch (IOException e) {
//            e.printStackTrace();
//            response.isSuccess = false;
//            response.errorMessage = "网络连接错误";
//            return response;
//        }
    }
}
