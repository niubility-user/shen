package org.mp4parser.aspectj.runtime.reflect;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.lang.ref.SoftReference;
import java.util.StringTokenizer;
import kotlin.text.Typography;
import org.mp4parser.aspectj.lang.Signature;

/* loaded from: classes11.dex */
abstract class SignatureImpl implements Signature {
    static final String INNER_SEP = ":";
    static final char SEP = '-';
    private static boolean useCache = true;
    Class declaringType;
    String declaringTypeName;
    ClassLoader lookupClassLoader;
    int modifiers;
    String name;
    Cache stringCache;
    private String stringRep;
    static String[] EMPTY_STRING_ARRAY = new String[0];
    static Class[] EMPTY_CLASS_ARRAY = new Class[0];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface Cache {
        String get(int i2);

        void set(int i2, String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class CacheImpl implements Cache {
        private SoftReference toStringCacheRef;

        public CacheImpl() {
            makeCache();
        }

        private String[] array() {
            return (String[]) this.toStringCacheRef.get();
        }

        private String[] makeCache() {
            String[] strArr = new String[3];
            this.toStringCacheRef = new SoftReference(strArr);
            return strArr;
        }

        @Override // org.mp4parser.aspectj.runtime.reflect.SignatureImpl.Cache
        public String get(int i2) {
            String[] array = array();
            if (array == null) {
                return null;
            }
            return array[i2];
        }

        @Override // org.mp4parser.aspectj.runtime.reflect.SignatureImpl.Cache
        public void set(int i2, String str) {
            String[] array = array();
            if (array == null) {
                array = makeCache();
            }
            array[i2] = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignatureImpl(int i2, String str, Class cls) {
        this.modifiers = -1;
        this.lookupClassLoader = null;
        this.modifiers = i2;
        this.name = str;
        this.declaringType = cls;
    }

    private ClassLoader getLookupClassLoader() {
        if (this.lookupClassLoader == null) {
            this.lookupClassLoader = getClass().getClassLoader();
        }
        return this.lookupClassLoader;
    }

    static boolean getUseCache() {
        return useCache;
    }

    static void setUseCache(boolean z) {
        useCache = z;
    }

    void addFullTypeNames(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(fullTypeName(clsArr[i2]));
        }
    }

    void addShortTypeNames(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i2 = 0; i2 < clsArr.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(shortTypeName(clsArr[i2]));
        }
    }

    void addTypeArray(StringBuffer stringBuffer, Class[] clsArr) {
        addFullTypeNames(stringBuffer, clsArr);
    }

    protected abstract String createToString(StringMaker stringMaker);

    int extractInt(int i2) {
        return Integer.parseInt(extractString(i2), 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String extractString(int i2) {
        int indexOf = this.stringRep.indexOf(45);
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            if (i2 <= 0) {
                break;
            }
            i3 = indexOf + 1;
            indexOf = this.stringRep.indexOf(45, i3);
            i2 = i4;
        }
        if (indexOf == -1) {
            indexOf = this.stringRep.length();
        }
        return this.stringRep.substring(i3, indexOf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] extractStrings(int i2) {
        StringTokenizer stringTokenizer = new StringTokenizer(extractString(i2), INNER_SEP);
        int countTokens = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens];
        for (int i3 = 0; i3 < countTokens; i3++) {
            strArr[i3] = stringTokenizer.nextToken();
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class extractType(int i2) {
        return Factory.makeClass(extractString(i2), getLookupClassLoader());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class[] extractTypes(int i2) {
        StringTokenizer stringTokenizer = new StringTokenizer(extractString(i2), INNER_SEP);
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i3 = 0; i3 < countTokens; i3++) {
            clsArr[i3] = Factory.makeClass(stringTokenizer.nextToken(), getLookupClassLoader());
        }
        return clsArr;
    }

    String fullTypeName(Class cls) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (cls.isArray()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(fullTypeName(cls.getComponentType()));
            stringBuffer.append("[]");
            return stringBuffer.toString();
        }
        return cls.getName().replace(Typography.dollar, OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    @Override // org.mp4parser.aspectj.lang.Signature
    public Class getDeclaringType() {
        if (this.declaringType == null) {
            this.declaringType = extractType(2);
        }
        return this.declaringType;
    }

    @Override // org.mp4parser.aspectj.lang.Signature
    public String getDeclaringTypeName() {
        if (this.declaringTypeName == null) {
            this.declaringTypeName = getDeclaringType().getName();
        }
        return this.declaringTypeName;
    }

    @Override // org.mp4parser.aspectj.lang.Signature
    public int getModifiers() {
        if (this.modifiers == -1) {
            this.modifiers = extractInt(0);
        }
        return this.modifiers;
    }

    @Override // org.mp4parser.aspectj.lang.Signature
    public String getName() {
        if (this.name == null) {
            this.name = extractString(1);
        }
        return this.name;
    }

    public void setLookupClassLoader(ClassLoader classLoader) {
        this.lookupClassLoader = classLoader;
    }

    String shortTypeName(Class cls) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (cls.isArray()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(shortTypeName(cls.getComponentType()));
            stringBuffer.append("[]");
            return stringBuffer.toString();
        }
        return stripPackageName(cls.getName()).replace(Typography.dollar, OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    String stripPackageName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    @Override // org.mp4parser.aspectj.lang.Signature
    public final String toLongString() {
        return toString(StringMaker.longStringMaker);
    }

    @Override // org.mp4parser.aspectj.lang.Signature
    public final String toShortString() {
        return toString(StringMaker.shortStringMaker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String toString(StringMaker stringMaker) {
        String str;
        if (useCache) {
            Cache cache = this.stringCache;
            if (cache == null) {
                try {
                    this.stringCache = new CacheImpl();
                } catch (Throwable unused) {
                    useCache = false;
                }
            } else {
                str = cache.get(stringMaker.cacheOffset);
                if (str == null) {
                    str = createToString(stringMaker);
                }
                if (useCache) {
                    this.stringCache.set(stringMaker.cacheOffset, str);
                }
                return str;
            }
        }
        str = null;
        if (str == null) {
        }
        if (useCache) {
        }
        return str;
    }

    public SignatureImpl(String str) {
        this.modifiers = -1;
        this.lookupClassLoader = null;
        this.stringRep = str;
    }

    @Override // org.mp4parser.aspectj.lang.Signature
    public final String toString() {
        return toString(StringMaker.middleStringMaker);
    }
}
