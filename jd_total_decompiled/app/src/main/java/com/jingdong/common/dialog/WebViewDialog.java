package com.jingdong.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.platform.lib.utils.InflateUtil;
import com.jingdong.sdk.utils.DPIUtil;
import com.tencent.smtt.sdk.WebView;
import de.greenrobot.event.EventBus;

/* loaded from: classes5.dex */
public class WebViewDialog extends DialogFragment implements IDialogView {
    private TextView closeIcon;
    private FrameLayout container;
    private TextView dialogTitle;
    private DismissListener dismissListener;
    private boolean isHideTitle;
    private CommonMFragment mFragment;
    private String mTitle;
    private String url;
    private final String FRAGMENT_TAG = "TAG_Fragment";
    private BasePresenter presenter = new DialogPresenter(this);

    /* loaded from: classes5.dex */
    public interface DismissListener {
        void dismiss(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class WebDialogListener extends WebViewClientListenerImpl {
        public WebDialogListener(IWebUiBinder iWebUiBinder) {
            super(iWebUiBinder);
        }

        @Override // com.jingdong.common.web.uilistener.impl.WebViewClientListenerImpl, com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (EventBus.getDefault().isRegistered(WebViewDialog.this.presenter)) {
                return;
            }
            EventBus.getDefault().register(WebViewDialog.this.presenter);
        }
    }

    public WebViewDialog(String str, String str2) {
        this.url = str;
        this.mTitle = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(View view) {
        dismissDialog(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(DialogInterface dialogInterface) {
        dismissDialog(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean f(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && i2 == 4) {
            dismissDialog(null);
            return true;
        }
        return false;
    }

    private CommonMFragment getFragment() {
        Fragment findFragmentByTag = getChildFragmentManager().findFragmentByTag("TAG_Fragment");
        if (!(findFragmentByTag instanceof CommonMFragment)) {
            findFragmentByTag = new CartMFragment();
        }
        return (CommonMFragment) findFragmentByTag;
    }

    private void hideTitle() {
        this.closeIcon.setVisibility(8);
        this.dialogTitle.setVisibility(8);
        ((RelativeLayout.LayoutParams) this.container.getLayoutParams()).setMargins(0, 0, 0, 0);
    }

    private void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.dialog_close);
        this.closeIcon = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.dialog.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                WebViewDialog.this.b(view2);
            }
        });
        TextView textView2 = (TextView) view.findViewById(R.id.dialog_title);
        this.dialogTitle = textView2;
        textView2.setText(this.mTitle);
        int i2 = R.id.h5_container;
        this.container = (FrameLayout) view.findViewById(i2);
        if (this.isHideTitle) {
            hideTitle();
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", this.url);
        bundle.putBoolean("isTopBarGone", true);
        bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, false);
        CommonMFragment fragment = getFragment();
        this.mFragment = fragment;
        fragment.setArguments(bundle);
        if (this.mFragment.isAdded()) {
            return;
        }
        getChildFragmentManager().beginTransaction().replace(i2, this.mFragment, "TAG_Fragment").commit();
    }

    private void setListener() {
        this.mFragment.getJdWebView().setWebViewClientListener(new WebDialogListener(this.mFragment.getWebUiBinder()));
    }

    public boolean checkDeviceHasNavigationBar(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = false;
        boolean z2 = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod(IMantoServerRequester.GET, String.class).invoke(cls, "qemu.hw.mainkeys");
            String str = invoke instanceof String ? (String) invoke : "";
            if (!"1".equals(str)) {
                z = "0".equals(str) ? true : z2;
            }
            return z;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return z2;
        }
    }

    public void configureDialogAttr(float f2) {
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            attributes.height = f2 > 0.0f ? (int) (getVirtualBarHeight() * f2) : 0;
            attributes.windowAnimations = com.jingdong.common.R.style.dialog_annim_bottom_style;
            window.setAttributes(attributes);
        }
    }

    @Override // com.jingdong.common.dialog.IDialogView
    public void dismissDialog(String str) {
        DismissListener dismissListener = this.dismissListener;
        if (dismissListener != null) {
            dismissListener.dismiss(str);
        }
        dismiss();
    }

    public int getVirtualBarHeight() {
        int max = Math.max(DPIUtil.getHeight(JdSdk.getInstance().getApplicationContext()), DPIUtil.getWidth(JdSdk.getInstance().getApplicationContext()));
        try {
            return checkDeviceHasNavigationBar(getActivity()) ? max + UnStatusBarTintUtil.getNavigationBarHeight(getActivity()) : max;
        } catch (Exception unused) {
            return max;
        }
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void hideProgress() {
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public boolean isRetain() {
        return false;
    }

    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (getDialog() != null) {
            getDialog().requestWindowFeature(1);
        }
        getDialog().getWindow().getDecorView().setPadding(0, 0, 0, 0);
        View inflate = InflateUtil.inflate(JdSdk.getInstance().getApplicationContext(), R.layout.jd_webview_dialog_layout, null);
        initView(inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.presenter != null) {
            EventBus.getDefault().unregister(this.presenter);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setListener();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        configureDialogAttr(0.8f);
        if (getDialog() != null) {
            getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.common.dialog.a
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    WebViewDialog.this.d(dialogInterface);
                }
            });
        }
        if (getView() != null) {
            getView().setFocusable(true);
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener(new View.OnKeyListener() { // from class: com.jingdong.common.dialog.c
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
                    return WebViewDialog.this.f(view, i2, keyEvent);
                }
            });
        }
    }

    @Override // com.jingdong.common.dialog.IDialogView
    public void setDialogTitle(String str) {
        JDJSONObject parseObject = JDJSON.parseObject(str);
        if (parseObject == null) {
            return;
        }
        String optString = parseObject.optString("title");
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        this.mTitle = optString;
        this.dialogTitle.setText(optString);
    }

    public void setDismissListener(DismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public void setHideFlag(boolean z) {
        this.isHideTitle = z;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(@NonNull FragmentManager fragmentManager, @Nullable String str) {
        try {
            fragmentManager.executePendingTransactions();
            if (isShowing() || isAdded()) {
                return;
            }
            super.show(fragmentManager, str);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showProgress() {
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showToast(String str) {
    }
}
