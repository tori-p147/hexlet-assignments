package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    private StringBuffer buff;
    private String state;
    final private String ip;
    final private int port;
    Connection c = new Connected();
    Disconnected d =  new Disconnected();


    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getCurrentState() {
        return this.state;
    }

    public void connect() {
        if (state == null) {
            state = c.getState();
        }
        if (this.state.equals(c.getState())) {
            System.out.println("Try to connect when connection already established. Message must contains word Error");
        } else {
            this.state = c.getState();
        }
    }
    public void disconnect() {
        if (state == null) {
            state = d.getState();
        }
        if (this.state.equals(d.getState())) {
            System.out.println("Try to disconnect when connection disconnected. Message must contains word Error");
        } else {
            this.state = d.getState();
        }
    }

    public void write(String s) {
        if (state.equals("disconnected")) {
            System.out.println("Try to write to disconnected connection. Message must contains word Error");
        }
        if (buff == null) {
            this.buff = new StringBuffer();
            buff.append(s);
        } else {
            buff.append(s);
        }
    }
}
// END
