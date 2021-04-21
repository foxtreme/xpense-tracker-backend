package com.ccharry.xpensetracker.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Expense {

    private @Id @GeneratedValue Long id;
    private String name; // internet
    private String type; // mandatory
    private String frequency; // monthly
    private double minValue; // 45.00
    private double maxValue; // 60.00

    @ManyToMany(mappedBy = "expenses")
    private List<Manager> managers;

    @ManyToMany(mappedBy = "expenses")
    private List<City> cities;

    public Expense() {
    }

    public Expense(String name, String type, String frequency, double minValue, double maxValue, List<Manager> managers,
            List<City> cities) {
        this.name = name;
        this.type = type;
        this.frequency = frequency;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.managers = managers;
        this.cities = cities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMinValue() {
        return this.minValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMaxValue() {
        return this.maxValue;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public List<Manager> getManagers() {
        return this.managers;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        return cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Expense)) {
            return false;
        }
        Expense expense = (Expense) o;
        return Objects.equals(this.id, expense.id) && Objects.equals(this.name, expense.name)
                && Objects.equals(this.type, expense.type) && Objects.equals(this.frequency, expense.frequency)
                && Objects.equals(this.minValue, expense.minValue) && Objects.equals(this.maxValue, expense.maxValue)
                && Objects.equals(this.cities, expense.cities) && Objects.equals(this.managers, expense.managers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.type, this.frequency, this.minValue, this.maxValue, this.cities,
                this, managers);
    }

    @Override
    public String toString() {
        return "Expense{ Id:" + this.id + ", Name: " + this.name + ", Type: " + this.type + ", Frequency:"
                + this.frequency + ", minValue: " + this.minValue + ", maxValue: " + this.maxValue + ", cities: "
                + this.cities + ", managers: " + this.managers + "}";
    }

}
