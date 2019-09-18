package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {
    public static void main(String[] args){
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("==== TEST 1: Seller findById ====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();

        System.out.println("==== TEST 2: Seller findByDepartment Department Object as Parameter ====");
        Department dep = new Department(2, null);
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
    }
}
