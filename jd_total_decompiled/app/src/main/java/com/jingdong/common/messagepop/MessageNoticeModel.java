package com.jingdong.common.messagepop;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class MessageNoticeModel implements Parcelable {
    public static final Parcelable.Creator<MessageNoticeModel> CREATOR = new Parcelable.Creator<MessageNoticeModel>() { // from class: com.jingdong.common.messagepop.MessageNoticeModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MessageNoticeModel createFromParcel(Parcel parcel) {
            return new MessageNoticeModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MessageNoticeModel[] newArray(int i2) {
            return new MessageNoticeModel[i2];
        }
    };
    private final String CHANNEL_ID = "channelId";
    private final String IS_ON = "isOn";
    private final String POP_TIPS_TIMER = "popTipsTimer";
    public int channelId;
    public boolean isOn;
    public int popTipsTimer;

    public MessageNoticeModel() {
    }

    public static ArrayList<MessageNoticeModel> toList(JDJSONArray jDJSONArray) {
        ArrayList<MessageNoticeModel> arrayList = new ArrayList<>();
        if (jDJSONArray != null && jDJSONArray.size() >= 1) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                if (jSONObject != null && jSONObject.size() > 0) {
                    arrayList.add(new MessageNoticeModel(jSONObject));
                }
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.channelId);
        parcel.writeInt(this.popTipsTimer);
        parcel.writeByte(this.isOn ? (byte) 1 : (byte) 0);
    }

    public MessageNoticeModel(JDJSONObject jDJSONObject) {
        this.channelId = jDJSONObject.optInt("channelId");
        this.popTipsTimer = jDJSONObject.optInt("popTipsTimer");
        this.isOn = jDJSONObject.optBoolean("isOn");
    }

    protected MessageNoticeModel(Parcel parcel) {
        this.channelId = parcel.readInt();
        this.popTipsTimer = parcel.readInt();
        this.isOn = parcel.readByte() != 0;
    }
}
