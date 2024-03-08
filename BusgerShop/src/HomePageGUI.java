import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class HomePageGUI extends JFrame {
        private JButton btnPlaceOrder;
        private JButton btnSearch;
        private JButton btnViewOrders;
        private JButton btnUpdateOrder;
        private JButton btnSearchBestCustomer;
        private JButton btnExit;
        private BufferedImage img;

        HomePageGUI(){
            setTitle("Home Page");
            setSize(900,600);
            setLocationRelativeTo(null);
            setLayout(null);

            btnPlaceOrder = new JButton("Place Order");
            btnPlaceOrder.setFont(new Font("",1,15));
            btnPlaceOrder.setBounds(500,100,300,50);
            add(btnPlaceOrder);

            btnSearch = new JButton("Search Order");
            btnSearch.setFont(new Font("",1,15));
            btnSearch.setBounds(500,170,300,50);
            add(btnSearch);

            btnViewOrders = new JButton("View Order");
            btnViewOrders.setFont(new Font("",1,15));
            btnViewOrders.setBounds(500,240,300,50);
            add(btnViewOrders);

            btnUpdateOrder = new JButton("Update Order");
            btnUpdateOrder.setFont(new Font("",1,15));
            btnUpdateOrder.setBounds(500,310,300,50);
            add(btnUpdateOrder);

            JLabel imgLabel = new JLabel();
            BufferedImage myPicture = ImageIO.read(new File("D:\\ICD107\\OOP\\COURSE_WORK\\Course Work 4\\bs"));


        }
}

class Demo{
    public static void main(String args[]){
        new HomePageGUI().setVisible(true);
    }
}
