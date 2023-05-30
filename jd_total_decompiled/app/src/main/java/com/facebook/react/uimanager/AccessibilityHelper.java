package com.facebook.react.uimanager;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.RadioButton;

/* loaded from: classes12.dex */
class AccessibilityHelper {
    private static final String BUTTON = "button";
    private static final String RADIOBUTTON_CHECKED = "radiobutton_checked";
    private static final String RADIOBUTTON_UNCHECKED = "radiobutton_unchecked";
    private static final View.AccessibilityDelegate BUTTON_DELEGATE = new View.AccessibilityDelegate() { // from class: com.facebook.react.uimanager.AccessibilityHelper.1
        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(Button.class.getName());
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(Button.class.getName());
        }
    };
    private static final View.AccessibilityDelegate RADIOBUTTON_CHECKED_DELEGATE = new View.AccessibilityDelegate() { // from class: com.facebook.react.uimanager.AccessibilityHelper.2
        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(RadioButton.class.getName());
            accessibilityEvent.setChecked(true);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(RadioButton.class.getName());
            accessibilityNodeInfo.setCheckable(true);
            accessibilityNodeInfo.setChecked(true);
        }
    };
    private static final View.AccessibilityDelegate RADIOBUTTON_UNCHECKED_DELEGATE = new View.AccessibilityDelegate() { // from class: com.facebook.react.uimanager.AccessibilityHelper.3
        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(RadioButton.class.getName());
            accessibilityEvent.setChecked(false);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(RadioButton.class.getName());
            accessibilityNodeInfo.setCheckable(true);
            accessibilityNodeInfo.setChecked(false);
        }
    };

    AccessibilityHelper() {
    }

    public static void sendAccessibilityEvent(View view, int i2) {
        view.sendAccessibilityEvent(i2);
    }

    public static void updateAccessibilityComponentType(View view, String str) {
        if (str == null) {
            view.setAccessibilityDelegate(null);
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1377687758:
                if (str.equals(BUTTON)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1320494052:
                if (str.equals(RADIOBUTTON_UNCHECKED)) {
                    c2 = 1;
                    break;
                }
                break;
            case -714126251:
                if (str.equals(RADIOBUTTON_CHECKED)) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                view.setAccessibilityDelegate(BUTTON_DELEGATE);
                return;
            case 1:
                view.setAccessibilityDelegate(RADIOBUTTON_UNCHECKED_DELEGATE);
                return;
            case 2:
                view.setAccessibilityDelegate(RADIOBUTTON_CHECKED_DELEGATE);
                return;
            default:
                view.setAccessibilityDelegate(null);
                return;
        }
    }
}
