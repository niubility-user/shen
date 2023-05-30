package com.laser.open.nfc.b;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.nfc.sdk.service.IHwTransitOpenService;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.laser.open.nfc.hw.entity.BaseResponse;
import com.laser.open.nfc.hw.entity.CardDataResponse;
import com.laser.open.nfc.hw.entity.CplcDataResponse;
import com.laser.utils.common.LogUtil;
import com.laser.utils.common.RSAUtils;
import com.laser.utils.common.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes12.dex */
public final class b {

    /* renamed from: f  reason: collision with root package name */
    private static b f15669f;
    private IHwTransitOpenService a = null;
    private final String b = "HwServiceOperator";

    /* renamed from: c  reason: collision with root package name */
    private boolean f15670c = false;
    private CountDownLatch d = null;

    /* renamed from: e  reason: collision with root package name */
    private ServiceConnection f15671e = new a();

    /* loaded from: classes12.dex */
    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.d("HwServiceOperator", "onServiceConnected");
            b.this.a = IHwTransitOpenService.Stub.asInterface(iBinder);
            b.this.f15670c = true;
            if (b.this.d != null) {
                b.this.d.countDown();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.d("HwServiceOperator", "onServiceDisconnected");
            b.this.a = null;
            b.this.f15670c = false;
            if (b.this.d != null) {
                b.this.d.countDown();
            }
        }
    }

    private b() {
    }

    private com.laser.open.nfc.c.d b() {
        if (this.a == null || !this.f15670c) {
            this.d = new CountDownLatch(1);
            com.laser.open.nfc.c.d a2 = a();
            if (a2.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
                return a2;
            }
            if (this.d.getCount() > 0) {
                try {
                    this.d.await();
                } catch (InterruptedException e2) {
                    LogUtil.e("HwServiceOperator", "checkServiceState exception" + e2.getMessage());
                }
            }
            this.d = null;
        }
        LogUtil.d("HwServiceOperator", "isServiceConnect:" + this.f15670c);
        if (this.f15670c && this.a != null) {
            return com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS;
        }
        return com.laser.open.nfc.c.d.BASE_STATUS_WALLET_SERVICE_BIND_FAIL;
    }

    public static synchronized b d() {
        b bVar;
        synchronized (b.class) {
            if (f15669f == null) {
                f15669f = new b();
            }
            bVar = f15669f;
        }
        return bVar;
    }

    public CplcDataResponse c() throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("getCplc checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new CplcDataResponse(b.getStatus(), b.getMsg());
        }
        String queryCplc = this.a.queryCplc();
        LogUtil.d("getCplc result:" + queryCplc);
        if (TextUtils.isEmpty(queryCplc)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new CplcDataResponse(dVar.getStatus(), dVar.getMsg() + ":getCplc");
        }
        return (CplcDataResponse) new Gson().fromJson(queryCplc, CplcDataResponse.class);
    }

    private com.laser.open.nfc.c.d a() {
        try {
            Intent intent = new Intent("com.huawei.nfc.action.TRANSIT_OPEN_SERVICE");
            intent.setPackage("com.huawei.wallet");
            boolean bindService = Utils.getApp().getApplicationContext().bindService(intent, this.f15671e, 1);
            LogUtil.d("HwServiceOperator", "bindHwService:" + bindService);
            return bindService ? com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS : com.laser.open.nfc.c.d.BASE_STATUS_WALLET_SERVICE_BIND_FAIL;
        } catch (Exception e2) {
            LogUtil.e("HwServiceOperator", "bindHwService:" + e2.getMessage());
            return com.laser.open.nfc.c.d.BASE_STATUS_LOCAL_ERROR;
        }
    }

    public BaseResponse c(String str, String str2, String str3) throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("setDefaultCard checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new BaseResponse(b.getStatus(), b.getMsg());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("issuerID", str2);
        hashMap.put("spID", str);
        if (TextUtils.isEmpty(str3)) {
            str3 = "0";
        }
        hashMap.put("mode", str3);
        String startSetDefault = this.a.startSetDefault(hashMap);
        LogUtil.d("setDefaultCard result:" + startSetDefault);
        if (TextUtils.isEmpty(startSetDefault)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new CardDataResponse(dVar.getStatus(), dVar.getMsg() + ":setDefaultCard");
        }
        return (BaseResponse) new Gson().fromJson(startSetDefault, BaseResponse.class);
    }

    private Intent a(Intent intent) {
        String str;
        List<ResolveInfo> queryIntentServices = Utils.getApp().getApplicationContext().getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null) {
            str = "\u670d\u52a1\u4e3a\u7a7a";
        } else {
            str = "" + queryIntentServices.size();
        }
        LogUtil.e("serviceSize:" + str);
        if (queryIntentServices != null && queryIntentServices.size() != 0) {
            ServiceInfo serviceInfo = queryIntentServices.get(0).serviceInfo;
            return new Intent(intent).setComponent(new ComponentName(serviceInfo.packageName, serviceInfo.name));
        }
        LogUtil.e("resolveInfo:" + queryIntentServices);
        return null;
    }

    public BaseResponse b(String str, String str2, String str3) throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("preIssueCard checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new BaseResponse(b.getStatus(), b.getMsg());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("issuerID", str2);
        hashMap.put("spID", str);
        hashMap.put("orderNo", str3);
        hashMap.put("operation", "1");
        hashMap.put("cityCode", "");
        String preIssueCard = this.a.preIssueCard(hashMap);
        LogUtil.d("preIssueCard result:" + preIssueCard);
        if (TextUtils.isEmpty(preIssueCard)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new BaseResponse(dVar.getStatus(), dVar.getMsg() + ":preIssueCard");
        }
        return (BaseResponse) new Gson().fromJson(preIssueCard, BaseResponse.class);
    }

    public BaseResponse a(String str, int i2) throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("checkIssueCardConditions checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new BaseResponse(b.getStatus(), b.getMsg());
        }
        if (i2 < 801113000) {
            int checkIssueCardConditions = this.a.checkIssueCardConditions(str);
            LogUtil.d("checkIssueCardConditions result:" + checkIssueCardConditions);
            c cVar = c.SUCCESS;
            if (checkIssueCardConditions != cVar.getStatus()) {
                if (String.valueOf(checkIssueCardConditions).length() == 4) {
                    return new BaseResponse(checkIssueCardConditions, -1, "\u94b1\u5305\u72b6\u6001\u4e0d\u6ee1\u8db3");
                }
                return new BaseResponse(checkIssueCardConditions, "\u94b1\u5305\u72b6\u6001\u4e0d\u6ee1\u8db3");
            }
            return new BaseResponse(cVar.getStatus(), cVar.getMsg());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("issuerID", str);
        String checkIssueConditions = this.a.checkIssueConditions(hashMap);
        LogUtil.d("checkIssueConditions result:" + checkIssueConditions);
        if (TextUtils.isEmpty(checkIssueConditions)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new BaseResponse(dVar.getStatus(), dVar.getMsg() + ":preIssueCard");
        }
        return (BaseResponse) new Gson().fromJson(checkIssueConditions, BaseResponse.class);
    }

    private String c(String str) {
        System.currentTimeMillis();
        return RSAUtils.sign(str, "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCXiI7DSv2w8kEI4sQqdixxvjRr6ONz6vqWSWQcmtNX89rEfazIi0R2vESTuJA+gZdbgYZ++9PyukCCy8mfeIVkD2ug1F42vcVghp9XI3mYU9ktUZard2rotfhyEXbi7v0/3J7jA7LqUhURbtXziuLXbdR3ugEbBdy7Ny6YEkhzoCDYUGOVKGIDqFdCLoJ4BBn5cnjOS0608aa/QWiKPzF9KqhRmHtrs6XW56rrG+SyjgmdrBJVFmmhiD0ZHOkcIGlRoalF49Q9L6lUy+QMM7WgOKOR4iq9VpEiXYWP2q/z4CGPZSdQlvMGTGl+Luq99RC/bzmne7325xJosP1Gn5lAgMBAAECggEALKh2bWgbsVJsH8DVOIYWNwAD6ECUdFW5fHPBTHQFkeHKOdFTgxWn/r/TaQQguUVsvMCd0nTdC6Zkn1CZfEQPGHfqyUcut4m6rmF9FYiJC2w2ODdovFNm5g7IiQ2D8KszXb/Lxcq6nsWEg2nHvVJg1ELsM116eVlVxwagri+hnbSUY76ljR6HkGs+YJEYixF+ocau0S3KqA3X9idDALc6B5WQZtkdXstoY77NsI+WPWVoXgL3jBOLoVVV+6vTVnAI76ViqKxQEL1SoTB00nsChqdaTT9Sc/dlCF/IHNExS5YYo3fzI8o+2saGrA8tkarIu7Nt1x284y2JTNU9TwiB2QKBgQC3dqaqkXD0ha7B1rT8FoTWNK3Hav5jR+5ZdnamwQZFNFHnsbyM8UE5kuqWUgLAqUrl886vLVODe1qbBAq6Gvzj3jawaYiJmnSKqznP2OJSJIkmThKksK9fUvSm+OTMjdkiru4xLCcksM8qmpx+m/u6Sr7dZnRrR+TtSgkf9lvc/wKBgQC16V4M1JEnsi22kR7XeAroPiaaCp2tt2ltvFUBNuhBlT5qCEQQsZ1e1uJFo0ojiC/pl6CHZ0YxlrxrRFNCU2KcKXeUNTrzOlPrpwyaLjhgWdKv5+HhFt/GLDTy6eNiuv06ir4smIXCNobhf6ao1Md7ntfipETtbe9J9m0HrZRQmwKBgHreMMKdAywP97kDxVPq9O3Me/HJGzN8cgliSBZnwX5Jiu0D4MX3sPoRBaDErek/t9SHUw2I6pxs2YJl05VOC73EDZZaJ0IYuiJAiEhF9VST+XI46U14swrBMO4+VGUWPqx2wTkXW+O7shLTVymH56+c7XSzcBaFILE2WyQXxvAbAoGAW7TlXBSBhQCXeRVZ9ggnf4NtlHTLnvr65H5wYCddGUry5yN5pOzVyC0Ob0rtolISzXFKVVikE3XJUU5lpudArDdfeFEJgfsiuYdCnCu28iP2SgXdYstUTqSUoO12W5ym8q9lRjgyHoG8p8vhqfVzB4hBj5l+wi+M1b2so5VHUwcCgYB0+qHnkLQB7FBc/MHtDEG8SVzMWP8UmylLZL1na35k0JfAlIad+SfR5Mds1H9Yn8toKQf5rm5n8dNOaODhQ+vI/yII0ycFMN49KE4Uo1A7cLAnlCaSuFq0vkgiThSxe3yi9+0U644t29K7GwPV+pPCOSqW9goIZUOtQtk8GOWP1w==");
    }

    public BaseResponse b(String str, String str2, String str3, String str4) throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("recharge checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new BaseResponse(b.getStatus(), b.getMsg());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("issuerID", str2);
        hashMap.put("orderNo", str3);
        hashMap.put("operation", str4);
        hashMap.put("spID", str);
        hashMap.put("retry", "0");
        hashMap.put("cityCode", "");
        String recharge = this.a.recharge(hashMap);
        LogUtil.d("recharge result:" + recharge);
        if (TextUtils.isEmpty(recharge)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new BaseResponse(dVar.getStatus(), dVar.getMsg() + ":recharge");
        }
        return (BaseResponse) new Gson().fromJson(recharge, BaseResponse.class);
    }

    public BaseResponse a(String str, String str2, String str3) throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("issueCard checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new BaseResponse(b.getStatus(), b.getMsg());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("issuerID", str2);
        hashMap.put("orderNo", str3);
        hashMap.put("spID", str);
        hashMap.put("operation", "1");
        hashMap.put("cityCode", "");
        String issueCard = this.a.issueCard(hashMap);
        LogUtil.d("issueCard result:" + issueCard);
        if (TextUtils.isEmpty(issueCard)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new BaseResponse(dVar.getStatus(), dVar.getMsg() + ":issueCard");
        }
        return (BaseResponse) new Gson().fromJson(issueCard, BaseResponse.class);
    }

    public CardDataResponse b(String str, int i2) throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("queryTrafficCardInfo checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new CardDataResponse(b.getStatus(), b.getMsg());
        }
        String queryTrafficCardInfo = this.a.queryTrafficCardInfo(str, i2);
        LogUtil.d("queryTrafficCardInfo result:" + queryTrafficCardInfo);
        if (TextUtils.isEmpty(queryTrafficCardInfo)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new CardDataResponse(dVar.getStatus(), dVar.getMsg() + ":queryTrafficCardInfo");
        }
        return (CardDataResponse) new Gson().fromJson(queryTrafficCardInfo, CardDataResponse.class);
    }

    public BaseResponse b(String str) throws RemoteException {
        return a(str, "rechargeService");
    }

    public BaseResponse a(String str, String str2, String str3, String str4) throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("deleteCard checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new BaseResponse(b.getStatus(), b.getMsg());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("issuerID", str2);
        hashMap.put("spID", str);
        hashMap.put("signData", str3);
        hashMap.put(VerifyTracker.KEY_TIMESTAMP, str4);
        String deleteCard = this.a.deleteCard(hashMap);
        if (TextUtils.isEmpty(deleteCard)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new BaseResponse(dVar.getStatus(), dVar.getMsg() + ":deleteCard");
        }
        return (BaseResponse) new Gson().fromJson(deleteCard, BaseResponse.class);
    }

    public BaseResponse a(String str) throws RemoteException {
        return a(str, "issueCardService");
    }

    private BaseResponse a(String str, String str2) throws RemoteException {
        com.laser.open.nfc.c.d b = b();
        LogUtil.d("checkBizServiceStatus checkState:" + b);
        if (b.getStatus() != com.laser.open.nfc.c.d.BASE_STATUS_WALLET_BIND_SUCCESS.getStatus()) {
            return new BaseResponse(b.getStatus(), b.getMsg());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("issuerID", str);
        hashMap.put("serviceID", str2);
        String checkServiceStatus = this.a.checkServiceStatus(hashMap);
        LogUtil.d("checkBizServiceStatus result:" + checkServiceStatus);
        if (TextUtils.isEmpty(checkServiceStatus)) {
            com.laser.open.nfc.c.d dVar = com.laser.open.nfc.c.d.BASE_STATUS_WALLET_INTERFACE_IS_NOT_EXIST;
            return new CardDataResponse(dVar.getStatus(), dVar.getMsg() + ":checkBizServiceStatus");
        }
        return (BaseResponse) new Gson().fromJson(checkServiceStatus, BaseResponse.class);
    }
}
