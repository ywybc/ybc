/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.model;

import fiberhome.Handle.Traversal;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(propOrder = {
        "nodes",
        "links",
        "traversals"
})

@XmlRootElement(name = "topo")
public class Topo {
   private  List<String> nodes;

   private  List<Link> links;

   private  List<Traversal> traversals;
   @XmlAttribute
   private  String xmlns = "http://www.fiberhome.com.cn/demo/topo" ;

    public List<Link> getLinks() {
        return links;
    }
    @XmlElementWrapper(name = "nodes")
    @XmlElement(name = "node")
    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
    public List<String> getNodes() {
        return nodes;
    }

    @XmlElementWrapper(name = "links")
    @XmlElement(name = "link")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Traversal> getTraversals() {
        return traversals;
    }
    @XmlElementWrapper(name = "traversals")
    @XmlElement(name = "traversal")
    public void setTraversals(List<Traversal> traversals) {
        this.traversals = traversals;
    }

}
