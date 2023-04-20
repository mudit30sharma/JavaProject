import java.io.*;
import java.util.*;
    public class QpgModified {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the subject (Maths/Science):");
            String subject = scanner.nextLine();
            System.out.println("Enter the number of 10-marker questions:");
            int num10Marker = scanner.nextInt();
            System.out.println("Enter the number of 5-marker questions:");
            int num5Marker = scanner.nextInt();
            System.out.println("Enter the number of 2-marker questions:");
            int num2Marker = scanner.nextInt();
            ArrayList<String> questions = new ArrayList<>();
            try {
                BufferedReader reader10 = new BufferedReader(new FileReader(subject + "_10_marker_questions.txt"));
                BufferedReader reader5 = new BufferedReader(new FileReader(subject + "_5_marker_questions.txt"));
                BufferedReader reader2 = new BufferedReader(new FileReader(subject + "_2_marker_questions.txt"));
                String line;
                while ((line = reader10.readLine()) != null) {
                    questions.add("10-marker: " + line);
                }
                while ((line = reader5.readLine()) != null) {
                    questions.add("5-marker: " + line);
                }
                while ((line = reader2.readLine()) != null) {
                    questions.add("2-marker: " + line);
                }
                reader10.close();
                reader5.close();
                reader2.close();
            } catch (IOException e) {
                System.out.println("Error reading question files.");
                System.exit(1);
            }
            Collections.shuffle(questions);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(subject + "_question_paper.txt"));
                writer.write("Question Paper - " + subject);
                writer.newLine();
                writer.newLine();
                int qNum = 1;
                for (String question : questions) {
                    if (question.startsWith("10-marker") && num10Marker > 0) {
                        writer.write(qNum + ". " + question.substring(11));
                        writer.newLine();
                        qNum++;
                        num10Marker--;
                    } else if (question.startsWith("5-marker") && num5Marker > 0) {
                        writer.write(qNum + ". " + question.substring(10));
                        writer.newLine();
                        qNum++;
                        num5Marker--;
                    } else if (question.startsWith("2-marker") && num2Marker > 0) {
                        writer.write(qNum + ". " + question.substring(10));
                        writer.newLine();
                        qNum++;
                        num2Marker--;
                    }
                    if (num10Marker == 0 && num5Marker == 0 && num2Marker == 0) {
                        break;
                    }
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing question paper file.");
                System.exit(1);
            }
            System.out.println("Question paper generated successfully.");
        }
    }