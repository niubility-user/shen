package com.jingdong.common.messagepop;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.MSGWithDDUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MessageDelayTimeModel implements Parcelable {
    public static final Parcelable.Creator<MessageDelayTimeModel> CREATOR = new Parcelable.Creator<MessageDelayTimeModel>() { // from class: com.jingdong.common.messagepop.MessageDelayTimeModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MessageDelayTimeModel createFromParcel(Parcel parcel) {
            return new MessageDelayTimeModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MessageDelayTimeModel[] newArray(int i2) {
            return new MessageDelayTimeModel[i2];
        }
    };
    private int delayTime;
    private boolean onFlag;
    private String pageId;
    private final String PAGE_ID = "pageId";
    private final String DELAY_TIME = "delayTime";
    private final String ON_FLAG = "onFlag";

    public MessageDelayTimeModel() {
    }

    public static ArrayList<MessageDelayTimeModel> toList(JDJSONArray jDJSONArray) {
        ArrayList<MessageDelayTimeModel> arrayList = new ArrayList<>();
        if (jDJSONArray != null && jDJSONArray.size() >= 1) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                if (jSONObject != null && jSONObject.size() > 0) {
                    arrayList.add(new MessageDelayTimeModel(jSONObject));
                }
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getDelayTime() {
        return this.delayTime;
    }

    public String getPageId() {
        return this.pageId;
    }

    public boolean isOnFlag() {
        return this.onFlag;
    }

    public void setDelayTime(int i2) {
        this.delayTime = i2;
    }

    public void setOnFlag(boolean z) {
        this.onFlag = z;
    }

    public void setPageId(String str) {
        this.pageId = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.pageId);
        parcel.writeInt(this.delayTime);
        parcel.writeByte(this.onFlag ? (byte) 1 : (byte) 0);
    }

    public MessageDelayTimeModel(JDJSONObject jDJSONObject) {
        try {
            this.pageId = jDJSONObject.optString("pageId");
            String optString = jDJSONObject.optString("delayTime");
            if (TextUtils.isEmpty(optString)) {
                this.delayTime = MSGWithDDUtil.getStationPullPeriod();
            } else {
                this.delayTime = Integer.parseInt(optString);
            }
            this.onFlag = "1".equals(jDJSONObject.optString("onFlag"));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            this.delayTime = MSGWithDDUtil.getStationPullPeriod();
            this.onFlag = false;
        }
    }

    protected MessageDelayTimeModel(Parcel parcel) {
        this.pageId = parcel.readString();
        this.delayTime = parcel.readInt();
        this.onFlag = parcel.readByte() != 0;
    }
}
