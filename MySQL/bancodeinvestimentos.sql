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

CREATE TABLE `bancodeinvestimentos`.`fundo` (
  `idFundo` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `saldoTotal` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`idFundo`));

CREATE TABLE `bancodeinvestimentos`.`fundo_movimento` (
  `idFundoMovimento` BIGINT(9) NOT NULL AUTO_INCREMENT,
  `idFundo` BIGINT(9) NOT NULL,
  `idCliente` BIGINT(9) NOT NULL,
  `saldo` DECIMAL(10,2) NOT NULL,
  `dataInicio` DATE NOT NULL,
  `status` TINYINT NOT NULL,
  PRIMARY KEY (`idFundoMovimento`),
  INDEX `fk_fundo_movimento_fundo_idx` (`idFundo` ASC) VISIBLE,
  INDEX `fk_fundo_movimento_cliente_idx` (`idCliente` ASC) VISIBLE,
  CONSTRAINT `fk_fundo_movimento_fundo`
    FOREIGN KEY (`idFundo`)
    REFERENCES `bancodeinvestimentos`.`fundo` (`idFundo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fundo_movimento_cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `bancodeinvestimentos`.`clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('Administrador', '1111', '1991/07/06', 1111);
INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('Leonardo', '2222', '1996/01/01', 2222);
INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('Diego', '3333', '1997/05/07', 3333);
INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('Maria', '4444', '2000/09/12', 4444);
INSERT INTO clientes(NOME, CPF, DATANASC, SENHA) VALUES('Ana', '5555', '1989/03/23', 5555);

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

INSERT INTO cdb(nome, saldoTotal) VALUES('CDB ADS 95% CDI', 0.00);
INSERT INTO cdb(nome, saldoTotal) VALUES('CDB EC 100% CDI', 0.00);
INSERT INTO cdb(nome, saldoTotal) VALUES('CDB EM 105% CDI', 0.00);
INSERT INTO cdb(nome, saldoTotal) VALUES('CDB EAD 110% CDI', 0.00);



