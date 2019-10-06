package org.difoul.store.common.event.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Event implements Serializable {



    private String id;
    private String aggregateId;
    private String source;
    private Date createdAt;
    private String status;
    private String payload;

    public Event() {
    }

    public Event(String source, String status, String aggregateId, String payload) {
        this.source = source;
        this.status = status;
        this.payload = payload;
        this.aggregateId = aggregateId;
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getPayload() {
        return payload;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
