package com.jingdong.common.jdpay;

import com.google.gson.Gson;
import com.jdpaysdk.freechargesdk.FreeRechargeUtil;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.corelib.utils.Log;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class JDPayFreeRecharge {
    private static final int CORE_NUM = 1;
    private static final int LONG_TIME = 10;
    private static final int MAX_NUM = 1;
    private static final String TAG = "JDPayFreeRecharge";
    private static final String TYPE = "59";
    private static final RejectStrategy mRejectStrategy;
    private static final ThreadPoolExecutor server;

    /* loaded from: classes5.dex */
    static class RejectStrategy implements RejectedExecutionHandler {
        RejectStrategy() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            Log.i(JDPayFreeRecharge.TAG, "thread is full, reject");
        }
    }

    static {
        RejectStrategy rejectStrategy = new RejectStrategy();
        mRejectStrategy = rejectStrategy;
        server = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS, new SynchronousQueue(), rejectStrategy);
    }

    public static void startFreeRecharge(final IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            server.execute(new Runnable() { // from class: com.jingdong.common.jdpay.JDPayFreeRecharge.1
                private synchronized void freeRecharge(IRouterParams iRouterParams2) {
                    FreeRechargeUtil freeRechargeUtil;
                    JDPayFreeRequest jDPayFreeRequest;
                    try {
                        freeRechargeUtil = FreeRechargeUtil.getInstance(iRouterParams2.getContext().getApplicationContext());
                        jDPayFreeRequest = (JDPayFreeRequest) new Gson().fromJson(iRouterParams2.getRouterParam(), JDPayFreeRequest.class);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (jDPayFreeRequest != null && jDPayFreeRequest.getData() != null) {
                        String entrance = freeRechargeUtil.entrance(jDPayFreeRequest.getData());
                        JDPayFreeResponse jDPayFreeResponse = new JDPayFreeResponse();
                        jDPayFreeResponse.setType(JDPayFreeRecharge.TYPE);
                        jDPayFreeResponse.setResult(entrance);
                        iRouterParams2.onCallBack(new Gson().toJson(jDPayFreeResponse));
                        return;
                    }
                    Log.i(JDPayFreeRecharge.TAG, "request is null");
                }

                @Override // java.lang.Runnable
                public void run() {
                    freeRecharge(IRouterParams.this);
                }
            });
        } catch (Throwable th) {
            Log.i(TAG, th.getLocalizedMessage());
        }
    }
}
