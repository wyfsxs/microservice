package ga.feiyu.course.service;

import ga.feiyu.thrift.user.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Value("${thrift.user.ip}")
    private String serverIP;

    @Value("${thrift.user.port}")
    private int serverPort;

    private TTransport transport;

    private enum ServiceType {
        USER,
        MESSAGE
    }

    public UserService.Client getUserService() {

        return getService(serverIP, serverPort, ServiceType.USER);
    }


    public <T> T getService(String ip, int port, ServiceType serviceType) {
        TSocket socket = new TSocket(ip, port, 3000);
        transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);

        TServiceClient result = null;
        switch (serviceType) {
            case USER:
                result = new UserService.Client(protocol);
                break;
        }
        return (T) result;
    }


    //关闭transport，如果不关闭，会出现socket被占用的问题
    public boolean closeTransport() {
        try{
            if (transport != null) {
                transport.close();
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
