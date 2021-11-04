package pers.masteryourself.tutorial.grpc.demo.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import pers.masteryourself.tutorial.grpc.demo.service.UserServiceImpl;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : UserServer
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2021/11/3 20:36
 */
@Slf4j
public class UserGrpcServer {

    private final int port;

    private final Server server;

    public UserGrpcServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port).addService(new UserServiceImpl()).build();
    }

    public void start() throws Exception {
        server.start();
        log.info("server start on port {}", port);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UserGrpcServer.this.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }));
    }

    public void close() throws Exception {
        server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }

    public void blockUntilShutdown() throws Exception {
        server.awaitTermination();
    }

    public static void main(String[] args) throws Exception {
        UserGrpcServer server = new UserGrpcServer(8080);
        server.start();
        server.blockUntilShutdown();
    }

}
