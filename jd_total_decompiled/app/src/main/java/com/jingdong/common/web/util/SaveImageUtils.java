package com.jingdong.common.web.util;

import android.content.Context;
import android.text.TextUtils;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.xconsole.XLog;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.util.SaveImageUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class SaveImageUtils {
    public static final String SWITCH_NAME = "saveImageNew";
    public static final String TAG = "SaveImageUtils";

    /* renamed from: com.jingdong.common.web.util.SaveImageUtils$1 */
    /* loaded from: classes12.dex */
    public class AnonymousClass1 implements HttpGroup.OnAllAndPauseListener {
        final /* synthetic */ SaveImageCallback val$callback;
        final /* synthetic */ Context val$context;

        AnonymousClass1(Context context, SaveImageCallback saveImageCallback) {
            this.val$context = context;
            this.val$callback = saveImageCallback;
        }

        public static /* synthetic */ void a(HttpResponse httpResponse, Context context, SaveImageCallback saveImageCallback) {
            boolean z;
            File saveFile;
            if (httpResponse == null || httpResponse.getSaveFile() == null || (saveFile = httpResponse.getSaveFile()) == null) {
                z = false;
            } else {
                SaveImageUtils.logX("httpSetting save file path: " + saveFile.getPath());
                z = MediaUtils.savePictureToAlbum(JdSdk.getInstance().getApplicationContext(), saveFile);
                if (saveFile.exists()) {
                    saveFile.delete();
                }
            }
            SaveImageUtils.notifyDownloadResult(context, saveImageCallback, z);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(final HttpResponse httpResponse) {
            DatabaseExecutors databaseExecutors = DatabaseExecutors.getInstance();
            final Context context = this.val$context;
            final SaveImageCallback saveImageCallback = this.val$callback;
            databaseExecutors.runOnIoThread(new Runnable() { // from class: com.jingdong.common.web.util.a
                @Override // java.lang.Runnable
                public final void run() {
                    SaveImageUtils.AnonymousClass1.a(httpResponse, context, saveImageCallback);
                }
            });
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            SaveImageUtils.notifyDownloadResult(this.val$context, this.val$callback, false);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
        public void onPause() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* loaded from: classes12.dex */
    public enum CallBackType {
        IGNORE,
        PERMISSION_ERROR
    }

    /* loaded from: classes12.dex */
    public interface SaveImageCallback {
        void onCallback(boolean z, CallBackType callBackType);
    }

    public static /* synthetic */ void b(String str, String str2, Context context, SaveImageCallback saveImageCallback) {
        byte[] decode;
        File file;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        boolean z = false;
        try {
            try {
                decode = Base64.decode(str);
                file = new File(FileService.getInternalDirectory("webviewTemp", 1, true), str2);
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileOutputStream.write(decode);
                z = MediaUtils.savePictureToAlbum(context, file);
                if (file.exists()) {
                    file.delete();
                }
                fileOutputStream.close();
            } catch (Exception unused2) {
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                notifyDownloadResult(context, saveImageCallback, z);
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (IOException unused4) {
            notifyDownloadResult(context, saveImageCallback, z);
        }
    }

    private static void downloadAndSaveImage(Context context, String str, SaveImageCallback saveImageCallback) {
        String str2 = System.currentTimeMillis() + ".png";
        try {
            Matcher matcher = Pattern.compile("^data:image[^,]+,(.+)$").matcher(str);
            matcher.find();
            String group = matcher.group(1);
            if (!TextUtils.isEmpty(group)) {
                saveBase64Img(context, group, str2, saveImageCallback);
                return;
            }
        } catch (Throwable unused) {
        }
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(2);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str2);
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setCacheMode(0);
        httpSetting.setType(500);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setNotifyUser(true);
        httpSetting.setEffect(1);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(1);
        httpSetting.setListener(new AnonymousClass1(context, saveImageCallback));
        httpSetting.setPriority(500);
        httpSetting.setIsExclusiveTask(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static boolean isSwitchOn() {
        return SwitchQueryFetcher.getSwitchBooleanValue(SWITCH_NAME, false);
    }

    public static void logX(String str) {
        if (TextUtils.isEmpty(str) || !Log.isDebug()) {
            return;
        }
        XLog.d(TAG, String.format("[%s] %s", TAG, str));
    }

    public static void notifyDownloadResult(Context context, SaveImageCallback saveImageCallback, boolean z) {
        notifyDownloadResult(context, saveImageCallback, z, CallBackType.IGNORE);
    }

    private static void saveBase64Img(final Context context, final String str, final String str2, final SaveImageCallback saveImageCallback) {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jingdong.common.web.util.c
            @Override // java.lang.Runnable
            public final void run() {
                SaveImageUtils.b(str, str2, context, saveImageCallback);
            }
        });
    }

    public static void saveImageToPhotoAlbum(String str, SaveImageCallback saveImageCallback) {
        logX("saveImageToPhotoAlbum:" + str);
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        if (!TextUtils.isEmpty(str) && applicationContext != null) {
            if (!MediaUtils.checkIfCanSaveFileToPublic()) {
                logX("saveImageToPhotoAlbum: permission denied");
                notifyDownloadResult(applicationContext, saveImageCallback, false, CallBackType.PERMISSION_ERROR);
                return;
            }
            downloadAndSaveImage(applicationContext, str, saveImageCallback);
            return;
        }
        notifyDownloadResult(applicationContext, saveImageCallback, false);
    }

    private static void notifyDownloadResult(final Context context, SaveImageCallback saveImageCallback, boolean z, CallBackType callBackType) {
        if (saveImageCallback != null) {
            saveImageCallback.onCallback(z, callBackType);
        } else if (callBackType != CallBackType.PERMISSION_ERROR) {
            final String str = z ? "\u5b58\u50a8\u6210\u529f,\u53ef\u5728\u76f8\u518c\u4e2d\u67e5\u770b" : "\u5b58\u50a8\u5931\u8d25,\u8bf7\u91cd\u8bd5";
            WebUtils.runOnMain(new Runnable() { // from class: com.jingdong.common.web.util.b
                @Override // java.lang.Runnable
                public final void run() {
                    ToastUtils.showToast(context, str);
                }
            });
        }
    }
}
