package DAO;

import ENTITY.*;

public interface CardDAO {
    /**
     * Metodo per la selzione delle carte per un determinato Conto Corrente dal bd.
     * @param contoCorrente riferimento la carta dal recuperare.
     * @return carta recuperata dal db.*/
    public Card selectCard(BankAccount contoCorrente);

    /**
     * Metodo per eseguire l'upgrade della carta sul db.
     * @param pan riferimento pan della carta da upgradare*/
    public void upgradeCard(String pan);

    /**
     * Metodo per eseguire l'upgrade della carta sul db.
     * @param pan riferimento pan della carta da downgradare*/
    public void downgradeCard(String pan);

}