package com.facebook.react.views.checkbox;

import android.widget.CompoundButton;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes12.dex */
public class ReactCheckBoxManager extends SimpleViewManager<ReactCheckBox> {
    private static final CompoundButton.OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new CompoundButton.OnCheckedChangeListener() { // from class: com.facebook.react.views.checkbox.ReactCheckBoxManager.1
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ((UIManagerModule) ((ReactContext) compoundButton.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactCheckBoxEvent(compoundButton.getId(), z));
        }
    };
    private static final String REACT_CLASS = "AndroidCheckBox";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.ENABLED)
    public void setEnabled(ReactCheckBox reactCheckBox, boolean z) {
        reactCheckBox.setEnabled(z);
    }

    @ReactProp(name = "on")
    public void setOn(ReactCheckBox reactCheckBox, boolean z) {
        reactCheckBox.setOnCheckedChangeListener(null);
        reactCheckBox.setOn(z);
        reactCheckBox.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactCheckBox reactCheckBox) {
        reactCheckBox.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ReactCheckBox createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactCheckBox(themedReactContext);
    }
}
