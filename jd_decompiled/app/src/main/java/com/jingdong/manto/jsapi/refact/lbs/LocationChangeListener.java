package com.jingdong.manto.jsapi.refact.lbs;

import android.os.Bundle;
import androidx.annotation.Keep;
import com.jingdong.manto.jsengine.IMantoBaseInterface;

@Keep
/* loaded from: classes15.dex */
public interface LocationChangeListener extends IMantoBaseInterface {
    void onLocationChange(Bundle bundle);

    void onLocationFailed(int i2, String str);
}
