package pers.masteryourself.tutorial.grpc.demo.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import pers.masteryourself.tutorial.grpc.demo.proto.User;
import pers.masteryourself.tutorial.grpc.demo.service.proto.UserServiceGrpc;
import pers.masteryourself.tutorial.grpc.demo.service.proto.UserServiceRequest;
import pers.masteryourself.tutorial.grpc.demo.service.proto.UserServiceResponse;

/**
 * <p>description : UserServiceImpl
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2021/11/3 20:29
 */
@Slf4j
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void findUserById(UserServiceRequest request, StreamObserver<UserServiceResponse> responseObserver) {
        long userId = request.getId();
        log.info("查询用户 userId {}", userId);
        if (userId == 1L) {
            responseObserver.onNext(
                    UserServiceResponse.newBuilder().setUser(
                            User.newBuilder().setId(1L).setName("张三").setAge(18).setSex(true).build())
                            .build());
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("数据不存在").asRuntimeException());
        }
    }

}
