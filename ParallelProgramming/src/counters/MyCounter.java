package counters;

public class MyCounter {
    public int value;
    public int n;

    public MyCounter(int n) {
        this.n = n;
        value = 0;
    }

    public void inc() { // can be synchronized, doesn't have to be. There is only one object of
        // MyCounter which gets accessed to by many threads. They each require a lock to
        // it before that.
        value++;
        value = value % n;
    }

    public int val() {
        return value;
    }
}
