package com.unionpay.tsmservice.mi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.tsmservice.mi.ITsmActivityCallback;
import com.unionpay.tsmservice.mi.ITsmCallback;
import com.unionpay.tsmservice.mi.ITsmService;
import com.unionpay.tsmservice.mi.SessionKeyReExchange;
import com.unionpay.tsmservice.mi.data.Constant;
import com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.mi.request.CancelPayRequestParams;
import com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.mi.request.CheckUserPhoneNumberRequestParams;
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
import com.unionpay.tsmservice.mi.request.QueryPromotionRequestParams;
import com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.mi.request.RequestParams;
import com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.mi.result.CheckPhoneNumberResult;
import com.unionpay.tsmservice.mi.result.QueryPromotionResult;
import com.unionpay.tsmservice.mi.utils.IUPJniInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class UPTsmAddon {
    private static UPTsmAddon a;
    private static CopyOnWriteArrayList<UPTsmConnectionListener> b;

    /* renamed from: c */
    private Context f18196c;
    private ServiceConnection d = null;

    /* renamed from: e */
    private ITsmService f18197e = null;

    /* renamed from: f */
    private boolean f18198f = false;

    /* renamed from: g */
    private HashMap<String, ITsmCallback> f18199g = new HashMap<>();

    /* renamed from: h */
    private HashMap<String, ITsmCallback> f18200h = new HashMap<>();

    /* renamed from: i */
    private HashMap<String, ITsmCallback> f18201i = new HashMap<>();

    /* renamed from: j */
    private HashMap<String, ITsmActivityCallback> f18202j = new HashMap<>();

    /* renamed from: k */
    private HashMap<String, ITsmCallback> f18203k = new HashMap<>();

    /* renamed from: l */
    private HashMap<String, ITsmCallback> f18204l = new HashMap<>();

    /* renamed from: m */
    private HashMap<String, ITsmCallback> f18205m = new HashMap<>();

    /* renamed from: n */
    private HashMap<String, ITsmCallback> f18206n = new HashMap<>();
    private HashMap<String, ITsmCallback> o = new HashMap<>();
    private HashMap<String, ITsmCallback> p = new HashMap<>();
    private HashMap<String, ITsmCallback> q = new HashMap<>();
    private HashMap<String, ITsmCallback> r = new HashMap<>();
    private HashMap<String, ITsmCallback> s = new HashMap<>();
    private HashMap<String, ITsmCallback> t = new HashMap<>();
    private HashMap<String, ITsmCallback> u = new HashMap<>();
    private HashMap<String, ITsmCallback> v = new HashMap<>();
    private int[] w;
    private final Handler.Callback x;
    private final Handler y;

    /* loaded from: classes11.dex */
    public interface UPTsmConnectionListener {
        void onTsmConnected();

        void onTsmDisconnected();
    }

    /* loaded from: classes11.dex */
    public class a extends ITsmActivityCallback.Stub {
        private int b = 1000;

        public a() {
            UPTsmAddon.this = r1;
        }

        @Override // com.unionpay.tsmservice.mi.ITsmActivityCallback
        public final void startActivity(String str, String str2, int i2, Bundle bundle) throws RemoteException {
            UPTsmAddon.a((ITsmActivityCallback) UPTsmAddon.b(UPTsmAddon.this, this.b).get(UPTsmAddon.c(UPTsmAddon.this)), str, str2, i2, bundle);
            UPTsmAddon.b(UPTsmAddon.this, this.b).remove(UPTsmAddon.c(UPTsmAddon.this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class b extends ITsmCallback.Stub {
        private int b;

        /* renamed from: c */
        private int f18207c;

        private b(int i2, int i3) {
            UPTsmAddon.this = r1;
            this.b = i2;
            this.f18207c = i3;
        }

        /* synthetic */ b(UPTsmAddon uPTsmAddon, int i2, int i3, byte b) {
            this(i2, i3);
        }

        @Override // com.unionpay.tsmservice.mi.ITsmCallback
        public final void onError(String str, String str2) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            UPTsmAddon.a((ITsmCallback) UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.f18207c)), bundle);
            UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.f18207c));
            if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty()) {
                UPTsmAddon.this.w[this.b] = 0;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:63:0x0127  */
        /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
        @Override // com.unionpay.tsmservice.mi.ITsmCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onResult(android.os.Bundle r9) throws android.os.RemoteException {
            /*
                Method dump skipped, instructions count: 348
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.b.onResult(android.os.Bundle):void");
        }
    }

    /* loaded from: classes11.dex */
    public class c extends ITsmCallback.Stub {
        private ITsmCallback b;

        /* renamed from: c */
        private int f18208c;

        private c(ITsmCallback iTsmCallback, int i2) {
            UPTsmAddon.this = r1;
            this.b = iTsmCallback;
            this.f18208c = i2;
        }

        /* synthetic */ c(UPTsmAddon uPTsmAddon, ITsmCallback iTsmCallback, int i2, byte b) {
            this(iTsmCallback, i2);
        }

        private void a(Bundle bundle) throws RemoteException {
            if (this.b != null) {
                bundle.putInt("interfaceId", this.f18208c);
                this.b.onResult(bundle);
            }
        }

        @Override // com.unionpay.tsmservice.mi.ITsmCallback
        public final void onError(String str, String str2) throws RemoteException {
            ITsmCallback iTsmCallback = this.b;
            if (iTsmCallback != null) {
                iTsmCallback.onError(str, str2);
            }
        }

        @Override // com.unionpay.tsmservice.mi.ITsmCallback
        public final void onResult(Bundle bundle) throws RemoteException {
            String string = bundle.getString("result");
            if (TextUtils.isEmpty(string)) {
                a(bundle);
            }
            bundle.putString("result", new String(Base64.decode(UPTsmAddon.d(string), 0), Constant.DEFAULT_CHARSET));
            a(bundle);
        }
    }

    /* loaded from: classes11.dex */
    private class d extends ITsmCallback.Stub {
        private ITsmCallback b;

        /* renamed from: c */
        private int f18209c;

        private d(ITsmCallback iTsmCallback, int i2) {
            UPTsmAddon.this = r1;
            this.f18209c = -1;
            this.b = iTsmCallback;
            this.f18209c = i2;
        }

        /* synthetic */ d(UPTsmAddon uPTsmAddon, ITsmCallback iTsmCallback, int i2, byte b) {
            this(iTsmCallback, i2);
        }

        @Override // com.unionpay.tsmservice.mi.ITsmCallback
        public final void onError(String str, String str2) throws RemoteException {
            ITsmCallback iTsmCallback = this.b;
            if (iTsmCallback != null) {
                iTsmCallback.onError(str, str2);
            }
        }

        @Override // com.unionpay.tsmservice.mi.ITsmCallback
        public final void onResult(Bundle bundle) throws RemoteException {
            String str;
            int i2 = this.f18209c;
            if (i2 == 1001) {
                try {
                    JSONObject jSONObject = new JSONObject(bundle.getString("result"));
                    Parcelable build = CheckPhoneNumberResult.Builder.newBuilder().phoneNumber(jSONObject.optString("phoneNumber")).verifyMsg(jSONObject.optString(Constant.KEY_RESULT_VERIFY_MSG)).verifyStatus(jSONObject.optString(Constant.KEY_RESULT_VERIFY_STATUS)).build();
                    bundle.putString("errorCode", "10000");
                    bundle.putParcelable("result", build);
                    bundle.remove("interfaceId");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    str = "1004900001";
                }
            } else if (i2 == 1002) {
                String string = bundle.getString("result");
                if (TextUtils.isEmpty(string)) {
                    str = "1005030047";
                    bundle.putString("errorCode", str);
                } else {
                    QueryPromotionResult queryPromotionResult = new QueryPromotionResult();
                    queryPromotionResult.setPromotions(string);
                    bundle.putString("errorCode", "10000");
                    bundle.putParcelable("result", queryPromotionResult);
                    bundle.remove("interfaceId");
                }
            }
            if ("10000".equalsIgnoreCase(bundle.getString("errorCode"))) {
                ITsmCallback iTsmCallback = this.b;
                if (iTsmCallback != null) {
                    iTsmCallback.onResult(bundle);
                    return;
                }
                return;
            }
            ITsmCallback iTsmCallback2 = this.b;
            if (iTsmCallback2 != null) {
                iTsmCallback2.onError(bundle.getString("errorCode"), bundle.getString("errorDesc"));
            }
        }
    }

    static {
        try {
            System.loadLibrary("uptsmaddonmi");
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
        a = null;
        b = null;
    }

    private UPTsmAddon(Context context) {
        this.f18196c = null;
        Handler.Callback callback = new Handler.Callback() { // from class: com.unionpay.tsmservice.mi.UPTsmAddon.1
            {
                UPTsmAddon.this = this;
            }

            @Override // android.os.Handler.Callback
            public final synchronized boolean handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 0) {
                    UPTsmAddon.a();
                    return true;
                } else if (i2 != 1) {
                    return false;
                } else {
                    UPTsmAddon.b();
                    return true;
                }
            }
        };
        this.x = callback;
        this.y = new Handler(Looper.getMainLooper(), callback);
        this.f18196c = context;
        this.w = new int[19];
        if (!a(context)) {
            throw new RuntimeException();
        }
    }

    private static int a(int i2, RequestParams requestParams, ITsmCallback iTsmCallback) throws RemoteException {
        return new SessionKeyReExchange(a, i2, requestParams, iTsmCallback).reExchangeKey();
    }

    private static int a(int i2, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i3, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) throws RemoteException {
        return new SessionKeyReExchange(a, i2, safetyKeyboardRequestParams, i3, onSafetyKeyboardCallback, context).reExchangeKey();
    }

    private static int a(RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        return new SessionKeyReExchange(a, 16, requestParams, iTsmCallback, iTsmProgressCallback).reExchangeKey();
    }

    private static int a(JSONObject jSONObject, JSONObject jSONObject2, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        return SessionKeyReExchange.a.a().a(a).b().a(jSONObject).b(jSONObject2).a(iTsmCallback).a(iTsmProgressCallback).c().reExchangeKey();
    }

    private static String a(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        String c2 = (marshall == null || marshall.length == 0) ? "" : c(Base64.encodeToString(marshall, 0));
        obtain.recycle();
        return c2;
    }

    static /* synthetic */ HashMap a(UPTsmAddon uPTsmAddon, int i2) {
        switch (i2) {
            case 0:
                return uPTsmAddon.f18199g;
            case 1:
                return uPTsmAddon.f18200h;
            case 2:
                return uPTsmAddon.f18201i;
            case 3:
            case 4:
            case 5:
            case 13:
            default:
                return null;
            case 6:
                return uPTsmAddon.f18203k;
            case 7:
                return uPTsmAddon.f18204l;
            case 8:
                return uPTsmAddon.f18205m;
            case 9:
                return uPTsmAddon.f18206n;
            case 10:
                return uPTsmAddon.o;
            case 11:
                return uPTsmAddon.p;
            case 12:
                return uPTsmAddon.q;
            case 14:
                return uPTsmAddon.r;
            case 15:
                return uPTsmAddon.s;
            case 16:
                return uPTsmAddon.t;
            case 17:
                return uPTsmAddon.u;
            case 18:
                return uPTsmAddon.v;
        }
    }

    static /* synthetic */ void a() {
        CopyOnWriteArrayList<UPTsmConnectionListener> copyOnWriteArrayList = b;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        Iterator<UPTsmConnectionListener> it = b.iterator();
        while (it.hasNext()) {
            UPTsmConnectionListener next = it.next();
            if (next != null) {
                next.onTsmConnected();
            }
        }
    }

    static /* synthetic */ void a(ITsmActivityCallback iTsmActivityCallback, String str, String str2, int i2, Bundle bundle) {
        if (iTsmActivityCallback != null) {
            try {
                iTsmActivityCallback.startActivity(str, str2, i2, bundle);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    static /* synthetic */ void a(ITsmCallback iTsmCallback, Bundle bundle) {
        if (iTsmCallback != null) {
            try {
                String string = bundle.getString("errorCode");
                if ("10000".equals(string)) {
                    iTsmCallback.onResult(bundle);
                } else {
                    iTsmCallback.onError(string, bundle.getString("errorDesc"));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static boolean a(Context context) {
        try {
            return IUPJniInterface.iJE(context);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        }
    }

    static /* synthetic */ HashMap b(UPTsmAddon uPTsmAddon, int i2) {
        if (i2 != 1000) {
            return null;
        }
        return uPTsmAddon.f18202j;
    }

    static /* synthetic */ void b() {
        CopyOnWriteArrayList<UPTsmConnectionListener> copyOnWriteArrayList = b;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        Iterator<UPTsmConnectionListener> it = b.iterator();
        while (it.hasNext()) {
            UPTsmConnectionListener next = it.next();
            if (next != null) {
                next.onTsmDisconnected();
            }
        }
    }

    private static boolean b(String str) {
        try {
            return IUPJniInterface.cSKV(str);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        }
    }

    static /* synthetic */ String c(UPTsmAddon uPTsmAddon) {
        return uPTsmAddon.f18196c.getPackageName();
    }

    private static String c(String str) {
        try {
            return IUPJniInterface.eMG(str);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private synchronized void c() {
        ServiceConnection serviceConnection = this.d;
        if (serviceConnection != null && this.f18198f) {
            this.f18196c.unbindService(serviceConnection);
            this.f18198f = false;
        }
    }

    public static String d(String str) {
        try {
            return IUPJniInterface.dMG(str);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private boolean d() {
        String f2 = f("com.unionpay.tsmservice.mi");
        return f2 != null && f2.compareTo(Constant.APK_VERSION_010002) >= 0;
    }

    private boolean e(String str) {
        String f2 = f("com.unionpay.tsmservice.mi");
        return f2 != null && f2.compareTo(str) >= 0;
    }

    private String f(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.f18196c.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    private static String g(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("jarVersionCode", 26);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static synchronized UPTsmAddon getInstance(Context context) {
        synchronized (UPTsmAddon.class) {
            if (context == null) {
                return null;
            }
            if (a == null) {
                a = new UPTsmAddon(context.getApplicationContext());
            }
            if (b == null) {
                b = new CopyOnWriteArrayList<>();
            }
            return a;
        }
    }

    private String h(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("packageName", this.f18196c.getPackageName());
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public synchronized int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.08")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 6;
            if (!b(this.f18196c.getPackageName())) {
                return a(6, acquireSEAppListRequestParams, iTsmCallback);
            }
            AcquireSEAppListRequestParams acquireSEAppListRequestParams2 = new AcquireSEAppListRequestParams();
            String str = "";
            if (acquireSEAppListRequestParams != null) {
                str = acquireSEAppListRequestParams.getReserve();
                Bundle params = acquireSEAppListRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", a(params));
                    acquireSEAppListRequestParams2.setParams(bundle);
                }
            }
            acquireSEAppListRequestParams2.setReserve(c(h(g(str))));
            this.f18203k.put(String.valueOf(this.w[6]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[6];
                iArr[6] = i4 + 1;
                int acquireSEAppList = iTsmService.acquireSEAppList(acquireSEAppListRequestParams2, new b(this, i3, i4, (byte) 0));
                if (acquireSEAppList != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18203k;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[6] - 1;
                    iArr2[6] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == acquireSEAppList) {
                    return a(6, acquireSEAppListRequestParams, iTsmCallback);
                }
                return acquireSEAppList;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i2;
        if (addCardToVendorPayRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!e(com.unionpay.tsmservice.data.Constant.SUPPORTED_MIN_APK_VERSION)) {
            i2 = -8;
        } else if (this.f18197e != null) {
            if (!b(this.f18196c.getPackageName())) {
                return a(addCardToVendorPayRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            AddCardToVendorPayRequestParams addCardToVendorPayRequestParams2 = new AddCardToVendorPayRequestParams();
            Bundle params = addCardToVendorPayRequestParams.getParams();
            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putString("encryptData", a(params));
                addCardToVendorPayRequestParams2.setParams(bundle);
            }
            addCardToVendorPayRequestParams2.setReserve(c(h(g(addCardToVendorPayRequestParams.getReserve()))));
            int i3 = 16;
            this.t.put(String.valueOf(this.w[16]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[16];
                iArr[16] = i4 + 1;
                int addCardToVendorPay = iTsmService.addCardToVendorPay(addCardToVendorPayRequestParams2, new b(this, i3, i4, (byte) 0), iTsmProgressCallback);
                if (addCardToVendorPay != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.t;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[16] - 1;
                    iArr2[16] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == addCardToVendorPay) {
                    return a(addCardToVendorPayRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                return addCardToVendorPay;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized void addConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.add(uPTsmConnectionListener);
        }
    }

    public boolean bind() {
        if (this.d == null) {
            this.d = new ServiceConnection() { // from class: com.unionpay.tsmservice.mi.UPTsmAddon.2
                {
                    UPTsmAddon.this = this;
                }

                @Override // android.content.ServiceConnection
                public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    UPTsmAddon.this.f18198f = true;
                    UPTsmAddon.this.f18197e = ITsmService.Stub.asInterface(iBinder);
                    UPTsmAddon.this.y.sendEmptyMessage(0);
                }

                @Override // android.content.ServiceConnection
                public final synchronized void onServiceDisconnected(ComponentName componentName) {
                    UPTsmAddon.this.f18198f = false;
                    UPTsmAddon.this.f18197e = null;
                    UPTsmAddon.this.y.sendEmptyMessage(1);
                }
            };
        }
        if (this.f18198f) {
            return true;
        }
        Intent intent = new Intent("com.unionpay.tsmservice.mi.UPTsmService");
        intent.setPackage("com.unionpay.tsmservice.mi");
        return this.f18196c.bindService(intent, this.d, 1);
    }

    public synchronized int cancelPay() throws RemoteException {
        int i2;
        if (!e("01.00.08")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            if (!b(this.f18196c.getPackageName())) {
                return a(13, (SafetyKeyboardRequestParams) null, 0, (OnSafetyKeyboardCallback) null, (Context) null);
            }
            CancelPayRequestParams cancelPayRequestParams = new CancelPayRequestParams();
            cancelPayRequestParams.setReserve(c(h(g(""))));
            try {
                int cancelPay = this.f18197e.cancelPay(cancelPayRequestParams);
                if (-2 == cancelPay) {
                    return a(13, (SafetyKeyboardRequestParams) null, 0, (OnSafetyKeyboardCallback) null, (Context) null);
                }
                return cancelPay;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.08")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 7;
            if (!b(this.f18196c.getPackageName())) {
                return a(7, cardListStatusChangedRequestParams, iTsmCallback);
            }
            CardListStatusChangedRequestParams cardListStatusChangedRequestParams2 = new CardListStatusChangedRequestParams();
            cardListStatusChangedRequestParams2.setReserve(c(h(g(cardListStatusChangedRequestParams != null ? cardListStatusChangedRequestParams.getReserve() : ""))));
            this.f18204l.put(String.valueOf(this.w[7]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[7];
                iArr[7] = i4 + 1;
                int cardListStatusChanged = iTsmService.cardListStatusChanged(cardListStatusChangedRequestParams2, new b(this, i3, i4, (byte) 0));
                if (cardListStatusChanged != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18204l;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[7] - 1;
                    iArr2[7] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == cardListStatusChanged) {
                    return a(7, cardListStatusChangedRequestParams, iTsmCallback);
                }
                return cardListStatusChanged;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int checkUserPhoneNumber(CheckUserPhoneNumberRequestParams checkUserPhoneNumberRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (checkUserPhoneNumberRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        if (TextUtils.isEmpty(checkUserPhoneNumberRequestParams.getPhoneNumber())) {
            return -3;
        }
        String reserve = checkUserPhoneNumberRequestParams.getReserve();
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(reserve)) {
            try {
                jSONObject = new JSONObject(reserve);
            } catch (JSONException e2) {
                e2.printStackTrace();
                return -3;
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            int i2 = 1001;
            jSONObject2.put("interfaceId", 1001);
            jSONObject2.put("phoneNumber", checkUserPhoneNumberRequestParams.getPhoneNumber());
            return commonInterface(jSONObject2, jSONObject, new d(this, iTsmCallback, i2, (byte) 0), null);
        } catch (JSONException e3) {
            e3.printStackTrace();
            return -3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0052 A[Catch: all -> 0x006d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:54:0x000a, B:56:0x000e, B:58:0x001c, B:60:0x0022, B:61:0x0038, B:69:0x0052, B:64:0x0040, B:65:0x0048, B:66:0x0049, B:75:0x005b, B:76:0x0063, B:77:0x0064), top: B:91:0x000a, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0058 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int clearEncryptData(int r5) throws android.os.RemoteException {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 2000(0x7d0, float:2.803E-42)
            if (r5 < r0) goto L70
            r0 = 2002(0x7d2, float:2.805E-42)
            if (r5 <= r0) goto La
            goto L70
        La:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.f18197e     // Catch: java.lang.Throwable -> L6d
            if (r0 == 0) goto L6a
            android.content.Context r0 = r4.f18196c     // Catch: java.lang.Throwable -> L6d
            java.lang.String r0 = r0.getPackageName()     // Catch: java.lang.Throwable -> L6d
            boolean r0 = b(r0)     // Catch: java.lang.Throwable -> L6d
            r1 = 4
            r2 = 0
            if (r0 == 0) goto L64
            boolean r0 = r4.d()     // Catch: java.lang.Throwable -> L6d
            if (r0 == 0) goto L49
            com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams r0 = new com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams     // Catch: java.lang.Throwable -> L6d
            r0.<init>()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = ""
            java.lang.String r3 = g(r3)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = r4.h(r3)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r3 = c(r3)     // Catch: java.lang.Throwable -> L6d
            r0.setReserve(r3)     // Catch: java.lang.Throwable -> L6d
            com.unionpay.tsmservice.mi.ITsmService r3 = r4.f18197e     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            int r0 = r3.clearKeyboardEncryptData(r0, r5)     // Catch: java.lang.Exception -> L3f java.lang.Throwable -> L6d
            goto L4f
        L3f:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L6d
            r5.<init>()     // Catch: java.lang.Throwable -> L6d
            throw r5     // Catch: java.lang.Throwable -> L6d
        L49:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.f18197e     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L6d
            int r0 = r0.clearEncryptData(r5)     // Catch: java.lang.Exception -> L5a java.lang.Throwable -> L6d
        L4f:
            r3 = -2
            if (r3 != r0) goto L58
            int r5 = a(r1, r2, r5, r2, r2)     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r4)
            return r5
        L58:
            monitor-exit(r4)
            return r0
        L5a:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L6d
            r5.<init>()     // Catch: java.lang.Throwable -> L6d
            throw r5     // Catch: java.lang.Throwable -> L6d
        L64:
            int r5 = a(r1, r2, r5, r2, r2)     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r4)
            return r5
        L6a:
            r5 = -1
        L6b:
            monitor-exit(r4)
            return r5
        L6d:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L70:
            r5 = -3
            goto L6b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.clearEncryptData(int):int");
    }

    public synchronized int commonInterface(JSONObject jSONObject, JSONObject jSONObject2, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        if (jSONObject == null || iTsmCallback == null) {
            return -3;
        }
        int optInt = jSONObject.optInt("interfaceId", -1);
        if (optInt == -1) {
            return -3;
        }
        if (this.f18197e == null) {
            return -1;
        }
        if (e(Constant.APK_VERSION_010019)) {
            if (!b(this.f18196c.getPackageName())) {
                return a(jSONObject, jSONObject2, iTsmCallback, iTsmProgressCallback);
            }
            if (d()) {
                if (jSONObject2 != null) {
                    try {
                        jSONObject2.put("jarVersionCode", 26);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                if (jSONObject2 != null) {
                    try {
                        jSONObject2.put("packageName", this.f18196c.getPackageName());
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            int commonInterface = this.f18197e.commonInterface(c(jSONObject.toString()), c(jSONObject2.toString()), new c(this, iTsmCallback, optInt, (byte) 0), iTsmProgressCallback);
            if (commonInterface == -2) {
                return a(jSONObject, jSONObject2, iTsmCallback, iTsmProgressCallback);
            }
            return commonInterface;
        }
        return -8;
    }

    public synchronized int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (encryptDataRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        if (this.f18197e != null) {
            int i2 = 1;
            if (!b(this.f18196c.getPackageName())) {
                return a(1, encryptDataRequestParams, iTsmCallback);
            }
            EncryptDataRequestParams encryptDataRequestParams2 = new EncryptDataRequestParams();
            String reserve = encryptDataRequestParams.getReserve();
            if (d()) {
                reserve = h(g(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                encryptDataRequestParams2.setReserve(c(reserve));
            }
            ArrayList arrayList = (ArrayList) encryptDataRequestParams.getData();
            byte b2 = 0;
            if (arrayList != null) {
                int size = arrayList.size();
                if (size == 0) {
                    return -3;
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i3 = 0; i3 < size; i3++) {
                    String str = (String) arrayList.get(i3);
                    if (!TextUtils.isEmpty(str)) {
                        arrayList2.add(c(str));
                    }
                }
                encryptDataRequestParams2.setData(arrayList2);
            }
            this.f18200h.put(String.valueOf(this.w[1]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[1];
                iArr[1] = i4 + 1;
                int encryptData = iTsmService.encryptData(encryptDataRequestParams2, new b(this, i2, i4, b2));
                if (encryptData != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18200h;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[1] - 1;
                    iArr2[1] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == encryptData) {
                    return a(1, encryptDataRequestParams, iTsmCallback);
                }
                return encryptData;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        }
        return -1;
    }

    public int exchangeKey(String str, String[] strArr) throws RemoteException {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return -3;
        }
        ITsmService iTsmService = this.f18197e;
        if (iTsmService != null) {
            try {
                return iTsmService.exchangeKey(str, strArr);
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        }
        return -1;
    }

    public Context getContext() {
        return this.f18196c;
    }

    public synchronized int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (iTsmCallback == null || getEncryptDataRequestParams == null) {
            return -3;
        }
        int type = getEncryptDataRequestParams.getType();
        String pan = getEncryptDataRequestParams.getPan();
        if (type >= 2000 && type <= 2001) {
            if (type == 2000 && TextUtils.isEmpty(pan)) {
                return -3;
            }
            if (this.f18197e != null) {
                int i2 = 2;
                if (!b(this.f18196c.getPackageName())) {
                    return a(2, getEncryptDataRequestParams, iTsmCallback);
                }
                GetEncryptDataRequestParams getEncryptDataRequestParams2 = new GetEncryptDataRequestParams();
                if (type == 2000) {
                    getEncryptDataRequestParams2.setPan(c(pan));
                }
                getEncryptDataRequestParams2.setType(type);
                String reserve = getEncryptDataRequestParams.getReserve();
                if (d()) {
                    reserve = h(g(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    getEncryptDataRequestParams2.setReserve(c(reserve));
                }
                this.f18201i.put(String.valueOf(this.w[2]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18197e;
                    int[] iArr = this.w;
                    int i3 = iArr[2];
                    iArr[2] = i3 + 1;
                    int encryptData = iTsmService.getEncryptData(getEncryptDataRequestParams2, new b(this, i2, i3, (byte) 0));
                    if (encryptData != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.f18201i;
                        int[] iArr2 = this.w;
                        int i4 = iArr2[2] - 1;
                        iArr2[2] = i4;
                        hashMap.remove(String.valueOf(i4));
                    }
                    if (-2 == encryptData) {
                        return a(2, getEncryptDataRequestParams, iTsmCallback);
                    }
                    return encryptData;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            return -1;
        }
        return -3;
    }

    public synchronized int getListenerCount() {
        CopyOnWriteArrayList<UPTsmConnectionListener> copyOnWriteArrayList = b;
        if (copyOnWriteArrayList != null) {
            return copyOnWriteArrayList.size();
        }
        return 0;
    }

    public synchronized int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e(com.unionpay.tsmservice.data.Constant.APK_VERSION_010017)) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 18;
            if (!b(this.f18196c.getPackageName())) {
                return a(18, getMessageDetailsRequestParams, iTsmCallback);
            }
            GetMessageDetailsRequestParams getMessageDetailsRequestParams2 = new GetMessageDetailsRequestParams();
            String str = "";
            if (getMessageDetailsRequestParams != null) {
                str = getMessageDetailsRequestParams.getReserve();
                Bundle params = getMessageDetailsRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", a(params));
                    getMessageDetailsRequestParams2.setParams(bundle);
                }
            }
            getMessageDetailsRequestParams2.setReserve(c(h(g(str))));
            this.v.put(String.valueOf(this.w[18]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[18];
                iArr[18] = i4 + 1;
                int messageDetails = iTsmService.getMessageDetails(getMessageDetailsRequestParams2, new b(this, i3, i4, (byte) 0));
                if (messageDetails != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.v;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[18] - 1;
                    iArr2[18] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == messageDetails) {
                    return a(18, getMessageDetailsRequestParams, iTsmCallback);
                }
                return messageDetails;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public int getPubKey(int i2, String[] strArr) throws RemoteException {
        if (strArr == null || strArr.length == 0) {
            return -3;
        }
        ITsmService iTsmService = this.f18197e;
        if (iTsmService != null) {
            try {
                return iTsmService.getPubKey(i2, strArr);
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        }
        return -1;
    }

    public synchronized int getSeId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.10")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 15;
            if (!b(this.f18196c.getPackageName())) {
                return a(15, getSeIdRequestParams, iTsmCallback);
            }
            GetSeIdRequestParams getSeIdRequestParams2 = new GetSeIdRequestParams();
            getSeIdRequestParams2.setReserve(c(h(g(getSeIdRequestParams != null ? getSeIdRequestParams.getReserve() : ""))));
            this.s.put(String.valueOf(this.w[15]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[15];
                iArr[15] = i4 + 1;
                int sEId = iTsmService.getSEId(getSeIdRequestParams2, new b(this, i3, i4, (byte) 0));
                if (sEId != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.s;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[15] - 1;
                    iArr2[15] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == sEId) {
                    return a(15, getSeIdRequestParams, iTsmCallback);
                }
                return sEId;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e(com.unionpay.tsmservice.data.Constant.APK_VERSION_010017)) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 17;
            if (!b(this.f18196c.getPackageName())) {
                return a(17, getTransactionDetailsRequestParams, iTsmCallback);
            }
            GetTransactionDetailsRequestParams getTransactionDetailsRequestParams2 = new GetTransactionDetailsRequestParams();
            String str = "";
            if (getTransactionDetailsRequestParams != null) {
                str = getTransactionDetailsRequestParams.getReserve();
                Bundle params = getTransactionDetailsRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", a(params));
                    getTransactionDetailsRequestParams2.setParams(bundle);
                }
            }
            getTransactionDetailsRequestParams2.setReserve(c(h(g(str))));
            this.u.put(String.valueOf(this.w[17]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[17];
                iArr[17] = i4 + 1;
                int transactionDetails = iTsmService.getTransactionDetails(getTransactionDetailsRequestParams2, new b(this, i3, i4, (byte) 0));
                if (transactionDetails != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.u;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[17] - 1;
                    iArr2[17] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == transactionDetails) {
                    return a(17, getTransactionDetailsRequestParams, iTsmCallback);
                }
                return transactionDetails;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.08")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 9;
            if (!b(this.f18196c.getPackageName())) {
                return a(9, getVendorPayStatusRequestParams, iTsmCallback);
            }
            GetVendorPayStatusRequestParams getVendorPayStatusRequestParams2 = new GetVendorPayStatusRequestParams();
            getVendorPayStatusRequestParams2.setReserve(c(h(g(getVendorPayStatusRequestParams != null ? getVendorPayStatusRequestParams.getReserve() : ""))));
            this.f18206n.put(String.valueOf(this.w[9]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[9];
                iArr[9] = i4 + 1;
                int vendorPayStatus = iTsmService.getVendorPayStatus(getVendorPayStatusRequestParams2, new b(this, i3, i4, (byte) 0));
                if (vendorPayStatus != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18206n;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[9] - 1;
                    iArr2[9] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == vendorPayStatus) {
                    return a(9, getVendorPayStatusRequestParams, iTsmCallback);
                }
                return vendorPayStatus;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int getVendorPayStatusForBankApp(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.09")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 14;
            if (!b(this.f18196c.getPackageName())) {
                return a(14, getVendorPayStatusRequestParams, iTsmCallback);
            }
            GetVendorPayStatusRequestParams getVendorPayStatusRequestParams2 = new GetVendorPayStatusRequestParams();
            getVendorPayStatusRequestParams2.setReserve(c(h(g(getVendorPayStatusRequestParams != null ? getVendorPayStatusRequestParams.getReserve() : ""))));
            this.r.put(String.valueOf(this.w[14]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[14];
                iArr[14] = i4 + 1;
                int vendorPayStatusForBankApp = iTsmService.getVendorPayStatusForBankApp(getVendorPayStatusRequestParams2, new b(this, i3, i4, (byte) 0));
                if (vendorPayStatusForBankApp != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.r;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[14] - 1;
                    iArr2[14] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == vendorPayStatusForBankApp) {
                    return a(14, getVendorPayStatusRequestParams, iTsmCallback);
                }
                return vendorPayStatusForBankApp;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x004a A[Catch: all -> 0x0065, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:43:0x0001, B:45:0x0005, B:47:0x0014, B:49:0x001a, B:50:0x0030, B:58:0x004a, B:53:0x0038, B:54:0x0040, B:55:0x0041, B:64:0x0053, B:65:0x005b, B:66:0x005c), top: B:75:0x0001, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0050 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int hideKeyboard() throws android.os.RemoteException {
        /*
            r5 = this;
            monitor-enter(r5)
            com.unionpay.tsmservice.mi.ITsmService r0 = r5.f18197e     // Catch: java.lang.Throwable -> L65
            if (r0 == 0) goto L62
            android.content.Context r0 = r5.f18196c     // Catch: java.lang.Throwable -> L65
            java.lang.String r0 = r0.getPackageName()     // Catch: java.lang.Throwable -> L65
            boolean r0 = b(r0)     // Catch: java.lang.Throwable -> L65
            r1 = 0
            r2 = 5
            r3 = 0
            if (r0 == 0) goto L5c
            boolean r0 = r5.d()     // Catch: java.lang.Throwable -> L65
            if (r0 == 0) goto L41
            com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams r0 = new com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams     // Catch: java.lang.Throwable -> L65
            r0.<init>()     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = ""
            java.lang.String r4 = g(r4)     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = r5.h(r4)     // Catch: java.lang.Throwable -> L65
            java.lang.String r4 = c(r4)     // Catch: java.lang.Throwable -> L65
            r0.setReserve(r4)     // Catch: java.lang.Throwable -> L65
            com.unionpay.tsmservice.mi.ITsmService r4 = r5.f18197e     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L65
            int r0 = r4.hideSafetyKeyboard(r0)     // Catch: java.lang.Exception -> L37 java.lang.Throwable -> L65
            goto L47
        L37:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L65
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L65
            r0.<init>()     // Catch: java.lang.Throwable -> L65
            throw r0     // Catch: java.lang.Throwable -> L65
        L41:
            com.unionpay.tsmservice.mi.ITsmService r0 = r5.f18197e     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L65
            int r0 = r0.hideKeyboard()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L65
        L47:
            r4 = -2
            if (r4 != r0) goto L50
            int r0 = a(r2, r3, r1, r3, r3)     // Catch: java.lang.Throwable -> L65
            monitor-exit(r5)
            return r0
        L50:
            monitor-exit(r5)
            return r0
        L52:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L65
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch: java.lang.Throwable -> L65
            r0.<init>()     // Catch: java.lang.Throwable -> L65
            throw r0     // Catch: java.lang.Throwable -> L65
        L5c:
            int r0 = a(r2, r3, r1, r3, r3)     // Catch: java.lang.Throwable -> L65
            monitor-exit(r5)
            return r0
        L62:
            r0 = -1
            monitor-exit(r5)
            return r0
        L65:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.hideKeyboard():int");
    }

    public synchronized int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (this.f18197e != null) {
            byte b2 = 0;
            if (!b(this.f18196c.getPackageName())) {
                return a(0, initRequestParams, iTsmCallback);
            }
            InitRequestParams initRequestParams2 = new InitRequestParams();
            String str = "";
            if (initRequestParams != null) {
                str = initRequestParams.getReserve();
                String signature = initRequestParams.getSignature();
                if (!TextUtils.isEmpty(signature)) {
                    initRequestParams2.setSignature(c(signature));
                }
            }
            if (d()) {
                str = h(g(str));
            }
            if (!TextUtils.isEmpty(str)) {
                initRequestParams2.setReserve(c(str));
            }
            this.f18199g.put(String.valueOf(this.w[0]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i3 = iArr[0];
                iArr[0] = i3 + 1;
                int init = iTsmService.init(initRequestParams2, new b(this, b2, i3, b2));
                if (init != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18199g;
                    int[] iArr2 = this.w;
                    int i4 = iArr2[0] - 1;
                    iArr2[0] = i4;
                    hashMap.remove(String.valueOf(i4));
                }
                if (-2 == init) {
                    return a(0, initRequestParams, iTsmCallback);
                }
                return init;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public boolean isConnected() {
        return this.f18198f;
    }

    public synchronized int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (onlinePaymentVerifyRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.08")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 10;
            if (!b(this.f18196c.getPackageName())) {
                return a(10, onlinePaymentVerifyRequestParams, iTsmCallback);
            }
            OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams2 = new OnlinePaymentVerifyRequestParams();
            Bundle params = onlinePaymentVerifyRequestParams.getParams();
            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putString("encryptData", a(params));
                onlinePaymentVerifyRequestParams2.setParams(bundle);
            }
            onlinePaymentVerifyRequestParams2.setReserve(c(h(g(onlinePaymentVerifyRequestParams.getReserve()))));
            this.o.put(String.valueOf(this.w[10]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[10];
                iArr[10] = i4 + 1;
                int onlinePaymentVerify = iTsmService.onlinePaymentVerify(onlinePaymentVerifyRequestParams2, new b(this, i3, i4, (byte) 0));
                if (onlinePaymentVerify != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.o;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[10] - 1;
                    iArr2[10] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == onlinePaymentVerify) {
                    return a(10, onlinePaymentVerifyRequestParams, iTsmCallback);
                }
                return onlinePaymentVerify;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int payResultNotify(PayResultNotifyRequestParams payResultNotifyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (payResultNotifyRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.08")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 12;
            if (!b(this.f18196c.getPackageName())) {
                return a(12, payResultNotifyRequestParams, iTsmCallback);
            }
            PayResultNotifyRequestParams payResultNotifyRequestParams2 = new PayResultNotifyRequestParams();
            Bundle params = payResultNotifyRequestParams.getParams();
            if (params != null) {
                Bundle bundle = new Bundle();
                bundle.putString("encryptData", a(params));
                payResultNotifyRequestParams2.setParams(bundle);
            }
            payResultNotifyRequestParams2.setReserve(c(h(g(payResultNotifyRequestParams.getReserve()))));
            this.q.put(String.valueOf(this.w[12]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[12];
                iArr[12] = i4 + 1;
                int payResultNotify = iTsmService.payResultNotify(payResultNotifyRequestParams2, new b(this, i3, i4, (byte) 0));
                if (payResultNotify != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.q;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[12] - 1;
                    iArr2[12] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == payResultNotify) {
                    return a(12, payResultNotifyRequestParams, iTsmCallback);
                }
                return payResultNotify;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int pinRequest(PinRequestRequestParams pinRequestRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.08")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 11;
            if (!b(this.f18196c.getPackageName())) {
                return a(11, pinRequestRequestParams, iTsmCallback);
            }
            PinRequestRequestParams pinRequestRequestParams2 = new PinRequestRequestParams();
            String str = "";
            if (pinRequestRequestParams != null) {
                str = pinRequestRequestParams.getReserve();
                Bundle params = pinRequestRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", a(params));
                    pinRequestRequestParams2.setParams(bundle);
                }
            }
            pinRequestRequestParams2.setReserve(c(h(g(str))));
            this.p.put(String.valueOf(this.w[11]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[11];
                iArr[11] = i4 + 1;
                int pinRequest = iTsmService.pinRequest(pinRequestRequestParams2, new b(this, i3, i4, (byte) 0));
                if (pinRequest != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.p;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[11] - 1;
                    iArr2[11] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == pinRequest) {
                    return a(11, pinRequestRequestParams, iTsmCallback);
                }
                return pinRequest;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int queryPromotion(QueryPromotionRequestParams queryPromotionRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        if (queryPromotionRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        Bundle params = queryPromotionRequestParams.getParams();
        if (params == null) {
            return -3;
        }
        String string = params.getString("token");
        String string2 = params.getString(Constant.KEY_QUERY_ID);
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            String reserve = queryPromotionRequestParams.getReserve();
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(reserve)) {
                try {
                    jSONObject = new JSONObject(reserve);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return -3;
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                int i2 = 1002;
                jSONObject2.put("interfaceId", 1002);
                jSONObject2.put("token", string);
                jSONObject2.put(Constant.KEY_QUERY_ID, string2);
                return commonInterface(jSONObject2, jSONObject, new d(this, iTsmCallback, i2, (byte) 0), null);
            } catch (JSONException e3) {
                e3.printStackTrace();
                return -3;
            }
        }
        return -3;
    }

    public synchronized int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!e("01.00.07")) {
            i2 = -8;
        } else if (this.f18197e != null) {
            int i3 = 8;
            if (!b(this.f18196c.getPackageName())) {
                return a(8, queryVendorPayStatusRequestParams, iTsmCallback);
            }
            QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams2 = new QueryVendorPayStatusRequestParams();
            queryVendorPayStatusRequestParams2.setReserve(c(h(g(queryVendorPayStatusRequestParams != null ? queryVendorPayStatusRequestParams.getReserve() : ""))));
            this.f18205m.put(String.valueOf(this.w[8]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18197e;
                int[] iArr = this.w;
                int i4 = iArr[8];
                iArr[8] = i4 + 1;
                int queryVendorPayStatus = iTsmService.queryVendorPayStatus(queryVendorPayStatusRequestParams2, new b(this, i3, i4, (byte) 0));
                if (queryVendorPayStatus != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18205m;
                    int[] iArr2 = this.w;
                    int i5 = iArr2[8] - 1;
                    iArr2[8] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == queryVendorPayStatus) {
                    return a(8, queryVendorPayStatusRequestParams, iTsmCallback);
                }
                return queryVendorPayStatus;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized void removeConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.remove(uPTsmConnectionListener);
        }
    }

    public synchronized int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams) throws RemoteException {
        int i2;
        if (safetyKeyboardRequestParams == null) {
            i2 = -3;
        } else if (this.f18197e != null) {
            if (!b(this.f18196c.getPackageName())) {
                return a(3, safetyKeyboardRequestParams, (ITsmCallback) null);
            }
            String reserve = safetyKeyboardRequestParams.getReserve();
            if (d()) {
                reserve = h(g(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                safetyKeyboardRequestParams.setReserve(c(reserve));
            }
            try {
                int safetyKeyboardBitmap = this.f18197e.setSafetyKeyboardBitmap(safetyKeyboardRequestParams);
                if (-2 == safetyKeyboardBitmap) {
                    return a(3, safetyKeyboardRequestParams, (ITsmCallback) null);
                }
                return safetyKeyboardBitmap;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i2, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) throws RemoteException {
        int i3;
        if (safetyKeyboardRequestParams == null || i2 < 2000 || i2 > 2002) {
            i3 = -3;
        } else if (this.f18197e != null) {
            if (!b(this.f18196c.getPackageName())) {
                return a(1000, safetyKeyboardRequestParams, i2, onSafetyKeyboardCallback, context);
            }
            this.f18202j.put(this.f18196c.getPackageName(), new com.unionpay.tsmservice.mi.a(context));
            String reserve = safetyKeyboardRequestParams.getReserve();
            if (d()) {
                reserve = h(g(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                safetyKeyboardRequestParams.setReserve(c(reserve));
            }
            try {
                int showSafetyKeyboard = this.f18197e.showSafetyKeyboard(safetyKeyboardRequestParams, i2, onSafetyKeyboardCallback, new a());
                if (showSafetyKeyboard != 0) {
                    this.f18202j.remove(this.f18196c.getPackageName());
                }
                if (-2 == showSafetyKeyboard) {
                    return a(1000, safetyKeyboardRequestParams, i2, onSafetyKeyboardCallback, context);
                }
                return showSafetyKeyboard;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i3 = -1;
        }
        return i3;
    }

    public void unbind() {
        c();
    }
}
