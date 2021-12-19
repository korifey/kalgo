package task14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

class Rule {
    char first;
    char second;
    char result;

    public Rule(char first, char second, char result) {
        this.first = first;
        this.second = second;
        this.result = result;
    }

    Character matches(char sym1, char sym2) {
        if(sym1 == first && sym2 == second)
            return result;
        else
            return null;
    }
}

public class Task14 {
    ArrayList<Rule> rules = new ArrayList<>();
    ArrayList<Character> polymer = new ArrayList<>();
    public Task14() throws Exception {
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input14.txt"));
        for(String symbol:inp.readLine().trim().split(""))
            polymer.add(symbol.charAt(0));
        inp.readLine();
        String s;
        while ((s = inp.readLine()) != null) {
            if(s.trim().length()==0)
                continue;
            String[] triada = s.split("\\s+");
            rules.add(new Rule(triada[0].trim().charAt(0)
                    ,triada[0].trim().charAt(1)
                    ,triada[2].trim().charAt(0)
                    )
            );
        }
        for(int i=1; i<=10; i++) {
            ArrayList<Character> produced = new ArrayList<>();
            for(int sym=0; sym<polymer.size()-1; sym++) {
                Character median = findRule(polymer.get(sym), polymer.get(sym+1));
                produced.add(polymer.get(sym));
                if(median != null) {
                    produced.add(median);
                }
            }
            produced.add(polymer.get(polymer.size()-1));
            polymer = produced;
//            System.out.println("step "+i+":"+polymer);
        }
        HashMap<Character, Integer> symbolsquantity = new HashMap<>();
        for(Character c: polymer) {
            if(symbolsquantity.get(c) != null) {
                symbolsquantity.put(c, symbolsquantity.get(c)+1);
            } else {
                symbolsquantity.put(c, 1);
            }
        }
        Set<Character> set = symbolsquantity.keySet();
        int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for(Character c:set) {
            if(symbolsquantity.get(c) < min)
                min = symbolsquantity.get(c);
            if(symbolsquantity.get(c) > max)
                max = symbolsquantity.get(c);
        }
        System.out.println(max - min);
    }

    // NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
    // NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB

    Character findRule(char first, char second) {
        Character res=null;
        for(Rule r:rules) {
            if( (res = r.matches(first, second)) != null)
                return res;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        new Task14();
    }
}
