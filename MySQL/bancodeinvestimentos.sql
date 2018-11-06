CREATE TABLE `bancodeinvestimentos`.`clientes` (
  `idCliente` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `dataNasc` DATE NOT NULL,
  `senha` INT NOT NULL,
  PRIMARY KEY (`idCliente`));


  CREATE TABLE `bancodeinvestimentos`.`conta_corrente` (
  `idContaCorrente` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `saldo` DECIMAL(10,2) NOT NULL,
  `idCliente` BIGINT(9) NOT NULL,
  PRIMARY KEY (`idContaCorrente`),
  INDEX `fk_conta_corrente_cliente_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_conta_corrente_cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bancodeinvestimentos`.`clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `bancodeinvestimentos`.`conta_poupanca` (
  `idConta_Poupanca` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `idCliente` BIGINT(9) NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idConta_Poupanca`),
  INDEX `fk_conta_poupanca_cliente_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_conta_poupanca_cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bancodeinvestimentos`.`clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `bancodeinvestimentos`.`movimento_conta` (
  `idMovimento_Conta` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `idContaCorrente` BIGINT(9) NOT NULL,
  `tipo_movimento` INT(2) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`idMovimento_Conta`),
  INDEX `fk_movimento_conta_corrente_idx` (`idContaCorrente` ASC) VISIBLE,
  CONSTRAINT `fk_movimento_conta_corrente`
    FOREIGN KEY (`idContaCorrente`)
    REFERENCES `bancodeinvestimentos`.`conta_corrente` (`idContaCorrente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `bancodeinvestimentos`.`conta_poupanca_deposito` (
  `idDeposito` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `idContaPoupanca` BIGINT(9) NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  `dataInicio` DATE NOT NULL,
  `dataTermino` DATE NOT NULL,
  `aniversario` DATE NOT NULL,
  `status` TINYINT NOT NULL,
  PRIMARY KEY (`idDeposito`),
  INDEX `fk_conta_poupanca_deposito_cp_idx` (`idContaPoupanca` ASC) VISIBLE,
  CONSTRAINT `fk_conta_poupanca_deposito_cp`
    FOREIGN KEY (`idContaPoupanca`)
    REFERENCES `bancodeinvestimentos`.`conta_poupanca` (`idConta_Poupanca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `bancodeinvestimentos`.`cdb` (
  `idCDB` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `saldoTotal` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idCDB`));

CREATE TABLE `bancodeinvestimentos`.`cdb_movimento` (
  `idCDB_movimento` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `idCDB` BIGINT(9) NOT NULL,
  `idCliente` BIGINT(9) NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  `dataInicio` DATE NOT NULL,
  `dataTermino` DATE NOT NULL,
  `status` TINYINT NOT NULL,
  PRIMARY KEY (`idCDB_movimento`),
  INDEX `fk_cdb_movimento_cdb_idx` (`idCDB` ASC) VISIBLE,
  INDEX `fk_cdb_movimento_cliente_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_cdb_movimento_cdb`
    FOREIGN KEY (`idCDB`)
    REFERENCES `bancodeinvestimentos`.`cdb` (`idCDB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cdb_movimento_cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bancodeinvestimentos`.`clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('LEONARDO', '11111111111', '1991/07/06', 12345);
INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('ANDRÉ', '11111111111', '1996/01/01', 12346);
INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('PEDRO', '22222222222', '1997/05/07', 12347);
INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('MARIA', '33333333333', '2000/09/12', 12348);
INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('JORGE', '44444444444', '1989/03/23', 12349);

INSERT INTO conta_corrente(SALDO, IDCLIENTE) values(0, 1);
INSERT INTO conta_corrente(SALDO, IDCLIENTE) values(0, 2);
INSERT INTO conta_corrente(SALDO, IDCLIENTE) values(0, 3);
INSERT INTO conta_corrente(SALDO, IDCLIENTE) values(0, 4);
INSERT INTO conta_corrente(SALDO, IDCLIENTE) values(0, 5);

INSERT INTO conta_poupanca(IDCLIENTE, SALDO) VALUES(1, 0);
INSERT INTO conta_poupanca(IDCLIENTE, SALDO) VALUES(2, 0);
INSERT INTO conta_poupanca(IDCLIENTE, SALDO) VALUES(3, 0);
INSERT INTO conta_poupanca(IDCLIENTE, SALDO) VALUES(4, 0);
INSERT INTO conta_poupanca(IDCLIENTE, SALDO) VALUES(5, 0);

INSERT INTO cdb(nome, saldoTotal) VALUES('CDB ADS', 0.00);
INSERT INTO cdb(nome, saldoTotal) VALUES('CDB EC', 0.00);
INSERT INTO cdb(nome, saldoTotal) VALUES('CDB EM', 0.00);
INSERT INTO cdb(nome, saldoTotal) VALUES('CDB EAD', 0.00);



update conta_corrente set saldo = 1000.00 where idContaCorrente = 1;
insert into movimento_conta(idContaCorrente, tipo_movimento, descricao, valor, data) values(1,1,"Deposito",1000.00,"2018/11/1");

update conta_corrente set saldo = 5000.00 where idContaCorrente = 2;
insert into movimento_conta(idContaCorrente, tipo_movimento, descricao, valor, data) values(2,1,"Deposito",5000.00,"2018/11/1");

update conta_corrente set saldo = 7000.00 where idContaCorrente = 3;
insert into movimento_conta(idContaCorrente, tipo_movimento, descricao, valor, data) values(3,1,"Deposito",7000.00,"2018/11/1");

update conta_corrente set saldo = 10000.00 where idContaCorrente = 4;
insert into movimento_conta(idContaCorrente, tipo_movimento, descricao, valor, data) values(4,1,"Deposito",10000.00,"2018/11/1");

update conta_corrente set saldo = 12000.00 where idContaCorrente = 5;
insert into movimento_conta(idContaCorrente, tipo_movimento, descricao, valor, data) values(5,1,"Deposito",12000.00,"2018/11/1");


