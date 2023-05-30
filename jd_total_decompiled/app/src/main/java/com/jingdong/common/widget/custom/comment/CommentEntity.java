package com.jingdong.common.widget.custom.comment;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.personal.PersonalConstants;
import java.util.List;

/* loaded from: classes12.dex */
public class CommentEntity implements ICommentFloorEntity, Parcelable {
    public static final Parcelable.Creator<CommentEntity> CREATOR = new Parcelable.Creator<CommentEntity>() { // from class: com.jingdong.common.widget.custom.comment.CommentEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CommentEntity createFromParcel(Parcel parcel) {
            return new CommentEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CommentEntity[] newArray(int i2) {
            return new CommentEntity[i2];
        }
    };
    public String avatar;
    public String bId;
    public Integer businessId;
    public int businessStyle;
    public String channelId;
    public int commentType;
    public String content;
    public JumpEntity daRenAndJump;
    public String eId;
    public String firstLevelCommentId;
    public boolean hasExpand;
    public boolean highLightFlag;
    public int hotCommentCount;
    public List<CommentEntity> hotSubComments;
    public String id;
    public boolean isAuthor;
    public boolean isCommentAuthor;
    public boolean isHot;
    public boolean isZaned;
    public String label;
    public String location;
    public String nickName;
    public String parentContent;
    public String parentId;
    public String parentNickName;
    public String pin;
    public String plusTagPicUrl;
    public boolean plusUser;
    public String publishTime;
    public String soleTag;
    public String status;
    public int subCommentCount;
    public List<CommentEntity> subComments;
    public String timeShow;
    public int type;
    public int zanCount;

    public CommentEntity() {
        this.hasExpand = false;
        this.isHot = false;
        this.soleTag = "";
    }

    public void deleteHotSubComment(String str) {
        List<CommentEntity> list;
        if (TextUtils.isEmpty(str) || (list = this.hotSubComments) == null || list.size() <= 0) {
            return;
        }
        for (int size = this.hotSubComments.size() - 1; size >= 0; size--) {
            if (TextUtils.equals(str, this.hotSubComments.get(size).id)) {
                this.hotSubComments.remove(size);
                return;
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getContent() {
        String str = this.content;
        return str == null ? "" : str;
    }

    @Override // com.jingdong.common.widget.custom.comment.ICommentFloorEntity
    public int getFloorType() {
        return 1;
    }

    public String getFormatSubCommentCount() {
        int i2 = this.subCommentCount;
        return i2 > 99999 ? PersonalConstants.PERSONAL_BIG_VALUE_DEFAULT_STRING : i2 < 0 ? "0" : String.valueOf(i2);
    }

    public String getNickName() {
        return TextUtils.isEmpty(this.nickName) ? CommentConstants.DEFAULT_COMMENT_AUTHOR_NAME : this.nickName;
    }

    public String getParentContent() {
        String str = this.parentContent;
        return str == null ? "" : str;
    }

    public String getParentNickName() {
        return TextUtils.isEmpty(this.parentNickName) ? CommentConstants.DEFAULT_COMMENT_AUTHOR_NAME : this.parentNickName;
    }

    public boolean isLegal() {
        return !TextUtils.isEmpty(this.id);
    }

    public CommentEntity setHot(boolean z) {
        this.isHot = z;
        return this;
    }

    public CommentEntity setSoleTag(String str) {
        this.soleTag = str;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.avatar);
        parcel.writeString(this.bId);
        parcel.writeString(this.channelId);
        parcel.writeString(this.content);
        parcel.writeString(this.eId);
        parcel.writeString(this.firstLevelCommentId);
        parcel.writeTypedList(this.hotSubComments);
        parcel.writeString(this.id);
        parcel.writeString(this.label);
        parcel.writeString(this.nickName);
        parcel.writeString(this.parentId);
        parcel.writeString(this.parentContent);
        parcel.writeString(this.parentNickName);
        parcel.writeString(this.pin);
        parcel.writeString(this.publishTime);
        parcel.writeString(this.status);
        parcel.writeInt(this.hotCommentCount);
        parcel.writeInt(this.subCommentCount);
        parcel.writeInt(this.zanCount);
        parcel.writeString(this.timeShow);
        parcel.writeTypedList(this.subComments);
        parcel.writeByte(this.isZaned ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isAuthor ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isCommentAuthor ? (byte) 1 : (byte) 0);
        parcel.writeSerializable(this.daRenAndJump);
        parcel.writeByte(this.hasExpand ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isHot ? (byte) 1 : (byte) 0);
        parcel.writeString(this.soleTag);
        parcel.writeInt(this.type);
        parcel.writeString(this.location);
        parcel.writeByte(this.highLightFlag ? (byte) 1 : (byte) 0);
    }

    protected CommentEntity(Parcel parcel) {
        this.hasExpand = false;
        this.isHot = false;
        this.soleTag = "";
        this.avatar = parcel.readString();
        this.bId = parcel.readString();
        this.channelId = parcel.readString();
        this.content = parcel.readString();
        this.eId = parcel.readString();
        this.firstLevelCommentId = parcel.readString();
        Parcelable.Creator<CommentEntity> creator = CREATOR;
        this.hotSubComments = parcel.createTypedArrayList(creator);
        this.id = parcel.readString();
        this.label = parcel.readString();
        this.nickName = parcel.readString();
        this.parentId = parcel.readString();
        this.parentContent = parcel.readString();
        this.parentNickName = parcel.readString();
        this.pin = parcel.readString();
        this.publishTime = parcel.readString();
        this.status = parcel.readString();
        this.hotCommentCount = parcel.readInt();
        this.subCommentCount = parcel.readInt();
        this.zanCount = parcel.readInt();
        this.timeShow = parcel.readString();
        this.subComments = parcel.createTypedArrayList(creator);
        this.isZaned = parcel.readByte() != 0;
        this.isAuthor = parcel.readByte() != 0;
        this.isCommentAuthor = parcel.readByte() != 0;
        this.daRenAndJump = (JumpEntity) parcel.readSerializable();
        this.hasExpand = parcel.readByte() != 0;
        this.isHot = parcel.readByte() != 0;
        this.soleTag = parcel.readString();
        this.type = parcel.readInt();
        this.location = parcel.readString();
        this.highLightFlag = parcel.readByte() != 0;
    }
}
