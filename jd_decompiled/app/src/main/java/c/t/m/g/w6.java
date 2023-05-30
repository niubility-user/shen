package c.t.m.g;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class w6 implements Cloneable {

    /* renamed from: g  reason: collision with root package name */
    public int f733g;

    /* renamed from: h  reason: collision with root package name */
    public int f734h;

    /* renamed from: i  reason: collision with root package name */
    public String f735i;

    /* renamed from: j  reason: collision with root package name */
    public int f736j;

    /* renamed from: k  reason: collision with root package name */
    public long f737k;

    @SuppressLint({"NewApi"})
    public static w6 a(BluetoothDevice bluetoothDevice, int i2, byte[] bArr) {
        if (bluetoothDevice == null) {
            return null;
        }
        boolean z = false;
        int i3 = 2;
        while (true) {
            if (i3 <= 5) {
                if ((bArr[i3 + 2] & 255) == 2 && (bArr[i3 + 3] & 255) == 21) {
                    z = true;
                    break;
                }
                i3++;
            } else {
                break;
            }
        }
        if (z) {
            w6 w6Var = new w6();
            w6Var.d(((bArr[i3 + 20] & 255) * 256) + (bArr[i3 + 21] & 255));
            w6Var.h(((bArr[i3 + 22] & 255) * 256) + (bArr[i3 + 23] & 255));
            w6Var.k(i2);
            w6Var.f(bluetoothDevice.getAddress().toUpperCase());
            w6Var.i(bluetoothDevice.getName());
            w6Var.e(System.currentTimeMillis());
            return w6Var;
        }
        return null;
    }

    public static String c(List<w6> list) {
        if (list == null || list.size() == 0) {
            return "[]";
        }
        JSONArray jSONArray = new JSONArray();
        for (w6 w6Var : list) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Constant.KEY_MAC, w6Var.b());
                jSONObject.put("major", w6Var.g());
                jSONObject.put("minor", w6Var.j());
                jSONObject.put("rssi", w6Var.l());
                jSONObject.put("time", w6Var.m() / 1000);
                jSONArray.put(jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return jSONArray.toString();
    }

    public String b() {
        return this.f735i;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void d(int i2) {
        this.f733g = i2;
    }

    public void e(long j2) {
        this.f737k = j2;
    }

    public void f(String str) {
        this.f735i = str;
    }

    public int g() {
        return this.f733g;
    }

    public void h(int i2) {
        this.f734h = i2;
    }

    public void i(String str) {
    }

    public int j() {
        return this.f734h;
    }

    public void k(int i2) {
        this.f736j = i2;
    }

    public int l() {
        return this.f736j;
    }

    public long m() {
        return this.f737k;
    }

    public String toString() {
        return "Beacon [major=" + this.f733g + ", minor=" + this.f734h + ", bluetoothAddress=" + this.f735i + ", rssi=" + this.f736j + ", time=" + this.f737k + "]";
    }
}
