import java.util.Collection;
import java.util.Iterator;

public interface MyList<E> extends Collection<E> {

    public void add(int index, E o);

    public int indexOf(E o);

    public E remove(int index);

    public E set(int index, E o);

    public E get(int index);

    @Override
    Iterator<E> iterator();

    public default boolean isEmpty() {
        return size() == 0;
    }

}
