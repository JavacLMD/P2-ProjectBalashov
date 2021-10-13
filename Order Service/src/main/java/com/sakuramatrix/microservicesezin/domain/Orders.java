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
@AllArgsConstructor
@NoArgsConstructor
public class Orders  {

    @PrimaryKeyColumn(value = "orderId", type = PrimaryKeyType.PARTITIONED)
    private @Getter @Setter  int orderId;

    @PrimaryKeyColumn(value = "customer_id", type = PrimaryKeyType.CLUSTERED)
    private @Getter @Setter int id;

    @Column
    private @Getter @Setter int item_id;
    @Column
    private @Getter @Setter double price;

}









