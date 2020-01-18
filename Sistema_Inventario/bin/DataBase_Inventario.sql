create database db_inventario;

use db_inventario;

create table tb_usuarios(
usuario 	varchar(20) not null primary key,
pass		varchar(20),
nombre		varchar(50),
tipo 	 	int
);

create table tb_registro_ingreso(
id			int primary key auto_increment,
fecha_registro	datetime,
usuario		varchar(20),
foreign key (usuario) references tb_usuarios(usuario)
);

create table tb_productos(
codproducto	varchar(100) not null primary key,
producto	varchar(40),
detalles	varchar(100),
unimedida	varchar(10),
cantidad	float,
precioCo	float,
precioVe	float,

promo1		varchar(20),
cantp1		float,
prep1		float,
promo2		varchar(20),
cantp2		float,
prep2		float,

marca		varchar(40),
color		varchar(40)

);

alter table tb_productos
  add prep2		float;

create table tb_ventas(
codventa	int primary key auto_increment,
cliente		varchar(50),
fecha		datetime,
usuario		varchar(20),
totcompra 	float,
totventa	float,
ganancia	float,
idcliente	int,
nota		varchar(200),
metpago		tinyint -- 0Efectivo 1Depósito BCP 2Depósito BBVA 3Depósito INTERBANK 4Transferencia BCP 5Transferencia BBVA 6Transferencia INTERBANK 7Pago con tarjeta BCP 8Pago con tarjeta BBVA 9Pago con tarjeta INTERBANK 10CRÉDITO
);

alter table tb_ventas
  add metpago		tinyint;

create table tb_ventas_detalle(
codventa	int not null,
codproducto	varchar(40) not null,
cantidad	float,
prevenOri   float,
totvenOri	float,
prevenFin	float,
totvenFin	float,
foreign key (codventa) references tb_ventas(codventa),
foreign key (codproducto) references tb_productos(codproducto),
primary key (codventa, codproducto)
);

create table tb_ingreso_productos(
	 coding int primary key auto_increment,
	 codproducto varchar(100) not null,
	 cantidad float,
	 precioCo float,
	 precioVe float,
	 fechaingreso datetime,
	 nombreusu varchar(50),
     foreign key(codproducto) references tb_productos(codproducto)
);

create table tb_clientes(
id			int primary key auto_increment,
tipodoc		varchar(10),
nrodoc		varchar(11),
nombre		varchar(150),
direccion	varchar(150),
telefono	varchar(15),
correo		varchar(50)
);


-- Usuarios de prueba
insert into tb_usuarios values('alex', 'Aa123', 'Alexander Gamarra', 1);
insert into tb_usuarios values(	'admin', 'admin', 'ADMINISTRADOR', 0);

insert into tb_clientes values(	null, 'DNI', '76784966', 'Alexander Gamarra', 'La Encalada', '986865523', 'agamarra@bytexbyte.com.pe');
insert into tb_clientes values(	null, 'RUC', '20604635447', 'BYTE x BYTE E.I.R.L.', 'Calle Octavio Muñoz Najar 213 Int 207', '986865523', 'admin@bytexbyte.com.pe');


use db_inventario;
select * from tb_usuarios;
select * from tb_productos; 
select * from tb_ventas;
select * from tb_ventas_detalle;
select * from tb_ingreso_productos;
select * from tb_clientes;

select * from tb_clientes order by id desc limit 1;

select * from tb_ventas;
select sum(totventa), sum(ganancia) from tb_ventas;

select codventa, cliente, fecha, usuario, totcompra, totventa, ganancia, (sum(totventa)), (sum(ganancia)) from tb_ventas;

-- delete from tb_ventas where codventa = 2;
-- delete from tb_ventas where codventa = 52;
-- drop table tb_productos;
-- drop table tb_ventas;
-- drop table tb_ventas_detalle;
-- drop table tb_ingreso_productos;

-- PRUEBAS ------------------------------------------------------------------------

-- ALTER TABLE tb_ventas_detalle MODIFY cantidad float;   SE ALTERA SI ES NECESARIO

select * from  db_inventario.tb_ventas where fecha between '2019-01-01 00:00:00' and '2019-09-07 23:59:59';

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





