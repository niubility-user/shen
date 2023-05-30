package com.jingdong.manto.m.t0.d.c;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

/* loaded from: classes15.dex */
public interface a {
    void a(BluetoothGatt bluetoothGatt, int i2);

    void a(BluetoothGatt bluetoothGatt, int i2, int i3);

    void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2);

    void a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2);

    void b(BluetoothGatt bluetoothGatt, int i2);

    void b(BluetoothGatt bluetoothGatt, int i2, int i3);

    void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2);

    void b(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2);

    void c(BluetoothGatt bluetoothGatt, int i2, int i3);
}
