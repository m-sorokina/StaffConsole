package lab3work;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements Logger {
    private PrintWriter writer;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private boolean logInfo;
    private boolean logError;

    public FileLogger(String fileName, boolean logInfo, boolean logError) {
        try {
            FileOutputStream fileStream = new FileOutputStream(fileName, true);
            this.writer = new PrintWriter(fileStream, true);
            this.logInfo = logInfo;
            this.logError = logError;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void info(String log) {
        if (!logInfo){
            return;
        }
        Date currentTime = new Date();
        String dateTimeString = simpleDateFormat.format(currentTime);
        writer.printf("[%s] INFO: %s%n", dateTimeString, log);

    }

    public void error(String log) {
        if (!logError) {
            return;
        }
        Date currentTime = new Date();
        String dateTimeString = simpleDateFormat.format(currentTime);
        writer.printf("[%s] ERROR: %s%n", dateTimeString, log);
    }
}
