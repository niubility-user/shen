package com.meizu.cloud.pushsdk.handler.e.j;

import android.os.Parcel;
import android.os.Parcelable;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class g implements Parcelable {
    public static final Parcelable.Creator<g> CREATOR = new a();

    /* renamed from: g  reason: collision with root package name */
    private int f15974g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f15975h;

    /* renamed from: i  reason: collision with root package name */
    private List<String> f15976i;

    /* renamed from: j  reason: collision with root package name */
    private b f15977j;

    /* renamed from: k  reason: collision with root package name */
    private String f15978k;

    /* loaded from: classes14.dex */
    class a implements Parcelable.Creator<g> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public g[] newArray(int i2) {
            return new g[i2];
        }
    }

    protected g(Parcel parcel) {
        this.f15974g = parcel.readInt();
        this.f15975h = parcel.readByte() != 0;
        this.f15976i = parcel.createStringArrayList();
        this.f15977j = (b) parcel.readParcelable(b.class.getClassLoader());
        this.f15978k = parcel.readString();
    }

    public g(String str, String str2, String str3, String str4) {
        this.f15978k = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull("max_size")) {
                this.f15974g = jSONObject.getInt("max_size");
            }
            if (!jSONObject.isNull("wifi_upload")) {
                this.f15975h = jSONObject.getBoolean("wifi_upload");
            }
            if (!jSONObject.isNull("upload_files")) {
                JSONArray jSONArray = jSONObject.getJSONArray("upload_files");
                this.f15976i = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    this.f15976i.add(jSONArray.getString(i2));
                }
            }
        } catch (JSONException e2) {
            DebugLogger.e("UploadLogMessage", "parse upload message error " + e2.getMessage());
        }
        this.f15977j = new b(str2, str3, str4);
    }

    public b a() {
        return this.f15977j;
    }

    public List<String> b() {
        return this.f15976i;
    }

    public int c() {
        return this.f15974g;
    }

    public boolean d() {
        return this.f15975h;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "UploadLogMessage{maxSize=" + this.f15974g + ", wifiUpload=" + this.f15975h + ", fileList=" + this.f15976i + ", controlMessage=" + this.f15977j + ", uploadMessage='" + this.f15978k + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f15974g);
        parcel.writeByte(this.f15975h ? (byte) 1 : (byte) 0);
        parcel.writeStringList(this.f15976i);
        parcel.writeParcelable(this.f15977j, i2);
        parcel.writeString(this.f15978k);
    }
}
