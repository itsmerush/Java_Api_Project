package com.practice.second;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface UserModelRepository  extends JpaRepository<UserModel, String>{
        public List<UserModel> findByBPN(String BPN);


        @Query(value = "select 'summ by month=' + sum(Spend),YEAR(Transaction_Date) as y,MONTH(Transaction_Date) as m from details group by m,y having m=SUBSTRING(?1,5,6) and y=SUBSTRING(?1,1,4) ", nativeQuery = true)
        public int findByTransaction_Date(@Param("Transaction_Date") String Transaction_Date);


        @Query(value = "select max(Batch_Id) from details",nativeQuery = true)
        public int findMaximum();


        @Query(value = "select MONTHNAME(Transaction_Date) as Month,sum(Spend) as Sum from details where BPN=?1 group by MONTH(Transaction_Date)",nativeQuery = true)
        public List<Map<String, String>> getBPNSumByMonths(@Param("BPN") String BPN);


        @Query(value = "select MONTHNAME(Transaction_Date) as Month,sum(Spend) as Sum from details where BPN=?1 and MONTH(Transaction_Date)=?2 group by MONTH(Transaction_Date)",nativeQuery = true)
        public Map<String, String> getBPNAndMonthSumByMonths(
                @Param ("BPN")String BPN,@Param("Transaction_Date") String Transaction_Date);

        @Query(value = "select distinct(BPN) from details",nativeQuery = true)
        List<String> getbpns();
}
