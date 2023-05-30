package com.huawei.hms.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.api.BindingFailedResolution;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;

/* loaded from: classes12.dex */
public class BinderAdapter implements ServiceConnection {
    private final Context a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    private final String f1192c;
    private BinderCallBack d;

    /* renamed from: e  reason: collision with root package name */
    private IBinder f1193e;

    /* renamed from: f  reason: collision with root package name */
    private final Object f1194f = new Object();

    /* renamed from: g  reason: collision with root package name */
    private boolean f1195g = false;

    /* renamed from: h  reason: collision with root package name */
    private Handler f1196h = null;

    /* renamed from: i  reason: collision with root package name */
    private Handler f1197i = null;

    /* loaded from: classes12.dex */
    public interface BinderCallBack {
        void onBinderFailed(int i2);

        void onBinderFailed(int i2, Intent intent);

        void onNullBinding(ComponentName componentName);

        void onServiceConnected(ComponentName componentName, IBinder iBinder);

        void onServiceDisconnected(ComponentName componentName);

        void onTimedDisconnected();
    }

    public BinderAdapter(Context context, String str, String str2) {
        this.a = context;
        this.b = str;
        this.f1192c = str2;
    }

    private void c() {
        synchronized (this.f1194f) {
            Handler handler = this.f1196h;
            if (handler != null) {
                handler.removeMessages(getConnTimeOut());
                this.f1196h = null;
            }
        }
    }

    private void d() {
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.huawei.hms.adapter.BinderAdapter.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message == null || message.what != BinderAdapter.this.getMsgDelayDisconnect()) {
                    return false;
                }
                HMSLog.i("BinderAdapter", "The serviceConnection has been bind for 1800s, need to unbind.");
                BinderAdapter.this.unBind();
                BinderCallBack f2 = BinderAdapter.this.f();
                if (f2 != null) {
                    f2.onTimedDisconnected();
                    return true;
                }
                return true;
            }
        });
        this.f1197i = handler;
        handler.sendEmptyMessageDelayed(getMsgDelayDisconnect(), 1800000L);
    }

    private void e() {
        HMSLog.e("BinderAdapter", "In connect, bind core service fail");
        try {
            ComponentName componentName = new ComponentName(this.a.getApplicationInfo().packageName, "com.huawei.hms.activity.BridgeActivity");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.putExtra(BridgeActivity.EXTRA_DELEGATE_CLASS_NAME, BindingFailedResolution.class.getName());
            BinderCallBack f2 = f();
            if (f2 != null) {
                f2.onBinderFailed(-1, intent);
            }
        } catch (RuntimeException e2) {
            HMSLog.e("BinderAdapter", "getBindFailPendingIntent failed " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BinderCallBack f() {
        return this.d;
    }

    private void g() {
        Handler handler = this.f1196h;
        if (handler != null) {
            handler.removeMessages(getConnTimeOut());
        } else {
            this.f1196h = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.huawei.hms.adapter.BinderAdapter.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    if (message == null || message.what != BinderAdapter.this.getConnTimeOut()) {
                        return false;
                    }
                    HMSLog.e("BinderAdapter", "In connect, bind core service time out");
                    BinderAdapter.this.b();
                    return true;
                }
            });
        }
        this.f1196h.sendEmptyMessageDelayed(getConnTimeOut(), 10000L);
    }

    private void h() {
        HMSLog.d("BinderAdapter", "removeDelayDisconnectTask.");
        synchronized (BinderAdapter.class) {
            Handler handler = this.f1197i;
            if (handler != null) {
                handler.removeMessages(getMsgDelayDisconnect());
            }
        }
    }

    public void binder(BinderCallBack binderCallBack) {
        if (binderCallBack == null) {
            return;
        }
        this.d = binderCallBack;
        a();
    }

    protected int getConnTimeOut() {
        return 0;
    }

    protected int getMsgDelayDisconnect() {
        return 0;
    }

    public String getServiceAction() {
        return this.b;
    }

    public IBinder getServiceBinder() {
        return this.f1193e;
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        HMSLog.e("BinderAdapter", "Enter onNullBinding, than unBind.");
        if (this.f1195g) {
            this.f1195g = false;
            return;
        }
        unBind();
        c();
        BinderCallBack f2 = f();
        if (f2 != null) {
            f2.onNullBinding(componentName);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HMSLog.i("BinderAdapter", "BinderAdapter Enter onServiceConnected.");
        this.f1193e = iBinder;
        c();
        BinderCallBack f2 = f();
        if (f2 != null) {
            f2.onServiceConnected(componentName, iBinder);
        }
        d();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        HMSLog.i("BinderAdapter", "Enter onServiceDisconnected.");
        BinderCallBack f2 = f();
        if (f2 != null) {
            f2.onServiceDisconnected(componentName);
        }
        h();
    }

    public void unBind() {
        Util.unBindServiceCatchException(this.a, this);
    }

    public void updateDelayTask() {
        HMSLog.d("BinderAdapter", "updateDelayTask.");
        synchronized (BinderAdapter.class) {
            Handler handler = this.f1197i;
            if (handler != null) {
                handler.removeMessages(getMsgDelayDisconnect());
                this.f1197i.sendEmptyMessageDelayed(getMsgDelayDisconnect(), 1800000L);
            }
        }
    }

    private void a() {
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.f1192c)) {
            e();
        }
        Intent intent = new Intent(this.b);
        try {
            intent.setPackage(this.f1192c);
        } catch (IllegalArgumentException unused) {
            HMSLog.e("BinderAdapter", "IllegalArgumentException when bindCoreService intent.setPackage");
            e();
        }
        synchronized (this.f1194f) {
            if (this.a.bindService(intent, this, 1)) {
                g();
                return;
            }
            this.f1195g = true;
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        BinderCallBack f2 = f();
        if (f2 != null) {
            f2.onBinderFailed(-1);
        }
    }
}
