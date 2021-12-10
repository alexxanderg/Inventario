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
marca		varchar(200),
color		varchar(200),
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

-- SET SQL_SAFE_UPDATES = 0;
-- UPDATE tb_productos SET promo3 = 0;
-- UPDATE tb_productos SET cantp3 = 0;
-- UPDATE tb_productos SET prep3 = 0;

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

insert into tb_configuraciones values(null,'marca,color,lote,laboratorio,fvencimiento,promo1,promo2,', 1, 1, 0);

insert into tb_clientes values(	null, 'Doc.trib.no.dom.sin.ruc', '99999999', '.Cliente Varios', null, null, null, 1);
insert into tb_clientes values(	null, 'DNI', '76784966', 'Alexander Gamarra', 'La Encalada', '986865523', 'agamarra@bytexbyte.com.pe', 1);
insert into tb_clientes values(	null, 'RUC', '20604635447', 'BYTE x BYTE E.I.R.L.', 'Calle Octavio Muñoz Najar 213 Int 207', '986865523', 'admin@bytexbyte.com.pe', 1);

INSERT INTO `tb_distribuidores` VALUES (1,'Doc.trib.no.dom.sin.ruc','99999999','.Distribuidor Varios','','','','',1),(2,'RUC','20557079441','Coca Cola','Jr.cajamarca 371 - Rimac - Lima - Lima','','','',1),(3,'RUC','20558078091','Socosani','Av. Pumacahua 717 Cerro Colorado-Arequipa-Arequipa','','','',1),(4,'RUC','20454063423','Kola Real','Av.Salaverry N° S/N Bar. Huaranguillo Sachaca-Arequipa-Arequipa','','','',1),(5,'RUC','10452983147','BACKUS','Av. Nicolas Ayllon 3986','','','',1),(6,'RUC','20456334971','Alicorp','Lt.714 C.P.Semi Rural Pachacutec Cerro Colorado-Arequipa-Arequipa','','','',1),(7,'RUC','20498441662','Gloria','Av. Lima 133 Urb Cercado de Mno Melgar-Arequipa','','','',1),(8,'RUC','20100220700','DIMEXA','Urb. Santa Maria B-12 Paucarpata Arequipa','','','',1),(9,'RUC','20100239559','J.MORAN','Av.San Martin Miraflores Arequipa','','','',1),(10,'RUC','00000000000','Jerico','','','','',1);

insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Caja', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Galon', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Gramo', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Hora', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Kilo', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Litro', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Metro', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Servicio', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Unidad', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA



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


