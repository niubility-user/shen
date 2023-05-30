package com.jingdong.common.web.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.jingdong.jdsdk.network.utils.Base64;
import java.io.ByteArrayOutputStream;
import org.apache.commons.codec.CharEncoding;

/* loaded from: classes12.dex */
public class BitmapUtils {
    private static final String TAG = "BitmapUtils";

    public static byte[] compressByParameter(String str, int i2, String str2) {
        int i3 = 80;
        try {
            if (Float.parseFloat(str2) > 0.0f && Float.parseFloat(str2) <= 1.0f) {
                i3 = (int) (Float.parseFloat(str2) * 100.0f);
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
        if (i2 > 0) {
            return initPicWithMinLength(str, i2, i3);
        }
        return initPic(str, i3);
    }

    private static int getInSampleSize(int i2, int i3, int i4) {
        return (i2 < i3 ? i2 / i4 : i3 / i4) + 1;
    }

    private static byte[] initPic(String str, int i2) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        byte[] bArr = null;
        if (decodeFile == null) {
            WebUnifiedMtaUtil.sendAlbumMtaWithoutUrl("album_exception", "decodeFile\u5931\u8d25", str);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            decodeFile.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
            bArr = Base64.encodeBytes(byteArrayOutputStream.toByteArray()).getBytes(CharEncoding.US_ASCII);
            decodeFile.recycle();
            byteArrayOutputStream.close();
            return bArr;
        } catch (Exception e2) {
            try {
                decodeFile.recycle();
                byteArrayOutputStream.close();
            } catch (Exception unused) {
            }
            e2.printStackTrace();
            WebUnifiedMtaUtil.sendAlbumMtaWithoutUrl("album_exception", "compress\u5931\u8d25 " + e2.toString(), "");
            return bArr;
        }
    }

    private static byte[] initPicWithMinLength(String str, int i2, int i3) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        byte[] bArr = null;
        if (decodeFile == null) {
            WebUnifiedMtaUtil.sendAlbumMtaWithoutUrl("album_exception", "decodeFile\u5931\u8d25", str);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int width = decodeFile.getWidth();
        int height = decodeFile.getHeight();
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            options.inSampleSize = getInSampleSize(width, height, i2);
            decodeFile = BitmapFactory.decodeFile(str, options);
            decodeFile.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
            bArr = Base64.encodeBytes(byteArrayOutputStream.toByteArray()).getBytes(CharEncoding.US_ASCII);
            decodeFile.recycle();
            byteArrayOutputStream.close();
            return bArr;
        } catch (Exception e2) {
            try {
                decodeFile.recycle();
                byteArrayOutputStream.close();
            } catch (Exception unused) {
            }
            e2.printStackTrace();
            WebUnifiedMtaUtil.sendAlbumMtaWithoutUrl("album_exception", "compress\u5931\u8d25 " + e2.toString(), "");
            return bArr;
        }
    }
}
