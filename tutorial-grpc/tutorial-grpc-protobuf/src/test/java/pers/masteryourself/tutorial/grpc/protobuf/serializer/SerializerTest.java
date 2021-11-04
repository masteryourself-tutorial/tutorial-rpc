package pers.masteryourself.tutorial.grpc.protobuf.serializer;

import org.junit.Test;
import pers.masteryourself.tutorial.grpc.protobuf.proto.User;

/**
 * <p>description : SerializerTest
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2021/11/3 19:54
 */
public class SerializerTest {

    String binaryFileName = "user.bin";

    String jsonFileName = "user.json";

    Serializer serializer = new Serializer();

    @Test
    public void writeBinaryFile() throws Exception {
        User user = User.newBuilder()
                .setId(9527L)
                .setName("唐伯虎")
                .setAge(18)
                .setSex(true)
                .build();
        serializer.writeBinaryFile(user, binaryFileName);
        User user2 = serializer.readBinaryFile(binaryFileName);
        System.out.println(user.equals(user2));
    }

    @Test
    public void writeJsonFile() throws Exception {
        User user = User.newBuilder()
                .setId(9527L)
                .setName("唐伯虎")
                .setAge(18)
                .setSex(true)
                .build();
        serializer.writeJsonFile(user, jsonFileName);
    }

}