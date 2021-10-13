package com.sakuramatrix.microservicesezin.domain;

import lombok.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.Random;

@Table
@Data
public class Orders  {

    @PrimaryKeyColumn(value = "orderId", type = PrimaryKeyType.PARTITIONED)
    private int orderId;

    @PrimaryKeyColumn(value = "customer_id", type = PrimaryKeyType.CLUSTERED)
    private int id;

    @Column
    private int item_id;
    @Column
    private double price;

}









