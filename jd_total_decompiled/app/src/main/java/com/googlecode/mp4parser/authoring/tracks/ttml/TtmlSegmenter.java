package com.googlecode.mp4parser.authoring.tracks.ttml;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes12.dex */
public class TtmlSegmenter {
    public static void changeTime(Node node, String str, long j2) {
        if (node.getAttributes() == null || node.getAttributes().getNamedItem(str) == null) {
            return;
        }
        String nodeValue = node.getAttributes().getNamedItem(str).getNodeValue();
        long time = TtmlHelpers.toTime(nodeValue) + j2;
        node.getAttributes().getNamedItem(str).setNodeValue(TtmlHelpers.toTimeExpression(time, nodeValue.contains(OrderISVUtil.MONEY_DECIMAL) ? -1 : ((int) (time - ((time / 1000) * 1000))) / 44));
    }

    public static Document normalizeTimes(Document document) throws XPathExpressionException {
        XPath newXPath = XPathFactory.newInstance().newXPath();
        newXPath.setNamespaceContext(TtmlHelpers.NAMESPACE_CONTEXT);
        NodeList nodeList = (NodeList) newXPath.compile("//*[name()='p']").evaluate(document, XPathConstants.NODESET);
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            pushDown(nodeList.item(i2));
        }
        for (int i3 = 0; i3 < nodeList.getLength(); i3++) {
            Node item = nodeList.item(i3);
            removeAfterPushDown(item, "begin");
            removeAfterPushDown(item, "end");
        }
        return document;
    }

    private static void pushDown(Node node) {
        long j2 = 0;
        Node node2 = node;
        while (true) {
            node2 = node2.getParentNode();
            if (node2 == null) {
                break;
            } else if (node2.getAttributes() != null && node2.getAttributes().getNamedItem("begin") != null) {
                j2 += TtmlHelpers.toTime(node2.getAttributes().getNamedItem("begin").getNodeValue());
            }
        }
        if (node.getAttributes() != null && node.getAttributes().getNamedItem("begin") != null) {
            node.getAttributes().getNamedItem("begin").setNodeValue(TtmlHelpers.toTimeExpression(TtmlHelpers.toTime(node.getAttributes().getNamedItem("begin").getNodeValue()) + j2));
        }
        if (node.getAttributes() == null || node.getAttributes().getNamedItem("end") == null) {
            return;
        }
        node.getAttributes().getNamedItem("end").setNodeValue(TtmlHelpers.toTimeExpression(j2 + TtmlHelpers.toTime(node.getAttributes().getNamedItem("end").getNodeValue())));
    }

    private static void removeAfterPushDown(Node node, String str) {
        while (true) {
            node = node.getParentNode();
            if (node == null) {
                return;
            }
            if (node.getAttributes() != null && node.getAttributes().getNamedItem(str) != null) {
                node.getAttributes().removeNamedItem(str);
            }
        }
    }

    public static List<Document> split(Document document, int i2) throws XPathExpressionException {
        XPath xPath;
        XPathExpression xPathExpression;
        int i3 = i2 * 1000;
        XPath newXPath = XPathFactory.newInstance().newXPath();
        XPathExpression compile = newXPath.compile("//*[name()='p']");
        ArrayList arrayList = new ArrayList();
        while (true) {
            long size = arrayList.size() * i3;
            long size2 = (arrayList.size() + 1) * i3;
            Document document2 = (Document) document.cloneNode(true);
            NodeList nodeList = (NodeList) compile.evaluate(document2, XPathConstants.NODESET);
            int i4 = 0;
            boolean z = false;
            while (i4 < nodeList.getLength()) {
                int i5 = i3;
                Node item = nodeList.item(i4);
                long startTime = TtmlHelpers.getStartTime(item);
                long endTime = TtmlHelpers.getEndTime(item);
                if (startTime >= size || endTime <= size) {
                    xPath = newXPath;
                    xPathExpression = compile;
                } else {
                    xPath = newXPath;
                    xPathExpression = compile;
                    changeTime(item, "begin", size - startTime);
                    startTime = size;
                }
                if (startTime >= size && startTime < size2 && endTime > size2) {
                    changeTime(item, "end", size2 - endTime);
                    startTime = size;
                    endTime = size2;
                }
                if (startTime > size2) {
                    z = true;
                }
                if (startTime >= size && endTime <= size2) {
                    long j2 = -size;
                    changeTime(item, "begin", j2);
                    changeTime(item, "end", j2);
                } else {
                    item.getParentNode().removeChild(item);
                }
                i4++;
                newXPath = xPath;
                i3 = i5;
                compile = xPathExpression;
            }
            trimWhitespace(document2);
            Element element = (Element) newXPath.compile("/*[name()='tt']/*[name()='body'][1]").evaluate(document2, XPathConstants.NODE);
            String attribute = element.getAttribute("begin");
            String attribute2 = element.getAttribute("end");
            int i6 = i3;
            if (attribute != null && !"".equals(attribute)) {
                changeTime(element, "begin", size);
            } else {
                element.setAttribute("begin", TtmlHelpers.toTimeExpression(size));
            }
            if (attribute2 != null && !"".equals(attribute2)) {
                changeTime(element, "end", size2);
            } else {
                element.setAttribute("end", TtmlHelpers.toTimeExpression(size2));
            }
            arrayList.add(document2);
            if (!z) {
                return arrayList;
            }
            i3 = i6;
        }
    }

    public static void trimWhitespace(Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
            Node item = childNodes.item(i2);
            if (item.getNodeType() == 3) {
                item.setTextContent(item.getTextContent().trim());
            }
            trimWhitespace(item);
        }
    }
}
