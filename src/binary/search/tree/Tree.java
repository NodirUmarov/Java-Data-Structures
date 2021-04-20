package binary.search.tree;

import java.util.Collection;

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



}
