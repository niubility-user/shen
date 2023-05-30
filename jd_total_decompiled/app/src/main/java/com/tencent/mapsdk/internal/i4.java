package com.tencent.mapsdk.internal;

import com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng;
import com.tencent.map.tools.json.JsonParser;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes9.dex */
public class i4 implements JsonParser.Deserializer<List<WeightedLatLng>> {
    private static List<WeightedLatLng> a(List<Double> list) {
        if (list != null) {
            if (list.size() < 3) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new WeightedLatLng(new LatLng(list.get(0).doubleValue(), list.get(1).doubleValue()), list.get(2).doubleValue()));
            for (int i2 = 3; i2 < list.size(); i2 += 3) {
                WeightedLatLng weightedLatLng = (WeightedLatLng) arrayList.get((i2 / 3) - 1);
                arrayList.add(new WeightedLatLng(new LatLng(weightedLatLng.getPoint().latitude + (list.get(i2).doubleValue() / 1000000.0d), weightedLatLng.getPoint().longitude + (list.get(i2 + 1).doubleValue() / 1000000.0d)), weightedLatLng.getIntensity() + (list.get(i2 + 2).doubleValue() / 100.0d)));
            }
            return arrayList;
        }
        return null;
    }

    @Override // com.tencent.map.tools.json.JsonParser.Deserializer
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public List<WeightedLatLng> deserialize(Object obj, String str, Object obj2) {
        if (obj2 != null && (obj2 instanceof JSONArray)) {
            JSONArray jSONArray = (JSONArray) obj2;
            if (jSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    arrayList.add(Double.valueOf(jSONArray.optDouble(i2)));
                }
                return a(arrayList);
            }
            return null;
        }
        return null;
    }
}
