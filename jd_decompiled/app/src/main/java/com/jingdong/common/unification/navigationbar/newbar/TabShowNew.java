package com.jingdong.common.unification.navigationbar.newbar;

import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class TabShowNew {
    public String functionId;
    public boolean hasAngle;
    private Boolean isShowNew;
    private Boolean isShowRedPoint;
    private RedPointView view;

    public TabShowNew(RedPointView redPointView) {
        this.view = redPointView;
    }

    private void reportBubbleExp() {
        Context context;
        try {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("name", (Object) this.functionId);
            jDJSONObject.put("type", (Object) "2");
            RedPointView redPointView = this.view;
            if (redPointView == null || (context = redPointView.getContext()) == null) {
                return;
            }
            JDMtaUtils.sendExposureDataWithExt(context, "NavigationBar_BubbleExpo", "", "NavigationBar_Main", "", "", jDJSONObject.toJSONString(), null);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("bubble_expo", e2.toString());
            }
        }
    }

    public Boolean getIsShowNew() {
        return this.isShowNew;
    }

    public Boolean getIsShowRedPoint() {
        return this.isShowRedPoint;
    }

    public void setIsShowNew(Boolean bool) {
        if (NavigationBase.getInstance().angleSwitch() && this.hasAngle) {
            return;
        }
        this.isShowNew = bool;
        this.view.drawNumAble(false);
        this.view.drawLabelEnable(false);
        this.view.invalidate();
        if (bool.booleanValue()) {
            reportBubbleExp();
        }
    }

    public void setIsShowRedPoint(Boolean bool) {
        if (NavigationBase.getInstance().angleSwitch() && this.hasAngle) {
            return;
        }
        this.view.drawNumAble(false);
        this.view.drawLabelEnable(false);
        this.isShowRedPoint = bool;
        this.view.invalidate();
        if (bool.booleanValue()) {
            reportBubbleExp();
        }
    }
}
