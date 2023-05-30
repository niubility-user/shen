package de.greenrobot.event;

import java.lang.reflect.Method;

/* loaded from: classes11.dex */
final class SubscriberMethod {
    final Class<?> eventType;
    final Method method;
    String methodString;
    final ThreadMode threadMode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubscriberMethod(Method method, ThreadMode threadMode, Class<?> cls) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = cls;
    }

    private synchronized void checkMethodString() {
        if (this.methodString == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.method.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.method.getName());
            sb.append('(');
            sb.append(this.eventType.getName());
            this.methodString = sb.toString();
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof SubscriberMethod) {
            checkMethodString();
            SubscriberMethod subscriberMethod = (SubscriberMethod) obj;
            subscriberMethod.checkMethodString();
            return this.methodString.equals(subscriberMethod.methodString);
        }
        return false;
    }

    public int hashCode() {
        return this.method.hashCode();
    }
}
