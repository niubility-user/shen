package com.facebook.react.views.textinput;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.view.ViewCompat;
import androidx.room.FtsOptions;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import javax.annotation.Nullable;

@TargetApi(23)
@VisibleForTesting
/* loaded from: classes12.dex */
public class ReactTextInputShadowNode extends ReactBaseTextShadowNode implements YogaMeasureFunction {
    @VisibleForTesting
    public static final String PROP_PLACEHOLDER = "placeholder";
    @VisibleForTesting
    public static final String PROP_TEXT = "text";
    @Nullable
    private EditText mDummyEditText;
    @Nullable
    private ReactTextInputLocalData mLocalData;
    private int mMostRecentEventCount = -1;
    @Nullable
    private String mText = null;
    @Nullable
    private String mPlaceholder = null;

    public ReactTextInputShadowNode() {
        this.mTextBreakStrategy = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        initMeasureFunction();
    }

    private void initMeasureFunction() {
        setMeasureFunction(this);
    }

    @Nullable
    public String getPlaceholder() {
        return this.mPlaceholder;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtualAnchor() {
        return true;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isYogaLeafNode() {
        return true;
    }

    @Override // com.facebook.yoga.YogaMeasureFunction
    public long measure(YogaNode yogaNode, float f2, YogaMeasureMode yogaMeasureMode, float f3, YogaMeasureMode yogaMeasureMode2) {
        EditText editText = this.mDummyEditText;
        if (editText == null) {
            return YogaMeasureOutput.make(0, 0);
        }
        EditText editText2 = (EditText) Assertions.assertNotNull(editText);
        ReactTextInputLocalData reactTextInputLocalData = this.mLocalData;
        if (reactTextInputLocalData != null) {
            reactTextInputLocalData.apply(editText2);
        } else {
            editText2.setTextSize(0, this.mTextAttributes.getEffectiveFontSize());
            int i2 = this.mNumberOfLines;
            if (i2 != -1) {
                editText2.setLines(i2);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                int breakStrategy = editText2.getBreakStrategy();
                int i3 = this.mTextBreakStrategy;
                if (breakStrategy != i3) {
                    editText2.setBreakStrategy(i3);
                }
            }
        }
        editText2.setHint(getPlaceholder());
        editText2.measure(MeasureUtil.getMeasureSpec(f2, yogaMeasureMode), MeasureUtil.getMeasureSpec(f3, yogaMeasureMode2));
        return YogaMeasureOutput.make(editText2.getMeasuredWidth(), editText2.getMeasuredHeight());
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
        super.onCollectExtraUpdates(uIViewOperationQueue);
        if (this.mMostRecentEventCount != -1) {
            uIViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(ReactBaseTextShadowNode.spannedFromShadowNode(this, getText()), this.mMostRecentEventCount, this.mContainsImages, getPadding(0), getPadding(1), getPadding(2), getPadding(3), this.mTextAlign, this.mTextBreakStrategy, this.mJustificationMode));
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object obj) {
        Assertions.assertCondition(obj instanceof ReactTextInputLocalData);
        this.mLocalData = (ReactTextInputLocalData) obj;
        dirty();
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int i2) {
        this.mMostRecentEventCount = i2;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setPadding(int i2, float f2) {
        super.setPadding(i2, f2);
        markUpdated();
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(@Nullable String str) {
        this.mPlaceholder = str;
        markUpdated();
    }

    @ReactProp(name = "text")
    public void setText(@Nullable String str) {
        this.mText = str;
        markUpdated();
    }

    @Override // com.facebook.react.views.text.ReactBaseTextShadowNode
    public void setTextBreakStrategy(@Nullable String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (str != null && !FtsOptions.TOKENIZER_SIMPLE.equals(str)) {
            if ("highQuality".equals(str)) {
                this.mTextBreakStrategy = 1;
                return;
            } else if ("balanced".equals(str)) {
                this.mTextBreakStrategy = 2;
                return;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + str);
            }
        }
        this.mTextBreakStrategy = 0;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setThemedContext(ThemedReactContext themedReactContext) {
        super.setThemedContext(themedReactContext);
        try {
            EditText editText = new EditText(getThemedContext());
            setDefaultPadding(4, ViewCompat.getPaddingStart(editText));
            setDefaultPadding(1, editText.getPaddingTop());
            setDefaultPadding(5, ViewCompat.getPaddingEnd(editText));
            setDefaultPadding(3, editText.getPaddingBottom());
            this.mDummyEditText = editText;
            editText.setPadding(0, 0, 0, 0);
            this.mDummyEditText.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        } catch (Exception unused) {
        }
    }
}
