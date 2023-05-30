package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Intent;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.OnCallBackListener;
import com.jingdong.common.unification.router.builderimpl.VideoRecorderBuilder;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoParam;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeVideoRecorderListener implements NativeCameraPickListener {
    public static final String VIDEO_TAG = "JDReactNativeVideoRecorderListener";
    private JDCallback successCB;

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCameraPickListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        if (i3 == -1 && i2 == 101 && intent != null) {
            String stringExtra = intent.getStringExtra(VideoConstant.PICTURE_PATH);
            JLog.e(VIDEO_TAG, "onActivityResult: " + stringExtra);
            JDCallback jDCallback = this.successCB;
            if (jDCallback != null) {
                jDCallback.invoke(stringExtra);
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCameraPickListener
    public void onImagePicked(HashMap hashMap, JDCallback jDCallback, final JDCallback jDCallback2) {
        this.successCB = jDCallback;
        Activity currentMyActivity = JDReactHelper.newInstance().getCurrentMyActivity();
        if (currentMyActivity != null && hashMap != null) {
            VideoParam videoParam = new VideoParam();
            if (hashMap.containsKey(VideoConstant.RECORD_MAX_TIME)) {
                videoParam.recordMaxTime = (int) ((Double) hashMap.get(VideoConstant.RECORD_MAX_TIME)).doubleValue();
            }
            if (hashMap.containsKey(VideoConstant.RECORD_MIN_TIME)) {
                videoParam.recordMinTime = (int) ((Double) hashMap.get(VideoConstant.RECORD_MIN_TIME)).doubleValue();
            }
            if (hashMap.containsKey(VideoConstant.RECORD_FUNCTION_CONTROL)) {
                videoParam.recordFunctionControl = (int) ((Double) hashMap.get(VideoConstant.RECORD_FUNCTION_CONTROL)).doubleValue();
            }
            if (hashMap.containsKey(VideoConstant.RECORD_CURRENT_STASTE)) {
                videoParam.recordCurrentState = (int) ((Double) hashMap.get(VideoConstant.RECORD_CURRENT_STASTE)).doubleValue();
            }
            if (hashMap.containsKey(VideoConstant.NEED_EDITOR)) {
                videoParam.needEditor = ((Boolean) hashMap.get(VideoConstant.NEED_EDITOR)).booleanValue();
            }
            if (hashMap.containsKey("needEditorPic")) {
                videoParam.needEditorPic = ((Boolean) hashMap.get("needEditorPic")).booleanValue();
            }
            if (hashMap.containsKey(VideoConstant.EDITOR_FUNCTION_CONTROL)) {
                videoParam.editorFunctionControl = (int) ((Double) hashMap.get(VideoConstant.EDITOR_FUNCTION_CONTROL)).doubleValue();
            }
            ((VideoRecorderBuilder) JDRouter.to(VideoRecorderBuilder.class)).videoParam(videoParam).onCallBackListener(new OnCallBackListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeVideoRecorderListener.1
                @Override // com.jingdong.common.unification.router.OnCallBackListener
                public void onComplete() {
                }

                @Override // com.jingdong.common.unification.router.OnCallBackListener
                public void onError(int i2, String str) {
                    AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
                }
            }).setRequestCode(101).jump(currentMyActivity);
            return;
        }
        AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
    }
}
