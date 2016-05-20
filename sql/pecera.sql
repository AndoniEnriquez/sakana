DROP database if exists pecera;
CREATE database pecera;
use pecera;

CREATE TABLE TIPOCOMIDA (
tipocomida_id			INT(3) AUTO_INCREMENT,
descripcionTipoComida	VARCHAR(20),
CONSTRAINT TIPOCOMIDA_PRIMARY_KEY PRIMARY KEY (tipocomida_id));

CREATE TABLE COMIDA (
comida_id			INT(3) AUTO_INCREMENT,
nombreComida		VARCHAR(20),
descripcionComida	VARCHAR(45),
tipocomida_id		INT(3) NOT NULL,
CONSTRAINT COMIDA_PRIMARY_KEY PRIMARY KEY (comida_id),
CONSTRAINT COMIDA_TIPOCOMIDA_FK FOREIGN KEY (tipocomida_id) REFERENCES TIPOCOMIDA (tipocomida_id));

CREATE TABLE PECERA (
pecera_id			INT(3) AUTO_INCREMENT,
nombrePecera 		VARCHAR(45),
horacomida			TIME,
IP					VARCHAR(45),
Capacidad			INT(3),
CONSTRAINT PECERA_PRIMARY_KEY PRIMARY KEY (pecera_id));

CREATE TABLE REGCOMIDA (
comida_id			INT(3) NOT NULL,
pecera_id			INT(3) NOT NULL,
datetime			DATETIME NOT NULL,
CONSTRAINT REGCOMIDA_PRIMARY_KEY PRIMARY KEY (comida_id,pecera_id,datetime),
CONSTRAINT REGCOMIDA_COMIDA_FK FOREIGN KEY (comida_id) REFERENCES COMIDA (comida_id),
CONSTRAINT REGCOMIDA_PECERA_FK FOREIGN KEY (pecera_id) REFERENCES PECERA (pecera_id));

CREATE TABLE TIPOPEZ (
tipopez_id			INT(3) AUTO_INCREMENT,
descripcion			VARCHAR(45),
CONSTRAINT TIPOPEZ_PRIMARY_KEY PRIMARY KEY (tipopez_id));

CREATE TABLE DUENO (
dueno_id			INT(3) AUTO_INCREMENT,
nombreDueno			VARCHAR(45),
password			VARCHAR(45),
CONSTRAINT TIPOPEZ_PRIMARY_KEY PRIMARY KEY (dueno_id));

CREATE TABLE PEZ (
pez_id				INT(3) AUTO_INCREMENT,
nombrePez			VARCHAR(45),
genero				VARCHAR(45),
tipopez_id			INT(3) NOT NULL,
dueno_id			INT(3) NOT NULL,
pecera_id			INT(3) NOT NULL,
CONSTRAINT PEZ_PRIMARY_KEY PRIMARY KEY (pez_id),
CONSTRAINT PEZ_TIPOPEZ_FK FOREIGN KEY (tipopez_id) REFERENCES TIPOPEZ (tipopez_id),
CONSTRAINT PEZ_DUENO_FK FOREIGN KEY (dueno_id) REFERENCES DUENO (dueno_id),
CONSTRAINT PEZ_PECERA_FK FOREIGN KEY (pecera_id) REFERENCES PECERA (pecera_id));

CREATE TABLE TIPOMEDICION (
tipomedicion_id		INT(3) AUTO_INCREMENT,
nombremedicion		VARCHAR(45),
CONSTRAINT TIPOMEDICION_PRIMARY_KEY PRIMARY KEY (tipomedicion_id));

CREATE TABLE MEDICION (
medicion_id			INT(3) AUTO_INCREMENT,
valor				FLOAT,
datetimeMedicion	DATETIME,
tipomedicion_id 	INT NOT NULL,
pecera_id			INT NOT NULL,
CONSTRAINT MEDICION_PRIMARY_KEY PRIMARY KEY (medicion_id),
CONSTRAINT MEDICION_TIPOMEDICION FOREIGN KEY (tipomedicion_id) REFERENCES TIPOMEDICION (tipomedicion_id),
CONSTRAINT MEDICION_PECERA_FK FOREIGN KEY (pecera_id) REFERENCES PECERA (pecera_id));

INSERT INTO dueno VALUES (1,'gorospe','dima');
INSERT INTO dueno VALUES (2, 'ander','gasteiz');
INSERT INTO dueno values (3, 'enriquez','durango');
INSERT INTO dueno values (4, 'alzelai','durango');

INSERT INTO tipomedicion VALUES(1,'Nivel PH');
INSERT INTO tipomedicion VALUES(2,'Temperatura');

INSERT INTO pecera VALUES (1,"Pecera de Iripollen", null, "69.69.69.69", 5);

INSERT INTO tipopez VALUES (1, "GoldFish");

INSERT INTO pez VALUES (1, "Iripollen", "Desconocido", 1, 1, 1);

INSERT INTO pecera (nombrePecera,horacomida,IP,capacidad) VALUES ("Pecera de MIERDA!!!", "00:11", "69.69.69.69", 5);


