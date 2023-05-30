package com.jd.dynamic.base;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.entity.ViewNode;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Keep
@Deprecated
/* loaded from: classes13.dex */
public class XMLParse {
    private InputStream in;
    public ConcurrentHashMap<ViewNode, HashMap<String, String>> unBindMaps = new ConcurrentHashMap<>();

    public XMLParse(InputStream inputStream) {
        this.in = inputStream;
    }

    private ViewNode parseNode(ViewNode viewNode, Node node, AtomicInteger atomicInteger, List<Integer> list) {
        NodeList childNodes;
        if (node.getNodeType() != 1) {
            return null;
        }
        ViewNode viewNode2 = new ViewNode();
        viewNode2.setViewName(node.getNodeName());
        viewNode2.textContent = node.getTextContent();
        NamedNodeMap attributes = node.getAttributes();
        HashMap<String, String> hashMap = new HashMap<>();
        HashMap<String, String> hashMap2 = new HashMap<>();
        if (DYConstants.DY_ITEMS.equals(viewNode2.getViewName()) || (viewNode != null && viewNode.unNeedInitBind)) {
            viewNode2.unNeedInitBind = true;
        }
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            Node item = attributes.item(i2);
            hashMap.put(item.getNodeName(), item.getNodeValue());
            if (DynamicUtils.isElOrKnownSymbol(item.getNodeValue())) {
                hashMap2.put(item.getNodeName(), item.getNodeValue());
            }
        }
        String str = hashMap.get("layoutId");
        if (!TextUtils.isEmpty(str)) {
            try {
                viewNode2.viewId = Integer.parseInt(str);
            } catch (Exception unused) {
            }
            list.add(Integer.valueOf(viewNode2.viewId));
            this.unBindMaps.put(viewNode2, hashMap2);
            viewNode2.setAttributes(hashMap);
            childNodes = node.getChildNodes();
            if (childNodes != null && childNodes.getLength() != 0) {
                viewNode2.setChilds(parseNodeList(viewNode2, childNodes, atomicInteger, list));
            }
            return viewNode2;
        }
        viewNode2.viewId = com.jd.dynamic.lib.dynamic.parser.h.b(atomicInteger, list);
        list.add(Integer.valueOf(viewNode2.viewId));
        this.unBindMaps.put(viewNode2, hashMap2);
        viewNode2.setAttributes(hashMap);
        childNodes = node.getChildNodes();
        if (childNodes != null) {
            viewNode2.setChilds(parseNodeList(viewNode2, childNodes, atomicInteger, list));
        }
        return viewNode2;
    }

    private List<ViewNode> parseNodeList(ViewNode viewNode, NodeList nodeList, AtomicInteger atomicInteger, List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            ViewNode parseNode = parseNode(viewNode, nodeList.item(i2), atomicInteger, list);
            if (parseNode != null) {
                arrayList.add(parseNode);
            }
        }
        return arrayList;
    }

    public ViewNode parse() {
        if (this.in == null) {
            return null;
        }
        AtomicInteger atomicInteger = new AtomicInteger(900000);
        ArrayList arrayList = new ArrayList();
        Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.in);
        parse.getDocumentElement().normalize();
        return parseNode(null, parse.getDocumentElement(), atomicInteger, arrayList);
    }
}
