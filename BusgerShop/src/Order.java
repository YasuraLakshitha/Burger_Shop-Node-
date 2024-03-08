public class Order {
    private String customerName;
    private String orderID;
    private int burgerQty;
    private String phone;
    private double total;
    private int status;
    public Order(String customerName, String orderID, int burgerQty, String phone, double total,int status) {
        this.customerName = customerName;
        this.orderID = orderID;
        this.burgerQty = burgerQty;
        this.phone = phone;
        this.total = total;
        this.status = status;
    }

    public Order(){}

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getBurgerQty() {
        return burgerQty;
    }

    public String getPhone() {
        return phone;
    }

    public double getTotal() {
        return total;
    }
    public int getStatus() {
        return status;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public boolean setPhone(String customerId) {
        if (customerId.charAt(0)=='0' && customerId.length()==10){
            this.phone = customerId;
            return true;
        }
        return false;
    }


    public void setTotal(double total){this.total = total;}
    public boolean setStatus(int status){
        if(status==0 ||status==1||status==2) {
            this.status = status;
            return true;
        }
        return false;
    }
    public boolean setBurgerQty(int qty){
        if(qty>0){
            burgerQty = qty;
            return true;
        }
        return false;
    }
}
