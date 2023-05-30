package com.jd.hybrid.downloader.m;

import android.text.TextUtils;
import com.jd.libs.hybrid.base.entity.IClone;
import com.jd.libs.hybrid.base.entity.IJsonfy;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a implements IJsonfy<a>, IClone<a>, Cloneable, b {
    public String app_max;
    public String app_min;
    public List<String> demand_classes;
    public String filePath;
    public String file_type;
    public String id;
    public String md5;
    public String nameSpace;
    public String oldFilePath;
    public int old_version_code;
    public String originalUrl;
    public int project_priority;
    public String source;
    public int status;
    public String url;
    public int version_code;

    public void cover(a aVar) {
        if (this.version_code > aVar.version_code) {
            if (aVar.filePath != null && new File(aVar.filePath).exists()) {
                this.oldFilePath = aVar.filePath;
                this.old_version_code = aVar.version_code;
            }
            this.status = 0;
            return;
        }
        this.filePath = aVar.filePath;
        this.oldFilePath = aVar.oldFilePath;
        this.old_version_code = aVar.old_version_code;
        this.status = aVar.status;
    }

    @Override // com.jd.hybrid.downloader.m.b
    public String getFilePath() {
        return this.filePath;
    }

    @Override // com.jd.hybrid.downloader.m.b
    public String getId() {
        return this.id;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getNameSpace() {
        return this.nameSpace;
    }

    @Override // com.jd.hybrid.downloader.m.b
    public int getStatus() {
        return this.status;
    }

    public void onDownloaded(String str) {
        this.status = 1;
        this.oldFilePath = null;
        this.old_version_code = 0;
        this.filePath = str;
    }

    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public synchronized JSONObject toJson() throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        jSONObject.put("originalUrl", this.url);
        jSONObject.put("fileId", this.id);
        jSONObject.put("md5", this.md5);
        jSONObject.put("app_min", this.app_min);
        jSONObject.put("app_max", this.app_max);
        jSONObject.put("version_code", this.version_code);
        jSONObject.put("nameSpace", this.nameSpace);
        jSONObject.put("source", this.source);
        jSONObject.put("project_priority", this.project_priority);
        jSONObject.put(TbsReaderView.KEY_FILE_PATH, this.filePath);
        jSONObject.put("oldFilePath", this.oldFilePath);
        jSONObject.put("old_version_code", this.old_version_code);
        jSONObject.put("file_type", this.file_type);
        if (this.demand_classes != null) {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = this.demand_classes.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put("demand_classes", jSONArray);
        }
        return jSONObject;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public synchronized a fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return this;
        }
        this.url = jSONObject.optString("url");
        this.originalUrl = jSONObject.optString("url");
        this.id = jSONObject.optString("fileId");
        this.md5 = jSONObject.optString("md5");
        this.app_min = jSONObject.optString("app_min");
        this.app_max = jSONObject.optString("app_max");
        this.file_type = jSONObject.optString("file_type");
        this.version_code = jSONObject.optInt("version_code");
        this.nameSpace = jSONObject.optString("nameSpace");
        this.project_priority = jSONObject.optInt("project_priority");
        this.source = jSONObject.optString("source");
        this.filePath = jSONObject.optString(TbsReaderView.KEY_FILE_PATH);
        this.oldFilePath = jSONObject.optString("oldFilePath");
        this.old_version_code = jSONObject.optInt("old_version_code");
        JSONArray optJSONArray = jSONObject.optJSONArray("demand_classes");
        if (optJSONArray != null) {
            this.demand_classes = new LinkedList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                String optString = optJSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString)) {
                    this.demand_classes.add(optString);
                }
            }
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.base.entity.IClone
    public a publicClone() throws CloneNotSupportedException {
        return (a) super.clone();
    }
}
