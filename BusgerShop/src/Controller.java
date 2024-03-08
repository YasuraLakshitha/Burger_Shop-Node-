
public class Controller {
    static Order[] sorted = new Order[0];
    static List  orderList = new List();
    //Clear Console
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    static boolean addOrder(Order obj) {
        return orderList.add(obj);
    }
    //Generate Order ID
    public static String generateOrderId() {
        if (orderList.size()==0){
            return "B0001";
        }
        String lastOrderId = orderList.getLast().getObj().getOrderID();
        int number = Integer.parseInt(lastOrderId.split("B")[1]); //1
        number++;//2
        return String.format("B%04d",number); //printf("",) //B0002
    }

    //search if exist Customer
   static String isExistCustomer(String customerId){
        for (int i = 0; i < orderList.size(); i++) {
            if (customerId.equals(orderList.getNode(i).getObj().getPhone()) &
                    orderList.getNode(i).getObj().getStatus()!=BurgerShop.CANCEL) {
                return orderList.getNode(i).getObj().getCustomerName();
            }
        }
        return null;
    }

    //remove duplicates
    static void removeDuplicates(){
        copyObjects();
        for(int i=0;i<sorted.length-1;i++){
            for(int j=i+1;j<sorted.length;j++){
                if(sorted[i].getPhone().equals(sorted[j].getPhone())){
                    sorted[i].setTotal(sorted[i].getTotal()+sorted[j].getTotal());
                    remove(j);
                }
            }
        }
    }

    //remove
    private static void remove(int index){
        Order[] temp = new Order[sorted.length-1];
        for(int i=index;i< sorted.length-1;i++){
            sorted[i]=sorted[i+1];
        }
        for(int i=0;i< temp.length;i++){
            temp[i] = sorted[i];
        }
        sorted = temp;
    }

    //copying objects
    private static void copyObjects() {
        Order[] temp = new Order[orderList.size()];
        sorted = temp;
        for(int i=0;i<orderList.size();i++) {
            sorted[i] =new Order(orderList.getNode(i).getObj().getCustomerName(),orderList.getNode(i).getObj().getOrderID(),
                    orderList.getNode(i).getObj().getBurgerQty(),orderList.getNode(i).getObj().getPhone(),
                    orderList.getNode(i).getObj().getTotal(),orderList.getNode(i).getObj().getStatus());
        }
    }

    //sort
    static void sort(){
        for(int i=0;i< sorted.length;i++){
            int index = i;
            for(int j=i;j< sorted.length;j++){
                if(sorted[i].getTotal()<sorted[j].getTotal()){
                    index = j;
                }
            }
            Order temp = sorted[index];
            sorted[index] = sorted[i];
            sorted[i] = temp;
        }
    }

    //search order
    static Order searchOrder(String orderId) {
        for(int i=0;i<orderList.size();i++){
            if(orderId.equals(orderList.getNode(i).getObj().getOrderID())){
                return orderList.getNode(i).getObj();
            }
        }
        return null;
    }

    //Get Status
    static String getStatus(Order obj){
        switch(obj.getStatus()){
            case BurgerShop.CANCEL:
                return "Cancel";
            case BurgerShop.PREPARING:
                return "Preparing";
            case BurgerShop.DELIVERED:
                return "Delivered";
        }
        return null;
    }

    //search Customer by customer ID
    static Order searchCustomer(String customerId){
        for(int i=0;i<orderList.size();i++){
            if(customerId.equals(orderList.getNode(i).getObj().getPhone())){
                return orderList.getNode(i).getObj();
            }
        }
        return null;
    }

    static Order[] searchCustomersDetails(String customerId){
        Order[] customer = new Order[0];
        for(int i=0;i<orderList.size();i++){
            if(customerId.equals(orderList.getNode(i).getObj().getPhone())){
                customer=extendArray(customer);
                customer[customer.length-1] = new Order(null,orderList.getNode(i).getObj().getOrderID(),
                        orderList.getNode(i).getObj().getBurgerQty(),null,
                        orderList.getNode(i).getObj().getTotal(),0);
            }
        }
        return customer;
    }

    static Order[] getDeliveredOrders(){
        Order[] delivered = new Order[0];
        for(int i=0;i< orderList.size();i++){
            if(orderList.getNode(i).getObj().getStatus()==BurgerShop.DELIVERED){
                delivered=extendArray(delivered);
                delivered[delivered.length-1] = new Order(orderList.getNode(i).getObj().getCustomerName(),orderList.getNode(i).getObj().getOrderID(),
                        orderList.getNode(i).getObj().getBurgerQty(),orderList.getNode(i).getObj().getPhone(),
                        orderList.getNode(i).getObj().getBurgerQty()*500,2);
            }
        }
        return delivered;
    }
    static Order[] getPreparedOrders(){
        Order[] prepared = new Order[0];
        for(int i=0;i< orderList.size();i++){
            if(orderList.getNode(i).getObj().getStatus()==BurgerShop.PREPARING){
                prepared=extendArray(prepared);
                prepared[prepared.length-1] = new Order(orderList.getNode(i).getObj().getCustomerName(),orderList.getNode(i).getObj().getOrderID(),
                        orderList.getNode(i).getObj().getBurgerQty(),orderList.getNode(i).getObj().getPhone(),
                        orderList.getNode(i).getObj().getBurgerQty()*500,2);
            }
        }
        return prepared;
    }

    static Order[] getCanceledOrders(){
        Order[] canceled = new Order[0];
        for(int i=0;i< orderList.size();i++){
            if(orderList.getNode(i).getObj().getStatus()==BurgerShop.CANCEL){
                canceled=extendArray(canceled);
                canceled[canceled.length-1] = new Order(orderList.getNode(i).getObj().getCustomerName(),orderList.getNode(i).getObj().getOrderID(),
                        orderList.getNode(i).getObj().getBurgerQty(),orderList.getNode(i).getObj().getPhone(),
                        orderList.getNode(i).getObj().getBurgerQty()*500,2);
            }
        }
        return canceled;
    }

    private static Order[] extendArray(Order[] arr) {
        Order[] temp = new Order[arr.length+1];
        for(int i=0;i< arr.length;i++){
            temp[i] = arr[i];
        }
        return temp;
    }
    //Exit
    public static void exit() {
        Controller.clearConsole();
        System.out.println("\n\t\tYou left the program...\n");
        System.exit(0);
    }
}


