package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.UnWidgetThemeController;

/* loaded from: classes12.dex */
public class JDLoadingMoreLayout extends FrameLayout {
    private boolean autoDarkMode;
    private TextView endMsg;
    private boolean isDarkMode;
    private TextView loadMsg;
    protected View mFootReachEndView;
    protected View mFootRetryView;
    protected View mFooterLoadingView;
    protected FooterState mFooterState;
    protected RetryListener mRetryListener;
    private TextView retryMsg;

    /* renamed from: com.handmark.pulltorefresh.library.JDLoadingMoreLayout$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$JDLoadingMoreLayout$FooterState;

        static {
            int[] iArr = new int[FooterState.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$JDLoadingMoreLayout$FooterState = iArr;
            try {
                iArr[FooterState.RESET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$JDLoadingMoreLayout$FooterState[FooterState.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$JDLoadingMoreLayout$FooterState[FooterState.LOADING_SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$JDLoadingMoreLayout$FooterState[FooterState.LOADING_FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$JDLoadingMoreLayout$FooterState[FooterState.REACH_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$JDLoadingMoreLayout$FooterState[FooterState.REACH_END_INVISIBLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum FooterState {
        RESET,
        LOADING,
        LOADING_SUCCESS,
        LOADING_FAILED,
        REACH_END,
        REACH_END_INVISIBLE
    }

    /* loaded from: classes12.dex */
    public interface RetryListener {
        void onRetry();
    }

    public JDLoadingMoreLayout(Context context) {
        super(context);
        getLoadingLayout();
    }

    public void dark() {
        int color = getResources().getColor(R.color.un_content_level_1_dark);
        this.loadMsg.setTextColor(color);
        this.retryMsg.setTextColor(color);
        this.endMsg.setTextColor(color);
    }

    public FooterState getFooterState() {
        return this.mFooterState;
    }

    public void getLoadingLayout() {
        LayoutInflater.from(getContext()).inflate(R.layout.ptr_footer, (ViewGroup) this, true);
        this.mFooterLoadingView = findViewById(R.id.loading_layout);
        int i2 = R.id.footer_retry_view;
        this.mFootRetryView = findViewById(i2);
        int i3 = R.id.footer_reach_end_view;
        this.mFootReachEndView = findViewById(i3);
        this.loadMsg = (TextView) findViewById(R.id.loading_msg);
        this.retryMsg = (TextView) findViewById(i2);
        this.endMsg = (TextView) findViewById(i3);
        this.mFootRetryView.setOnClickListener(new View.OnClickListener() { // from class: com.handmark.pulltorefresh.library.JDLoadingMoreLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RetryListener retryListener = JDLoadingMoreLayout.this.mRetryListener;
                if (retryListener != null) {
                    retryListener.onRetry();
                }
            }
        });
        this.mFooterState = FooterState.RESET;
    }

    public RetryListener getRetryListener() {
        return this.mRetryListener;
    }

    public boolean isDarkMode() {
        if (this.autoDarkMode) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    public void normal() {
        int color = getResources().getColor(R.color.un_content_level_1);
        this.loadMsg.setTextColor(color);
        this.retryMsg.setTextColor(color);
        this.endMsg.setTextColor(color);
    }

    public void refreshTheme() {
        if (isDarkMode()) {
            dark();
        } else {
            normal();
        }
    }

    public void setAutoDarkMode(boolean z) {
        this.autoDarkMode = z;
    }

    public void setDarkMode(boolean z) {
        this.isDarkMode = z;
    }

    public void setEndMsg(String str) {
        TextView textView;
        if (str == null || (textView = this.endMsg) == null) {
            return;
        }
        textView.setText(str);
    }

    public void setFootersState(FooterState footerState) {
        this.mFooterState = footerState;
        switch (AnonymousClass2.$SwitchMap$com$handmark$pulltorefresh$library$JDLoadingMoreLayout$FooterState[footerState.ordinal()]) {
            case 1:
            case 2:
            case 3:
                this.mFooterLoadingView.setVisibility(0);
                this.mFootRetryView.setVisibility(8);
                this.mFootReachEndView.setVisibility(8);
                return;
            case 4:
                this.mFooterLoadingView.setVisibility(8);
                this.mFootRetryView.setVisibility(0);
                this.mFootReachEndView.setVisibility(8);
                return;
            case 5:
                this.mFooterLoadingView.setVisibility(8);
                this.mFootRetryView.setVisibility(8);
                this.mFootReachEndView.setVisibility(0);
                return;
            case 6:
                this.mFooterLoadingView.setVisibility(8);
                this.mFootRetryView.setVisibility(8);
                this.mFootReachEndView.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void setLoadMsg(String str) {
        TextView textView;
        if (str == null || (textView = this.loadMsg) == null) {
            return;
        }
        textView.setText(str);
    }

    public void setOnRetryListener(RetryListener retryListener) {
        this.mRetryListener = retryListener;
    }

    public void setRetryMsg(String str) {
        TextView textView;
        if (str == null || (textView = this.retryMsg) == null) {
            return;
        }
        textView.setText(str);
    }

    public JDLoadingMoreLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getLoadingLayout();
    }
}
