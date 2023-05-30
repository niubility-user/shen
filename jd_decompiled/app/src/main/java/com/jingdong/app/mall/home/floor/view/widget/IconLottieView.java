package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.r.e.k.d;
import com.jingdong.app.mall.home.r.f.a.o;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class IconLottieView extends LottieAnimationViewCatchDraw {

    /* renamed from: l */
    private static final Map<String, Bitmap> f10097l = new ConcurrentHashMap();

    /* renamed from: g */
    private String f10098g;

    /* renamed from: h */
    private String f10099h;

    /* renamed from: i */
    private boolean f10100i;

    /* renamed from: j */
    private boolean f10101j;

    /* renamed from: k */
    private o f10102k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements ImageAssetDelegate {
        final /* synthetic */ d a;

        a(d dVar) {
            IconLottieView.this = r1;
            this.a = dVar;
        }

        @Override // com.airbnb.lottie.ImageAssetDelegate
        public Bitmap fetchBitmap(LottieImageAsset lottieImageAsset) {
            return "image_0".equals(lottieImageAsset.getId()) ? IconLottieView.this.k(this.a.d(), IconLottieView.this.d(28), IconLottieView.this.d(15)) : IconLottieView.this.k(this.a.e(), IconLottieView.this.d(28), IconLottieView.this.d(15));
        }
    }

    public IconLottieView(Context context, o oVar) {
        super(context);
        this.f10100i = false;
        this.f10102k = oVar;
    }

    private int c() {
        o oVar = this.f10102k;
        return (oVar == null || !oVar.A0()) ? 83 : 76;
    }

    public int d(int i2) {
        double d = i2 * 2.75f;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    private int e() {
        o oVar = this.f10102k;
        if (oVar == null || !oVar.A0()) {
            o oVar2 = this.f10102k;
            return (oVar2 == null || !oVar2.J0()) ? 4 : 12;
        }
        return 14;
    }

    private void g(String str) {
        try {
            if (TextUtils.equals(this.f10098g, str)) {
                return;
            }
            this.f10098g = str;
            setImageAssetsFolder("assets/");
            if (this.f10099h == null) {
                this.f10099h = k.o("local/homeIconLottie.json");
            }
            if (isValid(this.f10099h)) {
                setLottieJson(this.f10099h, "HOME_ICON_".concat(str));
                this.f10101j = true;
                return;
            }
            setVisibility(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void j(d dVar, boolean z) {
        if (!this.f10101j) {
            setVisibility(8);
            return;
        }
        if (z) {
            setRepeatCount(Integer.MAX_VALUE);
            this.f10100i = true;
            playAnimation();
        } else {
            pauseAnimation();
            setFrame(25);
        }
        setVisibility(0);
    }

    public Bitmap k(String str, int i2, int i3) {
        String concat = str.concat(String.valueOf(i2));
        Map<String, Bitmap> map = f10097l;
        Bitmap bitmap = map.get(concat);
        if (bitmap == null || bitmap.isRecycled()) {
            TextView textView = new TextView(getContext());
            textView.setGravity(17);
            textView.setTextColor(-1);
            textView.setTextSize(0, d(10));
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

    public void f() {
        this.f10100i = false;
        cancelAnimation();
        setVisibility(8);
    }

    public void h(d dVar, boolean z) {
        try {
            j(dVar, z);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void i(d dVar) {
        g(dVar.b());
        setImageAssetDelegate(new a(dVar));
    }

    @Override // com.airbnb.lottie.LottieAnimationView, android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f10100i) {
            playAnimation();
        }
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        int d = com.jingdong.app.mall.home.floor.common.d.d(52);
        int d2 = com.jingdong.app.mall.home.floor.common.d.d(28);
        int d3 = com.jingdong.app.mall.home.floor.common.d.d(c());
        int d4 = com.jingdong.app.mall.home.floor.common.d.d(e());
        layoutParams.width = d;
        layoutParams.height = d2;
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = d3;
            marginLayoutParams.topMargin = d4;
        }
        super.setLayoutParams(layoutParams);
    }
}
