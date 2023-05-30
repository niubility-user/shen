package retrofit2;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class OkHttpCall<T> implements Call<T> {
    private final Object[] args;
    private volatile boolean canceled;
    private Throwable creationFailure;
    private boolean executed;
    private okhttp3.Call rawCall;
    private final ServiceMethod<T> serviceMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ExceptionCatchingRequestBody extends ResponseBody {
        private final ResponseBody delegate;
        IOException thrownException;

        ExceptionCatchingRequestBody(ResponseBody responseBody) {
            this.delegate = responseBody;
        }

        @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.delegate.contentType();
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            return Okio.buffer(new ForwardingSource(this.delegate.source()) { // from class: retrofit2.OkHttpCall.ExceptionCatchingRequestBody.1
                @Override // okio.ForwardingSource, okio.Source
                public long read(Buffer buffer, long j2) throws IOException {
                    try {
                        return super.read(buffer, j2);
                    } catch (IOException e2) {
                        ExceptionCatchingRequestBody.this.thrownException = e2;
                        throw e2;
                    }
                }
            });
        }

        void throwIfCaught() throws IOException {
            IOException iOException = this.thrownException;
            if (iOException != null) {
                throw iOException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class NoContentResponseBody extends ResponseBody {
        private final long contentLength;
        private final MediaType contentType;

        NoContentResponseBody(MediaType mediaType, long j2) {
            this.contentType = mediaType;
            this.contentLength = j2;
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            return this.contentLength;
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp3.ResponseBody
        public BufferedSource source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OkHttpCall(ServiceMethod<T> serviceMethod, Object[] objArr) {
        this.serviceMethod = serviceMethod;
        this.args = objArr;
    }

    private okhttp3.Call createRawCall() throws IOException {
        okhttp3.Call newCall = this.serviceMethod.callFactory.newCall(this.serviceMethod.toRequest(this.args));
        if (newCall != null) {
            return newCall;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    @Override // retrofit2.Call
    public void cancel() {
        okhttp3.Call call;
        this.canceled = true;
        synchronized (this) {
            call = this.rawCall;
        }
        if (call != null) {
            call.cancel();
        }
    }

    @Override // retrofit2.Call
    public void enqueue(final Callback<T> callback) {
        okhttp3.Call call;
        Throwable th;
        if (callback != null) {
            synchronized (this) {
                if (!this.executed) {
                    this.executed = true;
                    call = this.rawCall;
                    th = this.creationFailure;
                    if (call == null && th == null) {
                        okhttp3.Call createRawCall = createRawCall();
                        this.rawCall = createRawCall;
                        call = createRawCall;
                    }
                } else {
                    throw new IllegalStateException("Already executed.");
                }
            }
            if (th != null) {
                callback.onFailure(this, th);
                return;
            }
            if (this.canceled) {
                call.cancel();
            }
            call.enqueue(new okhttp3.Callback() { // from class: retrofit2.OkHttpCall.1
                private void callFailure(Throwable th2) {
                    try {
                        callback.onFailure(OkHttpCall.this, th2);
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }

                private void callSuccess(Response<T> response) {
                    try {
                        callback.onResponse(OkHttpCall.this, response);
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }

                @Override // okhttp3.Callback
                public void onFailure(okhttp3.Call call2, IOException iOException) {
                    try {
                        callback.onFailure(OkHttpCall.this, iOException);
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }

                @Override // okhttp3.Callback
                public void onResponse(okhttp3.Call call2, okhttp3.Response response) throws IOException {
                    try {
                        callSuccess(OkHttpCall.this.parseResponse(response));
                    } catch (Throwable th2) {
                        callFailure(th2);
                    }
                }
            });
            return;
        }
        throw new NullPointerException("callback == null");
    }

    @Override // retrofit2.Call
    public Response<T> execute() throws IOException {
        okhttp3.Call call;
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Throwable th = this.creationFailure;
                if (th != null) {
                    if (th instanceof IOException) {
                        throw ((IOException) th);
                    }
                    throw ((RuntimeException) th);
                }
                call = this.rawCall;
                if (call == null) {
                    try {
                        call = createRawCall();
                        this.rawCall = call;
                    } catch (IOException | RuntimeException e2) {
                        this.creationFailure = e2;
                        throw e2;
                    }
                }
            } else {
                throw new IllegalStateException("Already executed.");
            }
        }
        if (this.canceled) {
            call.cancel();
        }
        return parseResponse(call.execute());
    }

    @Override // retrofit2.Call
    public boolean isCanceled() {
        return this.canceled;
    }

    @Override // retrofit2.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    Response<T> parseResponse(okhttp3.Response response) throws IOException {
        ResponseBody body = response.body();
        okhttp3.Response build = response.newBuilder().body(new NoContentResponseBody(body.contentType(), body.contentLength())).build();
        int code = build.code();
        if (code < 200 || code >= 300) {
            try {
                return Response.error(Utils.buffer(body), build);
            } finally {
                body.close();
            }
        } else if (code != 204 && code != 205) {
            ExceptionCatchingRequestBody exceptionCatchingRequestBody = new ExceptionCatchingRequestBody(body);
            try {
                return Response.success(this.serviceMethod.toResponse(exceptionCatchingRequestBody), build);
            } catch (RuntimeException e2) {
                exceptionCatchingRequestBody.throwIfCaught();
                throw e2;
            }
        } else {
            return Response.success((Object) null, build);
        }
    }

    @Override // retrofit2.Call
    public synchronized Request request() {
        okhttp3.Call call = this.rawCall;
        if (call != null) {
            return call.request();
        }
        Throwable th = this.creationFailure;
        if (th != null) {
            if (th instanceof IOException) {
                throw new RuntimeException("Unable to create request.", this.creationFailure);
            }
            throw ((RuntimeException) th);
        }
        try {
            okhttp3.Call createRawCall = createRawCall();
            this.rawCall = createRawCall;
            return createRawCall.request();
        } catch (IOException e2) {
            this.creationFailure = e2;
            throw new RuntimeException("Unable to create request.", e2);
        } catch (RuntimeException e3) {
            this.creationFailure = e3;
            throw e3;
        }
    }

    @Override // retrofit2.Call
    public OkHttpCall<T> clone() {
        return new OkHttpCall<>(this.serviceMethod, this.args);
    }
}
