package com.moralok.io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author moralok
 * @since 2020/9/11 5:13 下午
 */
public class BufferedInputStreamTests {

    @Test
    public void writeAlphabet() throws IOException {
        String name = "src/test/java/com/moralok/io/file/alphabet";
        InputStream inputStream = new FileInputStream(name);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = new byte[5];
        int len = 0;
        while ((len = bufferedInputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        bufferedInputStream.close();
    }
}
