package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product = "";
    @Column(name = "total_price")
    private Float totalPrice = 0f;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product += product;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice += totalPrice;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order {id = " + id +
                ", product='" + product + '\'' +
                ", totalPrice=" + totalPrice +
                ", userId=" + userId +
                '}';
    }

}
