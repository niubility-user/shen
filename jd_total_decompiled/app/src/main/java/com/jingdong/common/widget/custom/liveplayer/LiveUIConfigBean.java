package com.jingdong.common.widget.custom.liveplayer;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class LiveUIConfigBean implements Serializable {
    private int mCloseIconSize;
    private int mCloseIconSizeRightMargin;
    private int mCloseIconSizeTopMargin;
    private int mMuteIconSize;
    private int mMuteIconSizeBottomMargin;
    private int mMuteIconSizeRightMargin;
    private int mPlayStatusIconLeftMargin;
    private int mPlayStatusIconSizeHeight;
    private int mPlayStatusIconTopMargin;

    public LiveUIConfigBean(int i2, int i3) {
        this(i2, 6, 6, 12, 6, 6, i3, 6, 6);
    }

    public int getCloseIconSize() {
        return this.mCloseIconSize;
    }

    public int getCloseIconSizeRightMargin() {
        return this.mCloseIconSizeRightMargin;
    }

    public int getCloseIconSizeTopMargin() {
        return this.mCloseIconSizeTopMargin;
    }

    public int getCloseSize() {
        return this.mCloseIconSize;
    }

    public int getMuteIconSize() {
        return this.mMuteIconSize;
    }

    public int getMuteIconSizeBottomMargin() {
        return this.mMuteIconSizeBottomMargin;
    }

    public int getMuteIconSizeRightMargin() {
        return this.mMuteIconSizeRightMargin;
    }

    public int getMuteSize() {
        return this.mMuteIconSize;
    }

    public int getPlayStatusIconLeftMargin() {
        return this.mPlayStatusIconLeftMargin;
    }

    public int getPlayStatusIconSizeHeight() {
        return this.mPlayStatusIconSizeHeight;
    }

    public int getPlayStatusIconTopMargin() {
        return this.mPlayStatusIconTopMargin;
    }

    public void setCloseIconSize(int i2) {
        this.mCloseIconSize = i2;
    }

    public void setCloseIconSizeRightMargin(int i2) {
        this.mCloseIconSizeRightMargin = i2;
    }

    public void setCloseIconSizeTopMargin(int i2) {
        this.mCloseIconSizeTopMargin = i2;
    }

    public void setMuteIconSize(int i2) {
        this.mMuteIconSize = i2;
    }

    public void setMuteIconSizeBottomMargin(int i2) {
        this.mMuteIconSizeBottomMargin = i2;
    }

    public void setMuteIconSizeRightMargin(int i2) {
        this.mMuteIconSizeRightMargin = i2;
    }

    public void setPlayStatusIconLeftMargin(int i2) {
        this.mPlayStatusIconLeftMargin = i2;
    }

    public void setPlayStatusIconSizeHeight(int i2) {
        this.mPlayStatusIconSizeHeight = i2;
    }

    public void setPlayStatusIconTopMargin(int i2) {
        this.mPlayStatusIconTopMargin = i2;
    }

    public LiveUIConfigBean(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.mCloseIconSize = i2;
        this.mCloseIconSizeTopMargin = i3;
        this.mCloseIconSizeRightMargin = i4;
        this.mPlayStatusIconSizeHeight = i5;
        this.mPlayStatusIconTopMargin = i6;
        this.mPlayStatusIconLeftMargin = i7;
        this.mMuteIconSizeRightMargin = i10;
        this.mMuteIconSizeBottomMargin = i9;
        this.mMuteIconSize = i8;
    }
}
