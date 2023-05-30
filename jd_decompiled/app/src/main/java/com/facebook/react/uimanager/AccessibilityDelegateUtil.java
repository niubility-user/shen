package com.facebook.react.uimanager;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.facebook.react.R;
import java.util.Locale;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class AccessibilityDelegateUtil {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.uimanager.AccessibilityDelegateUtil$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole;

        static {
            int[] iArr = new int[AccessibilityRole.values().length];
            $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole = iArr;
            try {
                iArr[AccessibilityRole.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.BUTTON.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.LINK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.SEARCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.IMAGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.IMAGEBUTTON.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.KEYBOARDKEY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.TEXT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.ADJUSTABLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.SUMMARY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[AccessibilityRole.HEADER.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum AccessibilityRole {
        NONE,
        BUTTON,
        LINK,
        SEARCH,
        IMAGE,
        IMAGEBUTTON,
        KEYBOARDKEY,
        TEXT,
        ADJUSTABLE,
        SUMMARY,
        HEADER;

        public static AccessibilityRole fromValue(@Nullable String str) {
            for (AccessibilityRole accessibilityRole : values()) {
                if (accessibilityRole.name().equalsIgnoreCase(str)) {
                    return accessibilityRole;
                }
            }
            throw new IllegalArgumentException("Invalid accessibility role value: " + str);
        }

        public static String getValue(AccessibilityRole accessibilityRole) {
            switch (AnonymousClass2.$SwitchMap$com$facebook$react$uimanager$AccessibilityDelegateUtil$AccessibilityRole[accessibilityRole.ordinal()]) {
                case 1:
                    return null;
                case 2:
                    return "android.widget.Button";
                case 3:
                    return "android.widget.ViewGroup";
                case 4:
                    return "android.widget.EditText";
                case 5:
                case 6:
                    return "android.widget.ImageView";
                case 7:
                    return "android.inputmethodservice.Keyboard$Key";
                case 8:
                    return "android.widget.ViewGroup";
                case 9:
                    return "android.widget.SeekBar";
                case 10:
                case 11:
                    return "android.widget.ViewGroup";
                default:
                    throw new IllegalArgumentException("Invalid accessibility role value: " + accessibilityRole);
            }
        }
    }

    private AccessibilityDelegateUtil() {
    }

    public static void setDelegate(final View view) {
        final String str = (String) view.getTag(R.id.accessibility_hint);
        final AccessibilityRole accessibilityRole = (AccessibilityRole) view.getTag(R.id.accessibility_role);
        if (ViewCompat.hasAccessibilityDelegate(view)) {
            return;
        }
        if (str == null && accessibilityRole == null) {
            return;
        }
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: com.facebook.react.uimanager.AccessibilityDelegateUtil.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                if (str != null) {
                    String str2 = (String) accessibilityNodeInfoCompat.getContentDescription();
                    if (str2 != null) {
                        accessibilityNodeInfoCompat.setContentDescription(str2 + ", " + str);
                    } else {
                        accessibilityNodeInfoCompat.setContentDescription(str);
                    }
                }
                AccessibilityDelegateUtil.setRole(accessibilityNodeInfoCompat, accessibilityRole, view.getContext());
            }
        });
    }

    public static void setRole(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityRole accessibilityRole, Context context) {
        if (accessibilityRole == null) {
            accessibilityRole = AccessibilityRole.NONE;
        }
        accessibilityNodeInfoCompat.setClassName(AccessibilityRole.getValue(accessibilityRole));
        if (Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
            if (accessibilityRole.equals(AccessibilityRole.LINK)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.link_description));
                if (accessibilityNodeInfoCompat.getContentDescription() != null) {
                    SpannableString spannableString = new SpannableString(accessibilityNodeInfoCompat.getContentDescription());
                    spannableString.setSpan(new URLSpan(""), 0, spannableString.length(), 0);
                    accessibilityNodeInfoCompat.setContentDescription(spannableString);
                }
                if (accessibilityNodeInfoCompat.getText() != null) {
                    SpannableString spannableString2 = new SpannableString(accessibilityNodeInfoCompat.getText());
                    spannableString2.setSpan(new URLSpan(""), 0, spannableString2.length(), 0);
                    accessibilityNodeInfoCompat.setText(spannableString2);
                }
            }
            if (accessibilityRole.equals(AccessibilityRole.SEARCH)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.search_description));
            }
            if (accessibilityRole.equals(AccessibilityRole.IMAGE)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.image_description));
            }
            if (accessibilityRole.equals(AccessibilityRole.IMAGEBUTTON)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.image_button_description));
            }
            if (accessibilityRole.equals(AccessibilityRole.ADJUSTABLE)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.adjustable_description));
            }
            if (accessibilityRole.equals(AccessibilityRole.HEADER)) {
                accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.header_description));
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, 0, 1, true));
            }
        }
        if (accessibilityRole.equals(AccessibilityRole.IMAGEBUTTON)) {
            accessibilityNodeInfoCompat.setClickable(true);
        }
    }
}
