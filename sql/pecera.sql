DROP database if exists pecera;
CREATE database pecera;
use pecera;

CREATE TABLE TIPOCOMIDA (
tipocomida_id			INT(3),
descripcionTipoComida	VARCHAR(20),
CONSTRAINT TIPOCOMIDA_PRIMARY_KEY PRIMARY KEY (tipocomida_id));

CREATE TABLE COMIDA (
comida_id			INT(3),
nombreComida		VARCHAR(20),
descripcionComida	VARCHAR(45),
tipocomida_id		INT(3) NOT NULL,
CONSTRAINT COMIDA_PRIMARY_KEY PRIMARY KEY (comida_id),
CONSTRAINT COMIDA_TIPOCOMIDA_FK FOREIGN KEY (tipocomida_id) REFERENCES TIPOCOMIDA (tipocomida_id));

CREATE TABLE PECERA (
pecera_id			INT(3),
horacomida			VARCHAR(45),
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
tipopez_id			INT(3),
descripcion			VARCHAR(45),
CONSTRAINT TIPOPEZ_PRIMARY_KEY PRIMARY KEY (tipopez_id));

CREATE TABLE DUENO (
dueno_id			INT(3),
nombreDueno			VARCHAR(45),
password			VARCHAR(45),
CONSTRAINT TIPOPEZ_PRIMARY_KEY PRIMARY KEY (dueno_id));

CREATE TABLE PEZ (
pez_id				INT(3),
nombrePez			VARCHAR(20),
genero				VARCHAR(10),
tipopez_id			INT(3) NOT NULL,
dueno_id			INT(3) NOT NULL,
pecera_id			INT(3) NOT NULL,
CONSTRAINT PEZ_PRIMARY_KEY PRIMARY KEY (pez_id),
CONSTRAINT PEZ_TIPOPEZ_FK FOREIGN KEY (tipopez_id) REFERENCES TIPOPEZ (tipopez_id),
CONSTRAINT PEZ_DUENO_FK FOREIGN KEY (dueno_id) REFERENCES DUENO (dueno_id),
CONSTRAINT PEZ_PECERA_FK FOREIGN KEY (pecera_id) REFERENCES PECERA (pecera_id));

CREATE TABLE TIPOMEDICION (
tipomedicion_id		INT(3),
nombremedicion		VARCHAR(45),
CONSTRAINT TIPOMEDICION_PRIMARY_KEY PRIMARY KEY (tipomedicion_id));

CREATE TABLE MEDICION (
medicion_id			INT(3),
valor				FLOAT,
datetimeMedicion	DATETIME,
tipomedicion_id 	INT NOT NULL,
pecera_id			INT NOT NULL,
CONSTRAINT MEDICION_PRIMARY_KEY PRIMARY KEY (medicion_id),
CONSTRAINT MEDICION_TIPOMEDICION FOREIGN KEY (tipomedicion_id) REFERENCES TIPOMEDICION (tipomedicion_id),
CONSTRAINT MEDICION_PECERA_FK FOREIGN KEY (pecera_id) REFERENCES PECERA (pecera_id));

INSERT INTO dueno VALUES (1,'joseba','dima');
INSERT INTO dueno VALUES (2, 'ander','gasteiz');
INSERT INTO dueno values (3, 'enriquez','durango');
INSERT INTO dueno values (4, 'alzelai','durango');

