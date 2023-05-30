package com.jd.viewkit.templates.container;

import android.content.Context;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitFloorModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitEmptyView extends JDViewKitBaseLayout<JDViewKitVirtualView> {
    public JDViewKitEmptyView(@NonNull Context context) {
        super(context);
    }

    public void hiddenEmpty() {
        getLayoutParams().height = 1;
        requestLayout();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDslsMap(Map<String, JDViewKitVirtualView> map) {
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setFloorModel(JDViewKitFloorModel jDViewKitFloorModel) {
        hiddenEmpty();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setFloorModelByLayout(JDViewKitFloorModel jDViewKitFloorModel) {
        hiddenEmpty();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualView jDViewKitVirtualView) {
        setLayoutParams(new FrameLayout.LayoutParams(GlobalManage.getInstance().getScreenWidth(), 1));
    }

    public JDViewKitEmptyView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
    }
}
