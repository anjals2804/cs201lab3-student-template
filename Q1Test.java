public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     static boolean isValidTree(int[] in, int[] pre, int[] post, int inorderStart, int inorderEnd, int preorderStart, int preorderEnd, int postorderStart, int postorderEnd) {
          if (inorderStart > inorderEnd)
               return preorderStart > preorderEnd && postorderStart > postorderEnd;
          if (preorderStart > preorderEnd || postorderStart> postorderEnd)
               return false;
          if (pre[preorderStart] != post[postorderEnd])
               return false;
          int i = inorderStart;
          while (i <= inorderEnd && in[i] != pre[preorderStart])
               i++;
          if (i > inorderEnd)
               return false;
          int left = i - inorderStart;

          return isValidTree(in, pre, post, inorderStart, i - 1, preorderStart + 1, preorderStart + left, postorderStart, postorderStart + left - 1) && isValidTree(in, pre, post, i + 1, inorderEnd, preorderStart + left + 1, preorderEnd, postorderStart + left, postorderEnd - 1);
     }

     public static String verify(String t1, String t2, String t3) {
          String[] s = {t1, t2, t3};
          int[][] a = new int[3][];
          for (int i = 0; i < 3; i++) {
               String[] parts = s[i].split("-");
               a[i] = new int[parts.length];
               for (int j = 0; j < parts.length; j++) {
                    a[i][j] = Integer.parseInt(parts[j]);
               }
          }

          for (int i = 0; i < 3; i++) {
               for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                         if (i == j || i == k || j == k)
                         continue;

                         if (check(a[i], a[j], a[k])) {
                              String[] type = new String[3];
                              type[i] = "Inorder";
                              type[j] = "Preorder";
                              type[k] = "Postorder";

                              return "Traversal 1 - " + type[0] + ", Traversal 2 - " + type[1] + ", Traversal 3 - " + type[2];
                         }
                    }
               }
          }

          return "Invalid traversals";
     }

     static boolean check(int[] in, int[] pre, int[] post) {
          if (in.length != pre.length || in.length != post.length)
               return false;

          for (int i = 1; i < in.length; i++) {
               if (in[i] <= in[i - 1]) {
                    return false;
               }
          }

          return isValidTree(in, pre, post, 0, in.length - 1, 0, pre.length - 1, 0, post.length - 1);
     }
}