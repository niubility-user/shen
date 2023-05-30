package com.jingdong.common.frame;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

/* loaded from: classes.dex */
public interface IMainActivity {
    void finish();

    IMyActivity getCurrentMyActivity();

    Handler getHandler();

    Activity getThisActivity();

    boolean isMainStop();

    void removeAllRecords(boolean z);

    void startActivity(Intent intent);

    void validateCartIcon();

    void validateJdFaxianNewIcon(boolean z);

    void validateNewIcon();
}
