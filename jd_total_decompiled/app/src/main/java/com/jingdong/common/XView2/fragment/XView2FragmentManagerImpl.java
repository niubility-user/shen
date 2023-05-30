package com.jingdong.common.XView2.fragment;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;

/* loaded from: classes5.dex */
public class XView2FragmentManagerImpl extends XView2FragmentManager {
    private Context mContext;
    public FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks;
    XView2FragmentLifecycleCallbacks mXView2FragmentLifecycleCallbacks;

    /* loaded from: classes5.dex */
    public static abstract class XView2FragmentLifecycleCallbacks {
        public void onFragmentActivityCreated(@NonNull Fragment fragment) {
        }

        public void onFragmentAttached(@NonNull Fragment fragment) {
        }

        public void onFragmentCreated(@NonNull Fragment fragment) {
        }

        public void onFragmentDestroyed(@NonNull Fragment fragment) {
        }

        public void onFragmentPaused(@NonNull Fragment fragment) {
        }

        public void onFragmentPreAttached(@NonNull Fragment fragment) {
        }

        public void onFragmentRealVisible(@NonNull Fragment fragment, boolean z) {
        }

        public void onFragmentResumed(@NonNull Fragment fragment) {
        }

        public void onFragmentStarted(@NonNull Fragment fragment) {
        }

        public void onFragmentStopped(@NonNull Fragment fragment) {
        }

        public void onFragmentViewCreated(@NonNull Fragment fragment) {
        }

        public void onFragmentViewDestroyed(@NonNull Fragment fragment) {
        }
    }

    public XView2FragmentManagerImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.jingdong.common.XView2.fragment.XView2FragmentManager
    public void dispatchDestroy() {
        this.mXView2FragmentLifecycleCallbacks = null;
        Context context = this.mContext;
        if (!(context instanceof FragmentActivity) || ((FragmentActivity) context).getSupportFragmentManager() == null || this.mFragmentLifecycleCallbacks == null) {
            return;
        }
        ((FragmentActivity) this.mContext).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this.mFragmentLifecycleCallbacks);
    }

    @Override // com.jingdong.common.XView2.fragment.XView2FragmentManager
    public void initFragmentLifeCallBack() {
        this.mFragmentLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() { // from class: com.jingdong.common.XView2.fragment.XView2FragmentManagerImpl.1
            {
                XView2FragmentManagerImpl.this = this;
            }

            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                XView2FragmentLifecycleCallbacks xView2FragmentLifecycleCallbacks;
                super.onFragmentDestroyed(fragmentManager, fragment);
                if (fragment == null || (xView2FragmentLifecycleCallbacks = XView2FragmentManagerImpl.this.mXView2FragmentLifecycleCallbacks) == null) {
                    return;
                }
                xView2FragmentLifecycleCallbacks.onFragmentDestroyed(fragment);
            }

            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentPreAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
                super.onFragmentPreAttached(fragmentManager, fragment, context);
                if (fragment == null) {
                    return;
                }
                XView2FragmentManagerImpl.this.registerFragmentRealLifeCallBack(fragment);
            }

            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentStarted(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                XView2FragmentLifecycleCallbacks xView2FragmentLifecycleCallbacks;
                super.onFragmentStarted(fragmentManager, fragment);
                if (fragment == null || (xView2FragmentLifecycleCallbacks = XView2FragmentManagerImpl.this.mXView2FragmentLifecycleCallbacks) == null) {
                    return;
                }
                xView2FragmentLifecycleCallbacks.onFragmentStarted(fragment);
            }

            @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
            public void onFragmentViewDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                XView2FragmentLifecycleCallbacks xView2FragmentLifecycleCallbacks;
                super.onFragmentViewDestroyed(fragmentManager, fragment);
                if (fragment == null || (xView2FragmentLifecycleCallbacks = XView2FragmentManagerImpl.this.mXView2FragmentLifecycleCallbacks) == null) {
                    return;
                }
                xView2FragmentLifecycleCallbacks.onFragmentViewDestroyed(fragment);
            }
        };
        Context context = this.mContext;
        if (!(context instanceof FragmentActivity) || ((FragmentActivity) context).getSupportFragmentManager() == null || this.mFragmentLifecycleCallbacks == null) {
            return;
        }
        ((FragmentActivity) this.mContext).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.mFragmentLifecycleCallbacks, true);
    }

    public void registerFragmentLifecycleCallbacks(XView2FragmentLifecycleCallbacks xView2FragmentLifecycleCallbacks) {
        this.mXView2FragmentLifecycleCallbacks = xView2FragmentLifecycleCallbacks;
    }

    public void registerFragmentRealLifeCallBack(@NonNull Fragment fragment) {
        if (fragment instanceof BaseFragment) {
            ((BaseFragment) fragment).setFragmentStateCallBack(new BaseFragment.FragmentStateCallBack() { // from class: com.jingdong.common.XView2.fragment.XView2FragmentManagerImpl.2
                {
                    XView2FragmentManagerImpl.this = this;
                }

                @Override // com.jingdong.cleanmvp.ui.BaseFragment.FragmentStateCallBack
                public void onFragmentFirstVisible(@NonNull Fragment fragment2) {
                    super.onFragmentFirstVisible(fragment2);
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onFragmentFirstVisible " + fragment2.getClass().getName());
                }

                @Override // com.jingdong.cleanmvp.ui.BaseFragment.FragmentStateCallBack
                public void onFragmentRealPause(@NonNull Fragment fragment2) {
                    super.onFragmentRealPause(fragment2);
                    XView2FragmentLifecycleCallbacks xView2FragmentLifecycleCallbacks = XView2FragmentManagerImpl.this.mXView2FragmentLifecycleCallbacks;
                    if (xView2FragmentLifecycleCallbacks != null) {
                        xView2FragmentLifecycleCallbacks.onFragmentPaused(fragment2);
                    }
                }

                @Override // com.jingdong.cleanmvp.ui.BaseFragment.FragmentStateCallBack
                public void onFragmentRealResume(@NonNull Fragment fragment2) {
                    super.onFragmentRealResume(fragment2);
                    XView2FragmentLifecycleCallbacks xView2FragmentLifecycleCallbacks = XView2FragmentManagerImpl.this.mXView2FragmentLifecycleCallbacks;
                    if (xView2FragmentLifecycleCallbacks != null) {
                        xView2FragmentLifecycleCallbacks.onFragmentResumed(fragment2);
                    }
                }

                @Override // com.jingdong.cleanmvp.ui.BaseFragment.FragmentStateCallBack
                public void onFragmentRealVisible(@NonNull Fragment fragment2, boolean z) {
                    super.onFragmentRealVisible(fragment2, z);
                    XView2FragmentLifecycleCallbacks xView2FragmentLifecycleCallbacks = XView2FragmentManagerImpl.this.mXView2FragmentLifecycleCallbacks;
                    if (xView2FragmentLifecycleCallbacks != null) {
                        xView2FragmentLifecycleCallbacks.onFragmentRealVisible(fragment2, z);
                    }
                }
            });
        }
    }
}
