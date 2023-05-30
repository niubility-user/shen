package com.jingdong.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import androidx.core.view.MotionEventCompat;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.search.FilterConstant;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.WebView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.json.JSONArray;

/* loaded from: classes6.dex */
public class PhotoUtils {
    private static final String TAG = "PhotoUtils";
    private static float hRadius = 2.0f;
    private static int iterations = 2;
    private static float vRadius = 2.0f;

    public static void blur(int[] iArr, int[] iArr2, int i2, int i3, float f2) {
        int i4 = i2 - 1;
        int i5 = (int) f2;
        int i6 = (i5 * 2) + 1;
        int i7 = i6 * 256;
        int[] iArr3 = new int[i7];
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            iArr3[i9] = i9 / i6;
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < i3) {
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            for (int i16 = -i5; i16 <= i5; i16++) {
                int i17 = iArr[clamp(i16, i8, i4) + i11];
                i12 += (i17 >> 24) & 255;
                i13 += (i17 >> 16) & 255;
                i14 += (i17 >> 8) & 255;
                i15 += i17 & 255;
            }
            int i18 = i10;
            int i19 = 0;
            while (i19 < i2) {
                iArr2[i18] = (iArr3[i12] << 24) | (iArr3[i13] << 16) | (iArr3[i14] << 8) | iArr3[i15];
                int i20 = i19 + i5 + 1;
                if (i20 > i4) {
                    i20 = i4;
                }
                int i21 = i19 - i5;
                if (i21 < 0) {
                    i21 = 0;
                }
                int i22 = iArr[i20 + i11];
                int i23 = iArr[i21 + i11];
                i12 += ((i22 >> 24) & 255) - ((i23 >> 24) & 255);
                i13 += ((i22 & 16711680) - (16711680 & i23)) >> 16;
                i14 += ((i22 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) - (65280 & i23)) >> 8;
                i15 += (i22 & 255) - (i23 & 255);
                i18 += i3;
                i19++;
                i4 = i4;
            }
            i11 += i2;
            i10++;
            i8 = 0;
        }
    }

    public static void blurFractional(int[] iArr, int[] iArr2, int i2, int i3, float f2) {
        int i4;
        float f3 = f2 - ((int) f2);
        float f4 = 1.0f / ((2.0f * f3) + 1.0f);
        char c2 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i5 < i3) {
            iArr2[i5] = iArr[c2];
            int i7 = i5 + i3;
            int i8 = 1;
            int i9 = 1;
            while (true) {
                i4 = i2 - 1;
                if (i9 < i4) {
                    int i10 = i6 + i9;
                    int i11 = iArr[i10 - 1];
                    int i12 = iArr[i10];
                    int i13 = iArr[i10 + i8];
                    int i14 = ((i12 >> 16) & 255) + ((int) ((((i11 >> 16) & 255) + ((i13 >> 16) & 255)) * f3));
                    iArr2[i7] = (((int) (i14 * f4)) << 16) | (((int) ((((i12 >> 24) & 255) + ((int) ((((i11 >> 24) & 255) + ((i13 >> 24) & 255)) * f3))) * f4)) << 24) | (((int) ((((i12 >> 8) & 255) + ((int) ((((i11 >> 8) & 255) + ((i13 >> 8) & 255)) * f3))) * f4)) << 8) | ((int) (((i12 & 255) + ((int) (((i11 & 255) + (i13 & 255)) * f3))) * f4));
                    i7 += i3;
                    i9++;
                    i5 = i5;
                    i6 = i6;
                    i8 = 1;
                }
            }
            iArr2[i7] = iArr[i4];
            i6 += i2;
            i5++;
            c2 = 0;
        }
    }

    public static Drawable boxBlurFilter(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width * height;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i3 = 0; i3 < iterations; i3++) {
            blur(iArr, iArr2, width, height, hRadius);
            blur(iArr2, iArr, height, width, vRadius);
        }
        blurFractional(iArr, iArr2, width, height, hRadius);
        blurFractional(iArr2, iArr, height, width, vRadius);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return new BitmapDrawable(createBitmap);
    }

    public static int clamp(int i2, int i3, int i4) {
        return i2 < i3 ? i3 : i2 > i4 ? i4 : i2;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        if (OKLog.D) {
                            DatabaseUtils.dumpCursor(query);
                        }
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static Uri getPictureUri(Context context, File file, Intent intent) {
        if (Build.VERSION.SDK_INT > 23) {
            Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", file);
            if (intent != null) {
                intent.addFlags(3);
                return uriForFile;
            }
            return uriForFile;
        }
        return Uri.fromFile(file);
    }

    public static Uri getUri(File file) {
        if (file != null) {
            return Uri.fromFile(file);
        }
        return null;
    }

    public static boolean isCameraCanUse() {
        Camera camera;
        boolean z;
        try {
            camera = Camera.open();
            z = true;
        } catch (Exception unused) {
            camera = null;
            z = false;
        }
        boolean z2 = camera != null ? z : false;
        if (z2) {
            camera.release();
        }
        return z2;
    }

    @SuppressLint({"NewApi"})
    public static boolean isDocumentUri(Context context, Uri uri) {
        return (Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri);
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isLocal(String str) {
        return (str == null || str.startsWith("http://") || str.startsWith("https://")) ? false : true;
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaUri(Uri uri) {
        return FilterConstant.SELECT_KEY_MEDIA.equalsIgnoreCase(uri.getAuthority());
    }

    public static void submitIdCardToH5(final WebView webView, BaseActivity baseActivity, Bitmap bitmap, final String str) {
        if (baseActivity == null || webView == null) {
            return;
        }
        final String bitmapString = toBitmapString(bitmap, true);
        if (TextUtils.isEmpty(bitmapString)) {
            return;
        }
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.utils.PhotoUtils.1
            @Override // java.lang.Runnable
            public void run() {
                WebView.this.loadUrl("javascript:imgSrc('" + str + "', '" + bitmapString + "');");
            }
        });
    }

    public static void submitImageToH5(final WebView webView, BaseActivity baseActivity, Bitmap bitmap, final int i2, final int i3, boolean z) {
        if (baseActivity == null || webView == null) {
            return;
        }
        final String bitmapString = toBitmapString(bitmap, z);
        if (TextUtils.isEmpty(bitmapString)) {
            return;
        }
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.utils.PhotoUtils.2
            @Override // java.lang.Runnable
            public void run() {
                WebView.this.loadUrl("javascript:imageSrc( '" + bitmapString + "'," + i2 + DYConstants.DY_REGEX_COMMA + i3 + ");");
            }
        });
    }

    public static void submitPhotoToServer(final WebView webView, final BaseActivity baseActivity, Bitmap bitmap) {
        if (OKLog.D) {
            OKLog.d(TAG, "submitPhotoToServer -->> ");
        }
        ExceptionReporter.reportWebViewCommonError("oldPicUploadUsage", webView != null ? webView.getUrl() : "", baseActivity != null ? baseActivity.getClass().getSimpleName() : "", "");
        JSONArray jSONArray = new JSONArray();
        String bitmapString = toBitmapString(bitmap, true);
        if (TextUtils.isEmpty(bitmapString)) {
            return;
        }
        jSONArray.put(bitmapString);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.putJsonParam("pictureStreams", jSONArray);
        httpSetting.setFunctionId("uploadRuturnBackImage");
        new ExceptionReporter(httpSetting);
        httpSetting.setListener(new HttpGroup.OnAllListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0060: INVOKE 
              (r6v2 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
              (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnAllListener : 0x005d: CONSTRUCTOR 
              (r5v0 'baseActivity' com.jingdong.common.BaseActivity A[DONT_INLINE])
              (r0 I:com.jingdong.jdsdk.network.toolbox.ExceptionReporter A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r4v0 'webView' com.tencent.smtt.sdk.WebView A[DONT_INLINE])
             A[MD:(com.jingdong.common.BaseActivity, com.jingdong.jdsdk.network.toolbox.ExceptionReporter, com.tencent.smtt.sdk.WebView):void (m), WRAPPED] (LINE:13) call: com.jingdong.common.utils.PhotoUtils.3.<init>(com.jingdong.common.BaseActivity, com.jingdong.jdsdk.network.toolbox.ExceptionReporter, com.tencent.smtt.sdk.WebView):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:13) in method: com.jingdong.common.utils.PhotoUtils.submitPhotoToServer(com.tencent.smtt.sdk.WebView, com.jingdong.common.BaseActivity, android.graphics.Bitmap):void, file: classes6.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D
            if (r0 == 0) goto Lb
            java.lang.String r0 = com.jingdong.common.utils.PhotoUtils.TAG
            java.lang.String r1 = "submitPhotoToServer -->> "
            com.jingdong.sdk.oklog.OKLog.d(r0, r1)
        Lb:
            java.lang.String r0 = ""
            if (r4 == 0) goto L14
            java.lang.String r1 = r4.getUrl()
            goto L15
        L14:
            r1 = r0
        L15:
            if (r5 == 0) goto L20
            java.lang.Class r2 = r5.getClass()
            java.lang.String r2 = r2.getSimpleName()
            goto L21
        L20:
            r2 = r0
        L21:
            java.lang.String r3 = "oldPicUploadUsage"
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter.reportWebViewCommonError(r3, r1, r2, r0)
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            r1 = 1
            java.lang.String r6 = toBitmapString(r6, r1)
            boolean r2 = android.text.TextUtils.isEmpty(r6)
            if (r2 == 0) goto L37
            return
        L37:
            r0.put(r6)
            com.jingdong.jdsdk.network.toolbox.HttpSetting r6 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r6.<init>()
            com.jingdong.jdsdk.config.HostConfig r2 = com.jingdong.jdsdk.config.HostConfig.getInstance()
            java.lang.String r3 = "personal_host"
            java.lang.String r2 = r2.getHost(r3)
            r6.setHost(r2)
            java.lang.String r2 = "pictureStreams"
            r6.putJsonParam(r2, r0)
            java.lang.String r0 = "uploadRuturnBackImage"
            r6.setFunctionId(r0)
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter r0 = new com.jingdong.jdsdk.network.toolbox.ExceptionReporter
            r0.<init>(r6)
            com.jingdong.common.utils.PhotoUtils$3 r2 = new com.jingdong.common.utils.PhotoUtils$3
            r2.<init>()
            r6.setListener(r2)
            r6.setNotifyUser(r1)
            com.jingdong.jdsdk.network.toolbox.HttpGroup r4 = r5.getHttpGroupaAsynPool()
            r4.add(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.PhotoUtils.submitPhotoToServer(com.tencent.smtt.sdk.WebView, com.jingdong.common.BaseActivity, android.graphics.Bitmap):void");
    }

    private static String toBitmapString(Bitmap bitmap, boolean z) {
        Bitmap bitmap2;
        try {
            float parseFloat = Float.parseFloat(Configuration.getProperty(Configuration.DISCUSSUPLOADIMAGE_WIDTH));
            float parseFloat2 = Float.parseFloat(Configuration.getProperty(Configuration.DISCUSSUPLOADIMAGE_HEIGHT));
            if (!z || 0.0f >= parseFloat || 0.0f >= parseFloat2) {
                bitmap2 = bitmap;
            } else {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                if (OKLog.D) {
                    OKLog.d(TAG, "sourceWidth -->> " + width);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "sourceHeight -->> " + height);
                }
                float f2 = width > height ? parseFloat / width : parseFloat2 / height;
                int round = Math.round(width * f2);
                int round2 = Math.round(f2 * height);
                if (OKLog.D) {
                    OKLog.d(TAG, "width -->> " + round);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "height -->> " + round2);
                }
                bitmap2 = Bitmap.createScaledBitmap(bitmap, round, round2, false);
                bitmap.recycle();
            }
            int parseInt = Integer.parseInt(Configuration.getProperty(Configuration.DISCUSSUPLOADIMAGE_QUALITY));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, parseInt, byteArrayOutputStream);
            bitmap2.recycle();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (OKLog.D) {
                OKLog.d(TAG, "3.length -->> " + byteArray.length);
            }
            return Base64.encodeBytes(byteArray);
        } catch (Throwable th) {
            OKLog.e(TAG, th);
            bitmap.recycle();
            return null;
        }
    }

    public static void updateWebView(BaseActivity baseActivity, final WebView webView, final String str) {
        if (baseActivity == null || webView == null) {
            return;
        }
        baseActivity.post(new Runnable() { // from class: com.jingdong.common.utils.PhotoUtils.4
            @Override // java.lang.Runnable
            public void run() {
                WebView.this.loadUrl("javascript:cameraCallBack('" + str + "', '<head>' + document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });
    }
}
