package com.moralok.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author moralok
 * @since 2020/9/11 4:45 下午
 */
public class FileInputStreamTests {

    @Test
    public void readAlphabet() throws IOException {
        String name = "src/test/java/com/moralok/io/file/alphabet";
        InputStream inputStream = new FileInputStream(name);
        int i;
        // 一次读取一个字节
        while ((i = inputStream.read()) != -1) {
            // 字符在底层存储的时候就是存储的数值。即字符对应的ASCII码。
            // 65-90
            System.out.print(i + " ");
            // A-Z
            System.out.print((char) i + " ");
        }
        //关闭IO流
        inputStream.close();
    }

    @Test
    public void readNumbers() throws IOException {
        String name = "src/test/java/com/moralok/io/file/numbers";
        InputStream inputStream = new FileInputStream(name);
        int i;
        // 一次读取一个字节
        while ((i = inputStream.read()) != -1) {
            System.out.print(i + " ");
            System.out.print((char) i + " ");
        }
        // 关闭IO流
        inputStream.close();
    }
}
