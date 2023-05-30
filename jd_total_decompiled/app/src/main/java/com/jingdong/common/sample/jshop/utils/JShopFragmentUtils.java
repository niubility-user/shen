package com.jingdong.common.sample.jshop.utils;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/* loaded from: classes6.dex */
public class JShopFragmentUtils {
    public static Fragment replaceFragment(FragmentManager fragmentManager, int i2, Class<? extends Fragment> cls) {
        return replaceFragment(fragmentManager, i2, cls, (Bundle) null);
    }

    public static Fragment switchFragment(FragmentManager fragmentManager, int i2, Fragment fragment, Class<? extends Fragment> cls) {
        return switchFragment(fragmentManager, i2, fragment, cls, (Bundle) null);
    }

    public static Fragment switchFragmentWithAnimation(FragmentManager fragmentManager, int i2, Fragment fragment, Class<? extends Fragment> cls, int i3, int i4) {
        return switchFragment(fragmentManager, i2, fragment, cls, (Bundle) null, false, true, i3, i4);
    }

    public static Fragment replaceFragment(FragmentManager fragmentManager, int i2, Class<? extends Fragment> cls, Bundle bundle) {
        return replaceFragment(fragmentManager, i2, cls, bundle, false);
    }

    public static Fragment switchFragment(FragmentManager fragmentManager, int i2, Fragment fragment, Class<? extends Fragment> cls, Bundle bundle) {
        return switchFragment(fragmentManager, i2, fragment, cls, bundle, false, false, 0, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x002e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Fragment replaceFragment(FragmentManager fragmentManager, int i2, Class<? extends Fragment> cls, Bundle bundle, boolean z) {
        Fragment fragment;
        try {
            fragment = cls.newInstance();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            fragment = null;
            if (fragment != null) {
            }
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            fragment = null;
            if (fragment != null) {
            }
        }
        if (fragment != null) {
            if (bundle != null && !bundle.isEmpty()) {
                Bundle arguments = fragment.getArguments();
                if (arguments != null) {
                    arguments.putAll(bundle);
                } else {
                    fragment.setArguments(bundle);
                }
            }
            return replaceFragment(fragmentManager, i2, fragment, z);
        }
        return null;
    }

    public static Fragment switchFragment(FragmentManager fragmentManager, int i2, Fragment fragment, Class<? extends Fragment> cls, Bundle bundle, boolean z, boolean z2, int i3, int i4) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        if (z2) {
            beginTransaction.setCustomAnimations(i3, i4);
        }
        String simpleName = cls.getSimpleName();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(simpleName);
        if (fragment != null) {
            beginTransaction.hide(fragment);
        }
        if (findFragmentByTag != null) {
            if (findFragmentByTag != fragment) {
                if (fragment != null) {
                    beginTransaction.hide(fragment);
                }
                beginTransaction.show(findFragmentByTag);
                if (z) {
                    beginTransaction.addToBackStack(null);
                }
                beginTransaction.commitAllowingStateLoss();
            } else if (bundle != null && !bundle.isEmpty() && findFragmentByTag.getArguments() != null) {
                findFragmentByTag.getArguments().putAll(bundle);
            }
            return findFragmentByTag;
        }
        try {
            findFragmentByTag = cls.newInstance();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
        if (findFragmentByTag != null && bundle != null && !bundle.isEmpty()) {
            Bundle arguments = findFragmentByTag.getArguments();
            if (arguments != null) {
                arguments.putAll(bundle);
            } else {
                findFragmentByTag.setArguments(bundle);
            }
        }
        beginTransaction.add(i2, findFragmentByTag, simpleName);
        if (z) {
            beginTransaction.addToBackStack(null);
        }
        beginTransaction.commitAllowingStateLoss();
        return findFragmentByTag;
    }

    public static Fragment replaceFragment(FragmentManager fragmentManager, int i2, Fragment fragment, boolean z) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            beginTransaction.replace(i2, fragment, fragment.getClass().getSimpleName());
        }
        if (z) {
            beginTransaction.addToBackStack(null);
        }
        beginTransaction.commitAllowingStateLoss();
        return fragment;
    }

    public static Fragment switchFragment(FragmentManager fragmentManager, int i2, Fragment fragment, Fragment fragment2, Bundle bundle) {
        return switchFragment(fragmentManager, i2, fragment, fragment2, bundle, false, false, 0, 0);
    }

    public static Fragment switchFragment(FragmentManager fragmentManager, int i2, Fragment fragment, Fragment fragment2, Bundle bundle, boolean z, boolean z2, int i3, int i4) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        if (z2) {
            beginTransaction.setCustomAnimations(i3, i4);
        }
        String simpleName = fragment2.getClass().getSimpleName();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(simpleName);
        if (fragment != null) {
            beginTransaction.hide(fragment);
        }
        if (findFragmentByTag != null) {
            if (findFragmentByTag != fragment) {
                if (fragment != null) {
                    beginTransaction.hide(fragment);
                }
                beginTransaction.show(findFragmentByTag);
                if (z) {
                    beginTransaction.addToBackStack(null);
                }
                beginTransaction.commitAllowingStateLoss();
            } else if (bundle != null && !bundle.isEmpty() && findFragmentByTag.getArguments() != null) {
                findFragmentByTag.getArguments().putAll(bundle);
            }
            return findFragmentByTag;
        }
        try {
            findFragmentByTag = (Fragment) fragment2.getClass().newInstance();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
        if (findFragmentByTag != null && bundle != null && !bundle.isEmpty()) {
            Bundle arguments = findFragmentByTag.getArguments();
            if (arguments != null) {
                arguments.putAll(bundle);
            } else {
                findFragmentByTag.setArguments(bundle);
            }
        }
        beginTransaction.add(i2, findFragmentByTag, simpleName);
        if (z) {
            beginTransaction.addToBackStack(null);
        }
        beginTransaction.commitAllowingStateLoss();
        return findFragmentByTag;
    }
}
