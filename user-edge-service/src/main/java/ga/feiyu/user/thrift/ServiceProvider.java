package ga.feiyu.user.thrift;

import ga.feiyu.thrift.user.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Value("${thrift.user.ip}")
    private String serverIP;
    @Value("${thrift.user.port}")
    private int serverPort;


    public UserService.Client getUserService() {

        TSocket tSocket = new TSocket(serverIP, serverPort, 3000);
        TFramedTransport transport = new TFramedTransport(tSocket);

        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }

        TBinaryProtocol protocol = new TBinaryProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);

        return client;
    }
}
