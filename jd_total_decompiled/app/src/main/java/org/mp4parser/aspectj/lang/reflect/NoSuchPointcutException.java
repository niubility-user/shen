package org.mp4parser.aspectj.lang.reflect;

/* loaded from: classes11.dex */
public class NoSuchPointcutException extends Exception {
    private static final long serialVersionUID = 3256444698657634352L;
    private String name;

    public NoSuchPointcutException(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}