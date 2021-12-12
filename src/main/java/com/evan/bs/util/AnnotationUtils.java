package com.evan.bs.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
// import java.io.Writer;
// import java.util.ArrayList;
// import java.util.List;

import com.evan.bs.model.ModelExportMark;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class AnnotationUtils {
    public static boolean writeXML(String notation, ModelExportMark exportMark){
        boolean flag = true;
        String path = "D:/Coding/bs-image-mark-be/annotations/pascal voc/" + exportMark.getPath(); 
        XMLWriter writer = null;
        try{
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("annotation");
            Element empFolder = root.addElement("folder");
            empFolder.addText("pascal voc");
            Element empFilename = root.addElement("filename");
            empFilename.addText(exportMark.getPath());
            Element empPath = root.addElement("path");
            empPath.addText(path);
            Element empSource = root.addElement("source");
            Element empDatabase = empSource.addElement("database");
            empDatabase.addText("Unknown");
            Element empSize = root.addElement("size");
            Element empWidth = empSize.addElement("width");
            empWidth.addText(exportMark.getWidth());
            Element empHeight = empSize.addElement("height");
            empHeight.addText(exportMark.getHeight());
            Element empDepth = empSize.addElement("depth");
            empDepth.addText("3");

            Element empSegmented = root.addElement("segmented");
            empSegmented.addText("0");
            Element empObject = root.addElement("object");
            String[] annotation = notation.split(",");
            for(int i = 0; i < annotation.length; i = i + 5){
                Element empName = empObject.addElement("name");
                empName.addText(annotation[i+4]);
                Element empPose = empObject.addElement("pose");
                empPose.addText("Unspecified");
                Element empTruncated = empObject.addElement("truncated");
                empTruncated.addText("0");
                Element empDifficult = empObject.addElement("difficult");
                empDifficult.addText("0");
                Element empBndbox = empObject.addElement("bndbox");
                Element empXmin = empBndbox.addElement("xmin");
                empXmin.addText(annotation[i]);
                Element empYmin = empBndbox.addElement("ymin");
                empYmin.addText(annotation[i+1]);
                Element empXmax = empBndbox.addElement("xmax");
                empXmax.addText(annotation[i+2]);
                Element empYmax = empBndbox.addElement("ymax");
                empYmax.addText(annotation[i+3]);
            }

            FileOutputStream fos = new FileOutputStream("D:/Coding/bs-image-mark-be/annotations/pascal voc/" + exportMark.getGraph() + ".xml");
            writer = new XMLWriter(fos, OutputFormat.createPrettyPrint());
            writer.write(document);
            System.out.println("导出完毕");
        }catch(Exception e){
            e.printStackTrace();
            flag = false;
        }finally{
            if(writer != null){
                try{
                    writer.close();
                }catch(IOException e){
                    e.printStackTrace();
                    flag = false;
                }
            }
        }
        return flag;
    }

    public static boolean writeCOCO(String notation, ModelExportMark exportMark){
        boolean flag = true;
        String filename = "D:/Coding/bs-image-mark-be/annotations/coco/" + exportMark.getGraph() + ".txt";
        File file = new File(filename);
        try{
            FileWriter out = new FileWriter(file);
            String line = System.getProperty("line.separator");
            String[] annotation = notation.split(",");
            out.write("/* 由于没有参考COCO数据集的80个分类，这里直接显示用户标注时输入的分类 */");
            out.write(line);
            for(int i = 0; i < annotation.length; i += 5){
                out.write(annotation[i+4]);
                for(int j = 0; j < 4; j++){
                    out.write(" ");
                    out.write(annotation[i+j]);
                }
                out.write(line);
            }
            out.close();
        }catch(IOException e){
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}

