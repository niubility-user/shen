package com.jd.dynamic.entity;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.ObjectsCompat;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.lib.expv2.d;
import com.jd.dynamic.lib.utils.m;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import rx.Observable;
import rx.functions.Action1;

@Keep
/* loaded from: classes13.dex */
public class ViewNode implements Serializable, Cloneable {
    private static final long serialVersionUID = 101;
    private HashMap<String, String> attributes;
    private List<ViewNode> childs;
    private HashMap<String, com.jd.dynamic.b.h.a> elParserMaps;
    private d parserEntry;
    private int test;
    public String textContent;
    public int viewId;
    private String viewName;
    private HashMap<String, String> visibilityAttributes;
    private String visibilityExp;
    public String zipVersion;
    public boolean parseSuccess = true;
    public boolean unNeedInitBind = false;
    public boolean isSavedOnLoad = false;
    public boolean isSavedOnBind = false;
    public boolean isExecOnLoad = false;
    private HashMap<String, String> elAttributes = new HashMap<>();
    public transient ConcurrentHashMap<ViewNode, HashMap<String, String>> unBindMaps = new ConcurrentHashMap<>();
    public SparseArrayCompat<HashMap<String, String>> elAttrMapping = new SparseArrayCompat<>(16);
    private transient com.jd.dynamic.b.h.a parser = null;
    private int parentVisibilityFlag = 0;
    private int currentVisibilityFlag = 0;
    private int preVisibilityFlag = 0;
    private String uuid = UUID.randomUUID().toString();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(Throwable th) {
    }

    private void ifUpdateIt(int i2) {
        int i3 = this.currentVisibilityFlag;
        if (i3 == i2) {
            return;
        }
        this.preVisibilityFlag = i3;
        this.currentVisibilityFlag = i2;
        updateChildFlag(this, i2);
    }

    private void updateChildFlag(ViewNode viewNode, int i2) {
        List<ViewNode> childs = viewNode.getChilds();
        if (childs == null || childs.isEmpty()) {
            return;
        }
        for (ViewNode viewNode2 : childs) {
            viewNode2.updateParentFlag(i2);
            updateChildFlag(viewNode2, i2);
        }
    }

    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public ViewNode m18clone() {
        final ArrayList arrayList = new ArrayList();
        Observable.from(getChilds()).forEach(new Action1() { // from class: com.jd.dynamic.entity.a
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                arrayList.add(((ViewNode) obj).m18clone());
            }
        }, new Action1() { // from class: com.jd.dynamic.entity.b
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewNode.b((Throwable) obj);
            }
        });
        ViewNode viewNode = new ViewNode();
        viewNode.setTest(this.test);
        viewNode.setParser(this.parser);
        viewNode.unNeedInitBind = this.unNeedInitBind;
        viewNode.setChilds(arrayList);
        viewNode.parseSuccess = this.parseSuccess;
        viewNode.setAttributes((HashMap) getAttributes().clone());
        viewNode.setViewName(this.viewName);
        viewNode.isSavedOnBind = false;
        viewNode.isExecOnLoad = false;
        viewNode.isSavedOnLoad = false;
        viewNode.viewId = this.viewId;
        if (!this.elAttrMapping.isEmpty()) {
            SparseArrayCompat<HashMap<String, String>> sparseArrayCompat = new SparseArrayCompat<>();
            for (int i2 = 0; i2 < this.elAttrMapping.size(); i2++) {
                sparseArrayCompat.put(this.elAttrMapping.keyAt(i2), new HashMap<>(this.elAttrMapping.valueAt(i2)));
            }
            viewNode.elAttrMapping = sparseArrayCompat;
        }
        if (m.J(this.unBindMaps)) {
            for (ViewNode viewNode2 : this.unBindMaps.keySet()) {
                viewNode2.isSavedOnBind = false;
                viewNode2.isExecOnLoad = false;
                viewNode2.isSavedOnLoad = false;
            }
            viewNode.unBindMaps = new ConcurrentHashMap<>(this.unBindMaps);
        }
        return viewNode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ViewNode viewNode = (ViewNode) obj;
        return this.test == viewNode.test && this.uuid.equals(viewNode.uuid) && this.viewId == viewNode.viewId && this.viewName.equals(viewNode.viewName) && getAttributes().equals(viewNode.getAttributes()) && this.unBindMaps.equals(viewNode.unBindMaps) && getChilds().equals(viewNode.childs);
    }

    public HashMap<String, String> getAttributes() {
        HashMap<String, String> hashMap = this.attributes;
        if (hashMap == null) {
            HashMap<String, String> hashMap2 = new HashMap<>();
            this.attributes = hashMap2;
            return hashMap2;
        }
        return hashMap;
    }

    public ViewNode getChildByName(String str) {
        List<ViewNode> list = this.childs;
        if (list == null) {
            return null;
        }
        for (ViewNode viewNode : list) {
            if (TextUtils.equals(viewNode.getViewName(), str)) {
                return viewNode;
            }
        }
        return null;
    }

    public List<ViewNode> getChilds() {
        List<ViewNode> list = this.childs;
        return list == null ? new ArrayList() : list;
    }

    public HashMap<String, String> getELAttributes() {
        return this.elAttributes;
    }

    public HashMap<String, String> getNoELAttributes() {
        HashMap<String, String> hashMap = new HashMap<>();
        HashMap<String, String> hashMap2 = this.attributes;
        if (hashMap2 != null) {
            for (Map.Entry<String, String> entry : hashMap2.entrySet()) {
                if (!DynamicUtils.isElOrKnownSymbol(entry.getValue())) {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return hashMap;
    }

    public com.jd.dynamic.b.h.a getParser() {
        return this.parser;
    }

    public com.jd.dynamic.b.h.a getParser(String str) {
        HashMap<String, com.jd.dynamic.b.h.a> hashMap;
        if (TextUtils.isEmpty(str) || (hashMap = this.elParserMaps) == null) {
            return null;
        }
        return hashMap.get(str);
    }

    public d getParserEntry() {
        return this.parserEntry;
    }

    public int getTest() {
        return this.test;
    }

    public String getViewName() {
        return this.viewName;
    }

    public HashMap<String, String> getVisibilityAttributes() {
        return this.visibilityAttributes;
    }

    public String getVisibilityExp() {
        return 8 == this.parentVisibilityFlag ? "" : this.visibilityExp;
    }

    public int getVisibilityFlag() {
        int i2;
        int i3 = this.currentVisibilityFlag;
        int i4 = 8;
        if (8 != i3 && 8 != (i2 = this.parentVisibilityFlag)) {
            i4 = 4;
            if (4 != i3 && 4 != i2) {
                return 0;
            }
        }
        return i4;
    }

    @RequiresApi(api = 19)
    public int hashCode() {
        return ObjectsCompat.hash(this.uuid, Integer.valueOf(this.test), this.viewName, this.attributes, this.unBindMaps, this.childs, this.parser);
    }

    public void putVisibilityAttributes(String str, String str2) {
        if (this.visibilityAttributes == null) {
            this.visibilityAttributes = new HashMap<>();
        }
        this.visibilityAttributes.put(str, str2);
    }

    public void setAttributes(HashMap<String, String> hashMap) {
        this.attributes = hashMap;
    }

    public void setChilds(List<ViewNode> list) {
        this.childs = list;
        int i2 = this.preVisibilityFlag;
        int i3 = this.currentVisibilityFlag;
        if (i2 != i3) {
            updateChildFlag(this, i3);
        }
    }

    public void setParser(com.jd.dynamic.b.h.a aVar) {
        this.parser = aVar;
    }

    public void setParser(String str, com.jd.dynamic.b.h.a aVar) {
        if (TextUtils.isEmpty(str) || aVar == null) {
            return;
        }
        if (this.elParserMaps == null) {
            this.elParserMaps = new HashMap<>();
        }
        this.elParserMaps.put(str, aVar);
    }

    public void setParserEntry(d dVar) {
        this.parserEntry = dVar;
    }

    public void setTest(int i2) {
        this.test = i2;
    }

    public void setViewName(String str) {
        this.viewName = str;
    }

    public boolean setVisibilityExp(String str, String str2, boolean z) {
        return true;
    }

    public String toString() {
        return "ViewNode{viewName='" + this.viewName + "', attributes=" + this.attributes + ", childs=" + getChilds() + '}';
    }

    public void updateParentFlag(int i2) {
        this.parentVisibilityFlag = i2;
    }

    public void updateVisibility(String str) {
    }
}
