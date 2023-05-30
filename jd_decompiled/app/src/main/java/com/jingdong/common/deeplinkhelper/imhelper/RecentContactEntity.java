package com.jingdong.common.deeplinkhelper.imhelper;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class RecentContactEntity implements Cloneable, Parcelable {
    public String avatar;
    public String bbtf;
    public int buId;
    public int businessType;
    public int channelId;
    public int channelTag;
    public String jumpLink;
    public String lastMsg;
    public long lastMsgDate;
    public String lastMsgId;
    public String name;
    public int noDisturbTotalCount;
    public int opt;
    public String pid;
    public String sessionId;
    public int sessionType;
    public String toApp;
    public String toPin;
    public long topTimestamp;
    public int totalCount;
    public Transmission transmission;
    public int unread;
    public String venderQuality;
    public String venderType;
    private static final String TAG = RecentContactEntity.class.getSimpleName();
    public static final Parcelable.Creator<RecentContactEntity> CREATOR = new Parcelable.Creator<RecentContactEntity>() { // from class: com.jingdong.common.deeplinkhelper.imhelper.RecentContactEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentContactEntity createFromParcel(Parcel parcel) {
            return new RecentContactEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentContactEntity[] newArray(int i2) {
            return new RecentContactEntity[i2];
        }
    };

    /* loaded from: classes5.dex */
    public interface BusinessType {
        public static final int TYPE_CONTACT = 1;
        public static final int TYPE_CUSTOMER = 0;
        public static final int TYPE_GROUP = 2;
        public static final int TYPE_MIX = 3;
    }

    /* loaded from: classes5.dex */
    public interface Mute {
        public static final int MUTE_OFF = 0;
        public static final int MUTE_ON = 1;
    }

    /* loaded from: classes5.dex */
    public interface SessionType {
        public static final int TYPE_CONTACT = 1;
        public static final int TYPE_CUSTOMER = 4;
        public static final int TYPE_GROUP = 2;
        public static final int TYPE_VENDER = 5;
    }

    /* loaded from: classes5.dex */
    public static class Transmission implements Cloneable, Parcelable {
        public final Parcelable.Creator<Transmission> CREATOR = new Parcelable.Creator<Transmission>() { // from class: com.jingdong.common.deeplinkhelper.imhelper.RecentContactEntity.Transmission.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Transmission createFromParcel(Parcel parcel) {
                return new Transmission(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Transmission[] newArray(int i2) {
                return new Transmission[i2];
            }
        };
        public int businessType;
        public String jumpLink;
        public String sessionId;
        public int sessionType;
        public String toApp;
        public String toPin;

        public Transmission(Parcel parcel) {
            this.sessionId = parcel.readString();
            this.sessionType = parcel.readInt();
            this.businessType = parcel.readInt();
            this.jumpLink = parcel.readString();
            this.toPin = parcel.readString();
            this.toApp = parcel.readString();
        }

        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e2) {
                String unused = RecentContactEntity.TAG;
                e2.toString();
                return null;
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "Transmission{sessionId='" + this.sessionId + "', sessionType=" + this.sessionType + ", businessType=" + this.businessType + ", jumpLink=" + this.jumpLink + ", toPin='" + this.toPin + "', toApp='" + this.toApp + "'}";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.sessionId);
            parcel.writeInt(this.sessionType);
            parcel.writeInt(this.businessType);
            parcel.writeString(this.jumpLink);
            parcel.writeString(this.toPin);
            parcel.writeString(this.toApp);
        }
    }

    public RecentContactEntity() {
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            e2.toString();
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "RecentContactEntity{, venderType='" + this.venderType + "', venderQuality='" + this.venderQuality + "', pid='" + this.pid + "', unread=" + this.unread + ", lastMsgDate=" + this.lastMsgDate + ", lastMsgId='" + this.lastMsgId + "', lastMsg='" + this.lastMsg + "', sessionId='" + this.sessionId + "', sessionType=" + this.sessionType + ", businessType=" + this.businessType + ", jumpLink=" + this.jumpLink + ", toPin='" + this.toPin + "', toApp='" + this.toApp + "', name='" + this.name + "', avatar='" + this.avatar + "', opt=" + this.opt + ", topTimestamp=" + this.topTimestamp + ", transmission=" + this.transmission + ", totalCount=" + this.totalCount + ", noDisturbTotalCount=" + this.noDisturbTotalCount + ", buId=" + this.buId + ", channelId=" + this.channelId + ", channelTag=" + this.channelTag + ", bbtf=" + this.bbtf + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.venderType);
        parcel.writeString(this.venderQuality);
        parcel.writeString(this.pid);
        parcel.writeInt(this.unread);
        parcel.writeLong(this.lastMsgDate);
        parcel.writeString(this.lastMsgId);
        parcel.writeString(this.lastMsg);
        parcel.writeString(this.sessionId);
        parcel.writeInt(this.sessionType);
        parcel.writeInt(this.businessType);
        parcel.writeString(this.jumpLink);
        parcel.writeString(this.toPin);
        parcel.writeString(this.toApp);
        parcel.writeString(this.name);
        parcel.writeString(this.avatar);
        parcel.writeInt(this.opt);
        parcel.writeLong(this.topTimestamp);
        parcel.writeParcelable(this.transmission, i2);
        parcel.writeInt(this.totalCount);
        parcel.writeInt(this.noDisturbTotalCount);
        parcel.writeInt(this.buId);
        parcel.writeInt(this.channelId);
        parcel.writeInt(this.channelTag);
        parcel.writeString(this.bbtf);
    }

    protected RecentContactEntity(Parcel parcel) {
        this.venderType = parcel.readString();
        this.venderQuality = parcel.readString();
        this.pid = parcel.readString();
        this.unread = parcel.readInt();
        this.lastMsgDate = parcel.readLong();
        this.lastMsgId = parcel.readString();
        this.lastMsg = parcel.readString();
        this.sessionId = parcel.readString();
        this.sessionType = parcel.readInt();
        this.businessType = parcel.readInt();
        this.jumpLink = parcel.readString();
        this.toPin = parcel.readString();
        this.toApp = parcel.readString();
        this.name = parcel.readString();
        this.avatar = parcel.readString();
        this.opt = parcel.readInt();
        this.topTimestamp = parcel.readLong();
        this.transmission = (Transmission) parcel.readParcelable(Transmission.class.getClassLoader());
        this.totalCount = parcel.readInt();
        this.noDisturbTotalCount = parcel.readInt();
        this.buId = parcel.readInt();
        this.channelId = parcel.readInt();
        this.channelTag = parcel.readInt();
        this.bbtf = parcel.readString();
    }
}
