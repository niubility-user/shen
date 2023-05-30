package com.jingdong.app.mall.home.deploy.view.base;

import android.content.Context;
import android.view.View;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.o.a.f;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.LinkedList;

/* loaded from: classes4.dex */
public abstract class b<V extends View, M extends BaseModel> {
    private LinkedList<b> a = new LinkedList<>();
    protected Context b;

    /* renamed from: c  reason: collision with root package name */
    protected M f8888c;
    protected V d;

    public b(Context context) {
        this.b = context;
        i(false);
    }

    private void b() {
        m.J(this.d);
        this.a.clear();
    }

    private void e(boolean z) throws Exception {
        Class cls;
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            f.n(genericSuperclass);
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            Type type = actualTypeArguments[0];
            Type type2 = actualTypeArguments[1];
            Class cls2 = null;
            if (!z) {
                if (type instanceof Class) {
                    f.n(type);
                    cls = (Class) type;
                } else if (type instanceof TypeVariable) {
                    Type type3 = ((TypeVariable) type).getBounds()[0];
                    f.n(type3);
                    cls = (Class) type3;
                } else {
                    cls = null;
                }
                this.d = (V) cls.getConstructor(Context.class).newInstance(this.b);
            }
            if (type2 instanceof Class) {
                f.n(type2);
                cls2 = (Class) type2;
            } else if (type2 instanceof TypeVariable) {
                Type type4 = ((TypeVariable) type2).getBounds()[0];
                f.n(type4);
                cls2 = (Class) type4;
            }
            this.f8888c = (M) cls2.newInstance();
        }
    }

    public void a(View view, com.jingdong.app.mall.home.p.b.e.b bVar) {
        M m2 = this.f8888c;
        if (m2 == null) {
            return;
        }
        m2.a(this, view, bVar);
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar2 = this.a.get(i2);
            if (bVar2 != null) {
                bVar2.a(this.d, bVar);
            }
        }
    }

    public M c() {
        return this.f8888c;
    }

    public V d() {
        return this.d;
    }

    public void f(JDJSONArray jDJSONArray) {
        int size = jDJSONArray.size();
        b();
        for (int i2 = 0; i2 < size; i2++) {
            g(jDJSONArray.getJSONObject(i2));
        }
    }

    public void g(JDJSONObject jDJSONObject) {
        com.jingdong.app.mall.home.p.b.e.a aVar = new com.jingdong.app.mall.home.p.b.e.a(jDJSONObject);
        b crateParser = aVar.a().crateParser(this.b);
        crateParser.h(aVar);
        this.a.add(crateParser);
    }

    public void h(com.jingdong.app.mall.home.p.b.e.a aVar) {
        M m2 = this.f8888c;
        if (m2 == null || this.d == null || aVar == null) {
            return;
        }
        m2.D(this, aVar);
    }

    public void i(boolean z) {
        try {
            e(z);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void j(com.jingdong.app.mall.home.p.b.e.a aVar, M m2) {
        if (m2 != null) {
            this.f8888c = m2;
        } else {
            i(true);
        }
        if (aVar != null) {
            aVar.m(this.f8888c);
        }
    }
}
