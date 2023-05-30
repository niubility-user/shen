package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import javax.annotation.Nullable;

@TargetApi(23)
/* loaded from: classes12.dex */
public class ReactTextShadowNode extends ReactBaseTextShadowNode {
    private static final TextPaint sTextPaintInstance = new TextPaint(1);
    @Nullable
    private Spannable mPreparedSpannableText;
    private boolean mShouldNotifyOnTextLayout;
    private final YogaMeasureFunction mTextMeasureFunction = new YogaMeasureFunction() { // from class: com.facebook.react.views.text.ReactTextShadowNode.1
        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
            Layout build;
            int height;
            TextPaint textPaint = ReactTextShadowNode.sTextPaintInstance;
            textPaint.setTextSize(ReactTextShadowNode.this.mTextAttributes.getEffectiveFontSize());
            Spanned spanned = (Spanned) Assertions.assertNotNull(ReactTextShadowNode.this.mPreparedSpannableText, "Spannable element has not been prepared in onBeforeLayout");
            BoringLayout.Metrics isBoring = BoringLayout.isBoring(spanned, textPaint);
            float desiredWidth = isBoring == null ? Layout.getDesiredWidth(spanned, textPaint) : Float.NaN;
            boolean z = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f2 < 0.0f;
            Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
            int textAlign = ReactTextShadowNode.this.getTextAlign();
            if (textAlign == 1) {
                alignment = Layout.Alignment.ALIGN_CENTER;
            } else if (textAlign == 3) {
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else if (textAlign == 5) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            }
            Layout.Alignment alignment2 = alignment;
            if (isBoring == null && (z || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= f2))) {
                int ceil = (int) Math.ceil(desiredWidth);
                int i2 = Build.VERSION.SDK_INT;
                if (i2 < 23) {
                    build = new StaticLayout(spanned, textPaint, ceil, alignment2, 1.0f, 0.0f, ReactTextShadowNode.this.mIncludeFontPadding);
                } else {
                    StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(spanned, 0, spanned.length(), textPaint, ceil).setAlignment(alignment2).setLineSpacing(0.0f, 1.0f).setIncludePad(ReactTextShadowNode.this.mIncludeFontPadding).setBreakStrategy(ReactTextShadowNode.this.mTextBreakStrategy).setHyphenationFrequency(1);
                    if (i2 >= 26) {
                        hyphenationFrequency.setJustificationMode(ReactTextShadowNode.this.mJustificationMode);
                    }
                    build = hyphenationFrequency.build();
                }
            } else if (isBoring != null && (z || isBoring.width <= f2)) {
                build = BoringLayout.make(spanned, textPaint, isBoring.width, alignment2, 1.0f, 0.0f, isBoring, ReactTextShadowNode.this.mIncludeFontPadding);
            } else {
                int i3 = Build.VERSION.SDK_INT;
                if (i3 < 23) {
                    build = new StaticLayout(spanned, textPaint, (int) f2, alignment2, 1.0f, 0.0f, ReactTextShadowNode.this.mIncludeFontPadding);
                } else {
                    StaticLayout.Builder hyphenationFrequency2 = StaticLayout.Builder.obtain(spanned, 0, spanned.length(), textPaint, (int) f2).setAlignment(alignment2).setLineSpacing(0.0f, 1.0f).setIncludePad(ReactTextShadowNode.this.mIncludeFontPadding).setBreakStrategy(ReactTextShadowNode.this.mTextBreakStrategy).setHyphenationFrequency(1);
                    if (i3 >= 28 && ReactTextShadowNode.this.mTextAttributes.isUseLineSpacingFromFallbacks()) {
                        hyphenationFrequency2.setUseLineSpacingFromFallbacks(true);
                    }
                    build = hyphenationFrequency2.build();
                }
            }
            if (ReactTextShadowNode.this.mShouldNotifyOnTextLayout) {
                WritableArray fontMetrics = FontMetricsUtil.getFontMetrics(spanned, build, ReactTextShadowNode.sTextPaintInstance, ReactTextShadowNode.this.getThemedContext());
                WritableMap createMap = Arguments.createMap();
                createMap.putArray("lines", fontMetrics);
                ((RCTEventEmitter) ReactTextShadowNode.this.getThemedContext().getJSModule(RCTEventEmitter.class)).receiveEvent(ReactTextShadowNode.this.getReactTag(), "topTextLayout", createMap);
            }
            int i4 = ReactTextShadowNode.this.mNumberOfLines;
            if (i4 != -1 && i4 < build.getLineCount()) {
                try {
                    height = build.getLineBottom(ReactTextShadowNode.this.mNumberOfLines - 1);
                } catch (Exception unused) {
                    height = build.getHeight();
                }
                return YogaMeasureOutput.make(build.getWidth(), height);
            }
            return YogaMeasureOutput.make(build.getWidth(), build.getHeight());
        }
    };

    public ReactTextShadowNode() {
        initMeasureFunction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTextAlign() {
        int i2 = this.mTextAlign;
        if (getLayoutDirection() == YogaDirection.RTL) {
            if (i2 == 5) {
                return 3;
            }
            if (i2 == 3) {
                return 5;
            }
            return i2;
        }
        return i2;
    }

    private void initMeasureFunction() {
        if (isVirtual()) {
            return;
        }
        setMeasureFunction(this.mTextMeasureFunction);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtualAnchor() {
        return true;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void markUpdated() {
        super.markUpdated();
        super.dirty();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onBeforeLayout() {
        this.mPreparedSpannableText = ReactBaseTextShadowNode.spannedFromShadowNode(this, null);
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
        super.onCollectExtraUpdates(uIViewOperationQueue);
        Spannable spannable = this.mPreparedSpannableText;
        if (spannable != null) {
            uIViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(spannable, -1, this.mContainsImages, getPadding(4), getPadding(1), getPadding(5), getPadding(3), getTextAlign(), this.mTextBreakStrategy, this.mJustificationMode));
        }
    }

    @ReactProp(name = "onTextLayout")
    public void setShouldNotifyOnTextLayout(boolean z) {
        this.mShouldNotifyOnTextLayout = z;
    }
}
