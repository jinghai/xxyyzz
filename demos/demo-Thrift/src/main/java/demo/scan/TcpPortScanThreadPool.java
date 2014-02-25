/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package demo.scan;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author xiaojinghai
 */
public class TcpPortScanThreadPool {
//http://blog.csdn.net/zhangzhaokun/article/details/6615454

    public static void main(String args[]) {
        ExecutorService pool = Executors.newFixedThreadPool(500);
        long s = System.currentTimeMillis();
        final Vector list = new Vector();
        final Vector all = new Vector();
        final String ipblock = "172.16.217.";//IP前三位172.16.80.
        final String startIp = "1";//最后一位起始
        final String endIp = "254";//最后一位结束
        final String port = "8080";//目标端口//6060
        int start = Integer.valueOf(startIp);
        int end = Integer.valueOf(endIp);

        for (int i = start; i < end; i++) {

            all.add(new Callable() {
                boolean f = true;
                int c = 1;

                public Object call() throws Exception {
                    try {
                        String ip = ipblock + 1;
                        Socket theTcpSocket = new Socket(); //实例化socket
                        SocketAddress socketAddress = new InetSocketAddress(ip, Integer.valueOf(port)); //获取sockaddress对象
                        theTcpSocket.connect(socketAddress, 100);
                        theTcpSocket.close();
                        list.add(ip + ":" + port);
                    } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        f = false;

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        f = false;
                    } finally {
                        if (f) {
                            System.out.println("scan port==>" + port);
                        }
                        return port;
                    }
                }
            });
        }

        System.out.println("scanning...");
        try {
            pool.invokeAll(all);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ",");
        }
        System.out.println("scan over...");
        long e = System.currentTimeMillis();
        System.out.println("exec time==>" + (e - s) + "ms");

    }
}
