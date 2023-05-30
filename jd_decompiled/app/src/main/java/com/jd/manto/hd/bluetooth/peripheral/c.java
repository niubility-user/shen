package com.jd.manto.hd.bluetooth.peripheral;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import android.util.Base64;
import androidx.annotation.RequiresApi;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiresApi(api = 21)
/* loaded from: classes17.dex */
public class c extends BluetoothGattServerCallback {
    final HashSet<BluetoothDevice> a = new HashSet<>();
    final Map<Long, d> b = new ConcurrentHashMap();

    /* renamed from: c */
    public e f6680c;

    public c(e eVar) {
        this.f6680c = eVar;
    }

    private boolean a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return true;
        }
        HashSet<BluetoothDevice> hashSet = this.a;
        ArrayList arrayList = new ArrayList();
        Iterator<BluetoothDevice> it = hashSet.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getAddress());
        }
        return !arrayList.contains(bluetoothDevice.getAddress());
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onCharacteristicReadRequest(BluetoothDevice bluetoothDevice, int i2, int i3, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        long currentTimeMillis;
        super.onCharacteristicReadRequest(bluetoothDevice, i2, i3, bluetoothGattCharacteristic);
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (a(bluetoothDevice)) {
            return;
        }
        if (i3 != 0) {
            if (Build.VERSION.SDK_INT < 31) {
                this.f6680c.c().sendResponse(bluetoothDevice, i2, 7, i3, value);
                return;
            }
            IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            if (iPermission == null || !iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT")) {
                return;
            }
            this.f6680c.c().sendResponse(bluetoothDevice, i2, 7, i3, value);
            return;
        }
        do {
            currentTimeMillis = System.currentTimeMillis();
        } while (this.b.containsKey(Long.valueOf(currentTimeMillis)));
        AbstractMantoModule a = this.f6680c.a();
        String uuid = bluetoothGattCharacteristic.getService().getUuid().toString();
        String uuid2 = bluetoothGattCharacteristic.getUuid().toString();
        if (Build.VERSION.SDK_INT >= 31) {
            IPermission iPermission2 = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            if (iPermission2 != null && iPermission2.hasPermission("android.permission.BLUETOOTH_CONNECT")) {
                this.f6680c.c().sendResponse(bluetoothDevice, i2, 0, i3, value);
            }
        } else {
            this.f6680c.c().sendResponse(bluetoothDevice, i2, 0, i3, value);
        }
        if (a.a(a, this.f6680c.d(), uuid, uuid2, currentTimeMillis, this.f6680c.b())) {
            this.b.put(Long.valueOf(currentTimeMillis), new d(bluetoothDevice, i2, i3));
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onCharacteristicWriteRequest(BluetoothDevice bluetoothDevice, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i3, byte[] bArr) {
        long currentTimeMillis;
        super.onCharacteristicWriteRequest(bluetoothDevice, i2, bluetoothGattCharacteristic, z, z2, i3, bArr);
        String str = new String(bArr);
        if (a(bluetoothDevice)) {
            return;
        }
        if (i3 != 0) {
            if (Build.VERSION.SDK_INT < 31) {
                this.f6680c.c().sendResponse(bluetoothDevice, i2, 7, i3, bluetoothGattCharacteristic.getValue());
                return;
            }
            IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            if (iPermission == null || !iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT")) {
                return;
            }
            this.f6680c.c().sendResponse(bluetoothDevice, i2, 7, i3, bluetoothGattCharacteristic.getValue());
            return;
        }
        do {
            currentTimeMillis = System.currentTimeMillis();
        } while (this.b.containsKey(Long.valueOf(currentTimeMillis)));
        AbstractMantoModule a = this.f6680c.a();
        long d = this.f6680c.d();
        String uuid = bluetoothGattCharacteristic.getService().getUuid().toString();
        String uuid2 = bluetoothGattCharacteristic.getUuid().toString();
        if (Build.VERSION.SDK_INT >= 31) {
            IPermission iPermission2 = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            if (iPermission2 != null && iPermission2.hasPermission("android.permission.BLUETOOTH_CONNECT")) {
                this.f6680c.c().sendResponse(bluetoothDevice, i2, 0, i3, bArr);
            }
        } else {
            this.f6680c.c().sendResponse(bluetoothDevice, i2, 0, i3, bArr);
        }
        if (a.a(a, d, uuid, uuid2, currentTimeMillis, Base64.encodeToString(str.getBytes(), 2), this.f6680c.b())) {
            this.b.put(Long.valueOf(currentTimeMillis), new d(bluetoothDevice, i2, i3));
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onConnectionStateChange(BluetoothDevice bluetoothDevice, int i2, int i3) {
        super.onConnectionStateChange(bluetoothDevice, i2, i3);
        if (i2 != 0) {
            this.a.remove(bluetoothDevice);
        } else if (i3 == 0) {
            this.a.remove(bluetoothDevice);
            a.a(this.f6680c.a(), bluetoothDevice.getAddress(), String.valueOf(this.f6680c.d()), false, this.f6680c.b());
            this.f6680c.a(g.CREATED);
        } else if (i3 != 2) {
        } else {
            this.a.add(bluetoothDevice);
            a.a(this.f6680c.a(), bluetoothDevice.getAddress(), String.valueOf(this.f6680c.d()), true, this.f6680c.b());
            this.f6680c.a(g.CONNECTED);
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onDescriptorReadRequest(BluetoothDevice bluetoothDevice, int i2, int i3, BluetoothGattDescriptor bluetoothGattDescriptor) {
        IPermission iPermission;
        super.onDescriptorReadRequest(bluetoothDevice, i2, i3, bluetoothGattDescriptor);
        if (i3 != 0) {
            if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT"))) {
                this.f6680c.c().sendResponse(bluetoothDevice, i2, 7, i3, null);
            }
        } else if (Build.VERSION.SDK_INT < 31) {
            this.f6680c.c().sendResponse(bluetoothDevice, i2, 0, i3, bluetoothGattDescriptor.getValue());
        } else {
            IPermission iPermission2 = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            if (iPermission2 == null || !iPermission2.hasPermission("android.permission.BLUETOOTH_CONNECT")) {
                return;
            }
            this.f6680c.c().sendResponse(bluetoothDevice, i2, 0, i3, bluetoothGattDescriptor.getValue());
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onDescriptorWriteRequest(BluetoothDevice bluetoothDevice, int i2, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z, boolean z2, int i3, byte[] bArr) {
        IPermission iPermission;
        super.onDescriptorWriteRequest(bluetoothDevice, i2, bluetoothGattDescriptor, z, z2, i3, bArr);
        if (z2) {
            if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT"))) {
                this.f6680c.c().sendResponse(bluetoothDevice, i2, 0, 0, null);
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onExecuteWrite(BluetoothDevice bluetoothDevice, int i2, boolean z) {
        super.onExecuteWrite(bluetoothDevice, i2, z);
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onMtuChanged(BluetoothDevice bluetoothDevice, int i2) {
        super.onMtuChanged(bluetoothDevice, i2);
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onNotificationSent(BluetoothDevice bluetoothDevice, int i2) {
        super.onNotificationSent(bluetoothDevice, i2);
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onPhyRead(BluetoothDevice bluetoothDevice, int i2, int i3, int i4) {
        super.onPhyRead(bluetoothDevice, i2, i3, i4);
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onPhyUpdate(BluetoothDevice bluetoothDevice, int i2, int i3, int i4) {
        super.onPhyUpdate(bluetoothDevice, i2, i3, i4);
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onServiceAdded(int i2, BluetoothGattService bluetoothGattService) {
        super.onServiceAdded(i2, bluetoothGattService);
    }
}
