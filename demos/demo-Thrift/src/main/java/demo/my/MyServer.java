package demo.my;

import gen.my.MyDemo;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class MyServer {

    public static MyDemoHandler handler;

    @SuppressWarnings("rawtypes")
    public static MyDemo.Processor processor;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            handler = new MyDemoHandler();
            processor = new MyDemo.Processor(handler);

            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simple).start();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(MyDemo.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9091);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
