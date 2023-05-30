package com.jd.manto.hd.bluetooth.peripheral;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.AdvertiseData;
import android.os.ParcelUuid;
import android.util.Base64;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import com.huawei.hms.push.AttributionReporter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v4, types: [int] */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11, types: [int] */
    /* JADX WARN: Type inference failed for: r8v9 */
    @RequiresApi(api = 21)
    public static BluetoothGattService a(JSONObject jSONObject) {
        BluetoothGattService bluetoothGattService = null;
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("service");
            boolean z = false;
            BluetoothGattService bluetoothGattService2 = new BluetoothGattService(UUID.fromString(optJSONObject.optString("uuid")), 0);
            JSONArray optJSONArray = optJSONObject.optJSONArray("characteristics");
            if (optJSONArray == null) {
                return bluetoothGattService2;
            }
            int length = optJSONArray.length();
            JSONObject[] jSONObjectArr = new JSONObject[length];
            for (int i2 = 0; i2 < length; i2++) {
                jSONObjectArr[i2] = optJSONArray.optJSONObject(i2);
            }
            int i3 = 0;
            while (i3 < length) {
                JSONObject jSONObject2 = jSONObjectArr[i3];
                String optString = jSONObject2.optString("uuid");
                JSONObject optJSONObject2 = jSONObject2.optJSONObject("properties");
                boolean optBoolean = optJSONObject2.optBoolean("write", z);
                boolean optBoolean2 = optJSONObject2.optBoolean("read", z);
                try {
                    boolean optBoolean3 = optJSONObject2.optBoolean("notify", z);
                    int i4 = length;
                    boolean optBoolean4 = optJSONObject2.optBoolean("indicate", z);
                    JSONObject[] jSONObjectArr2 = jSONObjectArr;
                    boolean optBoolean5 = optJSONObject2.optBoolean("writeNoResponse", z);
                    int i5 = optBoolean ? 8 : 0;
                    if (optBoolean2) {
                        i5 |= 2;
                    }
                    if (optBoolean3) {
                        i5 |= 16;
                    }
                    if (optBoolean4) {
                        i5 |= 32;
                    }
                    if (optBoolean5) {
                        i5 |= 4;
                    }
                    JSONObject optJSONObject3 = jSONObject2.optJSONObject(AttributionReporter.SYSTEM_PERMISSION);
                    boolean optBoolean6 = optJSONObject3.optBoolean("readable", z);
                    boolean optBoolean7 = optJSONObject3.optBoolean("writeable", z);
                    boolean optBoolean8 = optJSONObject3.optBoolean("readEncryptionRequired", z);
                    boolean optBoolean9 = optJSONObject3.optBoolean("writeEncryptionRequired", z);
                    int i6 = optBoolean6;
                    if (optBoolean7) {
                        i6 = optBoolean6 | true;
                    }
                    if (optBoolean9) {
                        i6 = (i6 == true ? 1 : 0) | 32 | 64;
                    }
                    if (optBoolean8) {
                        i6 = (i6 == true ? 1 : 0) | 2 | 4;
                    }
                    BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(UUID.fromString(optString), i5, i6);
                    bluetoothGattCharacteristic.setValue(a(Base64.decode(jSONObject2.optString("value").getBytes(), 2)));
                    JSONArray optJSONArray2 = jSONObject2.optJSONArray("descriptors");
                    if (optJSONArray2 != null) {
                        int length2 = optJSONArray2.length();
                        JSONObject[] jSONObjectArr3 = new JSONObject[length2];
                        for (int i7 = 0; i7 < length2; i7++) {
                            jSONObjectArr3[i7] = optJSONArray2.optJSONObject(i7);
                        }
                        for (int i8 = 0; i8 < length2; i8++) {
                            JSONObject jSONObject3 = jSONObjectArr3[i8];
                            String optString2 = jSONObject3.optString("uuid");
                            JSONObject optJSONObject4 = jSONObject3.optJSONObject(AttributionReporter.SYSTEM_PERMISSION);
                            String optString3 = jSONObject3.optString("value");
                            boolean optBoolean10 = optJSONObject4.optBoolean("write");
                            ?? optBoolean11 = optJSONObject4.optBoolean("read");
                            if (optBoolean10) {
                                optBoolean11 = (optBoolean11 == true ? 1 : 0) | 16;
                            }
                            BluetoothGattDescriptor bluetoothGattDescriptor = new BluetoothGattDescriptor(UUID.fromString(optString2), optBoolean11);
                            bluetoothGattDescriptor.setValue(a(Base64.decode(optString3.getBytes(), 2)));
                            bluetoothGattCharacteristic.addDescriptor(bluetoothGattDescriptor);
                        }
                    }
                    bluetoothGattService2.addCharacteristic(bluetoothGattCharacteristic);
                    i3++;
                    length = i4;
                    jSONObjectArr = jSONObjectArr2;
                    bluetoothGattService = null;
                    z = false;
                } catch (Throwable unused) {
                    return null;
                }
            }
            return bluetoothGattService2;
        } catch (Throwable unused2) {
            return bluetoothGattService;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 21)
    public static AdvertiseData a(h hVar) {
        AdvertiseData.Builder includeDeviceName = new AdvertiseData.Builder().setIncludeDeviceName(true);
        for (Pair<Integer, String> pair : hVar.b) {
            includeDeviceName.addManufacturerData(((Integer) pair.first).intValue(), a(Base64.decode(((String) pair.second).getBytes(), 2)));
        }
        Iterator<ParcelUuid> it = hVar.a.iterator();
        while (it.hasNext()) {
            includeDeviceName.addServiceUuid(it.next());
        }
        return includeDeviceName.build();
    }

    public static byte[] a(byte[] bArr) {
        return (bArr == null || bArr.length == 0) ? new byte[0] : bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 21)
    public static h b(JSONObject jSONObject) {
        int i2;
        if (jSONObject == null) {
            return null;
        }
        h hVar = new h(new ArrayList(), new ArrayList(), "medium", "");
        try {
            hVar.d = jSONObject.optLong("serverId");
            hVar.f6692c = jSONObject.optString("powerLevel", "medium");
            JSONObject optJSONObject = jSONObject.optJSONObject("advertiseRequest");
            hVar.f6693e = optJSONObject.optBoolean("connectable", true);
            optJSONObject.optString("deviceName", "");
            JSONArray optJSONArray = optJSONObject.optJSONArray("serviceUuids");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                String[] strArr = new String[length];
                for (int i3 = 0; i3 < length; i3++) {
                    strArr[i3] = optJSONArray.getString(i3);
                }
                ArrayList arrayList = new ArrayList(length);
                for (int i4 = 0; i4 < length; i4++) {
                    arrayList.add(new ParcelUuid(UUID.fromString(strArr[i4])));
                }
                hVar.a.addAll(arrayList);
            }
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("manufacturerData");
            if (optJSONArray2 != null) {
                int length2 = optJSONArray2.length();
                JSONObject[] jSONObjectArr = new JSONObject[length2];
                for (int i5 = 0; i5 < length2; i5++) {
                    jSONObjectArr[i5] = optJSONArray2.getJSONObject(i5);
                }
                for (int i6 = 0; i6 < length2; i6++) {
                    JSONObject jSONObject2 = jSONObjectArr[i6];
                    String optString = jSONObject2.optString("manufacturerId");
                    try {
                        i2 = Integer.parseInt(optString, optString.startsWith("0x") ? 16 : 10);
                    } catch (Throwable unused) {
                        i2 = 0;
                    }
                    hVar.b.add(new Pair<>(Integer.valueOf(i2), jSONObject2.optString("manufacturerSpecificData")));
                }
            }
            return hVar;
        } catch (Throwable unused2) {
            return null;
        }
    }
}
