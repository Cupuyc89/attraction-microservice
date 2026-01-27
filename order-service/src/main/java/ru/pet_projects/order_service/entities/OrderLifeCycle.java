package ru.pet_projects.order_service.entities;

public enum OrderLifeCycle {

    PLACED("Order has been placed"),
    CANCELED("Order has been canceled"),
    PAID("Order has been paid"),
    RETURNED("Order has been returned");

    private final String orderStatus;

    OrderLifeCycle(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
