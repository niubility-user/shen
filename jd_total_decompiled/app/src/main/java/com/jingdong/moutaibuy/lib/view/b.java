package com.jingdong.moutaibuy.lib.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.moutaibuy.lib.R;

/* loaded from: classes16.dex */
public class b extends Dialog {

    /* renamed from: j  reason: collision with root package name */
    private static int f14626j;

    /* renamed from: k  reason: collision with root package name */
    private static int f14627k;

    /* renamed from: g  reason: collision with root package name */
    private c f14628g;

    /* renamed from: h  reason: collision with root package name */
    private Context f14629h;

    /* renamed from: i  reason: collision with root package name */
    SimpleDraweeView f14630i;

    /* loaded from: classes16.dex */
    class a implements ImageRequestListener<ImageInfo> {
        a() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(ImageInfo imageInfo) {
            int height = imageInfo.getHeight();
            int width = imageInfo.getWidth();
            int height2 = b.this.f14630i.getHeight();
            int width2 = b.this.f14630i.getWidth();
            if (width2 <= height2 || height2 != 1) {
                return;
            }
            int unused = b.f14627k = width2;
            int unused2 = b.f14626j = (width2 * height) / width;
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.moutaibuy.lib.view.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class ViewOnClickListenerC0709b implements View.OnClickListener {
        ViewOnClickListenerC0709b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.this.f14628g != null) {
                b.this.f14628g.a();
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a();
    }

    public b(@NonNull Context context) {
        this(context, 0);
    }

    private void d() {
        View inflate = LayoutInflater.from(this.f14629h).inflate(R.layout.layout_buy_moutai_now, (ViewGroup) null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.iv_buy_moutai_now);
        this.f14630i = simpleDraweeView;
        if (f14626j != 0 && f14627k != 0) {
            ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
            layoutParams.height = f14626j;
            layoutParams.width = f14627k;
            this.f14630i.setLayoutParams(layoutParams);
        }
        setContentView(inflate);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        this.f14630i.setOnClickListener(new ViewOnClickListenerC0709b());
    }

    public void e(int i2) {
        String str = i2 == 2 ? "http://m.360buyimg.com/mobilecal/jfs/t1/221615/21/3820/96142/619f2087E46e8627a/1928268aeab2e889.png" : "http://m.360buyimg.com/mobilecal/jfs/t1/138465/3/24673/97727/619f2087E0da99579/61aef946d3b830fb.png";
        SimpleDraweeView simpleDraweeView = this.f14630i;
        if (simpleDraweeView == null) {
            return;
        }
        JDImageLoader.display(str, simpleDraweeView, (JDDisplayImageOptions) null, new a());
    }

    public void f(c cVar) {
        this.f14628g = cVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public b(@NonNull Context context, int i2) {
        super(context);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.f14629h = context;
        d();
    }
}
