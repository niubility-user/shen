package com.jd.viewkit.templates.view.helper.eventcallback;

import android.view.View;
import androidx.annotation.NonNull;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitViewListenerParamsModel;
import com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitScorllView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewkitEventRecommendMoreCallBack extends JDViewKitEventAbstractCallBack {
    public static final String MORE_KEY_PATH = "path";
    public static final String MORE_KEY_VALUE = "value";

    public JDViewkitEventRecommendMoreCallBack(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        super(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, i2, jDViewKitChannelModel);
    }

    public void callBackPageRefresh(@NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        if (jDViewKitChannelModel.getJdViewKitFloorModel() == null || jDViewKitChannelModel.getJdViewKitFloorModel().getViewListener() == null) {
            return;
        }
        jDViewKitChannelModel.getJdViewKitFloorModel().getViewListener().floorRefresh(new JDViewKitViewListenerParamsModel(JDViewKitViewListenerParamsModel.paramsModelTypeRefreshPage, null), null);
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void failCallBack(int i2, Map<String, Object> map, String str) {
        super.failNextCallBack();
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void successCallBack(int i2, Map<String, Object> map, String str) {
        super.successNextCallBack();
    }

    public void updateData(int i2, Map<String, Object> map, String str) {
        JDViewKitChannelModel jDViewKitChannelModel;
        JSONObject JSONObject;
        Map<String, Object> map2;
        if (this.view == null || (jDViewKitChannelModel = this.channelModel) == null || jDViewKitChannelModel.getJdViewKitFloorModel() == null || !StringTool.isNotEmpty(str) || (JSONObject = JSONTool.JSONObject(str)) == null) {
            return;
        }
        String string = JSONTool.getString(JSONObject, "path");
        if (StringTool.isEmpty(string) || (map2 = JSONTool.getMap(JSONTool.getJSONObject(JSONObject, "value"))) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : map2.keySet()) {
            if (map2.get(str2) instanceof List) {
                sb.append(str2);
            }
        }
        if (sb.length() > 0) {
            map2.put(JDViewKitScorllView.ResetoffsetKey, sb.toString());
        }
        ExpressionParserTool.setMapByValueRef(string, this.channelModel.getJdViewKitFloorModel().getDataSourceMap(), map2);
        this.channelModel.getJdViewKitFloorModel().setForceUpdate(true);
        callBackPageRefresh(this.channelModel);
    }
}
