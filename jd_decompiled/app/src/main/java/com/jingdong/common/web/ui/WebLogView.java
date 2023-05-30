package com.jingdong.common.web.ui;

import android.content.Context;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.common.web.R;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;

/* loaded from: classes12.dex */
public class WebLogView extends RelativeLayout implements View.OnClickListener {
    public static boolean showLog;
    private int index;
    private StringBuffer mAllSb;
    private TextView mCurrentTv;
    private StringBuffer mDebugSb;
    private StringBuffer mErrSb;
    private Button mLogBtn;
    private View mLogLayout;
    private StringBuffer mLogSb;
    private TextView mMessageTv;
    private StringBuffer mTipSb;
    private StringBuffer mWarnSb;

    /* renamed from: com.jingdong.common.web.ui.WebLogView$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel;

        static {
            int[] iArr = new int[ConsoleMessage.MessageLevel.values().length];
            $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel = iArr;
            try {
                iArr[ConsoleMessage.MessageLevel.LOG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.TIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WebLogView(Context context) {
        this(context, null);
    }

    private void init() {
        View.inflate(getContext(), R.layout.web_log_layout, this);
        this.mLogLayout = findViewById(R.id.logLayout);
        this.mLogBtn = (Button) findViewById(R.id.logBtn);
        int i2 = R.id.allTv;
        findViewById(i2).setOnClickListener(this);
        findViewById(R.id.tipTv).setOnClickListener(this);
        findViewById(R.id.logTv).setOnClickListener(this);
        findViewById(R.id.warnTv).setOnClickListener(this);
        findViewById(R.id.errTv).setOnClickListener(this);
        findViewById(R.id.debugTv).setOnClickListener(this);
        TextView textView = (TextView) findViewById(i2);
        this.mCurrentTv = textView;
        textView.setBackgroundColor(-7829368);
        this.mLogBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.ui.WebLogView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WebLogView.this.mLogLayout.setVisibility(WebLogView.this.mLogLayout.getVisibility() == 0 ? 8 : 0);
            }
        });
        this.mAllSb = new StringBuffer();
        this.mTipSb = new StringBuffer();
        this.mLogSb = new StringBuffer();
        this.mWarnSb = new StringBuffer();
        this.mDebugSb = new StringBuffer();
        this.mErrSb = new StringBuffer();
        TextView textView2 = (TextView) findViewById(R.id.messageTv);
        this.mMessageTv = textView2;
        textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void refreshMessage() {
        int i2 = this.index;
        if (i2 == 0) {
            this.mMessageTv.setText(Html.fromHtml(this.mAllSb.toString()));
        } else if (i2 == 1) {
            this.mMessageTv.setText(Html.fromHtml(this.mTipSb.toString()));
        } else if (i2 == 2) {
            this.mMessageTv.setText(Html.fromHtml(this.mLogSb.toString()));
        } else if (i2 == 3) {
            this.mMessageTv.setText(Html.fromHtml(this.mWarnSb.toString()));
        } else if (i2 == 4) {
            this.mMessageTv.setText(Html.fromHtml(this.mDebugSb.toString()));
        } else if (i2 != 5) {
        } else {
            this.mMessageTv.setText(Html.fromHtml(this.mErrSb.toString()));
        }
    }

    public void console(ConsoleMessage consoleMessage) {
        if (consoleMessage == null) {
            return;
        }
        if (this.mAllSb.length() >= 3000) {
            StringBuffer stringBuffer = this.mAllSb;
            stringBuffer.delete(0, stringBuffer.indexOf("<br>"));
        }
        if (this.mLogSb.length() >= 1000) {
            StringBuffer stringBuffer2 = this.mLogSb;
            stringBuffer2.delete(0, stringBuffer2.indexOf("<br>"));
        }
        if (this.mTipSb.length() >= 1000) {
            StringBuffer stringBuffer3 = this.mTipSb;
            stringBuffer3.delete(0, stringBuffer3.indexOf("<br>"));
        }
        if (this.mDebugSb.length() >= 1000) {
            StringBuffer stringBuffer4 = this.mDebugSb;
            stringBuffer4.delete(0, stringBuffer4.indexOf("<br>"));
        }
        if (this.mWarnSb.length() >= 1000) {
            StringBuffer stringBuffer5 = this.mWarnSb;
            stringBuffer5.delete(0, stringBuffer5.indexOf("<br>"));
        }
        if (this.mErrSb.length() >= 1000) {
            StringBuffer stringBuffer6 = this.mErrSb;
            stringBuffer6.delete(0, stringBuffer6.indexOf("<br>"));
        }
        int i2 = AnonymousClass2.$SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[consoleMessage.messageLevel().ordinal()];
        if (i2 == 1) {
            this.mAllSb.append("LOG:");
            this.mAllSb.append("<font color=\"#222222\">");
            this.mLogSb.append("<font color=\"#222222\">");
            this.mLogSb.append(consoleMessage.message());
            this.mLogSb.append("</font><br>----------------------------------<br>");
        } else if (i2 == 2) {
            this.mAllSb.append("TIP:");
            this.mAllSb.append("<font color=\"#222222\">");
            this.mTipSb.append("<font color=\"#222222\">");
            this.mTipSb.append(consoleMessage.message());
            this.mTipSb.append("</font><br>----------------------------------<br>");
        } else if (i2 == 3) {
            this.mAllSb.append("DEBUG:");
            this.mAllSb.append("<font color=\"#22dd22\">");
            this.mDebugSb.append("<font color=\"#22dd22\">");
            this.mDebugSb.append(consoleMessage.message());
            this.mDebugSb.append("</font><br>----------------------------------<br>");
        } else if (i2 == 4) {
            this.mAllSb.append("WARNING:");
            this.mAllSb.append("<font color=\"#dd2222\">");
            this.mWarnSb.append("<font color=\"#dd2222\">");
            this.mWarnSb.append(consoleMessage.message());
            this.mWarnSb.append("</font><br>----------------------------------<br>");
        } else if (i2 == 5) {
            this.mAllSb.append("ERROR:");
            this.mAllSb.append("<font color=\"#dd2222\">");
            this.mErrSb.append("<font color=\"#dd2222\">");
            this.mErrSb.append(consoleMessage.message());
            this.mErrSb.append("</font><br>----------------------------------<br>");
        }
        this.mAllSb.append(consoleMessage.message());
        this.mAllSb.append("</font><br>----------------------------------<br>");
        refreshMessage();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.allTv) {
            this.index = 0;
        } else if (view.getId() == R.id.tipTv) {
            this.index = 1;
        } else if (view.getId() == R.id.logTv) {
            this.index = 2;
        } else if (view.getId() == R.id.warnTv) {
            this.index = 3;
        } else if (view.getId() == R.id.debugTv) {
            this.index = 4;
        } else if (view.getId() == R.id.errTv) {
            this.index = 5;
        }
        this.mCurrentTv.setBackgroundColor(0);
        TextView textView = (TextView) view;
        this.mCurrentTv = textView;
        textView.setBackgroundColor(-7829368);
        refreshMessage();
    }

    public WebLogView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebLogView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }
}
