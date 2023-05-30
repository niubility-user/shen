package com.jd.mobile.image.utils;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import com.caverock.androidsvg.h;
import com.caverock.androidsvg.k;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatCheckerUtils;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;
import javax.annotation.Nullable;

/* loaded from: classes17.dex */
public class e {
    public static final ImageFormat a = new ImageFormat("SVG_FORMAT", "svg");
    private static final byte[][] b = {ImageFormatCheckerUtils.asciiBytes("<?xml")};

    /* loaded from: classes17.dex */
    public static class a extends CloseableImage {

        /* renamed from: g  reason: collision with root package name */
        private final h f6849g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f6850h = false;

        public a(h hVar) {
            this.f6849g = hVar;
        }

        @Override // com.facebook.imagepipeline.image.CloseableImage, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f6850h = true;
        }

        public h f() {
            return this.f6849g;
        }

        @Override // com.facebook.imagepipeline.image.ImageInfo
        public int getHeight() {
            return 0;
        }

        @Override // com.facebook.imagepipeline.image.CloseableImage
        public int getSizeInBytes() {
            return 0;
        }

        @Override // com.facebook.imagepipeline.image.ImageInfo
        public int getWidth() {
            return 0;
        }

        @Override // com.facebook.imagepipeline.image.CloseableImage
        public boolean isClosed() {
            return this.f6850h;
        }
    }

    /* loaded from: classes17.dex */
    public static class b implements ImageDecoder {
        @Override // com.facebook.imagepipeline.decoder.ImageDecoder
        public CloseableImage decode(EncodedImage encodedImage, int i2, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
            try {
                return new a(h.h(encodedImage.getInputStream()));
            } catch (k e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* loaded from: classes17.dex */
    public static class c implements DrawableFactory {
        @Override // com.facebook.imagepipeline.drawable.DrawableFactory
        @Nullable
        public Drawable createDrawable(CloseableImage closeableImage) {
            return new C0211e(((a) closeableImage).f());
        }

        @Override // com.facebook.imagepipeline.drawable.DrawableFactory
        public boolean supportsImageType(CloseableImage closeableImage) {
            return closeableImage instanceof a;
        }
    }

    /* loaded from: classes17.dex */
    public static class d implements ImageFormat.FormatChecker {
        public static final byte[] a = ImageFormatCheckerUtils.asciiBytes("<svg");

        @Override // com.facebook.imageformat.ImageFormat.FormatChecker
        @Nullable
        public ImageFormat determineFormat(byte[] bArr, int i2) {
            if (i2 < getHeaderSize()) {
                return null;
            }
            if (ImageFormatCheckerUtils.startsWithPattern(bArr, a)) {
                return e.a;
            }
            for (byte[] bArr2 : e.b) {
                if (ImageFormatCheckerUtils.startsWithPattern(bArr, bArr2)) {
                    int length = bArr.length;
                    byte[] bArr3 = a;
                    if (ImageFormatCheckerUtils.indexOfPattern(bArr, length, bArr3, bArr3.length) > -1) {
                        return e.a;
                    }
                }
            }
            return null;
        }

        @Override // com.facebook.imageformat.ImageFormat.FormatChecker
        public int getHeaderSize() {
            return a.length;
        }
    }

    /* renamed from: com.jd.mobile.image.utils.e$e  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public static class C0211e extends PictureDrawable {
        private final h a;

        public C0211e(h hVar) {
            super(null);
            this.a = hVar;
        }

        @Override // android.graphics.drawable.Drawable
        protected void onBoundsChange(Rect rect) {
            super.onBoundsChange(rect);
            setPicture(this.a.n(rect.width(), rect.height()));
        }
    }
}
