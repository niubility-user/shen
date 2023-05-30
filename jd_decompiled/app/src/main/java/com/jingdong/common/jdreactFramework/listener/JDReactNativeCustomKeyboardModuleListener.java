package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.textinput.ReactEditText;
import com.jd.lib.un.utils.UnScreenUtils;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.common.jdreactFramework.views.JDCustomKeyBoardView;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeCustomKeyboardModuleListener implements NativeCustomKeyboardModuleListener, JDFlutterCall {
    public static final String OK_EVENT_NAME = "customKeyboardClickOk";
    public static final String TEXTINPUT_GET_FOCUS = "textinputgetfocus";
    public static final String TEXTINPUT_LOST_FOCUS = "textinputlostfocus";
    private final int TAG_ID = -559038801;
    private boolean isAttached;
    private boolean isComplete;

    /* JADX INFO: Access modifiers changed from: private */
    public ReactEditText getEditById(Context context, int i2) {
        try {
            return (ReactEditText) ((UIManagerModule) ((ReactApplicationContext) context).getNativeModule(UIManagerModule.class)).getUIImplementation().getUIViewOperationQueue().getNativeViewHierarchyManager().resolveView(i2);
        } catch (IllegalViewOperationException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keyboardClose(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams.bottomMargin != 0) {
            marginLayoutParams.bottomMargin = 0;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keyboardUp(Activity activity, final View view, final int i2, long j2) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.5
            @Override // java.lang.Runnable
            public void run() {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                if (marginLayoutParams.bottomMargin == 0) {
                    marginLayoutParams.bottomMargin = i2;
                    view.setLayoutParams(marginLayoutParams);
                }
            }
        }, 100L);
    }

    public void addKeyBoardView(Activity activity, View view) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 80;
        int screenHeightWithVirtKeyboard = UnScreenUtils.getScreenHeightWithVirtKeyboard(activity) - UnScreenUtils.getScreenHWithoutVirtKeyboard(activity);
        int navBarHeight = UnScreenUtils.getNavBarHeight(activity);
        if (screenHeightWithVirtKeyboard > navBarHeight) {
            screenHeightWithVirtKeyboard = navBarHeight;
        }
        layoutParams.bottomMargin = screenHeightWithVirtKeyboard;
        viewGroup.addView(view, layoutParams);
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCustomKeyboardModuleListener
    public void bindKeyBoard(final Context context, final int i2, String str, final Activity activity) {
        String str2 = "install========tag=====" + i2 + "====tag===" + str;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.1
            @Override // java.lang.Runnable
            public void run() {
                Activity activity2 = activity;
                if (activity2 == null) {
                    activity2 = JDReactHelper.newInstance().getCurrentMyActivity();
                }
                final Activity activity3 = activity2;
                final ReactEditText editById = JDReactNativeCustomKeyboardModuleListener.this.getEditById(context, i2);
                if (editById == null || activity3 == null) {
                    return;
                }
                JDReactNativeCustomKeyboardModuleListener.this.initSet(activity3, editById);
                JDReactNativeCustomKeyboardModuleListener.this.isAttached = false;
                View initKeyBoardView = JDReactNativeCustomKeyboardModuleListener.this.initKeyBoardView(context, editById, activity3);
                initKeyBoardView.measure(0, 0);
                initKeyBoardView.getMeasuredHeight();
                editById.setTag(-559038801, initKeyBoardView);
                activity3.findViewById(16908290);
                editById.setOnFocusChangeListener(new View.OnFocusChangeListener
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0048: INVOKE 
                      (r0v3 'editById' com.facebook.react.views.textinput.ReactEditText)
                      (wrap: android.view.View$OnFocusChangeListener : 0x0045: CONSTRUCTOR 
                      (r8v0 'this' com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener$1 A[IMMUTABLE_TYPE, THIS])
                      (r3v0 'activity3' android.app.Activity A[DONT_INLINE])
                      (r0v3 'editById' com.facebook.react.views.textinput.ReactEditText A[DONT_INLINE])
                      (r5 I:android.view.View A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener$1, android.app.Activity, com.facebook.react.views.textinput.ReactEditText, android.view.View):void (m), WRAPPED] (LINE:10) call: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.1.1.<init>(com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener$1, android.app.Activity, com.facebook.react.views.textinput.ReactEditText, android.view.View):void type: CONSTRUCTOR)
                     type: VIRTUAL call: android.widget.EditText.setOnFocusChangeListener(android.view.View$OnFocusChangeListener):void A[MD:(android.view.View$OnFocusChangeListener):void (s)] (LINE:10) in method: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.1.run():void, file: classes5.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    this = this;
                    android.app.Activity r0 = r2
                    if (r0 != 0) goto Lc
                    com.jingdong.common.jdreactFramework.JDReactHelper r0 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()
                    android.app.Activity r0 = r0.getCurrentMyActivity()
                Lc:
                    r3 = r0
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener r0 = com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.this
                    android.content.Context r1 = r3
                    int r2 = r4
                    com.facebook.react.views.textinput.ReactEditText r0 = com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.access$000(r0, r1, r2)
                    if (r0 == 0) goto L56
                    if (r3 != 0) goto L1c
                    goto L56
                L1c:
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener r1 = com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.this
                    r1.initSet(r3, r0)
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener r1 = com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.this
                    r2 = 0
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.access$102(r1, r2)
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener r1 = com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.this
                    android.content.Context r4 = r3
                    android.view.View r1 = r1.initKeyBoardView(r4, r0, r3)
                    r1.measure(r2, r2)
                    int r6 = r1.getMeasuredHeight()
                    r2 = -559038801(0xffffffffdeadbeaf, float:-6.2598182E18)
                    r0.setTag(r2, r1)
                    r1 = 16908290(0x1020002, float:2.3877235E-38)
                    android.view.View r5 = r3.findViewById(r1)
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener$1$1 r1 = new com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener$1$1
                    r1.<init>()
                    r0.setOnFocusChangeListener(r1)
                    com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener$1$2 r7 = new com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener$1$2
                    r1 = r7
                    r2 = r8
                    r4 = r0
                    r1.<init>()
                    r0.setOnClickListener(r7)
                L56:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.AnonymousClass1.run():void");
            }
        });
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCustomKeyboardModuleListener
    public void fromBackGroundToForeGround(final Context context, final int i2, final Activity activity) {
        String str = "hideSystemSofeKeyboard========tag=====" + i2 + "==isAttached==" + this.isAttached;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.3
            @Override // java.lang.Runnable
            public void run() {
                final Activity activity2 = activity;
                if (activity2 == null) {
                    activity2 = JDReactHelper.newInstance().getCurrentMyActivity();
                }
                final ReactEditText editById = JDReactNativeCustomKeyboardModuleListener.this.getEditById(context, i2);
                if (editById == null || activity2 == null) {
                    return;
                }
                if (activity2 != null) {
                    activity2.getWindow().setSoftInputMode(35);
                }
                boolean isFocused = editById.isFocused();
                if (JDReactNativeCustomKeyboardModuleListener.this.isAttached && isFocused) {
                    UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JDReactNativeCustomKeyboardModuleListener.this.hideSystemSofeKeyboard(activity2);
                            View view = (View) editById.getTag(-559038801);
                            if (view == null || view.getParent() != null) {
                                return;
                            }
                            JDReactNativeCustomKeyboardModuleListener.this.addKeyBoardView(activity2, view);
                            ((JDCustomKeyBoardView) view.findViewById(R.id.keyboard_view)).setVisibility(0);
                            JDReactNativeCustomKeyboardModuleListener.this.isAttached = true;
                        }
                    }, 300L);
                }
            }
        });
    }

    public int getCurrentNavigationBarHeight(Activity activity) {
        if (isNavigationBarShown(activity)) {
            return getNavigationBarHeight(activity);
        }
        return 0;
    }

    public String getDeviceInfo() {
        String deviceBrand = BaseInfo.getDeviceBrand();
        return (TextUtils.isEmpty(deviceBrand) || deviceBrand.equalsIgnoreCase("HUAWEI")) ? "navigationbar_is_min" : deviceBrand.equalsIgnoreCase("XIAOMI") ? "force_fsg_nav_bar" : (deviceBrand.equalsIgnoreCase("VIVO") || deviceBrand.equalsIgnoreCase("OPPO")) ? "navigation_gesture_on" : "navigationbar_is_min";
    }

    public int getNavigationBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public int getNavigationBarHeightIfRoom(Context context) {
        if (navigationGestureEnabled(context)) {
            return 0;
        }
        return getCurrentNavigationBarHeight((Activity) context);
    }

    public void hideSystemSofeKeyboard(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public View initKeyBoardView(final Context context, final ReactEditText reactEditText, Activity activity) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_jd_customkeyboardview_idcast, (ViewGroup) null);
        final Keyboard keyboard = new Keyboard(activity, R.xml.jd_customkeyboard_identity_idcast);
        JDCustomKeyBoardView jDCustomKeyBoardView = (JDCustomKeyBoardView) inflate.findViewById(R.id.keyboard_view);
        jDCustomKeyBoardView.setKeyboard(keyboard);
        jDCustomKeyBoardView.setEnabled(true);
        jDCustomKeyBoardView.setPreviewEnabled(false);
        jDCustomKeyBoardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.4
            @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
            public void onKey(int i2, int[] iArr) {
                ReactEditText reactEditText2 = reactEditText;
                if (reactEditText2 == null) {
                    return;
                }
                Editable text = reactEditText2.getText();
                int selectionStart = reactEditText.getSelectionStart();
                if (i2 == -5) {
                    String obj = text.toString();
                    if (text != null && text.length() > 0 && selectionStart > 0) {
                        text.delete(selectionStart - 1, selectionStart);
                    }
                    String str = "deleteStartText=====" + obj + "====deleteEndText===" + text.toString();
                } else if (i2 == -4) {
                    ReactEditText reactEditText3 = reactEditText;
                    if (reactEditText3 != null) {
                        reactEditText3.clearFocus();
                    }
                    JDReactNativeCustomKeyboardModuleListener.this.isComplete = true;
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) ((ReactApplicationContext) context).getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(JDReactNativeCustomKeyboardModuleListener.OK_EVENT_NAME, null);
                } else {
                    String obj2 = text.toString();
                    text.insert(selectionStart, Character.toString((char) i2));
                    String str2 = "deleteStartText=====" + obj2 + "====deleteEndText===" + text.toString();
                }
            }

            @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
            public void onPress(int i2) {
                if (i2 == -32) {
                    ((JDCustomKeyBoardView) ((View) reactEditText.getTag(-559038801)).findViewById(R.id.keyboard_view)).setPreviewEnabled(false);
                }
            }

            @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
            public void onRelease(int i2) {
            }

            @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
            public void onText(CharSequence charSequence) {
            }

            @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
            public void swipeDown() {
            }

            @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
            public void swipeLeft() {
            }

            @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
            public void swipeRight() {
            }

            @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
            public void swipeUp() {
            }
        });
        return inflate;
    }

    public void initSet(Activity activity, ReactEditText reactEditText) {
        activity.getWindow().setSoftInputMode(35);
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                Method method = EditText.class.getMethod("setShowSoftInputOnFocus", Boolean.TYPE);
                method.setAccessible(true);
                method.invoke(reactEditText, Boolean.FALSE);
                return;
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                return;
            } catch (SecurityException e3) {
                e3.printStackTrace();
                return;
            } catch (Exception e4) {
                e4.printStackTrace();
                return;
            }
        }
        reactEditText.setInputType(0);
    }

    public boolean isNavigationBarShown(Activity activity) {
        int visibility;
        View findViewById = activity.findViewById(16908336);
        return (findViewById == null || (visibility = findViewById.getVisibility()) == 8 || visibility == 4) ? false : true;
    }

    public boolean navigationGestureEnabled(Context context) {
        return (Build.VERSION.SDK_INT >= 17 ? Settings.Global.getInt(context.getContentResolver(), getDeviceInfo(), 0) : 0) != 0;
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, JDFlutterCallResult jDFlutterCallResult, Activity activity) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCustomKeyboardModuleListener
    public void showSystemKeyboard(Context context) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCustomKeyboardModuleListener
    public void switchSystemKeyboard(Context context, int i2) {
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCustomKeyboardModuleListener
    public void unBindKeyBoard(final Context context, final int i2, final Activity activity) {
        String str = "uninstall========tag=====" + i2;
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCustomKeyboardModuleListener.2
            @Override // java.lang.Runnable
            public void run() {
                Activity activity2 = activity;
                if (activity2 == null) {
                    activity2 = JDReactHelper.newInstance().getCurrentMyActivity();
                }
                ReactEditText editById = JDReactNativeCustomKeyboardModuleListener.this.getEditById(context, i2);
                if (editById == null || activity2 == null) {
                    return;
                }
                View view = (View) editById.getTag(-559038801);
                if (view != null && view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                    JDReactNativeCustomKeyboardModuleListener.this.keyboardClose(activity2.findViewById(16908290));
                }
                editById.setTag(-559038801, null);
            }
        });
    }
}
