import Account.PersonalNeedsAccount;
import Account.SavingsAccount;
import Card.Card;
import Client.Address;
import Client.Client;
import Transaction.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Client.ClientCSVSingleton;
import Client.AddressCSVSingleton;
import Transaction.TransactionCSVSingleton;
import Card.Card;
import Card.CardCSVSingleton;

public class Main {
    public static void printAllOptions() {
        System.out.println("1 - Add a client");
        System.out.println("2 - Print all clients");
        System.out.println("3 - Add personal needs account");
        System.out.println("4 - Add savings account");
        System.out.println("5 - Show all accounts created");
        System.out.println("6 - Get total money for client with given ID");
        System.out.println("7 - Create a card for given client (by ID) and account (by IBAN)");
        System.out.println("8 - Show all cards for a given client (by ID)");
        System.out.println("9 - Create Transaction");
        System.out.println("10 - Show all transactions");
        System.out.println("0 - END");
    }
    public static void main(String[] args) throws Exception {
        Audit myAudit = new Audit();
        boolean end = false;

        Service service = new Service();

        String[] corespondence = {"END", "addClient", "showAllClients", "addPersonalNeedsAccount", "addSavingsAccount", "showAccounts", "getTotalMoneyInEuro", "createClientCard", "showCards", "createTransaction", "showAllTransactions"};
        Scanner in = new Scanner(System.in);
        while (!end){
            System.out.println("Insert command: ");
            int command = -1;
            int id;
            String IBAN;
            try{
                printAllOptions();
                command = in.nextInt();
                switch (command) {
                    case 1 -> service.addClient(in);
                    case 2 -> service.showAllClients();
                    case 3 -> {System.out.println("Introduce client ID: "); id = in.nextInt(); service.addPersonalNeedsAccount(id, in);}
                    case 4 -> {System.out.println("Introduce client ID: "); id = in.nextInt(); service.addSavingsAccount(id, in);}
                    case 5 -> {service.showAccounts(); }
                    case 6 -> {System.out.println("Introduce client ID: "); id = in.nextInt();
                                System.out.println(service.getTotalMoneyInEuro(id));}
                    case 7 -> {System.out.println("Introduce client ID: "); id = in.nextInt();
                                in = new Scanner(System.in);
                                System.out.println("Introduce IBAN");
                                    IBAN = in.nextLine(); service.createClientCard(id, IBAN);}
                    case 8 -> {System.out.println("Introduce client ID: "); id = in.nextInt();
                                service.showCards(id);}
                    case 9 -> { service.mapAccounts();
                                service.createTransaction(in);}
                    case 10 -> { service.mapAccounts();
                                 service.showTransactions();}
                    case 0 -> end = true;
                }
                    myAudit.CSVPrint(corespondence[command]);
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
}
