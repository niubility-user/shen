package com.jd.lib.productdetail.core.entitys.detailcomment;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import jpbury.t;

/* loaded from: classes15.dex */
public class PdCommentInfo implements Parcelable {
    private static final String AUTO_PLAY_VIDEO = "1";
    public static final Parcelable.Creator<PdCommentInfo> CREATOR = new Parcelable.Creator<PdCommentInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo.1
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
    public String buyersShowTotal;
    public boolean canConsultFlag;
    public boolean cardStyle;
    public String commentButtonText;
    public int commentFloorShowNum;
    public List<PdCommentItemInfo> commentInfoList;
    public String commentListJsonString;
    public String commentTitle;
    public List<PDCommentVideoItemInfo> commentVideoInfoList;
    public String consultBtnName;
    public int consultationCount;
    public String currentSkuStatus;
    public String goodRate;
    public HotDiscussionInfo hotDiscussion;
    public boolean isAllDefaultGoodComment;
    public boolean isFloorHideBuyShow;
    public boolean isShowCommentBtn;
    public boolean isShowConsultBtn;
    public boolean isShowYoutuShaituBtn;
    public HashSet<PdCommentItemInfo> mShowedComments;
    public HashSet<PdCommentItemInfo> mUploadedComments;
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
    public boolean showCommentFloor;
    public int styleType;
    public ArrayList<TagStatisticsinfoList> tagStatisticsinfoList;
    public String testId;
    public ArrayList<PdCommentUGCInfo> ugcTextInfo;
    public String userVideoStyleType;
    public String videoAutoPlaySwitch;
    public String youTuOrShaiTu;

    /* loaded from: classes15.dex */
    public static class BookCommentInfo implements Parcelable {
        public static final Parcelable.Creator<BookCommentInfo> CREATOR = new Parcelable.Creator<BookCommentInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo.BookCommentInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BookCommentInfo createFromParcel(Parcel parcel) {
                return new BookCommentInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BookCommentInfo[] newArray(int i2) {
                return new BookCommentInfo[i2];
            }
        };
        public String openApp;

        public BookCommentInfo() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.openApp);
        }

        protected BookCommentInfo(Parcel parcel) {
            this.openApp = parcel.readString();
        }
    }

    /* loaded from: classes15.dex */
    public static class BuyersCommentInfo implements Parcelable {
        public static final Parcelable.Creator<BuyersCommentInfo> CREATOR = new Parcelable.Creator<BuyersCommentInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo.BuyersCommentInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BuyersCommentInfo createFromParcel(Parcel parcel) {
                return new BuyersCommentInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BuyersCommentInfo[] newArray(int i2) {
                return new BuyersCommentInfo[i2];
            }
        };
        public List<BuyersCommentInfoList> buyersCommentInfoList;
        public String buyersTitle;
        public String sources;

        public BuyersCommentInfo() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.buyersTitle);
            parcel.writeTypedList(this.buyersCommentInfoList);
            parcel.writeString(this.sources);
        }

        protected BuyersCommentInfo(Parcel parcel) {
            this.buyersTitle = parcel.readString();
            this.buyersCommentInfoList = parcel.createTypedArrayList(BuyersCommentInfoList.CREATOR);
            this.sources = parcel.readString();
        }
    }

    /* loaded from: classes15.dex */
    public static class CommentTag implements Parcelable {
        public static final Parcelable.Creator<CommentTag> CREATOR = new Parcelable.Creator<CommentTag>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo.CommentTag.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CommentTag createFromParcel(Parcel parcel) {
                return new CommentTag(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CommentTag[] newArray(int i2) {
                return new CommentTag[i2];
            }
        };
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

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.id);
            parcel.writeString(this.tagid);
            parcel.writeString(this.name);
            parcel.writeString(this.count);
            parcel.writeString(this.type);
            parcel.writeByte(this.canBeFiltered ? (byte) 1 : (byte) 0);
            parcel.writeString(this.stand);
            parcel.writeString(this.ckeKeyWordBury);
        }

        protected CommentTag(Parcel parcel) {
            this.id = parcel.readString();
            this.tagid = parcel.readString();
            this.name = parcel.readString();
            this.count = parcel.readString();
            this.type = parcel.readString();
            this.canBeFiltered = parcel.readByte() != 0;
            this.stand = parcel.readString();
            this.ckeKeyWordBury = parcel.readString();
        }
    }

    /* loaded from: classes15.dex */
    public static class HotDiscussionInfo implements Parcelable {
        public static final Parcelable.Creator<HotDiscussionInfo> CREATOR = new Parcelable.Creator<HotDiscussionInfo>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo.HotDiscussionInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HotDiscussionInfo createFromParcel(Parcel parcel) {
                return new HotDiscussionInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HotDiscussionInfo[] newArray(int i2) {
                return new HotDiscussionInfo[i2];
            }
        };
        public String icon;
        public String text;

        public HotDiscussionInfo() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.icon);
            parcel.writeString(this.text);
        }

        public HotDiscussionInfo(Parcel parcel) {
            this.icon = parcel.readString();
            this.text = parcel.readString();
        }
    }

    /* loaded from: classes15.dex */
    public static class TagStatisticsinfoList implements Parcelable {
        public static final Parcelable.Creator<TagStatisticsinfoList> CREATOR = new Parcelable.Creator<TagStatisticsinfoList>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo.TagStatisticsinfoList.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TagStatisticsinfoList createFromParcel(Parcel parcel) {
                return new TagStatisticsinfoList(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public TagStatisticsinfoList[] newArray(int i2) {
                return new TagStatisticsinfoList[i2];
            }
        };
        public boolean canBeFiltered;
        public String ckeKeyWordBury;
        public String count;
        public String id;
        public String name;
        public String stand;
        public String tagId;
        public String type;

        public TagStatisticsinfoList() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void readFromParcel(Parcel parcel) {
            this.id = parcel.readString();
            this.name = parcel.readString();
            this.count = parcel.readString();
            this.tagId = parcel.readString();
            this.type = parcel.readString();
            this.canBeFiltered = parcel.readByte() != 0;
            this.stand = parcel.readString();
            this.ckeKeyWordBury = parcel.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.id);
            parcel.writeString(this.name);
            parcel.writeString(this.count);
            parcel.writeString(this.tagId);
            parcel.writeString(this.type);
            parcel.writeByte(this.canBeFiltered ? (byte) 1 : (byte) 0);
            parcel.writeString(this.stand);
            parcel.writeString(this.ckeKeyWordBury);
        }

        protected TagStatisticsinfoList(Parcel parcel) {
            this.id = parcel.readString();
            this.name = parcel.readString();
            this.count = parcel.readString();
            this.tagId = parcel.readString();
            this.type = parcel.readString();
            this.canBeFiltered = parcel.readByte() != 0;
            this.stand = parcel.readString();
            this.ckeKeyWordBury = parcel.readString();
        }
    }

    protected PdCommentInfo(Parcel parcel) {
        this.showCommentFloor = true;
        this.isShowYoutuShaituBtn = true;
        this.mShowedComments = new HashSet<>();
        this.mUploadedComments = new HashSet<>();
        this.buyersCommentInfo = (BuyersCommentInfo) parcel.readParcelable(BuyersCommentInfo.class.getClassLoader());
        this.buyersShowTotal = parcel.readString();
        this.showCommentFloor = parcel.readByte() != 0;
        this.isShowCommentBtn = parcel.readByte() != 0;
        this.buyersShowInfo = parcel.readString();
        this.mainPicName = parcel.readString();
        this.allCnt = parcel.readInt();
        this.allCntStr = parcel.readString();
        this.consultationCount = parcel.readInt();
        this.canConsultFlag = parcel.readByte() != 0;
        this.commentButtonText = parcel.readString();
        this.goodRate = parcel.readString();
        this.commentInfoList = parcel.createTypedArrayList(PdCommentItemInfo.CREATOR);
        this.questionGeneralInfo = (PdQuestionGeneralInfo) parcel.readParcelable(PdQuestionGeneralInfo.class.getClassLoader());
        this.questionList = parcel.createTypedArrayList(PdQuestionInfo.CREATOR);
        this.noQuestionText = parcel.readString();
        this.questionCountText = parcel.readString();
        this.abTest = parcel.readString();
        this.newStyleText = parcel.readString();
        this.qaFloorStyle = parcel.readByte() != 0;
        this.styleType = parcel.readInt();
        this.isShowConsultBtn = parcel.readByte() != 0;
        this.currentSkuStatus = parcel.readString();
        this.ugcTextInfo = parcel.createTypedArrayList(PdCommentUGCInfo.CREATOR);
        this.youTuOrShaiTu = parcel.readString();
        this.isShowYoutuShaituBtn = parcel.readByte() != 0;
        this.consultBtnName = parcel.readString();
        this.commentTitle = parcel.readString();
        this.noCommentText = parcel.readString();
        this.userVideoStyleType = parcel.readString();
        this.videoAutoPlaySwitch = parcel.readString();
        this.isAllDefaultGoodComment = parcel.readByte() != 0;
        this.testId = parcel.readString();
        this.newCommentStyle = parcel.readByte() != 0;
        this.cardStyle = parcel.readByte() != 0;
        this.semanticTagList = parcel.createTypedArrayList(CommentTag.CREATOR);
        this.commentListJsonString = parcel.readString();
        this.biData = parcel.readString();
        this.mdKey = parcel.readString();
        this.bookCommentInfo = (BookCommentInfo) parcel.readParcelable(BookCommentInfo.class.getClassLoader());
        this.hotDiscussion = (HotDiscussionInfo) parcel.readParcelable(HotDiscussionInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCommentCountWithFloorShowNum() {
        List<PdCommentItemInfo> list = this.commentInfoList;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return Math.min(this.commentInfoList.size(), this.commentFloorShowNum);
    }

    public boolean isBuyerCommentShowSmallerThan(int i2) {
        List<BuyersCommentInfoList> list;
        BuyersCommentInfo buyersCommentInfo = this.buyersCommentInfo;
        return buyersCommentInfo == null || (list = buyersCommentInfo.buyersCommentInfoList) == null || list.size() < i2;
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
        parcel.writeParcelable(this.buyersCommentInfo, i2);
        parcel.writeString(this.buyersShowTotal);
        parcel.writeByte(this.showCommentFloor ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShowCommentBtn ? (byte) 1 : (byte) 0);
        parcel.writeString(this.buyersShowInfo);
        parcel.writeString(this.mainPicName);
        parcel.writeInt(this.allCnt);
        parcel.writeString(this.allCntStr);
        parcel.writeInt(this.consultationCount);
        parcel.writeByte(this.canConsultFlag ? (byte) 1 : (byte) 0);
        parcel.writeString(this.commentButtonText);
        parcel.writeString(this.goodRate);
        parcel.writeTypedList(this.commentInfoList);
        parcel.writeParcelable(this.questionGeneralInfo, i2);
        parcel.writeTypedList(this.questionList);
        parcel.writeString(this.noQuestionText);
        parcel.writeString(this.questionCountText);
        parcel.writeString(this.abTest);
        parcel.writeString(this.newStyleText);
        parcel.writeByte(this.qaFloorStyle ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.styleType);
        parcel.writeByte(this.isShowConsultBtn ? (byte) 1 : (byte) 0);
        parcel.writeString(this.currentSkuStatus);
        parcel.writeTypedList(this.ugcTextInfo);
        parcel.writeString(this.youTuOrShaiTu);
        parcel.writeByte(this.isShowYoutuShaituBtn ? (byte) 1 : (byte) 0);
        parcel.writeString(this.consultBtnName);
        parcel.writeString(this.commentTitle);
        parcel.writeString(this.noCommentText);
        parcel.writeString(this.userVideoStyleType);
        parcel.writeString(this.videoAutoPlaySwitch);
        parcel.writeByte(this.isAllDefaultGoodComment ? (byte) 1 : (byte) 0);
        parcel.writeString(this.testId);
        parcel.writeByte(this.newCommentStyle ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.cardStyle ? (byte) 1 : (byte) 0);
        parcel.writeTypedList(this.semanticTagList);
        parcel.writeString(this.commentListJsonString);
        parcel.writeString(this.biData);
        parcel.writeString(this.mdKey);
        parcel.writeParcelable(this.bookCommentInfo, i2);
        parcel.writeParcelable(this.hotDiscussion, i2);
    }

    /* loaded from: classes15.dex */
    public static class BuyersCommentInfoList implements Parcelable {
        public static final Parcelable.Creator<BuyersCommentInfoList> CREATOR = new Parcelable.Creator<BuyersCommentInfoList>() { // from class: com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo.BuyersCommentInfoList.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BuyersCommentInfoList createFromParcel(Parcel parcel) {
                return new BuyersCommentInfoList(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BuyersCommentInfoList[] newArray(int i2) {
                return new BuyersCommentInfoList[i2];
            }
        };
        public String commentId;
        public String content;
        public List<PdCommentContentTagInfo> contentTagList;
        public String guid;
        public String headImg;
        public String likeCount;
        public String mediaId;
        public String mediaLength;
        public int mediaType;
        public String nickName;
        public String picURL;
        public String topicId;
        public String ugcDetailOpenApp;
        public String videoPlayAddress;

        public BuyersCommentInfoList() {
            this.mediaType = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.commentId);
            parcel.writeString(this.picURL);
            parcel.writeString(this.guid);
            parcel.writeString(this.mediaId);
            parcel.writeString(this.mediaLength);
            parcel.writeInt(this.mediaType);
            parcel.writeString(this.videoPlayAddress);
            parcel.writeString(this.ugcDetailOpenApp);
            parcel.writeString(this.content);
            parcel.writeString(this.headImg);
            parcel.writeString(this.nickName);
            parcel.writeString(this.likeCount);
            parcel.writeTypedList(this.contentTagList);
        }

        protected BuyersCommentInfoList(Parcel parcel) {
            this.mediaType = -1;
            this.commentId = parcel.readString();
            this.picURL = parcel.readString();
            this.guid = parcel.readString();
            this.mediaId = parcel.readString();
            this.mediaLength = parcel.readString();
            this.mediaType = parcel.readInt();
            this.videoPlayAddress = parcel.readString();
            this.ugcDetailOpenApp = parcel.readString();
            this.content = parcel.readString();
            this.headImg = parcel.readString();
            this.nickName = parcel.readString();
            this.likeCount = parcel.readString();
            this.contentTagList = parcel.createTypedArrayList(PdCommentContentTagInfo.CREATOR);
        }
    }

    public PdCommentInfo() {
        this.showCommentFloor = true;
        this.isShowYoutuShaituBtn = true;
        this.mShowedComments = new HashSet<>();
        this.mUploadedComments = new HashSet<>();
    }
}
