package counters;

public class SynchronizedCounter implements Counter {

    public int value = 0;

    @Override
    public void increment() {

        synchronized (this) {
            System.out.println(Thread.currentThread().getName()); // commented out for
            // task F. Uncomment if trying out task E.
            value++;
        }
    }

    @Override
    public int value() {
        synchronized (this) {
            return value;
        }
    }

}
