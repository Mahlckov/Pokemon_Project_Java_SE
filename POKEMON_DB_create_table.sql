USE POKEMON_DB
GO
CREATE TABLE dbo.Pokedex(
	idPokemon int IDENTITY(1,1) NOT NULL,
	Nom nvarchar(15) NOT NULL,
	type nvarchar(15) NOT NULL,
	PC int NOT NULL,
	PV int NOT NULL,
	Shiny bit NOT NULL,
	
 CONSTRAINT PK_Pokemon PRIMARY KEY  
(
	idPokemon
))
GO
