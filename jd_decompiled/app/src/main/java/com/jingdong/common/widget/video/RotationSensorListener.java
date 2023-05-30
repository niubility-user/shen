package com.jingdong.common.widget.video;

import android.content.Context;
import android.provider.Settings;
import android.view.OrientationEventListener;

/* loaded from: classes12.dex */
public abstract class RotationSensorListener extends OrientationEventListener {
    protected int lastOrientation;

    public RotationSensorListener(Context context) {
        super(context);
        this.lastOrientation = -1;
    }

    private void noticeRotateChanged(int i2) {
        onRotateChanged(i2);
        this.lastOrientation = i2;
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i2) {
        if (i2 >= 60 && i2 <= 120) {
            noticeRotateChanged(8);
        } else if (i2 > 150 && i2 < 210) {
            noticeRotateChanged(9);
        } else if (i2 > 240 && i2 < 300) {
            noticeRotateChanged(0);
        } else if ((i2 <= 330 || i2 >= 360) && (i2 <= 0 || i2 >= 30)) {
        } else {
            noticeRotateChanged(1);
        }
    }

    public abstract void onRotateChanged(int i2);

    public boolean rotationSettingIsOpen(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation", 0) == 1;
    }
}
