//package movement;
//
//import java.awt.*;
//import java.awt.geom.Point2D;
//
//import static java.lang.Math.hypot;
//import static java.lang.Math.signum;
//
//public class poubelle {
//
//    public Point handleWallsAndBounceback(double dX, double dY){
//        final double originaldX=dX,originaldY=dY;
//
//        Direction blockedDirection=gp.collisionChecker.checkTileCollision(this);
//        if (blockedDirection.dy==-1 && dY<0) {
//            dY=0;
//            dX = this.speed*signum(originaldX)+(dX-originaldX);
//        }
//        else if (blockedDirection.dy==1 && dY>0) {
//            dY=0;
//            dX = this.speed*signum(originaldX)+(dX-originaldX);
//        }
//        if (blockedDirection.dx==-1 && dX<0) {
//            dX=0;
//            dY = this.speed*signum(originaldY)+(dY-originaldY);
//        }
//        else if (blockedDirection.dx==1 && dX>0) {
//            dX=0;
//            dY = this.speed*signum(originaldY)+(dY-originaldY);
//        }
//        return new Point((int) Math.round(dX),(int) Math.round(dY));
//    }
//
//    public Point2D.Double calculateBounceBackValues(){
//        int adjacent = bounceBackFrom.x-this.x;
//        int oppose = bounceBackFrom.y-this.y;
//
//        while (adjacent==0 && oppose==0) {
//            oppose = (int) (Math.random() * 3) - 1;
//            adjacent = (int) (Math.random() * 3) - 1;
//        }
//
//        double hypothenuse = hypot(oppose,adjacent);
//
//        double dX = -1*(this.bounceBackValue*(adjacent)/hypothenuse);
//        double dY = -1*(this.bounceBackValue*(oppose)/hypothenuse);
//        bounceBackValue-=0.1;
//        if (bounceBackValue<=0){
//            bounceBack=false;
//        }
//        return new Point2D.Double(dX,dY);
//    }
//}
