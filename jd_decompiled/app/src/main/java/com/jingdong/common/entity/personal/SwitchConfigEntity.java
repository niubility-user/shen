package com.jingdong.common.entity.personal;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.network.BaseEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class SwitchConfigEntity extends BaseEntity implements Parcelable {
    public static final Parcelable.Creator<SwitchConfigEntity> CREATOR = new Parcelable.Creator<SwitchConfigEntity>() { // from class: com.jingdong.common.entity.personal.SwitchConfigEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SwitchConfigEntity[] newArray(int i2) {
            return new SwitchConfigEntity[0];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SwitchConfigEntity createFromParcel(Parcel parcel) {
            return new SwitchConfigEntity(parcel);
        }
    };
    public String code;
    public List<DycNode> dycNodes;
    public MyJdSetBusiness myjdSetBusiness;
    public Switches switches;

    /* loaded from: classes5.dex */
    public static class MyJdSetBusiness implements Parcelable {
        public static final Parcelable.Creator<MyJdSetBusiness> CREATOR = new Parcelable.Creator<MyJdSetBusiness>() { // from class: com.jingdong.common.entity.personal.SwitchConfigEntity.MyJdSetBusiness.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MyJdSetBusiness createFromParcel(Parcel parcel) {
                return new MyJdSetBusiness(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MyJdSetBusiness[] newArray(int i2) {
                return new MyJdSetBusiness[i2];
            }
        };
        public long forceUpdateTime;
        public long requestInterval;

        public MyJdSetBusiness() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeLong(this.requestInterval);
            parcel.writeLong(this.forceUpdateTime);
        }

        protected MyJdSetBusiness(Parcel parcel) {
            this.requestInterval = parcel.readLong();
            this.forceUpdateTime = parcel.readLong();
        }
    }

    /* loaded from: classes5.dex */
    public static class Switches implements Parcelable {
        public static final Parcelable.Creator<Switches> CREATOR = new Parcelable.Creator<Switches>() { // from class: com.jingdong.common.entity.personal.SwitchConfigEntity.Switches.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Switches[] newArray(int i2) {
                return new Switches[0];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Switches createFromParcel(Parcel parcel) {
                return new Switches(parcel);
            }
        };
        public String acctSettingStyle;
        public int browseHistorySource;
        public String sheZhiAB;
        public boolean showCartFloatIcon;
        public boolean showSelectAllButton;
        public boolean showSimilarProductIcon;

        public Switches() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.browseHistorySource);
            parcel.writeByte(this.showSelectAllButton ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.showCartFloatIcon ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.showSimilarProductIcon ? (byte) 1 : (byte) 0);
            parcel.writeString(this.sheZhiAB);
            parcel.writeString(this.acctSettingStyle);
        }

        protected Switches(Parcel parcel) {
            this.browseHistorySource = parcel.readInt();
            this.showSelectAllButton = parcel.readByte() != 0;
            this.showCartFloatIcon = parcel.readByte() != 0;
            this.showSimilarProductIcon = parcel.readByte() != 0;
            this.sheZhiAB = parcel.readString();
            this.acctSettingStyle = parcel.readString();
        }
    }

    protected SwitchConfigEntity(Parcel parcel) {
        this.code = parcel.readString();
        this.switches = (Switches) parcel.readParcelable(Switches.class.getClassLoader());
        if (this.dycNodes == null) {
            this.dycNodes = new ArrayList();
        }
        parcel.readList(this.dycNodes, DycNode.class.getClassLoader());
        this.myjdSetBusiness = (MyJdSetBusiness) parcel.readParcelable(MyJdSetBusiness.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.code);
        parcel.writeParcelable(this.switches, i2);
        parcel.writeList(this.dycNodes);
        parcel.writeParcelable(this.myjdSetBusiness, i2);
    }

    /* loaded from: classes5.dex */
    public static class DycNode implements Parcelable {
        public static final Parcelable.Creator<DycNode> CREATOR = new Parcelable.Creator<DycNode>() { // from class: com.jingdong.common.entity.personal.SwitchConfigEntity.DycNode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DycNode[] newArray(int i2) {
                return new DycNode[0];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DycNode createFromParcel(Parcel parcel) {
                return new DycNode(parcel);
            }
        };
        public String functionId;
        public String lableName;

        protected DycNode(Parcel parcel) {
            this.functionId = parcel.readString();
            this.lableName = parcel.readString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.functionId);
            parcel.writeString(this.lableName);
        }

        public DycNode() {
        }
    }

    public SwitchConfigEntity() {
    }
}
