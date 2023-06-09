package com.shen.jd;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.arm.backend.Unicorn2Factory;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.DvmClass;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.memory.Memory;

import java.io.File;

/**
 * 模板代码
 */
public class Template extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;

    // 包名
    private final String packageName = "包名";
    // apk 地址
    private final String packagePath = "apk 地址";
    // so 名称, 要去掉 lib 和  .so
    private final String libraryName = "so 名称, 要去掉 lib 和  .so";
    // jni 类名
    private final String jniClassName = "jni 类名";
    // 调试信息
    private final Boolean verbose = true;
    // jni 模块
    private final DvmClass Module;


    public Template() {

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

    public static void main(String[] args) {
        Template ins = new Template();
    }


}