package com.jingdong.app.mall.bundle.order_center_isv_core.base;

import android.content.Context;
import android.view.ViewGroup;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVWidgetUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt;
import java.util.HashMap;

/* loaded from: classes3.dex */
public abstract class AbstractOrderDetailISVFloor extends OrderDetailISVBaseFloor {
    public IBaseCooperateExt cooperateExt;

    public AbstractOrderDetailISVFloor(Context context, BaseFloorData baseFloorData, String str, ViewGroup viewGroup) {
        super(context, baseFloorData, str, viewGroup);
    }

    private void doException() {
        hideFloor();
    }

    private void doFloorOperate(BaseTemplateEntity baseTemplateEntity) {
        Object obj;
        if (baseTemplateEntity == null || (obj = baseTemplateEntity.mData) == null || !(obj instanceof OrderDetailISVBaseFloorData)) {
            return;
        }
        OrderDetailISVBaseFloorData orderDetailISVBaseFloorData = (OrderDetailISVBaseFloorData) obj;
        this.cooperateExt = orderDetailISVBaseFloorData.cooperateExt;
        baseTemplateEntity.mData = orderDetailISVBaseFloorData.data;
    }

    private void doRenderUi(BaseTemplateEntity baseTemplateEntity) {
        OrderISVWidgetUtil.setVisible(this.mRootView);
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            viewGroup.setBackgroundColor(0);
        }
        showData(baseTemplateEntity);
    }

    protected void mtaWithClick(String str, String str2, String str3, String str4) {
        if (this.cooperateExt != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("jsonParam", str3);
            hashMap.put("eventParam", str2);
            hashMap.put("pageParam", str4);
            this.cooperateExt.mtaClick(str, hashMap);
        }
    }

    protected void mtaWithExpo(String str, String str2, String str3, String str4) {
        if (this.cooperateExt != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("jsonParam", str3);
            hashMap.put("eventParam", str2);
            hashMap.put("pageParam", str4);
            this.cooperateExt.mtaExplore(str, hashMap);
        }
    }

    protected void refreshOrderDetail() {
        IBaseCooperateExt iBaseCooperateExt = this.cooperateExt;
        if (iBaseCooperateExt != null) {
            iBaseCooperateExt.refresh();
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(BaseEntity baseEntity) {
        try {
            if (baseEntity instanceof BaseTemplateEntity) {
                doFloorOperate((BaseTemplateEntity) baseEntity);
                doRenderUi((BaseTemplateEntity) baseEntity);
            } else {
                doException();
            }
        } catch (Exception e2) {
            doException();
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    public abstract void showData(BaseTemplateEntity baseTemplateEntity);
}
