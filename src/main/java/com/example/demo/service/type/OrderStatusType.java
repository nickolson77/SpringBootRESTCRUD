package com.example.demo.service.type;

import jakarta.persistence.Column;

public enum OrderStatusType {
    NEW,
    CONFIRMED,
    ASSEMBLING,
    IN_DELIVERY,
    DELIVERED,
    CANCELED,
    RETURNED
}
