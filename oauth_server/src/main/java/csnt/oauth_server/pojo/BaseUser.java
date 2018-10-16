package csnt.oauth_server.pojo;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

public class BaseUser<T extends Model> extends Model<T> {

    private String id;

    private String userName;

    private String userPassword;

    private String nikeName;

    private Integer status;

    private Integer failtimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFailtimes() {
        return failtimes;
    }

    public void setFailtimes(Integer failtimes) {
        this.failtimes = failtimes;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}