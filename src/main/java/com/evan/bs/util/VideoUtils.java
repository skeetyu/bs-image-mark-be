package com.evan.bs.util;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class VideoUtils {
    public static void fetchFrames(String videoUrl, String[] imgUrls) throws IOException{
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoUrl);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        frameGrabber.start();
        int length = frameGrabber.getLengthInFrames();
        int i = 0;
        int idx = 0;
        int partition = length / (imgUrls.length + 1);
        Frame frame = null;
        while(i < length && idx < imgUrls.length){
            frame = frameGrabber.grabFrame();
            if(i > idx * partition && i > 10 && frame.image != null){
                BufferedImage bufferedImage = converter.convert(frame);
                File targetFile = new File(imgUrls[idx++]);
                if(!targetFile.isDirectory()){
                    targetFile.mkdirs();
                }
                ImageIO.write(bufferedImage, "jpg", targetFile);
            }
            i ++;
        }

        frameGrabber.stop();
        frameGrabber.close();
        converter.close();
    }

    public static void fetchFrame(String videoUrl, String imgUrl)throws Exception{
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoUrl);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        frameGrabber.start();
        //解码长度
        int length = frameGrabber.getLengthInFrames();
        //时间
        int i = 0;
        Frame frame = null;
        while(i < length){
            //过滤前五帧，避免出现全黑的图片
            frame = frameGrabber.grabFrame();
            if(i > 10 && (frame.image != null)){
                break;
            }
            i ++;
        }
        // Frame frame = frameGrabber.grabFrame();
        BufferedImage bufferedImage = converter.convert(frame);
        //照片保存文件夹
        File targetFile = new File(imgUrl);
        //文件夹不存在就新建
        if(!targetFile.isDirectory()){
            targetFile.mkdirs();
        }
        //写入文件夹,以jpg图片格式
        System.out.println(imgUrl);
        ImageIO.write(bufferedImage, "jpg", targetFile);
        System.out.println("yeye");
        //停止转化为帧
        frameGrabber.stop();
        frameGrabber.close();
        converter.close();
    }

    public static void fetchALLFrame(String videoUrl,String imgUrl)throws Exception{
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoUrl);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        frameGrabber.start();
        //解码长度
        int length = frameGrabber.getLengthInFrames();
        //时间
        int i = 0;
        Frame frame = null;
        while(i < length){
            //过滤前五帧，避免出现全黑的图片
            frame = frameGrabber.grabFrame();
            if((frame.image != null)){
                // Frame frame = frameGrabber.grabFrame();
                BufferedImage bufferedImage = converter.convert(frame);
                //照片保存文件夹
                File targetFile = new File(imgUrl+i+".jpg");
                //文件夹不存在就新建
                if(!targetFile.isDirectory()){
                    targetFile.mkdirs();
                }
                //写入文件夹,以jpg图片格式
                ImageIO.write(bufferedImage, "jpg", targetFile);
            }
            i ++;
        }

        //停止转化为帧
        frameGrabber.stop();
        frameGrabber.close();
        converter.close();
    }

}


