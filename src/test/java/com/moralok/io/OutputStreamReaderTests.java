package com.moralok.io;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author moralok
 * @since 2020/9/11 5:48 下午
 */
public class OutputStreamReaderTests {

    @Test
    public void testWriteWithUTF8() throws IOException {
        String name = "src/test/java/com/moralok/io/file/ChineseWithUTF8";
        OutputStream outputStream = new FileOutputStream(name);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        outputStreamWriter.write("我爱李小姐 I love you");
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }

    @Test
    public void testWriteWithUS_ASCII() throws IOException {
        String name = "src/test/java/com/moralok/io/file/ChineseWithUS_ASCII";
        OutputStream outputStream = new FileOutputStream(name);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.US_ASCII);
        outputStreamWriter.write("我爱李小姐 I love you");
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }
}
