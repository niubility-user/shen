package com.jingdong.manto.launch;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class UIConfig implements Parcelable {
    public static final Parcelable.Creator<UIConfig> CREATOR = new a();
    private boolean hideCapsule;
    private boolean hideNavigationBar;
    private boolean hideSplash;
    private boolean hideTabBar;

    /* loaded from: classes15.dex */
    public static class Builder {
        private boolean hideCapsule;
        private boolean hideNavigationBar;
        private boolean hideSplash;
        private boolean hideTabBar;

        public UIConfig build() {
            UIConfig uIConfig = new UIConfig((a) null);
            uIConfig.hideSplash = this.hideSplash;
            uIConfig.hideNavigationBar = this.hideNavigationBar;
            uIConfig.hideTabBar = this.hideTabBar;
            uIConfig.hideCapsule = this.hideCapsule;
            return uIConfig;
        }

        public Builder setHideCapsule(boolean z) {
            this.hideCapsule = z;
            return this;
        }

        public Builder setHideNavigationBar(boolean z) {
            this.hideNavigationBar = z;
            return this;
        }

        public Builder setHideSplash(boolean z) {
            this.hideSplash = z;
            return this;
        }

        public Builder setHideTabBar(boolean z) {
            this.hideTabBar = z;
            return this;
        }
    }

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<UIConfig> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public UIConfig createFromParcel(Parcel parcel) {
            return new UIConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public UIConfig[] newArray(int i2) {
            return new UIConfig[i2];
        }
    }

    private UIConfig() {
    }

    protected UIConfig(Parcel parcel) {
        this.hideSplash = parcel.readByte() != 0;
        this.hideNavigationBar = parcel.readByte() != 0;
        this.hideTabBar = parcel.readByte() != 0;
    }

    /* synthetic */ UIConfig(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isHideCapsule() {
        return this.hideCapsule;
    }

    public boolean isHideNavigationBar() {
        return this.hideNavigationBar;
    }

    public boolean isHideSplash() {
        return this.hideSplash;
    }

    public boolean isHideTabBar() {
        return this.hideTabBar;
    }

    public void readFromParcel(Parcel parcel) {
        this.hideSplash = parcel.readByte() != 0;
        this.hideNavigationBar = parcel.readByte() != 0;
        this.hideTabBar = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.hideSplash ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.hideNavigationBar ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.hideTabBar ? (byte) 1 : (byte) 0);
    }
}
