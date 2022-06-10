package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> points;
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);

        Point nearestPoint = points.get(0);
        double minDist = Point.distance(nearestPoint, goal);

        for (Point point : points) {
            double dist = Point.distance(point, goal);
            if (dist < minDist) {
                minDist = dist;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }
}
