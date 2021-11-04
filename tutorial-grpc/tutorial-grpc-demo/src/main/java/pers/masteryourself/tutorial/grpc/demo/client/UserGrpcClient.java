package pers.masteryourself.tutorial.grpc.demo.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import pers.masteryourself.tutorial.grpc.demo.proto.User;
import pers.masteryourself.tutorial.grpc.demo.service.proto.*;

import java.util.Iterator;
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

    public static void main(String[] args) {
        UserGrpcClient client = new UserGrpcClient("127.0.0.1", 8080);
        // 1. 添加用户
        for (int i = 1; i <= 10; i++) {
            client.add(i);
        }
        // 2. 查找用户
        client.find();
    }

    private void add(int id) {
        User user = User.newBuilder().setId(id).setName("张三_" + id).setAge(id + 20).build();
        UserAddRequest userAddRequest = UserAddRequest.newBuilder().setUser(user).build();
        UserAddResponse userAddResponse = blockingStub.add(userAddRequest);
        log.info("add user success {}", userAddResponse.getRet());
    }

    private void find() {
        UserFindRequest userFindRequest = UserFindRequest.newBuilder().setMinId(5).setMinAge(27).build();
        Iterator<UserFindResponse> userFindResponseIterator = blockingStub.find(userFindRequest);
        while (userFindResponseIterator.hasNext()) {
            UserFindResponse userFindResponse = userFindResponseIterator.next();
            User user = userFindResponse.getUser();
            log.info("查找用户 id {} name {} age {}", user.getId(), user.getName(), user.getAge());
        }
    }

}
