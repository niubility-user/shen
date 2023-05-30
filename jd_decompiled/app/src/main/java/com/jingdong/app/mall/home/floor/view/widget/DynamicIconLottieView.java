package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class DynamicIconLottieView extends LottieAnimationViewCatchDraw {

    /* renamed from: l  reason: collision with root package name */
    private static final Map<String, Bitmap> f10026l = new ConcurrentHashMap();

    /* renamed from: g  reason: collision with root package name */
    private String f10027g;

    /* renamed from: h  reason: collision with root package name */
    private String f10028h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f10029i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f10030j;

    /* renamed from: k  reason: collision with root package name */
    private String f10031k;

    /* loaded from: classes4.dex */
    class a implements ImageAssetDelegate {
        final /* synthetic */ com.jingdong.app.mall.home.r.e.k.b a;

        a(com.jingdong.app.mall.home.r.e.k.b bVar) {
            this.a = bVar;
        }

        @Override // com.airbnb.lottie.ImageAssetDelegate
        @Nullable
        public Bitmap fetchBitmap(LottieImageAsset lottieImageAsset) {
            Bitmap f2 = DynamicIconLottieView.this.k() ? DynamicIconLottieView.this.f(lottieImageAsset, this.a) : DynamicIconLottieView.this.g(lottieImageAsset, this.a);
            if (f2 == null) {
                DynamicIconLottieView.this.i();
            }
            return f2;
        }
    }

    public DynamicIconLottieView(Context context) {
        super(context);
        this.f10029i = false;
    }

    private Bitmap e(String str, String str2) {
        byte[] bArr;
        String concat = "asset_img_".concat(str);
        Bitmap bitmap = f10026l.get(concat);
        if (bitmap == null || bitmap.isRecycled()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = true;
            options.inDensity = 160;
            Bitmap bitmap2 = null;
            if (str2.startsWith("data:") && str2.indexOf("base64,") > 0) {
                try {
                    bArr = Base64.decode(str2.substring(str2.indexOf(44) + 1), 0);
                } catch (IllegalArgumentException unused) {
                    bArr = null;
                }
                if (bArr != null && bArr.length > 0) {
                    bitmap2 = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                }
            }
            if (bitmap2 != null) {
                f10026l.put(concat, bitmap2);
            }
            return bitmap2;
        }
        return bitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap f(LottieImageAsset lottieImageAsset, com.jingdong.app.mall.home.r.e.k.b bVar) {
        String id = lottieImageAsset.getId();
        if ("image_0".equals(id)) {
            return n(bVar.h(), h(32), h(20));
        }
        String fileName = lottieImageAsset.getFileName();
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(fileName)) {
            return null;
        }
        return e(id, fileName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap g(LottieImageAsset lottieImageAsset, com.jingdong.app.mall.home.r.e.k.b bVar) {
        if ("image_0".equals(lottieImageAsset.getId())) {
            return n(bVar.h(), h(28), h(15));
        }
        return n(bVar.i(), h(28), h(15));
    }

    private int h(int i2) {
        double d = i2 * 2.75f;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    private void j(com.jingdong.app.mall.home.r.e.k.b bVar) {
        if (bVar == null) {
            setVisibility(8);
            return;
        }
        if (!TextUtils.equals(this.f10031k, bVar.e())) {
            this.f10028h = null;
            this.f10027g = null;
            i();
        }
        this.f10031k = bVar.e();
        String f2 = bVar.f();
        String str = k() ? "local/homeIconLottie3.json" : "local/homeIconLottie.json";
        try {
            if (TextUtils.equals(this.f10027g, f2)) {
                return;
            }
            this.f10027g = f2;
            setImageAssetsFolder("assets/");
            if (this.f10028h == null) {
                this.f10028h = k.o(str);
            }
            if (isValid(this.f10028h)) {
                setLottieJson(this.f10028h, "HOME_ICON_".concat(f2));
                this.f10030j = true;
                return;
            }
            setVisibility(8);
        } catch (Exception e2) {
            setVisibility(8);
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return TextUtils.equals("1", this.f10031k);
    }

    private void l() {
        try {
            m();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void m() {
        if (!this.f10030j) {
            setVisibility(8);
            return;
        }
        setRepeatCount(Integer.MAX_VALUE);
        this.f10029i = true;
        playAnimation();
        setVisibility(0);
    }

    private Bitmap n(String str, int i2, int i3) {
        String concat = str.concat(String.valueOf(i2));
        Map<String, Bitmap> map = f10026l;
        Bitmap bitmap = map.get(concat);
        if (bitmap == null || bitmap.isRecycled()) {
            TextView textView = new TextView(getContext());
            textView.setGravity(17);
            textView.setTextColor(-1);
            textView.getPaint().setFakeBoldText(true);
            textView.setTextSize(0, h(10));
            if (k()) {
                str = f.j(2, str);
            }
            textView.setText(str);
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            textView.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
            textView.layout(0, 0, i2, i3);
            textView.draw(canvas);
            map.put(concat, createBitmap);
            return createBitmap;
        }
        return bitmap;
    }

    public void d(com.jingdong.app.mall.home.r.e.k.b bVar) {
        j(bVar);
        setImageAssetDelegate(new a(bVar));
        l();
    }

    public void i() {
        this.f10029i = false;
        try {
            cancelAnimation();
            setVisibility(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.airbnb.lottie.LottieAnimationView, android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f10029i) {
            playAnimation();
        }
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        boolean k2 = k();
        layoutParams.width = d.d(k2 ? 60 : 52);
        layoutParams.height = d.d(k2 ? 36 : 28);
        super.setLayoutParams(layoutParams);
    }
}
