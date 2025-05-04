package lab3work;

import java.util.ArrayList;
import java.util.Arrays;

public class Team {
    private final String teamName;
    private ArrayList<Employee> teamMembers;

    private final int ID;

    private static int nextID = 1;

    public Team(String teamName, Employee[] teamMembers) {
        ID = nextID++;
        this.teamName = teamName;
        for (int i = 0; i < teamMembers.length; i++) {
            this.teamMembers.add(teamMembers[i]);
        }
    }

    public Team(String teamName) {
        ID = nextID++;
        this.teamName = teamName;
        this.teamMembers = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamMembersQty() {
        return teamMembers.size();

    }

    public void addTeamMember(Employee[] teamMembers) {
        this.teamMembers.addAll(Arrays.asList(teamMembers));
        for (Employee employee : teamMembers) {
            employee.setTeam(ID);
            setMembersQtyManager();
        }

    }

    public void setMembersQtyManager() {
        for (Employee employee : teamMembers) {
            if (employee.getPosition() == Position.MANAGER) {
                ((Manager) employee).setTeamSize(getTeamMembersQty());
            }
        }
    }

    public boolean isTeamMemberExist(Employee employee) {
        for (Employee e : teamMembers) {
            return (e.equals(employee));
        }
        return false;
    }

    public String toString() {
        String teamName = this.teamName + "\n";
        String teamMembers = "";
        for (Employee member : this.teamMembers) {
            teamMembers += member + "\n";
        }
        return teamName + teamMembers;
    }

}

