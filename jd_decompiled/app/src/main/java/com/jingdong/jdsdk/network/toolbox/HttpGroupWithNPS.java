package com.jingdong.jdsdk.network.toolbox;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class HttpGroupWithNPS {
    private static final long COMPLETE_WAIT_TIME = 10000;
    private static final String TAG = "HttpGroupWithNPS";
    private static final String mType = "2";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private HttpGroup mHttpGroup;
    private boolean mIsPause;
    private boolean mIsStop;
    private Runnable mLastRunnable;
    private boolean mNeedStop;
    private String mPageName;
    private String mPageParam;
    private String mQueryTime;

    /* loaded from: classes14.dex */
    private class CompleteRunnable implements Runnable {
        private String mEndTime;

        public CompleteRunnable(String str) {
            HttpGroupWithNPS.this = r1;
            this.mEndTime = str;
        }

        private String getCurNetworkType() {
            return NetUtils.isWifi() ? "WIFI" : "NON-WIFI";
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OKLog.D) {
                OKLog.d(HttpGroupWithNPS.TAG, " CompleteRunnable: , mPageName = " + HttpGroupWithNPS.this.mPageName + ", mPageParam = " + HttpGroupWithNPS.this.mPageParam + ", startTime = " + HttpGroupWithNPS.this.mQueryTime + ", endTime = " + this.mEndTime);
            }
            synchronized (this) {
                HttpGroupWithNPS.this.mLastRunnable = null;
                HttpGroupWithNPS.this.mIsStop = true;
            }
            JDHttpTookit.getEngine().getExceptionReportDelegate().sendPropertyData(JDHttpTookit.getEngine().getApplicationContext(), HttpGroupWithNPS.this.mQueryTime, HttpGroupWithNPS.this.mPageName, HttpGroupWithNPS.this.mPageParam, this.mEndTime);
        }
    }

    public HttpGroupWithNPS(Context context, HttpGroup httpGroup, String str, String str2, boolean z) {
        this.mPageName = str;
        this.mPageParam = str2;
        this.mNeedStop = z;
        this.mHttpGroup = httpGroup;
        initHttpGroup();
        this.mIsPause = false;
        this.mIsStop = false;
    }

    private void initHttpGroup() {
        HttpGroup httpGroup = this.mHttpGroup;
        if (httpGroup == null) {
            return;
        }
        httpGroup.setOnGroupStartListener(new HttpGroup.OnGroupStartListener() { // from class: com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS.1
            {
                HttpGroupWithNPS.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnGroupStartListener
            public void onStart() {
                if (HttpGroupWithNPS.this.mQueryTime != null && OKLog.E) {
                    OKLog.e(HttpGroupWithNPS.TAG, "Error: there has old querytime. pullRefreshHttpGroup -->> onStart: mPageName = " + HttpGroupWithNPS.this.mPageName + ", mPageParam = " + HttpGroupWithNPS.this.mPageParam + ", old querytime = " + HttpGroupWithNPS.this.mQueryTime + ", new querytime = " + NetUtils.getCurrentMicrosecond());
                }
                HttpGroupWithNPS.this.mQueryTime = NetUtils.getCurrentMicrosecond();
                if (OKLog.D) {
                    OKLog.d(HttpGroupWithNPS.TAG, " pullRefreshHttpGroup -->> onStart: , mPageName = " + HttpGroupWithNPS.this.mPageName + ", mPageParam = " + HttpGroupWithNPS.this.mPageParam + ", mQueryTime = " + HttpGroupWithNPS.this.mQueryTime);
                }
            }
        });
        this.mHttpGroup.setOnGroupFirstRequestCompleteListener(new HttpGroup.OnGroupFirstRequestCompleteListener() { // from class: com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS.2
            {
                HttpGroupWithNPS.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnGroupFirstRequestCompleteListener
            public void onFirstLoadComplete() {
                synchronized (HttpGroupWithNPS.this) {
                    if (HttpGroupWithNPS.this.mNeedStop && HttpGroupWithNPS.this.mIsStop) {
                        return;
                    }
                    if (HttpGroupWithNPS.this.mIsPause) {
                        return;
                    }
                    String currentMicrosecond = NetUtils.getCurrentMicrosecond();
                    if (OKLog.D) {
                        OKLog.d(HttpGroupWithNPS.TAG, " pullRefreshHttpGroup -->> onComplete: , mPageName = " + HttpGroupWithNPS.this.mPageName + ", mPageParam = " + HttpGroupWithNPS.this.mPageParam + ", endTime = " + currentMicrosecond);
                    }
                    if (HttpGroupWithNPS.this.mQueryTime == null) {
                        if (OKLog.E) {
                            OKLog.e(HttpGroupWithNPS.TAG, "Error: there has no query time. can't send log.");
                            return;
                        }
                        return;
                    }
                    synchronized (HttpGroupWithNPS.this) {
                        if (HttpGroupWithNPS.this.mHandler != null) {
                            if (HttpGroupWithNPS.this.mLastRunnable != null) {
                                HttpGroupWithNPS.this.mHandler.removeCallbacks(HttpGroupWithNPS.this.mLastRunnable);
                            }
                            HttpGroupWithNPS httpGroupWithNPS = HttpGroupWithNPS.this;
                            httpGroupWithNPS.mLastRunnable = new CompleteRunnable(currentMicrosecond);
                            HttpGroupWithNPS.this.mHandler.postDelayed(HttpGroupWithNPS.this.mLastRunnable, 10000L);
                        }
                    }
                }
            }
        });
    }

    public void destory() {
        if (OKLog.D) {
            OKLog.d(TAG, "destory() ");
        }
        synchronized (this) {
            Handler handler = this.mHandler;
            if (handler != null) {
                Runnable runnable = this.mLastRunnable;
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                    this.mLastRunnable = null;
                }
                this.mHandler = null;
            }
        }
    }

    public HttpGroup getHttpGroup() {
        return this.mHttpGroup;
    }

    public void onPause() {
        if (this.mIsPause) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, " canceled current httpgroupNPS. mPageName = " + this.mPageName + ", mPageParam = " + this.mPageParam);
        }
        synchronized (this) {
            this.mIsPause = true;
            Runnable runnable = this.mLastRunnable;
            if (runnable != null) {
                this.mHandler.removeCallbacks(runnable);
            }
        }
    }

    public void onResume() {
        synchronized (this) {
            this.mIsPause = false;
        }
    }
}
