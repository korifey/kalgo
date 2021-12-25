package task24;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {
  // inp w
  // mul x 0
  // add x z
  // mod x 26
  // div z 26
  // add x -5
  private static final Pattern UNOP_VAR_PATTERN = Pattern.compile("inp (?<var>w|x|y|z)");
  private static final Pattern BINOP_VAR_VALUE_PATTERN =
      Pattern.compile("(?<op>add|mul|div|mod|eql) (?<var>w|x|y|z) (?<val>-?\\d+)");
  private static final Pattern BINOP_VAR_VAR_PATTERN =
      Pattern.compile("(?<op>add|mul|div|mod|eql) (?<var1>w|x|y|z) (?<var2>w|x|y|z)");

  public static Instruction parseInput(String s) {
    List<Function<String, Instruction>> parsers = Arrays.asList(Parser::parseUnop, Parser::parseBinopVarValue, Parser::parseBinopVarVar);
    
    for (Function<String, Instruction> parser : parsers) {
      Instruction instruction = parser.apply(s);
      if (instruction != null)
        return instruction;
    } 
    
    throw new IllegalArgumentException("Input string \"" + s + "\" is ill syntaxed.");
  }

  private static Instruction parseUnop(String s) {
    Matcher m = UNOP_VAR_PATTERN.matcher(s);

    if (m.matches()) {
      Variable v = Variable.valueOf(m.group("var"));
      return new Input(v);
    }
    
    return null;
  }
  
  @SuppressWarnings("incomplete-switch")
  private static Instruction parseBinopVarValue(String s) {
    Matcher m = BINOP_VAR_VALUE_PATTERN.matcher(s);
    
    if (m.matches()) {
      Instruction.Type op = Instruction.Type.valueOf(m.group("op"));
      Variable v = Variable.valueOf(m.group("var"));
      Long value = Long.parseLong(m.group("val"));

      switch (op) {
        case add:
          return new Add(v, value);
        case mul:
          return new Mul(v, value);
        case div:
          return new Div(v, value);
        case mod:
          return new Mod(v, value);
        case eql:
          return new Eql(v, value);
      }
    }
    
    return null;
  }
  
  @SuppressWarnings("incomplete-switch")
  private static Instruction parseBinopVarVar(String s) {
    Matcher m = BINOP_VAR_VAR_PATTERN.matcher(s);
    
    if (m.matches()) {
      Instruction.Type op = Instruction.Type.valueOf(m.group("op"));
      Variable v1 = Variable.valueOf(m.group("var1"));
      Variable v2 = Variable.valueOf(m.group("var2"));

      switch (op) {
        case add:
          return new Add(v1, v2);
        case mul:
          return new Mul(v1, v2);
        case div:
          return new Div(v1, v2);
        case mod:
          return new Mod(v1, v2);
        case eql:
          return new Eql(v1, v2);
      }
    }
    
    return null;
  }
}
