package pers.masteryourself.tutorial.grpc.demo.ex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>description : ServiceEx
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2021/11/4 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException {

    private int code;

    private String message;

}
