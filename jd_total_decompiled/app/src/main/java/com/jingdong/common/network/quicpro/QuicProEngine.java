package com.jingdong.common.network.quicpro;

import com.jingdong.common.network.cronet.CronetComponentHelper;
import com.jingdong.common.network.quicpro.UrlRequest;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public class QuicProEngine {
    public static final String TAG = "QuicProEngine";
    private QuicProNative mQuicProNative;

    /* loaded from: classes5.dex */
    private static class SingletonHolder {
        private static QuicProEngine instance = new QuicProEngine();

        private SingletonHolder() {
        }
    }

    public static QuicProEngine getInstance() {
        return SingletonHolder.instance;
    }

    private void init() {
        try {
            CronetComponentHelper.getMethod("com.jd.QuicProInitializer", "initialize", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException e2) {
            OKLog.d(TAG, e2.getClass().getSimpleName() + "" + e2.getMessage());
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            OKLog.d(TAG, e3.getClass().getSimpleName() + "" + e3.getMessage());
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            OKLog.d(TAG, e4.getClass().getSimpleName() + "" + e4.getMessage());
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            OKLog.d(TAG, e5.getClass().getSimpleName() + "" + e5.getMessage());
            e5.printStackTrace();
        }
        this.mQuicProNative = QuicProNative.getQuicProNative();
        if (OKLog.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("\u52a0\u8f7dQuicPro\u5e93\u72b6\u6001 -> ");
            sb.append(this.mQuicProNative != null);
            OKLog.d(TAG, sb.toString());
            OKLog.d(TAG, "mQuicProNative\u7c7b\u578b " + this.mQuicProNative.getClass().getName());
        }
    }

    public QuicProNative getQuicProJni() {
        return this.mQuicProNative;
    }

    public UrlRequest.Builder newUrlRequestBuilder(String str, UrlRequest.Callback callback, Executor executor) {
        return new UrlRequestBuilderImpl(str, callback, executor);
    }

    public void setLogLevel(int i2) {
        this.mQuicProNative.SetLogLevel(i2);
    }

    private QuicProEngine() {
        init();
    }
}
