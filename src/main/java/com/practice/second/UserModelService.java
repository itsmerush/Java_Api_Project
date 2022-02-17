package com.practice.second;

import com.practice.second.UserModel;
import com.practice.second.UserModelRepository;

import jdk.nashorn.internal.objects.Global;
import org.hibernate.JDBCException;
import org.hibernate.exception.GenericJDBCException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class UserModelService {
    @Autowired
    public UserModelRepository userModelRepository;

    public UserModel saveData(UserModel userModel) {

        return userModelRepository.save(userModel);



    }


    public List<UserModel> getdetails() {
        return userModelRepository.findAll();
    }


    public List<UserModel> fetchCustomerByBPN(String BPN) {
        return userModelRepository.findByBPN(BPN);
    }

    public String fetchSumByMonth(String Transaction_Date) {
        int TD;
        try {
            TD = userModelRepository.findByTransaction_Date(Transaction_Date);
        } catch (Exception e) {
            return String.valueOf(e);
        }
        return String.valueOf(TD);
    }


    public String saveDataAll(Bulk bulk) {

        List<UserModel>UserList =new ArrayList<>();


        int q;
        try {
            q=userModelRepository.findMaximum()+1;
        }
        catch (Exception e){
            q=0;

        }

        int finalQ = q;
        //int q;
        //q=userModelRepository.findMaximum()+1;
        bulk.getObjects().forEach(each->{
            UserModel user=new UserModel();
            user.setBPN(each.getBPN());
            user.setSerial_No(each.getSerial_No());
            user.setPCN(each.getPCN());
            user.setSpend(each.getSpend());
            user.setTransaction_Date(each.getTransaction_Date());
            user.setInsert_TIme(each.getInsert_TIme());
            user.setCountry_Code(each.getCountry_Code());
            user.setBatch_Id(finalQ);


            UserList.add(user);
        });

         try {
             userModelRepository.saveAll(UserList);

             return "Batch "+String.valueOf(finalQ)+" inserted successfully";
         }
         catch (RuntimeException e){
             return "Enter Correct Data";
         }

         catch (Exception e){
             return String.valueOf(e.getMessage());
         }




    }

    public List<Map<String, String>> getBPNSumByMonths(String BPN) {

        return userModelRepository.getBPNSumByMonths(BPN);
    }

    public Map<String, String> getBPNAndMonthSumByMonths(String BPN, String Transaction_Date) {
        return userModelRepository.getBPNAndMonthSumByMonths(BPN,Transaction_Date);
    }


    public List<String> getbpns() {
        return userModelRepository.getbpns();
    }
}
