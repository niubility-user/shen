package com.jd.mobile.image.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Pair;
import com.coremedia.iso.boxes.FileTypeBox;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatCheckerUtils;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.jd.dynamic.DYConstants;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import javax.annotation.Nullable;
import org.aomedia.avif.android.AvifDecoder;
import org.json.JSONArray;

/* loaded from: classes17.dex */
public class AvifDecoderUtil {
    private static final String AV_01_ALPHA = "av01Alpha";
    private static final int PAINT_FLAGS = 6;
    public static final String TAG = "AvifDecoder";
    private static final String AVIF = "avif";
    public static final ImageFormat AVIF_FORMAT = new ImageFormat("AVIF_FORMAT", AVIF);
    private static final String AVIS = "avis";
    public static final ImageFormat AVIF_FORMAT_ANIMATED = new ImageFormat("AVIF_FORMAT_ANIMATED", AVIS);
    private static ArrayList<String> avifWhitePageList = null;
    private static ArrayList<String> avifWhitePageImageHostList = null;
    private static Boolean isAVIFSupport = null;
    private static volatile boolean hasAddAVIFDecoder = false;
    private static final Paint DEFAULT_PAINT = new Paint(6);

    /* loaded from: classes17.dex */
    public static class a implements ImageFormat.FormatChecker {
        private static final byte[] a = ImageFormatCheckerUtils.asciiBytes(FileTypeBox.TYPE);
        private static final byte[] b = ImageFormatCheckerUtils.asciiBytes(AvifDecoderUtil.AVIF);

        static {
            ImageFormatCheckerUtils.asciiBytes(AvifDecoderUtil.AVIS);
        }

        @Override // com.facebook.imageformat.ImageFormat.FormatChecker
        public ImageFormat determineFormat(byte[] bArr, int i2) {
            if (i2 >= 144 && ImageFormatCheckerUtils.hasPatternAt(bArr, a, 4) && ImageFormatCheckerUtils.hasPatternAt(bArr, b, 8)) {
                return AvifDecoderUtil.AVIF_FORMAT;
            }
            return ImageFormat.UNKNOWN;
        }

        @Override // com.facebook.imageformat.ImageFormat.FormatChecker
        public int getHeaderSize() {
            return 144;
        }
    }

    /* loaded from: classes17.dex */
    public static class b implements ImageDecoder {
        /* JADX WARN: Removed duplicated region for block: B:86:0x0125  */
        @Override // com.facebook.imagepipeline.decoder.ImageDecoder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.facebook.imagepipeline.image.CloseableImage decode(com.facebook.imagepipeline.image.EncodedImage r11, int r12, com.facebook.imagepipeline.image.QualityInfo r13, com.facebook.imagepipeline.common.ImageDecodeOptions r14) {
            /*
                Method dump skipped, instructions count: 359
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.mobile.image.utils.AvifDecoderUtil.b.decode(com.facebook.imagepipeline.image.EncodedImage, int, com.facebook.imagepipeline.image.QualityInfo, com.facebook.imagepipeline.common.ImageDecodeOptions):com.facebook.imagepipeline.image.CloseableImage");
        }
    }

    public static Bitmap convertBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale(1.01f, 1.01f);
        Bitmap createBitmap = Bitmap.createBitmap((int) (bitmap.getWidth() * 1.01f), (int) (bitmap.getHeight() * 1.01f), bitmap.getConfig());
        createBitmap.setHasAlpha(bitmap.hasAlpha());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, matrix, DEFAULT_PAINT);
        canvas.setBitmap(null);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    @Nullable
    public static Pair<Integer, Integer> getSize(InputStream inputStream) {
        Pair<Integer, Integer> pair = null;
        try {
            try {
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(inputStream.available());
                Channels.newChannel(inputStream).read(allocateDirect);
                allocateDirect.flip();
                if (AvifDecoder.a(allocateDirect)) {
                    AvifDecoder.Info info = new AvifDecoder.Info();
                    if (AvifDecoder.getInfo(allocateDirect, allocateDirect.remaining(), info)) {
                        pair = Pair.create(Integer.valueOf(info.width), Integer.valueOf(info.height));
                    } else {
                        FLog.e(TAG, "getInfo : failed");
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return pair;
            } catch (IOException e3) {
                e3.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String getTransformImageUri(String str, boolean z, String str2) {
        StringBuilder sb;
        boolean isAVIFWhitePageWithPng = isAVIFWhitePageWithPng();
        boolean isAVIFWhitePage = isAVIFWhitePage(str2);
        if (isAVIFWhitePageWithPng) {
            if (isAVIFWhitePage && !TextUtils.isEmpty(str) && str.startsWith("http") && !z && !str.endsWith(".avif") && isAVIFWhitePageEnable() && !str.endsWith(".gif") && isAVIFWhitePageImageHost(str)) {
                str = str + ".avif";
                sb = new StringBuilder();
                sb.append("trans url");
                sb.append(str);
                FLog.i(TAG, sb.toString());
            }
        } else if (isAVIFWhitePage && !TextUtils.isEmpty(str) && str.startsWith("http") && !z && !str.endsWith(".avif") && isAVIFWhitePageEnable() && !str.endsWith(".gif") && !str.contains(".png") && isAVIFWhitePageImageHost(str)) {
            str = str + ".avif";
            sb = new StringBuilder();
            sb.append("trans url");
            sb.append(str);
            FLog.i(TAG, sb.toString());
        }
        return str;
    }

    public static boolean isAVIFBitmapConvertEnable() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "avifBitmapConvertEnable", "0"));
    }

    public static boolean isAVIFBitmapConvertOnlyAlphaEnable() {
        return TextUtils.equals("2", JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "avifBitmapConvertEnable", "0"));
    }

    public static boolean isAVIFEnable() {
        boolean equals = TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "avifEnable", "0"));
        if (Build.VERSION.SDK_INT < 23 || !Process.is64Bit()) {
            FLog.w(TAG, "32\u4f4d\u5e94\u7528");
            return (TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "is64Only", "0")) ^ true) && equals;
        }
        FLog.w(TAG, "64\u4f4d\u5e94\u7528");
        return equals;
    }

    public static boolean isAVIFRetry() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "avifRetry", "0"));
    }

    public static boolean isAVIFSupport() {
        Boolean bool = isAVIFSupport;
        if (bool != null) {
            return bool.booleanValue();
        }
        synchronized (AvifDecoderUtil.class) {
            Boolean bool2 = isAVIFSupport;
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            InputStream inputStream = null;
            try {
                InputStream open = JdImageToolKit.getContext().getAssets().open("avif_check.avif");
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(open.available());
                Channels.newChannel(open).read(allocateDirect);
                allocateDirect.flip();
                if (!AvifDecoder.a(allocateDirect)) {
                    isAVIFSupport = Boolean.FALSE;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return false;
                }
                AvifDecoder.Info info = new AvifDecoder.Info();
                if (!AvifDecoder.getInfo(allocateDirect, allocateDirect.remaining(), info)) {
                    isAVIFSupport = Boolean.FALSE;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return false;
                }
                Bitmap createBitmap = Bitmap.createBitmap(info.width, info.height, Bitmap.Config.ARGB_8888);
                isAVIFSupport = Boolean.valueOf(AvifDecoder.decode(allocateDirect, allocateDirect.remaining(), createBitmap));
                if (!createBitmap.isRecycled()) {
                    createBitmap.recycle();
                }
                boolean booleanValue = isAVIFSupport.booleanValue();
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return booleanValue;
            } catch (Exception e5) {
                e5.printStackTrace();
                isAVIFSupport = Boolean.FALSE;
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return isAVIFSupport.booleanValue();
            }
        }
    }

    private static boolean isAVIFWhitePage(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.contains(DYConstants.DY_REGEX_AT)) {
            str = str.substring(0, str.indexOf(DYConstants.DY_REGEX_AT));
        }
        if (avifWhitePageList == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            String config = JDMobileConfig.getInstance().getConfig("JDImageAvif", "addAvifWhitePage", "pages");
            if (!TextUtils.isEmpty(config)) {
                try {
                    JSONArray jSONArray = new JSONArray(config);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        arrayList.add(jSONArray.optString(i2));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    FLog.e(TAG, "AVIF_WHITE_PAGE_LIST:" + config);
                }
            }
            String config2 = JDMobileConfig.getInstance().getConfig("JDImageAvif", "addAvifGrayPage", "pages");
            if (!TextUtils.isEmpty(config2)) {
                try {
                    JSONArray jSONArray2 = new JSONArray(config2);
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        arrayList.add(jSONArray2.optString(i3));
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    FLog.e(TAG, "AVIF_GRAY_PAGE_LIST:" + config2);
                }
            }
            avifWhitePageList = arrayList;
        }
        return avifWhitePageList.contains(str);
    }

    private static boolean isAVIFWhitePageEnable() {
        return JdImageToolKit.getEngine().getAvifWhitePageEnable().isAVIFWhitePageEnable();
    }

    private static boolean isAVIFWhitePageImageHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            String host = new URL(str).getHost();
            if (avifWhitePageImageHostList == null) {
                ArrayList<String> arrayList = new ArrayList<>();
                String config = JDMobileConfig.getInstance().getConfig("JDImageAvif", "addAvifWhitePage", "hosts");
                if (!TextUtils.isEmpty(config)) {
                    try {
                        JSONArray jSONArray = new JSONArray(config);
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            arrayList.add(jSONArray.optString(i2));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        FLog.e(TAG, "AVIF_WHITE_PAGE_IMAGE_HOST_LIST:" + config);
                    }
                }
                String config2 = JDMobileConfig.getInstance().getConfig("JDImageAvif", "addAvifGrayPage", "hosts");
                if (!TextUtils.isEmpty(config2)) {
                    try {
                        JSONArray jSONArray2 = new JSONArray(config2);
                        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                            arrayList.add(jSONArray2.optString(i3));
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        FLog.e(TAG, "AVIF_GRAY_PAGE_IMAGE_HOST_LIST:" + config2);
                    }
                }
                avifWhitePageImageHostList = arrayList;
            }
            return avifWhitePageImageHostList.contains(host);
        } catch (MalformedURLException e4) {
            FLog.e(TAG, "isAVIFWhitePageImageHost:" + e4.getMessage());
            return false;
        }
    }

    private static boolean isAVIFWhitePageWithPng() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "isAVIFWhitePageWithPng", "0"));
    }

    public static boolean isAddAVIFHeaderAccept() {
        return isAVIFEnable() && TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDImageAvif", "avifConfig", "avifAddHeaderAccept", "0")) && isHasAddAVIFDecoder();
    }

    public static boolean isHasAddAVIFDecoder() {
        return hasAddAVIFDecoder;
    }

    public static void setHasAddAVIFDecoder(boolean z) {
        hasAddAVIFDecoder = z;
    }
}
