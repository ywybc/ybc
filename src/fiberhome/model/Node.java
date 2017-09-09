/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.model;

    public class Node {

        //定义链式、环形的Topo，节点的数据结构
        private String name;
        private Node next;
        public void setNext(Node next) {
            this.next = next;
        }

        public void setName(String name) {

            this.name = name;
        }

        public Node getNext() {

            return next;
        }

        public String getName() {

            return name;
        }
    }


