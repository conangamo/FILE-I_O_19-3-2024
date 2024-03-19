package baiTap;

import java.io.*;
import java.util.*;

public class StudentFileIOExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        // Nhập số lượng học viên từ bàn phím
        System.out.print("Nhập số lượng học viên: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Đọc kí tự '\n' sau khi nhập số lượng

        // Nhập thông tin học viên và thêm vào danh sách
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin học viên thứ " + (i + 1) + ":");
            System.out.print("Mã sinh viên: ");
            String id = scanner.nextLine();
            System.out.print("Họ và tên: ");
            String name = scanner.nextLine();
            System.out.print("Giới tính: ");
            String gender = scanner.nextLine();
            System.out.print("Điểm Python: ");
            double pythonGrade = scanner.nextDouble();
            System.out.print("Điểm Java: ");
            double javaGrade = scanner.nextDouble();
            scanner.nextLine(); // Đọc kí tự '\n' sau khi nhập điểm

            Student student = new Student(id, name, gender, pythonGrade, javaGrade);
            students.add(student);
        }

        // Sắp xếp học viên theo điểm trung bình giảm dần
        Collections.sort(students, Collections.reverseOrder());

        // In thông tin học viên đã sắp xếp và ghi vào file
        try {
            FileWriter writer = new FileWriter("D:\\codejava\\fileLuuTruHS\\sorted_students.txt");
            System.out.println("Danh sách học viên đã sắp xếp và ghi ra file:");
            for (Student student : students) {
                double averageGrade = student.getAverageGrade();
                String result;
                if (averageGrade >= 5) {
                    result = "Đậu";
                } else {
                    if (student.getGender().equalsIgnoreCase("nam")) {
                        result = "Trượt";
                    } else {
                        result = "Đậu";
                    }
                }
                String line = "Mã SV: " + student.getId() + ", Họ tên: " + student.getName() + ", Điểm trung bình: " + averageGrade + ", Kết quả: " + result;
                System.out.println(line);
                writer.write(line + "\n");
            }
            writer.close();
            System.out.println("Đã ghi thông tin học viên vào file.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi vào file.");
            e.printStackTrace();
        }

        // Tìm kiếm học viên theo họ tên
        System.out.print("Nhập họ tên học viên cần tìm: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        System.out.println("Thông tin học viên có họ tên \"" + searchName + "\":");
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Mã SV: " + student.getId() + ", Họ tên: " + student.getName() + ", Giới tính: " + student.getGender() + ", Điểm Python: " + student.getPythonGrade() + ", Điểm Java: " + student.getJavaGrade());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy học viên có họ tên \"" + searchName + "\".");
        }

        scanner.close();
    }
}

class Student implements Comparable<Student> {
    private String id;
    private String name;
    private String gender;
    private double pythonGrade;
    private double javaGrade;

    public Student(String id, String name, String gender, double pythonGrade, double javaGrade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.pythonGrade = pythonGrade;
        this.javaGrade = javaGrade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public double getPythonGrade() {
        return pythonGrade;
    }

    public double getJavaGrade() {
        return javaGrade;
    }

    public double getAverageGrade() {
        return (javaGrade * 2 + pythonGrade) / 3;
    }

    @Override
    public int compareTo(Student other) {
        // So sánh theo điểm trung bình giảm dần
        return Double.compare(this.getAverageGrade(), other.getAverageGrade());
    }
}



