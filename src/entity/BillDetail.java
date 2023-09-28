package entity;

public class BillDetail {
    private Service service;
    private int quantity;

    public BillDetail(Service service, int quantity) {
        this.service = service;
        this.quantity = quantity;
    }

    public Service getService() {
        return service;
    }

    public int getQuantity() {
        return quantity;
    }




}
