package com.jingdong.sdk.eldermode.helper;

import android.content.Context;
import android.view.View;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J-\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\b\u0010\tJ-\u0010\n\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\n\u0010\t\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;", "", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/view/View$OnClickListener;", "onOkButtonClickListener", "onCancelButtonClickListener", "", "showElderModeDialog", "(Landroid/content/Context;Landroid/view/View$OnClickListener;Landroid/view/View$OnClickListener;)Z", "showNormalModeDialog", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface IElderModeDialog {
    boolean showElderModeDialog(@Nullable Context context, @Nullable View.OnClickListener onOkButtonClickListener, @Nullable View.OnClickListener onCancelButtonClickListener);

    boolean showNormalModeDialog(@Nullable Context context, @Nullable View.OnClickListener onOkButtonClickListener, @Nullable View.OnClickListener onCancelButtonClickListener);
}
