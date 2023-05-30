package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.unification.album.AlbumConstant;
import com.jingdong.common.unification.album.AlbumParam;
import com.jingdong.common.unification.album.LocalMedia;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class DeepLinkAlbumHelper {
    public static void startAlbumActivityForResult(Activity activity, AlbumParam albumParam, int i2) {
        DeepLinkUri.Builder host = new DeepLinkUri.Builder().scheme("jingdong").host("album_activity");
        Bundle bundle = new Bundle();
        bundle.putParcelable(AlbumConstant.ALBUM_PARAM, albumParam);
        DeepLinkDispatch.startActivityForResult(activity, host.toString(), bundle, i2);
    }

    public static void startPreviewActivityByLocalMedia(Activity activity, ArrayList<LocalMedia> arrayList, int i2, boolean z, int i3) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(AlbumConstant.PREVIEW_DATA, arrayList);
        bundle.putBoolean(AlbumConstant.PRIVIEW_HAS_DELETE, z);
        bundle.putInt("position", i2);
        DeepLinkCommonHelper.startActivityForResult(activity, "un_media_preview_activity", bundle, i3);
    }

    public static void startPreviewActivityByPath(Activity activity, ArrayList<String> arrayList, int i2, boolean z, int i3) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(AlbumConstant.PREVIEW_DATA_PATH, arrayList);
        bundle.putBoolean(AlbumConstant.PRIVIEW_HAS_DELETE, z);
        bundle.putInt("position", i2);
        DeepLinkCommonHelper.startActivityForResult(activity, "un_media_preview_activity", bundle, i3);
    }

    public static void startPreviewActivityByLocalMedia(Activity activity, ArrayList<LocalMedia> arrayList, int i2, boolean z, AlbumParam albumParam, int i3) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(AlbumConstant.PREVIEW_DATA, arrayList);
        bundle.putBoolean(AlbumConstant.PRIVIEW_HAS_DELETE, z);
        bundle.putInt("position", i2);
        if (albumParam != null) {
            bundle.putInt(AlbumConstant.VIDEO_EDITOR_ACTION, albumParam.videoEditorAction);
            bundle.putBoolean("needEditorPic", albumParam.needEditorPic);
            bundle.putString(AlbumConstant.VIDEO_MIN_DURATION, albumParam.videoMinTime);
            bundle.putString(AlbumConstant.VIDEO_MAX_DURATION, albumParam.videoMaxTime);
        }
        DeepLinkCommonHelper.startActivityForResult(activity, "un_media_preview_activity", bundle, i3);
    }
}
