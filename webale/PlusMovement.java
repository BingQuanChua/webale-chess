package webale;

import java.util.ArrayList;

public class PlusMovement implements Movement {

    public boolean move(Coordinate[][] coordinate, Coordinate startPoint, Coordinate endPoint) {
        final int sourceCoordinateX = startPoint.getCoorX();
        final int sourceCoordinateY = startPoint.getCoorY();
        final int destCoordinateX = endPoint.getCoorX();
        final int destCoordinateY = endPoint.getCoorY();
        Coordinate sourceCoordinate = coordinate[sourceCoordinateY][sourceCoordinateX];
        Coordinate destCoordinate = coordinate[destCoordinateY][destCoordinateX];
        ArrayList<Coordinate> tempList = new ArrayList<Coordinate>();

        // check if destination has piece that is same colour
        if (destCoordinate.getChessPiece() != null) {
            if ((sourceCoordinate.getChessPiece().getIsRedColor() && destCoordinate.getChessPiece().getIsRedColor())
                    || (!sourceCoordinate.getChessPiece().getIsRedColor()
                            && !destCoordinate.getChessPiece().getIsRedColor())) {
                return false;
            }
        }

        // move vertical, x-axis no change , y-axis coordinate any
        if (destCoordinateX == sourceCoordinateX && destCoordinateY != sourceCoordinateY) {
            int distanceMovedOfY = destCoordinateY - sourceCoordinateY;
            // move upwards
            //if meet any pieces will stop
            if (distanceMovedOfY < 0) {
                if(distanceMovedOfY == -1){
                    return true;
                }

                for (int i = 1; i < Math.abs(distanceMovedOfY) ; i++) {
                    Coordinate temp = coordinate[sourceCoordinateY - i][sourceCoordinateX];
                    if(temp.getChessPiece() != null){
                        tempList.add(temp);
                    }
                    System.out.println(tempList);
                }

                if(tempList.size()!= 0){
                    tempList.clear();
                    return false;
                }else if (tempList.size() == 0 && destCoordinate.getChessPiece() !=null){
                    if((!destCoordinate.getChessPiece().getIsRedColor()&& !sourceCoordinate.getChessPiece().getIsRedColor()) || (destCoordinate.getChessPiece().getIsRedColor()&& sourceCoordinate.getChessPiece().getIsRedColor())){
                        //same destination chess colour 
                        tempList.clear();
                        return false;
                    }else {
                        tempList.clear();
                        return true;
                    }
                }else if(tempList.size() == 0 && destCoordinate.getChessPiece() == null){
                    tempList.clear();
                    return true;
                }
            }
            // move downwards
            else {

                if(distanceMovedOfY == 1 ){
                    return true;
                }

                for (int i = 1; i < distanceMovedOfY - 2; i++) {
                    Coordinate temp = coordinate[sourceCoordinateY + i][sourceCoordinateX];
                    if(temp.getChessPiece() != null){
                        tempList.add(temp);
                    }
                    System.out.println(tempList);
                }

                if(tempList.size()!= 0){
                    tempList.clear();
                    return false;
                }else if (tempList.size() == 0 && destCoordinate.getChessPiece() !=null){
                    if((!destCoordinate.getChessPiece().getIsRedColor()&& !sourceCoordinate.getChessPiece().getIsRedColor()) || (destCoordinate.getChessPiece().getIsRedColor()&& sourceCoordinate.getChessPiece().getIsRedColor())){
                        //same destination chess colour 
                        tempList.clear();
                        return false;
                    }else {
                        tempList.clear();
                        return true;
                    }
                }else if(tempList.size() == 0 && destCoordinate.getChessPiece() == null){
                    tempList.clear();
                    return true;
                }
            }
        }

        // move horizontal, x-axis coordinate any, y-axis no change
        else if (destCoordinateY == sourceCoordinateY && destCoordinateX != sourceCoordinateX) {
            int distanceMovedOfX = destCoordinateX - sourceCoordinateX;
            // move left
            if (distanceMovedOfX < 0) {

                if(distanceMovedOfX == -1 ){  
                    return true;
                }

                for (int i = 1; i < Math.abs(distanceMovedOfX) ; i++) {
                    Coordinate temp = coordinate[sourceCoordinateY][sourceCoordinateX - i];
                    if(temp.getChessPiece() != null){
                        tempList.add(temp);
                    }
                    System.out.println(tempList);
                }

                if(tempList.size()!= 0){
                    tempList.clear();
                    return false;
                }else if (tempList.size() == 0 && destCoordinate.getChessPiece() !=null){
                    if((!destCoordinate.getChessPiece().getIsRedColor()&& !sourceCoordinate.getChessPiece().getIsRedColor()) || (destCoordinate.getChessPiece().getIsRedColor()&& sourceCoordinate.getChessPiece().getIsRedColor())){
                        //same destination chess colour 
                        tempList.clear();
                        return false;
                    }else {
                        tempList.clear();
                        return true;
                    }
                }else if(tempList.size() == 0 && destCoordinate.getChessPiece() == null){
                    tempList.clear();
                    return true;
                }
            }
            // move right
            else {

                if (distanceMovedOfX == 1 ){
                    return true;
                }

                for (int i = 1; i < distanceMovedOfX ; i++) {
                    Coordinate temp = coordinate[sourceCoordinateY][sourceCoordinateX + i];
                    if(temp.getChessPiece() != null){
                        tempList.add(temp);
                    }
                    System.out.println(tempList);
                }

                if(tempList.size()!= 0){
                    tempList.clear();
                    return false;
                }else if (tempList.size() == 0 && destCoordinate.getChessPiece() !=null){
                    if((!destCoordinate.getChessPiece().getIsRedColor()&& !sourceCoordinate.getChessPiece().getIsRedColor()) || (destCoordinate.getChessPiece().getIsRedColor()&& sourceCoordinate.getChessPiece().getIsRedColor())){
                        //same destination chess colour 
                        tempList.clear();
                        return false;
                    }else {
                        tempList.clear();
                        return true;
                    }
                }else if(tempList.size() == 0 && destCoordinate.getChessPiece() == null){
                    tempList.clear();
                    return true;
                }
            }
        }

        return false;

    }

}