package com.my.entity;

import java.io.Serializable;

/**
 * @author QinHe at 2019-07-29
 */
public class OrderRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
