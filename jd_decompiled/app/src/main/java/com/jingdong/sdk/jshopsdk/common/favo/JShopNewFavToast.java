package com.jingdong.sdk.jshopsdk.common.favo;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.jshopsdk.R;

/* loaded from: classes7.dex */
public class JShopNewFavToast extends Toast {
    public static final int FAVO = 0;
    private static final String TAG = "JShopNewFavToast";
    public static final int UNFAVO = 1;
    private Handler handler;
    private ImageView mJShopLottieFavAnimationView;
    private ImageView mJShopLottieUnFavAnimationView;
    private View rootView;

    public JShopNewFavToast(Context context) {
        super(context);
        this.handler = ((IMyActivity) context).getHandler();
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.jshop_fav_new_toast, (ViewGroup) null);
        this.rootView = inflate;
        setView(inflate);
        setGravity(17, 0, 0);
        this.mJShopLottieFavAnimationView = (ImageView) this.rootView.findViewById(R.id.jshop_fav_lottie_animation);
        this.mJShopLottieUnFavAnimationView = (ImageView) this.rootView.findViewById(R.id.jshop_unfav_lottie_animation);
        if (isFavLottie()) {
            ((JShopLottieAnimationView) this.mJShopLottieFavAnimationView).loop(false);
            ((JShopLottieAnimationView) this.mJShopLottieFavAnimationView).setAnimation("jshop_home_toast_ok.json");
        }
        if (isUnFavLottie()) {
            ((JShopLottieAnimationView) this.mJShopLottieUnFavAnimationView).loop(false);
            ((JShopLottieAnimationView) this.mJShopLottieUnFavAnimationView).setAnimation("jshop_home_toast_cancel.json");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFavLottie() {
        return Build.VERSION.SDK_INT >= 16 && (this.mJShopLottieFavAnimationView instanceof JShopLottieAnimationView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUnFavLottie() {
        return Build.VERSION.SDK_INT >= 16 && (this.mJShopLottieUnFavAnimationView instanceof JShopLottieAnimationView);
    }

    public void show(int i2, String str, String str2) {
        show(i2, str, str2, -1);
    }

    public void show(final int i2, String str, String str2, int i3) {
        ImageView imageView;
        if (i2 == 0) {
            this.mJShopLottieUnFavAnimationView.setVisibility(4);
            this.mJShopLottieFavAnimationView.setVisibility(0);
            if (isFavLottie()) {
                imageView = this.mJShopLottieFavAnimationView;
                imageView.clearAnimation();
            }
        } else if (i2 == 1) {
            this.mJShopLottieUnFavAnimationView.setVisibility(0);
            this.mJShopLottieFavAnimationView.setVisibility(4);
            if (isUnFavLottie()) {
                imageView = this.mJShopLottieUnFavAnimationView;
                imageView.clearAnimation();
            }
        }
        if (i3 < 0) {
            setDuration(0);
        } else {
            setDuration(i3);
        }
        Handler handler = this.handler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.jingdong.sdk.jshopsdk.common.favo.JShopNewFavToast.1
                @Override // java.lang.Runnable
                public void run() {
                    ImageView imageView2;
                    int i4 = i2;
                    if (i4 == 0) {
                        if (JShopNewFavToast.this.isFavLottie()) {
                            imageView2 = JShopNewFavToast.this.mJShopLottieFavAnimationView;
                            ((JShopLottieAnimationView) imageView2).playAnimation();
                        }
                    } else if (i4 == 1 && JShopNewFavToast.this.isUnFavLottie()) {
                        imageView2 = JShopNewFavToast.this.mJShopLottieUnFavAnimationView;
                        ((JShopLottieAnimationView) imageView2).playAnimation();
                    }
                    JShopNewFavToast.this.show();
                }
            });
        }
    }
}
