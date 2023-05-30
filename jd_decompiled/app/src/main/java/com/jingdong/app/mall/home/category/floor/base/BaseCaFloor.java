package com.jingdong.app.mall.home.category.floor.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.react.modules.appstate.AppStateModule;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.n.g.u.c;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u00032\b\u0012\u0004\u0012\u00028\u00000\u0004B\u0017\u0012\u0006\u0010j\u001a\u00020i\u0012\u0006\u0010l\u001a\u00020k\u00a2\u0006\u0004\bm\u0010nJ\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0012\u001a\u00020\u00072\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00028\u0000H&\u00a2\u0006\u0004\b\u0015\u0010\u0016J5\u0010\u001a\u001a\u00020\u00072\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00028\u00002\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b \u0010\u0016J\u0017\u0010!\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b!\u0010\tJ\u0017\u0010$\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\"H\u0016\u00a2\u0006\u0004\b$\u0010%J\u0017\u0010'\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010*\u001a\u00020)H\u0004\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0004\u00a2\u0006\u0004\b,\u0010\tJ%\u0010/\u001a\u00020\u00072\u0014\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010-H\u0016\u00a2\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020)H\u0014\u00a2\u0006\u0004\b1\u0010+J\u0017\u00104\u001a\u00020\u00072\b\u00103\u001a\u0004\u0018\u000102\u00a2\u0006\u0004\b4\u00105J/\u0010:\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u0010H\u0017\u00a2\u0006\u0004\b:\u0010;J\u0017\u0010=\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b=\u0010(J\u0017\u0010@\u001a\u00020\u00072\u0006\u0010?\u001a\u00020>H\u0017\u00a2\u0006\u0004\b@\u0010AR\u0013\u0010D\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\bB\u0010CR\u0016\u0010F\u001a\u00020)8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bE\u0010+R\u0018\u0010I\u001a\u0004\u0018\u00010G8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010HR$\u0010N\u001a\u0004\u0018\u00018\u00008\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b/\u0010J\u001a\u0004\bK\u0010L\"\u0004\bM\u0010\u0016R\u0016\u0010Q\u001a\u00020O8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010PR$\u0010X\u001a\u0004\u0018\u00010\u000e8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u0018\u0010[\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0013\u0010]\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\\\u0010CR\u0016\u0010_\u001a\u00020\u00108V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b^\u0010CR\u0016\u0010a\u001a\u00020)8B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b`\u0010+R\u0013\u0010c\u001a\u00020\u00108F@\u0006\u00a2\u0006\u0006\u001a\u0004\bb\u0010CR\"\u0010h\u001a\u00020)8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010d\u001a\u0004\be\u0010+\"\u0004\bf\u0010g\u00a8\u0006o"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/base/BaseCaFloor;", "Lcom/jingdong/app/mall/home/n/g/u/c;", "M", "Landroid/widget/RelativeLayout;", "Lcom/jingdong/app/mall/home/category/floor/base/b;", "Landroid/graphics/Canvas;", "canvas", "", NotifyType.LIGHTS, "(Landroid/graphics/Canvas;)V", "Landroid/view/View;", "getContentView", "()Landroid/view/View;", CustomThemeConstance.NAVI_MODEL, "Lcom/jingdong/app/mall/home/category/adapter/a;", "adapter", "", "position", JshopConst.JSHOP_PROMOTIO_W, "(Lcom/jingdong/app/mall/home/n/g/u/c;Lcom/jingdong/app/mall/home/category/adapter/a;I)V", CartConstant.KEY_VENDOR_ITEM, "j", "(Lcom/jingdong/app/mall/home/n/g/u/c;)V", "", "", "payloads", JshopConst.JSHOP_PROMOTIO_X, "(Lcom/jingdong/app/mall/home/n/g/u/c;Lcom/jingdong/app/mall/home/category/adapter/a;ILjava/util/List;)V", JshopConst.JSHOP_PROMOTIO_Y, "(Lcom/jingdong/app/mall/home/n/g/u/c;Ljava/util/List;)V", "onViewRecycle", "()V", "i", "dispatchDraw", "Landroid/graphics/drawable/Drawable;", AppStateModule.APP_STATE_BACKGROUND, "setBackgroundDrawable", "(Landroid/graphics/drawable/Drawable;)V", "color", "setBackgroundColor", "(I)V", "", "A", "()Z", "m", "Landroid/util/Pair;", "radiiRect", "k", "(Landroid/util/Pair;)V", "B", "Landroid/graphics/Rect;", "paddingRect", "z", "(Landroid/graphics/Rect;)V", "left", "top", "right", "bottom", "setPadding", "(IIII)V", "visibility", "setVisibility", "Landroid/view/ViewGroup$LayoutParams;", "params", "setLayoutParams", "(Landroid/view/ViewGroup$LayoutParams;)V", "p", "()I", "floorHeight", "u", "isFloorDisplay", "Landroid/graphics/drawable/GradientDrawable;", "Landroid/graphics/drawable/GradientDrawable;", "mRoundBg", "Lcom/jingdong/app/mall/home/n/g/u/c;", PersonalConstants.ICON_STYLE_N, "()Lcom/jingdong/app/mall/home/n/g/u/c;", "setBindModel", "bindModel", "", "[F", "mBgRadii", "g", "Lcom/jingdong/app/mall/home/category/adapter/a;", "q", "()Lcom/jingdong/app/mall/home/category/adapter/a;", "setMParentAdapter", "(Lcom/jingdong/app/mall/home/category/adapter/a;)V", "mParentAdapter", JshopConst.JSHOP_PROMOTIO_H, "Landroid/graphics/drawable/Drawable;", "mBackground", "s", "topOffset", "o", "floorBgColor", "v", "isFullLine", "r", Constant.KEY_TITLE_HEIGHT, "Z", "t", "setFloorBind", "(Z)V", "isFloorBind", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "mCaAdapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class BaseCaFloor<M extends com.jingdong.app.mall.home.n.g.u.c> extends RelativeLayout implements b<M> {
    @Nullable

    /* renamed from: g  reason: from kotlin metadata */
    private com.jingdong.app.mall.home.category.adapter.a mParentAdapter;

    /* renamed from: h */
    private Drawable mBackground;

    /* renamed from: i  reason: from kotlin metadata */
    private GradientDrawable mRoundBg;

    /* renamed from: j  reason: from kotlin metadata */
    private final float[] mBgRadii;
    @Nullable

    /* renamed from: k  reason: from kotlin metadata */
    private M bindModel;

    /* renamed from: l */
    private boolean isFloorBind;

    public BaseCaFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context);
        this.mBgRadii = new float[8];
    }

    private final void l(Canvas canvas) {
        if (this.mRoundBg == null) {
            this.mRoundBg = new GradientDrawable();
        }
        GradientDrawable gradientDrawable = this.mRoundBg;
        if (gradientDrawable == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable.setColor(o());
        GradientDrawable gradientDrawable2 = this.mRoundBg;
        if (gradientDrawable2 == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable2.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        if (B()) {
            GradientDrawable gradientDrawable3 = this.mRoundBg;
            if (gradientDrawable3 == null) {
                Intrinsics.throwNpe();
            }
            gradientDrawable3.setCornerRadii(this.mBgRadii);
        } else {
            GradientDrawable gradientDrawable4 = this.mRoundBg;
            if (gradientDrawable4 == null) {
                Intrinsics.throwNpe();
            }
            gradientDrawable4.setCornerRadius(0.0f);
        }
        GradientDrawable gradientDrawable5 = this.mRoundBg;
        if (gradientDrawable5 == null) {
            Intrinsics.throwNpe();
        }
        gradientDrawable5.draw(canvas);
    }

    private final boolean v() {
        M m2 = this.bindModel;
        if (m2 != null) {
            if (m2 == null) {
                Intrinsics.throwNpe();
            }
            if (m2.l()) {
                return true;
            }
        }
        return false;
    }

    protected final boolean A() {
        return true;
    }

    public boolean B() {
        return f.M0();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(@NotNull Canvas canvas) {
        m(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.b
    @NotNull
    public View getContentView() {
        return this;
    }

    public void i(@NotNull M r4) {
        M m2 = this.bindModel;
        z(m2 != null ? m2.g() : null);
        M m3 = this.bindModel;
        Pair<Integer, Integer> a = m3 != null ? m3.a() : null;
        k(a);
        if (a != null && B()) {
            this.mBgRadii[3] = ((Number) a.first).intValue();
            float[] fArr = this.mBgRadii;
            fArr[2] = fArr[3];
            fArr[1] = fArr[2];
            fArr[0] = fArr[1];
            fArr[7] = ((Number) a.second).intValue();
            float[] fArr2 = this.mBgRadii;
            fArr2[6] = fArr2[7];
            fArr2[5] = fArr2[6];
            fArr2[4] = fArr2[5];
        }
        postInvalidate();
    }

    public abstract void j(@NotNull M r1);

    public void k(@Nullable Pair<Integer, Integer> radiiRect) {
    }

    protected final void m(@NotNull Canvas canvas) {
        try {
            M m2 = this.bindModel;
            if (m2 != null) {
                if (m2 == null) {
                    Intrinsics.throwNpe();
                }
                if (m2.B()) {
                    Drawable drawable = this.mBackground;
                    if (drawable == null) {
                        l(canvas);
                        return;
                    }
                    drawable.setBounds(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
                    drawable.draw(canvas);
                }
            }
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    @Nullable
    public final M n() {
        return this.bindModel;
    }

    public int o() {
        return com.jingdong.app.mall.home.state.dark.a.g(false);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.b
    public void onViewRecycle() {
        this.isFloorBind = false;
    }

    public final int p() {
        M m2 = this.bindModel;
        if (m2 == null) {
            return 0;
        }
        if (m2 == null) {
            Intrinsics.throwNpe();
        }
        return m2.getFloorHeight();
    }

    @Nullable
    /* renamed from: q  reason: from getter */
    public final com.jingdong.app.mall.home.category.adapter.a getMParentAdapter() {
        return this.mParentAdapter;
    }

    public final int r() {
        M m2 = this.bindModel;
        if (m2 == null) {
            return 0;
        }
        if (m2 == null) {
            Intrinsics.throwNpe();
        }
        return m2.i();
    }

    public final int s() {
        M m2 = this.bindModel;
        if (m2 == null) {
            return 0;
        }
        if (m2 == null) {
            Intrinsics.throwNpe();
        }
        return m2.k() - getTop();
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
    }

    @Override // android.view.View
    public void setBackgroundDrawable(@NotNull Drawable r2) {
        if (A()) {
            if (r2 == this.mBackground) {
                return;
            }
            this.mBackground = r2;
            postInvalidate();
            return;
        }
        super.setBackgroundDrawable(r2);
    }

    @Override // android.view.View
    @Deprecated(message = "")
    public void setLayoutParams(@NotNull ViewGroup.LayoutParams params) {
        if (params instanceof ViewGroup.MarginLayoutParams) {
            int p = p();
            if (getVisibility() != 8 && this.bindModel != null && p != 0) {
                com.jingdong.app.mall.home.category.adapter.a aVar = this.mParentAdapter;
                if (aVar == null) {
                    Intrinsics.throwNpe();
                }
                RecyclerView f2 = aVar.f();
                if (f2 != null) {
                    ((ViewGroup.MarginLayoutParams) params).setMargins(v() ? -f2.getPaddingLeft() : 0, 0, v() ? -f2.getPaddingRight() : 0, 0);
                }
                params.height = p + r();
                params.width = -1;
            } else {
                params.height = 0;
            }
            super.setLayoutParams(params);
        }
    }

    @Override // android.view.View
    @Deprecated(message = "")
    public void setPadding(int left, int top, int right, int bottom) {
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        int visibility2 = getVisibility();
        super.setVisibility(visibility);
        if (visibility2 != visibility) {
            setLayoutParams(getLayoutParams());
        }
    }

    /* renamed from: t  reason: from getter */
    public final boolean getIsFloorBind() {
        return this.isFloorBind;
    }

    public boolean u() {
        if (!this.isFloorBind || this.bindModel == null || getParent() == null) {
            return false;
        }
        return getBottom() - getHeight() < com.jingdong.app.mall.home.n.c.f10328n && getTop() + getHeight() > 0;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.b
    /* renamed from: w */
    public void d(@NotNull M m2, @NotNull com.jingdong.app.mall.home.category.adapter.a aVar, int i2) {
        this.isFloorBind = true;
        if (this.bindModel == m2) {
            return;
        }
        try {
            this.mParentAdapter = aVar;
            this.bindModel = m2;
            if (m2 == null) {
                Intrinsics.throwNpe();
            }
            j(m2);
            M m3 = this.bindModel;
            if (m3 == null) {
                Intrinsics.throwNpe();
            }
            i(m3);
        } catch (Exception e2) {
            e2.printStackTrace();
            f.o(e2.getMessage());
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.b
    /* renamed from: x */
    public void c(@NotNull M m2, @NotNull com.jingdong.app.mall.home.category.adapter.a aVar, int i2, @NotNull List<? extends Object> list) {
        M m3 = this.bindModel;
        if (m3 != null && m3 == m2) {
            try {
                this.isFloorBind = true;
                y(m2, list);
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                f.o(e2.getMessage());
                return;
            }
        }
        d(m2, aVar, i2);
    }

    public void y(@NotNull M m2, @Nullable List<? extends Object> list) {
    }

    public final void z(@Nullable Rect paddingRect) {
        if (paddingRect != null) {
            super.setPadding(paddingRect.left, paddingRect.top, paddingRect.right, paddingRect.bottom);
        }
    }
}
