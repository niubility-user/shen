package com.jingdong.common.utils.personal;

import androidx.annotation.NonNull;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;

/* loaded from: classes6.dex */
public interface PersonInfoBusinessCallback {
    void onError(Exception exc);

    void onSuccess(@NonNull HttpResponse httpResponse);
}
