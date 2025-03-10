package net.typicartist.talpius.setting;

import java.util.function.BooleanSupplier;

public class Setting<T> {
    
    private String name;
    
    private String description;
    
    private T value;

    private T min;
    private T max;
    private T steps;
    
    private BooleanSupplier visibility = () -> true;

    private Setting<Parent> parent = null;

    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public Setting(String name, T value, T max, T min, T steps) {
        this.name = name;
        this.value = value;
        this.max = max;
        this.min = min;
        this.steps = steps;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getSteps() {
        return this.steps;
    }

    public void setSteps(T steps) {
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        if (parent != null) {

            if (!parent.getValue().isExtended()) {
                return false;
            }
            
        }

        return visibility.getAsBoolean();
    }

    public Setting<T> withVisibility(BooleanSupplier visibility) {
        this.visibility = visibility;
        return this;
    }

    public Setting<T> withDescription(String description) {
        this.description = description;
        return this;
    }

    public Setting<T> withParent(Setting<Parent> parent) {
        this.parent = parent;
        return this;
    }

    public void getParentModule() { }

    public String getDescription() {
        return description;
    }

    public boolean hasDescription() {
        return description.length() > 0;
    }

}
