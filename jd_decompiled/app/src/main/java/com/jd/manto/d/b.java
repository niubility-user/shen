package com.jd.manto.d;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.facebook.common.util.UriUtil;
import com.jd.manto.album.AlbumProxyActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkAlbumHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkVideoAndImageHelper;
import com.jingdong.common.unification.album.AlbumConstant;
import com.jingdong.common.unification.album.AlbumParam;
import com.jingdong.common.unification.album.LocalMedia;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoParam;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.sdk.api.AbsChooseMedia;
import com.jingdong.manto.ui.MantoTransportActivity;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes17.dex */
public final class b extends AbsChooseMedia {

    /* loaded from: classes17.dex */
    class a implements MantoTransportActivity.OnCreateActivityListener {
        final /* synthetic */ Intent a;
        final /* synthetic */ int b;

        a(b bVar, Intent intent, int i2) {
            this.a = intent;
            this.b = i2;
        }

        @Override // com.jingdong.manto.ui.MantoTransportActivity.OnCreateActivityListener
        public void onCreate(Activity activity) {
            Bundle bundle = new Bundle(1);
            VideoParam videoParam = new VideoParam();
            videoParam.recordMaxTime = this.a.getIntExtra("manto_video_time_max", 60);
            videoParam.recordMinTime = 3;
            videoParam.recordFunctionControl = 1;
            videoParam.editorFunctionControl = 0;
            bundle.putSerializable(VideoConstant.VIDEO_PARAM, videoParam);
            com.jingdong.sdk.deeplink.b.a().b(activity.getApplicationContext());
            DeepLinkVideoAndImageHelper.startVideoRecorderActivityForResult(activity, videoParam, this.b);
        }
    }

    /* renamed from: com.jd.manto.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    class C0184b implements MantoActivityResult.ResultCallback {
        final /* synthetic */ MantoActivityResult a;

        C0184b(b bVar, MantoActivityResult mantoActivityResult) {
            this.a = mantoActivityResult;
        }

        @Override // com.jingdong.manto.MantoActivityResult.ResultCallback
        public void onActivityResult(int i2, int i3, Intent intent) {
            ArrayList<String> arrayList = new ArrayList<>(1);
            if (intent != null) {
                arrayList.add(intent.getStringExtra("videoPath"));
            }
            if (this.a.getResultCallback() != null) {
                Intent intent2 = new Intent();
                intent2.putStringArrayListExtra("select_media_list", arrayList);
                this.a.getResultCallback().onActivityResult(i2, -1, intent2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class c implements MantoTransportActivity.OnCreateActivityListener {
        final /* synthetic */ AlbumParam a;
        final /* synthetic */ int b;

        c(b bVar, AlbumParam albumParam, int i2) {
            this.a = albumParam;
            this.b = i2;
        }

        @Override // com.jingdong.manto.ui.MantoTransportActivity.OnCreateActivityListener
        public void onCreate(Activity activity) {
            com.jingdong.sdk.deeplink.b.a().b(activity.getApplicationContext());
            DeepLinkAlbumHelper.startAlbumActivityForResult(activity, this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements MantoActivityResult.ResultCallback {
        final /* synthetic */ MantoActivityResult a;

        d(b bVar, MantoActivityResult mantoActivityResult) {
            this.a = mantoActivityResult;
        }

        @Override // com.jingdong.manto.MantoActivityResult.ResultCallback
        public void onActivityResult(int i2, int i3, Intent intent) {
            ArrayList parcelableArrayListExtra;
            ArrayList<String> arrayList = new ArrayList<>();
            if (intent != null && (parcelableArrayListExtra = intent.getParcelableArrayListExtra(AlbumConstant.SELECT_MEDIAS)) != null && !parcelableArrayListExtra.isEmpty()) {
                for (int i4 = 0; i4 < parcelableArrayListExtra.size(); i4++) {
                    arrayList.add(((LocalMedia) parcelableArrayListExtra.get(i4)).getPath());
                    ((LocalMedia) parcelableArrayListExtra.get(i4)).getPath();
                }
            }
            if (this.a.getResultCallback() != null) {
                Intent intent2 = new Intent();
                intent2.putStringArrayListExtra("select_media_list", arrayList);
                this.a.getResultCallback().onActivityResult(i2, -1, intent2);
            }
        }
    }

    @Override // com.jingdong.manto.sdk.api.AbsChooseMedia
    public void onChooseImage(MantoActivityResult mantoActivityResult, Intent intent, int i2) {
        onChooseMedia(mantoActivityResult, intent, i2);
    }

    @Override // com.jingdong.manto.sdk.api.AbsChooseMedia
    public void onChooseMedia(MantoActivityResult mantoActivityResult, Intent intent, int i2) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("manto_media_type", 1);
            int intExtra2 = intent.getIntExtra("manto_count", 1);
            boolean booleanExtra = intent.getBooleanExtra("manto_show_camera", true);
            int min = Math.min(intent.getIntExtra("manto_video_time_max", 60), 60);
            boolean booleanExtra2 = intent.getBooleanExtra("manto_isClip", false);
            intent.getStringExtra("manto_record_path");
            intent.getStringExtra("manto_which_camera");
            AlbumParam albumParam = new AlbumParam();
            albumParam.cameraOrVideoAction = 0;
            if (booleanExtra) {
                if (intExtra == 1) {
                    albumParam.cameraOrVideoAction = 2;
                } else if (intExtra == 2) {
                    albumParam.cameraOrVideoAction = 3;
                } else {
                    albumParam.cameraOrVideoAction = 1;
                }
            }
            albumParam.videoMinTime = "3";
            albumParam.videoMaxTime = String.valueOf(min);
            albumParam.videoEditorAction = 0;
            albumParam.needEditorPic = booleanExtra2;
            albumParam.canSelectMediaCount = intExtra2;
            if (intExtra != 2) {
                albumParam.cameraOrVideoAction = 2;
                albumParam.loadCameraOrVideo = 1;
            } else {
                albumParam.cameraOrVideoAction = 3;
                albumParam.loadCameraOrVideo = 2;
                albumParam.canSelectMediaCount = 1;
            }
            MantoTransportActivity.start(mantoActivityResult.getActivity(), new c(this, albumParam, i2), new d(this, mantoActivityResult));
        }
    }

    @Override // com.jingdong.manto.sdk.api.AbsChooseMedia
    public void onChooseVideo(MantoActivityResult mantoActivityResult, Intent intent, int i2) {
        onChooseMedia(mantoActivityResult, intent, i2);
    }

    @Override // com.jingdong.manto.sdk.api.AbsChooseMedia
    public void onPreviewImages(@NonNull MantoActivityResult mantoActivityResult, ArrayList<String> arrayList, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", i2);
        ArrayList<String> arrayList2 = new ArrayList<>();
        if (arrayList != null && arrayList.size() > 0) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Uri parse = Uri.parse(arrayList.get(i3));
                if (UriUtil.isNetworkUri(parse)) {
                    arrayList2.add(parse.toString());
                } else if (UriUtil.isLocalFileUri(parse)) {
                    arrayList2.add(Uri.fromFile(new File(arrayList.get(i3))).toString());
                } else if (new File(arrayList.get(i3)).exists()) {
                    arrayList2.add(Uri.fromFile(new File(arrayList.get(i3))).toString());
                } else {
                    arrayList2.add(parse.toString());
                }
            }
        }
        bundle.putStringArrayList("image_show_list_url", arrayList2);
        bundle.putInt("image_show_style", 102);
        AlbumProxyActivity.u(mantoActivityResult.getActivity(), bundle);
    }

    @Override // com.jingdong.manto.sdk.api.AbsChooseMedia
    public void onRecordVideo(MantoActivityResult mantoActivityResult, Intent intent, int i2) {
        MantoTransportActivity.start(mantoActivityResult.getActivity(), new a(this, intent, i2), new C0184b(this, mantoActivityResult));
    }

    @Override // com.jingdong.manto.sdk.api.AbsChooseMedia
    public void onTakePhoto(MantoActivityResult mantoActivityResult, String str, int i2) {
        super.onTakePhoto(mantoActivityResult, str, i2);
    }
}
