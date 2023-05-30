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

    /* JADX WARN: Removed duplicated region for block: B:34:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x002e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.fragment.app.Fragment replaceFragment(androidx.fragment.app.FragmentManager r1, int r2, java.lang.Class<? extends androidx.fragment.app.Fragment> r3, android.os.Bundle r4, boolean r5) {
        /*
            r0 = 0
            java.lang.Object r3 = r3.newInstance()     // Catch: java.lang.IllegalAccessException -> L8 java.lang.InstantiationException -> Ld
            androidx.fragment.app.Fragment r3 = (androidx.fragment.app.Fragment) r3     // Catch: java.lang.IllegalAccessException -> L8 java.lang.InstantiationException -> Ld
            goto L12
        L8:
            r3 = move-exception
            r3.printStackTrace()
            goto L11
        Ld:
            r3 = move-exception
            r3.printStackTrace()
        L11:
            r3 = r0
        L12:
            if (r3 == 0) goto L2e
            if (r4 == 0) goto L29
            boolean r0 = r4.isEmpty()
            if (r0 != 0) goto L29
            android.os.Bundle r0 = r3.getArguments()
            if (r0 == 0) goto L26
            r0.putAll(r4)
            goto L29
        L26:
            r3.setArguments(r4)
        L29:
            androidx.fragment.app.Fragment r1 = replaceFragment(r1, r2, r3, r5)
            return r1
        L2e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.sample.jshop.utils.JShopFragmentUtils.replaceFragment(androidx.fragment.app.FragmentManager, int, java.lang.Class, android.os.Bundle, boolean):androidx.fragment.app.Fragment");
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
