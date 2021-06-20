package udemy.exampleEntity;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Enumerated
    @Column(name = "employee_status")
    private EmployeeStatus employeeStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_level")
    private EmployeeLevel employeeLevel;

    public Employee() {
    }

    public Employee(String name, String employeeId, EmployeeStatus employeeStatus, EmployeeLevel employeeLevel) {
        this.name = name;
        this.employeeId = employeeId;
        this.employeeStatus = employeeStatus;
        this.employeeLevel = employeeLevel;
    }

    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", employeeId="
                + employeeId + ", employeeStatus=" + employeeStatus + ", employeeStatus=" + employeeLevel + "]";
    }


}
