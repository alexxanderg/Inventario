
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


