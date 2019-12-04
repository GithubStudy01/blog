package com.chen.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Table(name="attention",uniqueConstraints = {
        @UniqueConstraint(name ="unique_join_user_id", columnNames = {"user_id","attention_user_id"})
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class Attention {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Id
    private Integer id;

//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

//    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="attention_user_id")
    private User attentionUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Date createtime;


}
