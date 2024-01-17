package com.study.jpa.chap02_querymethod.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter //실무에서 세터를 만들때 정말 필요한지 확인하고 만들것
@Getter
@ToString
@EqualsAndHashCode(of = {"id","name"})
//@EqualsAndHashCode(of = {"id","name"})//두객체를 비교할때 어떤 속성이 일치하면 어떤 속성이 일치하면 같은 것일지 설정
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_student")
public class Student {
    @Id
    @Column(name = "stu_id")
    @GeneratedValue(generator = "uid")
    @GenericGenerator(strategy = "uuid",name = "uid")
    private String id;
    @Column(name = "stu_name",nullable = false)
    private String name;
    private  String city;
    private String major;

}
