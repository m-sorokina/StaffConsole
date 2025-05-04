package lab3work;

import java.util.Scanner;

public class Program {
    public static Scanner in = new Scanner(System.in);
    public static final String formatDeveloper = "\"surname name age sex salary {skill1, skill2}\"";
    public static final String formatManager = "\"surname name age sex salary\"";

    public static void main(String[] args) {


        Company company = createCompany();
        if (company != null) {
            addCompanyEmployeeByDefault(company);
            company.printCompanyEmployees();
            int operationSelect = callMenu();
            while (operationSelect != 9) {
                in.nextLine();
                switch (operationSelect) {
                    case 1 -> company.printCompanyEmployees();
                    case 2 -> addCompanyEmployees(company);
                    case 3 -> {
                        System.out.println("\nEnter a developer in format " + formatDeveloper + ":");
                        company.addEmployee(addCompanyEmployee(Position.DEVELOPER));
                    }
                    case 4 -> {
                        System.out.println("\nEnter a manager in format " + formatManager + ":");
                        company.addEmployee(addCompanyEmployee(Position.MANAGER));
                    }
                    case 5 -> removeCompanyEmployee(company);
                    case 6 -> changeEmployeeSalary(company);
                    case 7 -> addDeveloperSkills(company);
                    case 8 -> groupEmployeeByTeam(company);
                    default -> System.out.println("\nOperation with such number doesn't exist");
                }
                company.printCompanyEmployees();
                operationSelect = callMenu();
            }
        } else {
            System.out.println("There is no company to which employees could be added or removed from");
        }

    }

    public static int callMenu() {

        System.out.print("""
                                
                Select operation:
                1 - print a list of existing company employees
                2 - enter new company employees with a list
                3 - add developer
                4 - add manager
                5 - remove employee by employee's index
                6 - change salary by employee's index
                7 - add new skill to developer by employee's index
                8 - group employees to team
                9 - end the program
                                
                Enter the operation number:\s""");

        return in.nextInt();
    }

    public static Company createCompany() {
        System.out.print("Enter company's name (press \"Enter\" to end): ");
        String companyName = in.nextLine();

        if (companyName.isBlank()) return null;

        return new Company(companyName);

    }

    public static void addCompanyEmployeeByDefault(Company company) {
        Employee[] employees = new Employee[6];
        employees[0] = new Manager("Weider", "Dart", 40, "m", 9500, Position.MANAGER);
        employees[1] = new Developer("Mortimer", "Adam", 28, "m", 4600, new String[]{"SQL", "JavaScript", "C#"}, Position.DEVELOPER);
        employees[2] = new Developer("Daniels", "Jack", 35, "m", 5800, new String[]{"HTML", "C++"}, Position.DEVELOPER);
        employees[3] = new Developer("Among", "Us", 35, "m", 4800, new String[]{""}, Position.DEVELOPER);
        employees[4] = new Developer("Duke", "Grand", 38, "m", 6800, new String[]{"absolutely all!", "guru", "the best"}, Position.DEVELOPER);
        employees[5] = new Developer("Humpty", "Dumpty", 45, "m", 6300, new String[]{"XLST", "XML"}, Position.DEVELOPER);
        for (Employee employee : employees) {
            company.addEmployee(employee);
        }

    }

    public static void addCompanyEmployees(Company company) {
        System.out.println("""
                Enter list of company's employees:
                (press "Enter" to enter the next employee)
                (press "Enter" on the empty row to end)\s""");

        String enterEmployees = "y";
        while (enterEmployees.equalsIgnoreCase("y")) {
            Position employeePosition = selectEmployeePosition();

            Employee employee = addCompanyEmployee(employeePosition);
            while (employee != null) {
                if (!company.addEmployee(employee)) {
                    System.out.println("The employee was not added");
                }
                employee = addCompanyEmployee(employeePosition);
            }
            System.out.print("Do you want to select another position to enter employees? (y/n): ");
            do {
                enterEmployees = in.nextLine();
            } while (!(enterEmployees.equalsIgnoreCase("y") || enterEmployees.equalsIgnoreCase("n")));
        }
    }

    public static Position selectEmployeePosition() {
        System.out.println("""
                Select the employee's position:
                1. Manager
                2. Developer""");

        Position position = null;
        int operationNumber = in.nextInt();
        while (position == null) {
            switch (operationNumber) {
                case 1 -> {
                    position = Position.MANAGER;
                    System.out.println("Enter a manager in format " + formatManager + ":");
                }
                case 2 -> {
                    position = Position.DEVELOPER;
                    System.out.println("Enter a developer in format " + formatDeveloper + ":");
                }
                default -> System.out.println("The entered position doesn't exist, please select correct position");
            }
        }
        in.nextLine();
        return position;
    }

    public static Employee addCompanyEmployee(Position position) {
        String str = in.nextLine();
        if (!str.isBlank()) {
            try {
                String[] employeeToSplit = (str.contains("{")) ? str.substring(0, str.indexOf("{")).split("\\s") : str.split("\\s");
                String lastname = employeeToSplit[0].trim();
                String firstname = employeeToSplit[1].trim();
                int age = Integer.parseInt(employeeToSplit[2].trim());
                String sexEmployee = employeeToSplit[3].trim();
                double salary = Double.parseDouble(employeeToSplit[4].trim());
                if (position == Position.DEVELOPER) {
                    String[] skills = str.substring(str.indexOf("{") + 1, str.length() - 1).trim().split("\\s+,\\s+|,\\s+|\\s+,|,");
                    return new Developer(lastname, firstname, age, sexEmployee, salary, skills, position);
                } else if (position == Position.MANAGER) {
                    return new Manager(lastname, firstname, age, sexEmployee, salary, position);
                }
            } catch (Exception ignore) {
                System.out.println("Some needed fields are missing or incorrect\nThe employee was not added");
            }
        }
        return null;
    }

    public static void removeCompanyEmployee(Company company) {
        System.out.print("\nTo remove company's employee enter employee's index: ");
        try {
            if (!company.removeEmployee(Integer.parseInt(in.nextLine()) - 1)) {
                System.out.println("The employee with such index was not found");
            }
        } catch (Exception ignore) {
            System.out.println("Some entered data are incorrect\nThe employee is not removed");
        }

    }

    public static void changeEmployeeSalary(Company company) {
        System.out.print("\nTo change the salary enter employee's index and new salary in format \"index, salary\": ");
        try {
            String[] temp = in.nextLine().trim().split(",\\s+|,");
            int index = Integer.parseInt(temp[0].trim()) - 1;
            double salary = Double.parseDouble(temp[1].trim());
            if (!company.changeEmployeeSalary(index, salary)) {
                System.out.println("The employee with such index was not found");
            }
        } catch (Exception ignore) {
            System.out.println("Some entered data are incorrect\nThe salary was not changed");
        }
    }

    public static void addDeveloperSkills(Company company) {
        System.out.print("\nTo add new skill to developer enter employee's index and new skill in format \"index, skill\": ");
        try {
            String[] temp = in.nextLine().trim().split(",\\s+|,");
            int index = Integer.parseInt(temp[0].trim()) - 1;
            String newSkill = temp[1].trim();
            if (!company.addEmployeeSkills(index, newSkill)) {
                System.out.println("The employee with such index was not found");
            }
        } catch (Exception ignore) {
            System.out.println("Some entered data are incorrect\nThe skill was not added");
        }
    }

    public static void groupEmployeeByTeam(Company company) {
        System.out.print("\nEnter team name and team members employee indexes in format \"team name, index1, index2\": ");
        try {
            String[] temp = Program.in.nextLine().trim().split(",\\s+|,");
            String teamName = temp[0].trim();
            int[] teamMembersIndexes = new int[temp.length - 1];
            if (teamMembersIndexes.length > 0) {
                for (int i = 0; i < temp.length - 1; i++) {
                    teamMembersIndexes[i] = Integer.parseInt(temp[i + 1].trim()) - 1;
                }
                company.addEmployeeToTeam(teamName, teamMembersIndexes);
            } else throw new Exception();
        } catch (Exception ignore) {
            System.out.println("Some entered data are incorrect\nThe employees were not grouped by team");
        }
    }
}




