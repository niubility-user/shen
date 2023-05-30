package com.jingdong.common.xbridge;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.widget.custom.CustomChannelFollowViewManager;
import com.jingdong.corelib.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class JDFollowPlugin implements IBridgePlugin, com.jd.xbridge.base.a {
    RelativeLayout mFollowContainer = null;

    private JDWebView getJDWebView(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            if (parent instanceof JDWebView) {
                return (JDWebView) parent;
            }
            return getJDWebView((View) parent);
        }
        return null;
    }

    @Override // com.jd.xbridge.base.a
    public void destroy() {
        CustomChannelFollowViewManager.getInstance().removeContainer(this.mFollowContainer);
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(final IBridgeWebView iBridgeWebView, String str, final String str2, IBridgeCallback iBridgeCallback) {
        Log.d("JDFollowPlugin", ":" + str);
        if ("showFollowTip".equals(str)) {
            final JDWebView jDWebView = getJDWebView((View) iBridgeWebView);
            if (jDWebView != null) {
                jDWebView.post(new Runnable() { // from class: com.jingdong.common.xbridge.JDFollowPlugin.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JDFollowPlugin jDFollowPlugin = JDFollowPlugin.this;
                        if (jDFollowPlugin.mFollowContainer == null) {
                            jDFollowPlugin.mFollowContainer = new RelativeLayout(jDWebView.getContext());
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                            layoutParams.addRule(12);
                            JDFollowPlugin.this.mFollowContainer.setLayoutParams(layoutParams);
                            if (jDWebView.getRootLayout() != null) {
                                if (JDFollowPlugin.this.mFollowContainer.getParent() instanceof ViewGroup) {
                                    ((ViewGroup) JDFollowPlugin.this.mFollowContainer.getParent()).removeView(JDFollowPlugin.this.mFollowContainer);
                                }
                                jDWebView.getRootLayout().addView(JDFollowPlugin.this.mFollowContainer, layoutParams);
                            }
                        }
                        try {
                            if (TextUtils.isEmpty(str2)) {
                                return;
                            }
                            CustomChannelFollowViewManager.getInstance().showFollowGuidTips(((View) iBridgeWebView).getContext(), JDFollowPlugin.this.mFollowContainer, new JSONObject(str2));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                });
                return true;
            }
            return true;
        }
        return false;
    }
}
