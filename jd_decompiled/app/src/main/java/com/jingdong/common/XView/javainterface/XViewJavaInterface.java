package com.jingdong.common.XView.javainterface;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class XViewJavaInterface extends BaseWebComponent implements IJavaInterface {
    private static final String TAG = "XViewJavaInterface";
    static final String XVIEW_VERSION = "2.0.0";

    public XViewJavaInterface(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @JavascriptInterface
    public void close() {
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.XView.javainterface.XViewJavaInterface.1
            @Override // java.lang.Runnable
            public void run() {
                if (((BaseWebComponent) XViewJavaInterface.this).webUiBinder.getUi() instanceof XView) {
                    ((XView) ((BaseWebComponent) XViewJavaInterface.this).webUiBinder.getUi()).closeXViewFromJs();
                }
            }
        });
    }

    @JavascriptInterface
    public void configCloseButton(String str, float f2, float f3) {
        if (this.webUiBinder.getUi() instanceof XView) {
            ((XView) this.webUiBinder.getUi()).configCloseButton(str, f2, f3);
        }
    }

    @JavascriptInterface
    public void configXView(String str) {
        if (Log.D) {
            Log.d(TAG, "configXView    jsonStr:" + str);
        }
        JDJSONObject jDJSONObject = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                jDJSONObject = JDJSON.parseObject(str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (!(this.webUiBinder.getUi() instanceof XView) || jDJSONObject == null) {
            return;
        }
        configXViewFromJS((XView) this.webUiBinder.getUi(), jDJSONObject);
    }

    public void configXViewFromJS(final XView xView, JDJSONObject jDJSONObject) {
        double optDouble = jDJSONObject.optDouble("top");
        double optDouble2 = jDJSONObject.optDouble("height");
        if (optDouble < 0.0d || optDouble >= 1.0d) {
            optDouble = 0.0d;
        }
        if (optDouble2 <= 0.0d || optDouble2 >= 1.0d) {
            optDouble2 = 1.0d;
        }
        double optDouble3 = jDJSONObject.optDouble("closeBtnDelay");
        double d = optDouble3 >= 0.0d ? optDouble3 : 0.0d;
        double height = DPIUtil.getHeight();
        Double.isNaN(height);
        final double d2 = height * optDouble;
        double height2 = DPIUtil.getHeight();
        Double.isNaN(height2);
        final double d3 = optDouble2 * height2;
        if (xView != null) {
            xView.setCloseBtnDelayTime((int) d);
            xView.post(new Runnable() { // from class: com.jingdong.common.XView.javainterface.XViewJavaInterface.2
                @Override // java.lang.Runnable
                public void run() {
                    xView.setTranslationY((int) d2);
                    ViewGroup.LayoutParams layoutParams = xView.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams.height = (int) d3;
                        xView.setLayoutParams(layoutParams);
                    }
                }
            });
        }
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return XView2Constants.CACHE_FILE_ROOT;
    }

    @JavascriptInterface
    public void mediaPlay() {
        this.webUiBinder.getJdWebView().injectJs("play();");
    }

    @JavascriptInterface
    public String version() {
        return XVIEW_VERSION;
    }
}
