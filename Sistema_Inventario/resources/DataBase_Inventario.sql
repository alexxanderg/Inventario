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
prep2		float,
promo3		varchar(30),
cantp3		float,
prep3		float
);  

-- ALTER TABLE tb_productos ADD promo3 VARCHAR(30);
-- ALTER TABLE tb_productos ADD cantp3 float;
-- ALTER TABLE tb_productos ADD prep3 float;
ALTER TABLE tb_productos ADD cantmax float;

 SET SQL_SAFE_UPDATES = 0;

-- UPDATE tb_productos SET promo3 = 0;
-- UPDATE tb_productos SET cantp3 = 0;
-- UPDATE tb_productos SET prep3 = 0;
UPDATE tb_productos SET cantmax = 0;

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

ALTER TABLE tb_ventas_detalle ADD detventa VARCHAR(200);

SET SQL_SAFE_UPDATES = 0;
UPDATE tb_ventas_detalle SET detventa = '';


create table tb_cotizaciones(
codcoti		int primary key auto_increment,
idcliente	int,
fecha		datetime,
idusuario	int,
totcompra 	float,
totventa	float,
nota		varchar(200),
estado		tinyint -- 1Activa 2Modificada 3Eliminada
);

create table tb_cotizaciones_detalle(
codcoti		int not null,
codproducto	int,
cantidad	float,
preVeSDInd	float,	
preVeSDTot	float,
descIndiv	float,
descTotal	float,
subTotal	float,
ganancia	float,
uMedidaUsada varchar(30),
primary key (codcoti, codproducto)
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
lote			varchar(50),
fechaVenc		date,
vendido         int, -- 0=SIN VENDER 1=VENDIDO
foreign key (idcompra) references tb_compras(idcompra)
);

-- ALTER TABLE tb_compras_detalles ADD fechaVenc date;
-- ALTER TABLE tb_compras_detalles ADD vendido int;

-- SET SQL_SAFE_UPDATES = 0;
-- UPDATE tb_compras_detalles SET fechaVenc = null;
-- UPDATE tb_compras_detalles SET vendido = 0;



create table tb_configuraciones(
idconfig		int primary key auto_increment,
atributosprod	varchar(200),
ventasinstock 	tinyint, -- 0NO 1SI Si perminte vender con stock 0
reducirstock	tinyint, -- 0NO 1SI Si disminuirá stock al vender
fechaVauto		tinyint	 -- 0NO 1SI Para poder modificar la fecha de venta cada ves que se realiza
);


-- Usuarios de prueba
insert into tb_usuarios values(null,'bxb', 'bxb01', 'Byte x Byte', 0, 1);
insert into tb_usuarios values(null,'admin', 'admin', 'ADMINISTRADOR', 0, 1);


insert into tb_configuraciones values(null,'marca,color,promo1,promo2,', 0, 1, 0);

insert into tb_clientes values(	null, 'Doc.trib.no.dom.sin.ruc', '99999999', '.Cliente Varios', null, null, null, 1);
insert into tb_clientes values(	null, 'DNI', '78548965', 'Juan Pérez', 'Las Orquídeas F7', '999888777', 'juanperez@gmail.com', 1);
insert into tb_clientes values(	null, 'DNI', '00452789', 'Mario Gomez', '', '789456123', '', 1);
insert into tb_clientes values(	null, 'DNI', '54668899', 'Marisol Torres', '', '123456789', 'maritorres@gmail.com', 1);

INSERT INTO `tb_distribuidores` VALUES (1,'Doc.trib.no.dom.sin.ruc','99999999','.Distribuidor Varios','','','','',1),(2,'RUC','20557079441','COCA COLA','Jr.cajamarca 371 - Rimac - Lima - Lima','','','',1),(3,'RUC','20558078091','SOCOSANI','Av. Pumacahua 717 Cerro Colorado-Arequipa-Arequipa','','','',1),(4,'RUC','20454063423','KOLA REAL','Av.Salaverry N° S/N Bar. Huaranguillo Sachaca-Arequipa-Arequipa','','','',1),(5,'RUC','10452983147','BACKUS','Av. Nicolas Ayllon 3986','','','',1),(6,'RUC','20456334971','ALICORP','Lt.714 C.P.Semi Rural Pachacutec Cerro Colorado-Arequipa-Arequipa','','','',1),(7,'RUC','20498441662','GLORIA','Av. Lima 133 Urb Cercado de Mno Melgar-Arequipa','','','',1),(8,'RUC','20100220700','DIMEXA','Urb. Santa Maria B-12 Paucarpata Arequipa','','','',1),(9,'RUC','20100239559','J.MORAN','Av.San Martin Miraflores Arequipa','','','',1),(10,'RUC','00000000000','JERICO','','','','',1);

insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Caja', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Galon', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Gramo', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Hora', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Kilo', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Litro', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Metro', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Servicio', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Unidad', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
