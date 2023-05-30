package com.jingdong.common.web.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.unification.album.AlbumConstant;
import com.jingdong.common.unification.album.AlbumParam;
import com.jingdong.common.unification.album.LocalMedia;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.builderimpl.AlbumBuilder;
import com.jingdong.common.unification.router.builderimpl.VideoRecorderBuilder;
import com.jingdong.common.unification.video.VideoConstant;
import com.jingdong.common.unification.video.VideoParam;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.R;
import com.jingdong.common.web.widget.CameraView;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.alpha.util.MediaUtil;

/* loaded from: classes12.dex */
public class MediaUtils {
    public static final String TAG = "MediaUtils";

    public static CameraView addCameraPreView(Context context, ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams, boolean z) {
        CameraView cameraView;
        CameraView cameraView2 = null;
        if (context != null) {
            if (viewGroup != null) {
                try {
                    if (layoutParams.width > 0 && layoutParams.height > 0) {
                        cameraView = new CameraView(context, layoutParams.width, layoutParams.height);
                    } else {
                        cameraView = new CameraView(context);
                    }
                    cameraView2 = cameraView;
                    cameraView2.setDefaultCamera(z);
                    cameraView2.setId(R.id.web_camera_view);
                    viewGroup.addView(cameraView2, layoutParams);
                } catch (Exception unused) {
                    return cameraView2;
                }
            }
            return cameraView2;
        }
        return null;
    }

    public static String bitmap2Base64Str(Bitmap bitmap, int i2) {
        String str = null;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                bitmap.recycle();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (OKLog.D) {
                    OKLog.d(TAG, "3.length -->> " + byteArray.length);
                }
                str = Base64.encodeBytes(byteArray);
                byteArrayOutputStream.close();
                return str;
            } catch (Exception unused) {
                bitmap.recycle();
                return str;
            }
        } catch (Exception unused2) {
            return str;
        }
    }

    public static boolean checkIfCanSaveFileToPublic() {
        if (Build.VERSION.SDK_INT >= 29) {
            return true;
        }
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("wvTipNoSaveFile", null);
        if (TextUtils.isEmpty(switchStringValue)) {
            return false;
        }
        ToastUtils.longToast(JdSdk.getInstance().getApplicationContext(), switchStringValue);
        return false;
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    public static void fileChoose(Activity activity, String str, int i2) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str);
        activity.startActivityForResult(Intent.createChooser(intent, activity.getString(com.jingdong.common.R.string.choose_file)), i2);
    }

    public static int getCameraNum() {
        return Camera.getNumberOfCameras();
    }

    public static void getPhotoWithDialog(final Context context, final int i2, final int i3, final DialogInterface.OnCancelListener onCancelListener) {
        if (context == null) {
            if (onCancelListener != null) {
                onCancelListener.onCancel(null);
                return;
            }
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(com.jingdong.common.R.string.get_image_tip);
        String[] strArr = Configuration.getBooleanProperty(Configuration.PHOTO_SHUT, Boolean.FALSE).booleanValue() ? new String[]{context.getString(com.jingdong.common.R.string.image_from_photos)} : new String[]{context.getString(com.jingdong.common.R.string.image_from_camera), context.getString(com.jingdong.common.R.string.image_from_photos)};
        final String[] strArr2 = strArr;
        builder.setItems(strArr, new DialogInterface.OnClickListener() { // from class: com.jingdong.common.web.util.MediaUtils.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i4) {
                String str = strArr2[i4];
                if (TextUtils.equals(str, context.getString(com.jingdong.common.R.string.image_from_camera))) {
                    MediaUtils.jumpToGetPhoto(context, true, i3);
                } else if (TextUtils.equals(str, context.getString(com.jingdong.common.R.string.image_from_photos))) {
                    MediaUtils.jumpToGetPhoto(context, false, i2);
                } else {
                    DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
                    if (onCancelListener2 != null) {
                        onCancelListener2.onCancel(dialogInterface);
                    }
                }
            }
        });
        builder.setOnCancelListener(onCancelListener);
        builder.show();
    }

    public static void getVideoFileWithDialog(Context context, String str, int i2, int i3, DialogInterface.OnCancelListener onCancelListener) {
        getVideoFileWithDialog(context, str, 2, 30, i2, i3, onCancelListener);
    }

    private static Uri insertFileIntoMediaStore(Context context, File file, boolean z) {
        Uri uri;
        String name = file.getName();
        String[] split = name.split("\\.");
        String str = split.length >= 2 ? split[split.length - 1] : null;
        if (TextUtils.isEmpty(str)) {
            str = z ? "png" : "mp4";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? "image/" : MediaUtil.TRACK_VIDEO);
        sb.append(str);
        String sb2 = sb.toString();
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", name);
        contentValues.put("mime_type", sb2);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            contentValues.put("datetaken", Long.valueOf(file.lastModified()));
            contentValues.put("is_pending", (Integer) 1);
        }
        if (i2 >= 29) {
            if (z) {
                uri = MediaStore.Images.Media.getContentUri("external_primary");
            } else {
                uri = MediaStore.Video.Media.getContentUri("external_primary");
            }
        } else if (z) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        try {
            Uri insert = contentResolver.insert(uri, contentValues);
            if (insert != null) {
                boolean saveFile = saveFile(contentResolver, file, insert);
                if (i2 >= 29) {
                    contentValues.clear();
                    contentValues.put("is_pending", (Integer) 0);
                    contentResolver.update(insert, contentValues, null, null);
                }
                if (saveFile) {
                    return insert;
                }
                return null;
            }
            return null;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "insertFileIntoMediaStore", e2);
            }
            return null;
        }
    }

    private static void jumpToAlbum(Context context, AlbumParam albumParam, int i2) {
        ((AlbumBuilder) JDRouter.to(AlbumBuilder.class)).albumParam(albumParam).setRequestCode(i2).jump(context);
    }

    public static void jumpToGetPhoto(Context context, boolean z, int i2) {
        if (z) {
            VideoParam videoParam = new VideoParam();
            videoParam.recordFunctionControl = 2;
            videoParam.recordCurrentState = 1;
            jumpToRecord(context, videoParam, i2);
            return;
        }
        AlbumParam albumParam = new AlbumParam();
        albumParam.cameraOrVideoAction = 0;
        albumParam.loadCameraOrVideo = 1;
        albumParam.canSelectMediaCount = 1;
        jumpToAlbum(context, albumParam, i2);
    }

    public static void jumpToGetVideo(Context context, boolean z, int i2, int i3, int i4) {
        if (z) {
            VideoParam videoParam = new VideoParam();
            if (i2 > 0) {
                videoParam.recordMinTime = i2;
            }
            if (i3 > 0) {
                videoParam.recordMaxTime = i3;
            }
            videoParam.recordFunctionControl = 1;
            videoParam.recordCurrentState = 0;
            jumpToRecord(context, videoParam, i4);
            return;
        }
        AlbumParam albumParam = new AlbumParam();
        albumParam.cameraOrVideoAction = 0;
        albumParam.loadCameraOrVideo = 2;
        albumParam.canSelectMediaCount = 1;
        jumpToAlbum(context, albumParam, i4);
    }

    private static void jumpToRecord(Context context, VideoParam videoParam, int i2) {
        ((VideoRecorderBuilder) JDRouter.to(VideoRecorderBuilder.class)).videoParam(videoParam).requestCode(i2).jump(context);
    }

    public static String processPhotoCaptureResult(Intent intent) {
        List<String> processResultIntent = processResultIntent(intent, true, 1);
        if (processResultIntent == null || processResultIntent.isEmpty()) {
            return null;
        }
        return processResultIntent.get(0);
    }

    public static List<String> processPhotoSelectResult(Intent intent) {
        return processResultIntent(intent, false, 1);
    }

    private static List<String> processResultIntent(Intent intent, boolean z, int i2) {
        if (intent == null) {
            return null;
        }
        if (z) {
            String stringExtra = intent.getStringExtra(i2 == 1 ? VideoConstant.PICTURE_PATH : "videoPath");
            if (TextUtils.isEmpty(stringExtra)) {
                return null;
            }
            return Collections.singletonList(stringExtra);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(AlbumConstant.SELECT_MEDIAS);
        if (parcelableArrayListExtra != null && !parcelableArrayListExtra.isEmpty()) {
            Iterator it = parcelableArrayListExtra.iterator();
            while (it.hasNext()) {
                LocalMedia localMedia = (LocalMedia) it.next();
                String path = localMedia != null ? localMedia.getPath() : null;
                if (!TextUtils.isEmpty(path)) {
                    arrayList.add(path);
                }
            }
        }
        return arrayList;
    }

    public static String processVideoCaptureResult(Intent intent) {
        List<String> processResultIntent = processResultIntent(intent, true, 2);
        if (processResultIntent == null || processResultIntent.isEmpty()) {
            return null;
        }
        return processResultIntent.get(0);
    }

    public static List<String> processVideoSelectResult(Intent intent) {
        return processResultIntent(intent, false, 2);
    }

    public static void removeCameraPreView(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        try {
            View findViewById = viewGroup.findViewById(R.id.web_camera_view);
            if (findViewById != null) {
                viewGroup.removeView(findViewById);
            }
        } catch (Exception unused) {
        }
    }

    private static boolean saveFile(ContentResolver contentResolver, File file, Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor;
        if (OKLog.D) {
            OKLog.d(TAG, "SaveFile: from: " + file.getPath() + ", to: " + uri);
        }
        FileInputStream fileInputStream = null;
        try {
            parcelFileDescriptor = contentResolver.openFileDescriptor(uri, JshopConst.JSHOP_PROMOTIO_W);
        } catch (FileNotFoundException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, "SaveFile", e2);
            }
            parcelFileDescriptor = null;
        }
        if (parcelFileDescriptor == null) {
            if (OKLog.E) {
                OKLog.e(TAG, "SaveFile: parcelFileDescriptor is null");
            }
            return false;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    copy(fileInputStream2, fileOutputStream);
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        if (OKLog.E) {
                            OKLog.e(TAG, "SaveFile", e3);
                        }
                    }
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                        if (OKLog.E) {
                            OKLog.e(TAG, "SaveFile", e4);
                        }
                    }
                    return true;
                } catch (Exception e5) {
                    e = e5;
                    fileInputStream = fileInputStream2;
                    if (OKLog.E) {
                        OKLog.e(TAG, "SaveFile", e);
                    }
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        if (OKLog.E) {
                            OKLog.e(TAG, "SaveFile", e6);
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e7) {
                            if (OKLog.E) {
                                OKLog.e(TAG, "SaveFile", e7);
                            }
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    try {
                        fileOutputStream.close();
                    } catch (IOException e8) {
                        if (OKLog.E) {
                            OKLog.e(TAG, "SaveFile", e8);
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e9) {
                            if (OKLog.E) {
                                OKLog.e(TAG, "SaveFile", e9);
                            }
                        }
                    }
                    throw th;
                }
            } catch (Exception e10) {
                e = e10;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean savePictureToAlbum(Context context, File file) {
        return (file == null || context == null || insertFileIntoMediaStore(context, file, true) == null) ? false : true;
    }

    public static boolean saveVideoToAlbum(Context context, File file) {
        return (file == null || context == null || insertFileIntoMediaStore(context, file, false) == null) ? false : true;
    }

    public static void switchCamera(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        try {
            ((CameraView) viewGroup.findViewById(R.id.web_camera_view)).switchCamera();
        } catch (Exception unused) {
        }
    }

    public static void getVideoFileWithDialog(final Context context, String str, final int i2, final int i3, final int i4, final int i5, final DialogInterface.OnCancelListener onCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, "getVideoFile -->");
        }
        if (context == null) {
            if (onCancelListener != null) {
                onCancelListener.onCancel(null);
                return;
            }
            return;
        }
        String[] strArr = {context.getString(com.jingdong.common.R.string.video_from_camera), context.getString(com.jingdong.common.R.string.video_from_file)};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(com.jingdong.common.R.string.get_image_tip);
        builder.setItems(strArr, new DialogInterface.OnClickListener() { // from class: com.jingdong.common.web.util.MediaUtils.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i6) {
                Context context2 = context;
                if (!(context2 instanceof Activity)) {
                    DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
                    if (onCancelListener2 != null) {
                        onCancelListener2.onCancel(dialogInterface);
                    }
                } else if (i6 == 0) {
                    MediaUtils.jumpToGetVideo((Activity) context2, true, i2, i3, i5);
                } else if (i6 != 1) {
                    DialogInterface.OnCancelListener onCancelListener3 = onCancelListener;
                    if (onCancelListener3 != null) {
                        onCancelListener3.onCancel(dialogInterface);
                    }
                } else {
                    MediaUtils.jumpToGetVideo((Activity) context2, false, i2, i3, i4);
                }
            }
        });
        builder.setOnCancelListener(onCancelListener);
        builder.show();
    }
}
