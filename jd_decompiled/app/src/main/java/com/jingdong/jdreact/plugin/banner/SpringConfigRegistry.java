package com.jingdong.jdreact.plugin.banner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes13.dex */
public class SpringConfigRegistry {
    private static final SpringConfigRegistry INSTANCE = new SpringConfigRegistry(true);
    private final Map<SpringConfig, String> mSpringConfigMap = new HashMap();

    SpringConfigRegistry(boolean z) {
        if (z) {
            addSpringConfig(SpringConfig.defaultConfig, "default config");
        }
    }

    public static SpringConfigRegistry getInstance() {
        return INSTANCE;
    }

    public boolean addSpringConfig(SpringConfig springConfig, String str) {
        if (springConfig != null) {
            if (str != null) {
                if (this.mSpringConfigMap.containsKey(springConfig)) {
                    return false;
                }
                this.mSpringConfigMap.put(springConfig, str);
                return true;
            }
            throw new IllegalArgumentException("configName is required");
        }
        throw new IllegalArgumentException("springConfig is required");
    }

    public Map<SpringConfig, String> getAllSpringConfig() {
        return Collections.unmodifiableMap(this.mSpringConfigMap);
    }

    public void removeAllSpringConfig() {
        this.mSpringConfigMap.clear();
    }

    public boolean removeSpringConfig(SpringConfig springConfig) {
        if (springConfig != null) {
            return this.mSpringConfigMap.remove(springConfig) != null;
        }
        throw new IllegalArgumentException("springConfig is required");
    }
}
