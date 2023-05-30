package com.jingdong.app.mall.ad.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes19.dex */
public class AdButtonLottieView extends LottieAnimationViewCatchDraw {

    /* renamed from: g  reason: collision with root package name */
    private String f7914g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f7915h;

    /* loaded from: classes19.dex */
    class a implements ImageAssetDelegate {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // com.airbnb.lottie.ImageAssetDelegate
        public Bitmap fetchBitmap(LottieImageAsset lottieImageAsset) {
            return "image_0".equals(lottieImageAsset.getId()) ? AdButtonLottieView.this.f(this.a, R2.attr.calendarViewShown, 132) : AdButtonLottieView.this.c(lottieImageAsset);
        }
    }

    public AdButtonLottieView(Context context, String str) {
        super(context);
        d(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap c(LottieImageAsset lottieImageAsset) {
        Bitmap bitmap = lottieImageAsset.getBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        String fileName = lottieImageAsset.getFileName();
        if (!fileName.startsWith("data:") || fileName.indexOf("base64,") <= 0) {
            return bitmap;
        }
        try {
            byte[] decode = Base64.decode(fileName.substring(fileName.indexOf(44) + 1), 0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = true;
            options.inDensity = 160;
            return f.b(decode, 0, decode.length, options);
        } catch (Exception e2) {
            e2.printStackTrace();
            return bitmap;
        }
    }

    private void d(String str) {
        try {
            setImageAssetsFolder("assets/");
            if (TextUtils.isEmpty(this.f7914g)) {
                this.f7914g = k.o("local/homeAdButtonLottie.json");
            }
            if (!TextUtils.isEmpty(this.f7914g) && isValid(this.f7914g)) {
                this.f7915h = true;
                setRepeatMode(1);
                setRepeatCount(Integer.MAX_VALUE);
                setLottieJson(this.f7914g, "HOME_BUTTON_" + str);
                return;
            }
            this.f7915h = false;
            setVisibility(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap f(String str, int i2, int i3) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setTextColor(-1);
        textView.setTextSize(0, 40.0f);
        textView.setText(str);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        textView.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
        textView.layout(0, 0, i2, i3);
        textView.draw(canvas);
        return createBitmap;
    }

    public boolean e() {
        return this.f7915h;
    }

    public void setText(String str) {
        if (TextUtils.isEmpty(str) || "\u6ed1\u52a8\u6216\u70b9\u51fb\u67e5\u770b\u8be6\u60c5".equals(str)) {
            return;
        }
        setImageAssetDelegate(new a(str));
    }
}
