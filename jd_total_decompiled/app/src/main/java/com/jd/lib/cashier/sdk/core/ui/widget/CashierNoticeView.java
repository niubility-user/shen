package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010(\u001a\u0004\u0018\u00010'\u0012\u0006\u0010)\u001a\u00020\u001f\u00a2\u0006\u0004\b*\u0010+B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010(\u001a\u0004\u0018\u00010'\u00a2\u0006\u0004\b*\u0010,J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n\u00a2\u0006\u0004\b\u0010\u0010\u0011J)\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0016\u0010\u001b\u001a\u00020\u00188\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010!\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010 R*\u0010\u000f\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\n8\u0006@FX\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b\u0019\u0010\u0011\u00a8\u0006-"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/CashierNoticeView;", "Landroid/widget/FrameLayout;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "f", "(Landroid/content/Context;)V", "Landroid/view/View;", "d", "()Landroid/view/View;", "", "recommendBtnText", "closeButton", com.jingdong.app.mall.e.a, "(Ljava/lang/String;Ljava/lang/String;)Landroid/view/View;", "message", "b", "(Ljava/lang/String;)V", "c", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/jd/lib/cashier/sdk/core/ui/a/a;", "cashierNoticeListener", JshopConst.JSHOP_PROMOTIO_H, "(Lcom/jd/lib/cashier/sdk/core/ui/a/a;)V", "Landroid/widget/TextView;", "g", "Landroid/widget/TextView;", "messageTv", "i", "Lcom/jd/lib/cashier/sdk/core/ui/a/a;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "", "I", "type", "value", "j", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "Landroid/util/AttributeSet;", "attributeSet", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierNoticeView extends FrameLayout {

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private TextView messageTv;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private int type;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private com.jd.lib.cashier.sdk.core.ui.a.a listener;
    @NotNull

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private String message;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CashierNoticeView.this.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CashierNoticeView.this.setVisibility(8);
            com.jd.lib.cashier.sdk.core.ui.a.a aVar = CashierNoticeView.this.listener;
            if (aVar != null) {
                aVar.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CashierNoticeView.this.setVisibility(8);
            com.jd.lib.cashier.sdk.core.ui.a.a aVar = CashierNoticeView.this.listener;
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    public CashierNoticeView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.message = "";
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CashierNoticeViewSdk);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr\u2026ble.CashierNoticeViewSdk)");
        int i3 = obtainStyledAttributes.getInt(R.styleable.CashierNoticeViewSdk_noticeViewType, 0);
        String string = obtainStyledAttributes.getString(R.styleable.CashierNoticeViewSdk_noticeViewMessage);
        String str = string != null ? string : "";
        Intrinsics.checkExpressionValueIsNotNull(str, "typedArray.getString(R.s\u2026_noticeViewMessage) ?: \"\"");
        obtainStyledAttributes.recycle();
        this.type = i3;
        g(str);
        f(context);
    }

    private final View d() {
        int i2;
        int i3 = this.type;
        if (i3 == 0) {
            i2 = R.layout.lib_cashier_sdk_notice_view_layout_default;
        } else if (i3 != 1) {
            i2 = R.layout.lib_cashier_sdk_notice_view_layout_default;
        } else {
            i2 = R.layout.lib_cashier_sdk_notice_view_layout_normal;
        }
        View bodyView = LayoutInflater.from(super.getContext()).inflate(i2, (ViewGroup) this, false);
        View findViewById = bodyView.findViewById(R.id.lib_cashier_notice_view_message);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "bodyView.findViewById<Te\u2026hier_notice_view_message)");
        TextView textView = (TextView) findViewById;
        this.messageTv = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageTv");
        }
        textView.setText(this.message);
        if (this.type == 1) {
            View findViewById2 = bodyView.findViewById(R.id.lib_cashier_notice_view_close);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "bodyView.findViewById(R.\u2026ashier_notice_view_close)");
            findViewById2.setOnClickListener(new a());
        }
        Intrinsics.checkExpressionValueIsNotNull(bodyView, "bodyView");
        return bodyView;
    }

    private final View e(String recommendBtnText, String closeButton) {
        int i2;
        if (this.type != 2) {
            i2 = R.layout.lib_cashier_sdk_notice_view_layout_default;
        } else {
            i2 = R.layout.lib_cashier_sdk_notice_view_layout_recommend;
        }
        View bodyView = LayoutInflater.from(super.getContext()).inflate(i2, (ViewGroup) this, false);
        View findViewById = bodyView.findViewById(R.id.lib_cashier_notice_view_message);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "bodyView.findViewById<Te\u2026hier_notice_view_message)");
        TextView textView = (TextView) findViewById;
        this.messageTv = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageTv");
        }
        textView.setText(this.message);
        if (this.type == 2) {
            View findViewById2 = bodyView.findViewById(R.id.lib_cashier_notice_view_recommend_close_icon);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "bodyView.findViewById(R.\u2026iew_recommend_close_icon)");
            if (Intrinsics.areEqual(closeButton, "0")) {
                findViewById2.setVisibility(8);
                bodyView.setBackgroundColor(Color.parseColor("#FEE9E8"));
                TextView textView2 = this.messageTv;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("messageTv");
                }
                textView2.setPadding(16, 0, 0, 0);
            }
            findViewById2.setOnClickListener(new b());
            View findViewById3 = bodyView.findViewById(R.id.lib_cashier_notice_view_recommend_btn_text);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, "bodyView.findViewById(R.\u2026_view_recommend_btn_text)");
            TextView textView3 = (TextView) findViewById3;
            textView3.setText(recommendBtnText);
            textView3.setOnClickListener(new c());
        }
        Intrinsics.checkExpressionValueIsNotNull(bodyView, "bodyView");
        return bodyView;
    }

    private final void f(Context context) {
        addView(d());
    }

    public final void b(@NotNull String message) {
        if (this.type != 0) {
            this.type = 0;
            removeAllViews();
            addView(d());
        }
        g(message);
    }

    public final void c(@NotNull String message, @Nullable String recommendBtnText, @Nullable String closeButton) {
        if (this.type != 2) {
            this.type = 2;
            removeAllViews();
            addView(e(recommendBtnText, closeButton));
        }
        g(message);
        TextView textView = (TextView) findViewById(R.id.lib_cashier_notice_view_recommend_btn_text);
        if (textView != null) {
            textView.setText(recommendBtnText);
        }
    }

    public final void g(@NotNull String str) {
        TextView textView = this.messageTv;
        if (textView != null) {
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageTv");
            }
            textView.setText(str);
            TextView textView2 = this.messageTv;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("messageTv");
            }
            textView2.setSelected(true);
        }
        this.message = str;
    }

    public final void h(@NotNull com.jd.lib.cashier.sdk.core.ui.a.a cashierNoticeListener) {
        this.listener = cashierNoticeListener;
    }

    public CashierNoticeView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
