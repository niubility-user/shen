package com.jingdong.common.utils.personal.dark;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0005"}, d2 = {"Lcom/jingdong/common/utils/personal/dark/PersonalDarkColors;", "", "<init>", "()V", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalDarkColors {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u0006\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\b\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u0019\u0010\u000b\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u000b\u0010\tJ\u0019\u0010\f\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\f\u0010\tJ\u0019\u0010\r\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\r\u0010\tJ\u0019\u0010\u000e\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u000e\u0010\tJ\u0019\u0010\u000f\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u000f\u0010\tJ\u0019\u0010\u0010\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0010\u0010\tJ\u0019\u0010\u0011\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0011\u0010\tJ\u0019\u0010\u0012\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0012\u0010\tJ\u0019\u0010\u0013\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0013\u0010\tJ\u0019\u0010\u0014\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0014\u0010\tJ\u0019\u0010\u0015\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0015\u0010\tJ\u0019\u0010\u0016\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0016\u0010\t\u00a8\u0006\u0019"}, d2 = {"Lcom/jingdong/common/utils/personal/dark/PersonalDarkColors$Companion;", "", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "resId", "getColor", "(Landroid/content/Context;Ljava/lang/Integer;)I", "darkBr", "(Landroid/content/Context;)I", "darkB1", "darkB2", "darkB3", "darkC1", "darkC2", "darkC3", "lightBr", "lightB1", "lightB2", "lightB3", "lightC1", "lightC2", "lightC3", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        private final int getColor(Context context, Integer resId) {
            if (context == null || resId == null) {
                return 0;
            }
            resId.intValue();
            try {
                return ContextCompat.getColor(context, resId.intValue());
            } catch (Exception unused) {
                return 0;
            }
        }

        @JvmStatic
        public final int darkB1(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.DARK_B1));
        }

        @JvmStatic
        public final int darkB2(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.DARK_B2));
        }

        @JvmStatic
        public final int darkB3(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.DARK_B3));
        }

        @JvmStatic
        public final int darkBr(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.DARK_BR));
        }

        @JvmStatic
        public final int darkC1(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.DARK_C1));
        }

        @JvmStatic
        public final int darkC2(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.DARK_C2));
        }

        @JvmStatic
        public final int darkC3(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.DARK_C3));
        }

        @JvmStatic
        public final int lightB1(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.LIGHT_B1));
        }

        @JvmStatic
        public final int lightB2(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.LIGHT_B2));
        }

        @JvmStatic
        public final int lightB3(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.LIGHT_B3));
        }

        @JvmStatic
        public final int lightBr(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.LIGHT_BR));
        }

        @JvmStatic
        public final int lightC1(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.LIGHT_C1));
        }

        @JvmStatic
        public final int lightC2(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.LIGHT_C2));
        }

        @JvmStatic
        public final int lightC3(@Nullable Context context) {
            return getColor(context, Integer.valueOf(PersonalDarkColorResourceIds.LIGHT_C3));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmStatic
    public static final int darkB1(@Nullable Context context) {
        return INSTANCE.darkB1(context);
    }

    @JvmStatic
    public static final int darkB2(@Nullable Context context) {
        return INSTANCE.darkB2(context);
    }

    @JvmStatic
    public static final int darkB3(@Nullable Context context) {
        return INSTANCE.darkB3(context);
    }

    @JvmStatic
    public static final int darkBr(@Nullable Context context) {
        return INSTANCE.darkBr(context);
    }

    @JvmStatic
    public static final int darkC1(@Nullable Context context) {
        return INSTANCE.darkC1(context);
    }

    @JvmStatic
    public static final int darkC2(@Nullable Context context) {
        return INSTANCE.darkC2(context);
    }

    @JvmStatic
    public static final int darkC3(@Nullable Context context) {
        return INSTANCE.darkC3(context);
    }

    @JvmStatic
    public static final int lightB1(@Nullable Context context) {
        return INSTANCE.lightB1(context);
    }

    @JvmStatic
    public static final int lightB2(@Nullable Context context) {
        return INSTANCE.lightB2(context);
    }

    @JvmStatic
    public static final int lightB3(@Nullable Context context) {
        return INSTANCE.lightB3(context);
    }

    @JvmStatic
    public static final int lightBr(@Nullable Context context) {
        return INSTANCE.lightBr(context);
    }

    @JvmStatic
    public static final int lightC1(@Nullable Context context) {
        return INSTANCE.lightC1(context);
    }

    @JvmStatic
    public static final int lightC2(@Nullable Context context) {
        return INSTANCE.lightC2(context);
    }

    @JvmStatic
    public static final int lightC3(@Nullable Context context) {
        return INSTANCE.lightC3(context);
    }
}
