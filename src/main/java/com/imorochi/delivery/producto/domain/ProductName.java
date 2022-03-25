package com.imorochi.delivery.producto.domain;

import com.imorochi.delivery.shared.domain.StringValueObject;

public final class ProductName extends StringValueObject {
    public ProductName(String value) {
        super(value);
    }

    public ProductName() {
        super("");
    }
}
