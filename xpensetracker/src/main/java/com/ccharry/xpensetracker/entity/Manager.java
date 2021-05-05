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
@Table(name = "managers")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Manager {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotNull(message = "name is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name must be a string")
    private String name;

    @Column(nullable = true)
    @ManyToMany(targetEntity = Expense.class, cascade = CascadeType.ALL)
    private List<Expense> expenses;

    public Manager() {
    }

    public Manager(String name, List<Expense> expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
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
     * @param expenses
     */
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    
    /** 
     * @return List<Expense>
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
        if (!(o instanceof Manager)) {
            return false;
        }
        Manager manager = (Manager) o;
        return Objects.equals(this.id, manager.id) && Objects.equals(this.name, manager.name)
                && Objects.equals(this.expenses, manager.expenses);
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.expenses);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Manager{ Id:" + this.id + ", name: " + this.name + ", expenses: " + this.expenses + "}";
    }
}
