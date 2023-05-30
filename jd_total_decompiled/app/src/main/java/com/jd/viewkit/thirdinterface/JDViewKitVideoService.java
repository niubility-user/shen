package com.jd.viewkit.thirdinterface;

import android.content.Context;
import android.view.View;
import com.jd.viewkit.thirdinterface.model.JDViewKitVideoModel;

/* loaded from: classes18.dex */
public interface JDViewKitVideoService {
    View getVideoView(JDViewKitVideoModel jDViewKitVideoModel, Context context);

    void jumpToVideoActivity(Context context, JDViewKitVideoModel jDViewKitVideoModel);

    void releasePlayer(View view);
}
