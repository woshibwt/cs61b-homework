public class NBody{
	private static String imageToDraw = "./images/starfield.jpg";
	private static double radius;
	private static Planet[] planets;
	
	/** return a double corresponding to the radius of the universe in that file*/
	public static double readRadius(String io){
		In in = new In(io);
		int planetNum = in.readInt();
		double r = in.readDouble();
		return r;
	}
	
	/** Given a file name, it should return an array of Planets corresponding to the planets in the file */
	public static Planet[] readPlanets(String io){
		In in = new In(io);
		int planetNum = in.readInt();
		double radius = in.readDouble();
		Planet[] allPlanets = new Planet[planetNum];
		for (int i = 0; i < planetNum; i ++ ){
			double coordinateX = in.readDouble();
			double coordinateY = in.readDouble();
			double velocityX = in.readDouble();
			double velocityY = in.readDouble();
			double mass = in.readDouble();
			String gif = in.readString();
			
			Planet p = new Planet(coordinateX, coordinateY, velocityX, velocityY,
									mass, gif);
			allPlanets[i] = p;
		}
		return allPlanets;
	}
	
	public static void main(String[] argv){
		double T = Double.valueOf(argv[0].toString());
		double dt = Double.valueOf(argv[1].toString());
		String filename = argv[2];
		
		radius = readRadius(filename);
		planets = readPlanets(filename);
		
		int rScale = (int)((radius / 1e8) * 4);
		StdDraw.setScale(-rScale, rScale);
		StdDraw.picture(0, 0, imageToDraw, 2 * rScale, 2 * rScale);
		
		for (int i = 0; i < planets.length; i ++ ){
			planets[i].draw();
		}
		
		StdDraw.enableDoubleBuffering();
		
		double time = 0;
		Double[] xForces = new Double[planets.length];
		Double[] yForces = new Double[planets.length];
		while (time < T){
			for (int i = 0; i < planets.length; i ++ ){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			
			for (int i = 0; i < planets.length; i ++ ){
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0, 0, imageToDraw, 2 * rScale, 2 * rScale);
			
			for (int i = 0; i < planets.length; i ++ ){
			planets[i].draw();
			}
			
			StdDraw.show();
			StdDraw.pause(10);
			
			time += dt;
		}
		
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
			}
		
		
	}
	
}