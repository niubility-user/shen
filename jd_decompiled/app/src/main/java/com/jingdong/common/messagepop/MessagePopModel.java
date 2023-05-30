package com.jingdong.common.messagepop;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class MessagePopModel implements Parcelable {
    public static final Parcelable.Creator<MessagePopModel> CREATOR = new Parcelable.Creator<MessagePopModel>() { // from class: com.jingdong.common.messagepop.MessagePopModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MessagePopModel createFromParcel(Parcel parcel) {
            return new MessagePopModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MessagePopModel[] newArray(int i2) {
            return new MessagePopModel[i2];
        }
    };
    public static final String PRODUCT_DETAIL_ID = "202";
    public String accountType;
    public String alert;
    public String allowPopModel;
    public String bCat;
    public String batchId;
    public String benefit;
    public String btnText;
    public String businessCategoryId;
    public String channelId;
    public String channelTitle;
    public long displayTime;
    public String icon;
    public boolean isPull;
    public String landPageUrl;
    public String msgId;
    public String newTemplateId;
    public int notifyTemplateId;
    public String requestId;
    public String sImagePath;
    public String sourceBusiness;
    public String stationSImgPath;
    public String stationVideoImgPath;
    public String taskId;
    public String testId;
    public String title;
    public String transmission;
    public String triggerModel;
    public String visitPageId;
    public String wareId;
    public String whitePageFlag;
    public String[] whitePageList;
    private final String TITLE = "title";
    private final String MSGALERT = "alert";
    private final String BUSINESSCATEGORYID = NotificationMessageSummary.BUSINESS_TYPE;
    private final String EXTRAATTRIBUTE = "extraAttribute";
    private final String LANDPAGEURL = NotificationMessageSummary.LAND_PAGE_URL;
    private final String TRANSMISSION = NotificationMessageSummary.TRANSMISSION;
    private final String ICON = "icon";
    private final String DISPLAYTIME = "displayTime";
    private final String MSGID = "msgId";
    private final String NOTIFYTEMPLATEID = NotificationMessageSummary.NOTIFY_TEMPLATE;
    private final String WAREID = "wareId";
    private final String BATCHID = JshopConst.JSKEY_BATCH_ID;
    private final String CHANNELTITLE = "channelTitle";
    private final String CHANNELID = "channelId";
    private final String SIMAGEPATH = "sImgPath";
    private final String TASKID = NotificationMessageSummary.TASK_ID;
    private final String ACCOUNTTYPE = "accountType";
    private final String SOURCEBUSINESS = "sourceBusiness";
    private final String STATIONSIMGPATH = "stationSImgPath";
    private final String STATIONVIDEOIMGPATH = "stationVideoImgPath";
    private final String BTNTEXT = "btnText";
    private final String BENEFIT = "benefit";
    private final String TESTID = NotificationMessageSummary.TEST_ID;
    private final String BCAT = "bCat";
    private final String ISPULL = "isPull";
    private final String VERSIONRANGE = "vr";
    private final String S_ANDROID = "s_android";
    private final String NEW_TEMPLATE_ID = "tid";
    private final String VISIT_PAGE_ID = "visitPageId";
    private final String WHITE_PAGE_FLAG = "whitePageFlag";
    private final String WHITE_PAGE_LIST = "whitePageList";

    protected MessagePopModel(Parcel parcel) {
        this.title = parcel.readString();
        this.alert = parcel.readString();
        this.businessCategoryId = parcel.readString();
        this.landPageUrl = parcel.readString();
        this.transmission = parcel.readString();
        this.icon = parcel.readString();
        this.msgId = parcel.readString();
        this.displayTime = parcel.readLong();
        this.notifyTemplateId = parcel.readInt();
        this.wareId = parcel.readString();
        this.batchId = parcel.readString();
        this.channelTitle = parcel.readString();
        this.channelId = parcel.readString();
        this.sImagePath = parcel.readString();
        this.taskId = parcel.readString();
        this.accountType = parcel.readString();
        this.sourceBusiness = parcel.readString();
        this.stationSImgPath = parcel.readString();
        this.stationVideoImgPath = parcel.readString();
        this.btnText = parcel.readString();
        this.benefit = parcel.readString();
        this.testId = parcel.readString();
        this.bCat = parcel.readString();
        this.isPull = parcel.readBoolean();
        this.newTemplateId = parcel.readString();
        this.visitPageId = parcel.readString();
        this.whitePageFlag = parcel.readString();
        this.whitePageList = parcel.createStringArray();
        this.triggerModel = parcel.readString();
        this.allowPopModel = parcel.readString();
        this.requestId = parcel.readString();
    }

    private int getCurrentAppVersion() {
        try {
            String[] split = BaseInfo.getAppVersionName().split("\\.");
            if (split.length == 3) {
                return Integer.parseInt(split[2]) + (Integer.parseInt(split[0]) * 10000) + (Integer.parseInt(split[1]) * 100);
            }
            return -1;
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    private void parseVersionRange(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.isEmpty()) {
            return;
        }
        int currentAppVersion = getCurrentAppVersion();
        try {
            Iterator<Object> it = jDJSONArray.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof JDJSONObject) {
                    JDJSONObject jDJSONObject = (JDJSONObject) next;
                    String[] split = jDJSONObject.optString("vr").split("-");
                    if (split.length != 2) {
                        return;
                    }
                    if (currentAppVersion >= Integer.parseInt(split[0]) && currentAppVersion < Integer.parseInt(split[1])) {
                        this.newTemplateId = jDJSONObject.optString("tid");
                        return;
                    }
                }
            }
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        }
    }

    private void setMessagePopExtras(JDJSONObject jDJSONObject) {
        this.landPageUrl = jDJSONObject.optString(NotificationMessageSummary.LAND_PAGE_URL);
        this.transmission = jDJSONObject.optString(NotificationMessageSummary.TRANSMISSION);
        this.icon = jDJSONObject.optString("icon");
        this.notifyTemplateId = jDJSONObject.optInt(NotificationMessageSummary.NOTIFY_TEMPLATE);
        this.businessCategoryId = jDJSONObject.optString(NotificationMessageSummary.BUSINESS_TYPE);
        this.batchId = jDJSONObject.optString(JshopConst.JSKEY_BATCH_ID);
        this.wareId = jDJSONObject.optString("wareId");
        this.channelTitle = jDJSONObject.optString("channelTitle");
        this.channelId = jDJSONObject.optString("channelId");
        this.sImagePath = jDJSONObject.optString("sImgPath");
        this.taskId = jDJSONObject.optString(NotificationMessageSummary.TASK_ID);
        this.accountType = jDJSONObject.optString("accountType");
        this.sourceBusiness = jDJSONObject.optString("sourceBusiness");
        this.stationSImgPath = jDJSONObject.optString("stationSImgPath");
        this.stationVideoImgPath = jDJSONObject.optString("stationVideoImgPath");
        this.btnText = jDJSONObject.optString("btnText");
        this.benefit = jDJSONObject.optString("benefit");
        this.testId = jDJSONObject.optString(NotificationMessageSummary.TEST_ID);
        this.bCat = jDJSONObject.optString("bCat");
        this.visitPageId = jDJSONObject.optString("visitPageId");
        this.whitePageFlag = jDJSONObject.optString("whitePageFlag");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("whitePageList");
        if (optJSONArray != null && !optJSONArray.isEmpty()) {
            this.whitePageList = (String[]) optJSONArray.toArray(new String[0]);
        }
        parseVersionRange(jDJSONObject.optJSONArray("s_android"));
        this.triggerModel = jDJSONObject.optString("triggerModel");
        this.allowPopModel = jDJSONObject.optString("allowPopModel");
        this.requestId = jDJSONObject.optString("requestId");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getTemplateId() {
        try {
            return TextUtils.isEmpty(this.newTemplateId) ? this.notifyTemplateId : Integer.parseInt(this.newTemplateId);
        } catch (Exception unused) {
            return 0;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.title);
        parcel.writeString(this.alert);
        parcel.writeString(this.businessCategoryId);
        parcel.writeString(this.landPageUrl);
        parcel.writeString(this.transmission);
        parcel.writeString(this.icon);
        parcel.writeLong(this.displayTime);
        parcel.writeInt(this.notifyTemplateId);
        parcel.writeString(this.msgId);
        parcel.writeString(this.wareId);
        parcel.writeString(this.batchId);
        parcel.writeString(this.channelTitle);
        parcel.writeString(this.channelId);
        parcel.writeString(this.sImagePath);
        parcel.writeString(this.taskId);
        parcel.writeString(this.accountType);
        parcel.writeString(this.sourceBusiness);
        parcel.writeString(this.stationSImgPath);
        parcel.writeString(this.stationVideoImgPath);
        parcel.writeString(this.btnText);
        parcel.writeString(this.benefit);
        parcel.writeString(this.testId);
        parcel.writeString(this.bCat);
        parcel.writeBoolean(this.isPull);
        parcel.writeString(this.newTemplateId);
        parcel.writeString(this.visitPageId);
        parcel.writeString(this.whitePageFlag);
        parcel.writeStringArray(this.whitePageList);
        parcel.writeString(this.triggerModel);
        parcel.writeString(this.allowPopModel);
        parcel.writeString(this.requestId);
    }

    public MessagePopModel(JDJSONObject jDJSONObject) {
        this.title = jDJSONObject.optString("title");
        this.alert = jDJSONObject.optString("alert");
        this.msgId = jDJSONObject.optString("msgId");
        this.displayTime = jDJSONObject.optLong("displayTime");
        this.isPull = jDJSONObject.optBoolean("isPull");
        String optString = jDJSONObject.optString("extraAttribute");
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        try {
            setMessagePopExtras(JDJSON.parseObject(optString));
        } catch (Exception e2) {
            OKLog.e("MessagePopModel", e2);
        }
    }
}
