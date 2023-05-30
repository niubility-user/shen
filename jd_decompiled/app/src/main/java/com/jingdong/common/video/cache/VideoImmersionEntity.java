package com.jingdong.common.video.cache;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.ShareEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.ntask.NTaskModel;
import com.jingdong.common.video.cache.MiniWareInfoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class VideoImmersionEntity extends TalentBaseTEntity {
    public String cardType;
    public boolean hasCoupon;
    public ArrayList<ItemEntity> list;
    public String offset;
    public QuestionInfoEntity questionInfo;
    public int subCode;
    public SubjectInfoEntity subjectInfo;
    public ArrayList<TabItem> tabList;
    public ConfigEntity videoDetailConfigDto;
    public String zanImg;

    /* loaded from: classes6.dex */
    public static class ConfigEntity implements Serializable {
        public ABConfigEntity abConfig;
        public int before950;
        public ArrayList<ButtonConfEntity> buttonConf;
        public String commentPlaceholder;
        public String playTypeStandBy;
        public JumpEntity searchJump;
        public int showMessageNotice;
        public int showSearchButton;
        public int showSearchRecommend;
        public int showSkuComments;
        public SwitchConf switchConf;
        public String uiMode;

        /* loaded from: classes6.dex */
        public static class ABConfigEntity implements Serializable {
            public int closeSkuCardAnimation;
            public int miniSkuCard;
            public int resolutionRatio;
            public int skuTopTag;
        }

        /* loaded from: classes6.dex */
        public static class ButtonConfEntity implements Serializable {
            public String button;
            public String color;
            public String text;
        }

        /* loaded from: classes6.dex */
        public static class SwitchConf implements Serializable {
            public boolean popup;
            public int popupGuideTimes = 3;
            public boolean popupSkuCardSwitch;
            public boolean topicSmallWindowSwitch;
        }

        public boolean showSearchBtn() {
            return this.showSearchButton == 1 && this.searchJump != null;
        }
    }

    /* loaded from: classes6.dex */
    public static class ItemEntity implements Serializable {
        public ActivityEntity activity;
        public int adaptationType;
        public String authorId;
        public JumpEntity authorJump;
        public String authorName;
        public String authorPic;
        public String biBrokerInfo;
        public String biclk;
        public String biimpr;
        public String bindQuestion;
        public JDJSONObject buttonMsg;
        public String channel;
        public boolean closeDownwardTip;
        public String commentBId;
        public String commentChannelId;
        public boolean commentHot;
        public int commentNum;
        public String commentNumStr;
        public String commentToast;
        public ActivityEntity contentTaskResource;
        public String descForVideo;
        public boolean finishFxTask;
        public long fondNum;
        public String fondNumStr;
        public boolean hasAnswered;
        public int hasFollowed;
        public int hasFond;
        public boolean hasQuestionCountdown;
        public boolean hasShowedQuestionPanel;
        public int hasStore;
        public boolean hideActivity;
        public boolean hideAuthor_img;
        public boolean hideComment;
        public ArrayList<String> hideModules;
        public boolean hideResource;
        public boolean hideSearchButton;
        public boolean hideShare;
        public boolean hideStore;
        public boolean hidefission;
        public List<HotCommentEntity> hotCommentInfos;
        public String hotListImg;
        public String hotScore;
        public String id;
        public String indexImage;
        public boolean isAutoSlideNext;
        public boolean isFromJump;
        public boolean isHot;
        public JumpEntity leftJump;
        public AuthorLiveInfo mAuthorLiveInfo;
        public InteractIconData mInteractIconData;
        public XydItemEntity mXydItemEntity;
        public List<MiniWareInfoEntity.SkuInfo> miniSkuInfoList;
        public NTaskModel nTaskModel;
        public ArrayList<NhtagsEntity> nhtags;
        public String pin;
        public int playGuideFlag;
        public int playGuideStartTime;
        public PlayEntity playInfo;
        public ProcessEntity process;
        public long publishTime;
        public String pv;
        public JumpEntity reportJump;
        public int reportSwitch;
        public String seacherImpr;
        public JCommandShareEntity shareInfo;
        public String shopId;
        public String shopImg;
        public JumpEntity shopJump;
        public String shopName;
        public SkuGuideEntity skuGuide;
        public SkuEntity skuInfo;
        public ArrayList<SkuEntity> skuList;
        public int skuNum;
        public String spuId;
        public String spuText;
        public boolean store;
        public String storeNum;
        public String strategy;
        public int style;
        public int subPosition;
        public SubjectActivityEntity subjectActivity;
        public String subjectId;
        public JumpEntity subjectJump;
        public boolean subjectThumbsUpDone;
        public String subjectTitle;
        public String tagName;
        public ArrayList<TagEntity> tags;
        public String talentArea;
        public String title;
        public ToastEntity toast;
        public String unionId;
        public String venderId;
        public ArrayList<BubbleCommentEntity> videoBubbleComment;
        public List<VideoColectionListEntity> videoCollectionList;
        public VideoEntity videoInfo;
        public boolean wishSwitch;
        public int layoutType = 1;
        public int screenType = 1;

        /* loaded from: classes6.dex */
        public static class ActivityEntity implements Serializable {
            public int cardType;
            public String id;
            public String indexImage;
            public JumpEntity linkJump;
            public String name;
            public String newBubble;
            public String oldBubble;
        }

        /* loaded from: classes6.dex */
        public static class AuthorLiveInfo implements Serializable {
            public JumpEntity jump;
            public String liveId;
            public String liveStatus;
            public String liveTitle;
            public long publishTime;

            public String getJumpString() {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_FIND_LIVE_PRE);
                JDJSONObject jDJSONObject2 = new JDJSONObject();
                jDJSONObject2.put("position", (Object) "0");
                jDJSONObject2.put("id", (Object) this.liveId);
                jDJSONObject.put("params", (Object) jDJSONObject2.toJSONString());
                return jDJSONObject.toJSONString();
            }
        }

        /* loaded from: classes6.dex */
        public static class BubbleCommentEntity implements Serializable {
            public String avatar;
            public String bId;
            public int businessId;
            public int commentType;
            public String content;
            public JumpEntity daRenAndJump;
            public String eId;
            public String firstLevelCommentId;
            public String id;
            public boolean isAuthor;
            public boolean isCommentAuthor;
            public boolean isZaned;
            public String nickName;
            public String publishTime;
            public String status;
            public String timeShow;
            public int zanCount;

            public String toString() {
                return "BubbleCommentEntity{commentType=" + this.commentType + ", avatar='" + this.avatar + "', businessId=" + this.businessId + ", content='" + this.content + "', eId='" + this.eId + "', firstLevelCommentId='" + this.firstLevelCommentId + "', id='" + this.id + "', isAuthor=" + this.isAuthor + ", isCommentAuthor=" + this.isCommentAuthor + ", isZaned=" + this.isZaned + ", nickName='" + this.nickName + "', daRenAndJump='" + this.daRenAndJump + "', publishTime='" + this.publishTime + "', timeShow='" + this.timeShow + "', status='" + this.status + "', zanCount=" + this.zanCount + ", bId='" + this.bId + "'}";
            }
        }

        /* loaded from: classes6.dex */
        public static class CouponInfoEntity implements Serializable {
            public String cKey;
            public CouponMonitor couponMonitor;
            public String des;
            public boolean localCouponReceived;
            public boolean receiveStatus;
        }

        /* loaded from: classes6.dex */
        public static class CouponMonitor implements Serializable {
            public String appid;
            public String batchId;
            public String biinfo;
            public String channel;
            public String channelDetail;
            public String couponPos;
            public String couponSource;
            public String couponSourceDetail;
            public String getType;
            public String is_jr;
            public String platformid;
            public String skus;
            public String subChannel;
        }

        /* loaded from: classes6.dex */
        public static class FinalPriceEntity implements Serializable {
            public int count;
            public String price;
        }

        /* loaded from: classes6.dex */
        public static class HotCommentEntity {
            public String cId;
            public String comment;
            public String img;
        }

        /* loaded from: classes6.dex */
        public static class InteractIconData implements Serializable {
            public String activityId;
            public String activityUrl;
            public String contentId;
            public int grantStatus;
            public String iconText;
            public String iconUrl;
            public String openApp;
            public int prizeType;
            public String shareText;
            public String shareUrl;

            public String getActivityId() {
                return this.activityId;
            }

            public String getActivityUrl() {
                return this.activityUrl;
            }

            public String getContentId() {
                return this.contentId;
            }

            public int getGrantStatus() {
                return this.grantStatus;
            }

            public String getIconText() {
                return this.iconText;
            }

            public String getIconUrl() {
                return this.iconUrl;
            }

            public String getOpenApp() {
                return this.openApp;
            }

            public int getPrizeType() {
                return this.prizeType;
            }

            public String getShareText() {
                return this.shareText;
            }

            public String getShareUrl() {
                return this.shareUrl;
            }

            public void setActivityId(String str) {
                this.activityId = str;
            }

            public void setActivityUrl(String str) {
                this.activityUrl = str;
            }

            public void setContentId(String str) {
                this.contentId = str;
            }

            public void setGrantStatus(int i2) {
                this.grantStatus = i2;
            }

            public void setIconText(String str) {
                this.iconText = str;
            }

            public void setIconUrl(String str) {
                this.iconUrl = str;
            }

            public void setOpenApp(String str) {
                this.openApp = str;
            }

            public void setPrizeType(int i2) {
                this.prizeType = i2;
            }

            public void setShareText(String str) {
                this.shareText = str;
            }

            public void setShareUrl(String str) {
                this.shareUrl = str;
            }

            public String toString() {
                return "InteractIconData{contentId='" + this.contentId + "', activityId='" + this.activityId + "', activityUrl='" + this.activityUrl + "', grantStatus=" + this.grantStatus + ", prizeType=" + this.prizeType + ", iconUrl='" + this.iconUrl + "', iconText='" + this.iconText + "', shareText='" + this.shareText + "'}";
            }
        }

        /* loaded from: classes6.dex */
        public static class InteractionEntity implements Serializable {
            public String bizMsg;
            public String busiCode;
            public String code;
            public InteractIconData data;
            public String message;

            public String getBizMsg() {
                return this.bizMsg;
            }

            public String getBusiCode() {
                return this.busiCode;
            }

            public String getCode() {
                return this.code;
            }

            public InteractIconData getData() {
                return this.data;
            }

            public String getMessage() {
                return this.message;
            }

            public void setBizMsg(String str) {
                this.bizMsg = str;
            }

            public void setBusiCode(String str) {
                this.busiCode = str;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public void setData(InteractIconData interactIconData) {
                this.data = interactIconData;
            }

            public void setMessage(String str) {
                this.message = str;
            }

            public String toString() {
                return "InteractionEntity{code='" + this.code + "', message='" + this.message + "', busiCode='" + this.busiCode + "', bizMsg='" + this.bizMsg + "', data=" + this.data + '}';
            }
        }

        /* loaded from: classes6.dex */
        public static class JCommand implements Serializable {
            public String keyChannel;
            public String keyContent;
            public long keyEndTime;
            public String keyId;
            public String keyImg;
            public String keyOpenapp;
            public String keyTitle;
            public String keyVer;
            public String sourceCode;
            public String typeCode;
            public String url;
        }

        /* loaded from: classes6.dex */
        public static class JCommandShareEntity extends ShareEntity implements Serializable {
            public JCommand command;
            public MpInfoEntity mpInfo;
        }

        /* loaded from: classes6.dex */
        public static class MpInfoEntity implements Serializable {
            public String mpIconUrl;
            public String mpId;
            public String mpPath;
            public int mpType;
        }

        /* loaded from: classes6.dex */
        public class NhtagsEntity implements Serializable {
            public String id;

            public NhtagsEntity() {
            }
        }

        /* loaded from: classes6.dex */
        public static class PlayEntity implements Serializable {
            public int VideoDuration;
            public String vid;
            public String videoImg;
            public String videoUnique;
            public String videoUrl;

            public String toString() {
                return "PlayEntity{vid='" + this.vid + "', videoUrl='" + this.videoUrl + "', videoImg='" + this.videoImg + "', VideoDuration=" + this.VideoDuration + ", videoUnique='" + this.videoUnique + "'}";
            }
        }

        /* loaded from: classes6.dex */
        public static class ProcessEntity implements Serializable {
            public String indexImage;
        }

        /* loaded from: classes6.dex */
        public static class RankInfoEntity implements Serializable {
            public String rankIcon;
            public String rankName;
        }

        /* loaded from: classes6.dex */
        public static class SkuEntity implements Serializable {
            public int closedLoop;
            public CouponInfoEntity couponInfo;
            public String deliveryPrice;
            public int exceptionStyle;
            public int exposeEndTime;
            public int exposeStartTime;
            public int exposedAuto;
            public FinalPriceEntity finalPrice;
            public boolean hasGottenMiniBtnDesc;
            public long id;
            public String img;
            public JumpEntity jump;
            public int lookSimilarFlag;
            public String name;
            public String originalPrice;
            public String pMtaStatus;
            public PdBottomButtonInfo pdBottomButtonInfo;
            public String plusPrice;
            public String price;
            private String promotionInfo;
            public int promotionSize;
            public boolean promotionStatus;
            public boolean qushihaowu;
            public RankInfoEntity rankInfo;
            public List<String> salesTags;
            public String shortName;
            public String sku;
            public String skuComments;
            public JumpEntity skuJump;
            public int superNew;
            public String tagBackColor;
            public SkuTitleTagEntity tagMtaInfo;
            public String tagName;
            public List<String> tags;
            public String title;
            public int type;
            public boolean yushou;

            public String getDiscountMtaStr(boolean z) {
                if (this.finalPrice == null || z) {
                    CouponInfoEntity couponInfoEntity = this.couponInfo;
                    return (couponInfoEntity == null || TextUtils.isEmpty(couponInfoEntity.des)) ? this.promotionSize > 0 ? "1" : "-100" : "4";
                }
                return "3";
            }

            public String getPromotionInfo() {
                if (this.promotionSize > 0) {
                    return this.promotionSize + "\u4ef6\u8d60\u54c1";
                }
                return "";
            }

            public boolean showSuperNewTag() {
                return this.superNew == 1;
            }

            public String toString() {
                return "SkuEntity{sku='" + this.sku + "', id=" + this.id + ", title='" + this.title + "', img='" + this.img + "', price='" + this.price + "', jump=" + this.jump + ", type=" + this.type + ", exceptionStyle=" + this.exceptionStyle + ", exposedAuto=" + this.exposedAuto + ", exposeStartTime=" + this.exposeStartTime + ", tagBackColor=" + this.tagBackColor + ", tagName=" + this.tagName + '}';
            }
        }

        /* loaded from: classes6.dex */
        public static class SkuGuideEntity implements Serializable {
            public String skuGuideDesc;
            public String type;
        }

        /* loaded from: classes6.dex */
        public static class SkuTitleTagEntity implements Serializable {
            public int imageResource;
            public String tagBackColor;
            public String tagName = "";
        }

        /* loaded from: classes6.dex */
        public static class TagEntity implements Serializable {
            public String id;
            public JumpEntity jump;
            public String name;

            public String toString() {
                return "TagEntity{id='" + this.id + "', name='" + this.name + "', jump=" + this.jump + '}';
            }
        }

        /* loaded from: classes6.dex */
        public static class ToastEntity implements Serializable {
            public String indexImage;
        }

        /* loaded from: classes6.dex */
        public static class VideoColectionListEntity implements Serializable {
            public String authorImg;
            public String authorName;
            public String id;
            public String indexImage;
            public JumpEntity jump;
            public String pageView;
            public int style;
            public String title;
            public long videoDuration;
            public String videoImg;
        }

        /* loaded from: classes6.dex */
        public static class VideoEntity implements Serializable {
            public String vd;
            public int vh;
            public String vs;
            public int vw;

            public String toString() {
                return "VideoEntity{vw=" + this.vw + ", vh=" + this.vh + ", vd='" + this.vd + "', vs='" + this.vs + "'}";
            }
        }

        /* loaded from: classes6.dex */
        public static class XydItemEntity implements Serializable {
            public String button;
            public String content;
            public String imgUrl;
            public String wishUrl;
        }

        public String toString() {
            return "ItemEntity{layoutType=" + this.layoutType + ", adaptationType=" + this.adaptationType + ", screenType=" + this.screenType + ", videoInfo=" + this.videoInfo + ", playInfo=" + this.playInfo + ", id='" + this.id + "', title='" + this.title + "', indexImage='" + this.indexImage + "', publishTime=" + this.publishTime + ", id='" + this.authorId + "', name='" + this.authorName + "', jump=" + this.authorJump + ", pin='" + this.pin + "', commentBId='" + this.commentBId + "', commentChannelId='" + this.commentChannelId + "', commentNum=" + this.commentNum + ", commentNumStr='" + this.commentNumStr + "', hasFond=" + this.hasFond + ", fondNum=" + this.fondNum + ", fondNumStr='" + this.fondNumStr + "', hasStore=" + this.hasStore + ", hasFollowed=" + this.hasFollowed + ", reportSwitch=" + this.reportSwitch + ", reportJump=" + this.reportJump + ", shareInfo=" + this.shareInfo + ", skuList=" + this.skuList + ", skuNum=" + this.skuNum + ", tags=" + this.tags + ", subPosition='" + this.subPosition + "', unionId='" + this.unionId + "', style=" + this.style + ", channel='" + this.channel + "', playGuideFlag='" + this.playGuideFlag + "', playGuideStartTime='" + this.playGuideStartTime + "', biimpr='" + this.biimpr + "', biclk='" + this.biclk + "'}";
        }
    }

    /* loaded from: classes6.dex */
    public static class QuestionInfoEntity implements Serializable {
        public String taskId;
        public String title;
        public String vTimes;
    }

    /* loaded from: classes6.dex */
    public static class SubjectActivityEntity implements Serializable {
        public String buttonMsg;
        public ArrayList<String> marketingTargetImgList;
    }

    /* loaded from: classes6.dex */
    public static class SubjectInfoEntity implements Serializable {
        public SubjectActivityEntity subjectActivity;
        public String subjectId;
        public JumpEntity subjectJump;
        public String subjectTitle;
        public String vTimes;
    }

    /* loaded from: classes6.dex */
    public static class TabItem implements Serializable {
        public String brokerInfo;
        public boolean fixed;
        public String id;
        public String nickName;
        public String title;
        public String type;
    }

    public boolean isSuccess() {
        return "0".equals(this.code);
    }

    public String toString() {
        return "VideoImmersionEntity{code='" + this.code + "', subCode=" + this.subCode + ", offset='" + this.offset + "', list=" + this.list + ", hasCoupon=" + this.hasCoupon + ", zanImg='" + this.zanImg + "', videoDetailConfigDto=" + this.videoDetailConfigDto + '}';
    }
}
