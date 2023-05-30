package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0004\b\u0010\u0010\u0014B#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0010\u0010\u0017J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J)\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0018"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/MSCountDownView;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountdownView;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "c", "(Landroid/content/Context;)Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "", "duration", "Lkotlin/Triple;", "", com.jingdong.app.mall.e.a, "(J)Lkotlin/Triple;", "", "j", "(J)V", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attributeSet", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class MSCountDownView extends AbsCountdownView {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "digit", "", "invoke", "(J)Ljava/lang/String;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class a extends Lambda implements Function1<Long, String> {
        public static final a INSTANCE = new a();

        a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ String invoke(Long l2) {
            return invoke(l2.longValue());
        }

        @NotNull
        public final String invoke(long j2) {
            if (j2 <= 9) {
                StringBuilder sb = new StringBuilder();
                sb.append('0');
                sb.append(j2);
                return sb.toString();
            }
            return String.valueOf(j2);
        }
    }

    /* loaded from: classes14.dex */
    public static final class b extends CountDownTimer {
        b(long j2, long j3, long j4) {
            super(j3, j4);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            AbsCountdownView.a onCountdownListener = MSCountDownView.this.getOnCountdownListener();
            if (onCountdownListener != null) {
                onCountdownListener.onFinish();
            }
            MSCountDownView.this.n(null);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            boolean isShown;
            if (Build.VERSION.SDK_INT >= 19) {
                isShown = MSCountDownView.this.isShown() && MSCountDownView.this.isAttachedToWindow();
            } else {
                isShown = MSCountDownView.this.isShown();
            }
            if (isShown) {
                MSCountDownView.this.q(j2);
            }
        }
    }

    public MSCountDownView(@NotNull Context context) {
        super(context);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView
    @NotNull
    public AbsCountDownStatelessView c(@NotNull Context context) {
        return new MSCountDownStatelessView(context);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView
    @NotNull
    public Triple<String, String, String> e(long duration) {
        a aVar = a.INSTANCE;
        long j2 = 60000;
        long j3 = duration / j2;
        long j4 = 1000;
        long j5 = (duration - (j2 * j3)) / j4;
        return new Triple<>(aVar.invoke((a) Long.valueOf(j3)), aVar.invoke((a) Long.valueOf(j5)), String.valueOf(((duration - ((60 * j3) * j4)) - (j4 * j5)) / 100));
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView
    public void j(long duration) {
        l(new b(duration, duration, 100L));
    }

    public MSCountDownView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MSCountDownView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
