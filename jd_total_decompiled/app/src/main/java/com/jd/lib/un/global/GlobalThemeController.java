package com.jd.lib.un.global;

/* loaded from: classes16.dex */
public class GlobalThemeController {
    private static GlobalThemeController controller;
    private b coustomThemeConfig;
    private boolean isCustomTheme;

    private GlobalThemeController() {
    }

    public static GlobalThemeController newInstance() {
        GlobalThemeController globalThemeController;
        GlobalThemeController globalThemeController2 = controller;
        if (globalThemeController2 != null) {
            return globalThemeController2;
        }
        synchronized (GlobalThemeController.class) {
            if (controller == null) {
                controller = new GlobalThemeController();
            }
            globalThemeController = controller;
        }
        return globalThemeController;
    }

    public b getThemeConfig() {
        return this.coustomThemeConfig;
    }

    public boolean isCustomTheme() {
        return this.isCustomTheme;
    }

    public synchronized GlobalThemeController setCustomTheme(boolean z) {
        if (z) {
            if (this.coustomThemeConfig != null) {
                this.isCustomTheme = true;
            }
        }
        this.isCustomTheme = false;
        return this;
    }

    public synchronized GlobalThemeController setThemeConfig(b bVar) {
        this.coustomThemeConfig = bVar;
        if (bVar != null) {
            this.isCustomTheme = true;
        } else {
            this.isCustomTheme = false;
        }
        return this;
    }
}
