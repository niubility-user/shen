package com.facebook.react.util;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class JSStackTrace {
    private static final Pattern FILE_ID_PATTERN = Pattern.compile("\\b((?:seg-\\d+(?:_\\d+)?|\\d+)\\.js)");
    private static final Pattern JDREACT_FILE_ID_PATTERN = Pattern.compile(".+/(.+)$");

    public static String format(String str, ReadableArray readableArray) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(", stack:\n");
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            ReadableMap map = readableArray.getMap(i2);
            sb.append((!map.hasKey("methodName") || map.isNull("methodName")) ? "" : map.getString("methodName"));
            sb.append("#");
            sb.append(parseFileId(map));
            if (map.hasKey(StackTraceHelper.LINE_NUMBER_KEY) && !map.isNull(StackTraceHelper.LINE_NUMBER_KEY) && map.getType(StackTraceHelper.LINE_NUMBER_KEY) == ReadableType.Number) {
                sb.append(map.getInt(StackTraceHelper.LINE_NUMBER_KEY));
            } else {
                sb.append(-1);
            }
            if (map.hasKey("column") && !map.isNull("column") && map.getType("column") == ReadableType.Number) {
                sb.append(":");
                sb.append(map.getInt("column"));
            }
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }

    private static String parseFileId(ReadableMap readableMap) {
        if (readableMap.hasKey("file") && !readableMap.isNull("file") && readableMap.getType("file") == ReadableType.String) {
            Matcher matcher = FILE_ID_PATTERN.matcher(readableMap.getString("file"));
            if (matcher.find()) {
                return matcher.group(1) + DYConstants.DY_REGEX_AT;
            }
            try {
                Matcher matcher2 = JDREACT_FILE_ID_PATTERN.matcher(readableMap.getString("file"));
                if (matcher2.find()) {
                    return matcher2.group(0) + DYConstants.DY_REGEX_AT;
                }
                return "";
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }
}
