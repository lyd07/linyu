package org.test;

import java.util.Arrays;
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final short N = Short.MAX_VALUE;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建Graph对象
        Graph graph = new Graph(vertex,matrix);
        graph.dijkstra(2);
        graph.showDijkstra();
    }
}

class Graph {
    //顶点数组
    private char[] vertex;
    //邻接矩阵
    private int[][] matrix;
    //已经访问的顶点的集合
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    /**
     * 显示结果
     */
    public void showDijkstra() {
        vv.showResult(vertex);
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
     *
     * @param index 顶点下标
     */
    private void update(int index) {
        int len = 0;
        //根据遍历我们的邻接矩阵的 matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len :出发顶点到index顶点的距离 + 从index顶点到i顶点的距离
            len = vv.getDis(index) + matrix[index][i];
            //如果j顶点没有被访问过,并且len小于出发顶点到j顶点的距离则更新
            if (!vv.indexAlreadyArr(i) && len < vv.getDis(i)) {
                //更新i顶点的前驱节点为index顶点
                vv.updatePre(i, index);
                //更新出发顶点到i顶点的距离
                vv.updateDis(i, len);
            }
        }
    }

    /**
     * 迪杰斯特拉算法实现
     *
     * @param index 表示出发顶点对应的下标
     */
    public void dijkstra(int index) {
        //创建已经访问的顶点的集合
        vv = new VisitedVertex(vertex.length, index);
        //更新index顶点到周围顶点的距离和前驱顶点
        update(index);

        for (int i = 0; i < vertex.length; i++) {
            //选择并返回新的访问节点
            index = vv.updateArr();
            //更新index顶点到周围顶点的距离和前驱顶点
            update(index);
        }
    }

}


/**
 * 已访问顶点集合
 */
class VisitedVertex {
    /**
     * 记录各个顶点是否访问过,未访问:0,已访问:1
     */
    public int[] alreadyArr;
    /**
     * 每个下标的值为前一个顶点的下标
     */
    public int[] preVisited;
    /**
     * 记录出发顶点到其他所有顶点的距离
     */
    public int[] dis;

    /**
     * 构造器
     *
     * @param length 顶点的个数
     * @param index  出发顶点对应的下标
     */
    public VisitedVertex(int length, int index) {
        //结点访问
        this.alreadyArr = new int[length];
        //前驱节点
        this.preVisited = new int[length];
        //访问距离
        this.dis = new int[length];
        //初始化 dis 数组
        Arrays.fill(dis, Short.MAX_VALUE);
        //设置出发顶点被访问过
        this.alreadyArr[index] = 1;
        //设置出发顶点的访问距离为0
        this.dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     *
     * @param index 顶点下标
     * @return 访问过返回true, 否则返回false
     */
    public boolean indexAlreadyArr(int index) {
        return alreadyArr[index] == 1;
    }


    /**
     * 修改出发顶点到index顶点的距离
     *
     * @param index 顶点下标
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 修改pre这个顶点的前驱顶点为index顶点
     *
     * @param pre   前驱顶点下标
     * @param index 待更新顶点下标
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 获取出发顶点到index顶点的距离
     *
     * @param index 顶点下标
     * @return 距离
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 选择并返回新的访问顶点
     *
     * @return 新的访问顶点
     */
    public int updateArr() {
        int min = Short.MAX_VALUE, index = 0;
        //获取最小距离的顶点
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        alreadyArr[index] = 1;
        return index;
    }

    /**
     * 显示是否顶点访问情况
     */
    public void showAlreadyArr() {
        System.out.println("-----------AlreadyArr-----------");
        for (int i : alreadyArr) {
            System.out.print(i + "  ");
        }

        System.out.println("-----------AlreadyArr-----------");
    }

    /**
     * 显示结果
     *
     * @param vertex
     */
    public void showResult(char[] vertex) {
        System.out.println("-----------Result-----------");
        int count = 0;
        for (int i : dis) {
            if (i != Short.MAX_VALUE) {
                System.out.println(vertex[count] + " -> " + i);
            } else {
                System.out.println(" N ");
            }
            count++;
        }
        System.out.println("-----------Result-----------");
    }

}

