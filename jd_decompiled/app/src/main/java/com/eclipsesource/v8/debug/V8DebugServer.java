package com.eclipsesource.v8.debug;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.Releasable;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.V8Value;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class V8DebugServer {
    private static final String DEBUG_BREAK_HANDLER = "__j2v8_debug_handler";
    public static String DEBUG_OBJECT_NAME = "__j2v8_Debug";
    private static final String HEADER_EMBEDDING_HOST = "Embedding-Host: ";
    private static final String HEADER_PROTOCOL_VERSION = "Protocol-Version: ";
    private static final String HEADER_TYPE = "Type: ";
    private static final String HEADER_V8_VERSION = "V8-Version: ";
    private static final String J2V8_VERSION = "4.0.0";
    private static final String MAKE_BREAK_EVENT = "__j2v8_MakeBreakEvent";
    private static final String MAKE_COMPILE_EVENT = "__j2v8_MakeCompileEvent";
    private static final int PROTOCOL_BUFFER_SIZE = 4096;
    private static final Charset PROTOCOL_CHARSET;
    private static final byte[] PROTOCOL_CONTENT_LENGTH_BYTES;
    private static final String PROTOCOL_CONTENT_LENGTH_HEADER = "Content-Length:";
    private static final String PROTOCOL_EOL = "\r\n";
    private static final byte[] PROTOCOL_EOL_BYTES;
    private static final String PROTOCOL_VERSION = "1";
    private static final String SET_LISTENER = "setListener";
    private static final String V8_DEBUG_OBJECT = "Debug";
    private static final String V8_VERSION = "4.10.253";
    private Socket client;
    private V8Object debugObject;
    private V8Object runningStateDcp;
    private V8 runtime;
    private ServerSocket server;
    private V8Object stoppedStateDcp;
    private boolean waitForConnection;
    private Object clientLock = new Object();
    private boolean traceCommunication = false;
    private List<String> requests = new LinkedList();

    /* loaded from: classes.dex */
    private class ClientLoop implements Runnable {
        private int from;

        private ClientLoop() {
        }

        private int indexOf(byte[] bArr, byte[] bArr2, int i2, int i3) {
            int i4;
            int length = bArr.length;
            while (i2 < i3) {
                while (i4 <= length) {
                    if (i4 == length) {
                        return i2;
                    }
                    int i5 = i2 + i4;
                    i4 = (i5 < i3 && bArr2[i5] == bArr[i4]) ? i4 + 1 : 0;
                    i2++;
                }
                i2++;
            }
            return -1;
        }

        private byte[] join(byte[] bArr, byte[] bArr2, int i2, int i3) {
            byte[] bArr3 = new byte[bArr.length + i3];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, i2, bArr3, bArr.length, i3);
            return bArr3;
        }

        private void processClientRequests() throws IOException {
            InputStream inputStream;
            byte[] bArr = new byte[0];
            byte[] bArr2 = new byte[4096];
            synchronized (V8DebugServer.this.clientLock) {
                inputStream = V8DebugServer.this.client.getInputStream();
            }
            byte[] bArr3 = bArr;
            int i2 = 0;
            boolean z = false;
            int i3 = -1;
            while (true) {
                int read = inputStream.read(bArr2, i2, 4096 - i2);
                if (read <= 0) {
                    return;
                }
                int i4 = read + i2;
                this.from = 0;
                do {
                    if (i3 < 0) {
                        i3 = readContentLength(bArr2, i4);
                        if (i3 < 0) {
                            break;
                        }
                    }
                    if (!z && !(z = skipToolInfo(bArr2, i4))) {
                        break;
                    }
                    int min = Math.min(i3 - bArr3.length, i4 - this.from);
                    bArr3 = join(bArr3, bArr2, this.from, min);
                    this.from += min;
                    if (bArr3.length == i3) {
                        String str = new String(bArr3, V8DebugServer.PROTOCOL_CHARSET);
                        synchronized (V8DebugServer.this.requests) {
                            V8DebugServer.this.requests.add(str);
                        }
                        bArr3 = bArr;
                        z = false;
                        i3 = -1;
                    }
                } while (this.from < i4);
                int i5 = this.from;
                if (i5 < i4) {
                    System.arraycopy(bArr2, i5, bArr2, 0, i4 - i5);
                    i2 = i4 - this.from;
                } else {
                    i2 = 0;
                }
            }
        }

        private int readContentLength(byte[] bArr, int i2) throws IOException {
            int length;
            int indexOf;
            int indexOf2 = indexOf(V8DebugServer.PROTOCOL_CONTENT_LENGTH_BYTES, bArr, this.from, i2);
            if (indexOf2 >= 0 && (indexOf = indexOf(V8DebugServer.PROTOCOL_EOL_BYTES, bArr, (length = indexOf2 + V8DebugServer.PROTOCOL_CONTENT_LENGTH_BYTES.length), i2)) >= 0) {
                String str = new String(bArr, length, indexOf - length, V8DebugServer.PROTOCOL_CHARSET);
                try {
                    int parseInt = Integer.parseInt(str.trim());
                    this.from = indexOf + V8DebugServer.PROTOCOL_EOL_BYTES.length;
                    return parseInt;
                } catch (Exception unused) {
                    throw new IOException("Invalid content length header: '" + str + "' in message" + new String(bArr, V8DebugServer.PROTOCOL_CHARSET));
                }
            }
            return -1;
        }

        private boolean skipToolInfo(byte[] bArr, int i2) {
            int indexOf = indexOf(V8DebugServer.PROTOCOL_EOL_BYTES, bArr, this.from, i2);
            if (indexOf < 0) {
                return false;
            }
            this.from = indexOf + V8DebugServer.PROTOCOL_EOL_BYTES.length;
            return true;
        }

        private void startHandshake() throws IOException {
            V8DebugServer.this.sendMessage(V8DebugServer.HEADER_V8_VERSION + V8DebugServer.V8_VERSION + V8DebugServer.PROTOCOL_EOL + V8DebugServer.HEADER_PROTOCOL_VERSION + "1" + V8DebugServer.PROTOCOL_EOL + V8DebugServer.HEADER_EMBEDDING_HOST + "j2v8 " + V8DebugServer.J2V8_VERSION + V8DebugServer.PROTOCOL_EOL + V8DebugServer.HEADER_TYPE + "connect" + V8DebugServer.PROTOCOL_EOL, "");
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Socket accept = V8DebugServer.this.server.accept();
                    accept.setTcpNoDelay(true);
                    synchronized (V8DebugServer.this.clientLock) {
                        V8DebugServer.this.client = accept;
                        V8DebugServer.this.waitForConnection = false;
                        V8DebugServer.this.clientLock.notifyAll();
                    }
                    startHandshake();
                    processClientRequests();
                } catch (Exception e2) {
                    synchronized (V8DebugServer.this.clientLock) {
                        if (V8DebugServer.this.client != null) {
                            try {
                                V8DebugServer.this.client.close();
                            } catch (IOException unused) {
                            }
                            V8DebugServer.this.client = null;
                        }
                        V8DebugServer.this.logError(e2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class EventHandler implements JavaVoidCallback {
        private EventHandler() {
        }

        private void safeRelease(Releasable releasable) {
            if (releasable != null) {
                releasable.release();
            }
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            Releasable releasable;
            int integer;
            V8Object object;
            if (v8Array == null || v8Array.isUndefined()) {
                return;
            }
            V8Object v8Object2 = null;
            try {
                integer = v8Array.getInteger(0);
                object = v8Array.getObject(1);
            } catch (Exception e2) {
                e = e2;
                releasable = null;
            } catch (Throwable th) {
                th = th;
                releasable = null;
            }
            try {
                V8Object object2 = v8Array.getObject(2);
                if (V8DebugServer.this.traceCommunication) {
                    String str = "unknown";
                    switch (integer) {
                        case 1:
                            str = "Break";
                            break;
                        case 2:
                            str = "Exception";
                            break;
                        case 3:
                            str = "NewFunction";
                            break;
                        case 4:
                            str = "BeforeCompile";
                            break;
                        case 5:
                            str = "AfterCompile";
                            break;
                        case 6:
                            str = "CompileError";
                            break;
                        case 7:
                            str = "PromiseEvent";
                            break;
                        case 8:
                            str = "AsyncTaskEvent";
                            break;
                    }
                    System.out.println("V8 has emmitted an event of type " + str);
                }
                if (!V8DebugServer.this.isConnected()) {
                    safeRelease(object);
                    safeRelease(object2);
                    return;
                }
                if (integer == 1) {
                    V8DebugServer.this.enterBreakLoop(object, object2);
                } else if (integer == 5 || integer == 6) {
                    V8DebugServer.this.sendCompileEvent(object2);
                }
                safeRelease(object);
                safeRelease(object2);
            } catch (Exception e3) {
                e = e3;
                releasable = null;
                v8Object2 = object;
                try {
                    V8DebugServer.this.logError(e);
                    safeRelease(v8Object2);
                    safeRelease(releasable);
                } catch (Throwable th2) {
                    th = th2;
                    safeRelease(v8Object2);
                    safeRelease(releasable);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                releasable = null;
                v8Object2 = object;
                safeRelease(v8Object2);
                safeRelease(releasable);
                throw th;
            }
        }
    }

    static {
        Charset forName = Charset.forName("UTF-8");
        PROTOCOL_CHARSET = forName;
        PROTOCOL_EOL_BYTES = PROTOCOL_EOL.getBytes(forName);
        PROTOCOL_CONTENT_LENGTH_BYTES = PROTOCOL_CONTENT_LENGTH_HEADER.getBytes(forName);
    }

    public V8DebugServer(V8 v8, int i2, boolean z) {
        this.runtime = v8;
        this.waitForConnection = z;
        V8Object object = v8.getObject(DEBUG_OBJECT_NAME);
        if (object == null) {
            System.err.println("Cannot initialize debugger server - global debug object not found.");
            return;
        }
        try {
            this.debugObject = object.getObject(V8_DEBUG_OBJECT);
            object.close();
            v8.executeVoidScript("(function() {\n " + DEBUG_OBJECT_NAME + ".Debug. " + MAKE_BREAK_EVENT + " = function (break_id,breakpoints_hit) {\n  return new " + DEBUG_OBJECT_NAME + ".BreakEvent(break_id,breakpoints_hit);\n }\n " + DEBUG_OBJECT_NAME + ".Debug. " + MAKE_COMPILE_EVENT + " = function(script,type) {\n  var scripts = " + DEBUG_OBJECT_NAME + ".Debug.scripts()\n  for (var i in scripts) {\n   if (scripts[i].id == script.id()) {\n     return new " + DEBUG_OBJECT_NAME + ".CompileEvent(scripts[i], type);\n   }\n  }\n  return {toJSONProtocol: function() {return ''}}\n }\n})()");
            try {
                this.server = new ServerSocket(i2);
            } catch (Exception e2) {
                logError(e2);
            }
        } catch (Throwable th) {
            object.close();
            throw th;
        }
    }

    public static void configureV8ForDebugging() {
        try {
            V8.setFlags("-expose-debug-as=" + DEBUG_OBJECT_NAME);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterBreakLoop(V8Object v8Object, V8Object v8Object2) throws IOException {
        V8Object v8Object3;
        try {
            V8Array v8Array = new V8Array(this.runtime);
            v8Array.push(false);
            this.stoppedStateDcp = v8Object.executeObjectFunction("debugCommandProcessor", v8Array);
            v8Array.close();
            int integer = v8Object.getInteger("break_id");
            V8Array array = v8Object2.getArray("break_points_hit_");
            V8Array v8Array2 = new V8Array(this.runtime);
            try {
                v8Array2.push(integer);
                v8Array2.push((V8Value) array);
                v8Object3 = this.debugObject.executeObjectFunction(MAKE_BREAK_EVENT, v8Array2);
            } catch (Throwable th) {
                th = th;
                v8Object3 = null;
            }
            try {
                String executeStringFunction = v8Object3.executeStringFunction("toJSONProtocol", null);
                if (this.traceCommunication) {
                    System.out.println("Sending event (Break):\n" + executeStringFunction);
                }
                sendJson(executeStringFunction);
                v8Array2.close();
                array.close();
                if (v8Object3 != null) {
                    v8Object3.close();
                }
                while (isConnected() && !this.stoppedStateDcp.executeBooleanFunction("isRunning", null)) {
                    try {
                        processRequests(10L);
                    } catch (InterruptedException unused) {
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                v8Array2.close();
                array.close();
                if (v8Object3 != null) {
                    v8Object3.close();
                }
                throw th;
            }
        } finally {
            this.stoppedStateDcp.close();
            this.stoppedStateDcp = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isConnected() {
        boolean z;
        Socket socket;
        synchronized (this.clientLock) {
            z = (this.server == null || (socket = this.client) == null || !socket.isConnected()) ? false : true;
        }
        return z;
    }

    private void processRequest(String str) throws IOException {
        if (this.traceCommunication) {
            System.out.println("Got message: \n" + str.substring(0, Math.min(str.length(), 1000)));
        }
        V8Array v8Array = new V8Array(this.runtime);
        v8Array.push(str);
        V8Object v8Object = this.stoppedStateDcp;
        if (v8Object == null) {
            v8Object = this.runningStateDcp;
        }
        String obj = v8Object.executeFunction("processDebugJSONRequest", v8Array).toString();
        if (this.stoppedStateDcp == null && obj.contains("\"running\":false")) {
            obj = obj.replace("\"running\":false", "\"running\":true").replace("\"success\":true", "\"success\":false").replace("{\"", "{\"message\":\"Client requested suspension is not supported on J2V8.\",\"");
            v8Object.add("running_", true);
        }
        if (this.traceCommunication) {
            System.out.println("Returning response: \n" + obj.substring(0, Math.min(obj.length(), 1000)));
        }
        sendJson(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCompileEvent(V8Object v8Object) throws IOException {
        Throwable th;
        V8Object v8Object2;
        if (isConnected()) {
            int integer = v8Object.getInteger("type_");
            V8Object object = v8Object.getObject("script_");
            V8Array v8Array = new V8Array(this.runtime);
            try {
                v8Array.push((V8Value) object);
                v8Array.push(integer);
                v8Object2 = this.debugObject.executeObjectFunction(MAKE_COMPILE_EVENT, v8Array);
            } catch (Throwable th2) {
                th = th2;
                v8Object2 = null;
            }
            try {
                String executeStringFunction = v8Object2.executeStringFunction("toJSONProtocol", null);
                if (this.traceCommunication) {
                    System.out.println("Sending event (CompileEvent):\n" + executeStringFunction.substring(0, Math.min(executeStringFunction.length(), 1000)));
                }
                if (executeStringFunction.length() > 0) {
                    sendJson(executeStringFunction);
                }
                v8Array.close();
                object.close();
                if (v8Object2 != null) {
                    v8Object2.close();
                }
            } catch (Throwable th3) {
                th = th3;
                v8Array.close();
                object.close();
                if (v8Object2 != null) {
                    v8Object2.close();
                }
                throw th;
            }
        }
    }

    private void sendJson(String str) throws IOException {
        sendMessage("", str.replace("\\/", "/"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(String str, String str2) throws IOException {
        synchronized (this.clientLock) {
            if (isConnected()) {
                Charset charset = PROTOCOL_CHARSET;
                byte[] bytes = str2.getBytes(charset);
                this.client.getOutputStream().write((str + PROTOCOL_CONTENT_LENGTH_HEADER + Integer.toString(bytes.length) + PROTOCOL_EOL + PROTOCOL_EOL).getBytes(charset));
                if (bytes.length > 0) {
                    this.client.getOutputStream().write(bytes);
                }
            } else {
                throw new IOException("There is no connected client.");
            }
        }
    }

    private void setupEventHandler() {
        V8Array v8Array;
        Throwable th;
        V8Function v8Function;
        this.debugObject.registerJavaMethod(new EventHandler(), DEBUG_BREAK_HANDLER);
        try {
            v8Function = (V8Function) this.debugObject.getObject(DEBUG_BREAK_HANDLER);
            try {
                v8Array = new V8Array(this.runtime);
            } catch (Throwable th2) {
                v8Array = null;
                th = th2;
            }
            try {
                v8Array.push((V8Value) v8Function);
                this.debugObject.executeFunction(SET_LISTENER, v8Array);
                if (v8Function != null && !v8Function.isReleased()) {
                    v8Function.close();
                }
                if (v8Array.isReleased()) {
                    return;
                }
                v8Array.close();
            } catch (Throwable th3) {
                th = th3;
                if (v8Function != null && !v8Function.isReleased()) {
                    v8Function.close();
                }
                if (v8Array != null && !v8Array.isReleased()) {
                    v8Array.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            v8Array = null;
            th = th4;
            v8Function = null;
        }
    }

    public int getPort() {
        ServerSocket serverSocket = this.server;
        if (serverSocket == null || !serverSocket.isBound()) {
            return -1;
        }
        return this.server.getLocalPort();
    }

    protected void logError(Throwable th) {
        th.printStackTrace();
    }

    public void processRequests(long j2) throws InterruptedException {
        String[] strArr;
        if (this.server == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            synchronized (this.requests) {
                List<String> list = this.requests;
                strArr = (String[]) list.toArray(new String[list.size()]);
                this.requests.clear();
            }
            for (String str : strArr) {
                try {
                    processRequest(str);
                } catch (Exception e2) {
                    logError(e2);
                }
            }
            if (strArr.length <= 0) {
                if (j2 > 0) {
                    Thread.sleep(10L);
                }
                if (j2 <= 0 || currentTimeMillis + j2 <= System.currentTimeMillis()) {
                    return;
                }
            }
        }
    }

    public void setTraceCommunication(boolean z) {
        this.traceCommunication = z;
    }

    public void start() {
        if (this.server == null) {
            return;
        }
        boolean z = this.waitForConnection;
        Thread thread = new Thread(new ClientLoop(), "J2V8 Debugger Server");
        thread.setDaemon(true);
        thread.start();
        setupEventHandler();
        this.runningStateDcp = this.runtime.executeObjectScript("(function() {return new " + DEBUG_OBJECT_NAME + ".DebugCommandProcessor(null, true)})()");
        if (z) {
            synchronized (this.clientLock) {
                while (this.waitForConnection) {
                    try {
                        this.clientLock.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            try {
                processRequests(100L);
            } catch (InterruptedException unused2) {
            }
        }
    }

    public void stop() {
        try {
            this.server.close();
            synchronized (this.clientLock) {
                Socket socket = this.client;
                if (socket != null) {
                    socket.close();
                    this.client = null;
                }
            }
        } catch (IOException e2) {
            logError(e2);
        }
        V8Object v8Object = this.runningStateDcp;
        if (v8Object != null) {
            v8Object.close();
            this.runningStateDcp = null;
        }
        V8Object v8Object2 = this.debugObject;
        if (v8Object2 != null) {
            v8Object2.close();
            this.debugObject = null;
        }
        V8Object v8Object3 = this.stoppedStateDcp;
        if (v8Object3 != null) {
            v8Object3.close();
            this.stoppedStateDcp = null;
        }
    }
}
