package com.jingdong.common.recommend.forlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.ViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.basewidget.widget.banner.BannerAdapter;
import com.jd.lib.un.basewidget.widget.banner.BannerView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.ui.AnimationLinerPagerCursor;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.mta.ExposureVpUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendBannerBViewHolder extends BaseRecommendAdViewHolder implements View.OnClickListener, ViewPager.OnPageChangeListener {
    public static final String BANNERB = "BannerB";
    private static final String KEY_CACHE = "__IS_FRONT_CACHE__";
    private static final String KEY_FROM_TOP = "__PIXEL_DISTANCE_FROM_TOP__";
    private static final String KEY_HEIGHT = "__EXPOSURE_HEIGHT__";
    private static final String KEY_SCREEN_HEIGHT = "__SCREEN_PIXEL_HEIGHT__";
    private static final String KEY_STATUS = "__HOME_STATUS__";
    private ImageView adTag;
    public int allFloorHeight;
    BannerAdapter bannerAdapter;
    private RecommendDna bannerDna;
    private BannerView bannerView;
    private ConstraintLayout constraintLayout;
    private View dot;
    private int from;
    public int homeContentHeight;
    public JDDisplayImageOptions jdDisplayImageOptions;
    private View leftBottomDot;
    private AnimationLinerPagerCursor mLinerPagerCursor;
    private ExposureVpUtils mUtils;
    private int scrollLoop;
    private int slipTime;

    /* loaded from: classes6.dex */
    class BannerViewHolder {
        public SimpleDraweeView markImageView;
        public ViewGroup rootView;
        public SimpleDraweeView simpleDraweeView;

        public BannerViewHolder(View view) {
            RecommendBannerBViewHolder.this = r1;
            this.rootView = (ViewGroup) view;
            this.simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_banner);
            this.markImageView = (SimpleDraweeView) view.findViewById(R.id.recommend_mark);
        }

        public void refreshView(RecommendProduct recommendProduct) {
            JDImageUtils.displayImage(recommendProduct.imgUrl, this.simpleDraweeView, RecommendBannerBViewHolder.this.jdDisplayImageOptions);
            if (TextUtils.isEmpty(recommendProduct.markImg)) {
                this.markImageView.setVisibility(8);
                return;
            }
            this.markImageView.setVisibility(0);
            JDImageUtils.displayImage(recommendProduct.markImg, this.markImageView, RecommendBannerBViewHolder.this.jdDisplayImageOptions);
        }
    }

    public RecommendBannerBViewHolder(View view) {
        super(view);
        this.slipTime = 3000;
        this.scrollLoop = 1;
        this.homeContentHeight = 0;
        this.allFloorHeight = 0;
        this.jdDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.ARGB_8888).setReferer(RecommendConstant.HTTP_REFER);
        this.constraintLayout = (ConstraintLayout) view.findViewById(R.id.recommend_banner_constraintLayout);
        BannerView bannerView = (BannerView) view.findViewById(R.id.recommend_banner_view);
        this.bannerView = bannerView;
        bannerView.stopLoop();
        this.mLinerPagerCursor = (AnimationLinerPagerCursor) view.findViewById(R.id.recommend_pager_cursor);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
        this.adTag = (ImageView) view.findViewById(R.id.recommend_ad_tag);
        initPagerCursorView();
    }

    private BannerAdapter createAdapter() {
        return new BannerAdapter() { // from class: com.jingdong.common.recommend.forlist.RecommendBannerBViewHolder.2
            {
                RecommendBannerBViewHolder.this = this;
            }

            @Override // com.jd.lib.un.basewidget.widget.banner.BannerAdapter
            public int getItemCount() {
                if (RecommendBannerBViewHolder.this.bannerDna == null || RecommendBannerBViewHolder.this.bannerDna.wareList == null) {
                    return 0;
                }
                return RecommendBannerBViewHolder.this.bannerDna.wareList.size();
            }

            @Override // com.jd.lib.un.basewidget.widget.banner.BannerAdapter
            public View getItemView(int i2, View view, ViewGroup viewGroup) {
                RecommendProduct recommendProduct;
                BannerViewHolder bannerViewHolder;
                if (RecommendBannerBViewHolder.this.bannerDna != null && RecommendBannerBViewHolder.this.bannerDna.wareList != null && (recommendProduct = RecommendBannerBViewHolder.this.bannerDna.wareList.get(i2)) != null) {
                    if (view != null) {
                        bannerViewHolder = (BannerViewHolder) view.getTag(R.id.recommend_banner_holder);
                    } else {
                        view = LayoutInflater.from(RecommendBannerBViewHolder.this.itemView.getContext()).inflate(R.layout.recommend_banner_item_view, (ViewGroup) null);
                        bannerViewHolder = new BannerViewHolder(view);
                        view.setTag(R.id.recommend_banner_holder, bannerViewHolder);
                    }
                    bannerViewHolder.refreshView(recommendProduct);
                    view.setOnClickListener(RecommendBannerBViewHolder.this);
                    view.setTag(recommendProduct);
                }
                return view;
            }
        };
    }

    private void execJump(Context context, JumpEntity jumpEntity) {
        if (jumpEntity != null) {
            JumpUtil.execJump(context, jumpEntity, 1);
        }
    }

    private RecommendProduct getCurrentProduct() {
        BannerView bannerView = this.bannerView;
        return getCurrentProduct(bannerView != null ? bannerView.getCurrentItem() : 0);
    }

    public JSONObject getExpoJsonObject(String str) {
        JSONObject jSONObject = null;
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            try {
                jSONObject2.put("is_cache", isFromNet() ? "0" : "1");
                return jSONObject2;
            } catch (Exception e2) {
                e = e2;
                jSONObject = jSONObject2;
                e.printStackTrace();
                return jSONObject;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    private void initPagerCursorView() {
        this.mLinerPagerCursor.setIndicatorColor(Color.parseColor("#66E2E2E2"), Color.parseColor("#80000000"), Color.parseColor("#FFFFFF"));
        this.mLinerPagerCursor.setIndicatorSize(RecommendViewUtil.getRightSize(12), RecommendViewUtil.getRightSize(24), RecommendViewUtil.getRightSize(12), RecommendViewUtil.getRightSize(8));
    }

    private void setBannerViewAspectRatio(RecommendDna recommendDna) {
        String str = !StringUtil.isEmpty(recommendDna.heightStyle) ? recommendDna.heightStyle : "0.777";
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.constraintLayout);
        constraintSet.setDimensionRatio(R.id.recommend_banner_view, str);
        constraintSet.applyTo(this.constraintLayout);
    }

    private void showAdTag(RecommendProduct recommendProduct) {
        if (recommendProduct != null && !TextUtils.isEmpty(recommendProduct.sourceTag) && recommendProduct.sourceTag.equals("ad")) {
            RecommendViewUtil.visible(this.adTag);
            this.adTag.setBackgroundResource(R.drawable.recommend_banner_ad);
            return;
        }
        RecommendViewUtil.gone(this.adTag);
    }

    public String changeUrl(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str2;
        }
        String replace = str2.replace(KEY_CACHE, isFromNet() ? "0" : "1");
        int i2 = 0;
        if (HomeRecommendContentLayout.isHomePageVisible) {
            if (!HomeRecommendContentLayout.isHomeRecommendFistVisible) {
                i2 = 1;
            }
        } else {
            i2 = HomeRecommendContentLayout.isHomeRecommendFistVisible ? 2 : 3;
        }
        return replace.replace(KEY_STATUS, i2 + "").replace(KEY_HEIGHT, str).replace(KEY_SCREEN_HEIGHT, this.homeContentHeight + "").replace(KEY_FROM_TOP, this.allFloorHeight + "");
    }

    public void expoProduct() {
        RecommendProduct currentProduct;
        try {
            if (this.itemView.getParent() == null || this.itemView.getParent().getParent() == null || (currentProduct = getCurrentProduct()) == null || currentProduct.hasBeenExposured) {
                return;
            }
            double currentExpoPercent = RecommendViewUtil.getCurrentExpoPercent(this.bannerView);
            StringBuilder sb = new StringBuilder();
            sb.append("\u5e7f\u544a\u66dd\u5149===>");
            double d = currentExpoPercent * 100.0d;
            sb.append(d);
            OKLog.d(BANNERB, sb.toString());
            if (d > 0.0d) {
                String str = ((int) d) + "";
                JSONObject expoJsonObject = getExpoJsonObject(currentProduct.exposureJsonSourceValue);
                expoJsonObject.put("high", str);
                currentProduct.hasBeenExposured = true;
                JDMtaUtils.sendExposureDataWithExt(this.itemView.getContext(), RecommendMtaUtils.New_FocusPic_Expo, "", RecommendMtaUtils.Home_PageId, "JDHomeFragment", "", expoJsonObject.toString(), "", "", "", null);
                if (this.bannerView.getCurrentItem() != 0 || isFromNet()) {
                    realExpo(changeUrl(str, currentProduct.client_exposal_url));
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    public boolean isFromNet() {
        RecommendDna recommendDna = this.bannerDna;
        return recommendDna != null && recommendDna.isBackUp == 0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getTag() instanceof RecommendProduct) {
            RecommendProduct recommendProduct = (RecommendProduct) view.getTag();
            String currentViewHeight = RecommendViewUtil.getCurrentViewHeight(this.bannerView);
            String str = recommendProduct.sourceValue;
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.put("high", currentViewHeight);
                jSONObject.put("is_cache", isFromNet() ? "0" : "1");
                str = jSONObject.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            RecommendMtaUtils.bannerViewClickMta(this.itemView.getContext(), str, this.from, recommendProduct.extension_id);
            if (!TextUtils.isEmpty(recommendProduct.client_click_url)) {
                onAdClick(changeUrl(currentViewHeight, recommendProduct.client_click_url));
            }
            execJump(view.getContext(), recommendProduct.jumpObj);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        this.mLinerPagerCursor.onPageScrolled(i2, f2, i3);
        this.mUtils.listen(this.itemView.getContext(), i2, i3);
        expoProduct();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        this.mLinerPagerCursor.onPageSelected(i2);
        showAdTag(getCurrentProduct());
        OKLog.d(BANNERB, "onPageSelected===>" + i2);
    }

    public void setData(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, int i2) {
        this.bannerView.removeOnPageChangeListener(this);
        try {
            RecommendConfig recommendConfig = this.mRecommendConfig;
            if (recommendConfig != null && recommendConfig.getNetExtentParam() != null) {
                String netExtentParam = this.mRecommendConfig.getNetExtentParam();
                if (!TextUtils.isEmpty(netExtentParam)) {
                    JDJSONObject parseObject = JDJSON.parseObject(netExtentParam);
                    this.slipTime = parseObject.optInt("slipTime", 3000);
                    this.scrollLoop = parseObject.optInt("scrollLoop", 1);
                    this.homeContentHeight = parseObject.optInt("homeContentHeight", 0);
                    this.allFloorHeight = parseObject.optInt("allFloorHeight", 0);
                }
            }
        } catch (Exception e2) {
            RecommendUtils.printRecommendException(e2);
        }
        this.bannerDna = recommendDna;
        this.bannerView.addOnPageChangeListener(this);
        this.mUtils = new ExposureVpUtils(this.bannerView, new ExposureVpUtils.VpListener() { // from class: com.jingdong.common.recommend.forlist.RecommendBannerBViewHolder.1
            {
                RecommendBannerBViewHolder.this = this;
            }

            @Override // com.jingdong.jdsdk.mta.ExposureVpUtils.VpListener
            public void onFinish(int i3, int i4, boolean z, long j2, long j3, float f2, float f3, float f4, float f5, float f6) {
                if (z) {
                    try {
                        RecommendProduct currentProduct = RecommendBannerBViewHolder.this.getCurrentProduct(i3);
                        if (currentProduct == null || TextUtils.isEmpty(currentProduct.exposureJsonSourceValue)) {
                            return;
                        }
                        JSONObject expoJsonObject = RecommendBannerBViewHolder.this.getExpoJsonObject(currentProduct.exposureJsonSourceValue);
                        expoJsonObject.put("high", ((int) (RecommendViewUtil.getCurrentExpoPercent(RecommendBannerBViewHolder.this.bannerView) * 100.0d)) + "");
                        expoJsonObject.put("cardlength", DPIUtil.px2dip((float) RecommendBannerBViewHolder.this.bannerView.getHeight()) + "");
                        expoJsonObject.put("cardwidth", RecommendBannerBViewHolder.this.bannerView.getWidth() + "");
                        expoJsonObject.put("slidespeed", f3 + "");
                        expoJsonObject.put("slide_distance", f2 + "");
                        expoJsonObject.put("slide_time", (j3 - j2) + "");
                        expoJsonObject.put("slidetype", f6 + "");
                        expoJsonObject.put("floHeight", RecommendBannerBViewHolder.this.allFloorHeight + "");
                        JDMtaUtils.sendClickDataWithExt(RecommendBannerBViewHolder.this.itemView.getContext(), "New_CCFocusPic_Slide", "", "", RecommendMtaUtils.Home_PageId, "", "", "", expoJsonObject.toString(), null);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
        });
        this.bannerView.setSlideInterval(this.slipTime);
        this.from = i2;
        setFrom(i2);
        setBannerViewAspectRatio(recommendDna);
        List<RecommendProduct> list = recommendDna.wareList;
        int i3 = 8;
        if (list != null && list.size() > 1) {
            this.mLinerPagerCursor.setVisibility(0);
        } else {
            this.mLinerPagerCursor.setVisibility(8);
        }
        List<RecommendProduct> list2 = recommendDna.wareList;
        if (list2 != null && list2.size() > 0) {
            this.mLinerPagerCursor.setPageCount(recommendDna.wareList.size());
            if (this.bannerAdapter == null) {
                this.bannerAdapter = createAdapter();
            }
            int currentItem = this.bannerView.getCurrentItem();
            this.bannerView.setAdapter(this.bannerAdapter);
            if (!recommendDna.isNewBanner) {
                this.bannerView.setCurrentItem(currentItem);
            }
            showAdTag(recommendDna.wareList.get(0));
        }
        this.dot.setVisibility((!recommendDna.isShowDot() || recommendDna.isMonetized) ? 8 : 0);
        View view = this.leftBottomDot;
        if (recommendDna.isShowDot() && recommendDna.isMonetized) {
            i3 = 0;
        }
        view.setVisibility(i3);
        recommendDna.isNewBanner = false;
    }

    public void switchPlayState(boolean z, int i2) {
        boolean z2 = true;
        if (!z || RecommendViewUtil.getCurrentExpoPercent(this.bannerView) <= 0.0d || (this.scrollLoop == 0 && i2 != 0)) {
            z2 = false;
        }
        OKLog.d(BANNERB, "\u64ad\u653e\u72b6\u6001\u5207\u6362==>" + z2);
        if (z2) {
            this.bannerView.startLoop();
        } else {
            this.bannerView.stopLoop();
        }
    }

    public RecommendProduct getCurrentProduct(int i2) {
        List<RecommendProduct> list;
        try {
            RecommendDna recommendDna = this.bannerDna;
            if (recommendDna == null || (list = recommendDna.wareList) == null || i2 >= list.size()) {
                return null;
            }
            return this.bannerDna.wareList.get(i2);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                return null;
            }
            return null;
        }
    }
}
