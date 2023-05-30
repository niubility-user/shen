package com.jd.lib.productdetail.mainimage.holder;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.PdOneToNPriceVo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDrugInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessWareImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.GoToBigEntnty;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.bean.PdMainImagePagerEntity;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImageParams;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.presenter.PdMainStaticData;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jd.lib.productdetail.mainimage.view.PdPriceBannerView;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDImageReportListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.image.JDFrescoUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.net.InetAddress;
import java.net.URI;
import jpbury.t;

/* loaded from: classes.dex */
public class PdMainImageBaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener, LifecycleObserver {
    public PdPriceBannerView A;

    /* renamed from: g */
    public String f4647g;

    /* renamed from: h */
    public String f4648h;

    /* renamed from: i */
    public Context f4649i;

    /* renamed from: j */
    public boolean f4650j;

    /* renamed from: k */
    public boolean f4651k;

    /* renamed from: l */
    public SimpleDraweeView f4652l;

    /* renamed from: m */
    public SimpleDraweeView f4653m;

    /* renamed from: n */
    public PdMainImagePresenter f4654n;
    public LifecycleOwner o;
    public ImageRequestListener<ImageInfo> p;
    public PdMainImagePagerEntity q;
    public WareBusinessMagicHeadPicInfoEntity r;
    public JDDisplayImageOptions s;
    public FrameLayout t;
    public UnBottomDialog u;
    public WareBusinessUnitMainImageEntity v;
    public Observer<Integer> w;
    public Observer<PDViewEvent> x;
    public Observer<PdCommentInfo> y;
    public Observer<PdMImageEventEntity> z;

    /* loaded from: classes15.dex */
    public class a implements Observer<PdCommentInfo> {
        public a() {
            PdMainImageBaseHolder.this = r1;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdCommentInfo pdCommentInfo) {
            try {
                PdMainImageBaseHolder.this.f(pdCommentInfo);
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements JDImageReportListener {
        public b() {
            PdMainImageBaseHolder.this = r1;
        }

        @Override // com.jingdong.app.util.image.listener.JDImageReportListener
        public void report(String str, int i2, int i3, int i4, long j2, long j3) {
            String str2;
            String host;
            PdMainImageBaseHolder pdMainImageBaseHolder = PdMainImageBaseHolder.this;
            Context context = pdMainImageBaseHolder.f4649i;
            String str3 = pdMainImageBaseHolder.q.skuId;
            String c2 = PdMainImageBaseHolder.c(pdMainImageBaseHolder, j2);
            String c3 = PdMainImageBaseHolder.c(PdMainImageBaseHolder.this, j3);
            String valueOf = String.valueOf(i2);
            PdMainImageBaseHolder pdMainImageBaseHolder2 = PdMainImageBaseHolder.this;
            pdMainImageBaseHolder2.getClass();
            try {
                host = URI.create(str).getHost();
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                }
            }
            if (host.equals(pdMainImageBaseHolder2.f4648h) && !TextUtils.isEmpty(pdMainImageBaseHolder2.f4647g)) {
                str2 = pdMainImageBaseHolder2.f4647g;
                JDMtaUtils.sendCDNImageData(context, "com.jd.lib.productdetail.ProductDetailActivity", str3, c2, str, c3, valueOf, str2);
            }
            String hostAddress = InetAddress.getByName(host).getHostAddress();
            if (!TextUtils.isEmpty(hostAddress)) {
                pdMainImageBaseHolder2.f4647g = hostAddress;
                pdMainImageBaseHolder2.f4648h = host;
            }
            str2 = pdMainImageBaseHolder2.f4647g;
            JDMtaUtils.sendCDNImageData(context, "com.jd.lib.productdetail.ProductDetailActivity", str3, c2, str, c3, valueOf, str2);
        }
    }

    /* loaded from: classes15.dex */
    public class c implements ImageRequestListener<ImageInfo> {

        /* loaded from: classes15.dex */
        public class a implements Runnable {

            /* renamed from: com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder$c$a$a */
            /* loaded from: classes15.dex */
            public class ViewOnClickListenerC0156a implements View.OnClickListener {
                public ViewOnClickListenerC0156a() {
                    a.this = r1;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PdMainImageBaseHolder.this.z();
                }
            }

            public a() {
                c.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                PdMainImageBaseHolder pdMainImageBaseHolder = PdMainImageBaseHolder.this;
                if (pdMainImageBaseHolder.f4651k || pdMainImageBaseHolder.f4652l == null || JDFrescoUtils.needNoImage()) {
                    return;
                }
                PdMainImageBaseHolder.this.f4652l.setOnClickListener(new ViewOnClickListenerC0156a());
            }
        }

        public c() {
            PdMainImageBaseHolder.this = r1;
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
            Context context = PdMainImageBaseHolder.this.f4649i;
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).post(new a());
            }
            PdMainImageBaseHolder pdMainImageBaseHolder = PdMainImageBaseHolder.this;
            pdMainImageBaseHolder.f4654n.mtaExposure("Productdetail_PhotobrowserFalse", "", pdMainImageBaseHolder.q.imageUrl);
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onSuccess(ImageInfo imageInfo) {
            PdMainImageBaseHolder pdMainImageBaseHolder = PdMainImageBaseHolder.this;
            if (pdMainImageBaseHolder.f4651k) {
                return;
            }
            ((BaseActivity) pdMainImageBaseHolder.f4649i).post(new d(this));
        }
    }

    public PdMainImageBaseHolder(@NonNull View view, View view2) {
        super(view);
        Context context = view.getContext();
        this.f4649i = context;
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).getLifecycle().addObserver(this);
            this.o = ViewTreeLifecycleOwner.get(b(this.f4649i));
        }
        this.f4652l = (SimpleDraweeView) view.findViewById(R.id.pd_topimage_base);
        this.A = (PdPriceBannerView) view.findViewById(R.id.lib_pd_mainimage_banner_price);
        this.t = (FrameLayout) view.findViewById(R.id.pd_topimage_holder_view);
        this.f4652l.setOnClickListener(this);
        this.f4653m = (SimpleDraweeView) view.findViewById(R.id.pd_topimage_shadow);
        if (view2 != null && view2.getParent() == null) {
            this.t.setVisibility(0);
            this.t.removeAllViews();
            this.t.addView(view2, new ViewGroup.LayoutParams(-1, -1));
        } else {
            this.t.setVisibility(8);
        }
        if (this.w == null) {
            this.w = new Observer() { // from class: com.jd.lib.productdetail.mainimage.holder.c
                {
                    PdMainImageBaseHolder.this = this;
                }

                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    PdMainImageBaseHolder.this.k((Integer) obj);
                }
            };
        }
        if (this.x == null) {
            this.x = new Observer() { // from class: com.jd.lib.productdetail.mainimage.holder.a
                {
                    PdMainImageBaseHolder.this = this;
                }

                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    PdMainImageBaseHolder.this.p((PDViewEvent) obj);
                }
            };
        }
        if (this.z == null) {
            this.z = new Observer() { // from class: com.jd.lib.productdetail.mainimage.holder.b
                {
                    PdMainImageBaseHolder.this = this;
                }

                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    PdMainImageBaseHolder.this.q((PdMImageEventEntity) obj);
                }
            };
        }
        if (this.y == null) {
            this.y = new a();
        }
        try {
            x();
            d(view2);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static String c(PdMainImageBaseHolder pdMainImageBaseHolder, long j2) {
        pdMainImageBaseHolder.getClass();
        if (j2 == 0) {
            j2 = System.currentTimeMillis();
        }
        double d = j2;
        Double.isNaN(d);
        return String.format("%.6f", Double.valueOf((d + 0.0d) / 1000.0d));
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x001c, code lost:
        if (r0.position == r3.intValue()) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void k(java.lang.Integer r3) {
        /*
            r2 = this;
            int r0 = r2.getAdapterPosition()     // Catch: java.lang.Exception -> L23
            r1 = 0
            if (r0 < 0) goto L12
            int r0 = r2.getAdapterPosition()     // Catch: java.lang.Exception -> L23
            int r3 = r3.intValue()     // Catch: java.lang.Exception -> L23
            if (r0 != r3) goto L1f
            goto L1e
        L12:
            com.jd.lib.productdetail.mainimage.bean.PdMainImagePagerEntity r0 = r2.q     // Catch: java.lang.Exception -> L23
            if (r0 == 0) goto L1f
            int r0 = r0.position     // Catch: java.lang.Exception -> L23
            int r3 = r3.intValue()     // Catch: java.lang.Exception -> L23
            if (r0 != r3) goto L1f
        L1e:
            r1 = 1
        L1f:
            r2.a(r1)     // Catch: java.lang.Exception -> L23
            goto L27
        L23:
            r3 = move-exception
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter.reportExceptionToBugly(r3)
        L27:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder.k(java.lang.Integer):void");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onLifecycleDestory() {
        r();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onLifecyclePause() {
        E();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onLifecycleResume() {
        F();
    }

    public /* synthetic */ void p(PDViewEvent pDViewEvent) {
        try {
            g(pDViewEvent);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public /* synthetic */ void q(PdMImageEventEntity pdMImageEventEntity) {
        if (pdMImageEventEntity != null) {
            try {
                if (pdMImageEventEntity.pdImageEventCodeCode != PdImageEventCode.NONE) {
                    h(pdMImageEventEntity);
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
    }

    public boolean A() {
        return false;
    }

    public void E() {
    }

    public void F() {
    }

    public void a(boolean z) {
        this.f4650j = z;
    }

    public View b(Context context) {
        if (context instanceof BaseActivity) {
            return ((BaseActivity) context).getWindow().getDecorView();
        }
        return null;
    }

    public void d(View view) {
    }

    public void e(View view, String str, boolean z, boolean z2) {
        try {
            if (this.f4651k) {
                return;
            }
            if (!z2 || PDUtils.repeatClick()) {
                this.u.addContentWithHeight(view, str, z);
                this.u.show();
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public void f(PdCommentInfo pdCommentInfo) {
    }

    public void g(PDViewEvent pDViewEvent) {
    }

    public void h(PdMImageEventEntity pdMImageEventEntity) {
    }

    public void i(PdMainImagePagerEntity pdMainImagePagerEntity) {
        LifecycleOwner lifecycleOwner;
        PdPriceBannerView pdPriceBannerView;
        PdOneToNPriceVo pdOneToNPriceVo;
        if (pdMainImagePagerEntity != null) {
            this.q = pdMainImagePagerEntity;
            this.v = pdMainImagePagerEntity.topImageEntity;
            this.r = pdMainImagePagerEntity.magicHeadPicData;
            SimpleDraweeView simpleDraweeView = this.f4652l;
            if (simpleDraweeView != null && this.t != null) {
                simpleDraweeView.getLayoutParams().height = v();
                this.t.getLayoutParams().height = v();
            }
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
            if (wareBusinessMagicHeadPicInfoEntity != null) {
                WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData;
                WareBusinessWareImageEntity wareBusinessWareImageEntity = wareBusinessMagicHeadPicInfoEntity.wareImage;
                if (wareBusinessWareImageEntity != null && TextUtils.equals(wareBusinessWareImageEntity.imageType, WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF)) {
                    Glide.with(this.f4652l).load(this.r.wareImage.small).into(this.f4652l);
                } else {
                    z();
                }
                n();
                WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 = this.r;
                if (wareBusinessMagicHeadPicInfoEntity2 != null && (pdPriceBannerView = this.A) != null) {
                    WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity2.bizData;
                    if (wareBuinessUnitMainImageBizDataEntity2 != null && (pdOneToNPriceVo = wareBuinessUnitMainImageBizDataEntity2.oneToNPriceVo) != null) {
                        pdPriceBannerView.bindData(wareBusinessMagicHeadPicInfoEntity2.anchorType, pdOneToNPriceVo, this.f4654n);
                    } else {
                        pdPriceBannerView.setVisibility(8);
                    }
                }
                if (!TextUtils.equals(this.r.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_WAREIMAGESEC) && !TextUtils.equals(this.r.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_WAREIMAGE) && !TextUtils.equals(this.r.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ATLAS) && (lifecycleOwner = this.o) != null) {
                    this.f4654n.pageSelectedIndex.observe(lifecycleOwner, this.w);
                    this.f4654n.pDViewEvent.observe(this.o, this.x);
                    this.f4654n.viewReceiveLiveData.observe(this.o, this.z);
                    this.f4654n.pdCommentInfo.observe(this.o, this.y);
                }
                this.itemView.setTag(Integer.valueOf(v() + PDUtils.dip2px(this.f4649i, PdMainStaticData.HOLDER_SHADOW_HEIGHT)));
            }
        }
    }

    public void j(PdMainImagePresenter pdMainImagePresenter) {
        this.f4654n = pdMainImagePresenter;
        this.u = pdMainImagePresenter.mLayerDialog;
        if (pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL) {
            this.f4653m.getLayoutParams().height = PDUtils.dip2px(this.f4649i, PdMainStaticData.HOLDER_SHADOW_HEIGHT);
            this.f4653m.setVisibility(4);
            return;
        }
        this.f4653m.setVisibility(8);
    }

    public void l(String str, int i2) {
        if (TextUtils.isEmpty(str) || this.f4654n == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("type", (Object) str);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        sb.append(wareBusinessMagicHeadPicInfoEntity != null ? wareBusinessMagicHeadPicInfoEntity.ztfwTemplateType : -100);
        jDJSONObject.put("frame_info", (Object) sb.toString());
        if (this.f4654n.mAskQuesInfos != null && TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ASK)) {
            jDJSONObject.put("QuesNum", (Object) Integer.valueOf(this.f4654n.mAskQuesInfos.size()));
        } else {
            jDJSONObject.put("QuesNum", (Object) "");
        }
        jDJSONObject.put("LableNum", (Object) "");
        jDJSONObject.put("isPhoto", (Object) "0");
        jDJSONObject.put("rankid", (Object) "");
        jDJSONObject.put("touchstone_expids", (Object) "");
        jDJSONObject.put("click_pos", (Object) Integer.valueOf(i2));
        this.f4654n.mtaClick("Productdetail_FunctionEntrance", jDJSONObject.toJSONString());
    }

    public void m(boolean z, boolean z2, boolean z3) {
        if (this.f4654n != null) {
            GoToBigEntnty goToBigEntnty = new GoToBigEntnty();
            goToBigEntnty.fromBigImage = z;
            goToBigEntnty.autoPlay = z2;
            goToBigEntnty.pureMode = z3;
            goToBigEntnty.position = this.q.position;
            this.f4654n.toBigEntntyMutableLiveData.setValue(goToBigEntnty);
        }
    }

    public void n() {
    }

    public void o(View view) {
        e(view, null, false, true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessUnitMainImageEntity.ExtMapEntity.CategoryEntity categoryEntity;
        try {
            if (PDUtils.repeatClick()) {
                this.f4654n.mtaClick("Productdetail_MainPhoto_New", w(), true);
                if (y()) {
                    return;
                }
                if (!A()) {
                    m(true, false, false);
                }
                String str2 = TextUtils.equals(this.r.anchorType, "video") ? "2" : "1";
                PdMainImagePresenter pdMainImagePresenter = this.f4654n;
                if (pdMainImagePresenter != null) {
                    try {
                        JDJSONObject parseObject = JDJSON.parseObject(pdMainImagePresenter.getMainImageParams().dJTemplateType);
                        if (parseObject != null) {
                            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
                            if (wareBusinessUnitMainImageEntity != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && (categoryEntity = extMapEntity.category) != null) {
                                PDUtils.setFloorCid(parseObject, String.valueOf(categoryEntity.fstId), String.valueOf(this.v.extMap.category.sndId), String.valueOf(this.v.extMap.category.thdId));
                            }
                            PdMainImageParams pdMainImageParams = this.f4654n.mainImageParams;
                            if (pdMainImageParams != null) {
                                PDUtils.setFloorPriceJson(parseObject, pdMainImageParams.floorPriceMta);
                                PDUtils.setCardInfo(parseObject, this.f4654n.mainImageParams.brandId, "bpMainImage", this.f4652l);
                            }
                        }
                        str = JDJSON.toJSONString(parseObject);
                    } catch (Exception unused) {
                        str = this.f4654n.getMainImageParams().dJTemplateType;
                    }
                    this.f4654n.mtaClick("Productdetail_Photo", str, str2, true);
                }
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public void r() {
        this.f4651k = true;
    }

    public void s() {
        UnBottomDialog unBottomDialog = this.u;
        if (unBottomDialog != null) {
            unBottomDialog.dismiss();
        }
    }

    public BaseActivity t() {
        Context context = this.f4649i;
        if (context instanceof BaseActivity) {
            return (BaseActivity) context;
        }
        return null;
    }

    public String u() {
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        return wareBusinessMagicHeadPicInfoEntity == null ? "" : wareBusinessMagicHeadPicInfoEntity.anchorType;
    }

    public int v() {
        int i2;
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        return (pdMainImagePresenter == null || (i2 = pdMainImagePresenter.appImageHeight) <= 0) ? PDUtils.getAppWidth((Activity) this.f4649i) : i2;
    }

    public String w() {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        PdMainImageParams pdMainImageParams;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareImageRecommendRankEntity wareImageRecommendRankEntity;
        PdDrugInfo pdDrugInfo;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdDrugBizData pdDrugBizData;
        PdDrugInfo pdDrugInfo2;
        if (this.r == null || (wareBusinessUnitMainImageEntity = this.v) == null || wareBusinessUnitMainImageEntity.extMap == null) {
            return "";
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (this.f4654n.mAskQuesInfos != null && TextUtils.equals(u(), WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ASK)) {
            jDJSONObject.put("QuesNum", (Object) Integer.valueOf(this.f4654n.mAskQuesInfos.size()));
        } else {
            jDJSONObject.put("QuesNum", (Object) "");
        }
        jDJSONObject.put("type", (Object) u());
        WareBusinessUnitMainImageEntity.ExtMapEntity.CategoryEntity categoryEntity = this.v.extMap.category;
        if (categoryEntity != null) {
            jDJSONObject.put("categoryId3", (Object) Integer.valueOf(categoryEntity.thdId));
        }
        if (TextUtils.equals(this.r.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_YPSMS) && (pdDrugInfo = this.r.drugInfo) != null) {
            if (pdDrugInfo.tips != null) {
                jDJSONObject.put("LableNum", (Object) (this.r.drugInfo.tips.size() + ""));
            } else {
                jDJSONObject.put("LableNum", (Object) "0");
            }
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 = this.r;
            if (wareBusinessMagicHeadPicInfoEntity2 != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity2.bizData) != null && (pdDrugBizData = wareBuinessUnitMainImageBizDataEntity.drugInstructionsBizData) != null && (pdDrugInfo2 = pdDrugBizData.drugInfo) != null && !TextUtils.isEmpty(pdDrugInfo2.imageInCell)) {
                jDJSONObject.put("isPhoto", (Object) "1");
            } else {
                jDJSONObject.put("isPhoto", (Object) "0");
            }
        } else {
            jDJSONObject.put("LableNum", (Object) "");
            jDJSONObject.put("isPhoto", (Object) "");
        }
        if (TextUtils.equals(u(), WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_GOLD_RANK_BANG_DAN) && (wareBusinessMagicHeadPicInfoEntity = this.r) != null && (wareImageRecommendRankEntity = wareBusinessMagicHeadPicInfoEntity.bangDanInfo) != null) {
            jDJSONObject.put("rankid", (Object) wareImageRecommendRankEntity.rankId);
            jDJSONObject.put("rank_type", (Object) wareImageRecommendRankEntity.rankType);
            jDJSONObject.put("clkSrv", (Object) wareImageRecommendRankEntity.clkSrv);
        }
        if (!TextUtils.isEmpty(this.v.extMap.activityId)) {
            jDJSONObject.put("activity_id", (Object) this.v.extMap.activityId);
        } else {
            jDJSONObject.put("activity_id", (Object) "-100");
        }
        if (!TextUtils.isEmpty(this.v.extMap.groupId)) {
            jDJSONObject.put("group_id", (Object) this.v.extMap.groupId);
        } else {
            jDJSONObject.put("group_id", (Object) "-100");
        }
        WareBusinessUnitMainImageEntity.ExtMapEntity.CategoryEntity categoryEntity2 = this.v.extMap.category;
        if (categoryEntity2 != null) {
            jDJSONObject.put("categoryId3", (Object) Integer.valueOf(categoryEntity2.thdId));
            try {
                PDUtils.setFloorCid(jDJSONObject, String.valueOf(this.v.extMap.category.fstId), String.valueOf(this.v.extMap.category.sndId), String.valueOf(this.v.extMap.category.thdId));
            } catch (Exception unused) {
            }
        }
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter != null && (pdMainImageParams = pdMainImagePresenter.mainImageParams) != null) {
            PDUtils.setFloorPriceJson(jDJSONObject, pdMainImageParams.floorPriceMta);
            PDUtils.setCardInfo(jDJSONObject, this.f4654n.mainImageParams.brandId, "bpMainImage", this.f4652l);
        }
        jDJSONObject.put("frame", (Object) Integer.valueOf(this.q.position + 1));
        return jDJSONObject.toJSONString();
    }

    public final void x() {
        this.s = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.ARGB_8888).cacheInMemory(true).cacheOnDisk(true).setReportListener(new b());
        this.p = new c();
    }

    public boolean y() {
        return false;
    }

    public void z() {
        PdMainImagePresenter pdMainImagePresenter;
        if (this.q != null) {
            try {
                this.s.setPlaceholder(20);
                JDImageLoader.display(this.q.imageUrl, this.f4652l, this.s, this.p);
                SimpleDraweeView simpleDraweeView = this.f4653m;
                if (simpleDraweeView == null || (pdMainImagePresenter = this.f4654n) == null || pdMainImagePresenter.imageFromType != PdImageFromType.PRODUCTDETAIL || this.f4651k || this.q.mIsDefault) {
                    return;
                }
                simpleDraweeView.setVisibility(4);
            } catch (Exception e2) {
                this.s.setPlaceholder(20);
                JDImageLoader.display(this.q.imageUrl, this.f4652l, this.s, this.p);
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
    }
}
