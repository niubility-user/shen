package com.jingdong.manto.m.t0.d.d;

import android.os.ParcelUuid;
import android.util.Base64;
import android.util.SparseArray;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public final class h {
    public String a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public SparseArray<byte[]> f13686c;
    public List<ParcelUuid> d;

    /* renamed from: e  reason: collision with root package name */
    public String f13687e;

    /* renamed from: f  reason: collision with root package name */
    public Map<ParcelUuid, byte[]> f13688f;

    /* renamed from: g  reason: collision with root package name */
    private JSONObject f13689g;

    /* renamed from: h  reason: collision with root package name */
    public String f13690h;

    public h(com.jingdong.manto.m.t0.d.e.b bVar) {
        this.f13690h = bVar.a().getName();
        this.a = bVar.a().getAddress();
        this.b = bVar.f13695c;
        com.jingdong.manto.m.t0.d.e.a aVar = bVar.b;
        this.f13686c = aVar.f13691c;
        this.d = aVar.b;
        this.f13687e = aVar.f13694g;
        this.f13688f = aVar.d;
    }

    public h(String str, String str2) {
        this.f13690h = str;
        this.a = str2;
    }

    public final JSONObject a() {
        if (this.f13689g == null) {
            JSONObject jSONObject = new JSONObject();
            this.f13689g = jSONObject;
            jSONObject.put("deviceId", this.a);
            this.f13689g.put("name", this.f13690h);
            this.f13689g.put("RSSI", this.b);
            byte[] bArr = new byte[0];
            StringBuilder sb = new StringBuilder();
            SparseArray<byte[]> sparseArray = this.f13686c;
            if (sparseArray != null && sparseArray.size() >= 0) {
                int i2 = 0;
                while (i2 < this.f13686c.size()) {
                    int keyAt = this.f13686c.keyAt(0);
                    byte[] bArr2 = {(byte) (keyAt & 255), (byte) ((keyAt >> 8) & 255)};
                    byte[] valueAt = this.f13686c.valueAt(i2);
                    byte[] bArr3 = new byte[bArr.length + 2 + valueAt.length];
                    System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
                    System.arraycopy(bArr2, 0, bArr3, bArr.length, 2);
                    System.arraycopy(valueAt, 0, bArr3, bArr.length + 2, valueAt.length);
                    i2++;
                    bArr = bArr3;
                }
            }
            sb.append(new String(Base64.encode(bArr, 2)));
            this.f13689g.put("advertisData", sb);
            JSONArray jSONArray = new JSONArray();
            List<ParcelUuid> list = this.d;
            if (list != null) {
                Iterator<ParcelUuid> it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().getUuid().toString().toUpperCase());
                }
            }
            this.f13689g.put("advertisServiceUUIDs", jSONArray);
            this.f13689g.put("localName", this.f13687e);
            JSONObject jSONObject2 = new JSONObject();
            Map<ParcelUuid, byte[]> map = this.f13688f;
            if (map != null && map.size() > 0) {
                for (ParcelUuid parcelUuid : this.f13688f.keySet()) {
                    jSONObject2.put(parcelUuid.getUuid().toString().toUpperCase(), new String(Base64.encode(this.f13688f.get(parcelUuid), 2)));
                }
            }
            this.f13689g.put("serviceData", jSONObject2);
        }
        return this.f13689g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((h) obj).a);
    }

    public int hashCode() {
        return Arrays.hashCode(new String[]{this.a});
    }

    public String toString() {
        return "DeviceFoundBean{deviceId='" + this.a + "', rssi=" + this.b + ", manufacturerData=" + this.f13686c + ", serviceUuids=" + this.d + ", localName='" + this.f13687e + "', serviceData=" + this.f13688f + ", jsonObject=" + this.f13689g + ", name='" + this.f13690h + "'}";
    }
}
