package com.example.labav7.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "deliveryCode", nullable = false, length = 128)
    private String deliveryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Product> products;

    @Column(name = "productAmount", nullable = false)
    private Integer productAmount;

    @Column(name = "productPrice", nullable = false)
    private Double productPrice;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "delivery_date", nullable = false, updatable = true)
    private LocalDateTime delivery_date;

    @Column(name = "productBalance", nullable = false)
    private Integer productBalance;
}
