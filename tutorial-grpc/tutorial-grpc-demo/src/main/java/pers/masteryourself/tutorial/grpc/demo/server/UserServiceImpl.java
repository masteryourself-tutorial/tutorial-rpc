package pers.masteryourself.tutorial.grpc.demo.server;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import pers.masteryourself.tutorial.grpc.demo.proto.User;
import pers.masteryourself.tutorial.grpc.demo.service.proto.*;

import java.util.ArrayList;
import java.util.List;

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

    private final List<User> userList = new ArrayList<>();

    @Override
    public void add(UserAddRequest request, StreamObserver<UserAddResponse> responseObserver) {
        userList.add(request.getUser());
        UserAddResponse response = UserAddResponse.newBuilder().setRet(1).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void find(UserFindRequest request, StreamObserver<UserFindResponse> responseObserver) {
        for (User user : userList) {
            if (this.filter(user, request)) {
                responseObserver.onNext(UserFindResponse.newBuilder().setUser(user).build());
            }
        }
        responseObserver.onCompleted();
    }

    private boolean filter(User user, UserFindRequest request) {
        if (user.getId() < request.getMinId()) {
            return false;
        }
        if (user.getAge() < request.getMinAge()) {
            return false;
        }
        return true;
    }

}
