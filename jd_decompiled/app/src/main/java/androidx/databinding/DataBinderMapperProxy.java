package androidx.databinding;

import android.view.View;
import androidx.annotation.NonNull;
import com.jingdong.aura.a.b.h;
import com.jingdong.aura.core.util.l.b;
import com.jingdong.aura.core.util.l.c;
import com.jingdong.common.utils.LangUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import l.b.a.a;
import l.b.a.e;

/* loaded from: classes.dex */
public class DataBinderMapperProxy extends DataBinderMapper implements e {
    public static final String TAG = "DataBinderMapperProxy";
    private static final b log = c.a(TAG);
    private Object[] mCache;
    private Set<Class<? extends DataBinderMapper>> mExistingMappers = new HashSet();
    private final LinkedList<Object> mMappers = new LinkedList<>();

    public DataBinderMapperProxy(@NonNull Object obj) {
        addMapper(obj);
    }

    private Object[] getCache() {
        Object[] objArr;
        synchronized (this.mMappers) {
            if (this.mCache == null) {
                LinkedList<Object> linkedList = this.mMappers;
                this.mCache = linkedList.toArray(new Object[linkedList.size()]);
            }
            objArr = this.mCache;
        }
        return objArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addMapper(Object obj) {
        if (!(obj instanceof DataBinderMapper)) {
            log.a("addMapper: not DataBinderMapper");
            return;
        }
        DataBinderMapper dataBinderMapper = (DataBinderMapper) obj;
        Class<?> cls = dataBinderMapper.getClass();
        if (this.mExistingMappers.add(cls)) {
            b bVar = log;
            bVar.a("addMapper: " + cls.getName());
            this.mMappers.add(dataBinderMapper);
            this.mCache = null;
            List<DataBinderMapper> collectDependencies = dataBinderMapper.collectDependencies();
            bVar.a("addMapper: dependencies:" + collectDependencies.size() + LangUtils.SINGLE_SPACE + collectDependencies);
            Iterator<DataBinderMapper> it = collectDependencies.iterator();
            while (it.hasNext()) {
                addMapper(it.next());
            }
        }
    }

    @Override // l.b.a.e
    public void bundleChanged(a aVar) {
        b bVar = log;
        bVar.a("bundleChanged: bundleEvent:" + aVar.getType());
        if (aVar.getType() == 2) {
            try {
                h hVar = (h) aVar.getBundle();
                String str = hVar.b() + ".feature.DataBinderMapperImpl";
                bVar.a("bundleChanged: clsName:" + str);
                addMapper(Class.forName(str, true, hVar.g()).newInstance());
            } catch (Exception unused) {
                log.c("bundleChanged: not found DataBinderMapperImpl");
            }
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public /* bridge */ /* synthetic */ List collectDependencies() {
        return super.collectDependencies();
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i2) {
        String str;
        for (Object obj : getCache()) {
            try {
                str = (String) obj.getClass().getMethod("convertBrIdToString", Integer.TYPE).invoke(obj, Integer.valueOf(i2));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (str != null) {
                return str;
            }
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i2) {
        b bVar;
        ViewDataBinding viewDataBinding;
        for (Object obj : getCache()) {
            try {
                bVar = log;
                bVar.a("getDataBinder from view: mapper\uff1a" + obj.getClass().getName());
                viewDataBinding = (ViewDataBinding) obj.getClass().getMethod("getDataBinder", DataBindingComponent.class, View.class, Integer.TYPE).invoke(obj, dataBindingComponent, view, Integer.valueOf(i2));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (viewDataBinding != null) {
                return viewDataBinding;
            }
            bVar.c("getDataBinder from view: viewDataBinding not found");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        int intValue;
        for (Object obj : getCache()) {
            try {
                intValue = ((Integer) obj.getClass().getMethod("getLayoutId", String.class).invoke(obj, str)).intValue();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (intValue != 0) {
                return intValue;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i2) {
        b bVar;
        ViewDataBinding viewDataBinding;
        for (Object obj : getCache()) {
            try {
                bVar = log;
                bVar.a("getDataBinder from views: mapper\uff1a" + obj.getClass().getName());
                viewDataBinding = (ViewDataBinding) obj.getClass().getMethod("getDataBinder", DataBindingComponent.class, View[].class, Integer.TYPE).invoke(obj, dataBindingComponent, viewArr, Integer.valueOf(i2));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (viewDataBinding != null) {
                return viewDataBinding;
            }
            bVar.c("getDataBinder from views: viewDataBinding not found");
        }
        return null;
    }
}
