package com.jingdong.service.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.jingdong.service.BaseService;
import com.jingdong.service.service.MediaService;

/* loaded from: classes10.dex */
public class IMMedia extends BaseService implements MediaService {
    private static final String TAG = "IMMedia";

    public int getOpenAlbumRequestCode() {
        return 0;
    }

    public int getVideoEditorRequestCode() {
        return 0;
    }

    public void handleGroupPic(Intent intent, String str) {
    }

    public void handleGroupVideo(Intent intent, String str) {
    }

    public void handlePic(Intent intent, String str) {
    }

    public void handleVideo(Intent intent, String str) {
    }

    public void playVideo(Context context, String str, String str2) {
    }

    public void showAlbum(Activity activity, int i2) {
    }

    public void showRecordVideo(Activity activity, int i2) {
    }
}
