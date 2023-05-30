package defpackage;

/* loaded from: classes.dex */
public /* synthetic */ class d {
    public static /* synthetic */ String a(CharSequence charSequence, CharSequence[] charSequenceArr) {
        if (charSequence != null) {
            StringBuilder sb = new StringBuilder();
            if (charSequenceArr.length > 0) {
                sb.append(charSequenceArr[0]);
                for (int i2 = 1; i2 < charSequenceArr.length; i2++) {
                    sb.append(charSequence);
                    sb.append(charSequenceArr[i2]);
                }
            }
            return sb.toString();
        }
        throw new NullPointerException("delimiter");
    }
}
