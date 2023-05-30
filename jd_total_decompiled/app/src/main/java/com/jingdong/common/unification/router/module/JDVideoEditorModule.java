package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkVideoAndImageHelper;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.unification.router.builderimpl.VideoEditorBuilder;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoParam;
import java.util.List;

/* loaded from: classes6.dex */
public class JDVideoEditorModule extends AbsJDModule {
    private VideoParam getParamFromJson(JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        VideoParam videoParam = new VideoParam();
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
        }
        return videoParam;
    }

    @Override // com.jingdong.common.unification.router.module.AbsJDModule
    public void show(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (context != null && jDJSONObject != null) {
            VideoParam videoParam = routerEntry instanceof VideoEditorBuilder.VideoEditorRouterEntry ? ((VideoEditorBuilder.VideoEditorRouterEntry) routerEntry).videoParam : null;
            if (videoParam == null) {
                videoParam = getParamFromJson(jDJSONObject, routerEntry);
            }
            int i2 = routerEntry.requestCode;
            if (i2 != -1 && (context instanceof Activity)) {
                DeepLinkVideoAndImageHelper.startVideoEditorActivityForResult((Activity) context, videoParam, i2);
            } else {
                DeepLinkVideoAndImageHelper.startVideoEditorActivity(context, videoParam);
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
