package DAO;

import ENTITY.*;
import EXCEPTIONS.MyExc;

import java.util.ArrayList;

public interface CollectionDAO {

    public ArrayList<Collection> selectCollectionByIban(BankAccount bankAccount);


    public void addCollection(BankAccount bankAccount, String name, String description) throws MyExc;


    public void deleteCollection(BankAccount bankAccount, String name);
}
