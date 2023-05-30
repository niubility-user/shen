package com.jd.viewkit.tool;

import android.graphics.Color;
import com.jingdong.common.utils.LangUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes18.dex */
public class ColorTool {
    private static Pattern patternHex = Pattern.compile("^#[0-9a-fA-F]{6}$");
    private static Pattern patternRbg = Pattern.compile("rgb\\((2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),((2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?))\\)");
    private static Pattern patternRbga = Pattern.compile("rgba\\((2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),(2[0-4][0-9]|25[0-5]|[01]?[0-9][0-9]?),((1\\.0+|[01]|0\\.{1}\\d+))\\)");

    public static void main(String[] strArr) {
        Matcher matcher = patternRbga.matcher("rgba(0,100,166,1.0)");
        if (matcher.find()) {
            System.out.println("hahah");
        }
        System.out.println("asdfsdf::" + matcher.groupCount());
        for (int i2 = 0; i2 < matcher.groupCount(); i2++) {
            System.out.println("index::" + i2 + "      :::" + matcher.group(i2));
        }
    }

    public static int parseColor(String str, int i2) {
        if (StringTool.isEmpty(str)) {
            return i2;
        }
        int parseHexColor = patternHex.matcher(str).find() ? parseHexColor(str, i2) : i2;
        String replace = str.replace(LangUtils.SINGLE_SPACE, "");
        if (StringTool.isBegin(replace, "rgba")) {
            return parseRgbaColor(replace, i2);
        }
        return StringTool.isBegin(replace, "rgb") ? parseRgbColor(replace, i2) : parseHexColor;
    }

    public static int parseHexColor(String str, int i2) {
        if (StringTool.isEmpty(str)) {
            return i2;
        }
        try {
            return Color.parseColor(str.toLowerCase());
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }

    public static int parseRgbColor(String str, int i2) {
        if (StringTool.isEmpty(str)) {
            return i2;
        }
        try {
            Matcher matcher = patternRbg.matcher(str);
            if (matcher.find()) {
                return Color.argb(255, NumberTool.valueOfInt(matcher.group(1)), NumberTool.valueOfInt(matcher.group(2)), NumberTool.valueOfInt(matcher.group(3)));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i2;
    }

    public static int parseRgbaColor(String str, int i2) {
        if (StringTool.isEmpty(str)) {
            return i2;
        }
        try {
            Matcher matcher = patternRbga.matcher(str);
            if (matcher.find() && matcher.groupCount() == 5) {
                return Color.argb((int) (NumberTool.valueOfFloat(matcher.group(4)) * 255.0f), NumberTool.valueOfInt(matcher.group(1)), NumberTool.valueOfInt(matcher.group(2)), NumberTool.valueOfInt(matcher.group(3)));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return i2;
    }
}
