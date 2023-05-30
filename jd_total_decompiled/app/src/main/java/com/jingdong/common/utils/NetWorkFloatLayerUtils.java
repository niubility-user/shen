package com.jingdong.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class NetWorkFloatLayerUtils {
    private static final String TAG = "NetWorkFloatLayerUtils";
    private static NetWorkFloatLayerUtils smLastInstance;
    private boolean isCanShow = false;
    private RelativeLayout layout;
    private Activity mActivity;
    private View model;
    private ViewGroup rootFrameLayout;

    public NetWorkFloatLayerUtils(Activity activity) {
        this.mActivity = activity;
    }

    public static NetWorkFloatLayerUtils getLastInstance() {
        return smLastInstance;
    }

    @SuppressLint({"NewApi"})
    private View getModel() {
        if (OKLog.D) {
            OKLog.d(TAG, " NetWorkFloatLayerUtils --> getModel() ");
        }
        int dip2px = DPIUtil.dip2px(74.0f);
        if (this.model == null) {
            this.model = ImageUtil.inflate(R.layout.app_network_model, null);
        }
        if (this.layout == null) {
            RelativeLayout relativeLayout = (RelativeLayout) this.model.findViewById(R.id.app_network_model_layout);
            this.layout = relativeLayout;
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.NetWorkFloatLayerUtils.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (NetWorkFloatLayerUtils.this.mActivity != null) {
                        Intent intent = new Intent("android.settings.SETTINGS");
                        if (NetWorkFloatLayerUtils.this.mActivity.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                            NetWorkFloatLayerUtils.this.mActivity.startActivity(intent);
                        } else {
                            Toast.makeText(NetWorkFloatLayerUtils.this.mActivity, "\u65e0\u6cd5\u8fdb\u5165\u624b\u673a\u7f51\u7edc\u8bbe\u7f6e", 0).show();
                        }
                    }
                }
            });
        }
        if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
            this.layout.setY(dip2px);
        } else {
            ((AbsoluteLayout.LayoutParams) this.layout.getLayoutParams()).y = dip2px;
        }
        return this.model;
    }

    private ViewGroup getRootFrameLayout() {
        ViewGroup viewGroup = this.rootFrameLayout;
        if (viewGroup != null) {
            return viewGroup;
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mActivity.getWindow().peekDecorView();
        this.rootFrameLayout = viewGroup2;
        if (viewGroup2 == null) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
            }
            this.rootFrameLayout = getRootFrameLayout();
        }
        return this.rootFrameLayout;
    }

    private void handleFloat() {
        if (OKLog.D) {
            OKLog.d(TAG, " NetWorkFloatLayerUtils --> handleFloat() , baseActivity == " + this.mActivity.getClass().getSimpleName());
        }
        if (NetUtils.isNetworkAvailable()) {
            hideModel();
        } else {
            showModel();
        }
    }

    private void hideModel() {
        ViewGroup rootFrameLayout = getRootFrameLayout();
        if (rootFrameLayout.indexOfChild(getModel()) != -1) {
            rootFrameLayout.removeView(getModel());
            rootFrameLayout.invalidate();
            if (OKLog.D) {
                OKLog.d(TAG, " NetWorkFloatLayerUtils --> hideModel  finish ");
            }
        }
    }

    public static void setLastInstance(NetWorkFloatLayerUtils netWorkFloatLayerUtils) {
        smLastInstance = netWorkFloatLayerUtils;
    }

    private void showModel() {
        ViewGroup rootFrameLayout = getRootFrameLayout();
        if (rootFrameLayout.indexOfChild(getModel()) != -1) {
            return;
        }
        rootFrameLayout.addView(getModel());
        rootFrameLayout.invalidate();
        if (OKLog.D) {
            OKLog.d(TAG, " NetWorkFloatLayerUtils --> showModel  finish ");
        }
    }

    public void checkIsCanShow() {
        if (this.mActivity != null) {
            handleFloat();
        }
    }
}
