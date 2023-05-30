package com.facebook.react.uimanager;

import android.content.res.Resources;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.PopupMenu;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.animation.Animation;
import com.facebook.react.animation.AnimationListener;
import com.facebook.react.animation.AnimationRegistry;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.jd.dynamic.DYConstants;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* loaded from: classes12.dex */
public class NativeViewHierarchyManager {
    private static final String TAG = "NativeViewHierarchyManager";
    private final AnimationRegistry mAnimationRegistry;
    private final JSResponderHandler mJSResponderHandler;
    private boolean mLayoutAnimationEnabled;
    private final LayoutAnimationController mLayoutAnimator;
    private PopupMenu mPopupMenu;
    private final SparseBooleanArray mRootTags;
    private final RootViewManager mRootViewManager;
    private final SparseArray<ViewManager> mTagsToViewManagers;
    private final SparseArray<View> mTagsToViews;
    private final ViewManagerRegistry mViewManagers;
    private final ReactContext reactContext;

    /* loaded from: classes12.dex */
    private static class PopupMenuCallbackHandler implements PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener {
        boolean mConsumed;
        final Callback mSuccess;

        @Override // android.widget.PopupMenu.OnDismissListener
        public void onDismiss(PopupMenu popupMenu) {
            if (this.mConsumed) {
                return;
            }
            this.mSuccess.invoke(UIManagerModuleConstants.ACTION_DISMISSED);
            this.mConsumed = true;
        }

        @Override // android.widget.PopupMenu.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (this.mConsumed) {
                return false;
            }
            this.mSuccess.invoke(UIManagerModuleConstants.ACTION_ITEM_SELECTED, Integer.valueOf(menuItem.getOrder()));
            this.mConsumed = true;
            return true;
        }

        private PopupMenuCallbackHandler(Callback callback) {
            this.mConsumed = false;
            this.mSuccess = callback;
        }
    }

    public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry) {
        this(viewManagerRegistry, null, new RootViewManager());
    }

    private boolean arrayContains(@Nullable int[] iArr, int i2) {
        if (iArr == null) {
            return false;
        }
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    private static String constructManageChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, @Nullable int[] iArr, @Nullable ViewAtIndex[] viewAtIndexArr, @Nullable int[] iArr2) {
        StringBuilder sb = new StringBuilder();
        if (viewGroup != null) {
            sb.append("View tag:" + viewGroup.getId() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append("  children(" + viewGroupManager.getChildCount(viewGroup) + "): [\n");
            for (int i2 = 0; i2 < viewGroupManager.getChildCount(viewGroup); i2 += 16) {
                int i3 = 0;
                while (true) {
                    int i4 = i2 + i3;
                    if (i4 < viewGroupManager.getChildCount(viewGroup) && i3 < 16) {
                        sb.append(viewGroupManager.getChildAt(viewGroup, i4).getId() + DYConstants.DY_REGEX_COMMA);
                        i3++;
                    }
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(" ],\n");
        }
        if (iArr != null) {
            sb.append("  indicesToRemove(" + iArr.length + "): [\n");
            for (int i5 = 0; i5 < iArr.length; i5 += 16) {
                int i6 = 0;
                while (true) {
                    int i7 = i5 + i6;
                    if (i7 < iArr.length && i6 < 16) {
                        sb.append(iArr[i7] + DYConstants.DY_REGEX_COMMA);
                        i6++;
                    }
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(" ],\n");
        }
        if (viewAtIndexArr != null) {
            sb.append("  viewsToAdd(" + viewAtIndexArr.length + "): [\n");
            for (int i8 = 0; i8 < viewAtIndexArr.length; i8 += 16) {
                int i9 = 0;
                while (true) {
                    int i10 = i8 + i9;
                    if (i10 < viewAtIndexArr.length && i9 < 16) {
                        sb.append("[" + viewAtIndexArr[i10].mIndex + DYConstants.DY_REGEX_COMMA + viewAtIndexArr[i10].mTag + "],");
                        i9++;
                    }
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(" ],\n");
        }
        if (iArr2 != null) {
            sb.append("  tagsToDelete(" + iArr2.length + "): [\n");
            for (int i11 = 0; i11 < iArr2.length; i11 += 16) {
                int i12 = 0;
                while (true) {
                    int i13 = i11 + i12;
                    if (i13 < iArr2.length && i12 < 16) {
                        sb.append(iArr2[i13] + DYConstants.DY_REGEX_COMMA);
                        i12++;
                    }
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }

    private static String constructSetChildrenErrorMessage(ViewGroup viewGroup, ViewGroupManager viewGroupManager, ReadableArray readableArray) {
        ViewAtIndex[] viewAtIndexArr = new ViewAtIndex[readableArray.size()];
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            viewAtIndexArr[i2] = new ViewAtIndex(readableArray.getInt(i2), i2);
        }
        return constructManageChildrenErrorMessage(viewGroup, viewGroupManager, null, viewAtIndexArr, null);
    }

    private ThemedReactContext getReactContextForView(int i2) {
        View view = this.mTagsToViews.get(i2);
        if (view != null) {
            return (ThemedReactContext) view.getContext();
        }
        throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i2);
    }

    public synchronized void addRootView(int i2, SizeMonitoringFrameLayout sizeMonitoringFrameLayout, ThemedReactContext themedReactContext) {
        addRootViewGroup(i2, sizeMonitoringFrameLayout, themedReactContext);
    }

    protected final synchronized void addRootViewGroup(int i2, ViewGroup viewGroup, ThemedReactContext themedReactContext) {
        if (viewGroup.getId() != -1) {
            FLog.e(TAG, "Trying to add a root view with an explicit id (" + viewGroup.getId() + ") already set. React Native uses the id field to track react tags and will overwrite this field. If that is fine, explicitly overwrite the id field to View.NO_ID before calling addRootView.");
        }
        this.mTagsToViews.put(i2, viewGroup);
        this.mTagsToViewManagers.put(i2, this.mRootViewManager);
        this.mRootTags.put(i2, true);
        viewGroup.setId(i2);
    }

    public void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearLayoutAnimation() {
        this.mLayoutAnimator.reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void configureLayoutAnimation(ReadableMap readableMap) {
        this.mLayoutAnimator.initializeFromConfig(readableMap);
    }

    public synchronized void createView(ThemedReactContext themedReactContext, int i2, String str, @Nullable ReactStylesDiffMap reactStylesDiffMap) {
        UiThreadUtil.assertOnUiThread();
        SystraceMessage.beginSection(0L, "NativeViewHierarchyManager_createView").arg("tag", i2).arg("className", str).flush();
        ViewManager viewManager = this.mViewManagers.get(str);
        View createView = viewManager.createView(themedReactContext, this.mJSResponderHandler);
        if (createView == null) {
            Systrace.endSection(0L);
            return;
        }
        this.mTagsToViews.put(i2, createView);
        this.mTagsToViewManagers.put(i2, viewManager);
        createView.setId(i2);
        if (reactStylesDiffMap != null) {
            viewManager.updateProperties(createView, reactStylesDiffMap);
        }
        Systrace.endSection(0L);
    }

    public void dismissPopupMenu() {
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu != null) {
            popupMenu.dismiss();
        }
    }

    public synchronized void dispatchCommand(int i2, int i3, @Nullable ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i2);
        if (view == null) {
            FLog.e("dispatchCommand", "Trying to send command to a non-existing view with tag " + i2);
            return;
        }
        resolveViewManager(i2).receiveCommand(view, i3, readableArray);
    }

    protected synchronized void dropView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (this.mTagsToViewManagers.get(view.getId()) == null) {
            return;
        }
        if (!this.mRootTags.get(view.getId())) {
            resolveViewManager(view.getId()).onDropViewInstance(view);
        }
        ViewManager viewManager = this.mTagsToViewManagers.get(view.getId());
        if ((view instanceof ViewGroup) && (viewManager instanceof ViewGroupManager)) {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroupManager viewGroupManager = (ViewGroupManager) viewManager;
            for (int childCount = viewGroupManager.getChildCount(viewGroup) - 1; childCount >= 0; childCount--) {
                View childAt = viewGroupManager.getChildAt(viewGroup, childCount);
                if (childAt == null) {
                    FLog.e(TAG, "Unable to drop null child view");
                } else if (this.mTagsToViews.get(childAt.getId()) != null) {
                    dropView(childAt);
                }
            }
            viewGroupManager.removeAllViews(viewGroup);
        }
        this.mTagsToViews.remove(view.getId());
        this.mTagsToViewManagers.remove(view.getId());
    }

    public synchronized int findTargetTagForTouch(int i2, float f2, float f3) {
        View view;
        UiThreadUtil.assertOnUiThread();
        view = this.mTagsToViews.get(i2);
        if (view != null) {
        } else {
            throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i2);
        }
        return TouchTargetHelper.findTargetTagForTouch(f2, f3, (ViewGroup) view);
    }

    public AnimationRegistry getAnimationRegistry() {
        return this.mAnimationRegistry;
    }

    @Nullable
    public long getInstanceHandle(int i2) {
        View view = this.mTagsToViews.get(i2);
        if (view != null) {
            Long l2 = (Long) view.getTag(R.id.view_tag_instance_handle);
            if (l2 != null) {
                return l2.longValue();
            }
            throw new IllegalViewOperationException("Unable to find instanceHandle for tag: " + i2);
        }
        throw new IllegalViewOperationException("Unable to find view for tag: " + i2);
    }

    public synchronized void manageChildren(int i2, @Nullable int[] iArr, @Nullable ViewAtIndex[] viewAtIndexArr, @Nullable int[] iArr2) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i2);
        if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup) view;
            final ViewGroupManager viewGroupManager = (ViewGroupManager) resolveViewManager(i2);
            int childCount = viewGroupManager.getChildCount(viewGroup);
            if (iArr != null) {
                for (int length = iArr.length - 1; length >= 0; length--) {
                    int i3 = iArr[length];
                    if (i3 >= 0) {
                        if (i3 >= viewGroupManager.getChildCount(viewGroup)) {
                            if (this.mRootTags.get(i2) && viewGroupManager.getChildCount(viewGroup) == 0) {
                                return;
                            }
                        } else if (i3 < childCount) {
                            View childAt = viewGroupManager.getChildAt(viewGroup, i3);
                            if (!this.mLayoutAnimationEnabled || !this.mLayoutAnimator.shouldAnimateLayout(childAt) || !arrayContains(iArr2, childAt.getId())) {
                                viewGroupManager.removeViewAt(viewGroup, i3);
                            }
                            childCount = i3;
                        }
                    }
                }
            }
            if (viewAtIndexArr != null) {
                for (ViewAtIndex viewAtIndex : viewAtIndexArr) {
                    View view2 = this.mTagsToViews.get(viewAtIndex.mTag);
                    if (view2 != null) {
                        viewGroupManager.addView(viewGroup, view2, viewAtIndex.mIndex);
                    }
                }
            }
            if (iArr2 != null) {
                for (int i4 : iArr2) {
                    final View view3 = this.mTagsToViews.get(i4);
                    if (view3 != null) {
                        if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(view3)) {
                            this.mLayoutAnimator.deleteView(view3, new LayoutAnimationListener() { // from class: com.facebook.react.uimanager.NativeViewHierarchyManager.1
                                @Override // com.facebook.react.uimanager.layoutanimation.LayoutAnimationListener
                                public void onAnimationEnd() {
                                    viewGroupManager.removeView(viewGroup, view3);
                                    NativeViewHierarchyManager.this.dropView(view3);
                                }
                            });
                        } else {
                            dropView(view3);
                        }
                    }
                }
            }
        }
    }

    public synchronized void measure(int i2, int[] iArr) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i2);
        if (view != null) {
            View view2 = (View) RootViewUtil.getRootView(view);
            if (view2 != null) {
                view2.getLocationInWindow(iArr);
                int i3 = iArr[0];
                int i4 = iArr[1];
                view.getLocationInWindow(iArr);
                iArr[0] = iArr[0] - i3;
                iArr[1] = iArr[1] - i4;
                iArr[2] = view.getWidth();
                iArr[3] = view.getHeight();
            } else {
                throw new NoSuchNativeViewException("Native view " + i2 + " is no longer on screen");
            }
        } else {
            throw new NoSuchNativeViewException("No native view for " + i2 + " currently exists");
        }
    }

    public synchronized void measureInWindow(int i2, int[] iArr) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i2);
        if (view != null) {
            view.getLocationOnScreen(iArr);
            Resources resources = view.getContext().getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                iArr[1] = iArr[1] - ((int) resources.getDimension(identifier));
            }
            iArr[2] = view.getWidth();
            iArr[3] = view.getHeight();
        } else {
            throw new NoSuchNativeViewException("No native view for " + i2 + " currently exists");
        }
    }

    public synchronized void removeRootView(int i2) {
        UiThreadUtil.assertOnUiThread();
        if (!this.mRootTags.get(i2)) {
            SoftAssertions.assertUnreachable("View with tag " + i2 + " is not registered as a root view");
        }
        dropView(this.mTagsToViews.get(i2));
        this.mRootTags.delete(i2);
    }

    public final synchronized View resolveView(int i2) {
        View view;
        view = this.mTagsToViews.get(i2);
        if (view == null) {
            throw new IllegalViewOperationException("Trying to resolve view with tag " + i2 + " which doesn't exist");
        }
        return view;
    }

    public final synchronized ViewManager resolveViewManager(int i2) {
        ViewManager viewManager;
        viewManager = this.mTagsToViewManagers.get(i2);
        if (viewManager == null) {
            throw new IllegalViewOperationException("ViewManager for tag " + i2 + " could not be found");
        }
        return viewManager;
    }

    public void sendAccessibilityEvent(int i2, int i3) {
        View view = this.mTagsToViews.get(i2);
        if (view != null) {
            AccessibilityHelper.sendAccessibilityEvent(view, i3);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Could not find view with tag " + i2);
    }

    public synchronized void setChildren(int i2, ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        ViewGroup viewGroup = (ViewGroup) this.mTagsToViews.get(i2);
        ViewGroupManager viewGroupManager = (ViewGroupManager) resolveViewManager(i2);
        for (int i3 = 0; i3 < readableArray.size(); i3++) {
            View view = this.mTagsToViews.get(readableArray.getInt(i3));
            if (view != null) {
                viewGroupManager.addView(viewGroup, view, i3);
            } else {
                throw new IllegalViewOperationException("Trying to add unknown view tag: " + readableArray.getInt(i3) + "\n detail: " + constructSetChildrenErrorMessage(viewGroup, viewGroupManager, readableArray));
            }
        }
    }

    public synchronized void setJSResponder(int i2, int i3, boolean z) {
        if (!z) {
            this.mJSResponderHandler.setJSResponder(i3, null);
            return;
        }
        View view = this.mTagsToViews.get(i2);
        if (i3 != i2 && (view instanceof ViewParent)) {
            this.mJSResponderHandler.setJSResponder(i3, (ViewParent) view);
            return;
        }
        if (this.mRootTags.get(i2)) {
            SoftAssertions.assertUnreachable("Cannot block native responder on " + i2 + " that is a root view");
        }
        this.mJSResponderHandler.setJSResponder(i3, view.getParent());
    }

    public void setLayoutAnimationEnabled(boolean z) {
        this.mLayoutAnimationEnabled = z;
    }

    public synchronized void showPopupMenu(int i2, ReadableArray readableArray, Callback callback, Callback callback2) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i2);
        if (view == null) {
            callback2.invoke("Can't display popup. Could not find view with tag " + i2);
            return;
        }
        PopupMenu popupMenu = new PopupMenu(getReactContextForView(i2), view);
        this.mPopupMenu = popupMenu;
        Menu menu = popupMenu.getMenu();
        for (int i3 = 0; i3 < readableArray.size(); i3++) {
            menu.add(0, 0, i3, readableArray.getString(i3));
        }
        PopupMenuCallbackHandler popupMenuCallbackHandler = new PopupMenuCallbackHandler(callback);
        this.mPopupMenu.setOnMenuItemClickListener(popupMenuCallbackHandler);
        this.mPopupMenu.setOnDismissListener(popupMenuCallbackHandler);
        this.mPopupMenu.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void startAnimationForNativeView(int i2, Animation animation, @Nullable final Callback callback) {
        UiThreadUtil.assertOnUiThread();
        View view = this.mTagsToViews.get(i2);
        animation.getAnimationID();
        if (view != null) {
            animation.setAnimationListener(new AnimationListener
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: INVOKE 
                  (r4v0 'animation' com.facebook.react.animation.Animation)
                  (wrap: com.facebook.react.animation.AnimationListener : 0x0014: CONSTRUCTOR 
                  (r2v0 'this' com.facebook.react.uimanager.NativeViewHierarchyManager A[IMMUTABLE_TYPE, THIS])
                  (r1 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r5v0 'callback' com.facebook.react.bridge.Callback A[DONT_INLINE])
                 A[Catch: all -> 0x003b, MD:(com.facebook.react.uimanager.NativeViewHierarchyManager, int, com.facebook.react.bridge.Callback):void (m), WRAPPED] (LINE:4) call: com.facebook.react.uimanager.NativeViewHierarchyManager.2.<init>(com.facebook.react.uimanager.NativeViewHierarchyManager, int, com.facebook.react.bridge.Callback):void type: CONSTRUCTOR)
                 type: VIRTUAL call: com.facebook.react.animation.Animation.setAnimationListener(com.facebook.react.animation.AnimationListener):void A[Catch: all -> 0x003b, MD:(com.facebook.react.animation.AnimationListener):void (m)] (LINE:4) in method: com.facebook.react.uimanager.NativeViewHierarchyManager.startAnimationForNativeView(int, com.facebook.react.animation.Animation, com.facebook.react.bridge.Callback):void, file: classes12.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                monitor-enter(r2)
                com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()     // Catch: java.lang.Throwable -> L3b
                android.util.SparseArray<android.view.View> r0 = r2.mTagsToViews     // Catch: java.lang.Throwable -> L3b
                java.lang.Object r0 = r0.get(r3)     // Catch: java.lang.Throwable -> L3b
                android.view.View r0 = (android.view.View) r0     // Catch: java.lang.Throwable -> L3b
                int r1 = r4.getAnimationID()     // Catch: java.lang.Throwable -> L3b
                if (r0 == 0) goto L1f
                com.facebook.react.uimanager.NativeViewHierarchyManager$2 r3 = new com.facebook.react.uimanager.NativeViewHierarchyManager$2     // Catch: java.lang.Throwable -> L3b
                r3.<init>()     // Catch: java.lang.Throwable -> L3b
                r4.setAnimationListener(r3)     // Catch: java.lang.Throwable -> L3b
                r4.start(r0)     // Catch: java.lang.Throwable -> L3b
                monitor-exit(r2)
                return
            L1f:
                com.facebook.react.uimanager.IllegalViewOperationException r4 = new com.facebook.react.uimanager.IllegalViewOperationException     // Catch: java.lang.Throwable -> L3b
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3b
                r5.<init>()     // Catch: java.lang.Throwable -> L3b
                java.lang.String r0 = "View with tag "
                r5.append(r0)     // Catch: java.lang.Throwable -> L3b
                r5.append(r3)     // Catch: java.lang.Throwable -> L3b
                java.lang.String r3 = " not found"
                r5.append(r3)     // Catch: java.lang.Throwable -> L3b
                java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> L3b
                r4.<init>(r3)     // Catch: java.lang.Throwable -> L3b
                throw r4     // Catch: java.lang.Throwable -> L3b
            L3b:
                r3 = move-exception
                monitor-exit(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.NativeViewHierarchyManager.startAnimationForNativeView(int, com.facebook.react.animation.Animation, com.facebook.react.bridge.Callback):void");
        }

        public synchronized void updateInstanceHandle(int i2, long j2) {
            UiThreadUtil.assertOnUiThread();
            try {
                updateInstanceHandle(resolveView(i2), j2);
            } catch (IllegalViewOperationException e2) {
                FLog.e(TAG, "Unable to update properties for view tag " + i2, e2);
            }
        }

        public synchronized void updateLayout(int i2, int i3, int i4, int i5, int i6, int i7) {
            UiThreadUtil.assertOnUiThread();
            SystraceMessage.beginSection(0L, "NativeViewHierarchyManager_updateLayout").arg("parentTag", i2).arg("tag", i3).flush();
            try {
                View resolveView = resolveView(i3);
                resolveView.measure(View.MeasureSpec.makeMeasureSpec(i6, 1073741824), View.MeasureSpec.makeMeasureSpec(i7, 1073741824));
                ViewParent parent = resolveView.getParent();
                if (parent instanceof RootView) {
                    parent.requestLayout();
                }
                if (!this.mRootTags.get(i2)) {
                    ViewManager viewManager = this.mTagsToViewManagers.get(i2);
                    ViewGroupManager viewGroupManager = viewManager instanceof ViewGroupManager ? (ViewGroupManager) viewManager : null;
                    if (viewGroupManager != null && !viewGroupManager.needsCustomLayoutForChildren()) {
                        updateLayout(resolveView, i4, i5, i6, i7);
                    }
                } else {
                    updateLayout(resolveView, i4, i5, i6, i7);
                }
            } catch (IllegalViewOperationException e2) {
                ReactContext reactContext = this.reactContext;
                if (reactContext != null) {
                    reactContext.handleException(e2);
                }
            }
            Systrace.endSection(0L);
        }

        public synchronized void updateProperties(int i2, ReactStylesDiffMap reactStylesDiffMap) {
            UiThreadUtil.assertOnUiThread();
            try {
                ViewManager resolveViewManager = resolveViewManager(i2);
                View resolveView = resolveView(i2);
                if (reactStylesDiffMap != null) {
                    resolveViewManager.updateProperties(resolveView, reactStylesDiffMap);
                }
            } catch (IllegalViewOperationException e2) {
                FLog.e(TAG, "Unable to update properties for view tag " + i2, e2);
            }
        }

        public synchronized void updateViewExtraData(int i2, Object obj) {
            UiThreadUtil.assertOnUiThread();
            try {
                resolveViewManager(i2).updateExtraData(resolveView(i2), obj);
            } catch (IllegalViewOperationException unused) {
            }
        }

        public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry, ReactApplicationContext reactApplicationContext) {
            this(viewManagerRegistry, reactApplicationContext, new RootViewManager());
        }

        public NativeViewHierarchyManager(ViewManagerRegistry viewManagerRegistry, ReactApplicationContext reactApplicationContext, RootViewManager rootViewManager) {
            this.mJSResponderHandler = new JSResponderHandler();
            this.mLayoutAnimator = new LayoutAnimationController();
            this.mAnimationRegistry = new AnimationRegistry();
            this.mViewManagers = viewManagerRegistry;
            this.mTagsToViews = new SparseArray<>();
            this.mTagsToViewManagers = new SparseArray<>();
            this.mRootTags = new SparseBooleanArray();
            this.mRootViewManager = rootViewManager;
            this.reactContext = reactApplicationContext;
        }

        private void updateInstanceHandle(View view, long j2) {
            UiThreadUtil.assertOnUiThread();
            view.setTag(R.id.view_tag_instance_handle, Long.valueOf(j2));
        }

        private void updateLayout(View view, int i2, int i3, int i4, int i5) {
            if (this.mLayoutAnimationEnabled && this.mLayoutAnimator.shouldAnimateLayout(view)) {
                this.mLayoutAnimator.applyLayoutUpdate(view, i2, i3, i4, i5);
            } else {
                view.layout(i2, i3, i4 + i2, i5 + i3);
            }
        }
    }
