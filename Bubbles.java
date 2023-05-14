
public class Bubbles {
	 private double x;
	 private double y;
	 private double yvelocity;
	 private double size;
	private double xvelocity; 
	  private boolean xmove;
	  private boolean ymove;
	 private double yfixforsinmovementfunction;
	
	public double getYfixforsinmovementfunction() {
		return yfixforsinmovementfunction;
	}


	public void setYfixforsinmovementfunction(double yfixforsinmovementfunction) {
		this.yfixforsinmovementfunction = yfixforsinmovementfunction;
	}


	public Bubbles(double x , double y , double yvelocity , double size , double xvelocity, boolean xmove,boolean ymove){
		 this.x = x;
		 this.y = y;
		 this.yvelocity = yvelocity;
		 this.size = size;
		 this.xvelocity = xvelocity;
		 this.xmove = xmove;
		 this.ymove = ymove;
		 this.yfixforsinmovementfunction = y;
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


	public double getSize() {
		return size;
	}


	public void setSize(double size) {
		this.size = size;
	}


	public double getXvelocity() {
		return xvelocity;
	}


	public void setXvelocity(double xvelocity) {
		this.xvelocity = xvelocity;
	}


	public boolean isXmove() {
		return xmove;
	}


	public void setXmove(boolean xmove) {
		this.xmove = xmove;
	}


	public boolean isYmove() {
		return ymove;
	}


	public void setYmove(boolean ymove) {
		this.ymove = ymove;
	}
	
	 
}
