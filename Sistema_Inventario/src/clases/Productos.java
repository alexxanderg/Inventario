package clases;

import java.util.Date;

public class Productos {
	String codigo;
	String producto;
	String detalles;
	String unimedida;
	float cantidad;
	float cantventa;
	float precioCo;
	float precioVe;
	String marca;
	String color;

	String laboratorio;
	Date fechavenci;
	float nrolote;
	
	String categoria;

	public Productos(String codigo, String producto, String detalles,String categoria,String laboratorio, Date fechavenci, float nrolote , String unimedida, float cantidad, float cantventa,
			float precioCo, float precioVe, String marca, String color) {
		this.codigo = codigo;
		this.producto = producto;
		this.detalles = detalles;
		this.unimedida = unimedida;
		this.cantidad = cantidad;
		this.cantventa = cantventa;
		this.precioCo = precioCo;
		this.precioVe = precioVe;
		this.marca = marca;
		this.color = color;
		this.laboratorio = laboratorio;
		this.fechavenci=fechavenci;
		this.nrolote = nrolote;
		this.categoria = categoria;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Date getFechavenci() {
		return fechavenci;
	}

	public void setFechavenci(Date fechavenci) {
		this.fechavenci = fechavenci;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getNrolote() {
		return nrolote;
	}

	public void setNrolote(float nrolote) {
		this.nrolote = nrolote;
	}

	public float getCantventa() {
		return cantventa;
	}

	public void setCantventa(float cantventa) {
		this.cantventa = cantventa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public String getUnimedida() {
		return unimedida;
	}

	public void setUnimedida(String unimedida) {
		this.unimedida = unimedida;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioCo() {
		return precioCo;
	}

	public void setPrecioCo(float precioCo) {
		this.precioCo = precioCo;
	}

	public float getPrecioVe() {
		return precioVe;
	}

	public void setPrecioVe(float precioVe) {
		this.precioVe = precioVe;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
