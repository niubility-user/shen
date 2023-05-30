package com.jingdong.app.mall.home.n.g.u;

import androidx.annotation.IntRange;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.floor.decoration.CaDividerDecoration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public abstract class d extends b {
    protected com.jingdong.app.mall.home.n.b[] s;
    protected CaDividerDecoration t;
    protected List<e> u;
    protected boolean v;
    protected int w;
    protected boolean x;
    protected boolean y;

    public d(JDJSONObject jDJSONObject, com.jingdong.app.mall.home.n.a aVar, @NotNull com.jingdong.app.mall.home.n.b[] bVarArr) {
        super(jDJSONObject, aVar);
        this.u = new ArrayList();
        this.x = true;
        this.y = true;
        this.s = bVarArr;
    }

    public CaDividerDecoration G() {
        return this.t;
    }

    public int H() {
        int size = this.u.size();
        return this.v ? size - 1 : size;
    }

    public List<e> I() {
        return this.u;
    }

    public void J(String str, com.jingdong.app.mall.home.n.g.v.c cVar, String str2) {
        if (cVar == null) {
            return;
        }
        cVar.s(str, str2);
        Iterator<e> it = this.u.iterator();
        while (it.hasNext()) {
            cVar.a(it.next().k());
        }
    }

    public void K(@IntRange(from = 1, to = 4) int i2) {
        L(i2, true);
    }

    protected void L(@IntRange(from = 1, to = 4) int i2, boolean z) {
        com.jingdong.app.mall.home.n.b[] bVarArr = this.s;
        if (bVarArr.length <= 0 || this.q == null) {
            return;
        }
        com.jingdong.app.mall.home.n.b bVar = bVarArr[0];
        int spanSize = bVar.getSpanSize();
        int size = this.q.size();
        if (size < 1 || spanSize < 2) {
            return;
        }
        if (size >= spanSize || !z) {
            int i3 = ((z ? 0 : spanSize - 1) + size) / spanSize;
            this.w = i3;
            if (i2 > 0) {
                this.w = Math.min(i3, i2);
            }
            for (int i4 = 0; i4 < this.w; i4++) {
                for (int i5 = 0; i5 < spanSize; i5++) {
                    int i6 = (i4 * spanSize) + i5;
                    if (size <= i6) {
                        break;
                    }
                    e typeModel = bVar.getTypeModel(this.q.getJSONObject(i6), this, i6);
                    if (typeModel.m()) {
                        this.u.add(typeModel);
                    }
                }
            }
        }
    }

    public void M(boolean z, int... iArr) {
        N(z, 0, 0, iArr);
    }

    public void N(boolean z, int i2, int i3, int... iArr) {
        JDJSONArray jDJSONArray;
        if (this.s.length <= 0 || (jDJSONArray = this.q) == null) {
            return;
        }
        int size = jDJSONArray.size();
        boolean z2 = false;
        com.jingdong.app.mall.home.n.b bVar = this.s[0];
        if (size <= 0) {
            return;
        }
        JDJSONObject jDJSONObject = null;
        int i4 = (iArr == null || iArr.length != 2) ? Integer.MAX_VALUE : iArr[1];
        for (int i5 = 0; i5 < size && i5 < i4; i5++) {
            JDJSONObject jSONObject = this.q.getJSONObject(i5);
            if (!"1".equals(jSONObject.optString("type"))) {
                e typeModel = bVar.getTypeModel(jSONObject, this, i5);
                typeModel.r(i5);
                if (typeModel.m()) {
                    this.u.add(typeModel);
                }
            } else if (z) {
                jDJSONObject = jSONObject;
            }
        }
        if (jDJSONObject != null && (iArr == null || iArr.length <= 0 || this.u.size() > iArr[0])) {
            z2 = true;
        }
        this.v = z2;
        if (z2) {
            jDJSONObject.put("height", (Object) Integer.valueOf(i3));
            jDJSONObject.put("width", (Object) Integer.valueOf(i2));
            this.u.add(com.jingdong.app.mall.home.n.b.S_JUMP_MORE.getTypeModel(jDJSONObject, this, size));
        }
    }

    public void O(String str, com.jingdong.app.mall.home.n.g.v.c cVar, String str2) {
        if (cVar == null) {
            return;
        }
        cVar.t(str, str2);
        Iterator<e> it = this.u.iterator();
        while (it.hasNext()) {
            cVar.a(it.next().k());
        }
    }

    public boolean P() {
        return this.x;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.c
    public boolean n() {
        return this.u.size() > 0 && (this.s.length == 1 || this.u.size() == this.s.length);
    }
}
