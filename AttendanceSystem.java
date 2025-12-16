import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class AttendanceSystem {

    public static void main(String[] args) {

        AttendanceService service = new AttendanceService();
        Scanner sc = new Scanner(System.in);

        String attendanceFile = "attendance_records.csv";

        try {
            List<String[]> input = CSVReader.readCSV("input_data.csv");

            String user = input.get(0)[0].trim();
            String pass = input.get(0)[1].trim();

            if (!(user.equals("admin") && pass.equals("12345"))) {
                System.out.println("Invalid Login!");
                return;
            }

            System.out.println("Login Successful!\n");

            LocalDate today = LocalDate.now();
            boolean attendanceAlreadyGiven = false;

            File f = new File(attendanceFile);
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String firstLine = br.readLine(); // "Attendance Date,YYYY-MM-DD"
                br.close();
                if (firstLine != null && firstLine.contains(today.toString())) {
                    attendanceAlreadyGiven = true;
                }
            }

            if (attendanceAlreadyGiven) {
                System.out.println("Attendance already given today! Come back after 24 hours.");
                return;
            }

            for (int i = 1; i < input.size(); i++) {
                String[] data = input.get(i);
                if (data.length < 5 || data[0].trim().isEmpty()) continue;

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String dept = data[2].trim();
                    String sem = data[3].trim();
                    String status = data[4].trim();

                    Student s = new Student(id, name, dept, sem, status);
                    service.addStudent(s);

                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid row: " + Arrays.toString(data));
                }
            }

            System.out.println("--- Mark Attendance ---");
            for (Student s : service.getStudents()) {
                String status = "";
                boolean validInput = false;

                while (!validInput) {
                    System.out.print("Mark attendance for " + s.getName() + " (P for Present / A for Absent): ");
                    status = sc.nextLine().trim().toUpperCase();

                    if (status.equals("P")) {
                        s.setStatus("Present");
                        validInput = true;
                    } else if (status.equals("A")) {
                        s.setStatus("Absent");
                        validInput = true;
                    } else {
                        System.out.println("Invalid input format! Please enter only 'P' or 'A'.");
                    }
                }
            }

            CSVWriter.write(attendanceFile, service.getStudents());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
