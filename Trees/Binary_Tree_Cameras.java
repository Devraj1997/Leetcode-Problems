/*
https://leetcode.com/problems/binary-tree-cameras/description/

To minimize the number of required cameras, it is advisable for parents of all leaf nodes to have cameras. 
The most efficient approach involves solving the leaf nodes first and then computing upwards to solve 
the non-leaf nodes.
*/
class Binary_Tree_Cameras {
    int count = 0;
    public int minCameraCover(TreeNode root) {
        int val = compute(root);
        if(val==-1) count+=1;
        return count;
    }

    private int compute(TreeNode root) {
        if(root.left == null && root.right == null) {
            return -1;
        }
        int left = 0, right = 0, status = -1;
        if(root.left!=null) left = compute(root.left);
        if(root.right!=null) right = compute(root.right);

        if(left==-1 || right==-1) {
            status = 1;
            count+=1;
        } else if(left==1 || right==1) {
            status = 0;
        }
        return status;
    }
}