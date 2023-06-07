package com.shen.jd;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.linux.android.dvm.jni.ProxyDvmObject;
import com.github.unidbg.memory.Memory;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.ParsingException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.cert.X509Certificate;

/**
 * 调用京东的Sign函数
 */
public class JdSign extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;

    // 包名
    private final String packageName = "com.com.jingdong.app.mall";
    // apk 地址
//    private final String packagePath = "unidbg-android/src/test/resources/jd-11.4.0.apk";
//    private final String apkPath = "/data/app/com.jingdong.app.mall-BrX6yKhr86jyzYPsue-8fQ==/bask.apk";
    private final String packagePath = "unidbg-android/src/test/resources/jd-12.0.1.apk";
private final String apkPath = "/data/app/com.jingdong.app.mall-F6JA7LM558fdAVVCT2q_og==/bask.apk";
    // so 名称, 要去掉 lib 和  .so
    private final String libraryName = "jdbitmapkit";
    // jni 类名
    private final String jniClassName = "com.jingdong.common.utils.BitmapkitUtils";
    // 调试信息
    private final Boolean verbose = true;
    // jni 模块
    private final DvmClass Module;


    public JdSign() {

        // 实例化一个模拟器

        emulator = AndroidEmulatorBuilder
                .for32Bit()
                .addBackendFactory(new Unicorn2Factory(true))
                .setProcessName(packageName)
                .build();


        Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        vm = emulator.createDalvikVM(new File(packagePath));
        vm.setJni(this);
        vm.setVerbose(verbose);
        DalvikModule dm = vm.loadLibrary(libraryName, true);
        Module = vm.resolveClass(jniClassName);
        dm.callJNI_OnLoad(emulator);
    }

    @Override
    public DvmObject<?> getStaticObjectField(BaseVM vm, DvmClass dvmClass, String signature) {
        switch (signature) {
            case "com/jingdong/common/utils/BitmapkitUtils->a:Landroid/app/Application;": {
//                DvmClass Application = vm.resolveClass("android/app/Application");
                // 使用 android/app/Application 发现会报错: com.github.unidbg.arm.backend.BackendException
                // 百度了一下发现这个错误是找不到 methodID
                // 因此可以使用 android/app/Activity 代替, 因为它也可以获取 Application 对象
                DvmClass Context = vm.resolveClass("android/content/Context");
                DvmClass ContextWrapper = vm.resolveClass("android/content/ContextWrapper", Context);
                DvmClass Activity = vm.resolveClass("android/app/Activity", ContextWrapper);
                return Activity.newObject(signature);
            }
            case "android/content/pm/ApplicationInfo->sourceDir:Ljava/lang/String;": {
                return new StringObject(vm, apkPath);
            }
        }
        return super.getStaticObjectField(vm, dvmClass, signature);
    }

    @Override
    public DvmObject<?> getObjectField(BaseVM vm, DvmObject<?> dvmObject, String signature) {
        switch (signature) {
            case "android/content/pm/ApplicationInfo->sourceDir:Ljava/lang/String;": {
                return new StringObject(vm, apkPath);
            }
        }
        return super.getObjectField(vm, dvmObject, signature);
    }

    @Override
    public DvmObject<?> callStaticObjectMethod(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature) {
            case "com/jingdong/common/utils/BitmapkitZip->unZip(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)[B": {
                StringObject apkPath = varArg.getObjectArg(0);
                StringObject directory = varArg.getObjectArg(1);
                StringObject fileSuffix = varArg.getObjectArg(2);
                System.out.println("DEBUG apkPath:" + apkPath); // 可打印看看
                if (this.apkPath.equals(apkPath.getValue())
                        && "META-INF/".equals(directory.getValue())
                        && ".RSA".equals(fileSuffix.getValue())
                ) {
                    byte[] data = vm.unzip("META-INF/JINGDONG.RSA");
                    return new ByteArray(vm, data);
                }
            }
            case "com/jingdong/common/utils/BitmapkitZip->objectToBytes(Ljava/lang/Object;)[B": {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(varArg.getObjectArg(0).getValue());
                    objectOutputStream.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    objectOutputStream.close();
                    byteArrayOutputStream.close();
                    return new ByteArray(vm, byteArray);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.callStaticObjectMethod(vm, dvmClass, signature, varArg);
    }

    @Override
    public DvmObject<?> newObject(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature) {
            case "sun/security/pkcs/PKCS7-><init>([B)V":
                byte[] bArray = (byte[]) varArg.getObjectArg(0).getValue();
                try {
                    PKCS7 pkcs7 = new PKCS7(bArray);
                    return vm.resolveClass("sun/security/pkcs/PKCS7").newObject(pkcs7);
                } catch (ParsingException e) {
                    e.printStackTrace();
                }
        }

        return super.newObject(vm, dvmClass, signature, varArg);
    }

    @Override
    public DvmObject<?> callObjectMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature) {
            case "sun/security/pkcs/PKCS7->getCertificates()[Ljava/security/cert/X509Certificate;": {
                PKCS7 pkcs7 = (PKCS7) dvmObject.getValue();
                X509Certificate[] x509Certificates = pkcs7.getCertificates();
                return ProxyDvmObject.createObject(vm, x509Certificates);
            }
        }
        return super.callObjectMethod(vm, dvmObject, signature, varArg);
    }

    @Override
    public DvmObject<?> newObjectV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature) {
            case "java/lang/StringBuffer-><init>()V": {
                return vm.resolveClass("java/lang/StringBuffer").newObject(new StringBuffer());
            }
        }
        return super.newObjectV(vm, dvmClass, signature, vaList);
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature) {
            case "java/lang/StringBuffer->append(Ljava/lang/String;)Ljava/lang/StringBuffer;": {
                StringBuffer sb = (java.lang.StringBuffer) dvmObject.getValue();
                sb.append(vaList.getObjectArg(0).getValue());
                DvmClass StringBuffer = vm.resolveClass("java/lang/StringBuffer");
                return StringBuffer.newObject(sb);
            }
            case "java/lang/StringBuffer->toString()Ljava/lang/String;":
            case "java/lang/Integer->toString()Ljava/lang/String;": {
                return vm.resolveClass("java/lang/String").newObject(String.valueOf(dvmObject.getValue()));
            }
        }
        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }

    public static void main(String[] args) {
        JdSign ins = new JdSign();
        VM vm = ins.vm;
        DvmClass module = ins.Module;
        AndroidEmulator emulator = ins.emulator;

        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(null);
        String functionID = "wareBusiness";
        String body = "{\"abTest800\":true,\"acceptPrivacy\":true,\"avoidLive\":false,\"bbtf\":\"\",\"brand\":\"Xiaomi\",\"bybt\":\"\",\"cityCode\":72,\"cityId\":0,\"cpsNoTuan\":null,\"darkModelEnum\":3,\"districtId\":0,\"euaf\":false,\"eventId\":\"MyHistory_Product\",\"fromType\":0,\"isDesCbc\":true,\"isFromOpenApp\":false,\"latitude\":\"0.0\",\"lego\":true,\"longitude\":\"0.0\",\"model\":\"MI 9\",\"ocrFlag\":false,\"oneboxChannel\":false,\"oneboxKeyword\":\"\",\"oneboxSource\":\"\",\"overseas\":0,\"pdVersion\":\"1\",\"personas\":null,\"pluginVersion\":101050,\"plusClickCount\":0,\"plusLandedFatigue\":0,\"productJdv\":\"0|kong|t_1000023384_502517|zssc|46964044-308f-48e9-a475-189e0fa25997-p_1999-pr_100310-at_502517-tg_ext_0-00-0-tgx-5016199-2126-20230606|1686051354740|1686051388\",\"provinceId\":\"0\",\"prstate\":\"0\",\"searchWareflag\":\"\",\"selfDelivery\":\"0\",\"skuId\":\"1579242407\",\"source_type\":\"history\",\"source_value\":\"\",\"townId\":0,\"uAddrId\":\"0\",\"utmMedium\":null,\"wareInnerSource\":\"extra.inner.source.init\",\"yrqNew\":\"1\"}";
        String deviceUUID = "c67bdae88aac5d42";
        String platform = "android";
        String versionName = "12.0.1";

        DvmObject<?> ret = module.callStaticJniMethodObject(
                emulator,
                "getSignFromJni(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
                vm.addLocalObject(context),
                functionID,
                body,
                deviceUUID,
                platform,
                versionName);
        System.out.println("================================================");
        System.out.println(ret.getValue());
        System.out.println("================================================");

    }
}