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
public class Manager {
    private @Id @GeneratedValue Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "expense_manager", joinColumns = @JoinColumn(name = "expense_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "manager_id", referencedColumnName = "id"))
    private List<Expense> expenses;

    public Manager() {
    }

    public Manager(String name, List<Expense> expenses) {
        this.name = name;
        this.expenses = expenses;
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
