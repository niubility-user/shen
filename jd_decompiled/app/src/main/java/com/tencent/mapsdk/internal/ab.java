package com.tencent.mapsdk.internal;

import android.graphics.PointF;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/* loaded from: classes9.dex */
public class ab {
    public static final Random a = new Random();

    /* loaded from: classes9.dex */
    public static class a implements Comparator<Integer> {
        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(Integer num, Integer num2) {
            return num.intValue() - num2.intValue();
        }
    }

    private static double a(PointF pointF, PointF pointF2, PointF pointF3) {
        if (pointF.equals(pointF2) || pointF3.equals(pointF) || pointF3.equals(pointF2)) {
            return 0.0d;
        }
        float f2 = pointF.x;
        float f3 = pointF2.y;
        float f4 = pointF2.x;
        float f5 = pointF3.y;
        float f6 = pointF3.x;
        float f7 = pointF.y;
        double d = (((((f2 * f3) + (f4 * f5)) + (f6 * f7)) - (f4 * f7)) - (f6 * f3)) - (f2 * f5);
        Double.isNaN(d);
        return (Math.abs(d * 0.5d) * 2.0d) / Math.sqrt(Math.pow(pointF.x - pointF2.x, 2.0d) + Math.pow(pointF.y - pointF2.y, 2.0d));
    }

    private static <E> int a(ArrayList<E> arrayList, int i2, int i3, Comparator<? super E> comparator) {
        int nextInt = a.nextInt((i3 - i2) + 1) + i2;
        E e2 = arrayList.get(nextInt);
        a(arrayList, nextInt, i3);
        int i4 = i2;
        while (i2 < i3) {
            if (comparator.compare(arrayList.get(i2), e2) <= 0) {
                a(arrayList, i4, i2);
                i4++;
            }
            i2++;
        }
        a(arrayList, i4, i3);
        return i4;
    }

    public static List<PointF> a(List<PointF> list, double d) {
        int size = list.size();
        if (list.isEmpty() || size < 3) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(0);
        int size2 = list.size() - 1;
        while (list.get(0).equals(list.get(size2))) {
            size2--;
            if (size2 <= 0) {
                return list;
            }
        }
        arrayList.add(Integer.valueOf(size2));
        a(list, 0, size2, d, arrayList);
        a(arrayList, new a());
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(list.get(((Integer) arrayList.get(i2)).intValue()));
        }
        return arrayList2;
    }

    private static <E> void a(ArrayList<E> arrayList, int i2, int i3) {
        E e2 = arrayList.get(i2);
        arrayList.set(i2, arrayList.get(i3));
        arrayList.set(i3, e2);
    }

    private static <E> void a(ArrayList<E> arrayList, Comparator<? super E> comparator) {
        b(arrayList, 0, arrayList.size() - 1, comparator);
    }

    private static void a(List<PointF> list, int i2, int i3, double d, ArrayList<Integer> arrayList) {
        double d2 = 0.0d;
        int i4 = 0;
        for (int i5 = i2; i5 < i3; i5++) {
            double a2 = a(list.get(i2), list.get(i3), list.get(i5));
            if (a2 > d2) {
                i4 = i5;
                d2 = a2;
            }
        }
        if (d2 <= d || i4 == 0) {
            return;
        }
        arrayList.add(Integer.valueOf(i4));
        a(list, i2, i4, d, arrayList);
        a(list, i4, i3, d, arrayList);
    }

    private static <E> void b(ArrayList<E> arrayList, int i2, int i3, Comparator<? super E> comparator) {
        if (i3 > i2) {
            int a2 = a(arrayList, i2, i3, comparator);
            b(arrayList, i2, a2 - 1, comparator);
            b(arrayList, a2 + 1, i3, comparator);
        }
    }
}
