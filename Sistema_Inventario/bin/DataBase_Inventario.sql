create database db_inventario;

use db_inventario;

create table tb_usuarios(
idusuario	int primary key auto_increment,
usuario 	varchar(20) not null,
pass		varchar(30) not null,
nombre		varchar(50),
tipo 	 	tinyint, -- 0ADMIN 1VENTAS
estado		tinyint -- 0INACTIVO 1IACTIVO
); 

create table tb_clientes(
idcliente	int primary key auto_increment,
tipodoc		varchar(30),
nrodoc		varchar(11),
nombre		varchar(150),
direccion	varchar(150),
telefono	varchar(15),
correo		varchar(50),
estado		tinyint -- 0INAACTIVO 1ACTIVO
);

create table tb_distribuidores(
iddistrib	int primary key auto_increment,
tipodoc		varchar(30),
nrodoc		varchar(11),
nombre		varchar(150),
direccion	varchar(150),
perscontact	varchar(150),
telefono	varchar(15),
correo		varchar(50),
estado		tinyint -- 0INAACTIVO 1ACTIVO
);

create table tb_productos(
codproducto	int primary key auto_increment,
codbarra	varchar(100),
producto	varchar(200),
detalles	varchar(200),
marca		varchar(30),
color		varchar(30),
lote		varchar(50),
laboratorio	varchar(50),
unimedida	varchar(30),
fechaVenc	date,
categoria	varchar(30),
almacen		varchar(50),
iddistrib	int,
cantidad	float,
cantmin		float,
precioCo	float,
precioVe	float,
ptjganancia	float,
estado		tinyint, -- 0INACTIVO 1ACTIVO

promo1		varchar(30),
cantp1		float,
prep1		float,
promo2		varchar(30),
cantp2		float,
prep2		float
);  

create table tb_ventas(
codventa	int primary key auto_increment,
idcliente	int,
fecha		datetime,
idusuario	int,
totcompra 	float,
totventa	float,
ganancia	float,
descuento	float,
saldo		float,
nota		varchar(200),
metpago1	tinyint, -- 0Efectivo 3Pago con tarjeta crédito/débito 2Depósito 3Transferencia  4CRÉDITO
montpago1	float,
metpago2	tinyint, -- 0Efectivo 3Pago con tarjeta crédito/débito 2Depósito 3Transferencia  4CRÉDITO
montpago2	float,
estado		tinyint -- 1Activa 2Modificada 3Eliminada
);

create table tb_ventas_detalle(
codventa	int not null,
codproducto	int,
cantidad	float,
preVeSDInd	float,
preVeSDTot	float,
descIndiv	float,
descTotal	float,
subTotal	float,
ganancia	float,
uMedidaUsada varchar(30),
foreign key (codventa) references tb_ventas(codventa),
foreign key (codproducto) references tb_productos(codproducto),
primary key (codventa, codproducto)
);

--
create table tb_ingreso_productos(
coding			int primary key auto_increment,
codproducto 	int,
cantidad 		float,
precioCoOld		float,
precioVeOld		float,
precioCoNew		float,
precioVeNew		float,
nombreusu		varchar(50),
fechaingreso	datetime,
foreign key (codproducto) references tb_productos(codproducto)
);
--


create table tb_kardex(
idkardex	int primary key auto_increment,
fecha		datetime,
nota		varchar(200)
);

create table tb_kardex_detalles(
idkardex	int,
codproducto int not null,
registros	float not null,
foreign key (idkardex) references tb_kardex(idkardex),
foreign key(codproducto) references tb_productos(codproducto)
);

create table tb_compras(
idcompra		int primary key auto_increment,
tipComprobante	varchar(50),
serie			varchar(15),
nroSerie		varchar(20),
idDistrib		int,
moneda			varchar(20),
tc				varchar(20),
nota			varchar(200),
metPago			varchar(50),
fechaEmision	date,
fechaVencimiento date,
idusuario		int,
tot				float,
pagado			float,
saldo			float
);

create table tb_compras_detalles(
idcompra		int,
idprod			int,
cantidad		float,
preUni			float,
preSubT			float,
foreign key (idcompra) references tb_compras(idcompra)
);

create table tb_configuraciones(
idconfig		int primary key auto_increment,
atributosprod	varchar(200),
ventasinstock 	tinyint, -- 0NO 1SI Si perminte vender con stock 0
reducirstock	tinyint, -- 0NO 1SI Si disminuirá stock al vender
fechaVauto		tinyint	 -- 0NO 1SI Para poder modificar la fecha de venta cada ves que se realiza
);

-- registros float - kardex_detalles




-- Usuarios de prueba
insert into tb_usuarios values(null,'alex', 'Aa123', 'Alexander Gamarra', 1, 1);
insert into tb_usuarios values(null,'bxb', 'bxb01', 'Byte x Byte', 0, 1);

insert into tb_configuraciones values(null,'marca,color,lote,laboratorio,fvencimiento,promo1,promo2,', 0, 1, 1);

insert into tb_clientes values(	null, 'Doc.trib.no.dom.sin.ruc', '99999999', '.Cliente Varios', null, null, null, 1);
insert into tb_clientes values(	null, 'DNI', '76784966', 'Alexander Gamarra', 'La Encalada', '986865523', 'agamarra@bytexbyte.com.pe', 1);
insert into tb_clientes values(	null, 'RUC', '20604635447', 'BYTE x BYTE E.I.R.L.', 'Calle Octavio Muñoz Najar 213 Int 207', '986865523', 'admin@bytexbyte.com.pe', 1);

INSERT INTO `tb_distribuidores` VALUES (1,'Doc.trib.no.dom.sin.ruc','99999999','.Distribuidor Varios','','','','',1),(2,'RUC','20557079441','Coca Cola','Jr.cajamarca 371 - Rimac - Lima - Lima','','','',1),(3,'RUC','20558078091','Socosani','Av. Pumacahua 717 Cerro Colorado-Arequipa-Arequipa','','','',1),(4,'RUC','20454063423','Kola Real','Av.Salaverry N° S/N Bar. Huaranguillo Sachaca-Arequipa-Arequipa','','','',1),(5,'RUC','10452983147','BACKUS','Av. Nicolas Ayllon 3986','','','',1),(6,'RUC','20456334971','Alicorp','Lt.714 C.P.Semi Rural Pachacutec Cerro Colorado-Arequipa-Arequipa','','','',1),(7,'RUC','20498441662','Gloria','Av. Lima 133 Urb Cercado de Mno Melgar-Arequipa','','','',1),(8,'RUC','20100220700','DIMEXA','Urb. Santa Maria B-12 Paucarpata Arequipa','','','',1),(9,'RUC','20100239559','J.MORAN','Av.San Martin Miraflores Arequipa','','','',1),(10,'RUC','00000000000','Jerico','','','','',1);

insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Caja', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Galon', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Gramo', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Hora', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Kilo', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Litro', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Metro', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Servicio', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Unidad', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA

INSERT INTO `tb_productos` VALUES (10,'7750182155656','coca cola','500ml','coca cola','','','','Litro','2020-04-03','bebidas','.Principal',1,4,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(11,'7755224135894','black','500ml','socosani','','','','Litro','2020-08-13','bebidas','.Principal',1,11,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(12,'7750182220378','fanta','500ml','coca cola','','','','Litro','2020-04-04','bebidas','.Principal',1,13,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(13,'7750182003827','coca cola','300ml','coca cola','','','','Litro','2020-03-24','bebidas','.Principal',1,10,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(14,'7750182001205','san luis','625ml','coca cola','','','','Litro','2020-07-26','bebidas','.Principal',1,7,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(15,'7750182000321','sprite','500ml','coca cola','','','','Litro','2020-04-27','bebidas','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(16,'7750182000796','coca cola','2.25L','coca cola','','','','Litro','2020-05-22','bebidas','.Principal',1,7,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(17,'7750182001892','powerade','473ml','coca cola','','','','Litro','2020-07-09','bebidas','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(18,'7750236552035','inka cola','2L','coca cola','','','','Litro','2020-08-01','bebidas','.Principal',1,5,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(19,'7750182102711','coca cola','2L','coca cola','','','','Litro','2020-07-25','bebidas','.Principal',1,4,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(20,'7750182004879','frugos valle','300ml','coca cola','','','','Litro','2020-07-12','bebidas','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(21,'7750236544092','inka cola','3L','coca cola','','','','Litro','2020-03-07','bebidas','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(22,'7750182544085','coca cola','3L','coca cola','','','','Litro','2020-05-23','bebidas','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(23,'7750182495301','fanta','3L','coca cola','','','','Litro','2020-03-27','bebidas','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(24,'7750182001229','san luis','1L','coca cola','','','','Litro','2020-08-19','bebidas','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(25,'','fanta','192ml','coca cola','','','','Litro','2020-07-28','bebidas','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(26,'','coca cola','192ml','coca cola','','','','Litro','2020-06-27','bebidas','.Principal',1,9,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(27,'','inka cola','192ml','coca cola','','','','Litro','2020-07-30','bebidas','.Principal',1,12,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(28,'7750236173896','inka cola','500ml','coca cola','','','','Litro','2020-04-18','bebidas','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(29,'7750236000246','inka cola','2.25L','coca cola','','','','Litro','2020-03-19','bebidas','.Principal',1,10,5,0,0,0,1,'0',0,0,'0',0,0),(30,'7750182698337','fanta','2.25L','coca cola','','','','Litro','2020-05-19','bebidas','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(31,'','inka cola','1L','coca cola','','','','Litro','2020-08-16','bebidas','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(32,'','coca cola','1L','coca cola','','','','Litro','2020-08-10','bebidas','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(33,'','coca cola','1 1/2ml','coca cola','','','','Litro','2020-08-08','bebidas','.Principal',1,10,5,0,0,0,1,'0',0,0,'0',0,0),(34,'','coca cola','625ml','coca cola','','','','Litro','2020-07-07','bebidas','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(35,'','inka cola','400ml','coca cola','','','','Litro','2020-08-15','bebidas','.Principal',1,11,5,0,0,0,1,'0',0,0,'0',0,0),(36,'','coca cola','400ml','coca cola','','','','Litro','2020-06-08','bebidas','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(37,'','inka cola','625ml','coca cola','','','','Litro','2020-09-08','bebidas','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(38,'','san luis','2.25L','coca cola','','','','Litro','2020-06-01','bebidas','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(39,'','inka cola','1 1/2ml','coca cola','','','','Litro','2020-09-05','bebidas','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(40,'7755224000185','tropical','500ml','socosani','','','','Litro','2020-06-13','bebidas','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(41,'7755224135917','premion','500ml','socosani','','','','Litro','2020-06-21','bebidas','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(42,'7755224000284','socosani con gas','500ml','socosani','','','','Litro','2021-01-17','bebidas','.Principal',1,17,5,0,0,0,1,'0',0,0,'0',0,0),(43,'7755224000291','socosani sin gas','500ml','socosani','','','','Litro',NULL,'bebidas','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(44,'7755224000239','energina','3L','socosani','','','','Litro','2020-06-08','bebidas','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(45,'7755224000086','premion','3L','socosani','','','','Litro','2020-06-07','bebidas','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(46,'7755224000079','black','3L','socosani','','','','Litro','2020-08-18','bebidas','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(47,'7751731002674','fruvi','300ml','kola real','','','','Litro','2020-07-06','bebidas','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(48,'7751731001868','drin t','475ml','kola real','','','','Litro','2020-07-15','bebidas','.Principal',1,13,5,0,0,0,1,'0',0,0,'0',0,0),(49,'7751731002698','fruvi','1L','kola real','','','','Litro','2020-07-18','bebidas','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(50,'7751731001998','KR','500ml','kola real','','','','Litro','2020-05-29','bebidas','.Principal',1,11,5,0,0,0,1,'0',0,0,'0',0,0),(51,'7751731002490','KR','500ml','kola real','','','','Litro','2020-05-30','bebidas','.Principal',1,11,5,0,0,0,1,'0',0,0,'0',0,0),(52,'7751731157350','cielo','625ml','kola real','','','','Litro','2020-08-10','bebidas','.Principal',1,14,5,0,0,0,1,'0',0,0,'0',0,0),(53,'','generade','500ml','kola real','','','','Litro','2020-07-21','bebidas','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(54,'7751731000939','oro','3L','kola real','','','','Litro','2020-06-30','bebidas','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(55,'7751731000939','KR','3L','kola real','','','','Litro','2020-06-06','bebidas','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(56,'7751731000069','cielo','2.25L','kola real','','','','Litro','2020-03-08','bebidas','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(57,'77531704','maltin','330ml','backus','','','','Litro','2020-01-21','bebidas','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(58,'7751271026611','aruba','400ml','gloria','','','','Litro','2020-07-10','bebidas','.Principal',1,14,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(59,'7750243042956','aceite primor ','500ml','alicorp','','','','Litro','2020-07-27','bebidas','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(60,'7750243034227','aceite cocinero','500ml','alicorp','','','','Litro','2020-05-17','bebidas','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(61,'7750243033954','aceite cil','500ml','alicorp','','','','Litro','2020-04-16','bebidas','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(62,'7750243049658','atun primor','170gr','alicorp','','','','Gramo','2020-07-05','alimentos','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(63,'7751271019743','atun gloria','170gr','gloria','','','','Gramo','2023-01-28','alimentos','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(64,'7750243043816','salsa roja don vitorio','200gr','alicorp','','','','Gramo','2020-06-08','alimentos','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(65,'7750243037532','linguini groso don vitorio ','500gr','alicorp','','','','Gramo','2020-07-08','alimentos','.Principal',1,10,5,0,0,0,1,'0',0,0,'0',0,0),(66,'7750243046091','mayonesa','95gr','alicorp','','','','Gramo','2020-08-19','alimentos','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(67,'77544643','mantequilla manty','95gr','alicorp','','','','Gramo','2020-08-14','abarrotes ','.Principal',1,1,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(68,'7750243053921','detergente bolivar ','500gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,10,5,0,0,0,1,'0',0,0,'0',0,0),(69,'070847010500','monster','473ml','coca cola','','','','Litro','2020-02-17','bebidas','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(70,'7750243054164','detergente marsella','500gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(71,'7750243053570','detergente opal','500gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(72,'7750243056113','detergente bolivar bb','150gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(73,'7750243053556','detergente opal','150gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(74,'7750243053907','detergente bolivar','150gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(75,'7750243053563','detergente opal','350gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(76,'7750243058469','jabon bolivar bb','210gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,4,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(77,'7750243057400','jabon bolivar rojo','210gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(78,'7750243054423','jabon bolivar verde','210gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(79,'7750243057424','jabon bolivar azul','210gr','alicorp','','','','Gramo',NULL,'abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(80,'7750885006163','chupetin picolines','10gr','dimexa','','','','Gramo','2020-10-23','abarrotes ','.Principal',1,51,5,0,0,0,1,'0',0,0,'0',0,0),(81,'7750885016469','galleta choco donuts','36gr','dimexa','','','','Gramo','2020-08-10','abarrotes ','.Principal',1,10,5,0,0,0,1,'0',0,0,'0',0,0),(82,'7750885015370','soda line','180gr','dimexa','','','','Gramo','2020-04-06','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(83,'7750168214292','chips ahoy','45gr','j.moran','','','','Gramo','2020-08-08','abarrotes ','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(84,'7750885006293','atun filete fanny','170gr','j.moran','','','','Gramo','2023-05-29','abarrotes ','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(85,'7750885005821','atun trozos fanny','170gr','j.moran','','','','Gramo','2023-04-09','abarrotes ','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(86,'7622210605337','cream crackers','73.5gr','j.moran','','','','Gramo','2020-08-07','abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(87,'7622210605405','cream crackers','295gr','j.moran','','','','Gramo','2020-07-25','abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(88,'7750885015776','avena 3 ositos','150gr','j.moran','','','','Gramo','2020-03-25','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(89,'7750243047654','lavaggi spaghetti','500gr','alicorp','','','','Gramo','2021-09-16','abarrotes ','.Principal',1,4,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(90,'7750243041751','tallarin grueso lavaggi','500gr','alicorp','','','','Gramo','2022-01-29','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(91,'7750243058841','tentacion naranja','45gr','alicorp','','','','Gramo','2020-11-25','abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(92,'7750243034562','soda v','35gr','alicorp','','','','Gramo','2020-09-04','abarrotes ','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(93,'7750243047395','tentacion chocolate','47gr','alicorp','','','','Gramo','2020-07-27','abarrotes ','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(94,'7751271022361','leche golria amarilla','400g','gloria','','','','Gramo','2020-10-07','abarrotes ','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(95,'7751271021579','leche gloria azul ','170g','gloria','','','','Gramo','2020-07-29','abarrotes ','.Principal',1,14,5,0,0,0,1,'0',0,0,'0',0,0),(96,'7751271001403','cheche gloria roja','170g','gloria','','','','Gramo','2020-04-04','abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(97,'7751271022682','leche gloria celeste','170g','gloria','','','','Gramo','2020-04-26','abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(98,'7751271022712','leche gloria celeste','400g','gloria','','','','Gramo','2020-02-28','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(99,'7751271021975','leche gloria azul','400g','gloria','','','','Gramo','2020-07-21','abarrotes ','.Principal',1,16,5,0,0,0,1,'0',0,0,'0',0,0),(100,'7751271021999','leche gloria roja','400gr','gloria','','','','Gramo','2020-02-27','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(101,'7751271023085','leche gloria amarilla','170gr','gloria','','','','Gramo','2020-05-17','abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(102,'7506195129838','always','8','dimexa','','','','Unidad',NULL,'abarrotes ','.Principal',1,14,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(103,'7702027494512','nosotras','10','','','','','Unidad',NULL,'abarrotes ','.Principal',1,9,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(104,'7702031800682','stayfree','12','','','','','Unidad',NULL,'abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(105,'7702425800779','kotex','10','','','','','Unidad',NULL,'abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(106,'7702031500377','carefree','15','','','','','Unidad',NULL,'abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(107,'7702027434020','nosotras diarios','15','','','','','Unidad',NULL,'abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(108,'7756641004091','clorox ropa','292ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(109,'7756641003964','poett frescura ','648ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,10,5,0,0,0,1,'0',0,0,'0',0,0),(110,'7756641003872','clorox','639ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(111,'7756641004107','clorox ropa','580ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(112,'7756641002301','clorox','271ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(113,'7501065922243','head y shoulders rosada','18ml','','','','','Litro',NULL,'.General','.Principal',1,11,5,0,0,0,1,'0',0,0,'0',0,0),(114,'7501007492568','head y shoulders azul','18ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(115,'7500435004305','pantene','18ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(116,'7754487001847','ajinomoto','24g','','','','','Gramo','2023-12-14','abarrotes ','.Principal',1,48,5,0,0,0,1,'0',0,0,'0',0,0),(117,'7501058636218','leche nestle','90gr','','','','','Gramo','2020-06-03','abarrotes ','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(118,'7751028003636','servilletas tess','400 und','','','','','Unidad',NULL,'abarrotes ','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(119,'7702024002826','leche nestle','100gr','','','','','Gramo','2020-10-02','abarrotes ','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(120,'7613035750319','leche nestle','393gr','','','','','Gramo','2020-10-06','abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(121,'7750243040327','uchucuta alacena','85gr','alicorp','','','','Gramo','2020-02-21','abarrotes ','.Principal',1,15,5,0,0,0,1,'0',0,0,'0',0,0),(122,'7750243053266','jaya alacena ','85gr','alicorp','','','','Gramo','2020-03-27','abarrotes ','.Principal',1,9,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(123,'7750243003070','ketchup alacena','100gr','alicorp','','','','Gramo','2020-08-16','abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(124,'7750243049559','mostaza','100gr','alicorp','','','','Gramo','2020-03-13','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(125,'7750885009164','atun fany grated','170gr','j.moran','','','','Gramo','2023-03-15','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(126,'7750670011839','Pulp','315ml','Aje Jerico','','','','Litro','2020-11-30','abarrotes ','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(127,'6937152710133','borradores','forma de animales','Importado ','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(128,'7750840003169','Silicona Liquida','30 ml','Layconsa','','','','Litro',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(129,'7792185893029','Silicona Liquida','30 ml','Mylin','','','','Litro',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(130,'7750840003183','Silicona Liquida','250 ml','Layconsa','','','','Litro',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(131,'7750840003176','Silicona Liquida','100 ml','Layconsa','','','','Litro',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(132,'7754111702508','Silicona Liquida','250 ml','Faber Castell','','','','Litro',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(133,'7754005010511','Silicona Liquida','100ml','Pegafan','','','','Litro',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(134,'7750082021525','Ceramica ultralijera','Masita elastica de colores 16g','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(135,'','Silicona en barra','','varios','','','','Unidad',NULL,'.General','.Principal',1,17,5,0,0,0,1,'0',0,0,'0',0,0),(136,'','Scarcha en bote','Dorada, azul, rosa, naranja','varios','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(137,'7750840000526','Cola escolar','250 g','Layconsa','','','','Gramo',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(138,'6970221726833','Notas de color','Notas Adhesivas','Jia Te','','','','Metro',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(139,'2000417157968','Set de Reglas Flexibles','3 unidades','Ofiscool','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(140,'7754111017480','Plastilina','12 barras de colores','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(141,'7754111685405','Fabercito','10 Plumones pequeños','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(142,'7750840005965','Notitas','Notas Adhesivas de colores','Layconsa','','','','Metro',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(143,'7754111500104','Plastilina','10 Barras pequeñas','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(144,'7750822003569','Plastilinas','Barras pequeñas','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(145,'9556091126538','Lápicero','Azul','Stabilo','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(146,'7750840439562','Puppy','Crayones JUMBO','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(147,'','Crayones Jumbo','12 Crayones de colores','Besko','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(148,'','Plastilinas','12 colores y formas ','Besko','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(149,'4716493732347','Lapiceros','De color azul, negro y rojo','F aber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,38,5,0,0,0,1,'0',0,0,'0',0,0),(150,'2000417157821','Notas Adhesivas','De colores','Ofiscool','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(151,'7754005005388','Cinta Adhesiva Cristalino','','Pegafan','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(152,'7891360474715','Lápices de Grafito','2b ','Faber Castell','','','','Unidad',NULL,'.General','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(153,'7750840404676','Lápiz Teknic-o',' 2H','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(154,'7750082003422','Lápices ','Grafito 2B Hexagonal','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(155,'7750840003800','Lápiz Grafito ','Triangular 2B','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,10,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(156,'3154148517221','Lápices ','2B','Maped','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(157,'7750082026179 ','Lápices','Grafito 2HB triangular','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,14,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(158,'7750082002098','Lápices Bicolor','Forma Triangular','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(159,'7891360466222','lápiz chequeo ','Triangular rojo','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,8,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(160,'7750840005699','Lápiz Chequeo','triangular rojo','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,15,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(161,'7750082003613','Lápices Chequeo','Triangular rojo','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(162,'7750822003507','Lápiz de Chequeo','Triangular rojo','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,12,5,0,0,0,1,'0',0,0,'0',0,0),(163,'7755354005128','Lápices Desing','Triangular 2B','Alpha','','','','Unidad',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(164,'7750082066229','Boligrafos Trimax','de colores por 5 unidades','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(165,'2000417157975','Plumones doble punta','Set x 3 Unidades','Ofiscool','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(166,'7750840003251','Marcadores Jumbo ','de colores por 6 unidades','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(167,'7750822008519','Lápicero Borrador','Azul','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(168,'7750082043176','Lápicero Borrador','Negro','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(169,'','Marcador Permanente ','Doble punta, especial para cd','Leon','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(170,'','Marcador Indeleble','L-23 Negro','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(171,'7750840007747','Borrador Blanco','LA-30','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,30,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(172,'7754111018371','Plumon para pizarra acrílica',' Negro','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(173,'4902505084584','Lápicero Pilot','Azul','Pilot','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(174,'7750082005570','Chinches Dorados ','100 unidades','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(175,'3013017959275','Chinches Indicadores','50 unidades por caja','Ove','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(176,'3013017959381','Clips Metalicos','100 unidades por caja','Ove','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(177,'7750082005563','Alfileres','50 gr','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(178,'6586265016665','Clips','de colores','Memories Precious','','','','Unidad',NULL,'.General','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(179,'','Chinche Mariposa','dos patas dorado','Besko','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(180,'','Plumones ','gruesos de colores ','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(181,'','Plumones','L-47','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(182,'','Crayones Grip','4 unidades','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(183,'','Marcador Indeleble','delgado doble punta','Marker','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(184,'7758574002014','piqueo snax','23gr','','','','','Gramo','2020-04-26','abarrotes ','.Principal',1,12,5,0,0,0,1,'0',0,0,'0',0,0),(185,'7758574001871','papitas lays clasicas','32gr','','','','','Gramo','2020-04-26','abarrotes ','.Principal',1,14,5,0,0,0,1,'0',0,0,'0',0,0),(186,'7758574001185','free papas','16gr','','','','','Gramo','2020-05-03','abarrotes ','.Principal',1,12,5,0,0,0,1,'0',0,0,'0',0,0),(187,'7759826005319','zuck cereales angel','20gr','','','','','Gramo','2020-08-04','abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(188,'7759826006095','copix cereales angel','18gr','','','','','Gramo','2020-09-18','abarrotes ','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(189,'7758574001161','cheese tris','16gr','','','','','Gramo','2020-03-22','abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(190,'7752025004749','papitas lays clasicas','17gr','','','','','Gramo','2020-05-03','abarrotes ','.Principal',1,11,5,0,0,0,1,'0',0,0,'0',0,0),(191,'7702354945220','notribela 10','30ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,12,5,0,0,0,1,'0',0,0,'0',0,0),(192,'7752285033954','rexona','10gr','','','','','Gramo',NULL,'abarrotes ','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(193,'','muñeca fad girl','1 und','','','','','Unidad',NULL,'jugueteria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(194,'850139001344','globos pequeños','100und','','','','','Unidad',NULL,'jugueteria','.Principal',1,5,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(195,'641080652897','mystyle','1und','','','','','Unidad',NULL,'acsesorios','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(196,'','portalapiceros','1und','','','','','Unidad',NULL,'acsesorios','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(197,'','muñecas mermald','1 und','','','','','Unidad',NULL,'jugueteria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(198,'','ligas en frasco','1und','','','','','Unidad',NULL,'acsesorios','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(199,'','ganchos de ropa','24und','','','','','Unidad',NULL,'acsesorios','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(200,'','colets','11 und','','','','','Unidad',NULL,'acsesorios','.Principal',1,11,5,0,0,0,1,'0',0,0,'0',0,0),(201,'6996538225203','makeup sponge','7 und','','','','','Unidad',NULL,'acsesorios','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(202,'131M','cepillo de peinar ','1und','','','','','Unidad',NULL,'acsesorios','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(203,'','globos grandes','100und','','','','','Unidad',NULL,'jugueteria','.Principal',1,100,5,0,0,0,1,'0',0,0,'0',0,0),(204,'','juguete de colets y ganchitos','1und','','','','','Unidad',NULL,'acsesorios','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(205,'','juguete de pony','1und','','','','','Unidad',NULL,'jugueteria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(206,'7750840007709','Minas de Lápiz','Estuche con 24 Minas cada uno','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(207,'7754807001816','Engrapador Mini','de colores','Vinifan','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(208,'6936063930425','Clips Zebra','estuche de varios colores de 50 unidades','Arti Creativo ','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(209,'2000417189570','Engrapador de formas','dos unidades por estuche ','Ofiscool','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(210,'6924998900576','Engrapador de formas','forma de estrella','Kamei','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(211,'7755163290593','Barra Adhesiva','Imagen de distintas figuras','Arti Creativo','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(212,'40267227','Barra Adhesiva','Imagen de los Minions 40 g','Uhu stic','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(213,'792095500038','Goma en barra','21g.','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(214,'6921583194099','Goma en Barra','40g.','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(215,'792095500014','Goma en Barra','8g.','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(216,'40267272','Goma en Barra ','8.2 g','Uhu','','','','Unidad',NULL,'Libreria','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(217,'7750822004528','Goma en Barra','8 g','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(218,'','Reglas Metálicas','de 30 cm','Importado','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(219,'7750082207424','Reglas de plástico','de 30 cm','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(220,'7750082207394','Reglas de plástico','20 cm','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(221,'7750082025073','Reglas de Plástico','15 cm','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(222,'','Regla Metálica','30 cm con tabla de multiplicar','Relampago','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(223,'3154140116118','Tajador','Forma de conejito','Maped','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(224,'','Borrador y Tajador','Dos en uno','Ofiscool','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(225,'','Borrador y Tajador','Dos en uno','Arti Creativo','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(226,'','Escuadras','Pequeñas de plástico','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(227,'','Tajador Cilindrico','Color amarillo y rosado','Vinifan','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(228,'7754005380591','Cinta Adhesiva Cristalino','16 Yds.','Pegafan','','','','Unidad',NULL,'Libreria','.Principal',1,11,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(229,'','Cola Sintética','60 ml','Ove','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(230,'','Borrador','Froma de de I phone ','Importado','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(231,'6926646807794','Corrector Mini','tipo cinta','Importado','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(232,'','Etiquetas Adhesivas','Recuadros rosados','Pega Max','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(233,'7750840009543','Mini Corrector','4 ml.','Layconsa','','','','Litro',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(234,'7754807000680','Corrctor Flex',' 9 ml.','Vinifan','','','','Litro',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(235,'7750082208865','Corrector','9 ml.','Artesco','','','','Litro',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(236,'4716493732330','Corrector','9ml','Faber Castell','','','','Litro',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(237,'','Corrector','7cc.','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(238,'','Cintas Adhesivas Cristalinas','14 Yds.','Toyoba','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(239,'7750082036963','Bote de Tajadores','de colores','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,20,5,0,0,0,1,'0',0,0,'0',0,0),(240,'','Cintas Adhesivas Cristalinas','1 pulgada','Ava','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(241,'7750822009394','Resaltador','Neón anaranjado','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(242,'7750822009387','Resaltador','Neón Verde','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(243,'6941287405192','Lápiz de Minas','0.5 mm / 2B','Baile','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(244,'6937152710133','Borradores','En forma de Animales x 3 unidades ','Importado','','','','Unidad',NULL,'.General','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(245,'7750840005774','Borradores','LA-40 Bicolor','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,13,5,0,0,0,1,'0',0,0,'0',0,0),(246,'6939540522137','Acuarelas','12 colores','Memoris Precious','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(247,'9555684605573','Borrador ','Negro','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(248,'9555684605603','Borrador ','Blanco','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(249,'7750822007086','Tijera Rebote','De color','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(250,'7750082039247','Tijera Super Flex','17 cm','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(251,'7750840001394','Lápices de Color','12 lápices de color por caja','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(252,'','Tajador de Metal','','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(253,'7750840008393','Mini Engrapador','Color Rosado','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(254,'7750840010846','Mini Engrapador',' Color Morado','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(255,'7754111017565','Grapas','1000 grapas por caja','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(256,'7750082416109','Grapas ','1000 Unidades por caja','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(257,'A10.05.04A','Cutter','Color verde y Amarillo','Leon','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(258,'','Cutter','Azul','Lucky Star','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(259,'','Tijera','TJ05-K002.A color Negro','Leon','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(260,'2000417157913','Borrador con Tajador','x 2 Unidades','Ofiscool','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(261,'7754111018319','Plumon para pizarra acrílica','Rojo','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(262,'7754111018333','Plumon para pizarra acrílica','Azul','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(263,'','Plumon para pizarra acrílica','Azul L-23','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(264,'','Plumon para pizarra acrílica','Rojo L-23','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(265,'','Plumon para pizarra acrílica','Negro L-23','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(266,'4902505084560','Lápicero Pilot','Negro','Pilot','','','','Unidad',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(267,'4902505084577','Lápicero Pilot','Rojo','Pilot','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(268,'7754111557511','Lápicero Trilux ','Azul 035','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(269,'7754111557214','Lápicero Trilux ','Rojo 035','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(270,'7754111557993','Lápicero Trilux ','Negro 035','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,8,5,0,0,0,1,'0',0,0,'0',0,0),(271,'7754111532020','Lápicero Trilux ','Rojo 032','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(272,'7754111532037','Lápicero Trilux ','Negro 032','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(273,'7754111532013','Lápicero Trilux ','Azul 032','Faber Castell','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(274,'9556091126545','Lápicero ','Negro','Stabilo','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(275,'9556091126521','Lápicero ','Rojo','Stabilo','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(276,'6941287402092','Lápiz de Minas','Retractable','Baile','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(277,'6941287400135','Lápiz de Minas','Color verde','Baile','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(278,'7750822008496','Lápicero Borrador','Color Rojo','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(279,'','Lápiz de Minas',' Grip 0.5','Layconsa','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(280,'7750822778399','Lápicero  Borrador','Negro','Stanford','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(281,'839313000830','gel masajeador refrescante corps','500gr','','','','','Gramo',NULL,'maquillaje','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(282,'8101750200619','crema para el cuerpo sensaciones','300ml','','','','','Litro',NULL,'maquillaje','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(283,'839313008522','hidratante corporal strax','240ml','','','','','Litro',NULL,'maquillaje','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(284,'839313008638','crema para manos hands','100gr','','','','','Gramo',NULL,'maquillaje','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(285,'810175028057','gel figador h men','120gr','','','','','Gramo',NULL,'maquillaje','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(286,'810175029702','lapiz labial mate mattify','7.0ml','','','','','Kilo','2021-03-03','maquillaje','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(287,'810175029108','mascara de pestañas max','6gr','','','','','Gramo',NULL,'maquillaje','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(288,'A810175022673A','prowhite','90gr','','','','','Gramo',NULL,'maquillaje','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(289,'819086020235',' perfume dazzle','60ml','','','','','Litro',NULL,'maquillaje','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(290,'810175022956','perfume ella dream of me','100ml','','','','','Litro',NULL,'maquillaje','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(291,'819086021652','perfume for her','100ml','','','','','Kilo',NULL,'maquillaje','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(292,'839313006993','aceite en crema avellana','140gr','','','','','Gramo',NULL,'maquillaje','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(293,'810175021904','perfume grace midnight','100ml','','','','','Litro',NULL,'maquillaje','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(294,'7751271025676','yogurt yofresh','1kg','gloria','','','','Kilo','2020-04-02','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(295,'7751271025744','jugo de naranja y piña','1L','gloria','','','','Litro','2020-07-20','abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(296,'7751271019569','chocolatada ','1L','gloria','','','','Litro','2020-07-21','abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(297,'7751271026956','jugo de mango','250ml','gloria','','','','Litro','2020-03-13','abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(298,'7751271017718','jugo de durazno','1L','gloria','','','','Litro','2020-04-02','abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(299,'7751271016681','chocolata','180ml','gloria','','','','Litro','2020-06-12','abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(300,'7751271012355','yogurt de durazno','120gr','gloria','','','','Gramo','2020-04-05','abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(301,'7751271012348','yogurt fresa','120gr','gloria','','','','Gramo','2020-03-24','abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(302,'7750670011839','pulp durazno ','315ml','','','','','Litro','2020-11-30','abarrotes ','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(303,'75032715','corona extra','355ml','backus','','','','Litro','2020-05-15','bebidas','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(304,'7751738002882','wild tropical orange','355ml','backus','','','','Litro',NULL,'bebidas','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(305,'8712000900663','heineken ','330ml','backus','','','','Litro',NULL,'bebidas','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(306,'7751738002868','hit piña colada','355ml','backus','','','','Kilo',NULL,'bebidas','.Principal',1,1,5.5,0,0,0,1,'0',0,0,'0',0,0),(307,'7751271026178','yogurt yofresh durazno','330gr','gloria','','','','Gramo','2020-03-21','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(308,'7751271026161','yogurt vainilla yofresh','330gr','gloria','','','','Gramo','2020-04-30','abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(309,'7751271026154','yogurt fresa yofresh','330gr','gloria','','','','Gramo','2020-04-11','abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(310,'7751271026758','chocolatada shake ','330ml','gloria','','','','Litro','2020-05-15','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(311,'7751028000604','papel ideal','6und','papelera','','','','Unidad',NULL,'abarrotes ','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(312,'7751028000680','papel talla ideal','2und','papelera','','','','Unidad',NULL,'abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(313,'7751028000574','papel ideal','1und','papelera','','','','Unidad',NULL,'abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(314,'7759185007238','papel ideal doble hoja plus','4und','papelera','','','','Unidad',NULL,'abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(315,'7702425679207','duramax paño ','1und','papelera','','','','Unidad',NULL,'abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(316,'7751493000376','papel suave','2und','papelera','','','','Unidad',NULL,'abarrotes ','.Principal',1,13,5,0,0,0,1,'0',0,0,'0',0,0),(317,'7759307005456','sal de andrews','5gr','','','','','Gramo',NULL,'.General','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(318,'7759307000017','andrews','5gr','','','','','Gramo',NULL,'abarrotes ','.Principal',1,21,5,0,0,0,1,'0',0,0,'0',0,0),(319,'7750082009134','Cerámica en frío','Masa Elástica color Blanco 250 gr.','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(320,'7750082024809','Cerámica en frío ','Masa Elástica color verde 250gr.','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(321,'7750082024816','Ceámica en Frío ','Masa Elástica color Rojo 250 gr.','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(322,'7750082024823','Cerámica en Frío','Masa Elástica color Azul 250 gr.','Artesco','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(323,'','Tarjeta ','De \"Feliz Cumpleaños\"','','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(324,'7750867000059','Tarjeta','De \"Feliz día de la Madre\"','Cabana Expressions','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(325,'7750867000059','Tarjeta','De \"Baby Shower\"','Cabana Expressions','','','','Unidad',NULL,'Libreria','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(326,'7750867000141','Tarjeta','De \"Baby Shower\"','Flora','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(327,'','Tarjeta','De \"Feliz Aniversario\"','','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(328,'7750822010093','Cuaderno A4',' 80 hojas, Cuadriculado con Stikers','Conti','','','','Unidad',NULL,'Libreria','.Principal',1,2,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(329,'7755354002509','Cuaderno A4','80 Hojas 2x2 Kids con Stikers','Apha','','','','Unidad',NULL,'.General','.Principal',1,3,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(330,'7750049004998','Cuaderno A4','76 Hojas, Cuadriculado','Surco','','','','Unidad',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(331,'7750049011156','Cuaderno Pequeño','40 Hojas, Cuadriculado 19.8x14.5cm','Surco','','','','Unidad',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(332,'7755354014168','Cuaderno Pequeño','80 Hojas, Cuadriculado 19.8x14.5cm','Apolo','','','','Unidad',NULL,'Libreria','.Principal',1,5,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(333,'','Cuaderno Pequeño','80 Hojas, Cuadriculado 19.8x14.5cm','Justus','','','','Unidad',NULL,'Libreria','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(334,'8718696760376','Foco Led','9 w','Philips','','','','Unidad',NULL,'.General','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(335,'','Carátulas ','Para Distintos Cursos','','','','','Unidad',NULL,'Libreria','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(336,'','Cintas Adhesivas','Con Diseño','','','','','Unidad',NULL,'Libreria','.Principal',1,35,5,0,0,0,1,'0',0,0,'0',0,0),(337,'','Cintas de Agua','De colores Gruesa','Cinteala','','','','Unidad',NULL,'Libreria','.Principal',1,11,5,0,0,0,1,'0',0,0,'0',0,0),(338,'','Cinta de Agua','De colores Delgada','Cintela','','','','Unidad',NULL,'Libreria','.Principal',1,12,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(339,'','Detalle de Osito','en su Canasta con glo de estrella','Propia','','','','Unidad',NULL,'acsesorios','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(340,'7758218196017','Primado','Vino Espumante 750 ml','Santiago Queirolo','','','','Unidad',NULL,'bebidas','.Principal',1,4,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(341,'7759291000680','Espumante','750 ml','Señorio de Najar','','','','Unidad',NULL,'bebidas','.Principal',1,1,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(342,'7759291000567','Borgoña','Vino  750 ml','Señorío de Najar','','','','Unidad',NULL,'bebidas','.Principal',1,1,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(343,'7596530001981','Calazan Ron Añejo','Ron Venezolano','Calazan','','','','Unidad',NULL,'bebidas','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(344,'7750533000147','Borgoña','Vino Tinto','Tabernero ','','','','Unidad',NULL,'bebidas','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(345,'7622300051822','polvo  de hornear royal','20gr','','','','','Gramo','2019-09-03','abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(346,'7758651000056','vinagre blanco','125ml','','','','','Litro','2022-06-28','abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(347,'7758651000063','vinagre tinto de vino','125ml','','','','','Litro','2022-06-08','abarrotes ','.Principal',1,4,5,0,0,0,1,'0',0,0,'0',0,0),(348,'7753121750943','sillao','85ml','','','','','Litro',NULL,'abarrotes ','.Principal',1,12,5,0,0,0,1,'0',0,0,'0',0,0),(349,'7753992000291','manjar blanco','200gr','','','','','Gramo','2020-02-21','abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(350,'7702133008535','gelatina royal ','160gr','','','','','Gramo',NULL,'abarrotes ','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(351,'7750243052115','gelatina negrita ','150gr','alicorp','','','','Gramo','2021-06-12','abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(352,'7750243048026','flan negrita','95gr','alicorp','','','','Gramo','2020-06-06','abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0),(353,'7750243051415','pudin negrita','110gr','alicorp','','','','Gramo','2021-06-22','abarrotes ','.Principal',1,2,5,0,0,0,1,'0',0,0,'0',0,0),(354,'7752230001687','gelatina univarsal','150gr','','','','','Gramo','2022-01-04','abarrotes ','.Principal',1,1,5,0,0,0,1,'0',0,0,'0',0,0),(355,'7613036415248','nescafe tradicion','9gr','','','','','Gramo','2020-10-05','abarrotes ','.Principal',1,30,5,0,0,0,1,'0',0,0,'0',0,0),(356,'7613035140301','ecco cafe','10gr','','','','','Gramo','2020-05-25','abarrotes ','.Principal',1,11,5,0,0,0,1,'0',0,0,'0',0,0),(357,'7613035180970','cafe kirma ','9gr','','','','','Gramo','2021-12-31','abarrotes ','.Principal',1,5,5,0,0,0,1,'0',0,0,'0',0,0),(358,'7750463003188','altomayo cfe','17gr','','','','','Gramo','2021-07-04','abarrotes ','.Principal',1,7,5,0,0,0,1,'0',0,0,'0',0,0),(359,'7613036414807','nescafe tradicion','17gr','','','','','Gramo','2020-05-23','abarrotes ','.Principal',1,9,5,0,0,0,1,'0',0,0,'0',0,0),(360,'7613034883209','milo','18gr','','','','','Gramo','2019-10-19','abarrotes ','.Principal',1,12,5,0,0,0,1,'0',0,0,'0',0,0),(361,'7613036167420','nescafe fina','8gr','','','','','Gramo','2020-12-10','abarrotes ','.Principal',1,16,5,0,0,0,1,'0',0,0,'0',0,0),(362,'7759185005289','babysec premion','1und','','','','','Gramo',NULL,'abarrotes ','.Principal',1,6,5,0,0,0,1,'0',0,0,'0',0,0),(363,'7702005601147','cigarro marlboro','10und','','','','','Unidad',NULL,'abarrotes ','.Principal',1,36,5,0,0,0,1,'0.0',0,0,'0.0',0,0),(364,'7751201000667','cigarros lucky strike','11und','','','','','Unidad',NULL,'abarrotes ','.Principal',1,25,5,0,0,0,1,'0',0,0,'0',0,0),(365,'7108005600973','cigarro l y m','10und','','','','','Unidad',NULL,'abarrotes ','.Principal',1,3,5,0,0,0,1,'0',0,0,'0',0,0);

use db_inventario;
select * from tb_usuarios;	
select * from tb_clientes;
select * from tb_distribuidores;
select * from tb_productos; 
select * from tb_configuraciones;

select * from tb_kardex;
select * from tb_kardex_detalles;
select * from tb_compras;
select * from tb_compras_detalles;
select * from tb_ventas;
select * from tb_ventas_detalle;

select * from tb_productos; 

select * from tb_ingreso_productos;


select * from tb_productos where cantidad < 50 and producto != '&'  order by producto;
select * from tb_productos where cantidad < 8 and estado = 1 and categoria = '.General' and marca =  'FILA'  order by producto;

update tb_productos set cantidad = 50 where codproducto=10;

-- delete from tb_ventas where codventa = 2;
-- delete from tb_ventas where codventa = 52;
-- drop table tb_productos;
-- drop table tb_ventas;
-- drop table tb_ventas_detalle;
-- drop table tb_ingreso_productos;

-- PRUEBAS ------------------------------------------------------------------------

-- ALTER TABLE tb_ventas_detalle MODIFY cantidad float;   SE ALTERA SI ES NECESARIO

show processlist;
show status like 'Threads%';

SET SQL_SAFE_UPDATES = 0;




select DISTINCT marca, codbarra from tb_productos where estado = 1 order by producto ;
select DISTINCT codbarra from tb_productos where estado = 1;

select * from tb_productos where estado = 1 and producto like '%aceite%' order by producto;

select codproducto from tb_productos order by codproducto desc limit 1 ;

select producto like '%" + prod + "%' or detalles like '%" + prod + "%' or marca like '%" + prod + "%' or color like '%" + prod + "%' or categoria like '%" + prod + "%' order by producto;

select * from  db_inventario.tb_ventas where fecha between '2019-01-01 00:00:00' and '2020-09-07 23:59:59';

update tb_ventas SET fecha='2019-10-14' WHERE codventa=11;

update tb_ventas set usuario='admin' where usuario='Alexander Gamarra';

select vd.codventa,pr.producto,vd.cantidad,vd.prevenOri,vd.totvenOri,vd.prevenFin,vd.totvenFin from db_inventario.tb_ventas_detalle vd 
inner join tb_productos pr on vd.codproducto=pr.codproducto where vd.codventa = 6;

select * from tb_ventas where estado = 1 order by fecha desc;

select * from tb_usuarios where usuario = BINARY '' or '' = '' and pass = BINARY '' or '' = '';
 
select sum(precioVe) from tb_productos where cantidad > 0 order by producto;

select * from tb_clientes;

select producto from tb_productos where codproducto like 'pol01%' limit 1;

select DATE_FORMAT(v.fecha,'%d-%m-%Y %h:%i %p') as fecha from tb_ventas v ;
select * from tb_ventas;

alter table tb_kardex_detalles
change registros  registros float ;
  
update tb_productos 
set cantidad = 10;

SELECT * FROM tb_productos WHERE fechaVenc >= CURDATE() ORDER BY fechaVenc LIMIT 3;
  


SELECT * FROM tb_productos 
WHERE fechaVenc >= CURDATE()
ORDER BY fechaVenc 
LIMIT 2;

-- COMPROBANTE
select * from tb_productos; 
select v.fecha, cl.nombre cli, u.nombre usu, v.nota, vd.cantidad, pr.producto, pr.marca, pr.color, pr.detalles, pr.precioVe, vd.preVeSDInd, vd.descTotal, 
vd.subTotal, v.totventa, v.descuento
from tb_ventas v
inner join tb_ventas_detalle vd on v.codventa = vd.codventa
inner join tb_productos pr on vd.codproducto = pr.codproducto  
inner join tb_clientes cl on v.idcliente = cl.idcliente
inner join tb_usuarios u on v.idusuario = u.idusuario
where vd.codventa = 4;


SELECT * 
FROM tb_productos 
ORDER BY ABS( DATEDIFF( fechaVenc, NOW() ) ) 
LIMIT 1;

select idkardex from tb_kardex order by idkardex desc limit 1;

select * from tb_kardex_detalles;

delete  from tb_productos where codproducto = 11;

select idkardex from tb_kardex order by idkardex desc limit 1;

select k.idkardex, k.fecha, k.nota, kd.codproducto, kd.registros
from tb_kardex k 
inner join tb_kardex_detalles kd
where k.idkardex = 7 and kd.idkardex = 7;


INSERT INTO tb_productos (producto, detalles, marca, color, lote, laboratorio, unimedida, fechaVenc, categoria, almacen, iddistrib, cantidad, cantmin, precioCo, precioVe, ptjganancia, estado, promo1, cantp1, prep1, promo2, cantp2, prep2)
SELECT producto, detalles, marca, color, lote, laboratorio, unimedida, fechaVenc, categoria, almacen, iddistrib, cantidad, cantmin, precioCo, precioVe, ptjganancia, estado, promo1, cantp1, prep1, promo2, cantp2, prep2
FROM tb_productos
WHERE codproducto = 10;

select * from tb_productos;
select * from tb_ventas;
select * from tb_ventas_detalle;

delete from tb_ventas_detalle where codventa = 22;


select * from tb_productos where codbarra like 'abc123' and length(codbarra)>2 and estado = 1 and codproducto != 17;


select ip.cantidad, pr.producto, pr.detalles,ip.precioCoNew,ip.precioVeNew,ip.nombreusu, ip.fechaingreso
from db_inventario.tb_ingreso_productos ip
inner join tb_productos pr
on ip.codproducto = pr.codproducto
where ip.fechaingreso between '2019-01-01 00:00:00' and '2021-09-07 23:59:59'


