package com.tencent.mapsdk.internal;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class w9<Key, Value> {
    private int a;
    private w9<Key, Value>.b b;

    /* renamed from: c  reason: collision with root package name */
    private w9<Key, Value>.b f17434c;
    private HashMap<Key, w9<Key, Value>.b> d = new HashMap<>();

    /* JADX WARN: Field signature parse error: a
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: TKey at position 1 ('K'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.visitors.SignatureProcessor.parseFieldSignature(SignatureProcessor.java:128)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:36)
     */
    /* JADX WARN: Field signature parse error: b
    jadx.core.utils.exceptions.JadxRuntimeException: Can't parse type: TValue at position 1 ('V'), unexpected: T
    	at jadx.core.dex.nodes.parser.SignatureParser.consumeType(SignatureParser.java:169)
    	at jadx.core.dex.visitors.SignatureProcessor.parseFieldSignature(SignatureProcessor.java:128)
    	at jadx.core.dex.visitors.SignatureProcessor.visit(SignatureProcessor.java:36)
     */
    /* loaded from: classes9.dex */
    public class b {
        private Object a;
        private Object b;

        /* renamed from: c  reason: collision with root package name */
        private w9<Key, Value>.b f17435c;
        private w9<Key, Value>.b d;

        private b(Key key, Value value) {
            this.a = key;
            this.b = value;
        }
    }

    public w9(int i2) {
        this.a = i2;
    }

    private void a(w9<Key, Value>.b bVar) {
        if (bVar == null || this.f17434c == bVar) {
            return;
        }
        w9<Key, Value>.b bVar2 = this.b;
        if (bVar2 == bVar) {
            w9<Key, Value>.b bVar3 = ((b) bVar2).d;
            this.b = bVar3;
            ((b) bVar3).f17435c = null;
        } else {
            ((b) bVar).f17435c.d = ((b) bVar).d;
            ((b) bVar).d.f17435c = ((b) bVar).f17435c;
        }
        ((b) this.f17434c).d = bVar;
        ((b) bVar).f17435c = this.f17434c;
        this.f17434c = bVar;
        ((b) bVar).d = null;
    }

    private w9<Key, Value>.b b(Key key) {
        for (w9<Key, Value>.b bVar = this.b; bVar != null; bVar = ((b) bVar).d) {
            if (((b) bVar).a.equals(key)) {
                return bVar;
            }
        }
        return null;
    }

    private boolean d() {
        w9<Key, Value>.b bVar = this.b;
        w9<Key, Value>.b bVar2 = ((b) bVar).d;
        this.b = bVar2;
        ((b) bVar2).f17435c = null;
        Object obj = ((b) bVar).a;
        return (obj == null || this.d.remove(obj) == null) ? false : true;
    }

    public Value a(Key key) {
        w9<Key, Value>.b bVar = this.d.get(key);
        if (bVar == null) {
            return null;
        }
        a((b) bVar);
        return (Value) ((b) bVar).b;
    }

    public void a() {
        this.d.clear();
        this.f17434c = null;
        this.b = null;
    }

    public void a(Key key, Value value) {
        if (this.d.containsKey(key)) {
            w9<Key, Value>.b b2 = b(key);
            if (b2 != null) {
                a((b) b2);
                return;
            }
            return;
        }
        if (this.d.size() >= this.a) {
            d();
        }
        w9<Key, Value>.b bVar = new b(key, value);
        w9<Key, Value>.b bVar2 = this.f17434c;
        if (bVar2 == null) {
            this.f17434c = bVar;
            this.b = bVar;
        } else {
            ((b) bVar2).d = bVar;
            ((b) bVar).f17435c = this.f17434c;
            this.f17434c = bVar;
        }
        this.d.put(key, bVar);
    }

    public boolean b() {
        return this.d.isEmpty();
    }

    public boolean c() {
        return d();
    }

    public boolean c(Key key) {
        return this.d.remove(key) != null;
    }

    public int e() {
        return this.d.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        w9<Key, Value>.b bVar = this.b;
        if (((b) bVar).f17435c != null) {
            System.out.println("header\u7684pre\u4e0d\u4e3aNULL!");
        }
        sb.append("header: \n");
        while (bVar != null) {
            sb.append(((b) bVar).a + "->");
            bVar = ((b) bVar).d;
        }
        sb.append("\ntail: \n");
        w9<Key, Value>.b bVar2 = this.f17434c;
        if (((b) bVar2).d != null) {
            System.out.println("tail\u7684next\u4e0d\u4e3aNULL!");
        }
        while (bVar2 != null) {
            sb.append(((b) bVar2).a + "<-");
            bVar2 = ((b) bVar2).f17435c;
        }
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        return sb.toString();
    }
}
