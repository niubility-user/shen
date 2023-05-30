package com.jd.lib.productdetail.core.entitys.giftshopping;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public class PdActiveRuleContent implements Parcelable {
    public static final Parcelable.Creator<PdActiveRuleContent> CREATOR = new Parcelable.Creator<PdActiveRuleContent>() { // from class: com.jd.lib.productdetail.core.entitys.giftshopping.PdActiveRuleContent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdActiveRuleContent createFromParcel(Parcel parcel) {
            return new PdActiveRuleContent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdActiveRuleContent[] newArray(int i2) {
            return new PdActiveRuleContent[i2];
        }
    };
    public static final int GIFT_RULE_TYPE_DATA = 0;
    public static final int GIFT_RULE_TYPE_SECTION = 2;
    public static final int GIFT_RULE_TYPE_TITLE = 1;
    public int contentType;
    public String icon;
    public String text;
    public String titleImage;

    public PdActiveRuleContent() {
        this.contentType = 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.icon);
        parcel.writeString(this.text);
        parcel.writeInt(this.contentType);
        parcel.writeString(this.titleImage);
    }

    protected PdActiveRuleContent(Parcel parcel) {
        this.contentType = 0;
        this.icon = parcel.readString();
        this.text = parcel.readString();
        this.contentType = parcel.readInt();
        this.titleImage = parcel.readString();
    }
}
