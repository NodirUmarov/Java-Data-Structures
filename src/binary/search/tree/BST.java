package binary.search.tree;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> extends AbstractCollection<E> implements Tree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    public BST() {}

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
            if (current.leftChild == null) {
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
            else {
                TreeNode<E> parentOfRightMost = current;
                TreeNode<E> rightMost = current.leftChild;

                while (rightMost.rightChild != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.rightChild;
                }
            }

        }

        size--;
        return true;
    }

    public ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;

        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.leftChild;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.rightChild;
            }
            else {
                break;
            }
        }
        return list;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    public void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.leftChild);
        postorder(root.rightChild);
        System.out.println(root.element + " ");
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    public void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.element + " ");
        preorder(root.leftChild);
        preorder(root.rightChild);

    }

    @Override
    public void inorder() {
        inorder(root);
    }

    public void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.leftChild);
        System.out.println(root.element + " ");
        inorder(root.rightChild);
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    @Override
    public int size() {
        return getSize();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    private class InorderIterator implements Iterator {

        private ArrayList<E> iterableList = new ArrayList<>();
        private int current = 0;

        public InorderIterator() {
            inorder();
        }

        private void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.leftChild);
            iterableList.add(root.element);
            inorder(root.rightChild);
        }

        @Override
        public boolean hasNext() {
            return current < iterableList.size();
        }

        @Override
        public Object next() {
            return iterableList.get(current++);
        }

        @Override
        public void remove() {
            if (current == 0) {
                throw new IllegalStateException();
            }
            delete(iterableList.get(--current)) ;
            iterableList.clear();
            inorder();
        }
    }
}
