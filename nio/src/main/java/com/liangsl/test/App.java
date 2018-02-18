package com.liangsl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        FileInputStream fis = new FileInputStream(new File(
                "nio/temp_buffer.txt"));
        FileOutputStream fos = new FileOutputStream(new File(
                "nio/temp_buffer1.txt"));
        FileChannel readChannel = fis.getChannel(); // 读文件通道
        FileChannel writeChannel = fos.getChannel(); // 写文件通道
        ByteBuffer buffer = ByteBuffer.allocate(1024); // 读入数据缓存
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer); // 读入数据
            if (len == -1) {
                break; // 读取完毕
            }
            buffer.flip();
            byteBufferToString(buffer);
            writeChannel.write(buffer); // 写入文件
        }
        readChannel.close();
        writeChannel.close();

    }

    public static String byteBufferToString(ByteBuffer byteBuffer) {
        CharBuffer charBuffer = null;
        Charset charset = null;
        CharsetDecoder decoder = null;
        try{
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            // charBuffer = decoder.decode(buffer);//用这个的话，只能输出来一次结果，第二次显示为空
            charBuffer = decoder.decode(byteBuffer.asReadOnlyBuffer());
            System.out.println(charBuffer.toString());
            return charBuffer.toString();
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
