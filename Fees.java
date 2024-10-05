import java.util.Date;

public class Fees {
    private String feeId;
    private double totalFee;
    private double amountPaid;
    private double dueAmount;
    private String feeStatus;
    private Date paymentDate;
    private Date dueDate;

    public Fees(String feeId, double totalFee, Date dueDate) {
        this.feeId = feeId;
        this.totalFee = totalFee;
        this.dueAmount = totalFee;
        this.feeStatus = "Pending"; 
        this.dueDate = dueDate;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void payFee(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }
        this.amountPaid += amount;
        this.dueAmount = totalFee - amountPaid;
        this.paymentDate = new Date();
        if (this.dueAmount <= 0) {
            this.feeStatus = "Paid";
        } else {
            this.feeStatus = "Partial";
        }
        System.out.println("Payment successful. Remaining due: " + this.dueAmount);
    }

    public double calculateDueAmount() {
        return totalFee - amountPaid;
    }

    public void checkFeeStatus() {
        if (this.dueAmount > 0) {
            System.out.println("Fees pending. Amount due: " + this.dueAmount);
        } else {
            System.out.println("Fees fully paid.");
        }
    }

    public void generateFeeInvoice() {
        System.out.println("Generating invoice...");
        System.out.println("Total Fee: " + totalFee);
        System.out.println("Amount Paid: " + amountPaid);
        System.out.println("Due Amount: " + dueAmount);
    }
}

