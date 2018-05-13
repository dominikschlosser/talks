package com.github.dmn1k.talks.fn.section5;

import com.github.dmn1k.talks.fn.section5.domain.Car;
import com.github.dmn1k.talks.fn.section5.domain.Insurance;
import com.github.dmn1k.talks.fn.section5.domain.Person;

public class OptionalImperative {
    public static void main(String[] args) {
        new OptionalImperative().getCarInsurance(new Person());

    }

    String getCarInsurance(Person person){
        if(person != null){
            Car car = person.getCar();
            if(car != null){
                Insurance insurance = car.getInsurance();
                if(insurance != null){
                    return insurance.getName();
                }
            }
        }

        return "Unknown";
    }
}
