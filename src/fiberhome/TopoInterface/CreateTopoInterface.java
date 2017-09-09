/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.TopoInterface;

import fiberhome.TopicService.MeshTopic;
import fiberhome.TopicService.Topic;
import fiberhome.model.Node;
public interface CreateTopoInterface {
    void handle();
    void convertToJson(String topoType, Topic topic, MeshTopic meshTopic);
    void convertToXML(String topoType,Topic topic,MeshTopic meshTopic);
    String coverteToJson(Topic topic);
    MeshTopic meshTopoCreativity(int nodenum, String topoType, String outputType, int traversalStart);
    Topic ringTopoCreativity(Node[] nodes, String topoType, String outputType, int traversalStart);
    Topic chainTopoCreativity(Node[] nodes, String topoType, String outputType, int traversalStart);
}
