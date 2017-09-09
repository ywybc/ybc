/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.TopoCreativity;

import fiberhome.Handle.Traversal;
import fiberhome.MeshHandle.MeshCreativity;
import fiberhome.MeshHandle.NodeMesh;
import fiberhome.TopicService.MeshTopic;
import fiberhome.TopicService.Topic;
import fiberhome.TopoInterface.CreateTopoInterface;
import fiberhome.model.Link;
import fiberhome.model.Node;
import fiberhome.service.PropertyFile;
import net.sf.json.JSONArray;
import javax.servlet.http.HttpServlet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CreateTopo implements CreateTopoInterface {
    public  void handle(){
        //读取配置文件
        try {
            Properties properties=new Properties();
            properties.load(new FileInputStream(new File(PropertyFile.FILE_PATH)));
            //定义节点数目
            int nodeNum=Integer.valueOf(properties.getProperty("nodeNum"));
            //定义节点的Topo类型
            String topoType=properties.getProperty("topyType");
            //定义输出格式
            String outputType=properties.getProperty("outputType");
            //遍历的起始序号（后续实现）
            int traversalStart=Integer.valueOf(properties.getProperty("traversalStart"));
            //节点初始化
            Node[] nodes=new Node[nodeNum];
            int count=1;
            for (int i = 0; i <nodes.length; i++) {
                nodes[i]=new Node();
                nodes[i].setName("node"+count);
                nodes[i].setNext(null);
                count++;
            }
            //定义返回值
            Topic topic=new Topic();
            //Mesh拓扑
            MeshTopic meshTopic=new MeshTopic();
            //不同拓扑类型对应的处理过程
            switch (topoType){
                case "Chain":
                    topic=chainTopoCreativity(nodes,topoType,outputType,traversalStart);break;
                case "Ring":
                    topic=ringTopoCreativity(nodes,topoType,outputType,traversalStart);break;
                case "Mesh":
                    meshTopic=meshTopoCreativity(nodeNum,topoType,outputType,traversalStart);break;
                default:break;
            }
            if (outputType.equals("JSON")) {
                //将Topic对象转化为JSON对象
                convertToJson(topoType, topic, meshTopic);
            }else if (outputType.equals("XML")) {
                //将java对象转为XML
                convertToXML(topoType, topic, meshTopic);
            }
        } catch (IOException e) {
            System.out.println("文件不存在！");
        }
    }

   public void convertToJson(String topoType, Topic topic, MeshTopic meshTopic) {
        File file=new File("D://zuoye//web//json.txt");
        if(file.exists()){
            file.delete();
        }
        BufferedWriter writers=null;
        if(topoType.equals("Chain")) {
            String jsonOutput = coverteToJson(topic);
            try {
                writers=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                writers.write(jsonOutput);
                writers.close();
                System.out.println(jsonOutput);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(topoType.equals("Ring")){
            //有环路不能直接转化为JSON串
            String jsonOutput = coverteToJson(topic);
            try {
                writers=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                writers.write(jsonOutput);
                writers.close();
                System.out.println(jsonOutput);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(topoType.equals("Mesh")){
            if(topic!=null) {
                JSONArray jsonObject = JSONArray.fromObject(meshTopic);
                try {
                    writers=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    writers.write(jsonObject.toString());
                    writers.close();
                    System.out.println(jsonObject.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   public void convertToXML(String topoType,Topic topic,MeshTopic meshTopic) {
        try {
            JAXBContext context;
            if (topoType.equals("Mesh")){
                //Mesh拓扑
                context=JAXBContext.newInstance(MeshTopic.class);
            }else {
                //链式和环形topo
                context = JAXBContext.newInstance(Topic.class);
            }
            Marshaller marshaller=context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING,"GBK");
            StringWriter writer=new StringWriter();
            if (topoType.equals("Mesh")){
                //Mesh拓扑
                marshaller.marshal(meshTopic,writer);
            }else {
                //链式和环形topo
                marshaller.marshal(topic,writer);
            }

            String str=writer.toString();
            //写入的XML文件路径
            String xmlPath="D://zuoye//web//result.xml";
            File filexml=new File(xmlPath);
            if(filexml.exists()){
                filexml.delete();
            }
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(xmlPath));
            bufferedWriter.write(str);
            bufferedWriter.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String coverteToJson(Topic topic) {
        if(topic!=null) {
            JSONArray jsonObject = JSONArray.fromObject(topic);
            return jsonObject.toString();
        }
        return "";
    }

   public MeshTopic meshTopoCreativity(int nodenum, String topoType, String outputType, int traversalStart) {
        if (nodenum== 0||topoType==null||outputType==null||traversalStart>nodenum||traversalStart<=0) {
            return null;
        }
        //定义起始节点
        String start=null;
        //定义深度搜索和广度搜索
        String depth=null;
        String breadth=null;
        //定义链路
        List<Link> links=new ArrayList<>();
        //深搜和广搜结果
        Traversal traversals=new Traversal();
        //定义返回值
        MeshTopic topic=new MeshTopic();

        MeshCreativity meshCreativity=new MeshCreativity(nodenum);
        //获取节点
        String[] nodes=meshCreativity.creativityNodes(nodenum);
        topic.setNodes(nodes);
        if (nodenum==1){
            start=nodes[0];
            breadth=nodes[0];
            depth=nodes[0];
            traversals.setStart(start);
            traversals.setDepth(depth);
            traversals.setBreadth(breadth);
            topic.setLinks(null);
            topic.setTraversals(traversals);
            return topic;
        }
        //建立Mesh拓扑
        NodeMesh nodeMesh=meshCreativity.creativityMesh(nodenum);
        //遍历链路
        links=nodeMesh.getLinks();
        topic.setLinks(links);
        start="node"+traversalStart;
        depth=nodeMesh.depthFirstSearch(traversalStart);
        breadth=nodeMesh.broadFirstSearch(traversalStart);
        traversals.setStart(start);
        traversals.setDepth(depth);
        traversals.setBreadth(breadth);
        topic.setTraversals(traversals);
        return  topic;
    }
    //实现环形Topo结构
   public Topic ringTopoCreativity(Node[] nodes, String topoType, String outputType, int traversalStart) {
        int nodeLength=nodes.length;
        if (nodeLength== 0||topoType==null||outputType==null||traversalStart>nodes.length||traversalStart<=0) {
            return null;
        }
        //定义起始节点
        String start=null;
        //定义深度搜索和广度搜索
        String depth=null;
        String breadth=null;
        //定义链路
        List<Link> links=new ArrayList<>();
        //深搜和广搜结果
        Traversal traversals=new Traversal();
        //定义返回值
        Topic topic=new Topic();
        //输出节点
        String[] crNodes=new String[nodeLength];
        for (int i = 0; i <nodeLength ; i++) {
            crNodes[i]=nodes[i].getName();
        }
        topic.setNodes(crNodes);
        //处理只有一个节点的情况
        if (nodeLength==1){
            start=nodes[0].getName();
            breadth=nodes[0].getName();
            depth=nodes[0].getName();
            traversals.setStart(start);
            traversals.setDepth(depth);
            traversals.setBreadth(breadth);
            topic.setLinks(null);
            topic.setTraversals(traversals);
            return topic;
        }

        //建立环形topo模型
        for (int i = 0; i < nodes.length-1; i++) {
            nodes[i].setNext(nodes[i+1]);
        }
        nodes[nodeLength-1].setNext(nodes[0]);
        //遍历链路
        for (int i = traversalStart - 1; i < nodes.length +traversalStart-1; i++) {
            Link link=new Link();
            link.setSource(nodes[(i%(nodes.length))].getName());
            link.setTarget(nodes[(i + 1)%(nodes.length)].getName());
            links.add(link);
        }
        topic.setLinks(links);
        //深度遍历和广度遍历
        start="node"+traversalStart;
        depth=traversals.depthSearchRing(nodes,traversalStart);
        //广度
        breadth=traversals.breadthSearchRing(nodes,traversalStart);
        traversals.setStart(start);
        traversals.setDepth(depth);
        traversals.setBreadth(breadth);
        // traversals[0]=new Traversal(nodes,traversalStart);
        topic.setTraversals(traversals);
        return topic;
    }

    //实现链型结构的Topo
    public Topic chainTopoCreativity(Node[] nodes, String topoType, String outputType, int traversalStart) {
        int nodeNum=nodes.length;
        if (nodeNum == 0||topoType==null||outputType==null||traversalStart>nodes.length||traversalStart<=0) {
            return null;
        }
        //定义起始节点
        String start=null;
        //定义深度搜索和广度搜索
        String depth=null;
        String breadth=null;
        //定义链路
        List links=new ArrayList<Link>();
        //深搜和广搜结果
        Traversal traversals=new Traversal();
        //定义返回值
        Topic topic=new Topic();
        //输出节点
        String[] crNodes=new String[nodeNum];
        for (int i = 0; i <crNodes.length ; i++) {
            crNodes[i]=nodes[i].getName();
        }
        topic.setNodes(crNodes);
        //处理只有一个节点的情况
        if (nodeNum==1){
            start=nodes[0].getName();
            breadth=nodes[0].getName();
            depth=nodes[0].getName();
            traversals.setStart(start);
            traversals.setDepth(depth);
            traversals.setBreadth(breadth);
            topic.setLinks(null);
            topic.setTraversals(traversals);
            return topic;
        }
        //建立链式topo模型
        for (int i = 0; i < nodes.length-1; i++) {
            nodes[i].setNext(nodes[i+1]);
        }
        //遍历链路
        //当遍历的起始序号为数组长度的时候
        if (traversalStart==nodeNum){
            topic.setLinks(null);
            start=nodes[nodeNum-1].getName();
            breadth=nodes[nodeNum-1].getName();
            depth=nodes[nodeNum-1].getName();
            traversals.setStart(start);
            traversals.setDepth(depth);
            traversals.setBreadth(breadth);
            topic.setLinks(null);
            topic.setTraversals(traversals);
            return topic;
        }else {
            for (int i = traversalStart - 1; i < nodes.length - 1; i++) {
                Link link=new Link();
                link.setSource(nodes[i].getName());
                link.setTarget(nodes[i + 1].getName());
                links.add(link);
            }
            topic.setLinks(links);
            //深度遍历和广度遍历
            start="node"+traversalStart;
            depth=traversals.depthSearchChain(nodes,traversalStart);
            //广度
            breadth=traversals.breadthSearchChain(nodes,traversalStart);
            traversals.setStart(start);
            traversals.setDepth(depth);
            traversals.setBreadth(breadth);
            topic.setTraversals(traversals);
            return topic;
        }
    }
}


