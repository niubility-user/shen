package com.jingdong.common.utils.personal.dark;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jdcn.biz.client.BankCardConstants;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.utils.DeepDarkChangeManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0005"}, d2 = {"Lcom/jingdong/common/utils/personal/dark/PersonalDarkHelper;", "", "<init>", "()V", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalDarkHelper {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b*\u0010+J\u000f\u0010\u0003\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0007\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0007\u00a2\u0006\u0004\b\b\u0010\tJ%\u0010\b\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007\u00a2\u0006\u0004\b\b\u0010\u0010J:\u0010\b\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\n2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u000f0\u0011H\u0007\u00a2\u0006\u0004\b\b\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0007\u00a2\u0006\u0004\b\u0016\u0010\tJ\u001d\u0010\u0016\u001a\u00020\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0017J)\u0010\u001c\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0007\u00a2\u0006\u0004\b\u001c\u0010\u001dJ)\u0010\u001f\u001a\u00020\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0007\u00a2\u0006\u0004\b\u001f\u0010\u001dJ)\u0010!\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0007\u00a2\u0006\u0004\b!\u0010\u001dJ\u0019\u0010\"\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0007\u00a2\u0006\u0004\b\"\u0010#J\u0019\u0010%\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010$H\u0007\u00a2\u0006\u0004\b%\u0010&J\u0019\u0010(\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010'H\u0007\u00a2\u0006\u0004\b(\u0010)\u00a8\u0006,"}, d2 = {"Lcom/jingdong/common/utils/personal/dark/PersonalDarkHelper$Companion;", "", "", "isDarkMode", "()Z", "isLightMode", "Lcom/jingdong/common/utils/DeepDarkChangeManager$OnUIModeChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "register", "(Lcom/jingdong/common/utils/DeepDarkChangeManager$OnUIModeChangeListener;)Z", "Landroidx/lifecycle/LifecycleOwner;", BankCardConstants.KEY_OWNER, "Landroidx/lifecycle/Observer;", "", "observer", "", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Observer;)V", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "uiMode", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function1;)V", "unregister", "(Landroidx/lifecycle/Observer;)V", "Landroid/view/View;", "view", "lightRes", "darkRes", "setBackgroundResource", "(Landroid/view/View;II)V", "textView", "setTextColor", "imageView", "setImageViewResource", "setMaskVisibility", "(Landroid/view/View;)V", "Landroid/widget/ImageView;", "setImageViewDark", "(Landroid/widget/ImageView;)V", "Lcom/facebook/drawee/view/SimpleDraweeView;", "setSimpleDraweeViewDark", "(Lcom/facebook/drawee/view/SimpleDraweeView;)V", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final boolean isDarkMode() {
            DeepDarkChangeManager deepDarkChangeManager = DeepDarkChangeManager.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(deepDarkChangeManager, "DeepDarkChangeManager.getInstance()");
            return deepDarkChangeManager.getUIMode() == 1;
        }

        @JvmStatic
        public final boolean isLightMode() {
            return !isDarkMode();
        }

        @JvmStatic
        public final boolean register(@NotNull DeepDarkChangeManager.OnUIModeChangeListener listener) {
            return DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(listener);
        }

        @JvmStatic
        public final void setBackgroundResource(@Nullable View view, int lightRes, int darkRes) {
            if (view != null) {
                if (!isLightMode()) {
                    lightRes = darkRes;
                }
                view.setBackgroundResource(lightRes);
            }
        }

        @JvmStatic
        public final void setImageViewDark(@Nullable ImageView view) {
            if (view instanceof ImageView) {
                view.setColorFilter(Color.parseColor(isLightMode() ? "#00000000" : JDDarkUtil.COLOR_33000000));
            }
        }

        @JvmStatic
        public final void setImageViewResource(@Nullable View imageView, int lightRes, int darkRes) {
            if (imageView instanceof ImageView) {
                ImageView imageView2 = (ImageView) imageView;
                if (!isLightMode()) {
                    lightRes = darkRes;
                }
                imageView2.setImageResource(lightRes);
            }
        }

        @JvmStatic
        public final void setMaskVisibility(@Nullable View view) {
            if (view != null) {
                view.setVisibility(isLightMode() ? 8 : 0);
            }
        }

        @JvmStatic
        public final void setSimpleDraweeViewDark(@Nullable SimpleDraweeView view) {
            if (view instanceof SimpleDraweeView) {
                view.setColorFilter(Color.parseColor(isLightMode() ? "#00000000" : JDDarkUtil.COLOR_33000000));
            }
        }

        @JvmStatic
        public final void setTextColor(@Nullable View textView, int lightRes, int darkRes) {
            if (textView instanceof TextView) {
                TextView textView2 = (TextView) textView;
                Context context = textView2.getContext();
                if (!isLightMode()) {
                    lightRes = darkRes;
                }
                textView2.setTextColor(ContextCompat.getColor(context, lightRes));
            }
        }

        @JvmStatic
        public final boolean unregister(@NotNull DeepDarkChangeManager.OnUIModeChangeListener listener) {
            return DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(listener);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void register(@NotNull LifecycleOwner owner, @NotNull Observer<Integer> observer) {
            DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(owner, observer);
        }

        @JvmStatic
        public final void unregister(@NotNull Observer<Integer> observer) {
            DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(observer);
        }

        @JvmStatic
        public final void register(@NotNull LifecycleOwner owner, @NotNull final Function1<? super Integer, Unit> observer) {
            DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(owner, new Observer<Integer>() { // from class: com.jingdong.common.utils.personal.dark.PersonalDarkHelper$Companion$register$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Integer num) {
                    if (num != null) {
                        Function1.this.invoke(Integer.valueOf(num.intValue()));
                    }
                }
            });
        }
    }

    @JvmStatic
    public static final boolean isDarkMode() {
        return INSTANCE.isDarkMode();
    }

    @JvmStatic
    public static final boolean isLightMode() {
        return INSTANCE.isLightMode();
    }

    @JvmStatic
    public static final void register(@NotNull LifecycleOwner lifecycleOwner, @NotNull Observer<Integer> observer) {
        INSTANCE.register(lifecycleOwner, observer);
    }

    @JvmStatic
    public static final void register(@NotNull LifecycleOwner lifecycleOwner, @NotNull Function1<? super Integer, Unit> function1) {
        INSTANCE.register(lifecycleOwner, function1);
    }

    @JvmStatic
    public static final boolean register(@NotNull DeepDarkChangeManager.OnUIModeChangeListener onUIModeChangeListener) {
        return INSTANCE.register(onUIModeChangeListener);
    }

    @JvmStatic
    public static final void setBackgroundResource(@Nullable View view, int i2, int i3) {
        INSTANCE.setBackgroundResource(view, i2, i3);
    }

    @JvmStatic
    public static final void setImageViewDark(@Nullable ImageView imageView) {
        INSTANCE.setImageViewDark(imageView);
    }

    @JvmStatic
    public static final void setImageViewResource(@Nullable View view, int i2, int i3) {
        INSTANCE.setImageViewResource(view, i2, i3);
    }

    @JvmStatic
    public static final void setMaskVisibility(@Nullable View view) {
        INSTANCE.setMaskVisibility(view);
    }

    @JvmStatic
    public static final void setSimpleDraweeViewDark(@Nullable SimpleDraweeView simpleDraweeView) {
        INSTANCE.setSimpleDraweeViewDark(simpleDraweeView);
    }

    @JvmStatic
    public static final void setTextColor(@Nullable View view, int i2, int i3) {
        INSTANCE.setTextColor(view, i2, i3);
    }

    @JvmStatic
    public static final void unregister(@NotNull Observer<Integer> observer) {
        INSTANCE.unregister(observer);
    }

    @JvmStatic
    public static final boolean unregister(@NotNull DeepDarkChangeManager.OnUIModeChangeListener onUIModeChangeListener) {
        return INSTANCE.unregister(onUIModeChangeListener);
    }
}
