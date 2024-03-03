Create database Exercicio_View1
go
use Exercicio_View1
go

Create table Onibus(
placa char(7) not null,
marca varchar(15) not null,
ano int not null,
descricao varchar(20) not null
primary key(placa)
)
go
Create table Motorista(
codigo int not null,
nome varchar(40) not null,
naturalidade varchar(40) not null,
primary key(codigo)
)
go
Create table Viagem (
codigo int not null,
onibus char(7) not null,
motorista int not null,
hora_saida int not null,
hora_chegada int not null,
partida varchar(40) not null,
destino varchar(40) not null
primary key(codigo)
foreign key(onibus) references Onibus(placa),
foreign key(motorista) references Motorista(codigo)
)
go

INSERT INTO Motorista (codigo, nome, naturalidade) 
VALUES 
(12341, 'Julio Cesar', 'Sao Paulo'),
(12342, 'Mario Carmo', 'Americana'),
(12343, 'Lucio Castro', 'Campinas'),
(12344, 'Andre Figueiredo', 'Sao Paulo'),
(12345, 'Luiz Carlos', 'Sao Paulo'),
(12346, 'Carlos Roberto', 'Campinas'),
(12347, 'Joao Paulo', 'Sao Paulo');
GO

INSERT INTO Onibus (placa, marca, ano, descricao) 
VALUES 
('adf0965', 'Mercedes', 2009, 'Leito'),
('bhg7654', 'Mercedes', 2012, 'Sem Banheiro'),
('dtr2093', 'Mercedes', 2017, 'Ar Condicionado'),
('gui7625', 'Volvo', 2014, 'Ar Condicionado'),
('jhy9425', 'Volvo', 2018, 'Leito'),
('lmk7485', 'Mercedes', 2015, 'Ar Condicionado'),
('aqw2374', 'Volvo', 2014, 'Leito');

GO

INSERT INTO Viagem (codigo, onibus, motorista, hora_saida, hora_chegada, partida, destino) 
VALUES 
(101, 'adf0965', 12343, 10, 12, 'sao paulo', 'campinas'),
(102, 'gui7625', 12341, 7, 12, 'sao paulo', 'araraquara'),
(103, 'bhg7654', 12345, 14, 22, 'sao paulo', 'rio de janeiro'),
(104, 'dtr2093', 12344, 18, 21, 'sao paulo', 'sorocaba'),
(105, 'aqw2374', 12342, 11, 17, 'sao paulo', 'ribeirao preto'),
(106, 'jhy9425', 12347, 10, 19, 'sao paulo', 'sao jose do rio preto'),
(107, 'lmk7485', 12346, 13, 20, 'sao paulo', 'curitiba'),
(108, 'adf0965', 12343, 14, 16, 'campinas', 'sao paulo'),
(109, 'gui7625', 12341, 14, 19, 'araraquara', 'sao paulo'),
(110, 'bhg7654', 12345, 0, 8, 'rio de janeiro', 'sao paulo'),
(111, 'dtr2093', 12344, 22, 1, 'sorocaba', 'sao paulo'),
(112, 'aqw2374', 12342, 19, 5, 'ribeirao preto', 'sao paulo'),
(113, 'jhy9425', 12347, 22, 7, 'sao jose do rio preto', 'sao paulo'),
(114, 'lmk7485', 12346, 0, 7, 'curitiba', 'sao paulo');
go
--Union e views
Select placa as ID, marca as Nome 
from Onibus
Union
Select CAST(codigo as varchar(10)) as ID, nome as Nome 
from Motorista

go

Create view v_motorista_onibus 
as
Select placa as ID, marca as Nome 
from Onibus
Union
Select CAST(codigo as varchar(10)) as ID, nome as Nome 
from Motorista

go

Create view v_descriscao_onibus 
as
Select v.codigo, m.nome, SUBSTRING(o.placa,1,3) +'-' + SUBSTRING(o.placa,4,4) as placa, o.marca, o.ano, o.descricao
from Onibus o, Viagem v, Motorista m
Where o.placa = v.onibus
And	  v.motorista = m.codigo

go

Create view v_descriscao_viagem
as 
Select v.codigo, SUBSTRING(o.placa,1,3) +'-' + SUBSTRING(o.placa,4,4) as placa, CONVERT(varchar(5), DATEADD(SECOND, v.hora_saida * 3600, 0), 108) AS hora_saida,
CONVERT(varchar(5), DATEADD(SECOND, v.hora_chegada * 3600, 0), 108) AS hora_chegada, v.partida, v.destino
from Onibus o, Viagem v
where o.placa = v.onibus
