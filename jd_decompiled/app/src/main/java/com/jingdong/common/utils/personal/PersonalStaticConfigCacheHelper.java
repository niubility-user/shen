package com.jingdong.common.utils.personal;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jingdong.common.impl.UtilFactory;
import com.jingdong.common.protocol.ParserModule;
import com.jingdong.common.protocol.parse.IJsonParse;
import com.jingdong.common.utils.PersonalNetDataManager;
import com.jingdong.common.utils.PersonalSwitchManager;
import com.jingdong.common.utils.RxUtil;
import com.jingdong.common.utils.rx.internal.schedulers.IOSchedulers;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/* loaded from: classes6.dex */
public class PersonalStaticConfigCacheHelper {
    private static final String CHILD_DIR = "personal";
    private static final String FILE_NAME = "static_config";
    private static final String TAG = "PersonalStaticConfigCacheHelper";
    private static final int TIME_OUT = 3;

    /* loaded from: classes6.dex */
    public static class CacheBean {
        public final String jsonString;
        public final String menuTimeStamp;

        public CacheBean(String str, String str2) {
            this.jsonString = str;
            this.menuTimeStamp = str2;
        }
    }

    /* loaded from: classes.dex */
    public interface GetCacheCallback {
        void onGetCache(@Nullable String str, @Nonnull String str2);
    }

    static /* synthetic */ CacheBean access$000() {
        return handleCacheBean();
    }

    @NonNull
    public static synchronized String convertStreamToString() {
        synchronized (PersonalStaticConfigCacheHelper.class) {
            FileInputStream fileInputStream = null;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(getParentCacheDir());
                String str = File.separator;
                sb.append(str);
                sb.append(CHILD_DIR);
                sb.append(str);
                sb.append(createFileName(FILE_NAME));
                File file = new File(sb.toString());
                if (file.exists()) {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[fileInputStream2.available()];
                        fileInputStream2.read(bArr);
                        fileInputStream2.close();
                        String str2 = new String(bArr, "UTF-8");
                        if (OKLog.D) {
                            OKLog.d(TAG, String.format("convertStreamToString:%s ", str2));
                        }
                        if (!isJSONValid(str2)) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e2) {
                                if (OKLog.D) {
                                    OKLog.d(TAG, String.format("convertStreamToString onError:%s ", e2));
                                }
                            }
                            return PlatformLocalStaticConfig.STATIC_CONFIG;
                        }
                        try {
                            fileInputStream2.close();
                        } catch (IOException e3) {
                            if (OKLog.D) {
                                OKLog.d(TAG, String.format("convertStreamToString onError:%s ", e3));
                            }
                        }
                        return str2;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (OKLog.D) {
                            OKLog.d(TAG, String.format("convertStreamToString onError:%s ", th));
                        }
                        JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalStaticConfigCacheHelper convertStreamToString: ", th));
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                if (OKLog.D) {
                                    OKLog.d(TAG, String.format("convertStreamToString onError:%s ", e4));
                                }
                            }
                        }
                        return PlatformLocalStaticConfig.STATIC_CONFIG;
                    }
                }
                return PlatformLocalStaticConfig.STATIC_CONFIG;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private static String createFileName(String str) {
        return Md5Encrypt.md5(str) + FileService.CACHE_EXT_NAME_JSON;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0030 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0032 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void doSave(java.lang.String r7) {
        /*
            java.lang.Class<com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper> r0 = com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper.class
            monitor-enter(r0)
            java.lang.String r1 = "static_config"
            java.lang.String r1 = createFileName(r1)     // Catch: java.lang.Throwable -> La5
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> La5
            java.io.File r4 = getParentCacheDir()     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> La5
            java.lang.String r5 = "personal"
            r3.<init>(r4, r5)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> La5
            boolean r4 = r3.exists()     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> La5
            if (r4 != 0) goto L2e
            r3.mkdirs()     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> La5
            goto L2e
        L1f:
            r4 = move-exception
            goto L23
        L21:
            r4 = move-exception
            r3 = r2
        L23:
            boolean r5 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> La5
            if (r5 == 0) goto L2e
            java.lang.String r5 = "PersonalStaticConfigCacheHelper"
            java.lang.String r6 = "doSave get error: "
            com.jingdong.sdk.oklog.OKLog.e(r5, r6, r4)     // Catch: java.lang.Throwable -> La5
        L2e:
            if (r3 != 0) goto L32
            monitor-exit(r0)
            return
        L32:
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r5.<init>()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.io.File r3 = r3.getAbsoluteFile()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r5.append(r3)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r3 = java.io.File.separator     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r5.append(r3)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r5.append(r1)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r1 = r5.toString()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
            java.lang.String r1 = "UTF-8"
            byte[] r7 = r7.getBytes(r1)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            r4.write(r7)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            r4.close()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            r4.close()     // Catch: java.lang.Exception -> L5f java.lang.Throwable -> La5
            goto L90
        L5f:
            r7 = move-exception
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> La5
            if (r1 == 0) goto L90
            java.lang.String r1 = "PersonalStaticConfigCacheHelper"
            java.lang.String r2 = "doSave get error: "
        L68:
            com.jingdong.sdk.oklog.OKLog.e(r1, r2, r7)     // Catch: java.lang.Throwable -> La5
            goto L90
        L6c:
            r7 = move-exception
            r2 = r4
            goto L92
        L6f:
            r7 = move-exception
            r2 = r4
            goto L75
        L72:
            r7 = move-exception
            goto L92
        L74:
            r7 = move-exception
        L75:
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L72
            if (r1 == 0) goto L80
            java.lang.String r1 = "PersonalStaticConfigCacheHelper"
            java.lang.String r3 = "doSave get error: "
            com.jingdong.sdk.oklog.OKLog.e(r1, r3, r7)     // Catch: java.lang.Throwable -> L72
        L80:
            if (r2 == 0) goto L90
            r2.close()     // Catch: java.lang.Exception -> L86 java.lang.Throwable -> La5
            goto L90
        L86:
            r7 = move-exception
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> La5
            if (r1 == 0) goto L90
            java.lang.String r1 = "PersonalStaticConfigCacheHelper"
            java.lang.String r2 = "doSave get error: "
            goto L68
        L90:
            monitor-exit(r0)
            return
        L92:
            if (r2 == 0) goto La4
            r2.close()     // Catch: java.lang.Exception -> L98 java.lang.Throwable -> La5
            goto La4
        L98:
            r1 = move-exception
            boolean r2 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> La5
            if (r2 == 0) goto La4
            java.lang.String r2 = "PersonalStaticConfigCacheHelper"
            java.lang.String r3 = "doSave get error: "
            com.jingdong.sdk.oklog.OKLog.e(r2, r3, r1)     // Catch: java.lang.Throwable -> La5
        La4:
            throw r7     // Catch: java.lang.Throwable -> La5
        La5:
            r7 = move-exception
            monitor-exit(r0)
            goto La9
        La8:
            throw r7
        La9:
            goto La8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper.doSave(java.lang.String):void");
    }

    public static void getCache(GetCacheCallback getCacheCallback) {
        if (OKLog.D) {
            OKLog.d(TAG, "getCache");
        }
        try {
            handleGetCacheSubscription(getCacheCallback);
        } catch (Throwable th) {
            if (getCacheCallback != null) {
                getCacheCallback.onGetCache(null, "0");
            }
            JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalStaticConfigCacheHelper getCache: ", th));
            if (OKLog.D) {
                OKLog.d(TAG, "getCache error: " + th);
            }
        }
    }

    public static Observable<CacheBean> getCacheObservable() {
        Observable onErrorResumeNext = Observable.create(new Observable.OnSubscribe<CacheBean>() { // from class: com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper.2
            @Override // rx.functions.Action1
            public void call(Subscriber<? super CacheBean> subscriber) {
                if (subscriber == null || subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onNext(PersonalStaticConfigCacheHelper.access$000());
                subscriber.onCompleted();
                RxUtil.unSubscribeSafely(subscriber);
            }
        }).timeout(3L, TimeUnit.SECONDS).onErrorResumeNext(Observable.just(new CacheBean(null, "0")));
        try {
            return onErrorResumeNext.subscribeOn(IOSchedulers.io(100));
        } catch (Throwable th) {
            JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalStaticConfigCacheHelper getCacheObservable: ", th));
            return onErrorResumeNext.subscribeOn(Schedulers.io());
        }
    }

    private static File getParentCacheDir() {
        return JdSdk.getInstance().getApplication().getFilesDir();
    }

    private static CacheBean handleCacheBean() {
        IJsonParse jsonParser = UtilFactory.getInstance().getJsonParser(ParserModule.PARSER_GSON);
        String staticData = PersonalSwitchManager.getAsyncInflateSwitch() ? PersonalNetDataManager.INSTANCE.getStaticData() : convertStreamToString();
        if (jsonParser != null && !TextUtils.isEmpty(staticData)) {
            String optString = jsonParser.optString(staticData, "menuTimeStamp");
            if (OKLog.D) {
                OKLog.d(TAG, String.format("handleCacheBean menuTimeStamp: %s | jsonString: %s", optString, staticData));
            }
            return new CacheBean(staticData, TextUtils.isEmpty(optString) ? "0" : optString);
        }
        return new CacheBean(null, "0");
    }

    private static void handleGetCacheSubscription(final GetCacheCallback getCacheCallback) {
        getCacheObservable().subscribe((Subscriber<? super CacheBean>) new Subscriber<CacheBean>() { // from class: com.jingdong.common.utils.personal.PersonalStaticConfigCacheHelper.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                GetCacheCallback getCacheCallback2 = GetCacheCallback.this;
                if (getCacheCallback2 != null) {
                    getCacheCallback2.onGetCache(null, "0");
                }
                if (OKLog.D) {
                    OKLog.d(PersonalStaticConfigCacheHelper.TAG, String.format("getCache onError:%s ", th));
                }
                JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalStaticConfigCacheHelper handleGetCacheSubscription: ", th));
            }

            @Override // rx.Observer
            public void onNext(CacheBean cacheBean) {
                if (GetCacheCallback.this != null) {
                    if (cacheBean == null) {
                        cacheBean = new CacheBean(null, "0");
                    }
                    GetCacheCallback.this.onGetCache(cacheBean.jsonString, TextUtils.isEmpty(cacheBean.menuTimeStamp) ? "0" : cacheBean.menuTimeStamp);
                }
            }
        });
    }

    public static boolean isJSONValid(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            new JSONObject(str);
            return true;
        } catch (Exception unused) {
            String substring = str.substring(0, Math.min(20, str.length()));
            JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalStaticConfigCacheHelper isJSONValid JSONObject: " + substring));
            try {
                new JSONArray(str);
                return true;
            } catch (Exception unused2) {
                JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalStaticConfigCacheHelper isJSONValid JSONArray: " + substring));
                return false;
            }
        }
    }
}
