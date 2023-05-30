package com.jingdong.common.babel.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.widget.custom.CustomIjkPlayer;
import com.jingdong.common.widget.custom.discovery.RotationSensorListener;
import com.jingdong.common.widget.video.AutoReportPlayer;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes5.dex */
public class BabelVideoActivity extends BaseActivity {
    private static final String PLAYTYPE = "5";
    private String articleId;
    private FrameLayout babel_video_player_parent;
    private String coverImageUrl;
    private String duration;
    private String eventParam;
    private boolean isForcePlay = false;
    private boolean isFullStatus;
    private boolean lowVersion;
    private AutoReportPlayer mPlayerProxy;
    private RotationSensorListener mRotationSensorListener;
    private String pageName;
    private String pageParam;
    private String videoId;
    private String videoUrl;

    public BabelVideoActivity() {
        this.lowVersion = Build.VERSION.SDK_INT <= 21;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeToLandscape() {
        this.isFullStatus = true;
        setRequestedOrientation(6);
        this.mPlayerProxy.setUiFullScreenState(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeToPortrait() {
        this.isFullStatus = false;
        setRequestedOrientation(1);
        this.mPlayerProxy.setUiFullScreenState(false);
    }

    private void handleIntent(Intent intent) {
        if (intent != null) {
            this.coverImageUrl = intent.getStringExtra("coverImageUrl");
            this.videoUrl = intent.getStringExtra("videoUrl");
            this.videoId = intent.getStringExtra("videoId");
            this.articleId = intent.getStringExtra("articleId");
            Log.d("BabelVideoActivity", "videoUrl:" + this.videoUrl);
            this.duration = intent.getStringExtra("duration");
            this.pageName = intent.getStringExtra(WebPerfManager.PAGE_NAME);
            this.pageParam = intent.getStringExtra("pageParam");
            this.eventParam = intent.getStringExtra("eventParam");
            this.isForcePlay = intent.getBooleanExtra("isForcePlay", false);
        }
    }

    private void initRotateListener() {
        this.mRotationSensorListener = new RotationSensorListener(this) { // from class: com.jingdong.common.babel.view.activity.BabelVideoActivity.3
            @Override // com.jingdong.common.widget.custom.discovery.RotationSensorListener
            public void onRotateChanged(int i2) {
                if (Log.D) {
                    Log.d("RotationSensorListener", "onOrientationChanged  lastOrientation " + this.lastOrientation + " orientation " + i2);
                }
                if (this.lastOrientation == i2 || !rotationSettingIsOpen(BabelVideoActivity.this)) {
                    return;
                }
                if (!BabelVideoActivity.this.isFullStatus || i2 != 1) {
                    if (BabelVideoActivity.this.mPlayerProxy == null || !BabelVideoActivity.this.mPlayerProxy.isPlaying()) {
                        return;
                    }
                    if (i2 == 0 || i2 == 8) {
                        BabelVideoActivity.this.changeToLandscape();
                        return;
                    }
                    return;
                }
                BabelVideoActivity.this.changeToPortrait();
            }
        };
    }

    private void initView() {
        this.babel_video_player_parent = (FrameLayout) findViewById(R.id.babel_video_player_parent);
        AutoReportPlayer autoReportPlayer = new AutoReportPlayer(this);
        this.mPlayerProxy = autoReportPlayer;
        this.babel_video_player_parent.addView(autoReportPlayer, 0, new FrameLayout.LayoutParams(-1, -1));
        this.mPlayerProxy.setAutoHideHeaderBar(findViewById(R.id.babel_video_close_btn));
        if (this.isForcePlay) {
            this.mPlayerProxy.setVideoPath(this.videoUrl, this.videoId, "5", 0L);
        } else {
            this.mPlayerProxy.setVideoPathNoAutoPlay(this.videoUrl, this.videoId, "5");
        }
        this.mPlayerProxy.setReportParams(this.videoId, "5", this.videoUrl, this.pageParam, "", this.articleId, "", this.pageName);
        this.mPlayerProxy.setAspectRatio(0);
        this.mPlayerProxy.setCouldAutoHide(true);
        if (this.lowVersion) {
            this.mPlayerProxy.hideFullBtn();
        } else {
            initRotateListener();
        }
        this.mPlayerProxy.setMtaListener(new CustomIjkPlayer.MtaListener() { // from class: com.jingdong.common.babel.view.activity.BabelVideoActivity.1
            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.MtaListener
            public void playBtnOnClick(boolean z) {
                if (z) {
                    return;
                }
                BabelVideoActivity babelVideoActivity = BabelVideoActivity.this;
                JDMtaUtils.onClick(babelVideoActivity, "Babel_VideoPause", babelVideoActivity.pageName, BabelVideoActivity.this.eventParam, BabelVideoActivity.this.pageParam);
            }

            @Override // com.jingdong.common.widget.custom.CustomIjkPlayer.MtaListener
            public void seekBarOnSeek(long j2) {
                BabelVideoActivity babelVideoActivity = BabelVideoActivity.this;
                JDMtaUtils.onClick(babelVideoActivity, "Babel_VideoDrag", babelVideoActivity.pageName, BabelVideoActivity.this.eventParam, BabelVideoActivity.this.pageParam);
            }
        });
        this.mPlayerProxy.setFullBtnOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.babel.view.activity.BabelVideoActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BabelVideoActivity.this.isFullStatus) {
                    BabelVideoActivity.this.changeToPortrait();
                } else {
                    BabelVideoActivity.this.changeToLandscape();
                }
            }
        });
    }

    public void onClickEvent(View view) {
        if (view.getId() != R.id.babel_video_close_btn) {
            return;
        }
        JDMtaUtils.onClick(this, "Babel_VideoClose", this.pageName, this.eventParam, this.pageParam);
        onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.statusBarTintEnable = true;
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        setContentView(R.layout.activity_babel_video_player);
        handleIntent(getIntent());
        initView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mPlayerProxy.release();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mPlayerProxy.pause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        RotationSensorListener rotationSensorListener = this.mRotationSensorListener;
        if (rotationSensorListener != null) {
            rotationSensorListener.enable();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        RotationSensorListener rotationSensorListener = this.mRotationSensorListener;
        if (rotationSensorListener != null) {
            rotationSensorListener.disable();
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }
}
