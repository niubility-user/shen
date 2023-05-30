package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.text.TextUtils;
import android.util.Xml;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Exposure;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Iterate;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Span;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateModel;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes8.dex */
public class TemplateXmlParser {
    private static final String TAG_NAME_ACTIONS = "Actions";
    private static final String TAG_NAME_ITERATE = "Iterate";
    private static final String TAG_NAME_SPAN = "Span";
    private static final String ns = null;

    private Action readActionElem(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, ns, str);
        int attributeCount = xmlPullParser.getAttributeCount();
        String str2 = "";
        String str3 = null;
        String str4 = "";
        for (int i2 = 0; i2 < attributeCount; i2++) {
            if (xmlPullParser.getAttributeName(i2).equals("type")) {
                str2 = xmlPullParser.getAttributeValue(i2);
            } else if (xmlPullParser.getAttributeName(i2).equals("value")) {
                str4 = xmlPullParser.getAttributeValue(i2);
            } else if (xmlPullParser.getAttributeName(i2).equals("client")) {
                str3 = xmlPullParser.getAttributeValue(i2);
            }
        }
        skip(xmlPullParser);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str4)) {
            return null;
        }
        Action action = new Action();
        action.key = str;
        action.type = str2;
        action.value = str4;
        action.parse(str2, str4, str3);
        return action;
    }

    private ArrayList<Action> readActions(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, ns, TAG_NAME_ACTIONS);
        ArrayList<Action> arrayList = new ArrayList<>();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                Action action = null;
                if (xmlPullParser.getName().equals(Action.ActionType_COMMON)) {
                    action = readActionElem(xmlPullParser, Action.ActionType_COMMON);
                } else {
                    skip(xmlPullParser);
                }
                if (action != null && action.isClientAndroid()) {
                    arrayList.add(action);
                }
            }
        }
        return arrayList;
    }

    private Exposure readExposure(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, ns, Action.ActionType_EXPO);
        Exposure exposure = new Exposure();
        skip(xmlPullParser);
        return exposure;
    }

    private Iterate readIterate(TemplateViewBase templateViewBase, XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, ns, TAG_NAME_ITERATE);
        Iterate iterate = new Iterate();
        int attributeCount = xmlPullParser.getAttributeCount();
        String str = "";
        String str2 = "";
        String str3 = str2;
        for (int i2 = 0; i2 < attributeCount; i2++) {
            if (xmlPullParser.getAttributeName(i2).equals("iterateOn")) {
                str = xmlPullParser.getAttributeValue(i2);
            } else if (xmlPullParser.getAttributeName(i2).equals("indexPropName")) {
                str2 = xmlPullParser.getAttributeValue(i2);
            } else if (xmlPullParser.getAttributeName(i2).equals("eachPropName")) {
                str3 = xmlPullParser.getAttributeValue(i2);
            }
        }
        iterate.arrayVar = str;
        if (TextUtils.isEmpty(str2)) {
            str2 = "index";
        }
        iterate.index = str2;
        iterate.item = str3;
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                TemplateViewBase templateViewBase2 = null;
                if (!name.equals(TemplateViewBase.ELEM_TYPE_VIEW) && !name.equals(TemplateViewBase.ELEM_TYPE_TEXT) && !name.equals(TemplateViewBase.ELEM_TYPE_BUTTON) && !name.equals(TemplateViewBase.ELEM_TYPE_IMAGE) && !name.equals(TemplateViewBase.ELEM_TYPE_RADIO) && !name.equals("Checkbox")) {
                    skip(xmlPullParser);
                } else {
                    templateViewBase2 = readViewBase(xmlPullParser, name);
                }
                if (templateViewBase2 != null) {
                    iterate.itemViewBase = templateViewBase2;
                }
            }
        }
        return iterate;
    }

    private Span readSpanElem(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, ns, str);
        int attributeCount = xmlPullParser.getAttributeCount();
        Span span = new Span();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            span.update(xmlPullParser.getAttributeName(i2), xmlPullParser.getAttributeValue(i2));
        }
        skip(xmlPullParser);
        return span;
    }

    private TemplateModel readTemplate(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        TemplateModel templateModel = new TemplateModel();
        xmlPullParser.require(2, ns, "Style");
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            if (!XView2Constants.STYLEID.equals(attributeName) && !"sid".equals(attributeName)) {
                if (!"minSdkVersion".equals(attributeName) && !"supportSdkVersion".equals(attributeName)) {
                    if ("name".equals(attributeName)) {
                        templateModel.name = xmlPullParser.getAttributeValue(i2);
                    } else if (!"styleVersion".equals(attributeName) && !"version".equals(attributeName)) {
                        if ("flexibleWidth".equals(attributeName)) {
                            templateModel.flexibleWidth = xmlPullParser.getAttributeValue(i2);
                        } else if ("flexibleHeight".equals(attributeName)) {
                            templateModel.flexibleHeight = xmlPullParser.getAttributeValue(i2);
                        }
                    } else {
                        templateModel.version = xmlPullParser.getAttributeValue(i2);
                    }
                } else {
                    templateModel.minSdkVersion = xmlPullParser.getAttributeValue(i2);
                }
            } else {
                templateModel.templateId = xmlPullParser.getAttributeValue(i2);
            }
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals(TemplateViewBase.ELEM_TYPE_VIEW)) {
                    templateModel.template = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_VIEW);
                } else {
                    skip(xmlPullParser);
                }
            }
        }
        return templateModel;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private TemplateViewBase readViewBase(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String str2;
        String str3;
        boolean z;
        boolean z2;
        String str4;
        TemplateViewBase readViewBase;
        xmlPullParser.require(2, ns, str);
        TemplateViewBase templateViewBase = new TemplateViewBase();
        int attributeCount = xmlPullParser.getAttributeCount();
        int i2 = 0;
        while (true) {
            str2 = TemplateViewBase.ELEM_TYPE_INDICATOR_2;
            str3 = TemplateViewBase.ELEM_TYPE_INDICATOR_1;
            if (i2 >= attributeCount) {
                break;
            }
            String attributeName = xmlPullParser.getAttributeName(i2);
            if (!TextUtils.isEmpty(attributeName)) {
                if (attributeName.startsWith("layout_")) {
                    String substring = attributeName.substring(7, attributeName.length());
                    templateViewBase.processLayout(substring, xmlPullParser.getAttributeValue(i2));
                    if ((TemplateViewBase.ELEM_TYPE_TEXT.equals(str) || TemplateViewBase.ELEM_TYPE_IMAGE.equals(str) || TemplateViewBase.ELEM_TYPE_BUTTON.equals(str) || TemplateViewBase.ELEM_TYPE_CAROUSEL.equals(str) || TemplateViewBase.ELEM_TYPE_INDICATOR_1.equals(str) || TemplateViewBase.ELEM_TYPE_INDICATOR_2.equals(str) || TemplateViewBase.ELEM_TYPE_HORIZONTAL_VIEW.equals(str)) && !"paddingLeft".equals(substring) && !"paddingTop".equals(substring) && !"paddingRight".equals(substring)) {
                        "paddingBottom".equals(substring);
                    }
                } else if (attributeName.startsWith("attribute_")) {
                    templateViewBase.processAttr(attributeName.substring(10, attributeName.length()), xmlPullParser.getAttributeValue(i2));
                }
            }
            i2++;
        }
        String str5 = TemplateViewBase.ELEM_TYPE_VIEW;
        boolean equals = TemplateViewBase.ELEM_TYPE_VIEW.equals(str);
        if (TemplateViewBase.ELEM_TYPE_CAROUSEL.equals(str)) {
            z = true;
        } else if (TemplateViewBase.ELEM_TYPE_SPAN_TEXT.equals(str)) {
            z = false;
            z2 = true;
            templateViewBase.elemType = str;
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    String name = xmlPullParser.getName();
                    String str6 = str2;
                    String str7 = str3;
                    if (equals) {
                        if (name.equals(str5)) {
                            readViewBase = readViewBase(xmlPullParser, str5);
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_TEXT)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_TEXT);
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_BUTTON)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_BUTTON);
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_IMAGE)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_IMAGE);
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_RADIO)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_RADIO);
                        } else if (name.equals("Checkbox")) {
                            readViewBase = readViewBase(xmlPullParser, "Checkbox");
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_LINE)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_LINE);
                            readViewBase.createLineAttrs();
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_CAROUSEL)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_CAROUSEL);
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_HORIZONTAL_VIEW)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_HORIZONTAL_VIEW);
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_CARD_BUTTON)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_CARD_BUTTON);
                        } else if (name.equals(TemplateViewBase.ELEM_TYPE_CUSTOM_WIDGET)) {
                            readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_CUSTOM_WIDGET);
                        } else {
                            if (name.equals(TAG_NAME_ACTIONS)) {
                                ArrayList<Action> readActions = readActions(xmlPullParser);
                                if (readActions != null) {
                                    templateViewBase.processActions(readActions);
                                }
                            } else if (name.equals(TAG_NAME_ITERATE)) {
                                Iterate readIterate = readIterate(null, xmlPullParser);
                                if (readIterate != null) {
                                    templateViewBase.iterate = readIterate;
                                }
                            } else if (name.equals(TemplateViewBase.ELEM_TYPE_SPAN_TEXT)) {
                                readViewBase = readViewBase(xmlPullParser, TemplateViewBase.ELEM_TYPE_SPAN_TEXT);
                            } else if (name.equals("Countdown")) {
                                readViewBase = readViewBase(xmlPullParser, "Countdown");
                            } else {
                                skip(xmlPullParser);
                            }
                            readViewBase = null;
                        }
                        if (readViewBase != null) {
                            templateViewBase.processSubView(readViewBase);
                        }
                        str3 = str7;
                    } else {
                        str3 = str7;
                        if (z && name.equals(str3)) {
                            templateViewBase.redirectIndicator(readViewBase(xmlPullParser, str3));
                        } else {
                            str4 = str5;
                            if (z && name.equals(str6)) {
                                templateViewBase.indicator2 = readViewBase(xmlPullParser, str6);
                                str6 = str6;
                            } else {
                                str6 = str6;
                                if (z2 && name.equals(TAG_NAME_SPAN)) {
                                    templateViewBase.processSpan(readSpanElem(xmlPullParser, TAG_NAME_SPAN));
                                } else if (name.equals(TAG_NAME_ACTIONS)) {
                                    ArrayList<Action> readActions2 = readActions(xmlPullParser);
                                    if (readActions2 != null && readActions2.size() > 0) {
                                        templateViewBase.processActions(readActions2);
                                    }
                                } else if (name.equals(TAG_NAME_ITERATE)) {
                                    Iterate readIterate2 = readIterate(null, xmlPullParser);
                                    if (readIterate2 != null) {
                                        templateViewBase.iterate = readIterate2;
                                    }
                                } else {
                                    skip(xmlPullParser);
                                }
                            }
                            str2 = str6;
                            str5 = str4;
                        }
                    }
                    str4 = str5;
                    str2 = str6;
                    str5 = str4;
                }
            }
            return templateViewBase;
        } else {
            z = false;
        }
        z2 = false;
        templateViewBase.elemType = str;
        while (xmlPullParser.next() != 3) {
        }
        return templateViewBase;
    }

    private void skip(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i2 = 1;
        while (i2 != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }

    public TemplateModel parse(StringReader stringReader) throws XmlPullParserException, IOException {
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            newPullParser.setInput(stringReader);
            newPullParser.nextTag();
            return readTemplate(newPullParser);
        } finally {
            stringReader.close();
        }
    }
}
