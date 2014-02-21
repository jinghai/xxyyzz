package demo.my;

import gen.my.MyDemo;
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

        transport.close();
    }
}
