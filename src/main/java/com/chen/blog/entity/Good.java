package com.chen.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name="good",uniqueConstraints = {
        @UniqueConstraint(name ="unique_join_good", columnNames = {"user_id","article_id"})
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class Good {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private LocalDateTime createtime;
}
