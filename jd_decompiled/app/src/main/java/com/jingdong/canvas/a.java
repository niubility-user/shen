package com.jingdong.canvas;

import android.os.Build;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes4.dex */
public class a {
    private boolean a;
    private HashMap<List<String>, List<String>> b = null;

    /* renamed from: c  reason: collision with root package name */
    private List<String> f12307c = null;

    public a() {
        this.a = false;
        if (0 == 0) {
            g();
            h();
            this.a = true;
        }
    }

    private void e(Document document) throws Exception {
        List<String> list = this.f12307c;
        if (list == null) {
            this.f12307c = new ArrayList();
        } else {
            list.clear();
        }
        Element documentElement = document.getDocumentElement();
        if (!documentElement.getTagName().equals("familyset")) {
            com.jingdong.canvas.b.a.g("GFontConfigParser", "Can't find familyset.");
            return;
        }
        NodeList elementsByTagName = documentElement.getElementsByTagName(IMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY);
        int length = elementsByTagName.getLength();
        for (int i2 = 0; i2 < length; i2++) {
            Node item = elementsByTagName.item(i2);
            Node node = null;
            if (item instanceof Element) {
                NodeList elementsByTagName2 = ((Element) item).getElementsByTagName("fileset");
                if (elementsByTagName2 != null && elementsByTagName2.getLength() == 1) {
                    node = elementsByTagName2.item(0);
                } else {
                    com.jingdong.canvas.b.a.g("GFontConfigParser", "nameset or fileset node doesn't exist.");
                    return;
                }
            }
            if (node == null) {
                com.jingdong.canvas.b.a.g("GFontConfigParser", "nameset or fileset is invalid.");
                return;
            }
            NodeList childNodes = node.getChildNodes();
            if (childNodes == null) {
                com.jingdong.canvas.b.a.g("GFontConfigParser", "nameset or fileset is empty.");
                return;
            }
            int length2 = childNodes.getLength();
            for (int i3 = 0; i3 < length2; i3++) {
                Node item2 = childNodes.item(i3);
                if (item2 instanceof Element) {
                    Element element = (Element) item2;
                    if (element.getTagName().equals("file")) {
                        this.f12307c.add(element.getTextContent());
                    }
                }
            }
        }
    }

    private void f(Document document) throws Exception {
        Node node;
        NodeList nodeList;
        HashMap<List<String>, List<String>> hashMap = this.b;
        if (hashMap == null) {
            this.b = new HashMap<>();
        } else {
            hashMap.clear();
        }
        Element documentElement = document.getDocumentElement();
        if (!documentElement.getTagName().equals("familyset")) {
            com.jingdong.canvas.b.a.g("GFontConfigParser", "Can't find familyset.");
        } else if (Build.VERSION.SDK_INT >= 21) {
            NodeList elementsByTagName = documentElement.getElementsByTagName(IMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY);
            int length = elementsByTagName.getLength();
            int i2 = 0;
            while (i2 < length) {
                Node item = elementsByTagName.item(i2);
                item.getAttributes();
                Node namedItem = item.getAttributes().getNamedItem("name");
                if (namedItem == null) {
                    return;
                }
                NodeList elementsByTagName2 = ((Element) item).getElementsByTagName("font");
                if (elementsByTagName2 == null) {
                    com.jingdong.canvas.b.a.g("GFontConfigParser", "nameset or fileset is invalid.");
                    return;
                }
                int length2 = elementsByTagName2.getLength();
                ArrayList arrayList = new ArrayList();
                arrayList.add(namedItem.getNodeValue());
                ArrayList arrayList2 = new ArrayList();
                for (int i3 = 0; i3 < length2; i3++) {
                    Node item2 = elementsByTagName2.item(i3);
                    if (item2 instanceof Element) {
                        arrayList2.add(((Element) item2).getTextContent());
                    }
                }
                NodeList elementsByTagName3 = documentElement.getElementsByTagName(PushConstants.SUB_ALIAS_STATUS_NAME);
                int length3 = elementsByTagName3.getLength();
                for (int i4 = 0; i4 < length3; i4++) {
                    Node item3 = elementsByTagName3.item(i4);
                    Node namedItem2 = item3.getAttributes().getNamedItem("name");
                    Node namedItem3 = item3.getAttributes().getNamedItem(RemoteMessageConst.TO);
                    if (namedItem2 != null && namedItem3 != null) {
                        ArrayList arrayList3 = new ArrayList();
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            NodeList nodeList2 = elementsByTagName;
                            int i5 = length;
                            if (((String) it.next()).equals(namedItem3.getNodeValue())) {
                                arrayList3.add(namedItem2.getNodeValue());
                            }
                            length = i5;
                            elementsByTagName = nodeList2;
                        }
                        arrayList.addAll(arrayList3);
                    }
                    this.b.put(arrayList, arrayList2);
                    i2++;
                    length = length;
                    elementsByTagName = elementsByTagName;
                }
                this.b.put(arrayList, arrayList2);
                i2++;
                length = length;
                elementsByTagName = elementsByTagName;
            }
        } else {
            NodeList elementsByTagName4 = documentElement.getElementsByTagName(IMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY);
            int length4 = elementsByTagName4.getLength();
            int i6 = 0;
            while (i6 < length4) {
                Node item4 = elementsByTagName4.item(i6);
                Node node2 = null;
                if (item4 instanceof Element) {
                    Element element = (Element) item4;
                    NodeList elementsByTagName5 = element.getElementsByTagName("nameset");
                    NodeList elementsByTagName6 = element.getElementsByTagName("fileset");
                    if (elementsByTagName5 != null && elementsByTagName6 != null && elementsByTagName5.getLength() == 1 && elementsByTagName6.getLength() == 1) {
                        node2 = elementsByTagName5.item(0);
                        node = elementsByTagName6.item(0);
                    } else {
                        com.jingdong.canvas.b.a.g("GFontConfigParser", "nameset or fileset node doesn't exist.");
                        return;
                    }
                } else {
                    node = null;
                }
                if (node2 != null && node != null) {
                    NodeList childNodes = node2.getChildNodes();
                    NodeList childNodes2 = node.getChildNodes();
                    if (childNodes != null && childNodes2 != null) {
                        int length5 = childNodes.getLength();
                        int length6 = childNodes2.getLength();
                        ArrayList arrayList4 = new ArrayList();
                        ArrayList arrayList5 = new ArrayList();
                        int i7 = 0;
                        while (i7 < length6) {
                            Node item5 = childNodes2.item(i7);
                            if (item5 instanceof Element) {
                                Element element2 = (Element) item5;
                                nodeList = elementsByTagName4;
                                if (element2.getTagName().equals("file")) {
                                    arrayList5.add(element2.getTextContent());
                                }
                            } else {
                                nodeList = elementsByTagName4;
                            }
                            i7++;
                            elementsByTagName4 = nodeList;
                        }
                        NodeList nodeList3 = elementsByTagName4;
                        for (int i8 = 0; i8 < length5; i8++) {
                            Node item6 = childNodes.item(i8);
                            if (item6 instanceof Element) {
                                Element element3 = (Element) item6;
                                if (element3.getTagName().equals("name")) {
                                    arrayList4.add(element3.getTextContent());
                                }
                            }
                        }
                        this.b.put(arrayList4, arrayList5);
                        i6++;
                        elementsByTagName4 = nodeList3;
                    } else {
                        com.jingdong.canvas.b.a.g("GFontConfigParser", "nameset or fileset is empty.");
                        return;
                    }
                } else {
                    com.jingdong.canvas.b.a.g("GFontConfigParser", "nameset or fileset is invalid.");
                    return;
                }
            }
        }
    }

    private void g() {
        Document parse;
        try {
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            if (Build.VERSION.SDK_INT >= 21) {
                parse = newDocumentBuilder.parse(new File("/system/etc/fonts.xml"));
            } else {
                parse = newDocumentBuilder.parse(new File("/system/etc/system_fonts.xml"));
            }
            f(parse);
        } catch (Exception e2) {
            com.jingdong.canvas.b.a.c("GFontConfigParser", e2.getMessage());
        }
    }

    private void h() {
        try {
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            File file = new File("/system/etc/fallback_fonts.xml");
            if (file.exists()) {
                e(newDocumentBuilder.parse(file));
            }
        } catch (Exception e2) {
            String str = "readFallbackConfigFile " + e2.getMessage();
        }
    }

    public String a() {
        return new File("/system/fonts/DroidSansFallbackBBK.ttf").exists() ? "DroidSansFallbackBBK.ttf" : new File("/system/fonts/NotoSansHans-Regular.otf").exists() ? "NotoSansHans-Regular.otf" : new File("/system/fonts/NotoSansSC-Regular.otf").exists() ? "NotoSansSC-Regular.otf" : new File("/system/fonts/NotoSansCJK-Regular.ttc").exists() ? "NotoSansCJK-Regular.ttc" : new File("/system/fonts/DroidSansFallback.ttf").exists() ? "DroidSansFallback.ttf" : "DroidSans.ttf";
    }

    public List<String> b() {
        return this.f12307c;
    }

    public HashMap<List<String>, List<String>> c() {
        return this.b;
    }

    public String d() {
        return "/system/fonts/";
    }
}
