package com.jingdong.jdpush_new.i;

import android.content.Context;
import com.jingdong.jdpush_new.j.g;

/* loaded from: classes12.dex */
public class d implements com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> {
    private Context a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int f12851c;

    public d(Context context, String str, int i2) {
        this.a = context;
        this.b = str;
        this.f12851c = i2;
    }

    @Override // com.jingdong.jdpush_new.j.d
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void accept(com.jingdong.jdpush_new.g.b bVar) {
        g.c("<- " + bVar);
        if (com.jingdong.jdpush_new.d.b.a(bVar.getCommand())) {
            short command = bVar.getCommand();
            if (command == 2105) {
                com.jingdong.jdpush_new.e.c.f().c(this.a, this.f12851c, bVar.getMsgBody(), this.b);
            } else if (command == 2109) {
                com.jingdong.jdpush_new.e.c.f().a(this.a, this.f12851c, bVar.getMsgBody(), this.b);
            } else if (command == 2111) {
                com.jingdong.jdpush_new.e.c.f().d(this.a, this.f12851c, bVar.getMsgBody(), this.b);
            } else if (command == 2113 || command == 2123) {
                com.jingdong.jdpush_new.e.c.f().b(this.a, this.f12851c, bVar.getMsgBody(), this.b);
            } else if (command != 2125) {
            } else {
                com.jingdong.jdpush_new.e.c.f().e(bVar.getMsgBody());
            }
        }
    }
}
