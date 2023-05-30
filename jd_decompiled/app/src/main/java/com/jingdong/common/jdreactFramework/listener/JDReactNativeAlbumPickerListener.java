package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Intent;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.deeplinkhelper.DeepLinkAlbumHelper;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.AresCommonUtils;
import com.jingdong.common.unification.album.AlbumConstant;
import com.jingdong.common.unification.album.AlbumParam;
import com.jingdong.common.unification.album.LocalMedia;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.OnCallBackListener;
import com.jingdong.common.unification.router.builderimpl.AlbumBuilder;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class JDReactNativeAlbumPickerListener implements NativeAlbumPickListener {
    public static final int PREVIEW_IMAGE_CODE = 2000;
    private List<LocalMedia> selectedMedias = new ArrayList();
    private JDCallback successCB;

    @Override // com.jingdong.common.jdreactFramework.listener.NativeAlbumPickListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        ArrayList parcelableArrayListExtra;
        if (i3 != -1 || i2 != 1000) {
            if (i3 != -1 || i2 != 2000 || intent == null || (parcelableArrayListExtra = intent.getParcelableArrayListExtra(AlbumConstant.PREVIEW_DATA)) == null) {
                return;
            }
            String jSONString = JDJSON.toJSONString(parcelableArrayListExtra);
            JDCallback jDCallback = this.successCB;
            if (jDCallback != null) {
                jDCallback.invoke(jSONString);
            }
        } else if (intent != null) {
            ArrayList parcelableArrayListExtra2 = intent.getParcelableArrayListExtra(AlbumConstant.SELECT_MEDIAS);
            this.selectedMedias = parcelableArrayListExtra2;
            if (parcelableArrayListExtra2 != null) {
                String jSONString2 = JDJSON.toJSONString(parcelableArrayListExtra2);
                JDCallback jDCallback2 = this.successCB;
                if (jDCallback2 != null) {
                    jDCallback2.invoke(jSONString2);
                }
            }
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeAlbumPickListener
    public void onImagePicked(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, Activity activity) {
        final JDCallback jDCallback3;
        int i2;
        AlbumParam albumParam;
        List<LocalMedia> parseArray;
        this.successCB = jDCallback;
        Activity currentMyActivity = activity == null ? JDReactHelper.newInstance().getCurrentMyActivity() : activity;
        if (currentMyActivity != null && hashMap != null) {
            try {
                albumParam = new AlbumParam();
                if (hashMap.containsKey("isUseSystemAlbum")) {
                    albumParam.isUseSystemAlbum = ((Boolean) hashMap.get("isUseSystemAlbum")).booleanValue();
                }
                if (hashMap.containsKey("isUsePreviewPage")) {
                    albumParam.isUsePreviewPage = ((Boolean) hashMap.get("isUsePreviewPage")).booleanValue();
                }
                if (hashMap.containsKey("source")) {
                    albumParam.source = (String) hashMap.get("source");
                }
                if (hashMap.containsKey(AlbumConstant.CAMERA_OR_VIDEO_ACTION)) {
                    albumParam.cameraOrVideoAction = (int) ((Double) hashMap.get(AlbumConstant.CAMERA_OR_VIDEO_ACTION)).doubleValue();
                }
                if (hashMap.containsKey(AlbumConstant.LOAD_CAMERA_OR_VIDEO)) {
                    albumParam.loadCameraOrVideo = (int) ((Double) hashMap.get(AlbumConstant.LOAD_CAMERA_OR_VIDEO)).doubleValue();
                }
                if (hashMap.containsKey("canSelectMediaCount")) {
                    albumParam.canSelectMediaCount = (int) ((Double) hashMap.get("canSelectMediaCount")).doubleValue();
                }
                if (hashMap.containsKey("videoEditorAction")) {
                    albumParam.videoEditorAction = (int) ((Double) hashMap.get("videoEditorAction")).doubleValue();
                }
                if (hashMap.containsKey("videoMinTime")) {
                    albumParam.videoMinTime = (String) hashMap.get("videoMinTime");
                }
                if (hashMap.containsKey("videoMaxTime")) {
                    albumParam.videoMaxTime = (String) hashMap.get("videoMaxTime");
                }
                if (hashMap.containsKey("needEditorPic")) {
                    albumParam.needEditorPic = ((Boolean) hashMap.get("needEditorPic")).booleanValue();
                }
                if (hashMap.containsKey("showAnimatePic")) {
                    albumParam.showAnimatePic = ((Boolean) hashMap.get("showAnimatePic")).booleanValue();
                }
                if (hashMap.containsKey("showFollowTake")) {
                    albumParam.showFollowTake = ((Boolean) hashMap.get("showFollowTake")).booleanValue();
                }
                if (hashMap.containsKey("savePhotoToAlbum")) {
                    albumParam.savePhotoToAlbum = ((Boolean) hashMap.get("savePhotoToAlbum")).booleanValue();
                }
                if (hashMap.containsKey("saveVideoToAlbum")) {
                    albumParam.saveVideoToAlbum = ((Boolean) hashMap.get("saveVideoToAlbum")).booleanValue();
                }
                if (hashMap.containsKey("selectedMedia") && (parseArray = JDJSON.parseArray((String) hashMap.get("selectedMedia"), LocalMedia.class)) != null) {
                    albumParam.selectedMedia = parseArray;
                }
                jDCallback3 = jDCallback2;
                i2 = 0;
            } catch (Exception unused) {
                jDCallback3 = jDCallback2;
                i2 = 0;
            }
            try {
                ((AlbumBuilder) JDRouter.to(AlbumBuilder.class)).albumParam(albumParam).onCallBackListener(new OnCallBackListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeAlbumPickerListener.1
                    @Override // com.jingdong.common.unification.router.OnCallBackListener
                    public void onComplete() {
                    }

                    @Override // com.jingdong.common.unification.router.OnCallBackListener
                    public void onError(int i3, String str) {
                        AresCommonUtils.invokeCallback(jDCallback3, new Object[0]);
                    }
                }).setRequestCode(1000).jump(currentMyActivity);
                return;
            } catch (Exception unused2) {
                jDCallback3.invoke(new Object[i2]);
                return;
            }
        }
        AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.jingdong.common.jdreactFramework.JDCallback] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.jingdong.common.jdreactFramework.JDCallback] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @Override // com.jingdong.common.jdreactFramework.listener.NativeAlbumPickListener
    public void previewImage(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2, Activity activity) {
        Activity currentMyActivity;
        String str;
        String str2;
        ?? r0 = hashMap;
        int i2 = jDCallback2;
        String str3 = "duration";
        String str4 = "path";
        this.successCB = jDCallback;
        if (activity == null) {
            try {
                currentMyActivity = JDReactHelper.newInstance().getCurrentMyActivity();
            } catch (Exception unused) {
                r0 = i2;
                i2 = 0;
                AresCommonUtils.invokeCallback(r0, new Object[i2]);
            }
        } else {
            currentMyActivity = activity;
        }
        try {
            if (currentMyActivity != null && r0 != 0) {
                boolean z = r0.containsKey("type") && ((String) r0.get("type")).equals("1");
                int intValue = r0.containsKey("index") ? Integer.valueOf((String) r0.get("index")).intValue() : 0;
                ArrayList arrayList = new ArrayList();
                try {
                    if (r0.containsKey(CartConstant.KEY_ITEMS)) {
                        ArrayList arrayList2 = (ArrayList) r0.get(CartConstant.KEY_ITEMS);
                        int i3 = 0;
                        i2 = i2;
                        while (i3 < arrayList2.size()) {
                            try {
                                HashMap hashMap2 = (HashMap) arrayList2.get(i3);
                                LocalMedia localMedia = new LocalMedia();
                                if (hashMap2.containsKey(str4)) {
                                    str = str4;
                                    localMedia.setPath((String) hashMap2.get(str4));
                                } else {
                                    str = str4;
                                }
                                if (hashMap2.containsKey("pictureType")) {
                                    localMedia.setPictureType((String) hashMap2.get("pictureType"));
                                }
                                if (hashMap2.containsKey("coverPath")) {
                                    localMedia.setCoverPath((String) hashMap2.get("coverPath"));
                                }
                                if (hashMap2.containsKey(str3)) {
                                    try {
                                        Double d = (Double) hashMap2.get(str3);
                                        str2 = str3;
                                        try {
                                            localMedia.setDuration(new Double(d.doubleValue()).longValue());
                                        } catch (Exception unused2) {
                                        }
                                    } catch (Exception unused3) {
                                    }
                                    arrayList.add(localMedia);
                                    i3++;
                                    i2 = jDCallback2;
                                    str3 = str2;
                                    str4 = str;
                                }
                                str2 = str3;
                                arrayList.add(localMedia);
                                i3++;
                                i2 = jDCallback2;
                                str3 = str2;
                                str4 = str;
                            } catch (Exception unused4) {
                                r0 = jDCallback2;
                                i2 = 0;
                                AresCommonUtils.invokeCallback(r0, new Object[i2]);
                            }
                        }
                        AlbumParam albumParam = new AlbumParam();
                        if (r0.containsKey("videoMaxTime")) {
                            albumParam.videoMaxTime = (String) r0.get("videoMaxTime");
                        }
                        if (r0.containsKey("videoMinTime")) {
                            albumParam.videoMaxTime = (String) r0.get("videoMinTime");
                        }
                        if (r0.containsKey("videoEditorAction")) {
                            albumParam.videoEditorAction = Integer.valueOf((String) r0.get("videoEditorAction")).intValue();
                        }
                        if (r0.containsKey("needEditorPic")) {
                            albumParam.needEditorPic = ((Boolean) r0.get("needEditorPic")).booleanValue();
                        }
                        if (intValue >= 0) {
                            DeepLinkAlbumHelper.startPreviewActivityByLocalMedia(currentMyActivity, arrayList, intValue, z, albumParam, 2000);
                            return;
                        } else {
                            AresCommonUtils.invokeCallback(jDCallback2, new Object[0]);
                            return;
                        }
                    }
                    AresCommonUtils.invokeCallback(i2, new Object[0]);
                    return;
                } catch (Exception unused5) {
                }
            }
            AresCommonUtils.invokeCallback(i2, new Object[0]);
        } catch (Exception unused6) {
            AresCommonUtils.invokeCallback(r0, new Object[i2]);
        }
    }
}
