package com.zc.commonutilslib;

/**
 * Created by 33400 on 2018-07-09.
 */

public class ByteUtil {

    public static String ByteToString(byte[] by)//把数组转换为字符串
    {
        String str="";
        char ch='\0';
        //for(int i=0;by[i]!='\0';i++)
        for(int i=0;by[i] != '\0';i++)
        {
            ch=(char)by[i];
            str+=ch;
        }
        return str;
    }

    public static String ByteArrayToString(byte[] by, int length)//把数据以十六进制显示
    {
        String str="";
        for(int i=0;i<length;i++)
        {
            String hex = Integer.toHexString(by[i] & 0xFF);
            if(hex.length() == 1)
                hex="0" + hex;
            str += hex.toUpperCase();
        }
        return str;
    }


    /*
 * 16进制字符串转字节数组
 */
    public static byte[] hexString2Bytes(String hex) {

        if ((hex == null) || (hex.equals(""))){
            return null;
        }
        else if (hex.length()%2 != 0){
            return null;
        }
        else{
            hex = hex.toUpperCase();
            int len = hex.length()/2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i=0; i<len; i++){
                int p=2*i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p+1]));
            }
            return b;
        }

    }
    /*
 * 字符转换为字节
 */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /*
 * 字节转10进制
 */
    public static int byte2Int(byte b){
        int r = (int) b;
        return r;
    }


}
