package com.jd.lib.productdetail.mainimage.bigimage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.BigImageEntity;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDpgSmallInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDrugInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdMainSku;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopVideoControl;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageQaEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendEntity;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDCalorieImageUtil;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager;
import com.jd.lib.productdetail.mainimage.holder.ask.PdMAskView;
import com.jd.lib.productdetail.mainimage.holder.comment.PdMImageCommentNewRootView;
import com.jd.lib.productdetail.mainimage.holder.comment.PdMImageCommentZcxView;
import com.jd.lib.productdetail.mainimage.holder.dym.PdMCooperManager;
import com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftView;
import com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendItemView;
import com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendNewView;
import com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendView;
import com.jd.lib.productdetail.mainimage.holder.suit.PdTopImageSuitView;
import com.jd.lib.productdetail.mainimage.holder.ypsms.PdMYpsmsView;
import com.jd.lib.productdetail.mainimage.old.g0;
import com.jd.lib.productdetail.mainimage.old.k;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImageParams;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.utils.PdMCooTouchImageView;
import com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.address.AddressConstant;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.ui.JDBottomDialog;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.video.player.VideoPlayView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.image.JDFrescoUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.SDKUtils;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.sdk.platform.floor.isv.FloorCooperateManager;
import com.jingdong.sdk.platform.floor.isv.IBaseView;
import com.jingdong.sdk.platform.utils.PlatformTools;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes15.dex */
public class PdBigImageActivity extends BaseActivity {
    public static PdMainImagePresenter Q;
    public static boolean R;
    public String A;
    public WareBusinessTopVideoControl B;
    public com.jd.lib.productdetail.mainimage.old.k C;
    public View D;
    public HashMap E;
    public PdCommentInfo F;
    public boolean G;
    public HeadPicGiftInfoEntity H;
    public BigImageEntity I;
    public List<String> J;
    public PdMainImageParams K;
    public String L;
    public String M;
    public String N;
    public ImageFragment P;

    /* renamed from: g */
    public PdMPullToRefreshViewPager f4550g;

    /* renamed from: h */
    public PdMDropDownViewPager f4551h;

    /* renamed from: i */
    public TextView f4552i;

    /* renamed from: j */
    public SparseArrayCompat<String> f4553j;

    /* renamed from: k */
    public int f4554k;

    /* renamed from: l */
    public d f4555l;

    /* renamed from: m */
    public View f4556m;
    public View p;
    public AlphaAnimation r;
    public AlphaAnimation s;
    public String t;
    public String u;
    public boolean v;
    public boolean y;
    public String z;

    /* renamed from: n */
    public boolean f4557n = true;
    public boolean o = false;
    public boolean q = false;
    public boolean w = false;
    public boolean x = false;
    public PdMDropDownViewPager.c O = new c();

    /* loaded from: classes15.dex */
    public static class ImageFragment extends Fragment {
        public ArrayList<PdDpgSmallInfo> A;
        public PdTopImageSuitView B;
        public PdMAskView C;
        public PdMImageRecommendView D;
        public PdMImageCommentNewRootView E;
        public PdMImageRecommendNewView F;
        public PdPreferentialRecommendProductListInfo G;
        public WareBusinessTopVideoControl H;
        public PdCommentInfo I;
        public PdDrugInfo N;
        public PdMYpsmsView O;
        public int P;
        public boolean Q;
        public String R;
        public WareImageRecommendEntity T;
        public String U;
        public boolean V;
        public int W;
        public String X;
        public HeadPicGiftInfoEntity Y;
        public PdMImageGiftView Z;
        public BigImageEntity a0;
        public WareImageQaEntity b0;
        public boolean c0;
        public String d0;
        public FrameLayout e0;
        public String f0;

        /* renamed from: g */
        public String f4558g;
        public String g0;

        /* renamed from: h */
        public String f4559h;
        public String h0;

        /* renamed from: i */
        public boolean f4560i;

        /* renamed from: j */
        public boolean f4561j;

        /* renamed from: k */
        public int f4562k;

        /* renamed from: m */
        public View f4564m;

        /* renamed from: n */
        public ViewGroup f4565n;
        public ViewGroup o;
        public ViewGroup p;
        public PdBigImageActivity q;
        public PdMCooTouchImageView r;
        public SimpleDraweeView s;
        public boolean t;
        public com.jd.lib.productdetail.mainimage.old.k u;
        public ImageView v;
        public String x;
        public PdMainSku y;
        public ArrayList<PdDpgSmallInfo> z;

        /* renamed from: l */
        public JDDisplayImageOptions f4563l = JDDisplayImageOptions.createSimple();
        public int w = -1;
        public int J = -1;
        public int K = -1;
        public int L = -1;
        public int M = -1;
        public int S = -1;

        /* loaded from: classes15.dex */
        public class a implements k.InterfaceC0159k {
            public a() {
                ImageFragment.this = r1;
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.InterfaceC0159k
            public void a(View view) {
                ImageView imageView = ImageFragment.this.v;
                if (imageView != null) {
                    if (PdBigImageActivity.R) {
                        imageView.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
                    } else {
                        imageView.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
                    }
                    ImageFragment.this.v.setVisibility(8);
                }
            }
        }

        /* loaded from: classes15.dex */
        public class b implements k.n {
            public b() {
                ImageFragment.this = r1;
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.n
            public void a() {
                PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
                if (pdMainImagePresenter != null) {
                    pdMainImagePresenter.mtaClick("Productdetail_PhotoVideo", "", "1");
                }
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.n
            public void a(boolean z) {
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.n
            public void b() {
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.n
            public void b(boolean z) {
                if (z) {
                    ImageFragment imageFragment = ImageFragment.this;
                    imageFragment.u.o = false;
                    if (PdBigImageActivity.R) {
                        imageFragment.v.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
                    } else {
                        imageFragment.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
                    }
                    ImageFragment.this.v.setVisibility(8);
                    return;
                }
                PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
                if (pdMainImagePresenter != null) {
                    pdMainImagePresenter.mtaClick("Productdetail_PhotoVideo", "", "2");
                }
                ImageFragment imageFragment2 = ImageFragment.this;
                imageFragment2.u.o = true;
                imageFragment2.v.setVisibility(0);
                if (PdBigImageActivity.R) {
                    PDCalorieImageUtil.get().display("2707", ImageFragment.this.v);
                } else {
                    ImageFragment.this.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
                }
            }
        }

        /* loaded from: classes15.dex */
        public class c implements k.p {
            public c() {
                ImageFragment.this = r1;
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.p
            public void a() {
                com.jd.lib.productdetail.mainimage.old.k kVar = ImageFragment.this.u;
                if (kVar == null) {
                    return;
                }
                if (kVar.C()) {
                    if (PdBigImageActivity.R) {
                        ImageFragment.this.v.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
                    } else {
                        ImageFragment.this.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
                    }
                    ImageFragment.this.v.setVisibility(8);
                    return;
                }
                if (PdBigImageActivity.R) {
                    PDCalorieImageUtil.get().display("2707", ImageFragment.this.v);
                } else {
                    ImageFragment.this.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
                }
                if (ImageFragment.this.u.s() == -1) {
                    ImageFragment.this.v.setVisibility(0);
                }
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.p
            public void b() {
                com.jd.lib.productdetail.mainimage.old.k kVar = ImageFragment.this.u;
                if (kVar == null) {
                    return;
                }
                if (kVar.s() == -1) {
                    ImageFragment.this.v.setVisibility(0);
                } else {
                    ImageFragment.this.v.setVisibility(8);
                }
                if (ImageFragment.this.u.C()) {
                    if (PdBigImageActivity.R) {
                        ImageFragment.this.v.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
                    } else {
                        ImageFragment.this.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
                    }
                } else if (PdBigImageActivity.R) {
                    PDCalorieImageUtil.get().display("2707", ImageFragment.this.v);
                } else {
                    ImageFragment.this.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
                }
            }
        }

        /* loaded from: classes15.dex */
        public class d implements k.o {
            public d() {
                ImageFragment.this = r1;
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.o
            public void a() {
                ImageFragment.this.v.setVisibility(8);
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.o
            public void a(int i2) {
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.o
            public void onVideoFinish() {
                ImageFragment imageFragment;
                com.jd.lib.productdetail.mainimage.old.k kVar;
                PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                if (pdBigImageActivity == null || pdBigImageActivity.isFinishing() || (kVar = (imageFragment = ImageFragment.this).u) == null) {
                    return;
                }
                WareBusinessTopVideoControl wareBusinessTopVideoControl = imageFragment.H;
                String str = imageFragment.R;
                String str2 = imageFragment.U;
                String str3 = imageFragment.f0;
                String str4 = imageFragment.g0;
                String str5 = imageFragment.h0;
                PdBigImageActivity pdBigImageActivity2 = imageFragment.q;
                kVar.i(wareBusinessTopVideoControl, kVar, true, str3, str4, str5, pdBigImageActivity2 != null ? pdBigImageActivity2.f4556m : null);
                ImageFragment.this.u.c();
                ImageFragment.this.d(true, false);
            }
        }

        /* loaded from: classes15.dex */
        public class e implements k.l {
            public e() {
                ImageFragment.this = r1;
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.l
            public void a() {
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.l
            public void a(boolean z) {
                if (z) {
                    ImageFragment.j(ImageFragment.this);
                } else {
                    ImageFragment.k(ImageFragment.this);
                }
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.l
            public void b() {
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.l
            public void b(boolean z) {
            }

            @Override // com.jd.lib.productdetail.mainimage.old.k.l
            public void onClose() {
            }
        }

        /* loaded from: classes15.dex */
        public class f implements Runnable {
            public f() {
                ImageFragment.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                if (pdBigImageActivity == null || pdBigImageActivity.isFinishing()) {
                    return;
                }
                ImageFragment imageFragment = ImageFragment.this;
                if (imageFragment.P == imageFragment.W) {
                    imageFragment.c(imageFragment.u.F);
                }
            }
        }

        /* loaded from: classes15.dex */
        public class g implements RequestListener<Drawable> {
            public g() {
                ImageFragment.this = r1;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                ImageFragment imageFragment;
                SimpleDraweeView simpleDraweeView;
                PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                if (pdBigImageActivity != null && !pdBigImageActivity.isFinishing() && (simpleDraweeView = (imageFragment = ImageFragment.this).s) != null && imageFragment.f4564m != null && imageFragment.f4565n != null && imageFragment.p != null) {
                    simpleDraweeView.setVisibility(8);
                    ImageFragment.this.f4564m.setVisibility(8);
                    ImageFragment.this.p.setVisibility(0);
                }
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                ImageFragment imageFragment;
                SimpleDraweeView simpleDraweeView;
                PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                if (pdBigImageActivity != null && !pdBigImageActivity.isFinishing() && (simpleDraweeView = (imageFragment = ImageFragment.this).s) != null && imageFragment.f4564m != null && imageFragment.f4565n != null && imageFragment.p != null) {
                    simpleDraweeView.setVisibility(0);
                    ImageFragment.this.p.setVisibility(8);
                    ImageFragment.this.f4564m.setVisibility(8);
                }
                return false;
            }
        }

        /* loaded from: classes15.dex */
        public class h extends JDSimpleImageLoadingListener {

            /* loaded from: classes15.dex */
            public class a implements Runnable {

                /* renamed from: g */
                public final /* synthetic */ String f4569g;

                public a(String str) {
                    h.this = r1;
                    this.f4569g = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                    if (pdBigImageActivity == null || pdBigImageActivity.isFinishing()) {
                        return;
                    }
                    ImageFragment imageFragment = ImageFragment.this;
                    SparseArrayCompat<String> sparseArrayCompat = imageFragment.q.f4553j;
                    if (sparseArrayCompat == null) {
                        return;
                    }
                    sparseArrayCompat.put(imageFragment.f4562k, this.f4569g);
                    ImageFragment.this.e(true, true, false);
                }
            }

            /* loaded from: classes15.dex */
            public class b implements Runnable {
                public b() {
                    h.this = r1;
                }

                @Override // java.lang.Runnable
                public void run() {
                    PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                    if (pdBigImageActivity == null || pdBigImageActivity.isFinishing() || ImageFragment.this.isRemoving() || ImageFragment.this.isDetached() || !ImageFragment.this.isVisible() || !TextUtils.isEmpty(ImageFragment.this.d0)) {
                        return;
                    }
                    if (!JDFrescoUtils.needNoImage()) {
                        ImageFragment.this.e(true, true, true);
                    } else {
                        ImageFragment.this.e(true, true, false);
                    }
                }
            }

            public h() {
                ImageFragment.this = r1;
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str, View view) {
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                if (pdBigImageActivity == null) {
                    return;
                }
                pdBigImageActivity.runOnUiThread(new a(str));
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                ExceptionReporter.reportBitmapException(str, jDFailReason, PdBigImageActivity.class.getSimpleName(), 0);
                PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                if (pdBigImageActivity == null) {
                    return;
                }
                pdBigImageActivity.runOnUiThread(new b());
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str, View view) {
            }
        }

        /* loaded from: classes15.dex */
        public class i implements View.OnClickListener {
            public i() {
                ImageFragment.this = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImageFragment imageFragment = ImageFragment.this;
                com.jd.lib.productdetail.mainimage.old.k kVar = imageFragment.u;
                if (kVar == null) {
                    return;
                }
                if (kVar.C()) {
                    PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
                    if (pdMainImagePresenter != null) {
                        pdMainImagePresenter.mtaClick("Productdetail_PhotoVideo", "", "2");
                    }
                    com.jd.lib.productdetail.mainimage.old.k kVar2 = imageFragment.u;
                    WareBusinessTopVideoControl wareBusinessTopVideoControl = imageFragment.H;
                    String str = imageFragment.f0;
                    String str2 = imageFragment.g0;
                    String str3 = imageFragment.h0;
                    PdBigImageActivity pdBigImageActivity = imageFragment.q;
                    kVar2.i(wareBusinessTopVideoControl, kVar2, false, str, str2, str3, pdBigImageActivity != null ? pdBigImageActivity.f4556m : null);
                    imageFragment.u.y(true);
                    imageFragment.d(true, false);
                    imageFragment.Q = true;
                    return;
                }
                if (!imageFragment.u.C()) {
                    com.jd.lib.productdetail.mainimage.old.k kVar3 = imageFragment.u;
                    if (kVar3.f5170n) {
                        VideoPlayView videoPlayView = kVar3.a;
                        if (videoPlayView != null) {
                            kVar3.o = false;
                            videoPlayView.startPlay();
                            kVar3.p = false;
                            kVar3.w(true);
                        }
                        imageFragment.d(true, true);
                        imageFragment.Q = false;
                        imageFragment.u.K();
                        return;
                    }
                }
                imageFragment.Q = false;
                imageFragment.c(false);
                imageFragment.u.J();
            }
        }

        /* loaded from: classes15.dex */
        public class j implements View.OnLongClickListener {
            public j() {
                ImageFragment.this = r1;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
                if (pdMainImagePresenter != null) {
                    pdMainImagePresenter.mtaClick("Productdetail_PhotoLong");
                }
                return ImageFragment.f(ImageFragment.this);
            }
        }

        /* loaded from: classes15.dex */
        public class k implements View.OnClickListener {
            public k() {
                ImageFragment.this = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                if (pdBigImageActivity != null) {
                    pdBigImageActivity.b();
                }
            }
        }

        /* loaded from: classes15.dex */
        public class l implements View.OnClickListener {
            public l() {
                ImageFragment.this = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PdBigImageActivity pdBigImageActivity = ImageFragment.this.q;
                if (pdBigImageActivity != null) {
                    pdBigImageActivity.b();
                }
            }
        }

        /* loaded from: classes15.dex */
        public class m implements View.OnClickListener {
            public m() {
                ImageFragment.this = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImageFragment imageFragment = ImageFragment.this;
                imageFragment.b(imageFragment.f4558g, imageFragment.f4559h);
            }
        }

        public static boolean f(ImageFragment imageFragment) {
            int i2;
            HeadPicGiftInfoEntity headPicGiftInfoEntity;
            if (!((imageFragment.t && imageFragment.f4562k == imageFragment.W) || (i2 = imageFragment.f4562k) == imageFragment.J || i2 == imageFragment.S || i2 == imageFragment.K || i2 == imageFragment.w || ((headPicGiftInfoEntity = imageFragment.Y) != null && i2 == headPicGiftInfoEntity.index)) && Build.VERSION.SDK_INT >= 29) {
                JDBottomDialog jDBottomDialog = new JDBottomDialog(imageFragment.q);
                jDBottomDialog.addContentWithHeight(PlatformTools.inflate(imageFragment.q, R.layout.lib_pd_mainimage_topimage_dialog, null, false), (String) null, 0.0f);
                jDBottomDialog.findViewById(R.id.tv_post).setOnClickListener(new com.jd.lib.productdetail.mainimage.bigimage.i(imageFragment, jDBottomDialog));
                jDBottomDialog.findViewById(R.id.tv_cancel).setOnClickListener(new com.jd.lib.productdetail.mainimage.bigimage.j(imageFragment, jDBottomDialog));
                PdBigImageActivity pdBigImageActivity = imageFragment.q;
                if (pdBigImageActivity == null || pdBigImageActivity.isFinishing() || jDBottomDialog.isShowing()) {
                    return true;
                }
                jDBottomDialog.show();
                return true;
            }
            return false;
        }

        public static void j(ImageFragment imageFragment) {
            com.jd.lib.productdetail.mainimage.old.k kVar = imageFragment.u;
            if (kVar == null) {
                return;
            }
            if (kVar.f5168l == 3 && kVar.f5169m == 1) {
                imageFragment.a();
            }
            PdBigImageActivity pdBigImageActivity = imageFragment.q;
            if (pdBigImageActivity == null || pdBigImageActivity.getRequestedOrientation() != 0) {
                return;
            }
            imageFragment.q.setRequestedOrientation(1);
        }

        public static void k(ImageFragment imageFragment) {
            com.jd.lib.productdetail.mainimage.old.k kVar = imageFragment.u;
            if (kVar == null) {
                return;
            }
            VideoPlayView videoPlayView = kVar.a;
            if ((videoPlayView != null && videoPlayView.getVideoWidth() > kVar.a.getVideoHeight()) && imageFragment.getActivity() != null) {
                imageFragment.getActivity().setRequestedOrientation(0);
            }
            PdBigImageActivity pdBigImageActivity = imageFragment.q;
            if (pdBigImageActivity != null) {
                pdBigImageActivity.post(new com.jd.lib.productdetail.mainimage.bigimage.h(imageFragment), 200);
            }
        }

        public final void a() {
            if (this.u != null) {
                this.o.setVisibility(0);
                com.jd.lib.productdetail.mainimage.old.k kVar = this.u;
                ViewGroup viewGroup = this.o;
                if (kVar.B || kVar.a == null || kVar.C) {
                    return;
                }
                kVar.a.setVideoViewOnTouchListener(null);
                kVar.f(viewGroup, 0);
                kVar.a.setBottomSharedEnable(false);
                kVar.a.hideFullscreen(true);
                kVar.a.loadErrorRetry(true);
                kVar.a.setVoiceIconKeepVisiInFullScreen(true);
                kVar.a.getBarPlayerView().setVisibility(8);
                kVar.E(true);
                kVar.a.changeToScreen(0);
                kVar.a.hideCloseBt(true);
                kVar.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                kVar.d(4);
                k.o oVar = kVar.f5164h;
                if (oVar != null) {
                    oVar.a(kVar.f5168l);
                }
            }
        }

        public final void b(String str, String str2) {
            HeadPicGiftInfoEntity headPicGiftInfoEntity;
            PdBigImageActivity pdBigImageActivity = this.q;
            if (pdBigImageActivity == null || pdBigImageActivity.isFinishing() || this.r == null) {
                return;
            }
            if (!TextUtils.isEmpty(str2) && this.s != null) {
                this.f4564m.setVisibility(0);
                this.f4565n.setVisibility(8);
                this.s.setVisibility(0);
                Glide.with(this.s).load(str2).addListener(new g()).into(this.s);
                return;
            }
            int i2 = this.f4562k;
            if (i2 == this.J || i2 == this.K || i2 == this.S || ((headPicGiftInfoEntity = this.Y) != null && i2 == headPicGiftInfoEntity.index)) {
                PdMCooTouchImageView pdMCooTouchImageView = this.r;
                pdMCooTouchImageView.f5221n = 1.0f;
                pdMCooTouchImageView.f5220m = 1.0f;
                pdMCooTouchImageView.f5219l = 1.0f;
                pdMCooTouchImageView.f5218k = 1.0f;
            }
            SimpleDraweeView simpleDraweeView = this.s;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
            }
            ViewGroup viewGroup = this.f4565n;
            if (viewGroup != null) {
                viewGroup.setVisibility(0);
            }
            SparseArrayCompat<String> sparseArrayCompat = this.q.f4553j;
            if (sparseArrayCompat == null) {
                return;
            }
            e(!TextUtils.isEmpty(this.d0), false, false);
            if (str != null && !TextUtils.isEmpty(str)) {
                sparseArrayCompat.remove(this.f4562k);
                this.f4563l.bitmapConfig(Bitmap.Config.ARGB_8888);
                JDImageUtils.displayImage(str, this.r, this.f4563l, new h());
            } else if (TextUtils.isEmpty(this.d0)) {
                e(true, true, true);
            }
        }

        public final void c(boolean z) {
            com.jd.lib.productdetail.mainimage.old.k kVar = this.u;
            if (kVar == null) {
                return;
            }
            kVar.t(this.f4561j);
            a();
            if (this.H.masterVideo.isHasMarkInfo()) {
                this.u.p(this.H.masterVideo.videoMarkList);
                this.u.f5166j = new a();
            }
            this.u.q(true, z);
            this.u.k(new b());
            this.u.m(new c());
            this.u.l(new d());
            this.u.j(new e());
            com.jd.lib.productdetail.mainimage.old.k kVar2 = this.u;
            if (!kVar2.f5170n && !this.Q) {
                d(true, !kVar2.o);
                this.u.h(this.H.masterVideo, this.f4561j);
                return;
            }
            kVar2.K();
            this.u.I();
            d(false, !this.u.o);
        }

        public void d(boolean z, boolean z2) {
            if (UnAndroidUtils.isMatex(getContext())) {
                return;
            }
            boolean isNetworkAvailable = NetUtils.isNetworkAvailable();
            com.jd.lib.productdetail.mainimage.old.k kVar = this.u;
            if (kVar != null) {
                isNetworkAvailable = kVar.s() == -1;
            }
            if (z && this.f4562k == this.W && isNetworkAvailable) {
                this.v.setVisibility(0);
                if (z2) {
                    if (PdBigImageActivity.R) {
                        this.v.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
                        return;
                    } else {
                        this.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
                        return;
                    }
                } else if (PdBigImageActivity.R) {
                    PDCalorieImageUtil.get().display("2707", this.v);
                    return;
                } else {
                    this.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
                    return;
                }
            }
            this.v.setVisibility(8);
        }

        public final void e(boolean z, boolean z2, boolean z3) {
            View view = this.f4564m;
            if (view == null || this.f4565n == null || this.p == null) {
                return;
            }
            if (z) {
                if (z2) {
                    PdBigImageActivity.fadeOut(view);
                    PdBigImageActivity.fadeIn(z3 ? this.p : this.f4565n);
                } else {
                    view.clearAnimation();
                    this.f4565n.clearAnimation();
                    this.p.clearAnimation();
                }
                this.f4564m.setVisibility(8);
                this.f4565n.setVisibility(z3 ? 8 : 0);
                this.p.setVisibility(z3 ? 0 : 8);
                return;
            }
            if (z2) {
                PdBigImageActivity.fadeIn(view);
                PdBigImageActivity.fadeOut(this.f4565n);
                PdBigImageActivity.fadeOut(this.p);
            } else {
                view.clearAnimation();
                this.f4565n.clearAnimation();
                this.p.clearAnimation();
            }
            this.f4564m.setVisibility(0);
            this.f4565n.setVisibility(0);
            this.p.setVisibility(8);
        }

        public final void h() {
            PdMImageRecommendView pdMImageRecommendView = this.D;
            if (pdMImageRecommendView == null || this.f4562k != this.S || PdBigImageActivity.Q == null) {
                return;
            }
            pdMImageRecommendView.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = this.D.getLayoutParams();
            layoutParams.height = PdBigImageActivity.Q.appImageHeight;
            this.D.setLayoutParams(layoutParams);
            this.D.f(PdBigImageActivity.Q);
            PdMImageRecommendView pdMImageRecommendView2 = this.D;
            int i2 = PdMImageRecommendItemView.s;
            pdMImageRecommendView2.g(1);
            this.D.b(this.G, this.T, null);
        }

        @Override // androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            Bundle arguments = getArguments();
            this.f4562k = arguments != null ? arguments.getInt("pos") : -1;
            this.P = arguments != null ? arguments.getInt("currentPos") : -1;
            if (arguments != null) {
                PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
                if (arguments.getSerializable("bigImageEntity") instanceof BigImageEntity) {
                    BigImageEntity bigImageEntity = (BigImageEntity) arguments.getSerializable("bigImageEntity");
                    this.a0 = bigImageEntity;
                    this.J = bigImageEntity.askPosition;
                    this.S = bigImageEntity.recommendPosition;
                    this.T = bigImageEntity.wareImageRecommendEntity;
                    this.G = bigImageEntity.recommendProductListInfo;
                    this.b0 = bigImageEntity.wareImageQaEntity;
                    this.K = bigImageEntity.ypsmsPosition;
                    this.w = bigImageEntity.suitPosition;
                    this.x = bigImageEntity.suitAnchorType;
                    this.y = bigImageEntity.mainProduct;
                    this.z = bigImageEntity.suitDetails;
                    this.A = bigImageEntity.dpgDetails;
                    this.L = bigImageEntity.commentZcxPosition;
                    this.M = bigImageEntity.recommendRankPosition;
                    this.N = bigImageEntity.drugInfo;
                    this.f0 = bigImageEntity.mCategroyId1;
                    this.g0 = bigImageEntity.mCategroyId2;
                    this.h0 = bigImageEntity.mCategroyId3;
                }
            }
            if (arguments != null) {
                arguments.getString("eventParams");
            }
            this.f4558g = arguments != null ? arguments.getString("image") : null;
            this.f4560i = (arguments != null ? Boolean.valueOf(arguments.getBoolean("autoPlay")) : null).booleanValue();
            boolean z = false;
            this.f4561j = arguments != null ? arguments.getBoolean("player2") : false;
            this.f4559h = arguments != null ? arguments.getString("gifUrl") : null;
            this.X = arguments != null ? arguments.getString("managerKey") : null;
            this.d0 = arguments != null ? arguments.getString("isvInfo") : null;
            Parcelable parcelable = arguments != null ? arguments.getParcelable("videoControl") : null;
            Parcelable parcelable2 = arguments != null ? arguments.getParcelable("pdCommentInfo") : null;
            Parcelable parcelable3 = arguments != null ? arguments.getParcelable("topImageGiftInfo") : null;
            if (parcelable instanceof WareBusinessTopVideoControl) {
                this.H = (WareBusinessTopVideoControl) parcelable;
            }
            if (parcelable2 instanceof PdCommentInfo) {
                this.I = (PdCommentInfo) parcelable2;
            }
            if (parcelable3 instanceof HeadPicGiftInfoEntity) {
                this.Y = (HeadPicGiftInfoEntity) parcelable3;
            }
            if (getActivity() instanceof PdBigImageActivity) {
                this.q = (PdBigImageActivity) getActivity();
            }
            if (Log.D) {
                Log.d("BigImageActivity", "onCreate = " + this.f4562k);
            }
            WareBusinessTopVideoControl wareBusinessTopVideoControl = this.H;
            boolean z2 = wareBusinessTopVideoControl != null && wareBusinessTopVideoControl.isHasMasterVideo();
            this.t = z2;
            if (z2) {
                this.W = this.H.position;
            }
            if (z2) {
                int i2 = this.P;
                int i3 = this.W;
                if (i2 == i3 && this.f4560i && this.f4562k == i3) {
                    z = true;
                }
            }
            if (z) {
                this.q.post(new f());
            }
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            this.f4563l.showImageOnLoading(R.drawable.lib_pd_mainimage_big_image_onloading_bg);
            View inflate = layoutInflater.inflate(R.layout.lib_pd_mainimage_big_image_item_fragment, (ViewGroup) null);
            this.f4564m = inflate.findViewById(R.id.pd_big_image_progress_id);
            this.F = (PdMImageRecommendNewView) inflate.findViewById(R.id.lib_pd_recommend_rank_item_big);
            this.C = (PdMAskView) inflate.findViewById(R.id.lib_pd_ask_item_big);
            this.D = (PdMImageRecommendView) inflate.findViewById(R.id.lib_pd_recommend_item_big);
            this.O = (PdMYpsmsView) inflate.findViewById(R.id.lib_pd_ypsms_item_big);
            PdTopImageSuitView pdTopImageSuitView = (PdTopImageSuitView) inflate.findViewById(R.id.lib_pd_suit_item_big);
            this.B = pdTopImageSuitView;
            pdTopImageSuitView.c(false);
            this.E = (PdMImageCommentNewRootView) inflate.findViewById(R.id.lib_pd_comment_new_item_big);
            PdMYpsmsView pdMYpsmsView = this.O;
            pdMYpsmsView.q = false;
            pdMYpsmsView.c(PdBigImageActivity.Q);
            this.f4565n = (ViewGroup) inflate.findViewById(R.id.pd_big_image_content_id);
            PdMCooTouchImageView pdMCooTouchImageView = (PdMCooTouchImageView) inflate.findViewById(R.id.pd_big_image_id);
            this.r = pdMCooTouchImageView;
            pdMCooTouchImageView.setImageResource(R.drawable.lib_pd_mainimage_transparent_shape);
            this.s = (SimpleDraweeView) inflate.findViewById(R.id.pd_big_image_gif);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.icon_play);
            this.v = imageView;
            imageView.setContentDescription(getString(R.string.lib_pd_live_play));
            this.v.setTag("play_btn");
            this.e0 = (FrameLayout) inflate.findViewById(R.id.lib_pd_dyn_item_big);
            PdMImageGiftView pdMImageGiftView = (PdMImageGiftView) inflate.findViewById(R.id.lib_pd_gift_show_big);
            this.Z = pdMImageGiftView;
            pdMImageGiftView.e(true);
            ViewGroup.LayoutParams layoutParams = this.v.getLayoutParams();
            if (PdBigImageActivity.R) {
                layoutParams.width = PDUtils.dip2px(75.0f);
                layoutParams.height = PDUtils.dip2px(75.0f);
            } else {
                layoutParams.width = PDUtils.dip2px(60.0f);
                layoutParams.height = PDUtils.dip2px(60.0f);
            }
            this.v.setLayoutParams(layoutParams);
            this.o = (ViewGroup) inflate.findViewById(R.id.video_container);
            this.v.setOnClickListener(new i());
            this.r.setOnLongClickListener(new j());
            this.r.setOnClickListener(new k());
            this.s.setOnClickListener(new l());
            this.p = (ViewGroup) inflate.findViewById(R.id.pd_big_image_error_id);
            inflate.findViewById(R.id.lib_pd_big_image_error_button).setOnClickListener(new m());
            if (this.f4562k == this.W) {
                WareBusinessTopVideoControl wareBusinessTopVideoControl = this.H;
                if (wareBusinessTopVideoControl != null && wareBusinessTopVideoControl.isHasMasterVideo()) {
                    com.jd.lib.productdetail.mainimage.old.k b2 = com.jd.lib.productdetail.mainimage.old.k.b(getContext(), this.X);
                    this.u = b2;
                    b2.z = PdBigImageActivity.Q;
                    d(true, false);
                }
                inflate.setContentDescription(getString(R.string.lib_pd_video_des));
            }
            if (this.u != null && this.t && !UnAndroidUtils.isMatex(getContext())) {
                if (this.f4562k == this.W) {
                    com.jd.lib.productdetail.mainimage.old.k kVar = this.u;
                    if (kVar.f5170n) {
                        kVar.t(this.f4561j);
                        a();
                        if (this.H.masterVideo.isHasMarkInfo()) {
                            this.u.p(this.H.masterVideo.videoMarkList);
                            this.u.f5166j = new com.jd.lib.productdetail.mainimage.bigimage.c(this);
                        }
                        com.jd.lib.productdetail.mainimage.old.k kVar2 = this.u;
                        kVar2.q(true, kVar2.F);
                        this.u.k(new com.jd.lib.productdetail.mainimage.bigimage.d(this));
                        this.u.m(new com.jd.lib.productdetail.mainimage.bigimage.e(this));
                        this.u.l(new com.jd.lib.productdetail.mainimage.bigimage.f(this));
                        this.u.j(new com.jd.lib.productdetail.mainimage.bigimage.g(this));
                    }
                }
                if (this.u.C()) {
                    d(true, false);
                } else if (this.f4560i) {
                    c(this.u.F);
                }
            }
            return inflate;
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroy() {
            super.onDestroy();
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroyView() {
            this.p = null;
            this.f4565n = null;
            this.f4564m = null;
            this.r = null;
            this.s = null;
            super.onDestroyView();
        }

        @Override // androidx.fragment.app.Fragment
        public void onDetach() {
            super.onDetach();
        }

        @Override // androidx.fragment.app.Fragment
        public void onResume() {
            super.onResume();
            com.jd.lib.productdetail.mainimage.old.k kVar = this.u;
            if (kVar == null || !kVar.f5170n || kVar.o || this.P != this.W) {
                return;
            }
            kVar.B(true);
            this.v.setVisibility(8);
        }

        @Override // androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            HeadPicGiftInfoEntity headPicGiftInfoEntity;
            PdMImageGiftView pdMImageGiftView;
            FloorCooperateManager a2;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
            FrameLayout frameLayout;
            int i2;
            g0 g0Var;
            MutableLiveData<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> mutableLiveData;
            ArrayList<PdDpgSmallInfo> arrayList;
            ArrayList<PdDpgSmallInfo> arrayList2;
            HeadPicGiftInfoEntity headPicGiftInfoEntity2;
            super.onViewCreated(view, bundle);
            int i3 = this.f4562k;
            if (i3 != this.J && i3 != this.K && i3 != this.w && i3 != this.L && i3 != this.M && i3 != this.S && ((headPicGiftInfoEntity2 = this.Y) == null || i3 != headPicGiftInfoEntity2.index)) {
                b(this.f4558g, this.f4559h);
            } else {
                String str = this.f4558g;
                b(str, str);
            }
            if (PdBigImageActivity.Q != null) {
                this.C.setVisibility(8);
                this.D.setVisibility(8);
                this.e0.setVisibility(8);
                this.O.setVisibility(8);
                this.B.setVisibility(8);
                this.E.setVisibility(8);
                this.F.setVisibility(8);
                PdMAskView pdMAskView = this.C;
                if (pdMAskView != null && this.J == this.f4562k) {
                    pdMAskView.setVisibility(0);
                    PdMAskView pdMAskView2 = this.C;
                    PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
                    pdMAskView2.f4671m = null;
                    pdMAskView2.f4665g = null;
                    pdMAskView2.A = pdMainImagePresenter;
                    ViewGroup.LayoutParams layoutParams = pdMAskView2.getLayoutParams();
                    layoutParams.height = PdBigImageActivity.Q.appImageHeight;
                    this.C.setLayoutParams(layoutParams);
                    PdMAskView pdMAskView3 = this.C;
                    int i4 = PdMAskView.B;
                    pdMAskView3.g(1);
                    this.C.c(this.I, this.b0);
                } else {
                    PdMYpsmsView pdMYpsmsView = this.O;
                    if (pdMYpsmsView != null && this.K == this.f4562k) {
                        pdMYpsmsView.setVisibility(0);
                        this.O.c(PdBigImageActivity.Q);
                        ViewGroup.LayoutParams layoutParams2 = this.O.getLayoutParams();
                        layoutParams2.height = PdBigImageActivity.Q.appImageHeight;
                        this.O.setLayoutParams(layoutParams2);
                        this.O.f(this.N);
                    } else {
                        PdTopImageSuitView pdTopImageSuitView = this.B;
                        if (pdTopImageSuitView != null && this.w == this.f4562k) {
                            pdTopImageSuitView.setVisibility(0);
                            ViewGroup.LayoutParams layoutParams3 = this.B.getLayoutParams();
                            layoutParams3.height = PdBigImageActivity.Q.appImageHeight;
                            this.B.setLayoutParams(layoutParams3);
                            PdMainSku pdMainSku = this.y;
                            if (pdMainSku != null && (arrayList2 = this.A) != null) {
                                this.B.b(pdMainSku, arrayList2, this.X, this.x, null, null);
                            } else if (pdMainSku != null && (arrayList = this.z) != null) {
                                this.B.b(pdMainSku, arrayList, this.X, this.x, null, null);
                            } else {
                                this.B.setVisibility(8);
                            }
                        } else {
                            PdMImageCommentNewRootView pdMImageCommentNewRootView = this.E;
                            if (pdMImageCommentNewRootView != null && this.f4562k == this.L) {
                                pdMImageCommentNewRootView.setVisibility(0);
                                this.E.d(PdBigImageActivity.Q);
                                ViewGroup.LayoutParams layoutParams4 = this.E.getLayoutParams();
                                PdBigImageActivity pdBigImageActivity = this.q;
                                BigImageEntity bigImageEntity = this.a0;
                                pdBigImageActivity.getClass();
                                int appWidth = DPIUtil.getAppWidth(pdBigImageActivity);
                                int statusBarHeight = (pdBigImageActivity.statusBarTransparentEnable ? UnStatusBarTintUtil.getStatusBarHeight((Activity) pdBigImageActivity) : 0) + appWidth;
                                if (bigImageEntity != null && bigImageEntity.magicHeadPicType == 2) {
                                    statusBarHeight = (int) ((appWidth * 4.0f) / 3.0f);
                                }
                                layoutParams4.height = statusBarHeight;
                                this.E.setLayoutParams(layoutParams4);
                                this.E.b(false, this.a0.magicHeadPicType);
                                BigImageEntity bigImageEntity2 = this.a0;
                                if (bigImageEntity2 != null) {
                                    this.E.a(this.I, bigImageEntity2.buyersIcon, bigImageEntity2.commentPriorityFlagNew, bigImageEntity2.commentDefaultUrl, bigImageEntity2.magicHeadPicType);
                                } else {
                                    this.E.a(this.I, "", false, "", 0);
                                }
                                PdMImageCommentZcxView pdMImageCommentZcxView = this.E.f4720h;
                                if (pdMImageCommentZcxView != null) {
                                    pdMImageCommentZcxView.t(false);
                                }
                            } else {
                                PdMImageRecommendNewView pdMImageRecommendNewView = this.F;
                                if (pdMImageRecommendNewView != null && this.M == this.f4562k) {
                                    pdMImageRecommendNewView.setVisibility(0);
                                    ViewGroup.LayoutParams layoutParams5 = this.F.getLayoutParams();
                                    layoutParams5.height = PdBigImageActivity.Q.appImageHeight;
                                    this.F.setLayoutParams(layoutParams5);
                                    PdMImageRecommendNewView pdMImageRecommendNewView2 = this.F;
                                    int i5 = PdMImageRecommendItemView.s;
                                    pdMImageRecommendNewView2.e(1);
                                    this.F.d(PdBigImageActivity.Q);
                                    PdMImageRecommendNewView pdMImageRecommendNewView3 = this.F;
                                    BigImageEntity bigImageEntity3 = this.a0;
                                    pdMImageRecommendNewView3.b(bigImageEntity3.mCategroyId1, bigImageEntity3.mCategroyId2, bigImageEntity3.mCategroyId3, bigImageEntity3.storeId, this.R, bigImageEntity3.bangDanInfo);
                                } else if (this.D != null && (i2 = this.S) == this.f4562k) {
                                    PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo = this.G;
                                    if (pdPreferentialRecommendProductListInfo == null) {
                                        PdMainImagePresenter pdMainImagePresenter2 = PdBigImageActivity.Q;
                                        if (pdMainImagePresenter2 != null && (g0Var = pdMainImagePresenter2.mRecommendContainer) != null && (mutableLiveData = g0Var.a) != null && i2 != -1 && pdPreferentialRecommendProductListInfo == null && !this.c0) {
                                            this.c0 = true;
                                            mutableLiveData.observe(this.q, new com.jd.lib.productdetail.mainimage.bigimage.k(this));
                                            PdMainImagePresenter pdMainImagePresenter3 = PdBigImageActivity.Q;
                                            pdMainImagePresenter3.mRecommendContainer.a(this.q, this.R, "", "4", pdMainImagePresenter3);
                                        }
                                    } else {
                                        h();
                                    }
                                } else if (this.e0 != null && !TextUtils.isEmpty(this.d0) && (a2 = PdMCooperManager.a(this.X)) != null && getContext() != null && (wareBusinessMagicHeadPicInfoEntity = (WareBusinessMagicHeadPicInfoEntity) JDJSON.parseObject(this.d0, WareBusinessMagicHeadPicInfoEntity.class)) != null) {
                                    boolean z = wareBusinessMagicHeadPicInfoEntity.iViewType == 2;
                                    IBaseView createView = a2.createView(this.e0, z ? wareBusinessMagicHeadPicInfoEntity.mfStyleId : wareBusinessMagicHeadPicInfoEntity.anchorType, z, this.d0);
                                    if (createView != null && (frameLayout = this.e0) != null) {
                                        frameLayout.addView(createView.getView());
                                        this.e0.setVisibility(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            PdMImageGiftView pdMImageGiftView2 = this.Z;
            if (pdMImageGiftView2 != null) {
                pdMImageGiftView2.setVisibility(8);
            }
            if (PdBigImageActivity.Q == null || (headPicGiftInfoEntity = this.Y) == null || (pdMImageGiftView = this.Z) == null || headPicGiftInfoEntity.index != this.f4562k || this.q == null) {
                return;
            }
            ViewGroup.LayoutParams layoutParams6 = pdMImageGiftView.getLayoutParams();
            this.Z.setVisibility(0);
            layoutParams6.height = PdBigImageActivity.Q.appImageHeight;
            this.Z.setLayoutParams(layoutParams6);
            this.Z.d(this.Y, PdBigImageActivity.Q);
            this.Z.e(true);
        }
    }

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
            PdBigImageActivity.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdBigImageActivity.this.finish();
        }
    }

    /* loaded from: classes15.dex */
    public class b implements PullToRefreshBase.OnRefreshListener<PdMDropDownViewPager> {
        public b() {
            PdBigImageActivity.this = r1;
        }

        @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
        public void onRefresh(PullToRefreshBase<PdMDropDownViewPager> pullToRefreshBase) {
            PdBigImageActivity pdBigImageActivity = PdBigImageActivity.this;
            PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
            pdBigImageActivity.getClass();
            Intent intent = new Intent(PdMainImageViewPage.INTENT_ACTION_PD_MAINIMAGE_SLIDEPIC);
            intent.putExtra("page", -1);
            LocalBroadcastManager.getInstance(pdBigImageActivity).sendBroadcast(intent);
            pdBigImageActivity.finish();
        }
    }

    /* loaded from: classes15.dex */
    public class c implements PdMDropDownViewPager.c {
        public c() {
            PdBigImageActivity.this = r1;
        }

        public void a(boolean z) {
            PdBigImageActivity pdBigImageActivity = PdBigImageActivity.this;
            if (pdBigImageActivity.y) {
                return;
            }
            View view = pdBigImageActivity.f4556m;
            if (view != null) {
                view.setBackgroundColor(-1);
            }
            if (z) {
                return;
            }
            PdBigImageActivity pdBigImageActivity2 = PdBigImageActivity.this;
            if (pdBigImageActivity2.o) {
                pdBigImageActivity2.o = false;
                PdBigImageActivity pdBigImageActivity3 = PdBigImageActivity.this;
                pdBigImageActivity3.a();
                if (pdBigImageActivity3.r != null) {
                    pdBigImageActivity3.f4557n = true;
                    View view2 = pdBigImageActivity3.p;
                    if (view2 != null) {
                        view2.setVisibility(0);
                        pdBigImageActivity3.p.startAnimation(pdBigImageActivity3.r);
                        pdBigImageActivity3.r.setAnimationListener(new com.jd.lib.productdetail.mainimage.bigimage.b(pdBigImageActivity3));
                    }
                }
            }
        }

        public boolean b() {
            PdMCooTouchImageView pdMCooTouchImageView;
            ImageFragment imageFragment = PdBigImageActivity.this.P;
            if (!(imageFragment instanceof ImageFragment) || (pdMCooTouchImageView = imageFragment.r) == null) {
                return false;
            }
            return !((pdMCooTouchImageView.f5214g > 1.0f ? 1 : (pdMCooTouchImageView.f5214g == 1.0f ? 0 : -1)) != 0);
        }

        @Nullable
        public View c() {
            ImageFragment imageFragment = PdBigImageActivity.this.P;
            if (imageFragment != null) {
                return imageFragment.getView();
            }
            return null;
        }

        public void d(boolean z) {
            PdBigImageActivity pdBigImageActivity = PdBigImageActivity.this;
            if (pdBigImageActivity.y || z) {
                return;
            }
            View view = pdBigImageActivity.f4556m;
            if (view != null) {
                view.setBackgroundColor(0);
            }
            View view2 = PdBigImageActivity.this.D;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            PdBigImageActivity pdBigImageActivity2 = PdBigImageActivity.this;
            if (pdBigImageActivity2.f4557n) {
                pdBigImageActivity2.o = true;
                PdBigImageActivity pdBigImageActivity3 = PdBigImageActivity.this;
                pdBigImageActivity3.a();
                AlphaAnimation alphaAnimation = pdBigImageActivity3.s;
                if (alphaAnimation != null) {
                    pdBigImageActivity3.f4557n = false;
                    alphaAnimation.setAnimationListener(new com.jd.lib.productdetail.mainimage.bigimage.a(pdBigImageActivity3));
                    View view3 = pdBigImageActivity3.p;
                    if (view3 != null) {
                        view3.startAnimation(pdBigImageActivity3.s);
                    }
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class d extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

        /* renamed from: g */
        public ViewPager f4578g;

        /* renamed from: h */
        public TextView f4579h;

        /* renamed from: i */
        public List<String> f4580i;

        /* renamed from: j */
        public PdBigImageActivity f4581j;

        /* renamed from: k */
        public int f4582k;

        /* renamed from: l */
        public ImageFragment f4583l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(FragmentActivity fragmentActivity, ViewPager viewPager, List<String> list) {
            super(fragmentActivity.getSupportFragmentManager());
            PdBigImageActivity.this = r1;
            this.f4581j = (PdBigImageActivity) fragmentActivity;
            this.f4580i = list;
            this.f4578g = viewPager;
            if (NetUtils.isWifi()) {
                this.f4578g.setOffscreenPageLimit(2);
            }
            this.f4578g.setAdapter(this);
            this.f4578g.setOnPageChangeListener(this);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            List<String> list = this.f4580i;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int i2) {
            String str;
            HashMap hashMap = PdBigImageActivity.this.E;
            String str2 = null;
            String str3 = (hashMap == null || hashMap.get(Integer.valueOf(i2)) == null) ? null : (String) PdBigImageActivity.this.E.get(Integer.valueOf(i2));
            List<String> list = PdBigImageActivity.this.J;
            if (list == null || list.size() <= i2) {
                str = "";
            } else {
                str = PdBigImageActivity.this.J.get(i2 <= 0 ? 0 : i2);
            }
            int i3 = this.f4582k;
            List<String> list2 = this.f4580i;
            if (list2 != null) {
                str2 = list2.get(i2 > 0 ? i2 : 0);
            }
            PdBigImageActivity pdBigImageActivity = PdBigImageActivity.this;
            WareBusinessTopVideoControl wareBusinessTopVideoControl = pdBigImageActivity.B;
            boolean z = pdBigImageActivity.x;
            PdCommentInfo pdCommentInfo = pdBigImageActivity.F;
            String str4 = pdBigImageActivity.t;
            HeadPicGiftInfoEntity headPicGiftInfoEntity = pdBigImageActivity.H;
            boolean z2 = pdBigImageActivity.G;
            BigImageEntity bigImageEntity = pdBigImageActivity.I;
            String str5 = pdBigImageActivity.u;
            ImageFragment imageFragment = new ImageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", i2);
            bundle.putBoolean("autoPlay", z);
            bundle.putInt("currentPos", i3);
            PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
            bundle.putSerializable("bigImageEntity", bigImageEntity);
            bundle.putString("image", str2);
            bundle.putString("gifUrl", str3);
            bundle.putBoolean("player2", z2);
            bundle.putParcelable("videoControl", wareBusinessTopVideoControl);
            bundle.putString("managerKey", str4);
            bundle.putParcelable("pdCommentInfo", pdCommentInfo);
            bundle.putParcelable("topImageGiftInfo", headPicGiftInfoEntity);
            bundle.putString("isvInfo", str);
            bundle.putString("eventParams", str5);
            imageFragment.setArguments(bundle);
            PdBigImageActivity pdBigImageActivity2 = PdBigImageActivity.this;
            imageFragment.R = pdBigImageActivity2.z;
            imageFragment.U = pdBigImageActivity2.A;
            WareBusinessTopVideoControl wareBusinessTopVideoControl2 = pdBigImageActivity2.B;
            if (wareBusinessTopVideoControl2 != null && wareBusinessTopVideoControl2.position == i2) {
                this.f4583l = imageFragment;
            }
            return imageFragment;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            ImageView imageView;
            if (this.f4579h != null) {
                this.f4579h.setText("" + (i2 + 1));
            }
            this.f4582k = i2;
            com.jd.lib.productdetail.mainimage.old.k kVar = PdBigImageActivity.this.C;
            if (kVar != null) {
                VideoPlayView videoPlayView = kVar.a;
                if ((videoPlayView != null && kVar.f5168l == 4 && videoPlayView.getVisibility() == 0) && NetUtils.isNetworkAvailable()) {
                    PdBigImageActivity pdBigImageActivity = PdBigImageActivity.this;
                    WareBusinessTopVideoControl wareBusinessTopVideoControl = pdBigImageActivity.B;
                    if (wareBusinessTopVideoControl != null && i2 == wareBusinessTopVideoControl.position) {
                        com.jd.lib.productdetail.mainimage.old.k kVar2 = pdBigImageActivity.C;
                        if (!kVar2.o && !kVar2.C()) {
                            PdBigImageActivity.this.C.I();
                            ImageFragment imageFragment = this.f4583l;
                            if (imageFragment != null && (imageView = imageFragment.v) != null) {
                                if (PdBigImageActivity.R) {
                                    imageView.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
                                } else {
                                    imageView.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
                                }
                                this.f4583l.v.setVisibility(8);
                            }
                        }
                    }
                    if (PdBigImageActivity.this.C.C()) {
                        PdBigImageActivity pdBigImageActivity2 = PdBigImageActivity.this;
                        com.jd.lib.productdetail.mainimage.old.k kVar3 = pdBigImageActivity2.C;
                        kVar3.i(pdBigImageActivity2.B, kVar3, false, pdBigImageActivity2.L, pdBigImageActivity2.M, pdBigImageActivity2.N, pdBigImageActivity2.f4556m);
                        PdBigImageActivity.this.C.y(false);
                    }
                }
            }
            if (PdBigImageActivity.this.v) {
                return;
            }
            Intent intent = new Intent(PdMainImageViewPage.INTENT_ACTION_PD_MAINIMAGE_SLIDEPIC);
            intent.putExtra("page", i2);
            LocalBroadcastManager.getInstance(this.f4581j).sendBroadcast(intent);
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
        public void setPrimaryItem(ViewGroup viewGroup, int i2, Object obj) {
            if (obj instanceof ImageFragment) {
                PdBigImageActivity.this.P = (ImageFragment) obj;
            }
            super.setPrimaryItem(viewGroup, i2, obj);
        }
    }

    public static void fadeIn(View view) {
        if (view.getVisibility() == 0) {
            return;
        }
        view.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(400L);
        view.startAnimation(alphaAnimation);
    }

    public static void fadeOut(View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(400L);
        view.startAnimation(alphaAnimation);
        view.setVisibility(8);
    }

    public final void a() {
        if (this.q) {
            return;
        }
        this.q = true;
        this.r = new AlphaAnimation(0.0f, 1.0f);
        this.s = new AlphaAnimation(1.0f, 0.0f);
        this.r.setDuration(300L);
        this.s.setDuration(300L);
    }

    public void b() {
        if (isFinishing()) {
            return;
        }
        JDMtaUtils.sendCommonData4ProductDetail(getApplicationContext(), "Photobrowser_Back", "", "onClick", RecommendMtaUtils.Productdetail_MainPage, PdBigImageActivity.class.getName(), null, null, null, null, null, null);
        finish();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        PdMainImagePresenter pdMainImagePresenter = Q;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.appImageWidth = PDUtils.getAppWidth(this);
        }
        d dVar = this.f4555l;
        if (dVar != null) {
            dVar.notifyDataSetChanged();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01d1  */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate(Bundle bundle) {
        int i2;
        WareBusinessTopVideoControl wareBusinessTopVideoControl;
        String str;
        String str2;
        if (SDKUtils.getSDKVersion() == 26) {
            setNeedSetOrientation(false);
        }
        if (isStatusBarTintEnable()) {
            this.statusBarTransparentEnable = true;
        }
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        ArrayList<String> arrayList = null;
        try {
            if (extras != null) {
                this.f4554k = extras.getInt("position");
                this.w = extras.getBoolean("fromBigImage", false);
                this.x = extras.getBoolean("autoPlay", false);
                this.t = extras.getString("managerKey");
                this.v = extras.getBoolean("pureMode");
                this.B = (WareBusinessTopVideoControl) extras.getParcelable("video");
                this.J = extras.getStringArrayList("isvInfo");
                this.F = (PdCommentInfo) extras.getParcelable("pdCommentInfo");
                this.G = extras.getBoolean("player2");
                this.u = extras.getString("eventParams");
                this.K = (PdMainImageParams) extras.getSerializable("mainImageParams");
                if (extras.getSerializable("bigImageEntity") instanceof BigImageEntity) {
                    BigImageEntity bigImageEntity = (BigImageEntity) extras.getSerializable("bigImageEntity");
                    this.I = bigImageEntity;
                    int i3 = bigImageEntity.recommendPosition;
                    WareImageRecommendEntity wareImageRecommendEntity = bigImageEntity.wareImageRecommendEntity;
                    this.L = bigImageEntity.mCategroyId1;
                    this.M = bigImageEntity.mCategroyId2;
                    this.N = bigImageEntity.mCategroyId3;
                }
                this.z = extras.getString("sku");
                this.A = extras.getString("skuTag");
                R = extras.getBoolean(AddressConstant.INTENT_EXTAS_IS_FROM_ELDER, false);
                this.E = (HashMap) extras.getSerializable("gifIndexMap");
                this.H = (HeadPicGiftInfoEntity) extras.getParcelable("topImageGiftInfo");
                arrayList = extras.getStringArrayList("image_show_list_url");
                if (arrayList != null) {
                    i2 = arrayList.size();
                    setContentView(R.layout.lib_pd_mainimage_big_image_activity);
                    if (Q == null) {
                        Q = new PdMainImagePresenter();
                    }
                    PdMainImagePresenter pdMainImagePresenter = Q;
                    pdMainImagePresenter.mainImageParams = this.K;
                    pdMainImagePresenter.appImageWidth = PDUtils.getAppWidth(getThisActivity());
                    PdMainImagePresenter pdMainImagePresenter2 = Q;
                    pdMainImagePresenter2.appImageHeight = pdMainImagePresenter2.appImageWidth;
                    pdMainImagePresenter2.getMainImageParams().skuId = this.z;
                    Q.getMainImageParams().mSkuTag = this.A;
                    Q.getMainImageParams().mManagerKey = this.t;
                    this.f4553j = new SparseArrayCompat<>();
                    setUseBasePV(false);
                    if (i2 > 9) {
                        GlobalImageCache.getLruBitmapCache().cleanMost();
                    }
                    this.f4556m = findViewById(R.id.lib_pd_big_image_activity_rl);
                    this.p = findViewById(R.id.lib_pd_big_image_page_num_ll);
                    View findViewById = findViewById(R.id.lib_pd_video_close);
                    this.D = findViewById;
                    findViewById.setOnClickListener(new a());
                    TextView textView = (TextView) findViewById(R.id.lib_pd_big_image_page_index);
                    FontsUtil.changeTextFont(textView);
                    TextView textView2 = (TextView) findViewById(R.id.lib_pd_big_image_page_num);
                    this.f4552i = textView2;
                    FontsUtil.changeTextFont(textView2);
                    wareBusinessTopVideoControl = this.B;
                    if (wareBusinessTopVideoControl != null && wareBusinessTopVideoControl.isHasMasterVideo()) {
                        com.jd.lib.productdetail.mainimage.old.k b2 = com.jd.lib.productdetail.mainimage.old.k.b(this, this.t);
                        this.C = b2;
                        b2.z = Q;
                    }
                    if (arrayList == null) {
                        str = "/" + arrayList.size();
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        int i4 = this.f4554k;
                        sb.append(i4 > 0 ? i4 : 1);
                        str2 = sb.toString();
                    } else {
                        str = "/0";
                        str2 = "0";
                    }
                    this.f4552i.setText(str);
                    textView.setText(str2);
                    PdMPullToRefreshViewPager pdMPullToRefreshViewPager = (PdMPullToRefreshViewPager) findViewById(R.id.lib_pd_big_image_pager);
                    this.f4550g = pdMPullToRefreshViewPager;
                    if (!this.w) {
                        pdMPullToRefreshViewPager.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
                    } else {
                        pdMPullToRefreshViewPager.setMode(PullToRefreshBase.Mode.DISABLED);
                    }
                    this.f4550g.setOnRefreshListener(new b());
                    PdMDropDownViewPager refreshableView = this.f4550g.getRefreshableView();
                    this.f4551h = refreshableView;
                    refreshableView.f(this.O);
                    d dVar = new d(this, this.f4551h, arrayList);
                    this.f4555l = dVar;
                    int i5 = this.f4554k;
                    dVar.f4582k = i5;
                    dVar.f4579h = textView;
                    this.f4551h.setCurrentItem(i5);
                    UnStatusBarTintUtil.setFitsSystemWindows(this, 0);
                    UnStatusBarTintUtil.setBackgroundResource(this, R.color.lib_pd_image_black);
                    return;
                }
            }
            UnStatusBarTintUtil.setFitsSystemWindows(this, 0);
            UnStatusBarTintUtil.setBackgroundResource(this, R.color.lib_pd_image_black);
            return;
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
            return;
        }
        i2 = 0;
        setContentView(R.layout.lib_pd_mainimage_big_image_activity);
        if (Q == null) {
        }
        PdMainImagePresenter pdMainImagePresenter3 = Q;
        pdMainImagePresenter3.mainImageParams = this.K;
        pdMainImagePresenter3.appImageWidth = PDUtils.getAppWidth(getThisActivity());
        PdMainImagePresenter pdMainImagePresenter22 = Q;
        pdMainImagePresenter22.appImageHeight = pdMainImagePresenter22.appImageWidth;
        pdMainImagePresenter22.getMainImageParams().skuId = this.z;
        Q.getMainImageParams().mSkuTag = this.A;
        Q.getMainImageParams().mManagerKey = this.t;
        this.f4553j = new SparseArrayCompat<>();
        setUseBasePV(false);
        if (i2 > 9) {
        }
        this.f4556m = findViewById(R.id.lib_pd_big_image_activity_rl);
        this.p = findViewById(R.id.lib_pd_big_image_page_num_ll);
        View findViewById2 = findViewById(R.id.lib_pd_video_close);
        this.D = findViewById2;
        findViewById2.setOnClickListener(new a());
        TextView textView3 = (TextView) findViewById(R.id.lib_pd_big_image_page_index);
        FontsUtil.changeTextFont(textView3);
        TextView textView22 = (TextView) findViewById(R.id.lib_pd_big_image_page_num);
        this.f4552i = textView22;
        FontsUtil.changeTextFont(textView22);
        wareBusinessTopVideoControl = this.B;
        if (wareBusinessTopVideoControl != null) {
            com.jd.lib.productdetail.mainimage.old.k b22 = com.jd.lib.productdetail.mainimage.old.k.b(this, this.t);
            this.C = b22;
            b22.z = Q;
        }
        if (arrayList == null) {
        }
        this.f4552i.setText(str);
        textView3.setText(str2);
        PdMPullToRefreshViewPager pdMPullToRefreshViewPager2 = (PdMPullToRefreshViewPager) findViewById(R.id.lib_pd_big_image_pager);
        this.f4550g = pdMPullToRefreshViewPager2;
        if (!this.w) {
        }
        this.f4550g.setOnRefreshListener(new b());
        PdMDropDownViewPager refreshableView2 = this.f4550g.getRefreshableView();
        this.f4551h = refreshableView2;
        refreshableView2.f(this.O);
        d dVar2 = new d(this, this.f4551h, arrayList);
        this.f4555l = dVar2;
        int i52 = this.f4554k;
        dVar2.f4582k = i52;
        dVar2.f4579h = textView3;
        this.f4551h.setCurrentItem(i52);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        SparseArrayCompat<String> sparseArrayCompat = this.f4553j;
        if (sparseArrayCompat != null) {
            sparseArrayCompat.clear();
            this.f4553j = null;
        }
        com.jd.lib.productdetail.mainimage.old.k kVar = this.C;
        if (kVar != null) {
            String str = this.t;
            if (kVar.f5170n) {
                if (!kVar.o) {
                    kVar.y(false);
                }
                PDManager.getEventBus().post(new PDViewEvent("action_event_change_video_constainer", null, str));
            } else {
                PDManager.getEventBus().post(new PDViewEvent("pd_action_close_default_video", null, str));
            }
        }
        this.y = true;
        Q = null;
        super.onDestroy();
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        PdMainImagePresenter pdMainImagePresenter;
        if (i2 == 24 || i2 == 25 || i2 == 164) {
            WareBusinessTopVideoControl wareBusinessTopVideoControl = this.B;
            if ((wareBusinessTopVideoControl != null && wareBusinessTopVideoControl.isHasMasterVideo()) && this.f4551h.getCurrentItem() == this.B.position && (pdMainImagePresenter = Q) != null) {
                pdMainImagePresenter.mtaClick("Productdetail_PhotoVideo", "", "3");
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        com.jd.lib.productdetail.mainimage.old.k kVar = this.C;
        if (kVar == null || !kVar.f5170n) {
            return;
        }
        if (!isFinishing() && this.C.C()) {
            com.jd.lib.productdetail.mainimage.old.k kVar2 = this.C;
            kVar2.i(this.B, kVar2, false, this.L, this.M, this.N, this.f4556m);
        }
        com.jd.lib.productdetail.mainimage.old.k kVar3 = this.C;
        kVar3.o = kVar3.o;
        VideoPlayView videoPlayView = kVar3.a;
        if (videoPlayView != null) {
            videoPlayView.onPause();
            kVar3.w(false);
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
