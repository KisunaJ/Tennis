CREATE TABLE Jugador(
	
	idJugador INT NOT NULL,
	CONSTRAINT PK_Jugador PRIMARY KEY (idJugador)
);

CREATE TABLE Cancha(
	
	idCancha INT NOT NULL, Nombre VARCHAR(100) NOT NULL , Direccion VARCHAR(100) NOT NULL, 
 PRIMARY KEY (idCancha)
);

CREATE TABLE Partido(
	
	idPartido INT NOT NULL, fechaComienzo TIMESTAMP NOT NULL , 
	Estado VARCHAR(100) NOT NULL, idJugadorLocal INT NOT NULL, 
	idJugadorVisitante INT NOT NULL, CONSTRAINT PK_Partido PRIMARY KEY (idPartido)
	
);

CREATE TABLE Entrenador(
	
	idEntrenador INT, 
	CONSTRAINT PK_Entrenador PRIMARY KEY (idEntrenador)
);

ALTER TABLE Entrenador

ADD COLUMN Nombre VARCHAR(100)
ADD COLUMN idJugadorVisitante INTEGER,
ADD COLUMN idCancha INTEGER,
ADD CONSTRAINT FK_idCancha FOREIGN KEY (idCancha) REFERENCES Cancha(idCancha)
ADD CONSTRAINT FK_idJugadorLocal FOREIGN KEY (idJugadorLocal) REFERENCES Jugador(idJugador),
ADD CONSTRAINT FK_idJugadorVisitante FOREIGN KEY (idJugadorVisitante) REFERENCES Jugador(idJugador)

SELECT * FROM Entrenador;
SELECT * FROM Jugador;
SELECT * FROM Partido;
SELECT * FROM Cancha;

INSERT INTO Entrenador VALUES (01, 'Helena Nito')
INSERT INTO Entrenador VALUES (02, 'Carmen Tita')
INSERT INTO Jugador VALUES (01, 'Josefa Rolito', 1800, 01)
INSERT INTO Jugador VALUES (02, 'Aquiles Baeza', 1500, 01)
INSERT INTO Jugador VALUES (03, 'Mario Neetha', 1800, 02)
INSERT INTO Jugador VALUES (04, 'Nicolas Riera', 1200)

INSERT INTO Cancha VALUES (01, 'Suzanne Chatrier', 'Paris')
INSERT INTO Cancha VALUES (02, 'Philip Lenglen', 'Paris')

INSERT INTO Partido VALUES (01, current_timestamp, 'EN JUEGO ',01, 04, 01)
INSERT INTO Partido VALUES (02, current_timestamp, 'EN JUEGO ',03, 02, 01)

INSERT INTO Partido VALUES (03, current_timestamp, 'FINALIZADO ',04, 02, 02)

UPDATE Jugador SET puntos = puntos + 900 WHERE idjugador = 04






