package com.jingdong.manto.m.t0.d.c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

@TargetApi(18)
/* loaded from: classes15.dex */
public class f extends BluetoothGattCallback {
    public a a;

    public f(a aVar) {
        this.a = aVar;
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        this.a.a(bluetoothGatt, bluetoothGattCharacteristic);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i2);
        this.a.b(bluetoothGatt, bluetoothGattCharacteristic, i2);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i2);
        this.a.a(bluetoothGatt, bluetoothGattCharacteristic, i2);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i2, int i3) {
        super.onConnectionStateChange(bluetoothGatt, i2, i3);
        this.a.c(bluetoothGatt, i2, i3);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i2);
        this.a.b(bluetoothGatt, bluetoothGattDescriptor, i2);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i2);
        this.a.a(bluetoothGatt, bluetoothGattDescriptor, i2);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i2, int i3) {
        super.onMtuChanged(bluetoothGatt, i2, i3);
        this.a.a(bluetoothGatt, i2, i3);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i2, int i3) {
        super.onReadRemoteRssi(bluetoothGatt, i2, i3);
        this.a.b(bluetoothGatt, i2, i3);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i2) {
        super.onReliableWriteCompleted(bluetoothGatt, i2);
        this.a.b(bluetoothGatt, i2);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i2) {
        super.onServicesDiscovered(bluetoothGatt, i2);
        this.a.a(bluetoothGatt, i2);
    }
}
