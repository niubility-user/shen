package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.f;
import com.jingdong.app.mall.home.floor.model.entity.LinearWithCenterIconFloorEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.m.a;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.r;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class MallFloorLinearWithCenterIcon extends BaseMallFloor<r> implements IMallFloorUI {
    private static Pair<String, Bitmap> mBgCache;
    private boolean alreadyPostUrl;
    private boolean isClosed;
    private ImageView mCloseBtn;
    private SimpleDraweeView mImageView;
    private String mImgUrl;
    private RelativeLayout.LayoutParams mLayoutParams;
    private h mParentModel;
    private int preVisibility;

    public MallFloorLinearWithCenterIcon(Context context) {
        super(context);
        this.alreadyPostUrl = false;
        this.preVisibility = 8;
    }

    private void displayCenterIcon(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        final String md5 = Md5Encrypt.md5(str);
        Bitmap byCache = getByCache(md5, mBgCache);
        if (byCache != null) {
            setImgBitmap(byCache);
            return;
        }
        final String b = a.b(str);
        setImgBitmap(null);
        this.mImageView.setTag(b);
        f.i(b, new com.jingdong.app.mall.home.t.a() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearWithCenterIcon.3
            @Override // com.jingdong.app.mall.home.t.a
            public void onBitmapWithUiNull(Bitmap bitmap) {
                if (MallFloorLinearWithCenterIcon.this.mImageView == null || bitmap == null) {
                    return;
                }
                try {
                    Object tag = MallFloorLinearWithCenterIcon.this.mImageView.getTag();
                    if (tag != null && tag.equals(b)) {
                        bitmap.setHasAlpha(true);
                        Pair unused = MallFloorLinearWithCenterIcon.mBgCache = new Pair(md5, bitmap);
                        MallFloorLinearWithCenterIcon.this.setImgBitmap(bitmap);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    private Bitmap getByCache(String str, Pair<String, Bitmap> pair) {
        Object obj;
        if (pair == null || (obj = pair.second) == null || ((Bitmap) obj).isRecycled()) {
            return null;
        }
        if (TextUtils.isEmpty(str) || str.equals(pair.first)) {
            return (Bitmap) pair.second;
        }
        return null;
    }

    private void sendHomeFloorADExpo() {
        h hVar = this.mParentModel;
        if (hVar == null) {
            return;
        }
        String jsonString = hVar.getJsonString("extension_id");
        if (TextUtils.isEmpty(jsonString)) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("extension_id", jsonString);
        com.jingdong.app.mall.home.r.c.a.B("Home_FloorADExpo", "", this.mParentModel.f(), RecommendMtaUtils.Home_PageId, "", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImgBitmap(final Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            bitmap = null;
        }
        com.jingdong.app.mall.home.o.a.f.E0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearWithCenterIcon.4
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                if (MallFloorLinearWithCenterIcon.this.mImageView == null) {
                    return;
                }
                if (bitmap == null) {
                    MallFloorLinearWithCenterIcon.this.mImageView.setImageResource(R.drawable.home_top_floor_default);
                } else {
                    MallFloorLinearWithCenterIcon.this.mImageView.setImageBitmap(bitmap);
                }
                MallFloorLinearWithCenterIcon.this.mImageView.setLayoutParams(MallFloorLinearWithCenterIcon.this.mLayoutParams);
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.preVisibility == 0 && super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public int getLayoutHeight() {
        if (this.isClosed) {
            return 0;
        }
        return super.getLayoutHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean isAttachTopFloor() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        com.jingdong.app.mall.home.r.c.a.i().d(this, ((r) this.mPresenter).g(), ((r) this.mPresenter).h());
        if (JDHomeFragment.Q0()) {
            setVisibility(8);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void onHomeResume(int i2, int i3) {
        checkAndReportHomeFloorIDExpo();
        setVisibility(this.preVisibility);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        checkAndReportHomeFloorIDExpo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onViewBindData(d dVar) {
        ImageView imageView;
        super.onViewBindData(dVar);
        cleanUI();
        if (dVar == null) {
            return;
        }
        h hVar = dVar.mParentModel;
        this.mParentModel = hVar;
        if (hVar == null) {
            return;
        }
        this.isClosed = false;
        this.alreadyPostUrl = false;
        if (this.mImageView == null) {
            this.mLayoutParams = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.f9279g, getLayoutHeight());
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.mImageView = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            this.mLayoutParams.width = com.jingdong.app.mall.home.floor.common.d.f9279g;
            this.mLayoutParams.height = getLayoutHeight();
        }
        this.mImgUrl = this.mParentModel.y;
        this.mImageView.setLayoutParams(this.mLayoutParams);
        setImgBitmap(null);
        displayCenterIcon(this.mImgUrl);
        m.b(this, this.mImageView, -1);
        setItemClickListener(this.mParentModel.getJump(), this.mParentModel.getJsonString("extension_id"));
        if (((r) this.mPresenter).R() && this.mCloseBtn == null) {
            HomeImageView homeImageView = new HomeImageView(getContext());
            this.mCloseBtn = homeImageView;
            homeImageView.setContentDescription("\u5173\u95ed\u6309\u94ae");
            this.mCloseBtn.setScaleType(ImageView.ScaleType.FIT_XY);
            this.mCloseBtn.setImageResource(R.drawable.home_floor_top_close);
            this.mCloseBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearWithCenterIcon.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MallFloorLinearWithCenterIcon.this.isClosed = true;
                    String h2 = MallFloorLinearWithCenterIcon.this.mParentModel.h();
                    if (!TextUtils.isEmpty(h2)) {
                        JDMtaUtils.sendCommonData(MallFloorLinearWithCenterIcon.this.getContext(), "Home_TopTLFloorClose", h2, "", com.jingdong.app.mall.home.r.c.a.f10642k, "", "", "", RecommendMtaUtils.Home_PageId);
                    }
                    com.jingdong.app.mall.home.o.a.f.A0(LinearWithCenterIconFloorEntity.CLOSE_ID, MallFloorLinearWithCenterIcon.this.mParentModel.getJsonString("materialId", "empty"));
                    MallFloorLinearWithCenterIcon.this.setVisibility(8);
                    com.jingdong.app.mall.home.a.s.h(true, 8);
                    JDHomeFragment z0 = JDHomeFragment.z0();
                    if (z0 == null) {
                        return;
                    }
                    z0.j1();
                }
            });
        }
        if (((r) this.mPresenter).R() && (imageView = this.mCloseBtn) != null && imageView.getParent() == null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.jingdong.app.mall.home.floor.common.d.d(78), com.jingdong.app.mall.home.floor.common.d.d(78));
            layoutParams.addRule(11);
            this.mCloseBtn.setPadding(com.jingdong.app.mall.home.floor.common.d.d(24), com.jingdong.app.mall.home.floor.common.d.d(24), com.jingdong.app.mall.home.floor.common.d.d(24), com.jingdong.app.mall.home.floor.common.d.d(24));
            addView(this.mCloseBtn, layoutParams);
        }
    }

    protected void setItemClickListener(final JumpEntity jumpEntity, final String str) {
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearWithCenterIcon.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str2 = "";
                if (jumpEntity == null || l.k()) {
                    return;
                }
                MallFloorLinearWithCenterIcon.this.postUrl(((r) ((BaseMallColorFloor) MallFloorLinearWithCenterIcon.this).mPresenter).P());
                try {
                    JumpEntity jumpEntity2 = jumpEntity;
                    str2 = com.jingdong.app.mall.home.r.c.b.c(jumpEntity2 == null ? "" : jumpEntity2.getSrvJson()).toString();
                } catch (Exception e2) {
                    com.jingdong.app.mall.home.o.a.f.s0(this, e2);
                }
                HashMap hashMap = new HashMap(1);
                hashMap.put("extension_id", str);
                Context context = MallFloorLinearWithCenterIcon.this.getContext();
                JumpEntity jumpEntity3 = jumpEntity;
                l.onClickJsonEvent(context, jumpEntity3, "", jumpEntity3.getSrv(), str2, 0, hashMap);
            }
        });
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (this.preVisibility != i2) {
            this.preVisibility = i2;
            if (i2 == 0) {
                displayCenterIcon(this.mImgUrl);
                sendHomeFloorADExpo();
            }
        }
        if (this.alreadyPostUrl || i2 != 0 || getParent() == null) {
            return;
        }
        this.alreadyPostUrl = true;
        postUrl(((r) this.mPresenter).Q());
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void unUseMargins(ViewGroup.MarginLayoutParams marginLayoutParams) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public r createPresenter() {
        return new r();
    }
}
