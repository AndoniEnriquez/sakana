DROP database if exists pecera;
CREATE database pecera;
use pecera;


CREATE TABLE COMIDA (
comida_id	 		INT(3) AUTO_INCREMENT,
nombreComida 		VARCHAR(20),
descripcionComida 	VARCHAR(70),
CONSTRAINT COMIDA_PRIMARY_KEY PRIMARY KEY (comida_id));

CREATE TABLE PECERA (
pecera_id		 	INT(3) AUTO_INCREMENT,
nombrePecera 	 	VARCHAR(45),
horacomida		 	TIME,
IP				 	VARCHAR(45) UNIQUE,
Capacidad		 	INT(3),
comida_id			INT(3),
CONSTRAINT PECERA_PRIMARY_KEY PRIMARY KEY (pecera_id),
CONSTRAINT PECERA_COMIDA_FK FOREIGN KEY (comida_id) REFERENCES COMIDA (comida_id));

CREATE TABLE REGCOMIDA (
comida_id		 	INT(3) NOT NULL,
pecera_id		 	INT(3) NOT NULL,
datetime		 	DATETIME NOT NULL,
CONSTRAINT REGCOMIDA_PRIMARY_KEY PRIMARY KEY (comida_id,pecera_id,datetime),
CONSTRAINT REGCOMIDA_COMIDA_FK FOREIGN KEY (comida_id) REFERENCES COMIDA (comida_id),
CONSTRAINT REGCOMIDA_PECERA_FK FOREIGN KEY (pecera_id) REFERENCES PECERA (pecera_id));

CREATE TABLE TIPOPEZ (
tipopez_id		 	INT(3) AUTO_INCREMENT,
descripcion		 	VARCHAR(45),
phMin			 	FLOAT(3),
phMax				FLOAT(3),
tempMin			 	FLOAT(3),
tempMax	 			FLOAT(3),	 
CONSTRAINT TIPOPEZ_PRIMARY_KEY PRIMARY KEY (tipopez_id));

CREATE TABLE DUENO (
dueno_id		 	INT(3) AUTO_INCREMENT,
nombreDueno		 	VARCHAR(45),
password		 	VARCHAR(45),
CONSTRAINT TIPOPEZ_PRIMARY_KEY PRIMARY KEY (dueno_id));

CREATE TABLE PEZ (
pez_id			 	INT(3) AUTO_INCREMENT,
nombrePez		 	VARCHAR(45),
genero			 	VARCHAR(45),
tipopez_id		 	INT(3) NOT NULL,
dueno_id		 	INT(3) NOT NULL,
pecera_id		  	INT(3) NOT NULL,
CONSTRAINT PEZ_PRIMARY_KEY PRIMARY KEY (pez_id),
CONSTRAINT PEZ_TIPOPEZ_FK FOREIGN KEY (tipopez_id) REFERENCES TIPOPEZ (tipopez_id),
CONSTRAINT PEZ_DUENO_FK FOREIGN KEY (dueno_id) REFERENCES DUENO (dueno_id),
CONSTRAINT PEZ_PECERA_FK FOREIGN KEY (pecera_id) REFERENCES PECERA (pecera_id));

CREATE TABLE TIPOMEDICION (
tipomedicion_id	 	INT(3) AUTO_INCREMENT,
nombremedicion	 	VARCHAR(45),
CONSTRAINT TIPOMEDICION_PRIMARY_KEY PRIMARY KEY (tipomedicion_id));

CREATE TABLE MEDICION (
medicion_id		 	INT(3) AUTO_INCREMENT,
valor			 	FLOAT,
datetimeMedicion 	DATETIME,
tipomedicion_id  	INT NOT NULL,
pecera_id		 	INT NOT NULL,
CONSTRAINT MEDICION_PRIMARY_KEY PRIMARY KEY (medicion_id),
CONSTRAINT MEDICION_TIPOMEDICION FOREIGN KEY (tipomedicion_id) REFERENCES TIPOMEDICION (tipomedicion_id),
CONSTRAINT MEDICION_PECERA_FK FOREIGN KEY (pecera_id) REFERENCES PECERA (pecera_id));

INSERT INTO comida VALUES (1, "Comida de Goldfish", "Comida idonea para darselo a los goldfish");
INSERT INTO comida VALUES (2, "Peces pequeños", "Se les suele dar a las pirañas o tiburones");


INSERT INTO pecera(nombrePecera,horacomida,IP,capacidad) VALUES ("MiniPecera", "02:00", "192.168.1.15", 1);



INSERT INTO regcomida VALUES (1,1, "2016/05/20 11:00");
INSERT INTO regcomida VALUES (2,1, "2016/05/21 11:00");
INSERT INTO regcomida VALUES (3,1, "2016/05/22 11:00");

INSERT INTO dueno VALUES (1,'gorospe','dima');
INSERT INTO dueno VALUES (2, 'ander','gasteiz');
INSERT INTO dueno values (3, 'enriquez','123');
INSERT INTO dueno values (4, 'alzelai','durango');

INSERT INTO tipomedicion VALUES(1,'Nivel PH');
INSERT INTO tipomedicion VALUES(2,'Temperatura');

INSERT INTO tipopez VALUES (1, "GoldFish", 6,8,10,30);
INSERT INTO tipopez VALUES (2, "Tiburon",5,7,10,200);
INSERT INTO tipopez VALUES (3, "Pez payaso",6,8,10,30);
INSERT INTO tipopez VALUES (4, "Piraña",6,8,10,30);
INSERT INTO tipopez VALUES (5, "Desconocido",1,10,0,40);

INSERT INTO pez VALUES (1, "Aliyah", "Hembra", 1, 3, 1);

insert into medicion values (1,6.5,"2016/05/20 11:00", 1,1);
insert into medicion values (2,8,"2016/05/20 11:00", 1,2);
insert into medicion values (3,23,"2016/05/20 11:00", 2,1);
insert into medicion values (4,25,"2016/05/20 11:00", 2,2);
insert into medicion values (5,21,"2016/05/21 11:00", 2,1);
insert into medicion values (6,7.2,"2016/05/21 11:00", 1,3);
insert into medicion values (7,24,"2016/05/21 11:00", 2,3);

