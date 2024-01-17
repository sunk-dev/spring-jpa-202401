package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
//양방향에서 ToString을 그냥사용하면 상호 무한호출이 일어나 스택오버플로우 발생
//한쪽 엔터티에서 ToString을 만들때 제외시켜야한다.
//보통리스트를 제외시킴.
@ToString(exclude = {"employeeList"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_dept")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id;

    @Column(name = "dept_name",nullable = false)
    private String name;

    /*
    양방향 매핑을 데이터 베이스와 달리 객체지향 시스템에서 가능한 방법으로
    1대 N관계에서 1쪽에 N데이터를 포함시킬수 있는 방법이다.
    - 양방향 매핑에서 1쪽은 상대방 엔터티 갱신에 관여할수 없고
    (리스트에서 사원을 지운다고 실제 디비에서 사원이 삭제되지 않는다는 말)
    단순히 읽기전용(조회전용)으로만 사용하는 것이다.
    - mappedBy에는 상대방 엔터티에 @ManyToOne에 대응되는 필드명을 꼭 적어야함



     */
    @OneToMany(mappedBy = "department",fetch = FetchType.EAGER)    //부서가1:사원M
    private List<Employee> employeeList= new ArrayList<>();



}
