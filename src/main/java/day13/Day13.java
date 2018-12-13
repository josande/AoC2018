package day13;

import javafx.util.Pair;

import java.util.*;

class Day13 {
    static class Cart implements Comparable<Cart> {
        int x, y;
        int dx, dy;
        int turns=0;

        Cart(int y, int x, char c) {
            this.x=x;
            this.y=y;
            if (c == '<') { dx = -1; dy =  0; }
            if (c == '^') { dx =  0; dy = -1; }
            if (c == '>') { dx =  1; dy =  0; }
            if (c == 'v') { dx =  0; dy =  1; }
        }

        int getX() { return x; }
        int getY() { return y; }

        @Override
        public int compareTo(Cart other){
            if (this.getY() == other.getY()) return this.getX()-other.getX();
            return  this.getY() - other.getY();
        }

        ArrayList<Cart> move(HashMap<Pair<Integer, Integer>, Character> map, List<Cart> carts) {
            Pair<Integer, Integer> location = new Pair<>(y+dy,x+dx);
            x=x+dx;
            y=y+dy;

            ArrayList<Cart> cartsHere= cartsAtLocation(location, carts);

            Character stopsAt= map.get(location);
            if (stopsAt != null) {
                int tmpX = dx;
                if (stopsAt == '\\') {
                    dx = dy;
                    dy = tmpX;

                } else if (stopsAt == '/') {
                    dx = -dy;
                    dy = -tmpX;

                } else if (stopsAt == '+') {
                    if (turns % 3 == 0) {
                        dx = dy;
                        dy = -tmpX;
                    } else if (turns % 3 == 2) {
                        dx = -dy;
                        dy = tmpX;
                    }
                    turns++;
                }
            }
            return cartsHere;
        }

        private ArrayList<Cart> cartsAtLocation(Pair<Integer, Integer> location, List<Cart> carts) {
            ArrayList<Cart> cartsHere=new ArrayList<>();
            for (Cart cart: carts) {
                if (cart.getX()==location.getValue() && cart.getY()==location.getKey()) {
                    cartsHere.add(cart);
                }
            }
            return cartsHere;
        }
    }

    static String solveA(String input) {
        String[] rows = input.split("\r?\n");
        ArrayList<Cart> carts = new ArrayList<>();
        HashMap<Pair<Integer, Integer>, Character> map = new HashMap<>();
        for (int row=0; row<rows.length; row++) {
            for (int col=0; col< rows[row].length(); col++) {
                char currentChar= rows[row].charAt(col);
                if ( currentChar=='^' || currentChar=='v' || currentChar=='<' || currentChar=='>') {
                    carts.add(new Cart(row, col, currentChar));
                } else if (currentChar=='/' || currentChar=='\\' || currentChar=='+' ) {
                    map.put(new Pair<>(row, col), currentChar);
                }
            }
        }

        //Move da cars!
        for (;;) {
            Collections.sort(carts);
            for (Cart cart : carts)
                if (cart.move(map, carts).size()>1)
                    return cart.getX()+","+cart.getY();
        }
    }

    static String solveB(String input) {
        String[] rows = input.split("\r?\n");
        ArrayList<Cart> carts = new ArrayList<>();
        HashMap<Pair<Integer, Integer>, Character> map = new HashMap<>();
        for (int row=0; row<rows.length; row++) {
            for (int col=0; col< rows[row].length(); col++) {
                Pair<Integer, Integer> coordinate = new Pair<>(row, col);
                char curr= rows[row].charAt(col);
                if (curr=='^' ||curr=='v' || curr=='<' ||curr=='>') {
                    carts.add(new Cart(row, col, curr));
                } else if (curr=='/' || curr=='\\'  || curr=='+' ) {
                    map.put(coordinate, curr);
                }
            }
        }

        //Move da cars!
        for (;;) {
            Collections.sort(carts);
            ArrayList<Cart> cartsToRemove=new ArrayList<>();
            Collections.sort(carts);
            for (Cart cart : carts) {
                ArrayList<Cart> cartsHere = cart.move(map, carts);
                if (cartsHere.size()==2)
                    cartsToRemove.addAll(cartsHere);
            }
            carts.removeAll(cartsToRemove);

            if (carts.size() == 1)
                return carts.get(0).getX() + "," + carts.get(0).getY();
        }
    }
}
