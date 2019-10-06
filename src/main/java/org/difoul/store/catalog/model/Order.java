package org.difoul.store.catalog.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable {

    private String id;
    private String order_type;
    private List<AbstractProperty> properties;


    public Order() {
    }

    public Order(String order_type, List<AbstractProperty> properties) {
        this.id = UUID.randomUUID().toString();
        this.order_type = order_type;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public List<AbstractProperty> getProperties() {
        return properties;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public void setProperties(List<AbstractProperty> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", order_type='" + order_type + '\'' +
                ", properties=" + properties +
                '}';
    }
}
