package com.zc.commonutilslib;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MLog {
    private static Boolean MY_LOG_SWITCH = true; // 日志文件总开关
    private static Boolean MY_LOG_WRITE_TO_FILE = true;// 日志写入文件开关
    private static char MY_LOG_TYPE = 'v';// 输入日志类型，w代表只输出告警信息等，v代表输出所有信息
    private static String MY_LOG_PATH_SDCARD_DIR = "/sdcard/my_log";// 日志文件在sdcard中的路径
    private static int SDCARD_LOG_FILE_SAVE_DAYS = 0;// sd卡中日志文件的最多保存天数
    private static String MYLOGFILEName = "_Log.txt";// 本类输出的日志文件名称
    private static SimpleDateFormat myLogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 日志的输出格式
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式
    public Context context;
    private static MLog mLog;

    public static MLog getInstance(){
        if(mLog == null){
            mLog = new MLog();
        }
        return mLog;
    }

    private MLog(){}
    /**
     * @param fileName 生成的文件名，可以自定义
     */
    public static void initFileName(String fileName){
        MYLOGFILEName = fileName;
    }

    public static void w(String tag, Object msg) { // 警告信息
        log(tag, msg.toString(), 'w');
    }

    public static void e(String tag, Object msg) { // 错误信息
        log(tag, msg.toString(), 'e');
    }

    public static void d(String tag, Object msg) {// 调试信息
        log(tag, msg.toString(), 'd');
    }

    public static void i(String tag, Object msg) {//
        log(tag, msg.toString(), 'i');
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), 'v');
    }

    public static void w(String tag, String text) {
        log(tag, text, 'w');
    }

    public static void e(String tag, String text) {
        log(tag, text, 'e');
    }

    public static void d(String tag, String text) {
        log(tag, text, 'd');
    }

    public static void i(String tag, String text) {
        log(tag, text, 'i');
    }

    public static void v(String tag, String text) {
        log(tag, text, 'v');
    }

    /**
     * 根据tag, msg和等级，输出日志
     * @param tag
     * @param msg
     * @param level
     */
    private static void log(String tag, String msg, char level) {
        if (MY_LOG_SWITCH) {//日志文件总开关
            if ('e' == level && ('e' == MY_LOG_TYPE || 'v' == MY_LOG_TYPE)) { // 输出错误信息
               Log.i(tag, msg);
            } else if ('w' == level && ('w' == MY_LOG_TYPE || 'v' == MY_LOG_TYPE)) {
               Log.i(tag, msg);
            } else if ('d' == level && ('d' == MY_LOG_TYPE || 'v' == MY_LOG_TYPE)) {
               Log.i(tag, msg);
            } else if ('i' == level && ('d' == MY_LOG_TYPE || 'v' == MY_LOG_TYPE)) {
               Log.i(tag, msg);
            } else {
               Log.i(tag, msg);
            }
            if (MY_LOG_WRITE_TO_FILE)//日志写入文件开关
                writeLogToFile(String.valueOf(level), tag, msg);
        }
    }

    /**
     * 打开日志文件并写入日志
     * @param myLogType
     * @param tag
     * @param text
     */
    private static void writeLogToFile(String myLogType, String tag, String text) {// 新建或打开日志文件
        Date nowTime = new Date();
        String needWriteFile = logfile.format(nowTime);
        String needWriteMessage = myLogSdf.format(nowTime) + "    " + myLogType + "    " + tag + "  ——>  " + text;

        File dirsFile = new File(MY_LOG_PATH_SDCARD_DIR);
        if (!dirsFile.exists()){
            dirsFile.mkdirs();
        }
        File file = new File(dirsFile.getAbsolutePath(), needWriteFile + MYLOGFILEName);// MY_LOG_PATH_SDCARD_DIR
        if (!file.exists()) {
            try {
                file.createNewFile(); //在指定的文件夹中创建文件
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除制定的日志文件
     */
    public static void delFile() {// 删除日志文件
        String needDelFiel = logfile.format(getDateBefore());
        File dirPath = Environment.getExternalStorageDirectory();
        File file = new File(dirPath, needDelFiel + MYLOGFILEName);// MYLOG_PATH_SDCARD_DIR
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     */
    private static Date getDateBefore() {
        Date nowTime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowTime);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }
}
