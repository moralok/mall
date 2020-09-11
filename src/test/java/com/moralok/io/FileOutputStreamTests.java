package com.moralok.io;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author moralok
 * @since 2020/9/11 5:00 下午
 */
public class FileOutputStreamTests {

    @Test
    public void writeAlphabet() throws IOException {
        String name = "src/test/java/com/moralok/io/file/alphabet";
        OutputStream outputStream = new FileOutputStream(name);
        outputStream.write("ABCDEFG\n".getBytes());
        outputStream.write("HIJKLMN\n".getBytes());
        outputStream.write("OPQRST\n".getBytes());
        outputStream.write("UVWXYZ\n".getBytes());
        // 关闭IO流
        outputStream.close();
    }

    @Test
    public void writeNumbers() throws IOException {
        String name = "src/test/java/com/moralok/io/file/numbers";
        OutputStream outputStream = new FileOutputStream(name);
        outputStream.write("123456789".getBytes());
        // 关闭IO流
        outputStream.close();
        // 内容追加写入
        outputStream = new FileOutputStream(name, true);
        // 输出换行符
        outputStream.write("\n".getBytes());
        // 输出追加内容
        outputStream.write("987654321".getBytes());
        // 输出换行符
        outputStream.write("\n".getBytes());
        // 输出追加内容
        for (int i = 97; i < 123; i++) {
            outputStream.write(i);
        }
        // 关闭IO流
        outputStream.close();
    }
}
