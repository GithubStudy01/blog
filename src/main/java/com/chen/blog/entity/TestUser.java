package com.chen.blog.entity;

import com.alibaba.druid.support.monitor.annotation.MTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name="test_user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class TestUser implements Serializable {

    public interface AddUserView{};
    public interface UpdateUserView{};


    public interface GetUserView{};
    public interface OnlyGetAddress{};
    public interface GetAllNotPassword extends GetUserView,OnlyGetAddress{};

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotNull(message = "用户id不能为空",groups = {UpdateUserView.class})
    @JsonView(GetUserView.class)
    private Integer id;

    @Column(length = 10)
    @NotBlank(message = "用户名不能为空",groups = {AddUserView.class})
    @JsonView(GetUserView.class)
    private String username;

    @NotBlank(message = "密码不能为空",groups = {AddUserView.class})
    private String password;

    @NotBlank(message = "地址不能为空",groups = {AddUserView.class})
    @JsonView(OnlyGetAddress.class)
    private String address;

}
