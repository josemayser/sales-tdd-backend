package com.group_e.sales_tdd_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "warehouses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Warehouse {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(unique = true, nullable = false, length = 128)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String location;
}
