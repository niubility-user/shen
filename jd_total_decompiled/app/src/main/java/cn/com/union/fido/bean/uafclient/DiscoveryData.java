package cn.com.union.fido.bean.uafclient;

import android.os.Parcel;
import android.os.Parcelable;
import cn.com.union.fido.bean.Version;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DiscoveryData implements Parcelable {
    public static final Parcelable.Creator<DiscoveryData> CREATOR = new Parcelable.Creator<DiscoveryData>() { // from class: cn.com.union.fido.bean.uafclient.DiscoveryData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DiscoveryData createFromParcel(Parcel parcel) {
            return new DiscoveryData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DiscoveryData[] newArray(int i2) {
            return new DiscoveryData[i2];
        }
    };
    public List<Authenticator> availableAuthenticators;
    public String clientVendor;
    public Version clientVersion;
    public List<Version> supportedUAFVersions;

    public DiscoveryData() {
        this.supportedUAFVersions = new ArrayList();
        this.availableAuthenticators = new ArrayList();
    }

    private DiscoveryData(Parcel parcel) {
        this.supportedUAFVersions = new ArrayList();
        this.availableAuthenticators = new ArrayList();
        parcel.readTypedList(this.supportedUAFVersions, Version.CREATOR);
        this.clientVendor = parcel.readString();
        this.clientVersion = (Version) parcel.readParcelable(null);
        parcel.readTypedList(this.availableAuthenticators, Authenticator.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.supportedUAFVersions);
        parcel.writeString(this.clientVendor);
        parcel.writeParcelable(this.clientVersion, 0);
        parcel.writeTypedList(this.availableAuthenticators);
    }
}
