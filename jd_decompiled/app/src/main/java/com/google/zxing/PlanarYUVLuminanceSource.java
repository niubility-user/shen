package com.google.zxing;

import com.jingdong.common.unification.navigationbar.NavigationConstants;

/* loaded from: classes12.dex */
public final class PlanarYUVLuminanceSource extends LuminanceSource {
    private static final int THUMBNAIL_SCALE_FACTOR = 2;
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final int top;
    private final byte[] yuvData;

    public PlanarYUVLuminanceSource(byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        super(i6, i7);
        if (i4 + i6 <= i2 && i5 + i7 <= i3) {
            this.yuvData = bArr;
            this.dataWidth = i2;
            this.dataHeight = i3;
            this.left = i4;
            this.top = i5;
            if (z) {
                reverseHorizontal(i6, i7);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
    }

    private void reverseHorizontal(int i2, int i3) {
        byte[] bArr = this.yuvData;
        int i4 = (this.top * this.dataWidth) + this.left;
        int i5 = 0;
        while (i5 < i3) {
            int i6 = (i2 / 2) + i4;
            int i7 = (i4 + i2) - 1;
            int i8 = i4;
            while (i8 < i6) {
                byte b = bArr[i8];
                bArr[i8] = bArr[i7];
                bArr[i7] = b;
                i8++;
                i7--;
            }
            i5++;
            i4 += this.dataWidth;
        }
    }

    @Override // com.google.zxing.LuminanceSource
    public LuminanceSource crop(int i2, int i3, int i4, int i5) {
        return new PlanarYUVLuminanceSource(this.yuvData, this.dataWidth, this.dataHeight, this.left + i2, this.top + i3, i4, i5, false);
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int i2 = this.dataWidth;
        if (width == i2 && height == this.dataHeight) {
            return this.yuvData;
        }
        int i3 = width * height;
        byte[] bArr = new byte[i3];
        int i4 = (this.top * i2) + this.left;
        if (width == i2) {
            System.arraycopy(this.yuvData, i4, bArr, 0, i3);
            return bArr;
        }
        byte[] bArr2 = this.yuvData;
        for (int i5 = 0; i5 < height; i5++) {
            System.arraycopy(bArr2, i4, bArr, i5 * width, width);
            i4 += this.dataWidth;
        }
        return bArr;
    }

    @Override // com.google.zxing.LuminanceSource
    public byte[] getRow(int i2, byte[] bArr) {
        if (i2 >= 0 && i2 < getHeight()) {
            int width = getWidth();
            if (bArr == null || bArr.length < width) {
                bArr = new byte[width];
            }
            System.arraycopy(this.yuvData, ((i2 + this.top) * this.dataWidth) + this.left, bArr, 0, width);
            return bArr;
        }
        throw new IllegalArgumentException("Requested row is outside the image: " + i2);
    }

    public int getThumbnailHeight() {
        return getHeight() / 2;
    }

    public int getThumbnailWidth() {
        return getWidth() / 2;
    }

    @Override // com.google.zxing.LuminanceSource
    public boolean isCropSupported() {
        return true;
    }

    public int[] renderThumbnail() {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int[] iArr = new int[width * height];
        byte[] bArr = this.yuvData;
        int i2 = (this.top * this.dataWidth) + this.left;
        for (int i3 = 0; i3 < height; i3++) {
            int i4 = i3 * width;
            for (int i5 = 0; i5 < width; i5++) {
                iArr[i4 + i5] = ((bArr[(i5 * 2) + i2] & 255) * NavigationConstants.ACTION_SEARCH) | (-16777216);
            }
            i2 += this.dataWidth * 2;
        }
        return iArr;
    }
}
