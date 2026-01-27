package ru.pet_projects.order_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_status")
    @Enumerated(value = EnumType.STRING)
    private OrderLifeCycle orderStatus;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "excursion_id")
    private Long excursionId;

    public Order() {
    }

    public Order(Long id, OrderLifeCycle orderStatus, User user, Long excursionId) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.user = user;
        this.excursionId = excursionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderLifeCycle getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderLifeCycle orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }

}
