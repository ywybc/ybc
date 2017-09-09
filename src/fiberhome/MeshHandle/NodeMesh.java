/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.MeshHandle;
import fiberhome.model.Link;

import java.util.ArrayList;
import java.util.LinkedList;

public class NodeMesh {
    private ArrayList vertexList;//存储点的链表
    private int[][] edges;//邻接矩阵，用来存储边
    private int numOfEdges;//边的数目
    private boolean[] isVisited;
    // private Link[] links;
    public NodeMesh(int n) {
        //初始化矩阵，一维数组，和边的数目
        edges=new int[n][n];
        vertexList=new ArrayList(n);
        numOfEdges=0;
    }

    //得到结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i的数据
    public Object getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1,v2的权值
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(Object vertex) {
        vertexList.add(vertexList.size(),vertex);
    }

    //插入结点
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2]=weight;
        numOfEdges++;
    }

    //删除结点
    public void deleteEdge(int v1,int v2) {
        edges[v1][v2]=0;
        numOfEdges--;
    }

    //得到第一个邻接结点的下标
    public int getFirstNeighbor(int index) {
        for(int j=0;j<vertexList.size();j++) {
            if (edges[index][j]>0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来取得下一个邻接结点，此处v1、V2表示邻节点
    public int getNextNeighbor(int v1,int v2) {
        for (int j=v2+1;j<vertexList.size();j++) {
            if (edges[v1][j]>0) {
                return j;
            }
        }
        return -1;
    }
    //获得图中的链路
    public ArrayList<Link> getLinks(){
//            int endge= n*(n-1)/2;
//            Link[] links=new Link[endge];
        ArrayList<Link> links=new ArrayList<>();
        int count=0;
        for (int i = 0; i <edges.length ; i++) {
            for (int j = i; j <edges[i].length ; j++) {
                if(getWeight(i,j)!=0){
                    Link link=new Link();
                    link.setSource((String) getValueByIndex(i));
                    link.setTarget((String)getValueByIndex(j));
                    links.add(link);
                }
            }
        }
        return links;
    }

    //私有函数，深度优先遍历
    private String depthFirstSearch(boolean[] isVisited,int i,ArrayList<String> visitedList) {
        //首先访问该结点，在控制台打印出来
        //System.out.print( getValueByIndex(i)+"  ");
        //定义输出结果
        String output="";
        //将遍历的节点存储在List中
        // List<String> visitedList=new ArrayList<>();
        visitedList.add((String) getValueByIndex(i));
        //置该结点为已访问
        isVisited[i]=true;

        int w=getFirstNeighbor(i);
        while (w!=-1) {
            if (!isVisited[w]) {
                depthFirstSearch(isVisited,w,visitedList);
            }
            w=getNextNeighbor(i, w);
        }
        //输出深度遍历结果
        if(!visitedList.isEmpty()){
            for (int j = 0; j <visitedList.size()-1 ; j++) {
                output+=visitedList.get(j)+"->";
            }
            output+=visitedList.get(visitedList.size()-1);
        }
        return output;
    }

    //考虑非连通和连通的情况
    public String depthFirstSearch(int traversalStart) {
        //将遍历的节点存储在List中
        ArrayList<String> visitedList=new ArrayList<>();
        //定义输出
        String output="";
        isVisited=new boolean[getNumOfVertex()];
        //所有节点初始访问值设置成false
        for (boolean aBoolean:isVisited) {
            aBoolean=false;
        }
//            for(int i=0;i<getNumOfVertex();i++) {
//                //因为对于非连通图来说，并不是通过一个结点就一定可以遍历所有结点的。
//                if (!isVisited[i]) {
//                    depthFirstSearch(isVisited,i,visitedList);
//                }
//          }
        output+=depthFirstSearch(isVisited,traversalStart-1,visitedList);
        return output;
    }

    //私有函数，广度优先遍历
    private String broadFirstSearch(boolean[] isVisited,int i, ArrayList<String> arrayList) {
        //u表示队列中的头结点
        int u,w;
        LinkedList<Integer> queue=new LinkedList<>();
        //访问结点i
        //System.out.print(getValueByIndex(i)+"  ");
        //将遍历的节点存储在List中
        //ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add((String)getValueByIndex(i));
        //定义输出
        String output="";
        isVisited[i]=true;
        //结点入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u=queue.removeFirst();
            w=getFirstNeighbor(u);
            while(w!=-1) {
                if(!isVisited[w]) {
                    //访问该结点
                    //System.out.print(getValueByIndex(w)+"  ");
                    arrayList.add((String) getValueByIndex(w));
                    //标记已被访问
                    isVisited[w]=true;
                    //入队列
                    queue.addLast(w);
                }
                //寻找下一个邻接结点
                w=getNextNeighbor(u, w);
            }
        }
        //输出深度遍历结果
        if(!arrayList.isEmpty()){
            for (int j = 0; j <arrayList.size()-1 ; j++) {
                output+=arrayList.get(j)+"->";
            }
            output+=arrayList.get(arrayList.size()-1);
        }
        return output;

    }

    //对外公开函数，广度优先遍历
    public String broadFirstSearch(int traversalStart) {
//            for(int i=0;i<getNumOfVertex();i++) {
//                if(!isVisited[i]) {
//                    broadFirstSearch(isVisited, i);
//                }
//            }
        //将遍历的节点存储在List中
        ArrayList<String> visitedList=new ArrayList<>();
        //定义输出
        String output="";
        //还原visited
        isVisited=new boolean[getNumOfVertex()];
        //所有节点初始访问值设置成false
        for (boolean aBoolean:isVisited) {
            aBoolean=false;
        }
//            for(int i=0;i<getNumOfVertex();i++) {
//                //因为对于非连通图来说，并不是通过一个结点就一定可以遍历所有结点的。
//                if (!isVisited[i]) {
//                    depthFirstSearch(isVisited,i,visitedList);
//                }
//          }
        output+=depthFirstSearch(isVisited,traversalStart-1,visitedList);
        return output;
    }
}



