package DE190556;


public class InsuranceClaim {
    private String claimId;
    private double amount;
    private String claimStatus;

    // Constructor
    public InsuranceClaim(String id, double claimAmount) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Claim ID cannot be null or empty");
        }
        if (claimAmount <= 0) {
            throw new IllegalArgumentException("Claim amount must be greater than 0");
        }
        this.claimId = id;
        this.amount = claimAmount;
        this.claimStatus = "Pending"; // mặc định
    }

    // Getter
    public String getClaimId() {
        return claimId;
    }

    public double getAmount() {
        return amount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    // Cập nhật trạng thái yêu cầu
    public boolean processClaim(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        if ("Pending".equals(this.claimStatus)) {
            this.claimStatus = status;
            return true;
        }
        return false; // nếu không ở trạng thái Pending thì không cho cập nhật
    }

    // Tính toán số tiền chi trả
    public double calculatePayout() {
        if ("Approved".equalsIgnoreCase(this.claimStatus)) {
            return this.amount * 0.85; // 15% bị trừ
        }
        return 0;
    }

    // Cập nhật số tiền yêu cầu
    public void updateClaimAmount(double newAmount) {
        if (newAmount <= 0) {
            throw new IllegalArgumentException("Claim amount must be greater than 0");
        }
        this.amount = newAmount;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId='" + claimId + '\'' +
                ", amount=" + amount +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}
