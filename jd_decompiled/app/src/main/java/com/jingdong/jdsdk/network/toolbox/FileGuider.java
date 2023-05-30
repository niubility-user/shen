package com.jingdong.jdsdk.network.toolbox;

/* loaded from: classes.dex */
public class FileGuider {
    private long availableSize;
    private String childDirName;
    private String childSndDirName;
    private String fileName;
    private boolean immutable;
    private int internalType;
    private int mode;
    private int space;
    private long totalSize;

    public long getAvailableSize() {
        return this.availableSize;
    }

    public String getChildDirName() {
        return this.childDirName;
    }

    public String getChildSndDirName() {
        return this.childSndDirName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getInternalType() {
        return this.internalType;
    }

    public int getMode() {
        return this.mode;
    }

    public int getSpace() {
        return this.space;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public boolean isImmutable() {
        return this.immutable;
    }

    public void setAvailableSize(long j2) {
        this.availableSize = j2;
    }

    public void setChildDirName(String str) {
        this.childDirName = str;
    }

    public void setChildSndDirName(String str) {
        this.childSndDirName = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setImmutable(boolean z) {
        this.immutable = z;
    }

    public void setInternalType(int i2) {
        this.internalType = i2;
    }

    public void setMode(int i2) {
        this.mode = i2;
    }

    public void setSpace(int i2) {
        this.space = i2;
    }

    public void setTotalSize(long j2) {
        this.totalSize = j2;
    }
}
