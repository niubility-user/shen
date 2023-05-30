package com.jd.manto.membercode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.manto.R;
import com.jdpay.membercode.JDPayMemberCode;
import com.jdpay.membercode.MemberCode;
import com.jdpay.membercode.widget.CodeView;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.MessageCommonUtils;
import com.jingdong.common.ui.JDBottomDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;

/* loaded from: classes17.dex */
public class MantoMemberCodeActivity extends Activity implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    private SimpleDraweeView f6764g;

    /* renamed from: h  reason: collision with root package name */
    private SimpleDraweeView f6765h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f6766i;

    /* renamed from: j  reason: collision with root package name */
    private View f6767j;

    /* renamed from: k  reason: collision with root package name */
    private View f6768k;

    /* renamed from: l  reason: collision with root package name */
    private View f6769l;

    /* renamed from: m  reason: collision with root package name */
    private CodeView f6770m;

    /* loaded from: classes17.dex */
    private class MyImpl extends MemberCode {
        private MyImpl() {
        }

        @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
        public void onActivated() {
            super.onActivated();
            MantoMemberCodeActivity.this.f(true);
        }

        @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
        public void onError(int i2, Throwable th) {
            if (i2 == 4) {
                MantoMemberCodeActivity.this.finish();
            }
        }

        @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
        public void onInactivated() {
            super.onInactivated();
            MantoMemberCodeActivity.this.f(false);
        }

        @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
        public void onInited() {
            super.onInited();
            MantoMemberCodeActivity.this.f(MantoMemberCodeActivity.this.f6770m.isCodeActivated());
        }

        @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
        public boolean onToast(CharSequence charSequence) {
            return false;
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public boolean startActivityForResult(Intent intent, int i2) {
            MantoMemberCodeActivity.this.startActivityForResult(intent, i2);
            return true;
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public boolean startBrowser(String str, int i2) {
            if (TextUtils.equals("onlychecksales", str)) {
                MantoMemberCodeActivity.this.f6770m.startBrowser(str, i2);
                return true;
            }
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            DeepLinkCommonHelper.startActivityForResult(MantoMemberCodeActivity.this, DeepLinkCommonHelper.HOST_WEBACTIVITY, bundle, i2);
            return true;
        }

        @Override // com.jdpay.membercode.MemberCodeInteractor
        public boolean startCommonVerifyModule(String str, String str2, String str3, String str4, int i2) {
            Bundle bundle = new Bundle();
            bundle.putString("APP_SOURCE", str);
            bundle.putString("BIZ_SOURCE", str2);
            bundle.putString("SESSION_KEY", str3);
            bundle.putString("BUSINESS_TOKEN", str4);
            DeepLinkDispatch.startActivityForResult(MantoMemberCodeActivity.this, "jingdong://jdpayverifyactivity", bundle, i2);
            return true;
        }

        /* synthetic */ MyImpl(MantoMemberCodeActivity mantoMemberCodeActivity, a aVar) {
            this();
        }
    }

    /* loaded from: classes17.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDBottomDialog f6771g;

        a(JDBottomDialog jDBottomDialog) {
            this.f6771g = jDBottomDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6771g.cancel();
            MantoMemberCodeActivity.this.f6770m.updateCode();
        }
    }

    /* loaded from: classes17.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDBottomDialog f6773g;

        b(JDBottomDialog jDBottomDialog) {
            this.f6773g = jDBottomDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6773g.cancel();
            MantoMemberCodeActivity.this.f6770m.inactivateCode();
        }
    }

    /* loaded from: classes17.dex */
    class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDBottomDialog f6775g;

        c(JDBottomDialog jDBottomDialog) {
            this.f6775g = jDBottomDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6775g.cancel();
            String str = "https://payxsign.jd.com/aggresign/main?merchantNo=110247176&userId=" + UserUtil.getWJLoginHelper().getPin();
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            DeepLinkCommonHelper.startActivityDirect(MantoMemberCodeActivity.this, DeepLinkCommonHelper.HOST_WEBACTIVITY, bundle);
        }
    }

    /* loaded from: classes17.dex */
    class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDBottomDialog f6777g;

        d(MantoMemberCodeActivity mantoMemberCodeActivity, JDBottomDialog jDBottomDialog) {
            this.f6777g = jDBottomDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f6777g.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f6778g;

        e(boolean z) {
            this.f6778g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MantoMemberCodeActivity.this.f6768k != null) {
                MantoMemberCodeActivity.this.f6768k.setVisibility(this.f6778g ? 0 : 8);
            }
        }
    }

    private void d() {
        CodeView codeView = this.f6770m;
        if (codeView == null) {
            return;
        }
        codeView.setAppSource("jdmall");
        String a2 = UserUtil.getWJLoginHelper().getA2();
        if (!TextUtils.isEmpty(a2)) {
            this.f6770m.setUserToken(a2);
        }
        String deviceToken = MessageCommonUtils.getDeviceToken(getApplicationContext());
        if (TextUtils.isEmpty(deviceToken)) {
            return;
        }
        this.f6770m.setDeviceToken(deviceToken);
    }

    private void e() {
        String nickName = PersonalInfoManager.getInstance().getNickName();
        if (TextUtils.isEmpty(nickName)) {
            nickName = LoginUserBase.getLoginName();
        }
        if (!TextUtils.isEmpty(nickName)) {
            this.f6766i.setText(nickName);
        }
        this.f6767j.setVisibility(PersonalInfoManager.getInstance().isUserPlusStatus() ? 0 : 8);
        String avatarUrl = PersonalInfoManager.getInstance().getAvatarUrl();
        if (TextUtils.isEmpty(avatarUrl)) {
            return;
        }
        JDImageUtils.displayImage(avatarUrl, this.f6764g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(boolean z) {
        runOnUiThread(new e(z));
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        CodeView codeView = this.f6770m;
        if (codeView != null) {
            codeView.onActivityResult(i2, i3, intent);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.equals(this.f6768k)) {
            JDBottomDialog jDBottomDialog = new JDBottomDialog(this);
            View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_member_code_setting, (ViewGroup) null);
            inflate.findViewById(R.id.member_code_setting_refresh).setOnClickListener(new a(jDBottomDialog));
            inflate.findViewById(R.id.member_code_setting_stop).setOnClickListener(new b(jDBottomDialog));
            inflate.findViewById(R.id.member_code_setting_quick_pay).setOnClickListener(new c(jDBottomDialog));
            inflate.findViewById(R.id.member_code_setting_cancel).setOnClickListener(new d(this, jDBottomDialog));
            jDBottomDialog.addContent(inflate, null);
            jDBottomDialog.show();
        } else if (view.equals(this.f6769l)) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        JDPayMemberCode.init(getApplication());
        setContentView(R.layout.activity_manto_membercode);
        this.f6764g = (SimpleDraweeView) findViewById(R.id.member_code_user_avatar);
        this.f6766i = (TextView) findViewById(R.id.member_code_user_name);
        this.f6767j = findViewById(R.id.member_code_plus);
        this.f6768k = findViewById(R.id.member_code_setting);
        this.f6770m = (CodeView) findViewById(R.id.manto_code_view);
        this.f6769l = findViewById(R.id.member_code_close);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.member_code_head_bg);
        this.f6765h = simpleDraweeView;
        JDImageUtils.displayImage("http://m.360buyimg.com/mobilecal/jfs/t1/191462/9/20436/155024/61288642Ec8db0375/e6dca937ac5b039d.png", simpleDraweeView);
        this.f6768k.setOnClickListener(this);
        this.f6769l.setOnClickListener(this);
        e();
        d();
        this.f6770m.setInteractor(new MyImpl(this, null));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        CodeView codeView = this.f6770m;
        if (codeView != null) {
            codeView.destroy();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        CodeView codeView = this.f6770m;
        if (codeView != null) {
            codeView.init();
        }
    }
}
