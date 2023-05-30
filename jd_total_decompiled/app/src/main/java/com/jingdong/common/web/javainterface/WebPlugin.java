package com.jingdong.common.web.javainterface;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.web.ui.IXFragment;

/* loaded from: classes6.dex */
public class WebPlugin implements IBridgePlugin {

    /* loaded from: classes6.dex */
    public interface CallBack {
        void callback(Object obj);
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(@Nullable IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable String str2, @Nullable final IBridgeCallback iBridgeCallback) {
        if (str == null) {
            return false;
        }
        str.hashCode();
        if (str.equals("notifyMessage")) {
            if (iBridgeWebView != null) {
                Fragment findFragment = FragmentManager.findFragment(iBridgeWebView.getView());
                if (findFragment instanceof IXFragment) {
                    ((IXFragment) findFragment).onJsMessage(str2, new CallBack() { // from class: com.jingdong.common.web.javainterface.WebPlugin.1
                        @Override // com.jingdong.common.web.javainterface.WebPlugin.CallBack
                        public void callback(Object obj) {
                            IBridgeCallback iBridgeCallback2 = iBridgeCallback;
                            if (iBridgeCallback2 != null) {
                                iBridgeCallback2.onSuccess(obj);
                            }
                        }
                    });
                    return true;
                } else if (iBridgeCallback != null) {
                    iBridgeCallback.onError("This function is not supported in the current container !");
                    return true;
                } else {
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}
