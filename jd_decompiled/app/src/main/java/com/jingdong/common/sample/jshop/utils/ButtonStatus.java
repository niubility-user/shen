package com.jingdong.common.sample.jshop.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.app.mall.R;

/* loaded from: classes6.dex */
public class ButtonStatus implements Parcelable {
    public static final Parcelable.Creator<ButtonStatus> CREATOR = new Parcelable.Creator<ButtonStatus>() { // from class: com.jingdong.common.sample.jshop.utils.ButtonStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ButtonStatus createFromParcel(Parcel parcel) {
            return new ButtonStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ButtonStatus[] newArray(int i2) {
            return new ButtonStatus[i2];
        }
    };
    public int btnBgId;
    public int mStatus;
    public int txtColor;
    public String txtString;

    public ButtonStatus() {
        this.txtString = "";
        this.mStatus = 11002;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setStatus(int i2) {
        switch (i2) {
            case 11002:
                this.btnBgId = R.drawable.jshop_seckill_butn_remind_selector;
                this.txtColor = R.color.lx;
                this.txtString = "\u63d0\u9192\u6211";
                break;
            case 11003:
                this.btnBgId = R.drawable.jshop_seckill_butn_remind_cencel_selector;
                this.txtColor = R.color.jshop_ms_remind_txt;
                this.txtString = "\u5df2\u8bbe\u7f6e\u63d0\u9192";
                break;
            case 11004:
                this.btnBgId = R.drawable.jshop_seckill_butn_selector;
                this.txtColor = R.color.lx;
                this.txtString = "\u7acb\u5373\u62a2\u8d2d";
                break;
            case 11005:
                this.btnBgId = R.drawable.jshop_seckill_butn_more_selector;
                this.txtColor = R.color.jshop_home_tab_indicator;
                this.txtString = "\u5176\u5b83\u4fc3\u9500";
                break;
            default:
                this.btnBgId = R.drawable.jshop_seckill_butn_selector;
                this.txtColor = R.color.lx;
                this.txtString = "\u7acb\u5373\u62a2\u8d2d";
                break;
        }
        this.mStatus = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.btnBgId);
        parcel.writeInt(this.txtColor);
        parcel.writeString(this.txtString);
        parcel.writeInt(this.mStatus);
    }

    public ButtonStatus(int i2) {
        this.txtString = "";
        this.mStatus = 11002;
        setStatus(i2);
    }

    protected ButtonStatus(Parcel parcel) {
        this.txtString = "";
        this.mStatus = 11002;
        this.btnBgId = parcel.readInt();
        this.txtColor = parcel.readInt();
        this.txtString = parcel.readString();
        this.mStatus = parcel.readInt();
    }
}
