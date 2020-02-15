package by.shakhrai.pavel.entity;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Element {
    private int value;
    private Lock lock = new ReentrantLock();

    public Element(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Lock getLock() {
        return lock;
    }

    public boolean changeElement(int value) {
        if (lock.tryLock()) {
            this.value = value;
            return true;
        }
        return false;
    }

    public void unlock() {
        lock.unlock();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return value == element.value;
    }

    @Override
    public int hashCode() {
        return value * 26 / 2;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                '}';
    }
}
