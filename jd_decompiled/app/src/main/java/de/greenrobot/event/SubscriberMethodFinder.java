package de.greenrobot.event;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.text.Typography;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SubscriberMethodFinder {
    private static final int BRIDGE = 64;
    private static final int MODIFIERS_IGNORE = 5192;
    private static final String ON_EVENT_METHOD_NAME = "onEvent";
    private static final int SYNTHETIC = 4096;
    private static final Map<String, List<SubscriberMethod>> methodCache = new HashMap();
    private final Map<Class<?>, Class<?>> skipMethodVerificationForClasses = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubscriberMethodFinder(List<Class<?>> list) {
        if (list != null) {
            for (Class<?> cls : list) {
                this.skipMethodVerificationForClasses.put(cls, cls);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearCaches() {
        Map<String, List<SubscriberMethod>> map = methodCache;
        synchronized (map) {
            map.clear();
        }
    }

    private void filterSubscriberMethods(List<SubscriberMethod> list, HashMap<String, Class> hashMap, StringBuilder sb, Method[] methodArr) {
        ThreadMode threadMode;
        for (Method method : methodArr) {
            String name = method.getName();
            if (name.startsWith(ON_EVENT_METHOD_NAME)) {
                int modifiers = method.getModifiers();
                Class<?> declaringClass = method.getDeclaringClass();
                if ((modifiers & 1) != 0 && (modifiers & 5192) == 0) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && (threadMode = getThreadMode(declaringClass, method, name)) != null) {
                        Class<?> cls = parameterTypes[0];
                        sb.setLength(0);
                        sb.append(name);
                        sb.append(Typography.greater);
                        sb.append(cls.getName());
                        String sb2 = sb.toString();
                        Class put = hashMap.put(sb2, declaringClass);
                        if (put != null && !put.isAssignableFrom(declaringClass)) {
                            hashMap.put(sb2, put);
                        } else {
                            list.add(new SubscriberMethod(method, threadMode, cls));
                        }
                    }
                } else if (!this.skipMethodVerificationForClasses.containsKey(declaringClass)) {
                    String str = EventBus.TAG;
                    String str2 = "Skipping method (not public, static or abstract): " + declaringClass + OrderISVUtil.MONEY_DECIMAL + name;
                }
            }
        }
    }

    private ThreadMode getThreadMode(Class<?> cls, Method method, String str) {
        String substring = str.substring(7);
        if (substring.length() == 0) {
            return ThreadMode.PostThread;
        }
        if (substring.equals("MainThread")) {
            return ThreadMode.MainThread;
        }
        if (substring.equals("BackgroundThread")) {
            return ThreadMode.BackgroundThread;
        }
        if (substring.equals("Async")) {
            return ThreadMode.Async;
        }
        if (this.skipMethodVerificationForClasses.containsKey(cls)) {
            return null;
        }
        throw new EventBusException("Illegal onEvent method, check for typos: " + method);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<SubscriberMethod> findSubscriberMethods(Class<?> cls) {
        List<SubscriberMethod> list;
        String name = cls.getName();
        Map<String, List<SubscriberMethod>> map = methodCache;
        synchronized (map) {
            list = map.get(name);
        }
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        HashMap<String, Class> hashMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            String name2 = cls2.getName();
            if (name2.startsWith("java.") || name2.startsWith("javax.") || name2.startsWith("android.")) {
                break;
            }
            try {
                filterSubscriberMethods(arrayList, hashMap, sb, cls2.getDeclaredMethods());
            } catch (Throwable th) {
                th.printStackTrace();
                Method[] methods = cls.getMethods();
                arrayList.clear();
                hashMap.clear();
                filterSubscriberMethods(arrayList, hashMap, sb, methods);
            }
        }
        if (!arrayList.isEmpty()) {
            Map<String, List<SubscriberMethod>> map2 = methodCache;
            synchronized (map2) {
                map2.put(name, arrayList);
            }
            return arrayList;
        }
        throw new EventBusException("Subscriber " + cls + " has no public methods called " + ON_EVENT_METHOD_NAME);
    }
}
