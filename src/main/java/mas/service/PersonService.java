package mas.service;

import mas.entity.person.Person;
import util.DateUtil;

import java.util.Date;

public class PersonService {
    public static Integer getAge(Person person) {
        if (person.getDateBirth() == null) {
            return null;
        }

        return DateUtil.getYearDifference(person.getDateBirth(), new Date());
    }
}
