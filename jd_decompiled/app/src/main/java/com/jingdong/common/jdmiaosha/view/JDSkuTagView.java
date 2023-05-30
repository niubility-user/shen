package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.base.CachePool;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.R;
import com.jingdong.common.jdmiaosha.entity.JDSkuTagEntity;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 e2\u00020\u0001:\u0001eB\u0019\u0012\u0006\u0010`\u001a\u00020_\u0012\b\u0010b\u001a\u0004\u0018\u00010a\u00a2\u0006\u0004\bc\u0010dJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J=\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0014\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u00a2\u0006\u0004\b\u001c\u0010\u001dJ7\u0010\u001e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fR\"\u0010!\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010'\u001a\u00020\u00168\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010\u0019R$\u0010-\u001a\u0004\u0018\u00010,8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u00103\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u0010\"\u001a\u0004\b4\u0010$\"\u0004\b5\u0010&R$\u00106\u001a\u0004\u0018\u00010,8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u0010.\u001a\u0004\b7\u00100\"\u0004\b8\u00102R\"\u00109\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010\"\u001a\u0004\b:\u0010$\"\u0004\b;\u0010&R\"\u0010<\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010\"\u001a\u0004\b=\u0010$\"\u0004\b>\u0010&R\"\u0010?\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b?\u0010\"\u001a\u0004\b@\u0010$\"\u0004\bA\u0010&R\"\u0010B\u001a\u00020\u00168\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010(\u001a\u0004\bC\u0010*\"\u0004\bD\u0010\u0019R\"\u0010\u0017\u001a\u00020\u00168\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010(\u001a\u0004\bE\u0010*\"\u0004\bF\u0010\u0019R\"\u0010G\u001a\u00020\u00168\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bG\u0010(\u001a\u0004\bH\u0010*\"\u0004\bI\u0010\u0019R$\u0010K\u001a\u0004\u0018\u00010J8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR$\u0010R\u001a\u0004\u0018\u00010Q8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010\"R\"\u0010Y\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bY\u0010\"\u001a\u0004\bZ\u0010$\"\u0004\b[\u0010&R\"\u0010\\\u001a\u00020 8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\\\u0010\"\u001a\u0004\b]\u0010$\"\u0004\b^\u0010&\u00a8\u0006f"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/JDSkuTagView;", "Landroid/widget/LinearLayout;", "", "url", "", "setSkuImageTag", "(Ljava/lang/String;)V", "str", "startColor", "endColor", "setImageTextTag", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "hasIcon", "setTagTextParams", "(Z)V", "initView", "()V", "Landroid/view/ViewGroup$LayoutParams;", "params", "setLayoutParams", "(Landroid/view/ViewGroup$LayoutParams;)V", "", "scale", "setChildViewsParams", "(F)V", "Lcom/jingdong/common/jdmiaosha/entity/JDSkuTagEntity;", "jdSkuTagEntity", "setSkuTag", "(Lcom/jingdong/common/jdmiaosha/entity/JDSkuTagEntity;)V", "setTagText", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "textLeftPadding", "I", "getTextLeftPadding", "()I", "setTextLeftPadding", "(I)V", "connerSize", "F", "getConnerSize", "()F", "setConnerSize", "Lcom/facebook/drawee/view/SimpleDraweeView;", "leftTagIcon", "Lcom/facebook/drawee/view/SimpleDraweeView;", "getLeftTagIcon", "()Lcom/facebook/drawee/view/SimpleDraweeView;", "setLeftTagIcon", "(Lcom/facebook/drawee/view/SimpleDraweeView;)V", "textRightPadding", "getTextRightPadding", "setTextRightPadding", "tagImage", "getTagImage", "setTagImage", "textViewMinWidth", "getTextViewMinWidth", "setTextViewMinWidth", "textViewHeight", "getTextViewHeight", "setTextViewHeight", "leftMargin", "getLeftMargin", "setLeftMargin", "tagTextSize", "getTagTextSize", "setTagTextSize", "getScale", "setScale", "preScale", "getPreScale", "setPreScale", "Landroid/widget/RelativeLayout;", "tag", "Landroid/widget/RelativeLayout;", CachePool.M_GET_TAG, "()Landroid/widget/RelativeLayout;", "setTag", "(Landroid/widget/RelativeLayout;)V", "Landroid/widget/TextView;", "textView", "Landroid/widget/TextView;", "getTextView", "()Landroid/widget/TextView;", "setTextView", "(Landroid/widget/TextView;)V", "tagLeftIconSize", "imageHeight", "getImageHeight", "setImageHeight", "imageWidth", "getImageWidth", "setImageWidth", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public class JDSkuTagView extends LinearLayout {
    private HashMap _$_findViewCache;
    private float connerSize;
    private int imageHeight;
    private int imageWidth;
    private int leftMargin;
    @Nullable
    private SimpleDraweeView leftTagIcon;
    private float preScale;
    private float scale;
    @Nullable
    private RelativeLayout tag;
    @Nullable
    private SimpleDraweeView tagImage;
    private int tagLeftIconSize;
    private float tagTextSize;
    private int textLeftPadding;
    private int textRightPadding;
    @Nullable
    private TextView textView;
    private int textViewHeight;
    private int textViewMinWidth;

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull
    private static int[] TAG_DEFAULT_COLORS = {Color.parseColor("#FF005A"), Color.parseColor("#FF4500")};
    private static int MIN_WIDTH = DPIUtil.dip2px(56.0f);
    private static int TEXT_PADDING = DPIUtil.dip2px(6.0f);
    private static int RIGHT_TEXT_LEFT_PADDING = DPIUtil.dip2px(19.0f);
    private static int TEXT_VIEW_MARGIN = DPIUtil.dip2px(2.0f);
    private static int BG_VIEW_CORNER = DPIUtil.dip2px(9.0f);
    private static int DEFAULT_LEFT_ICON_SIZE = DPIUtil.dip2px(19.0f);
    private static int DEFAULT_TEXTVIEW_HEIGHT = DPIUtil.dip2px(17.0f);
    private static int DEFAULT_IMAGEVIEW_HEIGHT = DPIUtil.dip2px(55.0f);
    @NotNull
    private static String TAG = "JDSkuTagView";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u0015\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b,\u0010-R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\"\u0010\f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\"\u0010\u000f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0004\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR\"\u0010\u001c\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u0004\u001a\u0004\b\u001d\u0010\u0006\"\u0004\b\u001e\u0010\bR\"\u0010\u001f\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u0004\u001a\u0004\b \u0010\u0006\"\u0004\b!\u0010\bR\"\u0010#\u001a\u00020\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010)\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010\u0004\u001a\u0004\b*\u0010\u0006\"\u0004\b+\u0010\b\u00a8\u0006."}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/JDSkuTagView$Companion;", "", "", "DEFAULT_IMAGEVIEW_HEIGHT", "I", "getDEFAULT_IMAGEVIEW_HEIGHT", "()I", "setDEFAULT_IMAGEVIEW_HEIGHT", "(I)V", "TEXT_VIEW_MARGIN", "getTEXT_VIEW_MARGIN", "setTEXT_VIEW_MARGIN", "DEFAULT_LEFT_ICON_SIZE", "getDEFAULT_LEFT_ICON_SIZE", "setDEFAULT_LEFT_ICON_SIZE", "BG_VIEW_CORNER", "getBG_VIEW_CORNER", "setBG_VIEW_CORNER", "", "TAG_DEFAULT_COLORS", "[I", "getTAG_DEFAULT_COLORS", "()[I", "setTAG_DEFAULT_COLORS", "([I)V", "TEXT_PADDING", "getTEXT_PADDING", "setTEXT_PADDING", "DEFAULT_TEXTVIEW_HEIGHT", "getDEFAULT_TEXTVIEW_HEIGHT", "setDEFAULT_TEXTVIEW_HEIGHT", "RIGHT_TEXT_LEFT_PADDING", "getRIGHT_TEXT_LEFT_PADDING", "setRIGHT_TEXT_LEFT_PADDING", "", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "MIN_WIDTH", "getMIN_WIDTH", "setMIN_WIDTH", "<init>", "()V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public final int getBG_VIEW_CORNER() {
            return JDSkuTagView.BG_VIEW_CORNER;
        }

        public final int getDEFAULT_IMAGEVIEW_HEIGHT() {
            return JDSkuTagView.DEFAULT_IMAGEVIEW_HEIGHT;
        }

        public final int getDEFAULT_LEFT_ICON_SIZE() {
            return JDSkuTagView.DEFAULT_LEFT_ICON_SIZE;
        }

        public final int getDEFAULT_TEXTVIEW_HEIGHT() {
            return JDSkuTagView.DEFAULT_TEXTVIEW_HEIGHT;
        }

        public final int getMIN_WIDTH() {
            return JDSkuTagView.MIN_WIDTH;
        }

        public final int getRIGHT_TEXT_LEFT_PADDING() {
            return JDSkuTagView.RIGHT_TEXT_LEFT_PADDING;
        }

        @NotNull
        public final String getTAG() {
            return JDSkuTagView.TAG;
        }

        @NotNull
        public final int[] getTAG_DEFAULT_COLORS() {
            return JDSkuTagView.TAG_DEFAULT_COLORS;
        }

        public final int getTEXT_PADDING() {
            return JDSkuTagView.TEXT_PADDING;
        }

        public final int getTEXT_VIEW_MARGIN() {
            return JDSkuTagView.TEXT_VIEW_MARGIN;
        }

        public final void setBG_VIEW_CORNER(int i2) {
            JDSkuTagView.BG_VIEW_CORNER = i2;
        }

        public final void setDEFAULT_IMAGEVIEW_HEIGHT(int i2) {
            JDSkuTagView.DEFAULT_IMAGEVIEW_HEIGHT = i2;
        }

        public final void setDEFAULT_LEFT_ICON_SIZE(int i2) {
            JDSkuTagView.DEFAULT_LEFT_ICON_SIZE = i2;
        }

        public final void setDEFAULT_TEXTVIEW_HEIGHT(int i2) {
            JDSkuTagView.DEFAULT_TEXTVIEW_HEIGHT = i2;
        }

        public final void setMIN_WIDTH(int i2) {
            JDSkuTagView.MIN_WIDTH = i2;
        }

        public final void setRIGHT_TEXT_LEFT_PADDING(int i2) {
            JDSkuTagView.RIGHT_TEXT_LEFT_PADDING = i2;
        }

        public final void setTAG(@NotNull String str) {
            JDSkuTagView.TAG = str;
        }

        public final void setTAG_DEFAULT_COLORS(@NotNull int[] iArr) {
            JDSkuTagView.TAG_DEFAULT_COLORS = iArr;
        }

        public final void setTEXT_PADDING(int i2) {
            JDSkuTagView.TEXT_PADDING = i2;
        }

        public final void setTEXT_VIEW_MARGIN(int i2) {
            JDSkuTagView.TEXT_VIEW_MARGIN = i2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public JDSkuTagView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tagTextSize = DPIUtil.dip2px(11.0f);
        this.scale = 1.0f;
        this.preScale = 1.0f;
        LayoutInflater.from(context).inflate(R.layout.jd_skutag_layout, (ViewGroup) this, true);
        initView();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDSkuTagView);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr\u2026R.styleable.JDSkuTagView)");
        this.tagLeftIconSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDSkuTagView_tag_leftIcon_size, DEFAULT_LEFT_ICON_SIZE);
        this.textViewMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDSkuTagView_tag_textView_minWidth, MIN_WIDTH);
        this.textViewHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDSkuTagView_tag_textView_height, DEFAULT_TEXTVIEW_HEIGHT);
        this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDSkuTagView_tag_textToIcon_leftMargin, TEXT_VIEW_MARGIN);
        this.textLeftPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDSkuTagView_textLeftPadding, RIGHT_TEXT_LEFT_PADDING);
        this.textRightPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDSkuTagView_textRightPadding, TEXT_PADDING);
        this.connerSize = obtainStyledAttributes.getDimension(R.styleable.JDSkuTagView_tag_corner_size, BG_VIEW_CORNER);
        this.imageWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDSkuTagView_tag_image_width, DEFAULT_IMAGEVIEW_HEIGHT);
        this.imageHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDSkuTagView_tag_image_height, DEFAULT_TEXTVIEW_HEIGHT);
        obtainStyledAttributes.recycle();
    }

    private final void setImageTextTag(String url, final String str, final String startColor, final String endColor) {
        RelativeLayout relativeLayout = this.tag;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
        SimpleDraweeView simpleDraweeView = this.tagImage;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
        if (TextUtils.isEmpty(url)) {
            SimpleDraweeView simpleDraweeView2 = this.leftTagIcon;
            if (simpleDraweeView2 != null) {
                simpleDraweeView2.setVisibility(8);
            }
            setTagText(false, str, startColor, endColor);
            return;
        }
        SimpleDraweeView simpleDraweeView3 = this.leftTagIcon;
        if (simpleDraweeView3 != null) {
            simpleDraweeView3.setVisibility(0);
        }
        TextView textView = this.textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        JDImageUtils.displayImage(url, this.leftTagIcon, new JDImageLoadingListener() { // from class: com.jingdong.common.jdmiaosha.view.JDSkuTagView$setImageTextTag$1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(@Nullable String p0, @Nullable View p1) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(@Nullable String p0, @Nullable View p1, @Nullable Bitmap p2) {
                SimpleDraweeView leftTagIcon = JDSkuTagView.this.getLeftTagIcon();
                if (leftTagIcon != null) {
                    leftTagIcon.setVisibility(0);
                }
                JDSkuTagView.this.setTagText(true, str, startColor, endColor);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(@Nullable String p0, @Nullable View p1, @Nullable JDFailReason p2) {
                SimpleDraweeView leftTagIcon = JDSkuTagView.this.getLeftTagIcon();
                if (leftTagIcon != null) {
                    leftTagIcon.setVisibility(8);
                }
                JDSkuTagView.this.setTagText(false, str, startColor, endColor);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(@Nullable String p0, @Nullable View p1) {
            }
        });
    }

    static /* synthetic */ void setImageTextTag$default(JDSkuTagView jDSkuTagView, String str, String str2, String str3, String str4, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                str = null;
            }
            if ((i2 & 4) != 0) {
                str3 = null;
            }
            if ((i2 & 8) != 0) {
                str4 = null;
            }
            jDSkuTagView.setImageTextTag(str, str2, str3, str4);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setImageTextTag");
    }

    private final void setSkuImageTag(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        RelativeLayout relativeLayout = this.tag;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
        SimpleDraweeView simpleDraweeView = this.tagImage;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(0);
        }
        SimpleDraweeView simpleDraweeView2 = this.leftTagIcon;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setVisibility(8);
        }
        TextView textView = this.textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        JDImageUtils.displayImage(url, this.tagImage, new JDImageLoadingListener() { // from class: com.jingdong.common.jdmiaosha.view.JDSkuTagView$setSkuImageTag$1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(@Nullable String p0, @Nullable View p1) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(@Nullable String p0, @Nullable View p1, @Nullable Bitmap p2) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(@Nullable String p0, @Nullable View p1, @Nullable JDFailReason p2) {
                SimpleDraweeView tagImage = JDSkuTagView.this.getTagImage();
                if (tagImage != null) {
                    tagImage.setVisibility(8);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(@Nullable String p0, @Nullable View p1) {
            }
        });
    }

    public static /* synthetic */ void setTagText$default(JDSkuTagView jDSkuTagView, boolean z, String str, String str2, String str3, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 4) != 0) {
                str2 = null;
            }
            if ((i2 & 8) != 0) {
                str3 = null;
            }
            jDSkuTagView.setTagText(z, str, str2, str3);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setTagText");
    }

    private final void setTagTextParams(boolean hasIcon) {
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        TextView textView = this.textView;
        if (textView != null) {
            textView.setVisibility(0);
        }
        if (hasIcon) {
            TextView textView2 = this.textView;
            if (textView2 != null) {
                textView2.setMinimumWidth(-1);
            }
            TextView textView3 = this.textView;
            if (textView3 != null) {
                float f2 = this.scale;
                textView3.setPadding((int) (this.textLeftPadding * f2), 0, (int) (this.textRightPadding * f2), 0);
            }
            TextView textView4 = this.textView;
            if (textView4 == null || (layoutParams2 = textView4.getLayoutParams()) == null) {
                return;
            }
            if (layoutParams2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            int i2 = this.leftMargin;
            float f3 = this.scale;
            ((RelativeLayout.LayoutParams) layoutParams2).setMargins((int) (i2 * f3), (int) (i2 * f3), 0, 0);
            return;
        }
        TextView textView5 = this.textView;
        if (textView5 != null) {
            textView5.setMinimumWidth((int) (this.textViewMinWidth * this.scale));
        }
        TextView textView6 = this.textView;
        if (textView6 != null) {
            int i3 = this.textRightPadding;
            float f4 = this.scale;
            textView6.setPadding((int) (i3 * f4), 0, (int) (i3 * f4), 0);
        }
        TextView textView7 = this.textView;
        if (textView7 == null || (layoutParams = textView7.getLayoutParams()) == null) {
            return;
        }
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        ((RelativeLayout.LayoutParams) layoutParams).setMargins(0, 0, 0, 0);
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

    public final float getConnerSize() {
        return this.connerSize;
    }

    public final int getImageHeight() {
        return this.imageHeight;
    }

    public final int getImageWidth() {
        return this.imageWidth;
    }

    public final int getLeftMargin() {
        return this.leftMargin;
    }

    @Nullable
    public final SimpleDraweeView getLeftTagIcon() {
        return this.leftTagIcon;
    }

    public final float getPreScale() {
        return this.preScale;
    }

    public final float getScale() {
        return this.scale;
    }

    @Override // android.view.View
    @Nullable
    public final RelativeLayout getTag() {
        return this.tag;
    }

    @Nullable
    public final SimpleDraweeView getTagImage() {
        return this.tagImage;
    }

    public final float getTagTextSize() {
        return this.tagTextSize;
    }

    public final int getTextLeftPadding() {
        return this.textLeftPadding;
    }

    public final int getTextRightPadding() {
        return this.textRightPadding;
    }

    @Nullable
    public final TextView getTextView() {
        return this.textView;
    }

    public final int getTextViewHeight() {
        return this.textViewHeight;
    }

    public final int getTextViewMinWidth() {
        return this.textViewMinWidth;
    }

    public final void initView() {
        this.textView = (TextView) _$_findCachedViewById(R.id.tagText);
        this.leftTagIcon = (SimpleDraweeView) _$_findCachedViewById(R.id.leftTagImg);
        this.tagImage = (SimpleDraweeView) _$_findCachedViewById(R.id.tagImg);
        this.tag = (RelativeLayout) _$_findCachedViewById(R.id.tagView);
    }

    public void setChildViewsParams(float scale) {
        RelativeLayout.LayoutParams layoutParams;
        RelativeLayout.LayoutParams layoutParams2;
        ViewGroup.LayoutParams layoutParams3;
        ViewGroup.LayoutParams layoutParams4;
        ViewGroup.LayoutParams layoutParams5;
        String str = "setChildViewsParams scale=" + scale;
        TextView textView = this.textView;
        RelativeLayout.LayoutParams layoutParams6 = null;
        if (textView == null || (layoutParams5 = textView.getLayoutParams()) == null) {
            layoutParams = null;
        } else if (layoutParams5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        } else {
            layoutParams = (RelativeLayout.LayoutParams) layoutParams5;
        }
        SimpleDraweeView simpleDraweeView = this.tagImage;
        if (simpleDraweeView == null || (layoutParams4 = simpleDraweeView.getLayoutParams()) == null) {
            layoutParams2 = null;
        } else if (layoutParams4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        } else {
            layoutParams2 = (RelativeLayout.LayoutParams) layoutParams4;
        }
        SimpleDraweeView simpleDraweeView2 = this.leftTagIcon;
        if (simpleDraweeView2 != null && (layoutParams3 = simpleDraweeView2.getLayoutParams()) != null) {
            if (layoutParams3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            layoutParams6 = (RelativeLayout.LayoutParams) layoutParams3;
        }
        if (layoutParams6 != null) {
            int i2 = this.tagLeftIconSize;
            layoutParams6.width = (int) (i2 * scale);
            layoutParams6.height = (int) (i2 * scale);
        }
        if (layoutParams != null) {
            layoutParams.height = (int) (this.textViewHeight * scale);
        }
        if (layoutParams2 != null) {
            layoutParams2.width = (int) (this.imageWidth * scale);
            layoutParams2.height = (int) (this.imageHeight * scale);
        }
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.setTextSize(0, this.tagTextSize * scale);
        }
    }

    public final void setConnerSize(float f2) {
        this.connerSize = f2;
    }

    public final void setImageHeight(int i2) {
        this.imageHeight = i2;
    }

    public final void setImageWidth(int i2) {
        this.imageWidth = i2;
    }

    @Override // android.view.View
    public void setLayoutParams(@Nullable ViewGroup.LayoutParams params) {
        int i2;
        super.setLayoutParams(params);
        this.preScale = this.scale;
        this.scale = 1.0f;
        if (params != null && (i2 = params.height) > 0) {
            this.scale = i2 / DEFAULT_LEFT_ICON_SIZE;
            String str = "tagView scale=" + this.scale;
        }
        float f2 = this.preScale;
        float f3 = this.scale;
        if (f2 != f3) {
            setChildViewsParams(f3);
        }
    }

    public final void setLeftMargin(int i2) {
        this.leftMargin = i2;
    }

    public final void setLeftTagIcon(@Nullable SimpleDraweeView simpleDraweeView) {
        this.leftTagIcon = simpleDraweeView;
    }

    public final void setPreScale(float f2) {
        this.preScale = f2;
    }

    public final void setScale(float f2) {
        this.scale = f2;
    }

    public final void setSkuTag(@Nullable JDSkuTagEntity jdSkuTagEntity) {
        RelativeLayout relativeLayout = this.tag;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        if (jdSkuTagEntity != null) {
            if (TextUtils.isEmpty(jdSkuTagEntity.getTagImg())) {
                if (TextUtils.isEmpty(jdSkuTagEntity.getTagText())) {
                    return;
                }
                setImageTextTag(jdSkuTagEntity.getTagLeftIcon(), jdSkuTagEntity.getTagText(), jdSkuTagEntity.getTagColorBegin(), jdSkuTagEntity.getTagColorEnd());
                return;
            }
            setSkuImageTag(jdSkuTagEntity.getTagImg());
        }
    }

    public final void setTag(@Nullable RelativeLayout relativeLayout) {
        this.tag = relativeLayout;
    }

    public final void setTagImage(@Nullable SimpleDraweeView simpleDraweeView) {
        this.tagImage = simpleDraweeView;
    }

    public final void setTagText(boolean hasIcon, @Nullable String str, @Nullable String startColor, @Nullable String endColor) {
        setTagTextParams(hasIcon);
        int[] iArr = new int[2];
        try {
            iArr[0] = Color.parseColor(startColor);
            iArr[1] = Color.parseColor(endColor);
        } catch (Exception unused) {
            iArr = TAG_DEFAULT_COLORS;
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
        gradientDrawable.setCornerRadius(this.connerSize);
        TextView textView = this.textView;
        if (textView != null) {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        String replace = str != null ? new Regex("\\\\n").replace(str, "\\\n") : null;
        String replace2 = replace != null ? new Regex(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE).replace(replace, "") : null;
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.setText(replace2);
        }
    }

    public final void setTagTextSize(float f2) {
        this.tagTextSize = f2;
    }

    public final void setTextLeftPadding(int i2) {
        this.textLeftPadding = i2;
    }

    public final void setTextRightPadding(int i2) {
        this.textRightPadding = i2;
    }

    public final void setTextView(@Nullable TextView textView) {
        this.textView = textView;
    }

    public final void setTextViewHeight(int i2) {
        this.textViewHeight = i2;
    }

    public final void setTextViewMinWidth(int i2) {
        this.textViewMinWidth = i2;
    }
}
