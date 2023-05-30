package com.jingdong.sdk.eldermode.helper.impl;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.eldermode.entity.ElderModeDialogInfo;
import com.jingdong.sdk.eldermode.entity.ElderModeResponse;
import com.jingdong.sdk.eldermode.helper.IElderModeDialog;
import com.jingdong.sdk.eldermode.util.JDElderModeDialogMtaUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeManager;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u001f\u0010\u0010J\u0015\u0010\u0004\u001a\u00020\u0003*\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0006\u001a\u00020\u0003*\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0005J-\u0010\r\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0003H\u0004\u00a2\u0006\u0004\b\u000f\u0010\u0010J-\u0010\u0011\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u000eJ!\u0010\u0014\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012H&\u00a2\u0006\u0004\b\u0014\u0010\u0015J!\u0010\u0016\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012H&\u00a2\u0006\u0004\b\u0016\u0010\u0015J\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0017\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u001d\u0010\u001e\u00a8\u0006 "}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/impl/AbstractElderModeDialogImpl;", "Lcom/jingdong/sdk/eldermode/helper/IElderModeDialog;", "Landroid/app/Dialog;", "", "cancelSafely", "(Landroid/app/Dialog;)V", "dismissSafely", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/view/View$OnClickListener;", "onOkButtonClickListener", "onCancelButtonClickListener", "", "showElderModeDialog", "(Landroid/content/Context;Landroid/view/View$OnClickListener;Landroid/view/View$OnClickListener;)Z", "onClickTips", "()V", "showNormalModeDialog", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;", "dialogInfo", "createElderDialog", "(Landroid/content/Context;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;)Landroid/app/Dialog;", "createNormalDialog", XView2Constants.XVIEW2_ACTION_DIALOG, "Landroid/widget/Button;", "getOkButton", "(Landroid/app/Dialog;)Landroid/widget/Button;", "getCancelButton", "Landroid/view/View;", "getCloseView", "(Landroid/app/Dialog;)Landroid/view/View;", "<init>", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public abstract class AbstractElderModeDialogImpl implements IElderModeDialog {
    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelSafely(@Nullable Dialog dialog) {
        if (dialog != null) {
            try {
                dialog.cancel();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dismissSafely(@Nullable Dialog dialog) {
        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (Throwable unused) {
            }
        }
    }

    @Nullable
    public abstract Dialog createElderDialog(@NotNull Context context, @NotNull ElderModeDialogInfo dialogInfo);

    @Nullable
    public abstract Dialog createNormalDialog(@NotNull Context context, @NotNull ElderModeDialogInfo dialogInfo);

    @Nullable
    public abstract Button getCancelButton(@NotNull Dialog dialog);

    @Nullable
    public abstract View getCloseView(@NotNull Dialog dialog);

    @Nullable
    public abstract Button getOkButton(@NotNull Dialog dialog);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onClickTips() {
        JDElderModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDElderModeDialogMtaUtils.EVENT_ID_ELDERPOP_DETAIL);
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDialog
    public boolean showElderModeDialog(@Nullable Context context, @Nullable final View.OnClickListener onOkButtonClickListener, @Nullable final View.OnClickListener onCancelButtonClickListener) {
        ElderModeResponse response;
        ElderModeDialogInfo caringInfo;
        final Dialog createElderDialog;
        if (context == null || (response = JDElderModeManager.INSTANCE.getResponse()) == null || (caringInfo = response.getCaringInfo()) == null || (createElderDialog = createElderDialog(context, caringInfo)) == null) {
            return false;
        }
        createElderDialog.setCanceledOnTouchOutside(false);
        Button okButton = getOkButton(createElderDialog);
        if (okButton != null) {
            okButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showElderModeDialog$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JDElderModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDElderModeDialogMtaUtils.EVENT_ID_ELDERPOP_CHANGE);
                    View.OnClickListener onClickListener = onOkButtonClickListener;
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    }
                    JDBModeUtils.changeToElderMode("1");
                    AbstractElderModeDialogImpl.this.dismissSafely(createElderDialog);
                }
            });
        }
        final Button cancelButton = getCancelButton(createElderDialog);
        if (cancelButton != null) {
            cancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showElderModeDialog$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JDElderModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDElderModeDialogMtaUtils.EVENT_ID_ELDERPOP_NO);
                    AbstractElderModeDialogImpl.this.cancelSafely(createElderDialog);
                }
            });
        }
        View closeView = getCloseView(createElderDialog);
        if (closeView != null) {
            closeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showElderModeDialog$3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JDElderModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDElderModeDialogMtaUtils.EVENT_ID_ELDERPOP_CLOSE);
                    AbstractElderModeDialogImpl.this.cancelSafely(createElderDialog);
                }
            });
        }
        createElderDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showElderModeDialog$4
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                View.OnClickListener onClickListener = onCancelButtonClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(cancelButton);
                }
            }
        });
        createElderDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showElderModeDialog$5
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                JDElderModeDialogMtaUtils.INSTANCE.exposure$eldermodelib(JDElderModeDialogMtaUtils.EVENT_ID_ELDERPOP_EXPO);
                JDElderModeManager.INSTANCE.onShowElderModeDialog();
            }
        });
        createElderDialog.show();
        Window window = createElderDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            return true;
        }
        return true;
    }

    @Override // com.jingdong.sdk.eldermode.helper.IElderModeDialog
    public boolean showNormalModeDialog(@Nullable Context context, @Nullable final View.OnClickListener onOkButtonClickListener, @Nullable final View.OnClickListener onCancelButtonClickListener) {
        ElderModeResponse response;
        ElderModeDialogInfo standardInfo;
        final Dialog createNormalDialog;
        if (context == null || (response = JDElderModeManager.INSTANCE.getResponse()) == null || (standardInfo = response.getStandardInfo()) == null || (createNormalDialog = createNormalDialog(context, standardInfo)) == null) {
            return false;
        }
        createNormalDialog.setCanceledOnTouchOutside(false);
        Button okButton = getOkButton(createNormalDialog);
        if (okButton != null) {
            okButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showNormalModeDialog$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    View.OnClickListener onClickListener = onOkButtonClickListener;
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    }
                    JDBModeUtils.changeToNormalMode("1");
                    AbstractElderModeDialogImpl.this.dismissSafely(createNormalDialog);
                }
            });
        }
        final Button cancelButton = getCancelButton(createNormalDialog);
        if (cancelButton != null) {
            cancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showNormalModeDialog$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AbstractElderModeDialogImpl.this.cancelSafely(createNormalDialog);
                }
            });
        }
        createNormalDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showNormalModeDialog$3
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                View.OnClickListener onClickListener = onCancelButtonClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(cancelButton);
                }
            }
        });
        View closeView = getCloseView(createNormalDialog);
        if (closeView != null) {
            closeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl$showNormalModeDialog$4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AbstractElderModeDialogImpl.this.cancelSafely(createNormalDialog);
                }
            });
        }
        createNormalDialog.show();
        Window window = createNormalDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            return true;
        }
        return true;
    }
}
