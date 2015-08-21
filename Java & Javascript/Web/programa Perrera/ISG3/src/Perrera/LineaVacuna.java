package Perrera;

import java.util.Date;

public class LineaVacuna {
	private Date fechaVacunacion;
	private boolean vacunado;
	private Vacuna vac;

	public boolean isVacunado() {
		return vacunado;
	}
	public void setVacunado(boolean vacunado) {
		this.vacunado = vacunado;
	}
	public Date getFechaVacunacion() {
		return fechaVacunacion;
	}
	public void setFechaVacunacion(Date fechaVacunacion) {
		this.fechaVacunacion = fechaVacunacion;
	}
	public Vacuna getVac() {
		return vac;
	}
	public void setVac(Vacuna vac) {
		this.vac = vac;
	}
	
	
}
