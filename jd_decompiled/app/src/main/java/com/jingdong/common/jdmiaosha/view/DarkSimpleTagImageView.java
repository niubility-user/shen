package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.R;
import com.jingdong.common.jdmiaosha.utils.DarkUtil;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 I2\u00020\u0001:\u0001IB\u001b\u0012\b\u0010D\u001a\u0004\u0018\u00010?\u0012\b\u0010F\u001a\u0004\u0018\u00010E\u00a2\u0006\u0004\bG\u0010HJ)\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007JU\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0014J_\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u000eH\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0017JK\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u000eH\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001b\u001a\u00020\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u00002\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020\u00002\b\u0010 \u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b!\u0010\u001fJ\u0019\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b#\u0010$J\u0019\u0010&\u001a\u00020\u00002\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b&\u0010$J\u0019\u0010)\u001a\u00020\u001a2\b\u0010(\u001a\u0004\u0018\u00010'H\u0014\u00a2\u0006\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010.\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010/R\u0016\u00100\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0018\u00102\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0018\u00104\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00101R\u0016\u00107\u001a\u00020+8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u0010-R\u0016\u00108\u001a\u00020+8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u0010-R\u0016\u00109\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00101R\u0016\u0010;\u001a\u00020:8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u00101R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u00105R\u0016\u0010>\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u00101R\u0018\u0010@\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u00101R\u0018\u0010%\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u00105R\u0016\u0010C\u001a\u00020+8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010-\u00a8\u0006J"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/DarkSimpleTagImageView;", "Lcom/jingdong/common/jdmiaosha/view/DarkWhiteBgImageView;", "", "drawableId", "drawableDarkId", "Landroid/graphics/Bitmap;", "getTagBitmap", "(Ljava/lang/Integer;Ljava/lang/Integer;)Landroid/graphics/Bitmap;", "textDarkColor", DYConstants.DY_TEXT_COLOR, "", DYConstants.DY_TEXT_SIZE, "Landroid/graphics/Paint$Align;", ViewProps.TEXT_ALIGN, "", "isAntiAlias", "isNeedTypeFace", "", "typeface", "setTagDescPaint", "(IIFLandroid/graphics/Paint$Align;ZZLjava/lang/String;)Lcom/jingdong/common/jdmiaosha/view/DarkSimpleTagImageView;", "isFakeBoldText", "setTagPricePaint", "(IIFLandroid/graphics/Paint$Align;ZZLjava/lang/String;Z)Lcom/jingdong/common/jdmiaosha/view/DarkSimpleTagImageView;", "setTagSymbolPaint", "(IIFLandroid/graphics/Paint$Align;ZZ)Lcom/jingdong/common/jdmiaosha/view/DarkSimpleTagImageView;", "", "configBigDrawableDefault", "()V", "drawabelTagId", "setDrawableTagId", "(Ljava/lang/Integer;)Lcom/jingdong/common/jdmiaosha/view/DarkSimpleTagImageView;", "drawabelDarkTagId", "setDrawableDarkTagId", "tagDesc", "setTagDesc", "(Ljava/lang/String;)Lcom/jingdong/common/jdmiaosha/view/DarkSimpleTagImageView;", "tagPrice", "setTagPrice", "Landroid/graphics/Canvas;", "canvas", "onDraw", "(Landroid/graphics/Canvas;)V", "Landroid/graphics/Paint;", "paintTagDesc", "Landroid/graphics/Paint;", "drawabelTagDarkId", "Ljava/lang/Integer;", "textStmbolColor", "I", "mBitmap", "Landroid/graphics/Bitmap;", "tagSymbol", "Ljava/lang/String;", "textDescColor", "paintTagSymbol", "paintBitmap", "textPriceColor", "Landroid/graphics/Rect;", "rectBitmap", "Landroid/graphics/Rect;", "textDescDarkColor", "textStmbolDarkColor", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "textPriceDarkColor", "paintTagPrice", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class DarkSimpleTagImageView extends DarkWhiteBgImageView {
    private static final String DEFAULT_TAGDESC = "\u9884\u4f30\u5230\u624b\u4ef7";
    private static final String DEFAULT_TEXTTYPEFACE = "fonts/JDZhengHT-Regular.ttf";
    private HashMap _$_findViewCache;
    private Integer drawabelTagDarkId;
    private Integer drawabelTagId;
    private Bitmap mBitmap;
    private Context mContext;
    private final Paint paintBitmap;
    private final Paint paintTagDesc;
    private final Paint paintTagPrice;
    private final Paint paintTagSymbol;
    private final Rect rectBitmap;
    private String tagDesc;
    private String tagPrice;
    private String tagSymbol;
    private int textDescColor;
    private int textDescDarkColor;
    private int textPriceColor;
    private int textPriceDarkColor;
    private int textStmbolColor;
    private int textStmbolDarkColor;
    private static final String RMB = StringUtil.getString(R.string.yangjiao);
    private static final float DEFAULT_TAGDESCTEXTSIZE = DPIUtil.dip2px(9.0f);
    private static final int DEFAULT_TAGDESCTEXTCOLOR = Color.parseColor("#FFFFFF");
    private static final int DEFAULT_TAGDESCTEXTDARKCOLOR = Color.parseColor(JDDarkUtil.COLOR_ECECEC);
    private static final float DEFAULT_TAGPRICETEXTSIZE = DPIUtil.dip2px(12.0f);
    private static final float DEFAULT_TAGSYMBOLTEXTSIZE = DPIUtil.dip2px(8.0f);
    private static final int DEFAULT_TAGPRICETEXTCOLOR = Color.parseColor("#F2270C");
    private static final int DEFAULT_TAGPRICE_SYMBOL_TEXTDARKCOLOR = Color.parseColor("#DE1616");
    private static final int DEFAULT_DRAWABELTAGID = R.drawable.seckill_tag_bg;
    private static final int DEFAULT_DRAWABELTAGDARKID = R.drawable.seckill_tag_bg_dark;
    private static final int DEFAULT_BIG_DRAWABLE_TAG_ID = R.drawable.brand_inner_product_tag_bg;
    private static final int DEFAULT_BIG_DRAWABLE_DARK_TAG_ID = R.drawable.brand_inner_product_tag_bg_dark;
    private static final float DEFAULT_TAG_BIG_PRICETEXTSIZE = DPIUtil.dip2px(18.0f);
    private static final float DEFAULT_TAG_BIG_DESCTEXTSIZE = DPIUtil.dip2px(12.0f);
    private static final float DEFAULT_TAG_BIG_SYMBOLTEXTSIZE = DPIUtil.dip2px(11.0f);

    public DarkSimpleTagImageView(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.paintBitmap = new Paint();
        this.paintTagDesc = new Paint();
        this.paintTagPrice = new Paint();
        this.paintTagSymbol = new Paint();
        this.rectBitmap = new Rect();
        this.drawabelTagId = Integer.valueOf(DEFAULT_DRAWABELTAGID);
        this.drawabelTagDarkId = Integer.valueOf(DEFAULT_DRAWABELTAGDARKID);
        this.tagDesc = DEFAULT_TAGDESC;
        this.tagPrice = "";
        this.tagSymbol = RMB;
        int i2 = DEFAULT_TAGDESCTEXTCOLOR;
        this.textDescColor = i2;
        this.textDescDarkColor = DEFAULT_TAGDESCTEXTDARKCOLOR;
        this.textStmbolColor = i2;
        int i3 = DEFAULT_TAGPRICE_SYMBOL_TEXTDARKCOLOR;
        this.textStmbolDarkColor = i3;
        this.textPriceColor = DEFAULT_TAGPRICETEXTCOLOR;
        this.textPriceDarkColor = i3;
        setTagDescPaint$default(this, 0, 0, 0.0f, null, false, false, null, 127, null);
        setTagSymbolPaint$default(this, 0, 0, 0.0f, null, false, false, 63, null);
        setTagPricePaint$default(this, 0, 0, 0.0f, null, false, false, null, false, 255, null);
    }

    private final Bitmap getTagBitmap(Integer drawableId, Integer drawableDarkId) {
        if (drawableId == null || drawableDarkId == null) {
            return null;
        }
        this.drawabelTagId = drawableId;
        this.drawabelTagDarkId = drawableDarkId;
        if (DarkUtil.isDarkTheme()) {
            Context context = getContext();
            return BitmapFactory.decodeResource(context != null ? context.getResources() : null, drawableDarkId.intValue());
        }
        Context context2 = getContext();
        return BitmapFactory.decodeResource(context2 != null ? context2.getResources() : null, drawableId.intValue());
    }

    static /* synthetic */ Bitmap getTagBitmap$default(DarkSimpleTagImageView darkSimpleTagImageView, Integer num, Integer num2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = darkSimpleTagImageView.drawabelTagId;
        }
        if ((i2 & 2) != 0) {
            num2 = darkSimpleTagImageView.drawabelTagDarkId;
        }
        return darkSimpleTagImageView.getTagBitmap(num, num2);
    }

    public static /* synthetic */ DarkSimpleTagImageView setTagDesc$default(DarkSimpleTagImageView darkSimpleTagImageView, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = DEFAULT_TAGDESC;
        }
        return darkSimpleTagImageView.setTagDesc(str);
    }

    public static /* synthetic */ DarkSimpleTagImageView setTagDescPaint$default(DarkSimpleTagImageView darkSimpleTagImageView, int i2, int i3, float f2, Paint.Align align, boolean z, boolean z2, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = DEFAULT_TAGDESCTEXTDARKCOLOR;
        }
        if ((i4 & 2) != 0) {
            i3 = DEFAULT_TAGDESCTEXTCOLOR;
        }
        int i5 = i3;
        if ((i4 & 4) != 0) {
            f2 = DEFAULT_TAGDESCTEXTSIZE;
        }
        float f3 = f2;
        if ((i4 & 8) != 0) {
            align = Paint.Align.LEFT;
        }
        Paint.Align align2 = align;
        boolean z3 = (i4 & 16) != 0 ? true : z;
        boolean z4 = (i4 & 32) != 0 ? false : z2;
        if ((i4 & 64) != 0) {
            str = DEFAULT_TEXTTYPEFACE;
        }
        return darkSimpleTagImageView.setTagDescPaint(i2, i5, f3, align2, z3, z4, str);
    }

    public static /* synthetic */ DarkSimpleTagImageView setTagPrice$default(DarkSimpleTagImageView darkSimpleTagImageView, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        return darkSimpleTagImageView.setTagPrice(str);
    }

    public static /* synthetic */ DarkSimpleTagImageView setTagPricePaint$default(DarkSimpleTagImageView darkSimpleTagImageView, int i2, int i3, float f2, Paint.Align align, boolean z, boolean z2, String str, boolean z3, int i4, Object obj) {
        return darkSimpleTagImageView.setTagPricePaint((i4 & 1) != 0 ? DEFAULT_TAGPRICE_SYMBOL_TEXTDARKCOLOR : i2, (i4 & 2) != 0 ? DEFAULT_TAGPRICETEXTCOLOR : i3, (i4 & 4) != 0 ? DEFAULT_TAGPRICETEXTSIZE : f2, (i4 & 8) != 0 ? Paint.Align.RIGHT : align, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? true : z2, (i4 & 64) != 0 ? DEFAULT_TEXTTYPEFACE : str, (i4 & 128) == 0 ? z3 : true);
    }

    public static /* synthetic */ DarkSimpleTagImageView setTagSymbolPaint$default(DarkSimpleTagImageView darkSimpleTagImageView, int i2, int i3, float f2, Paint.Align align, boolean z, boolean z2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = DEFAULT_TAGPRICE_SYMBOL_TEXTDARKCOLOR;
        }
        if ((i4 & 2) != 0) {
            i3 = DEFAULT_TAGPRICETEXTCOLOR;
        }
        int i5 = i3;
        if ((i4 & 4) != 0) {
            f2 = DEFAULT_TAGSYMBOLTEXTSIZE;
        }
        float f3 = f2;
        if ((i4 & 8) != 0) {
            align = Paint.Align.RIGHT;
        }
        return darkSimpleTagImageView.setTagSymbolPaint(i2, i5, f3, align, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? true : z2);
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

    public final void configBigDrawableDefault() {
        float f2 = DEFAULT_TAG_BIG_DESCTEXTSIZE;
        setTagDescPaint$default(this, 0, 0, f2, null, false, false, null, 123, null);
        setTagSymbolPaint$default(this, 0, 0, f2, null, false, false, 59, null);
        setTagPricePaint$default(this, 0, 0, DEFAULT_TAG_BIG_PRICETEXTSIZE, null, false, false, null, false, 251, null);
        setDrawableTagId(Integer.valueOf(DEFAULT_BIG_DRAWABLE_TAG_ID));
        setDrawableDarkTagId(Integer.valueOf(DEFAULT_BIG_DRAWABLE_DARK_TAG_ID));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.jdmiaosha.view.DarkWhiteBgImageView, android.widget.ImageView, android.view.View
    public void onDraw(@Nullable Canvas canvas) {
        Rect bounds;
        Rect bounds2;
        super.onDraw(canvas);
        if (TextUtils.isEmpty(this.tagPrice) || TextUtils.isEmpty(this.tagDesc)) {
            return;
        }
        Drawable drawable = getDrawable();
        int i2 = (drawable == null || (bounds2 = drawable.getBounds()) == null) ? 0 : bounds2.right;
        int i3 = (drawable == null || (bounds = drawable.getBounds()) == null) ? 0 : bounds.bottom;
        this.paintTagDesc.measureText(this.tagDesc);
        float measureText = this.paintTagPrice.measureText(this.tagPrice);
        float measureText2 = this.paintTagSymbol.measureText(this.tagSymbol) + measureText;
        if (measureText2 >= i2 / 2) {
            return;
        }
        Bitmap tagBitmap = getTagBitmap(this.drawabelTagId, this.drawabelTagDarkId);
        this.mBitmap = tagBitmap;
        if (tagBitmap == null) {
            return;
        }
        float f2 = i2;
        int height = (int) ((tagBitmap != null ? tagBitmap.getHeight() : 0) * (f2 / (this.mBitmap != null ? r6.getWidth() : 0)));
        Rect rect = this.rectBitmap;
        rect.top = i3 - height;
        rect.left = 0;
        rect.right = i2;
        rect.bottom = i3 + 1;
        this.paintBitmap.setAntiAlias(true);
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && canvas != null) {
            canvas.drawBitmap(bitmap, (Rect) null, this.rectBitmap, this.paintBitmap);
        }
        this.paintTagPrice.setTextAlign(Paint.Align.CENTER);
        this.paintTagSymbol.setTextAlign(Paint.Align.CENTER);
        this.paintTagDesc.setTextAlign(Paint.Align.CENTER);
        float dip2px = i3 - ((height / 4) + DPIUtil.dip2px(1.0f));
        float strokeWidth = this.paintTagDesc.getStrokeWidth();
        float strokeWidth2 = this.paintTagSymbol.getStrokeWidth();
        float strokeWidth3 = this.paintTagPrice.getStrokeWidth();
        float f3 = 4;
        float f4 = (f2 / f3) + strokeWidth;
        this.paintTagPrice.setColor(!DarkUtil.isDarkTheme() ? this.textPriceColor : this.textPriceDarkColor);
        this.paintTagDesc.setColor(!DarkUtil.isDarkTheme() ? this.textDescColor : this.textDescDarkColor);
        this.paintTagSymbol.setColor(!DarkUtil.isDarkTheme() ? this.textStmbolColor : this.textStmbolDarkColor);
        String str = this.tagDesc;
        if (str != null && canvas != null) {
            canvas.drawText(str, f4, dip2px, this.paintTagDesc);
        }
        float f5 = (f2 * 3) / f3;
        float f6 = 2;
        float f7 = (f5 - (measureText / f6)) + strokeWidth2 + strokeWidth3;
        String str2 = this.tagSymbol;
        if (str2 != null && canvas != null) {
            canvas.drawText(str2, f7, dip2px, this.paintTagSymbol);
        }
        String str3 = this.tagPrice;
        if (str3 != null && canvas != null) {
            canvas.drawText(str3, f7 + (measureText2 / f6) + strokeWidth2 + strokeWidth3, dip2px, this.paintTagPrice);
        }
        if (canvas != null) {
            canvas.save();
        }
    }

    @NotNull
    public final DarkSimpleTagImageView setDrawableDarkTagId(@Nullable Integer drawabelDarkTagId) {
        this.drawabelTagDarkId = drawabelDarkTagId;
        return this;
    }

    @NotNull
    public final DarkSimpleTagImageView setDrawableTagId(@Nullable Integer drawabelTagId) {
        this.drawabelTagId = drawabelTagId;
        return this;
    }

    @NotNull
    public final DarkSimpleTagImageView setTagDesc(@Nullable String tagDesc) {
        this.tagDesc = tagDesc;
        return this;
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagDescPaint() {
        return setTagDescPaint$default(this, 0, 0, 0.0f, null, false, false, null, 127, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagDescPaint(int i2) {
        return setTagDescPaint$default(this, i2, 0, 0.0f, null, false, false, null, 126, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagDescPaint(int i2, int i3) {
        return setTagDescPaint$default(this, i2, i3, 0.0f, null, false, false, null, 124, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagDescPaint(int i2, int i3, float f2) {
        return setTagDescPaint$default(this, i2, i3, f2, null, false, false, null, 120, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagDescPaint(int i2, int i3, float f2, @NotNull Paint.Align align) {
        return setTagDescPaint$default(this, i2, i3, f2, align, false, false, null, 112, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagDescPaint(int i2, int i3, float f2, @NotNull Paint.Align align, boolean z) {
        return setTagDescPaint$default(this, i2, i3, f2, align, z, false, null, 96, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagDescPaint(int i2, int i3, float f2, @NotNull Paint.Align align, boolean z, boolean z2) {
        return setTagDescPaint$default(this, i2, i3, f2, align, z, z2, null, 64, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagDescPaint(int textDarkColor, int textColor, float textSize, @NotNull Paint.Align textAlign, boolean isAntiAlias, boolean isNeedTypeFace, @NotNull String typeface) {
        if (isNeedTypeFace) {
            Context context = this.mContext;
            this.paintTagPrice.setTypeface(Typeface.createFromAsset(context != null ? context.getAssets() : null, typeface));
        }
        this.textDescColor = textColor;
        this.textDescDarkColor = textDarkColor;
        this.paintTagDesc.setAntiAlias(isAntiAlias);
        this.paintTagDesc.setTextSize(textSize);
        this.paintTagDesc.setTextAlign(textAlign);
        return this;
    }

    @NotNull
    public final DarkSimpleTagImageView setTagPrice(@Nullable String tagPrice) {
        if (TextUtils.isEmpty(tagPrice)) {
            this.tagPrice = "";
        } else {
            this.tagPrice = tagPrice;
        }
        return this;
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint() {
        return setTagPricePaint$default(this, 0, 0, 0.0f, null, false, false, null, false, 255, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint(int i2) {
        return setTagPricePaint$default(this, i2, 0, 0.0f, null, false, false, null, false, 254, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint(int i2, int i3) {
        return setTagPricePaint$default(this, i2, i3, 0.0f, null, false, false, null, false, 252, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint(int i2, int i3, float f2) {
        return setTagPricePaint$default(this, i2, i3, f2, null, false, false, null, false, 248, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint(int i2, int i3, float f2, @NotNull Paint.Align align) {
        return setTagPricePaint$default(this, i2, i3, f2, align, false, false, null, false, 240, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint(int i2, int i3, float f2, @NotNull Paint.Align align, boolean z) {
        return setTagPricePaint$default(this, i2, i3, f2, align, z, false, null, false, 224, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint(int i2, int i3, float f2, @NotNull Paint.Align align, boolean z, boolean z2) {
        return setTagPricePaint$default(this, i2, i3, f2, align, z, z2, null, false, 192, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint(int i2, int i3, float f2, @NotNull Paint.Align align, boolean z, boolean z2, @NotNull String str) {
        return setTagPricePaint$default(this, i2, i3, f2, align, z, z2, str, false, 128, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagPricePaint(int textDarkColor, int textColor, float textSize, @NotNull Paint.Align textAlign, boolean isAntiAlias, boolean isNeedTypeFace, @NotNull String typeface, boolean isFakeBoldText) {
        if (isNeedTypeFace) {
            Context context = this.mContext;
            this.paintTagPrice.setTypeface(Typeface.createFromAsset(context != null ? context.getAssets() : null, typeface));
        }
        this.textPriceColor = textColor;
        this.textPriceDarkColor = textDarkColor;
        this.paintTagPrice.setAntiAlias(isAntiAlias);
        this.paintTagPrice.setTextSize(textSize);
        this.paintTagPrice.setTextAlign(textAlign);
        this.paintTagPrice.setFakeBoldText(isFakeBoldText);
        return this;
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagSymbolPaint() {
        return setTagSymbolPaint$default(this, 0, 0, 0.0f, null, false, false, 63, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagSymbolPaint(int i2) {
        return setTagSymbolPaint$default(this, i2, 0, 0.0f, null, false, false, 62, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagSymbolPaint(int i2, int i3) {
        return setTagSymbolPaint$default(this, i2, i3, 0.0f, null, false, false, 60, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagSymbolPaint(int i2, int i3, float f2) {
        return setTagSymbolPaint$default(this, i2, i3, f2, null, false, false, 56, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagSymbolPaint(int i2, int i3, float f2, @NotNull Paint.Align align) {
        return setTagSymbolPaint$default(this, i2, i3, f2, align, false, false, 48, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagSymbolPaint(int i2, int i3, float f2, @NotNull Paint.Align align, boolean z) {
        return setTagSymbolPaint$default(this, i2, i3, f2, align, z, false, 32, null);
    }

    @JvmOverloads
    @NotNull
    public final DarkSimpleTagImageView setTagSymbolPaint(int textDarkColor, int textColor, float textSize, @NotNull Paint.Align textAlign, boolean isAntiAlias, boolean isFakeBoldText) {
        this.textStmbolColor = textColor;
        this.textStmbolDarkColor = textDarkColor;
        this.paintTagSymbol.setAntiAlias(isAntiAlias);
        this.paintTagSymbol.setTextSize(textSize);
        this.paintTagSymbol.setTextAlign(textAlign);
        this.paintTagSymbol.setFakeBoldText(isFakeBoldText);
        return this;
    }
}
