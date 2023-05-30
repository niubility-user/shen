package com.jingdong.common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.threadpool.ThreadManager;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class BssidFetcher {
    public static final String TAG = "BssidFetcher";
    private volatile String bssid;
    private volatile String bssidMd5;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class InnerHolder {
        private static final BssidFetcher instance = new BssidFetcher();

        private InnerHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String bssidFormat(String str) {
        String[] split = str.split(":");
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; split != null && i2 < split.length; i2++) {
            String str2 = split[i2];
            if (str2.startsWith("0") && str2.length() == 2) {
                str2 = str2.substring(1, 2);
            }
            if (i2 == split.length - 1) {
                sb.append(str2);
            } else {
                sb.append(str2 + ":");
            }
        }
        return sb.toString();
    }

    public static final BssidFetcher getInstance() {
        return InnerHolder.instance;
    }

    @Nullable
    public synchronized String getBssid() {
        return this.bssidMd5;
    }

    public void updateBssid(final boolean z, final boolean z2) {
        ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.utils.BssidFetcher.1
            @Override // java.lang.Runnable
            @SuppressLint({"MissingPermission"})
            public void run() {
                String str;
                String str2 = "unknown";
                if (z && z2) {
                    int i2 = 0;
                    do {
                        str = BaseInfo.getWifiBSSID(JdSdk.getInstance().getApplicationContext());
                        if (TextUtils.isEmpty(str)) {
                            try {
                                Thread.sleep(50L);
                            } catch (InterruptedException unused) {
                            }
                            i2++;
                        }
                        if (!TextUtils.isEmpty(str)) {
                            break;
                        }
                    } while (i2 < 3);
                    if (!TextUtils.isEmpty(str) && !TextUtils.equals(BssidFetcher.this.bssid, str)) {
                        if (!str.equalsIgnoreCase("02:00:00:00:00:00")) {
                            try {
                                str2 = Md5Encrypt.md5(BssidFetcher.this.bssidFormat(str));
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        BssidFetcher.this.bssid = str;
                        BssidFetcher.this.bssidMd5 = str2;
                    }
                }
                str = null;
                BssidFetcher.this.bssid = str;
                BssidFetcher.this.bssidMd5 = str2;
            }
        });
    }

    private BssidFetcher() {
        this.bssid = null;
        this.bssidMd5 = "unknown";
    }
}
