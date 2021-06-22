package mas.service;

import mas.entity.person.Person;

import java.util.Date;

public class PersonService {
    public static Integer getAge(Person person) {
        if (person.getDateBirth() == null) {
            return null;
        }

        return DateService.getYearDifference(person.getDateBirth(), new Date());
    }
}
