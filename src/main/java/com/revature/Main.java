package com.revature;


import com.revature.services.ReimbService;
import com.revature.services.ReimbStatusService;

public class Main {

    public static void main(String[] args) {

        ReimbService reimbServ = new ReimbService();
        ReimbStatusService statServ = new ReimbStatusService();
        //System.out.println(reimbServ.getByUser(1));
        //System.out.println(reimbServ.getByUser(2));
        //System.out.println(reimbServ.getByUser(3));
        //System.out.println(reimbServ.getByUser(4));
        //System.out.println(reimbServ.getByUser(5));
        System.out.println(statServ.filterGivenList(reimbServ.getByUser(2), "pending"));
        System.out.println(statServ.filterGivenList(reimbServ.getByUser(2), "completed"));
        System.out.println("\n");
        System.out.println(statServ.filterGivenList(reimbServ.getByUser(3), "pending"));
        System.out.println(statServ.filterGivenList(reimbServ.getByUser(3), "completed"));
        System.out.println("\n");
        System.out.println(statServ.filterGivenList(reimbServ.getByUser(4), "pending"));
        System.out.println(statServ.filterGivenList(reimbServ.getByUser(4), "completed"));



    }


}
