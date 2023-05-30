package com.unionpay.tsmservice.mi.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.unionpay.tsmservice.mi.ITsmCallback;
import com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback;
import com.unionpay.tsmservice.mi.UPTsmAddon;
import com.unionpay.tsmservice.mi.data.Constant;
import com.unionpay.tsmservice.mi.data.NinePatchInfo;
import com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.mi.result.GetEncryptDataResult;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes11.dex */
public class UPSaftyKeyboard {
    private boolean A;
    private boolean B;
    private boolean C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private Typeface J;
    private boolean K;
    private boolean L;
    private boolean M;
    private OnShowListener N;
    private OnHideListener O;
    private OnEditorListener P;
    private OnOutsideTouchListener Q;
    private OnConfirmClickListener R;
    private OnSafetyKeyboardCallback.Stub S;
    private UPTsmAddon.UPTsmConnectionListener T;
    private Handler.Callback U;
    private final Handler V;
    private Context a;
    private UPTsmAddon b;

    /* renamed from: c  reason: collision with root package name */
    private int f18210c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private String f18211e;

    /* renamed from: f  reason: collision with root package name */
    private int f18212f;

    /* renamed from: g  reason: collision with root package name */
    private int f18213g;

    /* renamed from: h  reason: collision with root package name */
    private int f18214h;

    /* renamed from: i  reason: collision with root package name */
    private int f18215i;

    /* renamed from: j  reason: collision with root package name */
    private int f18216j;

    /* renamed from: k  reason: collision with root package name */
    private int f18217k;

    /* renamed from: l  reason: collision with root package name */
    private int f18218l;

    /* renamed from: m  reason: collision with root package name */
    private int f18219m;

    /* renamed from: n  reason: collision with root package name */
    private int f18220n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private boolean z;

    /* loaded from: classes11.dex */
    public interface OnConfirmClickListener {
        void onConfirmClick();
    }

    /* loaded from: classes11.dex */
    public interface OnEditorListener {
        void onEditorChanged(int i2);
    }

    /* loaded from: classes11.dex */
    public interface OnHideListener {
        void onHide();
    }

    /* loaded from: classes11.dex */
    public interface OnOutsideTouchListener {
        void onTouch(float f2, float f3);
    }

    /* loaded from: classes11.dex */
    public interface OnShowListener {
        void onShow();
    }

    /* loaded from: classes11.dex */
    class a extends OnSafetyKeyboardCallback.Stub {
        a() {
        }

        @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
        public final void onConfirmClicked() throws RemoteException {
            UPSaftyKeyboard.this.V.sendEmptyMessage(4);
        }

        @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
        public final void onEditorChanged(int i2) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.arg1 = i2;
            UPSaftyKeyboard.this.V.sendMessage(obtain);
        }

        @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
        public final void onHide() throws RemoteException {
            UPSaftyKeyboard.this.V.sendEmptyMessage(1);
        }

        @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
        public final void onOutsideTouch(float f2, float f3) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = 3;
            Bundle bundle = new Bundle();
            bundle.putFloat("coordinateX", f2);
            bundle.putFloat("coordinateY", f3);
            obtain.setData(bundle);
            UPSaftyKeyboard.this.V.sendMessage(obtain);
        }

        @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
        public final void onShow() throws RemoteException {
            UPSaftyKeyboard.this.V.sendEmptyMessage(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class b extends FutureTask<String> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public class a extends ITsmCallback.Stub {
            a() {
            }

            @Override // com.unionpay.tsmservice.mi.ITsmCallback
            public final void onError(String str, String str2) throws RemoteException {
                b.this.set("");
            }

            @Override // com.unionpay.tsmservice.mi.ITsmCallback
            public final void onResult(Bundle bundle) throws RemoteException {
                bundle.setClassLoader(GetEncryptDataResult.class.getClassLoader());
                b.this.set(((GetEncryptDataResult) bundle.get("result")).getData());
            }
        }

        public b() {
            super(new Callable<String>() { // from class: com.unionpay.tsmservice.mi.widget.UPSaftyKeyboard.b.1
                @Override // java.util.concurrent.Callable
                public final /* synthetic */ String call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a(TimeUnit timeUnit) {
            try {
                try {
                    return get(2000L, timeUnit);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    cancel(true);
                    return "";
                } catch (ExecutionException e3) {
                    e3.printStackTrace();
                    cancel(true);
                    return "";
                } catch (TimeoutException e4) {
                    e4.printStackTrace();
                    cancel(true);
                    return "";
                }
            } finally {
                cancel(true);
            }
        }

        static /* synthetic */ void a(b bVar, String str) {
            GetEncryptDataRequestParams getEncryptDataRequestParams = new GetEncryptDataRequestParams();
            getEncryptDataRequestParams.setPan(str);
            getEncryptDataRequestParams.setType(UPSaftyKeyboard.this.f18210c);
            try {
                UPSaftyKeyboard.this.b.getEncryptData(getEncryptDataRequestParams, new a());
            } catch (RemoteException e2) {
                e2.printStackTrace();
                bVar.set("");
            }
        }
    }

    public UPSaftyKeyboard(Context context, int i2) throws RemoteException {
        this(context, i2, null);
    }

    public UPSaftyKeyboard(Context context, int i2, Drawable drawable) throws RemoteException {
        this.a = null;
        this.f18212f = -1;
        this.f18213g = -1;
        this.f18214h = -1;
        this.f18215i = -1;
        this.f18216j = -1;
        this.f18217k = -1;
        this.f18218l = -1;
        this.f18219m = -1;
        this.f18220n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = 0;
        this.x = 0;
        this.y = 1;
        this.z = false;
        this.A = false;
        this.B = true;
        this.C = false;
        this.D = -1;
        this.E = -1;
        this.F = -1;
        this.G = -1;
        this.H = -1;
        this.I = -16777216;
        this.K = false;
        this.L = true;
        this.M = true;
        this.U = new Handler.Callback() { // from class: com.unionpay.tsmservice.mi.widget.UPSaftyKeyboard.1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i3 = message.what;
                if (i3 == 0) {
                    if (UPSaftyKeyboard.this.N != null) {
                        UPSaftyKeyboard.this.N.onShow();
                    }
                    return true;
                } else if (i3 == 1) {
                    if (UPSaftyKeyboard.this.O != null) {
                        UPSaftyKeyboard.this.O.onHide();
                    }
                    UPSaftyKeyboard.c(UPSaftyKeyboard.this);
                    return true;
                } else if (i3 == 2) {
                    if (UPSaftyKeyboard.this.P != null) {
                        UPSaftyKeyboard.this.d = message.arg1;
                        UPSaftyKeyboard.this.P.onEditorChanged(UPSaftyKeyboard.this.d);
                    }
                    return true;
                } else if (i3 != 3) {
                    if (i3 != 4) {
                        return false;
                    }
                    if (UPSaftyKeyboard.this.R != null) {
                        UPSaftyKeyboard.this.R.onConfirmClick();
                    }
                    return true;
                } else {
                    if (UPSaftyKeyboard.this.Q != null) {
                        Bundle data = message.getData();
                        UPSaftyKeyboard.this.Q.onTouch(data.getFloat("coordinateX"), data.getFloat("coordinateY"));
                    }
                    return true;
                }
            }
        };
        this.V = new Handler(Looper.getMainLooper(), this.U);
        this.a = context;
        this.f18210c = i2;
        if (i2 < 2000 || i2 > 2002) {
            throw new IllegalArgumentException("Type is error");
        }
        UPTsmAddon uPTsmAddon = UPTsmAddon.getInstance(context);
        this.b = uPTsmAddon;
        if (uPTsmAddon.isConnected()) {
            a();
        } else {
            UPTsmAddon.UPTsmConnectionListener uPTsmConnectionListener = new UPTsmAddon.UPTsmConnectionListener() { // from class: com.unionpay.tsmservice.mi.widget.UPSaftyKeyboard.2
                @Override // com.unionpay.tsmservice.mi.UPTsmAddon.UPTsmConnectionListener
                public final void onTsmConnected() {
                    UPSaftyKeyboard.this.a();
                }

                @Override // com.unionpay.tsmservice.mi.UPTsmAddon.UPTsmConnectionListener
                public final void onTsmDisconnected() {
                }
            };
            this.T = uPTsmConnectionListener;
            this.b.addConnectionListener(uPTsmConnectionListener);
            this.b.bind();
        }
        if (drawable != null) {
            try {
                setKeyboardBackground(drawable);
            } catch (KeyboardDrawableErrorException e2) {
                e2.printStackTrace();
            }
        }
    }

    private String a(String str) {
        b bVar = new b();
        b.a(bVar, str);
        return bVar.a(TimeUnit.MILLISECONDS);
    }

    private static ArrayList<Bitmap> a(Drawable[] drawableArr) {
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        for (Drawable drawable : drawableArr) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                arrayList.add(bitmapDrawable.getBitmap());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        UPTsmAddon uPTsmAddon = this.b;
        if (uPTsmAddon != null) {
            try {
                uPTsmAddon.clearEncryptData(this.f18210c);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void a(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        int c2 = c(drawable);
        if (c2 == -1) {
            throw new KeyboardDrawableErrorException();
        }
        SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
        if (c2 == 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.KEY_DONE_FORE_BITMAP, ((BitmapDrawable) drawable).getBitmap());
            safetyKeyboardRequestParams.setParams(bundle);
        } else if (c2 == 1) {
            throw new KeyboardDrawableErrorException();
        }
        a(safetyKeyboardRequestParams);
    }

    private void a(SafetyKeyboardRequestParams safetyKeyboardRequestParams) throws RemoteException {
        this.b.setSafetyKeyboardBitmap(safetyKeyboardRequestParams);
    }

    private void b(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        int c2 = c(drawable);
        if (c2 == -1) {
            throw new KeyboardDrawableErrorException();
        }
        SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
        if (c2 == 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.KEY_DEL_FORE_BITMAP, ((BitmapDrawable) drawable).getBitmap());
            safetyKeyboardRequestParams.setParams(bundle);
        } else if (c2 == 1) {
            throw new KeyboardDrawableErrorException();
        }
        a(safetyKeyboardRequestParams);
    }

    private static int c(Drawable drawable) {
        if (drawable == null) {
            return -1;
        }
        if (drawable instanceof BitmapDrawable) {
            return 0;
        }
        if (drawable instanceof ColorDrawable) {
            return 1;
        }
        return drawable instanceof NinePatchDrawable ? 2 : -1;
    }

    static /* synthetic */ OnSafetyKeyboardCallback.Stub c(UPSaftyKeyboard uPSaftyKeyboard) {
        uPSaftyKeyboard.S = null;
        return null;
    }

    private static NinePatchInfo d(Drawable drawable) {
        NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) drawable;
        NinePatchInfo ninePatchInfo = new NinePatchInfo();
        Rect rect = new Rect();
        ninePatchDrawable.getPadding(rect);
        ninePatchInfo.setPadding(rect);
        Drawable.ConstantState constantState = ninePatchDrawable.getConstantState();
        try {
            Field declaredField = Class.forName("android.graphics.drawable.NinePatchDrawable$NinePatchState").getDeclaredField("mNinePatch");
            declaredField.setAccessible(true);
            Bitmap bitmap = (Bitmap) Class.forName("android.graphics.NinePatch").getDeclaredMethod("getBitmap", new Class[0]).invoke(declaredField.get(constantState), new Object[0]);
            ninePatchInfo.setBitmap(bitmap);
            ninePatchInfo.setChunk(bitmap.getNinePatchChunk());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return ninePatchInfo;
    }

    public boolean cancelPay() {
        int i2;
        try {
            i2 = this.b.cancelPay();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            i2 = -5;
        }
        return i2 == 0;
    }

    public synchronized boolean clearPwd() {
        this.d = 0;
        int i2 = -5;
        try {
            i2 = this.b.clearEncryptData(this.f18210c);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return i2 == 0;
    }

    public void enableDismissOnConfirmClick(boolean z) {
        this.M = z;
    }

    public void enableDismissOnOutsideTouch(boolean z) {
        this.L = z;
    }

    public void enableLightStatusBar(boolean z) {
        this.K = z;
    }

    public int getCurrentPinLength() {
        return this.d;
    }

    public String getInput() {
        return a("");
    }

    public String getInput(String str) {
        return this.f18210c != 2000 ? "" : a(str);
    }

    public boolean hide() {
        int i2;
        try {
            i2 = this.b.hideKeyboard();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            i2 = -5;
        }
        return i2 == 0;
    }

    public void setConfirmBtnOutPaddingRight(int i2) {
        this.v = i2;
    }

    public void setConfirmBtnSize(int i2, int i3) {
        this.f18214h = i2;
        this.f18215i = i3;
    }

    public void setDelKeyDrawable(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        if (drawable != null) {
            b(drawable);
        }
    }

    public void setDelKeyDrawable(Drawable drawable, Drawable drawable2) throws KeyboardDrawableErrorException, RemoteException {
        Bundle bundle;
        if (drawable != null) {
            b(drawable);
        }
        if (drawable2 != null) {
            int c2 = c(drawable2);
            if (c2 == -1) {
                throw new KeyboardDrawableErrorException();
            }
            SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
            if (c2 == 0) {
                bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_DEL_BG_BITMAP, ((BitmapDrawable) drawable2).getBitmap());
                bundle.putInt(Constant.KEY_DEL_BG_COLOR, -1);
            } else if (c2 != 1) {
                if (c2 == 2) {
                    NinePatchInfo d = d(drawable2);
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable(Constant.KEY_DEL_BG_NINE_PATCH, d);
                    safetyKeyboardRequestParams.setParams(bundle2);
                }
                a(safetyKeyboardRequestParams);
            } else {
                bundle = new Bundle();
                bundle.putInt(Constant.KEY_DEL_BG_COLOR, ((ColorDrawable) drawable2).getColor());
            }
            safetyKeyboardRequestParams.setParams(bundle);
            a(safetyKeyboardRequestParams);
        }
    }

    public void setDoneKeyDrawable(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        if (drawable != null) {
            a(drawable);
        }
    }

    public void setDoneKeyDrawable(Drawable drawable, Drawable drawable2) throws KeyboardDrawableErrorException, RemoteException {
        Bundle bundle;
        if (drawable != null) {
            a(drawable);
        }
        if (drawable2 != null) {
            int c2 = c(drawable2);
            if (c2 == -1) {
                throw new KeyboardDrawableErrorException();
            }
            SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
            if (c2 == 0) {
                bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_DONE_BG_BITMAP, ((BitmapDrawable) drawable2).getBitmap());
                bundle.putInt(Constant.KEY_DONE_BG_COLOR, -1);
            } else if (c2 != 1) {
                if (c2 == 2) {
                    NinePatchInfo d = d(drawable2);
                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelable(Constant.KEY_DONE_BG_NINE_PATCH, d);
                    safetyKeyboardRequestParams.setParams(bundle2);
                }
                a(safetyKeyboardRequestParams);
            } else {
                bundle = new Bundle();
                bundle.putInt(Constant.KEY_DONE_BG_COLOR, ((ColorDrawable) drawable2).getColor());
            }
            safetyKeyboardRequestParams.setParams(bundle);
            a(safetyKeyboardRequestParams);
        }
    }

    public void setDoneKeyEnable(boolean z) {
        this.B = z;
    }

    public void setDoneKeyRightMode(boolean z) {
        this.A = z;
    }

    public void setKeyAreaPadding(int i2, int i3, int i4, int i5) {
        this.q = i2;
        this.r = i3;
        this.s = i4;
        this.t = i5;
    }

    public void setKeyBoardSize(int i2, int i3) {
        this.f18212f = i2;
        this.f18213g = i3;
    }

    public void setKeyboardAudio(boolean z) {
        this.z = z;
    }

    public void setKeyboardBackground(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        Bundle bundle;
        int c2 = c(drawable);
        if (c2 == -1) {
            throw new KeyboardDrawableErrorException();
        }
        SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
        if (c2 == 0) {
            bundle = new Bundle();
            bundle.putParcelable(Constant.KEY_KEYBOARD_BG_BITMAP, ((BitmapDrawable) drawable).getBitmap());
            bundle.putInt(Constant.KEY_KEYBOARD_BG_COLOR, -1);
        } else if (c2 != 1) {
            if (c2 == 2) {
                NinePatchInfo d = d(drawable);
                bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_KEYBOARD_BG_NINE_PATCH, d);
            }
            a(safetyKeyboardRequestParams);
        } else {
            bundle = new Bundle();
            bundle.putInt(Constant.KEY_KEYBOARD_BG_COLOR, ((ColorDrawable) drawable).getColor());
        }
        safetyKeyboardRequestParams.setParams(bundle);
        a(safetyKeyboardRequestParams);
    }

    public void setKeyboardPadding(int i2, int i3, int i4, int i5) {
        this.f18219m = i2;
        this.f18220n = i3;
        this.o = i4;
        this.p = i5;
    }

    public void setKeyboardStartPosition(int i2, int i3) {
        this.w = i2;
        this.x = i3;
        this.y = 0;
    }

    public void setKeyboardVibrate(boolean z) {
        this.C = z;
    }

    public void setNumKeyBackgroud(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        Bundle bundle;
        int c2 = c(drawable);
        if (c2 == -1) {
            throw new KeyboardDrawableErrorException();
        }
        SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
        if (c2 == 0) {
            bundle = new Bundle();
            bundle.putParcelable(Constant.KEY_NUM_BG_BITMAP, ((BitmapDrawable) drawable).getBitmap());
            bundle.putInt(Constant.KEY_NUM_BG_COLOR, -1);
        } else if (c2 != 1) {
            if (c2 == 2) {
                NinePatchInfo d = d(drawable);
                bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_NUM_BG_NINE_PATCH, d);
            }
            a(safetyKeyboardRequestParams);
        } else {
            bundle = new Bundle();
            bundle.putInt(Constant.KEY_NUM_BG_COLOR, ((ColorDrawable) drawable).getColor());
        }
        safetyKeyboardRequestParams.setParams(bundle);
        a(safetyKeyboardRequestParams);
    }

    public void setNumKeyMargin(int i2, int i3) {
        this.f18217k = i2;
        this.f18218l = i3;
    }

    public void setNumberKeyColor(int i2) {
        this.I = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setNumberKeyDrawable(android.graphics.drawable.Drawable[] r6) throws com.unionpay.tsmservice.mi.widget.KeyboardDrawableErrorException, android.os.RemoteException {
        /*
            r5 = this;
            r0 = 0
            r1 = -1
            if (r6 == 0) goto L16
            int r2 = r6.length
            if (r2 > 0) goto L8
            goto L16
        L8:
            int r2 = r6.length
            r3 = 0
        La:
            if (r3 >= r2) goto L17
            r4 = r6[r3]
            boolean r4 = r4 instanceof android.graphics.drawable.BitmapDrawable
            if (r4 != 0) goto L13
            goto L16
        L13:
            int r3 = r3 + 1
            goto La
        L16:
            r0 = -1
        L17:
            if (r0 != 0) goto L33
            com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams r0 = new com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams
            r0.<init>()
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.util.ArrayList r6 = a(r6)
            java.lang.String r2 = "numForeBitmaps"
            r1.putParcelableArrayList(r2, r6)
            r0.setParams(r1)
            r5.a(r0)
            return
        L33:
            com.unionpay.tsmservice.mi.widget.KeyboardDrawableErrorException r6 = new com.unionpay.tsmservice.mi.widget.KeyboardDrawableErrorException
            r6.<init>()
            goto L3a
        L39:
            throw r6
        L3a:
            goto L39
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.widget.UPSaftyKeyboard.setNumberKeyDrawable(android.graphics.drawable.Drawable[]):void");
    }

    public void setNumberKeySize(int i2) {
        this.u = i2;
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.R = onConfirmClickListener;
    }

    public void setOnEditorListener(OnEditorListener onEditorListener) {
        this.P = onEditorListener;
    }

    public void setOnHideListener(OnHideListener onHideListener) {
        this.O = onHideListener;
    }

    public void setOnOutsideTouchListener(OnOutsideTouchListener onOutsideTouchListener) {
        this.Q = onOutsideTouchListener;
    }

    public void setOnShowListener(OnShowListener onShowListener) {
        this.N = onShowListener;
    }

    public void setTitleBackground(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        Bundle bundle;
        int c2 = c(drawable);
        if (c2 == -1) {
            throw new KeyboardDrawableErrorException();
        }
        SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
        if (c2 == 0) {
            bundle = new Bundle();
            bundle.putParcelable(Constant.KEY_TITLE_BG_BITMAP, ((BitmapDrawable) drawable).getBitmap());
            bundle.putInt(Constant.KEY_TITLE_BG_COLOR, -1);
        } else if (c2 != 1) {
            if (c2 == 2) {
                NinePatchInfo d = d(drawable);
                bundle = new Bundle();
                bundle.putParcelable(Constant.KEY_TITLE_BG_NINE_PATCH, d);
            }
            a(safetyKeyboardRequestParams);
        } else {
            bundle = new Bundle();
            bundle.putInt(Constant.KEY_TITLE_BG_COLOR, ((ColorDrawable) drawable).getColor());
        }
        safetyKeyboardRequestParams.setParams(bundle);
        a(safetyKeyboardRequestParams);
    }

    public void setTitleColor(int i2) {
        this.G = i2;
    }

    public void setTitleConfirmDrawable(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        int c2 = c(drawable);
        if (c2 == -1) {
            throw new KeyboardDrawableErrorException();
        }
        SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
        if (c2 == 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.KEY_TITLE_DROP_BITMAP, ((BitmapDrawable) drawable).getBitmap());
            safetyKeyboardRequestParams.setParams(bundle);
        } else if (c2 == 1) {
            throw new KeyboardDrawableErrorException();
        }
        a(safetyKeyboardRequestParams);
    }

    public void setTitleDrawable(Drawable drawable) throws KeyboardDrawableErrorException, RemoteException {
        int c2 = c(drawable);
        if (c2 == -1) {
            throw new KeyboardDrawableErrorException();
        }
        SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
        if (c2 == 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.KEY_TITLE_ICON_BITMAP, ((BitmapDrawable) drawable).getBitmap());
            safetyKeyboardRequestParams.setParams(bundle);
        } else if (c2 == 1) {
            throw new KeyboardDrawableErrorException();
        }
        a(safetyKeyboardRequestParams);
    }

    public void setTitleDrawablePadding(int i2) {
        this.F = i2;
    }

    public void setTitleDrawableSize(int i2, int i3) {
        this.D = i2;
        this.E = i3;
    }

    public void setTitleFont(Typeface typeface) {
        this.J = typeface;
    }

    public void setTitleHeight(int i2) {
        this.f18216j = i2;
    }

    public void setTitleSize(int i2) {
        this.H = i2;
    }

    public void setTitleText(String str) {
        this.f18211e = str;
    }

    public synchronized boolean show() {
        if (this.S == null) {
            this.S = new a();
            try {
                SafetyKeyboardRequestParams safetyKeyboardRequestParams = new SafetyKeyboardRequestParams();
                Bundle bundle = new Bundle();
                bundle.putString("title", this.f18211e);
                bundle.putInt("width", this.f18212f);
                bundle.putInt("height", this.f18213g);
                bundle.putInt(Constant.KEY_CONFIRMBTN_WIDTH, this.f18214h);
                bundle.putInt(Constant.KEY_CONFIRMBTN_HEIGHT, this.f18215i);
                bundle.putInt(Constant.KEY_TITLE_HEIGHT, this.f18216j);
                bundle.putInt("row", this.f18217k);
                bundle.putInt(Constant.KEY_COL, this.f18218l);
                bundle.putInt(Constant.KEY_OUT_LEFT, this.f18219m);
                bundle.putInt(Constant.KEY_OUT_RIGHT, this.o);
                bundle.putInt(Constant.KEY_OUT_TOP, this.f18220n);
                bundle.putInt(Constant.KEY_OUT_BOTTOM, this.p);
                bundle.putInt(Constant.KEY_INNER_LEFT, this.q);
                bundle.putInt(Constant.KEY_INNER_RIGHT, this.s);
                bundle.putInt(Constant.KEY_INNER_TOP, this.r);
                bundle.putInt(Constant.KEY_INNER_BOTTOM, this.t);
                bundle.putInt(Constant.KEY_CONFIRMBTN_RIGHT, this.v);
                bundle.putInt(Constant.KEY_STARTPOSITION_X, this.w);
                bundle.putInt(Constant.KEY_STARTPOSITION_Y, this.x);
                bundle.putInt(Constant.KEY_IS_DEFAULTPOSITION, this.y);
                bundle.putInt(Constant.KEY_NUM_SIZE, this.u);
                bundle.putInt(Constant.KEY_IS_AUDIO, this.z ? 1 : 0);
                bundle.putInt(Constant.KEY_IS_VIBRATE, this.C ? 1 : 0);
                bundle.putInt(Constant.KEY_DONE_RIGHT, this.A ? 1 : 0);
                bundle.putInt(Constant.KEY_ENABLE_OKBTN, this.B ? 1 : 0);
                bundle.putInt(Constant.KEY_SECURE_WIDTH, this.D);
                bundle.putInt(Constant.KEY_SECURE_HEIGHT, this.E);
                bundle.putInt(Constant.KEY_TITLE_DRAWABLE_PADDING, this.F);
                bundle.putInt(Constant.KEY_TITLE_COLOR, this.G);
                bundle.putInt(Constant.KEY_TITLE_SIZE, this.H);
                bundle.putInt(Constant.KEY_NUMBER_KEYCOLOR, this.I);
                Typeface typeface = this.J;
                if (typeface != null) {
                    bundle.putInt(Constant.KEY_TITLE_FONT, typeface.getStyle());
                }
                bundle.putBoolean(Constant.KEY_ENABLE_LIGHT_STATUS_BAR, this.K);
                bundle.putBoolean(Constant.KEY_ENABLE_DISMISS_ON_OUTSIDE_TOUCH, this.L);
                bundle.putBoolean(Constant.KEY_ENABLE_DISMISS_ON_CONFIRM_CLICK, this.M);
                safetyKeyboardRequestParams.setParams(bundle);
                if (this.b.showSafetyKeyboard(safetyKeyboardRequestParams, this.f18210c, this.S, this.a) != 0) {
                    this.S = null;
                    return false;
                }
                return true;
            } catch (RemoteException e2) {
                e2.printStackTrace();
                this.S = null;
                return false;
            }
        }
        return false;
    }
}
