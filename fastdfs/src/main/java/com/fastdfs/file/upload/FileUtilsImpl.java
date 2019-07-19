package com.fastdfs.file.upload;

import com.google.gson.Gson;
import fastdfs.FastDFSClient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileUtilsImpl
        implements FileUtils
{
    private String url = "http://182.137.14.196:1180";

    public String fileUpLoad(byte[] file_buff, String file_ext_name)
    {
        Gson g = new Gson();
        Map<String, String> map = new HashMap();
        try
        {
            FastDFSClient client = new FastDFSClient("classpath:fdfs_client.properties");
            String result = client.uploadFile(file_buff, file_ext_name);
            map.put("resultCode", "success");
            map.put("resultMsg", this.url + "/" + result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            map.put("resultMsg", e.getMessage());
            map.put("resultCode", "error");
        }
        return g.toJson(map);
    }

    private byte[] input2byte(InputStream inStream)
            throws IOException
    {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    public String fileUpLoadByStream(String file_ext_name, InputStream in)
    {
        Gson g = new Gson();
        Map<String, String> map = new HashMap();
        try
        {
            FastDFSClient client = new FastDFSClient("classpath:fdfs_client.properties");
            byte[] in2b = input2byte(in);
            String result = client.uploadFile(in2b, file_ext_name);
            map.put("resultCode", "success");
            map.put("resultMsg", this.url + "/" + result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            map.put("resultMsg", e.getMessage());
            map.put("resultCode", "error");
        }
        return g.toJson(map);
    }
}
