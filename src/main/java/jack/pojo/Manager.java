package jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private int id;
    private String collegeName;//学院名称：信息学院，航海学院，交通学院，外语学院
    private String username;//管理员账号
    private String password;//密码
}
