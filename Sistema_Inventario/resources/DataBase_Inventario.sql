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

create table tb_kardex(
idkardex	int primary key auto_increment,
fecha		datetime,
nota		varchar(200)
);

create table tb_kardex_detalles(
idkardex	int,
codproducto int not null,
registros	int not null,
foreign key (idkardex) references tb_kardex(idkardex),
foreign key(codproducto) references tb_productos(codproducto)
);

create table tb_configuraciones(
idconfig		int primary key auto_increment,
atributosprod	varchar(200),
ventasinstock 	tinyint, -- 0NO 1SI Si perminte vender con stock 0
reducirstock	tinyint, -- 0NO 1SI Si disminuirá stock al vender
fechaVauto		tinyint	 -- 0NO 1SI Para poder modificar la fecha de venta cada ves que se realiza
);
  
-- Usuarios de prueba
insert into tb_usuarios values(null,'alex', 'Aa123', 'Alexander Gamarra', 1, 1);
insert into tb_usuarios values(null,'bxb', 'bxb01', 'Byte x Byte', 0, 1);
insert into tb_usuarios values(null,'admin', 'admin', 'ADMINISTRADOR', 0, 1);

insert into tb_configuraciones values(null,'marca,color,lote,laboratorio,fvencimiento,promo1,promo2,', 0, 1, 1);

insert into tb_clientes values(	null, 'DNI', '76784966', 'Alexander Gamarra', 'La Encalada', '986865523', 'agamarra@bytexbyte.com.pe', 1);
insert into tb_clientes values(	null, 'RUC', '20604635447', 'BYTE x BYTE E.I.R.L.', 'Calle Octavio Muñoz Najar 213 Int 207', '986865523', 'admin@bytexbyte.com.pe', 1);

insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Caja', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Galon', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Gramo', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Hora', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Kilo', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Litro', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Metro', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Servicio', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, null, '&', null, null, null, null, null, 'Unidad', null, '.General', '.Principal', 0, 0, 0, 0, 0, 0, 0, null, 0, 0, null, 0, 0); -- AGREGA UNIDADES DE MEDIDA
insert into tb_productos values(null, '123456789', 'USB 64GB', '3.0', 'Sony', 'Negro', null, null, 'Unidad', null, '.General', '.Principal', 5, 2, 40.5, 55.9, 10, 0, null, 0, 0, null, 0, 0);

use db_inventario;
select * from tb_usuarios;	
select * from tb_clientes;
select * from tb_distribuidores;
select * from tb_productos; 
select * from tb_ventas;
select * from tb_ventas_detalle;
select * from tb_ingreso_productos;
select * from tb_clientes;
select * from tb_kardex_detalles;
select * from tb_configuraciones;

select codproducto from tb_productos order by codproducto desc limit 1 ;
  
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

select vd.codventa, vd.cantidad, pr.producto, pr.detalles, pr.marca, pr.color, pr.lote, pr.laboratorio, vd.descIndiv, vd.descTotal, vd.subTotal, v.fecha, c.nombre, v.totventa, u.usuario, v.metpago1, v.nota
from tb_ventas v 
inner join tb_ventas_detalle vd 
on v.codventa=vd.codventa
inner join tb_productos pr
on pr.codproducto=vd.codproducto
inner join tb_clientes c
on v.idcliente = c.idcliente
inner join tb_usuarios u
on v.idusuario = u.idusuario
where v.fecha between'2019-10-14' and '2021-10-14'
order by v.codventa;


select * from  db_inventario.tb_ventas where fecha between '2019-01-01 00:00:00' and '2020-09-07 23:59:59';

update tb_ventas SET fecha='2019-10-14' WHERE codventa=11;

update tb_ventas set usuario='admin' where usuario='Alexander Gamarra';

SET SQL_SAFE_UPDATES = 0;

select vd.codventa,pr.producto,vd.cantidad,vd.prevenOri,vd.totvenOri,vd.prevenFin,vd.totvenFin from db_inventario.tb_ventas_detalle vd 
inner join tb_productos pr on vd.codproducto=pr.codproducto where vd.codventa = 6;

select v.codventa, v.cliente, v.fecha, v.usuario, v.totcompra, v.totventa, v.ganancia,
vd.codproducto, vd.cantidad, vd.prevenOri, vd.totvenOri, vd.prevenFin, vd.totvenFin 
from  db_inventario.tb_ventas v
inner join tb_ventas_detalle vd
on v.codventa = vd.codventa
where fecha between '2019-01-01 00:00:00' and '2019-09-07 23:59:59'
group by v.codventa;


select vd.codventa, vd.cantidad, pr.producto, pr.detalles, vd.prevenFin,  vd.totvenFin, v.fecha, v.cliente, v.totventa, v.usuario
from tb_ventas v 
Inner join tb_ventas_detalle vd 
On v.codventa=vd.codventa
Inner join tb_productos pr
On pr.codproducto=vd.codproducto
where v.fecha between '2018-01-01 00:00:00' and '2019-10-07 23:59:59'
and v.usuario = 'alex';

select * from tb_ventas where estado = 1 order by fecha desc;


select v.codventa, c.nombre ncliente, u.nombre nusuario, v.nota, DATE_FORMAT(v.fecha,'%d-%m-%Y %h:%m') as fecha, v.descuento, v.saldo, v.totventa
from tb_ventas v
inner join tb_clientes c
on c.idcliente = v.idcliente
inner join tb_usuarios u
on u.idusuario = v.idusuario
where v.estado = 1 
and u.idusuario = 1
and v.fecha between '2018-01-01 00:00:00' and '2019-10-07 23:59:59'
order by v.fecha desc;

select * from tb_usuarios where usuario = BINARY '' or '' = '' and pass = BINARY '' or '' = '';
 
select sum(precioVe) from tb_productos where cantidad > 0 order by producto;

select vd.codventa, vd.cantidad, pr.producto, pr.detalles, vd.prevenFin,  vd.totvenFin, v.fecha, v.cliente, v.totventa, v.usuario, v.metpago, v.nota
from tb_ventas v 
Inner join tb_ventas_detalle vd 
On v.codventa=vd.codventa
Inner join tb_productos pr
On pr.codproducto=vd.codproducto
where v.fecha between '2019-01-01' and '2020-12-12'
and v.metpago = 3
order by v.codventa;

select vd.codventa, vd.cantidad, pr.producto, pr.detalles, vd.prevenFin,  vd.totvenFin, v.fecha, v.cliente, v.totventa, v.usuario, v.metpago, v.nota
from tb_ventas v 
Inner join tb_ventas_detalle vd 
On v.codventa=vd.codventa
Inner join tb_productos pr
On pr.codproducto=vd.codproducto
where v.fecha between '2020-01-01' and '2020-12-12'
and v.usuario = 'Alexander Gamarra';

select * from tb_clientes;

select vd.codventa, vd.cantidad, pr.producto, vd.prevenFin, v.fecha, v.cliente, v.totventa
from tb_ventas_detalle vd inner join tb_productos pr 
inner join tb_ventas v on vd.codproducto = pr.codproducto 
where vd.codventa = 50 and vd.codventa = v.codventa;

select producto from tb_productos where codproducto like 'pol01%' limit 1;

select p.producto, p.detalles, p.marca, p.color, p.cantidad, kd.registros from tb_kardex_detalles kd
inner join tb_productos p on p.codproducto = kd.codproducto
where kd.idkardex = 4;


alter table tb_ventas
  add saldo		float after descuento;