package com.jingdong.app.mall.im.business;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.app.mall.R;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewHelper;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public class RedPacketRainDialog extends DialogFragment {

    /* renamed from: k  reason: collision with root package name */
    private static final String f11113k = RedPacketRainDialog.class.getSimpleName();

    /* renamed from: l  reason: collision with root package name */
    private static Activity f11114l;

    /* renamed from: m  reason: collision with root package name */
    private static String f11115m;

    /* renamed from: n  reason: collision with root package name */
    private static String f11116n;
    private static boolean o;

    /* renamed from: g  reason: collision with root package name */
    private View f11117g;

    /* renamed from: h  reason: collision with root package name */
    private b f11118h;

    /* renamed from: i  reason: collision with root package name */
    private XView f11119i;

    /* renamed from: j  reason: collision with root package name */
    private c f11120j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements XViewCallBack {
        a() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onCloseButtonClicked() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onError(int i2) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onStart() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewDisplayed() {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewLoadingUrl(String str) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewReady() {
            RedPacketRainDialog.this.d();
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewRequest(XViewRequest xViewRequest) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXViewVisibleChanged(boolean z) {
        }

        @Override // com.jingdong.common.XView.XViewCallBack
        public void onXVivewClosed() {
            try {
                RedPacketRainDialog.this.dismiss();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (RedPacketRainDialog.this.f11120j != null) {
                RedPacketRainDialog.this.f11120j.onXVivewClosed();
            }
        }
    }

    /* loaded from: classes4.dex */
    static class b extends Handler {
        WeakReference<RedPacketRainDialog> a;

        b(RedPacketRainDialog redPacketRainDialog) {
            this.a = new WeakReference<>(redPacketRainDialog);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<RedPacketRainDialog> weakReference = this.a;
            if (weakReference == null || weakReference.get() == null || message.what != 0) {
                return;
            }
            this.a.get().dismiss();
        }
    }

    /* loaded from: classes4.dex */
    public interface c {
        void onXVivewClosed();
    }

    private void c() {
        XView xView = this.f11119i;
        if (xView != null) {
            xView.closeXView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        XView xView = this.f11119i;
        if (xView != null) {
            xView.displayXView();
        }
    }

    public static RedPacketRainDialog e(Activity activity, String str, String str2, boolean z) {
        OKLog.d("bundleicssdkservice", f11113k + "---RedPacketRainDialog newInstance link:" + str + " pageName: " + str2 + " needCloseButton: " + z);
        f11114l = activity;
        Bundle bundle = new Bundle();
        bundle.putSerializable("link", str);
        bundle.putString(WebPerfManager.PAGE_NAME, str2);
        bundle.putBoolean("needCloseButton", z);
        RedPacketRainDialog redPacketRainDialog = new RedPacketRainDialog();
        redPacketRainDialog.setArguments(bundle);
        return redPacketRainDialog;
    }

    private void g(View view, String str) {
        OKLog.d("bundleicssdkservice", f11113k + "---RedPacketRainDialog startRpRainView URL\uff1a" + str);
        XView xView = this.f11119i;
        if (xView == null || !xView.isXViewShow()) {
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.dialog_rp_layout);
            XViewEntity xViewEntity = new XViewEntity();
            xViewEntity.needAutoDisplay = false;
            if (o) {
                xViewEntity.needCloseButton = true;
            } else {
                xViewEntity.needCloseButton = false;
            }
            xViewEntity.url = str;
            XView createXView = XViewHelper.createXView(f11114l, viewGroup, f11116n, xViewEntity, new a());
            this.f11119i = createXView;
            if (createXView != null) {
                if (o) {
                    createXView.configCloseButton("res:///2130838832", 0.97f, 0.05f);
                }
                this.f11119i.startXView();
            }
        }
    }

    public void f(FragmentManager fragmentManager) {
        StringBuilder sb = new StringBuilder();
        String str = f11113k;
        sb.append(str);
        sb.append("---RedPacketRainDialog showDialog");
        OKLog.d("bundleicssdkservice", sb.toString());
        try {
            if (isAdded()) {
                getFragmentManager().beginTransaction().remove(this).commit();
            }
            show(fragmentManager, str);
        } catch (Exception unused) {
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            f11115m = getArguments().getString("link");
            f11116n = getArguments().getString(WebPerfManager.PAGE_NAME);
            o = getArguments().getBoolean("needCloseButton");
        }
        if (UnAndroidUtils.isDisplayCutoutLocal(f11114l)) {
            setStyle(1, R.style.f44a2m);
        } else {
            setStyle(1, R.style.f43a2l);
        }
        this.f11118h = new b(this);
    }

    @Override // android.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        if (this.f11117g == null) {
            this.f11117g = layoutInflater.inflate(R.layout.activity_dialog_rp, viewGroup, false);
        }
        g(this.f11117g, f11115m);
        return this.f11117g;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        f11114l = null;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        OKLog.d("bundleicssdkservice", f11113k + "---RedPacketRainDialog onDismiss");
        super.onDismiss(dialogInterface);
        c();
        try {
            b bVar = this.f11118h;
            if (bVar != null) {
                bVar.removeMessages(0);
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        XView xView = this.f11119i;
        if (xView != null) {
            xView.onResume();
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStop() {
        super.onStop();
        XView xView = this.f11119i;
        if (xView != null) {
            xView.onStop();
        }
    }
}
