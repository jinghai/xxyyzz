package demo.my;

import gen.my.MyDemo;
import gen.my.Response;
import gen.my.RetCode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class MyClient {

    public static void main(String[] args) throws TTransportException, TException {

        TTransport transport;
        transport = new TSocket("localhost", 9091);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        MyDemo.Client client = new MyDemo.Client(protocol);

        client.setText("aaa");
        System.out.println("setText:aaa");
        System.out.println("getText:" + client.getText());

        //---------上传文件---------
        //二进制文件
        String rpcUploadFile = "/maven-2.0.7-bin.zip";
        String uploadFileName = "rpcUploadFile-bin.zip";

        //二进制超大文件
        //String rpcUploadFile = "/maven-2.0.7-bin.zip";
        //String uploadFileName = "rpcUploadFile-bin.zip";
        //文件文件含中文
        //String rpcUploadFile = "/log.log";
        //String uploadFileName = "rpcUploadFile.log";
        Response res = client.setFile(uploadFileName, getByteBufferFromFile(rpcUploadFile));
        System.out.println("上传文件：" + res.ret_msg);

        //---------下载文件---------
        ByteBuffer bf = client.getFile(uploadFileName);
        String downloadFileName = "/rpcDownloadFile.zip";
        readByteBuufferToFile(bf, downloadFileName);
        System.out.println("下载文件：OK");

        transport.close();
    }

    public static void readByteBuufferToFile(ByteBuffer bf, String downloadFileName) {
        FileOutputStream fos = null;
        Response ret = new Response(RetCode.Success, "成功");
        try {
            fos = new FileOutputStream(downloadFileName);
            // 得到文件通道
            FileChannel fc = fos.getChannel();
            //write_buffer.flip();
            // 缓冲区数据写入到文件中，会把缓冲区中从 position 到 limit 之间的数据写入文件
            fc.write(bf);
            fc.close(); //关闭文件通道
            fos.close(); //关闭文件输出流
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {

            Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ByteBuffer getByteBufferFromFile(String filePath) {
        FileInputStream fis = null;
        FileChannel fc = null;
        ByteBuffer bf = null;

        try {
            fis = new FileInputStream(filePath);
            // 得到文件通道
            fc = fis.getChannel();
            // 指定大小为 1024 的缓冲区
            bf = ByteBuffer.allocate((int) fc.size());

            //读取文件内容到缓冲区
            fc.read(bf);
            //恢复指针位置，这句很重要
            bf.rewind();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return bf;
        }
    }
}
