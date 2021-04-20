import java.util.ArrayList;
import java.util.Collections;

public class Heap<E extends Comparable<E>> {

    private ArrayList<E> list = new ArrayList<E>();

    public Heap() {

    }

    public Heap (E[] objecs) {
        for (int i = 0; i < objecs.length; i++) {
            add(objecs[i]);
        }
    }

    public void add(E element) {
        list.add(element);

        int currentIndex = list.size() - 1;

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;

            if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
//                E temp = list.get(currentIndex);
//                list.set(currentIndex, list.get(parentIndex));
//                list.set(parentIndex, temp);
                Collections.swap(list, parentIndex, currentIndex);
            }
            else {
                break;
            }

            currentIndex = parentIndex;

        }
    }

    public E remove() {
        if (list.size() == 0) {
            return null;
        }

        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChild = 2 * currentIndex + 1;
            int rightChild = 2 * currentIndex + 2;

            if (leftChild >= list.size()) {
                break;
            }

            int maxIndex = leftChild;

            if (rightChild < list.size()) {
                if (list.get(maxIndex).compareTo(list.get(rightChild)) < 0) {
                    maxIndex = rightChild;
                }
            }

            if (list.get(currentIndex).compareTo(list.get(maxIndex))  < 0) {
                Collections.swap(list, currentIndex, maxIndex);
                currentIndex = maxIndex;
            }
            else {
                break;
            }
        }
        return removedObject;
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
