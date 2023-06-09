package com.facebook.react.uimanager;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.systrace.SystraceMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class UIManagerModuleConstantsHelper {
    private static final String BUBBLING_EVENTS_KEY = "bubblingEventTypes";
    private static final String DIRECT_EVENTS_KEY = "directEventTypes";

    UIManagerModuleConstantsHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> createConstants(UIManagerModule.ViewManagerResolver viewManagerResolver) {
        Map<String, Object> constants = UIManagerModuleConstants.getConstants();
        if (!ReactFeatureFlags.lazilyLoadViewManagers) {
            constants.put("ViewManagerNames", viewManagerResolver.getViewManagerNames());
        }
        constants.put("LazyViewManagersEnabled", Boolean.TRUE);
        return constants;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> createConstantsForViewManager(ViewManager viewManager, @Nullable Map map, @Nullable Map map2, @Nullable Map map3, @Nullable Map map4) {
        HashMap newHashMap = MapBuilder.newHashMap();
        Map<String, Object> exportedCustomBubblingEventTypeConstants = viewManager.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants != null) {
            recursiveMerge(map3, exportedCustomBubblingEventTypeConstants);
            recursiveMerge(exportedCustomBubblingEventTypeConstants, map);
            newHashMap.put(BUBBLING_EVENTS_KEY, exportedCustomBubblingEventTypeConstants);
        } else if (map != null) {
            newHashMap.put(BUBBLING_EVENTS_KEY, map);
        }
        Map<String, Object> exportedCustomDirectEventTypeConstants = viewManager.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants != null) {
            recursiveMerge(map4, exportedCustomDirectEventTypeConstants);
            recursiveMerge(exportedCustomDirectEventTypeConstants, map2);
            newHashMap.put(DIRECT_EVENTS_KEY, exportedCustomDirectEventTypeConstants);
        } else if (map2 != null) {
            newHashMap.put(DIRECT_EVENTS_KEY, map2);
        }
        Map<String, Object> exportedViewConstants = viewManager.getExportedViewConstants();
        if (exportedViewConstants != null) {
            newHashMap.put("Constants", exportedViewConstants);
        }
        Map<String, Integer> commandsMap = viewManager.getCommandsMap();
        if (commandsMap != null) {
            newHashMap.put("Commands", commandsMap);
        }
        Map<String, String> nativeProps = viewManager.getNativeProps();
        if (!nativeProps.isEmpty()) {
            newHashMap.put("NativeProps", nativeProps);
        }
        return newHashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getDefaultExportableEventTypes() {
        return MapBuilder.of(BUBBLING_EVENTS_KEY, UIManagerModuleConstants.getBubblingEventTypeConstants(), DIRECT_EVENTS_KEY, UIManagerModuleConstants.getDirectEventTypeConstants());
    }

    private static void recursiveMerge(@Nullable Map map, @Nullable Map map2) {
        if (map == null || map2 == null || map2.isEmpty()) {
            return;
        }
        for (Object obj : map2.keySet()) {
            Object obj2 = map2.get(obj);
            Object obj3 = map.get(obj);
            if (obj3 != null && (obj2 instanceof Map) && (obj3 instanceof Map)) {
                recursiveMerge((Map) obj3, (Map) obj2);
            } else {
                map.put(obj, obj2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> createConstants(List<ViewManager> list, @Nullable Map<String, Object> map, @Nullable Map<String, Object> map2) {
        Map<String, Object> constants = UIManagerModuleConstants.getConstants();
        Map<? extends String, ? extends Object> bubblingEventTypeConstants = UIManagerModuleConstants.getBubblingEventTypeConstants();
        Map<? extends String, ? extends Object> directEventTypeConstants = UIManagerModuleConstants.getDirectEventTypeConstants();
        if (map != null) {
            map.putAll(bubblingEventTypeConstants);
        }
        if (map2 != null) {
            map2.putAll(directEventTypeConstants);
        }
        for (ViewManager viewManager : list) {
            String name = viewManager.getName();
            SystraceMessage.beginSection(0L, "UIManagerModuleConstantsHelper.createConstants").arg("ViewManager", name).arg("Lazy", Boolean.FALSE).flush();
            try {
                Map<String, Object> createConstantsForViewManager = createConstantsForViewManager(viewManager, null, null, map, map2);
                if (!createConstantsForViewManager.isEmpty()) {
                    constants.put(name, createConstantsForViewManager);
                }
            } finally {
                SystraceMessage.endSection(0L);
            }
        }
        constants.put("genericBubblingEventTypes", bubblingEventTypeConstants);
        constants.put("genericDirectEventTypes", directEventTypeConstants);
        return constants;
    }
}
