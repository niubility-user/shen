package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 v2\u00020\u00012\u00020\u0002:\u0003\u009b\u0001@B\u001f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0095\u0001\u00a2\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001B(\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0095\u0001\u0012\u0007\u0010\u0099\u0001\u001a\u00020\f\u00a2\u0006\u0006\b\u0097\u0001\u0010\u009a\u0001J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H&\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH&\u00a2\u0006\u0004\b\r\u0010\u000eJ/\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0014\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\fH&\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001b\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u0005\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0005\u00a2\u0006\u0004\b!\u0010\"J!\u0010'\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010&\u001a\u0004\u0018\u00010%\u00a2\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00072\b\u0010)\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\b*\u0010+J!\u0010,\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010&\u001a\u0004\u0018\u00010%\u00a2\u0006\u0004\b,\u0010(J/\u00102\u001a\u00020\u0007\"\b\b\u0000\u0010.*\u00020-2\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000/2\b\u00101\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\b2\u00103J9\u00105\u001a\u00020\u0007\"\b\b\u0000\u0010.*\u00020-2\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000/2\u0006\u00104\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u00010\u0018H&\u00a2\u0006\u0004\b5\u00106J\u0017\u00109\u001a\u00020\u00072\b\u00108\u001a\u0004\u0018\u000107\u00a2\u0006\u0004\b9\u0010:J\u0019\u0010<\u001a\u0004\u0018\u0001072\u0006\u0010;\u001a\u00020\fH\u0000\u00a2\u0006\u0004\b<\u0010=J\r\u0010>\u001a\u00020\u0007\u00a2\u0006\u0004\b>\u0010\u000bJ\u000f\u0010?\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b?\u0010\u000bJ\r\u0010@\u001a\u00020\u0007\u00a2\u0006\u0004\b@\u0010\u000bR\u0018\u0010D\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010H\u001a\u0004\u0018\u00010E8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010GR*\u0010N\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010/8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010I\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR$\u0010S\u001a\u0004\u0018\u0001078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010:R\u0018\u0010U\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010CR\u0018\u0010X\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010WR$\u00101\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010+R$\u0010d\u001a\u0004\u0018\u00010^8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010_\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\"\u0010j\u001a\u00020e8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\bL\u0010f\u001a\u0004\bY\u0010g\"\u0004\bh\u0010iR\"\u0010o\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b>\u0010k\u001a\u0004\bl\u0010\"\"\u0004\bm\u0010nR\u0016\u0010p\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u0010,R\u0016\u0010r\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010qR$\u0010x\u001a\u0004\u0018\u00010-8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010s\u001a\u0004\bt\u0010u\"\u0004\bv\u0010wR\u0018\u0010y\u001a\u0004\u0018\u00010E8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010GR\u0018\u0010z\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010CR+\u0010\u0080\u0001\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010{8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010|\u001a\u0004\bB\u0010}\"\u0004\b~\u0010\u007fR*\u0010\u0086\u0001\u001a\u0004\u0018\u00010V8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0005\b\u0081\u0001\u0010W\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R*\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u0087\u00018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0016\n\u0005\bl\u0010\u0088\u0001\u001a\u0005\bF\u0010\u0089\u0001\"\u0006\b\u008a\u0001\u0010\u008b\u0001R7\u0010\u0093\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u008d\u00018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0017\n\u0005\b\u0016\u0010\u008e\u0001\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001\"\u0006\b\u0091\u0001\u0010\u0092\u0001R\u001a\u0010\u0094\u0001\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008f\u0001\u0010W\u00a8\u0006\u009c\u0001"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanView;", "Landroid/widget/FrameLayout;", "Lcom/jd/lib/cashier/sdk/core/aac/e;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "isUISelectReaction", "", "r", "(Landroid/content/Context;Z)V", "q", "()V", "", JshopConst.JSHOP_PROMOTIO_H, "()I", "width", "height", "oldWidth", "oldHeight", "onSizeChanged", "(IIII)V", "currentWidth", "u", "(I)V", "", "text", "isClick", JshopConst.JSHOP_PROMOTIO_Y, "(Ljava/lang/String;Z)V", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanView$b;", "type", DYConstants.LETTER_H, "(Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanView$b;)V", "f", "()Z", "Lcom/jd/lib/cashier/sdk/core/model/BaiTiaoExtraTip;", "btExtraTip", "Ljava/lang/Runnable;", "mtaCallBack", "K", "(Lcom/jd/lib/cashier/sdk/core/model/BaiTiaoExtraTip;Ljava/lang/Runnable;)V", "name", "J", "(Ljava/lang/String;)V", "I", "Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "W", "", "data", "mianxiHighlight", JshopConst.JSHOP_PROMOTIO_X, "(Ljava/util/List;Ljava/lang/String;)V", "screenWidth", JshopConst.JSHOP_PROMOTIO_W, "(Ljava/util/List;ILjava/lang/String;)V", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanItemView;", "planItemView", "t", "(Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanItemView;)V", "index", "o", "(I)Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanItemView;", "v", "onChangeSkin", "b", "Landroid/widget/TextView;", "m", "Landroid/widget/TextView;", "btGrowUpTextView", "Landroid/widget/ImageView;", PersonalConstants.ICON_STYLE_N, "Landroid/widget/ImageView;", "btGrowUpIcon", "Ljava/util/List;", com.jingdong.app.mall.e.a, "()Ljava/util/List;", "z", "(Ljava/util/List;)V", "dataList", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanItemView;", "j", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanItemView;", "A", "lastCheckedAbsPayPlanItemView", "i", "tvCreditCardBankName", "Landroid/widget/LinearLayout;", "Landroid/widget/LinearLayout;", "bankContainer", "p", "Ljava/lang/String;", "getMianxiHighlight", "()Ljava/lang/String;", "C", "Landroid/view/View$OnClickListener;", "Landroid/view/View$OnClickListener;", "getOnBTGrowUpIconClickListener", "()Landroid/view/View$OnClickListener;", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "(Landroid/view/View$OnClickListener;)V", "onBTGrowUpIconClickListener", "Landroid/view/View;", "Landroid/view/View;", "()Landroid/view/View;", "setPlanRootView", "(Landroid/view/View;)V", "planRootView", "Z", "s", "setClickAutoSelection", "(Z)V", "isClickAutoSelection", "currentDisplayWidth", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanView$b;", "typePlanView", "Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "k", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "B", "(Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;)V", "lastPlanItemViewEntity", "tvCreditCardCouponArrow", "tvCreditCardCoupon", "Lkotlin/Function0;", "Lkotlin/jvm/functions/Function0;", "()Lkotlin/jvm/functions/Function0;", "F", "(Lkotlin/jvm/functions/Function0;)V", "onHeaderClickListener", "g", "c", "()Landroid/widget/LinearLayout;", "setContainer", "(Landroid/widget/LinearLayout;)V", "container", "Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", "G", "(Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;)V", "onItemClickListener", "Lkotlin/Function1;", "Lkotlin/jvm/functions/Function1;", NotifyType.LIGHTS, "()Lkotlin/jvm/functions/Function1;", "E", "(Lkotlin/jvm/functions/Function1;)V", "onCreditCardCouponClickListener", "btGrowUpContainer", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", com.jingdong.jdsdk.a.a.a, "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public abstract class AbsPayPlanView extends FrameLayout implements com.jd.lib.cashier.sdk.core.aac.e {
    @NotNull
    private static final String A;

    /* renamed from: B  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @Nullable

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private LinearLayout container;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private LinearLayout bankContainer;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private TextView tvCreditCardBankName;

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private TextView tvCreditCardCoupon;

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private ImageView tvCreditCardCouponArrow;

    /* renamed from: l  reason: collision with root package name and from kotlin metadata */
    private LinearLayout btGrowUpContainer;

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private TextView btGrowUpTextView;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private ImageView btGrowUpIcon;
    @Nullable

    /* renamed from: o  reason: from kotlin metadata */
    private List<? extends IPlanItemViewEntity> dataList;
    @Nullable

    /* renamed from: p  reason: from kotlin metadata */
    private String mianxiHighlight;
    @Nullable

    /* renamed from: q  reason: from kotlin metadata */
    private AbsPayPlanItemView lastCheckedAbsPayPlanItemView;
    @Nullable

    /* renamed from: r  reason: from kotlin metadata */
    private IPlanItemViewEntity lastPlanItemViewEntity;
    @Nullable

    /* renamed from: s  reason: from kotlin metadata */
    private OnPlanViewClickListener onItemClickListener;
    @Nullable

    /* renamed from: t  reason: from kotlin metadata */
    private Function0<Unit> onHeaderClickListener;
    @Nullable

    /* renamed from: u  reason: from kotlin metadata */
    private Function1<? super String, Unit> onCreditCardCouponClickListener;

    /* renamed from: v  reason: from kotlin metadata */
    private boolean isClickAutoSelection;

    /* renamed from: w  reason: from kotlin metadata */
    private int currentDisplayWidth;

    /* renamed from: x  reason: from kotlin metadata */
    private b typePlanView;
    @Nullable

    /* renamed from: y  reason: from kotlin metadata */
    private View.OnClickListener onBTGrowUpIconClickListener;
    @NotNull

    /* renamed from: z  reason: from kotlin metadata */
    public View planRootView;

    /* renamed from: com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView$a  reason: from kotlin metadata */
    /* loaded from: classes14.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String a() {
            return AbsPayPlanView.A;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes14.dex */
    public enum b {
        PLAN_BAITIAO,
        PLAN_CREDIT_CARD
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Function0<Unit> m2 = AbsPayPlanView.this.m();
            if (m2 != null) {
                m2.invoke();
            }
        }
    }

    /* loaded from: classes14.dex */
    static final class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ AbsPayPlanItemView f3028g;

        d(AbsPayPlanItemView absPayPlanItemView) {
            this.f3028g = absPayPlanItemView;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f3028g.clearFocus();
            this.f3028g.sendAccessibilityEvent(65536);
            this.f3028g.sendAccessibilityEvent(4);
            this.f3028g.sendAccessibilityEvent(8);
            this.f3028g.sendAccessibilityEvent(32768);
        }
    }

    /* loaded from: classes14.dex */
    static final class e implements Runnable {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f3030h;

        e(int i2) {
            this.f3030h = i2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AbsPayPlanView.this.u(this.f3030h);
        }
    }

    /* loaded from: classes14.dex */
    static final class f implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f3032h;

        f(String str) {
            this.f3032h = str;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Function1<String, Unit> l2 = AbsPayPlanView.this.l();
            if (l2 != null) {
                String str = this.f3032h;
                if (str == null) {
                    str = "";
                }
                l2.invoke(str);
            }
        }
    }

    static {
        String simpleName = AbsPayPlanView.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "AbsPayPlanView::class.java.simpleName");
        A = simpleName;
    }

    public AbsPayPlanView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private final void r(Context context, boolean isUISelectReaction) {
        this.isClickAutoSelection = isUISelectReaction;
        View inflate = LayoutInflater.from(context).inflate(h(), (ViewGroup) this, true);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont\u2026teLayoutId(), this, true)");
        this.planRootView = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.container = (LinearLayout) inflate.findViewById(R.id.container_pay_plan_item_view);
        View view = this.planRootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.bankContainer = (LinearLayout) view.findViewById(R.id.container_pay_plan_bank_header);
        View view2 = this.planRootView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.tvCreditCardBankName = (TextView) view2.findViewById(R.id.tv_pay_plan_bank_name);
        View view3 = this.planRootView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.tvCreditCardCoupon = (TextView) view3.findViewById(R.id.tv_pay_plan_coupon_name);
        View view4 = this.planRootView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.tvCreditCardCouponArrow = (ImageView) view4.findViewById(R.id.tv_pay_plan_coupon_name_arrow);
        View view5 = this.planRootView;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.btGrowUpContainer = (LinearLayout) view5.findViewById(R.id.lib_cashier_bt_grow_up_container);
        View view6 = this.planRootView;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.btGrowUpTextView = (TextView) view6.findViewById(R.id.lib_cashier_bt_grow_up_text);
        View view7 = this.planRootView;
        if (view7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.btGrowUpIcon = (ImageView) view7.findViewById(R.id.lib_cashier_bt_grow_up_icon);
        q();
        LinearLayout linearLayout = this.bankContainer;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new c());
        }
    }

    public final void A(@Nullable AbsPayPlanItemView absPayPlanItemView) {
        this.lastCheckedAbsPayPlanItemView = absPayPlanItemView;
    }

    public final void B(@Nullable IPlanItemViewEntity iPlanItemViewEntity) {
        this.lastPlanItemViewEntity = iPlanItemViewEntity;
    }

    public final void C(@Nullable String str) {
        this.mianxiHighlight = str;
    }

    public final void D(@Nullable View.OnClickListener onClickListener) {
        this.onBTGrowUpIconClickListener = onClickListener;
    }

    public final void E(@Nullable Function1<? super String, Unit> function1) {
        this.onCreditCardCouponClickListener = function1;
    }

    public final void F(@Nullable Function0<Unit> function0) {
        this.onHeaderClickListener = function0;
    }

    public final void G(@Nullable OnPlanViewClickListener onPlanViewClickListener) {
        this.onItemClickListener = onPlanViewClickListener;
    }

    public final void H(@NotNull b type) {
        LinearLayout linearLayout;
        this.typePlanView = type;
        int i2 = a.$EnumSwitchMapping$0[type.ordinal()];
        if (i2 != 1) {
            if (i2 == 2 && (linearLayout = this.bankContainer) != null) {
                linearLayout.setVisibility(0);
                return;
            }
            return;
        }
        LinearLayout linearLayout2 = this.bankContainer;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
    }

    public final void I(@Nullable BaiTiaoExtraTip btExtraTip, @Nullable Runnable mtaCallBack) {
        CashierCommonPopConfig cashierCommonPopConfig;
        CashierCommonPopConfig cashierCommonPopConfig2;
        String str;
        m0.a(this.btGrowUpTextView, (byte) 3);
        TextView textView = this.btGrowUpTextView;
        if (textView != null) {
            if (btExtraTip == null || (str = btExtraTip.extraTipStr) == null) {
                str = "";
            }
            textView.setText(str);
        }
        if (TextUtils.isEmpty((btExtraTip == null || (cashierCommonPopConfig2 = btExtraTip.extraTipToast) == null) ? null : cashierCommonPopConfig2.cancelBtn)) {
            if (TextUtils.isEmpty((btExtraTip == null || (cashierCommonPopConfig = btExtraTip.extraTipToast) == null) ? null : cashierCommonPopConfig.confirmBtn)) {
                ImageView imageView = this.btGrowUpIcon;
                if (imageView != null) {
                    imageView.setOnClickListener(null);
                }
                ImageView imageView2 = this.btGrowUpIcon;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                    return;
                }
                return;
            }
        }
        if (mtaCallBack != null) {
            mtaCallBack.run();
        }
        ImageView imageView3 = this.btGrowUpIcon;
        if (imageView3 != null) {
            imageView3.setVisibility(0);
        }
        ImageView imageView4 = this.btGrowUpIcon;
        if (imageView4 != null) {
            imageView4.setOnClickListener(this.onBTGrowUpIconClickListener);
        }
    }

    public final void J(@Nullable String name) {
        if (this.typePlanView == b.PLAN_CREDIT_CARD) {
            if (!TextUtils.isEmpty(name)) {
                LinearLayout linearLayout = this.bankContainer;
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
                TextView textView = this.tvCreditCardBankName;
                if (textView != null) {
                    if (name == null) {
                        name = "";
                    }
                    textView.setText(name);
                }
            } else {
                LinearLayout linearLayout2 = this.bankContainer;
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(8);
                }
            }
            LinearLayout linearLayout3 = this.btGrowUpContainer;
            if (linearLayout3 != null) {
                linearLayout3.setVisibility(8);
                return;
            }
            return;
        }
        LinearLayout linearLayout4 = this.bankContainer;
        if (linearLayout4 != null) {
            linearLayout4.setVisibility(8);
        }
    }

    public final void K(@Nullable BaiTiaoExtraTip btExtraTip, @Nullable Runnable mtaCallBack) {
        if (this.typePlanView == b.PLAN_BAITIAO) {
            if (btExtraTip != null && !TextUtils.isEmpty(btExtraTip.extraTipStr)) {
                LinearLayout linearLayout = this.btGrowUpContainer;
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
                I(btExtraTip, mtaCallBack);
            } else {
                LinearLayout linearLayout2 = this.btGrowUpContainer;
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(8);
                }
            }
            LinearLayout linearLayout3 = this.bankContainer;
            if (linearLayout3 != null) {
                linearLayout3.setVisibility(8);
                return;
            }
            return;
        }
        LinearLayout linearLayout4 = this.btGrowUpContainer;
        if (linearLayout4 != null) {
            linearLayout4.setVisibility(8);
        }
    }

    public final void b() {
        TextView textView = this.btGrowUpTextView;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        }
        TextView textView2 = this.tvCreditCardBankName;
        if (textView2 != null) {
            textView2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_ECECEC));
        }
        ImageView imageView = this.tvCreditCardCouponArrow;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.lib_cashier_sdk_pay_channel_right_arrow);
        }
    }

    @Nullable
    /* renamed from: c  reason: from getter */
    public final LinearLayout getContainer() {
        return this.container;
    }

    @Nullable
    public final List<IPlanItemViewEntity> e() {
        return this.dataList;
    }

    public final boolean f() {
        LinearLayout linearLayout = this.btGrowUpContainer;
        return (linearLayout == null || linearLayout == null || linearLayout.getVisibility() != 0) ? false : true;
    }

    public abstract int h();

    @Nullable
    /* renamed from: j  reason: from getter */
    public final AbsPayPlanItemView getLastCheckedAbsPayPlanItemView() {
        return this.lastCheckedAbsPayPlanItemView;
    }

    @Nullable
    /* renamed from: k  reason: from getter */
    public final IPlanItemViewEntity getLastPlanItemViewEntity() {
        return this.lastPlanItemViewEntity;
    }

    @Nullable
    public final Function1<String, Unit> l() {
        return this.onCreditCardCouponClickListener;
    }

    @Nullable
    public final Function0<Unit> m() {
        return this.onHeaderClickListener;
    }

    @Nullable
    /* renamed from: n  reason: from getter */
    public final OnPlanViewClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Nullable
    public final AbsPayPlanItemView o(int index) {
        int i2 = index / 2;
        int i3 = index % 2;
        LinearLayout linearLayout = this.container;
        View childAt = linearLayout != null ? linearLayout.getChildAt(i2) : null;
        if (!(childAt instanceof LinearLayout)) {
            childAt = null;
        }
        LinearLayout linearLayout2 = (LinearLayout) childAt;
        View childAt2 = linearLayout2 != null ? linearLayout2.getChildAt(i3) : null;
        return childAt2 instanceof AbsPayPlanItemView ? childAt2 : null;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        b();
        setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
    }

    @Override // android.view.View
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        r.b(A, "onSizeChanged width = " + width + ", height = " + height);
        this.currentDisplayWidth = width;
        post(new e(width));
    }

    @NotNull
    public final View p() {
        View view = this.planRootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        return view;
    }

    public abstract void q();

    /* renamed from: s  reason: from getter */
    public final boolean getIsClickAutoSelection() {
        return this.isClickAutoSelection;
    }

    public final void t(@Nullable AbsPayPlanItemView planItemView) {
        if (planItemView != null) {
            try {
                if (g.a(planItemView.getContext())) {
                    planItemView.post(new d(planItemView));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public abstract void u(int currentWidth);

    public final void v() {
        List<? extends IPlanItemViewEntity> list = this.dataList;
        if (list != null) {
            x(list, this.mianxiHighlight);
        }
    }

    public abstract <W extends IPlanItemViewEntity> void w(@NotNull List<? extends W> data, int screenWidth, @Nullable String mianxiHighlight);

    public final <W extends IPlanItemViewEntity> void x(@NotNull List<? extends W> data, @Nullable String mianxiHighlight) {
        int i2 = this.currentDisplayWidth;
        if (i2 == 0) {
            i2 = DpiUtil.getWidth(super.getContext());
        }
        w(data, i2, mianxiHighlight);
    }

    public final void y(@Nullable String text, boolean isClick) {
        if (TextUtils.isEmpty(text)) {
            TextView textView = this.tvCreditCardCoupon;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        TextView textView2 = this.tvCreditCardCoupon;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = this.tvCreditCardCoupon;
        if (textView3 != null) {
            textView3.setText(text);
        }
        if (!isClick) {
            TextView textView4 = this.tvCreditCardCoupon;
            if (textView4 != null) {
                textView4.setClickable(false);
                return;
            }
            return;
        }
        TextView textView5 = this.tvCreditCardCoupon;
        if (textView5 != null) {
            textView5.setClickable(true);
        }
        Context context = super.getContext();
        if (context != null) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.lib_cashier_sdk_pay_channel_coupon_arrow);
            TextView textView6 = this.tvCreditCardCoupon;
            if (textView6 != null) {
                textView6.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            }
            TextView textView7 = this.tvCreditCardCoupon;
            if (textView7 != null) {
                textView7.setCompoundDrawablePadding(DpiUtil.dip2px(context, 2.0f));
            }
            TextView textView8 = this.tvCreditCardCoupon;
            if (textView8 != null) {
                textView8.setOnClickListener(new f(text));
            }
        }
    }

    public final void z(@Nullable List<? extends IPlanItemViewEntity> list) {
        this.dataList = list;
    }

    public AbsPayPlanView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isClickAutoSelection = true;
        this.typePlanView = b.PLAN_BAITIAO;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PayPlanViewSdk);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr\u2026styleable.PayPlanViewSdk)");
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.PayPlanViewSdk_clickAutoSelection, true);
        obtainStyledAttributes.recycle();
        r(context, z);
    }
}
