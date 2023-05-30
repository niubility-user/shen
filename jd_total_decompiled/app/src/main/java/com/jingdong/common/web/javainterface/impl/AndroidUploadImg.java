package com.jingdong.common.web.javainterface.impl;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.jingdong.common.utils.JdWebViewFunctionUtil;
import com.jingdong.common.utils.PhotoUtils;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.MediaUtils;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import java.util.List;

/* loaded from: classes12.dex */
public final class AndroidUploadImg extends BaseWebComponent implements IJavaInterface {
    public static final int IMAGE_ALBUM = 15;
    public static final int IMAGE_CAMERA = 14;
    private JsBridgeEntity jsBridgeEntity;

    public AndroidUploadImg(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    private static void finishUpload(JsBridgeEntity jsBridgeEntity) {
        if (jsBridgeEntity == null || !jsBridgeEntity.isAndroidUploadImage) {
            return;
        }
        jsBridgeEntity.isAndroidUploadImage = false;
    }

    public static void handleActivityResultAfterImageSelect(IWebUiBinder iWebUiBinder, JsBridgeEntity jsBridgeEntity, int i2, int i3, Intent intent) {
        List<String> processPhotoSelectResult;
        if (i3 == -1 && intent != null && iWebUiBinder != null) {
            String str = null;
            if (i2 == 14) {
                str = MediaUtils.processPhotoCaptureResult(intent);
            } else if (i2 == 15 && (processPhotoSelectResult = MediaUtils.processPhotoSelectResult(intent)) != null && !processPhotoSelectResult.isEmpty()) {
                str = processPhotoSelectResult.get(0);
            }
            if (TextUtils.isEmpty(str)) {
                finishUpload(jsBridgeEntity);
                return;
            }
            Bitmap bitmapFromPath = JdWebViewFunctionUtil.getBitmapFromPath(str, false);
            if (bitmapFromPath == null) {
                finishUpload(jsBridgeEntity);
                return;
            } else if (iWebUiBinder.getBaseActivity().getClass().getSimpleName().equals("IdCardUploadActivity")) {
                PhotoUtils.submitIdCardToH5(iWebUiBinder.getJdWebView().getWebView(), iWebUiBinder.getBaseActivity(), bitmapFromPath, iWebUiBinder.getWebEntity().idCardImgId);
                return;
            } else if (jsBridgeEntity != null && jsBridgeEntity.isAndroidUploadImage) {
                finishUpload(jsBridgeEntity);
                PhotoUtils.submitImageToH5(iWebUiBinder.getJdWebView().getWebView(), iWebUiBinder.getBaseActivity(), bitmapFromPath, JdWebViewFunctionUtil.getExifOrientation(str), 0, false);
                return;
            } else {
                WebUnifiedMtaUtil.albumReport(iWebUiBinder, "AndroidUploadImg-ToServer");
                PhotoUtils.submitPhotoToServer(iWebUiBinder.getJdWebView().getWebView(), iWebUiBinder.getBaseActivity(), bitmapFromPath);
                return;
            }
        }
        finishUpload(jsBridgeEntity);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.ANDROID_UPLOADIMG;
    }

    @JavascriptInterface
    public void imageUpload() {
        this.jsBridgeEntity.isAndroidUploadImage = true;
        MediaUtils.getPhotoWithDialog(this.webUiBinder.getBaseActivity(), 15, 14, new DialogInterface.OnCancelListener() { // from class: com.jingdong.common.web.javainterface.impl.AndroidUploadImg.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (AndroidUploadImg.this.jsBridgeEntity != null) {
                    AndroidUploadImg.this.jsBridgeEntity.isAndroidUploadImage = false;
                }
            }
        });
        WebUnifiedMtaUtil.albumReport(this.webUiBinder, "AndroidUploadImg-imageUpload");
    }

    public AndroidUploadImg() {
    }
}
