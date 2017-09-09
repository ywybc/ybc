/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.MeshHandle;

    public class MeshCreativity {
        //定义节点数
        private int nodeNum;
        //定义边数，完全Mesh中，edgeNum可以根据nodeNum计算出来
        private int edgeNum;

        public MeshCreativity(int nodeNum) {
            this.nodeNum = nodeNum;
        }

        public void setEdgeNum(int edgeNum) {
            this.edgeNum = edgeNum;
        }

        public void setNodeNum(int nodeNum) {

            this.nodeNum = nodeNum;
        }

        public int getEdgeNum() {

            return (nodeNum * (nodeNum - 1)) / 2;
        }

        public int getNodeNum() {

            return nodeNum;
        }

        //定义node
       public String[] creativityNodes(int nodeNum) {
            String[] nodes = new String[nodeNum];
            int count = 1;
            for(int i = 0;i<nodes.length;i++){
                nodes[i]="node"+count;
                count++;
            }
            return nodes;
        }
        //创建MESH
       public NodeMesh creativityMesh(int nodeNum){
            //边的数目
            int edgeNum=(nodeNum*(nodeNum-1))/2;
            //创建节点
            String[] nodes=creativityNodes(nodeNum);
            NodeMesh mesh=new NodeMesh(nodeNum);
            for (String string:nodes) {
                mesh.insertVertex(string);
            }
            //创建边，建立邻接表
            for (int i = 0; i < nodeNum; i++) {
                for (int j = 0; j <nodeNum ; j++) {
                    if(i!=j){
                        mesh.insertEdge(i,j,1);
                    }else
                        mesh.insertEdge(i,j,0);
                }
            }
            return mesh;
        }

    }


