package com.jingdong.common.jdreactFramework.utils.video;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class VideoRecordView extends RelativeLayout implements SurfaceHolder.Callback, Camera.AutoFocusCallback {
    private static final float FocusAreaXRadius = 100.0f;
    private static final float FocusAreaYRadius = 80.0f;
    private static final String TAG = VideoRecordActivity.TAG;
    private boolean isBackCamera;
    private Camera mCamera;
    private int mCameraId;
    private Context mContext;
    private Rect mFocusArea;
    private FocusAreaView mFocusAreaView;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private int screenOrientation;
    private Point videoSize;

    public VideoRecordView(Context context) {
        super(context);
        this.mCameraId = -1;
        this.isBackCamera = true;
        this.screenOrientation = 0;
        this.videoSize = null;
        this.mFocusArea = new Rect();
        init(context);
    }

    private void areaFocus() {
        Camera.Parameters parameters;
        if (OKLog.D) {
            OKLog.d(TAG, "areaFocus");
        }
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                parameters = camera.getParameters();
            } catch (Exception unused) {
                parameters = null;
            }
            if (parameters == null) {
                return;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "parameters.getMaxNumFocusAreas() : " + parameters.getMaxNumFocusAreas());
            }
            Rect translatedFocusArea = getTranslatedFocusArea();
            if (parameters.getMaxNumFocusAreas() <= 0 || translatedFocusArea == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new Camera.Area(translatedFocusArea, 1000));
            parameters.setFocusAreas(arrayList);
            this.mCamera.setParameters(parameters);
            focusCamera();
        }
    }

    private int findCameraByFacing(int i2) {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i3 = 0; i3 < numberOfCameras; i3++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i3, cameraInfo);
            if (cameraInfo.facing == i2) {
                return i3;
            }
        }
        return -1;
    }

    private Camera.Size findPreviewSize(float f2, int i2, List<Camera.Size> list) {
        Camera.Size size = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        int i3 = (i2 * 3) / 2;
        int i4 = i2 / 2;
        Camera.Size size2 = null;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        for (Camera.Size size3 : list) {
            float abs = Math.abs(((size3.height + 0.0f) / size3.width) - f2);
            int i5 = size3.height;
            if (i5 > i3 || i5 <= i4) {
                if (abs < f3) {
                    size2 = size3;
                    f3 = abs;
                }
            } else if (abs < f4) {
                size = size3;
                f4 = abs;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "find test preview size:[" + size3.width + DYConstants.DY_REGEX_COMMA + size3.height + "]");
            }
        }
        if (OKLog.D) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("find best preview size:");
            sb.append(size == null ? "0 null" : "[" + size.width + DYConstants.DY_REGEX_COMMA + size.height + "]");
            OKLog.d(str, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("find best preview size:");
            sb2.append(size == null ? "1 null" : "[" + size.width + DYConstants.DY_REGEX_COMMA + size.height + "]");
            OKLog.d(str, sb2.toString());
        }
        return size != null ? size : size2;
    }

    private Camera.Size findVideoSize(float f2, List<Camera.Size> list) {
        Camera.Size size = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Camera.Size size2 = null;
        float f3 = Float.MAX_VALUE;
        float f4 = Float.MAX_VALUE;
        for (Camera.Size size3 : list) {
            float abs = Math.abs(((size3.height + 0.0f) / size3.width) - f2);
            int i2 = size3.height;
            if (i2 > 960 || i2 < 480) {
                if (i2 <= 1080 && abs < f3) {
                    size2 = size3;
                    f3 = abs;
                }
            } else if (abs < f4) {
                size = size3;
                f4 = abs;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "find test video size:[" + size3.width + DYConstants.DY_REGEX_COMMA + size3.height + "]");
            }
        }
        if (OKLog.D) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("find best video size:");
            sb.append(size == null ? "0 null" : "[" + size.width + DYConstants.DY_REGEX_COMMA + size.height + "]");
            OKLog.d(str, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("find best video size:");
            sb2.append(size == null ? "1 null" : "[" + size.width + DYConstants.DY_REGEX_COMMA + size.height + "]");
            OKLog.d(str, sb2.toString());
        }
        return size != null ? size : size2;
    }

    private String getAutoFocusMode(Camera.Parameters parameters) {
        if (parameters != null) {
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            if ((BaseInfo.getDeviceModel().startsWith("GT-I950") || BaseInfo.getDeviceModel().endsWith("SCH-I959") || BaseInfo.getDeviceModel().endsWith("MEIZU MX3")) && isSupported(supportedFocusModes, "continuous-picture")) {
                return "continuous-picture";
            }
            if (isSupported(supportedFocusModes, "continuous-video")) {
                return "continuous-video";
            }
            if (isSupported(supportedFocusModes, "auto")) {
                return "auto";
            }
            return null;
        }
        return null;
    }

    private Rect getTranslatedFocusArea() {
        if (this.mFocusArea == null) {
            return null;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        Rect rect = this.mFocusArea;
        int i2 = ((rect.left * 2000) / measuredWidth) - 1000;
        int i3 = ((rect.top * 2000) / measuredHeight) - 1000;
        int i4 = ((rect.right * 2000) / measuredWidth) - 1000;
        int i5 = ((rect.bottom * 2000) / measuredHeight) - 1000;
        if (i2 < -1000) {
            i2 = -1000;
        }
        if (i3 < -1000) {
            i3 = -1000;
        }
        if (i4 > 1000) {
            i4 = 1000;
        }
        Rect rect2 = new Rect(i2, i3, i4, i5 <= 1000 ? i5 : 1000);
        if (OKLog.D) {
            OKLog.d(TAG, "getTranslatedFocusArea:" + rect2);
        }
        return rect2;
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.jdreact_video_record_layout, this);
        this.mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        this.mFocusAreaView = (FocusAreaView) findViewById(R.id.focus_area_view);
        SurfaceHolder holder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(this);
    }

    private void initCamera() {
        openCamera();
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                initCameraParams(camera.getParameters());
                this.mCamera.setDisplayOrientation(90);
                this.mCamera.setPreviewDisplay(this.mSurfaceHolder);
                this.mCamera.startPreview();
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    private void initCameraParams(Camera.Parameters parameters) {
        if (OKLog.D) {
            OKLog.d(TAG, "initCameraParams");
        }
        if (parameters == null) {
            return;
        }
        parameters.set(MBaseKeyNames.KEY_ORIENTATION, this.screenOrientation == 0 ? "portrait" : "landscape");
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (OKLog.D) {
            OKLog.d(TAG, "VideoRecordView width:" + measuredWidth + "   height:" + measuredHeight);
        }
        if (measuredHeight == 0 || measuredWidth == 0) {
            Point point2 = new Point();
            ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getSize(point2);
            int i2 = point2.y;
            int i3 = point2.x;
            measuredHeight = i2;
            measuredWidth = i3;
        }
        float f2 = (measuredWidth + 0.0f) / measuredHeight;
        Camera.Size findPreviewSize = findPreviewSize(f2, measuredWidth, parameters.getSupportedPreviewSizes());
        if (findPreviewSize != null) {
            parameters.setPreviewSize(findPreviewSize.width, findPreviewSize.height);
        }
        Camera.Size findVideoSize = findVideoSize(f2, parameters.getSupportedVideoSizes());
        if (findVideoSize != null) {
            Point point3 = new Point();
            this.videoSize = point3;
            point3.x = findVideoSize.width;
            point3.y = findVideoSize.height;
        }
        String autoFocusMode = getAutoFocusMode(parameters);
        if (autoFocusMode != null) {
            parameters.setFocusMode(autoFocusMode);
        }
        this.mCamera.setParameters(parameters);
    }

    private boolean isSupported(List<String> list, String str) {
        return list != null && list.contains(str);
    }

    private void openCamera() {
        if (this.mCamera != null) {
            freeCamera();
        }
        try {
            this.mCamera = Camera.open(this.mCameraId);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public void changeCamera() {
        int findCameraByFacing;
        if (this.isBackCamera) {
            findCameraByFacing = findCameraByFacing(1);
            if (findCameraByFacing == -1) {
                ToastUtils.showToast("\u4e0d\u5b58\u5728\u524d\u7f6e\u6444\u50cf\u5934");
                return;
            }
        } else {
            findCameraByFacing = findCameraByFacing(0);
            if (findCameraByFacing == -1) {
                ToastUtils.showToast("\u8be5\u8bbe\u5907\u65e0\u6444\u50cf\u5934");
                return;
            }
        }
        this.isBackCamera = true ^ this.isBackCamera;
        this.mCameraId = findCameraByFacing;
        initCamera();
    }

    public void focusCamera() {
        if (OKLog.D) {
            OKLog.d(TAG, "focusCamera");
        }
        try {
            Camera camera = this.mCamera;
            if (camera != null) {
                camera.cancelAutoFocus();
                this.mCamera.autoFocus(this);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "autofocus get exception" + e2.getMessage());
            }
            OKLog.e(TAG, e2);
        }
    }

    public void freeCamera() {
        if (OKLog.D) {
            OKLog.d(TAG, "freeCamera");
        }
        try {
            try {
                Camera camera = this.mCamera;
                if (camera != null) {
                    camera.setPreviewCallback(null);
                    this.mCamera.stopPreview();
                    this.mCamera.release();
                    this.mCamera = null;
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        } finally {
            this.mCamera = null;
        }
    }

    public Camera getCamera() {
        return this.mCamera;
    }

    public int getCameraId() {
        return this.mCameraId;
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.mSurfaceHolder;
    }

    public Point getVideoSize() {
        return this.videoSize;
    }

    public boolean isBackCamera() {
        return this.isBackCamera;
    }

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean z, Camera camera) {
        if (OKLog.D) {
            OKLog.d(TAG, "onAutoFocus:" + z);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int i2 = (int) (x - 100.0f);
            int i3 = (int) (y - FocusAreaYRadius);
            int i4 = (int) (x + 100.0f);
            int i5 = (int) (y + FocusAreaYRadius);
            if (i2 < 0) {
                i2 = 0;
            }
            if (i3 < 0) {
                i3 = 0;
            }
            if (i4 > getMeasuredWidth()) {
                i4 = getMeasuredWidth();
            }
            if (i5 > getMeasuredHeight()) {
                i5 = getMeasuredHeight();
            }
            this.mFocusArea.set(i2, i3, i4, i5);
            if (OKLog.D) {
                OKLog.d(TAG, "touch action area:" + this.mFocusArea);
            }
            this.mFocusAreaView.showFocusArea(this.mFocusArea);
            areaFocus();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        if (OKLog.D) {
            OKLog.d(TAG, "surfaceChanged");
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (OKLog.D) {
            OKLog.d(TAG, "surfaceCreated");
        }
        if (this.mCameraId == -1) {
            int findCameraByFacing = findCameraByFacing(0);
            this.mCameraId = findCameraByFacing;
            this.isBackCamera = true;
            if (findCameraByFacing == -1) {
                int findCameraByFacing2 = findCameraByFacing(1);
                this.mCameraId = findCameraByFacing2;
                this.isBackCamera = false;
                if (findCameraByFacing2 == -1) {
                    ToastUtils.shortToast("\u8be5\u8bbe\u5907\u65e0\u6444\u50cf\u5934");
                    return;
                }
            }
        }
        initCamera();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (OKLog.D) {
            OKLog.d(TAG, "surfaceDestroyed");
        }
        freeCamera();
    }

    public VideoRecordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCameraId = -1;
        this.isBackCamera = true;
        this.screenOrientation = 0;
        this.videoSize = null;
        this.mFocusArea = new Rect();
        init(context);
    }

    public VideoRecordView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCameraId = -1;
        this.isBackCamera = true;
        this.screenOrientation = 0;
        this.videoSize = null;
        this.mFocusArea = new Rect();
        init(context);
    }
}
