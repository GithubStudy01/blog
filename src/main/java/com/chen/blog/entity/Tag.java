package com.chen.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Table(name="tag")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })//解决转换异常
public class Tag {

    public interface ArticleTagView{}

    @JsonView({ArticleTagView.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Id
    private Integer id;

    @JsonView({ArticleTagView.class})
    @NotBlank(message = "标签名不能为空")
    @Column(name = "tag_name")
    private String tagName;

    @Column(columnDefinition = "int default 0")
    private Integer num;


    @ManyToMany(targetEntity = Article.class,fetch = FetchType.LAZY)
    @JoinTable(name = "join_tag_article",
            joinColumns = {@JoinColumn(name = "tag_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id",referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(name ="unique_join_tag_article", columnNames = {"tag_id","article_id"})
    })
    List<Article> articleList;

}
