/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package demo.scan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 *
 * @author xiaojinghai
 */
public class TCPScan {
//多线程描述参考:http://blog.csdn.net/zhangzhaokun/article/details/6615454

    public static void main(String args[]) {
        long startTime = System.currentTimeMillis();
        String ipblock = "172.16.217.";//IP前三位172.16.80.
        String startIp = "1";//最后一位起始
        String endIp = "254";//最后一位结束
        String port = "8080";//目标端口//6060
        int start = Integer.valueOf(startIp);
        int end = Integer.valueOf(endIp);
        String fullIp = "";
        int count = 0;
        System.out.println("正在扫描主机...");
        for (int i = start; i <= end; i++) {
            long s = System.currentTimeMillis();
            count++;
            try {
                fullIp = ipblock + i;

                //InetAddress hostAddress = InetAddress.getByName(fullIp);
                //Socket theTcpSocket = new Socket(hostAddress, i);
                Socket theTcpSocket = new Socket(); //实例化socket

                SocketAddress socketAddress = new InetSocketAddress(fullIp, Integer.valueOf(port)); //获取sockaddress对象

                theTcpSocket.connect(socketAddress, 100);//连接并指定超时时间

                System.out.println("扫描到目标端口：" + fullIp + ":" + port);
                theTcpSocket.close();
            } catch (UnknownHostException ex) {
                //System.out.println("未开机");
            } catch (IOException ex) {
                //System.out.println("未发现目标端口");
            }
            long e = System.currentTimeMillis();
            long used = (e - s) / 1000;
            //System.out.println("用时：" + used + "秒");
            //System.out.println("------------------------------");
        }
        long endTime = System.currentTimeMillis();
        long ta = (endTime - startTime) / 1000;
        long av = (endTime - startTime) / count;
        System.out.println("总用时：" + ta + "秒,扫描了" + count + "个节点");
        System.out.println("平均每次用时：" + av + "毫秒");
    }
}
