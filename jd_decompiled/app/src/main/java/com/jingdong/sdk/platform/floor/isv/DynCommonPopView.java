package com.jingdong.sdk.platform.floor.isv;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IContainerCallback;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.base.XMLParse;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.error.DynamicException;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.isv.BaseFunction;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes10.dex */
public class DynCommonPopView {
    private DynamicTemplateEngine engine;
    private double height;
    private DynamicContainer mContainer;
    private JSONObject mData;
    private FrameLayout rootView;
    private WeakReference<Context> weakReference;

    private InputStream getLocalXmlStream(String str) throws IOException {
        return this.weakReference.get().getAssets().open(str + ".xml");
    }

    private String isPopviewTemplateReady(String str, String str2) throws JSONException {
        JSONObject optJSONObject;
        JSONObject jSONObject = !TextUtils.isEmpty(str2) ? new JSONObject(str2) : null;
        if (jSONObject != null && (optJSONObject = jSONObject.optJSONObject("templateData")) != null) {
            String optString = optJSONObject.optString("popTemplateId");
            this.height = optJSONObject.optDouble("height", 0.6000000238418579d);
            if (ISVConst.isTemplateDownloaded(str, optString)) {
                return optString;
            }
        }
        return null;
    }

    public static DynCommonPopView openPopview(Context context, String str, String str2, BaseTemplateEntity baseTemplateEntity, ICommonBasicAbility iCommonBasicAbility) {
        String str3;
        JSONObject jSONObject;
        DynCommonPopView dynCommonPopView = new DynCommonPopView();
        String str4 = "";
        try {
            str4 = dynCommonPopView.isPopviewTemplateReady(ISVConst.getSystemCode(str), str2);
            str3 = str4;
            jSONObject = !TextUtils.isEmpty(str4) ? new JSONObject(str2) : null;
        } catch (JSONException e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            ISVConst.ISVReportBussiness(str, baseTemplateEntity);
            str3 = str4;
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        BaseFunction.DataSupport dataSupport = new BaseFunction.DataSupport(ISVConst.getSystemCode(str), jSONObject, baseTemplateEntity);
        BaseFunction baseFunction = new BaseFunction();
        baseFunction.initData(context, dataSupport);
        baseFunction.setCommonBaseAbility(iCommonBasicAbility);
        dynCommonPopView.showPopWindow(context, ISVConst.getSystemCode(str), str3, dynCommonPopView.height, jSONObject, baseFunction);
        baseFunction.dynCommonPopView = dynCommonPopView;
        return dynCommonPopView;
    }

    public boolean showPopWindow(Context context, String str, String str2, double d, JSONObject jSONObject, final BaseFunction baseFunction) {
        if (ISVConst.isTemplateDownloaded(str, str2) && jSONObject != null) {
            this.weakReference = new WeakReference<>(context);
            this.height = Math.min(Math.max(d, 0.2d), 1.0d);
            this.mData = jSONObject;
            if (ISVConst.IS_DEBUG) {
                this.rootView = new FrameLayout(context);
                if (!(context instanceof Activity)) {
                    return false;
                }
                this.engine = new DynamicTemplateEngine(context.getPackageName(), (Activity) context, this.rootView, new IFunctionFactory() { // from class: com.jingdong.sdk.platform.floor.isv.DynCommonPopView.1
                    {
                        DynCommonPopView.this = this;
                    }

                    @Override // com.jd.dynamic.base.IFunctionFactory
                    public CommFunction createCommFunction(String str3) {
                        if (TextUtils.equals(str3, ISVConst.ISV_DYN_COMMON_EVENT_CLASS)) {
                            return baseFunction;
                        }
                        return null;
                    }
                });
                useLocal(str2, baseFunction);
            } else {
                this.mContainer = DynamicSdk.getDriver().createContainer(new DYContainerConfig(context, str, str2, new IFunctionFactory() { // from class: com.jingdong.sdk.platform.floor.isv.DynCommonPopView.2
                    {
                        DynCommonPopView.this = this;
                    }

                    @Override // com.jd.dynamic.base.IFunctionFactory
                    public CommFunction createCommFunction(String str3) {
                        if (TextUtils.equals(str3, ISVConst.ISV_DYN_COMMON_EVENT_CLASS)) {
                            return baseFunction;
                        }
                        return null;
                    }
                }));
            }
            DynamicContainer dynamicContainer = this.mContainer;
            if (dynamicContainer != null) {
                this.rootView = dynamicContainer;
                dynamicContainer.setData(this.mData);
                this.mContainer.load(new IContainerCallback() { // from class: com.jingdong.sdk.platform.floor.isv.DynCommonPopView.3
                    {
                        DynCommonPopView.this = this;
                    }

                    @Override // com.jd.dynamic.apis.IContainerCallback
                    public void onError(@NotNull DynamicException dynamicException) {
                    }

                    @Override // com.jd.dynamic.apis.IContainerCallback
                    public void onSuccess() {
                        BaseFunction baseFunction2 = baseFunction;
                        if (baseFunction2 != null) {
                            baseFunction2.showDialog(DynCommonPopView.this.rootView, DynCommonPopView.this.height);
                        }
                    }
                });
                return true;
            }
            return true;
        }
        return false;
    }

    protected void useLocal(String str, BaseFunction baseFunction) {
        try {
            if ((this.weakReference.get() instanceof Activity) && (this.rootView instanceof FrameLayout)) {
                XMLParse xMLParse = new XMLParse(getLocalXmlStream(str));
                ViewNode parse = xMLParse.parse();
                parse.unBindMaps = xMLParse.unBindMaps;
                this.engine.initTemplate(parse, this.mData, null);
                this.mData.toString();
                if (baseFunction != null) {
                    baseFunction.showDialog(this.rootView, this.height);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
