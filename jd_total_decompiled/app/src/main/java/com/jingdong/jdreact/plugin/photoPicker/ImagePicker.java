package com.jingdong.jdreact.plugin.photoPicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.jd.dynamic.DYConstants;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class ImagePicker {
    static final String TAG = "ImagePicker";
    private static String mFileProvider = "com.jingdong.app.mall.fileProviderRootPath";
    private static PickImageCallback sCallback;
    private static String sCaptureImagePath;
    private static String sCropImagePath;
    private static boolean sNeedCrop;

    /* loaded from: classes13.dex */
    public interface PickImageCallback {
        void onPickImage(boolean z, String str);
    }

    /* loaded from: classes13.dex */
    public interface RequestCode {
        public static final int ALBUM = 161;
        public static final int CAMERA = 160;
        public static final int CROP = 162;
    }

    private static boolean deleteSingleFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile() && file.delete()) {
            String str2 = "Copy_Delete.deleteSingleFile: \u5220\u9664\u5355\u4e2a\u6587\u4ef6" + str + "\u6210\u529f\uff01";
            return true;
        }
        return false;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        return null;
    }

    public static String getFileProvider() {
        return mFileProvider;
    }

    public static Uri getUriFromFile(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        File file = new File(str);
        if (isAndroidN()) {
            return FileProvider.getUriForFile(context, getFileProvider(), file);
        }
        return Uri.fromFile(file);
    }

    public static void handleActivityResult(Activity activity, int i2, int i3, Intent intent) {
        String str = "handleActivityResult, requestCode:" + i2 + ", resultCode:" + i3 + ", data:" + intent;
        switch (i2) {
            case 160:
                if (i3 != -1) {
                    invokeCallback(false, null);
                    return;
                } else if (sNeedCrop) {
                    startCrop(activity, getUriFromFile(activity, sCaptureImagePath));
                    sNeedCrop = false;
                    return;
                } else {
                    invokeCallback(true, sCaptureImagePath);
                    return;
                }
            case 161:
                if (i3 != -1) {
                    invokeCallback(false, null);
                    return;
                }
                Uri data = intent != null ? intent.getData() : null;
                if (data != null) {
                    if (sNeedCrop) {
                        startCrop(activity, data);
                        sNeedCrop = false;
                        return;
                    }
                    invokeCallback(true, uriToPath(activity, data));
                    return;
                }
                return;
            case 162:
                if (i3 != -1) {
                    invokeCallback(false, null);
                    return;
                } else {
                    invokeCallback(true, sCropImagePath);
                    return;
                }
            default:
                return;
        }
    }

    private static void invokeCallback(boolean z, String str) {
        PickImageCallback pickImageCallback = sCallback;
        if (pickImageCallback == null) {
            return;
        }
        pickImageCallback.onPickImage(z, str);
        sCallback = null;
    }

    public static boolean isAndroidN() {
        return Build.VERSION.SDK_INT >= 24;
    }

    private static void performPickFromAlbum(Activity activity, boolean z) {
    }

    private static void performPickFromCamera(Activity activity, boolean z, boolean z2) {
    }

    public static void pickFromAlbum(Activity activity, boolean z, PickImageCallback pickImageCallback) {
        if (activity == null) {
            return;
        }
        sCallback = pickImageCallback;
        invokeCallback(false, "permission not granted");
    }

    public static void pickFromCamera(Activity activity, boolean z, PickImageCallback pickImageCallback) {
        pickFromCamera(activity, z, true, pickImageCallback);
    }

    private static boolean prepareImageSaveFilePath(Context context, boolean z) {
        return false;
    }

    public static void release() {
        sCallback = null;
    }

    public static void setFileProvider(String str) {
        mFileProvider = str;
    }

    private static void startCrop(Activity activity, Uri uri) {
        if (uri != null && activity != null) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            if (isAndroidN()) {
                intent.addFlags(1);
            }
            intent.putExtra("output", Uri.fromFile(new File(sCropImagePath)));
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", DYConstants.DY_TRUE);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);
            intent.putExtra("return-data", false);
            intent.putExtra("scale", true);
            intent.putExtra("scaleUpIfNeeded", true);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            activity.startActivityForResult(intent, 162);
            return;
        }
        invokeCallback(false, null);
    }

    public static String uriToPath(Context context, Uri uri) {
        return null;
    }

    public static void pickFromCamera(Activity activity, boolean z, boolean z2, PickImageCallback pickImageCallback) {
        if (activity == null) {
            return;
        }
        sCallback = pickImageCallback;
        invokeCallback(false, "permission not granted");
    }

    public static void pickFromAlbum(Activity activity, boolean z, PickImageCallback pickImageCallback, HashMap hashMap) {
        if (activity == null) {
            return;
        }
        sCallback = pickImageCallback;
        invokeCallback(false, "permission not granted");
    }

    public static void pickFromCamera(Activity activity, boolean z, boolean z2, PickImageCallback pickImageCallback, HashMap hashMap) {
        if (activity == null) {
            return;
        }
        sCallback = pickImageCallback;
        invokeCallback(false, "permission not granted");
    }
}
