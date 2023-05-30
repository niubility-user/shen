package com.hihonor.push.framework.aidl;

import android.os.Bundle;
import android.os.Parcelable;
import com.hihonor.push.framework.aidl.annotation.Packed;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class MessageCodec {
    private static final String BUNDLE_NEXT = "_next_item_";
    private static final String BUNDLE_VALUE = "_value_";
    public static final String PACKED_TYPE = "_packed_type_";
    private static final String TAG = "MessageCodec";
    public static final int VAL_ENTITY = 0;
    public static final int VAL_LIST = 1;
    public static final int VAL_NULL = -1;

    private MessageCodec() {
    }

    public static Bundle formMessageEntity(IMessageEntity iMessageEntity, Bundle bundle) {
        if (iMessageEntity != null && bundle != null) {
            for (Class<?> cls = iMessageEntity.getClass(); cls != null; cls = cls.getSuperclass()) {
                for (Field field : cls.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Packed.class)) {
                        try {
                            formMessageField(iMessageEntity, field, bundle);
                        } catch (IllegalAccessException | IllegalArgumentException unused) {
                            String str = "encode, get value of the field exception, field name: " + field.getName();
                        }
                    }
                }
            }
        }
        return bundle;
    }

    private static void formMessageField(IMessageEntity iMessageEntity, Field field, Bundle bundle) {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        put(field.getName(), field.get(iMessageEntity), bundle);
        field.setAccessible(isAccessible);
    }

    private static Object newInstance(Field field, Bundle bundle) {
        String name = field.getName();
        Object obj = bundle.get(name);
        if (obj instanceof Bundle) {
            try {
                Bundle bundle2 = (Bundle) obj;
                int i2 = bundle2.getInt(PACKED_TYPE, -1);
                if (i2 == 1) {
                    return parseGenericType(field.getGenericType(), bundle2);
                }
                if (i2 == 0) {
                    return parseMessageEntity((Bundle) obj, (IMessageEntity) field.getType().newInstance());
                }
            } catch (Exception unused) {
                String str = "decode, read value of the field exception, field name: " + name;
                return null;
            }
        }
        return obj;
    }

    private static List<Object> parseGenericType(Type type, Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            bundle = bundle.getBundle(BUNDLE_NEXT);
            if (bundle == null) {
                return arrayList;
            }
            Object obj = bundle.get(BUNDLE_VALUE);
            if (!obj.getClass().isPrimitive() && !(obj instanceof Serializable)) {
                if (obj instanceof Bundle) {
                    Bundle bundle2 = (Bundle) obj;
                    int i2 = bundle2.getInt(PACKED_TYPE, -1);
                    if (i2 == 1) {
                        throw new InstantiationException("Nested List can not be supported");
                    }
                    if (i2 != 0) {
                        throw new InstantiationException("Unknown type can not be supported");
                    }
                    obj = parseMessageEntity(bundle2, (IMessageEntity) ((Class) ((ParameterizedType) type).getActualTypeArguments()[0]).newInstance());
                } else {
                    continue;
                }
            }
            arrayList.add(obj);
        }
    }

    public static IMessageEntity parseMessageEntity(Bundle bundle, IMessageEntity iMessageEntity) {
        if (bundle != null && iMessageEntity != null) {
            bundle.setClassLoader(iMessageEntity.getClass().getClassLoader());
            for (Class<?> cls = iMessageEntity.getClass(); cls != null; cls = cls.getSuperclass()) {
                for (Field field : cls.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Packed.class)) {
                        try {
                            parseMessageField(iMessageEntity, field, bundle);
                        } catch (IllegalAccessException | IllegalArgumentException unused) {
                            String str = "decode, set value of the field exception, field name:" + field.getName();
                        }
                    }
                }
            }
        }
        return iMessageEntity;
    }

    private static void parseMessageField(IMessageEntity iMessageEntity, Field field, Bundle bundle) {
        Object newInstance = newInstance(field, bundle);
        if (newInstance != null) {
            boolean isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(iMessageEntity, newInstance);
            field.setAccessible(isAccessible);
        }
    }

    private static void put(String str, Object obj, Bundle bundle) {
        if (obj == null || putGenericType(str, obj, bundle)) {
            return;
        }
        if (obj instanceof CharSequence) {
            bundle.putCharSequence(str, (CharSequence) obj);
        } else if (obj instanceof Parcelable) {
            bundle.putParcelable(str, (Parcelable) obj);
        } else if (obj instanceof byte[]) {
            bundle.putByteArray(str, (byte[]) obj);
        } else if (obj instanceof List) {
            putList(str, (List) obj, bundle);
        } else if (obj instanceof Serializable) {
            bundle.putSerializable(str, (Serializable) obj);
        } else if (obj instanceof IMessageEntity) {
            Bundle formMessageEntity = formMessageEntity((IMessageEntity) obj, new Bundle());
            formMessageEntity.putInt(PACKED_TYPE, 0);
            bundle.putBundle(str, formMessageEntity);
        } else {
            String str2 = "cannot support type, " + str;
        }
    }

    private static boolean putGenericType(String str, Object obj, Bundle bundle) {
        if (obj instanceof String) {
            bundle.putString(str, (String) obj);
            return true;
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Integer) obj).intValue());
            return true;
        } else if (obj instanceof Short) {
            bundle.putShort(str, ((Short) obj).shortValue());
            return true;
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
            return true;
        } else if (obj instanceof Float) {
            bundle.putFloat(str, ((Float) obj).floatValue());
            return true;
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
            return true;
        } else if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            return true;
        } else {
            return false;
        }
    }

    private static void putList(String str, List list, Bundle bundle) {
        Bundle bundle2 = null;
        for (Object obj : list) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
                bundle.putBundle(str, bundle2);
                bundle2.putInt(PACKED_TYPE, 1);
            }
            bundle2 = putNext(BUNDLE_VALUE, bundle2, obj);
        }
    }

    private static Bundle putNext(String str, Bundle bundle, Object obj) {
        Bundle bundle2 = new Bundle();
        put(str, obj, bundle2);
        bundle.putBundle(BUNDLE_NEXT, bundle2);
        return bundle2;
    }
}
