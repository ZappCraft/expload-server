package me.digi.expload;

public abstract class ExploadServer {

    protected int port;
    protected ExploadDataHandler dataHandler;

    /**
     * Expload server
     * @param port Port of the server
     * @param dataHandler Data handler
     */
    public ExploadServer(int port, ExploadDataHandler dataHandler) {
        this.port = port;
        this.dataHandler = dataHandler;
    }

    public abstract void createAndBindServer();
    public abstract void closeServer();

}
