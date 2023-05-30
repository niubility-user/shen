package cn.com.union.fido.bean.uafclient;

import android.os.Parcel;
import android.os.Parcelable;
import cn.com.union.fido.bean.Version;
import cn.com.union.fido.bean.authenticator.DisplayPNGCharacteristicsDescriptor;
import java.util.List;

/* loaded from: classes.dex */
public class Authenticator implements Parcelable {
    public static final Parcelable.Creator<Authenticator> CREATOR = new Parcelable.Creator<Authenticator>() { // from class: cn.com.union.fido.bean.uafclient.Authenticator.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final Authenticator createFromParcel(Parcel parcel) {
            return new Authenticator(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final Authenticator[] newArray(int i2) {
            return new Authenticator[i2];
        }
    };
    public String aaid;
    public String assertionScheme;
    public long attachmentHint;
    public List<Short> attestationTypes;
    public int authenticationAlgorithm;
    public String description;
    public String icon;
    public boolean isSecondFactorOnly;
    public int keyProtection;
    public int matcherProtection;
    public List<String> supportedExtensionIDs;
    public List<Version> supportedUAFVersions;
    public int tcDisplay;
    public String tcDisplayContentType;
    public List<DisplayPNGCharacteristicsDescriptor> tcDisplayPNGCharacteristics;
    public String title;
    public long userVerification;

    public Authenticator() {
    }

    private Authenticator(Parcel parcel) {
        this.title = parcel.readString();
        this.aaid = parcel.readString();
        this.description = parcel.readString();
        parcel.readTypedList(this.supportedUAFVersions, Version.CREATOR);
        this.assertionScheme = parcel.readString();
        this.authenticationAlgorithm = parcel.readInt();
        parcel.readList(this.attestationTypes, Integer.class.getClassLoader());
        this.userVerification = parcel.readLong();
        this.keyProtection = parcel.readInt();
        this.matcherProtection = parcel.readInt();
        this.attachmentHint = parcel.readLong();
        this.isSecondFactorOnly = parcel.readByte() != 0;
        this.tcDisplay = parcel.readInt();
        this.tcDisplayContentType = parcel.readString();
        parcel.readList(this.tcDisplayPNGCharacteristics, DisplayPNGCharacteristicsDescriptor.class.getClassLoader());
        this.icon = parcel.readString();
        parcel.readStringList(this.supportedExtensionIDs);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.title);
        parcel.writeString(this.aaid);
        parcel.writeString(this.description);
        parcel.writeTypedList(this.supportedUAFVersions);
        parcel.writeString(this.assertionScheme);
        parcel.writeInt(this.authenticationAlgorithm);
        parcel.writeList(this.attestationTypes);
        parcel.writeLong(this.userVerification);
        parcel.writeInt(this.keyProtection);
        parcel.writeInt(this.matcherProtection);
        parcel.writeLong(this.attachmentHint);
        parcel.writeByte(this.isSecondFactorOnly ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.tcDisplay);
        parcel.writeString(this.tcDisplayContentType);
        parcel.writeList(this.tcDisplayPNGCharacteristics);
        parcel.writeString(this.icon);
        parcel.writeStringList(this.supportedExtensionIDs);
    }
}
