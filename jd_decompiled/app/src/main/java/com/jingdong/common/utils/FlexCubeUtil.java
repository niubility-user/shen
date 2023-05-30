package com.jingdong.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jdjr.mobilecert.MobileCertConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class FlexCubeUtil {
    public static List<JDJSONObject> filterToFlexCubeData(JDJSONObject jDJSONObject) {
        JDJSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jDJSONObject != null && (optJSONArray = jDJSONObject.optJSONArray("floorList")) != null) {
            for (JDJSONObject jDJSONObject2 : JDJSON.parseArray(optJSONArray.toJSONString(), JDJSONObject.class)) {
                if (jDJSONObject2 != null && jDJSONObject2.optString(MobileCertConstants.TEMPLATE, "").equals("flex_cube")) {
                    arrayList.add(jDJSONObject2);
                }
            }
        }
        return arrayList;
    }

    public static View getFlexCubeView(Context context, HashMap<Integer, String> hashMap, int i2) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        String str = hashMap.get(Integer.valueOf(i2));
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        View view = FlexCube.getView(context, str);
        if (view != null && (view instanceof IFloorView)) {
            ((IFloorView) view).initView(str);
        }
        return view;
    }

    public static <T extends FlexCubeModel> T transformFlexCubeModel(JDJSONObject jDJSONObject, Class<T> cls) {
        T t;
        int i2 = -1;
        if (jDJSONObject != null) {
            try {
                i2 = jDJSONObject.optInt("floorOrder", -1);
            } catch (Exception unused) {
            }
        }
        if (i2 >= 0 && (t = (T) JDJSON.optParseObject(jDJSONObject.toJSONString(), cls)) != null) {
            if (t.handleData()) {
                return t;
            }
        }
        return null;
    }

    public static HashMap<Integer, JDJSONObject> transformListToMapFlexCube(List<JDJSONObject> list) {
        HashMap<Integer, JDJSONObject> hashMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                JDJSONObject jDJSONObject = list.get(i2);
                if (jDJSONObject != null) {
                    hashMap.put(Integer.valueOf(jDJSONObject.optInt("position")), jDJSONObject);
                }
            }
        }
        return hashMap;
    }
}
