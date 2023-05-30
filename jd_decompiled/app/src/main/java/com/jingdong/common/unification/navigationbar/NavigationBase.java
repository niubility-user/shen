package com.jingdong.common.unification.navigationbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.R;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.common.unification.navigationbar.newbar.StateController;
import com.jingdong.common.unification.navigationbar.newbar.TabShowNew;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class NavigationBase {
    private static final String SP_ANGLE_SWITCH = "navigationAngleSwitch";
    private static final String SP_RED_POINT_SWITCH = "navigationRedPointSwitch";
    private static NavigationBase base;
    public List<NavigationButton> buttons;
    public boolean isUseDefaultBg;
    private SharedPreferences sp;
    private JDTabFragment thisFragment;
    public int mCurrentIndex = 0;
    public int oldPage = -1;
    private Object syncButtons = new Object();
    private boolean isJumpFromClickHome = false;
    private boolean isJumpFromClickCategory = false;
    private boolean isJumpFromClickFaXian = false;
    private boolean isJumpFromClickShoppingCart = false;
    private boolean isJumpFromClickMyJd = false;
    private boolean isJumpFromClickMessage = false;
    private boolean isJumpFromClickVideo = false;
    private boolean isJumpFromClickSearch = false;
    public List<NavigationConfig> navigationConfigList = new ArrayList();
    public int navigationCurrentMode = 0;
    public String mSourceId = "";

    private NavigationBase() {
        this.sp = null;
        this.sp = CommonBase.getJdSharedPreferences();
    }

    public static NavigationBase getInstance() {
        NavigationBase navigationBase = base;
        if (navigationBase != null) {
            return navigationBase;
        }
        synchronized (NavigationBase.class) {
            if (base == null) {
                base = new NavigationBase();
            }
        }
        return base;
    }

    public static String getLottieJsonByNavigationId(int i2, boolean z) {
        return z ? i2 != 0 ? i2 != 8 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "navigation_lottie/user_dark.json" : "navigation_lottie/cart_dark.json" : "navigation_lottie/discover_dark.json" : "navigation_lottie/new_dark.json" : "navigation_lottie/home_dark.json" : i2 != 0 ? i2 != 8 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "navigation_lottie/user.json" : "navigation_lottie/cart.json" : "navigation_lottie/discover.json" : "navigation_lottie/new.json" : "navigation_lottie/home.json";
    }

    public boolean angleSwitch() {
        return TextUtils.equals(SharedPreferencesUtil.getString(SP_ANGLE_SWITCH, ""), "1");
    }

    public void buttonIconMarketExp(Context context, String str) {
        if (context != null) {
            try {
                JDMtaUtils.sendExposureDataWithExt(context, "NavigationBar_BubbleExpo", "", "NavigationBar_Main", "", "", str, null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("NavigationBar_BubbleExpo", e2.toString());
                }
            }
        }
    }

    public void buttonMarketExp(Context context, String str, NavigationInfo navigationInfo) {
        if (navigationInfo != null) {
            try {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("name", (Object) (TextUtils.isEmpty(navigationInfo.functionId) ? "-100" : navigationInfo.functionId));
                String str2 = "0";
                if ("2".equals(str)) {
                    if (!TextUtils.isEmpty(navigationInfo.marketingLottieUrl)) {
                        str2 = "1";
                    }
                    jDJSONObject.put("shapetype", (Object) str2);
                } else if ("1".equals(str)) {
                    if (!TextUtils.isEmpty(navigationInfo.bigIconIsOpen)) {
                        str2 = navigationInfo.bigIconIsOpen;
                    }
                    jDJSONObject.put("bigpic", (Object) str2);
                }
                jDJSONObject.put("markettype", (Object) str);
                if (OKLog.D) {
                    OKLog.d("buttonMarketExp", jDJSONObject.toJSONString());
                }
                if (context != null) {
                    JDMtaUtils.sendExposureDataWithExt(context, "NavigationBar_ButtonMarketExpo", "", "NavigationBar_Main", "", "", jDJSONObject.toJSONString(), null);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("buttonMarketExp", e2.toString());
                }
            }
        }
    }

    public int getBubbleState() {
        try {
            Class<?> cls = Class.forName("com.jingdong.app.mall.navigationbar.NavigationBarUtil");
            return ((Integer) cls.getMethod("getBubbleState", new Class[0]).invoke(cls, new Object[0])).intValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("NavigationBase", "getBubbleState=" + e2);
                return 0;
            }
            return 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0052, code lost:
        r0.isJump = r6.sp.getBoolean(com.jingdong.common.unification.navigationbar.NavigationConstants.SHARED_FAXIAN_ISJUMP, false);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jingdong.common.unification.navigationbar.JumpEntry getJumpInfoByFunctionId(int r7) {
        /*
            r6 = this;
            com.jingdong.common.unification.navigationbar.JumpEntry r0 = new com.jingdong.common.unification.navigationbar.JumpEntry
            r0.<init>()
            java.lang.Object r1 = r6.syncButtons
            monitor-enter(r1)
            java.util.List<com.jingdong.common.unification.navigationbar.newbar.NavigationButton> r2 = r6.buttons     // Catch: java.lang.Throwable -> L5f
            r3 = 2
            if (r2 == 0) goto L50
            int r2 = r2.size()     // Catch: java.lang.Throwable -> L5f
            if (r2 > 0) goto L14
            goto L50
        L14:
            java.util.List<com.jingdong.common.unification.navigationbar.newbar.NavigationButton> r2 = r6.buttons     // Catch: java.lang.Throwable -> L5f
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L5f
        L1a:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Throwable -> L5f
            if (r4 == 0) goto L4e
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Throwable -> L5f
            com.jingdong.common.unification.navigationbar.newbar.NavigationButton r4 = (com.jingdong.common.unification.navigationbar.newbar.NavigationButton) r4     // Catch: java.lang.Throwable -> L5f
            int r5 = r4.getNavigationId()     // Catch: java.lang.Throwable -> L5f
            if (r5 != r7) goto L1a
            java.lang.String r2 = r4.mUrl     // Catch: java.lang.Throwable -> L5f
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L5f
            if (r2 != 0) goto L3b
            java.lang.String r2 = r4.mUrl     // Catch: java.lang.Throwable -> L5f
            r0.mUrl = r2     // Catch: java.lang.Throwable -> L5f
            r2 = 1
            r0.isJump = r2     // Catch: java.lang.Throwable -> L5f
        L3b:
            if (r3 != r7) goto L4e
            android.content.SharedPreferences r7 = r6.sp     // Catch: java.lang.Throwable -> L5f
            android.content.SharedPreferences$Editor r7 = r7.edit()     // Catch: java.lang.Throwable -> L5f
            java.lang.String r2 = "shared_faxian_isjump_Navigation"
            boolean r3 = r0.isJump     // Catch: java.lang.Throwable -> L5f
            android.content.SharedPreferences$Editor r7 = r7.putBoolean(r2, r3)     // Catch: java.lang.Throwable -> L5f
            r7.apply()     // Catch: java.lang.Throwable -> L5f
        L4e:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L5f
            return r0
        L50:
            if (r7 != r3) goto L5d
            android.content.SharedPreferences r7 = r6.sp     // Catch: java.lang.Throwable -> L5f
            java.lang.String r2 = "shared_faxian_isjump_Navigation"
            r3 = 0
            boolean r7 = r7.getBoolean(r2, r3)     // Catch: java.lang.Throwable -> L5f
            r0.isJump = r7     // Catch: java.lang.Throwable -> L5f
        L5d:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L5f
            return r0
        L5f:
            r7 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L5f
            goto L63
        L62:
            throw r7
        L63:
            goto L62
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.navigationbar.NavigationBase.getJumpInfoByFunctionId(int):com.jingdong.common.unification.navigationbar.JumpEntry");
    }

    public int getNavigationCurrentMode() {
        if (OKLog.D) {
            OKLog.d("NavigationBase", "getNavigationCurrentMode()-navigationCurrentMode=" + this.navigationCurrentMode);
        }
        return this.navigationCurrentMode;
    }

    public String getNavigationOrder() {
        if (OKLog.D) {
            OKLog.d("NavigationBase", "getNavigationOrder()-navigationCurrentMode=" + this.navigationCurrentMode);
        }
        int i2 = this.navigationCurrentMode;
        if (i2 == 1) {
            return CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ELDER_ORDER_NATIVE, "");
        }
        if (i2 == 0) {
            return CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ORDER_NATIVE, "");
        }
        return i2 == 2 ? CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ORDER_NATIVE_B, "") : "";
    }

    public int getNavigationTabHeight(Context context, int i2) {
        List<NavigationButton> list = this.buttons;
        int i3 = 0;
        if (list != null && list.size() > 0 && context != null) {
            for (NavigationButton navigationButton : this.buttons) {
                if (navigationButton != null && navigationButton.getNavigationId() == i2) {
                    if (navigationButton.bigIconTag) {
                        i3 = context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_big_icon_height);
                    } else {
                        i3 = context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_icon_height);
                    }
                    if (OKLog.D) {
                        OKLog.d("NavigationBase", "getNavigationTabHeight-navigationId=" + i2 + " bigIconTag=" + navigationButton.bigIconTag + " tabHeight=" + i3);
                    }
                }
            }
        }
        return i3;
    }

    public int getNavigationType() {
        try {
            List<NavigationButton> list = this.buttons;
            if (list != null && list.size() > 0) {
                for (NavigationButton navigationButton : this.buttons) {
                    if (navigationButton != null) {
                        if (navigationButton.getNavigationId() == 5) {
                            return 1;
                        }
                        if (navigationButton.getNavigationId() == 6) {
                            return 2;
                        }
                        if (navigationButton.getNavigationId() == 7) {
                            return 3;
                        }
                    }
                }
                return 0;
            }
            return -1;
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                return 0;
            }
            return 0;
        }
    }

    public JDTabFragment getThisFragment() {
        return this.thisFragment;
    }

    public String getmUrl(int i2) {
        List<NavigationButton> list = this.buttons;
        String str = "";
        if (list != null && list.size() > 0) {
            for (NavigationButton navigationButton : this.buttons) {
                if (navigationButton != null && navigationButton.getNavigationId() == i2) {
                    str = navigationButton.url;
                    if (OKLog.D) {
                        OKLog.d("NavigationBase", "getmUrl-navigationId=" + i2 + " url=" + str);
                    }
                }
            }
        }
        return str;
    }

    public int handleBubble(boolean z) {
        try {
            Class<?> cls = Class.forName("com.jingdong.app.mall.navigationbar.NavigationBarUtil");
            return ((Integer) cls.getMethod("handleNewBubble", Boolean.TYPE).invoke(cls, Boolean.valueOf(z))).intValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("NavigationBase", "handleBubble=" + e2);
                return 0;
            }
            return 0;
        }
    }

    public int hideBubble(boolean z) {
        try {
            Class<?> cls = Class.forName("com.jingdong.app.mall.navigationbar.NavigationBarUtil");
            return ((Integer) cls.getMethod("handleNewBubbleByMySelf", Boolean.TYPE).invoke(cls, Boolean.valueOf(z))).intValue();
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("NavigationBase", "handleBubble=" + e2);
                return 0;
            }
            return 0;
        }
    }

    public boolean isContainTab(String str) {
        if (OKLog.D) {
            OKLog.d("NavigationBase", "isContainTab-functionId=" + str + " isElderMode=" + JDElderModeUtils.isElderMode() + " navigationCurrentMode=" + this.navigationCurrentMode);
        }
        if (!TextUtils.isEmpty(str)) {
            int i2 = this.navigationCurrentMode;
            String str2 = "";
            if (i2 == 1) {
                str2 = CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ELDER_ORDER_NATIVE, "");
            } else if (i2 == 0) {
                str2 = CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ORDER_NATIVE, "");
            } else if (i2 == 2) {
                str2 = CommonBase.getStringFromPreference(NavigationConstants.NAVIGATION_ORDER_NATIVE_B, "");
            }
            if (!TextUtils.isEmpty(str2)) {
                if (OKLog.D) {
                    OKLog.d("NavigationBase", "isContainTab-nativeOrder=" + str2 + " nativeOrder.contains(functionId)=" + str2.contains(str));
                }
                return str2.contains(str);
            }
        }
        return false;
    }

    public boolean isJumpFromClick(int i2) {
        switch (i2) {
            case 0:
                return this.isJumpFromClickHome;
            case 1:
                return this.isJumpFromClickCategory;
            case 2:
                return this.isJumpFromClickFaXian;
            case 3:
                return this.isJumpFromClickShoppingCart;
            case 4:
                return this.isJumpFromClickMyJd;
            case 5:
                return this.isJumpFromClickMessage;
            case 6:
                return this.isJumpFromClickVideo;
            case 7:
                return this.isJumpFromClickSearch;
            default:
                return false;
        }
    }

    public boolean isMsgDisplayType() {
        return getNavigationType() == 1;
    }

    public boolean isNavigationType(int i2) {
        return i2 == getNavigationType();
    }

    public boolean redPointSwitch() {
        return TextUtils.equals(SharedPreferencesUtil.getString(SP_RED_POINT_SWITCH, ""), "1");
    }

    public void refreshMessageNum(Integer num) {
        StateController stateController;
        List<NavigationButton> list = this.buttons;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (NavigationButton navigationButton : this.buttons) {
            if (navigationButton != null && navigationButton.getNavigationId() == 5 && (stateController = navigationButton.getStateController()) != null) {
                stateController.setNum(num);
                return;
            }
        }
    }

    public void refreshMessageRedpoint(boolean z) {
        showRedpoint(5, z);
    }

    public void setIsJumpFromClick(int i2, boolean z) {
        switch (i2) {
            case 0:
                this.isJumpFromClickHome = z;
                return;
            case 1:
                this.isJumpFromClickCategory = z;
                return;
            case 2:
                this.isJumpFromClickFaXian = z;
                return;
            case 3:
                this.isJumpFromClickShoppingCart = z;
                return;
            case 4:
                this.isJumpFromClickMyJd = z;
                return;
            case 5:
                this.isJumpFromClickMessage = z;
                return;
            case 6:
                this.isJumpFromClickVideo = z;
                return;
            case 7:
                this.isJumpFromClickSearch = z;
                return;
            default:
                return;
        }
    }

    public void setOrUpdateNavigationConfig(NavigationConfig navigationConfig) {
        if (navigationConfig != null) {
            try {
                List<NavigationConfig> list = this.navigationConfigList;
                if (list != null) {
                    if (list.size() > 0) {
                        int i2 = -1;
                        for (int i3 = 0; i3 < this.navigationConfigList.size(); i3++) {
                            if (this.navigationConfigList.get(i3) != null && this.navigationConfigList.get(i3).getFunctionId().equals(navigationConfig.getFunctionId())) {
                                i2 = i3;
                            }
                        }
                        if (i2 != -1) {
                            this.navigationConfigList.set(i2, navigationConfig);
                            return;
                        } else {
                            this.navigationConfigList.add(navigationConfig);
                            return;
                        }
                    }
                    this.navigationConfigList.add(navigationConfig);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("NavigationBase", "setOrUpdateNavigationConfig=" + e2);
                }
            }
        }
    }

    public void setThisFragment(JDTabFragment jDTabFragment) {
        this.thisFragment = jDTabFragment;
    }

    public void showRedpoint(int i2, boolean z) {
        TabShowNew tabShowNew;
        List<NavigationButton> list = this.buttons;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (NavigationButton navigationButton : this.buttons) {
            if (navigationButton != null && navigationButton.getNavigationId() == i2 && (tabShowNew = navigationButton.getTabShowNew()) != null) {
                tabShowNew.setIsShowRedPoint(Boolean.valueOf(z));
                return;
            }
        }
    }
}
