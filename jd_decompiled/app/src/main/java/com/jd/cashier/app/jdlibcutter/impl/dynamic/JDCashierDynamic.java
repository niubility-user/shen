package com.jd.cashier.app.jdlibcutter.impl.dynamic;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamicListener;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IContainerCallback;
import com.jd.dynamic.apis.IDYContainerListener;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.IFunctionFactory;
import com.jd.dynamic.lib.error.DynamicException;
import com.jingdong.corelib.utils.Log;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDCashierDynamic implements IDynamic {
    private static final String TAG = "DynamicImpl";

    @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic
    public boolean asyncLoad(ViewGroup viewGroup, JSONObject jSONObject) {
        if (!(viewGroup instanceof DynamicContainer) || jSONObject == null) {
            return false;
        }
        DynamicContainer dynamicContainer = (DynamicContainer) viewGroup;
        dynamicContainer.setData(jSONObject);
        boolean load = dynamicContainer.load();
        if (!load) {
            dynamicContainer.load(new IContainerCallback() { // from class: com.jd.cashier.app.jdlibcutter.impl.dynamic.JDCashierDynamic.1
                @Override // com.jd.dynamic.apis.IContainerCallback
                public void onError(@NonNull DynamicException dynamicException) {
                }

                @Override // com.jd.dynamic.apis.IContainerCallback
                public void onSuccess() {
                }
            });
        }
        return load;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic
    public void bindData(ViewGroup viewGroup, JSONObject jSONObject) {
        if (!(viewGroup instanceof DynamicContainer) || jSONObject == null) {
            return;
        }
        DynamicContainer dynamicContainer = (DynamicContainer) viewGroup;
        dynamicContainer.setData(jSONObject);
        dynamicContainer.refresh();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic
    public ViewGroup createDynamicContainer(Context context, String str, String str2, IDynamicListener iDynamicListener) {
        return createDynamicContainer(context, str, str2, iDynamicListener, null);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic
    public boolean haveBackUp(String str, String str2) {
        try {
            return DynamicSdk.getDriver().getTemplateStatus(str, str2).getB();
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
                return false;
            }
            return false;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic
    public boolean haveCache(String str, String str2) {
        try {
            return DynamicSdk.getDriver().getTemplateStatus(str, str2).getA();
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
                return false;
            }
            return false;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic
    public void prepare(String str, String str2) {
        try {
            DynamicSdk.getDriver().prepare(str, str2);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic
    public ViewGroup createDynamicContainer(Context context, String str, String str2, final IDynamicListener iDynamicListener, final DynamicEventListener<JSONObject> dynamicEventListener) {
        DYContainerConfig dYContainerConfig;
        IDynamicDriver driver = DynamicSdk.getDriver();
        if (dynamicEventListener != null) {
            dYContainerConfig = new DYContainerConfig(context, str, str2, new IFunctionFactory() { // from class: com.jd.cashier.app.jdlibcutter.impl.dynamic.JDCashierDynamic.2
                @Override // com.jd.dynamic.base.IFunctionFactory
                public CommFunction createCommFunction(String str3) {
                    if (TextUtils.equals(str3, CashierDynamicFunction.PAY_DYNAMIC_CLASS_NAME)) {
                        return new CashierDynamicFunction(dynamicEventListener);
                    }
                    return null;
                }
            });
        } else {
            dYContainerConfig = new DYContainerConfig(context, str, str2, null);
        }
        return driver.createContainer(dYContainerConfig, new IDYContainerListener() { // from class: com.jd.cashier.app.jdlibcutter.impl.dynamic.JDCashierDynamic.3
            @Override // com.jd.dynamic.apis.IDYContainerListener
            public void onCreate() {
                IDynamicListener iDynamicListener2 = iDynamicListener;
                if (iDynamicListener2 != null) {
                    iDynamicListener2.onCreate();
                }
            }

            @Override // com.jd.dynamic.apis.IDYContainerListener
            public void onLoad() {
                IDynamicListener iDynamicListener2 = iDynamicListener;
                if (iDynamicListener2 != null) {
                    iDynamicListener2.onLoad();
                }
            }

            @Override // com.jd.dynamic.apis.IDYContainerListener
            public void onRefresh() {
                IDynamicListener iDynamicListener2 = iDynamicListener;
                if (iDynamicListener2 != null) {
                    iDynamicListener2.onRefresh();
                }
            }
        });
    }
}
