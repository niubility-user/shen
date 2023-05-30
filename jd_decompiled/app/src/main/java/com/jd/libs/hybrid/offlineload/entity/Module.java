package com.jd.libs.hybrid.offlineload.entity;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.entity.IClone;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.entity.IJsonfy;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.db.BuildInDataStore;
import com.jd.libs.hybrid.offlineload.entity.Module;
import com.jd.libs.hybrid.offlineload.loader.RetryFailInfo;
import com.jd.libs.hybrid.offlineload.utils.DateUtils;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.unionpay.tsmservice.mi.data.Constant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class Module<T extends Module> implements IInterfaceCheck, Comparable<T>, RetryFailInfo.RetryEntity, IJsonfy<T>, IClone<T>, Cloneable {
    public static final short TYPE_ORIGIN_REGEXP = 2;
    public static final short TYPE_ORIGIN_URL = 1;
    private FileDetail A;
    private String B;
    private FileDetail C;
    private long E;
    private long F;
    private volatile String G;
    private FileDetail H;
    private FileDetail I;
    @NonNull

    /* renamed from: g  reason: collision with root package name */
    private String f5977g;

    /* renamed from: i  reason: collision with root package name */
    private String f5979i;

    /* renamed from: j  reason: collision with root package name */
    private String f5980j;

    /* renamed from: k  reason: collision with root package name */
    private String f5981k;

    /* renamed from: m  reason: collision with root package name */
    private String f5983m;

    /* renamed from: n  reason: collision with root package name */
    private String f5984n;
    private String o;
    private OfflineEntityInfo p;
    private String r;
    private String s;
    private int t;
    private String u;
    private int v;
    private int w;

    /* renamed from: h  reason: collision with root package name */
    private int f5978h = 0;

    /* renamed from: l  reason: collision with root package name */
    private short f5982l = 1;
    private int q = 1;
    private String x = "0";
    private String y = "0";
    private boolean z = false;
    private boolean D = false;
    private int J = 10;
    private long K = 0;
    private long L = 0;
    private int M = 0;
    private volatile boolean N = false;
    private boolean O = false;

    private void b() {
        if (!TextUtils.isEmpty(this.G)) {
            try {
                JSONObject jSONObject = new JSONObject(this.G);
                this.J = jSONObject.optInt("localPriority", 0);
                this.K = jSONObject.optLong("lpLastIncreaseTime", 0L);
                this.L = jSONObject.optLong("lpLastDecreaseTime", 0L);
                this.M = jSONObject.optInt("lpIncreaseTimesEveryTimeGap", 0);
            } catch (JSONException e2) {
                Log.e("Module", e2);
            }
        }
        this.N = false;
    }

    private void c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("localPriority", this.J);
            jSONObject.put("lpLastIncreaseTime", this.K);
            jSONObject.put("lpLastDecreaseTime", this.L);
            jSONObject.put("lpIncreaseTimesEveryTimeGap", this.M);
        } catch (JSONException e2) {
            Log.e("Module", e2);
        }
        this.G = jSONObject.toString();
        this.N = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Module a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return this;
        }
        setAppid(jSONObject.optString("appid", ""));
        setModuleCode(jSONObject.optInt("module_code", 0));
        setExtendedVersion(jSONObject.optString("extended_version", ""));
        setName(jSONObject.optString("name", ""));
        setOriginalUrl(jSONObject.optString("original_url", ""));
        setOriginalUrlType((short) jSONObject.optInt("original_url_type", 1));
        setDocumentDir(jSONObject.optString("document_dir", ""));
        setSourceRoot(jSONObject.optString("source_root", ""));
        setSourceDir(jSONObject.optString("source_dir", ""));
        setFileInfo(new OfflineEntityInfo().fromJson(jSONObject.optJSONObject("file")));
        setNoAutoInstall(jSONObject.optInt("no_install", 1));
        setAppMin(jSONObject.optString("app_min", ""));
        setAppMax(jSONObject.optString("app_max", ""));
        setServerPriority(jSONObject.optInt(RemoteMessageConst.Notification.PRIORITY, 0));
        setCheckType(jSONObject.optString("check_type", ""));
        setHtmlStatic(jSONObject.optInt("html_static", 0));
        setCacheable(jSONObject.optInt("cacheable", 0));
        setBConfig(jSONObject.optString("b_config", "0"));
        setMinFileVer(jSONObject.optString("minFileVer", "0"));
        setBuildInConfig(jSONObject.optBoolean("is_build_in_config", false));
        if (jSONObject.has("unzip_file")) {
            setUnzipFile(new FileDetail().fromJson(jSONObject.getJSONObject("unzip_file")));
        }
        setLocalFileListJson(jSONObject.optString("local_file_list", ""));
        if (jSONObject.has("zip_file")) {
            setZipFile(new FileDetail().fromJson(jSONObject.getJSONObject("zip_file")));
        }
        setAvailable(jSONObject.optBoolean(Constant.KEY_PROMOTION_AVAILABLE, false));
        setCreateTimestamp(jSONObject.optLong("create_timestamp", 0L));
        setLastVisitTimestamp(jSONObject.optLong("last_visit_timestamp", 0L));
        setLocalPriorityInfo(jSONObject.optString("local_priority_info", ""));
        return this;
    }

    public synchronized void calculateLpEveryTimeGap() {
        boolean z;
        long j2 = this.F;
        long j3 = this.E;
        boolean z2 = j2 >= j3;
        long max = Math.max(j3, j2);
        long j4 = this.L;
        if (max < j4) {
            max = j4;
            z = true;
        } else {
            z = false;
        }
        if (max == 0) {
            Log.d("Module-Priority", "Error! lastTime is 0 when trying to recalculate priority for id " + this.f5977g);
            return;
        }
        int howManyDaysElapsed = DateUtils.howManyDaysElapsed(max, System.currentTimeMillis());
        if ((z && howManyDaysElapsed > 0) || ((!z && !z2 && howManyDaysElapsed > 0) || (!z && z2 && howManyDaysElapsed > 1))) {
            int i2 = this.J;
            int i3 = howManyDaysElapsed - ((z || !z2) ? 0 : 1);
            this.J = Math.max(i2 - i3, 0);
            this.L = System.currentTimeMillis();
            this.N = true;
            Log.d("Module-Priority", "calculateLpEveryTimeGap: Decreased local priority (-" + i3 + ") for id " + this.f5977g + ", old LP = " + i2 + ", new LP = " + this.J + ", time = " + max + ", elapsed day = " + howManyDaysElapsed);
            StringBuilder sb = new StringBuilder();
            sb.append("calculateLpEveryTimeGap: id = ");
            sb.append(this.f5977g);
            sb.append(", SP = ");
            sb.append(getServerPriority());
            sb.append(", LP = ");
            sb.append(getLocalPriority());
            sb.append(", P = ");
            sb.append(getPriority());
            Log.d("Module-Priority", sb.toString());
        } else {
            Log.d("Module-Priority", "calculateLpEveryTimeGap: still in time gap, SKIP decreasing local priority for id " + this.f5977g + ", last time = " + max);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public Object clone() throws CloneNotSupportedException {
        Module module = (Module) super.clone();
        if (getFileInfo() != null) {
            module.setFileInfo(getFileInfo().publicClone());
        }
        if (getUnzipFile() != null) {
            module.setUnzipFile(getUnzipFile().publicClone());
        }
        if (getOldUnzipFile() != null) {
            module.setOldUnzipFile(getOldUnzipFile().publicClone());
        }
        if (getZipFile() != null) {
            module.setZipFile(getZipFile().publicClone());
        }
        if (getOldZipFile() != null) {
            module.setOldZipFile(getOldZipFile().publicClone());
        }
        return module;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((Module<T>) obj);
    }

    public void copyLocalInfoFrom(Module module) {
        if (module != null) {
            this.E = module.E;
            this.F = module.F;
            this.G = module.G;
            this.J = module.J;
            this.K = module.K;
            this.L = module.L;
            this.M = module.M;
        }
    }

    public void copyLocalUnzipFileInfoFrom(Module module) {
        if (module != null) {
            setUnzipFile(module.getUnzipFile());
            setLocalFileListJson(module.getLocalFileListJson());
            setAvailable(module.isAvailable());
        }
    }

    public void copyLocalZipInfoFrom(Module module) {
        if (module == null) {
            return;
        }
        setZipFile(module.getZipFile());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && (obj instanceof Module)) {
            String str = this.f5977g;
            String str2 = ((Module) obj).f5977g;
            return str == null ? str2 == null : str.equals(str2);
        }
        return false;
    }

    public String getAppMax() {
        return this.s;
    }

    public String getAppMin() {
        return this.r;
    }

    public String getAppid() {
        return this.f5977g;
    }

    public String getBConfig() {
        return this.x;
    }

    public int getCacheable() {
        return this.w;
    }

    public String getCheckType() {
        return this.u;
    }

    public long getCreateTimestamp() {
        return this.E;
    }

    public String getDocumentDir() {
        return this.f5983m;
    }

    public String getExtendedVersion() {
        return this.f5979i;
    }

    public OfflineEntityInfo getFileInfo() {
        return this.p;
    }

    public int getHtmlStatic() {
        return this.v;
    }

    public long getLastVisitTimestamp() {
        return this.F;
    }

    public String getLocalFileListJson() {
        return this.B;
    }

    public int getLocalPriority() {
        return this.J;
    }

    public String getLocalPriorityInfo() {
        if (this.N) {
            c();
        }
        return this.G;
    }

    public String getMinFileVer() {
        return this.y;
    }

    public int getMinFileVerInt() {
        if (TextUtils.isEmpty(this.y) || !TextUtils.isDigitsOnly(this.y)) {
            return 0;
        }
        return Integer.parseInt(this.y);
    }

    public int getModuleCode() {
        return this.f5978h;
    }

    public String getName() {
        return this.f5980j;
    }

    public int getNoAutoInstall() {
        return this.q;
    }

    public FileDetail getOldUnzipFile() {
        return this.H;
    }

    public FileDetail getOldZipFile() {
        return this.I;
    }

    public String getOriginalUrl() {
        return this.f5981k;
    }

    public short getOriginalUrlType() {
        return this.f5982l;
    }

    public String getPatchUrl(int i2) {
        return this.p.getPatchBaseUrl() + "-" + i2 + "-" + this.p.getVersionCode();
    }

    public float getPriority() {
        float f2 = HybridSettings.SP_RATIO;
        return (this.t * f2) + (this.J * (1.0f - f2));
    }

    @Override // com.jd.libs.hybrid.offlineload.loader.RetryFailInfo.RetryEntity
    public String getRetryKey() {
        return getOriginalUrl() + CartConstant.KEY_YB_INFO_LINK + getFileInfo().getVersionCode() + CartConstant.KEY_YB_INFO_LINK + getFileInfo().getUrl();
    }

    public int getServerPriority() {
        return this.t;
    }

    public String getSourceDir() {
        return this.o;
    }

    public String getSourceRoot() {
        return this.f5984n;
    }

    public FileDetail getUnzipFile() {
        return this.A;
    }

    public FileDetail getZipFile() {
        return this.C;
    }

    public boolean hasBuildIn() {
        return BuildInDataStore.getInstance().contains(this.f5977g);
    }

    public boolean hasUnzipFileChanged() {
        if (getUnzipFile() == null) {
            return true;
        }
        return getUnzipFile().hasChanged();
    }

    public int hashCode() {
        if (TextUtils.isEmpty(this.f5977g)) {
            return 0;
        }
        return this.f5977g.hashCode();
    }

    public boolean isAvailable() {
        return this.D;
    }

    public boolean isBuildInConfig() {
        return this.z;
    }

    public boolean isDownloadDegraded() {
        return CommonUtils.getBinarySwitch(getBConfig(), ModuleHelper.BCONFIG_DOWNLOAD_DEGRADE);
    }

    public boolean isNewAdded() {
        return this.O;
    }

    public boolean isPatchOf(int i2) {
        OfflineEntityInfo offlineEntityInfo;
        if (i2 < 0 || (offlineEntityInfo = this.p) == null || offlineEntityInfo.getVersionCode() <= 0 || this.p.getPatchTotal() <= 0 || this.p.getVersionCode() <= i2 || this.p.getPatchTotal() < this.p.getVersionCode() - i2) {
            return false;
        }
        return !TextUtils.isEmpty(this.p.getPatchBaseUrl());
    }

    public boolean isRegexpMatch() {
        return 2 == this.f5982l && !TextUtils.isEmpty(this.f5981k);
    }

    public boolean isShared() {
        return CommonUtils.getBinarySwitch(getBConfig(), ModuleHelper.BCONFIG_IS_SHARED_PKG);
    }

    public void markVisited() {
        this.F = System.currentTimeMillis();
    }

    public boolean needCheckLatest() {
        return TextUtils.isEmpty(this.u) || "1".equals(this.u);
    }

    public void setAppMax(String str) {
        this.s = str;
    }

    public void setAppMin(String str) {
        this.r = str;
    }

    public void setAppid(String str) {
        this.f5977g = str;
    }

    public void setAvailable(boolean z) {
        this.D = z;
    }

    public void setBConfig(String str) {
        this.x = str;
    }

    public void setBuildInConfig(boolean z) {
        this.z = z;
    }

    public void setCacheable(int i2) {
        this.w = i2;
    }

    public void setCheckType(String str) {
        this.u = str;
    }

    public void setCreateTime() {
        if (this.E == 0) {
            this.E = System.currentTimeMillis();
        }
    }

    public void setCreateTimestamp(long j2) {
        this.E = j2;
    }

    public void setDocumentDir(String str) {
        this.f5983m = str;
    }

    public void setDownloadDegraded(boolean z) {
        setBConfig(CommonUtils.setBinarySwitch(getBConfig(), ModuleHelper.BCONFIG_DOWNLOAD_DEGRADE, z));
    }

    public void setExtendedVersion(String str) {
        this.f5979i = str;
    }

    public void setFileInfo(OfflineEntityInfo offlineEntityInfo) {
        this.p = offlineEntityInfo;
    }

    public void setHtmlStatic(int i2) {
        this.v = i2;
    }

    public void setLastVisitTimestamp(long j2) {
        this.F = j2;
    }

    public void setLocalFileListJson(String str) {
        this.B = str;
    }

    public void setLocalPriority(int i2) {
        this.J = i2;
        this.N = true;
    }

    public void setLocalPriorityInfo(String str) {
        this.G = str;
        b();
    }

    public void setMinFileVer(String str) {
        this.y = str;
    }

    public void setModuleCode(int i2) {
        this.f5978h = i2;
    }

    public void setName(String str) {
        this.f5980j = str;
    }

    public void setNewAdded(boolean z) {
        this.O = z;
    }

    public void setNoAutoInstall(int i2) {
        this.q = i2;
    }

    public void setOldUnzipFile(FileDetail fileDetail) {
        this.H = fileDetail;
    }

    public void setOldZipFile(FileDetail fileDetail) {
        this.I = fileDetail;
    }

    public void setOriginalUrl(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.f5981k = str;
    }

    public void setOriginalUrlType(short s) {
        this.f5982l = s;
    }

    public void setServerPriority(int i2) {
        this.t = i2;
    }

    public void setSourceDir(String str) {
        this.o = str;
    }

    public void setSourceRoot(String str) {
        this.f5984n = str;
    }

    public void setUnzipFile(FileDetail fileDetail) {
        this.A = fileDetail;
    }

    public void setZipFile(FileDetail fileDetail) {
        this.C = fileDetail;
    }

    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appid", getAppid());
        jSONObject.put("module_code", getModuleCode());
        jSONObject.put("extended_version", getExtendedVersion());
        jSONObject.put("name", getName());
        jSONObject.put("original_url", getOriginalUrl());
        jSONObject.put("original_url_type", (int) getOriginalUrlType());
        jSONObject.put("document_dir", getDocumentDir());
        jSONObject.put("source_root", getSourceRoot());
        jSONObject.put("source_dir", getSourceDir());
        jSONObject.put("file", getFileInfo().toJson());
        jSONObject.put("no_install", getNoAutoInstall());
        jSONObject.put("app_min", getAppMin());
        jSONObject.put("app_max", getAppMax());
        jSONObject.put(RemoteMessageConst.Notification.PRIORITY, getServerPriority());
        jSONObject.put("check_type", getCheckType());
        jSONObject.put("html_static", getHtmlStatic());
        jSONObject.put("cacheable", getCacheable());
        jSONObject.put("b_config", getBConfig());
        jSONObject.put("minFileVer", getMinFileVer());
        jSONObject.put("is_build_in_config", isBuildInConfig());
        if (getUnzipFile() != null) {
            jSONObject.put("unzip_file", getUnzipFile().toJson());
        }
        jSONObject.put("local_file_list", getLocalFileListJson());
        if (getZipFile() != null) {
            jSONObject.put("zip_file", getZipFile().toJson());
        }
        jSONObject.put(Constant.KEY_PROMOTION_AVAILABLE, isAvailable());
        jSONObject.put("create_timestamp", getCreateTimestamp());
        jSONObject.put("last_visit_timestamp", getLastVisitTimestamp());
        jSONObject.put("local_priority_info", getLocalPriorityInfo());
        return jSONObject;
    }

    @NonNull
    public String toString() {
        try {
            return toJson().toString();
        } catch (JSONException e2) {
            Log.e("Module", e2);
            return "";
        }
    }

    public synchronized void tryIncreaseLpWhenVisited() {
        if (!DateUtils.isSameDay(this.K, System.currentTimeMillis())) {
            this.M = 0;
            this.N = true;
            Log.d("Module-Priority", "tryIncreaseLpWhenVisited: Elapsed time exceed time-gap, reset times of increase to 0 for id " + this.f5977g + ", lastTime = " + this.K);
        }
        int i2 = this.M;
        if (i2 < 3) {
            this.M = i2 + 1;
            this.J = Math.min(this.J + 1, 20);
            this.K = System.currentTimeMillis();
            this.N = true;
            Log.d("Module-Priority", "tryIncreaseLpWhenVisited: Increased LP for id " + this.f5977g + ", new LP = " + this.J + ", increase times today = " + this.M);
            Log.d("Module-Priority", "tryIncreaseLpWhenVisited: id = " + this.f5977g + ", SP = " + getServerPriority() + ", LP = " + getLocalPriority() + ", P = " + getPriority());
        } else {
            Log.d("Module-Priority", "tryIncreaseLpWhenVisited: LP had been increased already " + this.M + " times in time-gap, no need to increase for id " + this.f5977g);
        }
    }

    @Override // com.jd.libs.hybrid.base.entity.IInterfaceCheck
    public boolean useful() {
        OfflineEntityInfo offlineEntityInfo;
        return (TextUtils.isEmpty(this.f5981k) || (offlineEntityInfo = this.p) == null || !offlineEntityInfo.useful()) ? false : true;
    }

    public int compareTo(T t) {
        return (int) ((t.getPriority() * 1000.0f) - (getPriority() * 1000.0f));
    }
}
