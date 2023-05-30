package com.google.zxing.pdf417.decoder.ec;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class ModulusPoly {
    private final int[] coefficients;
    private final ModulusGF field;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly(ModulusGF modulusGF, int[] iArr) {
        if (iArr.length != 0) {
            this.field = modulusGF;
            int length = iArr.length;
            if (length > 1 && iArr[0] == 0) {
                int i2 = 1;
                while (i2 < length && iArr[i2] == 0) {
                    i2++;
                }
                if (i2 == length) {
                    this.coefficients = new int[]{0};
                    return;
                }
                int[] iArr2 = new int[length - i2];
                this.coefficients = iArr2;
                System.arraycopy(iArr, i2, iArr2, 0, iArr2.length);
                return;
            }
            this.coefficients = iArr;
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly add(ModulusPoly modulusPoly) {
        if (this.field.equals(modulusPoly.field)) {
            if (isZero()) {
                return modulusPoly;
            }
            if (modulusPoly.isZero()) {
                return this;
            }
            int[] iArr = this.coefficients;
            int[] iArr2 = modulusPoly.coefficients;
            if (iArr.length <= iArr2.length) {
                iArr = iArr2;
                iArr2 = iArr;
            }
            int[] iArr3 = new int[iArr.length];
            int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr3, 0, length);
            for (int i2 = length; i2 < iArr.length; i2++) {
                iArr3[i2] = this.field.add(iArr2[i2 - length], iArr[i2]);
            }
            return new ModulusPoly(this.field, iArr3);
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    ModulusPoly[] divide(ModulusPoly modulusPoly) {
        if (this.field.equals(modulusPoly.field)) {
            if (!modulusPoly.isZero()) {
                ModulusPoly zero = this.field.getZero();
                int inverse = this.field.inverse(modulusPoly.getCoefficient(modulusPoly.getDegree()));
                ModulusPoly modulusPoly2 = this;
                while (modulusPoly2.getDegree() >= modulusPoly.getDegree() && !modulusPoly2.isZero()) {
                    int degree = modulusPoly2.getDegree() - modulusPoly.getDegree();
                    int multiply = this.field.multiply(modulusPoly2.getCoefficient(modulusPoly2.getDegree()), inverse);
                    ModulusPoly multiplyByMonomial = modulusPoly.multiplyByMonomial(degree, multiply);
                    zero = zero.add(this.field.buildMonomial(degree, multiply));
                    modulusPoly2 = modulusPoly2.subtract(multiplyByMonomial);
                }
                return new ModulusPoly[]{zero, modulusPoly2};
            }
            throw new IllegalArgumentException("Divide by 0");
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int evaluateAt(int i2) {
        if (i2 == 0) {
            return getCoefficient(0);
        }
        int[] iArr = this.coefficients;
        int length = iArr.length;
        if (i2 == 1) {
            int i3 = 0;
            for (int i4 : iArr) {
                i3 = this.field.add(i3, i4);
            }
            return i3;
        }
        int i5 = iArr[0];
        for (int i6 = 1; i6 < length; i6++) {
            ModulusGF modulusGF = this.field;
            i5 = modulusGF.add(modulusGF.multiply(i2, i5), this.coefficients[i6]);
        }
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCoefficient(int i2) {
        return this.coefficients[(r0.length - 1) - i2];
    }

    int[] getCoefficients() {
        return this.coefficients;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDegree() {
        return this.coefficients.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isZero() {
        return this.coefficients[0] == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly multiply(ModulusPoly modulusPoly) {
        if (this.field.equals(modulusPoly.field)) {
            if (!isZero() && !modulusPoly.isZero()) {
                int[] iArr = this.coefficients;
                int length = iArr.length;
                int[] iArr2 = modulusPoly.coefficients;
                int length2 = iArr2.length;
                int[] iArr3 = new int[(length + length2) - 1];
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = iArr[i2];
                    for (int i4 = 0; i4 < length2; i4++) {
                        int i5 = i2 + i4;
                        ModulusGF modulusGF = this.field;
                        iArr3[i5] = modulusGF.add(iArr3[i5], modulusGF.multiply(i3, iArr2[i4]));
                    }
                }
                return new ModulusPoly(this.field, iArr3);
            }
            return this.field.getZero();
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly multiplyByMonomial(int i2, int i3) {
        if (i2 >= 0) {
            if (i3 == 0) {
                return this.field.getZero();
            }
            int length = this.coefficients.length;
            int[] iArr = new int[i2 + length];
            for (int i4 = 0; i4 < length; i4++) {
                iArr[i4] = this.field.multiply(this.coefficients[i4], i3);
            }
            return new ModulusPoly(this.field, iArr);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly negative() {
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.field.subtract(0, this.coefficients[i2]);
        }
        return new ModulusPoly(this.field, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly subtract(ModulusPoly modulusPoly) {
        if (this.field.equals(modulusPoly.field)) {
            return modulusPoly.isZero() ? this : add(modulusPoly.negative());
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    sb.append(" - ");
                    coefficient = -coefficient;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    sb.append(coefficient);
                }
                if (degree != 0) {
                    if (degree == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(degree);
                    }
                }
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModulusPoly multiply(int i2) {
        if (i2 == 0) {
            return this.field.getZero();
        }
        if (i2 == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.field.multiply(this.coefficients[i3], i2);
        }
        return new ModulusPoly(this.field, iArr);
    }
}
