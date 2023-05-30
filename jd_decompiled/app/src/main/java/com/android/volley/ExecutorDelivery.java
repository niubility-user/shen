package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.error.VolleyError;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class ExecutorDelivery implements ResponseDelivery {
    private final Executor mResponsePoster;
    private final Executor mResponsePosterMainThread = new Executor() { // from class: com.android.volley.ExecutorDelivery.1
        final Handler uiHandler = new Handler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.uiHandler.post(runnable);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ResponseDeliveryRunnable implements Runnable {
        private final Request mRequest;
        private final Response mResponse;
        private final Runnable mRunnable;

        public ResponseDeliveryRunnable(Request request, Response response, Runnable runnable) {
            this.mRequest = request;
            this.mResponse = response;
            this.mRunnable = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.deliverCancel();
                this.mRequest.finish("canceled-at-delivery");
                return;
            }
            if (this.mResponse.isSuccess()) {
                this.mRequest.addMarker("onEnd -->" + this.mResponse.result);
                this.mRequest.deliverResponse(this.mResponse);
            } else {
                this.mRequest.addMarker("onError -->" + this.mResponse.error.getMessage());
                this.mRequest.deliverError(this.mResponse.error);
            }
            if (this.mResponse.intermediate) {
                this.mRequest.addMarker("intermediate-response");
            } else {
                this.mRequest.finish("done");
            }
            Runnable runnable = this.mRunnable;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public ExecutorDelivery(final Handler handler) {
        this.mResponsePoster = new Executor() { // from class: com.android.volley.ExecutorDelivery.2
            @Override // java.util.concurrent.Executor
            public void execute(final Runnable runnable) {
                if (RuntimeConfigHelper.multiCallbackEnable()) {
                    handler.post(new Runnable() { // from class: com.android.volley.ExecutorDelivery.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GlobalExecutorService.deliveryExecutorService().execute(runnable);
                        }
                    });
                } else {
                    handler.post(runnable);
                }
            }
        };
    }

    @Override // com.android.volley.ResponseDelivery
    public void postCancel(final Request<?> request) {
        request.addMarker("post-cancel");
        Runnable runnable = new Runnable() { // from class: com.android.volley.ExecutorDelivery.4
            @Override // java.lang.Runnable
            public void run() {
                request.deliverCancel();
            }
        };
        if (request.isCallbackInMainThread()) {
            this.mResponsePosterMainThread.execute(runnable);
        } else {
            this.mResponsePoster.execute(runnable);
        }
    }

    @Override // com.android.volley.ResponseDelivery
    public void postError(Request<?> request, VolleyError volleyError) {
        request.addMarker("post-error");
        Response error = Response.error(volleyError);
        if (request.isCallbackInMainThread()) {
            this.mResponsePosterMainThread.execute(new ResponseDeliveryRunnable(request, error, null));
        } else {
            this.mResponsePoster.execute(new ResponseDeliveryRunnable(request, error, null));
        }
    }

    @Override // com.android.volley.ResponseDelivery
    public void postOtherError(final Request<?> request, final VolleyError volleyError) {
        Runnable runnable = new Runnable() { // from class: com.android.volley.ExecutorDelivery.5
            @Override // java.lang.Runnable
            public void run() {
                request.deliverError(volleyError);
            }
        };
        if (request.isCallbackInMainThread()) {
            this.mResponsePosterMainThread.execute(runnable);
        } else {
            this.mResponsePoster.execute(runnable);
        }
    }

    @Override // com.android.volley.ResponseDelivery
    public void postResponse(Request<?> request, Response<?> response) {
        postResponse(request, response, null);
    }

    @Override // com.android.volley.ResponseDelivery
    public void postStart(final Request<?> request) {
        request.addMarker("post-start");
        Runnable runnable = new Runnable() { // from class: com.android.volley.ExecutorDelivery.3
            @Override // java.lang.Runnable
            public void run() {
                request.deliverStart();
            }
        };
        if (request.isCallbackInMainThread()) {
            this.mResponsePosterMainThread.execute(runnable);
        } else {
            this.mResponsePoster.execute(runnable);
        }
    }

    @Override // com.android.volley.ResponseDelivery
    public void postResponse(Request<?> request, Response<?> response, Runnable runnable) {
        request.markDelivered();
        request.addMarker("post-response");
        if (request.isCallbackInMainThread()) {
            this.mResponsePosterMainThread.execute(new ResponseDeliveryRunnable(request, response, runnable));
        } else {
            this.mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, runnable));
        }
    }

    public ExecutorDelivery(Executor executor) {
        this.mResponsePoster = executor;
    }
}
