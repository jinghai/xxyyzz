package tutorial;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import shared.SharedStruct;

public class MyClient {

    public static void main(String[] args) throws TTransportException, TException {

        TTransport transport;
        transport = new TSocket("172.16.80.250", 9090);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        Calculator.Client client = new Calculator.Client(protocol);
        client.ping();
    }
}
