package com.jdcn.jdmall_js_bridge2;

import android.content.Context;
import android.text.TextUtils;
import com.jdcn.common_bridge.JdcnCommonBridge;
import com.jingdong.common.web.IRouterParams;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JdcnJdmallJsBridge {
    public static void jdcnSqJsProtocolBridge(final IRouterParams iRouterParams) {
        if (iRouterParams != null) {
            try {
                String routerParam = iRouterParams.getRouterParam();
                Context context = iRouterParams.getContext();
                if (context != null && !TextUtils.isEmpty(routerParam)) {
                    JdcnCommonBridge.serviceCall(context, routerParam, new JdcnCommonBridge.JdcnCommonBridgeCallback() { // from class: com.jdcn.jdmall_js_bridge2.JdcnJdmallJsBridge.1
                        @Override // com.jdcn.common_bridge.JdcnCommonBridge.JdcnCommonBridgeCallback
                        public void callback(String str) {
                            try {
                                IRouterParams.this.onCallBack(new JSONObject(str));
                            } catch (Exception unused) {
                            }
                        }
                    });
                } else {
                    iRouterParams.onCallBack("context == null || paramStr == null");
                }
            } catch (Throwable unused) {
            }
        }
    }
}
