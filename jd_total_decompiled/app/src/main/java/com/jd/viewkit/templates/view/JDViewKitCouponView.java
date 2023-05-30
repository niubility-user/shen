package com.jd.viewkit.templates.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.jdviewkitvirtualcouponview.JDViewKitVirtualCouponView;
import com.jd.viewkit.templates.model.jdviewkitvirtualfreecouponview.JDViewKitVirtualFreeCouponView;

/* loaded from: classes18.dex */
public class JDViewKitCouponView extends JDViewKitFreeCouponView {
    public JDViewKitCouponView(@NonNull Context context) {
        super(context);
    }

    public void setVirtualView(JDViewKitVirtualCouponView jDViewKitVirtualCouponView) {
        super.setVirtualView((JDViewKitVirtualFreeCouponView) jDViewKitVirtualCouponView);
    }

    public JDViewKitCouponView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
    }

    public JDViewKitCouponView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
