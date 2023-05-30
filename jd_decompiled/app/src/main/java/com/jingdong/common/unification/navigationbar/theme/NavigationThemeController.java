package com.jingdong.common.unification.navigationbar.theme;

import java.util.List;

/* loaded from: classes6.dex */
public class NavigationThemeController {
    private static NavigationThemeController controller;
    public INavigationTheme theme;

    private NavigationThemeController() {
    }

    public static NavigationThemeController getInstance() {
        NavigationThemeController navigationThemeController;
        NavigationThemeController navigationThemeController2 = controller;
        if (navigationThemeController2 != null) {
            return navigationThemeController2;
        }
        synchronized (NavigationThemeController.class) {
            if (controller == null) {
                controller = new NavigationThemeController();
            }
            navigationThemeController = controller;
        }
        return navigationThemeController;
    }

    public void changeTheme(List<NavThemeEntity> list, String str) {
        if (this.theme == null || list == null || list.size() != 5) {
            return;
        }
        this.theme.changeTheme(list, str);
    }

    public void changeToDefault() {
        INavigationTheme iNavigationTheme = this.theme;
        if (iNavigationTheme == null) {
            return;
        }
        iNavigationTheme.changeToDefault();
    }

    public void changeTheme(NavThemeEntity navThemeEntity, String str, INavigationChangeState iNavigationChangeState) {
        INavigationTheme iNavigationTheme = this.theme;
        if (iNavigationTheme == null || navThemeEntity == null) {
            return;
        }
        iNavigationTheme.changeTheme(navThemeEntity, str, iNavigationChangeState);
    }
}
