package binary.search.tree;

import java.util.Iterator;

public class BST<E extends Comparable<E>> implements Tree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    public BST() {

    }

    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    @Override
    public boolean search(E element) {
        TreeNode<E> current = root;

        while (current != null) {
            if (element.compareTo(current.element) > 0) {
                current = current.rightChild;
                break;
            }
            else if (element.compareTo(current.element) < 0) {
                current = current.leftChild;
            }
            else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insert(E element) {

        if (root == null) {
            root = createNewNode(element);
        }
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                if (element.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.rightChild;
                }
                else if (element.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.leftChild;
                }
                else {
                    return false;
                }
            }

            if (element.compareTo(parent.element) > 0) {
                parent.rightChild = createNewNode(element);
            }
            else {
                parent.leftChild = createNewNode(element);
            }
        }

        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E element) {
        return new TreeNode<>(element);
    }

    @Override
    public boolean delete(E element) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null) {
            if (element.compareTo(current.element) > 0) {
                parent = current;
                current = current.rightChild;
            }
            else if (element.compareTo(current.element) < 0) {
                parent = current;
                current = current.leftChild;
            }
            else {
                break;
            }

            if (current == null) {
                return false;
            }
            else if (current.leftChild == null) {
                if (parent == null) {
                    root = current.rightChild;
                }
                else {
                    if (element.compareTo(parent.element) < 0) {
                        parent.leftChild = current.rightChild;
                    }
                    else {
                        parent.rightChild = current.rightChild;
                    }
                }
            }

        }

        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void postorder() {

    }

    @Override
    public void preorder() {

    }

    @Override
    public void inorder() {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public void clear() {

    }
}
