package com.jingdong.common.sample.jshop.Entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JshopComment implements Parcelable {
    public static final Parcelable.Creator<JshopComment> CREATOR = new Parcelable.Creator<JshopComment>() { // from class: com.jingdong.common.sample.jshop.Entity.JshopComment.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JshopComment createFromParcel(Parcel parcel) {
            return new JshopComment(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JshopComment[] newArray(int i2) {
            return new JshopComment[i2];
        }
    };
    public boolean hasNext;
    public List<JshopDynamicComment> mList;
    public String notice;
    public int pageIdx;
    public int pageSize;
    public Double riskLvl;
    public int totalCount;
    public int totalPage;
    public Object transParam;

    public JshopComment(JDJSONObject jDJSONObject) {
        this.mList = new ArrayList();
        this.pageIdx = 1;
        this.pageSize = 10;
        this.totalCount = 30;
        this.totalPage = 10;
        if (jDJSONObject != null) {
            this.pageIdx = jDJSONObject.optInt("pageIdx");
            this.pageSize = jDJSONObject.optInt("pageSize");
            this.totalCount = jDJSONObject.optInt("totalCount");
            this.totalPage = jDJSONObject.optInt("totalPage");
            this.hasNext = jDJSONObject.optBoolean("hasNext");
            this.riskLvl = Double.valueOf(jDJSONObject.optDouble("riskLvl", -100.0d));
            this.notice = jDJSONObject.optString("notice");
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray("result");
            if (optJSONArray == null || optJSONArray.size() <= 0) {
                return;
            }
            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                this.mList.add(new JshopDynamicComment(optJSONArray.optJSONObject(i2)));
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.mList);
        parcel.writeParcelable((Parcelable) this.transParam, i2);
        parcel.writeInt(this.pageIdx);
        parcel.writeInt(this.pageSize);
        parcel.writeInt(this.totalCount);
        parcel.writeInt(this.totalPage);
        parcel.writeByte(this.hasNext ? (byte) 1 : (byte) 0);
    }

    /* loaded from: classes6.dex */
    public static class JshopDynamicComment implements Parcelable {
        public static final Parcelable.Creator<JshopDynamicComment> CREATOR = new Parcelable.Creator<JshopDynamicComment>() { // from class: com.jingdong.common.sample.jshop.Entity.JshopComment.JshopDynamicComment.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public JshopDynamicComment createFromParcel(Parcel parcel) {
                return new JshopDynamicComment(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public JshopDynamicComment[] newArray(int i2) {
                return new JshopDynamicComment[i2];
            }
        };
        public long cId;
        public String cTime;
        public String comment;
        public int floorNo;
        public String headPic;
        public int isVender;
        public boolean mySelf;
        public long pCId;
        public String pCTime;
        public String pComment;
        public String pHeadPic;
        public boolean pMySelft;
        public String pUserName;
        public String userName;

        public JshopDynamicComment(JDJSONObject jDJSONObject) {
            if (jDJSONObject != null) {
                this.pCId = jDJSONObject.optLong("pCId");
                this.cId = jDJSONObject.optLong("cId");
                this.headPic = jDJSONObject.optString(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_HEADPIC);
                this.pHeadPic = jDJSONObject.optString("pHeadPic");
                this.userName = jDJSONObject.optString("userName");
                this.pUserName = jDJSONObject.optString("pUserName");
                this.comment = jDJSONObject.optString("comment");
                this.pComment = jDJSONObject.optString("pComment");
                this.cTime = jDJSONObject.optString("cTime");
                this.pCTime = jDJSONObject.optString("pCTime");
                this.mySelf = jDJSONObject.optBoolean("mySelf");
                this.pMySelft = jDJSONObject.optBoolean("pmySelf");
                this.isVender = jDJSONObject.optInt("isVender", 1);
                this.floorNo = jDJSONObject.optInt("floorNo", -1);
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.cId == ((JshopDynamicComment) obj).cId;
        }

        public int hashCode() {
            long j2 = this.cId;
            return (int) (j2 ^ (j2 >>> 32));
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeLong(this.pCId);
            parcel.writeLong(this.cId);
            parcel.writeString(this.headPic);
            parcel.writeString(this.pHeadPic);
            parcel.writeString(this.userName);
            parcel.writeString(this.pUserName);
            parcel.writeString(this.comment);
            parcel.writeString(this.pComment);
            parcel.writeString(this.cTime);
            parcel.writeString(this.pCTime);
            parcel.writeByte(this.mySelf ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.isVender);
            parcel.writeInt(this.floorNo);
        }

        public JshopDynamicComment() {
        }

        protected JshopDynamicComment(Parcel parcel) {
            this.pCId = parcel.readLong();
            this.cId = parcel.readLong();
            this.headPic = parcel.readString();
            this.pHeadPic = parcel.readString();
            this.userName = parcel.readString();
            this.pUserName = parcel.readString();
            this.comment = parcel.readString();
            this.pComment = parcel.readString();
            this.cTime = parcel.readString();
            this.pCTime = parcel.readString();
            this.mySelf = parcel.readByte() != 0;
            this.isVender = parcel.readInt();
            this.floorNo = parcel.readInt();
        }
    }

    public JshopComment() {
        this.mList = new ArrayList();
        this.pageIdx = 1;
        this.pageSize = 10;
        this.totalCount = 30;
        this.totalPage = 10;
    }

    protected JshopComment(Parcel parcel) {
        this.mList = new ArrayList();
        this.pageIdx = 1;
        this.pageSize = 10;
        this.totalCount = 30;
        this.totalPage = 10;
        this.mList = parcel.createTypedArrayList(JshopDynamicComment.CREATOR);
        this.transParam = parcel.readParcelable(JSONObject.class.getClassLoader());
        this.pageIdx = parcel.readInt();
        this.pageSize = parcel.readInt();
        this.totalCount = parcel.readInt();
        this.totalPage = parcel.readInt();
        this.hasNext = parcel.readByte() != 0;
    }
}
