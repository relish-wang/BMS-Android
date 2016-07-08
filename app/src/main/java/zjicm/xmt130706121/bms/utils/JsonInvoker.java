package zjicm.xmt130706121.bms.utils;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @deprecated 被OkHttpInvoker代替
 * <p/>
 * Created by r3lis on 2016/3/15.
 */
public class JsonInvoker {

    public static String postJson(String httpUrl, JSONObject json) throws IOException {
        URL url = new URL(httpUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        connection.connect();

        // POST请求
        DataOutputStream out = new DataOutputStream(
                connection.getOutputStream());
        out.writeBytes(json.toString());
        out.flush();
        out.close();

        // 读取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String lines;
        StringBuilder sb = new StringBuilder("");
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8");
            sb.append(lines);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        return sb.toString();
    }
}
