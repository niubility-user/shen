package com.jd.viewkit.templates.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.jdviewkitvirtualfreecouponview.JDViewKitVirtualFreeCouponView;

/* loaded from: classes18.dex */
public class JDViewKitFreeCouponView extends JDViewKitAbsoluteLayout {
    public static String statusKey = "status";
    private long oldTime;

    public JDViewKitFreeCouponView(@NonNull Context context) {
        super(context);
        this.oldTime = 0L;
    }

    @Override // com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout
    public JDViewKitDataSourceModel getDataSourceModel() {
        return this.dataSourceModel;
    }

    @Override // com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout
    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        super.setDataSourceModel(jDViewKitDataSourceModel, z);
    }

    public void setVirtualView(JDViewKitVirtualFreeCouponView jDViewKitVirtualFreeCouponView) {
        super.setVirtualView((JDViewKitVirtualView) jDViewKitVirtualFreeCouponView);
    }

    public JDViewKitFreeCouponView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
        this.oldTime = 0L;
    }

    public JDViewKitFreeCouponView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.oldTime = 0L;
    }
}
