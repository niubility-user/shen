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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase readViewBase(org.xmlpull.v1.XmlPullParser r21, java.lang.String r22) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.lib.puppetlayout.ylayout.TemplateXmlParser.readViewBase(org.xmlpull.v1.XmlPullParser, java.lang.String):com.jingdong.sdk.lib.puppetlayout.ylayout.model.TemplateViewBase");
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
