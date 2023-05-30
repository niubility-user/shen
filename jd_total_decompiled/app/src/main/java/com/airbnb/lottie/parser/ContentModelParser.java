package com.airbnb.lottie.parser;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;

/* loaded from: classes.dex */
public class ContentModelParser {
    private static JsonReader.Options NAMES = JsonReader.Options.of("ty", "d");

    private ContentModelParser() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x00be, code lost:
        if (r2.equals("gf") == false) goto L210;
     */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ContentModel parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        ContentModel contentModel;
        String str;
        jsonReader.beginObject();
        char c2 = 2;
        int i2 = 2;
        while (true) {
            contentModel = null;
            if (!jsonReader.hasNext()) {
                str = null;
                break;
            }
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                str = jsonReader.nextString();
                break;
            } else if (selectName != 1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                i2 = jsonReader.nextInt();
            }
        }
        if (str == null) {
            return null;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case R2.color.button_y_a_elder_font_color /* 3239 */:
                if (str.equals("el")) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.c_27CA69 /* 3270 */:
                if (str.equals("fl")) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.c_52B948 /* 3295 */:
                break;
            case R2.color.c_686868 /* 3307 */:
                if (str.equals("gr")) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.c_6A6A6A /* 3308 */:
                if (str.equals("gs")) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.c_F2F2F2 /* 3488 */:
                if (str.equals("mm")) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.color_2572AC /* 3633 */:
                if (str.equals("rc")) {
                    c2 = 6;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.color_4F4F4F /* 3646 */:
                if (str.equals("rp")) {
                    c2 = 7;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.color_DD493E /* 3669 */:
                if (str.equals("sh")) {
                    c2 = '\b';
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.color_EFEFEF /* 3679 */:
                if (str.equals("sr")) {
                    c2 = '\t';
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.color_F3AB57 /* 3681 */:
                if (str.equals("st")) {
                    c2 = '\n';
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.color_black_token_cloud /* 3705 */:
                if (str.equals("tm")) {
                    c2 = 11;
                    break;
                }
                c2 = '\uffff';
                break;
            case R2.color.color_main_token_cloud /* 3710 */:
                if (str.equals("tr")) {
                    c2 = '\f';
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
                contentModel = CircleShapeParser.parse(jsonReader, lottieComposition, i2);
                break;
            case 1:
                contentModel = ShapeFillParser.parse(jsonReader, lottieComposition);
                break;
            case 2:
                contentModel = GradientFillParser.parse(jsonReader, lottieComposition);
                break;
            case 3:
                contentModel = ShapeGroupParser.parse(jsonReader, lottieComposition);
                break;
            case 4:
                contentModel = GradientStrokeParser.parse(jsonReader, lottieComposition);
                break;
            case 5:
                contentModel = MergePathsParser.parse(jsonReader);
                lottieComposition.addWarning("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            case 6:
                contentModel = RectangleShapeParser.parse(jsonReader, lottieComposition);
                break;
            case 7:
                contentModel = RepeaterParser.parse(jsonReader, lottieComposition);
                break;
            case '\b':
                contentModel = ShapePathParser.parse(jsonReader, lottieComposition);
                break;
            case '\t':
                contentModel = PolystarShapeParser.parse(jsonReader, lottieComposition);
                break;
            case '\n':
                contentModel = ShapeStrokeParser.parse(jsonReader, lottieComposition);
                break;
            case 11:
                contentModel = ShapeTrimPathParser.parse(jsonReader, lottieComposition);
                break;
            case '\f':
                contentModel = AnimatableTransformParser.parse(jsonReader, lottieComposition);
                break;
            default:
                Logger.warning("Unknown shape type " + str);
                break;
        }
        while (jsonReader.hasNext()) {
            jsonReader.skipValue();
        }
        jsonReader.endObject();
        return contentModel;
    }
}
