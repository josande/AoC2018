package day02;

import org.junit.jupiter.api.Test;

import static day02.Day02.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day02Test {

    @Test
    void testExampleOne() {
        String input = "abcdef\n" +
                "bababc\n" +
                "abbcde\n" +
                "abcccd\n" +
                "aabcdd\n" +
                "abcdee\n" + //4*3
                "ababab";
        assertEquals(12,getChecksum(input));
    }

    @Test
    void testExampleTwo() {
        String input = "abcdef\n" +
                "abbcde\n" +
                "abcccd\n" +
                "aabcdd\n" +
                "abcdee\n" +  //3*2
                "ababab";
        assertEquals(6,getChecksum(input));
    }

    @Test
    void testContainsCombination_PairAndTriple() {
        String input = "bababc";
        assertTrue(containsCombination(input,2));
        assertTrue(containsCombination(input,3));
    }

    @Test
    void testContainsCombination_NoPair() {
        String input = "abcdef";
        assertFalse(containsCombination(input,2));
        assertFalse(containsCombination(input,3));

    }

    @Test
    void testContainsCombination_TwoPair() {
        String input = "aabcdd";
        assertTrue(containsCombination(input,2));
        assertFalse(containsCombination(input,3));
    }

    @Test
    void testContainsCombination_TwoTripleNoPair() {
        String input = "ababab";
        assertFalse(containsCombination(input,2));
        assertTrue(containsCombination(input,3));

    }

    @Test
    void testContainsCombination_Pair() {
        String input = "abcdee";
        assertTrue(containsCombination(input,2));
        assertFalse(containsCombination(input,3));

    }

    @Test
    void testIsMatch_Matches() {
        String inputA = "fghij";
        String inputB = "fguij";
        assertTrue(isMatch(inputA,inputB));
        assertTrue(isMatch(inputB,inputA));
    }

    @Test
    void testIsMatch_NoMatch() {
        String inputA = "abcde";
        String inputB = "axcye";
        assertFalse(isMatch(inputA,inputB));
        assertFalse(isMatch(inputB,inputA));
    }
    @Test
    void testIsMatch_ExactMatch() {
        String inputA = "abcde";
        String inputB = "abcde";
        assertFalse(isMatch(inputA,inputB));
        assertFalse(isMatch(inputB,inputA));
    }

    @Test
    void testFindCommonLetters() {
        String inputA = "fghij";
        String inputB = "fguij";
        assertEquals("fgij",findCommonLetters(inputA,inputB));
    }

    @Test
    void testFindCommonLettersTwo() {
        String inputA = "ABBA";
        String inputB = "ABCD";
        assertEquals("AB",findCommonLetters(inputA,inputB));
    }


    @Test
    void testExampleOnePartTwo() {
        String input = "abcde\n" +
                "fghij\n" +
                "klmno\n" +
                "pqrst\n" +
                "fguij\n" +
                "axcye\n" +
                "wvxyz";
        assertEquals("fgij",findCommonBoxIDs(input));
    }
    @Test
    void testExampleOneMatchesAreAtTheEnd() {
        String input = "abcde\n" +
                "klmno\n" +
                "pqrst\n" +
                "axcye\n" +
                "fghij\n" +
                "fguij";
        assertEquals("fgij",findCommonBoxIDs(input));
    }
    @Test
    void puzzle() {
        String input="ymdrcyapvwfloiuktanxzjsieb\n" +
                "ymdrwhgznwfloiuktanxzjsqeb\n" +
                "ymdrchguvwfloiuktanxmjsleb\n" +
                "pmdrchgmvwfdoiuktanxzjsqeb\n" +
                "ymdrfegpvwfloiukjanxzjsqeb\n" +
                "ymdrchgpvwfloiukmanazjsdeb\n" +
                "ymdsnhgpvwflciuktanxzjsqeb\n" +
                "lmdrbhrpvwfloiuktanxzjsqeb\n" +
                "ymdrwhgpvwfloiukeanxzjsjeb\n" +
                "ymdrchgpvpfloihktanszjsqeb\n" +
                "omdrchgpvwflokuktanazjsqeb\n" +
                "kmsrchgpvwfloiuktanxqjsqeb\n" +
                "ymdrchopvwzloiustanxzjsqeb\n" +
                "omdrchgpvwfloiuktawxtjsqeb\n" +
                "ymdrchgpvwfroiukhanozjsqeb\n" +
                "ymdrchgpvwfloikktanyzosqeb\n" +
                "ymdrchgpvwfioiuktaexzjsqea\n" +
                "ymdrcngpvwfloiuktanxzjsamb\n" +
                "ymdrchgpqwfaoiuktanxxjsqeb\n" +
                "ymdrcmgpvwflziuktakxzjsqeb\n" +
                "ymdrchguvwsloiuktanxzjsqen\n" +
                "ymdrchppowfloiuvtanxzjsqeb\n" +
                "ymdrcngpvwflyiukkanxzjsqeb\n" +
                "ymdrcbgpvwfloiukjanxzjspeb\n" +
                "ymdrchgpvwflopuktanxzosseb\n" +
                "ygdrchgpvwfloiukxanxcjsqeb\n" +
                "ymdrchgpvwfloauktanuzjsqei\n" +
                "ymerchgpvwfloiumtanxzjsqet\n" +
                "ymdlcegpvwfloiuktbnxzjsqeb\n" +
                "ymdrclgpvwfloiukyanxzjlqeb\n" +
                "ymdrchgpvwhmoiuktanxijsqeb\n" +
                "ymdrchgpwrfloiuktanxdjsqeb\n" +
                "ymdbcwgpvwfloiuktanxzusqeb\n" +
                "ymgrchgphwfloiuktanxzjspeb\n" +
                "imdrchgpvwflmiuktanxzjsqib\n" +
                "ymdrihgpvwfloiiktanxzjsteb\n" +
                "ywdrchgpvwfloibkvanxzjsqeb\n" +
                "ymdrchgpxwfloiuktanezjsqes\n" +
                "ymdrchgpiwfloiukxanxzhsqeb\n" +
                "ymdrchgpveflokuktdnxzjsqeb\n" +
                "kmdrchgpvwfloviktanxzjsqeb\n" +
                "ymdrchgpvgfmoiuytanxzjsqeb\n" +
                "ymyrchgpvzfluiuktanxzjsqeb\n" +
                "ymdrchguvwfloiuktanxpjsqlb\n" +
                "ymerchgpvwfloiukthnxsjsqeb\n" +
                "hmdrchgpvwglfiuktanxzjsqeb\n" +
                "ymdrchgpvwfdoiuktanxzjsqaf\n" +
                "yudrchgdvwfloiuktaexzjsqeb\n" +
                "ymdbchgxvwfloiuktanxzjsqem\n" +
                "ymdrchgpvwfloiumjanxzjsqpb\n" +
                "ymdrchgpqwfloiuqtanxrjsqeb\n" +
                "ymdqchhpvwfloiuktanxzzsqeb\n" +
                "ymdryhgpfwfloiuktanxzjsyeb\n" +
                "xmdrchgpvwfloioitanxzjsqeb\n" +
                "ykdrchgpvwfloiuktcnxzisqeb\n" +
                "ymdrcpgprwfloiuktanqzjsqeb\n" +
                "yidrchgpvwfloiuktanxzjgleb\n" +
                "ymdrchgpxwfloiuktanxzjsxfb\n" +
                "ymdrchgfvwfloiuktanxzjiteb\n" +
                "ymdrchgvvwflqifktanxzjsqeb\n" +
                "ymdrchgplwfloiuktanizjnqeb\n" +
                "ymdrchgpvwfyfiuktafxzjsqeb\n" +
                "ymddchgpvwcloiuktanxzjsqeq\n" +
                "ymdrchgkvwflaiuktanxzjsqfb\n" +
                "yudrchgpvwfzoiuktanxzjsreb\n" +
                "ymdrdhgpvwfloiuktnnxqjsqeb\n" +
                "ymdrnhgpvwfloiuktauxzjdqeb\n" +
                "ymdrchgpvwflsiddtanxzjsqeb\n" +
                "ymdrchgpvwhloeuktanxzjsqek\n" +
                "ymdrchgpvjfioiuktawxzjsqeb\n" +
                "ycdrohgpvwfgoiuktanxzjsqeb\n" +
                "ymdrchgpvwflmifktanxzjsqel\n" +
                "yfdrchrpvwfloruktanxzjsqeb\n" +
                "ymdrchgjvwfloiuktanxzrsqeg\n" +
                "ymarchgpxwfloiukkanxzjsqeb\n" +
                "ymdrchgppwflghuktanxzjsqeb\n" +
                "ymdrchvpvwfloiuktanxpjrqeb\n" +
                "ymdlchgpqjfloiuktanxzjsqeb\n" +
                "ymdrchgpvwfofiuktandzjsqeb\n" +
                "ymdrcngpqwfloiuktanlzjsqeb\n" +
                "ymdrchgpvwfloiuiocnxzjsqeb\n" +
                "ymdrcogpvwfloizktanxzjcqeb\n" +
                "ymdrchgpvlfvoiuksanxzjsqeb\n" +
                "ymdrchgpvwflocpctanxzjsqeb\n" +
                "ymdrchgpvwfloiuktanlzjsejb\n" +
                "yndrchgpvwflzifktanxzjsqeb\n" +
                "ymdrcrgpvkfloiuktanxrjsqeb\n" +
                "ymdrchhpvwslocuktanxzjsqeb\n" +
                "ymdrxhgpvwfloiuwtazxzjsqeb\n" +
                "ymdrchgpvafloiuutanxzjsqxb\n" +
                "ymdrchppvhfloquktanxzjsqeb\n" +
                "ymprcugpvwtloiuktanxzjsqeb\n" +
                "ymdrchgpvvflyiuktanxzjsqvb\n" +
                "ymdrchgovwfloiuftanxzjwqeb\n" +
                "ymdrchrpvwflotyktanxzjsqeb\n" +
                "gmdrchgpvwfloauttanxzjsqeb\n" +
                "ymdrchmpvofloiukmanxzjsqeb\n" +
                "ymdrchgpvwflsiuktanxzjspkb\n" +
                "ymdrchgpvwfloluktajxijsqmb\n" +
                "ymdrcngpvwfloiukbanxzdsqeb\n" +
                "ymdrchgpvwploiuktnnxzmsqeb\n" +
                "ymdrcwgpvwfloiuktbnxhjsqeb\n" +
                "ymdrcngpvwfloiuktaaxbjsqeb\n" +
                "ykdrchgpvwfloiuktanxzgsqej\n" +
                "yuhrchgpvwfdoiuktanxzjsqeb\n" +
                "ymdrchgpvsfloiukbanxujsqeb\n" +
                "ymqrchgpvwfliiuktanxzjsteb\n" +
                "ysdqchgpvwfloiuktanxzjtqeb\n" +
                "ymdjchgpcwfloiuktanxzrsqeb\n" +
                "ymdkchgpvwfloiukfanlzjsqeb\n" +
                "ymdrchgpvxfloikktanxzjiqeb\n" +
                "smdrchgwewfloiuktanxzjsqeb\n" +
                "ymdrchgpvwfljiuktanxajsqer\n" +
                "ymdrchgpowflifuktanxzjsqeb\n" +
                "ymdrchgpvpzloiukoanxzjsqeb\n" +
                "yydrchgwvwfvoiuktanxzjsqeb\n" +
                "ymdgcdgpvwflobuktanxzjsqeb\n" +
                "ymdechgpvkfloiuktanxzjsjeb\n" +
                "ymdnchnpvwfloixktanxzjsqeb\n" +
                "ymdrchgpiefloiuktqnxzjsqeb\n" +
                "ymprchgpvwfloiuktjnxzjsxeb\n" +
                "ymdrjdgpzwfloiuktanxzjsqeb\n" +
                "ymsrchgpywfloiuktanxzjsueb\n" +
                "ymdrchgpvgoloiuktanxzcsqeb\n" +
                "ymdrphgpswflbiuktanxzjsqeb\n" +
                "ymqrchgpvnfloiumtanxzjsqeb\n" +
                "ymjrchgpvwyloiuktacxzjsqeb\n" +
                "ymdrchepvwmlqiuktanxzjsqeb\n" +
                "kmirchgpvwfloiuktanxzjsreb\n" +
                "ymdncygpvwfloiuktanuzjsqeb\n" +
                "ymdrzhgpvwploiuktanxzxsqeb\n" +
                "ymdrchkpvwfloiwkmanxzjsqeb\n" +
                "ywdrchgovwfloiuktanxzjsceb\n" +
                "amdrchgpvwfloiuktanrzjqqeb\n" +
                "ymdpshgpvwfloiuktanxzjyqeb\n" +
                "ymdrcegpvwfloijktcnxzjsqeb\n" +
                "ymdrcygpvwfloiuktanxztsqwb\n" +
                "ymdrchgpvufloiuvtabxzjsqeb\n" +
                "ymdrchgpvwflkiuktrnxzjsqmb\n" +
                "ymdrchgpvqfloiuktanxpjfqeb\n" +
                "ymdrclgpvkfloiyktanxzjsqeb\n" +
                "ybdxchgpvwfloiuktanxzjskeb\n" +
                "pmdrchgpvwfzoirktanxzjsqeb\n" +
                "ycdfchgpvwfloiuktanxzjtqeb\n" +
                "ymdrchgpdwfloiumtbnxzjsqeb\n" +
                "ymdrchgpqmfloiuktanxzjsqer\n" +
                "ymgrchgpvwfroiuktanxzjsqey\n" +
                "ymdrnhgpvwfloiuktanjzjsqlb\n" +
                "dmdrchgpvgfloiuktqnxzjsqeb\n" +
                "yudrchgnvwfloiukranxzjsqeb\n" +
                "ymdrxhgpvafloiuktanxzjsqeq\n" +
                "ymdrchgpvwfyofuktanxzjsueb\n" +
                "ymdrrhgpvwfloiuktavxzjsqpb\n" +
                "yvdrchgpvwfloiuktalxzhsqeb\n" +
                "ymdrchgpbwfloiuktanxzfnqeb\n" +
                "ymdrqhgpvwfloiuvtznxzjsqeb\n" +
                "ymdrchgpvbfloiuetanxzjsqeo\n" +
                "ymdrchjpvwfloiuktanxzjnqrb\n" +
                "ymdrchgpmwfqoiuknanxzjsqeb\n" +
                "ymdrchgpvwfuoiuktaqxzjtqeb\n" +
                "ymdrchgpvwfloiuktamxaosqeb\n" +
                "fmdrchgpvffloiuktanxzjsaeb\n" +
                "ymdrrhglvwfwoiuktanxzjsqeb\n" +
                "ymdrchgpvwflohuktanxzjcqei\n" +
                "ymdrcsgpvwfloiuktaexzjsqek\n" +
                "ymlrchfpvwfloiuktpnxzjsqeb\n" +
                "yxdrchgpvwfdoiuvtanxzjsqeb\n" +
                "ymdrchgrvwfloiuktadxzjsqew\n" +
                "ymdrchgpvwbloiyktandzjsqeb\n" +
                "ymdrchgpvsfloiyktanozjsqeb\n" +
                "ymdrchgpjwfloiuktanxibsqeb\n" +
                "ymdrchgjvyfloiuktanxzjsqeh\n" +
                "ymdrchgvvwfloiuktanzrjsqeb\n" +
                "ymdrchgpvwaloiuktynxzjsqev\n" +
                "ymdrccgpvwflonvktanxzjsqeb\n" +
                "ymdrchgqvffloiuktanxfjsqeb\n" +
                "ymdbchgpvwsloiudtanxzjsqeb\n" +
                "ymdachgpvwfloiuktanlzjsqwb\n" +
                "ymdrclgpvwwloiuktanxzjsjeb\n" +
                "ybdpchgpvwdloiuktanxzjsqeb\n" +
                "ymdtchgpvwfleijktanxzjsqeb\n" +
                "ymdrchgpvwfloiustanxzjsxep\n" +
                "ymdrcjypvwfloiuktanxnjsqeb\n" +
                "ymdrcdgpvwfloiuutanxkjsqeb\n" +
                "yhirchgpvufloiuktanxzjsqeb\n" +
                "ymdrlhgpvwfluigktanxzjsqeb\n" +
                "ywdrhhgpvwftoiuktanxzjsqeb\n" +
                "ymdrchgpvwflyiuktanozjsqtb\n" +
                "cmdrchgpuwfloiukmanxzjsqeb\n" +
                "ymdochgpvrfloiuktanvzjsqeb\n" +
                "ymdrcvgpvwfgoiuktfnxzjsqeb\n" +
                "ymdrchgpmufloiuktanxzssqeb\n" +
                "ymurchgrvwfloiuktanxzjsqep\n" +
                "bmdrchgpvwfloiukpanxzjsqmb\n" +
                "ymdrchgphwvloiuktanszjsqeb\n" +
                "ymdpkhgpvwfloiuktanxzjsqtb\n" +
                "ymdrchgpvwfloiuwtanxzjfqev\n" +
                "ymdrchgpvwfloguktqlxzjsqeb\n" +
                "ymkrshgpvwflgiuktanxzjsqeb\n" +
                "ymdrchgpzwfloizktanxznsqeb\n" +
                "ymdrchgpvxfloiuktegxzjsqeb\n" +
                "yydrchgpwwfloiuktanxzjsqqb\n" +
                "ymdrcngwvwfltiuktanxzjsqeb\n" +
                "ymdszhgwvwfloiuktanxzjsqeb\n" +
                "ymdrchguvwfjoiuktanxzxsqeb\n" +
                "ymdomhgpvwfloiuktanxgjsqeb\n" +
                "ymdrcvgpvwfloiuktanwzzsqeb\n" +
                "yydrchgpvwfloiuktanxzjmqtb\n" +
                "rmdrchgpvwfloiuktmnszjsqeb\n" +
                "ykdrchgpvwfloyuktmnxzjsqeb\n" +
                "ymcrchkpvwfloiuktanxzjsoeb\n" +
                "ymdrcrgpvwfloiukpanxzjsceb\n" +
                "yrdrchgpvwfloiukwanxzjsqhb\n" +
                "ymdrcfgpvwfloiurtanxojsqeb\n" +
                "ymdrchgpuwstoiuktanxzjsqeb\n" +
                "ymdrchgpvwflpxuktanxzjsqer\n" +
                "ymdrehgpvwfloiuktabxdjsqeb\n" +
                "yedrchgpvwfloiukqanxzjiqeb\n" +
                "ymdrthgpvyfloiuktanxzjsqen\n" +
                "cmdlchgpvwfloiuvtanxzjsqeb\n" +
                "ymdrchgpvwtloiuktanlpjsqeb\n" +
                "ymdrchgpvwfloiuktanyvjsqea\n" +
                "gmdrcogpvwfloiuktanxzjsqqb\n" +
                "ymmrchgpvwflosuktauxzjsqeb\n" +
                "ymgrchgjvwfloiuktavxzjsqeb\n" +
                "ymdbclgpvwfloeuktanxzjsqeb\n" +
                "ymdrchgpvwfloiuktaixzcsqfb\n" +
                "ymdrchgpvwflmiuktanxttsqeb\n" +
                "ymxrchgpvwfloiuktanxzfsqec\n" +
                "yqzrchgpcwfloiuktanxzjsqeb\n" +
                "yvdrchgpvwfloiukgvnxzjsqeb\n" +
                "ymdrchepvwfloiuktahxzosqeb\n" +
                "ymdlchgpvwfloiuktamizjsqeb\n" +
                "ymdrchgpcwflovuktanxzjsqzb\n" +
                "yvduchgpvwfloiukaanxzjsqeb\n" +
                "ymdrchgpvwfloiuktxmxzjsgeb\n" +
                "ymdrcrgpvwfloizktanbzjsqeb\n" +
                "amdrchgpvwfloiukhanxzjsqbb\n" +
                "ymdrchgpvwfloluktajxijsqeb\n" +
                "ymdrcfgpvwfloiubtanxznsqeb\n" +
                "ymdrchgpvwfleiuwtanxzjsweb\n" +
                "ymdrchgpvwfzdguktanxzjsqeb\n" +
                "ymdrchgwvwflosyktanxzjsqeb\n" +
                "ymrrchgpvwfloiultanxzjsqez\n" +
                "ymdpchgkvwfleiuktanxzjsqeb\n" +
                "ymdrchgpvwfloijktalxfjsqeb\n" +
                "ymdrchgpmwfloiuktanzzjsqfb\n" +
                "ymdrcsgpvwfljiukyanxzjsqeb\n" +
                "ymdrcarpvwfloiuktapxzjsqeb\n" +
                "ymdrchgpvwfloiuktanxzjcqvs";
        System.out.println("Day02(a): " +getChecksum(input));
        System.out.println("Day02(b): "+ findCommonBoxIDs(input));
    }
}