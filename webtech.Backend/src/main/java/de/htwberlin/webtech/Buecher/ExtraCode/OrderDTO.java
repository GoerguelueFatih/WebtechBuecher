package de.htwberlin.webtech.Buecher.ExtraCode;

import de.htwberlin.webtech.Buecher.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private String id;
    private BigDecimal total;
    private Order.OrderStatus status;
    private String localDateTime;
    private List<String> bookTitles;


    public OrderDTO(String id, BigDecimal total, Order.OrderStatus status, String localDateTime, List<String> bookTitles) {
        this.id = id;
        this.total = total;
        this.status = status;
        this.localDateTime = localDateTime;
        this.bookTitles = bookTitles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Order.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(Order.OrderStatus status) {
        this.status = status;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    // Updated setter to accept String type
    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    public List<String> getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(List<String> bookTitles) {
        this.bookTitles = bookTitles;
    }
}
