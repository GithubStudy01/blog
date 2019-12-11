package com.chen.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;
@Table(name="comment")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class Comment {

    public interface ArticleCommentView{}

    @JsonView({ArticleCommentView.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    @JsonView({ArticleCommentView.class})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @JsonView({ArticleCommentView.class})
    @NotBlank
    @Column
    private String reply;


    @JsonView({ArticleCommentView.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//如果不加，转换为json后变为数组形式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private LocalDateTime createtime;


//    @JsonView({ArticleCommentView.class})
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="c_id")


    @Column(name = "c_id",columnDefinition = "int(255) default 0")
    private Integer cId;

    @Transient
    private String nickname;

}
