package m.a.c;

/* loaded from: classes11.dex */
public class g1 {
    private String a;
    private String b;

    public g1(String str) {
        this.a = null;
        this.b = null;
        int indexOf = str.indexOf(46);
        if (indexOf == -1) {
            this.a = str;
            return;
        }
        this.a = str.substring(0, indexOf);
        this.b = str.substring(indexOf + 1);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
