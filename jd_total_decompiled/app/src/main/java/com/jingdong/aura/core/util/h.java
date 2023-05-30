package com.jingdong.aura.core.util;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public class h {
    public static String[] a = new String[0];

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean b(String str) {
        return str != null && str.length() > 0;
    }

    public static String[] c(String str, String str2) {
        return a(str, str2, -1, false);
    }

    public static boolean d(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    public static String e(String str, String str2) {
        int indexOf;
        return a(str) ? str : (str2 == null || (indexOf = str.indexOf(str2)) == -1) ? "" : str.substring(indexOf + str2.length());
    }

    public static boolean a(String str, String str2) {
        return (str == null || str2 == null || str.indexOf(str2) < 0) ? false : true;
    }

    public static boolean b(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    private static String[] a(String str, String str2, int i2, boolean z) {
        int i3;
        boolean z2;
        boolean z3;
        int i4;
        int i5;
        boolean z4;
        boolean z5;
        int i6;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return a;
        }
        ArrayList arrayList = new ArrayList();
        if (str2 == null) {
            i5 = 0;
            z4 = false;
            z5 = false;
            i6 = 0;
            int i7 = 1;
            while (i5 < length) {
                if (Character.isWhitespace(str.charAt(i5))) {
                    if (z4 || z) {
                        int i8 = i7 + 1;
                        if (i7 == i2) {
                            i5 = length;
                            z5 = false;
                        } else {
                            z5 = true;
                        }
                        arrayList.add(str.substring(i6, i5));
                        i7 = i8;
                        z4 = false;
                    }
                    i6 = i5 + 1;
                    i5 = i6;
                } else {
                    i5++;
                    z4 = true;
                    z5 = false;
                }
            }
        } else {
            if (str2.length() == 1) {
                char charAt = str2.charAt(0);
                i3 = 0;
                z2 = false;
                z3 = false;
                i4 = 0;
                int i9 = 1;
                while (i3 < length) {
                    if (str.charAt(i3) == charAt) {
                        if (z2 || z) {
                            int i10 = i9 + 1;
                            if (i9 == i2) {
                                i3 = length;
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            arrayList.add(str.substring(i4, i3));
                            i9 = i10;
                            z2 = false;
                        }
                        i4 = i3 + 1;
                        i3 = i4;
                    } else {
                        i3++;
                        z2 = true;
                        z3 = false;
                    }
                }
            } else {
                i3 = 0;
                z2 = false;
                z3 = false;
                i4 = 0;
                int i11 = 1;
                while (i3 < length) {
                    if (str2.indexOf(str.charAt(i3)) >= 0) {
                        if (z2 || z) {
                            int i12 = i11 + 1;
                            if (i11 == i2) {
                                i3 = length;
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            arrayList.add(str.substring(i4, i3));
                            i11 = i12;
                            z2 = false;
                        }
                        i4 = i3 + 1;
                        i3 = i4;
                    } else {
                        i3++;
                        z2 = true;
                        z3 = false;
                    }
                }
            }
            i5 = i3;
            z4 = z2;
            z5 = z3;
            i6 = i4;
        }
        if (z4 || (z && z5)) {
            arrayList.add(str.substring(i6, i5));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String a(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return a(objArr, str, 0, objArr.length);
    }

    public static String a(Object[] objArr, String str, int i2, int i3) {
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        int i4 = i3 - i2;
        if (i4 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(((objArr[i2] == null ? 16 : objArr[i2].toString().length()) + str.length()) * i4);
        for (int i5 = i2; i5 < i3; i5++) {
            if (i5 > i2) {
                sb.append(str);
            }
            if (objArr[i5] != null) {
                sb.append(objArr[i5]);
            }
        }
        return sb.toString();
    }
}
