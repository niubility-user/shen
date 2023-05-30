package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import okhttp3.Request;
import retrofit2.CallAdapter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class ExecutorCallAdapterFactory extends CallAdapter.Factory {
    final Executor callbackExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ExecutorCallbackCall<T> implements Call<T> {
        final Executor callbackExecutor;
        final Call<T> delegate;

        ExecutorCallbackCall(Executor executor, Call<T> call) {
            this.callbackExecutor = executor;
            this.delegate = call;
        }

        @Override // retrofit2.Call
        public void cancel() {
            this.delegate.cancel();
        }

        @Override // retrofit2.Call
        public void enqueue(final Callback<T> callback) {
            if (callback != null) {
                this.delegate.enqueue(new Callback<T>() { // from class: retrofit2.ExecutorCallAdapterFactory.ExecutorCallbackCall.1
                    @Override // retrofit2.Callback
                    public void onFailure(Call<T> call, final Throwable th) {
                        ExecutorCallbackCall.this.callbackExecutor.execute(new Runnable() { // from class: retrofit2.ExecutorCallAdapterFactory.ExecutorCallbackCall.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                callback.onFailure(ExecutorCallbackCall.this, th);
                            }
                        });
                    }

                    @Override // retrofit2.Callback
                    public void onResponse(Call<T> call, final Response<T> response) {
                        ExecutorCallbackCall.this.callbackExecutor.execute(new Runnable() { // from class: retrofit2.ExecutorCallAdapterFactory.ExecutorCallbackCall.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (ExecutorCallbackCall.this.delegate.isCanceled()) {
                                    AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                    callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                                    return;
                                }
                                AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                                callback.onResponse(ExecutorCallbackCall.this, response);
                            }
                        });
                    }
                });
                return;
            }
            throw new NullPointerException("callback == null");
        }

        @Override // retrofit2.Call
        public Response<T> execute() throws IOException {
            return this.delegate.execute();
        }

        @Override // retrofit2.Call
        public boolean isCanceled() {
            return this.delegate.isCanceled();
        }

        @Override // retrofit2.Call
        public boolean isExecuted() {
            return this.delegate.isExecuted();
        }

        @Override // retrofit2.Call
        public Request request() {
            return this.delegate.request();
        }

        @Override // retrofit2.Call
        public Call<T> clone() {
            return new ExecutorCallbackCall(this.callbackExecutor, this.delegate.clone());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExecutorCallAdapterFactory(Executor executor) {
        this.callbackExecutor = executor;
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<Call<?>> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (CallAdapter.Factory.getRawType(type) != Call.class) {
            return null;
        }
        Utils.getCallResponseType(type);
        return new CallAdapter<Call<?>>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: RETURN 
              (wrap: retrofit2.CallAdapter<retrofit2.Call<?>> : 0x0010: CONSTRUCTOR 
              (r0v0 'this' retrofit2.ExecutorCallAdapterFactory A[IMMUTABLE_TYPE, THIS])
              (r1 I:java.lang.reflect.Type A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(retrofit2.ExecutorCallAdapterFactory, java.lang.reflect.Type):void (m), WRAPPED] (LINE:3) call: retrofit2.ExecutorCallAdapterFactory.1.<init>(retrofit2.ExecutorCallAdapterFactory, java.lang.reflect.Type):void type: CONSTRUCTOR)
             (LINE:3) in method: retrofit2.ExecutorCallAdapterFactory.get(java.lang.reflect.Type, java.lang.annotation.Annotation[], retrofit2.Retrofit):retrofit2.CallAdapter<retrofit2.Call<?>>, file: classes11.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
            java.lang.Class r2 = retrofit2.CallAdapter.Factory.getRawType(r1)
            java.lang.Class<retrofit2.Call> r3 = retrofit2.Call.class
            if (r2 == r3) goto La
            r1 = 0
            return r1
        La:
            java.lang.reflect.Type r1 = retrofit2.Utils.getCallResponseType(r1)
            retrofit2.ExecutorCallAdapterFactory$1 r2 = new retrofit2.ExecutorCallAdapterFactory$1
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.ExecutorCallAdapterFactory.get(java.lang.reflect.Type, java.lang.annotation.Annotation[], retrofit2.Retrofit):retrofit2.CallAdapter");
    }
}
