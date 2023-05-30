package com.jingdong.common.messagecenter;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class NotificationMessageSummary implements Parcelable {
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String ACTIVITY_ID = "activityId";
    public static final String APP_ID = "appId";
    public static final String BC_FLAG = "bcFlag";
    public static final String BUSINESS_TYPE = "businessCategoryId";
    public static final String B_CONTENT = "bcontent";
    public static final String B_TYPE = "btype";
    public static final String CONTAINER_ID = "containerType";
    public static final String CONTENT_KEY = "ALERT";
    public static final String COUPONS_FLAG = "couponsFlag";
    public static final String COUPONS_ID = "couponsId";
    public static final Parcelable.Creator<NotificationMessageSummary> CREATOR = new Parcelable.Creator<NotificationMessageSummary>() { // from class: com.jingdong.common.messagecenter.NotificationMessageSummary.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationMessageSummary createFromParcel(Parcel parcel) {
            return new NotificationMessageSummary(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NotificationMessageSummary[] newArray(int i2) {
            return new NotificationMessageSummary[i2];
        }
    };
    public static final String DD_MSG = "dd";
    public static final String DEVTYPE = "devType";
    public static final String ECHO = "echo";
    public static final String EXTRAS_KEY = "EXTRAS";
    public static final String GRAY_HASH_END = "androidLandPageBackGrayEnd";
    public static final String GRAY_HASH_START = "androidLandPageBackGrayStart";
    public static final String ID_MSG = "idMsg";
    public static final String IS_FEEDBACK = "isFeedback";
    public static final String KEY = "key";
    public static final String KEY_WORD = "keyword";
    public static final String LAND_PAGE = "landPageId";
    public static final String LAND_PAGE_FLAG = "landPageFlag";
    public static final String LAND_PAGE_URL = "landPageUrl";
    public static final String MSGTYPE = "msgType";
    public static final String MSG_BODY = "msgBody";
    public static final String MSG_ID = "msgId";
    public static final String MSG_SEQ = "msgSeq";
    public static final String NIMG_PATH = "nImgPath";
    public static final String NOTIFY_MOLD_ID = "notifyMoldId";
    public static final String NOTIFY_TEMPLATE = "notifyTemplateId";
    public static final String ORDERID = "orderId";
    public static final String PRICE = "price";
    public static final String PUSH_TEXT_ID = "pushTextId";
    public static final String QUESTION_ID = "questionId";
    public static final String SECOND_LIST_TITLE = "secondListTitle";
    public static final String SERIAL_NO = "serialNo";
    public static final String SET_ID = "setId";
    public static final String SHARE_FLAG = "shareFlag";
    public static final String SHOPID = "shopId";
    public static final String SOUND_SOURCE = "soundSource";
    public static final String SOURCE = "source";
    public static final String SQIMG_PATH = "sqImgPath";
    public static final String SUB_TASK_ID = "subTaskId";
    public static final String TAB = "tab";
    private static final String TAG = "NotificationMessageSumm";
    public static final String TASK_ID = "taskId";
    public static final String TEST_ID = "testId";
    public static final String TITLE_KEY = "TITLE";
    public static final String TRANSMISSION = "transmission";
    public static final String VENDER_ID = "venderId";
    public static final String VENDORID = "vendorId";
    public static final String WAREID = "wareId";
    public static final String WATCH_CATEGORY_ID = "WATCH_CATEGORY";
    public String accountType;
    public String activityId;
    public String alert;
    public int appId;
    public String bcFlag;
    public String bcontent;
    public String btype;
    public String businessType;
    public String containerType;
    public String couponsFlag;
    public String couponsId;
    public String dd;
    public int devType;
    public String echo;
    private JSONObjectProxy extrasProxy;
    public String grayHashEnd;
    public String grayHashStart;
    public String idMsg;
    public String imgPath;
    public String isFeedBack;
    public String key;
    public String keyWord;
    public String landPageFlag;
    public String landPageId;
    public String landPageUrl;
    private JSONObjectProxy msgBodyExtrasProxy;
    public String msgId;
    public String msgSeq;
    public int msgType;
    public String notifyMoldId;
    public String notifyTemplateId;
    public String orderId;
    public String price;
    public String pushTextId;
    public String questionId;
    public String secondListTitle;
    public int serialNo;
    public int setId;
    public String shareFlag;
    public String shopId;
    public String soundSource;
    public String source;
    public String sqImgPath;
    public String subTaskId;
    public String tab;
    public String taskId;
    public String testid;
    public String title;
    public String transmission;
    public String venderId;
    public String vendorId;
    public String wareId;
    public String watchCategoryId;

    public NotificationMessageSummary(JSONObjectProxy jSONObjectProxy) {
        this.msgSeq = jSONObjectProxy.optString(MSG_SEQ);
        this.devType = jSONObjectProxy.optInt(DEVTYPE);
        this.echo = jSONObjectProxy.optString(ECHO);
        this.msgType = jSONObjectProxy.optInt(MSGTYPE);
        this.idMsg = jSONObjectProxy.optString(ID_MSG);
        this.appId = jSONObjectProxy.optInt("appId");
        this.setId = jSONObjectProxy.optInt(SET_ID);
        this.serialNo = jSONObjectProxy.optInt(SERIAL_NO);
        String optString = jSONObjectProxy.optString(MSG_BODY);
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        try {
            setMsgBodyExtrasProxy(new JSONObjectProxy(new JSONObject(optString)));
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public JSONObjectProxy getExtrasProxy() {
        return this.extrasProxy;
    }

    public JSONObjectProxy getMsgBodyExtrasProxy() {
        return this.msgBodyExtrasProxy;
    }

    public void setExtrasProxy(JSONObjectProxy jSONObjectProxy) {
        this.extrasProxy = jSONObjectProxy;
        this.containerType = jSONObjectProxy.optString(CONTAINER_ID);
        this.imgPath = jSONObjectProxy.optString(NIMG_PATH);
        this.notifyTemplateId = jSONObjectProxy.optString(NOTIFY_TEMPLATE);
        this.landPageId = jSONObjectProxy.optString("landPageId");
        this.msgId = jSONObjectProxy.optString("msgId");
        this.landPageUrl = jSONObjectProxy.optString(LAND_PAGE_URL);
        this.shareFlag = jSONObjectProxy.optString(SHARE_FLAG);
        this.activityId = jSONObjectProxy.optString("activityId");
        this.shopId = jSONObjectProxy.optString("shopId");
        this.vendorId = jSONObjectProxy.optString("vendorId");
        this.wareId = jSONObjectProxy.optString("wareId");
        this.orderId = jSONObjectProxy.optString("orderId");
        this.businessType = jSONObjectProxy.optString(BUSINESS_TYPE);
        this.couponsFlag = jSONObjectProxy.optString(COUPONS_FLAG);
        this.secondListTitle = jSONObjectProxy.optString(SECOND_LIST_TITLE);
        this.bcFlag = jSONObjectProxy.optString(BC_FLAG);
        this.landPageFlag = jSONObjectProxy.optString(LAND_PAGE_FLAG);
        this.price = jSONObjectProxy.optString("price");
        this.keyWord = jSONObjectProxy.optString("keyword");
        this.subTaskId = jSONObjectProxy.optString(SUB_TASK_ID);
        this.questionId = jSONObjectProxy.optString(QUESTION_ID);
        this.tab = jSONObjectProxy.optString(TAB);
        this.sqImgPath = jSONObjectProxy.optString(SQIMG_PATH);
        this.notifyMoldId = jSONObjectProxy.optString(NOTIFY_MOLD_ID);
        this.key = jSONObjectProxy.optString("key");
        this.pushTextId = jSONObjectProxy.optString(PUSH_TEXT_ID);
        this.source = jSONObjectProxy.optString("source");
        this.soundSource = jSONObjectProxy.optString(SOUND_SOURCE);
        this.dd = jSONObjectProxy.optString(DD_MSG);
        this.transmission = jSONObjectProxy.optString(TRANSMISSION);
        this.accountType = jSONObjectProxy.optString("accountType");
        this.isFeedBack = jSONObjectProxy.optString(IS_FEEDBACK);
        this.couponsId = jSONObjectProxy.optString(COUPONS_ID);
        this.venderId = jSONObjectProxy.optString("venderId");
        this.testid = jSONObjectProxy.optString(TEST_ID);
        this.grayHashStart = jSONObjectProxy.optString(GRAY_HASH_START);
        this.grayHashEnd = jSONObjectProxy.optString(GRAY_HASH_END);
        this.btype = jSONObjectProxy.optString(B_TYPE);
        this.bcontent = jSONObjectProxy.optString(B_CONTENT);
    }

    public void setMsgBodyExtrasProxy(JSONObjectProxy jSONObjectProxy) {
        this.msgBodyExtrasProxy = jSONObjectProxy;
        this.title = jSONObjectProxy.optString(TITLE_KEY);
        this.alert = jSONObjectProxy.optString(CONTENT_KEY);
        this.watchCategoryId = jSONObjectProxy.optString(WATCH_CATEGORY_ID);
        this.taskId = jSONObjectProxy.optString(TASK_ID);
        String optString = jSONObjectProxy.optString(EXTRAS_KEY);
        if (optString != null) {
            try {
                setExtrasProxy(new JSONObjectProxy(new JSONObject(optString)));
            } catch (JSONException e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public String toString() {
        return "NotificationMessageSummary{devType=" + this.devType + ", echo='" + this.echo + "', msgType=" + this.msgType + ", msgSeq='" + this.msgSeq + "', title='" + this.title + "', alert='" + this.alert + "', taskId='" + this.taskId + "', secondListTitle='" + this.secondListTitle + "', notifyTemplateId='" + this.notifyTemplateId + "', containerType='" + this.containerType + "', imgPath='" + this.imgPath + "', landPageId='" + this.landPageId + "', msgId='" + this.msgId + "', landPageUrl='" + this.landPageUrl + "', shareFlag='" + this.shareFlag + "', activityId='" + this.activityId + "', shopId='" + this.shopId + "', vendorId='" + this.vendorId + "', wareId='" + this.wareId + "', orderId='" + this.orderId + "', businessType='" + this.businessType + "', couponsFlag='" + this.couponsFlag + "', landPageFlag='" + this.landPageFlag + "', price='" + this.price + "', keyWord='" + this.keyWord + "', subTaskId='" + this.subTaskId + "', questionId='" + this.questionId + "', bcFlag='" + this.bcFlag + "', watchCategoryId='" + this.watchCategoryId + "', tab='" + this.tab + "', idMsg='" + this.idMsg + "', appId=" + this.appId + ", setId=" + this.setId + ", serialNo=" + this.serialNo + ", notifyMoldId='" + this.notifyMoldId + "', sqImgPath='" + this.sqImgPath + "', key='" + this.key + "', pushTextId='" + this.pushTextId + "', source='" + this.source + "', soundSource='" + this.soundSource + "', dd='" + this.dd + "', transmission='" + this.transmission + "', accountType='" + this.accountType + "', isFeedBack='" + this.isFeedBack + "', couponsId='" + this.couponsId + "', venderId='" + this.venderId + "', testid='" + this.testid + "', grayHashStart='" + this.grayHashStart + "', grayHashEnd='" + this.grayHashEnd + "', btype='" + this.btype + "', bcontent='" + this.bcontent + "', extrasProxy=" + this.extrasProxy + ", msgBodyExtrasProxy=" + this.msgBodyExtrasProxy + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.devType);
        parcel.writeString(this.echo);
        parcel.writeInt(this.msgType);
        parcel.writeString(this.msgSeq);
        parcel.writeString(this.title);
        parcel.writeString(this.alert);
        parcel.writeString(this.taskId);
        parcel.writeString(this.secondListTitle);
        parcel.writeString(this.notifyTemplateId);
        parcel.writeString(this.containerType);
        parcel.writeString(this.imgPath);
        parcel.writeString(this.landPageId);
        parcel.writeString(this.msgId);
        parcel.writeString(this.landPageUrl);
        parcel.writeString(this.shareFlag);
        parcel.writeString(this.activityId);
        parcel.writeString(this.shopId);
        parcel.writeString(this.vendorId);
        parcel.writeString(this.wareId);
        parcel.writeString(this.orderId);
        parcel.writeString(this.businessType);
        parcel.writeString(this.couponsFlag);
        parcel.writeString(this.landPageFlag);
        parcel.writeString(this.price);
        parcel.writeString(this.keyWord);
        parcel.writeString(this.subTaskId);
        parcel.writeString(this.questionId);
        parcel.writeString(this.bcFlag);
        parcel.writeString(this.watchCategoryId);
        parcel.writeString(this.tab);
        parcel.writeString(this.idMsg);
        parcel.writeInt(this.appId);
        parcel.writeInt(this.setId);
        parcel.writeInt(this.serialNo);
        parcel.writeString(this.notifyMoldId);
        parcel.writeString(this.sqImgPath);
        parcel.writeString(this.key);
        parcel.writeString(this.pushTextId);
        parcel.writeString(this.source);
        parcel.writeString(this.soundSource);
        parcel.writeString(this.dd);
        parcel.writeString(this.transmission);
        parcel.writeString(this.accountType);
        parcel.writeString(this.isFeedBack);
        parcel.writeString(this.couponsId);
        parcel.writeString(this.venderId);
        parcel.writeString(this.testid);
        parcel.writeString(this.grayHashStart);
        parcel.writeString(this.grayHashEnd);
        parcel.writeString(this.btype);
        parcel.writeString(this.bcontent);
    }

    public NotificationMessageSummary(JDJSONObject jDJSONObject) {
        this.devType = jDJSONObject.optInt(DEVTYPE);
        this.echo = jDJSONObject.optString(ECHO);
        this.msgType = jDJSONObject.optInt(MSGTYPE);
        this.msgSeq = jDJSONObject.optString(MSG_SEQ);
        this.title = jDJSONObject.optString("title");
        this.alert = jDJSONObject.optString("alert");
        this.taskId = jDJSONObject.optString(TASK_ID);
        this.secondListTitle = jDJSONObject.optString(SECOND_LIST_TITLE);
        this.notifyTemplateId = jDJSONObject.optString(NOTIFY_TEMPLATE);
        this.containerType = jDJSONObject.optString(CONTAINER_ID);
        this.imgPath = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_IMGPATH);
        this.landPageId = jDJSONObject.optString("landPageId");
        this.msgId = jDJSONObject.optString("msgId");
        this.landPageUrl = jDJSONObject.optString(LAND_PAGE_URL);
        this.shareFlag = jDJSONObject.optString(SHARE_FLAG);
        this.activityId = jDJSONObject.optString("activityId");
        this.shopId = jDJSONObject.optString("shopId");
        this.vendorId = jDJSONObject.optString("vendorId");
        this.wareId = jDJSONObject.optString("wareId");
        this.orderId = jDJSONObject.optString("orderId");
        this.businessType = jDJSONObject.optString("businessType");
        this.couponsFlag = jDJSONObject.optString(COUPONS_FLAG);
        this.landPageFlag = jDJSONObject.optString(LAND_PAGE_FLAG);
        this.price = jDJSONObject.optString("price");
        this.keyWord = jDJSONObject.optString(JshopConst.JSHOP_SEARCH_KEYWORD);
        this.subTaskId = jDJSONObject.optString(SUB_TASK_ID);
        this.questionId = jDJSONObject.optString(QUESTION_ID);
        this.bcFlag = jDJSONObject.optString(BC_FLAG);
        this.watchCategoryId = jDJSONObject.optString("watchCategoryId");
        this.idMsg = jDJSONObject.optString(ID_MSG);
        this.appId = jDJSONObject.optInt("appId");
        this.setId = jDJSONObject.optInt(SET_ID);
        this.serialNo = jDJSONObject.optInt(SERIAL_NO);
        this.notifyMoldId = jDJSONObject.optString(NOTIFY_MOLD_ID);
        this.sqImgPath = jDJSONObject.optString(SQIMG_PATH);
        this.tab = jDJSONObject.optString(TAB);
        this.key = jDJSONObject.optString("key");
        this.pushTextId = jDJSONObject.optString(PUSH_TEXT_ID);
        this.source = jDJSONObject.optString("source");
        this.dd = jDJSONObject.optString(DD_MSG);
        this.soundSource = jDJSONObject.optString(SOUND_SOURCE);
        this.transmission = jDJSONObject.optString(TRANSMISSION);
        this.accountType = jDJSONObject.optString("accountType");
        this.isFeedBack = jDJSONObject.optString("isFeedBack");
        this.couponsId = jDJSONObject.optString(COUPONS_ID);
        this.venderId = jDJSONObject.optString("venderId");
        this.testid = jDJSONObject.optString(TEST_ID);
        this.btype = jDJSONObject.optString(B_TYPE);
        this.bcontent = jDJSONObject.optString(B_CONTENT);
        this.grayHashStart = jDJSONObject.optString("grayHashStart");
        this.grayHashEnd = jDJSONObject.optString("grayHashEnd");
    }

    public NotificationMessageSummary(Parcel parcel) {
        this.devType = parcel.readInt();
        this.echo = parcel.readString();
        this.msgType = parcel.readInt();
        this.msgSeq = parcel.readString();
        this.title = parcel.readString();
        this.alert = parcel.readString();
        this.taskId = parcel.readString();
        this.secondListTitle = parcel.readString();
        this.notifyTemplateId = parcel.readString();
        this.containerType = parcel.readString();
        this.imgPath = parcel.readString();
        this.landPageId = parcel.readString();
        this.msgId = parcel.readString();
        this.landPageUrl = parcel.readString();
        this.shareFlag = parcel.readString();
        this.activityId = parcel.readString();
        this.shopId = parcel.readString();
        this.vendorId = parcel.readString();
        this.wareId = parcel.readString();
        this.orderId = parcel.readString();
        this.businessType = parcel.readString();
        this.couponsFlag = parcel.readString();
        this.landPageFlag = parcel.readString();
        this.price = parcel.readString();
        this.keyWord = parcel.readString();
        this.subTaskId = parcel.readString();
        this.questionId = parcel.readString();
        this.bcFlag = parcel.readString();
        this.watchCategoryId = parcel.readString();
        this.tab = parcel.readString();
        this.idMsg = parcel.readString();
        this.appId = parcel.readInt();
        this.setId = parcel.readInt();
        this.serialNo = parcel.readInt();
        this.notifyMoldId = parcel.readString();
        this.sqImgPath = parcel.readString();
        this.key = parcel.readString();
        this.pushTextId = parcel.readString();
        this.source = parcel.readString();
        this.soundSource = parcel.readString();
        this.dd = parcel.readString();
        this.transmission = parcel.readString();
        this.accountType = parcel.readString();
        this.isFeedBack = parcel.readString();
        this.couponsId = parcel.readString();
        this.venderId = parcel.readString();
        this.testid = parcel.readString();
        this.grayHashStart = parcel.readString();
        this.grayHashEnd = parcel.readString();
        this.btype = parcel.readString();
        this.bcontent = parcel.readString();
    }
}
