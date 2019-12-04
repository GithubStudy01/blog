package com.chen.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Table(name="article")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class Article {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Id
    private Long id;

    @NotBlank(message = "标题不能为空")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "内容不能为空")
    @Column(nullable = false)
    private String content;

    //0公开 1私有
    @NotNull
    @Column(columnDefinition = "int(1) default 0")
    private Integer type;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatetime;

    @Column(name = "view_times",columnDefinition = "int default 0")
    private Integer viewTimes;

    @Column(name = "good_times",columnDefinition = "int default 0")
    private Integer goodTimes;

    @Column(name = "comment_times",columnDefinition = "int default 0")
    private Integer commentTimes;

    //0未顶置 1顶置
    @Column(columnDefinition = "int default 0")
    private Integer overhead;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "overhead_time")
    private Date overheadTime;


    @ManyToOne(targetEntity = Blog.class,fetch = FetchType.LAZY)
    @JoinColumn(name="blog_id")
    private Blog blog;

    @ManyToOne(targetEntity = Sort.class,fetch = FetchType.LAZY)
    @JoinColumn(name="sort_id")
    private Sort sort;


    @OneToMany(targetEntity = Comment.class,fetch = FetchType.LAZY,mappedBy = "article")
    private List<Comment> commentList;

    @ManyToMany(mappedBy = "articleList")
    private List<Tag> tagList;
}