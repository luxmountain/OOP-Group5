public class AddStudentFee {
    private String student_id;
    private String student_name;
    private Fees studentFees;

    public Fees getStudentFees() {
        return studentFees;
    }

    public void setStudentFees(Fees studentFees) {
        this.studentFees = studentFees;
    }

    public void payStudentFee(double amount) {
        this.studentFees.payFee(amount);
    }

    public void checkStudentFeeStatus() {
        this.studentFees.checkFeeStatus();
    }
}
