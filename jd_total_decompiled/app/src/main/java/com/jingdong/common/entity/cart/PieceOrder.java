package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.a.a;

/* loaded from: classes5.dex */
public class PieceOrder implements Parcelable {
    public static final Parcelable.Creator<PieceOrder> CREATOR = new Parcelable.Creator<PieceOrder>() { // from class: com.jingdong.common.entity.cart.PieceOrder.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PieceOrder createFromParcel(Parcel parcel) {
            return new PieceOrder(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PieceOrder[] newArray(int i2) {
            return new PieceOrder[i2];
        }
    };
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f12333c;
    public String d;

    public PieceOrder() {
    }

    public static PieceOrder ParseJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return null;
        }
        PieceOrder pieceOrder = new PieceOrder();
        pieceOrder.a = jDJSONObject.optString(a.a);
        pieceOrder.b = jDJSONObject.optString("b");
        pieceOrder.f12333c = jDJSONObject.optString("c");
        pieceOrder.d = jDJSONObject.optString("d");
        return pieceOrder;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.f12333c);
        parcel.writeString(this.d);
    }

    protected PieceOrder(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.f12333c = parcel.readString();
        this.d = parcel.readString();
    }
}
