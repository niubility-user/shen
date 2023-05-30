package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.DpiUtil;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 B2\u00020\u0001:\u0002BCB\u001b\u0012\b\u0010>\u001a\u0004\u0018\u00010=\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b?\u0010@B\u0013\b\u0016\u0012\b\u0010>\u001a\u0004\u0018\u00010=\u00a2\u0006\u0004\b?\u0010AJ\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0007J7\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015H\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J=\u0010\u001e\u001a\u00020\u00002\u0012\b\u0002\u0010\u001b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\u00192\u0010\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u0015H\u0007\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010!\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0002\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0005\u00a2\u0006\u0004\b#\u0010$R\"\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u00198\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R*\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00198\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010&\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\"\u0010,\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0016\u00102\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010-R\"\u00103\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u0010-\u001a\u0004\b4\u0010/\"\u0004\b5\u00101R\"\u00106\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u0010-\u001a\u0004\b7\u0010/\"\u0004\b8\u00101R\"\u00109\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010-\u001a\u0004\b:\u0010/\"\u0004\b;\u00101R\u001c\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00020\u00198\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010&\u00a8\u0006D"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/JDLableView;", "Landroid/view/ViewGroup;", "", "widthMeasureSpec", "heightMeasureSpec", "", "onMeasure", "(II)V", "", "changed", NotifyType.LIGHTS, "t", "r", "b", ViewProps.ON_LAYOUT, "(ZIIII)V", "Landroid/util/AttributeSet;", "attrs", "Landroid/view/ViewGroup$LayoutParams;", "generateLayoutParams", "(Landroid/util/AttributeSet;)Landroid/view/ViewGroup$LayoutParams;", "Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption;", "jdLableOption", "addJDLableView", "(Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption;)Lcom/jingdong/common/jdmiaosha/view/JDLableView;", "", "Landroid/view/View;", "views", "", "texts", "addJDLableViews", "(Ljava/util/List;Ljava/util/List;Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption;)Lcom/jingdong/common/jdmiaosha/view/JDLableView;", "visibility", "setNoneVisibility", "(I)Lcom/jingdong/common/jdmiaosha/view/JDLableView;", HybridSDK.APP_VERSION_CODE, "()V", "mLineViews", "Ljava/util/List;", "childViews", "getChildViews", "()Ljava/util/List;", "setChildViews", "(Ljava/util/List;)V", "viewTopMargin", "I", "getViewTopMargin", "()I", "setViewTopMargin", "(I)V", "mVisibility", "viewRightMargin", "getViewRightMargin", "setViewRightMargin", "viewLeftMargin", "getViewLeftMargin", "setViewLeftMargin", "viewBottomMargin", "getViewBottomMargin", "setViewBottomMargin", "mLineHeight", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "Companion", "JDLableOption", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class JDLableView extends ViewGroup {
    private static final int backgroundId_default = -1;
    private static final boolean isShowLeftDrawable_default = false;
    private static final float jdTextSize_default = 13.0f;
    private HashMap _$_findViewCache;
    @NotNull
    private List<View> childViews;
    private final List<Integer> mLineHeight;
    private final List<List<View>> mLineViews;
    private int mVisibility;
    private int viewBottomMargin;
    private int viewLeftMargin;
    private int viewRightMargin;
    private int viewTopMargin;
    private static final int jdTextColor_default = Color.parseColor("#F2270C");
    private static final int jdPaddingLeft_default = DPIUtil.dip2px(4.0f);
    private static final int jdPaddingRight_default = DPIUtil.dip2px(4.0f);
    private static final int jdRightMargin_default = DPIUtil.dip2px(0.0f);
    private static final int jdpPaddingTop_default = DPIUtil.dip2px(1.0f);
    private static final int jdPaddingBottom_default = DPIUtil.dip2px(1.0f);
    private static final int jdDrawableRightPadding_default = DPIUtil.dip2px(2.0f);
    private static final int TAGDRAWABLE_WH = DPIUtil.dip2px(10.0f);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0011\u0018\u00002\u00020\u0001:\u00016B\u000f\u0012\u0006\u0010 \u001a\u00020\u001f\u00a2\u0006\u0004\b5\u0010%R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0019\u0010\t\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006R\u001b\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0004\u001a\u0004\b\u0011\u0010\u0006R\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001c\u001a\u00020\u001b8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001c\u0010\u001eR\"\u0010 \u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0019\u0010'\u001a\u00020&8\u0006@\u0006\u00a2\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0019\u0010+\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b+\u0010\u0004\u001a\u0004\b,\u0010\u0006R\u0019\u0010-\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b-\u0010\u0004\u001a\u0004\b.\u0010\u0006R\u001b\u0010/\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b/\u0010\u0013\u001a\u0004\b0\u0010\u0015R\u0019\u00101\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b1\u0010\u0004\u001a\u0004\b2\u0010\u0006R\u0019\u00103\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b3\u0010\u0004\u001a\u0004\b4\u0010\u0006\u00a8\u00067"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption;", "", "", "paddingLeft", "I", "getPaddingLeft", "()I", DYConstants.DY_TEXT_COLOR, "getTextColor", "tagDrawableWH", "getTagDrawableWH", "", "text", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "paddingBottom", "getPaddingBottom", "drawableLeftID", "Ljava/lang/Integer;", "getDrawableLeftID", "()Ljava/lang/Integer;", "Landroid/view/View;", "jdLableView", "Landroid/view/View;", "getJdLableView", "()Landroid/view/View;", "", "isShowLeftDrawable", "Z", "()Z", "Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "builder", "Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "getBuilder", "()Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "setBuilder", "(Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;)V", "", DYConstants.DY_TEXT_SIZE, "F", "getTextSize", "()F", "rightMargin", "getRightMargin", "paddingRight", "getPaddingRight", "backgroundId", "getBackgroundId", "paddingTop", "getPaddingTop", "drawablePaddingRight", "getDrawablePaddingRight", "<init>", "OptionBuilder", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public static final class JDLableOption {
        @Nullable
        private final Integer backgroundId;
        @NotNull
        private OptionBuilder builder;
        @Nullable
        private final Integer drawableLeftID;
        private final int drawablePaddingRight;
        private final boolean isShowLeftDrawable;
        @Nullable
        private final View jdLableView;
        private final int paddingBottom;
        private final int paddingLeft;
        private final int paddingRight;
        private final int paddingTop;
        private final int rightMargin;
        private final int tagDrawableWH;
        @Nullable
        private final String text;
        private final int textColor;
        private final float textSize;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b3\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\bI\u0010JJ\u0017\u0010\u0003\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0006\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\u0019\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0010\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0012\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0013\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u0017\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0014\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0014\u0010\u000fJ\u0019\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u0015\u0010\rJ\u0017\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0016\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\u0017\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0017\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0017\u0010\u000fJ\u0017\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u0019\u0010\u001aR\"\u0010\u0011\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010\u0016\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u001b\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR$\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0006\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010\u0013\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u001b\u001a\u0004\b'\u0010\u001d\"\u0004\b(\u0010\u001fR\"\u0010\u0014\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u001b\u001a\u0004\b)\u0010\u001d\"\u0004\b*\u0010\u001fR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\"\u0010\u0019\u001a\u00020\u00188\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\f\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\"\u0010\u000e\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u001b\u001a\u0004\b:\u0010\u001d\"\u0004\b;\u0010\u001fR\"\u0010\u0012\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u001b\u001a\u0004\b<\u0010\u001d\"\u0004\b=\u0010\u001fR$\u0010\u0015\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u00105\u001a\u0004\b>\u00107\"\u0004\b?\u00109R\"\u0010\u0010\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u001b\u001a\u0004\b@\u0010\u001d\"\u0004\bA\u0010\u001fR\"\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\"\u0010\u0017\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u001b\u001a\u0004\bG\u0010\u001d\"\u0004\bH\u0010\u001f\u00a8\u0006K"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "", "Landroid/view/View;", "view", "(Landroid/view/View;)Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "", "text", "(Ljava/lang/String;)Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "", DYConstants.DY_TEXT_SIZE, "(F)Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "", "backgroundId", "(Ljava/lang/Integer;)Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", DYConstants.DY_TEXT_COLOR, "(I)Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "rightMargin", "drawableLeftID", "tagDrawableWH", "drawablePaddingRight", "", "isShowLeftDrawable", "(Z)Lcom/jingdong/common/jdmiaosha/view/JDLableView$JDLableOption$OptionBuilder;", "I", "getPaddingRight$AndroidJD_Lib_androidRelease", "()I", "setPaddingRight$AndroidJD_Lib_androidRelease", "(I)V", "getTagDrawableWH$AndroidJD_Lib_androidRelease", "setTagDrawableWH$AndroidJD_Lib_androidRelease", "Ljava/lang/String;", "getText$AndroidJD_Lib_androidRelease", "()Ljava/lang/String;", "setText$AndroidJD_Lib_androidRelease", "(Ljava/lang/String;)V", "getPaddingBottom$AndroidJD_Lib_androidRelease", "setPaddingBottom$AndroidJD_Lib_androidRelease", "getRightMargin$AndroidJD_Lib_androidRelease", "setRightMargin$AndroidJD_Lib_androidRelease", "Landroid/view/View;", "getView$AndroidJD_Lib_androidRelease", "()Landroid/view/View;", "setView$AndroidJD_Lib_androidRelease", "(Landroid/view/View;)V", "Z", "isShowLeftDrawable$AndroidJD_Lib_androidRelease", "()Z", "setShowLeftDrawable$AndroidJD_Lib_androidRelease", "(Z)V", "Ljava/lang/Integer;", "getBackgroundId$AndroidJD_Lib_androidRelease", "()Ljava/lang/Integer;", "setBackgroundId$AndroidJD_Lib_androidRelease", "(Ljava/lang/Integer;)V", "getTextColor$AndroidJD_Lib_androidRelease", "setTextColor$AndroidJD_Lib_androidRelease", "getPaddingTop$AndroidJD_Lib_androidRelease", "setPaddingTop$AndroidJD_Lib_androidRelease", "getDrawableLeftID$AndroidJD_Lib_androidRelease", "setDrawableLeftID$AndroidJD_Lib_androidRelease", "getPaddingLeft$AndroidJD_Lib_androidRelease", "setPaddingLeft$AndroidJD_Lib_androidRelease", "F", "getTextSize$AndroidJD_Lib_androidRelease", "()F", "setTextSize$AndroidJD_Lib_androidRelease", "(F)V", "getDrawablePaddingRight$AndroidJD_Lib_androidRelease", "setDrawablePaddingRight$AndroidJD_Lib_androidRelease", "<init>", "()V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
        /* loaded from: classes5.dex */
        public static final class OptionBuilder {
            private boolean isShowLeftDrawable;
            @Nullable
            private View view;
            @Nullable
            private String text = "";
            private float textSize = JDLableView.jdTextSize_default;
            @Nullable
            private Integer backgroundId = -1;
            private int textColor = JDLableView.jdTextColor_default;
            private int paddingLeft = JDLableView.jdPaddingLeft_default;
            private int paddingRight = JDLableView.jdPaddingRight_default;
            private int paddingTop = JDLableView.jdpPaddingTop_default;
            private int paddingBottom = JDLableView.jdPaddingBottom_default;
            private int rightMargin = JDLableView.jdRightMargin_default;
            @Nullable
            private Integer drawableLeftID = -1;
            private int tagDrawableWH = JDLableView.TAGDRAWABLE_WH;
            private int drawablePaddingRight = JDLableView.jdDrawableRightPadding_default;

            public static /* synthetic */ OptionBuilder backgroundId$default(OptionBuilder optionBuilder, Integer num, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    num = -1;
                }
                return optionBuilder.backgroundId(num);
            }

            public static /* synthetic */ OptionBuilder drawableLeftID$default(OptionBuilder optionBuilder, Integer num, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    num = -1;
                }
                return optionBuilder.drawableLeftID(num);
            }

            public static /* synthetic */ OptionBuilder drawablePaddingRight$default(OptionBuilder optionBuilder, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i2 = JDLableView.jdDrawableRightPadding_default;
                }
                return optionBuilder.drawablePaddingRight(i2);
            }

            public static /* synthetic */ OptionBuilder isShowLeftDrawable$default(OptionBuilder optionBuilder, boolean z, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    z = false;
                }
                return optionBuilder.isShowLeftDrawable(z);
            }

            public static /* synthetic */ OptionBuilder paddingBottom$default(OptionBuilder optionBuilder, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i2 = JDLableView.jdPaddingBottom_default;
                }
                return optionBuilder.paddingBottom(i2);
            }

            public static /* synthetic */ OptionBuilder paddingLeft$default(OptionBuilder optionBuilder, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i2 = JDLableView.jdPaddingLeft_default;
                }
                return optionBuilder.paddingLeft(i2);
            }

            public static /* synthetic */ OptionBuilder paddingRight$default(OptionBuilder optionBuilder, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i2 = JDLableView.jdPaddingRight_default;
                }
                return optionBuilder.paddingRight(i2);
            }

            public static /* synthetic */ OptionBuilder paddingTop$default(OptionBuilder optionBuilder, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i2 = JDLableView.jdpPaddingTop_default;
                }
                return optionBuilder.paddingTop(i2);
            }

            public static /* synthetic */ OptionBuilder rightMargin$default(OptionBuilder optionBuilder, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i2 = JDLableView.jdRightMargin_default;
                }
                return optionBuilder.rightMargin(i2);
            }

            public static /* synthetic */ OptionBuilder tagDrawableWH$default(OptionBuilder optionBuilder, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i2 = JDLableView.TAGDRAWABLE_WH;
                }
                return optionBuilder.tagDrawableWH(i2);
            }

            public static /* synthetic */ OptionBuilder textColor$default(OptionBuilder optionBuilder, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i2 = JDLableView.jdTextColor_default;
                }
                return optionBuilder.textColor(i2);
            }

            public static /* synthetic */ OptionBuilder textSize$default(OptionBuilder optionBuilder, float f2, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    f2 = JDLableView.jdTextSize_default;
                }
                return optionBuilder.textSize(f2);
            }

            @NotNull
            public final OptionBuilder backgroundId(@Nullable Integer backgroundId) {
                this.backgroundId = backgroundId;
                return this;
            }

            @NotNull
            public final OptionBuilder drawableLeftID(@Nullable Integer drawableLeftID) {
                this.drawableLeftID = drawableLeftID;
                return this;
            }

            @NotNull
            public final OptionBuilder drawablePaddingRight(int drawablePaddingRight) {
                this.drawablePaddingRight = drawablePaddingRight;
                return this;
            }

            @Nullable
            /* renamed from: getBackgroundId$AndroidJD_Lib_androidRelease  reason: from getter */
            public final Integer getBackgroundId() {
                return this.backgroundId;
            }

            @Nullable
            /* renamed from: getDrawableLeftID$AndroidJD_Lib_androidRelease  reason: from getter */
            public final Integer getDrawableLeftID() {
                return this.drawableLeftID;
            }

            /* renamed from: getDrawablePaddingRight$AndroidJD_Lib_androidRelease  reason: from getter */
            public final int getDrawablePaddingRight() {
                return this.drawablePaddingRight;
            }

            /* renamed from: getPaddingBottom$AndroidJD_Lib_androidRelease  reason: from getter */
            public final int getPaddingBottom() {
                return this.paddingBottom;
            }

            /* renamed from: getPaddingLeft$AndroidJD_Lib_androidRelease  reason: from getter */
            public final int getPaddingLeft() {
                return this.paddingLeft;
            }

            /* renamed from: getPaddingRight$AndroidJD_Lib_androidRelease  reason: from getter */
            public final int getPaddingRight() {
                return this.paddingRight;
            }

            /* renamed from: getPaddingTop$AndroidJD_Lib_androidRelease  reason: from getter */
            public final int getPaddingTop() {
                return this.paddingTop;
            }

            /* renamed from: getRightMargin$AndroidJD_Lib_androidRelease  reason: from getter */
            public final int getRightMargin() {
                return this.rightMargin;
            }

            /* renamed from: getTagDrawableWH$AndroidJD_Lib_androidRelease  reason: from getter */
            public final int getTagDrawableWH() {
                return this.tagDrawableWH;
            }

            @Nullable
            /* renamed from: getText$AndroidJD_Lib_androidRelease  reason: from getter */
            public final String getText() {
                return this.text;
            }

            /* renamed from: getTextColor$AndroidJD_Lib_androidRelease  reason: from getter */
            public final int getTextColor() {
                return this.textColor;
            }

            /* renamed from: getTextSize$AndroidJD_Lib_androidRelease  reason: from getter */
            public final float getTextSize() {
                return this.textSize;
            }

            @Nullable
            /* renamed from: getView$AndroidJD_Lib_androidRelease  reason: from getter */
            public final View getView() {
                return this.view;
            }

            @NotNull
            public final OptionBuilder isShowLeftDrawable(boolean isShowLeftDrawable) {
                this.isShowLeftDrawable = isShowLeftDrawable;
                return this;
            }

            /* renamed from: isShowLeftDrawable$AndroidJD_Lib_androidRelease  reason: from getter */
            public final boolean getIsShowLeftDrawable() {
                return this.isShowLeftDrawable;
            }

            @NotNull
            public final OptionBuilder paddingBottom(int paddingBottom) {
                this.paddingBottom = paddingBottom;
                return this;
            }

            @NotNull
            public final OptionBuilder paddingLeft(int paddingLeft) {
                this.paddingLeft = paddingLeft;
                return this;
            }

            @NotNull
            public final OptionBuilder paddingRight(int paddingRight) {
                this.paddingRight = paddingRight;
                return this;
            }

            @NotNull
            public final OptionBuilder paddingTop(int paddingTop) {
                this.paddingTop = paddingTop;
                return this;
            }

            @NotNull
            public final OptionBuilder rightMargin(int rightMargin) {
                this.rightMargin = rightMargin;
                return this;
            }

            public final void setBackgroundId$AndroidJD_Lib_androidRelease(@Nullable Integer num) {
                this.backgroundId = num;
            }

            public final void setDrawableLeftID$AndroidJD_Lib_androidRelease(@Nullable Integer num) {
                this.drawableLeftID = num;
            }

            public final void setDrawablePaddingRight$AndroidJD_Lib_androidRelease(int i2) {
                this.drawablePaddingRight = i2;
            }

            public final void setPaddingBottom$AndroidJD_Lib_androidRelease(int i2) {
                this.paddingBottom = i2;
            }

            public final void setPaddingLeft$AndroidJD_Lib_androidRelease(int i2) {
                this.paddingLeft = i2;
            }

            public final void setPaddingRight$AndroidJD_Lib_androidRelease(int i2) {
                this.paddingRight = i2;
            }

            public final void setPaddingTop$AndroidJD_Lib_androidRelease(int i2) {
                this.paddingTop = i2;
            }

            public final void setRightMargin$AndroidJD_Lib_androidRelease(int i2) {
                this.rightMargin = i2;
            }

            public final void setShowLeftDrawable$AndroidJD_Lib_androidRelease(boolean z) {
                this.isShowLeftDrawable = z;
            }

            public final void setTagDrawableWH$AndroidJD_Lib_androidRelease(int i2) {
                this.tagDrawableWH = i2;
            }

            public final void setText$AndroidJD_Lib_androidRelease(@Nullable String str) {
                this.text = str;
            }

            public final void setTextColor$AndroidJD_Lib_androidRelease(int i2) {
                this.textColor = i2;
            }

            public final void setTextSize$AndroidJD_Lib_androidRelease(float f2) {
                this.textSize = f2;
            }

            public final void setView$AndroidJD_Lib_androidRelease(@Nullable View view) {
                this.view = view;
            }

            @NotNull
            public final OptionBuilder tagDrawableWH(int tagDrawableWH) {
                this.tagDrawableWH = tagDrawableWH;
                return this;
            }

            @NotNull
            public final OptionBuilder text(@Nullable String text) {
                this.text = text;
                return this;
            }

            @NotNull
            public final OptionBuilder textColor(int textColor) {
                this.textColor = textColor;
                return this;
            }

            @NotNull
            public final OptionBuilder textSize(float textSize) {
                this.textSize = textSize;
                return this;
            }

            @NotNull
            public final OptionBuilder view(@Nullable View view) {
                this.view = view;
                return this;
            }
        }

        public JDLableOption(@NotNull OptionBuilder optionBuilder) {
            this.builder = optionBuilder;
            this.jdLableView = optionBuilder.getView();
            this.text = this.builder.getText();
            this.textSize = this.builder.getTextSize();
            this.backgroundId = this.builder.getBackgroundId();
            this.textColor = this.builder.getTextColor();
            this.paddingLeft = this.builder.getPaddingLeft();
            this.paddingRight = this.builder.getPaddingRight();
            this.paddingTop = this.builder.getPaddingTop();
            this.paddingBottom = this.builder.getPaddingBottom();
            this.rightMargin = this.builder.getRightMargin();
            this.drawableLeftID = this.builder.getDrawableLeftID();
            this.tagDrawableWH = this.builder.getTagDrawableWH();
            this.drawablePaddingRight = this.builder.getDrawablePaddingRight();
            this.isShowLeftDrawable = this.builder.getIsShowLeftDrawable();
        }

        @Nullable
        public final Integer getBackgroundId() {
            return this.backgroundId;
        }

        @NotNull
        public final OptionBuilder getBuilder() {
            return this.builder;
        }

        @Nullable
        public final Integer getDrawableLeftID() {
            return this.drawableLeftID;
        }

        public final int getDrawablePaddingRight() {
            return this.drawablePaddingRight;
        }

        @Nullable
        public final View getJdLableView() {
            return this.jdLableView;
        }

        public final int getPaddingBottom() {
            return this.paddingBottom;
        }

        public final int getPaddingLeft() {
            return this.paddingLeft;
        }

        public final int getPaddingRight() {
            return this.paddingRight;
        }

        public final int getPaddingTop() {
            return this.paddingTop;
        }

        public final int getRightMargin() {
            return this.rightMargin;
        }

        public final int getTagDrawableWH() {
            return this.tagDrawableWH;
        }

        @Nullable
        public final String getText() {
            return this.text;
        }

        public final int getTextColor() {
            return this.textColor;
        }

        public final float getTextSize() {
            return this.textSize;
        }

        /* renamed from: isShowLeftDrawable  reason: from getter */
        public final boolean getIsShowLeftDrawable() {
            return this.isShowLeftDrawable;
        }

        public final void setBuilder(@NotNull OptionBuilder optionBuilder) {
            this.builder = optionBuilder;
        }
    }

    public JDLableView(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLineViews = new ArrayList();
        this.mLineHeight = new ArrayList();
        this.mVisibility = 4;
        this.childViews = new ArrayList();
        this.viewLeftMargin = DpiUtil.dip2px(context, 3.0f);
        this.viewRightMargin = DpiUtil.dip2px(context, 3.0f);
        this.viewTopMargin = DpiUtil.dip2px(context, 0.0f);
        this.viewBottomMargin = DpiUtil.dip2px(context, 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JDLableView addJDLableViews$default(JDLableView jDLableView, List list, List list2, JDLableOption jDLableOption, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = null;
        }
        return jDLableView.addJDLableViews(list, list2, jDLableOption);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @JvmOverloads
    @NotNull
    public final JDLableView addJDLableView(@NotNull JDLableOption jdLableOption) {
        Integer backgroundId;
        Integer drawableLeftID;
        Drawable drawable;
        Resources resources;
        if (jdLableOption.getJdLableView() == null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = jdLableOption.getRightMargin();
            TextView textView = new TextView(getContext());
            if (TextUtils.isEmpty(jdLableOption.getText())) {
                return this;
            }
            textView.setText(jdLableOption.getText());
            textView.setTextSize(1, jdLableOption.getTextSize());
            textView.setTextColor(jdLableOption.getTextColor());
            textView.setMaxLines(1);
            textView.setPadding(jdLableOption.getPaddingLeft(), jdLableOption.getPaddingTop(), jdLableOption.getPaddingRight(), jdLableOption.getPaddingBottom());
            if (jdLableOption.getIsShowLeftDrawable() && jdLableOption.getDrawableLeftID() != null && ((drawableLeftID = jdLableOption.getDrawableLeftID()) == null || drawableLeftID.intValue() != -1)) {
                Context context = getContext();
                if (context == null || (resources = context.getResources()) == null) {
                    drawable = null;
                } else {
                    Integer drawableLeftID2 = jdLableOption.getDrawableLeftID();
                    if (drawableLeftID2 == null) {
                        Intrinsics.throwNpe();
                    }
                    drawable = resources.getDrawable(drawableLeftID2.intValue());
                }
                if (drawable != null) {
                    drawable.setBounds(0, 0, jdLableOption.getTagDrawableWH(), jdLableOption.getTagDrawableWH());
                }
                textView.setCompoundDrawables(drawable, null, null, null);
                textView.setCompoundDrawablePadding(jdLableOption.getDrawablePaddingRight());
            }
            if (jdLableOption.getBackgroundId() != null && ((backgroundId = jdLableOption.getBackgroundId()) == null || backgroundId.intValue() != -1)) {
                textView.setBackgroundResource(jdLableOption.getBackgroundId().intValue());
            }
            textView.setLayoutParams(layoutParams);
            this.childViews.add(textView);
        } else {
            this.childViews.add(jdLableOption.getJdLableView());
        }
        return this;
    }

    @JvmOverloads
    @NotNull
    public final JDLableView addJDLableViews(@Nullable List<String> list, @NotNull JDLableOption jDLableOption) {
        return addJDLableViews$default(this, null, list, jDLableOption, 1, null);
    }

    @JvmOverloads
    @NotNull
    public final JDLableView addJDLableViews(@Nullable List<View> views, @Nullable List<String> texts, @NotNull JDLableOption jdLableOption) {
        if (views == null) {
            if (texts == null || texts.isEmpty()) {
                return this;
            }
            Iterator<T> it = texts.iterator();
            while (it.hasNext()) {
                addJDLableView(new JDLableOption(jdLableOption.getBuilder().view(null).text((String) it.next())));
            }
        } else {
            Iterator<T> it2 = views.iterator();
            while (it2.hasNext()) {
                addJDLableView(new JDLableOption(jdLableOption.getBuilder().view((View) it2.next())));
            }
        }
        return this;
    }

    public final void build() {
        removeAllViews();
        if (this.childViews.isEmpty()) {
            setVisibility(this.mVisibility);
        } else {
            setVisibility(0);
            for (View view : this.childViews) {
                if (view != null) {
                    addView(view);
                }
            }
            invalidate();
            requestLayout();
        }
        this.childViews.clear();
    }

    @Override // android.view.ViewGroup
    @NotNull
    public ViewGroup.LayoutParams generateLayoutParams(@Nullable AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }

    @NotNull
    public final List<View> getChildViews() {
        return this.childViews;
    }

    public final int getViewBottomMargin() {
        return this.viewBottomMargin;
    }

    public final int getViewLeftMargin() {
        return this.viewLeftMargin;
    }

    public final int getViewRightMargin() {
        return this.viewRightMargin;
    }

    public final int getViewTopMargin() {
        return this.viewTopMargin;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l2, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int size = this.mLineViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            int intValue = this.mLineHeight.get(i2).intValue();
            List<View> list = this.mLineViews.get(i2);
            int size2 = list.size();
            int i3 = 0;
            while (i3 < size2) {
                View view = list.get(i3);
                int i4 = (i3 == 0 ? 0 : this.viewLeftMargin) + paddingLeft;
                int i5 = this.viewTopMargin + paddingTop;
                view.layout(i4, i5, view.getMeasuredWidth() + i4, view.getMeasuredHeight() + i5);
                paddingLeft += view.getMeasuredWidth() + (i3 == 0 ? 0 : this.viewLeftMargin) + this.viewRightMargin;
                i3++;
            }
            paddingLeft = getPaddingLeft();
            paddingTop += intValue;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.mLineViews.clear();
        this.mLineHeight.clear();
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = (View.MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()) - getPaddingRight();
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec) + getPaddingTop() + getPaddingBottom();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (mode != 1073741824 || mode2 != 1073741824) {
            ArrayList arrayList = new ArrayList();
            int childCount = getChildCount();
            int i2 = size;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != null) {
                    measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth() + this.viewLeftMargin + this.viewRightMargin;
                    int measuredHeight = childAt.getMeasuredHeight() + this.viewTopMargin + this.viewBottomMargin;
                    int i6 = i3 + measuredWidth;
                    if (i6 >= size) {
                        removeViews(i5, getChildCount() - i5);
                    } else {
                        i4 = Math.max(i4, measuredHeight);
                        arrayList.add(childAt);
                        i3 = i6;
                    }
                    if (i5 >= getChildCount() - 1) {
                        this.mLineViews.add(arrayList);
                        i2 = Math.max(measuredWidth, size);
                        paddingTop += i4;
                        this.mLineHeight.add(Integer.valueOf(i4));
                    }
                }
            }
            size2 = paddingTop;
            size = i2;
        }
        setMeasuredDimension(size, size2);
    }

    public final void setChildViews(@NotNull List<View> list) {
        this.childViews = list;
    }

    @NotNull
    public final JDLableView setNoneVisibility(int visibility) {
        this.mVisibility = visibility;
        return this;
    }

    public final void setViewBottomMargin(int i2) {
        this.viewBottomMargin = i2;
    }

    public final void setViewLeftMargin(int i2) {
        this.viewLeftMargin = i2;
    }

    public final void setViewRightMargin(int i2) {
        this.viewRightMargin = i2;
    }

    public final void setViewTopMargin(int i2) {
        this.viewTopMargin = i2;
    }

    public JDLableView(@Nullable Context context) {
        this(context, null);
    }
}
