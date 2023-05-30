package com.jd.viewkit.templates.view.helper.eventcallback;

import android.view.View;
import android.widget.Toast;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.templates.view.JDViewKitCountdownView;
import com.jd.viewkit.templates.view.JDViewKitMultistateView;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitEventCallbackCallBack extends JDViewKitEventAbstractCallBack {
    private JDViewKitMultistateView multistateView;
    private JDViewKitVirtualServiceModel serviceModel;

    public JDViewKitEventCallbackCallBack(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        super(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, i2, jDViewKitChannelModel);
        this.serviceModel = jDViewKitVirtualView.getServiceModel();
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void failCallBack(int i2, Map<String, Object> map, String str) {
        if (StringTool.isNotEmpty(str)) {
            JSONObject JSONObject = JSONTool.JSONObject(str);
            if (this.view != null && JSONObject != null) {
                String string = JSONTool.getString(JSONObject, "showToast");
                if (StringTool.isNotEmpty(string) && string.equals("1")) {
                    JSONObject jSONObject = JSONTool.getJSONObject(JSONObject, XView2Constants.XVIEW2_ACTION_TOAST);
                    String string2 = JSONTool.getString(jSONObject, "mainTitle");
                    String string3 = JSONTool.getString(jSONObject, "subTitle");
                    if (!StringTool.isNotEmpty(string2)) {
                        string2 = StringTool.isNotEmpty(string3) ? string3 : null;
                    }
                    if (StringTool.isNotEmpty(string2)) {
                        JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel = this.serviceModel;
                        if (jDViewKitVirtualServiceModel != null && jDViewKitVirtualServiceModel.getToastListener() != null) {
                            this.serviceModel.getToastListener().showToast(this.view.getContext(), string2, 0, 1);
                        } else {
                            Toast.makeText(this.view.getContext(), string2, 1).show();
                        }
                    }
                }
            }
        }
        super.failNextCallBack();
    }

    public JDViewKitMultistateView getMultistateView() {
        return this.multistateView;
    }

    public void setMultistateView(JDViewKitMultistateView jDViewKitMultistateView) {
        this.multistateView = jDViewKitMultistateView;
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void successCallBack(int i2, Map<String, Object> map, String str) {
        boolean UpdateMultiState;
        JDViewKitDataSourceModel jDViewKitDataSourceModel;
        if (this.view != null && this.dataSourceModel != null && StringTool.isNotEmpty(str)) {
            JSONObject JSONObject = JSONTool.JSONObject(str);
            if (JSONObject != null) {
                String string = JSONTool.getString(JSONObject, "showToast");
                if (StringTool.isNotEmpty(string) && string.equals("1")) {
                    JSONObject jSONObject = JSONTool.getJSONObject(JSONObject, XView2Constants.XVIEW2_ACTION_TOAST);
                    String string2 = JSONTool.getString(jSONObject, "mainTitle");
                    String string3 = JSONTool.getString(jSONObject, "subTitle");
                    if (!StringTool.isNotEmpty(string2)) {
                        string2 = StringTool.isNotEmpty(string3) ? string3 : null;
                    }
                    if (StringTool.isNotEmpty(string2)) {
                        JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel = this.serviceModel;
                        if (jDViewKitVirtualServiceModel != null && jDViewKitVirtualServiceModel.getToastListener() != null) {
                            this.serviceModel.getToastListener().showToast(this.view.getContext(), string2, 0, 1);
                        } else {
                            Toast.makeText(this.view.getContext(), string2, 1).show();
                        }
                    }
                }
                JSONArray jSONArray = JSONTool.getJSONArray(JSONObject, "refreshKey");
                if (jSONArray != null && (jSONArray instanceof JSONArray)) {
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONArray, i3);
                        if (jSONObject2 != null && (jSONObject2 instanceof JSONObject)) {
                            String string4 = JSONTool.getString(jSONObject2, "name");
                            String string5 = JSONTool.getString(jSONObject2, "value");
                            if (StringTool.isNotEmpty(string4) && StringTool.isNotEmpty(string5) && (jDViewKitDataSourceModel = this.dataSourceModel) != null && jDViewKitDataSourceModel.getDataMap() != null) {
                                ExpressionParserTool.setObjectValueRef(string4, this.dataSourceModel.getDataMap(), string5);
                            }
                        }
                    }
                }
                JDViewKitDataSourceModel jDViewKitDataSourceModel2 = this.dataSourceModel;
                if (jDViewKitDataSourceModel2 != null && jDViewKitDataSourceModel2.getDataMap() != null) {
                    this.dataSourceModel.getDataMap().put(JDViewKitCountdownView.useTimeOutKey, 0);
                }
                String string6 = JSONTool.getString(JSONObject, "activeState");
                if (StringTool.isNotEmpty(string6)) {
                    JDViewKitMultistateView jDViewKitMultistateView = this.multistateView;
                    if (jDViewKitMultistateView != null) {
                        UpdateMultiState = JDViewKitEventHelper.UpdateMultiState(jDViewKitMultistateView, string6);
                    } else {
                        UpdateMultiState = JDViewKitEventHelper.UpdateMultiState(this.view, string6);
                    }
                    if (!UpdateMultiState) {
                        super.failNextCallBack();
                        return;
                    } else {
                        super.successNextCallBack();
                        return;
                    }
                }
                super.failNextCallBack();
                return;
            }
            super.failNextCallBack();
            return;
        }
        super.failNextCallBack();
    }
}
