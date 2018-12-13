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

            if (c == '<'){
                dx=-1;
                dy=0;
            }
            if (c == '^') {
                dx = 0;
                dy = -1;
            }
            if (c == '>') {
                dx = 1;
                dy = 0;
            }
            if (c == 'v') {
                dy = 1;
                dx = 0;
            }
            System.out.println("New cart "+y+","+x+", "+c+" dx:"+dx+" dy:"+dy);

        }

        int getX() {
            return x;
        }
        int getY() {
            return y;
        }


        @Override
        public int compareTo(Cart other){
            if (this.getY() < other.getY())
                return -1;
            else if (this.getY() > other.getY())
                return 1;
            return other.getX()-this.getX();
        }

        boolean move(HashMap<Pair<Integer, Integer>, Character> map, List<Cart> carts, int iteration) {
            Pair<Integer, Integer> location = new Pair<>(y+dy,x+dx);
            x=x+dx;
            y=y+dy;
            if (!isFree(location, carts)) {
                return false;
            }

            char nextPlace= map.getOrDefault(location, ' ');

            int tmp=dx;
            if (nextPlace == '\\') {
                 dx=dy;
                 dy=tmp;

            } else if (nextPlace == '/') {
                dx=-dy;
                dy=-tmp;

            } else if (nextPlace == '+') {
                if (turns%3 == 0) {
                    dx=dy;
                    dy=tmp;
                } else if (turns%3 == 2) {
                    dx=-dy;
                    dy=-tmp;
                }
                turns++;

            }

            return true;
        }

        private boolean isFree(Pair<Integer, Integer> location, List<Cart> carts) {
            int cartsHere=0;
            for (Cart cart: carts) {
                if (cart.getX()==location.getValue() && cart.getY()==location.getKey()) {
                    cartsHere++;
                    if (cartsHere>1)
                        return false;
                }
            }
            return true;
        }

    }

    static String solveA(String input) {
        String[] rows = input.split("\r?\n");
        ArrayList<Cart> carts = new ArrayList<>();
        HashMap<Pair<Integer, Integer>, Character> map = new HashMap<>();
        for (int row=0; row<rows.length; row++) {
            for (int col=0; col< rows[row].length(); col++) {
                Pair<Integer, Integer> coordinate = new Pair<>(row, col);
                char curr= rows[row].charAt(col);
                if (curr=='^' ||curr=='v' || curr=='<' ||curr=='>') {
                    carts.add(new Cart(row, col, curr));

                }
                if (curr=='/' || curr=='\\'  || curr=='+' ) {
                    map.put(coordinate, curr);
                }
            }
        }

        //Move da cars!


        System.out.println("MapSize: "+map.size());
        for (int iteration = 0; ; iteration++) {
            Collections.sort(carts);
            for (Cart cart : carts) {
            //    System.out.println(cart.getY()+","+cart.getX());
                if (!cart.move(map, carts,iteration)) {
                    System.out.println(" KABLOOM @ "+cart.getX()+", "+cart.getY());
                    return cart.getX()+","+cart.getY();
                }
            }

        }
    }

    static String solveB(String input) {
        return "";
    }
}
