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
                dx = 0;
                dy = 1;
            }
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
            return this.getX()-other.getX();
        }

        ArrayList<Cart> move(HashMap<Pair<Integer, Integer>, Character> map, List<Cart> carts, int iteration) {
            Pair<Integer, Integer> location = new Pair<>(y+dy,x+dx);
            x=x+dx;
            y=y+dy;
            ArrayList<Cart> cartsHere=cartsAtEndPosition(location, carts);

            Character stopsAt= map.get(location);
            if (stopsAt == null)
                return cartsHere;

            int tmpdX=dx;
            if (stopsAt == '\\') {
                 dx=dy;
                 dy=tmpdX;

            } else if (stopsAt == '/') {
                dx=-dy;
                dy=-tmpdX;

            } else if (stopsAt == '+') {
                if (turns%3 == 0) {

                    //   >
                    // v   ^
                    //   >
                    //

                    //   v     dx dy  dx dy
                    //1    >   (0,1)  (1,0)          dy=-dx   dx=dy
                    //

                    //2    ^  dx dy  dx dy
                    //   >    (1,0)  (0,-1)          dy=-dx   dx=dy

                    //3  <    dx dy  dx dy
                    //     ^  (0,-1) (-1,0)          dy=-dx   dx=dy

                    //4    <  dx dy  dx dy
                    //   v    (-1,0) (0,1)           dy=-dx   dx=dy

                    if (dx == 0) {

                    }
                    dx=dy;
                    dy=-tmpdX;
                } else if (turns%3 == 2) {

                    //1    v  dx dy  dx dy
                    //   <    (0,1) (-1,0)          dy=dx   dx=-dy
                    //

                    //2  >    dx dy  dx dy
                    //    v   (1,0)  (0,1)           dy=dx    dx=-dy

                    //3    >  dx dy  dx dy
                    //   ^    (0,-1) (1,0)           dy=dx   dx=-dy

                    //4  ^    dx dy  dx dy
                    //     < (-1,0)  (0,-1)          dy=dx   dx=-dy

                    dx=-dy;
                    dy=tmpdX;
                }
                turns++;

            }

            return cartsHere;
        }

        private ArrayList<Cart> cartsAtEndPosition(Pair<Integer, Integer> location, List<Cart> carts) {
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
        for (int iteration = 0; ; iteration++) {
            Collections.sort(carts);
            for (Cart cart : carts) {
                if (cart.move(map, carts,iteration).size()>1) {
                    return cart.getX()+","+cart.getY();
                }
            }
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
        for (int iteration = 0; ; iteration++) {
            Collections.sort(carts);
            Iterator<Cart> cart = carts.iterator();
            ArrayList<Cart> cartsToRemove=new ArrayList<>();
            while(cart.hasNext()) {
                Cart currentCart = cart.next();
                ArrayList<Cart> cartsHere=currentCart.move(map, carts,iteration);
                if (cartsHere.size()==2) {
                    cartsToRemove.addAll(cartsHere);
                }
            }
            carts.removeAll(cartsToRemove);

            if (carts.size() == 1) {
                return carts.get(0).getX() + "," + carts.get(0).getY();
            }
        }
    }
}
