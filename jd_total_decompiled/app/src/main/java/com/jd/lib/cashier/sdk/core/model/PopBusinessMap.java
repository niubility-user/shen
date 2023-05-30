package com.jd.lib.cashier.sdk.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.lib.cashier.sdk.core.utils.g0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class PopBusinessMap implements Parcelable, ICheckNullObj {
    public static final Parcelable.Creator<PopBusinessMap> CREATOR = new Parcelable.Creator<PopBusinessMap>() { // from class: com.jd.lib.cashier.sdk.core.model.PopBusinessMap.1
        @Override // android.os.Parcelable.Creator
        public PopBusinessMap createFromParcel(Parcel parcel) {
            return new PopBusinessMap(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public PopBusinessMap[] newArray(int i2) {
            return new PopBusinessMap[i2];
        }
    };
    public PrismResult abTest;
    public String app_pin;
    public PopConfirmBtnMta confirmButtonMTA;
    public GuideInfo guideInfo;
    public String leaveto;
    public String newUserGeneralized;
    public String orderId;
    public String popType;
    public String recomInfo;
    public List<ExitRetainOptionEntity> retainBtnList;
    public List<PlanRowEntity> table;
    public String tip;
    public String touchstoneExpIds;
    public String user_pin;

    public PopBusinessMap() {
        this.touchstoneExpIds = "";
        this.recomInfo = "";
        this.newUserGeneralized = "";
        this.user_pin = "";
        this.app_pin = "";
        this.orderId = "";
        this.leaveto = "";
    }

    private void checkGuideInfo() {
        if (this.guideInfo == null) {
            this.guideInfo = new GuideInfo();
        }
        this.guideInfo.checkNullObjAndInit();
    }

    private void checkPrismResult() {
        if (this.abTest == null) {
            this.abTest = new PrismResult();
        }
        this.abTest.checkNullObjAndInit();
    }

    private void checkRetainBtnList() {
        if (this.retainBtnList == null) {
            this.retainBtnList = new ArrayList();
        }
        g0.f(this.retainBtnList);
    }

    private void checkTableList() {
        if (this.table == null) {
            this.table = new ArrayList();
        }
        g0.f(this.table);
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        checkTableList();
        checkGuideInfo();
        checkRetainBtnList();
        checkPrismResult();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.guideInfo, i2);
        parcel.writeParcelable(this.abTest, i2);
        parcel.writeParcelable(this.confirmButtonMTA, i2);
        parcel.writeTypedList(this.retainBtnList);
        parcel.writeTypedList(this.table);
        parcel.writeString(this.tip);
        parcel.writeString(this.popType);
        parcel.writeString(this.touchstoneExpIds);
        parcel.writeString(this.recomInfo);
        parcel.writeString(this.newUserGeneralized);
        parcel.writeString(this.user_pin);
        parcel.writeString(this.app_pin);
        parcel.writeString(this.orderId);
        parcel.writeString(this.leaveto);
    }

    protected PopBusinessMap(Parcel parcel) {
        this.touchstoneExpIds = "";
        this.recomInfo = "";
        this.newUserGeneralized = "";
        this.user_pin = "";
        this.app_pin = "";
        this.orderId = "";
        this.leaveto = "";
        this.guideInfo = (GuideInfo) parcel.readParcelable(GuideInfo.class.getClassLoader());
        this.abTest = (PrismResult) parcel.readParcelable(PrismResult.class.getClassLoader());
        this.confirmButtonMTA = (PopConfirmBtnMta) parcel.readParcelable(PopConfirmBtnMta.class.getClassLoader());
        this.retainBtnList = parcel.createTypedArrayList(ExitRetainOptionEntity.CREATOR);
        this.table = parcel.createTypedArrayList(PlanRowEntity.CREATOR);
        this.tip = parcel.readString();
        this.popType = parcel.readString();
        this.touchstoneExpIds = parcel.readString();
        this.recomInfo = parcel.readString();
        this.newUserGeneralized = parcel.readString();
        this.user_pin = parcel.readString();
        this.app_pin = parcel.readString();
        this.orderId = parcel.readString();
        this.leaveto = parcel.readString();
    }
}
