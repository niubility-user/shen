package com.jd.framework.network.filedown;

import android.content.Context;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpStackFactory;
import com.jd.framework.network.filedown.internal.BaseDownloader;
import com.jd.framework.network.request.JDFileRequest;
import com.jd.framework.network.request.JDFileRequestQueue;
import com.jingdong.jdsdk.network.toolbox.GlobalExecutorService;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class JDFileDownloader {
    public static final String CHARSET = "UTF-8";
    private static final int CONSUMER_COUNT = 3;
    public static final int MAX_REDIRECT_COUNT = 5;
    private static JDFileDownloader instance;
    private Context mContext;
    public AtomicInteger totalCount = new AtomicInteger(0);
    private JDFileRequestQueue mRequestQueue = new JDFileRequestQueue();
    private HttpStackFactory mHttpStackFactory = new HttpStackFactory();
    private JDFileRequestConsumer[] mRequestConsumers = new JDFileRequestConsumer[3];

    private JDFileDownloader(Context context) {
        this.mContext = context;
        start();
    }

    public static JDFileDownloader getInstance(Context context) {
        if (instance == null) {
            synchronized (JDFileDownloader.class) {
                if (instance == null) {
                    instance = new JDFileDownloader(context);
                }
            }
        }
        return instance;
    }

    private void start() {
        stop();
        for (int i2 = 0; i2 < 3; i2++) {
            JDFileRequestConsumer jDFileRequestConsumer = new JDFileRequestConsumer(this.mRequestQueue, this.mHttpStackFactory, this.mContext);
            JDFileRequestConsumer[] jDFileRequestConsumerArr = this.mRequestConsumers;
            jDFileRequestConsumerArr[i2] = jDFileRequestConsumer;
            jDFileRequestConsumerArr[i2].setName("JDFileDownloader-Consumer-" + i2);
            jDFileRequestConsumer.start();
        }
    }

    private void stop() {
        for (int i2 = 0; i2 < 3; i2++) {
            JDFileRequestConsumer[] jDFileRequestConsumerArr = this.mRequestConsumers;
            if (jDFileRequestConsumerArr[i2] != null) {
                jDFileRequestConsumerArr[i2].quit();
            }
        }
    }

    public void add(JDFileRequest jDFileRequest) {
        if (VolleyLog.DEBUG) {
            OKLog.i("JDFileDownloader", "==== total file request count ===> " + this.totalCount.incrementAndGet());
        }
        this.mRequestQueue.add(jDFileRequest);
    }

    public void execute(final JDFileRequest jDFileRequest) {
        GlobalExecutorService.downloadThreadPool().execute(new Runnable() { // from class: com.jd.framework.network.filedown.JDFileDownloader.1
            {
                JDFileDownloader.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (VolleyLog.DEBUG) {
                    OKLog.d("JDFileDownloader", "execute file request -> " + jDFileRequest.getUrl());
                }
                BaseDownloader.executeAction(JDFileDownloader.this.mContext, jDFileRequest, JDFileDownloader.this.mHttpStackFactory);
            }
        });
    }
}
