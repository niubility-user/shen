package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes.dex */
class FontCharacterParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("ch", ApkDownloadTable.FIELD_SIZE, JshopConst.JSHOP_PROMOTIO_W, DeeplinkProductDetailHelper.LAYER_STYLE, "fFamily", "data");
    private static final JsonReader.Options DATA_NAMES = JsonReader.Options.of("shapes");

    private FontCharacterParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FontCharacter parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        double d = 0.0d;
        double d2 = 0.0d;
        char c2 = 0;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                c2 = jsonReader.nextString().charAt(0);
            } else if (selectName == 1) {
                d = jsonReader.nextDouble();
            } else if (selectName == 2) {
                d2 = jsonReader.nextDouble();
            } else if (selectName == 3) {
                str = jsonReader.nextString();
            } else if (selectName == 4) {
                str2 = jsonReader.nextString();
            } else if (selectName != 5) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    if (jsonReader.selectName(DATA_NAMES) != 0) {
                        jsonReader.skipName();
                        jsonReader.skipValue();
                    } else {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            arrayList.add((ShapeGroup) ContentModelParser.parse(jsonReader, lottieComposition));
                        }
                        jsonReader.endArray();
                    }
                }
                jsonReader.endObject();
            }
        }
        jsonReader.endObject();
        return new FontCharacter(arrayList, c2, d, d2, str, str2);
    }
}
