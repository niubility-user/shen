package com.jd.viewkit.templates.container;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import java.util.List;

/* loaded from: classes18.dex */
public class JDViewKitViewView extends JDViewKitBaseLayout<JDViewKitVirtualView> {
    private JDViewKitAbsoluteLayout mainView;

    public JDViewKitViewView(@NonNull Context context) {
        super(context);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        if (this.dataSourceModels.size() > 0) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = this.dataSourceModels.get(0);
            if (this.mainView == null) {
                JDViewKitAbsoluteLayout view = JDViewKitViewFactory.getView(this.mContext, getDslsMap().get(jDViewKitDataSourceModel.getDslId()), getChannelModel());
                this.mainView = view;
                addView(view);
            }
            this.mainView.setDataSourceModel(jDViewKitDataSourceModel, z);
            this.mainView.setDataSourceMap(jDViewKitDataSourceModel.getDataMap(), z);
        }
    }

    public JDViewKitViewView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
    }
}
