package com.moralok.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author moralok
 * @since 2020/9/11 5:48 下午
 */
public class InputStreamReaderTests {

    @Test
    public void testReadWithUTF8() throws IOException {
        String name = "src/test/java/com/moralok/io/file/ChineseWithUTF8";
        InputStream inputStream = new FileInputStream(name);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        int len;
        while ((len = inputStreamReader.read()) != -1) {
            System.out.print((char) len);
        }
        inputStreamReader.close();
    }

    @Test
    public void testReadWithUS_ASCII() throws IOException {
        String name = "src/test/java/com/moralok/io/file/ChineseWithUS_ASCII";
        InputStream inputStream = new FileInputStream(name);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        int len;
        while ((len = inputStreamReader.read()) != -1) {
            System.out.print((char) len);
        }
        inputStreamReader.close();
    }
}
