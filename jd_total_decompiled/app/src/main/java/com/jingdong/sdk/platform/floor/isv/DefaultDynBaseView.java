package com.jingdong.sdk.platform.floor.isv;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IContainerCallback;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.lib.error.DynamicException;
import com.jingdong.sdk.platform.floor.isv.BaseFunction;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes10.dex */
public class DefaultDynBaseView implements IBaseView {
    private BaseFunction.DataSupport dataSuport;
    DynamicContainer mContainer;

    public DefaultDynBaseView(DynamicContainer dynamicContainer, BaseFunction.DataSupport dataSupport) {
        this.mContainer = dynamicContainer;
        this.dataSuport = dataSupport;
    }

    public static DefaultDynBaseView createInstance(final Context context, final String str, String str2, JSONObject jSONObject) {
        IDynamicDriver driver = DynamicSdk.getDriver();
        final BaseFunction.DataSupport dataSupport = new BaseFunction.DataSupport(str, jSONObject, null);
        DynamicContainer createContainer = driver.createContainer(new DYContainerConfig(context, ISVConst.getSystemCode(str), str2, new IFunctionFactory() { // from class: com.jingdong.sdk.platform.floor.isv.DefaultDynBaseView.1
            @Override // com.jd.dynamic.base.IFunctionFactory
            public CommFunction createCommFunction(String str3) {
                return ISVConst.getFunction(str, null, context, str3, dataSupport);
            }
        }));
        DefaultDynBaseView defaultDynBaseView = new DefaultDynBaseView(createContainer, dataSupport);
        if (createContainer != null) {
            createContainer.setData(jSONObject);
            if (!createContainer.load()) {
                createContainer.load(new IContainerCallback() { // from class: com.jingdong.sdk.platform.floor.isv.DefaultDynBaseView.2
                    @Override // com.jd.dynamic.apis.IContainerCallback
                    public void onError(@NonNull DynamicException dynamicException) {
                    }

                    @Override // com.jd.dynamic.apis.IContainerCallback
                    public void onSuccess() {
                    }
                });
            }
        }
        return defaultDynBaseView;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseView
    public View getView() {
        return this.mContainer;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseView
    public void onBindData(String str) {
        if (this.mContainer == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.dataSuport.update(jSONObject, null);
            this.mContainer.setData(jSONObject);
            this.mContainer.load();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseView
    public void onCreateView(ViewGroup viewGroup) {
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseView
    public void onDestroy() {
        this.mContainer = null;
    }
}
