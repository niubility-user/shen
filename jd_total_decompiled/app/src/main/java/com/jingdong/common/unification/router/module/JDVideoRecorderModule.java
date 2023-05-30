package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkVideoAndImageHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.unification.router.builderimpl.VideoRecorderBuilder;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoParam;
import java.util.List;

/* loaded from: classes6.dex */
public class JDVideoRecorderModule extends AbsJDModule {
    private VideoParam getParamFromJson(JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        VideoParam videoParam = new VideoParam();
        if (jDJSONObject.containsKey(VideoConstant.RECORD_FUNCTION_CONTROL)) {
            videoParam.recordFunctionControl = jDJSONObject.getIntValue(VideoConstant.RECORD_FUNCTION_CONTROL);
        }
        if (jDJSONObject.containsKey(VideoConstant.RECORD_CURRENT_STASTE)) {
            videoParam.recordCurrentState = jDJSONObject.getIntValue(VideoConstant.RECORD_CURRENT_STASTE);
        }
        if (jDJSONObject.containsKey(VideoConstant.IS_SQUARE_PHOTO)) {
            videoParam.isSquarePhoto = jDJSONObject.getBoolean(VideoConstant.IS_SQUARE_PHOTO).booleanValue();
        }
        if (jDJSONObject.containsKey(VideoConstant.SQUARE_PHOTO_WIDTH)) {
            videoParam.squarePhotoWidth = jDJSONObject.getIntValue(VideoConstant.SQUARE_PHOTO_WIDTH);
        }
        if (jDJSONObject.containsKey(VideoConstant.RECORD_MIN_TIME)) {
            videoParam.recordMinTime = jDJSONObject.getIntValue(VideoConstant.RECORD_MIN_TIME);
        }
        if (jDJSONObject.containsKey(VideoConstant.RECORD_MAX_TIME)) {
            videoParam.recordMaxTime = jDJSONObject.getIntValue(VideoConstant.RECORD_MAX_TIME);
        }
        if (jDJSONObject.containsKey(VideoConstant.NEED_EDITOR)) {
            videoParam.needEditor = jDJSONObject.getBoolean(VideoConstant.NEED_EDITOR).booleanValue();
        }
        if (jDJSONObject.containsKey("needEditorPic")) {
            videoParam.needEditorPic = jDJSONObject.getBoolean("needEditorPic").booleanValue();
        }
        if (jDJSONObject.containsKey(VideoConstant.EDITOR_VIDEO_PATH)) {
            videoParam.editorVideoPath = jDJSONObject.getString(VideoConstant.EDITOR_VIDEO_PATH);
        }
        if (jDJSONObject.containsKey(VideoConstant.EDITOR_FUNCTION_CONTROL)) {
            videoParam.editorFunctionControl = jDJSONObject.getIntValue(VideoConstant.EDITOR_FUNCTION_CONTROL);
        }
        if (jDJSONObject.containsKey(VideoConstant.CUT_MIN_TIME)) {
            videoParam.cutMinTime = jDJSONObject.getIntValue(VideoConstant.CUT_MIN_TIME);
        }
        if (jDJSONObject.containsKey(VideoConstant.CUT_MAX_TIME)) {
            videoParam.cutMaxTime = jDJSONObject.getIntValue(VideoConstant.CUT_MAX_TIME);
        }
        if (routerEntry != null) {
            videoParam.filterTypes = (List) routerEntry.extraBundle.get(VideoConstant.FILTER_TYPES);
            videoParam.picFilterTypes = (List) routerEntry.extraBundle.get("picFilterTypes");
        }
        return videoParam;
    }

    @Override // com.jingdong.common.unification.router.module.AbsJDModule
    public void show(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (context != null && jDJSONObject != null) {
            VideoParam videoParam = routerEntry instanceof VideoRecorderBuilder.VideoRecorderRouterEntry ? ((VideoRecorderBuilder.VideoRecorderRouterEntry) routerEntry).videoParam : null;
            if (videoParam == null) {
                videoParam = getParamFromJson(jDJSONObject, routerEntry);
            }
            int i2 = routerEntry.requestCode;
            if (i2 != -1 && (context instanceof Activity)) {
                DeepLinkVideoAndImageHelper.startVideoRecorderActivityForResult((Activity) context, videoParam, i2);
            } else {
                DeepLinkVideoAndImageHelper.startVideoRecorderActivity(context, videoParam);
            }
            CallBackListener callBackListener = routerEntry.callBackListener;
            if (callBackListener != null) {
                callBackListener.onComplete();
                return;
            }
            return;
        }
        CallBackListener callBackListener2 = routerEntry.callBackListener;
        if (callBackListener2 != null) {
            JDRouterUtil.callBackError(callBackListener2, 703);
        }
    }
}
