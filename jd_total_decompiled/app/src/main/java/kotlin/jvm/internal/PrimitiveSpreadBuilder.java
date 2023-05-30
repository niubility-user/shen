package kotlin.jvm.internal;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0006\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0014J\u0013\u0010\u0004\u001a\u00020\u0003*\u00028\u0000H$\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00028\u0000\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0003H\u0004\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0000H\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u00020\u00038\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\u0014R\u0016\u0010\n\u001a\u00020\u00038\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0011R$\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00158\u0002@\u0002X\u0082\u0004\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u0012\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "T", "", "getSize", "(Ljava/lang/Object;)I", "spreadArgument", "", "addSpread", "(Ljava/lang/Object;)V", ApkDownloadTable.FIELD_SIZE, "()I", "values", "result", "toArray", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "position", "I", "getPosition", "setPosition", "(I)V", "", "spreads", "[Ljava/lang/Object;", "spreads$annotations", "()V", "<init>", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public abstract class PrimitiveSpreadBuilder<T> {
    private int position;
    private final int size;
    private final T[] spreads;

    public PrimitiveSpreadBuilder(int i2) {
        this.size = i2;
        this.spreads = (T[]) new Object[i2];
    }

    private static /* synthetic */ void spreads$annotations() {
    }

    public final void addSpread(@NotNull T spreadArgument) {
        T[] tArr = this.spreads;
        int i2 = this.position;
        this.position = i2 + 1;
        tArr[i2] = spreadArgument;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getPosition() {
        return this.position;
    }

    protected abstract int getSize(@NotNull T t);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setPosition(int i2) {
        this.position = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int size() {
        int i2 = this.size - 1;
        int i3 = 0;
        if (i2 >= 0) {
            int i4 = 0;
            while (true) {
                T t = this.spreads[i4];
                i3 += t != null ? getSize(t) : 1;
                if (i4 == i2) {
                    break;
                }
                i4++;
            }
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final T toArray(@NotNull T values, @NotNull T result) {
        int i2;
        int i3 = this.size - 1;
        int i4 = 0;
        if (i3 >= 0) {
            int i5 = 0;
            int i6 = 0;
            i2 = 0;
            while (true) {
                T t = this.spreads[i5];
                if (t != null) {
                    if (i6 < i5) {
                        int i7 = i5 - i6;
                        System.arraycopy(values, i6, result, i2, i7);
                        i2 += i7;
                    }
                    int size = getSize(t);
                    System.arraycopy(t, 0, result, i2, size);
                    i2 += size;
                    i6 = i5 + 1;
                }
                if (i5 == i3) {
                    break;
                }
                i5++;
            }
            i4 = i6;
        } else {
            i2 = 0;
        }
        int i8 = this.size;
        if (i4 < i8) {
            System.arraycopy(values, i4, result, i2, i8 - i4);
        }
        return result;
    }
}
