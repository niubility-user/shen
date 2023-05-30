package com.jingdong.sdk.platform.floor.isv;

import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.utils.PlatformTools;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes10.dex */
public class BaseFunction extends CommFunction {
    protected BaseDynFloor baseFloor;
    DataSupport dataSupport;
    public DynCommonPopView dynCommonPopView;
    private JSONObject jsonObj;
    protected Context mContext;
    private ICommonBasicAbility mIcommonBaseAbility;

    /* JADX WARN: Type inference failed for: r0v3, types: [com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity] */
    private BaseTemplateEntity getFloorEntity() {
        DataSupport dataSupport = this.dataSupport;
        if (dataSupport != null) {
            return dataSupport.mFloorEntity;
        }
        BaseDynFloor baseDynFloor = this.baseFloor;
        if (baseDynFloor != null) {
            return baseDynFloor.getBaseEntity();
        }
        return null;
    }

    private String getSysCode() {
        DataSupport dataSupport = this.dataSupport;
        if (dataSupport != null) {
            return dataSupport.systemCode;
        }
        BaseDynFloor baseDynFloor = this.baseFloor;
        return baseDynFloor != null ? baseDynFloor.getSysCode() : "";
    }

    private JSONObject isPopviewTemplateReady(String str, String str2, String str3) {
        if (ISVConst.isTemplateDownloaded(getSysCode(), str)) {
            try {
                return new JSONObject(str3);
            } catch (JSONException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private void openPopview(String str, String str2, String str3, String str4) {
        double d;
        JSONObject isPopviewTemplateReady = isPopviewTemplateReady(str2, str3, str4);
        if (isPopviewTemplateReady == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                isPopviewTemplateReady.put(ISVConst.EVENT_PARAM_EXTDATA, new JSONObject(str));
            } catch (JSONException e2) {
                PlatformTools.catchExceptionAndReportToBugly(e2);
            }
        }
        if (this.dynCommonPopView == null) {
            this.dynCommonPopView = genereatePopView();
        }
        try {
            d = Double.parseDouble(str3);
        } catch (Exception e3) {
            PlatformTools.catchExceptionAndReportToBugly(e3);
            d = 0.6d;
        }
        this.dynCommonPopView.showPopWindow(this.mContext, getSysCode(), str2, d, isPopviewTemplateReady, this);
    }

    private void popFromData() {
        JSONObject jSONObject;
        JSONObject optJSONObject;
        DataSupport dataSupport;
        String optParam = optParam("dialogContent");
        try {
            jSONObject = new JSONObject(optParam);
        } catch (JSONException e2) {
            PlatformTools.catchExceptionAndReportToBugly(e2);
            jSONObject = null;
        }
        if (jSONObject == null && (dataSupport = this.dataSupport) != null && dataSupport.getBusinessData() != null) {
            jSONObject = this.dataSupport.getBusinessData().optJSONObject("popData");
        }
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("templateData")) == null) {
            return;
        }
        openPopview(optParam(ISVConst.EVENT_PARAM_EXTDATA), optJSONObject.optString("popTemplateId"), optJSONObject.optString("height"), optParam);
    }

    public void closeDialog() {
        ICommonBasicAbility iCommonBasicAbility = this.mIcommonBaseAbility;
        if (iCommonBasicAbility != null) {
            iCommonBasicAbility.closeDialog();
        }
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        this.jsonObj = jSONObject;
        return exec(jSONObject.optString("fun"));
    }

    public DynCommonPopView genereatePopView() {
        return new DynCommonPopView();
    }

    protected MtaData getMtaData(boolean z) {
        return new MtaData(z, this.jsonObj.optString("eventId"), this.jsonObj.optString("data"));
    }

    public void initData(BaseDynFloor baseDynFloor, Context context, DataSupport dataSupport) {
        this.baseFloor = baseDynFloor;
        this.mContext = context;
        this.dataSupport = dataSupport;
    }

    public void mtaClick() {
        ICommonBasicAbility iCommonBasicAbility = this.mIcommonBaseAbility;
        if (iCommonBasicAbility != null) {
            iCommonBasicAbility.sendMta(getMtaData(true));
        }
    }

    public void mtaExplore() {
        ICommonBasicAbility iCommonBasicAbility = this.mIcommonBaseAbility;
        if (iCommonBasicAbility != null) {
            iCommonBasicAbility.sendMta(getMtaData(false));
        }
    }

    public String optParam(String str) {
        JSONObject jSONObject = this.jsonObj;
        return jSONObject != null ? jSONObject.optString(str) : "";
    }

    public void refresh() {
        ICommonBasicAbility iCommonBasicAbility = this.mIcommonBaseAbility;
        if (iCommonBasicAbility != null) {
            iCommonBasicAbility.refresh();
        }
    }

    public void setCommonBaseAbility(ICommonBasicAbility iCommonBasicAbility) {
        this.mIcommonBaseAbility = iCommonBasicAbility;
    }

    public void showDialog(FrameLayout frameLayout, double d) {
        ICommonBasicAbility iCommonBasicAbility = this.mIcommonBaseAbility;
        if (iCommonBasicAbility != null) {
            iCommonBasicAbility.showDialog(frameLayout, d);
        }
    }

    protected void showToastShortNormal(Context context, String str) {
        ICommonBasicAbility iCommonBasicAbility = this.mIcommonBaseAbility;
        if (iCommonBasicAbility != null) {
            iCommonBasicAbility.showToastShortNormal(context, str);
        }
    }

    /* loaded from: classes10.dex */
    public static class DataSupport {
        private JSONObject businessData;
        private BaseTemplateEntity mFloorEntity;
        private String systemCode;

        public DataSupport(JSONObject jSONObject, BaseTemplateEntity baseTemplateEntity) {
            this.businessData = jSONObject;
            this.mFloorEntity = baseTemplateEntity;
        }

        public Object getBaseConfigData() {
            return this.mFloorEntity;
        }

        public JSONObject getBusinessData() {
            return this.businessData;
        }

        public void update(JSONObject jSONObject, BaseTemplateEntity baseTemplateEntity) {
            this.businessData = jSONObject;
            this.mFloorEntity = baseTemplateEntity;
        }

        public DataSupport(String str, JSONObject jSONObject, BaseTemplateEntity baseTemplateEntity) {
            this.systemCode = str;
            this.businessData = jSONObject;
            this.mFloorEntity = baseTemplateEntity;
        }
    }

    public Object exec(String str) {
        if (TextUtils.equals("openH5", str)) {
            PDBaseDeepLinkHelper.gotoMWithUrl(this.mContext, optParam("url"));
            return "";
        } else if (TextUtils.equals("openApp", str)) {
            OpenAppUtils.openAppForInner(this.mContext, optParam("url"));
            return "";
        } else if (TextUtils.equals(ISVConst.EVENT_OPEN_POP_WINDOW, str)) {
            if (getFloorEntity() != null && getFloorEntity().mfPopStyle != null) {
                BaseTemplateEntity floorEntity = getFloorEntity();
                String optParam = optParam(ISVConst.EVENT_PARAM_EXTDATA);
                BaseTemplateEntity.DynPopStyle dynPopStyle = floorEntity.mfPopStyle;
                openPopview(optParam, dynPopStyle.popStyleId, dynPopStyle.height, optParam("templateData"));
                return "";
            } else if (!TextUtils.isEmpty(optParam("dialogContent"))) {
                popFromData();
                return "";
            } else {
                openPopview(optParam(ISVConst.EVENT_PARAM_EXTDATA), optParam("templateID"), optParam("templateHeight"), optParam("templateData"));
                return "";
            }
        } else if (TextUtils.equals(ISVConst.EVENT_CLOSE_POP_WINDOW, str)) {
            closeDialog();
            return "";
        } else if (TextUtils.equals("refresh", str)) {
            refresh();
            return "";
        } else if (TextUtils.equals(ISVConst.EVENT_MTA_CLICK, str)) {
            mtaClick();
            return "";
        } else if (TextUtils.equals("mtaExplore", str)) {
            mtaExplore();
            return "";
        } else if (TextUtils.equals(XView2Constants.XVIEW2_ACTION_TOAST, str)) {
            showToastShortNormal(this.mContext, optParam("message"));
            return "";
        } else if (TextUtils.equals(ISVConst.EVENT_WILL_REFRESH, str) || !TextUtils.equals(ISVConst.EVENT_SHOW_FLOOR, str) || this.baseFloor == null) {
            return "";
        } else {
            this.baseFloor.setVisable(optParam("value"));
            return "";
        }
    }

    public void initData(Context context, DataSupport dataSupport) {
        initData(null, context, dataSupport);
    }
}
