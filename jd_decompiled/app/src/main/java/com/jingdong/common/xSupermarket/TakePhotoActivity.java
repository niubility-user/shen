package com.jingdong.common.xSupermarket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.util.List;

/* loaded from: classes12.dex */
public class TakePhotoActivity extends BaseActivity implements SurfaceHolder.Callback, View.OnClickListener {
    private Camera camera;
    private ImageView img_confirm;
    private int indexOfCamera;
    private int limitSize;
    private RelativeLayout rl_photo_confirm;
    private RelativeLayout rl_take_photo;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private ImageCompressUtils mImageCompressUtils = ImageCompressUtils.getInstance();
    private int cameraPosition = 1;
    private Camera.PictureCallback callback = new Camera.PictureCallback() { // from class: com.jingdong.common.xSupermarket.TakePhotoActivity.1
        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            Bitmap compressAsBitmap = TakePhotoActivity.this.mImageCompressUtils.compressAsBitmap(bArr, TakePhotoActivity.this.cameraPosition);
            TakePhotoActivity.this.mImageCompressUtils.compressAsImage(compressAsBitmap, TakePhotoActivity.this.limitSize);
            if (compressAsBitmap == null) {
                ToastUtils.shortToast(TakePhotoActivity.this, "\u56fe\u7247\u4e0d\u6e05\u6670,\u8bf7\u91cd\u65b0\u9009\u62e9");
            }
            if (camera != null) {
                camera.stopPreview();
            }
            TakePhotoActivity.this.rl_take_photo.setVisibility(8);
            TakePhotoActivity.this.rl_photo_confirm.setVisibility(0);
            TakePhotoActivity.this.img_confirm.setImageBitmap(compressAsBitmap);
        }
    };

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.limitSize = intent.getIntExtra("limitSize", 200);
        }
    }

    private void initView() {
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.pre_view);
        this.surfaceView = surfaceView;
        if (surfaceView != null) {
            SurfaceHolder holder = surfaceView.getHolder();
            this.surfaceHolder = holder;
            holder.addCallback(this);
        }
        this.rl_photo_confirm = (RelativeLayout) findViewById(R.id.rl_photo_confirm);
        this.rl_take_photo = (RelativeLayout) findViewById(R.id.rl_take_photo);
        this.img_confirm = (ImageView) findViewById(R.id.img_comfirm);
        findViewById(R.id.btn_take_photo).setOnClickListener(this);
        findViewById(R.id.su).setOnClickListener(this);
        findViewById(R.id.img_switch).setOnClickListener(this);
        findViewById(R.id.tv_retake).setOnClickListener(this);
        findViewById(R.id.tv_upload).setOnClickListener(this);
    }

    private void startPreviewDisplay(SurfaceHolder surfaceHolder, int i2) {
        try {
            Camera camera = this.camera;
            if (camera != null) {
                camera.setPreviewDisplay(surfaceHolder);
                setCameraDisplayOrientation(i2, this.camera);
                this.camera.startPreview();
                this.camera.cancelAutoFocus();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void stopPreviewDisplay() {
        try {
            Camera camera = this.camera;
            if (camera != null) {
                camera.stopPreview();
                this.camera.release();
                this.camera = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void switchCamera() {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            this.indexOfCamera = i2;
            Camera.getCameraInfo(i2, cameraInfo);
            if (this.cameraPosition == 1) {
                if (cameraInfo.facing == 0) {
                    stopPreviewDisplay();
                    Camera open = Camera.open(i2);
                    this.camera = open;
                    setCamera(open);
                    startPreviewDisplay(this.surfaceHolder, i2);
                    this.cameraPosition = i2;
                    return;
                }
            } else if (cameraInfo.facing == 1) {
                stopPreviewDisplay();
                Camera open2 = Camera.open(i2);
                this.camera = open2;
                setCamera(open2);
                startPreviewDisplay(this.surfaceHolder, i2);
                this.cameraPosition = i2;
                return;
            }
        }
    }

    private void takePhoto() {
        try {
            Camera camera = this.camera;
            if (camera != null) {
                camera.takePicture(null, null, this.callback);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_take_photo) {
            takePhoto();
        } else if (id == R.id.su) {
            finish();
        } else if (id == R.id.img_switch) {
            switchCamera();
        } else if (id != R.id.tv_retake) {
            if (id == R.id.tv_upload) {
                JDMtaUtils.onClickWithPageId(this, "Supermarket_Camera_Usephoto", getClass().getName(), "", "Supermarket_Camera");
                setResult(-1);
                finish();
            }
        } else {
            JDMtaUtils.onClickWithPageId(this, "Supermarket_Camera_ReTake", getClass().getName(), "", "Supermarket_Camera");
            this.rl_take_photo.setVisibility(0);
            this.rl_photo_confirm.setVisibility(8);
            Camera camera = this.camera;
            if (camera != null) {
                camera.startPreview();
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activty_takephoto);
        getWindow().addFlags(128);
        initView();
        initData();
        setPageId("Supermarket_Camera");
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Camera camera = this.camera;
        if (camera != null) {
            camera.release();
            this.camera = null;
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        stopPreviewDisplay();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            int i2 = 1;
            if (this.cameraPosition != 1) {
                i2 = 0;
            }
            setCamera(Camera.open(i2));
            startPreviewDisplay(this.surfaceHolder, this.cameraPosition);
        } catch (Exception e2) {
            e2.printStackTrace();
            ToastUtils.showToast(this, getString(R.string.please_check_camera_permission));
            finish();
        }
    }

    public void setCamera(Camera camera) {
        try {
            if (camera == null) {
                ToastUtil.showToast(this, "\u76f8\u673a\u65e0\u6cd5\u6253\u5f00");
                return;
            }
            this.camera = camera;
            Camera.Parameters parameters = camera.getParameters();
            if (parameters.getSupportedFocusModes().contains("continuous-picture")) {
                parameters.setFocusMode("continuous-picture");
            } else {
                parameters.setFocusMode("auto");
            }
            List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            if (supportedPreviewSizes != null && supportedPreviewSizes.size() > 1) {
                if (supportedPreviewSizes.get(0).height < supportedPreviewSizes.get(1).height) {
                    parameters.setPreviewSize(supportedPreviewSizes.get(supportedPreviewSizes.size() - 1).width, supportedPreviewSizes.get(supportedPreviewSizes.size() - 1).height);
                } else {
                    parameters.setPreviewSize(supportedPreviewSizes.get(0).width, supportedPreviewSizes.get(0).height);
                }
            }
            if (supportedPictureSizes != null && supportedPictureSizes.size() > 0) {
                int i2 = supportedPictureSizes.get(0).height;
                int i3 = supportedPictureSizes.get(0).width;
                for (int i4 = 0; i4 < supportedPictureSizes.size(); i4++) {
                    int i5 = supportedPictureSizes.get(i4).width;
                    int i6 = supportedPictureSizes.get(i4).height;
                    if (i3 < i5) {
                        i3 = i5;
                        i2 = i6;
                    }
                }
                parameters.setPictureSize(i3, i2);
            }
            camera.setParameters(parameters);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setCameraDisplayOrientation(int i2, Camera camera) {
        int i3;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i2, cameraInfo);
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int i4 = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i4 = 90;
            } else if (rotation == 2) {
                i4 = 180;
            } else if (rotation == 3) {
                i4 = 270;
            }
        }
        if (cameraInfo.facing == 1) {
            i3 = (360 - ((cameraInfo.orientation + i4) % R2.attr.additionBottom)) % R2.attr.additionBottom;
        } else {
            i3 = ((cameraInfo.orientation - i4) + R2.attr.additionBottom) % R2.attr.additionBottom;
        }
        camera.setDisplayOrientation(i3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        startPreviewDisplay(surfaceHolder, 1);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stopPreviewDisplay();
    }
}
