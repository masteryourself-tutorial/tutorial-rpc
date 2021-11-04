package pers.masteryourself.tutorial.grpc.protobuf.serializer;

import com.google.protobuf.util.JsonFormat;
import pers.masteryourself.tutorial.grpc.protobuf.proto.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * <p>description : Serializer
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2021/11/3 19:49
 */
public class Serializer {

    public void writeBinaryFile(User user, String fileName) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        user.writeTo(outputStream);
        outputStream.close();
    }

    public User readBinaryFile(String fileName) throws Exception {
        FileInputStream inputStream = new FileInputStream(fileName);
        return User.parseFrom(inputStream);
    }

    public void writeJsonFile(User user, String fileName) throws Exception {
        JsonFormat.Printer printer = JsonFormat.printer()
                .includingDefaultValueFields()
                .preservingProtoFieldNames();
        String json = printer.print(user);
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(json.getBytes());
        outputStream.close();
    }

}
