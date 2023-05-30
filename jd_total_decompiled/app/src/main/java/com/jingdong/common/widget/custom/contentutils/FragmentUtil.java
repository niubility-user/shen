package com.jingdong.common.widget.custom.contentutils;

import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class FragmentUtil {
    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, String str) {
        addFragment(fragmentManager, fragment, 16908290, str);
    }

    public static void addWithoutAnimFragment(FragmentManager fragmentManager, Fragment fragment, String str) {
        addWithoutAnimFragment(fragmentManager, fragment, 16908290, str);
    }

    public static void addWithoutStackFragment(FragmentManager fragmentManager, Fragment fragment, int i2, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(i2, fragment, str);
        beginTransaction.commitAllowingStateLoss();
    }

    public static ArrayList<Fragment> getAllFragments(FragmentManager fragmentManager) {
        ArrayList<Fragment> arrayList = null;
        if (fragmentManager == null) {
            return null;
        }
        try {
            Field declaredField = fragmentManager.getClass().getDeclaredField("mAdded");
            declaredField.setAccessible(true);
            ArrayList<Fragment> arrayList2 = new ArrayList<>();
            try {
                arrayList2.addAll((ArrayList) declaredField.get(fragmentManager));
                return arrayList2;
            } catch (Exception unused) {
                arrayList = arrayList2;
                return arrayList;
            }
        } catch (Exception unused2) {
        }
    }

    public static Fragment getFragment(FragmentManager fragmentManager, Fragment fragment) {
        ArrayList<Fragment> allFragments = getAllFragments(fragmentManager);
        if (allFragments != null) {
            for (int size = allFragments.size() - 1; size >= 0; size--) {
                Fragment fragment2 = allFragments.get(size);
                if (fragment2 != null && fragment2 == fragment) {
                    return fragment2;
                }
            }
            return null;
        }
        return null;
    }

    public static Fragment getTopFragment(FragmentManager fragmentManager) {
        ArrayList<Fragment> allFragments = getAllFragments(fragmentManager);
        if (allFragments != null) {
            for (int size = allFragments.size() - 1; size >= 0; size--) {
                Fragment fragment = allFragments.get(size);
                if (fragment != null && fragment.isVisible()) {
                    return fragment;
                }
            }
            return null;
        }
        return null;
    }

    public static void initFragment(FragmentManager fragmentManager, Fragment fragment, String str) {
        initFragment(fragmentManager, fragment, str, 16908290);
    }

    public static void pushFragment(FragmentManager fragmentManager, Fragment fragment, int i2, String str) {
        pushFragment(fragmentManager, fragment, i2, str, true, true, false, false, false, null);
    }

    public static void removeFragment(FragmentManager fragmentManager, String str) {
        if (fragmentManager != null) {
            if (fragmentManager != null) {
                try {
                    if (fragmentManager.findFragmentByTag(str) != null) {
                        fragmentManager.popBackStack(str, 1);
                    }
                } catch (Exception unused) {
                }
            }
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
            if (findFragmentByTag != null) {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.remove(findFragmentByTag);
                beginTransaction.commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
        }
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, String str) {
        replaceFragment(fragmentManager, fragment, 16908290, str);
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int i2, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        Fragment findFragmentById = fragmentManager.findFragmentById(i2);
        if (findFragmentById != null) {
            if (findFragmentById instanceof FragmentManager.OnBackStackChangedListener) {
                fragmentManager.addOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener) findFragmentById);
            }
            beginTransaction.hide(findFragmentById);
        }
        beginTransaction.add(i2, fragment, str);
        beginTransaction.addToBackStack(str);
        beginTransaction.commitAllowingStateLoss();
    }

    public static void addWithoutAnimFragment(FragmentManager fragmentManager, Fragment fragment, int i2, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(i2, fragment, str);
        beginTransaction.addToBackStack(str);
        beginTransaction.commitAllowingStateLoss();
    }

    public static void initFragment(FragmentManager fragmentManager, Fragment fragment, String str, int i2) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(i2, fragment, str);
        beginTransaction.commitAllowingStateLoss();
    }

    public static void pushFragment(FragmentManager fragmentManager, Fragment fragment, int i2, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, String str2) {
        Fragment findFragmentByTag;
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        if (z5) {
            beginTransaction.hide(fragment);
        }
        if (TextUtils.isEmpty(str2)) {
            findFragmentByTag = fragmentManager.findFragmentById(i2);
        } else {
            findFragmentByTag = fragmentManager.findFragmentByTag(str2);
        }
        if (findFragmentByTag != null) {
            if (findFragmentByTag instanceof FragmentManager.OnBackStackChangedListener) {
                fragmentManager.addOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener) findFragmentByTag);
            }
            beginTransaction.hide(findFragmentByTag);
        }
        Fragment fragment2 = null;
        if (z4) {
            if (!TextUtils.isEmpty(str)) {
                fragment2 = fragmentManager.findFragmentByTag(str);
            } else {
                fragment2 = getFragment(fragmentManager, fragment);
            }
        }
        if (fragment2 == null) {
            beginTransaction.add(i2, fragment, str);
        } else {
            beginTransaction.show(fragment2);
        }
        if (z) {
            beginTransaction.addToBackStack(str);
        }
        beginTransaction.commitAllowingStateLoss();
        if (z3) {
            fragmentManager.executePendingTransactions();
        }
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int i2, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(i2, fragment, str);
        beginTransaction.commitAllowingStateLoss();
    }

    public static void initFragment(FragmentManager fragmentManager, Fragment fragment, String str, int i2, int i3, int i4, int i5, int i6) {
        if (i3 < 0) {
            i3 = 0;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        if (i5 < 0) {
            i5 = 0;
        }
        if (i6 < 0) {
            i6 = 0;
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(i3, i4, i5, i6);
        beginTransaction.add(i2, fragment, str);
        beginTransaction.commitAllowingStateLoss();
    }

    public static void removeFragment(FragmentManager fragmentManager, Fragment fragment) {
        if (fragmentManager != null) {
            String tag = fragment.getTag();
            if (fragmentManager != null) {
                try {
                    if (fragmentManager.findFragmentByTag(tag) != null) {
                        fragmentManager.popBackStack(tag, 1);
                    }
                } catch (Exception unused) {
                }
            }
            try {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.remove(fragment);
                beginTransaction.commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
                Fragment findFragmentByTag = fragmentManager.findFragmentByTag(tag);
                if (findFragmentByTag != null) {
                    beginTransaction.remove(findFragmentByTag);
                    beginTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                }
            } catch (Exception unused2) {
            }
        }
    }

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int i2, String str, int i3, int i4, int i5, int i6) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(i3, i4, i5, i6);
        Fragment findFragmentById = fragmentManager.findFragmentById(i2);
        if (findFragmentById != null) {
            if (findFragmentById instanceof FragmentManager.OnBackStackChangedListener) {
                fragmentManager.addOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener) findFragmentById);
            }
            beginTransaction.hide(findFragmentById);
        }
        beginTransaction.add(i2, fragment, str);
        beginTransaction.addToBackStack(str);
        beginTransaction.commitAllowingStateLoss();
    }
}
