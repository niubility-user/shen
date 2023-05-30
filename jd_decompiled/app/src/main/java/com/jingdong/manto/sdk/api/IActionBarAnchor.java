package com.jingdong.manto.sdk.api;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IActionBarAnchor extends IMantoSdkBase {
    View getAnchorView();

    void onClicked(Activity activity, Bundle bundle);
}
