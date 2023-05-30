package com.unionpay.tsmservice.mi;

import android.content.Context;
import android.os.RemoteException;
import com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.mi.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.mi.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.mi.request.InitRequestParams;
import com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams;
import com.unionpay.tsmservice.mi.request.PinRequestRequestParams;
import com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.mi.request.RequestParams;
import com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.mi.utils.IUPJniInterface;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class SessionKeyReExchange {
    private UPTsmAddon a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private RequestParams f18180c;
    private ITsmCallback d;

    /* renamed from: e  reason: collision with root package name */
    private ITsmProgressCallback f18181e;

    /* renamed from: f  reason: collision with root package name */
    private int f18182f;

    /* renamed from: g  reason: collision with root package name */
    private JSONObject f18183g;

    /* renamed from: h  reason: collision with root package name */
    private JSONObject f18184h;

    /* renamed from: i  reason: collision with root package name */
    private OnSafetyKeyboardCallback f18185i;

    /* renamed from: j  reason: collision with root package name */
    private Context f18186j;

    /* renamed from: k  reason: collision with root package name */
    private int f18187k;

    /* loaded from: classes11.dex */
    public static final class a {
        private UPTsmAddon a;

        /* renamed from: c  reason: collision with root package name */
        private RequestParams f18188c;
        private ITsmCallback d;

        /* renamed from: e  reason: collision with root package name */
        private ITsmProgressCallback f18189e;

        /* renamed from: g  reason: collision with root package name */
        private OnSafetyKeyboardCallback f18191g;

        /* renamed from: h  reason: collision with root package name */
        private Context f18192h;

        /* renamed from: i  reason: collision with root package name */
        private int f18193i;

        /* renamed from: j  reason: collision with root package name */
        private JSONObject f18194j;

        /* renamed from: k  reason: collision with root package name */
        private JSONObject f18195k;
        private int b = -1;

        /* renamed from: f  reason: collision with root package name */
        private int f18190f = 1000;

        private a() {
        }

        public static a a() {
            return new a();
        }

        public final a a(ITsmCallback iTsmCallback) {
            this.d = iTsmCallback;
            return this;
        }

        public final a a(ITsmProgressCallback iTsmProgressCallback) {
            this.f18189e = iTsmProgressCallback;
            return this;
        }

        public final a a(UPTsmAddon uPTsmAddon) {
            this.a = uPTsmAddon;
            return this;
        }

        public final a a(JSONObject jSONObject) {
            this.f18194j = jSONObject;
            return this;
        }

        public final a b() {
            this.b = 111;
            return this;
        }

        public final a b(JSONObject jSONObject) {
            this.f18195k = jSONObject;
            return this;
        }

        public final SessionKeyReExchange c() {
            SessionKeyReExchange sessionKeyReExchange = new SessionKeyReExchange((byte) 0);
            sessionKeyReExchange.b = this.b;
            sessionKeyReExchange.f18186j = this.f18192h;
            sessionKeyReExchange.f18185i = this.f18191g;
            sessionKeyReExchange.f18181e = this.f18189e;
            sessionKeyReExchange.f18184h = this.f18195k;
            sessionKeyReExchange.f18183g = this.f18194j;
            sessionKeyReExchange.a = this.a;
            sessionKeyReExchange.f18182f = this.f18190f;
            sessionKeyReExchange.f18187k = this.f18193i;
            sessionKeyReExchange.f18180c = this.f18188c;
            sessionKeyReExchange.d = this.d;
            return sessionKeyReExchange;
        }
    }

    private SessionKeyReExchange() {
        this.b = -1;
        this.f18182f = 1000;
    }

    /* synthetic */ SessionKeyReExchange(byte b) {
        this();
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, ITsmCallback iTsmCallback) {
        this(uPTsmAddon, i2, null, iTsmCallback);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback) {
        this(uPTsmAddon, i2, requestParams, iTsmCallback, 1000);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback, int i3) {
        this.b = -1;
        this.f18182f = 1000;
        this.a = uPTsmAddon;
        this.b = i2;
        this.f18180c = requestParams;
        this.d = iTsmCallback;
        this.f18182f = i3;
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        this(uPTsmAddon, i2, requestParams, iTsmCallback, iTsmProgressCallback, 1000);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback, int i3) {
        this.b = -1;
        this.f18182f = 1000;
        this.a = uPTsmAddon;
        this.b = i2;
        this.f18180c = requestParams;
        this.d = iTsmCallback;
        this.f18181e = iTsmProgressCallback;
        this.f18182f = i3;
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i3, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        this.b = -1;
        this.f18182f = 1000;
        this.a = uPTsmAddon;
        this.b = i2;
        this.f18187k = i3;
        this.f18180c = safetyKeyboardRequestParams;
        this.f18185i = onSafetyKeyboardCallback;
        this.f18186j = context;
    }

    public int reExchangeKey() throws RemoteException {
        String[] strArr = new String[1];
        int pubKey = this.a.getPubKey(1000, strArr);
        if (pubKey != 0) {
            return pubKey;
        }
        int exchangeKey = this.a.exchangeKey(IUPJniInterface.rER(strArr[0], IUPJniInterface.mSK()), strArr);
        if (exchangeKey != 0) {
            return exchangeKey;
        }
        String dMG = IUPJniInterface.dMG(strArr[0]);
        IUPJniInterface.sSK(dMG);
        Context context = this.a.getContext();
        if (context != null) {
            IUPJniInterface.uSKT(context.getPackageName(), dMG);
        }
        int i2 = this.b;
        if (i2 != 111) {
            if (i2 == 1000) {
                return this.a.showSafetyKeyboard((SafetyKeyboardRequestParams) this.f18180c, this.f18187k, this.f18185i, this.f18186j);
            }
            switch (i2) {
                case 0:
                    return this.a.init((InitRequestParams) this.f18180c, this.d);
                case 1:
                    return this.a.encryptData((EncryptDataRequestParams) this.f18180c, this.d);
                case 2:
                    return this.a.getEncryptData((GetEncryptDataRequestParams) this.f18180c, this.d);
                case 3:
                    return this.a.setSafetyKeyboardBitmap((SafetyKeyboardRequestParams) this.f18180c);
                case 4:
                    return this.a.clearEncryptData(this.f18187k);
                case 5:
                    return this.a.hideKeyboard();
                case 6:
                    return this.a.acquireSEAppList((AcquireSEAppListRequestParams) this.f18180c, this.d);
                case 7:
                    return this.a.cardListStatusChanged((CardListStatusChangedRequestParams) this.f18180c, this.d);
                case 8:
                    return this.a.queryVendorPayStatus((QueryVendorPayStatusRequestParams) this.f18180c, this.d);
                case 9:
                    return this.a.getVendorPayStatus((GetVendorPayStatusRequestParams) this.f18180c, this.d);
                case 10:
                    return this.a.onlinePaymentVerify((OnlinePaymentVerifyRequestParams) this.f18180c, this.d);
                case 11:
                    return this.a.pinRequest((PinRequestRequestParams) this.f18180c, this.d);
                case 12:
                    return this.a.payResultNotify((PayResultNotifyRequestParams) this.f18180c, this.d);
                case 13:
                    return this.a.cancelPay();
                case 14:
                    return this.a.getVendorPayStatusForBankApp((GetVendorPayStatusRequestParams) this.f18180c, this.d);
                case 15:
                    return this.a.getSeId((GetSeIdRequestParams) this.f18180c, this.d);
                case 16:
                    return this.a.addCardToVendorPay((AddCardToVendorPayRequestParams) this.f18180c, this.d, this.f18181e);
                case 17:
                    return this.a.getTransactionDetails((GetTransactionDetailsRequestParams) this.f18180c, this.d);
                case 18:
                    return this.a.getMessageDetails((GetMessageDetailsRequestParams) this.f18180c, this.d);
                default:
                    return 0;
            }
        }
        return this.a.commonInterface(this.f18183g, this.f18184h, this.d, this.f18181e);
    }
}
