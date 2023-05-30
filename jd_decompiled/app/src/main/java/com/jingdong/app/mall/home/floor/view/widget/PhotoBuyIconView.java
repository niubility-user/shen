package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.ui.CameraUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SDKUtils;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class PhotoBuyIconView extends RelativeLayout {

    /* renamed from: g */
    private final JDDisplayImageOptions f10118g;

    /* renamed from: h */
    private boolean f10119h;

    /* renamed from: i */
    private long f10120i;

    /* renamed from: j */
    private final HomeDraweeView f10121j;

    /* renamed from: k */
    private f f10122k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            PhotoBuyIconView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PhotoBuyIconView.this.e();
        }
    }

    public PhotoBuyIconView(Context context) {
        super(context);
        this.f10118g = com.jingdong.app.mall.home.floor.ctrl.f.a();
        setContentDescription("\u62cd\u7167\u8d2d");
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.f10121j = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(homeDraweeView, new RelativeLayout.LayoutParams(-1, -1));
        setOnClickListener(new a());
    }

    private static boolean b() {
        return !SDKUtils.isSDKVersionMoreThan21() || CameraUtils.checkCameraHardware(JdSdk.getInstance().getApplicationContext());
    }

    private JumpEntity c() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("merge", (Object) DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        jDJSONObject.put("independent", (Object) "photobuy");
        jDJSONObject.put("showGuide", (Object) (this.f10119h ? "1" : "0"));
        JumpEntity jumpEntity = new JumpEntity();
        jumpEntity.des = JumpUtil.VALUE_DES_SCAN1;
        jumpEntity.params = jDJSONObject.toJSONString();
        return jumpEntity;
    }

    public static void d(HomeTitleNew homeTitleNew, h hVar, BaseActivity baseActivity) {
        JumpEntity jumpEntity;
        if (hVar != null && (jumpEntity = hVar.D) != null && !TextUtils.isEmpty(jumpEntity.des)) {
            l.e(baseActivity, hVar.D);
        } else if (b()) {
            DeepLinkScanHelper.startCaptureActivity(baseActivity, null);
        }
        com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
        bVar.a("status", homeTitleNew.getSearchProgress());
        com.jingdong.app.mall.home.r.c.a.s("Home_Scan", "", bVar.toString());
    }

    public void e() {
        if (System.currentTimeMillis() - this.f10120i > 1000) {
            l.e(getContext(), c());
            JDMtaUtils.onClickWithPageId(JdSdk.getInstance().getApplicationContext(), "Home_PhotoSearch", JDHomeFragment.class.getSimpleName(), "", RecommendMtaUtils.Home_PageId);
            this.f10120i = System.currentTimeMillis();
            new com.jingdong.app.mall.home.q.a("\u62cd\u7167\u8d2d\u70b9\u51fb", this.f10122k.f()).b();
        }
    }

    public void f(@NotNull f fVar, String str) {
        this.f10122k = fVar;
        new com.jingdong.app.mall.home.q.a("\u62cd\u7167\u8d2d\u66dd\u5149", true, fVar.m()).b();
        JDDisplayImageOptions resetViewBeforeLoading = this.f10118g.bitmapConfig(Bitmap.Config.ARGB_8888).resetViewBeforeLoading(false);
        int i2 = HomeTitleNew.sPhotoRes;
        resetViewBeforeLoading.showImageOnFail(i2).showImageOnLoading(i2).showImageForEmptyUri(i2);
        e.f(str, this.f10121j, this.f10118g);
        this.f10119h = com.jingdong.app.mall.home.o.a.f.m0(str);
    }
}
