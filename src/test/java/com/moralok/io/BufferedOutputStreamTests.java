package com.moralok.io;

import org.junit.Test;

import java.io.*;

/**
 * @author moralok
 * @since 2020/9/11 5:13 下午
 */
public class BufferedOutputStreamTests {

    @Test
    public void writeLong() throws IOException {
        String name = "src/test/java/com/moralok/io/file/long";
        OutputStream outputStream = new FileOutputStream(name);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        for (int i = 0; i < 1000; i++) {
            bufferedOutputStream.write(("Hello World " + i + "\n").getBytes());
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}
