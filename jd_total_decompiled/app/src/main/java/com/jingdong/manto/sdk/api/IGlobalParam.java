package com.jingdong.manto.sdk.api;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.Map;

/* loaded from: classes16.dex */
public interface IGlobalParam extends IMantoSdkBase {
    String getCartUUID(Context context);

    @NonNull
    Map getEncryptUUID(Context context);

    @NonNull
    String getIp(Context context);

    @NonNull
    String getRandomCartUUID(Context context);

    @NonNull
    String getUUID(Context context);
}
