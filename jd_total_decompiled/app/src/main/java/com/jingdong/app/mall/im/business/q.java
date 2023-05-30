package com.jingdong.app.mall.im.business;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.deeplinkhelper.VideoPlayerDeepLinkHelper;
import com.jingdong.common.unification.album.AlbumConstant;
import com.jingdong.common.unification.album.AlbumParam;
import com.jingdong.common.unification.album.LocalMedia;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterMtaUtil;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.OnCallBackListener;
import com.jingdong.common.unification.router.builderimpl.AlbumBuilder;
import com.jingdong.common.unification.router.builderimpl.VideoRecorderBuilder;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoEditorFinishUtil;
import com.jingdong.common.unification.video.VideoParam;
import com.jingdong.common.videoplayer.VideoPlayerConstants;
import com.jingdong.common.widget.videosmallwindow.SmallWindowManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.entity.MediaInfo;
import com.jingdong.service.impl.IMMedia;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class q extends IMMedia {
    private static final String a = "q";

    /* loaded from: classes4.dex */
    class a implements OnCallBackListener {
        a(q qVar) {
        }

        @Override // com.jingdong.common.unification.router.OnCallBackListener
        public void onComplete() {
        }

        @Override // com.jingdong.common.unification.router.OnCallBackListener
        public void onError(int i2, String str) {
        }
    }

    /* loaded from: classes4.dex */
    class b implements VideoEditorFinishUtil.OnFinishClickListener {
        b(q qVar) {
        }

        @Override // com.jingdong.common.unification.video.VideoEditorFinishUtil.OnFinishClickListener
        public void onFinish(Context context, String str, int i2) {
            ToastUtils.showToast(context.getApplicationContext(), context + DYConstants.DY_REGEX_COMMA + str + DYConstants.DY_REGEX_COMMA + i2);
        }
    }

    /* loaded from: classes4.dex */
    class c implements OnCallBackListener {
        final /* synthetic */ Activity a;

        c(q qVar, Activity activity) {
            this.a = activity;
        }

        @Override // com.jingdong.common.unification.router.OnCallBackListener
        public void onComplete() {
        }

        @Override // com.jingdong.common.unification.router.OnCallBackListener
        public void onError(int i2, String str) {
            Activity activity = this.a;
            JDRouterMtaUtil.routerErrorMta(activity, activity.getClass().getName(), str + CartConstant.KEY_YB_INFO_LINK + i2);
        }
    }

    /* loaded from: classes4.dex */
    class d implements CallBackListener {
        d(q qVar) {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
        }
    }

    /* loaded from: classes4.dex */
    class e implements CallBackListener {
        e(q qVar) {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
        }
    }

    /* loaded from: classes4.dex */
    class f implements CallBackListener {
        f(q qVar) {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
        }
    }

    /* loaded from: classes4.dex */
    class g implements CallBackListener {
        g(q qVar) {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
        }
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public int getOpenAlbumRequestCode() {
        return 1000;
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public int getVideoEditorRequestCode() {
        return 101;
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public void handleGroupPic(Intent intent, String str) {
        ArrayList parcelableArrayListExtra;
        OKLog.d("bundleicssdkservice", a + "---handleGroupPic");
        if (intent == null || (parcelableArrayListExtra = intent.getParcelableArrayListExtra(AlbumConstant.SELECT_MEDIAS)) == null || parcelableArrayListExtra.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < parcelableArrayListExtra.size(); i2++) {
            LocalMedia localMedia = (LocalMedia) parcelableArrayListExtra.get(i2);
            if (localMedia != null) {
                MediaInfo mediaInfo = new MediaInfo();
                mediaInfo.pictureType = localMedia.getPictureType();
                mediaInfo.path = localMedia.getPath();
                mediaInfo.width = localMedia.getWidth();
                mediaInfo.height = localMedia.getHeight();
                arrayList.add(mediaInfo);
                OKLog.d("bundleicssdkservice", a + "---handleGroupPic path: " + mediaInfo.path);
            }
        }
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName("JDDongDongMediaModule").setMethodName("handleGroupPic").putStringParam("pin", str).putStringParam("mediaInfos", new Gson().toJson(arrayList));
            JDRouter.build(JdSdk.getInstance().getApplicationContext(), jDRouterUrlBuilder.build()).callBackListener(new e(this)).open();
        }
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public void handleGroupVideo(Intent intent, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        String str3 = a;
        sb.append(str3);
        sb.append("---handleGroupVideo");
        OKLog.d("bundleicssdkservice", sb.toString());
        int intExtra = intent.getIntExtra(VideoConstant.VIDEO_RECORD_RETURN_STATE, 0);
        if (intExtra == 100) {
            str2 = intent.getStringExtra("videoPath");
            if (str2 == null) {
                return;
            }
        } else if (intExtra == 101) {
            str2 = intent.getStringExtra(VideoConstant.PICTURE_PATH);
            if (str2 == null) {
                return;
            }
        } else {
            str2 = null;
        }
        OKLog.d("bundleicssdkservice", str3 + "---handleGroupVideo path:" + str2);
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName("JDDongDongMediaModule").setMethodName("handleGroupVideo").putIntParam("mediaType", intExtra).putStringParam("pin", str).putStringParam("path", str2);
            JDRouter.build(JdSdk.getInstance().getApplicationContext(), jDRouterUrlBuilder.build()).callBackListener(new g(this)).open();
        }
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public void handlePic(Intent intent, String str) {
        ArrayList parcelableArrayListExtra;
        OKLog.d("bundleicssdkservice", a + "---handlePic");
        if (intent == null || (parcelableArrayListExtra = intent.getParcelableArrayListExtra(AlbumConstant.SELECT_MEDIAS)) == null || parcelableArrayListExtra.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < parcelableArrayListExtra.size(); i2++) {
            LocalMedia localMedia = (LocalMedia) parcelableArrayListExtra.get(i2);
            if (localMedia != null) {
                MediaInfo mediaInfo = new MediaInfo();
                mediaInfo.pictureType = localMedia.getPictureType();
                mediaInfo.path = localMedia.getPath();
                mediaInfo.width = localMedia.getWidth();
                mediaInfo.height = localMedia.getHeight();
                arrayList.add(mediaInfo);
                OKLog.d("bundleicssdkservice", a + "---handlePic path: " + mediaInfo.path);
            }
        }
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName("JDDongDongMediaModule").setMethodName("handlePic").putStringParam("pin", str).putStringParam("mediaInfos", new Gson().toJson(arrayList));
            JDRouter.build(JdSdk.getInstance().getApplicationContext(), jDRouterUrlBuilder.build()).callBackListener(new d(this)).open();
        }
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public void handleVideo(Intent intent, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        String str3 = a;
        sb.append(str3);
        sb.append("---handleVideo");
        OKLog.d("bundleicssdkservice", sb.toString());
        int intExtra = intent.getIntExtra(VideoConstant.VIDEO_RECORD_RETURN_STATE, 0);
        if (intExtra == 100) {
            str2 = intent.getStringExtra("videoPath");
            if (str2 == null) {
                return;
            }
        } else if (intExtra == 101) {
            str2 = intent.getStringExtra(VideoConstant.PICTURE_PATH);
            if (str2 == null) {
                return;
            }
        } else {
            str2 = null;
        }
        OKLog.d("bundleicssdkservice", str3 + "---handleVideo path:" + str2);
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName("JDDongDongMediaModule").setMethodName("handleVideo").putIntParam("mediaType", intExtra).putStringParam("pin", str).putStringParam("path", str2);
            JDRouter.build(JdSdk.getInstance().getApplicationContext(), jDRouterUrlBuilder.build()).callBackListener(new f(this)).open();
        }
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public void playVideo(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_VIDEO_URL, str);
        bundle.putInt(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAY_TYPE, 2);
        bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAYER_TYPEID, jd.wjlogin_sdk.util.f.f19955e);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(VideoPlayerConstants.ACTIVITY_INTENT_PARAMS_PLAYER_EXTINFO, str2);
        }
        VideoPlayerDeepLinkHelper.startVideoPlayer(context, bundle);
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public void showAlbum(Activity activity, int i2) {
        OKLog.d("bundleicssdkservice", a + "---showAlbum");
        try {
            SmallWindowManager.getInstance().close();
        } catch (Exception unused) {
        }
        AlbumParam albumParam = new AlbumParam();
        albumParam.cameraOrVideoAction = 0;
        albumParam.loadCameraOrVideo = i2;
        albumParam.canSelectMediaCount = 9;
        albumParam.videoMinTime = "3";
        albumParam.videoMaxTime = "10";
        albumParam.videoEditorAction = 0;
        albumParam.needEditorPic = false;
        albumParam.selectedMedia = null;
        albumParam.showAnimatePic = false;
        ((AlbumBuilder) JDRouter.to(AlbumBuilder.class)).albumParam(albumParam).onCallBackListener(new a(this)).setRequestCode(1000).jump(activity);
    }

    @Override // com.jingdong.service.impl.IMMedia, com.jingdong.service.service.MediaService
    public void showRecordVideo(Activity activity, int i2) {
        OKLog.d("bundleicssdkservice", a + "---showRecordVideo");
        try {
            SmallWindowManager.getInstance().close();
        } catch (Exception unused) {
        }
        VideoParam videoParam = new VideoParam();
        videoParam.recordMaxTime = 10;
        videoParam.recordMinTime = 3;
        videoParam.recordFunctionControl = i2;
        videoParam.recordCurrentState = i2 == 2 ? 1 : 0;
        VideoEditorFinishUtil.getInstance().setListener(new b(this));
        ((VideoRecorderBuilder) JDRouter.to(VideoRecorderBuilder.class)).videoParam(videoParam).onCallBackListener(new c(this, activity)).requestCode(101).jump(activity);
        JDRouterMtaUtil.routerEnterMta(activity, activity.getClass().getName(), "JDImageEditorModule_show");
    }
}
