package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.devsupport.JSDebuggerWebSocketClient;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class WebsocketJavaScriptExecutor implements JavaJSExecutor {
    private static final int CONNECT_RETRY_COUNT = 3;
    private static final long CONNECT_TIMEOUT_MS = 5000;
    private final HashMap<String, String> mInjectedObjects = new HashMap<>();
    @Nullable
    private JSDebuggerWebSocketClient mWebSocketClient;

    /* loaded from: classes12.dex */
    private static class JSExecutorCallbackFuture implements JSDebuggerWebSocketClient.JSDebuggerCallback {
        @Nullable
        private Throwable mCause;
        @Nullable
        private String mResponse;
        private final Semaphore mSemaphore;

        private JSExecutorCallbackFuture() {
            this.mSemaphore = new Semaphore(0);
        }

        @Nullable
        public String get() throws Throwable {
            this.mSemaphore.acquire();
            Throwable th = this.mCause;
            if (th == null) {
                return this.mResponse;
            }
            throw th;
        }

        @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
        public void onFailure(Throwable th) {
            this.mCause = th;
            this.mSemaphore.release();
        }

        @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
        public void onSuccess(@Nullable String str) {
            this.mResponse = str;
            this.mSemaphore.release();
        }
    }

    /* loaded from: classes12.dex */
    public interface JSExecutorConnectCallback {
        void onFailure(Throwable th);

        void onSuccess();
    }

    /* loaded from: classes12.dex */
    public static class WebsocketExecutorTimeoutException extends Exception {
        public WebsocketExecutorTimeoutException(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectInternal(String str, final JSExecutorConnectCallback jSExecutorConnectCallback) {
        final JSDebuggerWebSocketClient jSDebuggerWebSocketClient = new JSDebuggerWebSocketClient();
        final Handler handler = new Handler(Looper.getMainLooper());
        jSDebuggerWebSocketClient.connect(str, new JSDebuggerWebSocketClient.JSDebuggerCallback() { // from class: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.2
            private boolean didSendResult = false;

            @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
            public void onFailure(Throwable th) {
                handler.removeCallbacksAndMessages(null);
                if (this.didSendResult) {
                    return;
                }
                jSExecutorConnectCallback.onFailure(th);
                this.didSendResult = true;
            }

            @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
            public void onSuccess(@Nullable String str2) {
                jSDebuggerWebSocketClient.prepareJSRuntime(new JSDebuggerWebSocketClient.JSDebuggerCallback() { // from class: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.2.1
                    @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
                    public void onFailure(Throwable th) {
                        handler.removeCallbacksAndMessages(null);
                        if (AnonymousClass2.this.didSendResult) {
                            return;
                        }
                        jSExecutorConnectCallback.onFailure(th);
                        AnonymousClass2.this.didSendResult = true;
                    }

                    @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
                    public void onSuccess(@Nullable String str3) {
                        handler.removeCallbacksAndMessages(null);
                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                        WebsocketJavaScriptExecutor.this.mWebSocketClient = jSDebuggerWebSocketClient;
                        if (AnonymousClass2.this.didSendResult) {
                            return;
                        }
                        jSExecutorConnectCallback.onSuccess();
                        AnonymousClass2.this.didSendResult = true;
                    }
                });
            }
        });
        handler.postDelayed(new Runnable() { // from class: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.3
            @Override // java.lang.Runnable
            public void run() {
                jSDebuggerWebSocketClient.closeQuietly();
                jSExecutorConnectCallback.onFailure(new WebsocketExecutorTimeoutException("Timeout while connecting to remote debugger"));
            }
        }, 5000L);
    }

    @Override // com.facebook.react.bridge.JavaJSExecutor
    public void close() {
        JSDebuggerWebSocketClient jSDebuggerWebSocketClient = this.mWebSocketClient;
        if (jSDebuggerWebSocketClient != null) {
            jSDebuggerWebSocketClient.closeQuietly();
        }
    }

    public void connect(final String str, final JSExecutorConnectCallback jSExecutorConnectCallback) {
        new AtomicInteger(3);
        connectInternal(str, new JSExecutorConnectCallback
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: INVOKE 
              (r2v0 'this' com.facebook.react.devsupport.WebsocketJavaScriptExecutor A[IMMUTABLE_TYPE, THIS])
              (r3v0 'str' java.lang.String)
              (wrap: com.facebook.react.devsupport.WebsocketJavaScriptExecutor$JSExecutorConnectCallback : 0x0008: CONSTRUCTOR 
              (r2v0 'this' com.facebook.react.devsupport.WebsocketJavaScriptExecutor A[IMMUTABLE_TYPE, THIS])
              (r4v0 'jSExecutorConnectCallback' com.facebook.react.devsupport.WebsocketJavaScriptExecutor$JSExecutorConnectCallback A[DONT_INLINE])
              (r0 I:java.util.concurrent.atomic.AtomicInteger A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'str' java.lang.String A[DONT_INLINE])
             A[MD:(com.facebook.react.devsupport.WebsocketJavaScriptExecutor, com.facebook.react.devsupport.WebsocketJavaScriptExecutor$JSExecutorConnectCallback, java.util.concurrent.atomic.AtomicInteger, java.lang.String):void (m), WRAPPED] (LINE:2) call: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.1.<init>(com.facebook.react.devsupport.WebsocketJavaScriptExecutor, com.facebook.react.devsupport.WebsocketJavaScriptExecutor$JSExecutorConnectCallback, java.util.concurrent.atomic.AtomicInteger, java.lang.String):void type: CONSTRUCTOR)
             type: DIRECT call: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.connectInternal(java.lang.String, com.facebook.react.devsupport.WebsocketJavaScriptExecutor$JSExecutorConnectCallback):void A[MD:(java.lang.String, com.facebook.react.devsupport.WebsocketJavaScriptExecutor$JSExecutorConnectCallback):void (m)] (LINE:3) in method: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.connect(java.lang.String, com.facebook.react.devsupport.WebsocketJavaScriptExecutor$JSExecutorConnectCallback):void, file: classes12.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            java.util.concurrent.atomic.AtomicInteger r0 = new java.util.concurrent.atomic.AtomicInteger
            r1 = 3
            r0.<init>(r1)
            com.facebook.react.devsupport.WebsocketJavaScriptExecutor$1 r1 = new com.facebook.react.devsupport.WebsocketJavaScriptExecutor$1
            r1.<init>()
            r2.connectInternal(r3, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.connect(java.lang.String, com.facebook.react.devsupport.WebsocketJavaScriptExecutor$JSExecutorConnectCallback):void");
    }

    @Override // com.facebook.react.bridge.JavaJSExecutor
    @Nullable
    public String executeJSCall(String str, String str2) throws JavaJSExecutor.ProxyExecutorException {
        JSExecutorCallbackFuture jSExecutorCallbackFuture = new JSExecutorCallbackFuture();
        ((JSDebuggerWebSocketClient) Assertions.assertNotNull(this.mWebSocketClient)).executeJSCall(str, str2, jSExecutorCallbackFuture);
        try {
            return jSExecutorCallbackFuture.get();
        } catch (Throwable th) {
            throw new JavaJSExecutor.ProxyExecutorException(th);
        }
    }

    @Override // com.facebook.react.bridge.JavaJSExecutor
    public void loadApplicationScript(String str) throws JavaJSExecutor.ProxyExecutorException {
        JSExecutorCallbackFuture jSExecutorCallbackFuture = new JSExecutorCallbackFuture();
        ((JSDebuggerWebSocketClient) Assertions.assertNotNull(this.mWebSocketClient)).loadApplicationScript(str, this.mInjectedObjects, jSExecutorCallbackFuture);
        try {
            jSExecutorCallbackFuture.get();
        } catch (Throwable th) {
            throw new JavaJSExecutor.ProxyExecutorException(th);
        }
    }

    @Override // com.facebook.react.bridge.JavaJSExecutor
    public void setGlobalVariable(String str, String str2) {
        this.mInjectedObjects.put(str, str2);
    }
}
