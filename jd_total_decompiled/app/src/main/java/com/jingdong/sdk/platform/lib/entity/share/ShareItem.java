package com.jingdong.sdk.platform.lib.entity.share;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes10.dex */
public class ShareItem implements Parcelable, Cloneable {
    public static final Parcelable.Creator<ShareItem> CREATOR = new Parcelable.Creator<ShareItem>() { // from class: com.jingdong.sdk.platform.lib.entity.share.ShareItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareItem createFromParcel(Parcel parcel) {
            return new ShareItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareItem[] newArray(int i2) {
            return new ShareItem[i2];
        }
    };
    private static final String TAG = "ShareInfo";
    private String cancelEventId;
    private String channels;
    private String cpsUrl;
    private String[] defaultChannels;
    private String eventFrom;
    private String eventName;
    private String iconUrl;
    private String incentiveBizId;
    private String incentiveBizType;
    private String mpIconUrl;
    private String mpId;
    private String mpPath;
    private String normalText;
    private ShareImageItem shareImageInfo;
    private byte[] shareLogo;
    private String summary;
    private String summaryLottery;
    private String title;
    private String titleLottery;
    private String titleTimeline;
    private String transaction;
    private String url;
    private String vid;
    private String wxMomentsContent;
    private String wxcontent;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCancelEventId() {
        return this.cancelEventId;
    }

    public String getChannels() {
        return this.channels;
    }

    public List<String> getChannelsList() {
        if (!TextUtils.isEmpty(this.channels)) {
            return Arrays.asList(this.channels.split(DYConstants.DY_REGEX_COMMA));
        }
        return Arrays.asList(this.defaultChannels);
    }

    public String getCpsUrl() {
        return this.cpsUrl;
    }

    public String getEventFrom() {
        return this.eventFrom;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public String getIncentiveBizId() {
        return this.incentiveBizId;
    }

    public String getIncentiveBizType() {
        return this.incentiveBizType;
    }

    public String getMpIconUrl() {
        return this.mpIconUrl;
    }

    public String getMpId() {
        return this.mpId;
    }

    public String getMpPath() {
        return this.mpPath;
    }

    public String getNormalText() {
        return this.normalText;
    }

    public ShareImageItem getShareImageItem() {
        return this.shareImageInfo;
    }

    public byte[] getShareLogoBytes() {
        return (byte[]) this.shareLogo.clone();
    }

    public String getSummary() {
        return this.summary;
    }

    public String getSummaryLottery() {
        return this.summaryLottery;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleLottery() {
        return this.titleLottery;
    }

    public String getTitleTimeline() {
        return this.titleTimeline;
    }

    public String getTransaction() {
        return this.transaction;
    }

    public String getUrl() {
        return this.url;
    }

    public String getVid() {
        return TextUtils.isEmpty(this.vid) ? "" : this.vid;
    }

    public String getWxMomentsContent() {
        return this.wxMomentsContent;
    }

    public String getWxcontent() {
        return this.wxcontent;
    }

    public boolean isShareGift() {
        return (TextUtils.isEmpty(this.incentiveBizId) || TextUtils.isEmpty(this.incentiveBizType)) ? false : true;
    }

    public void setCancelEventId(String str) {
        this.cancelEventId = str;
    }

    public void setChannels(String str) {
        this.channels = str;
    }

    public void setCpsUrl(String str) {
        this.cpsUrl = str;
    }

    public void setEventFrom(String str) {
        this.eventFrom = str;
    }

    public void setEventName(String str) {
        this.eventName = str;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public void setIncentiveBizId(String str) {
        this.incentiveBizId = str;
    }

    public void setIncentiveBizType(String str) {
        this.incentiveBizType = str;
    }

    public void setMpIconUrl(String str) {
        this.mpIconUrl = str;
    }

    public void setMpId(String str) {
        this.mpId = str;
    }

    public void setMpPath(String str) {
        this.mpPath = str;
    }

    public void setNormalText(String str) {
        this.normalText = str;
    }

    public void setShareImageItem(ShareImageItem shareImageItem) {
        this.shareImageInfo = shareImageItem;
    }

    public void setShareLogo(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            this.shareLogo = byteArrayOutputStream.toByteArray();
        }
    }

    public void setSummary(String str) {
        this.summary = str;
    }

    public void setSummaryLottery(String str) {
        this.summaryLottery = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitleLottery(String str) {
        this.titleLottery = str;
    }

    public void setTitleTimeline(String str) {
        this.titleTimeline = str;
    }

    public void setTransaction(String str) {
        this.transaction = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVid(String str) {
        this.vid = str;
    }

    public void setWxMomentsContent(String str) {
        this.wxMomentsContent = str;
    }

    public void setWxcontent(String str) {
        this.wxcontent = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.title);
        parcel.writeString(this.titleTimeline);
        parcel.writeString(this.titleLottery);
        parcel.writeString(this.url);
        parcel.writeString(this.summary);
        parcel.writeString(this.summaryLottery);
        parcel.writeString(this.wxcontent);
        parcel.writeString(this.wxMomentsContent);
        parcel.writeString(this.normalText);
        parcel.writeString(this.iconUrl);
        parcel.writeString(this.eventFrom);
        parcel.writeString(this.cancelEventId);
        parcel.writeString(this.eventName);
        parcel.writeString(this.transaction);
        parcel.writeString(this.mpId);
        parcel.writeString(this.mpPath);
        parcel.writeString(this.mpIconUrl);
        parcel.writeString(this.channels);
        parcel.writeString(this.incentiveBizId);
        parcel.writeString(this.incentiveBizType);
        parcel.writeString(this.vid);
        parcel.writeParcelable(this.shareImageInfo, i2);
        parcel.writeStringArray(this.defaultChannels);
    }

    public ShareItem() {
        this.title = "";
        this.titleTimeline = "";
        this.titleLottery = "";
        this.url = "";
        this.cpsUrl = "";
        this.summary = "";
        this.summaryLottery = "";
        this.wxcontent = "";
        this.wxMomentsContent = "";
        this.normalText = "";
        this.iconUrl = "";
        this.shareLogo = new byte[0];
        this.eventFrom = "";
        this.cancelEventId = "";
        this.eventName = "";
        this.transaction = "";
        this.mpId = "";
        this.mpPath = "";
        this.mpIconUrl = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.defaultChannels = new String[]{"Wxfriends", "Wxmoments", "Sinaweibo", "QQfriends", "QQzone", "CopyURL"};
    }

    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public ShareItem m27clone() {
        ShareItem shareItem = new ShareItem();
        try {
            return (ShareItem) super.clone();
        } catch (CloneNotSupportedException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return shareItem;
            }
            return shareItem;
        }
    }

    public ShareItem(String str, String str2, String str3, String str4, String str5) {
        this.title = "";
        this.titleTimeline = "";
        this.titleLottery = "";
        this.url = "";
        this.cpsUrl = "";
        this.summary = "";
        this.summaryLottery = "";
        this.wxcontent = "";
        this.wxMomentsContent = "";
        this.normalText = "";
        this.iconUrl = "";
        this.shareLogo = new byte[0];
        this.eventFrom = "";
        this.cancelEventId = "";
        this.eventName = "";
        this.transaction = "";
        this.mpId = "";
        this.mpPath = "";
        this.mpIconUrl = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.defaultChannels = new String[]{"Wxfriends", "Wxmoments", "Sinaweibo", "QQfriends", "QQzone", "CopyURL"};
        this.url = str;
        this.title = str2;
        this.summary = str3;
        this.iconUrl = str4;
        this.eventName = str5;
    }

    public ShareItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, Bitmap bitmap) {
        this.title = "";
        this.titleTimeline = "";
        this.titleLottery = "";
        this.url = "";
        this.cpsUrl = "";
        this.summary = "";
        this.summaryLottery = "";
        this.wxcontent = "";
        this.wxMomentsContent = "";
        this.normalText = "";
        this.iconUrl = "";
        this.shareLogo = new byte[0];
        this.eventFrom = "";
        this.cancelEventId = "";
        this.eventName = "";
        this.transaction = "";
        this.mpId = "";
        this.mpPath = "";
        this.mpIconUrl = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.defaultChannels = new String[]{"Wxfriends", "Wxmoments", "Sinaweibo", "QQfriends", "QQzone", "CopyURL"};
        this.title = str;
        this.summary = str2;
        this.wxcontent = str2;
        this.wxMomentsContent = str3;
        this.url = str4;
        this.normalText = str5;
        this.eventFrom = str6;
        this.iconUrl = str7;
        setShareLogo(bitmap);
    }

    public ShareItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, Bitmap bitmap, String str8) {
        this.title = "";
        this.titleTimeline = "";
        this.titleLottery = "";
        this.url = "";
        this.cpsUrl = "";
        this.summary = "";
        this.summaryLottery = "";
        this.wxcontent = "";
        this.wxMomentsContent = "";
        this.normalText = "";
        this.iconUrl = "";
        this.shareLogo = new byte[0];
        this.eventFrom = "";
        this.cancelEventId = "";
        this.eventName = "";
        this.transaction = "";
        this.mpId = "";
        this.mpPath = "";
        this.mpIconUrl = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.defaultChannels = new String[]{"Wxfriends", "Wxmoments", "Sinaweibo", "QQfriends", "QQzone", "CopyURL"};
        this.title = str;
        this.summary = str2;
        this.wxcontent = str2;
        this.wxMomentsContent = str3;
        this.url = str4;
        this.normalText = str5;
        this.eventFrom = str6;
        this.iconUrl = str7;
        setShareLogo(bitmap);
        this.eventName = str8;
    }

    private ShareItem(Parcel parcel) {
        this.title = "";
        this.titleTimeline = "";
        this.titleLottery = "";
        this.url = "";
        this.cpsUrl = "";
        this.summary = "";
        this.summaryLottery = "";
        this.wxcontent = "";
        this.wxMomentsContent = "";
        this.normalText = "";
        this.iconUrl = "";
        this.shareLogo = new byte[0];
        this.eventFrom = "";
        this.cancelEventId = "";
        this.eventName = "";
        this.transaction = "";
        this.mpId = "";
        this.mpPath = "";
        this.mpIconUrl = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.defaultChannels = new String[]{"Wxfriends", "Wxmoments", "Sinaweibo", "QQfriends", "QQzone", "CopyURL"};
        this.title = parcel.readString();
        this.titleTimeline = parcel.readString();
        this.titleLottery = parcel.readString();
        this.url = parcel.readString();
        this.summary = parcel.readString();
        this.summaryLottery = parcel.readString();
        this.wxcontent = parcel.readString();
        this.wxMomentsContent = parcel.readString();
        this.normalText = parcel.readString();
        this.iconUrl = parcel.readString();
        this.eventFrom = parcel.readString();
        this.cancelEventId = parcel.readString();
        this.eventName = parcel.readString();
        this.transaction = parcel.readString();
        this.mpId = parcel.readString();
        this.mpPath = parcel.readString();
        this.mpIconUrl = parcel.readString();
        this.channels = parcel.readString();
        this.incentiveBizId = parcel.readString();
        this.incentiveBizType = parcel.readString();
        this.vid = parcel.readString();
        this.shareImageInfo = (ShareImageItem) parcel.readParcelable(ShareImageItem.class.getClassLoader());
        parcel.readStringArray(this.defaultChannels);
    }
}
