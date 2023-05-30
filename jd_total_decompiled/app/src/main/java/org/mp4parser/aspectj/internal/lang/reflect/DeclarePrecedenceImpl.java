package org.mp4parser.aspectj.internal.lang.reflect;

import com.jd.dynamic.DYConstants;
import java.util.StringTokenizer;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.DeclarePrecedence;
import org.mp4parser.aspectj.lang.reflect.TypePattern;

/* loaded from: classes11.dex */
public class DeclarePrecedenceImpl implements DeclarePrecedence {
    private AjType<?> declaringType;
    private TypePattern[] precedenceList;
    private String precedenceString;

    public DeclarePrecedenceImpl(String str, AjType ajType) {
        this.declaringType = ajType;
        this.precedenceString = str;
        StringTokenizer stringTokenizer = new StringTokenizer(str.startsWith("(") ? str.substring(1, str.length() - 1) : str, DYConstants.DY_REGEX_COMMA);
        this.precedenceList = new TypePattern[stringTokenizer.countTokens()];
        int i2 = 0;
        while (true) {
            TypePattern[] typePatternArr = this.precedenceList;
            if (i2 >= typePatternArr.length) {
                return;
            }
            typePatternArr[i2] = new TypePatternImpl(stringTokenizer.nextToken().trim());
            i2++;
        }
    }

    @Override // org.mp4parser.aspectj.lang.reflect.DeclarePrecedence
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.DeclarePrecedence
    public TypePattern[] getPrecedenceOrder() {
        return this.precedenceList;
    }

    public String toString() {
        return "declare precedence : " + this.precedenceString;
    }
}
