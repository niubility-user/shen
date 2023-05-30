package com.jingdong.app.mall.mylib.CouponUnit.Dynamic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IContainerCallback;
import com.jd.dynamic.apis.IDYContainerListener;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.lib.error.DynamicException;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class BaseView extends FrameLayout implements IContainerCallback {
    private static final String TAG = BaseView.class.getSimpleName();
    private DynamicContainer container;
    private String templateId;

    public BaseView(@NonNull Context context) {
        this(context, null);
    }

    private void prepare() {
        DynamicSdk.getDriver().prepare(getModuleName(), "");
    }

    public void bindView() {
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer == null) {
            OKLog.d(TAG, "Dynamic BaseView must init before show.");
        } else {
            dynamicContainer.bindView();
        }
    }

    public void createView() {
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer == null) {
            OKLog.d(TAG, "Dynamic BaseView must init before show.");
        } else {
            dynamicContainer.createView();
        }
    }

    protected abstract void dyViewAttachToParent(DynamicContainer dynamicContainer);

    protected abstract IDYContainerListener getDyContainerListener();

    public String getModuleName() {
        return "coupon";
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public void init(Context context) {
        removeAllViews();
        IDynamicDriver driver = DynamicSdk.getDriver();
        if (OKLog.D) {
            String moduleName = getModuleName();
            String templateId = getTemplateId();
            OKLog.d(TAG, "moduleName: ", moduleName, "  templateId: " + templateId);
        }
        DYContainerConfig dYContainerConfig = new DYContainerConfig(context, getModuleName(), getTemplateId(), new DynamicFunctionFactory());
        dYContainerConfig.shouldAutoListenDarkStatus(true);
        dYContainerConfig.setUseTagViewOptimize(true);
        dYContainerConfig.setUseAsyncItem(true);
        dYContainerConfig.setUseCacheView(true);
        IDYContainerListener dyContainerListener = getDyContainerListener();
        if (dyContainerListener == null) {
            this.container = driver.createContainer(dYContainerConfig);
        } else {
            this.container = driver.createContainer(dYContainerConfig, dyContainerListener);
        }
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer != null) {
            dyViewAttachToParent(dynamicContainer);
        }
    }

    @Override // com.jd.dynamic.apis.IContainerCallback
    public void onError(@NonNull DynamicException dynamicException) {
        OKLog.d(TAG, "DynamicView load error");
        dynamicException.printStackTrace();
    }

    @Override // com.jd.dynamic.apis.IContainerCallback
    public void onSuccess() {
        OKLog.d(TAG, "DynamicView load success");
    }

    public void onViewRecycled() {
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer != null) {
            dynamicContainer.onViewRecycled();
        }
    }

    public void setData(JSONObject jSONObject) {
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer == null) {
            OKLog.d(TAG, "Dynamic BaseView must init before setData.");
        } else if (jSONObject == null) {
            OKLog.d(TAG, "data \u4e0d\u80fd\u4e3anull");
        } else {
            dynamicContainer.setData(jSONObject);
        }
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    public void show() {
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer == null) {
            OKLog.d(TAG, "Dynamic BaseView must init before show.");
        } else if (dynamicContainer.getParent() == null) {
            OKLog.d(TAG, "Dynamic BaseView must attach parent before show.");
        } else {
            this.container.load();
        }
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        prepare();
    }

    @RequiresApi(api = 21)
    public BaseView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        prepare();
    }
}
