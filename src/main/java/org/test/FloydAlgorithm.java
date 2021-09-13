package org.test;


import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        //创建顶点
         char vertex [] = {'A','B','C','D','E','F','G'};
         //创建邻接矩阵
        int [][] matrix = new int[vertex.length][vertex.length];
        final short N = Short.MAX_VALUE;
        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};
        //创建graph对象
        Graph graph = new Graph(matrix, vertex);
        graph.floyd();
        graph.show();
    }
    static class Graph{
        //存放顶点的数组
        private char [] vertes;
        //存在结点之间距离的邻接矩阵
        private int [][] dis;
        //保存到达目标的中间点
        private int [][] pre;
        public Graph (int [][] matrix,char [] vertex){
            this.vertes = vertex;
            this.dis = matrix;
            this.pre = new int[vertex.length][vertex.length];
            //对中间点数组进行初始化，存放的是中间点的下标
            for (int i = 0; i < vertex.length; i++) {
                Arrays.fill(pre[i],i);
            }
        }
        public void  show(){
            for (int k = 0; k < dis.length; k++) {
                //先将中间点数组输出到一行
                for (int i = 0; i < dis.length; i++) {
                    System.out.print(vertes[pre[k][i]] + " ");
                }
                System.out.println("");
                for (int i = 0; i < dis.length; i++) {
                    System.out.println("<" + vertes[k]+"," + vertes[i] + "> =>"+dis[k][i]);
                }
                System.out.println("");
            }
        }
        //弗洛伊德算法
        void floyd(){
            //保持距离
            int len;
            //对中间顶点遍历
            for (int k = 0; k < dis.length; k++) {
                //从i顶点出发
                for (int i = 0; i < dis.length; i++) {
                    //到达j顶点
                    for (int j = 0; j < dis.length; j++) {
                        //求出从i顶点出发经过k，到达j的距离
                        len = dis[i][k] +dis[k][j];
                        //若len小于dis[i][j],则进行更新
                        if (len < dis[i][j]){
                            //更新距离
                            dis[i][j] = len;
                            //更新中间点
                            pre[i][j] = pre[k][j];
                        }
                    }
                }
            }
        }
    }
}
