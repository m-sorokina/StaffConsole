package lab3work;

public abstract class Employee {
    private final String lastname;
    private final String firstname;
    private final int age;
    private final Sex sexEmployee;
    private double salary;
    private Position position;
    private int teamID;


    public Employee(String lastname, String firstname, int age,
                    String sexEmployee, double salary, Position position) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.sexEmployee = (sexEmployee.toUpperCase().startsWith("M")) ? Sex.MALE : Sex.FEMALE;
        this.salary = salary;
        this.position = position;
        teamID = 0;
    }

    public String toString() {
        return String.join(" ", this.firstname,
                this.lastname,
                Integer.toString(this.age),
                (sexEmployee == Sex.MALE) ? "M" : "F");
    }

    public boolean equals(Employee employee) {

        return (employee != null && this.lastname.equalsIgnoreCase(employee.lastname)
                && this.firstname.equalsIgnoreCase(employee.firstname)
                && this.age == employee.age
                && this.sexEmployee == employee.sexEmployee);
    }

    public double getSalary() {
        return salary;

    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public abstract double getTotalSalary();

    public Position getPosition() {
        return position;
    }

    public int getTeam() {
        return teamID;
    }

    public void setTeam(int ID) {
        teamID = ID;
    }


}
