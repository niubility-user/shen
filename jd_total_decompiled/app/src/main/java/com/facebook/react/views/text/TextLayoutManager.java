package com.facebook.react.views.text;

import android.content.Context;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.LruCache;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class TextLayoutManager {
    private static final int spannableCacheSize = 100;
    private static final TextPaint sTextPaintInstance = new TextPaint(1);
    private static final Object sSpannableCacheLock = new Object();
    private static LruCache<String, Spannable> sSpannableCache = new LruCache<>(100);

    /* loaded from: classes12.dex */
    public static class SetSpanOperation {
        protected int end;
        protected int start;
        protected ReactSpan what;

        SetSpanOperation(int i2, int i3, ReactSpan reactSpan) {
            this.start = i2;
            this.end = i3;
            this.what = reactSpan;
        }

        public void execute(SpannableStringBuilder spannableStringBuilder, int i2) {
            int i3 = this.start;
            spannableStringBuilder.setSpan(this.what, i3, this.end, ((i2 << 16) & 16711680) | ((i3 == 0 ? 18 : 34) & (-16711681)));
        }
    }

    private static void buildSpannableFromFragment(Context context, ReadableArray readableArray, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list) {
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap map = readableArray.getMap(i2);
            int length = spannableStringBuilder.length();
            TextAttributeProps textAttributeProps = new TextAttributeProps(new ReactStylesDiffMap(map.getMap("textAttributes")));
            spannableStringBuilder.append((CharSequence) TextTransform.apply(map.getString("string"), textAttributeProps.mTextTransform));
            int length2 = spannableStringBuilder.length();
            if (length2 >= length) {
                if (textAttributeProps.mIsColorSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactForegroundColorSpan(textAttributeProps.mColor)));
                }
                if (textAttributeProps.mIsBackgroundColorSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactBackgroundColorSpan(textAttributeProps.mBackgroundColor)));
                }
                if (Build.VERSION.SDK_INT >= 21 && !Float.isNaN(textAttributeProps.mLetterSpacing)) {
                    list.add(new SetSpanOperation(length, length2, new CustomLetterSpacingSpan(textAttributeProps.mLetterSpacing)));
                }
                list.add(new SetSpanOperation(length, length2, new ReactAbsoluteSizeSpan(textAttributeProps.mFontSize)));
                if (textAttributeProps.mFontStyle != -1 || textAttributeProps.mFontWeight != -1 || textAttributeProps.mFontFamily != null) {
                    list.add(new SetSpanOperation(length, length2, new CustomStyleSpan(textAttributeProps.mFontStyle, textAttributeProps.mFontWeight, textAttributeProps.mFontFamily, context.getAssets())));
                }
                if (textAttributeProps.mIsUnderlineTextDecorationSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactUnderlineSpan()));
                }
                if (textAttributeProps.mIsLineThroughTextDecorationSet) {
                    list.add(new SetSpanOperation(length, length2, new ReactStrikethroughSpan()));
                }
                if (textAttributeProps.mTextShadowOffsetDx != 0.0f || textAttributeProps.mTextShadowOffsetDy != 0.0f) {
                    list.add(new SetSpanOperation(length, length2, new ShadowStyleSpan(textAttributeProps.mTextShadowOffsetDx, textAttributeProps.mTextShadowOffsetDy, textAttributeProps.mTextShadowRadius, textAttributeProps.mTextShadowColor)));
                }
                if (!Float.isNaN(textAttributeProps.getEffectiveLineHeight())) {
                    list.add(new SetSpanOperation(length, length2, new CustomLineHeightSpan(textAttributeProps.getEffectiveLineHeight())));
                }
                list.add(new SetSpanOperation(length, length2, new ReactTagSpan(map.getInt("reactTag"))));
            }
        }
    }

    private static Spannable createSpannableFromAttributedString(Context context, ReadableMap readableMap) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        buildSpannableFromFragment(context, readableMap.getArray("fragments"), spannableStringBuilder, arrayList);
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            ((SetSpanOperation) it.next()).execute(spannableStringBuilder, i2);
            i2++;
        }
        return spannableStringBuilder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Spannable getOrCreateSpannableForText(Context context, ReadableMap readableMap) {
        String obj = readableMap.toString();
        Object obj2 = sSpannableCacheLock;
        synchronized (obj2) {
            Spannable spannable = sSpannableCache.get(obj);
            if (spannable != null) {
                return spannable;
            }
            Spannable createSpannableFromAttributedString = createSpannableFromAttributedString(context, readableMap);
            synchronized (obj2) {
                sSpannableCache.put(obj, createSpannableFromAttributedString);
            }
            return createSpannableFromAttributedString;
        }
    }

    public static long measureText(ReactContext reactContext, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
        Layout build;
        int height;
        TextPaint textPaint = sTextPaintInstance;
        Spannable orCreateSpannableForText = getOrCreateSpannableForText(reactContext, readableNativeMap);
        if (orCreateSpannableForText != null) {
            BoringLayout.Metrics isBoring = BoringLayout.isBoring(orCreateSpannableForText, textPaint);
            float desiredWidth = isBoring == null ? Layout.getDesiredWidth(orCreateSpannableForText, textPaint) : Float.NaN;
            boolean z = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f2 < 0.0f;
            if (isBoring == null && (z || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= f2))) {
                int ceil = (int) Math.ceil(desiredWidth);
                if (Build.VERSION.SDK_INT < 23) {
                    build = new StaticLayout(orCreateSpannableForText, textPaint, ceil, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
                } else {
                    build = StaticLayout.Builder.obtain(orCreateSpannableForText, 0, orCreateSpannableForText.length(), textPaint, ceil).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(true).setBreakStrategy(1).setHyphenationFrequency(1).build();
                }
            } else if (isBoring != null && (z || isBoring.width <= f2)) {
                build = BoringLayout.make(orCreateSpannableForText, textPaint, isBoring.width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, isBoring, true);
            } else if (Build.VERSION.SDK_INT < 23) {
                build = new StaticLayout(orCreateSpannableForText, textPaint, (int) f2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            } else {
                build = StaticLayout.Builder.obtain(orCreateSpannableForText, 0, orCreateSpannableForText.length(), textPaint, (int) f2).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(true).setBreakStrategy(1).setHyphenationFrequency(1).build();
            }
            int i2 = readableNativeMap2.hasKey("maximumNumberOfLines") ? readableNativeMap2.getInt("maximumNumberOfLines") : -1;
            float width = build.getWidth();
            if (i2 != -1 && i2 != 0 && i2 < build.getLineCount()) {
                height = build.getLineBottom(i2 - 1);
            } else {
                height = build.getHeight();
            }
            return YogaMeasureOutput.make(PixelUtil.toSPFromPixel(width), PixelUtil.toSPFromPixel(height));
        }
        throw new IllegalStateException("Spannable element has not been prepared in onBeforeLayout");
    }
}
