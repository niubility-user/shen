package com.jingdong.common.web.xrender;

import android.os.Handler;
import android.os.Looper;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.XRenderAdapter;

/* loaded from: classes12.dex */
public class XRenderAdapterImpl extends XRenderAdapter {
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(HybridSDK.PreRender preRender) {
        XRender.Log("hybrid\u63a5\u53e3\u8bf7\u6c42\u6210\u529f \u8d4b\u503ccms\u9884\u6e32\u67d3\u6570\u636e");
        XRender.getInstance().setLastPreRenderDataTypeTemp(2);
        XRender.getInstance().setPreRenderData(preRender);
    }

    @Override // com.jd.libs.hybrid.adapter.XRenderAdapter
    public void getPreRenderData(boolean z, final HybridSDK.PreRender preRender) {
        if (!XRender.getInstance().isPreRender()) {
            XRender.Log("\u83b7\u53d6cms\u914d\u7f6e\u6570\u636e\u5931\u8d25\uff0cSwitchQuery\u5f00\u5173\u5df2\u5173");
        } else if (z) {
            this.mainHandler.post(new Runnable() { // from class: com.jingdong.common.web.xrender.e
                @Override // java.lang.Runnable
                public final void run() {
                    XRenderAdapterImpl.a(HybridSDK.PreRender.this);
                }
            });
        } else {
            XRender.Log("hybrid\u63a5\u53e3\u8bf7\u6c42\u5931\u8d25 \u4e0d\u505a\u5904\u7406");
        }
    }
}
