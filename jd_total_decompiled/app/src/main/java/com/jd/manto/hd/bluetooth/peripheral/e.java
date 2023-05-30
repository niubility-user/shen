package com.jd.manto.hd.bluetooth.peripheral;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.Build;
import android.util.Base64;
import androidx.annotation.RequiresApi;
import com.jingdong.manto.jsapi.bluetooth.sdk.util.BleHelpExt;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IPermission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiresApi(api = 21)
/* loaded from: classes17.dex */
public class e {
    public g a;
    private List<AdvertiseCallback> b = new CopyOnWriteArrayList();

    /* renamed from: c  reason: collision with root package name */
    private BluetoothGattServer f6682c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private Context f6683e;

    /* renamed from: f  reason: collision with root package name */
    private c f6684f;

    /* renamed from: g  reason: collision with root package name */
    private AbstractMantoModule f6685g;

    /* renamed from: h  reason: collision with root package name */
    private int f6686h;

    public e(long j2) {
        g gVar;
        BluetoothGattServer openGattServer;
        this.a = g.INIT;
        this.d = j2;
        Context g2 = com.jingdong.a.g();
        this.f6683e = g2;
        BluetoothManager bluetoothManager = (BluetoothManager) g2.getSystemService("bluetooth");
        if (bluetoothManager == null || !BleHelpExt.isPeripheralBleSupported()) {
            gVar = g.TROUBLESOME;
        } else {
            c cVar = new c(this);
            this.f6684f = cVar;
            if (Build.VERSION.SDK_INT >= 31) {
                IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
                openGattServer = (iPermission != null && iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT")) ? bluetoothManager.openGattServer(this.f6683e, this.f6684f) : openGattServer;
                gVar = g.CREATED;
            } else {
                openGattServer = bluetoothManager.openGattServer(this.f6683e, cVar);
            }
            this.f6682c = openGattServer;
            gVar = g.CREATED;
        }
        this.a = gVar;
    }

    public AbstractMantoModule a() {
        return this.f6685g;
    }

    public void a(int i2) {
        this.f6686h = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(BluetoothAdapter bluetoothAdapter) {
        IPermission iPermission;
        BluetoothLeAdvertiser bluetoothLeAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
        if (bluetoothLeAdvertiser == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (AdvertiseCallback advertiseCallback : this.b) {
            if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_ADVERTISE"))) {
                bluetoothLeAdvertiser.stopAdvertising(advertiseCallback);
            }
            arrayList.add(advertiseCallback);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.b.remove((AdvertiseCallback) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(AdvertiseCallback advertiseCallback) {
        this.b.add(advertiseCallback);
    }

    public void a(g gVar) {
        this.a = gVar;
    }

    public void a(AbstractMantoModule abstractMantoModule) {
        this.f6685g = abstractMantoModule;
    }

    public final void a(UUID uuid, UUID uuid2, boolean z, int i2, String str) {
        BluetoothGattCharacteristic characteristic;
        IPermission iPermission;
        BluetoothGattService service = this.f6682c.getService(uuid);
        if (service == null || (characteristic = service.getCharacteristic(uuid2)) == null) {
            return;
        }
        characteristic.setValue(b.a(Base64.decode(str.getBytes(), 2)));
        Iterator<BluetoothDevice> it = this.f6684f.a.iterator();
        while (it.hasNext()) {
            if (Build.VERSION.SDK_INT < 31 || ((iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class)) != null && iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT"))) {
                this.f6682c.notifyCharacteristicChanged(it.next(), characteristic, z);
            }
        }
        d dVar = this.f6684f.b.get(Integer.valueOf(i2));
        if (dVar == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            IPermission iPermission2 = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
            if (iPermission2 != null && iPermission2.hasPermission("android.permission.BLUETOOTH_CONNECT")) {
                this.f6682c.sendResponse(dVar.b, dVar.f6681c, 0, dVar.a, characteristic.getValue());
            }
        } else {
            this.f6682c.sendResponse(dVar.b, dVar.f6681c, 0, dVar.a, characteristic.getValue());
        }
        this.f6684f.b.remove(Integer.valueOf(i2));
    }

    public int b() {
        return this.f6686h;
    }

    public BluetoothGattServer c() {
        return this.f6682c;
    }

    public long d() {
        return this.d;
    }

    public final void e() {
        BluetoothGattServer bluetoothGattServer = this.f6682c;
        if (bluetoothGattServer != null) {
            if (Build.VERSION.SDK_INT >= 31) {
                IPermission iPermission = (IPermission) MantoSdkManager.instanceOf(IPermission.class);
                if (iPermission != null && iPermission.hasPermission("android.permission.BLUETOOTH_CONNECT")) {
                    bluetoothGattServer = this.f6682c;
                }
            }
            bluetoothGattServer.close();
        }
        a(BluetoothAdapter.getDefaultAdapter());
        this.f6684f = null;
        this.a = g.DESTROYED;
    }
}
