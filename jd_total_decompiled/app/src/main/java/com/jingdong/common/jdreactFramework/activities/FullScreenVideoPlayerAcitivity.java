package com.jingdong.common.jdreactFramework.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.widget.custom.CustomIjkPlayer;
import com.jingdong.common.widget.video.RotationSensorListener;

/* loaded from: classes5.dex */
public class FullScreenVideoPlayerAcitivity extends BaseActivity {
    public static final String EXTRA_SHOW_CLOSE_BTN = "show_close";
    private CustomIjkPlayer customIjkPlayer;
    private RotationSensorListener mRotationSensorListener;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTintEnable = false;
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        this.customIjkPlayer = new CustomIjkPlayer(this);
        setContentView(R.layout.jdreact_activity_fullscreen_video_player);
        this.customIjkPlayer.setVideoPath(getIntent().getStringExtra("url"));
        this.customIjkPlayer.hideFullBtn();
        RotationSensorListener rotationSensorListener = new RotationSensorListener(this) { // from class: com.jingdong.common.jdreactFramework.activities.FullScreenVideoPlayerAcitivity.1
            @Override // com.jingdong.common.widget.video.RotationSensorListener
            public void onRotateChanged(int i2) {
                if (this.lastOrientation == i2 || !rotationSettingIsOpen(FullScreenVideoPlayerAcitivity.this)) {
                    return;
                }
                if (i2 == 9 || i2 == 1) {
                    FullScreenVideoPlayerAcitivity.this.setRequestedOrientation(1);
                } else if (i2 == 8 || i2 == 0) {
                    FullScreenVideoPlayerAcitivity.this.setRequestedOrientation(0);
                }
            }
        };
        this.mRotationSensorListener = rotationSensorListener;
        rotationSensorListener.enable();
        ((RelativeLayout) findViewById(R.id.root_view)).addView(this.customIjkPlayer, 0);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.close);
        relativeLayout.setVisibility(getIntent().getBooleanExtra(EXTRA_SHOW_CLOSE_BTN, false) ? 0 : 8);
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.FullScreenVideoPlayerAcitivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FullScreenVideoPlayerAcitivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.customIjkPlayer.suspend();
        this.customIjkPlayer.releaseInThread();
        this.mRotationSensorListener.disable();
    }
}
