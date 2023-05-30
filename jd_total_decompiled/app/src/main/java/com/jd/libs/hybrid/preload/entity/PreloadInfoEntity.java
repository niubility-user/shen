package com.jd.libs.hybrid.preload.entity;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.anotation.JSONField;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import java.util.List;
import java.util.Map;

@Entity(indices = {@Index({"url"}), @Index({"originalUrl"})}, tableName = "HybridPreloadInfo")
@Keep
/* loaded from: classes.dex */
public class PreloadInfoEntity implements IInterfaceCheck {
    public static final String TYPE_ORIGIN_REGEXP = "2";
    public static final String TYPE_ORIGIN_URL = "1";
    private String appMax;
    private String appMin;
    @NonNull
    @PrimaryKey
    private String appid;
    @ColumnInfo(defaultValue = "0")
    private String bConfig;
    private JDJSONObject body;
    private List<String> extraKeys;
    private String functionId;
    private Map<String, String> header;
    private Map<String, String> mappings;
    private String method;
    @Nullable
    private String originalUrl;
    private String originalUrlType;
    private Map<String, String> params;
    private String requestType;
    private String requestUrl;
    @Nullable
    private String url;
    @JSONField(name = "url_params")
    private int urlParamsState;

    public String getAppMax() {
        return this.appMax;
    }

    public String getAppMin() {
        return this.appMin;
    }

    @NonNull
    public String getAppid() {
        return this.appid;
    }

    public String getBConfig() {
        return this.bConfig;
    }

    public JDJSONObject getBody() {
        return this.body;
    }

    public List<String> getExtraKeys() {
        return this.extraKeys;
    }

    @NonNull
    public String getFunctionId() {
        return this.functionId;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public Map<String, String> getMappings() {
        return this.mappings;
    }

    public String getMethod() {
        return this.method;
    }

    @Nullable
    public String getOriginalUrl() {
        if (TextUtils.isEmpty(this.originalUrl)) {
            return null;
        }
        return this.originalUrl;
    }

    public String getOriginalUrlType() {
        return this.originalUrlType;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public String getRequestTypeStr() {
        return "1".equalsIgnoreCase(this.requestType) ? "JSONP" : "\u666e\u901a\u63a5\u53e3";
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }

    public int getUrlParamsState() {
        return this.urlParamsState;
    }

    public boolean isRegexpMatch() {
        return "2".equals(this.originalUrlType) && !TextUtils.isEmpty(this.originalUrl);
    }

    public void setAppMax(String str) {
        this.appMax = str;
    }

    public void setAppMin(String str) {
        this.appMin = str;
    }

    public void setAppid(@NonNull String str) {
        this.appid = str;
    }

    public void setBConfig(String str) {
        this.bConfig = str;
    }

    public void setBody(JDJSONObject jDJSONObject) {
        this.body = jDJSONObject;
    }

    public void setExtraKeys(List<String> list) {
        this.extraKeys = list;
    }

    public void setFunctionId(@NonNull String str) {
        this.functionId = str;
    }

    public void setHeader(Map<String, String> map) {
        this.header = map;
    }

    public void setMappings(Map<String, String> map) {
        this.mappings = map;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setOriginalUrl(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.originalUrl = str;
    }

    public void setOriginalUrlType(String str) {
        this.originalUrlType = str;
    }

    public void setParams(Map<String, String> map) {
        this.params = map;
    }

    public void setRequestType(@NonNull String str) {
        this.requestType = str;
    }

    public void setRequestUrl(String str) {
        this.requestUrl = str;
    }

    public void setUrl(@NonNull String str) {
        this.url = str;
    }

    public void setUrlParamsState(int i2) {
        this.urlParamsState = i2;
    }

    @Override // com.jd.libs.hybrid.base.entity.IInterfaceCheck
    public boolean useful() {
        return !TextUtils.isEmpty(this.appid);
    }
}
