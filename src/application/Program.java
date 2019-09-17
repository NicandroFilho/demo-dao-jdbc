package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args){
        Department dep = new Department(1, "Books");
        System.out.println(dep);

        Seller seller = new Seller(1, "Alex Green", "alex@gmail.com", new Date(), 3000.0, dep);

        System.out.println(seller);

        SellerDao sellerDao = DaoFactory.createSellerDao();
    }
}
