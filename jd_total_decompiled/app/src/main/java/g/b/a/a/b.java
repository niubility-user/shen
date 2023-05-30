package g.b.a.a;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@Beta
@GwtCompatible
/* loaded from: classes12.dex */
public enum b {
    PRIVATE(':', ','),
    REGISTRY('!', '?');
    
    private final char innerNodeCode;
    private final char leafNodeCode;

    b(char c2, char c3) {
        this.innerNodeCode = c2;
        this.leafNodeCode = c3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a(char c2) {
        for (b bVar : values()) {
            if (bVar.b() == c2 || bVar.c() == c2) {
                return bVar;
            }
        }
        throw new IllegalArgumentException("No enum corresponding to given code: " + c2);
    }

    char b() {
        return this.innerNodeCode;
    }

    char c() {
        return this.leafNodeCode;
    }
}
