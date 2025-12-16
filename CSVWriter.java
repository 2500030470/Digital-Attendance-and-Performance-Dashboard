import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CSVWriter {

    public static void write(String fileName, List<Student> students) {
        LocalDate today = LocalDate.now();
        try (FileWriter fw = new FileWriter(fileName, false)) { 

            
            fw.write("Attendance Date," + today + "\n");
            fw.write("Attendance Time," + LocalTime.now() + "\n\n");

            
            fw.write("RollNo,Name,Department,Semester,Status\n");

        
            for (Student s : students) {
                fw.write(
                    s.getId() + "," +
                    s.getName() + "," +
                    s.getDept() + "," +
                    s.getSem() + "," +
                    s.getStatus() + "\n"
                );
            }

            System.out.println("\nAttendance saved successfully for " + today);

        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}
