package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Intent;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeMultiMediaModuleListener {
    void captureVideo(JDCallback jDCallback, JDCallback jDCallback2);

    void captureVideo(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void hidFullScreenVideoView(JDCallback jDCallback, JDCallback jDCallback2);

    void imageCompressToBase64(String str, int i2, JDCallback jDCallback, JDCallback jDCallback2);

    void imageCompression(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void onActivityResult(Activity activity, int i2, int i3, Intent intent);

    void onHostDestroy();

    void onHostPause();

    void onHostResume();

    void pickPhoto(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void scanCode(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void showFullScreenVideoView(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void startPlaying(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void stopPlaying(JDCallback jDCallback, JDCallback jDCallback2);
}
