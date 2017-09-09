/*
 * Fiberhome SDN Platform
 * Copyright (c)  Fiberhome Technologies.
 * 88,YouKeYuan Road,Hongshan District.,Wuhan,P.R.China,
 * Wuhan Research Institute of Post &amp; Telecommunication.
 * All rights reserved.
 */
package fiberhome.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class PropertyFile {
    private int nodeNum;
    private String topyType;
    private String outputType;
    private int traversalStart ;
    public PropertyFile(int nodeNum, String topoType, String outputType, int traversalStart) {
        this.nodeNum = nodeNum;
        this.topyType = topoType;
        this.outputType = outputType;
        this.traversalStart = traversalStart;
    }

    public static final String FILE_PATH = "D://config.properties";
    public  void makeFile() {
        Properties prop = new Properties();
        FileOutputStream configFile =null;
        File file = new File(FILE_PATH);
        if(file.exists()){
            file.delete();
        }
        try {
            //true表示追加打开
            configFile = new FileOutputStream( FILE_PATH, true);
            //设置node个数
            prop.setProperty("nodeNum", nodeNum + "");
            //设置拓扑类型
            prop.setProperty("topyType", topyType);
            //设置输出类型格式
            prop.setProperty("outputType", outputType);
            //设置遍历起点序号（可选）
            prop.setProperty("traversalStart", traversalStart + "");
            prop.store(configFile,"this is configure file");
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(configFile !=null) {
                try {
                    configFile.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


