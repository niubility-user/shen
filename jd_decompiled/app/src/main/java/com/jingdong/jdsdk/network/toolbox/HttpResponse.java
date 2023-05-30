package com.jingdong.jdsdk.network.toolbox;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HttpResponse {
    private int code;
    private Map<String, String> header;
    private Map<String, List<String>> headerFields;
    private byte[] inputData;
    private InputStream inputStream;
    private boolean isCache;
    private JDJSONArray jdjsonArray;
    private JDJSONObject jdjsonObject;
    private JSONArrayPoxy jsonArray;
    private JSONObjectProxy jsonObject;
    private long length;
    private Map<String, Object> moreParams;
    private File saveFile;
    private String shareImagePath;
    private int statusCode;
    private String string;
    private String type;

    public HttpResponse(Map<String, Object> map) {
        this.moreParams = map;
    }

    public int getCode() {
        return this.code;
    }

    public JDJSONArray getFastJsonArray() {
        JDJSONArray jDJSONArray = this.jdjsonArray;
        return jDJSONArray == null ? new JDJSONArray() : jDJSONArray;
    }

    public JDJSONObject getFastJsonObject() {
        JDJSONObject jDJSONObject = this.jdjsonObject;
        return jDJSONObject == null ? new JDJSONObject() : jDJSONObject;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public String getHeaderField(String str) {
        List<String> list;
        Map<String, List<String>> map = this.headerFields;
        if (map == null || (list = map.get(str)) == null || list.size() < 1) {
            return null;
        }
        return list.get(0);
    }

    public Map<String, List<String>> getHeaderFields() {
        return this.headerFields;
    }

    public byte[] getInputData() {
        return this.inputData;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public JSONObjectProxy getJSONObject() {
        JSONObjectProxy jSONObjectProxy = this.jsonObject;
        return jSONObjectProxy == null ? new JSONObjectProxy(new JSONObject()) : jSONObjectProxy;
    }

    public JSONArrayPoxy getJsonArray() {
        JSONArrayPoxy jSONArrayPoxy = this.jsonArray;
        return jSONArrayPoxy == null ? new JSONArrayPoxy(new JSONArray()) : jSONArrayPoxy;
    }

    public long getLength() {
        return this.length;
    }

    public Map<String, Object> getMoreParams() {
        if (this.moreParams == null) {
            this.moreParams = new HashMap();
        }
        return this.moreParams;
    }

    public File getSaveFile() {
        return this.saveFile;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getString() {
        return this.string;
    }

    public String getType() {
        return this.type;
    }

    public boolean isCache() {
        return this.isCache;
    }

    public void setCache(boolean z) {
        this.isCache = z;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setFastJsonArray(JDJSONArray jDJSONArray) {
        this.jdjsonArray = jDJSONArray;
    }

    public void setFastJsonObject(JDJSONObject jDJSONObject) {
        this.jdjsonObject = jDJSONObject;
    }

    public void setHeader(Map<String, String> map) {
        this.header = map;
    }

    public void setHeaderFields(Map<String, List<String>> map) {
        this.headerFields = map;
    }

    public void setInputData(byte[] bArr) {
        this.inputData = bArr;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setJsonArray(JSONArrayPoxy jSONArrayPoxy) {
        this.jsonArray = jSONArrayPoxy;
    }

    public void setJsonObject(JSONObjectProxy jSONObjectProxy) {
        this.jsonObject = jSONObjectProxy;
    }

    public void setLength(long j2) {
        this.length = j2;
    }

    public void setSaveFile(File file) {
        this.saveFile = file;
    }

    public void setShareImagePath(String str) {
        this.shareImagePath = str;
    }

    public void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    public void setString(String str) {
        this.string = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getHeader(String str) {
        Map<String, String> map = this.header;
        if (map == null || map.isEmpty()) {
            return null;
        }
        return this.header.get(str);
    }
}
