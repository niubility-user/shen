package com.jd.viewkit.templates.model.event;

import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualEvent {
    public static String actionKey = "action";
    public static String eventKeyKey = "eventKey";
    public static String failKey = "fail";
    public static String paramKey = "param";
    public static String paramNameKey = "paramName";
    public static String paramValueKey = "paramValue";
    public static String paramsKey = "params";
    public static String successKey = "success";
    public static String typeClick = "click";
    public static String typeDataUpload = "dataUpload";
    public static String typeKey = "type";
    public static String typeTimeOutEvent = "countdown_end";
    private String action;
    private String eventKey;
    private String failStr;
    private Map<String, Object> paramMap;
    private String paramName;
    private String paramValue;
    private String successStr;
    private String type;

    public JDViewKitVirtualEvent(JSONObject jSONObject) {
        setEventKey(JSONTool.getString(jSONObject, eventKeyKey));
        setType(JSONTool.getString(jSONObject, typeKey));
        setAction(JSONTool.getString(jSONObject, actionKey));
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, paramsKey);
        if (jSONObject2 != null) {
            setParamName(JSONTool.getString(jSONObject2, paramNameKey));
            setParamValue(JSONTool.getString(jSONObject2, paramValueKey));
        }
        JSONObject jSONObject3 = JSONTool.getJSONObject(jSONObject, paramKey);
        if (jSONObject3 != null) {
            setParamMap(JSONTool.getMap(jSONObject3));
        }
        setSuccessStr(JSONTool.getString(jSONObject, successKey));
        setFailStr(JSONTool.getString(jSONObject, failKey));
    }

    public String getAction() {
        return this.action;
    }

    public String getEventKey() {
        return this.eventKey;
    }

    public String getFailStr() {
        return this.failStr;
    }

    public Map<String, Object> getParamMap() {
        return this.paramMap;
    }

    public String getParamName() {
        return this.paramName;
    }

    public String getParamValue() {
        return this.paramValue;
    }

    public String getSuccessStr() {
        return this.successStr;
    }

    public String getType() {
        return this.type;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setEventKey(String str) {
        this.eventKey = str;
    }

    public void setFailStr(String str) {
        this.failStr = str;
    }

    public void setParamMap(Map<String, Object> map) {
        this.paramMap = map;
    }

    public void setParamName(String str) {
        this.paramName = str;
    }

    public void setParamValue(String str) {
        this.paramValue = str;
    }

    public void setSuccessStr(String str) {
        this.successStr = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
