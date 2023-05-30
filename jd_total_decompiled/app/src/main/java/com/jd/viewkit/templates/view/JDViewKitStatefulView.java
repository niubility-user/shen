package com.jd.viewkit.templates.view;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.jdviewkitvirtualstatefulview.JDViewKitVirtualStatefulView;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitStatefulView extends JDViewKitAbsoluteLayout {
    private JDViewKitAbsoluteLayout contentLayout;
    private String selectState;
    private JDViewKitVirtualStatefulView statefulView;

    public JDViewKitStatefulView(@NonNull Context context) {
        super(context);
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
        if (!StringTool.isNotEmpty(this.statefulView.getValueRefer()) || (stringValueRef = ExpressionParserTool.getStringValueRef(this.statefulView.getValueRefer(), jDViewKitDataSourceModel.getDataMap())) == null) {
            return;
        }
        String str = this.selectState;
        if (str != null && str.equals(stringValueRef) && (jDViewKitAbsoluteLayout = this.contentLayout) != null) {
            jDViewKitAbsoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, z);
            return;
        }
        JDViewKitVirtualView jDViewKitVirtualView = this.statefulView.getStateMap().get(stringValueRef);
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

    public void setVirtualView(JDViewKitVirtualStatefulView jDViewKitVirtualStatefulView) {
        super.setVirtualView((JDViewKitVirtualView) jDViewKitVirtualStatefulView);
        this.statefulView = jDViewKitVirtualStatefulView;
    }

    public JDViewKitStatefulView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
    }
}
