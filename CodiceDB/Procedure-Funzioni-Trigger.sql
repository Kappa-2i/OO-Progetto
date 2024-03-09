create function Controllo_Saldo(soldi_in double precision, iban_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
   saldo_contabile DECIMAL;
BEGIN
    -- controlla il saldo del conto
   select saldo
   into saldo_contabile
   from contocorrente
   where iban ilike iban_In;

    -- se il saldo è minore dei soldi da inviare mi ritorna 0, altrimenti 1
   if saldo_contabile < soldi_In then
       return 0;
   else
       return 1;
   end if;
END;
$$;





create function Controllo_Tipocarta(iban_in character varying) returns boolean
    language Plpgsql
as
$$
DECLARE
   controllo_tipocarta carta.tipocarta%TYPE;
BEGIN
   select tipocarta
   into controllo_tipocarta
   from test.carta
   where contocorrente_iban ilike iban_In;


   if controllo_tipocarta = 'CartaDiDebito' then
       return false;
   else
       return true;
   end if;
END;
$$;



create function Crea_Contocorrente_Con_Carta(account_email_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
   iban_generato VARCHAR(27);
   pan_generato CHAR(16);
   pin_generato CHAR(5);
   cvv_generato CHAR(3);
BEGIN
   -- Genera un IBAN casuale utilizzando la funzione genera_iban_casuale
   iban_generato := test.genera_iban_casuale();


   -- Genera un PAN casuale di 16 cifre
   pan_generato := test.genera_pan_casuale();


   -- Genera un PIN casuale di 5 cifre
   pin_generato := test.genera_pin_casuale();


   -- Genera un CVV casuale di 3 cifre
   cvv_generato := test.genera_cvv_casuale();



    account_email_in = lower(account_email_in);
       -- Inserisce il nuovo record nella tabella ContoCorrente
       INSERT INTO test.ContoCorrente(iban, dataapertura, saldo, account_email)
       VALUES (iban_generato, CURRENT_DATE, 0, account_email_In);
       RAISE NOTICE E'---------------------------------------------------------------------------\nConto creato con successo! \n---------------------------------------------------------------------------';

        -- Inserisce anche in carta i valori generati casuali
       INSERT INTO test.carta(Pan, Pin, Cvv, Tipocarta, maxinvio, Contocorrente_Iban, price_upgrade)
       VALUES (pan_generato, pin_generato, cvv_generato, 'CartaDiDebito', 3000, iban_generato, null);
   return 1;

   EXCEPTION
    when foreign_key_violation then
    RAISE NOTICE E'---------------------------------------------------------------------------\nL\'email inserita non esiste!\n---------------------------------------------------------------------------';
    when not_null_violation then
    RAISE NOTICE E'---------------------------------------------------------------------------\nL\'email non può essere NULL!\n---------------------------------------------------------------------------';


END;
$$;




create procedure Crea_Nuovo_Account(IN email_in character varying, IN password_in character varying, IN nome_in character varying, IN cognome_in character varying)
    language Plpgsql
as
$$
declare

begin

        email_in = lower(email_in);
        --inserisce i dati in account
       INSERT INTO test.account(email, password, nome, cognome) VALUES (email_In, password_in, nome_in, cognome_in);
       RAISE NOTICE E'---------------------------------------------------------------------------\nAccount creato con successo! \n---------------------------------------------------------------------------';

exception
       when foreign_key_violation THEN
       RAISE NOTICE E'---------------------------------------------------------------------------\nNon esiste nessuna persona con questo codice Fiscale, per favore riporvare \n---------------------------------------------------------------------------';

      when not_null_violation THEN
       RAISE NOTICE 'Errore NOT NULL: %', SQLERRM;

      when unique_violation THEN
       RAISE NOTICE E'---------------------------------------------------------------------------\nEsiste già un account con quest\'e-mail \n---------------------------------------------------------------------------';

       when check_violation then
        RAISE NOTICE E'---------------------------------------------------------------------------\nInserisci un-email valida\n---------------------------------------------------------------------------';

end;
$$;



create function Crea_Raccolta(nome_in character varying, descrizione_in character varying, contocorrente_in character varying) returns integer
    language Plpgsql
as
$$
BEGIN

    nome_in = upper(nome_in);
    --inserisce i dati in raccolta
   insert into test.raccolta(nomeraccolta, datacreazione, contocorrente_iban, descrizione)
       values (nome_In, current_date, contocorrente_in, descrizione_in);
   RAISE NOTICE E'---------------------------------------------------------------------------\nRaccolta creata con successo! \n---------------------------------------------------------------------------';
   return 1;


   exception
   when not_null_violation then
   RAISE NOTICE 'Errore NOT NULL: %', SQLERRM;


END;
$$;







create function Crea_Salvadanaio(iban_in character varying, nomesalvadanaio_in character varying, obiettivo_in double precision, descrizione_in character varying) returns integer
    language Plpgsql
as
$$
BEGIN
    nomesalvadanaio_in = upper(nomesalvadanaio_in);
    --inserisce i dati in salvadanaio
   INSERT INTO test.salvadanaio(nomesalvadanaio, obiettivo, saldorisparmio, saldorimanente, descrizione, contocorrente_iban, datacreazione)
    VALUES (nomesalvadanaio_In, obiettivo_In, default, obiettivo_In, descrizione_In, iban_In, current_date);
   RAISE NOTICE E'---------------------------------------------------------------------------\nSalvadanaio creato con successo!\n---------------------------------------------------------------------------';
    return 1;

   EXCEPTION
       when foreign_key_violation then
       RAISE NOTICE E'---------------------------------------------------------------------------\nL\'Iban inserito non esiste!\n---------------------------------------------------------------------------';
       when not_null_violation then
       RAISE NOTICE 'Errore NOT NULL: %', SQLERRM;
       return 0;
END;
$$;



create function Downgrade_Carta(pan_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
  controllo_esistenza int = 0;
  controllo_iban test.contocorrente.iban%TYPE;
BEGIN

    -- controllo esistenza carta inserita
  select count(pan)
  into controllo_esistenza
  from test.carta
  where pan = pan_In;


  if controllo_esistenza > 0 then
      -- select dell'iban collegato alla carta, per chiamare la funzione 'controllo_tipocarta'
        select contocorrente_iban
        into controllo_iban
        from test.carta
        where pan = pan_in;

         if (test.controllo_tipocarta(controllo_iban)) then
              UPDATE test.carta
              set tipocarta = 'CartaDiDebito', maxinvio = 3000, price_upgrade = null
              where pan = pan_In;
              RAISE NOTICE E'---------------------------------------------------------------------------\nDowngrade effettuato! \n---------------------------------------------------------------------------';
         else

               RAISE NOTICE E'---------------------------------------------------------------------------\nNon puoi effettuare il downgrade, la carta è già di tipo Carta di Debito\n---------------------------------------------------------------------------';

         end if;
  else
      RAISE NOTICE E'---------------------------------------------------------------------------\nInserisci una carta valida!\n---------------------------------------------------------------------------';
  end if;
    return 1;
END;
$$;



create function Genera_Cvv_Casuale() returns character
    language Plpgsql
as
$$
DECLARE
   cvv_generato CHAR(3) := '';
   i INTEGER;
BEGIN
   FOR i IN 1..3 LOOP
       cvv_generato := cvv_generato || TRUNC(RANDOM() * 10)::varchar;
   END LOOP;


   RETURN cvv_generato;
END;
$$;




create function Genera_Iban_Casuale() returns character varying
    language Plpgsql
as
$$
DECLARE
  iban_generato VARCHAR(27) := 'IT';
  i int;
BEGIN
  FOR i IN 1..25 LOOP
      iban_generato := iban_generato || (TRUNC(RANDOM() * 10))::VARCHAR;
  END LOOP;

  RETURN iban_generato;

END;
$$;





create function Genera_Pan_Casuale() returns character
    language Plpgsql
as
$$
DECLARE
   pan_generato CHAR(16) := '';
   i INTEGER;
BEGIN
   FOR i IN 1..16 LOOP
       pan_generato := pan_generato || TRUNC(RANDOM() * 10)::varchar;
   END LOOP;


   RETURN pan_generato;
END;
$$;




create function Genera_Pin_Casuale() returns character
    language Plpgsql
as
$$
DECLARE
   pin_generato CHAR(5) := '';
   i INTEGER;
BEGIN
   FOR i IN 1..5 LOOP
       pin_generato := pin_generato || TRUNC(RANDOM() * 10)::varchar;
   END LOOP;


   RETURN pin_generato;
END;
$$;





create function Invia_Bonifico(iban_mittente_in character varying, iban_destinatario_in character varying, soldi_in double precision, causale_in character varying, categoria_in character varying, raccolta_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
      controllo_esistenza_ibanmitt int = 0;
      controllo_esistenza_ibandest int = 0;
      controllo_saldo test.contocorrente.saldo%TYPE;
      controllo_raccolta test.raccolta.id_raccolta%TYPE;
BEGIN

  --togliamo gli spazi laterali alla causale
  causale_in = ltrim(causale_in);
  causale_in = rtrim(causale_in);

  --select dell'iban mittente esistente
  SELECT count(iban)
  into controllo_esistenza_ibanmitt
  from test.contocorrente
  where iban = iban_mittente_In;

  --select dell'iban destinatario esistente
  SELECT count(iban)
  into controllo_esistenza_ibandest
  from test.contocorrente
  where iban = iban_destinatario_In;

  --se l'importo inserito è valido
  if soldi_in > 0 then

       -- se entrambi gli iban esistono allora entra nel primo if
       if controllo_esistenza_ibanmitt > 0 and controllo_esistenza_ibandest > 0 then
           if iban_mittente_in = iban_destinatario_in then
               RAISE NOTICE E'---------------------------------------------------------------------------\nNon puoi inviare un bonifico a te stesso!\n---------------------------------------------------------------------------';
           else
               -- funzione che controlla il saldo dell'iban mittente
              if test.controllo_saldo(soldi_In, iban_mittente_In) then
            
                -- funzione che controlla il tipo della carta dell'iban mittente (0:debito, 1:credito)
                  if (NOT test.controllo_tipocarta(iban_mittente_In)) then
                      if soldi_In <= 3000 then
                          -- funzione che controlla il tipo della carta dell'iban destinatario
                            
                              if(raccolta_in IS NOT NULL)then
                                        raccolta_in = upper(raccolta_in);
                                
                                        select id_raccolta
                                        into controllo_raccolta
                                        from test.raccolta
                                        where nomeraccolta = raccolta_in and contocorrente_iban = iban_mittente_in;
                            

                                      --inserisco i dati in transazione
                                      INSERT INTO test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
                                      VALUES (soldi_In, causale_In, current_date, current_time,  iban_mittente_In, 'Bonifico', 'Invia a', null, categoria_in, iban_destinatario_In, controllo_raccolta),
                                          (soldi_In, causale_In, current_date, current_time + interval '30 minutes', iban_destinatario_In, 'Bonifico', 'Riceve da', categoria_in, null, iban_mittente_In, null);

                                    -- aggiorno il saldo del destinatario
                                      UPDATE test.contocorrente
                                      SET saldo = saldo + soldi_In
                                      WHERE iban = iban_destinatario_In;

                                    --aggiorno il saldo del mittente
                                      UPDATE test.contocorrente
                                      SET saldo = saldo - soldi_In
                                      WHERE iban = iban_mittente_In;
                                      RAISE NOTICE E'---------------------------------------------------------------------------\nBonifico inviato con successo!\n---------------------------------------------------------------------------';
                              else
                                     --inserisco i dati in transazione
                                      INSERT INTO test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
                                      VALUES (soldi_In, causale_In, current_date, current_time,  iban_mittente_In, 'Bonifico', 'Invia a', null, categoria_in, iban_destinatario_In, null),
                                          (soldi_In, causale_In, current_date, current_time + interval '30 minutes', iban_destinatario_In, 'Bonifico', 'Riceve da', categoria_in, null, iban_mittente_In, null);

                                    -- aggiorno il saldo del destinatario
                                      UPDATE test.contocorrente
                                      SET saldo = saldo + soldi_In
                                      WHERE iban = iban_destinatario_In;

                                    --aggiorno il saldo del mittente
                                      UPDATE test.contocorrente
                                      SET saldo = saldo - soldi_In
                                      WHERE iban = iban_mittente_In;
                                      RAISE NOTICE E'---------------------------------------------------------------------------\nBonifico inviato con successo!\n---------------------------------------------------------------------------';
                              end if;        
                      else
                          RAISE NOTICE E'---------------------------------------------------------------------------\nL\'importo supera il limite di soldi che è possibile inviare con una sola transazione.\n Se desideri puoi effettuare l\'upgrade!\n---------------------------------------------------------------------------';
                      end if;
                  else -- caso carta di credito del mittente

                                if(raccolta_in IS NOT NULL)then
                                        raccolta_in = upper(raccolta_in);
                                
                                        select id_raccolta
                                        into controllo_raccolta
                                        from test.raccolta
                                        where nomeraccolta = raccolta_in and contocorrente_iban = iban_mittente_in;
                                  
                                      -- inserisco i dati in transazione
                                      INSERT INTO test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
                                      VALUES (soldi_In, causale_In, current_date, current_time,  iban_mittente_In, 'Bonifico', 'Invia a', null, categoria_in, iban_destinatario_In, controllo_raccolta),
                                          (soldi_In, causale_In, current_date, current_time + interval '30 minutes', iban_destinatario_In, 'Bonifico', 'Riceve da', categoria_in, null, iban_mittente_In, null);
                                      -- aggiorno saldo destinatario
                                      UPDATE test.contocorrente
                                      SET saldo = saldo + soldi_In
                                      WHERE iban = iban_destinatario_In;

                                        -- aggiorno saldo mittente
                                      UPDATE test.contocorrente
                                      SET saldo = saldo - soldi_In
                                      WHERE iban = iban_mittente_In;
                                      RAISE NOTICE E'---------------------------------------------------------------------------\nBonifico inviato con successo!\n---------------------------------------------------------------------------';
                                else 
                                     --inserisco i dati in transazione
                                      INSERT INTO test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
                                      VALUES (soldi_In, causale_In, current_date, current_time,  iban_mittente_In, 'Bonifico', 'Invia a', null, categoria_in, iban_destinatario_In, null),
                                          (soldi_In, causale_In, current_date, current_time + interval '30 minutes', iban_destinatario_In, 'Bonifico', 'Riceve da', categoria_in, null, iban_mittente_In, null);

                                    -- aggiorno il saldo del destinatario
                                      UPDATE test.contocorrente
                                      SET saldo = saldo + soldi_In
                                      WHERE iban = iban_destinatario_In;

                                    --aggiorno il saldo del mittente
                                      UPDATE test.contocorrente
                                      SET saldo = saldo - soldi_In
                                      WHERE iban = iban_mittente_In;
                                      RAISE NOTICE E'---------------------------------------------------------------------------\nBonifico inviato con successo!\n---------------------------------------------------------------------------';
                              end if;  
                                    
                  end if;
              else
                          RAISE NOTICE E'---------------------------------------------------------------------------\nSaldo insufficiente!\n---------------------------------------------------------------------------';
              end if;
        end if;
       elseif controllo_esistenza_ibanmitt > 0 and controllo_esistenza_ibandest = 0 then
          RAISE NOTICE E'---------------------------------------------------------------------------\nIban destinatario errato!\n---------------------------------------------------------------------------';

       elseif controllo_esistenza_ibanmitt = 0 and controllo_esistenza_ibandest > 0 then
          RAISE NOTICE E'---------------------------------------------------------------------------\nIban mittente errato!\n---------------------------------------------------------------------------';
       else
          RAISE NOTICE E'---------------------------------------------------------------------------\nIban mittente e destinatario errati!\n---------------------------------------------------------------------------';
       end if;
  else
                RAISE NOTICE E'---------------------------------------------------------------------------\nInserisci una cifra valida!\n---------------------------------------------------------------------------';
  end if;
  return 1;

  exception
  when not_null_violation then
    RAISE NOTICE E'---------------------------------------------------------------------------\nLa causale non può essere NULL!\n---------------------------------------------------------------------------';
END;
$$;







create function Invia_Bonifico_Istantaneo(iban_mittente_in character varying, iban_destinatario_in character varying, soldi_in double precision, causale_in character varying, categoria_in character varying, raccolta_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
      controllo_esistenza_ibanmitt int = 0;
      controllo_esistenza_ibandest int = 0;
      controllo_saldo test.contocorrente.saldo%TYPE;
      controllo_raccolta test.raccolta.id_raccolta%TYPE;
BEGIN

  --togliamo gli spazi laterali alla causale
  causale_in = ltrim(causale_in);
  causale_in = rtrim(causale_in);

  --select dell'iban mittente esistente
  SELECT count(iban)
  into controllo_esistenza_ibanmitt
  from test.contocorrente
  where iban = iban_mittente_In;

  --select dell'iban destinatario esistente
  SELECT count(iban)
  into controllo_esistenza_ibandest
  from test.contocorrente
  where iban = iban_destinatario_In;

  --se l'importo inserito è valido
  if soldi_in > 0 then

       -- se entrambi gli iban esistono allora entra nel primo if
       if controllo_esistenza_ibanmitt > 0 and controllo_esistenza_ibandest > 0 then
           if iban_mittente_in = iban_destinatario_in then
               RAISE NOTICE E'---------------------------------------------------------------------------\nNon puoi inviare un bonifico a te stesso!\n---------------------------------------------------------------------------';
           else
               -- funzione che controlla il saldo dell'iban mittente
              if test.controllo_saldo(soldi_In, iban_mittente_In) then
            
                -- funzione che controlla il tipo della carta dell'iban mittente (0:debito, 1:credito)
                  if (NOT test.controllo_tipocarta(iban_mittente_In)) then
                      if soldi_In <= 3000 then
                          -- funzione che controlla il tipo della carta dell'iban destinatario
                            
                              if(raccolta_in IS NOT NULL)then
                                        raccolta_in = upper(raccolta_in);
                                
                                        select id_raccolta
                                        into controllo_raccolta
                                        from test.raccolta
                                        where nomeraccolta = raccolta_in and contocorrente_iban = iban_mittente_in;
                            

                                      --inserisco i dati in transazione
                                      INSERT INTO test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
                                      VALUES (soldi_In, causale_In, current_date, current_time,  iban_mittente_In, 'BonificoIstantaneo', 'Invia a', null, categoria_in, iban_destinatario_In, controllo_raccolta),
                                          (soldi_In, causale_In, current_date, current_time, iban_destinatario_In, 'BonificoIstantaneo', 'Riceve da', categoria_in, null, iban_mittente_In, null);

                                    -- aggiorno il saldo del destinatario
                                      UPDATE test.contocorrente
                                      SET saldo = saldo + soldi_In
                                      WHERE iban = iban_destinatario_In;

                                    --aggiorno il saldo del mittente
                                      UPDATE test.contocorrente
                                      SET saldo = saldo - soldi_In - 2
                                      WHERE iban = iban_mittente_In;
                                      RAISE NOTICE E'---------------------------------------------------------------------------\nBonifico inviato con successo!\n---------------------------------------------------------------------------';
                              else
                                     --inserisco i dati in transazione
                                      INSERT INTO test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
                                      VALUES (soldi_In, causale_In, current_date, current_time,  iban_mittente_In, 'BonificoIstantaneo', 'Invia a', null, categoria_in, iban_destinatario_In, null),
                                          (soldi_In, causale_In, current_date,current_time, iban_destinatario_In, 'BonificoIstantaneo', 'Riceve da', categoria_in, null, iban_mittente_In, null);

                                    -- aggiorno il saldo del destinatario
                                      UPDATE test.contocorrente
                                      SET saldo = saldo + soldi_In
                                      WHERE iban = iban_destinatario_In;

                                    --aggiorno il saldo del mittente
                                      UPDATE test.contocorrente
                                      SET saldo = saldo - soldi_In
                                      WHERE iban = iban_mittente_In;
                                      RAISE NOTICE E'---------------------------------------------------------------------------\nBonifico inviato con successo!\n---------------------------------------------------------------------------';
                              end if;        
                      else
                          RAISE NOTICE E'---------------------------------------------------------------------------\nL\'importo supera il limite di soldi che è possibile inviare con una sola transazione.\n Se desideri puoi effettuare l\'upgrade!\n---------------------------------------------------------------------------';
                      end if;
                  else -- caso carta di credito del mittente

                                if(raccolta_in IS NOT NULL)then
                                        raccolta_in = upper(raccolta_in);
                                
                                        select id_raccolta
                                        into controllo_raccolta
                                        from test.raccolta
                                        where nomeraccolta = raccolta_in and contocorrente_iban = iban_mittente_in;
                                  
                                      -- inserisco i dati in transazione
                                      INSERT INTO test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
                                      VALUES (soldi_In, causale_In, current_date, current_time,  iban_mittente_In, 'BonificoIstantaneo', 'Invia a', null, categoria_in, iban_destinatario_In, controllo_raccolta),
                                          (soldi_In, causale_In, current_date, current_time, iban_destinatario_In, 'BonificoIstantaneo', 'Riceve da', categoria_in, null, iban_mittente_In, null);
                                      -- aggiorno saldo destinatario
                                      UPDATE test.contocorrente
                                      SET saldo = saldo + soldi_In
                                      WHERE iban = iban_destinatario_In;

                                        -- aggiorno saldo mittente
                                      UPDATE test.contocorrente
                                      SET saldo = saldo - soldi_In
                                      WHERE iban = iban_mittente_In;
                                      RAISE NOTICE E'---------------------------------------------------------------------------\nBonifico inviato con successo!\n---------------------------------------------------------------------------';
                                else 
                                     --inserisco i dati in transazione
                                      INSERT INTO test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
                                      VALUES (soldi_In, causale_In, current_date, current_time,  iban_mittente_In, 'BonificoIstantaneo', 'Invia a', null, categoria_in, iban_destinatario_In, null),
                                          (soldi_In, causale_In, current_date, current_time, iban_destinatario_In, 'BonificoIstantaneo', 'Riceve da', categoria_in, null, iban_mittente_In, null);

                                    -- aggiorno il saldo del destinatario
                                      UPDATE test.contocorrente
                                      SET saldo = saldo + soldi_In
                                      WHERE iban = iban_destinatario_In;

                                    --aggiorno il saldo del mittente
                                      UPDATE test.contocorrente
                                      SET saldo = saldo - soldi_In
                                      WHERE iban = iban_mittente_In;
                                      RAISE NOTICE E'---------------------------------------------------------------------------\nBonifico inviato con successo!\n---------------------------------------------------------------------------';
                              end if;  
                                    
                  end if;
              else
                          RAISE NOTICE E'---------------------------------------------------------------------------\nSaldo insufficiente!\n---------------------------------------------------------------------------';
              end if;
        end if;
       elseif controllo_esistenza_ibanmitt > 0 and controllo_esistenza_ibandest = 0 then
          RAISE NOTICE E'---------------------------------------------------------------------------\nIban destinatario errato!\n---------------------------------------------------------------------------';

       elseif controllo_esistenza_ibanmitt = 0 and controllo_esistenza_ibandest > 0 then
          RAISE NOTICE E'---------------------------------------------------------------------------\nIban mittente errato!\n---------------------------------------------------------------------------';
       else
          RAISE NOTICE E'---------------------------------------------------------------------------\nIban mittente e destinatario errati!\n---------------------------------------------------------------------------';
       end if;
  else
                RAISE NOTICE E'---------------------------------------------------------------------------\nInserisci una cifra valida!\n---------------------------------------------------------------------------';
  end if;
  return 1;

  exception
  when not_null_violation then
    RAISE NOTICE E'---------------------------------------------------------------------------\nLa causale non può essere NULL!\n---------------------------------------------------------------------------';
END;
$$;





create function Invia_Soldi_Al_Salvadanaio(iban_in character varying, nome_in character varying, soldi_in double precision) returns integer
    language Plpgsql
as
$$
              DECLARE
                  controllo_iban int;
                  controllo_saldo contocorrente.saldo%TYPE;
                  controllo_salvadanaio int;
                  controllo_obiettivo salvadanaio.obiettivo%TYPE;
                  controllo_saldorimanente salvadanaio.saldorimanente%TYPE;
                  controllo_saldorisparmio salvadanaio.saldorisparmio%TYPE;
              BEGIN

                -- controlla se esiste l'iban inserito
                  select count(iban)
                  into controllo_iban
                  from test.contocorrente
                  where iban = iban_In;


                  if controllo_iban > 0 then
                      -- controlla se esiste il salvadanaio inserito
                      select count(idsalvadanaio)
                      into controllo_salvadanaio
                      from test.salvadanaio
                      where contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;


                      if controllo_salvadanaio > 0 then


                          select saldo
                          into controllo_saldo
                          from test.contocorrente
                          where iban = iban_In;

                        -- controlla se il saldo dal conto è sufficiente
                        if soldi_in > 0 then
                          if controllo_saldo >= soldi_In then

                            -- select che prende l'obiettivo del salvadanaio
                              select obiettivo
                              into controllo_obiettivo
                              from test.salvadanaio
                              where nomesalvadanaio ILIKE nome_In and contocorrente_iban = iban_In;

                            -- aggiorna il saldo risparmio del salvadanaio
                              UPDATE test.salvadanaio
                              SET saldorisparmio = saldorisparmio + soldi_In
                              WHERE contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;

                            -- aggiorna il saldo rimanente del salvadanaio
                              UPDATE test.salvadanaio
                              SET saldorimanente = salvadanaio.obiettivo - salvadanaio.saldorisparmio
                              WHERE contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;

                              -- aggiorna il saldo del contocorrente
                              UPDATE test.contocorrente
                              SET saldo = saldo-soldi_in
                              WHERE iban = iban_in;

                            -- select del saldo risparmio dopo l'update
                              select saldorisparmio
                              into controllo_saldorisparmio
                              from test.salvadanaio
                              where nomesalvadanaio ILIKE nome_In and contocorrente_iban = iban_In;

                            -- select del saldo rimanente dopo l'update
                              select saldorimanente
                              into controllo_saldorimanente
                              from test.salvadanaio
                              where nomesalvadanaio ILIKE nome_In and contocorrente_iban = iban_In;

                            -- controllo se ho raggiunto l'obiettivo
                              if  controllo_saldorisparmio >= controllo_obiettivo then
                                  RAISE NOTICE E'---------------------------------------------------------------------------\n Saldo salvadanaio attuale: %. \n Hai raggiunto il tuo obiettivo, congratulazioni!\n---------------------------------------------------------------------------', controllo_saldorisparmio;
                                  -- se l'ho raggiunto aggiorno il saldo rimanente a 0
                                  UPDATE test.salvadanaio
                                  set saldorimanente = 0
                                  WHERE contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;
                              else
                                  RAISE NOTICE E'---------------------------------------------------------------------------\n Saldo salvadanaio attuale: %. \n Saldo rimanente per raggiungere il tuo obiettivo: %!\n---------------------------------------------------------------------------', controllo_saldorisparmio, controllo_saldorimanente;
                              end if;

                          else
                              RAISE NOTICE E'---------------------------------------------------------------------------\nSaldo insufficiente!\n---------------------------------------------------------------------------';
                          end if;

                        else
                            RAISE NOTICE E'---------------------------------------------------------------------------\nInserisci una cifra valida!\n---------------------------------------------------------------------------';
                        end if;

                      else
                          RAISE NOTICE E'---------------------------------------------------------------------------\nNon esiste nessun salvadanaio con questo nome!\n---------------------------------------------------------------------------';
                      end if;

                  else
                      RAISE NOTICE E'---------------------------------------------------------------------------\nNon esiste nessun conto con questo Iban!\n---------------------------------------------------------------------------';
                  end if;
              return 1;
              END;
$$;





create function Prendi_Soldi_Dal_Salvadanaio(iban_in character varying, nome_in character varying, soldi_in double precision) returns integer
    language Plpgsql
as
$$
DECLARE
   controllo_iban int;
   controllo_salvadanaio int;
   controllo_saldorisparmio salvadanaio.saldorisparmio%TYPE;
   controllo_obiettivo salvadanaio.obiettivo%TYPE;
   controllo_saldorimanente salvadanaio.saldorimanente%TYPE;
BEGIN
    --controllo se esite l'iban inserito
   select count(iban)
   into controllo_iban
   from test.contocorrente
   where iban = iban_In;


   if controllo_iban > 0 then
       -- controllo se esiste il salvadanaio inserito
       select count(idsalvadanaio)
       into controllo_salvadanaio
       from test.salvadanaio
       where contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;


       if controllo_salvadanaio > 0 then
           -- select del saldo risparmio
           select saldorisparmio
           into controllo_saldorisparmio
           from test.salvadanaio
           where nomesalvadanaio ILIKE nome_In and contocorrente_iban = iban_In;

        -- controllo se il saldo risparmio è sufficiente
            if soldi_in > 0 then
               if controllo_saldorisparmio >= soldi_In then
                   -- aggiorno il saldo risparmio
                   UPDATE test.salvadanaio
                   SET saldorisparmio = saldorisparmio - soldi_In
                   WHERE contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;

                    -- aggiorno il saldo del conto corrente
                   UPDATE test.contocorrente
                   SET saldo = saldo + soldi_In
                   where iban = iban_In;

                -- select dell'obiettivo del salvadanaio
                   select obiettivo
                   into controllo_obiettivo
                   from test.salvadanaio
                   where nomesalvadanaio = nome_In and contocorrente_iban ILIKE iban_In;


                   -- vado a prendere il saldo risparmio aggiornato
                   select saldorisparmio
                   into controllo_saldorisparmio
                   from test.salvadanaio
                   where nomesalvadanaio ILIKE nome_In and contocorrente_iban = iban_In;

                    -- controllo se il salvadanaio ha raggiunto l'obiettivo
                   if  controllo_saldorisparmio >= controllo_obiettivo then
                       RAISE NOTICE E'---------------------------------------------------------------------------\n Saldo salvadanaio attuale: %.\n---------------------------------------------------------------------------', controllo_saldorisparmio;
                       -- aggiorno il saldo rimanente a 0
                       UPDATE test.salvadanaio
                       set saldorimanente = 0
                       WHERE contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;
                   else
                       -- aggiorno il saldo rimanente
                       UPDATE test.salvadanaio
                       set saldorimanente = salvadanaio.obiettivo - salvadanaio.saldorisparmio
                       WHERE contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;

                        -- select del saldo rimanente aggiornato
                       select saldorimanente
                       into controllo_saldorimanente
                       from test.salvadanaio
                       where nomesalvadanaio ILIKE nome_In and contocorrente_iban = iban_In;


                       RAISE NOTICE E'---------------------------------------------------------------------------\n Saldo salvadanaio attuale: %.\n Saldo rimanente per raggiungere il tuo obiettivo: %!\n---------------------------------------------------------------------------', controllo_saldorisparmio, controllo_saldorimanente;


                   end if;


                   RAISE NOTICE E'---------------------------------------------------------------------------\nSaldo ripreso con successo!\n---------------------------------------------------------------------------';


               else
                   RAISE NOTICE E'---------------------------------------------------------------------------\nSaldo salvadanaio insufficiente!\n---------------------------------------------------------------------------';
               end if;

            else
                RAISE NOTICE E'---------------------------------------------------------------------------\nInserisci una cifra valida!\n---------------------------------------------------------------------------';
            end if;

       else
           RAISE NOTICE E'---------------------------------------------------------------------------\nNon esiste nessun salvadanaio con questo nome!\n---------------------------------------------------------------------------';
       end if;

   else
       RAISE NOTICE E'---------------------------------------------------------------------------\nNon esiste nessun conto con questo Iban!\n---------------------------------------------------------------------------';
   end if;
    return 1;
END;
$$;








create function Rimuovi_Contocorrente_Con_Carta(iban_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
   controllo_esistenza int = 0;
BEGIN
    -- select controllo esistenza iban inserito
   SELECT count(iban)
   into controllo_esistenza
   from test.contocorrente
   where iban ilike iban_In;


   if controllo_esistenza > 0 then
       DELETE FROM test.contocorrente
       WHERE iban ilike iban_In;
       RAISE NOTICE E'---------------------------------------------------------------------------\nConto chiuso con successo! \n---------------------------------------------------------------------------';
   else
       RAISE NOTICE E'---------------------------------------------------------------------------\nNon esiste nessun conto con questo IBAN.\n---------------------------------------------------------------------------';
   end if;

    return 1;
END;
$$;







create function Rimuovi_Raccolta(iban_in character varying, nome_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
    controllo_raccolta int;
    controllo_id int;
BEGIN
    --select controllo esistenza nome raccolta inserito
    if nome_in ilike 'altro' then
        RAISE NOTICE E'---------------------------------------------------------------------------\nNon puoi rimuovere la raccolta "Altro"!\n---------------------------------------------------------------------------';
    else
        select count(nomeraccolta)
        into controllo_raccolta
        from test.raccolta t
        where t.nomeraccolta ILIKE nome_In AND t.contocorrente_iban ILIKE iban_in;
    
        if controllo_raccolta > 0 then
            select id_raccolta
            into controllo_id
            from test.raccolta
            where nomeraccolta = nome_in and contocorrente_iban = iban_in;

            update test.transazione
            set raccolta_id_fk = null
            where raccolta_id_fk = controllo_id;

            DELETE from test.raccolta
            where nomeraccolta ILIKE nome_In;
            RAISE NOTICE E'---------------------------------------------------------------------------\nRaccolta eliminata con successo!\n---------------------------------------------------------------------------';
        else
        RAISE NOTICE E'---------------------------------------------------------------------------\nInserisci un nome valido!\n---------------------------------------------------------------------------';
        end if;
    end if;
    return 1;
end;
$$;





create function Rimuovi_Salvadanaio(iban_in character varying, nome_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
   controllo_iban int;
   controllo_salvadanaio int;
   controllo_saldorisparmio salvadanaio.saldorisparmio%TYPE;
BEGIN
    -- select controllo esistenza iban inserito
   select count(iban)
   into controllo_iban
   from test.contocorrente
   where iban = iban_In;


   if controllo_iban > 0 then
       --select controllo esistenza salvadanaio inserito
       select count(idsalvadanaio)
       into controllo_salvadanaio
       from test.salvadanaio
       where contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;


       if controllo_salvadanaio > 0 then
           --select saldo risparmio salvadanaio
           select saldorisparmio
           into controllo_saldorisparmio
           from test.salvadanaio
           where nomesalvadanaio ILIKE nome_In and contocorrente_iban = iban_In;
           --se il saldo è = 0 allora elimina il salvadanaio, altrimenti no
           if controllo_saldorisparmio = 0 then
               DELETE 
               FROM test.salvadanaio
               WHERE contocorrente_iban = iban_In AND nomesalvadanaio ILIKE nome_In;
               RAISE NOTICE E'---------------------------------------------------------------------------\nSalvadanaio eliminato con successo!\n---------------------------------------------------------------------------';
           else
               RAISE NOTICE E'---------------------------------------------------------------------------\nRimuovi prima i tuoi risparmi!\n---------------------------------------------------------------------------';
           end if;
       else
           RAISE NOTICE E'---------------------------------------------------------------------------\nNon esiste nessun salvadanaio con questo nome!\n---------------------------------------------------------------------------';
       end if;
   else
       RAISE NOTICE E'---------------------------------------------------------------------------\nNon esiste nessun conto con questo Iban!\n---------------------------------------------------------------------------';
   end if;
    return 1;
END;
$$;




create function Upgrade_Carta(pan_in character varying) returns integer
    language Plpgsql
as
$$
DECLARE
  controllo_esistenza int;
  controllo_iban test.contocorrente.iban%TYPE;
BEGIN
    -- controllo esistenza carta inserita
  select count(pan)
  into controllo_esistenza
  from test.carta
  where pan = pan_In;


  if controllo_esistenza > 0 then
      -- select dell'iban collegato alla carta, per chiamare la funzione 'controllo_tipocarta'
        select contocorrente_iban
        into controllo_iban
        from test.carta
        where pan = pan_in;


      if (NOT test.controllo_tipocarta(controllo_iban)) then
        if(test.controllo_saldo(5, controllo_iban)) then
          update test.contocorrente
          set saldo = saldo - 5
          where iban = controllo_iban;

          update test.contocorrente
          set saldo = saldo + 5
          where iban = 'IT5535226849092148923886796';

          insert into test.transazione(importo, causale, datatransazione, orariotransazione, iban2, tipologiapagamento, tipotransazione, categoriaentrata, categoriauscita, iban1, raccolta_id_fk)
          values (5,'Upgrade Carta',current_date, current_time, controllo_iban, null, 'Invia a', null, 'Altro', 'IT5535226849092148923886796', null),
                 (5,'Upgrade Carta',current_date, current_time, 'IT5535226849092148923886796', null, 'Riceve da', 'Altro', null, controllo_iban, null);


          UPDATE test.carta
          set tipocarta = 'CartaDiCredito', maxinvio = default, price_upgrade=5
          where pan = pan_In;
          RAISE NOTICE E'---------------------------------------------------------------------------\nUpgrade effettuato! \n---------------------------------------------------------------------------';
        end if;
      else
          RAISE NOTICE E'---------------------------------------------------------------------------\nNon puoi effettuare l\'upgrade perchè la carta è già di tipo Carta di Credito \n---------------------------------------------------------------------------';

       end if;
   else
      RAISE NOTICE E'---------------------------------------------------------------------------\nInserisci una carta valida!\n---------------------------------------------------------------------------';
  end if;
    return 1;
END;
$$;












