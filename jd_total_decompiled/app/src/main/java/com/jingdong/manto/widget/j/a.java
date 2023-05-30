package com.jingdong.manto.widget.j;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class a implements c {
    private Context a;
    private List<d> b = new ArrayList();

    public a(Context context) {
        this.a = context;
    }

    @Override // com.jingdong.manto.widget.j.c
    public d a(int i2, int i3, int i4) {
        b bVar = new b(this.a, i2);
        bVar.b(i3);
        bVar.a(i4);
        this.b.add(bVar);
        return bVar;
    }

    @Override // com.jingdong.manto.widget.j.c
    public d a(int i2, int i3, int i4, boolean z, int i5) {
        d dVar = null;
        for (d dVar2 : this.b) {
            if (dVar2 != null && dVar2.b() == i2) {
                dVar2.b(i3);
                dVar2.a(i4);
                dVar2.b(z);
                dVar2.c(i5);
                dVar = dVar2;
            }
        }
        return dVar;
    }

    @Override // com.jingdong.manto.widget.j.c
    public d a(int i2, CharSequence charSequence, Drawable drawable) {
        b bVar = new b(this.a, i2);
        bVar.a(charSequence);
        bVar.a(drawable);
        this.b.add(bVar);
        return bVar;
    }

    @Override // com.jingdong.manto.widget.j.c
    public void clear() {
        this.b.clear();
    }

    @Override // com.jingdong.manto.widget.j.c
    public d getItem(int i2) {
        if (i2 < 0 || i2 >= this.b.size()) {
            return null;
        }
        return this.b.get(i2);
    }

    @Override // com.jingdong.manto.widget.j.c
    public int size() {
        return this.b.size();
    }
}
