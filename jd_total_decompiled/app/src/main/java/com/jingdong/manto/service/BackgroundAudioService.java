package com.jingdong.manto.service;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.m.p0.d.a;

/* loaded from: classes16.dex */
public class BackgroundAudioService extends IntentService {
    public BackgroundAudioService() {
        super("Audio.BackgroundService");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0042, code lost:
        if (r2.equals("Next") == false) goto L14;
     */
    @Override // android.app.IntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onHandleIntent(@Nullable Intent intent) {
        String[] split;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (TextUtils.isEmpty(action) || (split = action.split(CartConstant.KEY_YB_INFO_LINK)) == null) {
            return;
        }
        char c2 = 2;
        if (split.length < 2) {
            return;
        }
        String str = split[0];
        String str2 = split[1];
        str.hashCode();
        str.hashCode();
        switch (str.hashCode()) {
            case -1209131241:
                if (str.equals("Previous")) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case -134623265:
                if (str.equals("PlayOrPause")) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case 2424595:
                break;
            case 65203672:
                if (str.equals("Close")) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        switch (c2) {
            case 0:
                a.e(str2);
                return;
            case 1:
                a.d(str2);
                return;
            case 2:
                a.c(str2);
                return;
            case 3:
                a.b(str2);
                return;
            default:
                return;
        }
    }
}
