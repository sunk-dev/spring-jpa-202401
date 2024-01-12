package com.study.jpa.chap01.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tbl_product")
public class Product {

    //엔터티 클래스응 DB 테이블의 컬럼과 1:1로 매칭되는
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private long id;//pk

    @Column(name = "prod_nm",nullable=false,length = 30)
    private String name; //상품명
//    @Column(name = "price")
    private int price=1000;//상품가격
    @Enumerated(EnumType.STRING)
    private Category category;//상품 카테고기
    @CreationTimestamp //insert기 현재 로컬서ㅗ버 시간으로 자동저잔
    private LocalDateTime createdDate;

    @UpdateTimestamp //updat실효ㅐㄹ부 자동으로 변셩
    private LocalDateTime updatedDate; //상품 수정 시간

    @Transient //DB컬럼에는 생성안됨
    private String nickName;
    public enum  Category{
        FOOD,FASHION,ELETROIC
    }


}
