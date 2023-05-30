package com.jingdong.app.mall.dynamicImpl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IContainerCallback;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.NewDynamicFetcher;
import com.jd.dynamic.base.interfaces.IAppRouter;
import com.jd.dynamic.lib.error.DynamicException;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jingdong.app.mall.dynamicImpl.n;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.ui.JDBottomDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.unification.uniwidget.UnErrorPageView;

/* loaded from: classes3.dex */
public class n {

    /* loaded from: classes3.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f8395g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ IAppRouter.DialogConfig f8396h;

        a(JDDialog jDDialog, IAppRouter.DialogConfig dialogConfig) {
            this.f8395g = jDDialog;
            this.f8396h = dialogConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f8395g.dismiss();
            View.OnClickListener onClickListener = this.f8396h.onCancelClick;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes3.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f8397g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ IAppRouter.DialogConfig f8398h;

        b(JDDialog jDDialog, IAppRouter.DialogConfig dialogConfig) {
            this.f8397g = jDDialog;
            this.f8398h = dialogConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f8397g.dismiss();
            View.OnClickListener onClickListener = this.f8398h.onConfirmClick;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes3.dex */
    class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDBottomDialog f8399g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ IAppRouter.PopViewConfig f8400h;

        c(JDBottomDialog jDBottomDialog, IAppRouter.PopViewConfig popViewConfig) {
            this.f8399g = jDBottomDialog;
            this.f8400h = popViewConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f8399g.dismiss();
            View.OnClickListener onClickListener = this.f8400h.bottomClick;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class d implements NewDynamicFetcher.GlobalConfigListener {
        final /* synthetic */ DynamicContainer a;
        final /* synthetic */ FrameLayout b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ boolean f8401c;
        final /* synthetic */ Context d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f8402e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f8403f;

        /* loaded from: classes3.dex */
        class a implements IContainerCallback {
            a() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ void a(FrameLayout frameLayout, DynamicContainer dynamicContainer) {
                frameLayout.removeAllViews();
                frameLayout.addView(dynamicContainer);
            }

            @Override // com.jd.dynamic.apis.IContainerCallback
            public void onError(@NonNull DynamicException dynamicException) {
                d dVar = d.this;
                n.e(dVar.f8401c, dVar.b, dVar.d, dVar.a, dVar.f8402e, dVar.f8403f);
                d dVar2 = d.this;
                DynamicSdk.handException(dVar2.f8401c ? XView2Constants.XVIEW2_ACTION_DIALOG : "popView", "load template error", dVar2.f8403f, dVar2.f8402e, dynamicException);
            }

            @Override // com.jd.dynamic.apis.IContainerCallback
            public void onSuccess() {
                d dVar = d.this;
                final FrameLayout frameLayout = dVar.b;
                final DynamicContainer dynamicContainer = dVar.a;
                frameLayout.post(new Runnable() { // from class: com.jingdong.app.mall.dynamicImpl.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        n.d.a.a(frameLayout, dynamicContainer);
                    }
                });
            }
        }

        d(DynamicContainer dynamicContainer, FrameLayout frameLayout, boolean z, Context context, String str, String str2) {
            this.a = dynamicContainer;
            this.b = frameLayout;
            this.f8401c = z;
            this.d = context;
            this.f8402e = str;
            this.f8403f = str2;
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onEnd(boolean z) {
            this.a.load(new a());
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onError(Exception exc) {
            n.e(this.f8401c, this.b, this.d, this.a, this.f8402e, this.f8403f);
            DynamicSdk.handException(this.f8401c ? XView2Constants.XVIEW2_ACTION_DIALOG : "popView", "load template error", this.f8403f, this.f8402e, exc);
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        public void onStart() {
        }
    }

    private static JDDialog b(Context context, IAppRouter.DialogConfig dialogConfig) {
        DynamicContainer d2 = d(context, dialogConfig);
        if (d2 != null) {
            FrameLayout frameLayout = new FrameLayout(context);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            frameLayout.setLayoutParams(layoutParams);
            JDDialog createJdDialogWithStyle14 = JDDialogFactory.getInstance().createJdDialogWithStyle14(context, dialogConfig.titleText, dialogConfig.contentText, frameLayout, dialogConfig.cancelText, dialogConfig.confirmText, false, false);
            d2.setDialog(createJdDialogWithStyle14);
            i(context, true, frameLayout, d2, dialogConfig.systemCode, dialogConfig.bizField);
            return createJdDialogWithStyle14;
        }
        return null;
    }

    private static JDBottomDialog c(Context context, IAppRouter.PopViewConfig popViewConfig) {
        DynamicContainer d2 = d(context, popViewConfig);
        if (d2 != null) {
            FrameLayout frameLayout = new FrameLayout(context);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            frameLayout.setLayoutParams(layoutParams);
            JDBottomDialog jDBottomDialog = new JDBottomDialog(context);
            d2.setDialog(jDBottomDialog);
            if (TextUtils.isEmpty(popViewConfig.titleText)) {
                jDBottomDialog.addContentWithHeight(frameLayout, popViewConfig.bottom, popViewConfig.heightPercent, true);
            } else {
                jDBottomDialog.addContentWithTitleAndHeight(popViewConfig.titleText, frameLayout, popViewConfig.bottom, false, popViewConfig.heightPercent, true);
            }
            if (context instanceof Activity) {
                jDBottomDialog.setDialogHeightPx((int) (DPIUtil.getAppHeight((Activity) context) * popViewConfig.heightPercent));
            }
            i(context, false, frameLayout, d2, popViewConfig.systemCode, popViewConfig.bizField);
            return jDBottomDialog;
        }
        return null;
    }

    private static DynamicContainer d(Context context, IAppRouter.DialogBaseConfig dialogBaseConfig) {
        IDynamicDriver driver = DynamicSdk.getDriver();
        DYContainerConfig dYContainerConfig = new DYContainerConfig(context, dialogBaseConfig.systemCode, dialogBaseConfig.bizField, dialogBaseConfig.functionFactory);
        int i2 = dialogBaseConfig.containerWidth;
        if (i2 >= 0) {
            dYContainerConfig.setContainerWidth(i2);
        }
        int i3 = dialogBaseConfig.containerHeight;
        if (i3 >= 0) {
            dYContainerConfig.setContainerHeight(i3);
        }
        DynamicContainer createContainer = driver.createContainer(dYContainerConfig);
        if (createContainer != null) {
            createContainer.setData(dialogBaseConfig.businessData);
            return createContainer;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final boolean z, final FrameLayout frameLayout, final Context context, final DynamicContainer dynamicContainer, final String str, final String str2) {
        frameLayout.post(new Runnable() { // from class: com.jingdong.app.mall.dynamicImpl.b
            @Override // java.lang.Runnable
            public final void run() {
                n.h(frameLayout, context, z, dynamicContainer, str, str2);
            }
        });
    }

    private static boolean f(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void h(final FrameLayout frameLayout, final Context context, final boolean z, final DynamicContainer dynamicContainer, final String str, final String str2) {
        frameLayout.removeAllViews();
        UnErrorPageView unErrorPageView = new UnErrorPageView(context);
        unErrorPageView.setAutoDarkMode(true);
        unErrorPageView.setAutoElderMode(true);
        unErrorPageView.setTipText("\u6a21\u7248\u52a0\u8f7d\u5931\u8d25", "\u70b9\u51fb\u53ef\u91cd\u65b0\u52a0\u8f7d");
        unErrorPageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.dynamicImpl.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                n.i(context, z, frameLayout, dynamicContainer, str, str2);
            }
        });
        frameLayout.addView(unErrorPageView, new FrameLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(Context context, boolean z, FrameLayout frameLayout, DynamicContainer dynamicContainer, String str, String str2) {
        if (dynamicContainer.load()) {
            frameLayout.removeAllViews();
            frameLayout.addView(dynamicContainer);
            return;
        }
        View jDProgressBar = new JDProgressBar(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        frameLayout.addView(jDProgressBar, layoutParams);
        DynamicSdk.getEngine().newFetchTemplates(new d(dynamicContainer, frameLayout, z, context, str, str2), false, str);
    }

    public static Dialog j(Context context, IAppRouter.DialogConfig dialogConfig) {
        JDDialog createJdDialogWithStyle6;
        try {
            if (f(dialogConfig.systemCode, dialogConfig.bizField)) {
                createJdDialogWithStyle6 = b(context, dialogConfig);
            } else {
                createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(context, dialogConfig.titleText, dialogConfig.contentText, dialogConfig.cancelText, dialogConfig.confirmText);
            }
            if (createJdDialogWithStyle6 != null) {
                createJdDialogWithStyle6.setCancelable(dialogConfig.canCanceledOnTouchOutside);
                createJdDialogWithStyle6.setCanceledOnTouchOutside(dialogConfig.canCanceledOnTouchOutside);
                createJdDialogWithStyle6.setMessagePosition(17);
                createJdDialogWithStyle6.setOnLeftButtonClickListener(new a(createJdDialogWithStyle6, dialogConfig));
                createJdDialogWithStyle6.setOnRightButtonClickListener(new b(createJdDialogWithStyle6, dialogConfig));
                createJdDialogWithStyle6.show();
                return createJdDialogWithStyle6;
            }
            return null;
        } catch (Exception e2) {
            DynamicSdk.handException(XView2Constants.XVIEW2_ACTION_DIALOG, "open dialog error", dialogConfig.bizField, dialogConfig.systemCode, e2);
            return null;
        }
    }

    public static Dialog k(Context context, IAppRouter.PopViewConfig popViewConfig) {
        try {
            if (f(popViewConfig.systemCode, popViewConfig.bizField)) {
                JDBottomDialog c2 = c(context, popViewConfig);
                if (c2 != null) {
                    if (popViewConfig.bgTransparent) {
                        c2.getWindow().setBackgroundDrawableResource(17170445);
                    } else {
                        c2.setUseBg(true);
                    }
                    c2.setCancelable(popViewConfig.canCanceledOnTouchOutside);
                    c2.setCanceledOnTouchOutside(popViewConfig.canCanceledOnTouchOutside);
                    c2.setPosBtnClickListener(new c(c2, popViewConfig));
                    c2.show();
                    return c2;
                }
                return null;
            }
            DynamicSdk.handException("popView", "bizField or systemCode is null", popViewConfig.bizField, popViewConfig.systemCode, new Exception());
            return null;
        } catch (Exception e2) {
            DynamicSdk.handException("popView", "open popView error", popViewConfig.bizField, popViewConfig.systemCode, e2);
            return null;
        }
    }
}
