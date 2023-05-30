package com.jingdong.common.lbs.search;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDLbsSearchCity {
    private String fullname;
    private String id;
    private int level;
    private String name;
    private String pinyin;

    public String getFirstPinyin() {
        return TextUtils.isEmpty(this.pinyin) ? "" : this.pinyin.substring(0, 1).toUpperCase();
    }

    public String getFullname() {
        String str = this.fullname;
        return str == null ? "" : str;
    }

    public String getId() {
        String str = this.id;
        return str == null ? "" : str;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.id);
            jSONObject.put("name", this.name);
            jSONObject.put("fullname", this.fullname);
            jSONObject.put("level", this.level);
            jSONObject.put("pinyin", this.pinyin);
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public int getLevel() {
        return this.level;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public String getPinyin() {
        String str = this.pinyin;
        return str == null ? "" : str;
    }

    public void setFullname(String str) {
        this.fullname = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLevel(int i2) {
        this.level = i2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPinyin(String str) {
        this.pinyin = str;
    }
}
