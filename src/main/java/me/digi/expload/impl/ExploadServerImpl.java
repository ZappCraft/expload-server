package me.digi.expload.impl;

import me.digi.expload.ExploadDataHandler;
import me.digi.expload.ExploadServer;
import ru.sonicxd2.socket.server.SocketServer;
import java.io.IOException;

public class ExploadServerImpl extends ExploadServer {

    protected Thread thread;

    public ExploadServerImpl(int port, ExploadDataHandler dataHandler) {
        super(port, dataHandler);
    }

    @Override
    public void createAndBindServer() {
        if(thread != null) {
            return;
        }
        SocketServer server = new SocketServer(port, (t, u, v) -> dataHandler.handle(new ExploadDataImpl(t, u ,v)));
        thread = new Thread(() -> {
            try {
                server.bind();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    @Override
    public void closeServer() {
        if(thread != null) thread.interrupt();
        thread = null;
    }

}
