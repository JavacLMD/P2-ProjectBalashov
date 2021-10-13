package com.github.JavacLMD.Marketplace.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.*;

@Table("items")
public class Item implements Serializable {

    @PrimaryKeyColumn(name = "item_id", type = PrimaryKeyType.PARTITIONED)
    @PrimaryKey("item_id") //passed in the column name from the table
    private int id = new Random().nextInt(99999);
    @Column //table's column name matches the field so don't need to specify it
    private String name = "New Item";
    @Column("price") //this would be how you specify the columns
    private double price = -1;
    @Column
    private Set<String> category = new HashSet<>();

    public Item() {
    }

    public Item(int item_id, String name, double price, Set<String> category) {
        this.id = item_id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Item(int item_id, String name, double price, String ...category) {
        this(item_id, name, price, new HashSet<>(Arrays.asList(category)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<String> getCategory() {
        return category;
    }

    public void setCategory(Set<String> category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Double.compare(item.price, price) == 0 && Objects.equals(name, item.name) && Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public static String toJSON(Item item) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(item);
        } catch (Exception e) {
            return "";
        }
    }
}
