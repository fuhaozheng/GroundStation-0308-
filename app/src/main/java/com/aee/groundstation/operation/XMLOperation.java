package com.aee.groundstation.operation;

import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.aee.groundstation.GSApplication;
import com.aee.groundstation.activity.messageOfAirlineandAirpoint.Airpoint;
import com.aee.groundstation.activity.messageOfAirlineandAirpoint.TaskofAirpoint;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by fuhz on 2017/3/1.
 */
public class XMLOperation {
    //根据list保存的信息，生产XML文件，并且将其保存
    public void saveXML(String filePath, String fileName, List<Airpoint> list){
        File linceseFile = makeFilePath(filePath,fileName);
        FileOutputStream fileos = null;
        try{
            fileos = new FileOutputStream(linceseFile);
        }catch(FileNotFoundException e) {
            Log.e("FileNotFoundException", "can't create FileOutputStream");
        }
        XmlSerializer serializer = Xml.newSerializer();
        try {
            serializer.setOutput(fileos,"UTF-8");
            serializer.startDocument(null, true);
            serializer.startTag(null, "Airpoints");
            for(Airpoint airpoint:list){
                serializer.startTag(null, "Airpoint");
                //id
                serializer.startTag(null, "id");
                serializer.text(""+airpoint.getId() );
                serializer.endTag(null, "id");
                //飞行高度
                serializer.startTag(null, "heightOfFlying");
                serializer.text(""+airpoint.getHeightOfFlying());
                serializer.endTag(null, "heightOfFlying");
                //旋转方向
                serializer.startTag(null, "RotationDirection");
                serializer.text(airpoint.getRotationDirection());
                serializer.endTag(null, "RotationDirection");
                //飞行海拔高度
                serializer.startTag(null, "HeightOfAltitudeFly");
                serializer.text(""+airpoint.getHeightOfAltitudeFly());
                serializer.endTag(null, "HeightOfAltitudeFly");
                //飞行地面高度
                serializer.startTag(null, "HeightOfGroundFly");
                serializer.text(""+airpoint.getHeightOfGroundFly());
                serializer.endTag(null, "HeightOfGroundFly");
                //上升速度
                serializer.startTag(null, "SpeedOfUp");
                serializer.text(""+airpoint.getSpeedOfUp());
                serializer.endTag(null, "SpeedOfUp");
                //下降速度
                serializer.startTag(null, "SpeedOfDown");
                serializer.text(""+airpoint.getSpeedOfDown());
                serializer.endTag(null, "SpeedOfDown");
                //飞行速度
                serializer.startTag(null, "SpeedOfFlying");
                serializer.text(""+airpoint.getSpeedOfFlying());
                serializer.endTag(null, "SpeedOfFlying");
                //经度
                serializer.startTag(null, "Longitude");
                serializer.text(""+airpoint.getLatLng().longitude);
                serializer.endTag(null, "Longitude");
                //纬度
                serializer.startTag(null, "Latitude");
                serializer.text(""+airpoint.getLatLng().latitude);
                serializer.endTag(null, "Latitude");
                //偏航角模式
                serializer.startTag(null, "ModeOfYaw");
                serializer.text(""+airpoint.getModeOfYaw());
                serializer.endTag(null, "ModeOfYaw");
                //相机类型
                serializer.startTag(null, "TypeOfCamera");
                serializer.text(""+airpoint.getTypeOfCamera());
                serializer.endTag(null, "TypeOfCamera");
                //相机编号
                serializer.startTag(null, "NumberOfCamera");
                serializer.text(""+airpoint.getNumberOfCamera());
                serializer.endTag(null, "NumberOfCamera");
                //相机偏航角模式
                serializer.startTag(null, "ModeOfCameraYaw");
                serializer.text(""+airpoint.getModeOfCameraYaw());
                serializer.endTag(null, "ModeOfCameraYaw");
                //相机俯仰角度
                serializer.startTag(null, "PitchOfCamera");
                serializer.text(""+airpoint.getPitchOfCamera());
                serializer.endTag(null, "PitchOfCamera");
                //相机横滚角度
                serializer.startTag(null, "RollOfCamera");
                serializer.text(""+airpoint.getPitchOfCamera());
                serializer.endTag(null, "RollOfCamera");


                for(TaskofAirpoint taskofAirpoint:airpoint.getListOfairpointAction1()){
                    serializer.startTag(null, "Task");
                    serializer.startTag(null, "task_id");
                    serializer.text(""+taskofAirpoint.getID());
                    serializer.endTag(null, "task_id");

                    serializer.startTag(null, "task_Sequence");
                    serializer.text(""+taskofAirpoint.getSequence());
                    serializer.endTag(null, "task_Sequence");

                    serializer.startTag(null, "task_Parameter");
                    serializer.text(""+taskofAirpoint.getParameter());
                    serializer.endTag(null, "task_Parameter");

                    serializer.startTag(null, "task_message");
                    serializer.text(taskofAirpoint.getMessage());
                    serializer.endTag(null, "task_message");
                    serializer.endTag(null, "Task");
                }
                serializer.endTag(null, "Airpoint");
            }
            serializer.endTag(null, "Airpoints");
            serializer.endDocument();
            serializer.flush();
            fileos.close();
        } catch (Exception e) {
            Log.e("Exception","error occurred while creating xml file");
        }
        //Toast.makeText(GSApplication.getContext(), "创建xml文件成功!", Toast.LENGTH_SHORT).show();
    }
    //dom解析xml文件
    public String domParseXML(String filePath, String fileName){
        String res = "本结果是通过dom解析:" + "\n";
        File file = new File(filePath,fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = db.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element root = doc.getDocumentElement();
        NodeList airpoints = root.getElementsByTagName("Airpoint");
        for(int i = 0; i < airpoints.getLength();i++){
            Element airpoint = (Element)airpoints.item(i);
            Element id = (Element)airpoint.getElementsByTagName("id").item(0);
            Element heightOfFlying = (Element)airpoint.getElementsByTagName("heightOfFlying").item(0);
            Element rotationDirection = (Element)airpoint.getElementsByTagName("RotationDirection").item(0);
            Element heightOfAltitudeFly = (Element)airpoint.getElementsByTagName("HeightOfAltitudeFly").item(0);
            Element heightOfGroundFly = (Element)airpoint.getElementsByTagName("HeightOfGroundFly").item(0);
            Element speedOfUp = (Element)airpoint.getElementsByTagName("SpeedOfUp").item(0);
            Element speedOfDown = (Element)airpoint.getElementsByTagName("SpeedOfDown").item(0);
            Element speedOfFlying = (Element)airpoint.getElementsByTagName("SpeedOfFlying").item(0);
            Element longitude = (Element)airpoint.getElementsByTagName("Longitude").item(0);
            Element latitude = (Element)airpoint.getElementsByTagName("Latitude").item(0);
            Element modeOfYaw = (Element)airpoint.getElementsByTagName("ModeOfYaw").item(0);
            Element typeOfCamera = (Element)airpoint.getElementsByTagName("TypeOfCamera").item(0);
            Element numberOfCamera = (Element)airpoint.getElementsByTagName("NumberOfCamera").item(0);
            Element modeOfCameraYaw = (Element)airpoint.getElementsByTagName("ModeOfCameraYaw").item(0);
            Element pitchOfCamera = (Element)airpoint.getElementsByTagName("PitchOfCamera").item(0);
            Element rollOfCamera = (Element)airpoint.getElementsByTagName("RollOfCamera").item(0);
            res += "id: " + id.getFirstChild().getNodeValue() + " " +
                    "飞行高度: " + heightOfFlying.getFirstChild().getNodeValue() +
                    "旋转方向: " + rotationDirection.getFirstChild().getNodeValue() +
                    "海拔高度: " + heightOfAltitudeFly.getFirstChild().getNodeValue()  +
                    "地面高度: " + heightOfGroundFly.getFirstChild().getNodeValue()  +
                    "上升速度: " + speedOfUp.getFirstChild().getNodeValue()  +
                    "下降速度: " + speedOfDown.getFirstChild().getNodeValue()  +
                    "飞行速度: " + speedOfFlying.getFirstChild().getNodeValue() + "\n " +
                    "经度: " + longitude.getFirstChild().getNodeValue() +
                    "纬度: " + latitude.getFirstChild().getNodeValue()  +
                    "偏航角模式: " + modeOfYaw.getFirstChild().getNodeValue() +
                    "相机类型: " + typeOfCamera.getFirstChild().getNodeValue() +
                    "相机编号: " + numberOfCamera.getFirstChild().getNodeValue() +
                    "相机偏航角模式: " + modeOfCameraYaw.getFirstChild().getNodeValue() + "\n " +
                    "相机俯仰角度: " + pitchOfCamera.getFirstChild().getNodeValue()  +
                    "相机横滚角度: " + rollOfCamera.getFirstChild().getNodeValue()  ;
            int count=airpoint.getElementsByTagName("Task").getLength();
            for(int j=0;j<count;j++){
                Element task = (Element)airpoint.getElementsByTagName("Task").item(j);
                Element task_id = (Element)task.getElementsByTagName("task_id").item(0);
                Element task_Sequence = (Element)task.getElementsByTagName("task_Sequence").item(0);
                Element task_Parameter = (Element)task.getElementsByTagName("task_Parameter").item(0);
                Element task_message = (Element)task.getElementsByTagName("task_message").item(0);
                res+="任务ID"+task_id.getFirstChild().getNodeValue() +
                        "顺序"+task_Sequence.getFirstChild().getNodeValue() +
                        "参数"+task_Parameter.getFirstChild().getNodeValue() +
                        "信息"+task_message.getFirstChild().getNodeValue()+ "\n";
            }

        }
        return res;
    }
    //xmlPullParser解析xml文件
    public String xmlPullParseXML(String filePath, String fileName){
        String res = "本结果是通过XmlPullParse解析:" + "\n";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            File file=new File(filePath,fileName);
            InputStream inputStream=new FileInputStream(file);
            xmlPullParser.setInput(inputStream,"UTF-8");
            int eventType = xmlPullParser.getEventType();
            try{
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String nodeName = xmlPullParser.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if("id".equals(nodeName)){
                                res += "id: " + xmlPullParser.nextText() + "";
                            }else if("heightOfFlying".equals(nodeName)){
                                res += "飞行高度: " + xmlPullParser.nextText() + "";
                            }else if("RotationDirection".equals(nodeName)){
                                res += "旋转方向: " + xmlPullParser.nextText() + "";
                            }else if("HeightOfAltitudeFly".equals(nodeName)){
                                res += "海拔高度: " + xmlPullParser.nextText() + "";
                            }else if("HeightOfGroundFly".equals(nodeName)){
                                res += "地面高度: " + xmlPullParser.nextText() + "";
                            } else if("SpeedOfUp".equals(nodeName)){
                                res += "上升速度: " + xmlPullParser.nextText() + "";
                            } else if("SpeedOfDown".equals(nodeName)){
                                res += "下降速度: " + xmlPullParser.nextText() + "";
                            } else if("SpeedOfFlying".equals(nodeName)){
                                res += "飞行速度: " + xmlPullParser.nextText() + "\n";
                            } else if("Longitude".equals(nodeName)){
                                res += "经度: " + xmlPullParser.nextText() + "";
                            }else if("Latitude".equals(nodeName)){
                                res += "纬度: " + xmlPullParser.nextText() + "";
                            } else if("ModeOfYaw".equals(nodeName)){
                                res += "偏航角模式: " + xmlPullParser.nextText() + "";
                            } else if("TypeOfCamera".equals(nodeName)){
                                res += "相机类型: " + xmlPullParser.nextText() + "";
                            } else if("NumberOfCamera".equals(nodeName)){
                                res += "相机编号: " + xmlPullParser.nextText() + "";
                            } else if("ModeOfCameraYaw".equals(nodeName)){
                                res += "相机偏航角模式: " + xmlPullParser.nextText() + "";
                            } else if("PitchOfCamera".equals(nodeName)){
                                res += "相机俯仰: " + xmlPullParser.nextText() + "\n";
                            } else if("RollOfCamera".equals(nodeName)){
                                res += "相机横滚: " + xmlPullParser.nextText() + "";
                            } else if("task_id".equals(nodeName)){
                                res += "任务ID: " + xmlPullParser.nextText() + "";
                            } else if("task_Sequence".equals(nodeName)){
                                res += "任务序号: " + xmlPullParser.nextText() + "";
                            }else if("task_Parameter".equals(nodeName)){
                                res += "任务参数: " + xmlPullParser.nextText() + "";
                            } else if("task_message".equals(nodeName)){
                                res += "任务信息: " + xmlPullParser.nextText() + "";
                            }
                            break;
                        default:
                            break;
                    }
                    eventType = xmlPullParser.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    //判断文件夹是否存在，若不存在，生成文件夹
    private void makeRootDirectory(String filePath){
        File file=null;
        try{
            file=new File(filePath);
            if(!file.exists()){
                file.mkdir();
            }
        }catch (Exception e){
            Log.i("付浩征",e+"");
        }
    }
    //判断文件是否存在，若不存在，生成文件
    private File makeFilePath(String filePath,String fileName){
        File file=null;
        makeRootDirectory(filePath);
        try{
            file=new File(filePath+fileName);
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (Exception e){
            Log.i("付浩征",e+"");
        }
        return file;
    }

}

