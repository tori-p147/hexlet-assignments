package exercise;

import java.util.ArrayList;

class SafetyList extends ArrayList {
    // BEGIN
    public int getSize() {
        return super.size();
    }

    @Override
    public Object get(int index) {
        return super.get(index);
    }

    @Override
    public synchronized boolean add(Object o) {
        return super.add(o);
    }
    // END
}
