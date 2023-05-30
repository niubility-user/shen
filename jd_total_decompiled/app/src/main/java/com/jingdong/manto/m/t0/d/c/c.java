package com.jingdong.manto.m.t0.d.c;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.Queue;

/* loaded from: classes15.dex */
public class c implements com.jingdong.manto.m.t0.d.c.a {
    public Handler a = new Handler(Looper.getMainLooper());
    public Queue<com.jingdong.manto.m.t0.d.d.c> b;

    /* renamed from: c */
    com.jingdong.manto.m.t0.d.d.c f13634c;
    public List<com.jingdong.manto.m.t0.d.d.c> d;

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        a() {
            c.this = r1;
        }

        @Override // java.lang.Runnable
        public final void run() {
            c.this.f13634c.a();
        }
    }

    public final synchronized void a() {
        Queue<com.jingdong.manto.m.t0.d.d.c> queue;
        com.jingdong.manto.m.t0.d.d.c cVar = this.f13634c;
        if ((cVar == null || cVar.f13654c) && (queue = this.b) != null && queue.size() > 0) {
            com.jingdong.manto.m.t0.d.d.c poll = this.b.poll();
            this.f13634c = poll;
            if (poll != null) {
                if (poll.d) {
                    this.a.postDelayed(new a(), poll.f13661k);
                } else {
                    poll.a();
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, int i2) {
        com.jingdong.manto.m.t0.d.d.c cVar = this.f13634c;
        if (cVar != null) {
            cVar.a(bluetoothGatt, i2);
        }
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar2 : list) {
                if (cVar2.f13654c) {
                    cVar2.a(bluetoothGatt, i2);
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, int i2, int i3) {
        com.jingdong.manto.m.t0.d.d.c cVar = this.f13634c;
        if (cVar != null) {
            cVar.a(bluetoothGatt, i2, i3);
        }
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar2 : list) {
                if (cVar2.f13654c) {
                    cVar2.a(bluetoothGatt, i2, i3);
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar : list) {
                if (cVar.f13654c) {
                    cVar.a(bluetoothGatt, bluetoothGattCharacteristic);
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        com.jingdong.manto.m.t0.d.d.c cVar = this.f13634c;
        if (cVar != null) {
            cVar.a(bluetoothGatt, bluetoothGattCharacteristic, i2);
        }
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar2 : list) {
                if (cVar2.f13654c) {
                    cVar2.a(bluetoothGatt, bluetoothGattCharacteristic, i2);
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void a(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        com.jingdong.manto.m.t0.d.d.c cVar = this.f13634c;
        if (cVar != null) {
            cVar.a(bluetoothGatt, bluetoothGattDescriptor, i2);
        }
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar2 : list) {
                if (cVar2.f13654c) {
                    cVar2.a(bluetoothGatt, bluetoothGattDescriptor, i2);
                }
            }
        }
    }

    public final synchronized void b() {
        Queue<com.jingdong.manto.m.t0.d.d.c> queue = this.b;
        if (queue != null) {
            queue.clear();
            this.b = null;
        }
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            list.clear();
            this.d = null;
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void b(BluetoothGatt bluetoothGatt, int i2) {
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar : list) {
                if (cVar.f13654c) {
                    cVar.b(bluetoothGatt, i2);
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void b(BluetoothGatt bluetoothGatt, int i2, int i3) {
        com.jingdong.manto.m.t0.d.d.c cVar = this.f13634c;
        if (cVar != null) {
            cVar.b(bluetoothGatt, i2, i3);
        }
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar2 : list) {
                if (cVar2.f13654c) {
                    cVar2.a(bluetoothGatt, i3);
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void b(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2) {
        com.jingdong.manto.m.t0.d.d.c cVar = this.f13634c;
        if (cVar != null) {
            cVar.b(bluetoothGatt, bluetoothGattCharacteristic, i2);
        }
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar2 : list) {
                if (cVar2.f13654c) {
                    cVar2.a(bluetoothGatt, i2);
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void b(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i2) {
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar : list) {
                if (cVar.f13654c) {
                    cVar.b(bluetoothGatt, bluetoothGattDescriptor, i2);
                }
            }
        }
    }

    @Override // com.jingdong.manto.m.t0.d.c.a
    public final void c(BluetoothGatt bluetoothGatt, int i2, int i3) {
        com.jingdong.manto.m.t0.d.d.c cVar = this.f13634c;
        if (cVar != null) {
            cVar.c(bluetoothGatt, i2, i3);
        }
        List<com.jingdong.manto.m.t0.d.d.c> list = this.d;
        if (list != null) {
            for (com.jingdong.manto.m.t0.d.d.c cVar2 : list) {
                if (cVar2.f13654c) {
                    cVar2.c(bluetoothGatt, i2, i3);
                }
            }
        }
    }
}
