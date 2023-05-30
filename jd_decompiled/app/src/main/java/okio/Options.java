package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes11.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;
    final int[] trie;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    private static void buildTrieRecursive(long j2, Buffer buffer, int i2, List<ByteString> list, int i3, int i4, List<Integer> list2) {
        int i5;
        int i6;
        int i7;
        Buffer buffer2;
        int i8 = i3;
        if (i8 < i4) {
            for (int i9 = i8; i9 < i4; i9++) {
                if (list.get(i9).size() < i2) {
                    throw new AssertionError();
                }
            }
            ByteString byteString = list.get(i3);
            ByteString byteString2 = list.get(i4 - 1);
            int i10 = -1;
            if (i2 == byteString.size()) {
                i10 = list2.get(i8).intValue();
                i8++;
                byteString = list.get(i8);
            }
            int i11 = i8;
            if (byteString.getByte(i2) != byteString2.getByte(i2)) {
                int i12 = 1;
                for (int i13 = i11 + 1; i13 < i4; i13++) {
                    if (list.get(i13 - 1).getByte(i2) != list.get(i13).getByte(i2)) {
                        i12++;
                    }
                }
                long intCount = j2 + intCount(buffer) + 2 + (i12 * 2);
                buffer.writeInt(i12);
                buffer.writeInt(i10);
                for (int i14 = i11; i14 < i4; i14++) {
                    byte b = list.get(i14).getByte(i2);
                    if (i14 == i11 || b != list.get(i14 - 1).getByte(i2)) {
                        buffer.writeInt(b & 255);
                    }
                }
                Buffer buffer3 = new Buffer();
                int i15 = i11;
                while (i15 < i4) {
                    byte b2 = list.get(i15).getByte(i2);
                    int i16 = i15 + 1;
                    int i17 = i16;
                    while (true) {
                        if (i17 >= i4) {
                            i6 = i4;
                            break;
                        } else if (b2 != list.get(i17).getByte(i2)) {
                            i6 = i17;
                            break;
                        } else {
                            i17++;
                        }
                    }
                    if (i16 == i6 && i2 + 1 == list.get(i15).size()) {
                        buffer.writeInt(list2.get(i15).intValue());
                        i7 = i6;
                        buffer2 = buffer3;
                    } else {
                        buffer.writeInt((int) ((intCount(buffer3) + intCount) * (-1)));
                        i7 = i6;
                        buffer2 = buffer3;
                        buildTrieRecursive(intCount, buffer3, i2 + 1, list, i15, i6, list2);
                    }
                    buffer3 = buffer2;
                    i15 = i7;
                }
                Buffer buffer4 = buffer3;
                buffer.write(buffer4, buffer4.size());
                return;
            }
            int i18 = 0;
            int min = Math.min(byteString.size(), byteString2.size());
            for (int i19 = i2; i19 < min && byteString.getByte(i19) == byteString2.getByte(i19); i19++) {
                i18++;
            }
            long intCount2 = 1 + j2 + intCount(buffer) + 2 + i18;
            buffer.writeInt(-i18);
            buffer.writeInt(i10);
            int i20 = i2;
            while (true) {
                i5 = i2 + i18;
                if (i20 >= i5) {
                    break;
                }
                buffer.writeInt(byteString.getByte(i20) & 255);
                i20++;
            }
            if (i11 + 1 == i4) {
                if (i5 == list.get(i11).size()) {
                    buffer.writeInt(list2.get(i11).intValue());
                    return;
                }
                throw new AssertionError();
            }
            Buffer buffer5 = new Buffer();
            buffer.writeInt((int) ((intCount(buffer5) + intCount2) * (-1)));
            buildTrieRecursive(intCount2, buffer5, i5, list, i11, i4, list2);
            buffer.write(buffer5, buffer5.size());
            return;
        }
        throw new AssertionError();
    }

    private static int intCount(Buffer buffer) {
        return (int) (buffer.size() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x00bc, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static okio.Options of(okio.ByteString... r10) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.of(okio.ByteString[]):okio.Options");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.byteStrings.length;
    }

    @Override // java.util.AbstractList, java.util.List
    public ByteString get(int i2) {
        return this.byteStrings[i2];
    }
}
