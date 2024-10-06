import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Homeworks {
    private String homeworkId; 
    private String title; 
    private String description; 
    private Date dueDate; 
    private String subject; 
    private String studentId; 
    private Date submissionDate; 
    private String status; 

    private List<String> questions; 
    private List<List<String>> options; 
    private List<String> correctAnswers; 

 
    public Homeworks(String homeworkId, String title, String description, Date dueDate, String subject, String studentId) {
        this.homeworkId = homeworkId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.subject = subject;
        this.studentId = studentId;
        this.status = "Pending"; 


        this.questions = new ArrayList<>();
        this.options = new ArrayList<>();
        this.correctAnswers = new ArrayList<>();
    }


    public String getHomeworkId() {
        return homeworkId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getQuestions() {
        return new ArrayList<>(questions); 
    }

    public List<String> getOptions(int questionIndex) {
        return options.get(questionIndex);
    }

    public String getCorrectAnswer(int questionIndex) {
        return correctAnswers.get(questionIndex);
    }


    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
        this.status = "Submitted";
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public boolean addQuestion(String question, List<String> answerOptions, String correctAnswer) {
        if (questions.size() < 10) { 
            questions.add(question);
            options.add(answerOptions);
            correctAnswers.add(correctAnswer);
            return true; 
        } else {
            System.out.println("Đã đạt giới hạn tối đa 10 câu hỏi.");
            return false; 
        }
    }

    public boolean isOverdue() {
        Date currentDate = new Date();
        return currentDate.after(dueDate) && status.equals("Pending");
    }

    public void gradeHomework() {
        this.status = "Graded"; 
    }
}
