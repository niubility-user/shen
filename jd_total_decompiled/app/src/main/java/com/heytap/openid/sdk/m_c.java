package com.heytap.openid.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.heytap.openid.m_a;

/* loaded from: classes12.dex */
public class m_c {
    public volatile com.heytap.openid.m_a m_a = null;
    public String m_b = null;
    public String m_c = null;
    public final Object m_d = new Object();
    public ServiceConnection m_e = new m_a();

    /* loaded from: classes12.dex */
    public class m_a implements ServiceConnection {
        public m_a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            m_c.this.m_a = m_a.AbstractBinderC0055m_a.m_a(iBinder);
            synchronized (m_c.this.m_d) {
                m_c.this.m_d.notify();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            m_c.this.m_a = null;
        }
    }

    /* loaded from: classes12.dex */
    public static class m_b {
        public static final m_c m_a = new m_c();
    }

    public String m_a(Context context, String str) {
        if (this.m_a != null) {
            try {
                return m_b(context, str);
            } catch (RemoteException unused) {
                return "";
            }
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(com.heytap.openid.sdk.m_a.m_a("Y29tLmhleXRhcC5vcGVuaWQ="), com.heytap.openid.sdk.m_a.m_a("Y29tLmhleXRhcC5vcGVuaWQuSWRlbnRpZnlTZXJ2aWNl")));
        intent.setAction(com.heytap.openid.sdk.m_a.m_a("YWN0aW9uLmNvbS5oZXl0YXAub3BlbmlkLk9QRU5fSURfU0VSVklDRQ=="));
        try {
            if (context.bindService(intent, this.m_e, 1) && this.m_a == null) {
                synchronized (this.m_d) {
                    try {
                        if (this.m_a == null) {
                            this.m_d.wait(10000L);
                        }
                    } catch (InterruptedException unused2) {
                    }
                }
            }
        } catch (Exception e2) {
            StringBuilder sb = new StringBuilder("1008 ");
            sb.append(e2.getMessage() != null ? e2.getMessage() : e2.getLocalizedMessage());
            sb.toString();
        }
        if (this.m_a == null) {
            return "";
        }
        try {
            return m_b(context, str);
        } catch (RemoteException unused3) {
            return "";
        }
    }

    public synchronized void m_a(Context context) {
        try {
            if (this.m_a != null) {
                context.unbindService(this.m_e);
                this.m_a = null;
            }
        } catch (Exception unused) {
        }
    }

    public final String m_b(Context context, String str) {
        if (TextUtils.isEmpty(this.m_b)) {
            this.m_b = context.getPackageName();
        }
        if (TextUtils.isEmpty(this.m_c)) {
            this.m_c = com.heytap.openid.sdk.m_a.m_a(context, this.m_b, "SHA1");
        }
        if (this.m_a != null) {
            String m_a2 = this.m_a.m_a(this.m_b, this.m_c, str);
            return TextUtils.isEmpty(m_a2) ? "" : m_a2;
        }
        String str2 = context.getPackageName() + " 1009";
        return "";
    }
}
