package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import java.util.List;
import jpbury.t;

/* loaded from: classes15.dex */
public class PdCommentItemInfo implements Parcelable {
    public static final Parcelable.Creator<PdCommentItemInfo> CREATOR = new Parcelable.Creator<PdCommentItemInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentItemInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentItemInfo createFromParcel(Parcel parcel) {
            return new PdCommentItemInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentItemInfo[] newArray(int i2) {
            return new PdCommentItemInfo[i2];
        }
    };
    public String commentData;
    public long commentId;
    public String commentOfficerUserImgSwitch;
    public String commentScore;
    public String guid;
    public List<PdCommentIconListInfo> iconList;
    public String isShowUserLevel;
    public String jsonString;
    public int mCurPosition;
    public String mProductAttr;
    public List<PdCommentPictureInfo> pictureInfoList;
    public String plusAddress;
    public String plusAvailable;
    public String plusLogoId;
    public String testId;
    public String userImgUrl;
    public String userLevel;
    public String userNickName;

    public PdCommentItemInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void getProductAttribute(JDJSONObject jDJSONObject) {
        StringBuilder sb = new StringBuilder();
        try {
            JDJSONArray jSONArray = jDJSONObject.getJSONArray("wareAttribute");
            if (jSONArray == null) {
                return;
            }
            int size = jSONArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                for (Object obj : jSONArray.getJSONObject(i2).values().toArray()) {
                    sb.append(obj);
                    sb.append(" , ");
                }
            }
            if (sb.length() > 3) {
                this.mProductAttr = sb.toString().substring(0, sb.length() - 3);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
    }

    public boolean isHasCommentOfficer() {
        List<PdCommentIconListInfo> list = this.iconList;
        if (list != null) {
            for (PdCommentIconListInfo pdCommentIconListInfo : list) {
                if (pdCommentIconListInfo != null && pdCommentIconListInfo.isCommentOfficer()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isHasSkinIcon() {
        List<PdCommentIconListInfo> list = this.iconList;
        if (list != null) {
            for (PdCommentIconListInfo pdCommentIconListInfo : list) {
                if (pdCommentIconListInfo != null && pdCommentIconListInfo.isSkinIcon()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.userNickName);
        parcel.writeString(this.userLevel);
        parcel.writeString(this.userImgUrl);
        parcel.writeLong(this.commentId);
        parcel.writeString(this.commentScore);
        parcel.writeString(this.commentData);
        parcel.writeTypedList(this.pictureInfoList);
        parcel.writeString(this.jsonString);
        parcel.writeString(this.isShowUserLevel);
        parcel.writeString(this.mProductAttr);
        parcel.writeString(this.plusAvailable);
        parcel.writeString(this.guid);
        parcel.writeString(this.plusLogoId);
        parcel.writeString(this.plusAddress);
        parcel.writeInt(this.mCurPosition);
        parcel.writeString(this.testId);
        parcel.writeTypedList(this.iconList);
        parcel.writeString(this.commentOfficerUserImgSwitch);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PdCommentItemInfo(Parcel parcel) {
        this.userNickName = parcel.readString();
        this.userLevel = parcel.readString();
        this.userImgUrl = parcel.readString();
        this.commentId = parcel.readLong();
        this.commentScore = parcel.readString();
        this.commentData = parcel.readString();
        this.pictureInfoList = parcel.createTypedArrayList(PdCommentPictureInfo.CREATOR);
        this.jsonString = parcel.readString();
        this.isShowUserLevel = parcel.readString();
        this.mProductAttr = parcel.readString();
        this.plusAvailable = parcel.readString();
        this.guid = parcel.readString();
        this.plusLogoId = parcel.readString();
        this.plusAddress = parcel.readString();
        this.mCurPosition = parcel.readInt();
        this.testId = parcel.readString();
        this.iconList = parcel.createTypedArrayList(PdCommentIconListInfo.CREATOR);
        this.commentOfficerUserImgSwitch = parcel.readString();
    }
}
