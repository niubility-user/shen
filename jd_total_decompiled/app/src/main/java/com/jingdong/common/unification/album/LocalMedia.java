package com.jingdong.common.unification.album;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import cn.com.union.fido.common.MIMEType;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class LocalMedia implements Parcelable {
    public static final Parcelable.Creator<LocalMedia> CREATOR = new Parcelable.Creator<LocalMedia>() { // from class: com.jingdong.common.unification.album.LocalMedia.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocalMedia createFromParcel(Parcel parcel) {
            return new LocalMedia(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocalMedia[] newArray(int i2) {
            return new LocalMedia[i2];
        }
    };
    public String businessTag;
    private String coverPath;
    private String cutInfo;
    private long date;
    private long duration;
    private HashMap<String, String> extraMap;
    private String filterInfo;
    private int height;
    private boolean isChecked;
    private boolean isHighlight;
    private boolean isPicked;
    private int mimeType;
    private String musicId;
    private int num;
    private String pasterInfo;
    private String path;
    private String pictureType;
    private int position;
    private int type;
    private Uri uri;
    private int width;

    public LocalMedia(String str, boolean z) {
        this.businessTag = "0";
        this.extraMap = new HashMap<>();
        this.path = str;
        this.isPicked = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof LocalMedia) {
            return this.path.equals(((LocalMedia) obj).path);
        }
        return super.equals(obj);
    }

    public String getCoverPath() {
        return this.coverPath;
    }

    public String getCutInfo() {
        return this.cutInfo;
    }

    public long getDate() {
        return this.date;
    }

    public long getDuration() {
        return this.duration;
    }

    public HashMap<String, String> getExtraMap() {
        return this.extraMap;
    }

    public String getFilterInfo() {
        return this.filterInfo;
    }

    public int getHeight() {
        return this.height;
    }

    public String getMusicId() {
        return this.musicId;
    }

    public String getPasterInfo() {
        return this.pasterInfo;
    }

    public String getPath() {
        return this.path;
    }

    public String getPictureType() {
        if (TextUtils.isEmpty(this.pictureType)) {
            this.pictureType = MIMEType.MIME_TYPE_JPEG;
        }
        return this.pictureType;
    }

    public int getPosition() {
        return this.position;
    }

    public int getType() {
        return this.type;
    }

    public Uri getUri() {
        return this.uri;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public boolean isHighlight() {
        return this.isHighlight;
    }

    public boolean isPicked() {
        return this.isPicked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public void setCoverPath(String str) {
        this.coverPath = str;
    }

    public void setCutInfo(String str) {
        this.cutInfo = str;
    }

    public void setDate(long j2) {
        this.date = j2;
    }

    public void setDuration(long j2) {
        this.duration = j2;
    }

    public void setExtraMap(HashMap<String, String> hashMap) {
        this.extraMap = hashMap;
    }

    public void setFilterInfo(String str) {
        this.filterInfo = str;
    }

    public void setHeight(int i2) {
        this.height = i2;
    }

    public void setHighlight(boolean z) {
        this.isHighlight = z;
    }

    public void setMusicId(String str) {
        this.musicId = str;
    }

    public void setPasterInfo(String str) {
        this.pasterInfo = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setPicked(boolean z) {
        this.isPicked = z;
    }

    public void setPictureType(String str) {
        this.pictureType = str;
    }

    public void setPosition(int i2) {
        this.position = i2;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setWidth(int i2) {
        this.width = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.path);
        parcel.writeString(this.coverPath);
        parcel.writeLong(this.duration);
        parcel.writeInt(this.type);
        parcel.writeByte(this.isPicked ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isHighlight ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.date);
        parcel.writeInt(this.position);
        parcel.writeInt(this.num);
        parcel.writeInt(this.mimeType);
        parcel.writeString(this.pictureType);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeString(this.businessTag);
        parcel.writeString(this.musicId);
        parcel.writeString(this.filterInfo);
        parcel.writeString(this.pasterInfo);
        parcel.writeString(this.cutInfo);
        parcel.writeMap(this.extraMap);
        parcel.writeParcelable(this.uri, 1);
    }

    public LocalMedia(String str, long j2, boolean z, String str2) {
        this.businessTag = "0";
        this.extraMap = new HashMap<>();
        this.path = str;
        this.duration = j2;
        this.isPicked = z;
        this.pictureType = str2;
    }

    protected LocalMedia(Parcel parcel) {
        this.businessTag = "0";
        this.extraMap = new HashMap<>();
        this.path = parcel.readString();
        this.coverPath = parcel.readString();
        this.duration = parcel.readLong();
        this.type = parcel.readInt();
        this.isPicked = parcel.readByte() != 0;
        this.isChecked = parcel.readByte() != 0;
        this.isHighlight = parcel.readByte() != 0;
        this.date = parcel.readLong();
        this.position = parcel.readInt();
        this.num = parcel.readInt();
        this.mimeType = parcel.readInt();
        this.pictureType = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.businessTag = parcel.readString();
        this.musicId = parcel.readString();
        this.filterInfo = parcel.readString();
        this.pasterInfo = parcel.readString();
        this.cutInfo = parcel.readString();
        this.extraMap = parcel.readHashMap(HashMap.class.getClassLoader());
        this.uri = (Uri) parcel.readParcelable(getClass().getClassLoader());
    }

    public LocalMedia() {
        this.businessTag = "0";
        this.extraMap = new HashMap<>();
    }

    public LocalMedia(String str, long j2, int i2, String str2) {
        this.businessTag = "0";
        this.extraMap = new HashMap<>();
        this.path = str;
        this.duration = j2;
        this.mimeType = i2;
        this.pictureType = str2;
    }

    public LocalMedia(String str, long j2, long j3, int i2, String str2) {
        this.businessTag = "0";
        this.extraMap = new HashMap<>();
        this.path = str;
        this.duration = j2;
        this.date = j3;
        this.mimeType = i2;
        this.pictureType = str2;
    }

    public LocalMedia(String str, long j2, long j3, int i2, String str2, int i3, int i4) {
        this.businessTag = "0";
        this.extraMap = new HashMap<>();
        this.path = str;
        this.duration = j2;
        this.date = j3;
        this.mimeType = i2;
        this.pictureType = str2;
        this.width = i3;
        this.height = i4;
    }

    public LocalMedia(String str, long j2, boolean z, int i2, int i3, int i4) {
        this.businessTag = "0";
        this.extraMap = new HashMap<>();
        this.path = str;
        this.duration = j2;
        this.isChecked = z;
        this.position = i2;
        this.num = i3;
        this.mimeType = i4;
    }
}
