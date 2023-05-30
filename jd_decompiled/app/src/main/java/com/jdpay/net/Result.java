package com.jdpay.net;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.exception.JPException;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.listener.ProgressListener;
import com.jdpay.lib.util.JDPayLog;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes18.dex */
public class Result<DATA> implements ProgressListener {
    protected DATA data;
    protected final Handler handlerOnMainThread;
    protected final boolean hasProgress;
    protected final ResultObserver<DATA> proxyObserver;
    protected final ResultObserver<DATA> realObserver;
    protected Throwable throwable;

    public Result(@NonNull ResultObserver<DATA> resultObserver) {
        boolean z = resultObserver instanceof ProgressResultObserver;
        this.hasProgress = z;
        this.realObserver = resultObserver;
        ClassLoader classLoader = resultObserver.getClass().getClassLoader();
        Class[] clsArr = new Class[1];
        clsArr[0] = z ? ProgressResultObserver.class : ResultObserver.class;
        this.proxyObserver = (ResultObserver) Proxy.newProxyInstance(classLoader, clsArr, new ObserverInvocationHandler());
        this.handlerOnMainThread = new Handler(Looper.getMainLooper());
    }

    public void onHandleResult(@Nullable Object obj, @Nullable Converter<Object, DATA> converter, @Nullable Throwable th) {
        if (converter == null) {
            if (th == null) {
                th = new JPException("Unkonwn error");
            }
            this.proxyObserver.onFailure(th);
            return;
        }
        try {
            DATA convert = converter.convert(obj);
            this.data = convert;
            this.proxyObserver.onSuccess(convert);
        } catch (Throwable th2) {
            JDPayLog.e(th2);
            this.proxyObserver.onFailure(th2);
        }
    }

    @Override // com.jdpay.lib.listener.ProgressListener
    public void onProgress(long j2) {
        if (this.hasProgress) {
            ((ProgressResultObserver) this.proxyObserver).onProgress(j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class ObserverInvocationHandler implements InvocationHandler, Runnable {
        volatile Object[] args;
        volatile boolean isThrowable;
        volatile Method method;
        volatile int onThread;

        private ObserverInvocationHandler() {
            Result.this = r1;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Handler handler;
            this.method = method;
            this.args = objArr;
            OnResult onResult = (OnResult) Result.this.realObserver.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes()).getAnnotation(OnResult.class);
            if (onResult != null) {
                this.onThread = onResult.onThread();
                this.isThrowable = onResult.isThrowable();
            } else {
                this.onThread = 1;
                this.isThrowable = false;
            }
            if (this.onThread == 1 && (handler = Result.this.handlerOnMainThread) != null) {
                handler.post(this);
                return null;
            }
            invoke();
            return null;
        }

        @Override // java.lang.Runnable
        public void run() {
            invoke();
        }

        private void invoke() {
            if (this.isThrowable) {
                try {
                    this.method.invoke(Result.this.realObserver, this.args);
                    return;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    JDPayLog.e(e2);
                    Result.this.realObserver.onFailure(e2);
                    return;
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                    JDPayLog.e(e3);
                    Result.this.realObserver.onFailure(e3);
                    return;
                }
            }
            try {
                this.method.invoke(Result.this.realObserver, this.args);
            } catch (Throwable th) {
                JDPayLog.e(th);
                Result.this.realObserver.onFailure(th);
            }
        }
    }
}
