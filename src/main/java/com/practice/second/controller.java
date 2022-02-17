package com.practice.second;


import com.github.tsohr.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class controller {
    //public String strmon(String a){
    //    String[] strAr1=new String[] {"Jan", "Feb", "Mar","Apr","May", };
    //    return a;
    //}

    @Autowired
    public UserModelService userModelService;

    @GetMapping("/getdetails")
    public List<UserModel>getdetails(){
        return userModelService.getdetails();
    }


    @GetMapping("/getdetails/BPN/{BPN}")
    public List<UserModel> fetchCustomerByBPN(@PathVariable("BPN") String BPN){
        return userModelService.fetchCustomerByBPN(BPN);
    }

    @GetMapping("/getdetails/month/{Transaction_Date}")
    public String fetchSumByMonth(@PathVariable("Transaction_Date")  String Transaction_Date) {
        int z;
        try {
            z = Integer.parseInt(userModelService.fetchSumByMonth(Transaction_Date));
        }

        catch (Exception e) {
            //return String.valueOf(e);
            return "Please Enter Data in Format YYYYMM";
        }

        return "Sum of " + String.valueOf(Transaction_Date) + " is " + String.valueOf(z);

    }

    @PostMapping("/add")
    public UserModel saveData(@RequestBody UserModel userModel){

        return userModelService.saveData(userModel);
    }

    @PostMapping("/addd")
    public String saveDataAll(@RequestBody Bulk bulk){

        return userModelService.saveDataAll(bulk);



    }




    @GetMapping("/getBPNSumByMonths/{BPN}")
    public Map<String,List<Map<String, String>>> getBPNSumByMonths(@PathVariable("BPN") String BPN){
        Map <String,List<Map<String, String>>>json = new HashMap();

        json.put(BPN, userModelService.getBPNSumByMonths(BPN));
        return json;
    }



    @GetMapping("/getBPNAndMonthSumByMonths/{BPN}/{Transaction_Date}")
    public Map<String,Map<String, String>> getBPNAndMonthSumByMonths(
            @PathVariable("BPN") String BPN,@PathVariable("Transaction_Date") String Transaction_Date){
        Map <String,Map<String, String>>json = new HashMap();

        json.put(BPN, userModelService.getBPNAndMonthSumByMonths(BPN,Transaction_Date));
        return json;
    }

    private List<String> getbpns() {
        return userModelService.getbpns();
    }

    @GetMapping("/getAllBPNdetails")
    public Map<String,List<Map<String, String>>> getAllBPNdetails(){
        Map <String,List<Map<String, String>>>json = new HashMap();
        List<String>bpns=getbpns();

        for (int i=0;i<bpns.size();i++) {
            json.put(bpns.get(i), userModelService.getBPNSumByMonths(bpns.get(i)));
        }



        return json;
    }




}
