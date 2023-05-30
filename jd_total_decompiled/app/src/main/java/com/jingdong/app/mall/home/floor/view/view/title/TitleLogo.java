package com.jingdong.app.mall.home.floor.view.view.title;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.q.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class TitleLogo extends RelativeLayout {
    private static final int TYPE_PROMOTION = 2;
    private String biInfo;
    private String defImg;
    private SimpleDraweeView defLogo;
    private final f defLogoSize;
    private final HomeTitleNew homeTitleNew;
    private String img1;
    private String img2;
    private boolean isCacheData;
    private boolean isReleased;
    private com.jingdong.app.mall.home.r.e.f logoElement;
    private h logoModel;
    private int logoType;
    private float mScrollProgress;
    private SimpleDraweeView proLogo;
    private final AtomicBoolean proLogoLoadSuccess;
    private final f proLogoSize;
    private String srvJson;

    public TitleLogo(Context context, HomeTitleNew homeTitleNew) {
        super(context);
        this.proLogoLoadSuccess = new AtomicBoolean(false);
        this.defLogoSize = new f(116, 88);
        this.proLogoSize = new f(240, 88);
        this.homeTitleNew = homeTitleNew;
        initLogo();
    }

    private void addOrRefreshProLogo() {
        if (TextUtils.isEmpty(this.img2)) {
            resetLogo();
            return;
        }
        displayLogo(this.mScrollProgress);
        if (this.proLogo == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.proLogo = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = this.proLogoSize.u(this.proLogo);
            u.addRule(15);
            addView(this.proLogo, u);
            this.proLogo.setAlpha(0.01f);
        }
        this.defLogo.bringToFront();
        f.c(this.proLogo, this.proLogoSize);
        this.proLogo.setContentDescription(TextUtils.isEmpty(this.logoElement.O()) ? "\u9876\u90e8\u8d44\u6e90\u4f4d" : this.logoElement.O());
        SimpleDraweeView simpleDraweeView = this.proLogo;
        simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.TitleLogo.2
            {
                TitleLogo.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TitleLogo.this.onClick(view);
            }
        });
        this.homeTitleNew.setHaveTitleLogoResource();
        postMta(this.logoElement.n(), false);
        new a("\u8425\u9500topLogo \u66dd\u5149", true, this.logoElement.m()).b();
        new AtomicBoolean(true);
        e.p(simpleDraweeView, this.img2, e.b, new e.b
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x009d: INVOKE 
              (r0v12 'simpleDraweeView' com.facebook.drawee.view.SimpleDraweeView)
              (wrap: java.lang.String : 0x0094: IGET (r5v0 'this' com.jingdong.app.mall.home.floor.view.view.title.TitleLogo A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:21) com.jingdong.app.mall.home.floor.view.view.title.TitleLogo.img2 java.lang.String)
              (wrap: android.graphics.drawable.Drawable : 0x0096: SGET  A[WRAPPED] com.jingdong.app.mall.home.floor.ctrl.e.b android.graphics.drawable.Drawable)
              (wrap: com.jingdong.app.mall.home.floor.ctrl.e$b : 0x009a: CONSTRUCTOR 
              (r5v0 'this' com.jingdong.app.mall.home.floor.view.view.title.TitleLogo A[IMMUTABLE_TYPE, THIS])
              (r1 I:java.util.concurrent.atomic.AtomicBoolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.app.mall.home.floor.view.view.title.TitleLogo, java.util.concurrent.atomic.AtomicBoolean):void (m), WRAPPED] call: com.jingdong.app.mall.home.floor.view.view.title.TitleLogo.3.<init>(com.jingdong.app.mall.home.floor.view.view.title.TitleLogo, java.util.concurrent.atomic.AtomicBoolean):void type: CONSTRUCTOR)
             type: STATIC call: com.jingdong.app.mall.home.floor.ctrl.e.p(android.widget.ImageView, java.lang.String, android.graphics.drawable.Drawable, com.jingdong.app.mall.home.floor.ctrl.e$b):void A[MD:(android.widget.ImageView, java.lang.String, android.graphics.drawable.Drawable, com.jingdong.app.mall.home.floor.ctrl.e$b):void (m)] (LINE:21) in method: com.jingdong.app.mall.home.floor.view.view.title.TitleLogo.addOrRefreshProLogo():void, file: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            java.lang.String r0 = r5.img2
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Lc
            r5.resetLogo()
            return
        Lc:
            float r0 = r5.mScrollProgress
            r5.displayLogo(r0)
            com.facebook.drawee.view.SimpleDraweeView r0 = r5.proLogo
            if (r0 != 0) goto L3f
            com.jingdong.app.mall.home.base.HomeDraweeView r0 = new com.jingdong.app.mall.home.base.HomeDraweeView
            android.content.Context r1 = r5.getContext()
            r0.<init>(r1)
            r5.proLogo = r0
            android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY
            r0.setScaleType(r1)
            com.jingdong.app.mall.home.floor.common.f r0 = r5.proLogoSize
            com.facebook.drawee.view.SimpleDraweeView r1 = r5.proLogo
            android.widget.RelativeLayout$LayoutParams r0 = r0.u(r1)
            r1 = 15
            r0.addRule(r1)
            com.facebook.drawee.view.SimpleDraweeView r1 = r5.proLogo
            r5.addView(r1, r0)
            com.facebook.drawee.view.SimpleDraweeView r0 = r5.proLogo
            r1 = 1008981770(0x3c23d70a, float:0.01)
            r0.setAlpha(r1)
        L3f:
            com.facebook.drawee.view.SimpleDraweeView r0 = r5.defLogo
            r0.bringToFront()
            com.facebook.drawee.view.SimpleDraweeView r0 = r5.proLogo
            com.jingdong.app.mall.home.floor.common.f r1 = r5.proLogoSize
            com.jingdong.app.mall.home.floor.common.f.c(r0, r1)
            com.jingdong.app.mall.home.r.e.f r0 = r5.logoElement
            java.lang.String r0 = r0.O()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L5a
            java.lang.String r0 = "\u9876\u90e8\u8d44\u6e90\u4f4d"
            goto L60
        L5a:
            com.jingdong.app.mall.home.r.e.f r0 = r5.logoElement
            java.lang.String r0 = r0.O()
        L60:
            com.facebook.drawee.view.SimpleDraweeView r1 = r5.proLogo
            r1.setContentDescription(r0)
            com.facebook.drawee.view.SimpleDraweeView r0 = r5.proLogo
            com.jingdong.app.mall.home.floor.view.view.title.TitleLogo$2 r1 = new com.jingdong.app.mall.home.floor.view.view.title.TitleLogo$2
            r1.<init>()
            r0.setOnClickListener(r1)
            com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew r1 = r5.homeTitleNew
            r1.setHaveTitleLogoResource()
            com.jingdong.app.mall.home.r.e.f r1 = r5.logoElement
            java.lang.String r1 = r1.n()
            r2 = 0
            r5.postMta(r1, r2)
            com.jingdong.app.mall.home.q.a r1 = new com.jingdong.app.mall.home.q.a
            com.jingdong.app.mall.home.r.e.f r2 = r5.logoElement
            java.lang.String r2 = r2.m()
            java.lang.String r3 = "\u8425\u9500topLogo \u66dd\u5149"
            r4 = 1
            r1.<init>(r3, r4, r2)
            r1.b()
            java.util.concurrent.atomic.AtomicBoolean r1 = new java.util.concurrent.atomic.AtomicBoolean
            r1.<init>(r4)
            java.lang.String r2 = r5.img2
            android.graphics.drawable.Drawable r3 = com.jingdong.app.mall.home.floor.ctrl.e.b
            com.jingdong.app.mall.home.floor.view.view.title.TitleLogo$3 r4 = new com.jingdong.app.mall.home.floor.view.view.title.TitleLogo$3
            r4.<init>()
            com.jingdong.app.mall.home.floor.ctrl.e.p(r0, r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.view.view.title.TitleLogo.addOrRefreshProLogo():void");
    }

    private void displayLogo(float f2) {
        this.defLogo.setVisibility(0);
        this.defLogo.setAlpha(f2);
        String str = (!TextUtils.isEmpty(this.defImg) || this.logoType == 2) ? this.defImg : this.img1;
        if (TextUtils.isEmpty(str)) {
            str = "https://emptyUrl";
        }
        SimpleDraweeView simpleDraweeView = this.defLogo;
        JDDisplayImageOptions a = com.jingdong.app.mall.home.floor.ctrl.f.a();
        int i2 = HomeTitleNew.DEFAULT_LOGO_RES;
        e.f(str, simpleDraweeView, a.showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2));
    }

    private void handleProLogoClickState() {
        SimpleDraweeView simpleDraweeView = this.proLogo;
        if (simpleDraweeView == null) {
            return;
        }
        boolean z = simpleDraweeView.getAlpha() > 0.5f;
        if (this.proLogo.isClickable() ^ z) {
            this.proLogo.setClickable(z);
        }
    }

    private void initLogo() {
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.defLogo = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.defLogo.setImageResource(HomeTitleNew.DEFAULT_LOGO_RES);
        RelativeLayout.LayoutParams u = this.defLogoSize.u(this.defLogo);
        u.addRule(15);
        addView(this.defLogo, u);
        this.defLogo.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.TitleLogo.1
            {
                TitleLogo.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TitleLogo.this.onClick(view);
            }
        });
    }

    public void onClick(View view) {
        com.jingdong.app.mall.home.r.e.f fVar;
        JumpEntity jump;
        if (l.k() || view == null || view.getAlpha() < 0.5f || (fVar = this.logoElement) == null || (jump = fVar.getJump()) == null) {
            return;
        }
        l.e(getContext(), jump);
        HashMap hashMap = new HashMap();
        hashMap.put("extension_id", this.logoElement.o());
        b c2 = b.c(this.srvJson);
        c2.put("biinfo", this.logoType == 2 ? this.biInfo : "-100");
        com.jingdong.app.mall.home.r.c.a.u("Home_ResourceTop", String.format(Locale.getDefault(), "%s&%d&%d", this.logoElement.b0(), 0, 0), c2.toString(), RecommendMtaUtils.Home_PageId, hashMap, "");
        postMta(this.logoElement.e(), true);
        new a("topLogo \u70b9\u51fb", this.logoElement.f()).b();
    }

    private boolean parseLogoData(h hVar) {
        JDJSONArray j2;
        JDJSONObject jSONObject;
        JDJSONArray jSONArray;
        if (hVar == null || (j2 = hVar.j()) == null || j2.isEmpty() || (jSONObject = j2.getJSONObject(0)) == null || (jSONArray = jSONObject.getJSONArray("data")) == null || jSONArray.isEmpty()) {
            return false;
        }
        this.isReleased = false;
        this.isCacheData = hVar.Z;
        this.logoModel = hVar;
        com.jingdong.app.mall.home.r.e.f fVar = new com.jingdong.app.mall.home.r.e.f(jSONArray.getJSONObject(0), 0);
        this.logoElement = fVar;
        this.logoType = fVar.j0();
        this.img1 = this.logoElement.u();
        this.img2 = this.logoElement.v();
        this.defImg = this.logoElement.getJsonString("defaultImg");
        this.biInfo = this.logoElement.getJsonString("biInfo2", "-100");
        com.jingdong.app.mall.home.r.e.f fVar2 = this.logoElement;
        JumpEntity jump = fVar2 == null ? null : fVar2.getJump();
        this.srvJson = jump == null ? "" : jump.getSrvJson();
        return true;
    }

    private void postMta(String str, boolean z) {
        postUrl(str, b.c(this.srvJson), z, this.isCacheData);
    }

    public static void postUrl(String str, final b bVar, final boolean z, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.app.mall.home.o.a.f.d(bVar);
        com.jingdong.app.mall.home.o.a.f.c(bVar, z2);
        com.jingdong.app.mall.home.o.a.f.b(bVar);
        com.jingdong.app.mall.home.o.a.f.v0(str, new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.TitleLogo.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                com.jingdong.app.mall.home.r.c.a.y(z ? "Home_ResourceTop_ClkSuccess" : "Home_ResourceTop_Success", "", bVar.toString());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                com.jingdong.app.mall.home.r.c.a.y(z ? "Home_ResourceTop_ClkFail" : "Home_ResourceTop_Fail", "", bVar.toString());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                com.jingdong.app.mall.home.r.c.a.y(z ? "Home_ResourceTop_ClkRequest" : "Home_ResourceTop_Request", "", bVar.toString());
            }
        });
    }

    private void refreshLogo(boolean z) {
        displayLogo(1.0f);
        if (z) {
            sendExpoMta("-100");
        }
        SimpleDraweeView simpleDraweeView = this.proLogo;
        if (simpleDraweeView != null) {
            removeView(simpleDraweeView);
            this.proLogo = null;
        }
    }

    private void resetLogo() {
        this.isReleased = true;
        this.logoElement = null;
        this.logoType = 0;
        this.srvJson = "";
        this.defImg = "";
        this.biInfo = "-100";
        this.img1 = "";
        this.img2 = "";
        displayLogo(1.0f);
        SimpleDraweeView simpleDraweeView = this.proLogo;
        if (simpleDraweeView != null) {
            removeView(simpleDraweeView);
            this.proLogo = null;
        }
    }

    public void sendExpoMta(String str) {
        b c2 = b.c(this.srvJson);
        c2.put("biinfo", str);
        com.jingdong.app.mall.home.r.e.f fVar = this.logoElement;
        String format = String.format(Locale.getDefault(), "%s&%d&%d", fVar == null ? "" : fVar.b0(), 0, 0);
        com.jingdong.app.mall.home.r.c.a.y("Home_ResourceTopExpo", format, c2.toString());
        com.jingdong.app.mall.home.r.e.f fVar2 = this.logoElement;
        String o = fVar2 != null ? fVar2.o() : "";
        if (TextUtils.isEmpty(o)) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("extension_id", o);
        com.jingdong.app.mall.home.r.c.a.B("Home_ResourceTopADExpo", format, c2.toString(), RecommendMtaUtils.Home_PageId, "", hashMap);
    }

    private void sendLoadExpoMta() {
        b c2 = b.c(this.srvJson);
        com.jingdong.app.mall.home.r.e.f fVar = this.logoElement;
        com.jingdong.app.mall.home.r.c.a.y("Home_ResourceTopLoad", String.format(Locale.getDefault(), "%s&%d&%d", fVar == null ? "" : fVar.b0(), 0, 0), c2.toString());
    }

    public void showProLogo(boolean z) {
        if (z && this.proLogo != null && this.proLogoLoadSuccess.get()) {
            this.proLogo.setAlpha(Math.max(1.0f - this.mScrollProgress, 0.0f));
            this.defLogo.setAlpha(this.mScrollProgress);
            this.homeTitleNew.updateTitleResourceVisibleState(this.mScrollProgress < 1.0f);
        } else {
            this.defLogo.setAlpha(1.0f);
            SimpleDraweeView simpleDraweeView = this.proLogo;
            if (simpleDraweeView != null) {
                simpleDraweeView.setAlpha(0.01f);
            }
            this.homeTitleNew.updateTitleResourceVisibleState(false);
        }
        handleProLogoClickState();
    }

    public void afterRefresh() {
        if (this.logoModel == null) {
            resetLogo();
        }
    }

    public void beforeRefresh() {
        this.logoType = -1;
        this.logoModel = null;
        this.isReleased = true;
        this.proLogoLoadSuccess.set(false);
    }

    public void doAnimation(float f2) {
        SimpleDraweeView simpleDraweeView = this.proLogo;
        if (simpleDraweeView == null || this.defLogo == null || this.mScrollProgress != 0.0f) {
            return;
        }
        if (this.isReleased) {
            displayLogo(1.0f);
            return;
        }
        simpleDraweeView.setAlpha(1.0f - f2);
        this.defLogo.setAlpha(f2);
    }

    public void onScreenChanged() {
        f.c(this.defLogo, this.defLogoSize);
        f.c(this.proLogo, this.proLogoSize);
    }

    public void onTitleScroll(float f2) {
        this.mScrollProgress = Math.max(Math.min(f2, 1.0f), 0.0f);
        if (this.logoType == 2) {
            if (this.proLogo != null && this.proLogoLoadSuccess.get()) {
                this.proLogo.setAlpha(Math.max(1.0f - this.mScrollProgress, 0.0f));
                this.defLogo.setAlpha(this.mScrollProgress);
                this.homeTitleNew.updateTitleResourceVisibleState(this.mScrollProgress < 1.0f);
            } else {
                this.defLogo.setAlpha(1.0f);
                SimpleDraweeView simpleDraweeView = this.proLogo;
                if (simpleDraweeView != null) {
                    simpleDraweeView.setAlpha(0.01f);
                }
                this.homeTitleNew.updateTitleResourceVisibleState(false);
            }
            handleProLogoClickState();
            return;
        }
        this.homeTitleNew.updateTitleResourceVisibleState(false);
    }

    public void setLogoStyle(h hVar) {
        int i2 = parseLogoData(hVar) ? this.logoType : -1;
        this.logoType = i2;
        if (i2 == 0) {
            sendLoadExpoMta();
            refreshLogo(true);
        } else if (i2 != 2) {
            resetLogo();
        } else {
            sendLoadExpoMta();
            addOrRefreshProLogo();
        }
    }
}
