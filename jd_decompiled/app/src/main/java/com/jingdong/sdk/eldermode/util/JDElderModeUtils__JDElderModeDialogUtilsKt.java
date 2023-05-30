package com.jingdong.sdk.eldermode.util;

import android.app.Activity;
import android.view.View;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmOverloads;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\r\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\r\u0010\u0003\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0002\u001a1\u0010\t\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0004\b\t\u0010\n\u001a;\u0010\t\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0004\b\t\u0010\r\u001a1\u0010\u000e\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0004\b\u000e\u0010\n\u001a;\u0010\u000e\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0004\b\u000e\u0010\r\u00a8\u0006\u000f"}, d2 = {"", "isNeedShowElderModeDialog", "()Z", "isNeedShowNormalModeDialog", "Landroid/app/Activity;", "activity", "Landroid/view/View$OnClickListener;", "onOkButtonClickListener", "onCancelButtonClickListener", "showElderModeDialog", "(Landroid/app/Activity;Landroid/view/View$OnClickListener;Landroid/view/View$OnClickListener;)Z", "", "source", "(Landroid/app/Activity;Ljava/lang/String;Landroid/view/View$OnClickListener;Landroid/view/View$OnClickListener;)Z", "showNormalModeDialog", "eldermodelib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/sdk/eldermode/util/JDElderModeUtils")
/* loaded from: classes.dex */
public final /* synthetic */ class JDElderModeUtils__JDElderModeDialogUtilsKt {
    public static final boolean isNeedShowElderModeDialog() {
        return JDElderModeManager.INSTANCE.isNeedShowElderModeDialog();
    }

    public static final boolean isNeedShowNormalModeDialog() {
        return JDElderModeManager.INSTANCE.isNeedShowNormalModeDialog();
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showElderModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity) {
        return showElderModeDialog$default(activity, null, null, 6, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showElderModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable View.OnClickListener onClickListener) {
        return showElderModeDialog$default(activity, onClickListener, null, 4, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showElderModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable View.OnClickListener onClickListener, @Nullable View.OnClickListener onClickListener2) {
        return JDElderModeUtils.showElderModeDialog(activity, null, onClickListener, onClickListener2);
    }

    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable String str) {
        return showElderModeDialog$default(activity, str, null, null, 12, null);
    }

    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable String str, @Nullable View.OnClickListener onClickListener) {
        return showElderModeDialog$default(activity, str, onClickListener, null, 8, null);
    }

    public static /* synthetic */ boolean showElderModeDialog$default(Activity activity, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            onClickListener = null;
        }
        if ((i2 & 4) != 0) {
            onClickListener2 = null;
        }
        return JDElderModeUtils.showElderModeDialog(activity, onClickListener, onClickListener2);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showNormalModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity) {
        return showNormalModeDialog$default(activity, null, null, 6, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showNormalModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable View.OnClickListener onClickListener) {
        return showNormalModeDialog$default(activity, onClickListener, null, 4, null);
    }

    @Deprecated(message = "\u9700\u8981\u66ff\u6362\u4e3a\u4f7f\u7528 source \u53c2\u6570\u7684\u65b9\u6cd5", replaceWith = @ReplaceWith(expression = "showNormalModeDialog(activity, source, onOkButtonClickListener, onCancelButtonClickListener)", imports = {}))
    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable View.OnClickListener onClickListener, @Nullable View.OnClickListener onClickListener2) {
        return JDElderModeUtils.showNormalModeDialog(activity, null, onClickListener, onClickListener2);
    }

    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable String str) {
        return showNormalModeDialog$default(activity, str, null, null, 12, null);
    }

    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable String str, @Nullable View.OnClickListener onClickListener) {
        return showNormalModeDialog$default(activity, str, onClickListener, null, 8, null);
    }

    public static /* synthetic */ boolean showNormalModeDialog$default(Activity activity, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            onClickListener = null;
        }
        if ((i2 & 4) != 0) {
            onClickListener2 = null;
        }
        return JDElderModeUtils.showNormalModeDialog(activity, onClickListener, onClickListener2);
    }

    @JvmOverloads
    public static final boolean showElderModeDialog(@Nullable Activity activity, @Nullable String str, @Nullable View.OnClickListener onClickListener, @Nullable View.OnClickListener onClickListener2) {
        return JDElderModeManager.INSTANCE.showElderModeDialog(activity, str, onClickListener, onClickListener2);
    }

    public static /* synthetic */ boolean showElderModeDialog$default(Activity activity, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            onClickListener = null;
        }
        if ((i2 & 8) != 0) {
            onClickListener2 = null;
        }
        return JDElderModeUtils.showElderModeDialog(activity, str, onClickListener, onClickListener2);
    }

    @JvmOverloads
    public static final boolean showNormalModeDialog(@Nullable Activity activity, @Nullable String str, @Nullable View.OnClickListener onClickListener, @Nullable View.OnClickListener onClickListener2) {
        return JDElderModeManager.INSTANCE.showNormalModeDialog(activity, str, onClickListener, onClickListener2);
    }

    public static /* synthetic */ boolean showNormalModeDialog$default(Activity activity, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            onClickListener = null;
        }
        if ((i2 & 8) != 0) {
            onClickListener2 = null;
        }
        return JDElderModeUtils.showNormalModeDialog(activity, str, onClickListener, onClickListener2);
    }
}
