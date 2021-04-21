package com.ccharry.xpensetracker.errors;

public class CityNotFoundException extends RuntimeException{
    
    public CityNotFoundException(Long id){
        super("Could not find City "+id);
    }
}
