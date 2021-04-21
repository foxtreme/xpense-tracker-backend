package com.ccharry.xpensetracker.errors;

public class ManagerNotFoundException extends RuntimeException{
    
    public ManagerNotFoundException(Long id){
        super("Could not find Manager "+id);
    }
}
