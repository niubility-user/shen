package com.jingdong.common.entity.personal;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.entity.personal.HomeConfig;
import java.util.List;

/* loaded from: classes5.dex */
public class GeneralInfo implements Parcelable {
    public static final Parcelable.Creator<GeneralInfo> CREATOR = new Parcelable.Creator<GeneralInfo>() { // from class: com.jingdong.common.entity.personal.GeneralInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GeneralInfo createFromParcel(Parcel parcel) {
            return new GeneralInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GeneralInfo[] newArray(int i2) {
            return new GeneralInfo[i2];
        }
    };
    public int angleStyle;
    public BaseMta callNoteClickMta;
    public List<HomeConfig> childItems;
    public ClickMta clickMta;
    public BaseMta expoMta;
    public String functionId;
    public HomeConfig.ConfigJumpInfo jumpInfo;
    public int jumpStyle;
    public String lableImage;
    public int redDotType;
    public BaseTitle subtitle;
    public BaseTitle title;
    public long updateRedDotTime;

    /* loaded from: classes5.dex */
    public static class BaseMta implements Parcelable {
        public static final Parcelable.Creator<BaseMta> CREATOR = new Parcelable.Creator<BaseMta>() { // from class: com.jingdong.common.entity.personal.GeneralInfo.BaseMta.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BaseMta createFromParcel(Parcel parcel) {
                return new BaseMta(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BaseMta[] newArray(int i2) {
                return new BaseMta[i2];
            }
        };
        public String eventId;
        public String eventParam;
        public String jsonParam;
        public String pageId;
        public String pageParam;

        public BaseMta() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.eventId);
            parcel.writeString(this.eventParam);
            parcel.writeString(this.pageId);
            parcel.writeString(this.pageParam);
            parcel.writeString(this.jsonParam);
        }

        protected BaseMta(Parcel parcel) {
            this.eventId = parcel.readString();
            this.eventParam = parcel.readString();
            this.pageId = parcel.readString();
            this.pageParam = parcel.readString();
            this.jsonParam = parcel.readString();
        }
    }

    /* loaded from: classes5.dex */
    public static class BaseTitle implements Parcelable {
        public static final Parcelable.Creator<BaseTitle> CREATOR = new Parcelable.Creator<BaseTitle>() { // from class: com.jingdong.common.entity.personal.GeneralInfo.BaseTitle.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BaseTitle createFromParcel(Parcel parcel) {
                return new BaseTitle(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BaseTitle[] newArray(int i2) {
                return new BaseTitle[i2];
            }
        };
        public String color;
        public String value;

        public BaseTitle() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.value);
            parcel.writeString(this.color);
        }

        protected BaseTitle(Parcel parcel) {
            this.value = parcel.readString();
            this.color = parcel.readString();
        }
    }

    /* loaded from: classes5.dex */
    public static class ClickMta extends BaseMta implements Parcelable {
        public static final Parcelable.Creator<ClickMta> CREATOR = new Parcelable.Creator<ClickMta>() { // from class: com.jingdong.common.entity.personal.GeneralInfo.ClickMta.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ClickMta createFromParcel(Parcel parcel) {
                return new ClickMta(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ClickMta[] newArray(int i2) {
                return new ClickMta[i2];
            }
        };
        public String pageLevel;

        public ClickMta() {
        }

        @Override // com.jingdong.common.entity.personal.GeneralInfo.BaseMta, android.os.Parcelable
        public int describeContents() {
            return super.describeContents();
        }

        @Override // com.jingdong.common.entity.personal.GeneralInfo.BaseMta, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.pageLevel);
        }

        protected ClickMta(Parcel parcel) {
            super(parcel);
            this.pageLevel = parcel.readString();
        }
    }

    public GeneralInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isRedDotFlag() {
        return this.redDotType != 0;
    }

    public boolean styleCheckBox() {
        return this.jumpStyle == 2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.functionId);
        parcel.writeParcelable(this.title, i2);
        parcel.writeParcelable(this.subtitle, i2);
        parcel.writeTypedList(this.childItems);
        parcel.writeParcelable(this.jumpInfo, i2);
        parcel.writeInt(this.jumpStyle);
        parcel.writeInt(this.redDotType);
        parcel.writeLong(this.updateRedDotTime);
        parcel.writeParcelable(this.clickMta, i2);
        parcel.writeParcelable(this.expoMta, i2);
        parcel.writeParcelable(this.callNoteClickMta, i2);
        parcel.writeString(this.lableImage);
    }

    protected GeneralInfo(Parcel parcel) {
        this.functionId = parcel.readString();
        this.title = (BaseTitle) parcel.readParcelable(BaseTitle.class.getClassLoader());
        this.subtitle = (BaseTitle) parcel.readParcelable(BaseTitle.class.getClassLoader());
        this.childItems = parcel.createTypedArrayList(HomeConfig.CREATOR);
        this.jumpInfo = (HomeConfig.ConfigJumpInfo) parcel.readParcelable(HomeConfig.ConfigJumpInfo.class.getClassLoader());
        this.jumpStyle = parcel.readInt();
        this.redDotType = parcel.readInt();
        this.updateRedDotTime = parcel.readLong();
        this.clickMta = (ClickMta) parcel.readParcelable(ClickMta.class.getClassLoader());
        this.expoMta = (BaseMta) parcel.readParcelable(BaseMta.class.getClassLoader());
        this.callNoteClickMta = (BaseMta) parcel.readParcelable(BaseMta.class.getClassLoader());
        this.lableImage = parcel.readString();
    }
}
