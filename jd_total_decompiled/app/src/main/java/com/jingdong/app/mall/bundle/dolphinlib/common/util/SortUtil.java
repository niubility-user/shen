package com.jingdong.app.mall.bundle.dolphinlib.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes19.dex */
public final class SortUtil {

    /* loaded from: classes19.dex */
    public static final class SortByInt<T> implements Comparable<Integer> {
        public T data;
        public int sortKey;

        @Override // java.lang.Comparable
        public int compareTo(Integer num) {
            if (num == null) {
                return 0;
            }
            return num.intValue() - this.sortKey;
        }
    }

    public static final <T> List<T> sort(List<SortByInt<T>> list) {
        if (list == null && list.isEmpty()) {
            return Collections.emptyList();
        }
        Collections.sort(list, new Comparator<SortByInt<T>>() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.util.SortUtil.1
            @Override // java.util.Comparator
            public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                return compare((SortByInt) ((SortByInt) obj), (SortByInt) ((SortByInt) obj2));
            }

            public int compare(SortByInt<T> sortByInt, SortByInt<T> sortByInt2) {
                if (sortByInt == null || sortByInt2 == null) {
                    return 0;
                }
                return sortByInt.sortKey - sortByInt2.sortKey;
            }
        });
        ArrayList arrayList = new ArrayList();
        Iterator<SortByInt<T>> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().data);
        }
        return arrayList;
    }
}
