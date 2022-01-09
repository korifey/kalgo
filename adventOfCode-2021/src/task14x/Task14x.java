package task14x;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// 2265039461735 - неверный результат

class Rule {
    public char first;
    public char second;
    public char middle;

    public Rule(char first, char second, char middle) {
        this.first = first;
        this.second = second;
        this.middle = middle;
        System.out.println("creating rule: "+first+second+" -> "+middle);
    }

    public ArrayList<Character> chain20 = new ArrayList<>();
    public HashMap<Character, Long> hashMap;
}

public class Task14x {
    ArrayList<Rule> rules = new ArrayList<>();
    String dataFileName = "adventOfCode-2021/resources/input14test.txt";
    public Task14x() throws Exception {

        // + parse input data
        ArrayList<Character> polymer = new ArrayList<>();
        BufferedReader inp = new BufferedReader(new FileReader(dataFileName));
        for(String symbol:inp.readLine().trim().split(""))
            polymer.add(symbol.charAt(0));
        System.out.println("polymer: "+polymer);

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
        for(int i=0; i<rules.size(); i++) {
            Rule curr = rules.get(i);
            for(int k=i+1; k<rules.size(); k++) {
                if(rules.get(k).first == curr.first && rules.get(k).second == curr.second) {
                    System.out.println("duplicate rules! "+i+","+k);
                    System.exit(1);
                }
            }
        }
        // - parsing input data done

        // Each rule has 2 symbols. For each pair of symbols make 20 step calculation
        // Result: chain20 - long polymer, saved in rule
        // Result: hash    - HashMap<symbol,count> for each symbol in polymer
        fillRules();
        System.out.println("fillRules done");

        // Common HashMap for all subchains
        HashMap<Character, Long> symbolsquantity = new HashMap<>();

        System.out.println("polimer size:"+polymer.size());

        for(int sym=0; sym<polymer.size()-1; sym++) {
            // for each pair of symbols in polymer get result chain of 20 steps
            ArrayList<Character> res20 = matchingRule(polymer.get(sym), polymer.get(sym+1)).chain20;
            for(int sym1=0; sym1<res20.size()-1; sym1++) {
                // for each pair of symbols in resulting chain get result chain of 20 steps
                Rule r = matchingRule(res20.get(sym1), res20.get(sym1+1));
                ArrayList<Character> res202 = r.chain20;
                Set<Character> set  = r.hashMap.keySet();
                // System.out.println("sym1:"+sym1+"; set size:"+set.size());
                for(Character c:set) {
                    long stat = r.hashMap.get(c);
                    if(symbolsquantity.get(c) != null) {
                        symbolsquantity.put(c, symbolsquantity.get(c)+stat);
                    } else {
                        symbolsquantity.put(c, stat);
                    }
                }
                if(sym1 != 0) {
                    char c = res202.get(0);
                    symbolsquantity.put(c, symbolsquantity.get(c) - 1L);
                }
//                if(sym1%10000 == 0) System.out.println("sym1:"+sym1+" res20:"+res20.size());
            }
            if(sym != 0) {
                char c = polymer.get(sym+1);
                symbolsquantity.put(c, symbolsquantity.get(c) - 1L);
            }

            System.out.println("sym:"+sym+" total:"+polymer.size());
        }

        Set<Character> set = symbolsquantity.keySet();
        long min=Long.MAX_VALUE;
        long max=Long.MIN_VALUE;
        for(Character c:set) {
            if(symbolsquantity.get(c) < min)
                min = symbolsquantity.get(c);
            if(symbolsquantity.get(c) > max)
                max = symbolsquantity.get(c);

            System.out.println(symbolsquantity.get(c)+":"+max+"-"+min);
        }

        System.out.println(max+"-"+min);

        System.out.println( (long)(max - min) );
    }

    void fillRules() {
        for(Rule r:rules) {
            ArrayList<Character> polymer = new ArrayList<>();
            polymer.add(r.first);
            polymer.add(r.second);
            for(int i=1; i<=20; i++) {
                ArrayList<Character> produced = new ArrayList<>();
                for(int sym=0; sym<polymer.size()-1; sym++) {
                    Character median = matchingRule(polymer.get(sym), polymer.get(sym+1)).middle;
                    produced.add(polymer.get(sym));
                    if(median != null) {
                        produced.add(median);
                    } else {
                        System.out.println("no rule for: "+polymer.get(sym)+polymer.get(sym+1));
                        System.exit(1);
                    }
                }
                produced.add(polymer.get(polymer.size()-1));
                polymer = produced;
            }
            r.chain20 = polymer;
            HashMap<Character, Long> hash = new HashMap<>();
            for(Character c: polymer) {
                if(hash.get(c) != null) {
                    hash.put(c, hash.get(c)+1L);
                } else {
                    hash.put(c, 1L);
                }
            }
            r.hashMap = hash;
            System.out.println("rule "+r.first+r.second+ " chain.size()="+r.chain20.size());
        }
    }

    Rule matchingRule(char first, char second) {
        for(Rule r: rules) {
            if(r.first == first && r.second == second)
                return r;
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        new Task14x();
    }
}
