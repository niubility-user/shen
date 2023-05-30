package com.eclipsesource.v8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class NodeJS {
    private static final String GLOBAL = "global";
    private static final String NEXT_TICK = "nextTick";
    private static final String NODE = "node";
    private static final String PROCESS = "process";
    private static final String STARTUP_CALLBACK = "__run";
    private static final String STARTUP_SCRIPT = "global.__run(require, exports, module, __filename, __dirname);";
    private static final String STARTUP_SCRIPT_NAME = "startup";
    private static final String TMP_JS_EXT = ".js.tmp";
    private static final String VERSIONS = "versions";
    private String nodeVersion = null;
    private V8Function require;
    private V8 v8;

    private NodeJS(V8 v8) {
        this.v8 = v8;
    }

    public static NodeJS createNodeJS() {
        return createNodeJS(null);
    }

    private V8Function createScriptExecutionCallback(final File file) {
        return new V8Function(this.v8, new JavaCallback() { // from class: com.eclipsesource.v8.NodeJS.2
            @Override // com.eclipsesource.v8.JavaCallback
            public Object invoke(V8Object v8Object, V8Array v8Array) {
                V8Array v8Array2 = new V8Array(NodeJS.this.v8);
                try {
                    v8Array2.push(file.getAbsolutePath());
                    return NodeJS.this.require.call(null, v8Array2);
                } finally {
                    v8Array2.close();
                }
            }
        });
    }

    private static File createTemporaryScriptFile(String str, String str2) throws IOException {
        File createTempFile = File.createTempFile(str2, TMP_JS_EXT);
        PrintWriter printWriter = new PrintWriter(createTempFile, "UTF-8");
        try {
            printWriter.print(str);
            return createTempFile;
        } finally {
            printWriter.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init(V8Function v8Function) {
        this.require = v8Function;
    }

    private void safeRelease(Releasable releasable) {
        if (releasable != null) {
            releasable.release();
        }
    }

    public void exec(File file) {
        V8Array v8Array;
        Throwable th;
        V8Object v8Object;
        V8Function createScriptExecutionCallback = createScriptExecutionCallback(file);
        try {
            v8Object = this.v8.getObject(PROCESS);
            try {
                v8Array = new V8Array(this.v8);
            } catch (Throwable th2) {
                v8Array = null;
                th = th2;
            }
        } catch (Throwable th3) {
            v8Array = null;
            th = th3;
            v8Object = null;
        }
        try {
            v8Array.push((V8Value) createScriptExecutionCallback);
            v8Object.executeObjectFunction(NEXT_TICK, v8Array);
            safeRelease(v8Object);
            safeRelease(v8Array);
            safeRelease(createScriptExecutionCallback);
        } catch (Throwable th4) {
            th = th4;
            safeRelease(v8Object);
            safeRelease(v8Array);
            safeRelease(createScriptExecutionCallback);
            throw th;
        }
    }

    public String getNodeVersion() {
        Releasable releasable;
        V8Object object;
        String str = this.nodeVersion;
        if (str != null) {
            return str;
        }
        V8Object v8Object = null;
        try {
            object = this.v8.getObject(PROCESS);
        } catch (Throwable th) {
            th = th;
            releasable = null;
        }
        try {
            v8Object = object.getObject(VERSIONS);
            this.nodeVersion = v8Object.getString(NODE);
            safeRelease(object);
            safeRelease(v8Object);
            return this.nodeVersion;
        } catch (Throwable th2) {
            th = th2;
            releasable = v8Object;
            v8Object = object;
            safeRelease(v8Object);
            safeRelease(releasable);
            throw th;
        }
    }

    public V8 getRuntime() {
        return this.v8;
    }

    public boolean handleMessage() {
        this.v8.checkThread();
        return this.v8.pumpMessageLoop();
    }

    public boolean isRunning() {
        this.v8.checkThread();
        return this.v8.isRunning();
    }

    public void release() {
        this.v8.checkThread();
        if (!this.require.isReleased()) {
            this.require.close();
        }
        if (this.v8.isReleased()) {
            return;
        }
        this.v8.close();
    }

    public V8Object require(File file) {
        this.v8.checkThread();
        V8Array v8Array = new V8Array(this.v8);
        try {
            v8Array.push(file.getAbsolutePath());
            return (V8Object) this.require.call(null, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public static NodeJS createNodeJS(File file) {
        V8 createV8Runtime = V8.createV8Runtime("global");
        NodeJS nodeJS = new NodeJS(createV8Runtime);
        createV8Runtime.registerJavaMethod(new JavaVoidCallback() { // from class: com.eclipsesource.v8.NodeJS.1
            @Override // com.eclipsesource.v8.JavaVoidCallback
            public void invoke(V8Object v8Object, V8Array v8Array) {
                V8Function v8Function = (V8Function) v8Array.get(0);
                try {
                    NodeJS.this.init(v8Function.twin());
                } finally {
                    v8Function.close();
                }
            }
        }, STARTUP_CALLBACK);
        try {
            File createTemporaryScriptFile = createTemporaryScriptFile(STARTUP_SCRIPT, STARTUP_SCRIPT_NAME);
            createV8Runtime.createNodeRuntime(createTemporaryScriptFile.getAbsolutePath());
            createTemporaryScriptFile.delete();
            if (file != null) {
                nodeJS.exec(file);
            }
            return nodeJS;
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }
}
