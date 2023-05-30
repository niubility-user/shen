package com.jd.lib.productdetail.tradein.time;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInSelectWishTimesResp {
    public String code;
    public Data data;

    /* loaded from: classes16.dex */
    public static final class Data {
        public List<PromiseDateItem> promiseDates;

        /* loaded from: classes16.dex */
        public static final class PromiseDateItem implements Parcelable {
            public static final Parcelable.Creator<PromiseDateItem> CREATOR = new Parcelable.Creator<PromiseDateItem>() { // from class: com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimesResp.Data.PromiseDateItem.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PromiseDateItem createFromParcel(Parcel parcel) {
                    return new PromiseDateItem(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public PromiseDateItem[] newArray(int i2) {
                    return new PromiseDateItem[i2];
                }
            };
            public String dayOfWeek;
            public String displayText;
            public String recycleDate;
            public int selected;
            public String shortRecycleDate;
            public List<TimeMomentItem> timeMoments;

            /* loaded from: classes16.dex */
            public static final class TimeMomentItem implements Parcelable {
                public static final Parcelable.Creator<TimeMomentItem> CREATOR = new Parcelable.Creator<TimeMomentItem>() { // from class: com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public TimeMomentItem createFromParcel(Parcel parcel) {
                        return new TimeMomentItem(parcel);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // android.os.Parcelable.Creator
                    public TimeMomentItem[] newArray(int i2) {
                        return new TimeMomentItem[i2];
                    }
                };
                public String displayText;
                public String endTime;
                public int selected;
                public String startTime;
                public int state;

                public TimeMomentItem() {
                }

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public void readFromParcel(Parcel parcel) {
                    this.selected = parcel.readInt();
                    this.state = parcel.readInt();
                    this.startTime = parcel.readString();
                    this.endTime = parcel.readString();
                    this.displayText = parcel.readString();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i2) {
                    parcel.writeInt(this.selected);
                    parcel.writeInt(this.state);
                    parcel.writeString(this.startTime);
                    parcel.writeString(this.endTime);
                    parcel.writeString(this.displayText);
                }

                public TimeMomentItem(Parcel parcel) {
                    this.selected = parcel.readInt();
                    this.state = parcel.readInt();
                    this.startTime = parcel.readString();
                    this.endTime = parcel.readString();
                    this.displayText = parcel.readString();
                }
            }

            public PromiseDateItem() {
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public void readFromParcel(Parcel parcel) {
                this.recycleDate = parcel.readString();
                this.shortRecycleDate = parcel.readString();
                this.displayText = parcel.readString();
                this.dayOfWeek = parcel.readString();
                this.selected = parcel.readInt();
                this.timeMoments = parcel.createTypedArrayList(TimeMomentItem.CREATOR);
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeString(this.recycleDate);
                parcel.writeString(this.shortRecycleDate);
                parcel.writeString(this.displayText);
                parcel.writeString(this.dayOfWeek);
                parcel.writeInt(this.selected);
                parcel.writeTypedList(this.timeMoments);
            }

            public PromiseDateItem(Parcel parcel) {
                this.recycleDate = parcel.readString();
                this.shortRecycleDate = parcel.readString();
                this.displayText = parcel.readString();
                this.dayOfWeek = parcel.readString();
                this.selected = parcel.readInt();
                this.timeMoments = parcel.createTypedArrayList(TimeMomentItem.CREATOR);
            }
        }
    }
}
