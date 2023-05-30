package com.unionpay.tsmservice;

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
import com.unionpay.tsmservice.ITsmActivityCallback;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.ITsmService;
import com.unionpay.tsmservice.data.Amount;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.request.ActivateVendorPayRequestParams;
import com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.request.AppDataUpdateRequestParams;
import com.unionpay.tsmservice.request.AppDeleteRequestParams;
import com.unionpay.tsmservice.request.AppDownloadApplyRequestParams;
import com.unionpay.tsmservice.request.AppDownloadRequestParams;
import com.unionpay.tsmservice.request.AppLockRequestParams;
import com.unionpay.tsmservice.request.AppUnlockRequestParams;
import com.unionpay.tsmservice.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams;
import com.unionpay.tsmservice.request.ClearEncryptDataRequestParams;
import com.unionpay.tsmservice.request.CloseChannelRequestParams;
import com.unionpay.tsmservice.request.ECashTopUpRequestParams;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.request.ExecuteCmdRequestParams;
import com.unionpay.tsmservice.request.GetAccountBalanceRequestParams;
import com.unionpay.tsmservice.request.GetAccountInfoRequestParams;
import com.unionpay.tsmservice.request.GetAppDetailRequestParams;
import com.unionpay.tsmservice.request.GetAppListRequestParams;
import com.unionpay.tsmservice.request.GetAppStatusRequestParams;
import com.unionpay.tsmservice.request.GetAssociatedAppRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoRequestParams;
import com.unionpay.tsmservice.request.GetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams;
import com.unionpay.tsmservice.request.GetSeAppListRequestParams;
import com.unionpay.tsmservice.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import com.unionpay.tsmservice.request.GetTransRecordRequestParams;
import com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.HideAppApplyRequestParams;
import com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.request.OpenChannelRequestParams;
import com.unionpay.tsmservice.request.PreDownloadRequestParams;
import com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.RequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.request.SendApduRequestParams;
import com.unionpay.tsmservice.request.SendCustomDataRequestParams;
import com.unionpay.tsmservice.request.SetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams;
import com.unionpay.tsmservice.request.UniteRequestParams;
import com.unionpay.tsmservice.result.AcquireSeAppListResult;
import com.unionpay.tsmservice.result.AddCardResult;
import com.unionpay.tsmservice.result.CheckSSamsungPayResult;
import com.unionpay.tsmservice.result.EncryptDataResult;
import com.unionpay.tsmservice.result.GetCardInfoBySpayResult;
import com.unionpay.tsmservice.result.GetEncryptDataResult;
import com.unionpay.tsmservice.result.GetSeAppListResult;
import com.unionpay.tsmservice.result.GetSeIdResult;
import com.unionpay.tsmservice.result.InitResult;
import com.unionpay.tsmservice.result.MessageDetailsResult;
import com.unionpay.tsmservice.result.OnlinePaymentVerifyResult;
import com.unionpay.tsmservice.result.OpenChannelResult;
import com.unionpay.tsmservice.result.SendApduResult;
import com.unionpay.tsmservice.result.SendCustomDataResult;
import com.unionpay.tsmservice.result.TransactionDetailsResult;
import com.unionpay.tsmservice.result.UniteResult;
import com.unionpay.tsmservice.result.VendorPayStatusResult;
import com.unionpay.tsmservice.utils.IUPJniInterface;
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
    private int[] Z;
    private final Handler.Callback aa;
    private final Handler ab;

    /* renamed from: c */
    private Context f18168c;
    private ServiceConnection d = null;

    /* renamed from: e */
    private ITsmService f18169e = null;

    /* renamed from: f */
    private boolean f18170f = false;

    /* renamed from: g */
    private int f18171g = 1;

    /* renamed from: h */
    private boolean f18172h = false;

    /* renamed from: i */
    private HashMap<String, ITsmCallback> f18173i = new HashMap<>();

    /* renamed from: j */
    private HashMap<String, ITsmCallback> f18174j = new HashMap<>();

    /* renamed from: k */
    private HashMap<String, ITsmCallback> f18175k = new HashMap<>();

    /* renamed from: l */
    private HashMap<String, ITsmCallback> f18176l = new HashMap<>();

    /* renamed from: m */
    private HashMap<String, ITsmCallback> f18177m = new HashMap<>();

    /* renamed from: n */
    private HashMap<String, ITsmCallback> f18178n = new HashMap<>();
    private HashMap<String, ITsmCallback> o = new HashMap<>();
    private HashMap<String, ITsmCallback> p = new HashMap<>();
    private HashMap<String, ITsmCallback> q = new HashMap<>();
    private HashMap<String, ITsmCallback> r = new HashMap<>();
    private HashMap<String, ITsmCallback> s = new HashMap<>();
    private HashMap<String, ITsmCallback> t = new HashMap<>();
    private HashMap<String, ITsmCallback> u = new HashMap<>();
    private HashMap<String, ITsmCallback> v = new HashMap<>();
    private HashMap<String, ITsmCallback> w = new HashMap<>();
    private HashMap<String, ITsmCallback> x = new HashMap<>();
    private HashMap<String, ITsmCallback> y = new HashMap<>();
    private HashMap<String, ITsmCallback> z = new HashMap<>();
    private HashMap<String, ITsmCallback> A = new HashMap<>();
    private HashMap<String, ITsmCallback> B = new HashMap<>();
    private HashMap<String, ITsmCallback> C = new HashMap<>();
    private HashMap<String, ITsmCallback> D = new HashMap<>();
    private HashMap<String, ITsmCallback> E = new HashMap<>();
    private HashMap<String, ITsmCallback> F = new HashMap<>();
    private HashMap<String, ITsmCallback> G = new HashMap<>();
    private HashMap<String, ITsmCallback> H = new HashMap<>();
    private HashMap<String, ITsmCallback> I = new HashMap<>();
    private HashMap<String, ITsmCallback> J = new HashMap<>();
    private HashMap<String, ITsmCallback> K = new HashMap<>();
    private HashMap<String, ITsmCallback> L = new HashMap<>();
    private HashMap<String, ITsmCallback> M = new HashMap<>();
    private HashMap<String, ITsmCallback> N = new HashMap<>();
    private HashMap<String, ITsmCallback> O = new HashMap<>();
    private HashMap<String, ITsmCallback> P = new HashMap<>();
    private HashMap<String, ITsmCallback> Q = new HashMap<>();
    private HashMap<String, ITsmCallback> R = new HashMap<>();
    private HashMap<String, ITsmCallback> S = new HashMap<>();
    private HashMap<String, ITsmCallback> T = new HashMap<>();
    private HashMap<String, ITsmCallback> U = new HashMap<>();
    private HashMap<String, ITsmCallback> V = new HashMap<>();
    private HashMap<String, ITsmCallback> W = new HashMap<>();
    private HashMap<String, ITsmCallback> X = new HashMap<>();
    private HashMap<String, ITsmActivityCallback> Y = new HashMap<>();

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

        @Override // com.unionpay.tsmservice.ITsmActivityCallback
        public final void startActivity(String str, String str2, int i2, Bundle bundle) throws RemoteException {
            UPTsmAddon.a((ITsmActivityCallback) UPTsmAddon.b(UPTsmAddon.this, this.b).get(UPTsmAddon.e(UPTsmAddon.this)), str, str2, i2, bundle);
            UPTsmAddon.b(UPTsmAddon.this, this.b).remove(UPTsmAddon.e(UPTsmAddon.this));
        }
    }

    /* loaded from: classes11.dex */
    public class b extends ITsmCallback.Stub {
        private int b;

        /* renamed from: c */
        private int f18179c;

        private b(int i2, int i3) {
            UPTsmAddon.this = r1;
            this.b = i2;
            this.f18179c = i3;
        }

        /* synthetic */ b(UPTsmAddon uPTsmAddon, int i2, int i3, byte b) {
            this(i2, i3);
        }

        @Override // com.unionpay.tsmservice.ITsmCallback
        public final void onError(String str, String str2) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            UPTsmAddon.a((ITsmCallback) UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.f18179c)), bundle);
            UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.f18179c));
            if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty()) {
                UPTsmAddon.this.Z[this.b] = 0;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:180:0x019c  */
        /* JADX WARN: Removed duplicated region for block: B:182:? A[RETURN, SYNTHETIC] */
        @Override // com.unionpay.tsmservice.ITsmCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void onResult(Bundle bundle) throws RemoteException {
            Parcelable parcelable;
            new Bundle();
            int i2 = this.b;
            Bundle bundle2 = new Bundle();
            Parcel obtain = Parcel.obtain();
            String string = bundle.getString("errorCode");
            String string2 = bundle.getString("result");
            bundle2.putString("errorCode", string);
            if (!TextUtils.isEmpty(string2)) {
                byte[] decode = Base64.decode(UPTsmAddon.this.c(string2), 0);
                if (decode != null && decode.length != 0) {
                    obtain.unmarshall(decode, 0, decode.length);
                    obtain.setDataPosition(0);
                }
                if (obtain.dataSize() == 0) {
                    bundle2.putString("errorCode", "010035");
                    bundle = bundle2;
                    obtain.recycle();
                    UPTsmAddon.a((ITsmCallback) UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.f18179c)), bundle);
                    UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.f18179c));
                    if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty()) {
                        UPTsmAddon.this.Z[this.b] = 0;
                        return;
                    }
                    return;
                }
            }
            if (i2 == 0) {
                parcelable = (InitResult) obtain.readParcelable(InitResult.class.getClassLoader());
            } else if (i2 == 3) {
                parcelable = (GetSeAppListResult) obtain.readParcelable(GetSeAppListResult.class.getClassLoader());
            } else if (i2 == 12) {
                parcelable = (GetSeIdResult) obtain.readParcelable(GetSeIdResult.class.getClassLoader());
            } else if (i2 == 20) {
                parcelable = (OpenChannelResult) obtain.readParcelable(OpenChannelResult.class.getClassLoader());
            } else if (i2 != 31) {
                if (i2 != 36) {
                    if (i2 == 22) {
                        parcelable = (SendApduResult) obtain.readParcelable(SendApduResult.class.getClassLoader());
                    } else if (i2 == 23) {
                        parcelable = (EncryptDataResult) obtain.readParcelable(EncryptDataResult.class.getClassLoader());
                    } else if (i2 == 28) {
                        parcelable = (GetCardInfoBySpayResult) obtain.readParcelable(GetCardInfoBySpayResult.class.getClassLoader());
                    } else if (i2 == 29) {
                        parcelable = (CheckSSamsungPayResult) obtain.readParcelable(CheckSSamsungPayResult.class.getClassLoader());
                    } else if (i2 == 38) {
                        parcelable = (AddCardResult) obtain.readParcelable(AddCardResult.class.getClassLoader());
                    } else if (i2 != 39) {
                        switch (i2) {
                            case 42:
                                parcelable = (AcquireSeAppListResult) obtain.readParcelable(AcquireSeAppListResult.class.getClassLoader());
                                break;
                            case 43:
                                parcelable = (TransactionDetailsResult) obtain.readParcelable(TransactionDetailsResult.class.getClassLoader());
                                break;
                            case 44:
                                parcelable = (MessageDetailsResult) obtain.readParcelable(MessageDetailsResult.class.getClassLoader());
                                break;
                            case 45:
                                parcelable = (SendCustomDataResult) obtain.readParcelable(SendCustomDataResult.class.getClassLoader());
                                break;
                            case 46:
                                parcelable = (UniteResult) obtain.readParcelable(UniteResult.class.getClassLoader());
                                break;
                        }
                        obtain.recycle();
                        UPTsmAddon.a((ITsmCallback) UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.f18179c)), bundle);
                        UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.f18179c));
                        if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty()) {
                        }
                    } else {
                        parcelable = (OnlinePaymentVerifyResult) obtain.readParcelable(OnlinePaymentVerifyResult.class.getClassLoader());
                    }
                }
                parcelable = (VendorPayStatusResult) obtain.readParcelable(VendorPayStatusResult.class.getClassLoader());
            } else {
                parcelable = (GetEncryptDataResult) obtain.readParcelable(GetEncryptDataResult.class.getClassLoader());
            }
            bundle2.putParcelable("result", parcelable);
            bundle = bundle2;
            obtain.recycle();
            UPTsmAddon.a((ITsmCallback) UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.f18179c)), bundle);
            UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.f18179c));
            if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty()) {
            }
        }
    }

    static {
        try {
            System.loadLibrary("uptsmaddon");
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
        a = null;
        b = null;
    }

    private UPTsmAddon(Context context) {
        this.f18168c = null;
        Handler.Callback callback = new Handler.Callback() { // from class: com.unionpay.tsmservice.UPTsmAddon.1
            {
                UPTsmAddon.this = this;
            }

            @Override // android.os.Handler.Callback
            public final synchronized boolean handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 0) {
                    UPTsmAddon.this.a();
                    return true;
                } else if (i2 != 1) {
                    return false;
                } else {
                    UPTsmAddon.this.b();
                    return true;
                }
            }
        };
        this.aa = callback;
        this.ab = new Handler(Looper.getMainLooper(), callback);
        this.f18168c = context;
        this.Z = new int[47];
        if (!a(context)) {
            throw new RuntimeException();
        }
    }

    private static int a(int i2, RequestParams requestParams, ITsmCallback iTsmCallback) throws RemoteException {
        return new SessionKeyReExchange(a, i2, requestParams, iTsmCallback).reExchangeKey();
    }

    private static int a(int i2, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        return new SessionKeyReExchange(a, i2, requestParams, iTsmCallback, iTsmProgressCallback).reExchangeKey();
    }

    private static int a(int i2, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i3, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) throws RemoteException {
        return new SessionKeyReExchange(a, i2, safetyKeyboardRequestParams, i3, onSafetyKeyboardCallback, context).reExchangeKey();
    }

    private String a(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        String b2 = (marshall == null || marshall.length == 0) ? "" : b(Base64.encodeToString(marshall, 0));
        obtain.recycle();
        return b2;
    }

    static /* synthetic */ HashMap a(UPTsmAddon uPTsmAddon, int i2) {
        switch (i2) {
            case 0:
                return uPTsmAddon.f18173i;
            case 1:
                return uPTsmAddon.f18174j;
            case 2:
                return uPTsmAddon.f18176l;
            case 3:
                return uPTsmAddon.f18175k;
            case 4:
                return uPTsmAddon.f18178n;
            case 5:
                return uPTsmAddon.f18177m;
            case 6:
                return uPTsmAddon.A;
            case 7:
                return uPTsmAddon.u;
            case 8:
                return uPTsmAddon.v;
            case 9:
                return uPTsmAddon.o;
            case 10:
                return uPTsmAddon.t;
            case 11:
                return uPTsmAddon.r;
            case 12:
                return uPTsmAddon.z;
            case 13:
                return uPTsmAddon.y;
            case 14:
                return uPTsmAddon.E;
            case 15:
                return uPTsmAddon.p;
            case 16:
                return uPTsmAddon.C;
            case 17:
                return uPTsmAddon.D;
            case 18:
                return uPTsmAddon.q;
            case 19:
                return uPTsmAddon.s;
            case 20:
                return uPTsmAddon.w;
            case 21:
                return uPTsmAddon.F;
            case 22:
                return uPTsmAddon.x;
            case 23:
                return uPTsmAddon.B;
            case 24:
                return uPTsmAddon.G;
            case 25:
                return uPTsmAddon.H;
            case 26:
            case 27:
            case 32:
            case 33:
            case 34:
            default:
                return null;
            case 28:
                return uPTsmAddon.I;
            case 29:
                return uPTsmAddon.J;
            case 30:
                return uPTsmAddon.K;
            case 31:
                return uPTsmAddon.L;
            case 35:
                return uPTsmAddon.M;
            case 36:
                return uPTsmAddon.N;
            case 37:
                return uPTsmAddon.O;
            case 38:
                return uPTsmAddon.P;
            case 39:
                return uPTsmAddon.Q;
            case 40:
                return uPTsmAddon.R;
            case 41:
                return uPTsmAddon.S;
            case 42:
                return uPTsmAddon.T;
            case 43:
                return uPTsmAddon.U;
            case 44:
                return uPTsmAddon.V;
            case 45:
                return uPTsmAddon.W;
            case 46:
                return uPTsmAddon.X;
        }
    }

    private static HashMap<String, String> a(HashMap<String, String> hashMap) {
        String str;
        if (hashMap == null) {
            return new HashMap<>();
        }
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (String str2 : hashMap.keySet()) {
            if (str2 != null && (str = hashMap.get(str2)) != null) {
                hashMap2.put(new String(str2), new String(str));
            }
        }
        return hashMap2;
    }

    public synchronized void a() {
        CopyOnWriteArrayList<UPTsmConnectionListener> copyOnWriteArrayList = b;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
            Iterator<UPTsmConnectionListener> it = b.iterator();
            while (it.hasNext()) {
                UPTsmConnectionListener next = it.next();
                if (next != null) {
                    next.onTsmConnected();
                }
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

    private static boolean a(String str) {
        try {
            return IUPJniInterface.cSKV(str);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private String b(String str) {
        try {
            return IUPJniInterface.eMG(str, this.f18171g);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "";
        }
    }

    static /* synthetic */ HashMap b(UPTsmAddon uPTsmAddon, int i2) {
        if (i2 != 1000) {
            return null;
        }
        return uPTsmAddon.Y;
    }

    public synchronized void b() {
        CopyOnWriteArrayList<UPTsmConnectionListener> copyOnWriteArrayList = b;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0) {
            Iterator<UPTsmConnectionListener> it = b.iterator();
            while (it.hasNext()) {
                UPTsmConnectionListener next = it.next();
                if (next != null) {
                    next.onTsmDisconnected();
                }
            }
        }
    }

    public String c(String str) {
        try {
            return IUPJniInterface.dMG(str, this.f18171g);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private boolean c() {
        String e2 = e("com.unionpay.tsmservice");
        if (e2 == null || e2.compareTo(Constant.SUPPORTED_MIN_APK_VERSION) < 0) {
            return false;
        }
        if (e2.compareTo(Constant.APK_VERSION_010018) >= 0) {
            this.f18171g = 1;
            this.f18172h = true;
            return true;
        } else if (e2.compareTo(Constant.APK_VERSION_010012) >= 0 && e2.compareTo(Constant.APK_VERSION_010016) <= 0) {
            this.f18171g = 2;
            this.f18172h = false;
            return true;
        } else if (e2.compareTo(Constant.APK_VERSION_010017) == 0 || e2.compareTo(Constant.SUPPORTED_MIN_APK_VERSION) == 0) {
            this.f18171g = 1;
            this.f18172h = false;
            return true;
        } else {
            return false;
        }
    }

    private boolean d(String str) {
        String e2 = e("com.unionpay.tsmservice");
        return e2 != null && e2.compareTo(str) >= 0;
    }

    static /* synthetic */ String e(UPTsmAddon uPTsmAddon) {
        return uPTsmAddon.f18168c.getPackageName();
    }

    private String e(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.f18168c.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    private static String f(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("jarVersionCode", 52);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private String g(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("packageName", this.f18168c.getPackageName());
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

    public synchronized int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.28")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 42;
                if (!a(this.f18168c.getPackageName())) {
                    return a(42, acquireSEAppListRequestParams, iTsmCallback);
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
                if (this.f18172h) {
                    str = g(f(str));
                }
                if (!TextUtils.isEmpty(str)) {
                    acquireSEAppListRequestParams2.setReserve(b(str));
                }
                this.T.put(String.valueOf(this.Z[42]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[42];
                    iArr[42] = i4 + 1;
                    int acquireSEAppList = iTsmService.acquireSEAppList(acquireSEAppListRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (acquireSEAppList != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.T;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[42] - 1;
                        iArr2[42] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == acquireSEAppList) {
                        return a(42, acquireSEAppListRequestParams, iTsmCallback);
                    }
                    return acquireSEAppList;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized int activateVendorPay(ActivateVendorPayRequestParams activateVendorPayRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.20")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 37;
                if (!a(this.f18168c.getPackageName())) {
                    return a(37, activateVendorPayRequestParams, iTsmCallback);
                }
                ActivateVendorPayRequestParams activateVendorPayRequestParams2 = new ActivateVendorPayRequestParams();
                String reserve = activateVendorPayRequestParams != null ? activateVendorPayRequestParams.getReserve() : "";
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    activateVendorPayRequestParams2.setReserve(b(reserve));
                }
                this.O.put(String.valueOf(this.Z[37]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[37];
                    iArr[37] = i4 + 1;
                    int activateVendorPay = iTsmService.activateVendorPay(activateVendorPayRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (activateVendorPay != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.O;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[37] - 1;
                        iArr2[37] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == activateVendorPay) {
                        return a(37, activateVendorPayRequestParams, iTsmCallback);
                    }
                    return activateVendorPay;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i2;
        if (addCardToVendorPayRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.20")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 38;
                if (!a(this.f18168c.getPackageName())) {
                    return a(38, addCardToVendorPayRequestParams, iTsmCallback);
                }
                AddCardToVendorPayRequestParams addCardToVendorPayRequestParams2 = new AddCardToVendorPayRequestParams();
                Bundle params = addCardToVendorPayRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", a(params));
                    addCardToVendorPayRequestParams2.setParams(bundle);
                }
                String reserve = addCardToVendorPayRequestParams.getReserve();
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    addCardToVendorPayRequestParams2.setReserve(b(reserve));
                }
                this.P.put(String.valueOf(this.Z[38]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[38];
                    iArr[38] = i4 + 1;
                    int addCardToVendorPay = iTsmService.addCardToVendorPay(addCardToVendorPayRequestParams2, new b(this, i3, i4, (byte) 0), iTsmProgressCallback);
                    if (addCardToVendorPay != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.P;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[38] - 1;
                        iArr2[38] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == addCardToVendorPay) {
                        return a(38, addCardToVendorPayRequestParams, iTsmCallback, iTsmProgressCallback);
                    }
                    return addCardToVendorPay;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized void addConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.add(uPTsmConnectionListener);
        }
    }

    @Deprecated
    public synchronized int appDataUpdate(AppDataUpdateRequestParams appDataUpdateRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i2;
        if (appDataUpdateRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 18;
            if (!a(this.f18168c.getPackageName())) {
                return a(18, appDataUpdateRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            AppDataUpdateRequestParams appDataUpdateRequestParams2 = new AppDataUpdateRequestParams();
            String reserve = appDataUpdateRequestParams.getReserve();
            AppID appID = appDataUpdateRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appDataUpdateRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appDataUpdateRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            try {
                int appDataUpdate = this.f18169e.appDataUpdate(appDataUpdateRequestParams2, new b(this, i3, this.Z[18], (byte) 0), iTsmProgressCallback);
                if (-2 == appDataUpdate) {
                    return a(18, appDataUpdateRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDataUpdate == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.q;
                    int[] iArr = this.Z;
                    int i4 = iArr[18];
                    iArr[18] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return appDataUpdate;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int appDelete(AppDeleteRequestParams appDeleteRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i2;
        if (appDeleteRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 17;
            if (!a(this.f18168c.getPackageName())) {
                return a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            AppDeleteRequestParams appDeleteRequestParams2 = new AppDeleteRequestParams();
            String reserve = appDeleteRequestParams.getReserve();
            AppID appID = appDeleteRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appDeleteRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appDeleteRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            HashMap hashMap = (HashMap) appDeleteRequestParams.getParams();
            byte b2 = 0;
            if (hashMap == null) {
                try {
                    int appDelete = this.f18169e.appDelete(appDeleteRequestParams2, new b(this, i3, this.Z[17], b2), iTsmProgressCallback);
                    if (-2 == appDelete) {
                        return a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
                    }
                    if (appDelete == 0) {
                        HashMap<String, ITsmCallback> hashMap2 = this.D;
                        int[] iArr = this.Z;
                        int i4 = iArr[17];
                        iArr[17] = i4 + 1;
                        hashMap2.put(String.valueOf(i4), iTsmCallback);
                    }
                    return appDelete;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            HashMap<String, String> a2 = a(hashMap);
            String str = a2.get(Constant.KEY_CARD_HOLDER_NAME);
            String str2 = a2.get(Constant.KEY_ID_TYPE);
            String str3 = a2.get(Constant.KEY_ID_NO);
            String str4 = a2.get(Constant.KEY_PAN);
            String str5 = a2.get("pin");
            String str6 = a2.get(Constant.KEY_EXPIRY_DATE);
            String str7 = a2.get(Constant.KEY_CVN2);
            String str8 = a2.get(Constant.KEY_PHONE_NUMBER);
            String str9 = a2.get(Constant.KEY_SMS_AUTH_CODE);
            String str10 = a2.get(Constant.KEY_BALANCE);
            String str11 = a2.get("cardType");
            if (!TextUtils.isEmpty(str)) {
                a2.put(Constant.KEY_CARD_HOLDER_NAME, b(str));
            }
            if (!TextUtils.isEmpty(str2)) {
                a2.put(Constant.KEY_ID_TYPE, b(str2));
            }
            if (!TextUtils.isEmpty(str3)) {
                a2.put(Constant.KEY_ID_NO, b(str3));
            }
            if (!TextUtils.isEmpty(str4)) {
                a2.put(Constant.KEY_PAN, b(str4));
            }
            if (!TextUtils.isEmpty(str5)) {
                a2.put("pin", str5);
            }
            if (!TextUtils.isEmpty(str6)) {
                a2.put(Constant.KEY_EXPIRY_DATE, b(str6));
            }
            if (!TextUtils.isEmpty(str7)) {
                a2.put(Constant.KEY_CVN2, b(str7));
            }
            if (!TextUtils.isEmpty(str8)) {
                a2.put(Constant.KEY_PHONE_NUMBER, b(str8));
            }
            if (!TextUtils.isEmpty(str9)) {
                a2.put(Constant.KEY_SMS_AUTH_CODE, b(str9));
            }
            if (!TextUtils.isEmpty(str10)) {
                a2.put(Constant.KEY_BALANCE, b(str10));
            }
            if (!TextUtils.isEmpty(str11)) {
                a2.put("cardType", b(str11));
            }
            appDeleteRequestParams2.setParams(a2);
            try {
                int appDelete2 = this.f18169e.appDelete(appDeleteRequestParams2, new b(this, 17, this.Z[17], (byte) 0), iTsmProgressCallback);
                if (-2 == appDelete2) {
                    return a(17, appDeleteRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDelete2 == 0) {
                    HashMap<String, ITsmCallback> hashMap3 = this.D;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[17];
                    iArr2[17] = i5 + 1;
                    hashMap3.put(String.valueOf(i5), iTsmCallback);
                }
                return appDelete2;
            } catch (Exception e3) {
                e3.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int appDownload(AppDownloadRequestParams appDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i2;
        if (appDownloadRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 16;
            if (!a(this.f18168c.getPackageName())) {
                return a(16, appDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            AppDownloadRequestParams appDownloadRequestParams2 = new AppDownloadRequestParams();
            String reserve = appDownloadRequestParams.getReserve();
            AppID appID = appDownloadRequestParams.getAppID();
            String appName = appDownloadRequestParams.getAppName();
            if (!TextUtils.isEmpty(reserve)) {
                appDownloadRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appDownloadRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            if (!TextUtils.isEmpty(appName)) {
                appDownloadRequestParams2.setAppName(b(appName));
            }
            try {
                int appDownload = this.f18169e.appDownload(appDownloadRequestParams2, new b(this, i3, this.Z[16], (byte) 0), iTsmProgressCallback);
                if (-2 == appDownload) {
                    return a(16, appDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                if (appDownload == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.C;
                    int[] iArr = this.Z;
                    int i4 = iArr[16];
                    iArr[16] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return appDownload;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int appDownloadApply(AppDownloadApplyRequestParams appDownloadApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (appDownloadApplyRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 15;
            if (!a(this.f18168c.getPackageName())) {
                return a(15, appDownloadApplyRequestParams, iTsmCallback);
            }
            AppDownloadApplyRequestParams appDownloadApplyRequestParams2 = new AppDownloadApplyRequestParams();
            String reserve = appDownloadApplyRequestParams.getReserve();
            AppID appID = appDownloadApplyRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appDownloadApplyRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appDownloadApplyRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            HashMap hashMap = (HashMap) appDownloadApplyRequestParams.getParams();
            byte b2 = 0;
            if (hashMap == null) {
                try {
                    int appDownloadApply = this.f18169e.appDownloadApply(appDownloadApplyRequestParams2, new b(this, i3, this.Z[15], b2));
                    if (-2 == appDownloadApply) {
                        return a(15, appDownloadApplyRequestParams, iTsmCallback);
                    }
                    if (appDownloadApply == 0) {
                        HashMap<String, ITsmCallback> hashMap2 = this.p;
                        int[] iArr = this.Z;
                        int i4 = iArr[15];
                        iArr[15] = i4 + 1;
                        hashMap2.put(String.valueOf(i4), iTsmCallback);
                    }
                    return appDownloadApply;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            HashMap<String, String> a2 = a(hashMap);
            String str = a2.get(Constant.KEY_ACCOUNT_LIMIT);
            String str2 = a2.get("accountType");
            String str3 = a2.get(Constant.KEY_CARD_HOLDER_NAME);
            String str4 = a2.get(Constant.KEY_ID_TYPE);
            String str5 = a2.get(Constant.KEY_ID_NO);
            String str6 = a2.get(Constant.KEY_PAN);
            String str7 = a2.get("pin");
            String str8 = a2.get(Constant.KEY_EXPIRY_DATE);
            String str9 = a2.get(Constant.KEY_CVN2);
            String str10 = a2.get(Constant.KEY_PHONE_NUMBER);
            String str11 = a2.get(Constant.KEY_SMS_AUTH_CODE);
            String str12 = a2.get("cardType");
            if (!TextUtils.isEmpty(str)) {
                a2.put(Constant.KEY_ACCOUNT_LIMIT, b(str));
            }
            if (!TextUtils.isEmpty(str2)) {
                a2.put("accountType", b(str2));
            }
            if (!TextUtils.isEmpty(str3)) {
                a2.put(Constant.KEY_CARD_HOLDER_NAME, b(str3));
            }
            if (!TextUtils.isEmpty(str4)) {
                a2.put(Constant.KEY_ID_TYPE, b(str4));
            }
            if (!TextUtils.isEmpty(str5)) {
                a2.put(Constant.KEY_ID_NO, b(str5));
            }
            if (!TextUtils.isEmpty(str6)) {
                a2.put(Constant.KEY_PAN, b(str6));
            }
            if (!TextUtils.isEmpty(str7)) {
                a2.put("pin", str7);
            }
            if (!TextUtils.isEmpty(str8)) {
                a2.put(Constant.KEY_EXPIRY_DATE, b(str8));
            }
            if (!TextUtils.isEmpty(str9)) {
                a2.put(Constant.KEY_CVN2, b(str9));
            }
            if (!TextUtils.isEmpty(str10)) {
                a2.put(Constant.KEY_PHONE_NUMBER, b(str10));
            }
            if (!TextUtils.isEmpty(str11)) {
                a2.put(Constant.KEY_SMS_AUTH_CODE, b(str11));
            }
            if (!TextUtils.isEmpty(str12)) {
                a2.put("cardType", b(str12));
            }
            appDownloadApplyRequestParams2.setParams(a2);
            try {
                int appDownloadApply2 = this.f18169e.appDownloadApply(appDownloadApplyRequestParams2, new b(this, 15, this.Z[15], (byte) 0));
                if (-2 == appDownloadApply2) {
                    return a(15, appDownloadApplyRequestParams, iTsmCallback);
                }
                if (appDownloadApply2 == 0) {
                    HashMap<String, ITsmCallback> hashMap3 = this.p;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[15];
                    iArr2[15] = i5 + 1;
                    hashMap3.put(String.valueOf(i5), iTsmCallback);
                }
                return appDownloadApply2;
            } catch (Exception e3) {
                e3.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int appLock(AppLockRequestParams appLockRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (appLockRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            if (!a(this.f18168c.getPackageName())) {
                return a(26, appLockRequestParams, iTsmCallback);
            }
            AppLockRequestParams appLockRequestParams2 = new AppLockRequestParams();
            String reserve = appLockRequestParams.getReserve();
            AppID appID = appLockRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appLockRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appLockRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            try {
                int appLock = this.f18169e.appLock(appLockRequestParams2, iTsmCallback);
                if (-2 == appLock) {
                    return a(26, appLockRequestParams, iTsmCallback);
                }
                return appLock;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int appUnlock(AppUnlockRequestParams appUnlockRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (appUnlockRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            if (!a(this.f18168c.getPackageName())) {
                return a(27, appUnlockRequestParams, iTsmCallback);
            }
            AppUnlockRequestParams appUnlockRequestParams2 = new AppUnlockRequestParams();
            String reserve = appUnlockRequestParams.getReserve();
            AppID appID = appUnlockRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                appUnlockRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    appUnlockRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            try {
                int appUnlock = this.f18169e.appUnlock(appUnlockRequestParams2, iTsmCallback);
                if (-2 == appUnlock) {
                    return a(27, appUnlockRequestParams, iTsmCallback);
                }
                return appUnlock;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public boolean bind() {
        try {
            if (this.d == null) {
                this.d = new ServiceConnection() { // from class: com.unionpay.tsmservice.UPTsmAddon.2
                    {
                        UPTsmAddon.this = this;
                    }

                    @Override // android.content.ServiceConnection
                    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        UPTsmAddon.this.f18170f = true;
                        UPTsmAddon.this.f18169e = ITsmService.Stub.asInterface(iBinder);
                        UPTsmAddon.this.ab.sendEmptyMessage(0);
                    }

                    @Override // android.content.ServiceConnection
                    public final synchronized void onServiceDisconnected(ComponentName componentName) {
                        UPTsmAddon.this.f18170f = false;
                        UPTsmAddon.this.f18169e = null;
                        UPTsmAddon.this.ab.sendEmptyMessage(1);
                    }
                };
            }
            if (this.f18170f) {
                return true;
            }
            Intent intent = new Intent("com.unionpay.tsmservice.UPTsmService");
            intent.setPackage("com.unionpay.tsmservice");
            return this.f18168c.bindService(intent, this.d, 1);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public synchronized int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.14")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 35;
                if (!a(this.f18168c.getPackageName())) {
                    return a(35, cardListStatusChangedRequestParams, iTsmCallback);
                }
                CardListStatusChangedRequestParams cardListStatusChangedRequestParams2 = new CardListStatusChangedRequestParams();
                String reserve = cardListStatusChangedRequestParams != null ? cardListStatusChangedRequestParams.getReserve() : "";
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    cardListStatusChangedRequestParams2.setReserve(b(reserve));
                }
                this.M.put(String.valueOf(this.Z[35]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[35];
                    iArr[35] = i4 + 1;
                    int cardListStatusChanged = iTsmService.cardListStatusChanged(cardListStatusChangedRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (cardListStatusChanged != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.M;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[35] - 1;
                        iArr2[35] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == cardListStatusChanged) {
                        return a(35, cardListStatusChangedRequestParams, iTsmCallback);
                    }
                    return cardListStatusChanged;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized int checkSSamsungPay(CheckSSamsungPayRequestParams checkSSamsungPayRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (checkSSamsungPayRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 29;
            if (!a(this.f18168c.getPackageName())) {
                return a(29, checkSSamsungPayRequestParams, iTsmCallback);
            }
            CheckSSamsungPayRequestParams checkSSamsungPayRequestParams2 = new CheckSSamsungPayRequestParams();
            String reserve = checkSSamsungPayRequestParams.getReserve();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                checkSSamsungPayRequestParams2.setReserve(b(reserve));
            }
            this.J.put(String.valueOf(this.Z[29]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[29];
                iArr[29] = i4 + 1;
                int checkSSamsungPay = iTsmService.checkSSamsungPay(checkSSamsungPayRequestParams2, new b(this, i3, i4, (byte) 0));
                if (checkSSamsungPay != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.J;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[29] - 1;
                    iArr2[29] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == checkSSamsungPay) {
                    return a(29, checkSSamsungPayRequestParams, iTsmCallback);
                }
                return checkSSamsungPay;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x0069 A[Catch: all -> 0x0083, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:116:0x000b, B:121:0x0014, B:123:0x0018, B:125:0x0027, B:127:0x002f, B:129:0x003a, B:130:0x0042, B:132:0x0048, B:133:0x004f, B:136:0x0057, B:137:0x005f, B:141:0x0069, B:138:0x0060, B:147:0x0072, B:148:0x007a, B:149:0x007b), top: B:157:0x000b, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x006f A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized int clearEncryptData(int i2) throws RemoteException {
        int i3;
        int clearEncryptData;
        if (i2 < 2000 || i2 > 2001) {
            i3 = -3;
        } else if (!c()) {
            i3 = -8;
        } else if (this.f18169e == null) {
            i3 = -1;
        } else if (!a(this.f18168c.getPackageName())) {
            return a(33, (SafetyKeyboardRequestParams) null, i2, (OnSafetyKeyboardCallback) null, (Context) null);
        } else if (d("01.00.24")) {
            ClearEncryptDataRequestParams clearEncryptDataRequestParams = new ClearEncryptDataRequestParams();
            String g2 = this.f18172h ? g(f("")) : "";
            if (!TextUtils.isEmpty(g2)) {
                clearEncryptDataRequestParams.setReserve(b(g2));
            }
            try {
                clearEncryptData = this.f18169e.clearKeyboardEncryptData(clearEncryptDataRequestParams, i2);
                if (-2 != clearEncryptData) {
                    return a(33, (SafetyKeyboardRequestParams) null, i2, (OnSafetyKeyboardCallback) null, (Context) null);
                }
                return clearEncryptData;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            try {
                clearEncryptData = this.f18169e.clearEncryptData(i2);
                if (-2 != clearEncryptData) {
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                throw new RemoteException();
            }
        }
        return i3;
    }

    public synchronized int closeChannel(CloseChannelRequestParams closeChannelRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (closeChannelRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String channel = closeChannelRequestParams.getChannel();
        if (TextUtils.isEmpty(channel)) {
            return -3;
        }
        if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 21;
            if (!a(this.f18168c.getPackageName())) {
                return a(21, closeChannelRequestParams, iTsmCallback);
            }
            String b2 = b(channel);
            CloseChannelRequestParams closeChannelRequestParams2 = new CloseChannelRequestParams();
            closeChannelRequestParams2.setChannel(b2);
            String reserve = closeChannelRequestParams.getReserve();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                closeChannelRequestParams2.setReserve(b(reserve));
            }
            this.F.put(String.valueOf(this.Z[21]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[21];
                iArr[21] = i4 + 1;
                int closeChannel = iTsmService.closeChannel(closeChannelRequestParams2, new b(this, i3, i4, (byte) 0));
                if (closeChannel != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.F;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[21] - 1;
                    iArr2[21] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == closeChannel) {
                    return a(21, closeChannelRequestParams, iTsmCallback);
                }
                return closeChannel;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int createSSD(UniteRequestParams uniteRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.38")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 46;
                if (!a(this.f18168c.getPackageName())) {
                    return a(46, uniteRequestParams, iTsmCallback);
                }
                UniteRequestParams uniteRequestParams2 = new UniteRequestParams();
                if (uniteRequestParams == null) {
                    uniteRequestParams = new UniteRequestParams();
                }
                Bundle params = uniteRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", a(params));
                    uniteRequestParams2.setParams(bundle);
                }
                String reserve = uniteRequestParams.getReserve();
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    uniteRequestParams2.setReserve(b(reserve));
                }
                this.X.put(String.valueOf(this.Z[46]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[46];
                    iArr[46] = i4 + 1;
                    int createSSD = iTsmService.createSSD(uniteRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (createSSD != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.X;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[46] - 1;
                        iArr2[46] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == createSSD) {
                        return a(46, uniteRequestParams, iTsmCallback);
                    }
                    return createSSD;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int eCashTopUp(ECashTopUpRequestParams eCashTopUpRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (eCashTopUpRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 19;
            if (!a(this.f18168c.getPackageName())) {
                return a(19, eCashTopUpRequestParams, iTsmCallback);
            }
            ECashTopUpRequestParams eCashTopUpRequestParams2 = new ECashTopUpRequestParams();
            String reserve = eCashTopUpRequestParams.getReserve();
            AppID appID = eCashTopUpRequestParams.getAppID();
            String type = eCashTopUpRequestParams.getType();
            String amount = eCashTopUpRequestParams.getAmount();
            if (!TextUtils.isEmpty(reserve)) {
                eCashTopUpRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    eCashTopUpRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            String encrpytPin = eCashTopUpRequestParams.getEncrpytPin();
            if (!TextUtils.isEmpty(encrpytPin)) {
                eCashTopUpRequestParams2.setEncrpytPin(encrpytPin);
            }
            if (!TextUtils.isEmpty(type)) {
                eCashTopUpRequestParams2.setType(b(type));
            }
            if (!TextUtils.isEmpty(amount)) {
                eCashTopUpRequestParams2.setAmount(b(amount));
            }
            try {
                int eCashTopUp = this.f18169e.eCashTopUp(eCashTopUpRequestParams2, new b(this, i3, this.Z[19], (byte) 0));
                if (-2 == eCashTopUp) {
                    return a(19, eCashTopUpRequestParams, iTsmCallback);
                }
                if (eCashTopUp == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.s;
                    int[] iArr = this.Z;
                    int i4 = iArr[19];
                    iArr[19] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return eCashTopUp;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (encryptDataRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 23;
            if (!a(this.f18168c.getPackageName())) {
                return a(23, encryptDataRequestParams, iTsmCallback);
            }
            EncryptDataRequestParams encryptDataRequestParams2 = new EncryptDataRequestParams();
            String reserve = encryptDataRequestParams.getReserve();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                encryptDataRequestParams2.setReserve(b(reserve));
            }
            ArrayList arrayList = (ArrayList) encryptDataRequestParams.getData();
            byte b2 = 0;
            if (arrayList != null) {
                int size = arrayList.size();
                if (size == 0) {
                    return -3;
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i4 = 0; i4 < size; i4++) {
                    String str = (String) arrayList.get(i4);
                    if (!TextUtils.isEmpty(str)) {
                        arrayList2.add(b(str));
                    }
                }
                encryptDataRequestParams2.setData(arrayList2);
            }
            this.B.put(String.valueOf(this.Z[23]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i5 = iArr[23];
                iArr[23] = i5 + 1;
                int encryptData = iTsmService.encryptData(encryptDataRequestParams2, new b(this, i3, i5, b2));
                if (encryptData != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.B;
                    int[] iArr2 = this.Z;
                    int i6 = iArr2[23] - 1;
                    iArr2[23] = i6;
                    hashMap.remove(String.valueOf(i6));
                }
                if (-2 == encryptData) {
                    return a(23, encryptDataRequestParams, iTsmCallback);
                }
                return encryptData;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public int exchangeKey(String str, String[] strArr) throws RemoteException {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return -3;
        }
        if (c()) {
            ITsmService iTsmService = this.f18169e;
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
        return -8;
    }

    public synchronized int executeCmd(ExecuteCmdRequestParams executeCmdRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i2;
        if (executeCmdRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 25;
            if (!a(this.f18168c.getPackageName())) {
                return a(25, executeCmdRequestParams, iTsmCallback, iTsmProgressCallback);
            }
            ExecuteCmdRequestParams executeCmdRequestParams2 = new ExecuteCmdRequestParams();
            String reserve = executeCmdRequestParams.getReserve();
            String ssid = executeCmdRequestParams.getSsid();
            String sign = executeCmdRequestParams.getSign();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                executeCmdRequestParams2.setReserve(b(reserve));
            }
            if (!TextUtils.isEmpty(ssid)) {
                executeCmdRequestParams2.setSsid(b(ssid));
            }
            if (!TextUtils.isEmpty(sign)) {
                executeCmdRequestParams2.setSign(b(sign));
            }
            this.H.put(String.valueOf(this.Z[25]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[25];
                iArr[25] = i4 + 1;
                int executeCmd = iTsmService.executeCmd(executeCmdRequestParams2, new b(this, i3, i4, (byte) 0), iTsmProgressCallback);
                if (executeCmd != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.H;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[25] - 1;
                    iArr2[25] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == executeCmd) {
                    return a(25, executeCmdRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                return executeCmd;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getAccountBalance(GetAccountBalanceRequestParams getAccountBalanceRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getAccountBalanceRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 8;
            if (!a(this.f18168c.getPackageName())) {
                return a(8, getAccountBalanceRequestParams, iTsmCallback);
            }
            GetAccountBalanceRequestParams getAccountBalanceRequestParams2 = new GetAccountBalanceRequestParams();
            String reserve = getAccountBalanceRequestParams.getReserve();
            AppID appID = getAccountBalanceRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getAccountBalanceRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getAccountBalanceRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            String encryptPin = getAccountBalanceRequestParams.getEncryptPin();
            if (!TextUtils.isEmpty(encryptPin)) {
                getAccountBalanceRequestParams2.setEncryptPin(encryptPin);
            }
            try {
                int accountBalance = this.f18169e.getAccountBalance(getAccountBalanceRequestParams2, new b(this, i3, this.Z[8], (byte) 0));
                if (-2 == accountBalance) {
                    return a(8, getAccountBalanceRequestParams, iTsmCallback);
                }
                if (accountBalance == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.v;
                    int[] iArr = this.Z;
                    int i4 = iArr[8];
                    iArr[8] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return accountBalance;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getAccountInfo(GetAccountInfoRequestParams getAccountInfoRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getAccountInfoRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 7;
            if (!a(this.f18168c.getPackageName())) {
                return a(7, getAccountInfoRequestParams, iTsmCallback);
            }
            GetAccountInfoRequestParams getAccountInfoRequestParams2 = new GetAccountInfoRequestParams();
            String reserve = getAccountInfoRequestParams.getReserve();
            AppID appID = getAccountInfoRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getAccountInfoRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getAccountInfoRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            try {
                int accountInfo = this.f18169e.getAccountInfo(getAccountInfoRequestParams2, new b(this, i3, this.Z[7], (byte) 0));
                if (-2 == accountInfo) {
                    return a(7, getAccountInfoRequestParams, iTsmCallback);
                }
                if (accountInfo == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.u;
                    int[] iArr = this.Z;
                    int i4 = iArr[7];
                    iArr[7] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return accountInfo;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getAppDetail(GetAppDetailRequestParams getAppDetailRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getAppDetailRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 4;
            if (!a(this.f18168c.getPackageName())) {
                return a(4, getAppDetailRequestParams, iTsmCallback);
            }
            GetAppDetailRequestParams getAppDetailRequestParams2 = new GetAppDetailRequestParams();
            String reserve = getAppDetailRequestParams.getReserve();
            AppID appID = getAppDetailRequestParams.getAppID();
            String transType = getAppDetailRequestParams.getTransType();
            if (!TextUtils.isEmpty(reserve)) {
                getAppDetailRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getAppDetailRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            if (!TextUtils.isEmpty(transType)) {
                getAppDetailRequestParams2.setTransType(b(transType));
            }
            try {
                int appDetail = this.f18169e.getAppDetail(getAppDetailRequestParams2, new b(this, i3, this.Z[4], (byte) 0));
                if (-2 == appDetail) {
                    return a(4, getAppDetailRequestParams, iTsmCallback);
                }
                if (appDetail == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18178n;
                    int[] iArr = this.Z;
                    int i4 = iArr[4];
                    iArr[4] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return appDetail;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getAppList(GetAppListRequestParams getAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getAppListRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 2;
            if (!a(this.f18168c.getPackageName())) {
                return a(2, getAppListRequestParams, iTsmCallback);
            }
            GetAppListRequestParams getAppListRequestParams2 = new GetAppListRequestParams();
            String reserve = getAppListRequestParams.getReserve();
            String keyword = getAppListRequestParams.getKeyword();
            String[] status = getAppListRequestParams.getStatus();
            if (!TextUtils.isEmpty(reserve)) {
                getAppListRequestParams2.setReserve(b(reserve));
            }
            if (!TextUtils.isEmpty(keyword)) {
                getAppListRequestParams2.setKeyword(b(keyword));
            }
            byte b2 = 0;
            if (status != null) {
                int length = status.length;
                String[] strArr = new String[length];
                for (int i4 = 0; i4 < length; i4++) {
                    if (!TextUtils.isEmpty(status[i4])) {
                        strArr[i4] = b(status[i4]);
                    }
                }
                getAppListRequestParams2.setStatus(strArr);
            }
            try {
                int appList = this.f18169e.getAppList(getAppListRequestParams2, new b(this, i3, this.Z[2], b2));
                if (-2 == appList) {
                    return a(2, getAppListRequestParams, iTsmCallback);
                }
                if (appList == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18176l;
                    int[] iArr = this.Z;
                    int i5 = iArr[2];
                    iArr[2] = i5 + 1;
                    hashMap.put(String.valueOf(i5), iTsmCallback);
                }
                return appList;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getAppStatus(GetAppStatusRequestParams getAppStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getAppStatusRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 5;
            if (!a(this.f18168c.getPackageName())) {
                return a(5, getAppStatusRequestParams, iTsmCallback);
            }
            GetAppStatusRequestParams getAppStatusRequestParams2 = new GetAppStatusRequestParams();
            String reserve = getAppStatusRequestParams.getReserve();
            AppID appID = getAppStatusRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getAppStatusRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getAppStatusRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            try {
                int appStatus = this.f18169e.getAppStatus(getAppStatusRequestParams2, new b(this, i3, this.Z[5], (byte) 0));
                if (-2 == appStatus) {
                    return a(5, getAppStatusRequestParams, iTsmCallback);
                }
                if (appStatus == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18177m;
                    int[] iArr = this.Z;
                    int i4 = iArr[5];
                    iArr[5] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return appStatus;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getAssociatedApp(GetAssociatedAppRequestParams getAssociatedAppRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getAssociatedAppRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String encryptPan = getAssociatedAppRequestParams.getEncryptPan();
        if (TextUtils.isEmpty(encryptPan)) {
            return -3;
        }
        if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 1;
            if (!a(this.f18168c.getPackageName())) {
                return a(1, getAssociatedAppRequestParams, iTsmCallback);
            }
            GetAssociatedAppRequestParams getAssociatedAppRequestParams2 = new GetAssociatedAppRequestParams();
            String reserve = getAssociatedAppRequestParams.getReserve();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                getAssociatedAppRequestParams2.setReserve(b(reserve));
            }
            getAssociatedAppRequestParams2.setEncryptPan(b(encryptPan));
            try {
                int associatedApp = this.f18169e.getAssociatedApp(getAssociatedAppRequestParams2, new b(this, i3, this.Z[1], (byte) 0));
                if (-2 == associatedApp) {
                    return a(1, getAssociatedAppRequestParams, iTsmCallback);
                }
                if (associatedApp == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18174j;
                    int[] iArr = this.Z;
                    int i4 = iArr[1];
                    iArr[1] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return associatedApp;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getCardInfo(GetCardInfoRequestParams getCardInfoRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getCardInfoRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String[] appAID = getCardInfoRequestParams.getAppAID();
        int length = appAID.length;
        if (appAID != null && length != 0) {
            byte b2 = 0;
            int i3 = 0;
            while (i3 < length && appAID[i3] == null) {
                i3++;
            }
            if (i3 == length) {
                return -3;
            }
            if (!c()) {
                i2 = -8;
            } else if (this.f18169e != null) {
                int i4 = 6;
                if (!a(this.f18168c.getPackageName())) {
                    return a(6, getCardInfoRequestParams, iTsmCallback);
                }
                String[] strArr = new String[length];
                for (int i5 = 0; i5 < length; i5++) {
                    if (appAID[i5] == null) {
                        strArr[i5] = appAID[i5];
                    } else {
                        strArr[i5] = b(appAID[i5]);
                    }
                }
                GetCardInfoRequestParams getCardInfoRequestParams2 = new GetCardInfoRequestParams();
                getCardInfoRequestParams2.setAppAID(strArr);
                String reserve = getCardInfoRequestParams.getReserve();
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    getCardInfoRequestParams2.setReserve(b(reserve));
                }
                try {
                    int cardInfo = this.f18169e.getCardInfo(getCardInfoRequestParams2, new b(this, i4, this.Z[6], b2));
                    if (-2 == cardInfo) {
                        return a(6, getCardInfoRequestParams, iTsmCallback);
                    }
                    if (cardInfo == 0) {
                        HashMap<String, ITsmCallback> hashMap = this.A;
                        int[] iArr = this.Z;
                        int i6 = iArr[6];
                        iArr[6] = i6 + 1;
                        hashMap.put(String.valueOf(i6), iTsmCallback);
                    }
                    return cardInfo;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            } else {
                i2 = -1;
            }
            return i2;
        }
        return -3;
    }

    public synchronized int getCardInfoBySamsungPay(GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getCardInfoBySpayRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 28;
            if (!a(this.f18168c.getPackageName())) {
                return a(28, getCardInfoBySpayRequestParams, iTsmCallback);
            }
            GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams2 = new GetCardInfoBySpayRequestParams();
            String reserve = getCardInfoBySpayRequestParams.getReserve();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                getCardInfoBySpayRequestParams2.setReserve(b(reserve));
            }
            Amount amount = getCardInfoBySpayRequestParams.getAmount();
            if (amount != null) {
                String currencyType = amount.getCurrencyType();
                String productPrice = amount.getProductPrice();
                Amount amount2 = new Amount();
                if (!TextUtils.isEmpty(currencyType)) {
                    amount2.setCurrencyType(b(currencyType));
                }
                if (!TextUtils.isEmpty(productPrice)) {
                    amount2.setProductPrice(b(productPrice));
                }
                getCardInfoBySpayRequestParams2.setAmount(amount2);
            }
            this.I.put(String.valueOf(this.Z[28]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[28];
                iArr[28] = i4 + 1;
                int cardInfoBySamsungPay = iTsmService.getCardInfoBySamsungPay(getCardInfoBySpayRequestParams2, new b(this, i3, i4, (byte) 0));
                if (cardInfoBySamsungPay != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.I;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[28] - 1;
                    iArr2[28] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == cardInfoBySamsungPay) {
                    return a(28, getCardInfoBySpayRequestParams, iTsmCallback);
                }
                return cardInfoBySamsungPay;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public Context getContext() {
        return this.f18168c;
    }

    public int getCryptType() {
        return this.f18171g;
    }

    @Deprecated
    public synchronized int getDefaultCard(GetDefaultCardRequestParams getDefaultCardRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 13;
            if (!a(this.f18168c.getPackageName())) {
                return a(13, getDefaultCardRequestParams, iTsmCallback);
            }
            GetDefaultCardRequestParams getDefaultCardRequestParams2 = new GetDefaultCardRequestParams();
            if (getDefaultCardRequestParams != null) {
                String reserve = getDefaultCardRequestParams.getReserve();
                if (!TextUtils.isEmpty(reserve)) {
                    getDefaultCardRequestParams2.setReserve(b(reserve));
                }
            }
            try {
                int defaultCard = this.f18169e.getDefaultCard(getDefaultCardRequestParams2, new b(this, i3, this.Z[13], (byte) 0));
                if (-2 == defaultCard) {
                    return a(13, getDefaultCardRequestParams, iTsmCallback);
                }
                if (defaultCard == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.y;
                    int[] iArr = this.Z;
                    int i4 = iArr[13];
                    iArr[13] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return defaultCard;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null || getEncryptDataRequestParams == null) {
            return -3;
        }
        int type = getEncryptDataRequestParams.getType();
        String pan = getEncryptDataRequestParams.getPan();
        if (type >= 2000 && type <= 2001) {
            if (type == 2000 && TextUtils.isEmpty(pan)) {
                return -3;
            }
            if (!c()) {
                i2 = -8;
            } else if (this.f18169e != null) {
                int i3 = 31;
                if (!a(this.f18168c.getPackageName())) {
                    return a(31, getEncryptDataRequestParams, iTsmCallback);
                }
                GetEncryptDataRequestParams getEncryptDataRequestParams2 = new GetEncryptDataRequestParams();
                if (type == 2000) {
                    getEncryptDataRequestParams2.setPan(b(pan));
                }
                getEncryptDataRequestParams2.setType(type);
                String reserve = getEncryptDataRequestParams.getReserve();
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    getEncryptDataRequestParams2.setReserve(b(reserve));
                }
                this.L.put(String.valueOf(this.Z[31]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[31];
                    iArr[31] = i4 + 1;
                    int encryptData = iTsmService.getEncryptData(getEncryptDataRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (encryptData != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.L;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[31] - 1;
                        iArr2[31] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == encryptData) {
                        return a(31, getEncryptDataRequestParams, iTsmCallback);
                    }
                    return encryptData;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            } else {
                i2 = -1;
            }
            return i2;
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
        } else if (!d("01.00.35")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 44;
                if (!a(this.f18168c.getPackageName())) {
                    return a(44, getMessageDetailsRequestParams, iTsmCallback);
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
                if (this.f18172h) {
                    str = g(f(str));
                }
                if (!TextUtils.isEmpty(str)) {
                    getMessageDetailsRequestParams2.setReserve(b(str));
                }
                this.V.put(String.valueOf(this.Z[44]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[44];
                    iArr[44] = i4 + 1;
                    int messageDetails = iTsmService.getMessageDetails(getMessageDetailsRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (messageDetails != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.V;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[44] - 1;
                        iArr2[44] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == messageDetails) {
                        return a(44, getMessageDetailsRequestParams, iTsmCallback);
                    }
                    return messageDetails;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public int getPubKey(int i2, String[] strArr) throws RemoteException {
        if (strArr == null || strArr.length == 0) {
            return -3;
        }
        if (c()) {
            ITsmService iTsmService = this.f18169e;
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
        return -8;
    }

    @Deprecated
    public synchronized int getSEAppList(GetSeAppListRequestParams getSeAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 3;
            if (!a(this.f18168c.getPackageName())) {
                return a(3, getSeAppListRequestParams, iTsmCallback);
            }
            GetSeAppListRequestParams getSeAppListRequestParams2 = new GetSeAppListRequestParams();
            String reserve = getSeAppListRequestParams != null ? getSeAppListRequestParams.getReserve() : "";
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                getSeAppListRequestParams2.setReserve(b(reserve));
            }
            this.f18175k.put(String.valueOf(this.Z[3]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[3];
                iArr[3] = i4 + 1;
                int sEAppList = iTsmService.getSEAppList(getSeAppListRequestParams2, new b(this, i3, i4, (byte) 0));
                if (sEAppList != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18175k;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[3] - 1;
                    iArr2[3] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == sEAppList) {
                    return a(3, getSeAppListRequestParams, iTsmCallback);
                }
                return sEAppList;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getSMSAuthCode(GetSMSAuthCodeRequestParams getSMSAuthCodeRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getSMSAuthCodeRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 11;
            if (!a(this.f18168c.getPackageName())) {
                return a(11, getSMSAuthCodeRequestParams, iTsmCallback);
            }
            GetSMSAuthCodeRequestParams getSMSAuthCodeRequestParams2 = new GetSMSAuthCodeRequestParams();
            String reserve = getSMSAuthCodeRequestParams.getReserve();
            AppID appID = getSMSAuthCodeRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getSMSAuthCodeRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getSMSAuthCodeRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            String pan = getSMSAuthCodeRequestParams.getPan();
            String msisdn = getSMSAuthCodeRequestParams.getMsisdn();
            if (!TextUtils.isEmpty(pan)) {
                getSMSAuthCodeRequestParams2.setPan(b(pan));
            }
            if (!TextUtils.isEmpty(msisdn)) {
                getSMSAuthCodeRequestParams2.setMsisdn(b(msisdn));
            }
            try {
                int sMSAuthCode = this.f18169e.getSMSAuthCode(getSMSAuthCodeRequestParams2, new b(this, i3, this.Z[11], (byte) 0));
                if (-2 == sMSAuthCode) {
                    return a(11, getSMSAuthCodeRequestParams, iTsmCallback);
                }
                if (sMSAuthCode == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.r;
                    int[] iArr = this.Z;
                    int i4 = iArr[11];
                    iArr[11] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return sMSAuthCode;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int getSeId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 12;
            if (!a(this.f18168c.getPackageName())) {
                return a(12, getSeIdRequestParams, iTsmCallback);
            }
            GetSeIdRequestParams getSeIdRequestParams2 = new GetSeIdRequestParams();
            String reserve = getSeIdRequestParams != null ? getSeIdRequestParams.getReserve() : "";
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                getSeIdRequestParams2.setReserve(b(reserve));
            }
            this.z.put(String.valueOf(this.Z[12]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[12];
                iArr[12] = i4 + 1;
                int sEId = iTsmService.getSEId(getSeIdRequestParams2, new b(this, i3, i4, (byte) 0));
                if (sEId != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.z;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[12] - 1;
                    iArr2[12] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == sEId) {
                    return a(12, getSeIdRequestParams, iTsmCallback);
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

    @Deprecated
    public synchronized int getTransElements(GetTransElementsRequestParams getTransElementsRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getTransElementsRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 9;
            if (!a(this.f18168c.getPackageName())) {
                return a(9, getTransElementsRequestParams, iTsmCallback);
            }
            GetTransElementsRequestParams getTransElementsRequestParams2 = new GetTransElementsRequestParams();
            String reserve = getTransElementsRequestParams.getReserve();
            AppID appID = getTransElementsRequestParams.getAppID();
            String transType = getTransElementsRequestParams.getTransType();
            if (!TextUtils.isEmpty(reserve)) {
                getTransElementsRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getTransElementsRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            if (!TextUtils.isEmpty(transType)) {
                getTransElementsRequestParams2.setTransType(b(transType));
            }
            try {
                int transElements = this.f18169e.getTransElements(getTransElementsRequestParams2, new b(this, i3, this.Z[9], (byte) 0));
                if (-2 == transElements) {
                    return a(9, getTransElementsRequestParams, iTsmCallback);
                }
                if (transElements == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.o;
                    int[] iArr = this.Z;
                    int i4 = iArr[9];
                    iArr[9] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return transElements;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int getTransRecord(GetTransRecordRequestParams getTransRecordRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (getTransRecordRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 10;
            if (!a(this.f18168c.getPackageName())) {
                return a(10, getTransRecordRequestParams, iTsmCallback);
            }
            GetTransRecordRequestParams getTransRecordRequestParams2 = new GetTransRecordRequestParams();
            String reserve = getTransRecordRequestParams.getReserve();
            AppID appID = getTransRecordRequestParams.getAppID();
            if (!TextUtils.isEmpty(reserve)) {
                getTransRecordRequestParams2.setReserve(b(reserve));
            }
            if (appID != null) {
                String appAid = appID.getAppAid();
                String appVersion = appID.getAppVersion();
                if (!TextUtils.isEmpty(appAid) && !TextUtils.isEmpty(appVersion)) {
                    getTransRecordRequestParams2.setAppID(new AppID(b(appAid), b(appVersion)));
                }
            }
            try {
                int transRecord = this.f18169e.getTransRecord(getTransRecordRequestParams2, new b(this, i3, this.Z[10], (byte) 0));
                if (-2 == transRecord) {
                    return a(10, getTransRecordRequestParams, iTsmCallback);
                }
                if (transRecord == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.t;
                    int[] iArr = this.Z;
                    int i4 = iArr[10];
                    iArr[10] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return transRecord;
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
        } else if (!d("01.00.35")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 43;
                if (!a(this.f18168c.getPackageName())) {
                    return a(43, getTransactionDetailsRequestParams, iTsmCallback);
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
                if (this.f18172h) {
                    str = g(f(str));
                }
                if (!TextUtils.isEmpty(str)) {
                    getTransactionDetailsRequestParams2.setReserve(b(str));
                }
                this.U.put(String.valueOf(this.Z[43]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[43];
                    iArr[43] = i4 + 1;
                    int transactionDetails = iTsmService.getTransactionDetails(getTransactionDetailsRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (transactionDetails != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.U;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[43] - 1;
                        iArr2[43] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == transactionDetails) {
                        return a(43, getTransactionDetailsRequestParams, iTsmCallback);
                    }
                    return transactionDetails;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.20")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 36;
                if (!a(this.f18168c.getPackageName())) {
                    return a(36, getVendorPayStatusRequestParams, iTsmCallback);
                }
                GetVendorPayStatusRequestParams getVendorPayStatusRequestParams2 = new GetVendorPayStatusRequestParams();
                String reserve = getVendorPayStatusRequestParams != null ? getVendorPayStatusRequestParams.getReserve() : "";
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    getVendorPayStatusRequestParams2.setReserve(b(reserve));
                }
                this.N.put(String.valueOf(this.Z[36]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[36];
                    iArr[36] = i4 + 1;
                    int vendorPayStatus = iTsmService.getVendorPayStatus(getVendorPayStatusRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (vendorPayStatus != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.N;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[36] - 1;
                        iArr2[36] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == vendorPayStatus) {
                        return a(36, getVendorPayStatusRequestParams, iTsmCallback);
                    }
                    return vendorPayStatus;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized int hideAppApply(HideAppApplyRequestParams hideAppApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (hideAppApplyRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String applyId = hideAppApplyRequestParams.getApplyId();
        if (TextUtils.isEmpty(applyId)) {
            return -3;
        }
        if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 24;
            if (!a(this.f18168c.getPackageName())) {
                return a(24, hideAppApplyRequestParams, iTsmCallback);
            }
            String b2 = b(applyId);
            HideAppApplyRequestParams hideAppApplyRequestParams2 = new HideAppApplyRequestParams();
            hideAppApplyRequestParams2.setApplyId(b2);
            String reserve = hideAppApplyRequestParams.getReserve();
            if (!TextUtils.isEmpty(reserve)) {
                hideAppApplyRequestParams2.setReserve(b(reserve));
            }
            try {
                int hideAppApply = this.f18169e.hideAppApply(hideAppApplyRequestParams2, new b(this, i3, this.Z[24], (byte) 0));
                if (-2 == hideAppApply) {
                    return a(24, hideAppApplyRequestParams, iTsmCallback);
                }
                if (hideAppApply == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.G;
                    int[] iArr = this.Z;
                    int i4 = iArr[24];
                    iArr[24] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return hideAppApply;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x0060 A[Catch: all -> 0x007a, TRY_ENTER, TRY_LEAVE, TryCatch #2 {, blocks: (B:103:0x0001, B:108:0x000a, B:110:0x000e, B:112:0x001e, B:114:0x0026, B:116:0x0031, B:117:0x0039, B:119:0x003f, B:120:0x0046, B:123:0x004e, B:124:0x0056, B:128:0x0060, B:125:0x0057, B:134:0x0069, B:135:0x0071, B:136:0x0072), top: B:149:0x0001, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0066 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized int hideKeyboard() throws RemoteException {
        int i2;
        int hideKeyboard;
        if (!c()) {
            i2 = -8;
        } else if (this.f18169e == null) {
            i2 = -1;
        } else if (!a(this.f18168c.getPackageName())) {
            return a(34, (SafetyKeyboardRequestParams) null, 0, (OnSafetyKeyboardCallback) null, (Context) null);
        } else if (d("01.00.24")) {
            HideSafetyKeyboardRequestParams hideSafetyKeyboardRequestParams = new HideSafetyKeyboardRequestParams();
            String g2 = this.f18172h ? g(f("")) : "";
            if (!TextUtils.isEmpty(g2)) {
                hideSafetyKeyboardRequestParams.setReserve(b(g2));
            }
            try {
                hideKeyboard = this.f18169e.hideSafetyKeyboard(hideSafetyKeyboardRequestParams);
                if (-2 != hideKeyboard) {
                    return a(34, (SafetyKeyboardRequestParams) null, 0, (OnSafetyKeyboardCallback) null, (Context) null);
                }
                return hideKeyboard;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            try {
                hideKeyboard = this.f18169e.hideKeyboard();
                if (-2 != hideKeyboard) {
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                throw new RemoteException();
            }
        }
        return i2;
    }

    public synchronized int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            byte b2 = 0;
            if (!a(this.f18168c.getPackageName())) {
                return a(0, initRequestParams, iTsmCallback);
            }
            InitRequestParams initRequestParams2 = new InitRequestParams();
            String str = "";
            if (initRequestParams != null) {
                str = initRequestParams.getReserve();
                String signature = initRequestParams.getSignature();
                if (!TextUtils.isEmpty(signature)) {
                    initRequestParams2.setSignature(b(signature));
                }
            }
            if (this.f18172h) {
                str = g(f(str));
            }
            if (!TextUtils.isEmpty(str)) {
                initRequestParams2.setReserve(b(str));
            }
            this.f18173i.put(String.valueOf(this.Z[0]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i3 = iArr[0];
                iArr[0] = i3 + 1;
                int init = iTsmService.init(initRequestParams2, new b(this, b2, i3, b2));
                if (init != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.f18173i;
                    int[] iArr2 = this.Z;
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
        return this.f18170f;
    }

    public synchronized int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (onlinePaymentVerifyRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.21")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 39;
                if (!a(this.f18168c.getPackageName())) {
                    return a(39, onlinePaymentVerifyRequestParams, iTsmCallback);
                }
                OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams2 = new OnlinePaymentVerifyRequestParams();
                Bundle resource = onlinePaymentVerifyRequestParams.getResource();
                if (resource != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", a(resource));
                    onlinePaymentVerifyRequestParams2.setResource(bundle);
                }
                String orderNumber = onlinePaymentVerifyRequestParams.getOrderNumber();
                String aId = onlinePaymentVerifyRequestParams.getAId();
                if (!TextUtils.isEmpty(orderNumber)) {
                    onlinePaymentVerifyRequestParams2.setOrderNumber(b(orderNumber));
                }
                if (!TextUtils.isEmpty(aId)) {
                    onlinePaymentVerifyRequestParams2.setAId(b(aId));
                }
                String reserve = onlinePaymentVerifyRequestParams.getReserve();
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    onlinePaymentVerifyRequestParams2.setReserve(b(reserve));
                }
                this.Q.put(String.valueOf(this.Z[39]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[39];
                    iArr[39] = i4 + 1;
                    int onlinePaymentVerify = iTsmService.onlinePaymentVerify(onlinePaymentVerifyRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (onlinePaymentVerify != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.Q;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[39] - 1;
                        iArr2[39] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == onlinePaymentVerify) {
                        return a(39, onlinePaymentVerifyRequestParams, iTsmCallback);
                    }
                    return onlinePaymentVerify;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized int openChannel(OpenChannelRequestParams openChannelRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (openChannelRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String appAID = openChannelRequestParams.getAppAID();
        if (TextUtils.isEmpty(appAID)) {
            return -3;
        }
        if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 20;
            if (!a(this.f18168c.getPackageName())) {
                return a(20, openChannelRequestParams, iTsmCallback);
            }
            String b2 = b(appAID);
            OpenChannelRequestParams openChannelRequestParams2 = new OpenChannelRequestParams();
            openChannelRequestParams2.setAppAID(b2);
            String reserve = openChannelRequestParams.getReserve();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                openChannelRequestParams2.setReserve(b(reserve));
            }
            this.w.put(String.valueOf(this.Z[20]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[20];
                iArr[20] = i4 + 1;
                int openChannel = iTsmService.openChannel(openChannelRequestParams2, new b(this, i3, i4, (byte) 0));
                if (openChannel != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.w;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[20] - 1;
                    iArr2[20] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == openChannel) {
                    return a(20, openChannelRequestParams, iTsmCallback);
                }
                return openChannel;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int preDownload(PreDownloadRequestParams preDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.26")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 40;
                if (!a(this.f18168c.getPackageName())) {
                    return a(40, preDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
                }
                PreDownloadRequestParams preDownloadRequestParams2 = new PreDownloadRequestParams();
                String str = "";
                if (preDownloadRequestParams != null) {
                    str = preDownloadRequestParams.getReserve();
                    Bundle params = preDownloadRequestParams.getParams();
                    if (params != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("encryptData", a(params));
                        preDownloadRequestParams2.setParams(bundle);
                    }
                }
                if (this.f18172h) {
                    str = g(f(str));
                }
                if (!TextUtils.isEmpty(str)) {
                    preDownloadRequestParams2.setReserve(b(str));
                }
                this.R.put(String.valueOf(this.Z[40]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[40];
                    iArr[40] = i4 + 1;
                    int preDownload = iTsmService.preDownload(preDownloadRequestParams2, new b(this, i3, i4, (byte) 0), iTsmProgressCallback);
                    if (preDownload != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.R;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[40] - 1;
                        iArr2[40] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == preDownload) {
                        return a(40, preDownloadRequestParams, iTsmCallback, iTsmProgressCallback);
                    }
                    return preDownload;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.27")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 41;
                if (!a(this.f18168c.getPackageName())) {
                    return a(41, queryVendorPayStatusRequestParams, iTsmCallback);
                }
                QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams2 = new QueryVendorPayStatusRequestParams();
                String reserve = queryVendorPayStatusRequestParams != null ? queryVendorPayStatusRequestParams.getReserve() : "";
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    queryVendorPayStatusRequestParams2.setReserve(b(reserve));
                }
                this.S.put(String.valueOf(this.Z[41]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[41];
                    iArr[41] = i4 + 1;
                    int queryVendorPayStatus = iTsmService.queryVendorPayStatus(queryVendorPayStatusRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (queryVendorPayStatus != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.S;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[41] - 1;
                        iArr2[41] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == queryVendorPayStatus) {
                        return a(41, queryVendorPayStatusRequestParams, iTsmCallback);
                    }
                    return queryVendorPayStatus;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    public synchronized void removeConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.remove(uPTsmConnectionListener);
        }
    }

    public synchronized int sendApdu(SendApduRequestParams sendApduRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (sendApduRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 22;
            if (!a(this.f18168c.getPackageName())) {
                return a(22, sendApduRequestParams, iTsmCallback);
            }
            SendApduRequestParams sendApduRequestParams2 = new SendApduRequestParams();
            String reserve = sendApduRequestParams.getReserve();
            String channel = sendApduRequestParams.getChannel();
            String hexApdu = sendApduRequestParams.getHexApdu();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                sendApduRequestParams2.setReserve(b(reserve));
            }
            if (!TextUtils.isEmpty(channel)) {
                sendApduRequestParams2.setChannel(b(channel));
            }
            if (!TextUtils.isEmpty(hexApdu)) {
                sendApduRequestParams2.setHexApdu(b(hexApdu));
            }
            this.x.put(String.valueOf(this.Z[22]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[22];
                iArr[22] = i4 + 1;
                int sendApdu = iTsmService.sendApdu(sendApduRequestParams2, new b(this, i3, i4, (byte) 0));
                if (sendApdu != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.x;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[22] - 1;
                    iArr2[22] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == sendApdu) {
                    return a(22, sendApduRequestParams, iTsmCallback);
                }
                return sendApdu;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int sendCustomData(SendCustomDataRequestParams sendCustomDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (sendCustomDataRequestParams == null || iTsmCallback == null) {
            i2 = -3;
        } else if (!d("01.00.37")) {
            return -8;
        } else {
            if (!c()) {
                return -8;
            }
            if (this.f18169e != null) {
                int i3 = 45;
                if (!a(this.f18168c.getPackageName())) {
                    return a(45, sendCustomDataRequestParams, iTsmCallback);
                }
                SendCustomDataRequestParams sendCustomDataRequestParams2 = new SendCustomDataRequestParams();
                Bundle params = sendCustomDataRequestParams.getParams();
                if (params != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("encryptData", a(params));
                    sendCustomDataRequestParams2.setParams(bundle);
                }
                String reserve = sendCustomDataRequestParams.getReserve();
                if (this.f18172h) {
                    reserve = g(f(reserve));
                }
                if (!TextUtils.isEmpty(reserve)) {
                    sendCustomDataRequestParams2.setReserve(b(reserve));
                }
                this.W.put(String.valueOf(this.Z[45]), iTsmCallback);
                try {
                    ITsmService iTsmService = this.f18169e;
                    int[] iArr = this.Z;
                    int i4 = iArr[45];
                    iArr[45] = i4 + 1;
                    int sendCustomData = iTsmService.sendCustomData(sendCustomDataRequestParams2, new b(this, i3, i4, (byte) 0));
                    if (sendCustomData != 0) {
                        HashMap<String, ITsmCallback> hashMap = this.W;
                        int[] iArr2 = this.Z;
                        int i5 = iArr2[45] - 1;
                        iArr2[45] = i5;
                        hashMap.remove(String.valueOf(i5));
                    }
                    if (-2 == sendCustomData) {
                        return a(45, sendCustomDataRequestParams, iTsmCallback);
                    }
                    return sendCustomData;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    throw new RemoteException();
                }
            }
            i2 = -1;
        }
        return i2;
    }

    @Deprecated
    public synchronized int setDefaultCard(SetDefaultCardRequestParams setDefaultCardRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (setDefaultCardRequestParams == null || iTsmCallback == null) {
            return -3;
        }
        String appAID = setDefaultCardRequestParams.getAppAID();
        if (TextUtils.isEmpty(appAID)) {
            return -3;
        }
        if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 14;
            if (!a(this.f18168c.getPackageName())) {
                return a(14, setDefaultCardRequestParams, iTsmCallback);
            }
            String b2 = b(appAID);
            SetDefaultCardRequestParams setDefaultCardRequestParams2 = new SetDefaultCardRequestParams();
            setDefaultCardRequestParams2.setAppAID(b2);
            String reserve = setDefaultCardRequestParams.getReserve();
            if (!TextUtils.isEmpty(reserve)) {
                setDefaultCardRequestParams2.setReserve(b(reserve));
            }
            try {
                int defaultCard = this.f18169e.setDefaultCard(setDefaultCardRequestParams2, new b(this, i3, this.Z[14], (byte) 0));
                if (-2 == defaultCard) {
                    return a(14, setDefaultCardRequestParams, iTsmCallback);
                }
                if (defaultCard == 0) {
                    HashMap<String, ITsmCallback> hashMap = this.E;
                    int[] iArr = this.Z;
                    int i4 = iArr[14];
                    iArr[14] = i4 + 1;
                    hashMap.put(String.valueOf(i4), iTsmCallback);
                }
                return defaultCard;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new RemoteException();
            }
        } else {
            i2 = -1;
        }
        return i2;
    }

    public synchronized int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams) throws RemoteException {
        int i2;
        if (safetyKeyboardRequestParams == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            if (!a(this.f18168c.getPackageName())) {
                return a(32, safetyKeyboardRequestParams, null);
            }
            String reserve = safetyKeyboardRequestParams.getReserve();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                safetyKeyboardRequestParams.setReserve(b(reserve));
            }
            try {
                int safetyKeyboardBitmap = this.f18169e.setSafetyKeyboardBitmap(safetyKeyboardRequestParams);
                if (-2 == safetyKeyboardBitmap) {
                    return a(32, safetyKeyboardRequestParams, null);
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

    public synchronized int setSamsungDefaultWallet(SetSamsungDefWalletRequestParams setSamsungDefWalletRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
        int i2;
        if (iTsmCallback == null) {
            i2 = -3;
        } else if (!c()) {
            i2 = -8;
        } else if (this.f18169e != null) {
            int i3 = 30;
            if (!a(this.f18168c.getPackageName())) {
                return a(30, setSamsungDefWalletRequestParams, iTsmCallback);
            }
            SetSamsungDefWalletRequestParams setSamsungDefWalletRequestParams2 = new SetSamsungDefWalletRequestParams();
            if (setSamsungDefWalletRequestParams != null) {
                String reserve = setSamsungDefWalletRequestParams.getReserve();
                if (!TextUtils.isEmpty(reserve)) {
                    setSamsungDefWalletRequestParams2.setReserve(b(reserve));
                }
            }
            this.K.put(String.valueOf(this.Z[30]), iTsmCallback);
            try {
                ITsmService iTsmService = this.f18169e;
                int[] iArr = this.Z;
                int i4 = iArr[30];
                iArr[30] = i4 + 1;
                int samsungDefaultWallet = iTsmService.setSamsungDefaultWallet(setSamsungDefWalletRequestParams2, new b(this, i3, i4, (byte) 0));
                if (samsungDefaultWallet != 0) {
                    HashMap<String, ITsmCallback> hashMap = this.K;
                    int[] iArr2 = this.Z;
                    int i5 = iArr2[30] - 1;
                    iArr2[30] = i5;
                    hashMap.remove(String.valueOf(i5));
                }
                if (-2 == samsungDefaultWallet) {
                    return a(30, setSamsungDefWalletRequestParams, iTsmCallback);
                }
                return samsungDefaultWallet;
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
        if (safetyKeyboardRequestParams == null || i2 < 2000 || i2 > 2001) {
            i3 = -3;
        } else if (!c()) {
            i3 = -8;
        } else if (this.f18169e != null) {
            if (!a(this.f18168c.getPackageName())) {
                return a(1000, safetyKeyboardRequestParams, i2, onSafetyKeyboardCallback, context);
            }
            this.Y.put(this.f18168c.getPackageName(), new com.unionpay.tsmservice.a(context));
            String reserve = safetyKeyboardRequestParams.getReserve();
            if (this.f18172h) {
                reserve = g(f(reserve));
            }
            if (!TextUtils.isEmpty(reserve)) {
                safetyKeyboardRequestParams.setReserve(b(reserve));
            }
            try {
                int showSafetyKeyboard = this.f18169e.showSafetyKeyboard(safetyKeyboardRequestParams, i2, onSafetyKeyboardCallback, new a());
                if (showSafetyKeyboard != 0) {
                    this.Y.remove(this.f18168c.getPackageName());
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
        ServiceConnection serviceConnection = this.d;
        if (serviceConnection == null || !this.f18170f) {
            return;
        }
        this.f18168c.unbindService(serviceConnection);
        this.f18170f = false;
    }
}
