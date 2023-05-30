package com.jingdong.app.mall.bundle.styleinfoview.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import java.util.List;
import jpbury.t;

/* loaded from: classes3.dex */
public class PdCommentInfo implements Parcelable {
    private static final String AUTO_PLAY_VIDEO = "1";
    public static final Parcelable.Creator<PdCommentInfo> CREATOR = new Parcelable.Creator<PdCommentInfo>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.detailcomment.PdCommentInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentInfo createFromParcel(Parcel parcel) {
            return new PdCommentInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PdCommentInfo[] newArray(int i2) {
            return new PdCommentInfo[i2];
        }
    };
    public String abTest;
    public int allCnt;
    public String allCntStr;
    public String biData;
    public BookCommentInfo bookCommentInfo;
    public BuyersCommentInfo buyersCommentInfo;
    public String buyersShowInfo;
    public boolean canConsultFlag;
    public boolean cardStyle;
    public String commentButtonText;
    public List<PdCommentItemInfo> commentInfoList;
    public String commentListJsonString;
    public String commentTitle;
    public List<PDCommentVideoItemInfo> commentVideoInfoList;
    public String consultBtnName;
    public int consultationCount;
    public String currentSkuStatus;
    public String goodRate;
    public boolean isAllDefaultGoodComment;
    public boolean isShowCommentBtn;
    public boolean isShowConsultBtn;
    public boolean isShowYoutuShaituBtn;
    public String mainPicName;
    public String mdKey;
    public boolean newCommentStyle;
    public String newStyleText;
    public String noCommentText;
    public String noQuestionText;
    public boolean qaFloorStyle;
    public String questionCountText;
    public PdQuestionGeneralInfo questionGeneralInfo;
    public List<PdQuestionInfo> questionList;
    public List<CommentTag> semanticTagList;
    public int styleType;
    public String testId;
    public String userVideoStyleType;
    public String videoAutoPlaySwitch;
    public String youTuOrShaiTu;

    /* loaded from: classes3.dex */
    public class BookCommentInfo {
        public String openApp;

        public BookCommentInfo() {
        }
    }

    /* loaded from: classes3.dex */
    public static class BuyersCommentInfo {
        public List<BuyersCommentInfoList> buyersCommentInfoList;
        public String buyersTitle;
        public String sources;
    }

    /* loaded from: classes3.dex */
    public static class BuyersCommentInfoList {
        public String commentId;
        public String guid;
        public String mediaId;
        public String mediaLength;
        public int mediaType = -1;
        public String picURL;
        public String videoPlayAddress;
    }

    /* loaded from: classes3.dex */
    public class CommentTag {
        public boolean canBeFiltered;
        public String ckeKeyWordBury;
        public String count;
        public String id;
        public String name;
        public String stand;
        public String tagid;
        public String type;

        public CommentTag() {
        }
    }

    public PdCommentInfo() {
        this.isShowYoutuShaituBtn = true;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isShowAskFloor() {
        PdQuestionGeneralInfo pdQuestionGeneralInfo = this.questionGeneralInfo;
        return (pdQuestionGeneralInfo == null || TextUtils.isEmpty(pdQuestionGeneralInfo.questionIcon) || TextUtils.isEmpty(this.questionGeneralInfo.questionText)) ? false : true;
    }

    public boolean isVideoAutoPlaySwitch() {
        return TextUtils.equals(this.videoAutoPlaySwitch, "1");
    }

    public void putJson(String str) {
        List<PdCommentItemInfo> list;
        if (TextUtils.isEmpty(str) || (list = this.commentInfoList) == null || list.isEmpty()) {
            return;
        }
        try {
            JDJSONArray jSONArray = new JDJSONObject(JDJSON.parseObject(str)).getJSONArray("commentInfoList");
            this.commentListJsonString = jSONArray.toJSONString();
            int i2 = 0;
            if (jSONArray != null) {
                for (PdCommentItemInfo pdCommentItemInfo : this.commentInfoList) {
                    JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
                    pdCommentItemInfo.jsonString = jSONObject.toString();
                    pdCommentItemInfo.getProductAttribute(jSONObject);
                    i2++;
                }
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.youTuOrShaiTu);
        parcel.writeByte(this.isShowYoutuShaituBtn ? (byte) 1 : (byte) 0);
        parcel.writeString(this.testId);
    }

    protected PdCommentInfo(Parcel parcel) {
        this.isShowYoutuShaituBtn = true;
        this.youTuOrShaiTu = parcel.readString();
        this.isShowYoutuShaituBtn = parcel.readByte() != 0;
        this.testId = parcel.readString();
    }
}
