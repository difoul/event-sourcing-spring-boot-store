package org.difoul.store.catalog.model;

public class BooleanProperty extends AbstractProperty {

    private String name;
    private Boolean value;

    public BooleanProperty() {
    }

    public BooleanProperty(String name, Boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Boolean getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BooleanProperty{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
