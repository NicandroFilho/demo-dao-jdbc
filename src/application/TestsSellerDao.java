package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TestsSellerDao {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("==== TEST 1: Seller findById ====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();

        System.out.println("==== TEST 2: Seller findByDepartment Department Object as Parameter ====");
        Department dep = new Department(4, null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        for(Seller  obj : list){
            System.out.println(obj);
        }

        System.out.println();

        System.out.println("==== TEST 3: Seller findByDepartment Integer idDepartment as Parameter ====");
        list = sellerDao.findByDepartment(2);
        for(Seller  obj : list){
            System.out.println(obj);
        }

        System.out.println();

        System.out.println("==== TEST 4: Seller findAll ====");
        list = sellerDao.findAll();
        for(Seller  obj : list){
            System.out.println(obj);
        }

        /*System.out.println();

        System.out.println("==== TEST 5: Seller insert ====");
        Seller newSeller = new Seller(null, "Dorian Gray", "dorian@gmail.com", new Date(), 1234.50, dep);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New Id: "+ newSeller.getId());
        */

       /* System.out.println();

        System.out.println("==== TEST 6: Seller Update ====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Wayne");
        sellerDao.update(seller);
        System.out.println("Update Completed!");
        System.out.println(sellerDao.findById(1));
        */

        System.out.println();

        System.out.println("==== TEST 7: Seller Delete by Id ====");
        System.out.print("Enter Id for Delete test: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete Complete!");
        list = sellerDao.findAll();
        for(Seller  obj : list){
            System.out.println(obj);
        }




        DB.closeConnection();
        sc.close();
    }
}
