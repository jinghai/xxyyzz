package demo.my;

import gen.my.MyDemo;
import gen.my.Response;
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
        transport = new TSocket("172.16.80.249", 9090);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        MyDemo.Client client = new MyDemo.Client(protocol);

        client.setText("aaa");
        System.out.println("setText:aaa");
        System.out.println("getText:" + client.getText());

        //---------上传文件---------
        String rpcUploadFile = "/apache-ant-1.8.4-bin.tar.gz";//上传的二进制文件5M
        //String rpcUploadFile = "/Git-1.8.0-preview20121022.exe";//上传的二进制文件14M
        //String rpcUploadFile = "/LdapAdminToolProfessional-6.2.x-win-x86-Setup.msi";//上传的二进制文件40M
        //String rpcUploadFile = "/mongodb-win32-i386-2.4.8.zip";//上传的二进制文件80M
        //String rpcUploadFile = "/100M.zip";//上传的二进制文件100M

        //String rpcUploadFile = "/M200M.zip";//上传的二进制文件1M
        //String rpcUploadFile = "/5GFile.zip";//二进制超大文件
        //String rpcUploadFile = "/log.log";//文件文件含中文
        //上传位置
        String uploadFilePath = "/rpcUploadFilebin.tar.gz";
        //下载位置
        String downloadFilePath = "/rpcDownloadFilebin.tar.gz";

        Response res = client.uploadFile(uploadFilePath, getByteBufferFromFile(rpcUploadFile));
        System.out.println("上传文件：" + res.ret_msg);

        //---------下载文件---------
        ByteBuffer bf = client.downloadFile(uploadFilePath);

        readByteBuufferToFile(bf, downloadFilePath);
        System.out.println("下载文件：OK");
        //关闭连接
        transport.close();
    }

    public static void readByteBuufferToFile(ByteBuffer bf, String downloadFilePath) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(downloadFilePath);
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
//LongBuffer
        try {
            fis = new FileInputStream(filePath);

            // 得到文件通道
            fc = fis.getChannel();

            // 指定缓冲区大小
            bf = ByteBuffer.allocate((int) fc.size());//不能传输超过int范围的文件
            bf.capacity();
            System.out.println((int) fc.size());
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
