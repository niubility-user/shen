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
import java.io.FileOutputStream;
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
    */
    public static synchronized void doSave(String str) {
        File file;
        String str2;
        String str3;
        synchronized (PersonalStaticConfigCacheHelper.class) {
            String createFileName = createFileName(FILE_NAME);
            FileOutputStream fileOutputStream = null;
            try {
                file = new File(getParentCacheDir(), CHILD_DIR);
            } catch (Exception e2) {
                e = e2;
                file = null;
            }
            try {
                try {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                } catch (Exception e3) {
                    e = e3;
                    if (OKLog.E) {
                        OKLog.e(TAG, "doSave get error: ", e);
                    }
                    if (file != null) {
                    }
                }
                if (file != null) {
                    return;
                }
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file.getAbsoluteFile() + File.separator + createFileName);
                    try {
                        fileOutputStream2.write(str.getBytes("UTF-8"));
                        fileOutputStream2.close();
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e4) {
                            e = e4;
                            if (OKLog.E) {
                                str2 = TAG;
                                str3 = "doSave get error: ";
                                OKLog.e(str2, str3, e);
                            }
                        }
                    } catch (Exception e5) {
                        e = e5;
                        fileOutputStream = fileOutputStream2;
                        if (OKLog.E) {
                            OKLog.e(TAG, "doSave get error: ", e);
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e6) {
                                e = e6;
                                if (OKLog.E) {
                                    str2 = TAG;
                                    str3 = "doSave get error: ";
                                    OKLog.e(str2, str3, e);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e7) {
                                if (OKLog.E) {
                                    OKLog.e(TAG, "doSave get error: ", e7);
                                }
                            }
                        }
                        throw th;
                    }
                } catch (Exception e8) {
                    e = e8;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
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
