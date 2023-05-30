package com.jingdong.common.eldermode;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.R;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.eldermode.entity.ElderModeDialogInfo;
import com.jingdong.sdk.eldermode.entity.ElderModeDialogJumpInfo;
import com.jingdong.sdk.eldermode.entity.ElderModeDialogPopupRemarkInfo;
import com.jingdong.sdk.eldermode.entity.ElderModeDialogSubTitleInfo;
import com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl;
import com.jingdong.sdk.platform.business.utils.JumpHelper;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes5.dex */
public class JDElderModeDialogImpl extends AbstractElderModeDialogImpl {
    private void bindItem(View view, ElderModeDialogSubTitleInfo elderModeDialogSubTitleInfo) {
        ImageView imageView;
        String smallIcon = elderModeDialogSubTitleInfo.getSmallIcon();
        if (!TextUtils.isEmpty(smallIcon) && (imageView = (ImageView) view.findViewById(R.id.iv_icon)) != null) {
            JDImageUtils.displayImage(smallIcon, imageView);
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_sub_title);
        if (textView != null) {
            textView.setText(elderModeDialogSubTitleInfo.getContent());
        }
    }

    private void initCommonViews(Dialog dialog, ElderModeDialogInfo elderModeDialogInfo) {
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(elderModeDialogInfo.getPopupTitle());
        }
        Button okButton = getOkButton(dialog);
        if (okButton != null) {
            okButton.setText(elderModeDialogInfo.getSureButtonContent());
        }
        Button cancelButton = getCancelButton(dialog);
        if (cancelButton != null) {
            cancelButton.setText(elderModeDialogInfo.getCancelButtonContent());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jump(final Context context, final ElderModeDialogJumpInfo elderModeDialogJumpInfo) {
        if (elderModeDialogJumpInfo != null) {
            if (elderModeDialogJumpInfo.getNeedLogin() == 1 && (context instanceof IMyActivity) && !LoginUserBase.hasLogin()) {
                LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new Runnable() { // from class: com.jingdong.common.eldermode.JDElderModeDialogImpl.2
                    @Override // java.lang.Runnable
                    public void run() {
                        JumpHelper.jump(context, elderModeDialogJumpInfo.getJumpUrl(), elderModeDialogJumpInfo.getJumpType());
                    }
                });
            } else {
                JumpHelper.jump(context, elderModeDialogJumpInfo.getJumpUrl(), elderModeDialogJumpInfo.getJumpType());
            }
        }
    }

    @Override // com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl
    @Nullable
    public Dialog createElderDialog(@NotNull final Context context, @NotNull ElderModeDialogInfo elderModeDialogInfo) {
        JDDialog jDDialog = new JDDialog(context);
        jDDialog.setContentView(R.layout.elder_mode_dialog_elder_mode);
        jDDialog.setIsElder(false);
        ImageView imageView = (ImageView) jDDialog.findViewById(R.id.iv_logo);
        if (imageView != null) {
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            jDDisplayImageOptions.setPlaceholder(17);
            JDImageUtils.displayImage(elderModeDialogInfo.getPopupIcon(), imageView, jDDisplayImageOptions, true);
        }
        initCommonViews(jDDialog, elderModeDialogInfo);
        List<ElderModeDialogSubTitleInfo> popupSubTitle = elderModeDialogInfo.getPopupSubTitle();
        if (popupSubTitle != null) {
            LinearLayout linearLayout = (LinearLayout) jDDialog.findViewById(R.id.ll_sub_title);
            linearLayout.removeAllViews();
            Iterator<ElderModeDialogSubTitleInfo> it = popupSubTitle.iterator();
            while (it.hasNext()) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.elder_mode_dialog_elder_mode_item, (ViewGroup) linearLayout, false);
                bindItem(inflate, it.next());
                linearLayout.addView(inflate);
            }
        }
        LinearLayout linearLayout2 = (LinearLayout) jDDialog.findViewById(R.id.ll_tips);
        ElderModeDialogPopupRemarkInfo popupRemark = elderModeDialogInfo.getPopupRemark();
        if (popupRemark != null) {
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(0);
            }
            TextView textView = (TextView) jDDialog.findViewById(R.id.tv_tips);
            TextView textView2 = (TextView) jDDialog.findViewById(R.id.tv_tips_more);
            if (textView != null) {
                textView.setText(popupRemark.getTitle());
            }
            if (textView2 != null) {
                String jumpTitle = popupRemark.getJumpTitle();
                if (TextUtils.isEmpty(jumpTitle)) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setVisibility(0);
                    textView2.setText(jumpTitle);
                    popupRemark.getJumpInfo();
                    textView2.setOnClickListener(new View.OnClickListener
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00b2: INVOKE 
                          (r4v2 'textView2' android.widget.TextView)
                          (wrap: android.view.View$OnClickListener : 0x00af: CONSTRUCTOR 
                          (r7v0 'this' com.jingdong.common.eldermode.JDElderModeDialogImpl A[IMMUTABLE_TYPE, THIS])
                          (r8v0 'context' android.content.Context A[DONT_INLINE])
                          (r9 I:com.jingdong.sdk.eldermode.entity.ElderModeDialogJumpInfo A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[MD:(com.jingdong.common.eldermode.JDElderModeDialogImpl, android.content.Context, com.jingdong.sdk.eldermode.entity.ElderModeDialogJumpInfo):void (m), WRAPPED] (LINE:29) call: com.jingdong.common.eldermode.JDElderModeDialogImpl.1.<init>(com.jingdong.common.eldermode.JDElderModeDialogImpl, android.content.Context, com.jingdong.sdk.eldermode.entity.ElderModeDialogJumpInfo):void type: CONSTRUCTOR)
                         type: VIRTUAL call: android.widget.TextView.setOnClickListener(android.view.View$OnClickListener):void A[MD:(android.view.View$OnClickListener):void (c)] (LINE:29) in method: com.jingdong.common.eldermode.JDElderModeDialogImpl.createElderDialog(android.content.Context, com.jingdong.sdk.eldermode.entity.ElderModeDialogInfo):android.app.Dialog, file: classes5.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        Caused by: java.lang.NullPointerException
                        */
                    /*
                        this = this;
                        com.jingdong.common.ui.JDDialog r0 = new com.jingdong.common.ui.JDDialog
                        r0.<init>(r8)
                        int r1 = com.jingdong.common.R.layout.elder_mode_dialog_elder_mode
                        r0.setContentView(r1)
                        r1 = 0
                        r0.setIsElder(r1)
                        int r2 = com.jingdong.common.R.id.iv_logo
                        android.view.View r2 = r0.findViewById(r2)
                        android.widget.ImageView r2 = (android.widget.ImageView) r2
                        if (r2 == 0) goto L2a
                        com.jingdong.app.util.image.JDDisplayImageOptions r3 = new com.jingdong.app.util.image.JDDisplayImageOptions
                        r3.<init>()
                        r4 = 17
                        r3.setPlaceholder(r4)
                        java.lang.String r4 = r9.getPopupIcon()
                        r5 = 1
                        com.jingdong.common.utils.JDImageUtils.displayImage(r4, r2, r3, r5)
                    L2a:
                        r7.initCommonViews(r0, r9)
                        java.util.List r2 = r9.getPopupSubTitle()
                        if (r2 == 0) goto L5f
                        int r3 = com.jingdong.common.R.id.ll_sub_title
                        android.view.View r3 = r0.findViewById(r3)
                        android.widget.LinearLayout r3 = (android.widget.LinearLayout) r3
                        r3.removeAllViews()
                        java.util.Iterator r2 = r2.iterator()
                    L42:
                        boolean r4 = r2.hasNext()
                        if (r4 == 0) goto L5f
                        java.lang.Object r4 = r2.next()
                        com.jingdong.sdk.eldermode.entity.ElderModeDialogSubTitleInfo r4 = (com.jingdong.sdk.eldermode.entity.ElderModeDialogSubTitleInfo) r4
                        android.view.LayoutInflater r5 = android.view.LayoutInflater.from(r8)
                        int r6 = com.jingdong.common.R.layout.elder_mode_dialog_elder_mode_item
                        android.view.View r5 = r5.inflate(r6, r3, r1)
                        r7.bindItem(r5, r4)
                        r3.addView(r5)
                        goto L42
                    L5f:
                        int r2 = com.jingdong.common.R.id.ll_tips
                        android.view.View r2 = r0.findViewById(r2)
                        android.widget.LinearLayout r2 = (android.widget.LinearLayout) r2
                        com.jingdong.sdk.eldermode.entity.ElderModeDialogPopupRemarkInfo r9 = r9.getPopupRemark()
                        r3 = 8
                        if (r9 != 0) goto L75
                        if (r2 == 0) goto Lb5
                        r2.setVisibility(r3)
                        goto Lb5
                    L75:
                        if (r2 == 0) goto L7a
                        r2.setVisibility(r1)
                    L7a:
                        int r2 = com.jingdong.common.R.id.tv_tips
                        android.view.View r2 = r0.findViewById(r2)
                        android.widget.TextView r2 = (android.widget.TextView) r2
                        int r4 = com.jingdong.common.R.id.tv_tips_more
                        android.view.View r4 = r0.findViewById(r4)
                        android.widget.TextView r4 = (android.widget.TextView) r4
                        if (r2 == 0) goto L93
                        java.lang.String r5 = r9.getTitle()
                        r2.setText(r5)
                    L93:
                        if (r4 == 0) goto Lb5
                        java.lang.String r2 = r9.getJumpTitle()
                        boolean r5 = android.text.TextUtils.isEmpty(r2)
                        if (r5 == 0) goto La3
                        r4.setVisibility(r3)
                        goto Lb5
                    La3:
                        r4.setVisibility(r1)
                        r4.setText(r2)
                        com.jingdong.sdk.eldermode.entity.ElderModeDialogJumpInfo r9 = r9.getJumpInfo()
                        com.jingdong.common.eldermode.JDElderModeDialogImpl$1 r1 = new com.jingdong.common.eldermode.JDElderModeDialogImpl$1
                        r1.<init>()
                        r4.setOnClickListener(r1)
                    Lb5:
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.eldermode.JDElderModeDialogImpl.createElderDialog(android.content.Context, com.jingdong.sdk.eldermode.entity.ElderModeDialogInfo):android.app.Dialog");
                }

                @Override // com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl
                @Nullable
                public Dialog createNormalDialog(@NotNull Context context, @NotNull ElderModeDialogInfo elderModeDialogInfo) {
                    JDDialog jDDialog = new JDDialog(context);
                    jDDialog.setContentView(R.layout.elder_mode_dialog_normal_mode);
                    jDDialog.setIsElder(false);
                    initCommonViews(jDDialog, elderModeDialogInfo);
                    return jDDialog;
                }

                @Override // com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl
                @Nullable
                public Button getCancelButton(@NotNull Dialog dialog) {
                    return (Button) dialog.findViewById(R.id.btn_cancel);
                }

                @Override // com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl
                @Nullable
                public View getCloseView(@NotNull Dialog dialog) {
                    return dialog.findViewById(R.id.iv_close);
                }

                @Override // com.jingdong.sdk.eldermode.helper.impl.AbstractElderModeDialogImpl
                @Nullable
                public Button getOkButton(@NotNull Dialog dialog) {
                    return (Button) dialog.findViewById(R.id.btn_ok);
                }
            }
