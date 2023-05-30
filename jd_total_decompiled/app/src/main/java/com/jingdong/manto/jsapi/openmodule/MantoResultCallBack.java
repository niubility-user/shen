package com.jingdong.manto.jsapi.openmodule;

import android.os.Bundle;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes15.dex */
public interface MantoResultCallBack extends IMantoSdkBase {
    void onCancel(Bundle bundle);

    void onFailed(Bundle bundle);

    void onSuccess(Bundle bundle);
}
