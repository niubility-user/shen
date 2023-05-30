package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.CommandFunctionModelClass;
import com.tencent.map.lib.models.ReturnInfoModelClass;
import com.tencent.map.tools.json.JsonUtils;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public final class nh {
    private static final TreeMap<String, Class<? extends CommandFunctionModelClass.BaseCommandFunction>> a = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private static final AtomicInteger b = new AtomicInteger(0);

    private nh() {
    }

    public static CommandFunctionModelClass.BaseCommandFunction a(String str) {
        return (CommandFunctionModelClass.BaseCommandFunction) JsonUtils.parseToModel(str, CommandFunctionModelClass.BaseCommandFunction.class, new Object[0]);
    }

    public static CommandFunctionModelClass.BaseCommandFunction a(String str, String str2) {
        String trim = str2.trim();
        TreeMap<String, Class<? extends CommandFunctionModelClass.BaseCommandFunction>> treeMap = a;
        return treeMap.containsKey(trim) ? (CommandFunctionModelClass.BaseCommandFunction) JsonUtils.parseToModel(str, treeMap.get(trim), new Object[0]) : new CommandFunctionModelClass.ErrorCommandFunction();
    }

    public static String a(ReturnInfoModelClass.ReturnStatus returnStatus) {
        return JsonUtils.modelToJsonString(returnStatus);
    }

    public static void a() {
        if (b.getAndIncrement() == 0) {
            c();
        }
    }

    public static void b() {
        if (b.decrementAndGet() == 0) {
            d();
        }
    }

    public static void c() {
        TreeMap<String, Class<? extends CommandFunctionModelClass.BaseCommandFunction>> treeMap = a;
        treeMap.put("setPosition", CommandFunctionModelClass.SetPositionCommand.class);
        treeMap.put("getPosition", CommandFunctionModelClass.GetPositionCommand.class);
        treeMap.put("enableClick", CommandFunctionModelClass.EnableClickCommand.class);
        treeMap.put("getClickEnabled", CommandFunctionModelClass.GetClickEnabledCommand.class);
        treeMap.put("setVisible", CommandFunctionModelClass.SetVisibleCommand.class);
        treeMap.put("getVisible", CommandFunctionModelClass.GetVisibleCommand.class);
        treeMap.put("playSkeletonAnimation", CommandFunctionModelClass.PlaySkeletonAnimationCommand.class);
        treeMap.put("stopSkeletonAnimation", CommandFunctionModelClass.StopSkeletonAnimationCommand.class);
        treeMap.put("getSkeletonAnimationInfo", CommandFunctionModelClass.GetSkeletonAnimationInfoCommand.class);
        treeMap.put("startTranslateAnimation", CommandFunctionModelClass.StartTranslateAnimationCommand.class);
        treeMap.put("getType", CommandFunctionModelClass.GetTypeCommand.class);
        treeMap.put("setScale", CommandFunctionModelClass.SetScaleCommand.class);
        treeMap.put("getScale", CommandFunctionModelClass.GetScaleCommand.class);
        treeMap.put("setRotation", CommandFunctionModelClass.SetRotationCommand.class);
        treeMap.put("getRotation", CommandFunctionModelClass.GetRotationCommand.class);
        treeMap.put("setPixelBound", CommandFunctionModelClass.SetPixelBoundCommand.class);
        treeMap.put("setExposure", CommandFunctionModelClass.SetExposureCommand.class);
        treeMap.put("getExposure", CommandFunctionModelClass.GetExposureCommand.class);
        treeMap.put("setZoomLevelRange", CommandFunctionModelClass.SetZoomLevelRangeCommand.class);
        treeMap.put("getMaterialVariantsInfo", CommandFunctionModelClass.GetMaterialVariantsCommand.class);
        treeMap.put("getCurrentMaterialVariant", CommandFunctionModelClass.GetCurrentMaterialVariantCommand.class);
        treeMap.put("setMaterialVariant", CommandFunctionModelClass.SetMaterialVariantCommand.class);
        treeMap.put("setMonoColor", CommandFunctionModelClass.SetMonoColorCommand.class);
        treeMap.put("resetMonoColor", CommandFunctionModelClass.ResetColorCommand.class);
    }

    public static void d() {
        a.clear();
    }
}
