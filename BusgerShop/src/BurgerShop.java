import java.util.*;
class BurgerShop {
    final static double BURGERPRICE = 500;
    public static final int CANCEL = 0;
    public static final int PREPARING = 1;
    public static final int DELIVERED = 2;

    // placeOrder
    public static void placeOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tPLACE ORDER\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n\n");
        System.out.print("ORDER ID - ");
        String orderId = Controller.generateOrderId();
        System.out.println(orderId + "\n================\n\n");
        Order orderObj = new Order(); // creating order object
        orderObj.setOrderID(orderId);
        L1: do {
            System.out.print("Enter Customer ID (phone no): ");
            String customerId = input.next();
            if(!orderObj.setPhone(customerId)){System.out.println("Invalid phone number Enter Again");continue L1;}
            String customerName;
            if(Controller.isExistCustomer(customerId)!=null){
                System.out.println("Enter Customer Name: " + Controller.isExistCustomer(customerId));
                customerName = Controller.isExistCustomer(customerId);
            }else{
                System.out.print("\nEnter Customer Name: ");
                customerName = input.next();
            }
            orderObj.setCustomerName(customerName);
            do {
                System.out.print("Enter Burger Quantity - ");
                if (!orderObj.setBurgerQty(input.nextInt())) {System.out.println("\n\tInvalid input\n");continue;}
                orderObj.setTotal(orderObj.getBurgerQty()*BURGERPRICE);
                break;
            } while (true);
            System.out.printf("Total value - %.2f", orderObj.getTotal());
            System.out.println();
            L3:do {
                System.out.print("\tAre you confirm order - ");
                String option = input.next().toUpperCase();
                if (option.equalsIgnoreCase("Y")) {
                   orderObj.setStatus(PREPARING);
                   if(Controller.addOrder(orderObj)){ System.out.println("\n\tYour order is enter to the system successfully...");break L1;}
                } else if (option.equalsIgnoreCase("N")) {
                    System.out.println("\n\tYour order is not enter the system...");
                    Controller.clearConsole();
                    return;
                } else {
                    System.out.println("\tInvalid option..input again...");
                    break L1;
                }
            } while (true);

        } while (true);
        do {
            System.out.println();
            System.out.print("Do you want to place another order (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                Controller.clearConsole();
                placeOrder();
            } else if (option.equalsIgnoreCase("N")) {
                Controller.clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
            }
        } while (true);

    }
    // Search best customer
    public static void searchBestCustomer() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tBEST Customer\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");
        Controller.removeDuplicates();
        Controller.sort();
        System.out.println("\n----------------------------------------");
        String line1 = String.format("%-14s%-15s%8s", " CustomerID", "Name", "Total");
        System.out.println(line1);
        System.out.println("----------------------------------------");
        for (int i = 0; i < Controller.sorted.length; i++) {
            String line = String.format("%1s%-14s%-15s%8.2f", " ", Controller.sorted[i].getOrderID(), Controller.sorted[i].getCustomerName(),
                    Controller.sorted[i].getTotal());
            System.out.println(line);
            System.out.println("----------------------------------------");

        }
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("\n\tDo you want to go back to main menu? (Y/N)> ");
            String exitOption = input.nextLine();
            if (exitOption.equalsIgnoreCase("Y")) {
                Controller.clearConsole();
                homePage();
            } else if (exitOption.equalsIgnoreCase("N")) {
                Controller.clearConsole();
                searchBestCustomer();
            } else {
                System.out.println("\tInvalid option..input again...");
            }
        } while (true);

    }
    // search order
    public static void searchOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tSEARCH ORDER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
         do {
            System.out.print("Enter order Id - ");
            String orderId = input.next();
            System.out.println();
            Order obj = Controller.searchOrder(orderId);
            if(obj!=null) {
                String status = Controller.getStatus(obj);
                System.out.println("---------------------------------------------------------------------------");
                String line1 = String.format("%-10s%-14s%-12s%-10s%-14s%-10s", " OrderID", " CustomerID", " Name",
                        "Quantity", "  OrderValue", "  OrderStatus");
                System.out.print(line1);
                System.out.println(" |");
                System.out.println("---------------------------------------------------------------------------");
                String line = String.format("%1s%-10s%-14s%-15s%-10d%-14.2f%-10s", " ", obj.getOrderID(), obj.getPhone(), obj.getCustomerName(),
                        obj.getBurgerQty(), obj.getTotal(), status);
                System.out.print(line);
                System.out.println("|");
                System.out.println("---------------------------------------------------------------------------");
                break ;
            }else {
                do {
                    System.out.print("\n\nInvalid Order ID. Do you want to enter again? (Y/N)>");
                    String option = input.next();
                    if (option.equalsIgnoreCase("Y")) {
                        Controller.clearConsole();
                        searchOrder();
                    } else if (option.equalsIgnoreCase("N")) {
                        Controller.clearConsole();
                        homePage();
                    } else {
                        System.out.println("\tInvalid option..input again...");
                    }
                } while (true);
            }
        } while (true);
        do {
            System.out.println();
            System.out.print("Do you want to search another order details (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                Controller.clearConsole();
                searchOrder();
            } else if (option.equalsIgnoreCase("N")) {
                Controller.clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
            }
        } while (true);
    }
    // search Customer
    public static void searchCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tSEARCH CUSTOMER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        L1: do {
            System.out.print("Enter customer Id - ");
            String customerId = input.next();
            System.out.println("\n");
            Order obj = Controller.searchCustomer(customerId);
            if(obj!=null) {
                System.out.println("CustomerID - " + obj.getPhone());
                System.out.println("Name       - " + obj.getCustomerName());
                System.out.println("\nCustomer Order Details");
                System.out.println("========================\n");
                System.out.println("----------------------------------------------");
                String line = String.format("%-12s%-18s%-14s", " Order_ID", "Order_Quantity", "Total_Value  ");
                System.out.println(line);
                System.out.println("----------------------------------------------");
                Order[] cus = Controller.searchCustomersDetails(customerId);
                for (int j = 0; j < cus.length; j++) {
                    String line2 =  String.format("%1s%-12s%-18d%-14.2f", " ", cus[j].getOrderID(), cus[j].getBurgerQty(), cus[j].getTotal());
                    System.out.println(line2);
                }
                System.out.println("----------------------------------------------");
                break L1;
            }else {
                do {
                    System.out.print("\n\nInvalid Customer ID. Do you want to enter again? (Y/N)>");
                    String option = input.next();
                    if (option.equalsIgnoreCase("Y")) {
                        Controller.clearConsole();
                        searchCustomer();
                    } else if (option.equalsIgnoreCase("N")) {
                        Controller.clearConsole();
                        homePage();
                    } else {
                        System.out.println("\tInvalid option..input again...");
                    }
                } while (true);
            }
        } while (true);
        do {
            System.out.println();
            System.out.print("Do you want to search another customer details (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                Controller.clearConsole();
                searchCustomer();
            } else if (option.equalsIgnoreCase("N")) {
                Controller.clearConsole();
                homePage();
            } else {
                System.out.println("\tInvalid option..input again...");
            }
        } while (true);
    }
    // View Order list
    public static void viewOrders() {
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tVIEW ORDER LIST\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("[1] Delivered Order");
        System.out.println("[2] Preparing Order");
        System.out.println("[3] Cancel Order");
        System.out.print("\nEnter an option to continue > ");
        int option = input.nextInt();
        switch (option) {
            case 1:
                Controller.clearConsole();
                deliverOrder();
                break;
            case 2:
                Controller.clearConsole();
                preparingOrder();
                break;
            case 3:
                Controller.clearConsole();
                cancelOrder();
                break;
        }
    }
    // Delivered Order
    public static void deliverOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tDELIVERED ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity","  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        Order[] delivered = Controller.getDeliveredOrders();
        for (int i = 0; i < delivered.length; i++) {
           String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", delivered[i].getOrderID(), delivered[i].getPhone(),
           delivered[i].getCustomerName(),delivered[i].getBurgerQty(),delivered[i].getTotal());
           System.out.println(line);
           System.out.println("--------------------------------------------------------------");
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                Controller.clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                Controller.clearConsole();
                deliverOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }
    // Preparing Order
    public static void preparingOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tPREPARING ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity","  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        Order[] prepared = Controller.getPreparedOrders();
        for (int i = 0; i < prepared.length; i++) {
            String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", prepared[i].getOrderID(), prepared[i].getPhone(),
                    prepared[i].getCustomerName(),prepared[i].getBurgerQty(),prepared[i].getTotal());
            System.out.println(line);
            System.out.println("--------------------------------------------------------------");
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                Controller.clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                Controller.clearConsole();
                preparingOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }
    // Canceled Order
    public static void cancelOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tCANCEL ORDER\t\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        System.out.println("\n--------------------------------------------------------------");
        String line1 = String.format("%-10s%-15s%-13s%-10s%12s", " OrderID", " CustomerID", " Name", "Quantity","  OrderValue");
        System.out.println(line1);
        System.out.println("--------------------------------------------------------------");
        Order[] canceled = Controller.getCanceledOrders();
        for (int i = 0; i < canceled.length; i++) {
            String line = String.format("%1s%-10s%-15s%-15s%-10d%8.2f", " ", canceled[i].getOrderID(), canceled[i].getPhone(),
                    canceled[i].getCustomerName(),canceled[i].getBurgerQty(),canceled[i].getTotal());
            System.out.println(line);
            System.out.println("--------------------------------------------------------------");
        }
        L1: do {
            System.out.print("\nDo you want to go to home page (Y/N): ");
            String option = input.next();
            if (option.equalsIgnoreCase("Y")) {
                Controller.clearConsole();
                homePage();
            } else if (option.equalsIgnoreCase("N")) {
                Controller.clearConsole();
                cancelOrder();
            } else {
                System.out.println("\tInvalid option..input again...");
                continue L1;
            }
        } while (true);
    }

    // Update order details
    public static void updateOrderDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tUPDATE ORDER DETAILS\t\t\t\t|");
        System.out.println("--------------------------------------------------------------------------------\n");
        L1: do {
            System.out.print("Enter order Id - ");
            String orderId = input.next();
            System.out.println();
            Order order = Controller.searchOrder(orderId);
            if(order==null) {
                do {
                    System.out.print("\n\nInvalid Order ID. Do you want to enter again? (Y/N)>");
                    String option = input.next();
                    if (option.equalsIgnoreCase("Y")) {
                        System.out.println("\n");
                        continue L1;
                    } else if (option.equalsIgnoreCase("N")) {
                        Controller.clearConsole();
                        return;
                    } else {
                        System.out.println("\tInvalid option..input again...");
                    }
                } while (true);
            }
            String status = Controller.getStatus(order);
            if (status == "Cancel") {
                System.out.println("This Order is already cancelled...You can not update this order...");
            }else if(status=="Delivered"){
                System.out.println("This Order is already delivered...You can not update this order...");
            }else{
                System.out.println("OrderID    - " + order.getOrderID());
                System.out.println("CustomerID - " + order.getPhone());
                System.out.println("Name       - " + order.getCustomerName());
                System.out.println("Quantity   - " + order.getBurgerQty());
                System.out.printf("OrderValue - %.2f", order.getTotal());
                System.out.println("\nOrderStatus- " + status);

                System.out.println("\nWhat do you want to update ? ");
                System.out.println("\t(01) Quantity ");
                System.out.println("\t(02) Status ");
                System.out.print("\nEnter your option - ");
                int option = input.nextInt();
                switch(option){
                    case 1:
                        Controller.clearConsole();
                        System.out.println("\nQuantity Update");
                        System.out.println("================\n");
                        System.out.println("OrderID    - " +order.getOrderID());
                        System.out.println("CustomerID - " + order.getPhone());
                        System.out.println("Name       - " + order.getCustomerName());
                        do {
                            System.out.print("\nEnter your quantity update value - ");
                            int qty = input.nextInt();
                            if (order.setBurgerQty(qty)) {
                                order.setTotal(qty*BURGERPRICE);
                                System.out.println("\n\tupdate order quantity success fully...");
                                System.out.println("\nnew order quantity - " + order.getBurgerQty());
                                System.out.printf("new order value - %.2f", order.getTotal());
                                break;
                            }else{
                                System.out.println("\n\tInvalid value,enter again");
                            }
                        } while (true);
                        break;

                    case 2:
                        Controller.clearConsole();
                        System.out.println("\nStatus Update");
                        System.out.println("==============\n");
                        System.out.println("OrderID    - " + order.getOrderID());
                        System.out.println("CustomerID - " + order.getPhone());
                        System.out.println("Name       - " + order.getCustomerName());
                        System.out.println("\n\t(0)Cancel");
                        System.out.println("\t(1)Preparing");
                        System.out.println("\t(2)Delivered");
                        System.out.print("\nEnter new order status - ");
                        int s = input.nextInt();
                        if (order.setStatus(s)) {
                            System.out.println("\n\tUpdate order status successfully...");
                            System.out.print("\nnew order status - " );
                            System.out.println(order.getStatus()==0?"Cancel":order.getStatus()==1?"Preparing":"Delivered");
                        }else{
                            System.out.println("\n\tUpdate order status is Unsuccessful...");
                        }
                        break;

                    default:
                        System.out.println("\ninvalid input...\n");
                        break;
                }
            }
            do {
                System.out.println();
                System.out.print("Do you want to update another order details (Y/N): ");
                String option = input.next();
                if (option.equalsIgnoreCase("Y")) {
                    Controller.clearConsole();
                    updateOrderDetails();
                } else if (option.equalsIgnoreCase("N")) {
                    Controller.clearConsole();
                    homePage();
                } else {
                    System.out.println("\tInvalid option..input again...");
                }
            } while (true);
        } while (true);
    }

    // home page
    public static void homePage() {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\t\t\t\tiHungry Burger\t\t\t\t|");
        System.out.println("-------------------------------------------------------------------------\n");
        System.out.println("[1] Place Order\t\t\t[2] Search Best Customer");
        System.out.println("[3] Search Order\t\t[4] Search Customer");
        System.out.println("[5] View Orders\t\t\t[6] Update Order Details");
        System.out.println("[7] Exit");

        Scanner input = new Scanner(System.in);
        do {

            System.out.print("\nEnter an option to continue > ");
            char option = input.next().charAt(0);

            switch (option) {
                case '1':
                    Controller.clearConsole();
                    placeOrder();
                    break;
                case '2':
                    Controller.clearConsole();
                    searchBestCustomer();
                    break;
                case '3':
                    Controller.clearConsole();
                    searchOrder();
                    break;
                case '4':
                    Controller.clearConsole();
                    searchCustomer();
                    break;
                case '5':
                    Controller.clearConsole();
                    viewOrders();
                    break;
                case '6':
                    Controller.clearConsole();
                    updateOrderDetails();
                    break;
                case '7':
                    Controller.exit();
                    break;
            }
        } while (true);
    }

    public static void run(){
        homePage();
    }
}
