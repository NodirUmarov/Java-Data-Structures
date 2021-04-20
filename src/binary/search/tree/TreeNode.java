package binary.search.tree;

public class TreeNode<E> {

    protected E element;
    protected TreeNode<E> leftChild;
    protected TreeNode<E> rightChild;

    public TreeNode(E element) {
        this.element = element;
    }
}
