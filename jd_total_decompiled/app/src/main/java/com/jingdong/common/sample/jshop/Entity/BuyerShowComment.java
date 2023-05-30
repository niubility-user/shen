package com.jingdong.common.sample.jshop.Entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class BuyerShowComment implements Parcelable {
    public static final Parcelable.Creator<BuyerShowComment> CREATOR = new Parcelable.Creator<BuyerShowComment>() { // from class: com.jingdong.common.sample.jshop.Entity.BuyerShowComment.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BuyerShowComment createFromParcel(Parcel parcel) {
            return new BuyerShowComment(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BuyerShowComment[] newArray(int i2) {
            return new BuyerShowComment[i2];
        }
    };
    public ArrayList<String> afterImage;
    public String content;
    public String creationTime;
    public String id;
    public String nickname;
    public String pin;
    public String referenceId;
    public int replyCount;
    public int usefulVoteCount;
    public String userImage;
    public String userImageUrl;

    public BuyerShowComment(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.id = jDJSONObject.optString("id");
        this.content = jDJSONObject.optString("content");
        this.creationTime = jDJSONObject.optString("creationTime");
        this.referenceId = jDJSONObject.optString("referenceId");
        this.replyCount = jDJSONObject.optInt("replyCount");
        this.usefulVoteCount = jDJSONObject.optInt("usefulVoteCount");
        this.pin = jDJSONObject.optString("pin");
        this.userImage = jDJSONObject.optString("userImage");
        this.userImageUrl = jDJSONObject.optString("userImageUrl");
        this.nickname = jDJSONObject.optString("nickname");
        this.afterImage = toList(jDJSONObject.optJSONArray("images"));
    }

    private ArrayList<String> toList(JDJSONArray jDJSONArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (jDJSONArray != null) {
            try {
                if (jDJSONArray.size() > 0) {
                    for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                        JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
                        if (optJSONObject != null) {
                            arrayList.add(optJSONObject.optString("imgUrl"));
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.id);
        parcel.writeString(this.content);
        parcel.writeString(this.creationTime);
        parcel.writeString(this.referenceId);
        parcel.writeInt(this.replyCount);
        parcel.writeInt(this.usefulVoteCount);
        parcel.writeString(this.pin);
        parcel.writeString(this.userImage);
        parcel.writeString(this.userImageUrl);
        parcel.writeString(this.nickname);
        parcel.writeStringList(this.afterImage);
    }

    protected BuyerShowComment(Parcel parcel) {
        this.id = parcel.readString();
        this.content = parcel.readString();
        this.creationTime = parcel.readString();
        this.referenceId = parcel.readString();
        this.replyCount = parcel.readInt();
        this.usefulVoteCount = parcel.readInt();
        this.pin = parcel.readString();
        this.userImage = parcel.readString();
        this.userImageUrl = parcel.readString();
        this.nickname = parcel.readString();
        this.afterImage = parcel.createStringArrayList();
    }
}
