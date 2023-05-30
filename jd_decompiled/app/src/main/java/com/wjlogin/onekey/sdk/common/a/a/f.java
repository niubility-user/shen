package com.wjlogin.onekey.sdk.common.a.a;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.wjlogin.onekey.sdk.util.LogUtil;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class f extends ConnectivityManager.NetworkCallback {
    private AtomicBoolean a = new AtomicBoolean(false);
    final /* synthetic */ URL b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ g f18326c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(g gVar, URL url) {
        this.f18326c = gVar;
        this.b = url;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        super.onAvailable(network);
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", " post onAvailable android5.0");
        }
        if (this.a.getAndSet(true)) {
            return;
        }
        try {
            if (LogUtil.enableLog) {
                LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", " post onAvailable android5.0  connection");
            }
            this.f18326c.a((HttpURLConnection) network.openConnection(this.b));
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f18326c.a(com.wjlogin.onekey.sdk.util.a.a("-100", "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e", "CT", e2.getMessage()));
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", " post onCapabilitiesChanged");
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties);
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", " post onLinkPropertiesChanged");
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLosing(Network network, int i2) {
        super.onLosing(network, i2);
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", " post onLosing");
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        super.onLost(network);
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", " post onLost");
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onUnavailable() {
        super.onUnavailable();
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomHttpExecut", " post onUnavailable");
        }
    }
}
