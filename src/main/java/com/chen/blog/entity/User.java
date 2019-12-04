package com.chen.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * 用户实体
 */
@Table(name="user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class User {

    public interface OnlyUserInfo{}


    @JsonView(OnlyUserInfo.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Id
    private Long id;

    @JsonView(OnlyUserInfo.class)
    @NotBlank(message = "用户名不能为空")
    @Size(min = 6,max = 10)
    @Column(nullable = false,unique = true,length = 10)
    private String username;

    @JsonView(OnlyUserInfo.class)
    @NotBlank(message = "密码不能为空")
    @Size(min = 12,max = 15)
    @Column(nullable = false,length = 15)
    private String password;

    @JsonView(OnlyUserInfo.class)
    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "电话号码格式不对")
    @Column(nullable = false,unique = true,length = 11)
    private String phone;

    @JsonView(OnlyUserInfo.class)
    //0 私密 1男 2女
    @NotNull(message = "性别不能为空")
    @Column(columnDefinition = "int(1) default 0")
    private Integer sex;

    @JsonView(OnlyUserInfo.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "出生日期不能为空")
    @Temporal(TemporalType.DATE)
    @Column
    private Date birthday;

    @JsonView(OnlyUserInfo.class)
    @Email(message = "邮箱格式不正确")
    @Column(nullable = false,unique = true)
    private String email;

    @JsonView(OnlyUserInfo.class)
    @Column
    private String briefIntr;

    @JsonView(OnlyUserInfo.class)
    @NotBlank(message = "昵称不能为空")
    @Column(nullable = false)
    private String nickname;


    @JsonView(OnlyUserInfo.class)
    @NotBlank(message = "头像不能为空")
    @Column(nullable = false)
    private String headurl;

    @JsonView(OnlyUserInfo.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Date createtime;

    @JsonView(OnlyUserInfo.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatetime;

    @JsonView(OnlyUserInfo.class)
    //0 未删除  1删除:逻辑删除
    @Column(name = "delete_sign",columnDefinition = "int(1) default 0")
    private Integer deleteSign;

    @JsonView(OnlyUserInfo.class)
    //0 未锁定 1锁定
    @Column(name="lock_sign",columnDefinition = "int(1) default 0")
    private Integer lockSign;

    @JsonView(Blog.OnlyBlogInfo.class)
    @OneToOne(targetEntity = Blog.class,cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name="blog_id")
    private Blog blog;

    @JsonView(OnlyUserInfo.class)
    @Column(name = "view_times",columnDefinition = "int default 0")
    private Integer viewSum;

    @JsonView(OnlyUserInfo.class)
    @Column(name = "good_times",columnDefinition = "int default 0")
    private Integer goodSum;

    @JsonView(OnlyUserInfo.class)
    @Column(name = "comment_times",columnDefinition = "int default 0")
    private Integer commentSum;


    @OneToMany(targetEntity = Attention.class,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Attention> attentionList;

    @OneToMany(targetEntity = Comment.class,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Comment> commentList;

    @OneToMany(targetEntity = Good.class,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Good> goodList;

}
