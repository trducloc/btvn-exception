package constant;

public enum ClientType {
    INDIVIDUAL("Cá nhân"),
    ADMINISTRATIVE_REPRESENTATIVE("Đại diện đơn vị hành chính"),
    BUSSINESS_REPRESENTATIVE("Đại diện kinh doanh");

    public String value;

    ClientType(String value) {
        this.value = value;
    }

}
