create database db_inventario;

use db_inventario;

create table tb_usuarios(
usuario 	varchar(20) not null primary key,
pass		varchar(20),
nombre		varchar(50),
tipo 	 	int
);

create table tb_productos(
codproducto	varchar(100) not null primary key,
producto	varchar(40),
detalles	varchar(100),
unimedida	varchar(10),
cantidad	float,
precioCo	float,
precioVe	float
);

create table tb_ventas(
codventa	int primary key auto_increment,
cliente		varchar(50),
fecha		datetime,
usuario		varchar(20),
totcompra 	float,
totventa	float,
ganancia	float
);

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

-- Usuarios de prueba
insert into tb_usuarios values('alex', 'Aa123', 'Alexander Gamarra', 1);
insert into tb_usuarios values(	'admin', 'admin', 'ADMINISTRADOR', 0);

use db_inventario;
select * from tb_usuarios;
select * from tb_productos; 
select * from tb_ventas;
select * from tb_ventas_detalle;

-- delete from tb_ventas where codventa = 2;
-- delete from tb_ventas where codventa = 52;
-- drop table tb_productos;
-- drop table tb_ventas;
-- drop table tb_ventas_detalle;

-- PRUEBAS ------------------------------------------------------------------------

-- ALTER TABLE tb_ventas_detalle MODIFY cantidad float;   SE ALTERA SI ES NECESARIO

select * from  db_inventario.tb_ventas where fecha between '2019-01-01 00:00:00' and '2019-09-07 23:59:59';


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


select * from tb_usuarios where usuario = BINARY '' or '' = '' and pass = BINARY '' or '' = ''
 



