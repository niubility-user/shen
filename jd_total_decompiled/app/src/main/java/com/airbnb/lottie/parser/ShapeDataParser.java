package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeDataParser implements ValueParser<ShapeData> {
    public static final ShapeDataParser INSTANCE = new ShapeDataParser();
    private static final JsonReader.Options NAMES = JsonReader.Options.of("c", "v", "i", "o");

    private ShapeDataParser() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.ValueParser
    public ShapeData parse(JsonReader jsonReader, float f2) throws IOException {
        if (jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.beginArray();
        }
        jsonReader.beginObject();
        List<PointF> list = null;
        List<PointF> list2 = null;
        List<PointF> list3 = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                z = jsonReader.nextBoolean();
            } else if (selectName == 1) {
                list = JsonUtils.jsonToPoints(jsonReader, f2);
            } else if (selectName == 2) {
                list2 = JsonUtils.jsonToPoints(jsonReader, f2);
            } else if (selectName != 3) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                list3 = JsonUtils.jsonToPoints(jsonReader, f2);
            }
        }
        jsonReader.endObject();
        if (jsonReader.peek() == JsonReader.Token.END_ARRAY) {
            jsonReader.endArray();
        }
        if (list != null && list2 != null && list3 != null) {
            if (list.isEmpty()) {
                return new ShapeData(new PointF(), false, Collections.emptyList());
            }
            int size = list.size();
            PointF pointF = list.get(0);
            ArrayList arrayList = new ArrayList(size);
            for (int i2 = 1; i2 < size; i2++) {
                PointF pointF2 = list.get(i2);
                int i3 = i2 - 1;
                arrayList.add(new CubicCurveData(MiscUtils.addPoints(list.get(i3), list3.get(i3)), MiscUtils.addPoints(pointF2, list2.get(i2)), pointF2));
            }
            if (z) {
                PointF pointF3 = list.get(0);
                int i4 = size - 1;
                arrayList.add(new CubicCurveData(MiscUtils.addPoints(list.get(i4), list3.get(i4)), MiscUtils.addPoints(pointF3, list2.get(0)), pointF3));
            }
            return new ShapeData(pointF, z, arrayList);
        }
        throw new IllegalArgumentException("Shape data was missing information.");
    }
}
