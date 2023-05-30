package com.jd.lib.productdetail.mainimage.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.LifecycleObserver;
import com.facebook.imagepipeline.image.EncodedImage;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.PdOneToNPriceVo;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes15.dex */
public class PdPriceBannerView extends RelativeLayout implements LifecycleObserver {
    private Context mContext;
    private AppCompatTextView mLeftBottomTv;
    private AppCompatTextView mLeftTopTv;
    private LinearLayout mPriceLeftBg;
    private LinearLayout mPriceRightBg;
    private AppCompatTextView mRightBottomTv;
    private AppCompatTextView mRightTopTv;
    private View rootView;

    public PdPriceBannerView(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
    }

    private void adapterSize(AppCompatTextView appCompatTextView, int i2, CharSequence charSequence, int i3) {
        if (appCompatTextView == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        float desiredWidth = StaticLayout.getDesiredWidth(charSequence, appCompatTextView.getPaint());
        float f2 = i3;
        if (desiredWidth > f2) {
            appCompatTextView.setTextSize(0, i2 * ((f2 * 1.0f) / desiredWidth));
        }
    }

    private void bannerPriceExpo(String str, PdOneToNPriceVo pdOneToNPriceVo, PdMainImagePresenter pdMainImagePresenter) {
        if (pdOneToNPriceVo == null || pdMainImagePresenter == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("xPrice", (Object) pdOneToNPriceVo.xPrice);
        jDJSONObject.put("anchorType", (Object) str);
        pdMainImagePresenter.mtaExposure("Productdetail_MainPicPricebeltExpo", jDJSONObject.toJSONString(), true);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_banner_price, this);
        this.rootView = inflate;
        this.mPriceLeftBg = (LinearLayout) inflate.findViewById(R.id.pd_banner_price_left_bg);
        this.mPriceRightBg = (LinearLayout) inflate.findViewById(R.id.pd_banner_price_right_bg);
        this.mLeftTopTv = (AppCompatTextView) inflate.findViewById(R.id.pd_left_top_tv);
        this.mLeftBottomTv = (AppCompatTextView) inflate.findViewById(R.id.pd_left_bottom_tv);
        this.mRightTopTv = (AppCompatTextView) inflate.findViewById(R.id.pd_right_top_tv);
        this.mRightBottomTv = (AppCompatTextView) inflate.findViewById(R.id.pd_right_bottom_tv);
        setVisibility(8);
        FontsUtil.changeTextFont(this.mLeftTopTv, 4099);
        FontsUtil.changeTextFont(this.mLeftBottomTv, 4099);
        FontsUtil.changeTextFont(this.mRightTopTv, 4099);
        FontsUtil.changeTextFont(this.mRightBottomTv, 4099);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEncodedImage(LinearLayout linearLayout, EncodedImage encodedImage) {
        Drawable bitmapDrawable;
        if (encodedImage == null || this.mContext == null) {
            return;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream());
        if (decodeStream == null) {
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
                return;
            }
            return;
        }
        byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
        if (ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk)) {
            bitmapDrawable = new NinePatchDrawable(this.mContext.getResources(), new NinePatch(decodeStream, ninePatchChunk, null));
        } else {
            bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), decodeStream);
        }
        if (linearLayout != null) {
            linearLayout.setBackgroundDrawable(bitmapDrawable);
            linearLayout.setVisibility(0);
        }
    }

    private void showLeftBg(PdOneToNPriceVo pdOneToNPriceVo) {
        this.mPriceLeftBg.setVisibility(4);
        JDImageLoader.getEncodedImage(pdOneToNPriceVo.backgroundImageAndroid, null, new ImageRequestListener<EncodedImage>() { // from class: com.jd.lib.productdetail.mainimage.view.PdPriceBannerView.2
            @Override // com.jd.mobile.image.ImageRequestListener
            public void onCancel() {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onFailure(Throwable th) {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onSuccess(EncodedImage encodedImage) {
                PdPriceBannerView pdPriceBannerView = PdPriceBannerView.this;
                pdPriceBannerView.setEncodedImage(pdPriceBannerView.mPriceLeftBg, encodedImage);
            }
        });
    }

    private void showRightBg(PdOneToNPriceVo pdOneToNPriceVo) {
        this.mPriceRightBg.setVisibility(4);
        JDImageLoader.getEncodedImage(pdOneToNPriceVo.backgroundImageAndroidRight, null, new ImageRequestListener<EncodedImage>() { // from class: com.jd.lib.productdetail.mainimage.view.PdPriceBannerView.3
            @Override // com.jd.mobile.image.ImageRequestListener
            public void onCancel() {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onFailure(Throwable th) {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onSuccess(EncodedImage encodedImage) {
                PdPriceBannerView pdPriceBannerView = PdPriceBannerView.this;
                pdPriceBannerView.setEncodedImage(pdPriceBannerView.mPriceRightBg, encodedImage);
            }
        });
    }

    private void showTotalBg(PdOneToNPriceVo pdOneToNPriceVo) {
        if (pdOneToNPriceVo == null || TextUtils.isEmpty(pdOneToNPriceVo.backgroundImage4New)) {
            return;
        }
        JDImageUtils.loadImage(pdOneToNPriceVo.backgroundImage4New, new JDImageLoadingListener() { // from class: com.jd.lib.productdetail.mainimage.view.PdPriceBannerView.1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str, View view) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                if (PdPriceBannerView.this.getResources() == null || bitmap == null || bitmap.isRecycled()) {
                    return;
                }
                BitmapDrawable bitmapDrawable = new BitmapDrawable(PdPriceBannerView.this.getResources(), bitmap);
                if (PdPriceBannerView.this.rootView != null) {
                    PdPriceBannerView.this.rootView.setBackground(bitmapDrawable);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str, View view) {
            }
        });
    }

    public void bindData(String str, PdOneToNPriceVo pdOneToNPriceVo, PdMainImagePresenter pdMainImagePresenter) {
        setVisibility(0);
        if (!TextUtils.isEmpty(pdOneToNPriceVo.leftTopTxt)) {
            this.mLeftTopTv.setText(pdOneToNPriceVo.leftTopTxt);
            this.mLeftTopTv.setVisibility(0);
            adapterSize(this.mLeftTopTv, PDUtils.dip2px(10.0f), pdOneToNPriceVo.leftTopTxt, PDUtils.dip2px(65.0f));
        } else {
            this.mLeftTopTv.setVisibility(4);
        }
        if (!TextUtils.isEmpty(pdOneToNPriceVo.leftBottomTxt)) {
            CharSequence jPriceTextForTen = PDUtils.getJPriceTextForTen(pdOneToNPriceVo.leftBottomTxt, 0.5f);
            this.mLeftBottomTv.setVisibility(0);
            adapterSize(this.mLeftBottomTv, PDUtils.dip2px(20.0f), jPriceTextForTen, PDUtils.dip2px(65.0f));
            this.mLeftBottomTv.setText(jPriceTextForTen);
        } else {
            this.mLeftBottomTv.setVisibility(4);
        }
        if (!TextUtils.isEmpty(pdOneToNPriceVo.rightTopTxt)) {
            this.mRightTopTv.setText(pdOneToNPriceVo.rightTopTxt);
            adapterSize(this.mRightTopTv, PDUtils.dip2px(12.0f), pdOneToNPriceVo.rightTopTxt, pdMainImagePresenter.appImageWidth - PDUtils.dip2px(105.0f));
            this.mRightTopTv.setVisibility(0);
        } else {
            this.mRightTopTv.setVisibility(4);
        }
        if (!TextUtils.isEmpty(pdOneToNPriceVo.rightBottomTxt)) {
            this.mRightBottomTv.setText(pdOneToNPriceVo.rightBottomTxt);
            adapterSize(this.mRightBottomTv, PDUtils.dip2px(12.0f), pdOneToNPriceVo.rightBottomTxt, pdMainImagePresenter.appImageWidth - PDUtils.dip2px(155.0f));
            this.mRightBottomTv.setVisibility(0);
        } else {
            this.mRightBottomTv.setVisibility(4);
        }
        bannerPriceExpo(str, pdOneToNPriceVo, pdMainImagePresenter);
        showTotalBg(pdOneToNPriceVo);
        showLeftBg(pdOneToNPriceVo);
        showRightBg(pdOneToNPriceVo);
    }

    public PdPriceBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView(context);
    }
}
