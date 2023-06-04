package exercise.connections;

// BEGIN
public class Disconnected implements Connection {

    @Override
    public String getState() {
        return "disconnected";
    }
}
// END
