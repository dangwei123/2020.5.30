构建乘积数组
import java.util.*;
public class Solution {
    public int[] multiply(int[] A) {
        int[] res=new int[A.length];
        if(A.length<=1) return res;
        Arrays.fill(res,1);
        int left=A[0];
        for(int i=1;i<A.length;i++){
            res[i]=left;
            left*=A[i];
        }
        int right=A[A.length-1];
        for(int i=A.length-2;i>=0;i--){
            res[i]*=right;
            right*=A[i];
        }
        return res;
    }
}


请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 public class Solution {
    public boolean match(char[] str, char[] pattern){
        int m=str.length;
        int n=pattern.length; 
        boolean[][] dp=new boolean[m+1][n+1]; 
        dp[0][0]=true;
        for(int i=2;i<=n;i++){
            dp[0][i]=pattern[i-1]=='*'&&dp[0][i-2];
        }
        
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(pattern[j-1]=='*'){
                    dp[i][j]=(isMatched(str,pattern,i-1,j-2)&&dp[i-1][j])||
                             dp[i][j-2];
                }else{
                    dp[i][j]=isMatched(str,pattern,i-1,j-1)&&dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }
    
    private boolean isMatched(char[] str,char[] pat,int i,int j){
        return str[i]==pat[j]||pat[j]=='.';
    }
}


请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
import java.util.*;
public class Solution {
    //Insert one char from stringstream
    Set<Character> set=new HashSet<>();
    LinkedList<Character> list=new LinkedList<>();
    private int size;
    public void Insert(char ch)
    {
        if(set.contains(ch)){
            if(list.remove(Character.valueOf(ch)))
                 size--;
        }else{
            set.add(ch);
            list.addLast(ch);
            size++;
        }
    }
  //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
       if(size==0) return '#';
        return list.getFirst();
    }
}

给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if(pNode.right!=null){
            TreeLinkNode cur=pNode.right;
            while(cur.left!=null){
                cur=cur.left;
            }
            return cur;
        } 

        if(pNode.next!=null&&pNode.next.left==pNode) return pNode.next;
        
        if(pNode.next!=null){
            TreeLinkNode parent=pNode.next;
            while(parent.next!=null&&parent.next.right==parent){
                parent=parent.next;
            }
            return parent.next;
        }
        return null;
    }
}


请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
public class Solution {
    boolean isSymmetrical(TreeNode pRoot)
    {
        return isSame(pRoot,pRoot);
    }
    private boolean isSame(TreeNode r1,TreeNode r2){
        if(r1==null&&r2==null) return true;
        if(r1==null||r2==null) return false;
        if(r1.val!=r2.val) return false;
        return isSame(r1.left,r2.right)&&isSame(r1.right,r2.left);
    }
}

请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
public class Solution {
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
        if(pRoot==null) return res;
        Deque<TreeNode> dq=new LinkedList<>();
        dq.offer(pRoot);
        boolean left=true;
        while(!dq.isEmpty()){
            ArrayList<Integer> row=new ArrayList<>();
            if(left){
                int size=dq.size();
                while(size--!=0){
                    TreeNode cur=dq.poll();
                    row.add(cur.val);
                    if(cur.left!=null) dq.offerLast(cur.left);
                    if(cur.right!=null) dq.offerLast(cur.right);
                }
            }else{
                int size=dq.size();
                while(size--!=0){
                    TreeNode cur=dq.pollLast();
                    row.add(cur.val);
                    if(cur.right!=null) dq.offerFirst(cur.right);
                    if(cur.left!=null) dq.offerFirst(cur.left);
                }
            }
            res.add(row);
            left=!left;
        }
        return res;
    }

}


从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
import java.util.*;
public class Solution {
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
        if(pRoot==null) return res;
        Queue<TreeNode> dq=new LinkedList<>();
        dq.offer(pRoot);
        while(!dq.isEmpty()){
            ArrayList<Integer> row=new ArrayList<>();
                int size=dq.size();
                while(size--!=0){
                    TreeNode cur=dq.poll();
                    row.add(cur.val);
                    if(cur.left!=null) dq.offer(cur.left);
                    if(cur.right!=null) dq.offer(cur.right);
                }
            res.add(row);
        }
      return res;
}
}

请实现两个函数，分别用来序列化和反序列化二叉树

二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。

二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
import java.util.*;
public class Solution {
    String Serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        ser(root,sb);
        return new String(sb);
  }
    private void ser(TreeNode root,StringBuilder sb){
        if(root==null){
            sb.append("null,");
            return;
        }
        sb.append(root.val).append(",");
        ser(root.left,sb);
        ser(root.right,sb);
    }
    TreeNode Deserialize(String str) {
        String[] arr=str.split(",");
        LinkedList<String> list=new LinkedList<>();
        for(String s:arr){
            list.add(s);
        }
        return der(list);
  }
    private TreeNode der(LinkedList<String> list){
        if(list.getFirst().equals("null")){
            list.removeFirst();
            return null;
        }
        TreeNode root=new TreeNode(Integer.parseInt(list.removeFirst()));
        root.left=der(list);
        root.right=der(list);
        return root;
    }
}


给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
public class Solution {
    TreeNode res;
    int num;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        num=k;
        inorder(pRoot);
        return res;
    }
    private void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        if(--num==0){
            res=root;
            return;
        }
        inorder(root.right);
    }

}

