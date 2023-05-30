package com.jd.libs.hybrid.offlineload.entity;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Ignore;
import com.jd.libs.hybrid.base.entity.IClone;
import com.jd.libs.hybrid.base.entity.IJsonfy;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes16.dex */
public class FileDetail implements IJsonfy<FileDetail>, IClone<FileDetail>, Cloneable {
    private long lastModified;
    private String path;
    private long totalSpace;
    @Ignore
    private int versionCode;

    public FileDetail() {
        this.versionCode = -1;
    }

    public boolean exists() {
        return !new File(this.path).exists();
    }

    public long getLastModified() {
        return this.lastModified;
    }

    public String getPath() {
        return this.path;
    }

    public long getTotalSpace() {
        return this.totalSpace;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public boolean hasChanged() {
        File file = new File(this.path);
        return (file.exists() && file.lastModified() == this.lastModified) ? false : true;
    }

    public void setLastModified(long j2) {
        this.lastModified = j2;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setTotalSpace(long j2) {
        this.totalSpace = j2;
    }

    public void setVersionCode(int i2) {
        this.versionCode = i2;
    }

    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("path", getPath());
        jSONObject.put("last_modified", getLastModified());
        jSONObject.put("total_space", getTotalSpace());
        jSONObject.put("version_code", getVersionCode());
        return jSONObject;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public FileDetail fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            setPath(jSONObject.optString("path", ""));
            setLastModified(jSONObject.optLong("last_modified", 0L));
            setTotalSpace(jSONObject.optLong("total_space", 0L));
            setVersionCode(jSONObject.optInt("version_code", -1));
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.base.entity.IClone
    @NonNull
    public FileDetail publicClone() throws CloneNotSupportedException {
        return (FileDetail) super.clone();
    }

    @Ignore
    @Deprecated
    public FileDetail(String str, long j2, long j3) {
        this.versionCode = -1;
        this.path = str;
        this.lastModified = j2;
        this.totalSpace = j3;
    }

    @Ignore
    @Deprecated
    public FileDetail(File file) {
        this.versionCode = -1;
        this.path = file.getAbsolutePath();
        this.lastModified = file.lastModified();
        this.totalSpace = file.length();
    }

    @Ignore
    public FileDetail(File file, int i2) {
        this.versionCode = -1;
        this.path = file.getAbsolutePath();
        this.lastModified = file.lastModified();
        this.totalSpace = file.length();
        this.versionCode = i2;
    }
}
