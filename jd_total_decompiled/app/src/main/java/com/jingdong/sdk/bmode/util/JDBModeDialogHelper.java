package com.jingdong.sdk.bmode.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.generic.RoundingParams;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.eldermode.R;
import com.jingdong.sdk.eldermode.entity.BModeDialogInfo;
import com.jingdong.sdk.eldermode.entity.BModeSlimUserData;
import com.jingdong.sdk.eldermode.entity.ElderModeDialogSubTitleInfo;
import com.jingdong.sdk.eldermode.entity.ElderModeResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\r*\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\r*\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0017"}, d2 = {"Lcom/jingdong/sdk/bmode/util/JDBModeDialogHelper;", "", "Landroid/app/Activity;", "activity", "Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;", "slimStandardInfo", "Landroid/app/Dialog;", "createDialog", "(Landroid/app/Activity;Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;)Landroid/app/Dialog;", "Landroid/view/View;", CartConstant.KEY_VENDOR_ITEM, "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogSubTitleInfo;", "subTitleInfo", "", "bindItem", "(Landroid/view/View;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogSubTitleInfo;)V", "cancelSafely", "(Landroid/app/Dialog;)V", "dismissSafely", "showDialog", "(Landroid/app/Activity;)V", "<init>", "()V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class JDBModeDialogHelper {
    private final void bindItem(View item, ElderModeDialogSubTitleInfo subTitleInfo) {
        String smallIcon = subTitleInfo.getSmallIcon();
        if (!TextUtils.isEmpty(smallIcon)) {
            View findViewById = item.findViewById(R.id.iv_icon);
            if (findViewById == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
            }
            ImageView imageView = (ImageView) findViewById;
            if (imageView != null) {
                JDImageUtils.displayImage(smallIcon, imageView);
            }
        }
        View findViewById2 = item.findViewById(R.id.tv_sub_title);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView textView = (TextView) findViewById2;
        if (textView != null) {
            textView.setText(subTitleInfo.getContent());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelSafely(@Nullable Dialog dialog) {
        if (dialog != null) {
            try {
                dialog.cancel();
            } catch (Throwable unused) {
            }
        }
    }

    private final Dialog createDialog(Activity activity, final BModeDialogInfo slimStandardInfo) {
        final JDDialog jDDialog = new JDDialog(activity);
        jDDialog.setContentView(R.layout.b_mode_dialog_normal_mode);
        jDDialog.setIsElder(false);
        jDDialog.setCanceledOnTouchOutside(false);
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setPlaceholder(17);
        jDDisplayImageOptions.setRoundingParams(RoundingParams.fromCornersRadii(24.0f, 24.0f, 0.0f, 0.0f));
        JDImageUtils.displayImage(slimStandardInfo.getPopupIcon(), (ImageView) jDDialog.findViewById(R.id.iv_logo), jDDisplayImageOptions, true);
        View findViewById = jDDialog.findViewById(R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "dialog.findViewById<TextView>(R.id.tv_title)");
        ((TextView) findViewById).setText(slimStandardInfo.getPopupTitle());
        List<ElderModeDialogSubTitleInfo> popupSubTitle = slimStandardInfo.getPopupSubTitle();
        if (popupSubTitle != null) {
            LinearLayout linearLayout = (LinearLayout) jDDialog.findViewById(R.id.ll_sub_title);
            linearLayout.removeAllViews();
            if (popupSubTitle.size() >= 3) {
                int i2 = 0;
                for (Object obj : popupSubTitle) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    ElderModeDialogSubTitleInfo elderModeDialogSubTitleInfo = (ElderModeDialogSubTitleInfo) obj;
                    if (i2 <= 2) {
                        View item = LayoutInflater.from(activity).inflate(R.layout.b_mode_dialog_normal_mode_item, (ViewGroup) linearLayout, false);
                        Intrinsics.checkExpressionValueIsNotNull(item, "item");
                        bindItem(item, elderModeDialogSubTitleInfo);
                        linearLayout.addView(item);
                    }
                    i2 = i3;
                }
            }
        }
        View findViewById2 = jDDialog.findViewById(R.id.tv_tips);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "dialog.findViewById(R.id.tv_tips)");
        TextView textView = (TextView) findViewById2;
        String popupAdviceContent = slimStandardInfo.getPopupAdviceContent();
        if (popupAdviceContent != null) {
            if (!(popupAdviceContent.length() > 0)) {
                popupAdviceContent = null;
            }
            if (popupAdviceContent != null) {
                textView.setVisibility(0);
                textView.setText(popupAdviceContent);
                Button button = (Button) jDDialog.findViewById(R.id.btn_ok);
                button.setText(slimStandardInfo.getSureButtonContent());
                button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.bmode.util.JDBModeDialogHelper$createDialog$$inlined$apply$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        try {
                            JDBModeUtils.changeToNormalMode("0");
                            JDBModeManager.INSTANCE.setBModeDidClickConfirmButtonBroadcast();
                            JDBModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDBModeDialogMtaUtils.EVENT_ID_APP_POPUPUPGRADETOA);
                            JDBModeDialogHelper.this.dismissSafely(jDDialog);
                        } catch (Throwable unused) {
                        }
                    }
                });
                TextView textView2 = (TextView) jDDialog.findViewById(R.id.btn_cancel);
                textView2.setText(slimStandardInfo.getCancelButtonContent());
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.bmode.util.JDBModeDialogHelper$createDialog$$inlined$apply$lambda$2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        try {
                            JDBModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDBModeDialogMtaUtils.EVENT_ID_APP_POPUPUPGRADENOTTOA);
                            JDBModeDialogHelper.this.cancelSafely(jDDialog);
                        } catch (Throwable unused) {
                        }
                    }
                });
                jDDialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.bmode.util.JDBModeDialogHelper$createDialog$7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        try {
                            JDBModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDBModeDialogMtaUtils.EVENT_ID_APP_POPUPUPGRADETOACLOSE);
                            JDBModeDialogHelper.this.cancelSafely(jDDialog);
                        } catch (Throwable unused) {
                        }
                    }
                });
                return jDDialog;
            }
        }
        textView.setVisibility(8);
        Button button2 = (Button) jDDialog.findViewById(R.id.btn_ok);
        button2.setText(slimStandardInfo.getSureButtonContent());
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.bmode.util.JDBModeDialogHelper$createDialog$$inlined$apply$lambda$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    JDBModeUtils.changeToNormalMode("0");
                    JDBModeManager.INSTANCE.setBModeDidClickConfirmButtonBroadcast();
                    JDBModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDBModeDialogMtaUtils.EVENT_ID_APP_POPUPUPGRADETOA);
                    JDBModeDialogHelper.this.dismissSafely(jDDialog);
                } catch (Throwable unused) {
                }
            }
        });
        TextView textView22 = (TextView) jDDialog.findViewById(R.id.btn_cancel);
        textView22.setText(slimStandardInfo.getCancelButtonContent());
        textView22.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.bmode.util.JDBModeDialogHelper$createDialog$$inlined$apply$lambda$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    JDBModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDBModeDialogMtaUtils.EVENT_ID_APP_POPUPUPGRADENOTTOA);
                    JDBModeDialogHelper.this.cancelSafely(jDDialog);
                } catch (Throwable unused) {
                }
            }
        });
        jDDialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.bmode.util.JDBModeDialogHelper$createDialog$7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    JDBModeDialogMtaUtils.INSTANCE.click$eldermodelib(JDBModeDialogMtaUtils.EVENT_ID_APP_POPUPUPGRADETOACLOSE);
                    JDBModeDialogHelper.this.cancelSafely(jDDialog);
                } catch (Throwable unused) {
                }
            }
        });
        return jDDialog;
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

    public final void showDialog(@Nullable Activity activity) {
        BModeSlimUserData slimUserData;
        if (activity != null) {
            ElderModeResponse response = JDBModeManager.INSTANCE.getResponse();
            BModeDialogInfo slimStandardInfo = (response == null || (slimUserData = response.getSlimUserData()) == null) ? null : slimUserData.getSlimStandardInfo();
            if (slimStandardInfo != null) {
                Dialog createDialog = createDialog(activity, slimStandardInfo);
                createDialog.show();
                Window window = createDialog.getWindow();
                if (window != null) {
                    window.setBackgroundDrawable(new ColorDrawable(0));
                }
                JDBModeDialogMtaUtils.INSTANCE.exposure$eldermodelib(JDBModeDialogMtaUtils.EVENT_ID_APP_POPUPUPGRADETOAEXPO);
            }
        }
    }
}
