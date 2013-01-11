/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzerofjava;

/**
 *
 * @author Андрей
 */
public class Regexpresions {

    public final static String OPERATORS = "(\\|\\||!=|==|>>|<<|>=|<=|!|=|\\-|\\++|\\+|\\\\|\\*|%|^|&&|\\||>|<|>>>)"; //|>|<|<=|>=|!|=|-|+|/|*|&&
    public final static String SEPARATORS = "(\\[|\\]|\\{|\\}|\\(|\\)|\\;|\\.|\\,)";
    public final static String INDIFICATORS = "[А-Яа-я\\w]+";
    public final static String NUMBERS = "(([\\+-]?\\d+)+(\\.(\\d*(e[\\+-]?)\\d+|\\d+)?)+)";
    public final static String NUMBERS2 = "([\\+-]?(\\d+|\\.(e[\\+-]\\d+|\\d+)|\\d\\.((e[\\+-]\\d+|\\d+))?))";
    public final static String LITERALS = "[\\+-]?0x?\\d+(l|L|d|D|f|F|u|U)?";
    public final static String COMENTS = "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.+)";    //okey
    public final static String SCHLITERAL = "(\"([^\"\\n])*\")|(\\'([^\\'])?\\')";      //okey
    public final static String LEXEMS = SCHLITERAL + "|" + COMENTS + "|"
            + SEPARATORS + "|" + NUMBERS + "|" + NUMBERS2 + "|" + OPERATORS
            + "|" + LITERALS + "|" + INDIFICATORS;
}
