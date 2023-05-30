package com.jdpay.membercode.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.image.loader.converter.ScaleConfig;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.lib.util.OnClick;
import com.jdpay.membercode.JDPayMemberCode;
import com.jdpay.membercode.R;
import com.jdpay.membercode.bean.CodeOpenInfoBean;
import com.jdpay.membercode.bean.ProtocolBean;
import com.jdpay.net.ResultObserver;
import com.jdpay.util.JPMCMonitor;

/* loaded from: classes18.dex */
public class a extends com.jdpay.membercode.widget.b implements View.OnClickListener {

    /* renamed from: i */
    protected ImageView f7558i;

    /* renamed from: j */
    protected ImageView f7559j;

    /* renamed from: k */
    protected Button f7560k;

    /* renamed from: l */
    protected TextView f7561l;

    /* renamed from: m */
    protected CodeOpenInfoBean f7562m;

    /* renamed from: n */
    protected final View.OnClickListener f7563n;

    /* renamed from: com.jdpay.membercode.widget.a$a */
    /* loaded from: classes18.dex */
    class RunnableC0231a implements Runnable {
        RunnableC0231a() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            aVar.g(aVar.f7558i, "https://storage.360buyimg.com/jdpay-common/membercode/pic_jdpay_mb_sign_inset_1.png");
            a aVar2 = a.this;
            aVar2.g(aVar2.f7559j, "https://storage.360buyimg.com/jdpay-common/membercode/pic_jdpay_mb_sign_inset_2.png");
        }
    }

    /* loaded from: classes18.dex */
    public class b implements ResultObserver<Object> {
        final /* synthetic */ ImageView a;
        final /* synthetic */ String b;

        b(a aVar, ImageView imageView, String str) {
            this.a = imageView;
            this.b = str;
        }

        @Override // com.jdpay.net.ResultObserver
        public void onFailure(@NonNull Throwable th) {
            JPMCMonitor.onEvent("MCLOAD_SIGN_IMAGE_FAILURE", "Err:" + th.getLocalizedMessage() + " Url:" + this.b);
        }

        @Override // com.jdpay.net.ResultObserver
        public void onSuccess(@Nullable Object obj) {
            if (obj instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) obj;
                ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = bitmap.getWidth();
                    layoutParams.height = bitmap.getHeight();
                    this.a.requestLayout();
                    JPMCMonitor.onEvent("MCLOAD_SIGN_IMAGE_SUCCESS", "Url:" + this.b);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class c implements View.OnClickListener {
        c() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ProtocolBean protocolBean;
            CodeOpenInfoBean codeOpenInfoBean = a.this.f7562m;
            String str = (codeOpenInfoBean == null || (protocolBean = codeOpenInfoBean.protocolInfo) == null) ? null : protocolBean.url;
            JDPayLog.i("onClick:" + str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a.this.f7566g.a(str, 0);
        }
    }

    public a(@NonNull CodeView codeView) {
        super(codeView);
        this.f7563n = OnClick.create(new c());
    }

    public void g(ImageView imageView, String str) {
        JDPayMemberCode.getImageLoader().uri(str).defaultCache(imageView.getContext().getApplicationContext()).to(imageView).setFd(true).setScaleConfig(new ScaleConfig.Builder().setMode(1).setWidth(imageView.getWidth()).setMaxHeight(Integer.MAX_VALUE).build()).observe(new b(this, imageView, str)).load();
    }

    @Override // com.jdpay.membercode.widget.b
    protected View b(@NonNull Context context) {
        View inflate = View.inflate(context, R.layout.jdpay_mb_sign, null);
        this.f7558i = (ImageView) inflate.findViewById(R.id.img_sign1);
        this.f7559j = (ImageView) inflate.findViewById(R.id.img_sign2);
        this.f7558i.post(new RunnableC0231a());
        TextView textView = (TextView) inflate.findViewById(R.id.jdpay_mb_activate_agreement);
        this.f7561l = textView;
        textView.setOnClickListener(this);
        ((TextView) inflate.findViewById(R.id.jdpay_mb_activate_protocol)).setOnClickListener(this.f7563n);
        Button button = (Button) inflate.findViewById(R.id.jdpay_mb_activate_button);
        this.f7560k = button;
        button.setOnClickListener(this);
        return inflate;
    }

    public void h(@Nullable CodeOpenInfoBean codeOpenInfoBean) {
        this.f7562m = codeOpenInfoBean;
    }

    public void j(boolean z) {
        TextView textView = this.f7561l;
        if (textView != null) {
            textView.setSelected(z);
            this.f7561l.setCompoundDrawablesWithIntrinsicBounds(z ? R.mipmap.ic_jdpay_mb_activate_checked : R.mipmap.ic_jdpay_mb_activate_unchecked, 0, 0, 0);
            this.f7560k.setEnabled(z);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != this.f7560k) {
            if (view == this.f7561l) {
                j(!r0.isSelected());
            }
        } else if (this.f7561l.isSelected()) {
            this.f7566g.activateCode(true);
        } else {
            CodeView codeView = this.f7566g;
            codeView.showToast(codeView.getResources().getText(R.string.jdpay_mb_err_activate_unchecked_agreement));
        }
    }
}
