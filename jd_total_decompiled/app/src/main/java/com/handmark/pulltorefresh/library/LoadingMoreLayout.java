package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;

/* loaded from: classes12.dex */
public class LoadingMoreLayout extends FrameLayout implements OnViewThemeConfig<LoadingMoreLayout> {
    private boolean isAutoDark;
    private boolean isDarkMode;
    private TextView loadMsg;
    private TextView mFootReachEndView;
    public TextView mFootRetryView;
    private View mFooterLoadingView;
    private FooterState mFooterState;
    private RetryListener mRetryListener;

    /* renamed from: com.handmark.pulltorefresh.library.LoadingMoreLayout$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$LoadingMoreLayout$FooterState;

        static {
            int[] iArr = new int[FooterState.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$LoadingMoreLayout$FooterState = iArr;
            try {
                iArr[FooterState.RESET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$LoadingMoreLayout$FooterState[FooterState.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$LoadingMoreLayout$FooterState[FooterState.LOADING_SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$LoadingMoreLayout$FooterState[FooterState.LOADING_FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$LoadingMoreLayout$FooterState[FooterState.REACH_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$LoadingMoreLayout$FooterState[FooterState.REACH_END_INVISIBLE.ordinal()] = 6;
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

    public LoadingMoreLayout(Context context) {
        this(context, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LoadingMoreLayout elderMode() {
        return null;
    }

    public FooterState getFooterState() {
        return this.mFooterState;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoDarkMode() {
        return this.isAutoDark;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoElderMode() {
        return false;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isDarkMode() {
        if (this.isAutoDark) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isElderMode() {
        return false;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LoadingMoreLayout setAutoElderMode(boolean z) {
        return null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LoadingMoreLayout setElderMode(boolean z) {
        return null;
    }

    public void setFootersState(FooterState footerState) {
        this.mFooterState = footerState;
        switch (AnonymousClass2.$SwitchMap$com$handmark$pulltorefresh$library$LoadingMoreLayout$FooterState[footerState.ordinal()]) {
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

    public void setOnRetryListener(RetryListener retryListener) {
        this.mRetryListener = retryListener;
    }

    public LoadingMoreLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.ptr_footer, (ViewGroup) this, true);
        this.mFooterLoadingView = findViewById(R.id.loading_layout);
        this.mFootRetryView = (TextView) findViewById(R.id.footer_retry_view);
        this.mFootReachEndView = (TextView) findViewById(R.id.footer_reach_end_view);
        this.loadMsg = (TextView) findViewById(R.id.loading_msg);
        this.mFootRetryView.setOnClickListener(new View.OnClickListener() { // from class: com.handmark.pulltorefresh.library.LoadingMoreLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LoadingMoreLayout.this.mRetryListener != null) {
                    LoadingMoreLayout.this.mRetryListener.onRetry();
                }
            }
        });
        this.mFooterState = FooterState.RESET;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LoadingMoreLayout darkMode() {
        TextView textView = this.loadMsg;
        Resources resources = getResources();
        int i2 = R.color.un_content_level_1_dark;
        textView.setTextColor(resources.getColor(i2));
        this.mFootReachEndView.setTextColor(getResources().getColor(i2));
        this.mFootRetryView.setTextColor(getResources().getColor(i2));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LoadingMoreLayout normalMode() {
        TextView textView = this.loadMsg;
        Resources resources = getResources();
        int i2 = R.color.un_content_level_1;
        textView.setTextColor(resources.getColor(i2));
        this.mFootReachEndView.setTextColor(getResources().getColor(i2));
        this.mFootRetryView.setTextColor(getResources().getColor(i2));
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LoadingMoreLayout setAutoDarkMode(boolean z) {
        this.isAutoDark = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public LoadingMoreLayout setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }
}
