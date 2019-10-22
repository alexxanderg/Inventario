package clases;


public class RegistroVentas {
	String cliente;
	String usuario;
	float totCompra;
	float totVentaProd;
	String codProducto;
	float ganancia;
	float cantidad;
	
	public RegistroVentas(String cliente, String usuario, float totCompra, float totVentaProd, String codProducto, float ganancia, float cantidad){
		this.cliente = cliente;
		this.usuario = usuario;
		this.totCompra = totCompra;
		this.totVentaProd = totVentaProd;
		this.codProducto = codProducto;
		this.ganancia = ganancia;
		this.cantidad = cantidad;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public float getTotCompra() {
		return totCompra;
	}

	public void setTotCompra(float totCompra) {
		this.totCompra = totCompra;
	}

	public float getTotVentaProd() {
		return totVentaProd;
	}

	public void setTotVentaProd(float totVentaProd) {
		this.totVentaProd = totVentaProd;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public float getGanancia() {
		return ganancia;
	}

	public void setGanancia(float ganancia) {
		this.ganancia = ganancia;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}	
}
