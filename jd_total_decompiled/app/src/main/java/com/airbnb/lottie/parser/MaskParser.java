package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.a.a;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;

/* loaded from: classes.dex */
class MaskParser {
    private MaskParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006c, code lost:
        if (r1.equals("s") == false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Mask parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        char c2;
        jsonReader.beginObject();
        Mask.MaskMode maskMode = null;
        AnimatableShapeValue animatableShapeValue = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            nextName.hashCode();
            char c3 = 3;
            switch (nextName.hashCode()) {
                case 111:
                    if (nextName.equals("o")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case R2.color.c_e18e0c /* 3588 */:
                    if (nextName.equals("pt")) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 104433:
                    if (nextName.equals("inv")) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 3357091:
                    if (nextName.equals("mode")) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
                    break;
                case 1:
                    animatableShapeValue = AnimatableValueParser.parseShapeData(jsonReader, lottieComposition);
                    break;
                case 2:
                    z = jsonReader.nextBoolean();
                    break;
                case 3:
                    String nextString = jsonReader.nextString();
                    nextString.hashCode();
                    switch (nextString.hashCode()) {
                        case 97:
                            if (nextString.equals(a.a)) {
                                c3 = 0;
                                break;
                            }
                            c3 = '\uffff';
                            break;
                        case 105:
                            if (nextString.equals("i")) {
                                c3 = 1;
                                break;
                            }
                            c3 = '\uffff';
                            break;
                        case 110:
                            if (nextString.equals(PersonalConstants.ICON_STYLE_N)) {
                                c3 = 2;
                                break;
                            }
                            c3 = '\uffff';
                            break;
                        case 115:
                            break;
                        default:
                            c3 = '\uffff';
                            break;
                    }
                    switch (c3) {
                        case 0:
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            continue;
                        case 1:
                            lottieComposition.addWarning("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                            maskMode = Mask.MaskMode.MASK_MODE_INTERSECT;
                            continue;
                        case 2:
                            maskMode = Mask.MaskMode.MASK_MODE_NONE;
                            continue;
                        case 3:
                            maskMode = Mask.MaskMode.MASK_MODE_SUBTRACT;
                            continue;
                        default:
                            Logger.warning("Unknown mask mode " + nextName + ". Defaulting to Add.");
                            maskMode = Mask.MaskMode.MASK_MODE_ADD;
                            continue;
                    }
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return new Mask(maskMode, animatableShapeValue, animatableIntegerValue, z);
    }
}
