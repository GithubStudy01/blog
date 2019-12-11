package com.chen.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Table(name="collection",uniqueConstraints = {
        @UniqueConstraint(name ="unique_join_collection", columnNames = {"user_id","article_id"})
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class Collection {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="article_id")
    private Article article;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//如果不加，转换为json后变为数组形式
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private LocalDateTime createtime;
}
