package kotlin;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0007\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001fB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001eJ'\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0096\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0000H\u0096\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J%\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0016R\u0019\u0010\u0004\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0011R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0019\u0010\u0011R\u0019\u0010\u0005\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u001a\u0010\u0011R\u0016\u0010\u001b\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0017\u00a8\u0006 "}, d2 = {"Lkotlin/KotlinVersion;", "", "", "major", "minor", "patch", "versionOf", "(III)I", "", "toString", "()Ljava/lang/String;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "compareTo", "(Lkotlin/KotlinVersion;)I", "isAtLeast", "(II)Z", "(III)Z", "I", "getMinor", "getMajor", "getPatch", "version", "<init>", "(III)V", "(II)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class KotlinVersion implements Comparable<KotlinVersion> {
    public static final int MAX_COMPONENT_VALUE = 255;
    private final int major;
    private final int minor;
    private final int patch;
    private final int version;
    @JvmField
    @NotNull
    public static final KotlinVersion CURRENT = new KotlinVersion(1, 3, 72);

    public KotlinVersion(int i2, int i3, int i4) {
        this.major = i2;
        this.minor = i3;
        this.patch = i4;
        this.version = versionOf(i2, i3, i4);
    }

    private final int versionOf(int major, int minor, int patch) {
        if (major >= 0 && 255 >= major && minor >= 0 && 255 >= minor && patch >= 0 && 255 >= patch) {
            return (major << 16) + (minor << 8) + patch;
        }
        throw new IllegalArgumentException(("Version components are out of range: " + major + OrderISVUtil.MONEY_DECIMAL_CHAR + minor + OrderISVUtil.MONEY_DECIMAL_CHAR + patch).toString());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KotlinVersion)) {
            other = null;
        }
        KotlinVersion kotlinVersion = (KotlinVersion) other;
        return kotlinVersion != null && this.version == kotlinVersion.version;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    /* renamed from: hashCode  reason: from getter */
    public int getVersion() {
        return this.version;
    }

    public final boolean isAtLeast(int major, int minor) {
        int i2 = this.major;
        return i2 > major || (i2 == major && this.minor >= minor);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        sb.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
        sb.append(this.minor);
        sb.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
        sb.append(this.patch);
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull KotlinVersion other) {
        return this.version - other.version;
    }

    public final boolean isAtLeast(int major, int minor, int patch) {
        int i2;
        int i3 = this.major;
        return i3 > major || (i3 == major && ((i2 = this.minor) > minor || (i2 == minor && this.patch >= patch)));
    }

    public KotlinVersion(int i2, int i3) {
        this(i2, i3, 0);
    }
}
