package c.t.m.g;

import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class w {
    public static final Pattern a;
    public static final Pattern b;

    /* renamed from: c  reason: collision with root package name */
    public static final Pattern f724c;

    static {
        Pattern compile = Pattern.compile("[0-9a-zA-Z+-]*");
        a = compile;
        b = compile;
        f724c = Pattern.compile("[A-Z0-9]{12}");
    }

    public static String a(String str, Pattern pattern) {
        return (str != null && pattern.matcher(str).matches()) ? str : "";
    }
}
