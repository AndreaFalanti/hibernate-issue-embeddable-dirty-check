package org.hibernate.entity;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class TestEmbeddable {
    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(final String myValue) {
        this.val = myValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TestEmbeddable that = (TestEmbeddable) o;
        return Objects.equals(val, that.val);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(val);
    }
}
