package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeAudioRecordListener {
    void deleteRecordingFile(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void startRecording(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, Activity activity);

    void stopRecording(JDCallback jDCallback, JDCallback jDCallback2);
}
