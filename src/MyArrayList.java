import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E> {

    public static final int INITIAL_CAPACITY = 10;
    private E[] data;
    private int size = 0;

    public MyArrayList() {
        this(INITIAL_CAPACITY);
    }

    public MyArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public void add(int index, E o) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + " size: " + size);
        }
        ensureCapacity();

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = o;
        size++;
    }

    private void ensureCapacity() {
        if (data.length <= size) {
            E[] buff = (E[]) (new Object[data.length * 2 + 1]);
            System.arraycopy(data, 0, buff, 0, size);
            data = buff;
        }
    }

    public E get(int index) {
        checkIndex(index);
        return data[index]; // O(1)
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    @Override
    public int indexOf(E o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1; // O(n)
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E e = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return e;
    }

    @Override
    public E set(int index, E o) {
        checkIndex(index);
        E temp = data[index];
        data[index] = o;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        E e = (E)o;
        int index = indexOf(e);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    public int lastIndexOf(E e) {
        for (int index = size - 1; index >= 0; index--) {
            if (e.equals(data[index])) {
                return index;
            }
        }
        return -1;
    }

    public void trimToSize() {
        if (size != data.length) {
            E[] buff = (E[]) (new Object[size]);
            System.arraycopy(data, 0, buff, 0, size);
            data = buff;
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        E[] a = (E[]) c.toArray();

        for (E e : a) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // home work
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // home work
        return false;
    }

    @Override
    public void clear() {
        data = (E[])(new Object[INITIAL_CAPACITY]);
        size = 0;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            return data[current++];
        }
    }

}
