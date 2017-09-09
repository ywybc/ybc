/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */package fiberhome.TopicService;
import fiberhome.Handle.Traversal;
import fiberhome.model.Link;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
//定义Mesh的Topic的返回值
@XmlRootElement(name="topic")
public class MeshTopic {
    private String[] nodes;
    private List<Link> links;
    private Traversal traversals;
    public MeshTopic() {
        super();
    }

    public void setTraversals(Traversal traversals) {
        this.traversals = traversals;
    }

    public void setLinks(List<Link> links) {

        this.links = links;
    }

    public void setNodes(String[] nodes) {

        this.nodes = nodes;
    }
    @XmlElement(name="traversals")
    public Traversal getTraversals() {

        return traversals;
    }
    @XmlElementWrapper(name="links")
    @XmlElement(name="link")
    public List<Link> getLinks() {

        return links;
    }
    @XmlElementWrapper(name="nodes")
    @XmlElement(name="node")
    public String[] getNodes() {
        return nodes;
    }
}

