package com.facebook.react.views.text;

import android.text.Spannable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.yoga.YogaMeasureMode;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = ReactTextViewManager.REACT_CLASS)
/* loaded from: classes12.dex */
public class ReactTextViewManager extends ReactTextAnchorViewManager<ReactTextView, ReactTextShadowNode> {
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTText";

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("topTextLayout", MapBuilder.of("registrationName", "onTextLayout"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public long measure(ReactContext reactContext, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
        return TextLayoutManager.measureText(reactContext, readableNativeMap, readableNativeMap2, f2, yogaMeasureMode, f3, yogaMeasureMode2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactTextView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactTextView(themedReactContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactTextView reactTextView) {
        super.onAfterUpdateTransaction((ReactTextViewManager) reactTextView);
        reactTextView.updateView();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(ReactTextView reactTextView, Object obj) {
        ReactTextUpdate reactTextUpdate = (ReactTextUpdate) obj;
        if (reactTextUpdate.containsImages()) {
            TextInlineImageSpan.possiblyUpdateInlineImageSpans(reactTextUpdate.getText(), reactTextView);
        }
        reactTextView.setText(reactTextUpdate);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Object updateLocalData(ReactTextView reactTextView, ReactStylesDiffMap reactStylesDiffMap, ReactStylesDiffMap reactStylesDiffMap2) {
        Spannable orCreateSpannableForText = TextLayoutManager.getOrCreateSpannableForText(reactTextView.getContext(), reactStylesDiffMap2.getMap("attributedString"));
        reactTextView.setSpanned(orCreateSpannableForText);
        TextAttributeProps textAttributeProps = new TextAttributeProps(reactStylesDiffMap);
        return new ReactTextUpdate(orCreateSpannableForText, -1, false, textAttributeProps.getStartPadding(), textAttributeProps.getTopPadding(), textAttributeProps.getEndPadding(), textAttributeProps.getBottomPadding(), textAttributeProps.getTextAlign(), 1, 0);
    }
}
