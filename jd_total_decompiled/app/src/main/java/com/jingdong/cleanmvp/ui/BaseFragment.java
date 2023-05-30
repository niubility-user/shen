package com.jingdong.cleanmvp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    private boolean isMoveTaskBack;
    private boolean isStoped;
    public IBackKeyTriggerListener mBackKeyTriggerListener;
    public FragmentStateCallBack mFragmentStateCallBack;
    protected HttpGroupWithNPS mHttpGroupWithNPS;
    private ViewGroup mViewGroup;
    protected View rootView;
    public BaseActivity thisActivity;
    private Handler handler = new Handler();
    protected String page_id = "";
    protected String shop_id = "";
    protected boolean isUseBasePV = true;
    protected boolean needRemoveviewOnStop = true;
    public boolean useSubrootView = true;
    boolean mIsViewCreated = false;
    boolean mIsFirstVisible = true;
    boolean mCurrentVisibleState = false;
    boolean mIsHasRealPaused = false;
    public boolean manualResume = false;

    /* loaded from: classes4.dex */
    public static abstract class FragmentStateCallBack {
        public void onFragmentFirstVisible(@NonNull Fragment fragment) {
        }

        public void onFragmentRealPause(@NonNull Fragment fragment) {
        }

        public void onFragmentRealResume(@NonNull Fragment fragment) {
        }

        public void onFragmentRealVisible(@NonNull Fragment fragment, boolean z) {
        }
    }

    /* loaded from: classes4.dex */
    public interface IBackKeyTriggerListener {
        void backKeyTrigger();
    }

    private void dispatchChildVisibleState(boolean z) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (childFragmentManager == null) {
            return;
        }
        List<Fragment> fragments = childFragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        for (Fragment fragment : fragments) {
            if ((fragment instanceof BaseFragment) && !fragment.isHidden() && fragment.getUserVisibleHint()) {
                ((BaseFragment) fragment).dispatchUserVisibleHint(z);
            }
        }
    }

    private void dispatchUserVisibleHint(boolean z) {
        if ((z && isParentInvisible()) || this.mCurrentVisibleState == z) {
            return;
        }
        this.mCurrentVisibleState = z;
        if (z) {
            if (this.mIsFirstVisible) {
                this.mIsFirstVisible = false;
                OKLog.d(TAG, "onFragmentFirstVisible " + getClass().getName());
                FragmentStateCallBack fragmentStateCallBack = this.mFragmentStateCallBack;
                if (fragmentStateCallBack != null) {
                    fragmentStateCallBack.onFragmentFirstVisible(this);
                }
            }
            OKLog.d(TAG, "onFragmentResume " + getClass().getName());
            FragmentStateCallBack fragmentStateCallBack2 = this.mFragmentStateCallBack;
            if (fragmentStateCallBack2 != null) {
                fragmentStateCallBack2.onFragmentRealResume(this);
            }
            this.mIsHasRealPaused = false;
            dispatchChildVisibleState(true);
            return;
        }
        OKLog.d(TAG, "onFragmentPause " + getClass().getName());
        dispatchChildVisibleState(false);
        FragmentStateCallBack fragmentStateCallBack3 = this.mFragmentStateCallBack;
        if (fragmentStateCallBack3 != null) {
            fragmentStateCallBack3.onFragmentRealPause(this);
        }
        this.mIsHasRealPaused = true;
    }

    private boolean isParentInvisible() {
        if (getParentFragment() instanceof BaseFragment) {
            return !((BaseFragment) r0).isSupportVisible();
        }
        return false;
    }

    private boolean isSupportVisible() {
        return this.mCurrentVisibleState;
    }

    public void add() {
        View view;
        try {
            if (!this.isStoped || (view = this.rootView) == null) {
                return;
            }
            if (view.getParent() == null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "onStart() rootView.getParent() is null-->> " + getClass().getName());
                }
                if (this.mViewGroup != null) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "onStart() add view-->> " + getClass().getName());
                    }
                    this.mViewGroup.removeView(this.rootView);
                    this.mViewGroup.addView(this.rootView);
                } else {
                    ViewGroup viewGroup = (ViewGroup) getView().getParent();
                    if (viewGroup != null) {
                        if (OKLog.D) {
                            OKLog.d(TAG, "onStart() remove add view-->> " + getClass().getName());
                        }
                        viewGroup.removeView(this.rootView);
                        viewGroup.addView(this.rootView);
                    }
                }
                this.isStoped = false;
            } else if (OKLog.D) {
                OKLog.d(TAG, "onStart() rootView.getParent() not null-->> " + getClass().getName());
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public String getFragmentString(int i2) {
        return isAdded() ? getString(i2) : "";
    }

    public int getFragmentTextColor(int i2) {
        if (isAdded()) {
            return getResources().getColor(i2);
        }
        return 0;
    }

    public boolean getManualResume() {
        boolean z;
        boolean z2;
        FragmentActivity activity = getActivity();
        if (activity == null || !(activity instanceof BaseActivity)) {
            z = false;
            z2 = false;
        } else {
            z = ((BaseActivity) activity).getManualResume();
            z2 = true;
        }
        OKLog.d(TAG, toString() + "   manualReusme::" + this.manualResume + "  activityManualResume::" + z + "   instanceBaseActivity::" + z2);
        return this.manualResume || z;
    }

    public String getPageParam() {
        return "";
    }

    public boolean isMoveTaskBack() {
        return this.isMoveTaskBack;
    }

    public boolean isRealPausedState() {
        return this.mIsHasRealPaused;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        if (OKLog.D) {
            OKLog.d(TAG, "onActivityCreated() >> " + getClass().getName());
        }
        super.onActivityCreated(bundle);
        this.mIsViewCreated = true;
        if (isHidden() || !getUserVisibleHint()) {
            return;
        }
        dispatchUserVisibleHint(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (OKLog.D) {
            OKLog.d(TAG, "onActivityResult() >> " + getClass().getName());
        }
        super.onActivityResult(i2, i3, intent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.thisActivity = (BaseActivity) activity;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void onClickEvent(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "onClickEvent clickId-->> " + str);
        }
        try {
            JDMtaUtils.onClick(getActivity().getBaseContext(), str, getClass().getSimpleName());
        } catch (NullPointerException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        if (OKLog.D) {
            OKLog.d(TAG, "onCreate() >> " + getClass().getName());
        }
        this.manualResume = true;
        try {
            super.onCreate(bundle);
        } catch (Throwable th) {
            th = th;
            if (OKLog.D) {
                th.printStackTrace();
            }
            while (th.getCause() != null) {
                th = th.getCause();
            }
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    sb.append(stackTraceElement.toString());
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
            }
            if ((th instanceof ClassNotFoundException) && sb.toString().contains("FragmentFactory.loadClass")) {
                if (OKLog.D) {
                    OKLog.d(TAG, " onCreate()   FragmentFactory.loadClass failed!!! exec super.onCreate(null)");
                }
                super.onCreate(null);
            }
        }
        if (this.rootView == null) {
            try {
                this.rootView = onCreateViews(this.thisActivity.getLayoutInflater(), bundle);
            } catch (Throwable th2) {
                if (OKLog.E) {
                    OKLog.e(TAG, th2);
                }
                ExceptionReporter.reportExceptionToBugly(new RuntimeException("BaseFragment_onCreateViews_Error()", th2));
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.rootView;
    }

    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (OKLog.D) {
            OKLog.d(TAG, "onDestroy() >> " + getClass().getName());
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (OKLog.D) {
            OKLog.d(TAG, "onDestroyView() >> " + getClass().getName());
        }
        super.onDestroyView();
        this.mIsViewCreated = false;
        this.mIsFirstVisible = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        BaseActivity baseActivity;
        super.onHiddenChanged(z);
        if (!z && (baseActivity = this.thisActivity) != null) {
            JMA.addTrackView(baseActivity);
        }
        if (z) {
            dispatchUserVisibleHint(false);
        } else {
            dispatchUserVisibleHint(true);
        }
        FragmentStateCallBack fragmentStateCallBack = this.mFragmentStateCallBack;
        if (fragmentStateCallBack != null) {
            fragmentStateCallBack.onFragmentRealVisible(this, !z);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "onHiddenChanged() >> " + getClass().getName() + z);
        }
    }

    public abstract boolean onKeyDown(int i2, KeyEvent keyEvent);

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        if (OKLog.D) {
            OKLog.d(TAG, "onPause() >> " + getClass().getName());
        }
        super.onPause();
        JDMtaUtils.onPause();
        if (this.mCurrentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
        this.manualResume = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        if (OKLog.D) {
            OKLog.d(TAG, "onResume() >> " + getClass().getName());
        }
        if (this.useSubrootView) {
            this.thisActivity.setSubRootView((ViewGroup) this.rootView);
        }
        JDMtaUtils.onResume(this.thisActivity);
        if (this.isUseBasePV) {
            JDMtaUtils.sendPagePv(this.thisActivity, this, getPageParam(), this.page_id, this.shop_id);
        }
        super.onResume();
        if (this.mIsFirstVisible || isHidden() || this.mCurrentVisibleState || !getUserVisibleHint()) {
            return;
        }
        dispatchUserVisibleHint(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        if (OKLog.D) {
            OKLog.d(TAG, "onStart() >> " + getClass().getName());
        }
        super.onStart();
        BaseActivity baseActivity = this.thisActivity;
        if (baseActivity != null) {
            JMA.addTrackView(baseActivity);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    public void post(Runnable runnable) {
        BaseActivity baseActivity = this.thisActivity;
        if (baseActivity != null) {
            baseActivity.post(runnable);
        } else {
            this.handler.post(runnable);
        }
    }

    public void remove() {
        try {
            View view = this.rootView;
            if (view != null) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                this.mViewGroup = viewGroup;
                if (viewGroup != null) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "onStop() remove-->> " + getClass().getName());
                    }
                    this.isStoped = true;
                    this.mViewGroup.removeView(this.rootView);
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void removeBackKeyTriggerListener() {
        this.mBackKeyTriggerListener = null;
    }

    public void removeFragmentStateCallBack() {
        this.mFragmentStateCallBack = null;
    }

    public boolean removeXview2() {
        IBackKeyTriggerListener iBackKeyTriggerListener = this.mBackKeyTriggerListener;
        if (iBackKeyTriggerListener != null) {
            iBackKeyTriggerListener.backKeyTrigger();
            this.mBackKeyTriggerListener = null;
            return true;
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void setArguments(Bundle bundle) {
        try {
            super.setArguments(bundle);
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
        }
    }

    public void setBackKeyTriggerListener(IBackKeyTriggerListener iBackKeyTriggerListener) {
        this.mBackKeyTriggerListener = iBackKeyTriggerListener;
    }

    public void setFragmentStateCallBack(FragmentStateCallBack fragmentStateCallBack) {
        this.mFragmentStateCallBack = fragmentStateCallBack;
    }

    public void setIsUseBasePV(boolean z) {
        this.isUseBasePV = z;
    }

    public void setManualResume(boolean z) {
        this.manualResume = z;
    }

    public void setMoveTaskBack(boolean z) {
        this.isMoveTaskBack = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPageId(String str) {
        this.page_id = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setShopId(String str) {
        this.shop_id = str;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        OKLog.d(TAG, "setUserVisibleHint >>" + getClass().getName() + z);
        if (this.mIsViewCreated) {
            if (z && !this.mCurrentVisibleState) {
                dispatchUserVisibleHint(true);
                FragmentStateCallBack fragmentStateCallBack = this.mFragmentStateCallBack;
                if (fragmentStateCallBack != null) {
                    fragmentStateCallBack.onFragmentRealVisible(this, true);
                }
            } else if (z || !this.mCurrentVisibleState) {
            } else {
                dispatchUserVisibleHint(false);
                FragmentStateCallBack fragmentStateCallBack2 = this.mFragmentStateCallBack;
                if (fragmentStateCallBack2 != null) {
                    fragmentStateCallBack2.onFragmentRealVisible(this, false);
                }
            }
        }
    }

    public void post(Runnable runnable, int i2) {
        BaseActivity baseActivity = this.thisActivity;
        if (baseActivity != null) {
            baseActivity.post(runnable, i2);
        } else {
            this.handler.postDelayed(runnable, i2);
        }
    }

    protected void onClickEvent(String str, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, "onClickEvent clickId-->> " + str);
        }
        JDMtaUtils.onClick(getActivity().getBaseContext(), str, getClass().getName(), str2);
    }
}
