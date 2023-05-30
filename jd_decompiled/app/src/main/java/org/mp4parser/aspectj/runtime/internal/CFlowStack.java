package org.mp4parser.aspectj.runtime.internal;

import com.jd.dynamic.DYConstants;
import java.util.Stack;
import org.mp4parser.aspectj.lang.NoAspectBoundException;
import org.mp4parser.aspectj.runtime.CFlow;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStack;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactory;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl;
import org.mp4parser.aspectj.runtime.internal.cflowstack.ThreadStackFactoryImpl11;

/* loaded from: classes11.dex */
public class CFlowStack {
    private static ThreadStackFactory tsFactory;
    private ThreadStack stackProxy = tsFactory.getNewThreadStack();

    static {
        selectFactoryForVMVersion();
    }

    private static String getSystemPropertyWithoutSecurityException(String str, String str2) {
        try {
            return System.getProperty(str, str2);
        } catch (SecurityException unused) {
            return str2;
        }
    }

    private static ThreadStackFactory getThreadLocalStackFactory() {
        return new ThreadStackFactoryImpl();
    }

    private static ThreadStackFactory getThreadLocalStackFactoryFor11() {
        return new ThreadStackFactoryImpl11();
    }

    private Stack getThreadStack() {
        return this.stackProxy.getThreadStack();
    }

    public static String getThreadStackFactoryClassName() {
        return tsFactory.getClass().getName();
    }

    private static void selectFactoryForVMVersion() {
        String systemPropertyWithoutSecurityException = getSystemPropertyWithoutSecurityException("aspectj.runtime.cflowstack.usethreadlocal", "unspecified");
        boolean z = false;
        if (!systemPropertyWithoutSecurityException.equals("unspecified") ? systemPropertyWithoutSecurityException.equals("yes") || systemPropertyWithoutSecurityException.equals(DYConstants.DY_TRUE) : System.getProperty("java.class.version", "0.0").compareTo("46.0") >= 0) {
            z = true;
        }
        if (z) {
            tsFactory = getThreadLocalStackFactory();
        } else {
            tsFactory = getThreadLocalStackFactoryFor11();
        }
    }

    public Object get(int i2) {
        CFlow peekCFlow = peekCFlow();
        if (peekCFlow == null) {
            return null;
        }
        return peekCFlow.get(i2);
    }

    public boolean isValid() {
        return !getThreadStack().isEmpty();
    }

    public Object peek() {
        Stack threadStack = getThreadStack();
        if (!threadStack.isEmpty()) {
            return threadStack.peek();
        }
        throw new NoAspectBoundException();
    }

    public CFlow peekCFlow() {
        Stack threadStack = getThreadStack();
        if (threadStack.isEmpty()) {
            return null;
        }
        return (CFlow) threadStack.peek();
    }

    public Object peekInstance() {
        CFlow peekCFlow = peekCFlow();
        if (peekCFlow != null) {
            return peekCFlow.getAspect();
        }
        throw new NoAspectBoundException();
    }

    public CFlow peekTopCFlow() {
        Stack threadStack = getThreadStack();
        if (threadStack.isEmpty()) {
            return null;
        }
        return (CFlow) threadStack.elementAt(0);
    }

    public void pop() {
        Stack threadStack = getThreadStack();
        threadStack.pop();
        if (threadStack.isEmpty()) {
            this.stackProxy.removeThreadStack();
        }
    }

    public void push(Object obj) {
        getThreadStack().push(obj);
    }

    public void pushInstance(Object obj) {
        getThreadStack().push(new CFlow(obj));
    }

    public void push(Object[] objArr) {
        getThreadStack().push(new CFlowPlusState(objArr));
    }
}
