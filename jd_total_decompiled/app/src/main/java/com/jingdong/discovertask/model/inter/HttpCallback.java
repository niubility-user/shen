package com.jingdong.discovertask.model.inter;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;

/* loaded from: classes12.dex */
public class HttpCallback<T> implements HttpGroup.OnCommonListener {
    public static final String TAG = "HttpCallback";
    private final WeakReference<BaseActivity> mReferenceContext;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Field signature parse error: nt
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: TT at position 1 ('T'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.visitors.SignatureProcessor.parseFieldSignature(SignatureProcessor.java:128)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:36)
     */
    /* loaded from: classes12.dex */
    public class NetRunnable implements Runnable {
        int msgWhat;
        Object nt;

        /* JADX WARN: Failed to parse method signature: (ITT)V
        jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: (ITT)V at position 3 ('T'), unexpected: T
        	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
        	at jadx.core.dex.nodes.parser.SignatureParser.consumeMethodArgs(SignatureParser.java:318)
        	at jadx.core.dex.visitors.SignatureProcessor.parseMethodSignature(SignatureProcessor.java:154)
        	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:39)
         */
        public NetRunnable(int i2, Object obj) {
            this.msgWhat = i2;
            this.nt = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.msgWhat;
            if (i2 == 0) {
                HttpCallback.this.handReady();
            } else if (i2 == 1) {
                HttpCallback.this.handSuccess(this.nt);
            } else if (i2 != 2) {
            } else {
                HttpCallback.this.handError(this.nt);
            }
        }
    }

    public HttpCallback(BaseActivity baseActivity) {
        this.mReferenceContext = new WeakReference<>(baseActivity);
    }

    private void sendMessageToMainThread(int i2, T t) {
        if (this.mReferenceContext.get() != null) {
            this.mReferenceContext.get().post(new NetRunnable(i2, t));
        }
    }

    public void handError(T t) {
    }

    public void handReady() {
    }

    public void handSuccess(T t) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        sendMessageToMainThread(1, JDJSON.parseObject(httpResponse.getString(), (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        Class cls = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        HttpResponse httpResponse = httpError.getHttpResponse();
        sendMessageToMainThread(2, (httpResponse == null || httpResponse.getStatusCode() != 200 || TextUtils.isEmpty(httpResponse.getString())) ? null : JDJSON.parseObject(httpResponse.getString(), cls));
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        sendMessageToMainThread(0, null);
    }
}
