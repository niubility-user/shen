package com.jd.viewkit.templates.manager;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;

/* loaded from: classes18.dex */
public interface JDViewKitViewManager {
    View virtualView2View(@NonNull Context context, @NonNull JDViewKitVirtualView jDViewKitVirtualView, @NonNull JDViewKitChannelModel jDViewKitChannelModel);
}
