package com.jingdong.common.web.javainterface.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.web.R;
import com.jingdong.common.web.managers.JSVoiceManager;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.MediaUtils;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.widget.CameraView;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.lang.ref.SoftReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class WebMediaJSUtils {
    private static long cameraOperTime;

    /* loaded from: classes12.dex */
    public static class BitmapRunnable implements Runnable {
        SoftReference<Bitmap> bitmapSoftReference;
        SoftReference<IWebUiBinder> jdWebViewSoftReference;
        String jsCallBack;
        int quality;

        @Override // java.lang.Runnable
        public void run() {
            if (this.bitmapSoftReference.get() != null) {
                if (this.jdWebViewSoftReference.get() != null && this.jdWebViewSoftReference.get().getJdWebView() != null) {
                    WebUtils.sendJSONStr2M(this.jdWebViewSoftReference.get(), this.jsCallBack, WebUtils.stringfyJSonData("0", MediaUtils.bitmap2Base64Str(this.bitmapSoftReference.get(), this.quality), ""));
                    return;
                }
                try {
                    this.bitmapSoftReference.get().recycle();
                    this.bitmapSoftReference.clear();
                } catch (Exception unused) {
                }
                WebUtils.sendJSONStr2M(this.jdWebViewSoftReference.get(), this.jsCallBack, WebUtils.stringfyJSonData("-1"));
            }
        }

        private BitmapRunnable(IWebUiBinder iWebUiBinder, Bitmap bitmap, String str, int i2) {
            this.quality = 50;
            this.jdWebViewSoftReference = new SoftReference<>(iWebUiBinder);
            this.bitmapSoftReference = new SoftReference<>(bitmap);
            this.jsCallBack = str;
            if (20 <= i2 && 90 >= i2) {
                this.quality = i2;
            } else {
                this.quality = 50;
            }
        }
    }

    public static void closeCamera(IWebUiBinder iWebUiBinder) {
        ViewGroup viewGroup;
        if (!isAvaliable(iWebUiBinder) || (viewGroup = (ViewGroup) iWebUiBinder.getBaseActivity().findViewById(R.id.root_layout)) == null) {
            return;
        }
        MediaUtils.removeCameraPreView(viewGroup);
    }

    public static void closeRecoder(final IWebUiBinder iWebUiBinder, final String str) {
        JSVoiceManager.getInstance().stop(new JSVoiceManager.Callback() { // from class: com.jingdong.common.web.javainterface.impl.WebMediaJSUtils.4
            @Override // com.jingdong.common.web.managers.JSVoiceManager.Callback
            public void callback(String str2, String str3) {
                if ("0".equals(str2)) {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("0", str3, ""));
                } else {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1"));
                }
            }
        });
    }

    public static void initVoiceSdk(final IWebUiBinder iWebUiBinder, final String str) {
        if (isAvaliable(iWebUiBinder)) {
            JSVoiceManager.getInstance().initSDK(new JSVoiceManager.Callback() { // from class: com.jingdong.common.web.javainterface.impl.WebMediaJSUtils.5
                @Override // com.jingdong.common.web.managers.JSVoiceManager.Callback
                public void callback(String str2, String str3) {
                    if ("0".equals(str2)) {
                        WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("0"));
                    } else {
                        WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1", "", str3));
                    }
                }
            });
        }
    }

    private static boolean isAvaliable(IWebUiBinder iWebUiBinder) {
        return (iWebUiBinder == null || iWebUiBinder.getBaseActivity() == null || iWebUiBinder.getJdWebView() == null) ? false : true;
    }

    public static void requestCameraPermissionAndShow(final IWebUiBinder iWebUiBinder, final String str, final String str2, final boolean z, String str3) {
        if (System.currentTimeMillis() - cameraOperTime < 300) {
            return;
        }
        cameraOperTime = System.currentTimeMillis();
        Bundle generateBundle = PermissionHelper.generateBundle("webview", WebMediaJSUtils.class.getSimpleName(), "showCamera");
        if (PermissionHelper.hasPermission(generateBundle, "android.permission.CAMERA")) {
            showCamera(iWebUiBinder, str, str2, z);
        } else {
            if (TextUtils.isEmpty(str3)) {
                str3 = "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u6444\u50cf\u5934\u62cd\u6444\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u80fd\u6b63\u5e38\u4f7f\u7528\u6444\u50cf\u5934\u8fdb\u884c\u62cd\u6444\u9884\u89c8";
            }
            PermissionHelper.requestPermission(iWebUiBinder.getBaseActivity(), generateBundle, "android.permission.CAMERA", new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.web.javainterface.impl.WebMediaJSUtils.1
                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onCanceled() {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1"));
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onDenied() {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1"));
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onGranted() {
                    WebMediaJSUtils.showCamera(iWebUiBinder, str, str2, z);
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onIgnored() {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1"));
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onOpenSetting() {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("1"));
                }
            }, "\u76f8\u673a", str3);
        }
        WebUnifiedMtaUtil.permissionReport(iWebUiBinder, "WebMediaJSUtils-requestCameraPermissionAndShow");
    }

    public static void requestRecordPermissionAndRecord(final IWebUiBinder iWebUiBinder, final String str, final int i2, String str2) {
        Bundle generateBundle = PermissionHelper.generateBundle("webview", WebMediaJSUtils.class.getSimpleName(), "showCamera");
        if (!PermissionHelper.hasPermission(generateBundle, "android.permission.RECORD_AUDIO")) {
            if (TextUtils.isEmpty(str2)) {
                str2 = "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u9ea6\u514b\u98ce\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u80fd\u6b63\u5e38\u4f7f\u7528\u8bed\u97f3\u8f6c\u6587\u5b57\u670d\u52a1";
            }
            PermissionHelper.PermissionResultCallBack permissionResultCallBack = new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.web.javainterface.impl.WebMediaJSUtils.2
                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onCanceled() {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1"));
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onDenied() {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1"));
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onGranted() {
                    WebMediaJSUtils.voice2Str(iWebUiBinder, str, i2);
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onIgnored() {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1"));
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onOpenSetting() {
                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("1"));
                }
            };
            PermissionHelper.requestPermission(iWebUiBinder.getBaseActivity(), generateBundle, "android.permission.RECORD_AUDIO", permissionResultCallBack, "\u9ea6\u514b\u98ce", str2);
            return;
        }
        voice2Str(iWebUiBinder, str, i2);
    }

    public static void screenShot(IWebUiBinder iWebUiBinder, String str, float f2) {
        Bitmap bitmap;
        if (isAvaliable(iWebUiBinder)) {
            JDWebView jdWebView = iWebUiBinder.getJdWebView();
            jdWebView.setDrawingCacheEnabled(true);
            jdWebView.buildDrawingCache();
            Bitmap createBitmap = Bitmap.createBitmap(jdWebView.getDrawingCache());
            CameraView cameraView = (CameraView) iWebUiBinder.getBaseActivity().findViewById(R.id.web_camera_view);
            if (createBitmap != null && cameraView != null && (bitmap = cameraView.getBitmap()) != null) {
                cameraView.getLocationInWindow(new int[2]);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(bitmap, r6[0], r6[1], new Paint());
                canvas.save();
                canvas.restore();
                try {
                    bitmap.recycle();
                } catch (Exception unused) {
                }
            }
            jdWebView.setDrawingCacheEnabled(false);
            new Thread(new BitmapRunnable(iWebUiBinder, createBitmap, str, (int) (f2 * 100.0f)), "web_bitmap").start();
        }
    }

    public static void showCamera(IWebUiBinder iWebUiBinder, String str, String str2, boolean z) {
        View findViewById;
        if (isAvaliable(iWebUiBinder) && (findViewById = iWebUiBinder.getBaseActivity().findViewById(R.id.root_layout)) != null && (findViewById instanceof RelativeLayout)) {
            if (findViewById.findViewById(R.id.web_camera_view) != null) {
                return;
            }
            int width = DPIUtil.getWidth(iWebUiBinder.getBaseActivity());
            int height = DPIUtil.getHeight(iWebUiBinder.getBaseActivity());
            int dip2px = DPIUtil.dip2px(iWebUiBinder.getBaseActivity(), 75.0f);
            int dip2px2 = DPIUtil.dip2px(iWebUiBinder.getBaseActivity(), 167.0f);
            int dip2px3 = DPIUtil.dip2px(iWebUiBinder.getBaseActivity(), 21.0f);
            int dip2px4 = (width - dip2px) - DPIUtil.dip2px(iWebUiBinder.getBaseActivity(), 3.0f);
            if (str2 != null && !str2.equals("")) {
                String[] split = str2.split(DYConstants.DY_REGEX_COMMA);
                if (split.length == 4) {
                    try {
                        float floatValue = Float.valueOf(split[0]).floatValue();
                        float floatValue2 = Float.valueOf(split[1]).floatValue();
                        float floatValue3 = Float.valueOf(split[2]).floatValue();
                        float floatValue4 = Float.valueOf(split[3]).floatValue();
                        if (floatValue > 0.0f && floatValue < 1.0f && floatValue2 > 0.0f && floatValue2 < 1.0f && floatValue3 > 0.0f && floatValue3 < 1.0f && floatValue4 > 0.0f && floatValue4 < 1.0f) {
                            float f2 = width;
                            int i2 = (int) (floatValue3 * f2);
                            float f3 = height;
                            int i3 = (int) (floatValue4 * f3);
                            int i4 = (int) (floatValue * f2);
                            dip2px3 = (int) (floatValue2 * f3);
                            int i5 = width - i2;
                            if (i4 > i5) {
                                i4 = i5;
                            }
                            int i6 = height - i3;
                            if (dip2px3 > i6) {
                                dip2px3 = i6;
                            }
                            dip2px4 = i4;
                            dip2px = i2;
                            dip2px2 = i3;
                        }
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px, dip2px2);
            layoutParams.addRule(9);
            layoutParams.leftMargin = dip2px4;
            layoutParams.topMargin = dip2px3;
            if (MediaUtils.addCameraPreView(iWebUiBinder.getBaseActivity(), (ViewGroup) findViewById, layoutParams, z) != null) {
                int cameraNum = MediaUtils.getCameraNum();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("number", cameraNum);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
                WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("0", jSONObject, "success"));
                return;
            }
        }
        WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1"));
    }

    public static void switchCamera(IWebUiBinder iWebUiBinder) {
        View findViewById;
        if (System.currentTimeMillis() - cameraOperTime < 300) {
            return;
        }
        cameraOperTime = System.currentTimeMillis();
        if (isAvaliable(iWebUiBinder) && (findViewById = iWebUiBinder.getBaseActivity().findViewById(R.id.web_camera_view)) != null && (findViewById instanceof CameraView)) {
            ((CameraView) findViewById).switchCamera();
        }
    }

    public static void voice2Str(final IWebUiBinder iWebUiBinder, final String str, int i2) {
        JSVoiceManager.getInstance().start(new JSVoiceManager.Callback() { // from class: com.jingdong.common.web.javainterface.impl.WebMediaJSUtils.3
            @Override // com.jingdong.common.web.managers.JSVoiceManager.Callback
            public void callback(String str2, String str3) {
                str2.hashCode();
                char c2 = '\uffff';
                switch (str2.hashCode()) {
                    case 48:
                        if (str2.equals("0")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case R2.attr.motionPath /* 1444 */:
                        if (str2.equals("-1")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case R2.attr.motionPathRotate /* 1445 */:
                        if (str2.equals("-2")) {
                            c2 = 2;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("0"));
                        return;
                    case 1:
                        WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1", "", str3));
                        return;
                    case 2:
                        iWebUiBinder.getJdWebView().loadUrl("javascript:window.voiceRecognizedAbort && voiceRecognizedAbort('" + WebUtils.stringfyJSonData("-1") + "')");
                        return;
                    default:
                        return;
                }
            }
        }, i2);
    }
}
