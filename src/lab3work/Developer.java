package lab3work;

public class Developer extends Employee {

    private String[] skills;

    public Developer(String lastname, String firstname, int age, String sexEmployee, double salary, String[] skills, Position position) {
        super(lastname, firstname, age, sexEmployee, salary, position);
        this.skills = skills;

    }

    public void addDeveloperSkill(String skill) {
        String skills = String.join(",", this.skills) + "," + skill;
        this.skills = skills.trim().split("\\s+,\\s+|,\\s+|\\s+,|,");
    }

    @Override
    public String toString() {
        String skillsToPrint = " {" + String.join(", ", this.skills) + "}";
        return super.toString() + skillsToPrint;
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
    public double getTotalSalary() {
        double bonus = (0.02 * getSalary()) * skills.length;
        return getSalary() + bonus;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public int getTeam() {
        return super.getTeam();
    }

    @Override
    public void setTeam(int ID) {
        super.setTeam(ID);
    }

}
