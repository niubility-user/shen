package com.jd.dynamic.base;

import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.interfaces.IDarkChangeListener;
import com.jd.dynamic.base.interfaces.IRecycleListener;
import com.jd.dynamic.entity.AttrMethod;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class CachePool {
    public static final String K_TAG_CELL_DATA = "cellData";
    public static final String K_TAG_CURRENT_PAGE = "currentPage";
    public static final String K_TAG_NOTIFY_TYPE = "notifyType";
    public static final String K_TAG_PAGE_INDEX = "pageIndex";
    public static final String K_TAG_POSITION = "position";
    public static final String M_GET_TAG = "getTag";

    /* renamed from: j */
    private static final Map<String, AttrMethod> f1816j;

    /* renamed from: k */
    private static final Map<String, AttrMethod> f1817k;
    private DynamicTemplateEngine a;
    private Map<String, CommFunction> b = new HashMap();

    /* renamed from: c */
    private Map<String, String> f1818c = new ConcurrentHashMap();
    private final Map<String, Object> d = new ConcurrentHashMap();

    /* renamed from: e */
    private Map<Integer, View> f1819e = new HashMap();

    /* renamed from: f */
    private Map<String, String> f1820f = new HashMap();

    /* renamed from: g */
    private Map<String, Integer> f1821g = new HashMap();

    /* renamed from: h */
    private final ArrayList<IDarkChangeListener> f1822h = new ArrayList<>();

    /* renamed from: i */
    private final ArrayList<IRecycleListener> f1823i = new ArrayList<>();

    static {
        HashMap hashMap = new HashMap();
        f1816j = hashMap;
        HashMap hashMap2 = new HashMap();
        f1817k = hashMap2;
        AttrMethod attrMethod = new AttrMethod();
        attrMethod.methodName = "getText";
        hashMap.put("text", attrMethod);
        AttrMethod attrMethod2 = new AttrMethod();
        attrMethod2.methodName = M_GET_TAG;
        Class<?> cls = Integer.TYPE;
        attrMethod2.parameterTypes = new Class[]{cls};
        attrMethod2.args = new Object[]{Integer.valueOf(R.id.dynamic_item_position)};
        hashMap.put("position", attrMethod2);
        AttrMethod attrMethod3 = new AttrMethod();
        attrMethod3.methodName = M_GET_TAG;
        attrMethod3.parameterTypes = new Class[]{cls};
        attrMethod3.args = new Object[]{Integer.valueOf(R.id.dynamic_item_page_index)};
        hashMap.put(K_TAG_PAGE_INDEX, attrMethod3);
        AttrMethod attrMethod4 = new AttrMethod();
        attrMethod4.methodName = M_GET_TAG;
        attrMethod4.parameterTypes = new Class[]{cls};
        attrMethod4.args = new Object[]{Integer.valueOf(R.id.carousel_notify_type)};
        hashMap.put(K_TAG_NOTIFY_TYPE, attrMethod4);
        AttrMethod attrMethod5 = new AttrMethod();
        attrMethod5.methodName = M_GET_TAG;
        attrMethod5.parameterTypes = new Class[]{cls};
        attrMethod5.args = new Object[]{Integer.valueOf(R.id.carousel_current_page)};
        hashMap.put(K_TAG_CURRENT_PAGE, attrMethod5);
        AttrMethod attrMethod6 = new AttrMethod();
        attrMethod6.methodName = M_GET_TAG;
        attrMethod6.parameterTypes = new Class[]{cls};
        attrMethod6.args = new Object[]{Integer.valueOf(R.id.dynamic_item_data)};
        hashMap.put(K_TAG_CELL_DATA, attrMethod6);
        AttrMethod attrMethod7 = new AttrMethod();
        attrMethod7.methodName = M_GET_TAG;
        hashMap.put("tag", attrMethod7);
        AttrMethod attrMethod8 = new AttrMethod();
        attrMethod8.methodName = "getHint";
        putAttrMethod(DYConstants.DY_HINT, attrMethod8);
        AttrMethod attrMethod9 = new AttrMethod();
        attrMethod9.methodName = "getExpandOrFold";
        putAttrMethod("isExpand", attrMethod9);
        AttrMethod attrMethod10 = new AttrMethod();
        attrMethod10.methodName = "isRichExpand";
        putAttrMethod("richExpand", attrMethod10);
        AttrMethod attrMethod11 = new AttrMethod();
        attrMethod11.methodName = "getShowingCount";
        hashMap.put("currentShowChildCounts", attrMethod11);
        AttrMethod attrMethod12 = new AttrMethod();
        attrMethod12.methodName = "isChecked";
        hashMap.put("isSelected", attrMethod12);
        AttrMethod attrMethod13 = new AttrMethod();
        attrMethod13.methodName = "getCheckedRadioButtonId";
        hashMap.put("getCheckedRadioButtonId", attrMethod13);
        AttrMethod attrMethod14 = new AttrMethod();
        attrMethod14.methodName = "getValue";
        hashMap.put("getValue", attrMethod14);
        AttrMethod attrMethod15 = new AttrMethod();
        attrMethod15.methodName = "getY";
        hashMap.put("getY", attrMethod15);
        AttrMethod attrMethod16 = new AttrMethod();
        attrMethod16.methodName = "getX";
        hashMap.put("getX", attrMethod16);
        AttrMethod attrMethod17 = new AttrMethod();
        attrMethod17.methodName = "verticalScrolledDistance";
        hashMap.put("verticalScrolledDistance", attrMethod17);
        AttrMethod attrMethod18 = new AttrMethod();
        attrMethod18.methodName = "horizontalScrolledDistance";
        hashMap.put("horizontalScrolledDistance", attrMethod18);
        AttrMethod attrMethod19 = new AttrMethod();
        attrMethod19.methodName = "getLoadRefreshState";
        hashMap.put("refreshState", attrMethod19);
        AttrMethod attrMethod20 = new AttrMethod();
        attrMethod20.methodName = "getLoadMoreState";
        hashMap.put("loadMoreState", attrMethod20);
        AttrMethod attrMethod21 = new AttrMethod();
        attrMethod21.methodName = "getCurrentTextColor";
        hashMap.put(DYConstants.DY_TEXT_COLOR, attrMethod21);
        AttrMethod attrMethod22 = new AttrMethod();
        attrMethod22.methodName = "updateLoadRefreshState";
        attrMethod22.parameterTypes = new Class[]{cls};
        hashMap2.put("refreshState", attrMethod22);
        AttrMethod attrMethod23 = new AttrMethod();
        attrMethod23.methodName = "updateLoadMoreState";
        attrMethod23.parameterTypes = new Class[]{cls};
        hashMap2.put("loadMoreState", attrMethod23);
        AttrMethod attrMethod24 = new AttrMethod();
        attrMethod24.methodName = "scrollToPosition";
        attrMethod24.parameterTypes = new Class[]{cls};
        hashMap2.put("scrollToPosition", attrMethod24);
    }

    public CachePool(DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = dynamicTemplateEngine;
    }

    public static AttrMethod getMethod(String str) {
        return f1816j.get(str);
    }

    public static AttrMethod getSetterMethod(String str) {
        return f1817k.get(str);
    }

    public static void putAttrMethod(String str, AttrMethod attrMethod) {
        f1816j.put(str, attrMethod);
    }

    public void addCommFunction(String str, CommFunction commFunction) {
        this.b.put(str, commFunction);
    }

    public void addDarkChangeListener(IDarkChangeListener iDarkChangeListener) {
        if (this.f1822h.contains(iDarkChangeListener)) {
            return;
        }
        this.f1822h.add(iDarkChangeListener);
    }

    public void addRecycleListener(IRecycleListener iRecycleListener) {
        if (this.f1823i.contains(iRecycleListener)) {
            return;
        }
        this.f1823i.add(iRecycleListener);
    }

    public void cleanCacheData() {
        this.f1818c.clear();
    }

    public void cleanFunctions() {
        this.b.clear();
    }

    public void cleanImageResourceCache() {
        this.f1821g.clear();
    }

    public void cleanParams() {
        this.f1820f.clear();
    }

    public void cleanPool() {
        cleanParams();
        cleanFunctions();
        cleanCacheData();
        cleanImageResourceCache();
        this.f1819e.clear();
        this.f1822h.clear();
        this.f1823i.clear();
    }

    public CommFunction getCommFunctionByType(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.b.get(str);
    }

    public ArrayList<IDarkChangeListener> getDarkChangeListeners() {
        return this.f1822h;
    }

    public Object getData(String str) {
        return TextUtils.equals(str, "dynamic_init_data") ? this.a.currentData : this.f1818c.get(str);
    }

    public Map<String, String> getDataCache() {
        return this.f1818c;
    }

    public Object getDataObj(String str) {
        DynamicTemplateEngine dynamicTemplateEngine;
        return (!"dynamic_init_data".equals(str) || (dynamicTemplateEngine = this.a) == null) ? this.d.get(str) : dynamicTemplateEngine.currentData;
    }

    public int getImageResourceCache(String str) {
        return this.f1821g.get(str).intValue();
    }

    public String getParam(String str) {
        return this.f1820f.get(str);
    }

    public ArrayList<IRecycleListener> getRecycleListeners() {
        return this.f1823i;
    }

    public View getViewFromCacheById(int i2) {
        if (i2 > 0) {
            return this.f1819e.get(Integer.valueOf(i2));
        }
        return null;
    }

    public boolean imageResourceCacheContainsKey(String str) {
        return this.f1821g.containsKey(str);
    }

    public void putData(String str, String str2) {
        DynamicTemplateEngine dynamicTemplateEngine;
        if (TextUtils.equals(str, "dynamic_init_data") && (dynamicTemplateEngine = this.a) != null) {
            try {
                dynamicTemplateEngine.currentData = new JSONObject(str2);
                return;
            } catch (Exception unused) {
            }
        }
        this.f1818c.put(str, str2);
    }

    public void putImageResourceCache(String str, int i2) {
        this.f1821g.put(str, Integer.valueOf(i2));
    }

    public void putObjData(String str, Object obj) {
        if (!"dynamic_init_data".equals(str)) {
            this.d.put(str, obj);
            return;
        }
        DynamicTemplateEngine dynamicTemplateEngine = this.a;
        if (dynamicTemplateEngine != null) {
            if (obj instanceof JSONObject) {
                dynamicTemplateEngine.currentData = (JSONObject) obj;
            } else if (obj instanceof String) {
                try {
                    dynamicTemplateEngine.currentData = new JSONObject((String) obj);
                } catch (Exception unused) {
                }
            }
        }
    }

    public void putParam(String str, String str2) {
        this.f1820f.put(str, str2);
    }

    public void putViewIntoCache(int i2, View view) {
        if (view instanceof IDarkChangeListener) {
            addDarkChangeListener((IDarkChangeListener) view);
        }
        if (view instanceof IRecycleListener) {
            addRecycleListener((IRecycleListener) view);
        }
        if (i2 <= 0 || view == null) {
            return;
        }
        this.f1819e.put(Integer.valueOf(i2), view);
    }

    public void removeCommFunctionByType(String str) {
        this.b.remove(str);
    }

    public void removeData(String str) {
        this.f1818c.remove(str);
    }
}
