package cn.com.union.fido.bean.uafclient;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class UAFMessage implements Parcelable {
    public static final Parcelable.Creator<UAFMessage> CREATOR = new Parcelable.Creator<UAFMessage>() { // from class: cn.com.union.fido.bean.uafclient.UAFMessage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final UAFMessage createFromParcel(Parcel parcel) {
            return new UAFMessage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final UAFMessage[] newArray(int i2) {
            return new UAFMessage[i2];
        }
    };
    public String additionalData;
    public String uafProtocolMessage;

    public UAFMessage() {
    }

    private UAFMessage(Parcel parcel) {
        this.uafProtocolMessage = parcel.readString();
        this.additionalData = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.uafProtocolMessage);
        parcel.writeString(this.additionalData);
    }
}
