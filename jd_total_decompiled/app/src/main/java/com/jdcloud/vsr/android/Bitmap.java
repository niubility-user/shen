package com.jdcloud.vsr.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.jdcloud.vsr.JDTBitmap;
import com.jdcloud.vsr.JDTContext;
import java.io.FileDescriptor;
import java.io.InputStream;

/* loaded from: classes18.dex */
public class Bitmap extends JDTBitmap {
    private android.graphics.Bitmap source;

    private Bitmap(JDTContext jDTContext, android.graphics.Bitmap bitmap) {
        super(jDTContext, bitmap);
        this.source = bitmap;
    }

    public static Bitmap createColorBitmap(JDTContext jDTContext, int i2, int i3) {
        return new Bitmap(jDTContext, android.graphics.Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888));
    }

    private static Bitmap createEnsuringPixelFormat(JDTContext jDTContext, android.graphics.Bitmap bitmap) {
        if (bitmap.getConfig() != Bitmap.Config.ALPHA_8) {
            Bitmap.Config config = bitmap.getConfig();
            Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
            if (config != config2) {
                android.graphics.Bitmap copy = bitmap.copy(config2, true);
                bitmap.recycle();
                return new Bitmap(jDTContext, copy);
            }
        }
        return new Bitmap(jDTContext, bitmap);
    }

    public static Bitmap createGrayscaleBitmap(JDTContext jDTContext, int i2, int i3) {
        return new Bitmap(jDTContext, android.graphics.Bitmap.createBitmap(i2, i3, Bitmap.Config.ALPHA_8));
    }

    public static Bitmap decodeFile(JDTContext jDTContext, String str, BitmapFactory.Options options) throws OutOfMemoryError {
        android.graphics.Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile == null) {
            return null;
        }
        return createEnsuringPixelFormat(jDTContext, decodeFile);
    }

    public static Bitmap decodeFileDescriptor(JDTContext jDTContext, FileDescriptor fileDescriptor, Rect rect, BitmapFactory.Options options) throws OutOfMemoryError {
        android.graphics.Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(fileDescriptor, rect, options);
        if (decodeFileDescriptor == null) {
            return null;
        }
        return createEnsuringPixelFormat(jDTContext, decodeFileDescriptor);
    }

    public static Bitmap decodeStream(JDTContext jDTContext, InputStream inputStream) throws OutOfMemoryError {
        android.graphics.Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
        if (decodeStream == null) {
            return null;
        }
        return createEnsuringPixelFormat(jDTContext, decodeStream);
    }

    @Override // com.jdcloud.vsr.JDTObject
    public synchronized void dispose() {
        android.graphics.Bitmap bitmap = this.source;
        if (bitmap != null) {
            bitmap.recycle();
            this.source = null;
        }
        super.dispose();
    }

    public android.graphics.Bitmap getBitmap() {
        return this.source;
    }

    @Override // com.jdcloud.vsr.JDTBitmap
    /* renamed from: clone */
    public Bitmap mo19clone() {
        JDTContext jDTContext = this.context;
        android.graphics.Bitmap bitmap = this.source;
        return new Bitmap(jDTContext, bitmap.copy(bitmap.getConfig(), true));
    }
}
