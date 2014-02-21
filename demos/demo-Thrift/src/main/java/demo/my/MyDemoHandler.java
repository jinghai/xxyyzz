/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package demo.my;

import gen.my.MyDemo;
import gen.my.Response;
import gen.my.RetCode;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;

/**
 *
 * @author xiaojinghai
 */
public class MyDemoHandler implements MyDemo.Iface {

    private String text;

    @Override
    public String getText() throws TException {
        return text;
    }

    @Override
    public ByteBuffer getFile(String file_name) throws TException {
        FileInputStream fis = null;
        FileChannel fc = null;
        ByteBuffer bf = null;

        try {
            fis = new FileInputStream("/" + file_name);
            // 得到文件通道
            fc = fis.getChannel();
            // 指定大小为 1024 的缓冲区
            bf = ByteBuffer.allocate((int) fc.size());

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

    @Override
    public Response setFile(String file_name, ByteBuffer write_buffer, int length) throws TException {
        FileOutputStream fos = null;
        Response ret = new Response(RetCode.Success, "成功");
        try {
            fos = new FileOutputStream("/" + file_name);
            // 得到文件通道
            FileChannel fc = fos.getChannel();
            // 缓冲区数据写入到文件中，会把缓冲区中从 position 到 limit 之间的数据写入文件
            fc.write(write_buffer);
            fc.close(); //关闭文件通道
            fos.close(); //关闭文件输出流
        } catch (FileNotFoundException ex) {
            ret = new Response(RetCode.Failed, "失败");
            Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ret = new Response(RetCode.Failed, "失败");
            Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(MyDemoHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ret;
        }
    }

    @Override
    public Response setText(String text) throws TException {
        this.text = text;
        Response res = new Response(RetCode.Success, "成功");
        return res;
    }

}
