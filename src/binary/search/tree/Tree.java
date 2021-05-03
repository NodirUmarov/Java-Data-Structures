package binary.search.tree;

import java.util.Collection;
import java.util.Iterator;

public interface Tree<E> extends Collection<E> {

    public boolean search(E element);

    public boolean insert(E element);

    public boolean delete(E element);

    public int getSize();

    public void postorder();

    public void preorder();

    public void inorder();

    public default boolean isEmpty() {
        return size() == 0;
    }

    public default boolean contains(Object element) {
        return search((E) element);
    }

    public default boolean add(E element) {
        return insert(element);
    }

    public default boolean remove(Object element) {
        return delete((E) element);
    }

    public default int size() {
        return getSize();
    }

    public default boolean containsAll(Collection<?> collection) {
        for (E e : this) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    public default boolean addAll(Collection<? extends E> collection) {
        int length = size();
        for (E e : this) {
            add(e);
        }
        return length != size();
    }

    public default boolean removeAll(Collection<?> collection) {
        int length = size();
        for (E e : this) {
            if (contains(e)) {
                remove(e);
            }
        }
        return length != size();
    }

    public default boolean retainAll(Collection<?> collection) {
        int length = size();
        for (E e : this) {
            if (!contains(e)) {
                remove(e);
            }
        }
        return length != size();
    }

    public default Object[] toArray() {
        Object[] elements = new Object[size()];
        int index = 0;

        for (E e : this) {
            elements[index++] = e;
        }
        return elements;
    }
}
