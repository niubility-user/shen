package com.eclipsesource.v8.inspector;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class V8Inspector {
    private long inspectorPtr;
    private V8 runtime;
    private boolean waitingForConnection = true;
    private List<DebuggerConnectionListener> debuggerConnectionListeners = new ArrayList();

    protected V8Inspector(V8 v8, V8InspectorDelegate v8InspectorDelegate, String str) {
        this.inspectorPtr = 0L;
        this.runtime = v8;
        this.inspectorPtr = v8.createInspector(v8InspectorDelegate, str);
    }

    public static V8Inspector createV8Inspector(V8 v8, V8InspectorDelegate v8InspectorDelegate, String str) {
        return new V8Inspector(v8, v8InspectorDelegate, str);
    }

    private void verifyDebuggerConnection(String str) {
        V8Object v8Object = null;
        try {
            v8Object = this.runtime.executeObjectScript("JSON.parse(JSON.stringify(" + str + "))");
            if (v8Object.getString("method").equals("Runtime.runIfWaitingForDebugger")) {
                this.waitingForConnection = false;
                this.runtime.schedulePauseOnNextStatement(this.inspectorPtr, "");
                Iterator<DebuggerConnectionListener> it = this.debuggerConnectionListeners.iterator();
                while (it.hasNext()) {
                    it.next().onDebuggerConnected();
                }
            }
        } finally {
            if (v8Object != null) {
                v8Object.close();
            }
        }
    }

    public void addDebuggerConnectionListener(DebuggerConnectionListener debuggerConnectionListener) {
        this.debuggerConnectionListeners.add(debuggerConnectionListener);
    }

    public void dispatchProtocolMessage(String str) {
        try {
            this.runtime.dispatchProtocolMessage(this.inspectorPtr, str);
            if (this.waitingForConnection) {
                verifyDebuggerConnection(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void removeDebuggerConnectionListener(DebuggerConnectionListener debuggerConnectionListener) {
        this.debuggerConnectionListeners.remove(debuggerConnectionListener);
    }

    public static V8Inspector createV8Inspector(V8 v8, V8InspectorDelegate v8InspectorDelegate) {
        return new V8Inspector(v8, v8InspectorDelegate, null);
    }
}
