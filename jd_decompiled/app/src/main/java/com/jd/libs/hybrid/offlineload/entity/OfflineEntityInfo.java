package com.jd.libs.hybrid.offlineload.entity;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Ignore;
import com.jd.libs.hybrid.base.entity.IClone;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.entity.IJsonfy;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes16.dex */
public class OfflineEntityInfo implements IInterfaceCheck, IJsonfy<OfflineEntityInfo>, IClone<OfflineEntityInfo>, Cloneable {
    public static final String FILE_TYPE_ZIP = "zip";
    public static final String FILE_TYPE_ZIP2 = "2zip";
    private String fileType;
    private String fileUrlZip;
    private String fileZipMd5;
    private String md5;
    private String password;
    private int patchTotal;
    private String url;
    private String version;
    private int versionCode = -1;
    @Ignore
    private int patchVersionCode = -1;
    private boolean useZip = false;

    /* JADX WARN: Code restructure failed: missing block: B:25:0x003a, code lost:
        if ((r2 ? r4.fileUrlZip : r4.url).equals(r5.getUrl()) == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0041, code lost:
        if (r5.getUrl() != null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0043, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0044, code lost:
        r2 = r4.useZip;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
        if (r2 == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0048, code lost:
        r3 = r4.fileZipMd5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004b, code lost:
        r3 = r4.md5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004d, code lost:
        if (r3 == null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004f, code lost:
        if (r2 == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0051, code lost:
        r0 = r4.fileZipMd5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0054, code lost:
        r0 = r4.md5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0063, code lost:
        if (r5.getMd5() != null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0066, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:?, code lost:
        return r0.equals(r5.getMd5());
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:?, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L4
            return r0
        L4:
            r1 = 0
            if (r5 == 0) goto L68
            java.lang.Class r2 = r4.getClass()
            java.lang.Class r3 = r5.getClass()
            if (r2 == r3) goto L12
            goto L68
        L12:
            boolean r2 = r5 instanceof com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo
            if (r2 != 0) goto L17
            return r1
        L17:
            com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo r5 = (com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo) r5
            int r2 = r4.versionCode
            int r3 = r5.versionCode
            if (r2 == r3) goto L20
            return r1
        L20:
            boolean r2 = r4.useZip
            if (r2 == 0) goto L27
            java.lang.String r3 = r4.fileUrlZip
            goto L29
        L27:
            java.lang.String r3 = r4.url
        L29:
            if (r3 == 0) goto L3d
            if (r2 == 0) goto L30
            java.lang.String r2 = r4.fileUrlZip
            goto L32
        L30:
            java.lang.String r2 = r4.url
        L32:
            java.lang.String r3 = r5.getUrl()
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L44
            goto L43
        L3d:
            java.lang.String r2 = r5.getUrl()
            if (r2 == 0) goto L44
        L43:
            return r1
        L44:
            boolean r2 = r4.useZip
            if (r2 == 0) goto L4b
            java.lang.String r3 = r4.fileZipMd5
            goto L4d
        L4b:
            java.lang.String r3 = r4.md5
        L4d:
            if (r3 == 0) goto L5f
            if (r2 == 0) goto L54
            java.lang.String r0 = r4.fileZipMd5
            goto L56
        L54:
            java.lang.String r0 = r4.md5
        L56:
            java.lang.String r5 = r5.getMd5()
            boolean r0 = r0.equals(r5)
            goto L67
        L5f:
            java.lang.String r5 = r5.getMd5()
            if (r5 != 0) goto L66
            goto L67
        L66:
            r0 = 0
        L67:
            return r0
        L68:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo.equals(java.lang.Object):boolean");
    }

    public String getFileType() {
        return this.fileType;
    }

    public String getFileUrlZip() {
        return this.fileUrlZip;
    }

    public String getFileZipMd5() {
        return this.fileZipMd5;
    }

    public String getMd5() {
        return this.useZip ? this.fileZipMd5 : this.md5;
    }

    public String getPassword() {
        return this.password;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getPatchBaseUrl() {
        return this.url;
    }

    public int getPatchTotal() {
        return this.patchTotal;
    }

    public int getPatchVersionCode() {
        return this.patchVersionCode;
    }

    public String getUrl() {
        return this.useZip ? this.fileUrlZip : this.url;
    }

    public boolean getUseZip() {
        return this.useZip;
    }

    public String getVersion() {
        return this.version;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public int hashCode() {
        int i2;
        int i3 = 0;
        if (TextUtils.isEmpty(this.useZip ? this.fileUrlZip : this.url)) {
            i2 = 0;
        } else {
            i2 = (this.useZip ? this.fileUrlZip : this.url).hashCode();
        }
        int i4 = ((i2 * 31) + this.versionCode) * 31;
        if (!TextUtils.isEmpty(this.useZip ? this.fileZipMd5 : this.md5)) {
            i3 = (this.useZip ? this.fileZipMd5 : this.md5).hashCode();
        }
        return i4 + i3;
    }

    public void setFileType(String str) {
        this.fileType = str;
    }

    public void setFileUrlZip(String str) {
        this.fileUrlZip = str;
    }

    public void setFileZipMd5(String str) {
        this.fileZipMd5 = str;
    }

    public void setMd5(String str) {
        if (this.useZip) {
            setFileZipMd5(str);
        } else {
            this.md5 = str;
        }
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPatchTotal(int i2) {
        this.patchTotal = i2;
    }

    public void setPatchVersionCode(int i2) {
        this.patchVersionCode = i2;
    }

    public void setUrl(String str) {
        if (this.useZip) {
            setFileUrlZip(str);
        } else {
            this.url = str;
        }
    }

    public void setUseZip(boolean z) {
        this.useZip = z;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setVersionCode(int i2) {
        this.versionCode = i2;
    }

    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("useZip", getUseZip());
        jSONObject.put("file_url_zip", getFileUrlZip());
        jSONObject.put("file_zip_md5", getFileZipMd5());
        jSONObject.put("url", getUrl());
        jSONObject.put("version", getVersion());
        jSONObject.put("version_code", getVersionCode());
        jSONObject.put("md5", getMd5());
        jSONObject.put("file_type", getFileType());
        jSONObject.put(DeepLink3DProductHelper.EXTRA_PASSWORD, getPassword());
        jSONObject.put("patch_total", getPatchTotal());
        return jSONObject;
    }

    @Override // com.jd.libs.hybrid.base.entity.IInterfaceCheck
    public boolean useful() {
        if (!TextUtils.isEmpty(this.useZip ? this.fileUrlZip : this.url) && this.versionCode > 0) {
            if (!TextUtils.isEmpty(this.useZip ? this.fileZipMd5 : this.md5) && !TextUtils.isEmpty(this.fileType) && ("zip".equalsIgnoreCase(this.fileType) || FILE_TYPE_ZIP2.equalsIgnoreCase(this.fileType))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public OfflineEntityInfo fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            setUseZip(jSONObject.optBoolean("useZip", false));
            setFileUrlZip(jSONObject.optString("file_url_zip", ""));
            setFileZipMd5(jSONObject.optString("file_zip_md5", ""));
            setUrl(jSONObject.optString("url", ""));
            setVersion(jSONObject.optString("version", ""));
            setVersionCode(jSONObject.optInt("version_code", -1));
            setMd5(jSONObject.optString("md5", ""));
            setFileType(jSONObject.optString("file_type", ""));
            setPassword(jSONObject.optString(DeepLink3DProductHelper.EXTRA_PASSWORD, ""));
            setPatchTotal(jSONObject.optInt("patch_total", 0));
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.libs.hybrid.base.entity.IClone
    @NonNull
    public OfflineEntityInfo publicClone() throws CloneNotSupportedException {
        return (OfflineEntityInfo) super.clone();
    }
}
