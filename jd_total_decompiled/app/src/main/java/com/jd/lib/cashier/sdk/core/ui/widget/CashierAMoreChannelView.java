package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010:\u001a\u0004\u0018\u000109\u00a2\u0006\u0004\b;\u0010<B#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010:\u001a\u0004\u0018\u000109\u0012\u0006\u0010>\u001a\u00020=\u00a2\u0006\u0004\b;\u0010?J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018R$\u0010 \u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010(\u001a\u00020!8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u00100\u001a\u0004\u0018\u00010)8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R$\u00108\u001a\u0004\u0018\u0001018\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107\u00a8\u0006@"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/CashierAMoreChannelView;", "Landroid/widget/FrameLayout;", "Lcom/jd/lib/cashier/sdk/core/aac/e;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "b", "(Landroid/content/Context;)V", "Lcom/jd/lib/cashier/sdk/d/b/a$a;", "imageType", "c", "(Lcom/jd/lib/cashier/sdk/d/b/a$a;)V", "", "text", com.jingdong.app.mall.e.a, "(Ljava/lang/String;)V", "", com.jingdong.jdsdk.a.a.a, "()Z", "Lcom/jd/lib/cashier/sdk/d/b/a$b;", "splitLineType", "f", "(Lcom/jd/lib/cashier/sdk/d/b/a$b;)V", "onChangeSkin", "()V", "Landroid/widget/TextView;", "i", "Landroid/widget/TextView;", "getMoreChannelText", "()Landroid/widget/TextView;", "setMoreChannelText", "(Landroid/widget/TextView;)V", "moreChannelText", "Landroid/view/View;", "g", "Landroid/view/View;", "getMoreChannelRootView", "()Landroid/view/View;", "setMoreChannelRootView", "(Landroid/view/View;)V", "moreChannelRootView", "Landroid/widget/LinearLayout;", JshopConst.JSHOP_PROMOTIO_H, "Landroid/widget/LinearLayout;", "getMoreChannelRoot", "()Landroid/widget/LinearLayout;", "setMoreChannelRoot", "(Landroid/widget/LinearLayout;)V", "moreChannelRoot", "Landroid/widget/ImageView;", "j", "Landroid/widget/ImageView;", "getMoreChannelArrow", "()Landroid/widget/ImageView;", "setMoreChannelArrow", "(Landroid/widget/ImageView;)V", "moreChannelArrow", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierAMoreChannelView extends FrameLayout implements com.jd.lib.cashier.sdk.core.aac.e {
    @NotNull

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    public View moreChannelRootView;
    @Nullable

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private LinearLayout moreChannelRoot;
    @Nullable

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private TextView moreChannelText;
    @Nullable

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private ImageView moreChannelArrow;

    static {
        Intrinsics.checkExpressionValueIsNotNull(CashierAMoreChannelView.class.getSimpleName(), "CashierAMoreChannelView::class.java.simpleName");
    }

    public CashierAMoreChannelView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private final void b(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_a_pay_more_channel_container, (ViewGroup) this, true);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont\u2026el_container, this, true)");
        this.moreChannelRootView = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moreChannelRootView");
        }
        this.moreChannelRoot = (LinearLayout) inflate.findViewById(R.id.lib_cashier_sdk_a_more_info_root);
        View view = this.moreChannelRootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moreChannelRootView");
        }
        this.moreChannelText = (TextView) view.findViewById(R.id.lib_cashier_sdk_a_more_info_text);
        View view2 = this.moreChannelRootView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moreChannelRootView");
        }
        this.moreChannelArrow = (ImageView) view2.findViewById(R.id.lib_cashier_sdk_a_more_info_arrow);
    }

    public final boolean a() {
        TextView textView = this.moreChannelText;
        return textView != null && textView.getVisibility() == 0;
    }

    public final void c(@NotNull a.EnumC0116a imageType) {
        if (a.EnumC0116a.NONE == imageType) {
            ImageView imageView = this.moreChannelArrow;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
        } else if (a.EnumC0116a.RIGHT_ARROW == imageType) {
            ImageView imageView2 = this.moreChannelArrow;
            if (imageView2 != null) {
                imageView2.setVisibility(0);
            }
            ImageView imageView3 = this.moreChannelArrow;
            if (imageView3 != null) {
                imageView3.setImageResource(R.drawable.lib_cashier_sdk_pay_channel_right_arrow);
            }
        } else if (a.EnumC0116a.DOWN_ARROW == imageType) {
            ImageView imageView4 = this.moreChannelArrow;
            if (imageView4 != null) {
                imageView4.setVisibility(0);
            }
            ImageView imageView5 = this.moreChannelArrow;
            if (imageView5 != null) {
                imageView5.setImageResource(R.drawable.lib_cashier_sdk_icon_friend_pay_product_list_down);
            }
        }
    }

    public final void e(@NotNull String text) {
        if (TextUtils.isEmpty(text)) {
            TextView textView = this.moreChannelText;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        TextView textView2 = this.moreChannelText;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = this.moreChannelText;
        if (textView3 != null) {
            textView3.setText(text);
        }
    }

    public final void f(@NotNull a.b splitLineType) {
        if (a.b.NORMAL == splitLineType) {
            LinearLayout linearLayout = this.moreChannelRoot;
            if (linearLayout != null) {
                linearLayout.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
        } else if (a.b.FLOOR_BOTTOM == splitLineType) {
            if (JDDarkUtil.isDarkMode()) {
                LinearLayout linearLayout2 = this.moreChannelRoot;
                if (linearLayout2 != null) {
                    linearLayout2.setBackgroundResource(R.drawable.lib_cashier_sdk_a_bottom_corner_dark_bg);
                    return;
                }
                return;
            }
            LinearLayout linearLayout3 = this.moreChannelRoot;
            if (linearLayout3 != null) {
                linearLayout3.setBackgroundResource(R.drawable.lib_cashier_sdk_a_bottom_corner_bg);
            }
        } else if (a.b.FLOOR_TOP_AND_NORMAL == splitLineType) {
            if (JDDarkUtil.isDarkMode()) {
                LinearLayout linearLayout4 = this.moreChannelRoot;
                if (linearLayout4 != null) {
                    linearLayout4.setBackgroundResource(R.drawable.lib_cashier_sdk_a_top_corner_dark_bg);
                    return;
                }
                return;
            }
            LinearLayout linearLayout5 = this.moreChannelRoot;
            if (linearLayout5 != null) {
                linearLayout5.setBackgroundResource(R.drawable.lib_cashier_sdk_a_top_corner_bg);
            }
        } else if (a.b.FLOOR_TOP_AND_BOTTOM == splitLineType) {
            if (JDDarkUtil.isDarkMode()) {
                LinearLayout linearLayout6 = this.moreChannelRoot;
                if (linearLayout6 != null) {
                    linearLayout6.setBackgroundResource(R.drawable.lib_cashier_sdk_a_top_bottom_corner_dark_bg);
                    return;
                }
                return;
            }
            LinearLayout linearLayout7 = this.moreChannelRoot;
            if (linearLayout7 != null) {
                linearLayout7.setBackgroundResource(R.drawable.lib_cashier_sdk_a_top_bottom_corner_bg);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        TextView textView = this.moreChannelText;
        if (textView != null) {
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        }
    }

    public CashierAMoreChannelView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b(context);
    }
}
