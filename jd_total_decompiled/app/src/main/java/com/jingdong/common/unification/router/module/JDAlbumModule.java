package com.jingdong.common.unification.router.module;

import android.app.Activity;
import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkAlbumHelper;
import com.jingdong.common.unification.album.AlbumConstant;
import com.jingdong.common.unification.album.AlbumParam;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.unification.router.builderimpl.AlbumBuilder;
import java.util.List;

/* loaded from: classes6.dex */
public class JDAlbumModule extends AbsJDModule {
    private AlbumParam getParamFromJson(JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        AlbumParam albumParam = new AlbumParam();
        if (jDJSONObject.containsKey("fromSource")) {
            albumParam.source = jDJSONObject.getString("fromSource");
        }
        if (jDJSONObject.containsKey(AlbumConstant.CAMERA_OR_VIDEO_ACTION)) {
            albumParam.cameraOrVideoAction = jDJSONObject.getIntValue(AlbumConstant.CAMERA_OR_VIDEO_ACTION);
        }
        if (jDJSONObject.containsKey(AlbumConstant.LOAD_CAMERA_OR_VIDEO)) {
            albumParam.loadCameraOrVideo = jDJSONObject.getIntValue(AlbumConstant.LOAD_CAMERA_OR_VIDEO);
        }
        if (jDJSONObject.containsKey(AlbumConstant.CAN_SELECT_MEDIA_COUNT)) {
            albumParam.canSelectMediaCount = jDJSONObject.getIntValue(AlbumConstant.CAN_SELECT_MEDIA_COUNT);
        }
        if (jDJSONObject.containsKey(AlbumConstant.VIDEO_EDITOR_ACTION)) {
            albumParam.videoEditorAction = jDJSONObject.getIntValue(AlbumConstant.VIDEO_EDITOR_ACTION);
        }
        if (jDJSONObject.containsKey(AlbumConstant.VIDEO_MIN_DURATION)) {
            albumParam.videoMaxTime = jDJSONObject.getString(AlbumConstant.VIDEO_MIN_DURATION);
        }
        if (jDJSONObject.containsKey(AlbumConstant.VIDEO_MAX_DURATION)) {
            albumParam.videoMaxTime = jDJSONObject.getString(AlbumConstant.VIDEO_MAX_DURATION);
        }
        if (jDJSONObject.containsKey("needEditorPic")) {
            albumParam.needEditorPic = jDJSONObject.getBoolean("needEditorPic").booleanValue();
        }
        if (jDJSONObject.containsKey(AlbumConstant.PIC_BEAUTIFY_ANIMATE_SWITCH)) {
            albumParam.showAnimatePic = jDJSONObject.getBoolean(AlbumConstant.PIC_BEAUTIFY_ANIMATE_SWITCH).booleanValue();
        }
        if (jDJSONObject.containsKey(AlbumConstant.NEED_CUT_PIC)) {
            albumParam.needCutPic = jDJSONObject.getBoolean(AlbumConstant.NEED_CUT_PIC).booleanValue();
        }
        if (routerEntry != null) {
            albumParam.selectedMedia = (List) routerEntry.extraBundle.get(AlbumConstant.SELECT_MEDIAS);
        }
        return albumParam;
    }

    @Override // com.jingdong.common.unification.router.module.AbsJDModule
    public void show(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (context != null && jDJSONObject != null) {
            AlbumParam albumParam = routerEntry instanceof AlbumBuilder.AlbumRouterEntry ? ((AlbumBuilder.AlbumRouterEntry) routerEntry).albumParam : null;
            if (albumParam == null) {
                albumParam = getParamFromJson(jDJSONObject, routerEntry);
            }
            int i2 = routerEntry.requestCode;
            if (i2 != -1 && (context instanceof Activity)) {
                DeepLinkAlbumHelper.startAlbumActivityForResult((Activity) context, albumParam, i2);
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
