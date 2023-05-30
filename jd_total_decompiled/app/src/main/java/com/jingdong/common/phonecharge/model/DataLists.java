package com.jingdong.common.phonecharge.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class DataLists implements Parcelable {
    public static final Parcelable.Creator<DataLists> CREATOR = new Parcelable.Creator<DataLists>() { // from class: com.jingdong.common.phonecharge.model.DataLists.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataLists createFromParcel(Parcel parcel) {
            DataLists dataLists = new DataLists();
            dataLists.f12413k = parcel.readString();
            dataLists.v = parcel.readString();
            dataLists.element = (FormsList) parcel.readValue(FormsList.class.getClassLoader());
            return dataLists;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataLists[] newArray(int i2) {
            return new DataLists[i2];
        }
    };
    public FormsList element;

    /* renamed from: k  reason: collision with root package name */
    public String f12413k;
    public String v;

    public static DataLists getDataLists(JSONObjectProxy jSONObjectProxy) {
        try {
            return (DataLists) JDJSON.parseObject(jSONObjectProxy.toString(), DataLists.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ArrayList<DataLists> toList(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        ArrayList<DataLists> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            try {
                if (jSONArrayPoxy.getJSONObject(i2) != null) {
                    arrayList.add(getDataLists(jSONArrayPoxy.getJSONObject(i2)));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
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
        parcel.writeString(this.f12413k);
        parcel.writeString(this.v);
        parcel.writeValue(this.element);
    }
}
