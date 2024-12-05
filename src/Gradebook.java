public class Gradebook {

    String[] labels = { "Last", "First","Test1", "Test2", "Quiz1", "Quiz2", "Homework1", "Homework2", "Homework3",
            "Homework4", "Homework5", "Homework6", "Homework7", "Homework8", "Homework9", "Homework10" };

    String[][] book;

    public Gradebook(String[][] b) {
        if (b.length > 0 && b[0].length == 16) {
            book = b;
        }

        else {
            System.out.println("Gradebook is not the right size. Ensure that formatting is correct.");
            book = new String[1][16];
        }
    }

    public boolean changeGrade(String lastName, String assnName, int newGrade) {
        // Your code here
        int assignmentIndex = -1;
        for(int i = 0; i < labels.length; i ++){
            if(labels[i].equals(assnName)){
                assignmentIndex = i;
                break;
            }
        }
        if(assignmentIndex == -1){
            return false;
        }
        for(int i = 0; i < book.length; i++){
            if(book[i][0].equals(lastName)){
                book[i][assignmentIndex] = String.valueOf(newGrade);
                return true;
                
            }
        }
        
        return false;
    }


    public double findAssignmentAverage(String assnName) {
        // Your code here
        int assignmentIndex = -1;
        for(int i = 0; i < labels.length; i++){
            if(labels[i].equals(assnName)){
                assignmentIndex = i;
                break;
            }
        }
        if(assignmentIndex == -1){
            System.out.println("Assignment not found.");
            return -1;
        }

        double total = 0;
        int count = 0;
        for(int i = 0; i < book.length; i++){
            String grade = book[i][assignmentIndex];

            if(grade != null && !grade.equals("")){
                boolean isAnum = true;
                for(int j = 0; j < grade.length(); j ++){
                    if(!Character.isDigit(grade.charAt(j))){
                        isAnum = false;
                        break;
                    }
                }
                if(isAnum){
                    total += Integer.parseInt(grade);
                    count++;

                }
            }
        }
        return count > 0 ? total / count : 0;

    }

    public double findStudentAverage(String lastName) {
        // Your code here
        int studentIndex = -1;

        for(int i = 0; i < book.length; i ++){
            if(book[i][0].equals(lastName)){
                studentIndex = i;
                break;
            }
        }
        if(studentIndex == -1){
            System.out.println("Student not found.");
            return -1;
        }

        double testTotal = 0, quizTotal =0, homeworkTotal = 0;
        int testCount = 0, quizCount = 0, homeworkCount = 0;

        for(int j = 2; j < book[studentIndex].length; j++){
            String grade = book[studentIndex][j];

            if(grade != null && !grade.equals("")){
                boolean isDigi = true;

                for(int k = 0; k < grade.length(); k ++){
                    if(!Character.isDigit(grade.charAt(k))){
                        isDigi = false;
                        break;
                    }
                }

                if(isDigi){
                    int numericGrade = Integer.parseInt(grade);

                    if(labels[j].startsWith("Test")){
                        testTotal += numericGrade;
                        testCount ++;
                    }else if(labels[j].startsWith("Quiz")){
                        quizTotal += numericGrade;
                        quizCount ++;
                    }else if(labels[j].startsWith("Homework")){
                        homeworkTotal += numericGrade;
                        homeworkCount++;
                    }
                }
            }
        }
        double testAverage = (testCount > 0) ? (testTotal / testCount) : 0;
        double quizAverage = (quizCount > 0) ? (quizTotal / quizCount) : 0;
        double homeworkAverage = (homeworkCount > 0) ? (homeworkTotal / homeworkCount) : 0;

        return (testAverage * 0.3) + (quizAverage * 0.3) + (homeworkAverage * 0.4);
    }

    public void printStudentInfo(String lastName) {
        // Your code here
        int studentIndex = -1;

        for(int i = 0; i < book.length; i++){
            if(book[i][0].equals(lastName)){
                studentIndex = i;
                break;
            }
        }
        if(studentIndex == -1){
            System.out.println("Student does not exist.");
            return;
        }

        System.out.println("Student: " + book[studentIndex][0] + ", " + book[studentIndex][1]);

        for(int j = 2; j < book[studentIndex].length; j++){
            System.out.println(labels[j] + ": " + book[studentIndex][j]);
        }
    }
}
  

