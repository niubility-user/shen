package com.jdcloud.vsr;

import android.graphics.Bitmap;
import com.jdcloud.vsr.geometry.IntRectangle;
import com.jdcloud.vsr.imaging.PixelFormat;

/* loaded from: classes18.dex */
public class JDTBitmap extends JDTObject {
    protected JDTContext context;

    /* loaded from: classes18.dex */
    public static class BadPixelFormat extends IllegalArgumentException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public BadPixelFormat(android.graphics.Bitmap.Config r3) {
            /*
                r2 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Pixel format not supported: "
                r0.append(r1)
                if (r3 != 0) goto Lf
                java.lang.String r3 = "config is null"
                goto L13
            Lf:
                java.lang.String r3 = r3.toString()
            L13:
                r0.append(r3)
                java.lang.String r3 = r0.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jdcloud.vsr.JDTBitmap.BadPixelFormat.<init>(android.graphics.Bitmap$Config):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JDTBitmap(JDTContext jDTContext, long j2) {
        super(j2);
        this.context = jDTContext;
        jDTContext.watchBitmap(this);
    }

    static JDTBitmap createByteAligned(JDTContext jDTContext, int i2, int i3, PixelFormat pixelFormat) {
        int bitsPerPixel = 8 / pixelFormat.getBitsPerPixel();
        if (bitsPerPixel > 1) {
            i2 = (((i2 + bitsPerPixel) - 1) / bitsPerPixel) * bitsPerPixel;
        }
        return new JDTBitmap(jDTContext, i2, i3, pixelFormat);
    }

    public static JDTBitmap createEmpty(JDTBitmap jDTBitmap) {
        return new JDTBitmap(jDTBitmap.context, jDTBitmap.getWidth(), jDTBitmap.getHeight(), jDTBitmap.getPixelFormat());
    }

    private static long createNativeBitmapEnsuringPixelFormat(JDTContext jDTContext, Bitmap bitmap) throws BadPixelFormat {
        if (bitmap.getConfig() != Bitmap.Config.ALPHA_8 && bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            throw new BadPixelFormat(bitmap.getConfig());
        }
        return newNativeBitmap(jDTContext, bitmap);
    }

    private native void crop(long j2, long j3, int i2, int i3, int i4, int i5, int i6, int i7);

    private native int getHeight(long j2);

    private native int getPixelFormat(long j2);

    private native int getWidth(long j2);

    private static native void invert(long j2);

    private static native long newInternalBitmap(JDTContext jDTContext, int i2, int i3, int i4);

    protected static native long newNativeBitmap(JDTContext jDTContext, Bitmap bitmap);

    protected static native void pullPixels(long j2);

    public static void recycle(JDTBitmap jDTBitmap) {
        if (jDTBitmap != null) {
            jDTBitmap.dispose();
        }
    }

    private native void zero(long j2);

    public IntRectangle clientRectangle() {
        return new IntRectangle(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public JDTBitmap copyRegion(IntRectangle intRectangle) {
        JDTBitmap createByteAligned = createByteAligned(this.context, intRectangle.getWidth(), intRectangle.getHeight(), getPixelFormat());
        if (createByteAligned.getWidth() != intRectangle.getWidth()) {
            createByteAligned.zero();
        }
        crop(this.handle, createByteAligned.handle, intRectangle.x1, intRectangle.y1, intRectangle.x2, intRectangle.y2, 0, 0);
        return createByteAligned;
    }

    public int getHeight() {
        return getHeight(this.handle);
    }

    public PixelFormat getPixelFormat() {
        return PixelFormat.values()[getPixelFormat(this.handle)];
    }

    public int getWidth() {
        return getWidth(this.handle);
    }

    public void invert() {
        invert(this.handle);
    }

    public void projectOn(JDTBitmap jDTBitmap, int i2, int i3) {
        crop(this.handle, jDTBitmap.handle, i2, i3, i2 + jDTBitmap.getWidth(), i3 + jDTBitmap.getHeight(), 0, 0);
    }

    public void pullPixels() {
        pullPixels(this.handle);
    }

    public void zero() {
        zero(this.handle);
    }

    @Override // 
    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public JDTBitmap mo19clone() {
        return this.context.copyBitmap(this, getPixelFormat());
    }

    public JDTBitmap clone(PixelFormat pixelFormat) {
        return this.context.copyBitmap(this, pixelFormat);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JDTBitmap(JDTContext jDTContext, Bitmap bitmap) throws BadPixelFormat {
        super(createNativeBitmapEnsuringPixelFormat(jDTContext, bitmap));
        this.context = jDTContext;
        jDTContext.watchBitmap(this);
    }

    public JDTBitmap(JDTContext jDTContext, int i2, int i3, PixelFormat pixelFormat) {
        super(newInternalBitmap(jDTContext, i2, i3, pixelFormat.ordinal()));
        this.context = jDTContext;
        jDTContext.watchBitmap(this);
    }
}
