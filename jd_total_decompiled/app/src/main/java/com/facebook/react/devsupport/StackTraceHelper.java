package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class StackTraceHelper {
    public static final String COLUMN_KEY = "column";
    public static final String LINE_NUMBER_KEY = "lineNumber";
    private static final Pattern STACK_FRAME_PATTERN1 = Pattern.compile("^(?:(.*?)@)?(.*?)\\:([0-9]+)\\:([0-9]+)$");
    private static final Pattern STACK_FRAME_PATTERN2 = Pattern.compile("\\s*(?:at)\\s*(.+?)\\s*[@(](.*):([0-9]+):([0-9]+)[)]$");

    public static StackFrame[] convertJavaStackTrace(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StackFrame[] stackFrameArr = new StackFrame[stackTrace.length];
        for (int i2 = 0; i2 < stackTrace.length; i2++) {
            stackFrameArr[i2] = new StackFrameImpl(stackTrace[i2].getClassName(), stackTrace[i2].getFileName(), stackTrace[i2].getMethodName(), stackTrace[i2].getLineNumber(), -1);
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJsStackTrace(@Nullable ReadableArray readableArray) {
        int size = readableArray != null ? readableArray.size() : 0;
        StackFrame[] stackFrameArr = new StackFrame[size];
        for (int i2 = 0; i2 < size; i2++) {
            ReadableType type = readableArray.getType(i2);
            if (type == ReadableType.Map) {
                ReadableMap map = readableArray.getMap(i2);
                String string = map.getString("methodName");
                stackFrameArr[i2] = new StackFrameImpl(map.getString("file"), string, (!map.hasKey(LINE_NUMBER_KEY) || map.isNull(LINE_NUMBER_KEY)) ? -1 : map.getInt(LINE_NUMBER_KEY), (!map.hasKey("column") || map.isNull("column")) ? -1 : map.getInt("column"));
            } else if (type == ReadableType.String) {
                stackFrameArr[i2] = new StackFrameImpl((String) null, readableArray.getString(i2), -1, -1);
            }
        }
        return stackFrameArr;
    }

    public static String formatFrameSource(StackFrame stackFrame) {
        StringBuilder sb = new StringBuilder();
        sb.append(stackFrame.getFileName());
        int line = stackFrame.getLine();
        if (line > 0) {
            sb.append(":");
            sb.append(line);
            int column = stackFrame.getColumn();
            if (column > 0) {
                sb.append(":");
                sb.append(column);
            }
        }
        return sb.toString();
    }

    public static String formatStackTrace(String str, StackFrame[] stackFrameArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        for (StackFrame stackFrame : stackFrameArr) {
            sb.append(stackFrame.getMethod());
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append("    ");
            sb.append(formatFrameSource(stackFrame));
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }

    /* loaded from: classes12.dex */
    public static class StackFrameImpl implements StackFrame {
        private final int mColumn;
        private final String mFile;
        private final String mFileName;
        private final int mLine;
        private final String mMethod;

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getColumn() {
            return this.mColumn;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFile() {
            return this.mFile;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFileName() {
            return this.mFileName;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getLine() {
            return this.mLine;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getMethod() {
            return this.mMethod;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public JSONObject toJSON() {
            return new JSONObject(MapBuilder.of("file", getFile(), "methodName", getMethod(), StackTraceHelper.LINE_NUMBER_KEY, Integer.valueOf(getLine()), "column", Integer.valueOf(getColumn())));
        }

        private StackFrameImpl(String str, String str2, int i2, int i3) {
            this.mFile = str;
            this.mMethod = str2;
            this.mLine = i2;
            this.mColumn = i3;
            this.mFileName = str != null ? new File(str).getName() : "";
        }

        private StackFrameImpl(String str, String str2, String str3, int i2, int i3) {
            this.mFile = str;
            this.mFileName = str2;
            this.mMethod = str3;
            this.mLine = i2;
            this.mColumn = i3;
        }
    }

    public static StackFrame[] convertJsStackTrace(JSONArray jSONArray) {
        int length = jSONArray != null ? jSONArray.length() : 0;
        StackFrame[] stackFrameArr = new StackFrame[length];
        for (int i2 = 0; i2 < length; i2++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                stackFrameArr[i2] = new StackFrameImpl(jSONObject.getString("file"), jSONObject.getString("methodName"), (!jSONObject.has(LINE_NUMBER_KEY) || jSONObject.isNull(LINE_NUMBER_KEY)) ? -1 : jSONObject.getInt(LINE_NUMBER_KEY), (!jSONObject.has("column") || jSONObject.isNull("column")) ? -1 : jSONObject.getInt("column"));
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJsStackTrace(String str) {
        String[] split = str.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        StackFrame[] stackFrameArr = new StackFrame[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            Matcher matcher = STACK_FRAME_PATTERN1.matcher(split[i2]);
            Matcher matcher2 = STACK_FRAME_PATTERN2.matcher(split[i2]);
            if (matcher2.find()) {
                matcher = matcher2;
            } else if (!matcher.find()) {
                stackFrameArr[i2] = new StackFrameImpl((String) null, split[i2], -1, -1);
            }
            stackFrameArr[i2] = new StackFrameImpl(matcher.group(2), matcher.group(1) == null ? "(unknown)" : matcher.group(1), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
        return stackFrameArr;
    }
}
