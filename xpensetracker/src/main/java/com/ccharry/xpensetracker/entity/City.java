package com.ccharry.xpensetracker.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "cities")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotNull(message = "name is required")
    @Pattern(regexp = "^[a-zA-Z. ]+$", message = "name must be a string")
    private String name;

    @NotNull(message = "country is required")
    @Pattern(regexp = "^[a-zA-Z. ]+$", message = "country must be a string")
    private String country;
    
    @NotNull(message = "description is required")
    @Pattern(regexp = "^[a-zA-Z. ]+$", message = "description must be a string")
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = true)
    @ManyToMany(targetEntity =Expense.class, cascade = CascadeType.ALL)
    private List<Expense> expenses;

    /**
     * Constructor unparameterized
     */
    public City() {
    }

    /**
     * Constructor
     * @param name The name of the City
     * @param description A brief description of the city 
     * @param country The name of the country where the city is located
     * @param expenses A list of expenses associated to the city
     */
    public City(String name, String description, String country, List<Expense> expenses) {
        this.name = name;
        this.country = country;
        this.expenses = expenses;
        this.description = description;
    }

    /**
     * Sets the name of the city
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the city
     * @return The name of the city
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the id of the city
     * @return The id of the city
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the city
     * @param id The id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the description for the city
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the city
     * @return The description of the city
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the country of the city
     * @param country the country name to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the country in which the city is
     * @return The country in which the city is
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the expenses associated to the city
     * @param expenses the expenses to set
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * Returns the list of expenses associated to the city
     * @return The expenses associated to the city
     */
    public List<Expense> getExpenses() {
        return expenses;
    }

    
    /** 
     * @param o
     * @return boolean
     */
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

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.country, this.expenses);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "City{Id: " + this.id + ", Name: " + this.name + ", Description: " + this.description + ", Country: "
                + this.country + ", expenses: " + this.expenses + "}";
    }
}
