package com.jingdong.service.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes10.dex */
public interface MediaService {
    int getOpenAlbumRequestCode();

    int getVideoEditorRequestCode();

    void handleGroupPic(Intent intent, String str);

    void handleGroupVideo(Intent intent, String str);

    void handlePic(Intent intent, String str);

    void handleVideo(Intent intent, String str);

    void playVideo(Context context, String str, String str2);

    void showAlbum(Activity activity, int i2);

    void showRecordVideo(Activity activity, int i2);
}
