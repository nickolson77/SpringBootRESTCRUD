package com.example.demo.repository.entity;

import com.example.demo.service.type.OrderStatusType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

//@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name = "user_id")
    private Long user_id;
    @Column(updatable = false)
    private Long product_id;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "order_status_type")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private OrderStatusType status;
    @Column(insertable = false)
    private LocalDateTime created;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", status=" + status +
                ", created=" + created +
                '}';
    }
}
