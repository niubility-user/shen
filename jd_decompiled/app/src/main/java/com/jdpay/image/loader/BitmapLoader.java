package com.jdpay.image.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes18.dex */
public class BitmapLoader {
    public static Bitmap loadOnFileDescriptor(String str, BitmapFactory.Options options) throws FileNotFoundException {
        return loadOnFileDescriptor(new File(str), options);
    }

    public static Bitmap loadWithScaleWidth(File file, BitmapFactory.Options options, int i2, int i3) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            try {
                Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
                int height = (decodeFileDescriptor.getHeight() * i2) / decodeFileDescriptor.getWidth();
                Bitmap createBitmap = Bitmap.createBitmap(Bitmap.createScaledBitmap(decodeFileDescriptor, i2, height, false), 0, 0, i2, Math.min(height, i3));
                try {
                    fileInputStream.close();
                } catch (IOException unused) {
                }
                return createBitmap;
            } catch (IOException e2) {
                e2.printStackTrace();
                Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
                try {
                    fileInputStream.close();
                } catch (IOException unused2) {
                }
                return decodeStream;
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (IOException unused3) {
            }
            throw th;
        }
    }

    public static Bitmap loadOnFileDescriptor(File file, BitmapFactory.Options options) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            try {
                Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
                try {
                    fileInputStream.close();
                } catch (IOException unused) {
                }
                return decodeFileDescriptor;
            } catch (IOException e2) {
                e2.printStackTrace();
                Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
                try {
                    fileInputStream.close();
                } catch (IOException unused2) {
                }
                return decodeStream;
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (IOException unused3) {
            }
            throw th;
        }
    }
}
