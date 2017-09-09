/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.model;

import javax.xml.bind.annotation.XmlElement;

public class Link {

    private String source;
    private String target;

    public Link() {
        super();
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setSource(String source) {

        this.source = source;
    }
    @XmlElement(name = "target")
    public String getTarget() {

        return target;
    }
    @XmlElement(name = "source")
    public String getSource() {

        return source;
    }

}
