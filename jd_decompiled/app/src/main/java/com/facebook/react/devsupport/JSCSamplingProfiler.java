package com.facebook.react.devsupport;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

@ReactModule(name = "JSCSamplingProfiler", needsEagerInit = true)
/* loaded from: classes12.dex */
public class JSCSamplingProfiler extends ReactContextBaseJavaModule {
    private static final HashSet<JSCSamplingProfiler> sRegisteredDumpers = new HashSet<>();
    @Nullable
    private String mOperationError;
    private boolean mOperationInProgress;
    private int mOperationToken;
    @Nullable
    private SamplingProfiler mSamplingProfiler;
    @Nullable
    private String mSamplingProfilerResult;

    /* loaded from: classes12.dex */
    public static class ProfilerException extends Exception {
        ProfilerException(String str) {
            super(str);
        }
    }

    /* loaded from: classes12.dex */
    public interface SamplingProfiler extends JavaScriptModule {
        void poke(int i2);
    }

    public JSCSamplingProfiler(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mSamplingProfiler = null;
        this.mOperationInProgress = false;
        this.mOperationToken = 0;
        this.mOperationError = null;
        this.mSamplingProfilerResult = null;
    }

    private int getOperationToken() throws ProfilerException {
        if (!this.mOperationInProgress) {
            this.mOperationInProgress = true;
            int i2 = this.mOperationToken + 1;
            this.mOperationToken = i2;
            return i2;
        }
        throw new ProfilerException("Another operation already in progress.");
    }

    public static synchronized List<String> poke(long j2) throws ProfilerException {
        LinkedList linkedList;
        synchronized (JSCSamplingProfiler.class) {
            linkedList = new LinkedList();
            HashSet<JSCSamplingProfiler> hashSet = sRegisteredDumpers;
            if (!hashSet.isEmpty()) {
                Iterator<JSCSamplingProfiler> it = hashSet.iterator();
                while (it.hasNext()) {
                    JSCSamplingProfiler next = it.next();
                    next.pokeHelper(j2);
                    linkedList.add(next.mSamplingProfilerResult);
                }
            } else {
                throw new ProfilerException("No JSC registered");
            }
        }
        return linkedList;
    }

    private synchronized void pokeHelper(long j2) throws ProfilerException {
        SamplingProfiler samplingProfiler = this.mSamplingProfiler;
        if (samplingProfiler != null) {
            samplingProfiler.poke(getOperationToken());
            waitForOperation(j2);
        } else {
            throw new ProfilerException("SamplingProfiler.js module not connected");
        }
    }

    private static synchronized void registerSamplingProfiler(JSCSamplingProfiler jSCSamplingProfiler) {
        synchronized (JSCSamplingProfiler.class) {
            HashSet<JSCSamplingProfiler> hashSet = sRegisteredDumpers;
            if (!hashSet.contains(jSCSamplingProfiler)) {
                hashSet.add(jSCSamplingProfiler);
            } else {
                throw new RuntimeException("a JSCSamplingProfiler registered more than once");
            }
        }
    }

    private static synchronized void unregisterSamplingProfiler(JSCSamplingProfiler jSCSamplingProfiler) {
        synchronized (JSCSamplingProfiler.class) {
            sRegisteredDumpers.remove(jSCSamplingProfiler);
        }
    }

    private void waitForOperation(long j2) throws ProfilerException {
        try {
            wait(j2);
            if (!this.mOperationInProgress) {
                if (this.mOperationError != null) {
                    throw new ProfilerException(this.mOperationError);
                }
                return;
            }
            this.mOperationInProgress = false;
            throw new ProfilerException("heap capture timed out.");
        } catch (InterruptedException e2) {
            throw new ProfilerException("Waiting for heap capture failed: " + e2.getMessage());
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JSCSamplingProfiler";
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        this.mSamplingProfiler = (SamplingProfiler) getReactApplicationContext().getJSModule(SamplingProfiler.class);
        registerSamplingProfiler(this);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        unregisterSamplingProfiler(this);
        this.mSamplingProfiler = null;
    }

    @ReactMethod
    public synchronized void operationComplete(int i2, String str, String str2) {
        if (i2 == this.mOperationToken) {
            this.mOperationInProgress = false;
            this.mSamplingProfilerResult = str;
            this.mOperationError = str2;
            notify();
        } else {
            throw new RuntimeException("Completed operation is not in progress.");
        }
    }
}
