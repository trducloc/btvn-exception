package entity;

public class Bill {
    private Client client;
    private BillDetail[] billDetails;
    private int totalQuantity;

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public Bill(Client client, BillDetail[] billDetails) {
        this.client = client;
        this.billDetails = billDetails;
    }

    public Client getClient() {
        return client;
    }

    public BillDetail[] getBillDetails() {
        return billDetails;
    }


}
