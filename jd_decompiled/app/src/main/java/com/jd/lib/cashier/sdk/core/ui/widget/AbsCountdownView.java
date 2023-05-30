package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002:\u00016B#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u00100\u001a\u0004\u0018\u00010/\u0012\u0006\u00102\u001a\u000201\u00a2\u0006\u0004\b3\u00104B\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u00100\u001a\u0004\u0018\u00010/\u00a2\u0006\u0004\b3\u00105B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b3\u0010\u0007J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH&\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H&\u00a2\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\t\u001a\u00020\bH&\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0015J\u0015\u0010\u0017\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u0017\u0010\u000bJ\u000f\u0010\u0018\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0015J\u000f\u0010\u0019\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\u0019\u0010\u0015J\u000f\u0010\u001a\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0015R$\u0010!\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001c\u0010\u001e\"\u0004\b\u001f\u0010 R\u001d\u0010&\u001a\u00020\r8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\"\u0010.\u001a\u00020'8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-\u00a8\u00067"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountdownView;", "Landroid/widget/FrameLayout;", "Lcom/jd/lib/cashier/sdk/core/aac/e;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "k", "(Landroid/content/Context;)V", "", "duration", "m", "(J)V", "j", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "c", "(Landroid/content/Context;)Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "Lkotlin/Triple;", "", com.jingdong.app.mall.e.a, "(J)Lkotlin/Triple;", "o", "()V", "p", "q", "onAttachedToWindow", "onDetachedFromWindow", "onChangeSkin", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountdownView$a;", JshopConst.JSHOP_PROMOTIO_H, "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountdownView$a;", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountdownView$a;", PersonalConstants.ICON_STYLE_N, "(Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountdownView$a;)V", "onCountdownListener", "i", "Lkotlin/Lazy;", "f", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "countDownViewStateless", "Landroid/os/CountDownTimer;", "g", "Landroid/os/CountDownTimer;", "getCountDownTimer", "()Landroid/os/CountDownTimer;", NotifyType.LIGHTS, "(Landroid/os/CountDownTimer;)V", "countDownTimer", "Landroid/util/AttributeSet;", "attributeSet", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", com.jingdong.jdsdk.a.a.a, "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public abstract class AbsCountdownView extends FrameLayout implements com.jd.lib.cashier.sdk.core.aac.e {
    @NotNull

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    public CountDownTimer countDownTimer;
    @Nullable

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private a onCountdownListener;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private final Lazy countDownViewStateless;

    /* loaded from: classes14.dex */
    public interface a {
        void onFinish();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "invoke", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class b extends Lambda implements Function0<AbsCountDownStatelessView> {
        b() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final AbsCountDownStatelessView invoke() {
            AbsCountdownView absCountdownView = AbsCountdownView.this;
            Context context = AbsCountdownView.super.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "super.getContext()");
            return absCountdownView.c(context);
        }
    }

    /* loaded from: classes14.dex */
    static final class c implements Runnable {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ long f3009h;

        c(long j2) {
            this.f3009h = j2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Triple<String, String, String> e2 = AbsCountdownView.this.e(this.f3009h);
            String component1 = e2.component1();
            String component2 = e2.component2();
            String component3 = e2.component3();
            AbsCountDownStatelessView f2 = AbsCountdownView.this.f();
            f2.getHourClockTv$cashier_release().setText(component1);
            f2.getHourClockTv$cashier_release().setContentDescription(component1);
            f2.getMinuteClockTv$cashier_release().setText(component2);
            f2.getMinuteClockTv$cashier_release().setContentDescription(component2);
            f2.getSecondClockTv$cashier_release().setText(component3);
            f2.getSecondClockTv$cashier_release().setContentDescription(component3);
            AbsCountdownView absCountdownView = AbsCountdownView.this;
            absCountdownView.setContentDescription(absCountdownView.f().getCountDownContentDescription());
        }
    }

    public AbsCountdownView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new b());
        this.countDownViewStateless = lazy;
        k(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AbsCountDownStatelessView f() {
        return (AbsCountDownStatelessView) this.countDownViewStateless.getValue();
    }

    private final void k(Context context) {
        addView(f());
    }

    @NotNull
    public abstract AbsCountDownStatelessView c(@NotNull Context context);

    @NotNull
    public abstract Triple<String, String, String> e(long duration);

    @Nullable
    /* renamed from: h  reason: from getter */
    public final a getOnCountdownListener() {
        return this.onCountdownListener;
    }

    public abstract void j(long duration);

    public final void l(@NotNull CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public final void m(long duration) {
        j(duration);
    }

    public final void n(@Nullable a aVar) {
        this.onCountdownListener = aVar;
    }

    public final void o() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            if (countDownTimer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countDownTimer");
            }
            countDownTimer.start();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        o();
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        f().onChangeSkin();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        p();
        super.onDetachedFromWindow();
    }

    public final void p() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            if (countDownTimer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("countDownTimer");
            }
            countDownTimer.cancel();
        }
    }

    public final void q(long duration) {
        post(new c(duration));
    }

    public AbsCountdownView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AbsCountdownView(@NotNull Context context) {
        super(context);
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new b());
        this.countDownViewStateless = lazy;
        k(context);
    }
}
