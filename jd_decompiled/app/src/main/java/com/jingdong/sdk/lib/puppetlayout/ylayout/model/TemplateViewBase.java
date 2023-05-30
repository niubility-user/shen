package com.jingdong.sdk.lib.puppetlayout.ylayout.model;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes8.dex */
public class TemplateViewBase {
    public static final String ELEM_TYPE_BUTTON = "Button";
    public static final String ELEM_TYPE_CARD_BUTTON = "CardButton";
    public static final String ELEM_TYPE_CAROUSEL = "Carousel";
    public static final String ELEM_TYPE_CHECKBOX = "CheckBox";
    public static final String ELEM_TYPE_COUNT_DOWN = "Countdown";
    public static final String ELEM_TYPE_CUSTOM_WIDGET = "CustomWidget";
    public static final String ELEM_TYPE_HORIZONTAL_VIEW = "HorizontalView";
    public static final String ELEM_TYPE_IMAGE = "Image";
    public static final String ELEM_TYPE_INDICATOR_1 = "Indicator1";
    public static final String ELEM_TYPE_INDICATOR_2 = "Indicator2";
    public static final String ELEM_TYPE_LINE = "Line";
    public static final String ELEM_TYPE_RADIO = "Radio";
    public static final String ELEM_TYPE_SPAN_TEXT = "SpanText";
    public static final String ELEM_TYPE_TEXT = "Text";
    public static final String ELEM_TYPE_VIEW = "View";
    public static final String ELEM_TYPE_VIEW_GROUP = "ViewGroup";
    public TemplateViewBase indicator1;
    public TemplateViewBase indicator2;
    public Iterate iterate;
    public ArrayList<Span> spans;
    public String elemType = ELEM_TYPE_VIEW_GROUP;
    public ArrayList<TemplateViewBase> elems = null;
    public String flexibleHeight = null;
    public String flexibleWidth = null;
    public ArrayList<Action> actions = null;
    public String layout = "";
    public HashMap<String, String> layoutMap = null;
    public String attribute = null;
    public HashMap<String, String> attributeMap = null;
    public boolean isDynamicSpans = false;

    public static String getCustomElemType(String str) {
        return str;
    }

    private HashMap<String, String> processMap(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        if (str.length() == 0) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : split) {
            if (!TextUtils.isEmpty(str2)) {
                String[] split2 = str2.split(":");
                if (split2.length == 2 && !TextUtils.isEmpty(split2[0]) && !TextUtils.isEmpty(split2[1])) {
                    hashMap.put(split2[0], split2[1]);
                }
            }
        }
        return hashMap;
    }

    public void createLineAttrs() {
        if (!this.elemType.equals(ELEM_TYPE_LINE)) {
        }
    }

    public void processActions(ArrayList<Action> arrayList) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        this.actions = arrayList;
    }

    public void processAttr(String str, String str2) {
        if (this.attributeMap == null) {
            this.attributeMap = new HashMap<>();
        }
        this.attributeMap.put(str, str2);
    }

    public void processLayout(String str, String str2) {
        if (this.layoutMap == null) {
            this.layoutMap = new HashMap<>();
        }
        this.layoutMap.put(str, str2);
    }

    public void processSpan(Span span) {
        if (span == null) {
            return;
        }
        if (!this.isDynamicSpans) {
            this.isDynamicSpans = span.hasDynamic();
        }
        if (this.spans == null) {
            this.spans = new ArrayList<>();
        }
        this.spans.add(span);
    }

    public void processSubView(TemplateViewBase templateViewBase) {
        if (this.elems == null) {
            this.elems = new ArrayList<>();
        }
        this.elems.add(templateViewBase);
    }

    public void redirectIndicator(TemplateViewBase templateViewBase) {
        this.indicator1 = templateViewBase;
    }

    public void setAttribute(String str) {
        this.attributeMap = processMap(str);
        this.attribute = str;
    }

    public void setLayout(String str) {
        this.layoutMap = processMap(str);
        this.layout = str;
    }
}
