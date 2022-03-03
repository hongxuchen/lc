
package e;

public class InvertTree {

    private final int val;
    private InvertTree left;
    private InvertTree right;

    public InvertTree(int val, InvertTree left, InvertTree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void inOrder(InvertTree root, StringBuffer sb) {
        if (root.left != null) {
            inOrder(root.left, sb);
        }
        sb.append(" ").append(root.val);
        if (root.right != null) {
            inOrder(root.right, sb);
        }
    }

    public static void preOrder(InvertTree root, StringBuffer sb) {
        sb.append(" ").append(root.val);
        if (root.left != null) {
            preOrder(root.left, sb);
        }
        if (root.right != null) {
            preOrder(root.right, sb);
        }
    }

    public static String postOrder(InvertTree root, StringBuffer sb) {
        if (root.left != null) {
            postOrder(root.left, sb);
        }
        if (root.right != null) {
            postOrder(root.right, sb);
        }
        sb.append(" ").append(root.val);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        preOrder(this, sb);
        return sb.toString();
    }

    public static InvertTree ofLeaf(int val) {
        return new InvertTree(val, null, null);
    }

    public static InvertTree treeGenerator() {
        InvertTree four = InvertTree.ofLeaf(4);
        InvertTree five = InvertTree.ofLeaf(5);
        InvertTree two = new InvertTree(2, four, five);
        InvertTree six = InvertTree.ofLeaf(6);
        InvertTree seven = InvertTree.ofLeaf(7);
        InvertTree three = new InvertTree(3, six, seven);
        InvertTree one = new InvertTree(1, two, three);
        return one;
    }

    public static InvertTree invert(InvertTree root) {
        if (root == null) {
            return null;
        }

        InvertTree left = invert(root.left);
        InvertTree right = invert(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    public static void main(String[] args) {
        InvertTree root = treeGenerator();
        System.out.println("before: " + root);
        invert(root);
        System.out.println("after: " + root);
    }

}
