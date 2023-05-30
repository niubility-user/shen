package com.jd.manto.d;

import android.content.Context;
import android.os.Bundle;
import com.jd.manto.navigate.NavigateProxyActivity;
import com.jingdong.manto.sdk.api.IFeedback;

/* loaded from: classes17.dex */
public class l implements IFeedback {
    @Override // com.jingdong.manto.sdk.api.IFeedback
    public void jumpToFeedback(Context context, Bundle bundle) {
        NavigateProxyActivity.x(context, bundle);
    }
}
