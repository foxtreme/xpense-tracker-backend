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
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "expenses")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Expense {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotNull(message = "name is required")
    @Pattern(regexp = "^[a-zA-Z. ]+$", message = "name must be a string")
    private String name; // internet
    
    @NotNull(message = "type is required")
    @Pattern(regexp = "^[a-zA-Z. ]+$", message = "type must be a string")
    private String type; // mandatory

    @NotNull(message = "frequency is required")
    @Pattern(regexp = "^[a-zA-Z. ]+$", message = "frequency must be a string")
    private String frequency; // monthly

    @NotNull(message = "minValue is required")
    @Positive(message = "minValue must be positive")
    private Long minValue; // 45

    @NotNull(message = "maxValue is required")
    @Positive(message = "maxValue must be positive")
    private Long maxValue; // 60
    
    @Column(nullable = true)
    @ManyToMany(targetEntity = Manager.class,  mappedBy = "expenses", cascade = CascadeType.ALL)
    private List<Manager> managers;

    @Column(nullable = true)
    @ManyToMany(targetEntity = City.class, mappedBy = "expenses", cascade = CascadeType.ALL)
    private List<City> cities;

    /**
     * Unparameterized Constructor
     */
    public Expense() {
    }

    /**
     * Constructor
     * @param name Name of the expense
     * @param type Type of the expense
     * @param frequency Frequency of payment
     * @param minValue Minimum value
     * @param maxValue Maximum value
     * @param managers List of managers
     * @param cities List of cities
     */
    public Expense(String name, String type, String frequency, Long minValue, Long maxValue, List<Manager> managers,
            List<City> cities) {
        this.name = name;
        this.type = type;
        this.frequency = frequency;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.managers = managers;
        this.cities = cities;
    }

    /**
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    /** 
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    
    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    
    /** 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    
    /** 
     * @return String
     */
    public String getType() {
        return this.type;
    }

    
    /** 
     * @param frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    
    /** 
     * @return String
     */
    public String getFrequency() {
        return this.frequency;
    }

    
    /** 
     * @param minValue
     */
    public void setMinValue(Long minValue) {
        this.minValue = minValue;
    }

    
    /** 
     * @return double
     */
    public Long getMinValue() {
        return this.minValue;
    }

    
    /** 
     * @param maxValue
     */
    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    
    /** 
     * @return double
     */
    public Long getMaxValue() {
        return this.maxValue;
    }

    
    /** 
     * @param managers
     */
    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    
    /** 
     * @return List<Manager>
     */
    public List<Manager> getManagers() {
        return this.managers;
    }

    
    /** 
     * @param cities
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    
    /** 
     * @return List<City>
     */
    public List<City> getCities() {
        return cities;
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
        if (!(o instanceof Expense)) {
            return false;
        }
        Expense expense = (Expense) o;
        return Objects.equals(this.id, expense.id) && Objects.equals(this.name, expense.name)
                && Objects.equals(this.type, expense.type) && Objects.equals(this.frequency, expense.frequency)
                && Objects.equals(this.minValue, expense.minValue) && Objects.equals(this.maxValue, expense.maxValue)
                && Objects.equals(this.cities, expense.cities) && Objects.equals(this.managers, expense.managers);
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.type, this.frequency, this.minValue, this.maxValue, this.cities,
                this, managers);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Expense{ Id:" + this.id + ", Name: " + this.name + ", Type: " + this.type + ", Frequency:"
                + this.frequency + ", minValue: " + this.minValue + ", maxValue: " + this.maxValue + ", cities: "
                + this.cities + ", managers: " + this.managers + "}";
    }

}
