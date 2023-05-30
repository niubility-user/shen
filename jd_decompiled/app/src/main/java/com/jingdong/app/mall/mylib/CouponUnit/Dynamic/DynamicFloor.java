package com.jingdong.app.mall.mylib.CouponUnit.Dynamic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IDYContainerListener;
import com.jd.dynamic.base.DynamicMtaUtil;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class DynamicFloor extends BaseView {
    public DynamicFloor(@NonNull Context context) {
        super(context);
    }

    public void bindDynamicData(JSONObject jSONObject) {
        long nanoTime = System.nanoTime();
        setData(jSONObject);
        bindView();
        String str = "bindDynamicData take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime);
    }

    public void createDynamicView(String str) {
        long nanoTime = System.nanoTime();
        setTemplateId(str);
        init(getContext());
        createView();
        setVisibility(0);
        String str2 = "createDynamicView take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime);
    }

    @Override // com.jingdong.app.mall.mylib.CouponUnit.Dynamic.BaseView
    protected void dyViewAttachToParent(DynamicContainer dynamicContainer) {
        addView(dynamicContainer, new FrameLayout.LayoutParams(-1, -2));
    }

    @Override // com.jingdong.app.mall.mylib.CouponUnit.Dynamic.BaseView
    protected IDYContainerListener getDyContainerListener() {
        return null;
    }

    public void refreshDynamicView(JSONObject jSONObject) {
        if (jSONObject != null) {
            setData(jSONObject);
            show();
        }
    }

    public void showDynamicView(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            setTemplateId(str);
            init(getContext());
            setData(jSONObject);
            setVisibility(0);
            show();
        }
    }

    public DynamicFloor(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DynamicFloor(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @RequiresApi(api = 21)
    public DynamicFloor(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
