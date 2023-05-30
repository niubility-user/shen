package com.jingdong.common.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class ShareInfo implements Parcelable, Cloneable {
    private static final String LANDSCAPE = "1";
    private static final String TAG = "ShareInfo";
    private String abilityName;
    private String action;
    private int bizType;
    private String bundleName;
    private String cancelEventId;
    private String channels;
    public List<CmShareChannelInfo> cmChannelList;
    private String cpsUrl;
    public int defaultShowFriendChannel;
    private String eventFrom;
    private String eventName;
    private String extParams;
    public int hidePlus;
    private String iconUrl;
    private String incentiveBizId;
    private String incentiveBizType;
    private String isNewWeixinShareUI;
    private String isScreenOrientationLandscape;
    private String jdMpTaskAction;
    private String keyShareJsonStr;
    private String mpIconUrl;
    private String mpId;
    private String mpLocalIconPath;
    private String mpPath;
    private int mpType;
    private String normalText;
    private String panelBanner;
    private String price;
    private ShareImageInfo shareImageInfo;
    private byte[] shareLogo;
    private String shareSource;
    public int showFriendChannel;
    private String summary;
    private String summaryLottery;
    private String title;
    private String titleLottery;
    private String titleTimeline;
    private String transaction;
    private String url;
    private String vid;
    private String weiXinContentParam;
    private String wxMomentsContent;
    private String wxcontent;
    private static final String[] DEFAULT_CHANNELS = {"Wxfriends", "Wxmoments", "Sinaweibo", "QQfriends", "QQzone", "CopyURL", ShareUtil.S_JDFamily};
    public static final Parcelable.Creator<ShareInfo> CREATOR = new Parcelable.Creator<ShareInfo>() { // from class: com.jingdong.common.entity.ShareInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareInfo createFromParcel(Parcel parcel) {
            return new ShareInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ShareInfo[] newArray(int i2) {
            return new ShareInfo[i2];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAbilityName() {
        return this.abilityName;
    }

    public String getAction() {
        return this.action;
    }

    public int getBizType() {
        return this.bizType;
    }

    public String getBundleName() {
        return this.bundleName;
    }

    public String getCancelEventId() {
        return this.cancelEventId;
    }

    public String getChannels() {
        return this.channels;
    }

    public List<String> getChannelsList() {
        if (!TextUtils.isEmpty(this.channels)) {
            this.defaultShowFriendChannel = 0;
            ArrayList arrayList = new ArrayList(Arrays.asList(this.channels.split(DYConstants.DY_REGEX_COMMA)));
            arrayList.remove(ShareUtil.S_Hw_CaasShare);
            return arrayList;
        }
        this.defaultShowFriendChannel = 1;
        return Arrays.asList(DEFAULT_CHANNELS);
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

    public String getExtParams() {
        return this.extParams;
    }

    public int getHidePlus() {
        return this.hidePlus;
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

    public String getIsNewWeixinShareUI() {
        return this.isNewWeixinShareUI;
    }

    public String getJdMpTaskAction() {
        return this.jdMpTaskAction;
    }

    public String getKeyShareJsonStr() {
        return this.keyShareJsonStr;
    }

    public String getMpIconUrl() {
        return this.mpIconUrl;
    }

    public String getMpId() {
        return this.mpId;
    }

    public String getMpLocalIconPath() {
        return this.mpLocalIconPath;
    }

    public String getMpPath() {
        return this.mpPath;
    }

    public int getMpType() {
        return this.mpType;
    }

    public String getNormalText() {
        return this.normalText;
    }

    public String getPanelBanner() {
        return this.panelBanner;
    }

    public String getPrice() {
        return this.price;
    }

    public ShareImageInfo getShareImageInfo() {
        return this.shareImageInfo;
    }

    public byte[] getShareLogoBytes() {
        return (byte[]) this.shareLogo.clone();
    }

    public String getShareSource() {
        return this.shareSource;
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

    public String getWeiXinContentParam() {
        return this.weiXinContentParam;
    }

    public String getWxMomentsContent() {
        return this.wxMomentsContent;
    }

    public String getWxcontent() {
        return this.wxcontent;
    }

    public void hidePlusTag() {
        this.hidePlus = 1;
    }

    public boolean isShareGift() {
        return (TextUtils.isEmpty(this.incentiveBizId) || TextUtils.isEmpty(this.incentiveBizType)) ? false : true;
    }

    public void setAbilityName(String str) {
        this.abilityName = str;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setBizType(int i2) {
        this.bizType = i2;
    }

    public void setBundleName(String str) {
        this.bundleName = str;
    }

    public void setCancelEventId(String str) {
        this.cancelEventId = str;
    }

    public void setChannels(String str) {
        this.channels = str;
    }

    public void setCmChannelList(List<CmShareChannelInfo> list) {
        this.cmChannelList = list;
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

    public void setExtParams(String str) {
        this.extParams = str;
    }

    public void setHidePlus(int i2) {
        this.hidePlus = i2;
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

    public void setIsNewWeixinShareUI(String str) {
        this.isNewWeixinShareUI = str;
    }

    public void setJdMpTaskAction(String str) {
        this.jdMpTaskAction = str;
    }

    public void setKeyShareJsonStr(String str) {
        this.keyShareJsonStr = str;
    }

    public void setMpIconUrl(String str) {
        this.mpIconUrl = str;
    }

    public void setMpId(String str) {
        this.mpId = str;
    }

    public void setMpLocalIconPath(String str) {
        this.mpLocalIconPath = str;
    }

    public void setMpPath(String str) {
        this.mpPath = str;
    }

    public void setMpType(int i2) {
        this.mpType = i2;
    }

    public void setNormalText(String str) {
        this.normalText = str;
    }

    public void setPanelBanner(String str) {
        this.panelBanner = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setScreenOrientation(String str) {
        this.isScreenOrientationLandscape = str;
        ShareValues.updateScreenOrientation(TextUtils.equals(str, "1"));
    }

    public void setShareImageInfo(ShareImageInfo shareImageInfo) {
        this.shareImageInfo = shareImageInfo;
    }

    public void setShareLogo(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            this.shareLogo = byteArrayOutputStream.toByteArray();
        }
    }

    public void setShareSource(String str) {
        this.shareSource = str;
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

    public void setWeiXinContentParam(String str) {
        this.weiXinContentParam = str;
    }

    public void setWxMomentsContent(String str) {
        this.wxMomentsContent = str;
    }

    public void setWxcontent(String str) {
        this.wxcontent = str;
    }

    public void showJDFriendChannel(boolean z) {
        this.showFriendChannel = z ? 2 : 1;
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
        parcel.writeInt(this.mpType);
        parcel.writeString(this.mpPath);
        parcel.writeString(this.mpLocalIconPath);
        parcel.writeString(this.mpIconUrl);
        parcel.writeString(this.channels);
        parcel.writeString(this.incentiveBizId);
        parcel.writeString(this.isNewWeixinShareUI);
        parcel.writeString(this.incentiveBizType);
        parcel.writeString(this.vid);
        parcel.writeInt(this.bizType);
        parcel.writeParcelable(this.shareImageInfo, i2);
        parcel.writeString(this.action);
        parcel.writeString(this.keyShareJsonStr);
        parcel.writeString(this.panelBanner);
        parcel.writeInt(this.hidePlus);
        parcel.writeString(this.jdMpTaskAction);
        parcel.writeString(this.shareSource);
        parcel.writeString(this.weiXinContentParam);
        parcel.writeTypedList(this.cmChannelList);
        parcel.writeInt(this.defaultShowFriendChannel);
        parcel.writeInt(this.showFriendChannel);
        parcel.writeString(this.abilityName);
        parcel.writeString(this.bundleName);
        parcel.writeString(this.price);
        parcel.writeString(this.extParams);
        parcel.writeString(this.isScreenOrientationLandscape);
    }

    public ShareInfo() {
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
        this.mpType = 0;
        this.mpPath = "";
        this.mpIconUrl = "";
        this.mpLocalIconPath = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.isNewWeixinShareUI = "0";
        this.bizType = 0;
        this.action = "";
        this.panelBanner = "";
        this.hidePlus = 0;
        this.cmChannelList = new ArrayList();
        this.defaultShowFriendChannel = 0;
        this.showFriendChannel = 0;
        this.isScreenOrientationLandscape = "0";
    }

    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public ShareInfo m20clone() {
        ShareInfo shareInfo = new ShareInfo();
        try {
            return (ShareInfo) super.clone();
        } catch (CloneNotSupportedException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return shareInfo;
            }
            return shareInfo;
        }
    }

    public ShareInfo(String str, String str2, String str3, String str4, String str5) {
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
        this.mpType = 0;
        this.mpPath = "";
        this.mpIconUrl = "";
        this.mpLocalIconPath = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.isNewWeixinShareUI = "0";
        this.bizType = 0;
        this.action = "";
        this.panelBanner = "";
        this.hidePlus = 0;
        this.cmChannelList = new ArrayList();
        this.defaultShowFriendChannel = 0;
        this.showFriendChannel = 0;
        this.isScreenOrientationLandscape = "0";
        this.url = str;
        this.title = str2;
        this.summary = str3;
        this.iconUrl = str4;
        this.shareSource = str5;
    }

    public ShareInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, Bitmap bitmap) {
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
        this.mpType = 0;
        this.mpPath = "";
        this.mpIconUrl = "";
        this.mpLocalIconPath = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.isNewWeixinShareUI = "0";
        this.bizType = 0;
        this.action = "";
        this.panelBanner = "";
        this.hidePlus = 0;
        this.cmChannelList = new ArrayList();
        this.defaultShowFriendChannel = 0;
        this.showFriendChannel = 0;
        this.isScreenOrientationLandscape = "0";
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

    public ShareInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, Bitmap bitmap, String str8) {
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
        this.mpType = 0;
        this.mpPath = "";
        this.mpIconUrl = "";
        this.mpLocalIconPath = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.isNewWeixinShareUI = "0";
        this.bizType = 0;
        this.action = "";
        this.panelBanner = "";
        this.hidePlus = 0;
        this.cmChannelList = new ArrayList();
        this.defaultShowFriendChannel = 0;
        this.showFriendChannel = 0;
        this.isScreenOrientationLandscape = "0";
        this.title = str;
        this.summary = str2;
        this.wxcontent = str2;
        this.wxMomentsContent = str3;
        this.url = str4;
        this.normalText = str5;
        this.eventFrom = str6;
        this.iconUrl = str7;
        setShareLogo(bitmap);
        this.shareSource = str8;
    }

    private ShareInfo(Parcel parcel) {
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
        this.mpType = 0;
        this.mpPath = "";
        this.mpIconUrl = "";
        this.mpLocalIconPath = "";
        this.channels = "";
        this.incentiveBizType = "";
        this.incentiveBizId = "";
        this.isNewWeixinShareUI = "0";
        this.bizType = 0;
        this.action = "";
        this.panelBanner = "";
        this.hidePlus = 0;
        this.cmChannelList = new ArrayList();
        this.defaultShowFriendChannel = 0;
        this.showFriendChannel = 0;
        this.isScreenOrientationLandscape = "0";
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
        this.mpType = parcel.readInt();
        this.mpPath = parcel.readString();
        this.mpLocalIconPath = parcel.readString();
        this.mpIconUrl = parcel.readString();
        this.channels = parcel.readString();
        this.incentiveBizId = parcel.readString();
        this.isNewWeixinShareUI = parcel.readString();
        this.incentiveBizType = parcel.readString();
        this.vid = parcel.readString();
        this.bizType = parcel.readInt();
        this.shareImageInfo = (ShareImageInfo) parcel.readParcelable(ShareImageInfo.class.getClassLoader());
        this.action = parcel.readString();
        this.keyShareJsonStr = parcel.readString();
        this.panelBanner = parcel.readString();
        this.hidePlus = parcel.readInt();
        this.jdMpTaskAction = parcel.readString();
        this.shareSource = parcel.readString();
        this.weiXinContentParam = parcel.readString();
        parcel.readTypedList(this.cmChannelList, CmShareChannelInfo.CREATOR);
        this.defaultShowFriendChannel = parcel.readInt();
        this.showFriendChannel = parcel.readInt();
        this.abilityName = parcel.readString();
        this.bundleName = parcel.readString();
        this.price = parcel.readString();
        this.extParams = parcel.readString();
        this.isScreenOrientationLandscape = parcel.readString();
    }
}
