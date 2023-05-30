package com.jingdong.common.unification.translation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes6.dex */
public class JDTransitionManager {
    private static final long ANIM_DURATION = 400;
    private static SoftReference<JDTransitionAnimView> animViewRef;
    private JDTransitionAnimView animView;
    private int frontHeight;
    private int frontLeft;
    private String frontMarkId;
    private int frontTop;
    private View frontView;
    private int frontWidth;
    private boolean hasAlreadyStart = false;
    private ITransitionAnimStateListener listener;
    private int postHeight;
    private int postLeft;
    private int postTop;
    private View postView;
    private int postWidth;
    private String url;
    private static Map<String, View> cacheFrontView = new HashMap();
    private static Map<String, String> cacheFrontUrl = new HashMap();

    /* loaded from: classes6.dex */
    public interface ITransitionAnimStateListener {
        void onEnterAnimFinish();
    }

    private JDTransitionManager(Bundle bundle, View view) {
        this.frontLeft = getViewLeft(bundle);
        this.frontTop = getViewTop(bundle);
        this.frontWidth = getViewWidth(bundle);
        this.frontHeight = getViewHeight(bundle);
        this.frontMarkId = getViewMarkId(bundle);
        this.postView = view;
        this.frontView = getView(bundle);
        this.url = getUrl(bundle);
    }

    public static boolean appendViewDataToBundle(Bundle bundle, View view, String str) {
        return false;
    }

    private void buildAnimViewAndStartAnim(Activity activity) {
        if (animViewRef == null) {
            animViewRef = new SoftReference<>(new JDTransitionAnimView(activity.getApplicationContext()));
        }
        JDTransitionAnimView jDTransitionAnimView = animViewRef.get();
        this.animView = jDTransitionAnimView;
        if (jDTransitionAnimView == null) {
            this.animView = new JDTransitionAnimView(activity.getApplicationContext());
            animViewRef = new SoftReference<>(this.animView);
        }
        this.animView.startAnim(activity, this, this.listener);
    }

    private boolean checkEnterConfig(Activity activity) {
        return getDecorView(activity) != null && checkFrontValue();
    }

    private boolean checkFrontValue() {
        return (this.frontView == null || this.frontWidth == 0 || this.frontHeight == 0) ? false : true;
    }

    private boolean checkIsNull(Object obj) {
        return obj == null;
    }

    private static boolean checkRemoteAnimSwitch() {
        return ConfigUtil.getStringFromPreference(ConfigUtil.KEY_TRANSITION_ANIM_SWITCH, "0").trim().equals("0");
    }

    private static boolean checkView(View view) {
        return view != null && view.getWidth() > 0 && view.getHeight() > 0;
    }

    public static JDTransitionManager createJDTransitionManager(Bundle bundle, View view) {
        return new JDTransitionManager(bundle, view);
    }

    static String createUUID() {
        return UUID.randomUUID().toString();
    }

    private static String getUrl(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return cacheFrontUrl.get(getViewMarkId(bundle));
    }

    private static View getView(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return cacheFrontView.get(getViewMarkId(bundle));
    }

    public static int getViewHeight(Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt(JDTransitionConstant.TRANSITION_VIEW_HEIGHT, 0);
    }

    public static int getViewLeft(Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt(JDTransitionConstant.TRANSITION_VIEW_LEFT, 0);
    }

    public static String getViewMarkId(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return bundle.getString(JDTransitionConstant.TRANSITION_VIEW, null);
    }

    public static int getViewTop(Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt(JDTransitionConstant.TRANSITION_VIEW_TOP, 0);
    }

    public static int getViewWidth(Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt(JDTransitionConstant.TRANSITION_VIEW_WIDTH, 0);
    }

    public static boolean isHuaweiQ() {
        return Build.VERSION.SDK_INT == 29 && TextUtils.equals(UnAndroidUtils.getBrand().toUpperCase(), "HUAWEI");
    }

    public static boolean isNeedPostEnter(Intent intent) {
        Bundle extras;
        String viewMarkId;
        return (!checkRemoteAnimSwitch() || !isVersionMoreThanLOLLIPOP() || isHuaweiQ() || intent == null || (extras = intent.getExtras()) == null || (viewMarkId = getViewMarkId(extras)) == null || cacheFrontView.get(viewMarkId) == null || getViewWidth(extras) <= 0 || getViewHeight(extras) <= 0) ? false : true;
    }

    public static boolean isVersionMoreThanLOLLIPOP() {
        return Build.VERSION.SDK_INT >= 21;
    }

    private static Bundle makeViewDataBundle(View view) {
        return makeViewDataBundle(null, view, null, false);
    }

    public void changeChildViewAlpha(ViewGroup viewGroup, float f2) {
        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return;
        }
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            viewGroup.getChildAt(i2).setAlpha(f2);
        }
    }

    public void changeEnterAnimState(Activity activity, boolean z) {
        if (!isVersionMoreThanLOLLIPOP() || checkIsNull(activity)) {
            return;
        }
        if (z) {
            activity.getWindow().setEnterTransition(new EmptyTransition().setDuration(0L));
            activity.getWindow().setExitTransition(null);
            activity.getWindow().setReturnTransition(null);
            activity.getWindow().setTransitionBackgroundFadeDuration(ANIM_DURATION);
            activity.startPostponedEnterTransition();
            return;
        }
        activity.postponeEnterTransition();
    }

    void changeFrontAndPostViewState(boolean z) {
        View view = this.frontView;
        if (view == null || this.postView == null) {
            return;
        }
        if (z) {
            view.setAlpha(1.0f);
            this.postView.setAlpha(1.0f);
            return;
        }
        view.setAlpha(0.0f);
        this.postView.setAlpha(0.0f);
    }

    public void changeToTransparent(Activity activity) {
        if (activity != null) {
            activity.getWindow().getDecorView().setBackgroundColor(0);
            activity.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    public void clearData() {
        this.frontView = null;
        this.postView = null;
        this.frontMarkId = null;
    }

    ViewGroup getDecorView(Activity activity) {
        if (activity == null) {
            return null;
        }
        return (ViewGroup) activity.getWindow().getDecorView();
    }

    public int getFrontHeight() {
        return this.frontHeight;
    }

    public int getFrontLeft() {
        return this.frontLeft;
    }

    public int getFrontTop() {
        return this.frontTop;
    }

    View getFrontView() {
        return this.frontView;
    }

    public int getFrontWidth() {
        return this.frontWidth;
    }

    public int getPostHeight() {
        return this.postHeight;
    }

    public int getPostLeft() {
        return this.postLeft;
    }

    public int getPostTop() {
        return this.postTop;
    }

    public View getPostView() {
        return this.postView;
    }

    public int getPostWidth() {
        return this.postWidth;
    }

    void notifyAnimStateChange(boolean z) {
        ITransitionAnimStateListener iTransitionAnimStateListener = this.listener;
        if (iTransitionAnimStateListener == null || !z) {
            return;
        }
        iTransitionAnimStateListener.onEnterAnimFinish();
    }

    public void removeFrontUrlFromCache() {
        Map<String, String> map = cacheFrontUrl;
        if (map == null || map.isEmpty() || TextUtils.isEmpty(this.frontMarkId)) {
            return;
        }
        cacheFrontUrl.remove(this.frontMarkId);
    }

    public void removeFrontViewFromCache() {
        Map<String, View> map = cacheFrontView;
        if (map == null || map.isEmpty() || TextUtils.isEmpty(this.frontMarkId)) {
            return;
        }
        cacheFrontView.remove(this.frontMarkId);
    }

    public void removePlaceHolderViewWithAnim(long j2) {
        if (checkIsNull(this.animView)) {
            return;
        }
        this.animView.removePlaceHolderView(j2);
    }

    public void reset(Activity activity) {
        if (checkIsNull(this.animView)) {
            return;
        }
        this.animView.reset();
        this.animView = null;
    }

    public void resetFadeAnimDuration(Activity activity) {
        if (activity == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        activity.getWindow().setTransitionBackgroundFadeDuration(0L);
    }

    public void setTransitionAnimStateListener(ITransitionAnimStateListener iTransitionAnimStateListener) {
        this.listener = iTransitionAnimStateListener;
    }

    public void startEnterTransition(Activity activity) {
        if (this.hasAlreadyStart) {
            return;
        }
        this.hasAlreadyStart = true;
        if (!checkRemoteAnimSwitch()) {
            notifyAnimStateChange(true);
            changeEnterAnimState(activity, true);
        } else if (!isVersionMoreThanLOLLIPOP()) {
            notifyAnimStateChange(true);
            changeEnterAnimState(activity, true);
        } else if (isHuaweiQ()) {
            notifyAnimStateChange(true);
            changeEnterAnimState(activity, true);
        } else if (UnAndroidUtils.mateXEasyClient(activity)) {
            notifyAnimStateChange(true);
            changeEnterAnimState(activity, true);
        } else if (!checkEnterConfig(activity)) {
            notifyAnimStateChange(true);
            changeEnterAnimState(activity, true);
        } else {
            buildAnimViewAndStartAnim(activity);
        }
    }

    public void updatePostViewData() {
        Bundle makeViewDataBundle = makeViewDataBundle(this.postView);
        this.postLeft = getViewLeft(makeViewDataBundle);
        this.postTop = getViewTop(makeViewDataBundle);
        this.postWidth = getViewWidth(makeViewDataBundle);
        this.postHeight = getViewHeight(makeViewDataBundle);
    }

    private static Bundle makeViewDataBundle(Bundle bundle, View view, String str, boolean z) {
        if (checkRemoteAnimSwitch() && isVersionMoreThanLOLLIPOP() && !isHuaweiQ() && checkView(view)) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            int width = view.getWidth();
            int height = view.getHeight();
            bundle.putInt(JDTransitionConstant.TRANSITION_VIEW_LEFT, iArr[0]);
            bundle.putInt(JDTransitionConstant.TRANSITION_VIEW_TOP, iArr[1]);
            bundle.putInt(JDTransitionConstant.TRANSITION_VIEW_WIDTH, width);
            bundle.putInt(JDTransitionConstant.TRANSITION_VIEW_HEIGHT, height);
            if (z) {
                String createUUID = createUUID();
                bundle.putString(JDTransitionConstant.TRANSITION_VIEW, createUUID);
                cacheFrontView.put(createUUID, view);
                cacheFrontUrl.put(createUUID, str);
            }
            return bundle;
        }
        return null;
    }

    public String getUrl() {
        return this.url;
    }
}
