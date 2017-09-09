/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.Handle;

import fiberhome.model.Node;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Traversal {
    //定义广度遍历
    private String breadth;
    //定义深度遍历
    private String depth;
    //定义开始节点
    private String start;

    public Traversal() {
        super();
    }

    public void setBreadth(String breadth) {
        this.breadth = breadth;
    }

    public void setDepth(String depth) {

        this.depth = depth;
    }

    public void setStart(String start) {

        this.start = start;
    }
    @XmlElement(name = "breadth")
    public String getBreadth() {

        return breadth;
    }
    @XmlElement(name="depth")
    public String getDepth() {

        return depth;
    }
    @XmlElement(name = "start")
    public String getStart() {

        return start;
    }

    //Chain深度搜索
   public String depthSearchChain(Node[] nodes, int traversalStart){
        List<String> list=new ArrayList<>();
        int count=1;
        //定义输出
        String output="";
        if(nodes.length==0||traversalStart>nodes.length){
            return null;
        }
        //起始遍历的序号为数组长度
//        if (traversalStart==nodes.length){
//            output=nodes[nodes.length-1].name;
//        }
        for (int i = 0; i <nodes.length ; i++) {
            if(nodes[i].getNext()!=null&&count!=traversalStart){
                count++;
                continue;
            }
            list.add(nodes[i].getName());
        }
        if (!list.isEmpty()) {
            for (int i=0;i<list.size()-1;i++) {
                output+=list.get(i)+"->";
            }
            output+=list.get(list.size()-1);
        }
        return output;
    }
    //Ring深度搜索
   public String depthSearchRing(Node[] nodes,int traversalStart){
        List<String> list=new ArrayList<>();
        int count=1;
        //定义输出
        String output="";
        if(nodes.length==0||traversalStart>nodes.length){
            return null;
        }
        //起始遍历的序号为数组长度
//        if (traversalStart==nodes.length){
//            output=nodes[nodes.length-1].name;
//        }
        for (int i = traversalStart-1; i <nodes.length+traversalStart-1 ; i++) {
            if(nodes[i%nodes.length].getNext()!=nodes[i%nodes.length]){
                list.add(nodes[i%nodes.length].getName());
            }
        }
        list.add(nodes[(nodes.length+traversalStart-1)%nodes.length].getName());
        if (!list.isEmpty()) {
            for (int i=0;i<list.size()-1;i++) {
                output+=list.get(i)+"->";
            }
            output+=list.get(list.size()-1);
        }
        return output;
    }

    //Chain的广度搜索
    public String breadthSearchChain(Node[] nodes,int traversalStart) {
        String output = "";
        int nodeNum = nodes.length;
        //构造邻接关系
        int[][] near = new int[nodeNum][nodeNum];
        for (int i = 0; i < nodeNum; i++) {
            for (int j = 0; j < nodeNum; j++) {
                if (nodes[i % nodeNum].getNext() == nodes[(j) % nodeNum]) {
                    near[i][j] = 1;
                } else
                    near[i][j] = 0;
            }
        }
        //遍历标记
        boolean[] isvisited = new boolean[nodeNum];
        for (boolean b : isvisited) {
            b = false;
        }
        //存储遍历结果
        List<String> nodeList = new ArrayList<>();
        //u代表队列的头结点，u的第一个邻接点为w
        int u, w;
        //存储访问过的节点
        LinkedList<Integer> visitedNode = new LinkedList<>();
        nodeList.add(nodes[traversalStart - 1].getName());
        isvisited[traversalStart - 1] = true;
        //初始节点的访问
        visitedNode.addLast(traversalStart - 1);
        while (!visitedNode.isEmpty()) {
            u = visitedNode.removeFirst();
            w = getFirstNeighbour(near, u);
            while (w != -1) {
                if (!isvisited[w]) {
                    nodeList.add(nodes[w].getName());
                    isvisited[w] = true;
                    visitedNode.addLast(w);
                }
                w = getNextNeighbour(near, u, w);
            }
        }

        if (!nodeList.isEmpty()) {
            for (int i = 0; i < nodeList.size() - 1; i++) {
                output += nodeList.get(i) + "->";
            }
            output += nodeList.get(nodeList.size() - 1);
        }
        return output;
    }
    //Ring的广度搜索
   public String breadthSearchRing(Node[] nodes,int traversalStart){
        String output="";
        int nodeNum=nodes.length;
        //构造邻接关系
        int[][] near=new int[nodeNum][nodeNum];
        for (int i = 0; i <nodeNum ; i++) {
            for (int j = 0; j < nodeNum; j++) {
                if (nodes[i%nodeNum].getNext()==nodes[(j)%nodeNum]) {
                    near[i][j] = 1;
                }else
                    near[i][j]=0;
            }
        }
        near[nodeNum-1][0]=1;
        //遍历标记
        boolean[] isvisited=new boolean[nodeNum];
        for (boolean b:isvisited) {
            b=false;
        }
        //存储遍历结果
        List<String> nodeList=new ArrayList<>();
        //u代表队列的头结点，u的第一个邻接点为w
        int u,w;
        //存储访问过的节点
        LinkedList<Integer> visitedNode=new LinkedList<>();
        nodeList.add(nodes[traversalStart-1].getName());
        isvisited[traversalStart-1]=true;
        //初始节点的访问
        visitedNode.addLast(traversalStart-1);
        while (!visitedNode.isEmpty()){
            u=visitedNode.removeFirst();
            w=getFirstNeighbour(near,u);
            while (w!=-1){
                if (!isvisited[w]){
                    nodeList.add(nodes[w].getName());
                    isvisited[w]=true;
                    visitedNode.addLast(w);
                }
                w=getNextNeighbour(near,u,w);
            }
        }

        if (!nodeList.isEmpty()) {
            for (int i=0;i<nodeList.size();i++) {
                output+=nodeList.get(i)+"->";
            }
            output+=nodeList.get(0);
        }
        return output;
    }

    private int getNextNeighbour(int[][] near,int u, int w) {
        for (int i = w+1; i <near.length ; i++) {
            if (near[u][i]>0){
                return i;
            }
        }
        return -1;
    }

    private int getFirstNeighbour(int[][] near, int u) {
        for (int i = 0; i < near.length; i++) {
            if (near[u][i]>0){
                return  i;
            }
        }
        return -1;
    }

}


