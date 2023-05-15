
public class pumpWaterClass {
	private double x;
	 private double y;
	 private double yvelocity;
	 private double xvelocity;
	 private double size;
	public pumpWaterClass(double x, double y, double yvelocity,
			double xvelocity, double size) {
		super();
		this.x = x;
		this.y = y;
		this.yvelocity = yvelocity;
		this.xvelocity = xvelocity;
		this.size = size;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getYvelocity() {
		return yvelocity;
	}
	public void setYvelocity(double yvelocity) {
		this.yvelocity = yvelocity;
	}
	public double getXvelocity() {
		return xvelocity;
	}
	public void setXvelocity(double xvelocity) {
		this.xvelocity = xvelocity;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	
	 
}
