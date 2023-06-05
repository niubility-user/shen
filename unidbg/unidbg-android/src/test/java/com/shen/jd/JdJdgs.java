package com.shen.jd;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.hook.hookzz.HookZz;
import com.github.unidbg.hook.hookzz.IHookZz;
import com.github.unidbg.hook.xhook.IxHook;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.XHookImpl;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.jni.ProxyDvmObject;
import com.github.unidbg.memory.Memory;

import java.io.File;

public class JdJdgs extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;

    // 包名
    private final String packageName = "com.com.jingdong.app.mall";
    // apk 地址
    private final String packagePath = "unidbg-android/src/test/resources/jd-12.0.1.apk";
    // so 名称, 要去掉 lib 和  .so
    private final String libraryName = "jdg";
    // jni 类名
    private final String jniClassName = "com.jd.security.jdguard.core.Bridge";
    // 调试信息
    private final Boolean verbose = true;
    // jni 模块
    private final DvmClass module;

    public JdJdgs() {
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
        module = vm.resolveClass(jniClassName);
        dm.callJNI_OnLoad(emulator);

    }

    public static void main(String[] args) {
        JdJdgs ins = new JdJdgs();
        ins.getJdgs();

    }

    public void getJdgs() {
        Integer i2 = 101;
        String arr1 = "/client.action bef=1&bfa06501b64b4672e3b8645fd0ad54401e4786bd9efebc784fa483cf99bec2fd=&build=98704&client=android&clientVersion=11.6.4&ef=1&eid=eidAf5088122acsfbxEYf1ulTs2pUn38cGRLh28RGgYgyPwz80gu9s7yfbXAvbZ1R70WQm1PENZaDGd6EF%2FmunBEHRDWPW5sYgYPelPhS+vYueanoeJv&ep=%7B%22hdid%22%3A%22JM9F1ywUPwflvMIpYPok0tt5k9kW4ArJEU3lfLhxBqw%3D%22%2C%22ts%22%3A1678345151525%2C%22ridx%22%3A-1%2C%22cipher%22%3A%7B%22area%22%3A%22CV83Cv8yDzu5XzK%3D%22%2C%22d_model%22%3A%22J05PUOnVU0O2CNKm%22%2C%22wifiBssid%22%3A%22dW5hbw93bq%3D%3D%22%2C%22osVersion%22%3A%22CJK%3D%22%2C%22d_brand%22%3A%22J25vUQn1cm%3D%3D%22%2C%22screen%22%3A%22CtO1EIenCNqm%22%2C%22uuid%22%3A%22ZtvwDNKnDzVsZtHtDtcnCK%3D%3D%22%2C%22aid%22%3A%22ZtvwDNKnDzVsZtHtDtcnCK%3D%3D%22%2C%22openudid%22%3A%22ZtvwDNKnDzVsZtHtDtcnCK%3D%3D%22%7D%2C%22ciphertype%22%3A5%2C%22version%22%3A%221.2.0%22%2C%22appname%22%3A%22com.jingdong.app.mall%22%7D&ext=%7B%22prstate%22%3A%220%22%2C%22pvcStu%22%3A%221%22%2C%22cfgExt%22%3A%22%7B%5C%22privacyOffline%5C%22%3A%5C%220%5C%22%7D%22%7D&functionId=wareBusiness&harmonyOs=0&lang=zh_CN&lmt=0&networkType=wifi&oaid=1B4220DEA49487D7AF8DE6AF4C0F1F60F05819E2ADCC3CD2ACDF3C07D81BEDC6&partner=tencent&scval=10062561918391&sdkVersion=29&sign=68049b09fa2cb6111f9ab0209ea9412e&st=1678348533650&sv=100&uemps=2-2-2";
        String arr2 = "sdm845|-|OnePlus6|-|-|-|-|-|-|-|-|-|-|-|-|-|-§§0|1§§-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-§§-|115717931008§§-|-|1.0|-§§0|1|-|-|-|-|-|-|-|-|-§§OnePlus/OnePlus6/OnePlus6:10/QKQ1.190716.003/2107162030:user/release-keys/|-|-|1678344292938|-§§-|-|-|-|-|-|-";
        String arr3 = "eidAf5088122acsfbxEYf1ulTs2pUn38cGRLh28RGgYgyPwz80gu9s7yfbXAvbZ1R70WQm1PENZaDGd6EF/munBEHRDWPW5sYgYPelPhS+vYueanoeJv";
        String arr4 = "1.0";
        String arr5 = "83";

        Object[] arr = new Object[]{arr1.getBytes(), arr2, arr3, arr4, arr5};

        DvmObject<?> ret = module.callStaticJniMethodObject(emulator, "main(I[Ljava/lang/Object;)[Ljava/lang/Object;", i2, ProxyDvmObject.createObject(vm, arr));
        System.out.println("================================================");


        System.out.println(ret.getValue());
        System.out.println("================================================");

    }

}