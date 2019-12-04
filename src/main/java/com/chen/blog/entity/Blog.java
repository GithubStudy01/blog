package com.chen.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Table(name="blog")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class Blog {

    public interface OnlyBlogInfo{}

    @JsonView(Blog.OnlyBlogInfo.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Id
    private Long id;

    @JsonView(Blog.OnlyBlogInfo.class)
    @NotBlank(message = "博客名称不能为空")
    @Column(name = "blog_name")
    private String blogName;

    @JsonView(Blog.OnlyBlogInfo.class)
    @Column
    private String description;

    @JsonView(Blog.OnlyBlogInfo.class)
    //0 未删除  1删除:逻辑删除
    @Column(name = "delete_sign",columnDefinition = "int(1) default 0")
    private Integer deleteSign;

    @JsonView(Blog.OnlyBlogInfo.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Date createtime;

    @JsonView(Blog.OnlyBlogInfo.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatetime;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY,targetEntity = Article.class,mappedBy = "blog")
    private List<Article> articleList;

}
