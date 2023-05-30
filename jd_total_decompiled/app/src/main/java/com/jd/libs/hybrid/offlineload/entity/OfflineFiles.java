package com.jd.libs.hybrid.offlineload.entity;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.entity.IJsonfy;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.utils.FileUtils;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes16.dex */
public class OfflineFiles implements IJsonfy<OfflineFiles> {
    private static final String TAG = "OfflineFiles";
    private String appId;
    private boolean available;
    private String bConfig;
    private String extendedVersion;
    private String fileRootPath;
    private volatile HtmlDownloadMgr htmlDownloadMgr;
    private String htmlFile;
    private String htmlPath;
    private Map<String, LocalFileEntity> localFileMap;
    private int moduleVersion;
    private String name;
    private String originHtmlPath;
    private String staticDir;
    private String staticPath;
    private boolean regexpUrl = false;
    private int fileVersion = -1;
    @Deprecated
    private String type = "1";
    private String minFileVer = "0";
    @Deprecated
    private boolean canPreloadHtml = false;
    @Deprecated
    private boolean canPassGentoken = false;
    private boolean hasBuildIn = false;
    private boolean isBuildConfig = false;

    /* loaded from: classes16.dex */
    public static class Builder {
        boolean a;
        String b;

        /* renamed from: c */
        String f5985c;
        String d;

        /* renamed from: f */
        boolean f5987f;

        /* renamed from: g */
        String f5988g;

        /* renamed from: h */
        String f5989h;

        /* renamed from: i */
        boolean f5990i;

        /* renamed from: j */
        String f5991j;

        /* renamed from: k */
        String f5992k;

        /* renamed from: l */
        String f5993l;

        /* renamed from: m */
        String f5994m;

        /* renamed from: n */
        String f5995n;
        String o;
        int p;
        boolean r;
        String s;
        boolean t;
        boolean u;

        /* renamed from: e */
        String f5986e = "0";
        int q = -1;

        public OfflineFiles build() {
            return new OfflineFiles(this);
        }

        public Builder setAppId(String str) {
            this.b = str;
            return this;
        }

        public Builder setAvailable(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setBConfig(String str) {
            this.s = str;
            return this;
        }

        public Builder setBuildInConfig(boolean z) {
            this.u = z;
            return this;
        }

        public Builder setCanPassGentoken(boolean z) {
            this.r = z;
            return this;
        }

        @Deprecated
        public Builder setCanPreloadHtml(boolean z) {
            this.f5987f = z;
            return this;
        }

        public Builder setExtendedVersion(String str) {
            this.o = str;
            return this;
        }

        public Builder setFileRootPath(String str) {
            this.f5991j = str;
            return this;
        }

        public Builder setFileVersion(int i2) {
            this.q = i2;
            return this;
        }

        public Builder setHasBuildIn(boolean z) {
            this.t = z;
            return this;
        }

        public Builder setHtmlFile(String str) {
            this.f5994m = str;
            return this;
        }

        public Builder setHtmlPath(String str) {
            this.f5988g = str;
            return this;
        }

        public Builder setLocalFileListJson(String str) {
            this.f5992k = str;
            return this;
        }

        public Builder setMinFileVer(String str) {
            this.f5986e = str;
            return this;
        }

        public Builder setModuleVersion(int i2) {
            this.p = i2;
            return this;
        }

        public Builder setName(String str) {
            this.f5985c = str;
            return this;
        }

        public Builder setOriginHtmlPath(String str) {
            this.f5989h = str;
            return this;
        }

        public Builder setRegexpUrl(boolean z) {
            this.f5990i = z;
            return this;
        }

        public Builder setStaticDir(String str) {
            this.f5995n = str;
            return this;
        }

        public Builder setStaticPath(String str) {
            this.f5993l = str;
            return this;
        }

        @Deprecated
        public Builder setType(String str) {
            this.d = str;
            return this;
        }

        public OfflineFiles build(OfflineFiles offlineFiles) {
            return offlineFiles.init(this);
        }
    }

    /* loaded from: classes16.dex */
    public static class HtmlDownloadMgr {
        public static final int STATE_DEFAULT = 0;
        public static final int STATE_FAIL = -1;
        public static final int STATE_SUCCESS = 200;
        public static final int STATE_TIMEOUT = -2;
        private String b;

        /* renamed from: c */
        private String f5996c;
        private String d;

        /* renamed from: e */
        private PreReadInputStream f5997e;

        /* renamed from: i */
        private Condition f6001i;

        /* renamed from: k */
        private long f6003k;

        /* renamed from: l */
        private long f6004l;
        private int a = 0;

        /* renamed from: f */
        private boolean f5998f = true;

        /* renamed from: g */
        private boolean f5999g = false;

        /* renamed from: h */
        private final ReentrantLock f6000h = new ReentrantLock();

        /* renamed from: j */
        private boolean f6002j = false;

        /* renamed from: m */
        private long f6005m = -1;

        /* renamed from: n */
        private long f6006n = -1;

        public HtmlDownloadMgr(String str) {
            this.b = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:122:0x0062 A[ORIG_RETURN, RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void l() {
            boolean z = true;
            this.f6002j = true;
            boolean z2 = false;
            try {
                try {
                    if (!TextUtils.isEmpty(this.d)) {
                        OfflineFiles.deletePreloadHtml(this.d);
                        this.d = null;
                    }
                    PreReadInputStream preReadInputStream = this.f5997e;
                    if (preReadInputStream != null) {
                        preReadInputStream.close();
                        this.f5997e = null;
                    }
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e2) {
                e = e2;
            }
            if (this.f5999g && this.f6001i != null) {
                if (this.f6000h.tryLock()) {
                    try {
                        if (this.f6000h.hasWaiters(this.f6001i)) {
                            this.f6001i.signal();
                        }
                        if (!z) {
                            return;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        z2 = true;
                        Log.e(OfflineFiles.TAG, e);
                        OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "HtmlDownloadMgr#destroy", this.b, (String) null, e);
                        if (!z2) {
                            return;
                        }
                        this.f6000h.unlock();
                    } catch (Throwable th2) {
                        th = th2;
                        z2 = true;
                        if (z2) {
                            this.f6000h.unlock();
                        }
                        throw th;
                    }
                    this.f6000h.unlock();
                }
            }
            z = false;
            if (!z) {
            }
            this.f6000h.unlock();
        }

        public long m() {
            return this.f6004l;
        }

        public long n() {
            return this.f6003k;
        }

        public JSONObject o() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("getTime", this.f6005m);
            long j2 = this.f6005m;
            if (j2 > 0) {
                long j3 = this.f6003k;
                if (j3 > 0) {
                    jSONObject.put("startToGet", j2 - j3);
                }
                long j4 = this.f6004l;
                if (j4 > 0) {
                    jSONObject.put("endToGet", this.f6005m - j4);
                }
            }
            jSONObject.put("threadWait", this.f6006n);
            jSONObject.put("useStream", this.f5998f);
            if (this.f5998f) {
                PreReadInputStream preReadInputStream = this.f5997e;
                jSONObject.put("streamInfo", preReadInputStream != null ? preReadInputStream.getStateInfo() : null);
            }
            return jSONObject;
        }

        public Object p() {
            if (this.f5998f) {
                return r();
            }
            return q();
        }

        public String q() {
            this.f6005m = System.currentTimeMillis();
            this.f6006n = -1L;
            if (TextUtils.isEmpty(this.f5996c)) {
                this.a = 0;
                return null;
            }
            this.f5996c = null;
            Log.d(OfflineFiles.TAG, "try to get SSR preload html file.");
            if (this.f5999g && this.f6001i == null) {
                try {
                    try {
                        this.f6000h.lock();
                        this.f6001i = this.f6000h.newCondition();
                        Log.d(OfflineFiles.TAG, "waiting for downloading SSR html.");
                        long currentTimeMillis = System.currentTimeMillis();
                        boolean await = this.f6001i.await(HybridSettings.MAX_HTML_PRE_FETCH_TIME, TimeUnit.MILLISECONDS);
                        this.f6006n = System.currentTimeMillis() - currentTimeMillis;
                        if (!await) {
                            this.a = -2;
                            Log.d(OfflineFiles.TAG, "Timeout for downloading SSR html.");
                            if (Log.isDebug()) {
                                Log.xLogDForDev(OfflineFiles.TAG, "\u7b49\u5f85\u83b7\u53d6\u9884\u4e0b\u8f7d\u7684html\u672c\u5730\u6587\u4ef6\u8d85\u65f6\uff01\u76ee\u524d\u8d85\u65f6\u7684\u65f6\u95f4\u914d\u7f6e\u662f:" + HybridSettings.MAX_HTML_PRE_FETCH_TIME + "ms");
                            }
                        } else {
                            Log.d(OfflineFiles.TAG, "Download SSR html finished in time or webview is exiting.");
                        }
                    } catch (InterruptedException e2) {
                        Log.e(OfflineFiles.TAG, e2);
                        OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "getPreloadHtmlPath", this.b, (String) null, ExceptionUtils.getStackStringFromException(e2));
                    }
                } finally {
                    this.f6000h.unlock();
                }
            }
            return this.d;
        }

        private InputStream r() {
            this.f6005m = System.currentTimeMillis();
            this.f6006n = -1L;
            if (TextUtils.isEmpty(this.f5996c)) {
                this.a = 0;
                return null;
            }
            this.f5996c = null;
            Log.d(OfflineFiles.TAG, "try to get preload html stream.");
            if (this.f5999g && this.f6001i == null) {
                try {
                    try {
                        this.f6000h.lock();
                        this.f6001i = this.f6000h.newCondition();
                        Log.d(OfflineFiles.TAG, "waiting for requesting html.");
                        long currentTimeMillis = System.currentTimeMillis();
                        boolean await = this.f6001i.await(HybridSettings.MAX_HTML_PRE_FETCH_TIME, TimeUnit.MILLISECONDS);
                        this.f6006n = System.currentTimeMillis() - currentTimeMillis;
                        if (!await) {
                            this.a = -2;
                            Log.d(OfflineFiles.TAG, "Timeout for requesting html.");
                            if (Log.isDebug()) {
                                Log.xLogDForDev(OfflineFiles.TAG, "\u7b49\u5f85\u83b7\u53d6\u9884\u4e0b\u8f7d\u7684html\u6587\u4ef6\u6d41\u8d85\u65f6\uff01\u76ee\u524d\u8d85\u65f6\u7684\u65f6\u95f4\u914d\u7f6e\u662f:" + HybridSettings.MAX_HTML_PRE_FETCH_TIME + "ms");
                            }
                        } else {
                            Log.d(OfflineFiles.TAG, "Get html stream finished in time or webview is exiting.");
                        }
                    } catch (InterruptedException e2) {
                        Log.e(OfflineFiles.TAG, e2);
                        OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "getPreloadHtmlStream", this.b, (String) null, ExceptionUtils.getStackStringFromException(e2));
                    }
                } finally {
                    this.f6000h.unlock();
                }
            }
            PreReadInputStream preReadInputStream = this.f5997e;
            if (preReadInputStream != null) {
                preReadInputStream.finishPreRead();
            }
            return this.f5997e;
        }

        public int s() {
            return this.a;
        }

        /* JADX WARN: Removed duplicated region for block: B:125:0x006f  */
        /* JADX WARN: Removed duplicated region for block: B:136:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        @WorkerThread
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void t(boolean z, InputStream inputStream) {
            PreReadInputStream preReadInputStream;
            Condition condition;
            boolean z2 = false;
            this.f5999g = false;
            this.f6004l = System.currentTimeMillis();
            try {
                try {
                    this.f6000h.lock();
                    if (this.a != -2) {
                        this.a = z ? 200 : -1;
                    }
                    if (this.f6002j) {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        this.f5997e = null;
                    } else if (z) {
                        this.f5997e = new PreReadInputStream(new BufferedInputStream(inputStream));
                    } else {
                        this.f5997e = null;
                    }
                    this.f5999g = false;
                    condition = this.f6001i;
                } catch (Exception e2) {
                    e = e2;
                    z2 = true;
                }
                if (condition != null) {
                    if (this.f6000h.hasWaiters(condition)) {
                        try {
                            this.f6001i.signal();
                        } catch (Exception e3) {
                            e = e3;
                            Log.e(OfflineFiles.TAG, e);
                            OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "HtmlDownloadMgr.onPreloadConnect", this.b, (String) null, ExceptionUtils.getStackStringFromException(e));
                            if (z2) {
                            }
                        }
                        if (!z2 || (preReadInputStream = this.f5997e) == null) {
                        }
                        preReadInputStream.startPreRead();
                        return;
                    }
                }
                z2 = true;
                if (z2) {
                }
            } finally {
                this.f6000h.unlock();
            }
        }

        public void u(boolean z, String str) {
            this.f5999g = false;
            this.f6004l = System.currentTimeMillis();
            try {
                try {
                    this.f6000h.lock();
                    if (this.a != -2) {
                        this.a = z ? 200 : -1;
                    }
                    if (z) {
                        this.d = str;
                    } else {
                        this.d = null;
                    }
                    if (this.f6002j) {
                        OfflineFiles.deletePreloadHtml(this.d);
                        this.d = null;
                    }
                    this.f5999g = false;
                    Condition condition = this.f6001i;
                    if (condition != null && this.f6000h.hasWaiters(condition)) {
                        this.f6001i.signal();
                    }
                } catch (Exception e2) {
                    Log.e(OfflineFiles.TAG, e2);
                    OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "HtmlDownloadMgr.onPreloadEnd", this.b, (String) null, ExceptionUtils.getStackStringFromException(e2));
                }
            } finally {
                this.f6000h.unlock();
            }
        }

        public void v(String str, boolean z) {
            this.f5999g = true;
            this.f6003k = System.currentTimeMillis();
            this.f5998f = z;
            this.f5996c = str;
        }
    }

    public OfflineFiles(Builder builder) {
        init(builder);
    }

    public static void deletePreloadHtml(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        synchronized (OfflineFiles.class) {
            FileUtils.deleteFile(file.getParentFile());
        }
    }

    public OfflineFiles init(Builder builder) {
        if (builder != null) {
            this.appId = builder.b;
            this.name = builder.f5985c;
            this.available = builder.a;
            this.type = builder.d;
            this.minFileVer = builder.f5986e;
            this.canPreloadHtml = builder.f5987f;
            this.fileRootPath = builder.f5991j;
            setLocalFileMap(builder.f5992k);
            this.htmlPath = builder.f5988g;
            this.originHtmlPath = builder.f5989h;
            this.regexpUrl = builder.f5990i;
            this.staticPath = builder.f5993l;
            this.htmlFile = builder.f5994m;
            this.staticDir = builder.f5995n;
            this.moduleVersion = builder.p;
            this.extendedVersion = builder.o;
            this.fileVersion = builder.q;
            this.canPassGentoken = builder.r;
            this.bConfig = builder.s;
            this.hasBuildIn = builder.t;
            this.isBuildConfig = builder.u;
        }
        return this;
    }

    private void setLocalFileMap(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            if (length > 0) {
                this.localFileMap = new HashMap(length);
                for (int i2 = 0; i2 < length; i2++) {
                    LocalFileEntity fromJson = new LocalFileEntity().fromJson(jSONArray.getJSONObject(i2));
                    if (fromJson.useful()) {
                        this.localFileMap.put(fromJson.url, fromJson);
                    } else {
                        Log.e(TAG, "Local file entity is not useful, missing params. fileName: " + fromJson.fileName + ", url: " + fromJson.url + ", header: " + fromJson.header);
                    }
                }
                return;
            }
            this.localFileMap = null;
        } catch (Exception e2) {
            Log.e(TAG, e2);
            OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_CODE, "setLocalFileMap", (String) null, e2);
        }
    }

    public void destroy() {
        if (this.htmlDownloadMgr != null) {
            this.htmlDownloadMgr.l();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && (obj instanceof OfflineFiles)) {
            OfflineFiles offlineFiles = (OfflineFiles) obj;
            if (this.fileVersion != offlineFiles.fileVersion) {
                return false;
            }
            String str = this.htmlFile;
            String str2 = offlineFiles.htmlFile;
            return str != null ? str.equals(str2) : str2 == null;
        }
        return false;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getBConfig() {
        return this.bConfig;
    }

    public String getExtendedVersion() {
        return this.extendedVersion;
    }

    public String getFileRootPath() {
        return this.fileRootPath;
    }

    public int getFileVersion() {
        return this.fileVersion;
    }

    @Deprecated
    public String getHtmlFile() {
        return this.htmlFile;
    }

    @Deprecated
    public String getHtmlPath() {
        return this.htmlPath;
    }

    public Map<String, LocalFileEntity> getLocalFileMap() {
        return this.localFileMap;
    }

    public String getMinFileVer() {
        return this.minFileVer;
    }

    public int getMinFileVerInt() {
        if (TextUtils.isEmpty(this.minFileVer) || !TextUtils.isDigitsOnly(this.minFileVer)) {
            return 0;
        }
        return Integer.parseInt(this.minFileVer);
    }

    public int getModuleVersion() {
        return this.moduleVersion;
    }

    public String getName() {
        return this.name;
    }

    public String getOriginHtmlPath() {
        return this.originHtmlPath;
    }

    public Object getPreloadHtml() {
        if (this.htmlDownloadMgr != null) {
            return this.htmlDownloadMgr.p();
        }
        return null;
    }

    public long getPreloadHtmlDownloadEnd() {
        if (this.htmlDownloadMgr != null) {
            return this.htmlDownloadMgr.m();
        }
        return 0L;
    }

    public long getPreloadHtmlDownloadStart() {
        if (this.htmlDownloadMgr != null) {
            return this.htmlDownloadMgr.n();
        }
        return 0L;
    }

    public JSONObject getPreloadHtmlInfo() throws JSONException {
        if (this.htmlDownloadMgr != null) {
            return this.htmlDownloadMgr.o();
        }
        return null;
    }

    @Deprecated
    public String getPreloadHtmlPath() {
        if (this.htmlDownloadMgr != null) {
            return this.htmlDownloadMgr.q();
        }
        return null;
    }

    public int getPreloadHtmlState() {
        if (this.htmlDownloadMgr != null) {
            return this.htmlDownloadMgr.s();
        }
        return 0;
    }

    public String getPreloadHtmlUrl() {
        if (this.htmlDownloadMgr != null) {
            return this.htmlDownloadMgr.f5996c;
        }
        return null;
    }

    @Deprecated
    public String getStaticDir() {
        return this.staticDir;
    }

    @Deprecated
    public String getStaticPath() {
        return this.staticPath;
    }

    @Deprecated
    public String getType() {
        return this.type;
    }

    @Deprecated
    public String getTypeString() {
        return isBuildInBiz() ? "\u5185\u7f6e\u5305" : isSsrBiz() ? "\u79bb\u7ebf\u8d44\u6e90" : "\u79bb\u7ebf\u5e94\u7528";
    }

    public int hashCode() {
        String str = this.htmlFile;
        return ((str != null ? str.hashCode() : 0) * 31) + this.fileVersion;
    }

    public boolean isAvailable() {
        return this.available;
    }

    @Deprecated
    public boolean isBuildInBiz() {
        return "3".equals(this.type);
    }

    @Deprecated
    public boolean isCanPassGentoken() {
        return this.canPassGentoken;
    }

    public boolean isCanPreloadHtml() {
        if ("4".equals(this.type)) {
            return CommonUtils.getBinarySwitch(this.bConfig, ModuleHelper.BCONFIG_PRELOAD_HTML);
        }
        return this.canPreloadHtml;
    }

    public boolean isRegexpUrl() {
        return this.regexpUrl;
    }

    @Deprecated
    public boolean isSsrBiz() {
        return "2".equals(this.type);
    }

    @WorkerThread
    public void onPreloadConnect(boolean z, InputStream inputStream) {
        if (this.htmlDownloadMgr != null) {
            this.htmlDownloadMgr.t(z, inputStream);
        }
    }

    public void onPreloadEnd(boolean z, String str) {
        if (this.htmlDownloadMgr != null) {
            this.htmlDownloadMgr.u(z, str);
        } else if (TextUtils.isEmpty(str)) {
        } else {
            try {
                deletePreloadHtml(str);
            } catch (Exception e2) {
                Log.e(TAG, e2);
                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "onPreloadEnd", this.appId, (String) null, ExceptionUtils.getStackStringFromException(e2));
            }
        }
    }

    public void onPreloadHtmlUrl(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.htmlDownloadMgr = new HtmlDownloadMgr(this.appId);
        this.htmlDownloadMgr.v(str, z);
    }

    public void setAvailable(boolean z) {
        this.available = z;
    }

    public void setLocalFileInfo(boolean z, String str, String str2, int i2) {
        setAvailable(z);
        this.fileRootPath = str;
        setLocalFileMap(str2);
        this.fileVersion = i2;
    }

    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appId", getAppId());
        jSONObject.put("originHtmlPath", getOriginHtmlPath());
        jSONObject.put("regexpUrl", isRegexpUrl());
        jSONObject.put("fileRootPath", getFileRootPath());
        Map<String, LocalFileEntity> localFileMap = getLocalFileMap();
        if (localFileMap != null && !localFileMap.isEmpty()) {
            JSONObject jSONObject2 = new JSONObject();
            for (LocalFileEntity localFileEntity : localFileMap.values()) {
                jSONObject2.put(localFileEntity.url, localFileEntity.toJson());
            }
            jSONObject.put("localFileMap", jSONObject2);
        }
        jSONObject.put("moduleVersion", getModuleVersion());
        jSONObject.put("moduleVersion", getModuleVersion());
        jSONObject.put("extendedVersion", getExtendedVersion());
        jSONObject.put("fileVersion", getFileVersion());
        jSONObject.put(Constant.KEY_PROMOTION_AVAILABLE, isAvailable());
        jSONObject.put("type", getType());
        jSONObject.put("minFileVer", getMinFileVer());
        jSONObject.put("canPreloadHtml", isCanPreloadHtml());
        jSONObject.put("bConfig", CommonUtils.base10StrToBase2Str(getBConfig()));
        jSONObject.put("hasBuildIn", this.hasBuildIn);
        jSONObject.put("isBuildInConfig", this.isBuildConfig);
        return jSONObject;
    }

    @NonNull
    public String toString() {
        try {
            return toJson().toString();
        } catch (Exception e2) {
            Log.e(TAG, e2);
            return "";
        }
    }

    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public OfflineFiles fromJson(JSONObject jSONObject) throws JSONException {
        throw new RuntimeException("OfflineFiles fromJson no implemented.");
    }
}
