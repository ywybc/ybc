/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.control;
import fiberhome.TopoCreativity.CreateTopo;
import fiberhome.service.PropertyFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class FileOutControl {
    //@Autowired
    private CreateTopo topo ;
    private int nodeNum;
    private String topoType;
    private String outputType;
    private int traversalStart ;
    @RequestMapping("/fileOutput")
    public String fileOUT(HttpServletRequest request, HttpServletResponse response){
        nodeNum =Integer.parseInt(request.getParameter("nodeNum")) ;
        topoType =request.getParameter("topyType") ;
        outputType =request.getParameter("outputType").toUpperCase() ;
        if(request.getParameter("traversalStart") != null)
        traversalStart =Integer.parseInt(request.getParameter("traversalStart")) ;
         //往SEEION里注入属性
        System.out.println(nodeNum);
        System.out.println(topoType);
        System.out.println(outputType);
        HttpSession session = request.getSession();
        session.setAttribute("nodeNum",nodeNum);
        session.setAttribute("topyType",topoType);
        session.setAttribute("outputType",outputType);
        session.setAttribute("traversalStart",traversalStart);

        PropertyFile configOutput = new PropertyFile(nodeNum,topoType,outputType,traversalStart);
        configOutput.makeFile();
        topo=new CreateTopo();
        topo.handle();
        return "success";
    }
}
