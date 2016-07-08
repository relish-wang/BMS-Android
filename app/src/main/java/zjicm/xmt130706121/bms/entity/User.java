package zjicm.xmt130706121.bms.entity;

import android.text.TextUtils;

import java.io.Serializable;

import zjicm.xmt130706121.bms.utils.AppPreference;

/**
 * 用户实体类（包括借阅者和管理员）
 * Created by Relish on 2016/7/3.
 */
public class User implements Serializable{

    private Integer id;
    private String name;
    private String pwd;
    private Integer type;
    private String sessionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        if (TextUtils.isEmpty(name)) {
            return AppPreference.getString("name", "");
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        if (TextUtils.isEmpty(pwd)) {
            return AppPreference.getString("pwd", "");
        }
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getType() {
        if (type == null || type == 0) {
            return AppPreference.getInt("type", 1);
        }
        return type;
    }

    public void setType(Integer type) {
        AppPreference.put("type", type);
        this.type = type;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
