package com.jingdong.common.jdreactFramework.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.widgets.JDReactProgressBar;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uilistener.WebViewClientListener;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes5.dex */
public class RNMFragment extends CommonMFragment {
    private int mProgressBarSize;
    LinearLayout mRetryView;
    private RelativeLayout modal;
    private View progressBar;
    private TextView progressTextView;
    private FrameLayout rootFrameLayout;

    private ViewGroup getModal() {
        RelativeLayout relativeLayout = this.modal;
        if (relativeLayout != null) {
            return relativeLayout;
        }
        if (getContext() == null) {
            return null;
        }
        RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
        this.modal = relativeLayout2;
        relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.jdreactFramework.fragment.RNMFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return this.modal;
    }

    private void newProgressBar() {
        RelativeLayout relativeLayout;
        RelativeLayout relativeLayout2 = this.modal;
        if (relativeLayout2 != null) {
            relativeLayout2.removeAllViews();
        }
        View view = this.progressBar;
        if (view != null && view != null) {
            try {
                JDReactHelper.newInstance().recycleLoadingLottieView(this.progressBar);
                this.progressBar = null;
            } catch (Exception unused) {
            }
        }
        try {
            int i2 = this.mProgressBarSize;
            if (i2 <= 0) {
                i2 = (int) getResources().getDimension(R.dimen.l1);
            }
            this.progressBar = getLottieLoadingView();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, i2);
            layoutParams.addRule(13);
            this.modal.addView(this.progressBar, layoutParams);
        } catch (Throwable unused2) {
            TextView textView = this.progressTextView;
            if (textView == null) {
                TextView loadingTextView = getLoadingTextView();
                this.progressTextView = loadingTextView;
                if (loadingTextView != null) {
                    loadingTextView.setText(R.string.fi);
                }
            } else {
                this.modal.removeView(textView);
            }
            TextView textView2 = this.progressTextView;
            if (textView2 == null || (relativeLayout = this.modal) == null) {
                return;
            }
            relativeLayout.addView(textView2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProgressBarNow() {
        ViewGroup modal = getModal();
        if (modal == null || this.rootFrameLayout == null || getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        newProgressBar();
        if (modal.getParent() == null) {
            this.rootFrameLayout.addView(modal, new ViewGroup.LayoutParams(-1, -1));
        }
        this.rootFrameLayout.invalidate();
    }

    public void dismissProgressBarNow() {
        ViewGroup modal = getModal();
        if (modal == null || this.rootFrameLayout == null || getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        this.rootFrameLayout.removeView(modal);
        this.rootFrameLayout.invalidate();
    }

    public TextView getLoadingTextView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        if (getContext() == null) {
            return null;
        }
        TextView textView = new TextView(getContext());
        textView.setPadding(10, 10, 10, 10);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        textView.setBackgroundColor(getResources().getColor(R.color.o_));
        return textView;
    }

    public View getLottieLoadingView() {
        View view;
        try {
            view = JDReactHelper.newInstance().getLoadingLottieView();
        } catch (Exception unused) {
            view = null;
        }
        return view != null ? view : new JDReactProgressBar(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.web.ui.CommonMFragment
    public void initJdWebView() {
        super.initJdWebView();
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.setStatusBarAlwaysTransparent(true);
            this.mJdWebView.setWebViewClientListener(new WebViewClientListener() { // from class: com.jingdong.common.jdreactFramework.fragment.RNMFragment.1
                @Override // com.jingdong.common.web.uilistener.WebViewClientListener
                public void onPageFinished(WebView webView, String str) {
                    RNMFragment.this.dismissProgressBarNow();
                }

                @Override // com.jingdong.common.web.uilistener.WebViewClientListener
                public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                }

                @Override // com.jingdong.common.web.uilistener.WebViewClientListener
                public void onReceivedError(WebView webView, int i2, String str, String str2) {
                    RNMFragment.this.dismissProgressBarNow();
                    if (((CommonMFragment) RNMFragment.this).mJdWebView != null) {
                        RNMFragment.this.showRetryView();
                    }
                }

                @Override // com.jingdong.common.web.uilistener.WebViewClientListener
                public void shouldOverrideUrlLoading(WebView webView, String str) {
                }
            });
        }
    }

    @Override // com.jingdong.common.web.ui.CommonMFragment, com.jingdong.common.unification.navigationbar.JDTabFragment, com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        this.rootFrameLayout = new FrameLayout(getContext());
        this.rootFrameLayout.addView(super.onCreateViews(layoutInflater, bundle), new FrameLayout.LayoutParams(-1, -1));
        return this.rootFrameLayout;
    }

    public void removeRetryView() {
        LinearLayout linearLayout;
        if (this.rootFrameLayout == null || (linearLayout = this.mRetryView) == null || linearLayout.getParent() == null) {
            return;
        }
        this.rootFrameLayout.removeView(this.mRetryView);
    }

    public void showRetryView() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        if (this.mRetryView == null) {
            this.mRetryView = new LinearLayout(getContext());
            JDReactHelper.newInstance().showErrorRetryView(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.fragment.RNMFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!NetUtils.isNetworkAvailable()) {
                        ToastUtils.showToast(RNMFragment.this.getContext(), "\u68c0\u67e5\u4e0b\u7f51\u7edc\u518d\u6765\u5427");
                        return;
                    }
                    RNMFragment.this.removeRetryView();
                    RNMFragment.this.showProgressBarNow();
                    RNMFragment.this.loadWeb();
                }
            }, this.mRetryView);
        }
        if (this.rootFrameLayout != null && this.mRetryView.getParent() == null) {
            this.mRetryView.setVisibility(0);
            this.rootFrameLayout.addView(this.mRetryView, new ViewGroup.LayoutParams(-1, -1));
            this.rootFrameLayout.invalidate();
        }
    }
}
