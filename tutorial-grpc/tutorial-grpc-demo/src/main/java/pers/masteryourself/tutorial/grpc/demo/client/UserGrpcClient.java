package pers.masteryourself.tutorial.grpc.demo.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import pers.masteryourself.tutorial.grpc.demo.service.proto.UserServiceGrpc;
import pers.masteryourself.tutorial.grpc.demo.service.proto.UserServiceRequest;
import pers.masteryourself.tutorial.grpc.demo.service.proto.UserServiceResponse;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : UserGrpcClient
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2021/11/4 14:26
 */
@Slf4j
public class UserGrpcClient {

    private final ManagedChannel managedChannel;
    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    public UserGrpcClient(String host, int port) {
        this.managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.blockingStub = UserServiceGrpc.newBlockingStub(managedChannel);
    }

    public void close() throws Exception {
        this.managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void findUserById(Long id) {
        UserServiceRequest userServiceRequest = UserServiceRequest.newBuilder().setId(id).build();
        UserServiceResponse userServiceResponse = null;
        try {
            userServiceResponse = blockingStub.findUserById(userServiceRequest);
        } catch (StatusRuntimeException e) {
            log.info("request failed {}", e.getStatus().getCode(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("findUserById request {}, response {}", userServiceRequest, userServiceResponse);
    }

    public static void main(String[] args) {
        UserGrpcClient client = new UserGrpcClient("127.0.0.1", 8080);
        client.findUserById(1L);
        client.findUserById(2L);
    }

}
