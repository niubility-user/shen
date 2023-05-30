package com.jingdong.manto.jsapi.bluetooth.sdk.util;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.os.Build;
import com.jingdong.manto.c;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.reflect.Method;
import java.util.UUID;

/* loaded from: classes15.dex */
public class BTHelper {
    private static final String TAG = "BT.BTHelper";
    public static final UUID notifyUuid = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    public static boolean btEnabled() {
        BluetoothAdapter bTAdapter = getBTAdapter();
        if (bTAdapter == null) {
            return false;
        }
        return bTAdapter.isEnabled();
    }

    public static boolean btSupport() {
        return Build.VERSION.SDK_INT > 18 && hasFeature();
    }

    public static boolean deepEquals(byte[] bArr, byte[] bArr2) {
        int length;
        if (bArr == null) {
            return bArr2 == null;
        } else if (bArr2 != null && (length = bArr.length) == bArr2.length) {
            for (int i2 = 0; i2 < length; i2++) {
                if (bArr[i2] != bArr2[i2]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else if (obj2 == null) {
            return false;
        } else {
            return obj.equals(obj2);
        }
    }

    @TargetApi(18)
    public static synchronized BluetoothAdapter getBTAdapter() {
        IPermission iPermission;
        synchronized (BTHelper.class) {
            BluetoothAdapter bluetoothAdapter = null;
            synchronized (BTHelper.class) {
                BluetoothManager bTManager = getBTManager();
                if (bTManager != null) {
                    boolean z = false;
                    if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT"))) {
                        z = true;
                    }
                    if (z && (bluetoothAdapter = bTManager.getAdapter()) != null && !bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.enable();
                    }
                } else {
                    MantoLog.e(TAG, "BluetoothManager is null err");
                }
            }
            return bluetoothAdapter;
        }
        return bluetoothAdapter;
    }

    @TargetApi(18)
    public static synchronized BluetoothManager getBTManager() {
        BluetoothManager bluetoothManager;
        synchronized (BTHelper.class) {
            synchronized (BTHelper.class) {
                bluetoothManager = (BluetoothManager) c.a().getSystemService("bluetooth");
            }
            return bluetoothManager;
        }
        return bluetoothManager;
    }

    private static boolean hasFeature() {
        return c.a().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static boolean isServiceValid(String str) {
        try {
            UUID.fromString(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static String parse(int i2) {
        if (i2 != 257) {
            switch (i2) {
                case 0:
                    return "GATT SUCCESS";
                case 1:
                    return "GATT INVALID HANDLE";
                case 2:
                    return "GATT READ NOT PERMIT";
                case 3:
                    return "GATT WRITE NOT PERMIT";
                case 4:
                    return "GATT INVALID PDU";
                case 5:
                    return "GATT INSUF AUTHENTICATION";
                case 6:
                    return "GATT REQ NOT SUPPORTED";
                case 7:
                    return "GATT INVALID OFFSET";
                case 8:
                    return "GATT INSUF AUTHORIZATION";
                case 9:
                    return "GATT PREPARE Q FULL";
                case 10:
                    return "GATT NOT FOUND";
                case 11:
                    return "GATT NOT LONG";
                case 12:
                    return "GATT INSUF KEY SIZE";
                case 13:
                    return "GATT INVALID ATTR LEN";
                case 14:
                    return "GATT ERR UNLIKELY";
                case 15:
                    return "GATT INSUF ENCRYPTION";
                case 16:
                    return "GATT UNSUPPORT GRP TYPE";
                case 17:
                    return "GATT INSUF RESOURCE";
                default:
                    switch (i2) {
                        case 128:
                            return "GATT NO RESOURCES";
                        case 129:
                            return "GATT INTERNAL ERROR";
                        case 130:
                            return "GATT WRONG STATE";
                        case 131:
                            return "GATT DB FULL";
                        case 132:
                            return "GATT BUSY";
                        case 133:
                            return "GATT ERROR";
                        case 134:
                            return "GATT CMD STARTED";
                        case 135:
                            return "GATT ILLEGAL PARAMETER";
                        case R2.anim.lib_cashier_sdk_fragment_right_out /* 136 */:
                            return "GATT PENDING";
                        case R2.anim.lib_cashier_sdk_pop_in_animation_bottom /* 137 */:
                            return "GATT AUTH FAIL";
                        case R2.anim.lib_cashier_sdk_pop_out_animation_bottom /* 138 */:
                            return "GATT MORE";
                        case R2.anim.live_pop_rotate_amin /* 139 */:
                            return "GATT INVALID CFG";
                        case 140:
                            return "GATT SERVICE STARTED";
                        case R2.anim.manto_push_right_in /* 141 */:
                            return "GATT ENCRYPTED NO MITM";
                        case R2.anim.manto_translate_dialog_in /* 142 */:
                            return "GATT NOT ENCRYPTED";
                        case R2.anim.manto_translate_dialog_out /* 143 */:
                            return "GATT CONGESTED";
                        default:
                            switch (i2) {
                                case 253:
                                    return "GATT CCCD CFG ERROR";
                                case 254:
                                    return "GATT PROCEDURE IN PROGRESS";
                                case 255:
                                    return "GATT VALUE OUT OF RANGE";
                                default:
                                    return "UNKNOWN (" + i2 + ")";
                            }
                    }
            }
        }
        return "GATT FAILURE, TOO MANY OPEN CONNECTIONS";
    }

    public static String parseStatus(int i2) {
        return i2 != 1 ? i2 != 8 ? i2 != 19 ? i2 != 22 ? i2 != 34 ? i2 != 62 ? i2 != 133 ? i2 != 256 ? parse(i2) : "GATT CONN CANCEL " : "GATT ERROR" : "GATT CONN FAIL ESTABLISH" : "GATT CONN LMP TIMEOUT" : "GATT CONN TERMINATE LOCAL HOST" : "GATT CONN TERMINATE PEER USER" : "GATT CONN TIMEOUT" : "GATT CONN L2C FAILURE";
    }

    @TargetApi(18)
    public static boolean refreshGatt(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt != null) {
            try {
                Method method = BluetoothGatt.class.getMethod("refresh", new Class[0]);
                if (method != null) {
                    method.setAccessible(true);
                    return ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static byte[] safeByteArray(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            MantoLog.e(TAG, "data is null");
            return new byte[0];
        }
        return bArr;
    }

    public static boolean supportIndicate(int i2) {
        return (i2 & 32) > 0;
    }

    public static boolean supportNotify(int i2) {
        return (i2 & 16) > 0;
    }

    public static boolean supportRead(int i2) {
        return (i2 & 2) > 0;
    }

    public static boolean supportWrite(int i2) {
        return (i2 & 8) > 0;
    }

    public static boolean supportWriteWithoutResponse(int i2) {
        return (i2 & 4) > 0;
    }
}
