package com.jd.lib.babel.servicekit.networkkit;

import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class Request {
    private int attempts;
    private Map<String, String> bodys;
    private String finalUrl;
    private String functionId;
    private String functionIdVersion;
    private Map<String, String> headers;
    private String host;
    private boolean isWeakReferenceListenerMode;
    private CancelListener mCancelListener;
    private Listener mListener;
    private WeakReference<Listener> mListenerWeakReference;
    private int method;
    private String strBody;

    /* loaded from: classes13.dex */
    public interface CancelListener {
        void cancel();
    }

    /* loaded from: classes13.dex */
    public interface Listener {
        void onEnd(BabelResponse babelResponse);

        void onError(BabelError babelError);

        void onStart();
    }

    public Request(int i2, String str, String str2, String str3, Map<String, String> map, Map<String, String> map2, String str4, int i3, Listener listener, boolean z) {
        this.method = i2;
        this.host = str;
        this.functionId = str2;
        this.functionIdVersion = str3;
        this.headers = map;
        this.bodys = map2;
        this.finalUrl = str4;
        this.attempts = i3;
        this.isWeakReferenceListenerMode = z;
        if (z) {
            this.mListenerWeakReference = new WeakReference<>(listener);
        } else {
            this.mListener = listener;
        }
    }

    public void cacel() {
        CancelListener cancelListener = this.mCancelListener;
        if (cancelListener != null) {
            cancelListener.cancel();
        }
    }

    public int getAttempts() {
        return this.attempts;
    }

    public Map<String, String> getBody() {
        return this.bodys;
    }

    public String getBodyJsonStr() {
        if (TextUtils.isEmpty(this.strBody)) {
            if (this.bodys == null) {
                this.strBody = "{}";
            } else {
                this.strBody = new JSONObject(this.bodys).toString();
            }
        }
        return this.strBody;
    }

    public String getFinalUrl() {
        return this.finalUrl;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public String getFunctionIdVersion() {
        return this.functionIdVersion;
    }

    public Map<String, String> getHeader() {
        return this.headers;
    }

    public Listener getListener() {
        WeakReference<Listener> weakReference;
        if (this.isWeakReferenceListenerMode && (weakReference = this.mListenerWeakReference) != null) {
            return weakReference.get();
        }
        return this.mListener;
    }

    public int getMethod() {
        return this.method;
    }

    public void setCancelListener(CancelListener cancelListener) {
        this.mCancelListener = cancelListener;
    }
}
