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

    @Column(nullable = false)
    private String name;

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
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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
        if (!(o instanceof Manager)) {
            return false;
        }
        Manager manager = (Manager) o;
        return Objects.equals(this.id, manager.id) && Objects.equals(this.name, manager.name)
                && Objects.equals(this.expenses, manager.expenses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.expenses);
    }

    @Override
    public String toString() {
        return "Manager{ Id:" + this.id + ", name: " + this.name + ", expenses: " + this.expenses + "}";
    }
}
