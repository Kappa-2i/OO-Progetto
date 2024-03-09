-- Creazione della tabella Account
create table Account
(
    Email    Varchar(255) primary key,
    Nome     Varchar(255) not null,
    Password Varchar(255) not null,
    Cognome  Varchar(255) not null
)


-- Definire il tipo ENUM per TipoCarta
CREATE TYPE tipo_carta AS ENUM ('CartaDiCredito', 'CartaDiDebito');

-- Creare o modificare la tabella Carta per usare il tipo ENUM
create table Carta
(
    Pan Varchar(16) primary key,
    Pin Varchar(5) not null unique,
    Cvv Varchar(3) not null unique,
    Tipocarta Tipo_Carta not null,
    Maxinvio Double Precision,
    Contocorrente_Iban Varchar(27) not null references Contocorrente(IBAN) on delete cascade,
    Price_Upgrade Double Precision default 5
)


-- Creazione della tabella Salvadanaio
CREATE TABLE Salvadanaio (
  IdSalvadanaio SERIAL PRIMARY KEY ,
  NomeSalvadanaio VARCHAR(255) NOT NULL,
  Obiettivo DECIMAL NOT NULL,
  SaldoRisparmio DECIMAL DEFAULT 0 NOT NULL,
  SaldoRimanente DECIMAL NOT NULL,
  Descrizione VARCHAR(255) DEFAULT NULL,
  ContoCorrente_IBAN VARCHAR(27) REFERENCES ContoCorrente(IBAN) ON DELETE CASCADE,
  DataCreazione date not null,
  UNIQUE(NomeSalvadanaio, ContoCorrente_IBAN),
);


-- Prima, definiamo il tipo ENUM per TipologiaPagamento
CREATE TYPE tipologia_pagamento AS ENUM ('Bonifico', 'BonificoIstantaneo');
CREATE TYPE tipo_transazione AS ENUM ('Riceve da', 'Invia a');


-- Poi, utilizziamo il tipo ENUM nella definizione della tabella Transazione
create table Transazione
(
    Idtransazione Serial primary key,
    Importo Double Precision,
    Causale Varchar(255) not null,
    Datatransazione Date,
    Orariotransazione Time,
    Iban2 Varchar(27)  not null references Contocorrente(IBAN) on delete cascade,
    Tipologiapagamento Tipologia_Pagamento,
    Tipotransazione    Tipo_Transazione,
    Categoriaentrata   Varchar(255),
    Categoriauscita    Varchar(255),
    Iban1              Varchar(27),
    Raccolta_Id_Fk     Integer references Raccolta(id_raccolta)
);



-- Creazione della tabella Raccolta
create table Raccolta
(
    Nomeraccolta Varchar(255) not null,
    Datacreazione Date not null,
    Descrizione Varchar(1000) default NULL,
    Contocorrente_Iban Varchar not null references Contocorrente(IBAN),
    Id_Raccolta Serial primary key
);


--Creazione della tabella ContoCorrente
create table Contocorrente
(
    Iban Varchar(27) primary key,
    Dataapertura  Date not null,
    Saldo Numeric default 0 not null,
    Account_Email Varchar(255) not null references Account(Email) on delete cascade
);
