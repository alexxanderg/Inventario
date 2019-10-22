package clases;

public class Productos {
	String codigo;
	String producto;
	String detalles;
	String unimedida;
	float cantidad;
	float cantventa;
	float precioCo;
	float precioVe;
	
	public Productos(String codigo, String producto, String detalles, String unimedida, float cantidad, float cantventa, float precioCo, float precioVe){
		this.codigo = codigo;
		this.producto = producto;
		this.detalles = detalles;
		this.unimedida = unimedida;
		this.cantidad = cantidad;
		this.cantventa = cantventa;
		this.precioCo = precioCo;
		this.precioVe = precioVe;
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
	
}
