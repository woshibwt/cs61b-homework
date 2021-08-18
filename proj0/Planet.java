public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	
	public static final double GRAV_CONSTANT = 6.67e-11;
	
	public Planet(double xP, double yP, double xV,
					double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	
	}
	
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
		
	}
	
	/** calculates the distance between two Planets */
	public double calcDistance(Planet p){
		return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + 
							(this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
	}
	
	/** takes in a planet, returns a double describing the force exerted on this planet
		by the given planet*/
	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		return (GRAV_CONSTANT * this.mass * p.mass) / (r * r); 
	}
	
	/** return the force exerted in the X*/
	public double calcForceExertedByX(Planet p){
		double ff = calcForceExertedBy(p);
		double rr = calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		return ff * dx / rr;
	}
	
	/** return the force exerted in the Y*/
	public double calcForceExertedByY(Planet p){
		double ff = calcForceExertedBy(p);
		double rr = calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		return ff * dy / rr;
	}
	
	/**  take in an array of Planets and calculate the net X 
	force exerted by all planets in that array upon the current Planet.*/
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double fx = 0;
		for (int i = 0; i < allPlanets.length; i ++ ){
			if (this.equals(allPlanets[i])) continue;
			fx += calcForceExertedByX(allPlanets[i]);
		}
		return fx;
	}
	
	/**  take in an array of Planets and calculate the net Y 
	force exerted by all planets in that array upon the current Planet.*/
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double fy = 0;
		for (int i = 0; i < allPlanets.length; i ++ ){
			if (this.equals(allPlanets[i])) continue;
			fy += calcForceExertedByY(allPlanets[i]);
		}
		return fy;
	}
	
	/** Calculate the acceleration on X*/
	public double calcAccelerationByX(double fx){
		return fx / this.mass;
	}
	
	/** Calculate the acceleration on Y*/
	public double calcAccelerationByY(double fy){
		return fy / this.mass;
	}
	
	/**Calculate the new velocity by using the acceleration and current velocity on X */
	public double calcCurrentVelocityByX(double ax, double dt){
		return this.xxVel + ax * dt;
	}
	
	/**Calculate the new velocity by using the acceleration and current velocity on Y */
	public double calcCurrentVelocityByY(double ay, double dt){
		return this.yyVel + ay * dt;
	}

	/**Calculate the new position by using the current velocity and position on X */
	public double calcCurrentPositionByX(double vx, double dt){
		return this.xxPos + dt * vx;
	}
	
	/**Calculate the new position by using the current velocity and position on Y */
	public double calcCurrentPositionByY(double vy, double dt){
		return this.yyPos + dt * vy;
	}
	
	/** adjust the velocity and position */
	public void update(double dt, double fx, double fy){
		this.xxVel = calcCurrentVelocityByX(calcAccelerationByX(fx), dt);
		this.yyVel = calcCurrentVelocityByY(calcAccelerationByY(fy), dt);
		this.xxPos = calcCurrentPositionByX(this.xxVel, dt);
		this.yyPos = calcCurrentPositionByY(this.yyVel, dt);
	}
	
	/** draw planets on the screen */
	public void draw(){
		double proper_coordinateX = this.xxPos / 1e8 * 4;
		double proper_coordinateY = this.yyPos / 1e8 * 4;
		String img = "./images/" + imgFileName;
		StdDraw.picture(proper_coordinateX, proper_coordinateY, img);
		
		/*
		double coordinateX = in.readDouble();
			double coordinateY = in.readDouble();
			double velocityX = in.readDouble();
			double velocityY = in.readDouble();
			double mass = in.readDouble();
			String gif = in.readString();
		*/
	}
	
	
	
	
	
}