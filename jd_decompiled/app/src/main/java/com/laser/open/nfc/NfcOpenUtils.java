package com.laser.open.nfc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import com.laser.open.nfc.c.c;
import com.laser.open.nfc.model.entity.BaseResp;
import com.laser.open.nfc.model.entity.QueryTrafficCardInfoResp;
import com.laser.open.nfc.model.entity.QueryTrafficCardsResp;
import com.laser.open.nfc.model.entity.SEInfoResp;
import com.laser.open.nfc.model.entity.TrafficCardEntity;

/* loaded from: classes12.dex */
public final class NfcOpenUtils {
    private static volatile NfcOpenUtils b;
    private c a;

    private NfcOpenUtils() {
    }

    public static NfcOpenUtils getInstance() {
        if (b == null) {
            synchronized (NfcOpenUtils.class) {
                if (b == null) {
                    b = new NfcOpenUtils();
                }
            }
        }
        return b;
    }

    public BaseResp checkIssueCardCondition(TrafficCardEntity trafficCardEntity) {
        c cVar = this.a;
        if (cVar == null || !cVar.c()) {
            throw new IllegalStateException("\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
        }
        if (trafficCardEntity != null) {
            return this.a.a(trafficCardEntity);
        }
        throw new NullPointerException("cardDetail must not be null");
    }

    public BaseResp checkTopUpCondition(TrafficCardEntity trafficCardEntity) {
        c cVar = this.a;
        if (cVar == null || !cVar.c()) {
            throw new IllegalStateException("\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
        }
        if (trafficCardEntity != null) {
            return this.a.b(trafficCardEntity);
        }
        throw new NullPointerException("cardDetail must not be null");
    }

    public BaseResp deleteCard(TrafficCardEntity trafficCardEntity) {
        c cVar = this.a;
        if (cVar == null || !cVar.c()) {
            throw new IllegalStateException("\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
        }
        if (trafficCardEntity != null) {
            return this.a.c(trafficCardEntity);
        }
        throw new NullPointerException("cardDetail must not be null");
    }

    public SEInfoResp getSEInfo() {
        c cVar = this.a;
        if (cVar != null && cVar.c()) {
            return this.a.d();
        }
        throw new IllegalStateException("\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
    }

    public QueryTrafficCardsResp getTrafficCardInfo(String str) {
        c cVar = this.a;
        Preconditions.checkArgument(cVar != null && cVar.c(), "\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
        return this.a.b(str);
    }

    public QueryTrafficCardsResp getTrafficCards() {
        return getTrafficCardInfo(null);
    }

    public BaseResp initNfcOpenSDK(Context context, String str, String str2, String str3) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    c cVar = new c();
                    this.a = cVar;
                    return cVar.a(context, str, str2, str3);
                }
                throw new NullPointerException("phoneNum must not be null");
            }
            throw new NullPointerException("partnerId must not be null");
        }
        throw new NullPointerException("ctx must not be null");
    }

    @RequiresApi(api = 19)
    public void intentToTrafficPage(Activity activity, String str, String str2) {
        c.a(activity, "activity must not be null");
        c.a(str, "partnerId must not be null");
        c.a(str2, "phoneNum must not be null");
        Intent intent = new Intent();
        intent.setAction("com.laser.open.nfc.mainpage");
        intent.putExtra("partnerId", str);
        intent.putExtra("phoneNum", str2);
        activity.startActivity(intent);
    }

    public boolean isInit() {
        c cVar = this.a;
        return cVar != null && cVar.c();
    }

    public BaseResp issueCard(TrafficCardEntity trafficCardEntity, String str) {
        c cVar = this.a;
        if (cVar == null || !cVar.c()) {
            throw new IllegalStateException("\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
        }
        if (trafficCardEntity != null) {
            if (!TextUtils.isEmpty(str)) {
                return this.a.a(trafficCardEntity, str);
            }
            throw new NullPointerException("orderNo must not be null");
        }
        throw new NullPointerException("cardDetail must not be null");
    }

    public QueryTrafficCardInfoResp queryCardInfo(TrafficCardEntity trafficCardEntity, int i2) {
        c cVar = this.a;
        if (cVar == null || !cVar.c()) {
            throw new IllegalStateException("\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
        }
        if (trafficCardEntity != null) {
            return this.a.a(trafficCardEntity, i2);
        }
        throw new NullPointerException("cardDetail must not be null");
    }

    public BaseResp setDefaultCard(TrafficCardEntity trafficCardEntity) {
        return setDefaultCard(trafficCardEntity, "0");
    }

    public BaseResp topUp(TrafficCardEntity trafficCardEntity, String str) {
        return topUp(trafficCardEntity, str, "1");
    }

    public BaseResp setDefaultCard(TrafficCardEntity trafficCardEntity, String str) {
        c cVar = this.a;
        if (cVar == null || !cVar.c()) {
            throw new IllegalStateException("\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
        }
        if (trafficCardEntity != null) {
            return this.a.b(trafficCardEntity, str);
        }
        throw new NullPointerException("cardDetail must not be null");
    }

    public BaseResp topUp(TrafficCardEntity trafficCardEntity, String str, String str2) {
        c cVar = this.a;
        if (cVar == null || !cVar.c()) {
            throw new IllegalStateException("\u521d\u59cb\u5316\u672a\u6267\u884c\u6210\u529f");
        }
        if (trafficCardEntity != null) {
            if (!TextUtils.isEmpty(str)) {
                return this.a.a(trafficCardEntity, str, str2);
            }
            throw new NullPointerException("orderNo must not be null");
        }
        throw new NullPointerException("cardDetail must not be null");
    }
}
