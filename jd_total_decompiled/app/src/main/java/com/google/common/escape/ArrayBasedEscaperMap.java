package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

@Beta
@GwtCompatible
/* loaded from: classes12.dex */
public final class ArrayBasedEscaperMap {
    private static final char[][] EMPTY_REPLACEMENT_ARRAY = (char[][]) Array.newInstance(char.class, 0, 0);
    private final char[][] replacementArray;

    private ArrayBasedEscaperMap(char[][] cArr) {
        this.replacementArray = cArr;
    }

    public static ArrayBasedEscaperMap create(Map<Character, String> map) {
        return new ArrayBasedEscaperMap(createReplacementArray(map));
    }

    @VisibleForTesting
    static char[][] createReplacementArray(Map<Character, String> map) {
        Preconditions.checkNotNull(map);
        if (map.isEmpty()) {
            return EMPTY_REPLACEMENT_ARRAY;
        }
        char[][] cArr = new char[((Character) Collections.max(map.keySet())).charValue() + 1];
        Iterator<Character> it = map.keySet().iterator();
        while (it.hasNext()) {
            char charValue = it.next().charValue();
            cArr[charValue] = map.get(Character.valueOf(charValue)).toCharArray();
        }
        return cArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public char[][] getReplacementArray() {
        return this.replacementArray;
    }
}
