package com.jingdong.common.unification.navigationbar.theme;

import java.util.List;

/* loaded from: classes6.dex */
public interface INavigationTheme {
    void changeTheme(NavThemeEntity navThemeEntity, String str, INavigationChangeState iNavigationChangeState);

    void changeTheme(List<NavThemeEntity> list, String str);

    void changeToDefault();
}
