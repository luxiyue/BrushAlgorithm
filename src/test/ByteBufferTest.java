package test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferTest {
    public static void main(String[] args) {
        try {
            FileChannel channel = new FileInputStream("E:\\JavaCode\\LeetCode\\src\\test\\1.txt").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (channel.read(buffer) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                buffer.clear();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
