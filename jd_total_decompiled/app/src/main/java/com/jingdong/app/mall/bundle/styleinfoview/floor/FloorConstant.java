package com.jingdong.app.mall.bundle.styleinfoview.floor;

import android.text.TextUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.PlatformHelper;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.FloorManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import jpbury.t;

/* loaded from: classes3.dex */
public class FloorConstant implements FloorBussinessName {
    public static final ArrayList<String> COMMON_FLOORS;
    public static final ArrayList<String> PACKNAME_FLATFORM;
    public static final ArrayList<String[]> PRIORITY_FLOORS;
    public static final LinkedHashMap<String, String> TEMPLATE_ID_CLASS;
    public static FloorManager floorManager;

    static {
        ArrayList<String> arrayList = new ArrayList<>(4);
        PACKNAME_FLATFORM = arrayList;
        TEMPLATE_ID_CLASS = new LinkedHashMap<>(32);
        floorManager = PlatformHelper.getFloorManager("productDetail");
        COMMON_FLOORS = new ArrayList<>(4);
        ArrayList<String[]> arrayList2 = new ArrayList<>(2);
        PRIORITY_FLOORS = arrayList2;
        arrayList2.add(new String[]{"bpSeckill", "bpGroup"});
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            doAllPlatfromInit(it.next());
        }
    }

    private static final void doAllPlatfromInit(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Class<?> cls = Class.forName(str + ".Init");
            if (cls != null) {
                cls.getMethod(XView2Constants.XVIEW2_ACTION_INIT, FloorManager.class).invoke(null, floorManager);
            }
        } catch (ClassNotFoundException e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        } catch (IllegalAccessException e3) {
            if (Log.D) {
                Log.d(t.f20145j, e3.getMessage());
            }
        } catch (NoSuchMethodException e4) {
            if (Log.D) {
                Log.d(t.f20145j, e4.getMessage());
            }
        } catch (Exception e5) {
            if (Log.D) {
                Log.d(t.f20145j, e5.getMessage());
            }
        }
    }

    public static boolean isCommonFloor(String str) {
        Class<? extends BaseFloor> classById = floorManager.getClassById(str);
        if (classById != null) {
            return COMMON_FLOORS.contains(classById.getName());
        }
        return false;
    }
}
