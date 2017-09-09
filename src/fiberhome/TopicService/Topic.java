/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.TopicService;
import fiberhome.Handle.Traversal;
import fiberhome.model.Link;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="topic")
public class Topic {
    //定义返回值
    private String[] nodes;
    private List<Link> links;
    private Traversal traversals;

    public Topic() {
        super();
    }

    public void setNodes(String[] nodes) {
        this.nodes = nodes;
    }

    @XmlElementWrapper(name="nodes")
    @javax.xml.bind.annotation.XmlElement(name="node")
    public String[] getNodes() {

        return nodes;
    }

    public void setTraversals(Traversal traversals) {
        this.traversals = traversals;
    }


    @javax.xml.bind.annotation.XmlElement(name = "traversals")
    //@XmlElement(name="traversal")
    public Traversal getTraversals() {

        return traversals;
    }
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @XmlElementWrapper(name="links")
    @javax.xml.bind.annotation.XmlElement(name="link")
    public List<Link> getLinks() {
        return links;
    }

}


