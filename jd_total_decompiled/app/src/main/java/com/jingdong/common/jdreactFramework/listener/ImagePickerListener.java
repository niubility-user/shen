package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Intent;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface ImagePickerListener {
    void onActivityResult(Activity activity, int i2, int i3, Intent intent);

    void onDestroy();

    void pickFromAlbum(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, Activity activity);

    void pickFromCamera(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, Activity activity);
}
