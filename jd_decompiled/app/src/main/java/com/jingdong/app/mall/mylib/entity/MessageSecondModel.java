package com.jingdong.app.mall.mylib.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class MessageSecondModel implements Parcelable {
    private static final String BUBBLES_COUNT = "bubblesCount";
    private static final String CONTAINER_NAME = "containerName";
    private static final String CONTAINER_TYPE = "containerType";
    public static final Parcelable.Creator<MessageSecondModel> CREATOR = new a();
    private int bubbleCount;
    private String containerId;
    private String containerName;
    private String containerType;
    private int mode;

    /* loaded from: classes4.dex */
    class a implements Parcelable.Creator<MessageSecondModel> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public MessageSecondModel createFromParcel(Parcel parcel) {
            return new MessageSecondModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public MessageSecondModel[] newArray(int i2) {
            return new MessageSecondModel[i2];
        }
    }

    public MessageSecondModel() {
        this.bubbleCount = -1;
        this.mode = 3;
    }

    public static ArrayList<MessageSecondModel> toList(JDJSONArray jDJSONArray) {
        ArrayList<MessageSecondModel> arrayList = new ArrayList<>();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                if (jSONObject != null && jSONObject.size() > 0) {
                    arrayList.add(new MessageSecondModel(jSONObject));
                }
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getBubbleCount() {
        return this.bubbleCount;
    }

    public String getContainerId() {
        return this.containerId;
    }

    public String getContainerName() {
        return this.containerName;
    }

    public String getContainerType() {
        return this.containerType;
    }

    public int getMode() {
        return this.mode;
    }

    public void setBubbleCount(int i2) {
        this.bubbleCount = i2;
    }

    public void setContainerId(String str) {
        this.containerId = str;
    }

    public void setContainerName(String str) {
        this.containerName = str;
    }

    public void setContainerType(String str) {
        this.containerType = str;
    }

    public void setMode(int i2) {
        this.mode = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.containerId);
        parcel.writeString(this.containerType);
        parcel.writeInt(this.bubbleCount);
        parcel.writeInt(this.mode);
        parcel.writeString(this.containerName);
    }

    public MessageSecondModel(JDJSONObject jDJSONObject) {
        this.bubbleCount = -1;
        this.mode = 3;
        this.containerType = jDJSONObject.optString("containerType");
        this.containerName = jDJSONObject.optString(CONTAINER_NAME);
        this.bubbleCount = jDJSONObject.optInt(BUBBLES_COUNT);
    }

    protected MessageSecondModel(Parcel parcel) {
        this.bubbleCount = -1;
        this.mode = 3;
        this.containerId = parcel.readString();
        this.containerType = parcel.readString();
        this.bubbleCount = parcel.readInt();
        this.mode = parcel.readInt();
        this.containerName = parcel.readString();
    }
}
