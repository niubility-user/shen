package com.jingdong.common.cart.clean;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.parser.Feature;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.TypeToken;
import com.jd.jdsdk.security.DesCbcCrypto;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.cart.CartBaseUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.jingdong.sdk.threadpool.callback.UIRunnerTaskCallback;
import de.greenrobot.event.EventBus;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class OnDiskCacheListener implements HttpGroup.OnCommonListener {
    public static volatile HttpResponse cachedResponse;
    private static final Set<String> funSetDefault;
    public static volatile boolean isHitException;
    public static volatile boolean isNeedWrite;
    private static volatile boolean writeSwitch;
    private final String functionId;
    private boolean isNoResponse;
    public final boolean readSwitch;
    private String response;
    private static final String key = JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES);
    private static Set<String> funSet = new HashSet();

    static {
        HashSet hashSet = new HashSet();
        funSetDefault = hashSet;
        isHitException = false;
        hashSet.add("cartChange");
        hashSet.add("cartCheckAll");
        hashSet.add("cartUnCheckAll");
        hashSet.add("cartUnCheckSingle");
        hashSet.add("cartCheckSingle");
        hashSet.add(CartConstant.FUNCTION_ID_CART_SWITCH);
        hashSet.add("cartRemoveGift");
        hashSet.add("cartRemove");
        hashSet.add("cartReplace");
        hashSet.add("cart");
    }

    public OnDiskCacheListener(String str, String str2) {
        this.isNoResponse = false;
        this.response = null;
        this.readSwitch = isReadCache() || isUseDiskQuery();
        this.functionId = str;
        if (str2 != null) {
            this.isNoResponse = JDJSON.parseObject(str2).optBoolean(CartConstant.KEY_NO_RESPONSE);
            this.response = JDJSON.parseObject(str2).optString("response");
        }
        if (funSet.size() == 0) {
            addFuc();
        }
    }

    private synchronized void addFuc() {
        JDJSONArray parseArray = JDJSON.parseArray(JDMobileConfig.getInstance().getConfig("jdCart", "useDiskNetworkCache", "interface", null));
        if (parseArray == null) {
            funSet = funSetDefault;
        } else {
            for (int i2 = 0; i2 < parseArray.size(); i2++) {
                funSet.add(parseArray.optString(i2));
            }
        }
    }

    public static void deleteDiskCache(final File file) {
        ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.cart.clean.OnDiskCacheListener.6
            @Override // java.lang.Runnable
            public void run() {
                File file2 = file;
                if (file2 != null && file2.exists() && file.isFile()) {
                    file.delete();
                }
            }
        });
    }

    public static File getDiskCacheDir(Context context) {
        File cacheDir = context.getCacheDir();
        return cacheDir != null ? cacheDir : FileService.getInternalDirectory(null, true);
    }

    public static boolean isReadCache() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig("jdCart", "useDiskNetworkCache", "useDiskNetworkCache", "0"), "1");
    }

    public static boolean isUseDiskQuery() {
        return TextUtils.equals(SwitchQueryFetcher.getSwitchStringValue("cartCache", "0"), "1");
    }

    public HttpResponse parseResponse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JDJSONObject parseObject = JDJSON.parseObject(str);
            HttpResponse httpResponse = new HttpResponse(parseObject.optJSONObject("moreParams").getInnerMap());
            httpResponse.setCache(parseObject.optBoolean("cache"));
            httpResponse.setCode(parseObject.optInt("code"));
            httpResponse.setFastJsonArray(parseObject.optJSONArray("fastJsonArray"));
            httpResponse.setFastJsonObject(parseObject.optJSONObject("fastJsonObject"));
            httpResponse.setStatusCode(parseObject.optInt(HiAnalyticsConstant.HaKey.BI_KEY_RESULT));
            httpResponse.setLength(parseObject.optLong(CartConstant.KEY_LENGTH));
            httpResponse.setString(parseObject.optString("string"));
            httpResponse.setJsonArray(new JSONArrayPoxy(new JSONArray()));
            httpResponse.setJsonObject(new JSONObjectProxy(new JSONObject()));
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, Object> entry : parseObject.optJSONObject("header").getInnerMap().entrySet()) {
                hashMap.put(entry.getKey(), (String) entry.getValue());
            }
            httpResponse.setHeader(hashMap);
            return httpResponse;
        } catch (Exception e2) {
            e2.printStackTrace();
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    private void readFromDiskCache(final File file, String str, final UIRunnerTaskCallback<HttpResponse> uIRunnerTaskCallback) {
        ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.cart.clean.OnDiskCacheListener.5
            {
                OnDiskCacheListener.this = this;
            }

            /* JADX WARN: Removed duplicated region for block: B:156:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                Throwable th;
                FileInputStream fileInputStream;
                if (file.exists()) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    FileInputStream fileInputStream2 = null;
                    try {
                        try {
                            try {
                                fileInputStream = new FileInputStream(file.getPath() + File.separator + CartBaseUtil.CART_CACHE_FILE);
                            } catch (Exception e2) {
                                e = e2;
                                fileInputStream = null;
                            } catch (Throwable th2) {
                                th = th2;
                                if (0 != 0) {
                                }
                                byteArrayOutputStream.close();
                                throw th;
                            }
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = fileInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(bArr, 0, read);
                                }
                                if (!TextUtils.isEmpty(byteArrayOutputStream.toString())) {
                                    HttpResponse parseResponse = OnDiskCacheListener.this.parseResponse(DesCbcCrypto.decrypt(byteArrayOutputStream.toString(), OnDiskCacheListener.key, (byte[]) null));
                                    if (parseResponse != null) {
                                        if (OnDiskCacheListener.cachedResponse == null) {
                                            OnDiskCacheListener.cachedResponse = parseResponse;
                                        }
                                        uIRunnerTaskCallback.onSuccess(null, parseResponse);
                                    } else {
                                        uIRunnerTaskCallback.onFailed(null, null);
                                    }
                                } else {
                                    uIRunnerTaskCallback.onFailed(null, null);
                                }
                                fileInputStream.close();
                                byteArrayOutputStream.close();
                            } catch (Exception e3) {
                                e = e3;
                                e.printStackTrace();
                                ExceptionReporter.reportExceptionToBugly(e);
                                uIRunnerTaskCallback.onFailed(null, null);
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                byteArrayOutputStream.close();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (0 != 0) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    ExceptionReporter.reportExceptionToBugly(e4);
                                    throw th;
                                }
                            }
                            byteArrayOutputStream.close();
                            throw th;
                        }
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        ExceptionReporter.reportExceptionToBugly(e5);
                    }
                }
            }
        }, "worker readDisk");
    }

    private void readMemoryOrDisk(UIRunnerTaskCallback<HttpResponse> uIRunnerTaskCallback) {
        try {
            if (cachedResponse != null) {
                uIRunnerTaskCallback.onSuccess(null, cachedResponse);
                return;
            }
            File diskCacheDir = getDiskCacheDir(JdSdk.getInstance().getApplicationContext());
            if (new File(diskCacheDir.getPath(), CartBaseUtil.CART_CACHE_FILE).exists() && SharedPreferencesUtil.getInt("cacheVersion", 0) == PackageInfoUtil.getVersionCode()) {
                readFromDiskCache(diskCacheDir, this.functionId, uIRunnerTaskCallback);
            } else {
                uIRunnerTaskCallback.onFailed(null, null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            ExceptionReporter.reportExceptionToBugly(e2);
            uIRunnerTaskCallback.onFailed(null, null);
        }
    }

    public static void writeCachedResponse() {
        if (writeSwitch) {
            File diskCacheDir = getDiskCacheDir(JdSdk.getInstance().getApplicationContext());
            if (cachedResponse != null) {
                writeToDiskCache(diskCacheDir, "", JDJSON.toJSONString(cachedResponse));
            }
        }
    }

    private static void writeToDiskCache(final File file, String str, final String str2) {
        ThreadManager.single().post(new Runnable() { // from class: com.jingdong.common.cart.clean.OnDiskCacheListener.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = null;
                try {
                    try {
                        try {
                            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, CartBaseUtil.CART_CACHE_FILE));
                            try {
                                fileOutputStream2.write(DesCbcCrypto.encrypt(str2, OnDiskCacheListener.key, (byte[]) null).getBytes());
                                int versionCode = PackageInfoUtil.getVersionCode();
                                SharedPreferencesUtil.putInt("cacheVersion", versionCode);
                                fileOutputStream2.close();
                                fileOutputStream = versionCode;
                            } catch (Exception e2) {
                                e = e2;
                                fileOutputStream = fileOutputStream2;
                                e.printStackTrace();
                                ExceptionReporter.reportExceptionToBugly(e);
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                    fileOutputStream = fileOutputStream;
                                }
                            } catch (Throwable th) {
                                th = th;
                                fileOutputStream = fileOutputStream2;
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        ExceptionReporter.reportExceptionToBugly(e3);
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e4) {
                            e = e4;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                    ExceptionReporter.reportExceptionToBugly(e5);
                }
            }
        });
    }

    public abstract void endOperation(HttpResponse httpResponse);

    public abstract void errorOperation(HttpError httpError);

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(final HttpResponse httpResponse) {
        isHitException = false;
        List list = (List) JDJSON.parseObject(JDMobileConfig.getInstance().getConfig("jdCart", "useDiskNetworkCache", "resultCode", "[\"5\",\"8\"]"), new TypeToken<List<String>>() { // from class: com.jingdong.common.cart.clean.OnDiskCacheListener.1
            {
                OnDiskCacheListener.this = this;
            }
        }.getType(), new Feature[0]);
        if (this.readSwitch && funSet.contains(this.functionId) && list.contains(httpResponse.getFastJsonObject().optString("resultCode", "-1"))) {
            isHitException = true;
            readMemoryOrDisk(new UIRunnerTaskCallback<HttpResponse>() { // from class: com.jingdong.common.cart.clean.OnDiskCacheListener.2
                {
                    OnDiskCacheListener.this = this;
                }

                @Override // com.jingdong.sdk.threadpool.callback.UIRunnerTaskCallback, com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
                public void onFailed(String str, Throwable th) {
                    OnDiskCacheListener.this.endOperation(httpResponse);
                }

                @Override // com.jingdong.sdk.threadpool.callback.UIRunnerTaskCallback, com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
                public void onStart(String str) {
                }

                @Override // com.jingdong.sdk.threadpool.callback.UIRunnerTaskCallback, com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
                public void onSuccess(String str, HttpResponse httpResponse2) {
                    EventBus.getDefault().post(new BaseEvent("diskCache"));
                    OnDiskCacheListener.this.endOperation(httpResponse2);
                }
            });
            return;
        }
        endOperation(httpResponse);
        writeSwitch = TextUtils.equals(httpResponse.getFastJsonObject().optString("cache", "0"), "1");
        if (writeSwitch && funSet.contains(this.functionId) && !this.isNoResponse && TextUtils.isEmpty(this.response) && TextUtils.equals(httpResponse.getFastJsonObject().optString("code", "-1"), "0") && TextUtils.equals(httpResponse.getFastJsonObject().optString("resultCode", "-1"), "0")) {
            cachedResponse = httpResponse;
            if (isNeedWrite) {
                writeCachedResponse();
                isNeedWrite = false;
            }
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(final HttpError httpError) {
        isHitException = false;
        if (this.readSwitch && funSet.contains(this.functionId)) {
            isHitException = true;
            readMemoryOrDisk(new UIRunnerTaskCallback<HttpResponse>() { // from class: com.jingdong.common.cart.clean.OnDiskCacheListener.3
                {
                    OnDiskCacheListener.this = this;
                }

                @Override // com.jingdong.sdk.threadpool.callback.UIRunnerTaskCallback, com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
                public void onFailed(String str, Throwable th) {
                    OnDiskCacheListener.this.errorOperation(httpError);
                }

                @Override // com.jingdong.sdk.threadpool.callback.UIRunnerTaskCallback, com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
                public void onStart(String str) {
                }

                @Override // com.jingdong.sdk.threadpool.callback.UIRunnerTaskCallback, com.jingdong.sdk.threadpool.callback.RunnerTaskCallback
                public void onSuccess(String str, HttpResponse httpResponse) {
                    EventBus.getDefault().post(new BaseEvent("diskCache"));
                    OnDiskCacheListener.this.endOperation(httpResponse);
                }
            });
            return;
        }
        errorOperation(httpError);
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        readyOperation(httpSettingParams);
    }

    public abstract void readyOperation(HttpGroup.HttpSettingParams httpSettingParams);
}
