package lab3work;

public class ConsoleLogger implements Logger {
    String log;

    public ConsoleLogger(String log){

        this.log = log;
    }

    public void info(String log){
        System.out.println("INFO: " + log);
    }

    public void error(String log){
         System.out.println("ERROR: " + log);
    }

    public String toString() {
        return log;
    }
}
