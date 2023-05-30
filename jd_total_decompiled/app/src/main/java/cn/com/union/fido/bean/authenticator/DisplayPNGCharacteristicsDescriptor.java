package cn.com.union.fido.bean.authenticator;

import java.util.List;

/* loaded from: classes.dex */
public class DisplayPNGCharacteristicsDescriptor {
    public short bitDepth;
    public short colorType;
    public short compression;
    public short filter;
    public long height;
    public short interlace;
    public List<RgbPalletteEntry> plte;
    public long width;

    public short getBitDepth() {
        return this.bitDepth;
    }

    public short getColorType() {
        return this.colorType;
    }

    public short getCompression() {
        return this.compression;
    }

    public short getFilter() {
        return this.filter;
    }

    public long getHeight() {
        return this.height;
    }

    public short getInterlace() {
        return this.interlace;
    }

    public List<RgbPalletteEntry> getPlte() {
        return this.plte;
    }

    public long getWidth() {
        return this.width;
    }

    public void setBitDepth(short s) {
        this.bitDepth = s;
    }

    public void setColorType(short s) {
        this.colorType = s;
    }

    public void setCompression(short s) {
        this.compression = s;
    }

    public void setFilter(short s) {
        this.filter = s;
    }

    public void setHeight(long j2) {
        this.height = j2;
    }

    public void setInterlace(short s) {
        this.interlace = s;
    }

    public void setPlte(List<RgbPalletteEntry> list) {
        this.plte = list;
    }

    public void setWidth(long j2) {
        this.width = j2;
    }
}
