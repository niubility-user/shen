package c.t.m.g;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressLint({"NewApi"})
/* loaded from: classes.dex */
public class p1 extends ScanCallback {
    public final Context a;
    public BluetoothManager b;

    /* renamed from: c  reason: collision with root package name */
    public BluetoothAdapter f595c;
    public BluetoothLeScanner d;

    /* renamed from: j  reason: collision with root package name */
    public boolean f601j;

    /* renamed from: k  reason: collision with root package name */
    public a f602k;

    /* renamed from: l  reason: collision with root package name */
    public HandlerThread f603l;

    /* renamed from: m  reason: collision with root package name */
    public byte[] f604m = new byte[0];

    /* renamed from: g  reason: collision with root package name */
    public List<w6> f598g = new LinkedList();

    /* renamed from: h  reason: collision with root package name */
    public List<w6> f599h = new LinkedList();

    /* renamed from: i  reason: collision with root package name */
    public String[] f600i = "AB8190D5-D11E-4941-ACC4-42F30510B408,FDA50693-A4E2-4FB1-AFCF-C6EB07647825".split(DYConstants.DY_REGEX_COMMA);

    /* renamed from: e  reason: collision with root package name */
    public ScanSettings f596e = new ScanSettings.Builder().setScanMode(1).build();

    /* renamed from: f  reason: collision with root package name */
    public List<ScanFilter> f597f = new ArrayList();

    /* loaded from: classes.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        public void a(Message message) {
            switch (message.what) {
                case 99001:
                    p1 p1Var = p1.this;
                    p1Var.f595c = p1Var.b == null ? null : p1.this.b.getAdapter();
                    if (p1.this.f595c != null) {
                        p1 p1Var2 = p1.this;
                        p1Var2.d = p1Var2.f595c.getBluetoothLeScanner();
                    }
                    p1.this.m();
                    return;
                case 99002:
                    if (p1.this.f601j) {
                        p1.this.o();
                        return;
                    }
                    return;
                case 99003:
                    p1.this.e((ScanResult) message.obj);
                    return;
                default:
                    return;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                a(message);
            } catch (Throwable unused) {
            }
        }
    }

    public p1(Context context) {
        this.a = context;
        this.b = (BluetoothManager) context.getSystemService("bluetooth");
        for (String str : this.f600i) {
            this.f597f.add(k(str));
        }
    }

    public List<w6> d() {
        List<w6> list;
        synchronized (this.f598g) {
            this.f599h.clear();
            for (w6 w6Var : this.f598g) {
                if (System.currentTimeMillis() - w6Var.m() <= Final.FIVE_SECOND) {
                    this.f599h.add((w6) w6Var.clone());
                }
            }
            this.f598g.clear();
            list = this.f599h;
        }
        return list;
    }

    public final void e(ScanResult scanResult) {
        if (scanResult == null) {
            return;
        }
        try {
            BluetoothDevice device = scanResult.getDevice();
            int rssi = scanResult.getRssi();
            byte[] bytes = scanResult.getScanRecord().getBytes();
            if (bytes == null || bytes.length < 30) {
                return;
            }
            h(w6.a(device, rssi, bytes));
        } catch (Throwable unused) {
        }
    }

    public void f(Handler handler) {
        a aVar;
        synchronized (this.f604m) {
            if (this.f602k == null) {
                if (handler == null || handler.getLooper() == null) {
                    HandlerThread handlerThread = new HandlerThread("thread-bleloc");
                    this.f603l = handlerThread;
                    handlerThread.start();
                    aVar = new a(this.f603l.getLooper());
                } else {
                    aVar = new a(handler.getLooper());
                }
                this.f602k = aVar;
            }
            this.f602k.sendEmptyMessage(99001);
        }
    }

    public final void h(w6 w6Var) {
        synchronized (this.f598g) {
            if (w6Var != null) {
                this.f598g.add(w6Var);
            }
        }
    }

    public final byte[] i(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    public final ScanFilter k(String str) {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.arraycopy(i(str.replace("-", "")), 0, bArr, 2, 16);
        return new ScanFilter.Builder().setManufacturerData(76, bArr, new byte[]{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}).build();
    }

    public void l() {
        synchronized (this.f604m) {
            a aVar = this.f602k;
            if (aVar != null) {
                aVar.removeCallbacksAndMessages(null);
                this.f602k.sendEmptyMessage(99002);
                this.f602k = null;
            }
            if (this.f603l != null) {
                this.f603l = null;
            }
        }
    }

    public final int m() {
        BluetoothLeScanner bluetoothLeScanner;
        try {
            if (this.a.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
                BluetoothAdapter bluetoothAdapter = this.f595c;
                if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled() || (bluetoothLeScanner = this.d) == null) {
                    return -2;
                }
                bluetoothLeScanner.startScan(this.f597f, this.f596e, this);
                this.f601j = true;
                return 0;
            }
            return -1;
        } catch (Throwable unused) {
            return -3;
        }
    }

    public final void o() {
        try {
            synchronized (this.f604m) {
                if (this.a.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
                    BluetoothLeScanner bluetoothLeScanner = this.d;
                    if (bluetoothLeScanner != null) {
                        bluetoothLeScanner.stopScan(this);
                    }
                    this.f595c = null;
                    this.f601j = false;
                    synchronized (this.f598g) {
                        this.f598g.clear();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onBatchScanResults(List<ScanResult> list) {
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanFailed(int i2) {
    }

    @Override // android.bluetooth.le.ScanCallback
    public void onScanResult(int i2, ScanResult scanResult) {
        a aVar = this.f602k;
        if (aVar != null) {
            Message obtainMessage = aVar.obtainMessage();
            obtainMessage.what = 99003;
            obtainMessage.obj = scanResult;
            aVar.sendMessage(obtainMessage);
        }
    }
}
