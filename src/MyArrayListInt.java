public class MyArrayListInt {
    private final int DEFAULT_CAPACITY = 10;

    private int[] data;
    private int sz;

    public MyArrayListInt() {
        data = new int[DEFAULT_CAPACITY];
    }

    public void add(int v) {
        ensureCapacity();
        data[sz] = v;
        ++sz;
    }

    public void add(int index, int v) {
        checkAdd(index);

        ensureCapacity();

        for (int i = sz; i > index; --i) {
            data[i] = data[i - 1];
        }

        data[index] = v;

        ++sz;
    }

    public void remove(int index) {
        check(index);

        for (int i = index; i + 1 < sz; ++i) {
            data[i] = data[i + 1];
        }

        --sz;
    }

    public int size() {
        return sz;
    }

    int get(int index) {
        check(index);
        return data[index];
    }

    void set(int index, int v) {
        check(index);
        data[index] = v;
    }


    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        r.append("[");
        for (int i = 0; i < sz; ++i) {
            if (i != 0) {
                r.append(", ");
            }
            r.append(data[i]);
        }
        r.append("]");

        return r.toString();
    }

    private void checkAdd(int index) {
        if (index < 0 || sz < index) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
    }

    private void check(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
    }

    private void ensureCapacity() {
        if (sz == data.length) {
            int[] buff = new int[data.length + data.length / 2];
            for (int i = 0; i < data.length; ++i) {
                buff[i] = data[i];
            }
            data = buff;
        }
    }
}