package com.jingdong.manto.sdk.api;

import android.content.Intent;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.jingdong.manto.MantoActivityResult;
import com.jingdong.manto.jsapi.refact.rec.MantoVideoRecorderActivity;
import com.jingdong.manto.jsapi.refact.rec.VideoParam;
import com.jingdong.manto.m.f1.b;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.ArrayList;

@Keep
/* loaded from: classes16.dex */
public abstract class AbsChooseMedia implements IMantoSdkBase {
    public abstract void onChooseImage(MantoActivityResult mantoActivityResult, Intent intent, int i2);

    public abstract void onChooseMedia(MantoActivityResult mantoActivityResult, Intent intent, int i2);

    public abstract void onChooseVideo(MantoActivityResult mantoActivityResult, Intent intent, int i2);

    public abstract void onPreviewImages(@NonNull MantoActivityResult mantoActivityResult, ArrayList<String> arrayList, int i2);

    public void onRecordVideo(MantoActivityResult mantoActivityResult, Intent intent, int i2) {
        VideoParam videoParam = new VideoParam();
        videoParam.camera = "front".equals(intent.getStringExtra("manto_which_camera")) ? 1 : 0;
        videoParam.maxTime = intent.getIntExtra("manto_video_time_max", 60);
        videoParam.recordFilePath = intent.getStringExtra("manto_record_path");
        Intent intent2 = new Intent(mantoActivityResult.getActivity(), MantoVideoRecorderActivity.class);
        intent2.putExtra(MantoVideoRecorderActivity.VIDEO_PARAM, videoParam);
        mantoActivityResult.getActivity().startActivityForResult(intent2, i2);
    }

    public void onTakePhoto(MantoActivityResult mantoActivityResult, String str, int i2) {
        b.a(mantoActivityResult.getActivity(), str, i2);
    }
}
