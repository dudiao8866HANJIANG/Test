package com.fastdfs.file.upload;

import java.io.InputStream;

public interface FileUtils
{
    String fileUpLoad(byte[] paramArrayOfByte, String paramString);

    String fileUpLoadByStream(String paramString, InputStream paramInputStream);
}
