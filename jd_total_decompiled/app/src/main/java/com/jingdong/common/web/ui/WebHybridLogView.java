package com.jingdong.common.web.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.jingdong.common.web.R;
import com.jingdong.common.web.util.HybridBackdoorLogger;

/* loaded from: classes12.dex */
public class WebHybridLogView extends RelativeLayout implements HybridBackdoorLogger.BackdoorLogChangeListener {
    public static boolean showLog;
    public static boolean toastHit;
    private View mLogLayout;
    private ScrollView mMessageScroll;
    private TextView mMessageTv;
    private TextView mSummary;
    private Handler mainHandler;

    public WebHybridLogView(Context context) {
        this(context, null);
    }

    private void init() {
        View.inflate(getContext(), R.layout.web_hybrid_log_layout, this);
        this.mLogLayout = findViewById(R.id.hybrid_log_layout);
        findViewById(R.id.hybrid_log_btn).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.ui.WebHybridLogView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WebHybridLogView.this.mLogLayout.getVisibility() == 0) {
                    WebHybridLogView.this.mLogLayout.setVisibility(8);
                } else {
                    WebHybridLogView.this.mLogLayout.setVisibility(0);
                }
            }
        });
        TextView textView = (TextView) findViewById(R.id.hybrid_summary);
        this.mSummary = textView;
        textView.setText("Hybrid Log:");
        this.mMessageTv = (TextView) findViewById(R.id.hybrid_message_tv);
        this.mMessageScroll = (ScrollView) findViewById(R.id.hybrid_message_scroll);
    }

    private void setLog(final String str) {
        Runnable runnable = new Runnable() { // from class: com.jingdong.common.web.ui.WebHybridLogView.2
            @Override // java.lang.Runnable
            public void run() {
                WebHybridLogView.this.mMessageTv.setText(Html.fromHtml(str));
                if (WebHybridLogView.this.mainHandler != null) {
                    WebHybridLogView.this.mainHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.web.ui.WebHybridLogView.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (WebHybridLogView.this.mMessageScroll != null) {
                                WebHybridLogView.this.mMessageScroll.fullScroll(130);
                            }
                        }
                    }, 100L);
                }
            }
        };
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Handler handler = this.mainHandler;
            if (handler != null) {
                handler.post(runnable);
                return;
            }
            return;
        }
        runnable.run();
    }

    public void onDestroy() {
        HybridBackdoorLogger.newLogSection();
        HybridBackdoorLogger.setLogChangeListener(null);
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mainHandler = null;
    }

    @Override // com.jingdong.common.web.util.HybridBackdoorLogger.BackdoorLogChangeListener
    public void onNewLog(String str) {
        setLog(str);
    }

    public void onResume() {
        StringBuffer hybridLog = HybridBackdoorLogger.getHybridLog();
        setLog(hybridLog != null ? hybridLog.toString() : "");
        HybridBackdoorLogger.setLogChangeListener(this);
    }

    public WebHybridLogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebHybridLogView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mainHandler = new Handler(Looper.getMainLooper());
        init();
    }
}
