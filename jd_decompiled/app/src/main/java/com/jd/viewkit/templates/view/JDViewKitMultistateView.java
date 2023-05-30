package com.jd.viewkit.templates.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.jdveiwkitvirtualmultistateview.JDViewKitVirtualMultistateView;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.tool.DateTool;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitMultistateView extends JDViewKitAbsoluteLayout {
    private static final String TAG = "JDViewKitMultistateView";
    private JDViewKitAbsoluteLayout contentLayout;
    private JDViewKitVirtualMultistateView multistateView;
    private String selectState;

    public JDViewKitMultistateView(@NonNull Context context) {
        super(context);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public JDViewKitVirtualView getVirtualView() {
        return super.getVirtualView();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceMap(Map map, boolean z) {
        super.setDataSourceMap(map, z);
    }

    @Override // com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout
    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        String stringValueRef;
        JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout;
        super.setDataSourceModel(jDViewKitDataSourceModel, z);
        if (!StringTool.isNotEmpty(this.multistateView.getValueRefer()) || (stringValueRef = ExpressionParserTool.getStringValueRef(this.multistateView.getValueRefer(), jDViewKitDataSourceModel.getDataMap())) == null) {
            return;
        }
        String str = this.selectState;
        if (str != null && str.equals(stringValueRef) && (jDViewKitAbsoluteLayout = this.contentLayout) != null) {
            jDViewKitAbsoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, z);
        } else {
            JDViewKitVirtualView jDViewKitVirtualView = this.multistateView.getStateMap().get(stringValueRef);
            View view = this.contentLayout;
            if (view != null) {
                removeView(view);
                this.contentLayout = null;
            }
            JDViewKitAbsoluteLayout view2 = JDViewKitViewFactory.getView(((JDViewKitAbsoluteLayout) this).mContext, jDViewKitDataSourceModel, jDViewKitVirtualView, getChannelModel());
            this.contentLayout = view2;
            addView(view2);
            this.selectState = stringValueRef;
        }
        updateFitStateView();
    }

    public void setVirtualView(JDViewKitVirtualMultistateView jDViewKitVirtualMultistateView) {
        super.setVirtualView((JDViewKitVirtualView) jDViewKitVirtualMultistateView);
        this.multistateView = jDViewKitVirtualMultistateView;
    }

    public boolean updataByState(String str) {
        if (StringTool.isNotEmpty(str)) {
            this.dataSourceModel.setTimeStame(DateTool.getTime());
            JDViewKitDataSourceModel jDViewKitDataSourceModel = this.dataSourceModel;
            if (jDViewKitDataSourceModel != null && jDViewKitDataSourceModel.getDataMap() != null) {
                this.dataSourceModel.getDataMap().put(JDViewKitCountdownView.useTimeOutKey, 0);
                this.dataSourceModel.setLongDataState(0);
                for (Map.Entry<String, Object> entry : this.dataSourceModel.getDataMap().entrySet()) {
                    if (entry.getKey().startsWith(JDViewKitProgressView.useTimeOutKey)) {
                        entry.setValue(0);
                    }
                }
            }
            JDViewKitVirtualMultistateView jDViewKitVirtualMultistateView = this.multistateView;
            if (jDViewKitVirtualMultistateView != null && jDViewKitVirtualMultistateView.getStateMap().get(str) != null) {
                ExpressionParserTool.setObjectValueRef(this.multistateView.getValueRefer(), this.dataSourceModel.getDataMap(), str);
                setDataSourceModel(this.dataSourceModel, false);
                return true;
            }
        }
        return false;
    }

    public void updateFitStateView() {
        JDViewKitVirtualMultistateView jDViewKitVirtualMultistateView = this.multistateView;
        JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout = this.contentLayout;
        JDViewKitVirtualView virtualView = jDViewKitAbsoluteLayout != null ? jDViewKitAbsoluteLayout.getVirtualView() : null;
        if (jDViewKitVirtualMultistateView != null && virtualView != null) {
            int realPx = GlobalManage.getInstance().getRealPx(jDViewKitVirtualMultistateView.getOrgX(), getChannelModel());
            int realPx2 = GlobalManage.getInstance().getRealPx(jDViewKitVirtualMultistateView.getOrgY(), getChannelModel());
            int realPx3 = GlobalManage.getInstance().getRealPx(virtualView.getOrgX(), getChannelModel());
            int realPx4 = GlobalManage.getInstance().getRealPx(virtualView.getOrgY(), getChannelModel());
            int realPx5 = GlobalManage.getInstance().getRealPx(virtualView.getWidth(), getChannelModel());
            int realPx6 = GlobalManage.getInstance().getRealPx(virtualView.getHeigh(), getChannelModel());
            int i2 = realPx + realPx3;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(realPx5, realPx6);
            layoutParams.topMargin = realPx2 + realPx4;
            layoutParams.leftMargin = i2;
            setLayoutParams(layoutParams);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(realPx5, realPx6);
            layoutParams2.topMargin = 0;
            layoutParams2.leftMargin = 0;
            this.contentLayout.setLayoutParams(layoutParams2);
        }
        String str = "pudateFitStateView: " + jDViewKitVirtualMultistateView;
        String str2 = "pudateFitStateView: " + virtualView;
    }

    public JDViewKitMultistateView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
    }
}
