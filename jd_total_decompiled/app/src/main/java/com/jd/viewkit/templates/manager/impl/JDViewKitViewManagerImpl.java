package com.jd.viewkit.templates.manager.impl;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.manager.JDViewKitViewManager;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;

/* loaded from: classes18.dex */
public class JDViewKitViewManagerImpl implements JDViewKitViewManager {
    @Override // com.jd.viewkit.templates.manager.JDViewKitViewManager
    public View virtualView2View(@NonNull Context context, @NonNull JDViewKitVirtualView jDViewKitVirtualView, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        return JDViewKitViewFactory.getViewByVirtualView(context, jDViewKitVirtualView, jDViewKitChannelModel);
    }
}
