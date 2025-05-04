package lab3work;

public class Manager extends Employee {

    int teamSize;

    public Manager(String lastname, String firstname, int age, String sexEmployee, double salary, int teamSize, Position position) {
        super(lastname, firstname, age, sexEmployee, salary, position);
        this.teamSize = teamSize;
    }

    public Manager(String lastname, String firstname, int age, String sexEmployee, double salary, Position position) {
        super(lastname, firstname, age, sexEmployee, salary, position);
        teamSize = 0;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Employee employee) {
        return super.equals(employee);
    }

    @Override
    public double getSalary() {
        return super.getSalary();
    }

    @Override
    public void setSalary(double salary) {
        super.setSalary(salary);
    }

    @Override
    public double getTotalSalary() {
        int bonusRate = teamSize / 5;
        double bonus = (0.05 * getSalary()) * bonusRate;
        return getSalary() + bonus;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public void setTeam(int ID) {
        super.setTeam(ID);
    }

    @Override
    public int getTeam() {
        return super.getTeam();
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize - 1;
    }


}
