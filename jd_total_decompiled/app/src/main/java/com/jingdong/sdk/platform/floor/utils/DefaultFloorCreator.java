package com.jingdong.sdk.platform.floor.utils;

import android.content.Context;
import android.view.ViewGroup;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.lang.reflect.Constructor;

/* loaded from: classes10.dex */
public class DefaultFloorCreator extends FloorCreator {
    private static final String TAG = "DefaultFloorCreator";

    private DefaultFloorCreator() {
    }

    public static DefaultFloorCreator newInstance() {
        return new DefaultFloorCreator();
    }

    @Override // com.jingdong.sdk.platform.floor.utils.FloorCreator
    public BaseFloor createFloor(Context context, BaseFloorData baseFloorData, String str, Class cls, ViewGroup viewGroup) {
        if (cls != null) {
            if (PlatformTools.D) {
                PlatformTools.d(TAG, "CLASS NANE = " + cls.getName());
            }
            try {
                Constructor<?> constructor = cls.getConstructors()[0];
                constructor.setAccessible(true);
                return (BaseFloor) constructor.newInstance(context, baseFloorData, str, viewGroup);
            } catch (Exception e2) {
                if (PlatformTools.D) {
                    PlatformTools.catchExceptionAndThrow(e2);
                    PlatformTools.d(TAG, "CLASS NANE = " + cls.getName());
                }
            }
        } else if (PlatformTools.D) {
            PlatformTools.d(TAG, "CLASS NANE = null");
        }
        return null;
    }
}
