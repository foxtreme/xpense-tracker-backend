package com.ccharry.xpensetracker.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class City {

    private @Id @GeneratedValue Long id;
    private String name;
    private String country;
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "expense_city", joinColumns = @JoinColumn(name = "expense_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"))
    private List<Expense> expenses;

    public City() {
    }

    public City(String name, String description, String country, List<Expense> expenses) {
        this.name = name;
        this.country = country;
        this.expenses = expenses;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof City)) {
            return false;
        }
        City city = (City) o;
        return Objects.equals(this.id, city.id) && Objects.equals(this.name, city.name)
                && Objects.equals(this.description, city.description) && Objects.equals(this.country, city.country)
                && Objects.equals(this.expenses, city.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.country, this.expenses);
    }

    @Override
    public String toString() {
        return "City{Id: " + this.id + ", Name: " + this.name + ", Description: " + this.description + ", Country: "
                + this.country + ", expenses: " + this.expenses + "}";
    }
}
