package com.jdpay.membercode.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jdpay.bury.SessionPack;
import com.jdpay.json.JsonAdapter;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.membercode.JDPayMemberCode;
import com.jdpay.membercode.MemberCodeException;
import com.jdpay.membercode.MemberCodeInteractor;
import com.jdpay.membercode.R;
import com.jdpay.membercode.bean.CodeEnterInfoBean;
import com.jdpay.membercode.bean.CodeInfoBean;
import com.jdpay.membercode.bean.CodeOpenInfoBean;
import com.jdpay.membercode.bean.CodeResultInfoBean;
import com.jdpay.membercode.bean.CodeVerifyInfoBean;
import com.jdpay.membercode.bean.CommonVerifyResultBean;
import com.jdpay.membercode.bean.OpenFastPayPageInfoBean;
import com.jdpay.membercode.bean.QueryVerifyPayWay;
import com.jdpay.membercode.network.JDPayCheckErrorInfoBean;
import com.jdpay.membercode.network.JDPayResultCtrlBean;
import com.jdpay.membercode.network.ResponseBean;
import com.jdpay.membercode.outer.CommonVerify;
import com.jdpay.net.ResultObserver;
import com.jdpay.util.JPMCMonitor;
import com.jdpay.util.Utils;
import com.jdpay.widget.dialog.CPDialog;
import com.jdpay.widget.dialog.CPProgressDialog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class CodeView extends LinearLayout implements com.jdpay.membercode.c {
    public static final int REQUEST_CODE_ACTIVATE = 1001;
    public static final int REQUEST_CODE_COMMON_VERIFY_OPEN_CODE = 1010;
    public static final int REQUEST_CODE_COMMON_VERIFY_PAY_RESULT = 1011;
    public static final int REQUEST_CODE_COMMON_VERIFY_SHOW_CODE = 1012;
    public static final int REQUEST_CODE_NONE = 0;
    public static final int REQUEST_CODE_UPDATE = 1000;
    private final com.jdpay.membercode.widget.d a;
    private final com.jdpay.membercode.widget.a b;

    /* renamed from: c  reason: collision with root package name */
    private final com.jdpay.membercode.widget.e f7543c;
    private final com.jdpay.membercode.widget.c d;

    /* renamed from: e  reason: collision with root package name */
    private final com.jdpay.membercode.e.c f7544e;

    /* renamed from: f  reason: collision with root package name */
    private final com.jdpay.membercode.e.b f7545f;

    /* renamed from: g  reason: collision with root package name */
    private final InteractorDelegate f7546g;

    /* renamed from: h  reason: collision with root package name */
    private CPProgressDialog f7547h;

    /* renamed from: i  reason: collision with root package name */
    private CPDialog f7548i;

    /* renamed from: j  reason: collision with root package name */
    private CodeEnterInfoBean f7549j;

    /* renamed from: k  reason: collision with root package name */
    private volatile boolean f7550k;

    /* renamed from: l  reason: collision with root package name */
    private volatile long f7551l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f7552m;

    /* renamed from: n  reason: collision with root package name */
    private final View.OnClickListener f7553n;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class InteractorDelegate implements MemberCodeInteractor {
        private MemberCodeInteractor a;

        private InteractorDelegate() {
        }

        /* synthetic */ InteractorDelegate(CodeView codeView, a aVar) {
            this();
        }

        public void b() {
            JPMCMonitor.onEvent("MConSigned");
            CodeView.this.c();
            CodeView.this.b.j(false);
            CodeView.this.f7543c.j(false);
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public void onActivated() {
            JPMCMonitor.onEvent("MConActivated");
            CodeView.this.c();
            CodeView.this.b.j(false);
            CodeView.this.f7543c.j(false);
            MemberCodeInteractor memberCodeInteractor = this.a;
            if (memberCodeInteractor != null) {
                memberCodeInteractor.onActivated();
            }
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public void onError(int i2, @Nullable Throwable th) {
            Resources resources;
            int i3;
            CharSequence exceptionMessage = CodeView.getExceptionMessage(th);
            if (TextUtils.isEmpty(exceptionMessage)) {
                if (i2 == 1) {
                    resources = CodeView.this.getResources();
                    i3 = R.string.jdpay_mb_err_network;
                } else if (i2 == 2) {
                    resources = CodeView.this.getResources();
                    i3 = R.string.jdpay_mb_err_activate;
                } else if (i2 == 3) {
                    resources = CodeView.this.getResources();
                    i3 = R.string.jdpay_mb_err_inactivate;
                }
                exceptionMessage = resources.getText(i3);
            }
            JPMCMonitor.e("MC error tpye:" + i2 + " text:" + ((Object) exceptionMessage));
            if (i2 == 1) {
                CodeView.this.a(exceptionMessage);
            } else if (!TextUtils.isEmpty(exceptionMessage)) {
                CodeView.this.f7546g.onToast(exceptionMessage);
            }
            MemberCodeInteractor memberCodeInteractor = this.a;
            if (memberCodeInteractor != null) {
                memberCodeInteractor.onError(i2, th);
            }
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public void onInactivated() {
            JPMCMonitor.onEvent("MConInactivated");
            CodeView.this.a.s();
            CodeView.this.c();
            MemberCodeInteractor memberCodeInteractor = this.a;
            if (memberCodeInteractor != null) {
                memberCodeInteractor.onInactivated();
            }
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public void onInited() {
            JPMCMonitor.onEvent("MConlnited");
            CodeView.this.c();
            MemberCodeInteractor memberCodeInteractor = this.a;
            if (memberCodeInteractor != null) {
                memberCodeInteractor.onInited();
            }
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public boolean onToast(@NonNull CharSequence charSequence) {
            MemberCodeInteractor memberCodeInteractor = this.a;
            boolean z = memberCodeInteractor != null && memberCodeInteractor.onToast(charSequence);
            if (!CodeView.this.f7550k && !z) {
                Toast.makeText(CodeView.this.getContext(), charSequence, 0).show();
            }
            return true;
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public boolean startActivityForResult(@NonNull Intent intent, int i2) {
            MemberCodeInteractor memberCodeInteractor = this.a;
            boolean z = memberCodeInteractor != null && memberCodeInteractor.startActivityForResult(intent, i2);
            if (!CodeView.this.f7550k && !z) {
                Context context = CodeView.this.getContext();
                if (context instanceof Activity) {
                    ((Activity) context).startActivityForResult(intent, i2);
                } else {
                    context.startActivity(intent);
                }
            }
            return true;
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public boolean startBrowser(@NonNull String str, int i2) {
            MemberCodeInteractor memberCodeInteractor;
            JPMCMonitor.d(CodeView.this + " isDestroy:" + CodeView.this.f7550k);
            return CodeView.this.f7550k || ((memberCodeInteractor = this.a) != null && memberCodeInteractor.startBrowser(str, i2));
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public boolean startCommonVerifyModule(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, int i2) {
            MemberCodeInteractor memberCodeInteractor;
            return CodeView.this.f7550k || ((memberCodeInteractor = this.a) != null && memberCodeInteractor.startCommonVerifyModule(str, str2, str4, str3, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CodeView codeView = CodeView.this;
            codeView.measure(View.MeasureSpec.makeMeasureSpec(codeView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(CodeView.this.getHeight(), 1073741824));
            CodeView codeView2 = CodeView.this;
            codeView2.layout(codeView2.getLeft(), CodeView.this.getTop(), CodeView.this.getRight(), CodeView.this.getBottom());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class b implements ResultObserver<ResponseBean<QueryVerifyPayWay>> {
        b() {
        }

        @Override // com.jdpay.net.ResultObserver
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(@Nullable ResponseBean<QueryVerifyPayWay> responseBean) {
            CodeView.this.dismissLoadingDialog();
            if (responseBean != null) {
                QueryVerifyPayWay queryVerifyPayWay = responseBean.data;
                if (queryVerifyPayWay != null && "NEED_VERIFY".equals(queryVerifyPayWay.nextStep) && !TextUtils.isEmpty(responseBean.data.bizToken)) {
                    JPMCMonitor.i("MC need verify");
                    CodeView.this.a(responseBean.data.bizToken, 1011, 1);
                }
                if (responseBean.isSuccessful()) {
                    return;
                }
                JDPayResultCtrlBean jDPayResultCtrlBean = responseBean.ctrl;
                if (jDPayResultCtrlBean != null) {
                    CodeView.this.a(jDPayResultCtrlBean);
                    return;
                } else if (!TextUtils.isEmpty(responseBean.msg)) {
                    onFailure(new MemberCodeException(responseBean.msg));
                    return;
                }
            }
            onFailure(null);
        }

        @Override // com.jdpay.net.ResultObserver
        public void onFailure(@Nullable Throwable th) {
            CodeView.this.dismissLoadingDialog();
            JPMCMonitor.i("MC queryVerifyPayWay failed:" + th);
            CodeView.this.f7546g.onError(4, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Utils.showDialog(CodeView.this.f7547h);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Utils.dismissDialog(CodeView.this.f7547h);
        }
    }

    /* loaded from: classes18.dex */
    class e implements View.OnClickListener {
        e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Object tag = view.getTag();
            if (tag instanceof JDPayCheckErrorInfoBean) {
                JDPayCheckErrorInfoBean jDPayCheckErrorInfoBean = (JDPayCheckErrorInfoBean) tag;
                if (jDPayCheckErrorInfoBean.isUrl) {
                    CodeView.this.f7546g.startBrowser(jDPayCheckErrorInfoBean.url, 1000);
                }
            }
            CodeView.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class f implements ResultObserver<ResponseBean<CodeEnterInfoBean>> {
        int a;

        public f(int i2) {
            this.a = i2;
        }

        @Override // com.jdpay.net.ResultObserver
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(@Nullable ResponseBean<CodeEnterInfoBean> responseBean) {
            if (CodeView.this.f7550k) {
                return;
            }
            if (responseBean != null) {
                CodeView.this.dismissLoadingDialog();
                CodeEnterInfoBean codeEnterInfoBean = responseBean.data;
                if (codeEnterInfoBean != null) {
                    CodeView.this.f7549j = codeEnterInfoBean;
                    if (CodeView.this.f7549j.openPageInfo != null && CodeView.this.f7549j.openPageInfo.tips != null) {
                        ArrayList arrayList = new ArrayList(2);
                        for (String str : CodeView.this.f7549j.openPageInfo.tips) {
                            if (!TextUtils.isEmpty(str)) {
                                arrayList.add(str);
                            }
                        }
                        CodeView.this.f7549j.openPageInfo.tips = arrayList;
                    }
                    int i2 = this.a;
                    if (i2 == 1) {
                        CodeView.this.f7546g.onInited();
                    } else if (i2 == 6) {
                        CodeView.this.f7546g.b();
                    } else if (i2 == 2) {
                        CodeView.this.f7546g.onActivated();
                    } else if (i2 == 3) {
                        CodeView.this.f7546g.onInactivated();
                    }
                }
                if (responseBean.isSuccessful()) {
                    return;
                }
                JDPayResultCtrlBean jDPayResultCtrlBean = responseBean.ctrl;
                if (jDPayResultCtrlBean != null) {
                    CodeView.this.a(jDPayResultCtrlBean);
                    return;
                } else if (!TextUtils.isEmpty(responseBean.msg)) {
                    onFailure(new MemberCodeException(responseBean.msg));
                    return;
                }
            }
            onFailure(null);
        }

        @Override // com.jdpay.net.ResultObserver
        public void onFailure(@Nullable Throwable th) {
            if (CodeView.this.f7550k) {
                return;
            }
            JPMCMonitor.i("MC Error:" + Utils.getThrowableMessage(th));
            CodeView.this.dismissLoadingDialog();
            CodeView.this.f7546g.onError(this.a, th);
        }
    }

    public CodeView(Context context) {
        this(context, null, 0);
    }

    public CodeView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CodeView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        com.jdpay.membercode.widget.d dVar = new com.jdpay.membercode.widget.d(this);
        this.a = dVar;
        this.b = new com.jdpay.membercode.widget.a(this);
        this.f7543c = new com.jdpay.membercode.widget.e(this);
        this.d = new com.jdpay.membercode.widget.c(this);
        this.f7544e = new com.jdpay.membercode.e.c(dVar);
        this.f7545f = new com.jdpay.membercode.e.b(this);
        this.f7546g = new InteractorDelegate(this, null);
        this.f7553n = new e();
        JDPayMemberCode.init(context);
    }

    @RequiresApi(api = 21)
    public CodeView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        com.jdpay.membercode.widget.d dVar = new com.jdpay.membercode.widget.d(this);
        this.a = dVar;
        this.b = new com.jdpay.membercode.widget.a(this);
        this.f7543c = new com.jdpay.membercode.widget.e(this);
        this.d = new com.jdpay.membercode.widget.c(this);
        this.f7544e = new com.jdpay.membercode.e.c(dVar);
        this.f7545f = new com.jdpay.membercode.e.b(this);
        this.f7546g = new InteractorDelegate(this, null);
        this.f7553n = new e();
        JDPayMemberCode.init(context);
    }

    private CommonVerifyResultBean a(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra(CommonVerifyResultBean.KEY_RESULT);
            if (TextUtils.isEmpty(stringExtra)) {
                return null;
            }
            return (CommonVerifyResultBean) JsonAdapter.objectSafety(stringExtra, CommonVerifyResultBean.class);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        CPDialog cPDialog = this.f7548i;
        if (cPDialog != null) {
            if (cPDialog.isShowing()) {
                this.f7548i.cancel();
            }
            this.f7548i = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(@NonNull JDPayResultCtrlBean jDPayResultCtrlBean) {
        List<JDPayCheckErrorInfoBean> list;
        JDPayCheckErrorInfoBean jDPayCheckErrorInfoBean;
        if (this.f7550k) {
            return;
        }
        CPDialog cPDialog = this.f7548i;
        if ((cPDialog != null && cPDialog.isShowing()) || (list = jDPayResultCtrlBean.btnActions) == null || list.isEmpty()) {
            return;
        }
        CPDialog cPDialog2 = new CPDialog(getContext());
        this.f7548i = cPDialog2;
        cPDialog2.setTitle(jDPayResultCtrlBean.title);
        this.f7548i.setMsg(jDPayResultCtrlBean.content);
        JPMCMonitor.e("MC Ctrl:" + jDPayResultCtrlBean.title + " [" + jDPayResultCtrlBean.content + "]");
        int size = list.size();
        if (size != 1) {
            if (size == 2) {
                JDPayCheckErrorInfoBean jDPayCheckErrorInfoBean2 = list.get(0);
                this.f7548i.setOkButton(jDPayCheckErrorInfoBean2.text, this.f7553n, jDPayCheckErrorInfoBean2);
                jDPayCheckErrorInfoBean = list.get(1);
            }
            this.f7548i.show();
        }
        jDPayCheckErrorInfoBean = list.get(0);
        JDPayCheckErrorInfoBean jDPayCheckErrorInfoBean3 = jDPayCheckErrorInfoBean;
        this.f7548i.setOkButton(jDPayCheckErrorInfoBean3.text, this.f7553n, jDPayCheckErrorInfoBean3);
        this.f7548i.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i2, int i3) {
        CommonVerify commonVerify = CommonVerify.getInstance();
        JPMCMonitor.i("MC start commonverify:" + commonVerify.isVerifying());
        if (commonVerify.isVerifying()) {
            return;
        }
        String str2 = JDPayMemberCode.getService().b;
        String str3 = JDPayMemberCode.getService().f7522c;
        commonVerify.setFrom(i3);
        commonVerify.verify(str3, "customer", str, str2, i2, this.f7546g);
    }

    private boolean b() {
        CodeEnterInfoBean codeEnterInfoBean = this.f7549j;
        return (codeEnterInfoBean == null || TextUtils.isEmpty(codeEnterInfoBean.nextStep) || "FINISH".equals(this.f7549j.nextStep)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        CodeEnterInfoBean data = getData();
        if (this.f7550k || data == null) {
            return;
        }
        if (!data.isNeedOpen && data.isSigned && b()) {
            JPMCMonitor.i("MC onDataChanged verify");
            a(data.bizToken, 1012, 2);
        }
        if (data.isNeedOpen) {
            e();
        } else if (data.isSigned) {
            f();
        } else {
            g();
        }
    }

    private void d() {
        if (this.f7550k) {
            return;
        }
        showLoadingDialog();
        JDPayMemberCode.getService().e(new b());
    }

    public static CharSequence getExceptionMessage(@Nullable Throwable th) {
        return Utils.getThrowableMessage(th);
    }

    private void h() {
        post(new a());
    }

    void a(@Nullable CharSequence charSequence) {
        stopScheduler();
        if (this.f7550k) {
            return;
        }
        this.a.e();
        this.b.e();
        this.f7543c.e();
        this.d.c();
        this.d.g(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(@NonNull String str, int i2) {
        this.f7546g.startBrowser(str, i2);
    }

    public void activateCode(boolean z) {
        CodeEnterInfoBean codeEnterInfoBean;
        if (this.f7550k || (codeEnterInfoBean = this.f7549j) == null || codeEnterInfoBean.verifyInfo == null) {
            return;
        }
        JPMCMonitor.onEvent("MCactivate", "isNeedVerify:" + z + " Check:" + b());
        if (z && b()) {
            a(this.f7549j.bizToken, 1010, 2);
            return;
        }
        showLoadingDialog();
        CodeEnterInfoBean codeEnterInfoBean2 = this.f7549j;
        CodeVerifyInfoBean codeVerifyInfoBean = codeEnterInfoBean2.verifyInfo;
        JDPayMemberCode.getService().d(codeEnterInfoBean2.bizToken, codeVerifyInfoBean.signResult, codeVerifyInfoBean.payWayType, new f(2));
    }

    public void activateFastPay(@Nullable OpenFastPayPageInfoBean openFastPayPageInfoBean) {
        JPMCMonitor.i("activateFastPay");
        if (this.f7550k || openFastPayPageInfoBean == null || TextUtils.isEmpty(openFastPayPageInfoBean.protocol.url)) {
            this.f7546g.onToast(getResources().getText(R.string.jdpay_mb_err_miss_params));
        } else {
            this.f7546g.startBrowser(openFastPayPageInfoBean.protocol.url, 1000);
        }
    }

    public void destroy() {
        JPMCMonitor.i("destroy");
        JDPayMemberCode.getService().f7524f = "0";
        CommonVerify.getInstance().setVerifying(false);
        this.f7550k = true;
        dismissLoadingDialog();
        a();
        this.f7544e.a();
        this.f7545f.a();
        this.a.d();
        this.b.d();
        this.d.d();
    }

    public void dismissLoadingDialog() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            post(new d());
        } else {
            Utils.dismissDialog(this.f7547h);
        }
    }

    void e() {
        CodeOpenInfoBean codeOpenInfoBean;
        stopScheduler();
        if (this.f7550k) {
            return;
        }
        this.a.e();
        this.d.e();
        this.f7543c.e();
        this.b.c();
        CodeEnterInfoBean codeEnterInfoBean = this.f7549j;
        if (codeEnterInfoBean == null || (codeOpenInfoBean = codeEnterInfoBean.openPageInfo) == null) {
            return;
        }
        this.b.h(codeOpenInfoBean);
    }

    void f() {
        CodeInfoBean codeInfoBean;
        if (this.f7550k) {
            return;
        }
        this.b.e();
        this.f7543c.e();
        this.d.e();
        this.a.c();
        CodeEnterInfoBean codeEnterInfoBean = this.f7549j;
        if (codeEnterInfoBean != null && (codeInfoBean = codeEnterInfoBean.code) != null) {
            this.a.k(false, codeInfoBean);
        }
        startScheduler();
    }

    public void forbiddenScreenCapture(@NonNull Window window) {
        com.jdpay.membercode.f.f.a(window);
    }

    void g() {
        CodeOpenInfoBean codeOpenInfoBean;
        stopScheduler();
        if (this.f7550k) {
            return;
        }
        this.a.e();
        this.d.e();
        this.b.e();
        this.f7543c.c();
        CodeEnterInfoBean codeEnterInfoBean = this.f7549j;
        if (codeEnterInfoBean == null || (codeOpenInfoBean = codeEnterInfoBean.openPageInfo) == null) {
            return;
        }
        this.f7543c.h(codeOpenInfoBean);
    }

    CodeEnterInfoBean getData() {
        return this.f7549j;
    }

    public void inactivateCode() {
        if (this.f7550k) {
            return;
        }
        JPMCMonitor.onEvent("MCinactivate");
        showLoadingDialog();
        JDPayMemberCode.getService().h(new f(3));
    }

    public void init() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f7550k || currentTimeMillis - this.f7551l < 2000) {
            JPMCMonitor.d("Ignore init");
            return;
        }
        if (!this.f7552m) {
            JPMCMonitor.startSession(JPMCMonitor.initSession().with("sessionKey", JDPayMemberCode.getService().b).with(SessionPack.KEY_APP_SOURCE, JDPayMemberCode.getService().f7522c).with("mode", "Native"));
            this.f7552m = true;
        }
        JPMCMonitor.onEvent("MCentrance");
        this.f7551l = currentTimeMillis;
        showLoadingDialog();
        JDPayMemberCode.getService().a(new f(1));
    }

    public boolean isCodeActivated() {
        CodeEnterInfoBean codeEnterInfoBean = this.f7549j;
        return (codeEnterInfoBean == null || !codeEnterInfoBean.isSigned || codeEnterInfoBean.isNeedOpen) ? false : true;
    }

    public boolean isCodeAvailable() {
        CodeEnterInfoBean codeEnterInfoBean = this.f7549j;
        return codeEnterInfoBean != null && "FINISH".equals(codeEnterInfoBean.nextStep);
    }

    public boolean onActivityResult(int i2, int i3, Intent intent) {
        InteractorDelegate interactorDelegate;
        int i4;
        JPMCMonitor.i("MC RequestCode:" + i2);
        if (this.f7550k) {
            return false;
        }
        if (i2 == 1000 || i2 == 1001) {
            init();
        } else {
            MemberCodeException memberCodeException = null;
            if (i2 == 1010) {
                CommonVerifyResultBean a2 = a(intent);
                if (a2 == null || !CommonVerifyResultBean.STATUS_SUCCESS.equals(a2.status)) {
                    if (a2 != null && !TextUtils.isEmpty(a2.msg)) {
                        memberCodeException = new MemberCodeException(a2.msg);
                    }
                    interactorDelegate = this.f7546g;
                    i4 = 4;
                    interactorDelegate.onError(i4, memberCodeException);
                }
                JDPayMemberCode.getService().f7524f = "1";
                activateCode(false);
            } else if (i2 == 1012) {
                CommonVerifyResultBean a3 = a(intent);
                if (a3 == null || !CommonVerifyResultBean.STATUS_SUCCESS.equals(a3.status)) {
                    if (a3 != null && !TextUtils.isEmpty(a3.msg)) {
                        memberCodeException = new MemberCodeException(a3.msg);
                    }
                    interactorDelegate = this.f7546g;
                    i4 = 4;
                    interactorDelegate.onError(i4, memberCodeException);
                }
                JDPayMemberCode.getService().f7524f = "1";
                activateCode(false);
            } else if (i2 != 1011) {
                return false;
            } else {
                CommonVerifyResultBean a4 = a(intent);
                if (a4 == null || !CommonVerifyResultBean.STATUS_SUCCESS.equals(a4.status)) {
                    if (a4 != null && !TextUtils.isEmpty(a4.msg)) {
                        memberCodeException = new MemberCodeException(a4.msg);
                    }
                    interactorDelegate = this.f7546g;
                    i4 = 5;
                    interactorDelegate.onError(i4, memberCodeException);
                }
            }
        }
        return true;
    }

    @Override // com.jdpay.membercode.c
    public void onPayResult(@NonNull CodeResultInfoBean codeResultInfoBean) {
        if (this.f7550k) {
            return;
        }
        if (CodeResultInfoBean.NEXT_STEP_URL.equals(codeResultInfoBean.nextStep)) {
            JPMCMonitor.onEvent("MCPayResultUrl", codeResultInfoBean.url);
            this.f7546g.startBrowser(codeResultInfoBean.url, 0);
        } else if (CodeResultInfoBean.NEXT_STEP_TOKEN.equals(codeResultInfoBean.nextStep)) {
            JPMCMonitor.onEvent("MCPayResultVerify");
            d();
        }
    }

    public void onPayResult(String str) {
        if (this.f7550k) {
            return;
        }
        try {
            JDPayLog.i(this + "\nJSON:" + str);
            if (TextUtils.isEmpty(str)) {
                JDPayLog.i("result is null");
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("bizMsg")) {
                JDPayLog.i("result is not contain bizMsg");
                return;
            }
            CodeResultInfoBean codeResultInfoBean = (CodeResultInfoBean) JsonAdapter.objectSafety(jSONObject.getString("bizMsg"), CodeResultInfoBean.class);
            if (codeResultInfoBean == null) {
                JDPayLog.i("json parse error");
            } else {
                onPayResult(codeResultInfoBean);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            JDPayLog.e(th.getLocalizedMessage());
            this.f7546g.onError(5, th);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        h();
    }

    public void resumeScreenCapture(@NonNull Window window) {
        com.jdpay.membercode.f.f.b(window);
    }

    public void setAppSource(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDPayMemberCode.getService().f7522c = str;
    }

    public void setContent(String str) {
        this.a.i(str);
    }

    public void setCouponIds(List<String> list) {
        JDPayMemberCode.getService().f7523e = list;
    }

    public void setDeviceToken(String str) {
        JDPayMemberCode.getService().d = str;
    }

    public void setInteractor(MemberCodeInteractor memberCodeInteractor) {
        this.f7546g.a = memberCodeInteractor;
    }

    public void setUserToken(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDPayMemberCode.userToken = str;
        JDPayMemberCode.getService().b = str;
    }

    public void showLoadingDialog() {
        if (this.f7550k) {
            return;
        }
        if (this.f7547h == null) {
            CPProgressDialog cPProgressDialog = new CPProgressDialog(getContext());
            this.f7547h = cPProgressDialog;
            cPProgressDialog.setCanceledOnTouchOutside(false);
            this.f7547h.setMessage(getResources().getString(R.string.jdpay_common_loading));
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            post(new c());
        } else {
            Utils.showDialog(this.f7547h);
        }
    }

    public void showToast(CharSequence charSequence) {
        this.f7546g.onToast(charSequence);
    }

    public void sign() {
        JPMCMonitor.onEvent("MCsign");
        JDPayMemberCode.getService().g(new f(6));
    }

    public boolean startActivityForResult(@NonNull Intent intent, int i2) {
        return this.f7546g.startActivityForResult(intent, i2);
    }

    public boolean startBrowser(@NonNull String str, int i2) {
        return this.f7546g.startBrowser(str, i2);
    }

    public void startScheduler() {
        if (!this.f7550k && isCodeAvailable()) {
            if (!this.f7544e.d()) {
                JDPayLog.i("");
                this.f7544e.b(0, 60);
            }
            if (!CodeResultInfoBean.QUERY_WAY_PULL.equalsIgnoreCase(this.f7549j.queryPayResultWay) || this.f7545f.d()) {
                return;
            }
            this.f7545f.b(0, 2);
        }
    }

    public void stopScheduler() {
        JDPayLog.i("");
        if (this.f7544e.d()) {
            this.f7544e.a();
        }
        if (this.f7545f.d()) {
            this.f7545f.a();
        }
    }

    public void updateCode() {
        JPMCMonitor.d("");
        if (this.f7550k || !isCodeAvailable()) {
            return;
        }
        this.a.j(false);
        this.f7544e.c(false);
    }

    public void updateCode(@NonNull String str) {
        CodeInfoBean codeInfoBean;
        if (this.f7550k) {
            return;
        }
        CodeEnterInfoBean codeEnterInfoBean = this.f7549j;
        if (codeEnterInfoBean != null && (codeInfoBean = codeEnterInfoBean.code) != null) {
            codeInfoBean.code = str;
        }
        this.f7545f.f(str);
    }
}
