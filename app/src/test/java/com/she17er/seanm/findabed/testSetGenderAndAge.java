package com.she17er.seanm.findabed;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests setGenderAndAge function in Shelter class
 * @author elissa huang
 */
public class testSetGenderAndAge {
    private Shelter s;
    private String str;

    @Before
    public void setUp() {
        s = new Shelter();
        str = new String();
    }

    @Test
    public void testEmptyInput() {
        s.setGenderAndAge("");
        assertEquals("N/A", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testRandomStringInputAllUppercaseSpaced() {
        str = "EARTHQUAKING LOLLIPOP DERIVATIVELY DES";
        s.setGenderAndAge(str);
        assertEquals("earthquaking lollipop derivatively des", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age",s.getAgeRange());
    }

    @Test
    public void testRandomStringInputAllLowercaseSpaced() {
        str = "sap craving duteously sur";
        s.setGenderAndAge(str);
        assertEquals("sap craving duteously sur", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testRandomStringInputAllUppercaseNoSpace() {
        str = "OVERBURDENEDDECORUM";
        s.setGenderAndAge(str);
        assertEquals("overburdeneddecorum", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testRandomStringInputAllLowercaseNoSpace() {
        str = "encasedcontortion";
        s.setGenderAndAge(str);
        assertEquals("encasedcontortion", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testRandomStringInputMixedCaseSpaced() {
        str = "eXpLiCaBlE wInDmIlL";
        s.setGenderAndAge(str);
        assertEquals("explicable windmill", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testRandomStringInputStrangeCharacters() {
        str = ";*887%^2";
        s.setGenderAndAge(str);
        assertEquals(";*887%^2", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testSemicolonStringInput() {
        str = ";;;;;;;";
        s.setGenderAndAge(str);
        assertEquals(";;;;;;;", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testActualInputAll() {
        str = "women men any gender newborn children young adults";
        s.setGenderAndAge(str);
        assertEquals("women men any gender newborn children young adults", s.getRestrictions());
        assertEquals("women men", s.getGender());
        assertEquals("newborns, children, young adults, ", s.getAgeRange());
    }

    @Test
    public void testActualInputSingle() {
        str = "women";
        s.setGenderAndAge(str);
        assertEquals("women", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "men";
        s.setGenderAndAge(str);
        assertEquals("men", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "any gender";
        s.setGenderAndAge(str);
        assertEquals("any gender", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "newborn";
        s.setGenderAndAge(str);
        assertEquals("newborn", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("newborns, ", s.getAgeRange());

        str = "children";
        s.setGenderAndAge(str);
        assertEquals("children", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("children, ", s.getAgeRange());

        str = "young adults";
        s.setGenderAndAge(str);
        assertEquals("young adults", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("young adults, ", s.getAgeRange());

        str = "any age";
        s.setGenderAndAge(str);
        assertEquals("any age", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testActualInputDoubles() {
        str = "women newborn";
        s.setGenderAndAge(str);
        assertEquals("women newborn", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("newborns, ", s.getAgeRange());

        str = "women children";
        s.setGenderAndAge(str);
        assertEquals("women children", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("children, ", s.getAgeRange());

        str = "women young adults";
        s.setGenderAndAge(str);
        assertEquals("women young adults", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("young adults, ", s.getAgeRange());

        str = "women any";
        s.setGenderAndAge(str);
        assertEquals("women any", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "men newborn";
        s.setGenderAndAge(str);
        assertEquals("men newborn", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("newborns, ", s.getAgeRange());

        str = "men children";
        s.setGenderAndAge(str);
        assertEquals("men children", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("children, ", s.getAgeRange());

        str = "men young adults";
        s.setGenderAndAge(str);
        assertEquals("men young adults", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("young adults, ", s.getAgeRange());

        str = "men any";
        s.setGenderAndAge(str);
        assertEquals("men any", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "any newborn";
        s.setGenderAndAge(str);
        assertEquals("any newborn", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("newborns, ", s.getAgeRange());

        str = "any children";
        s.setGenderAndAge(str);
        assertEquals("any children", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("children, ", s.getAgeRange());

        str = "any young adults";
        s.setGenderAndAge(str);
        assertEquals("any young adults", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("young adults, ", s.getAgeRange());

        str = "any any";
        s.setGenderAndAge(str);
        assertEquals("any any", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testActualInputTriples() {
        str = "women newborn children";
        s.setGenderAndAge(str);
        assertEquals("women newborn children", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("newborns, children, ", s.getAgeRange());

        str = "women newborn young adults";
        s.setGenderAndAge(str);
        assertEquals("women newborn young adults", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("newborns, young adults, ", s.getAgeRange());

        str = "women children young adults";
        s.setGenderAndAge(str);
        assertEquals("women children young adults", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("children, young adults, ", s.getAgeRange());

        str = "men newborn children";
        s.setGenderAndAge(str);
        assertEquals("men newborn children", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("newborns, children, ", s.getAgeRange());

        str = "men newborn young adults";
        s.setGenderAndAge(str);
        assertEquals("men newborn young adults", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("newborns, young adults, ", s.getAgeRange());

        str = "men children young adults";
        s.setGenderAndAge(str);
        assertEquals("men children young adults", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("children, young adults, ", s.getAgeRange());

        str = "any gender newborn children";
        s.setGenderAndAge(str);
        assertEquals("any gender newborn children", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("newborns, children, ", s.getAgeRange());

        str = "any gender newborn young adults";
        s.setGenderAndAge(str);
        assertEquals("any gender newborn young adults", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("newborns, young adults, ", s.getAgeRange());

        str = "any gender children young adults";
        s.setGenderAndAge(str);
        assertEquals("any gender children young adults", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("children, young adults, ", s.getAgeRange());
    }

    @Test
    public void testActualInputQuadruples() {
        str = "women newborn children young adults";
        s.setGenderAndAge(str);
        assertEquals("women newborn children young adults", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("newborns, children, young adults, ", s.getAgeRange());

        str = "men newborn children young adults";
        s.setGenderAndAge(str);
        assertEquals("men newborn children young adults", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("newborns, children, young adults, ", s.getAgeRange());

        str = "any gender newborn children young adults";
        s.setGenderAndAge(str);
        assertEquals("any gender newborn children young adults", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("newborns, children, young adults, ", s.getAgeRange());
    }

    @Test
    public void testActualInputVariousWomen() {
        str = "WOMEN";
        s.setGenderAndAge(str);
        assertEquals("women", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "wOmEn";
        s.setGenderAndAge(str);
        assertEquals("women", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "Women";
        s.setGenderAndAge(str);
        assertEquals("women", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "wOMEN";
        s.setGenderAndAge(str);
        assertEquals("women", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "wOMEn";
        s.setGenderAndAge(str);
        assertEquals("women", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "WOmen";
        s.setGenderAndAge(str);
        assertEquals("women", s.getRestrictions());
        assertEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "wo m e n";
        s.setGenderAndAge(str);
        assertEquals("wo m e n", s.getRestrictions());
        assertNotEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "woman";
        s.setGenderAndAge(str);
        assertEquals("woman", s.getRestrictions());
        assertNotEquals("women", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testActualInputVariousMen() {
        str = "MEN";
        s.setGenderAndAge(str);
        assertEquals("men", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "mEn";
        s.setGenderAndAge(str);
        assertEquals("men", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "Men";
        s.setGenderAndAge(str);
        assertEquals("men", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "mEN";
        s.setGenderAndAge(str);
        assertEquals("men", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "MeN";
        s.setGenderAndAge(str);
        assertEquals("men", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "MEn";
        s.setGenderAndAge(str);
        assertEquals("men", s.getRestrictions());
        assertEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "mAN";
        s.setGenderAndAge(str);
        assertEquals("man", s.getRestrictions());
        assertNotEquals("men", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }

    @Test
    public void testActualInputVariousAnyGender() {
        str = "ANY GENDER";
        s.setGenderAndAge(str);
        assertEquals("any gender", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "aNy GeNdeR";
        s.setGenderAndAge(str);
        assertEquals("any gender", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "Any Gender";
        s.setGenderAndAge(str);
        assertEquals("any gender", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "aNY gENDER";
        s.setGenderAndAge(str);
        assertEquals("any gender", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "AnY GeNdEr";
        s.setGenderAndAge(str);
        assertEquals("any gender", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "ANy GENdEr";
        s.setGenderAndAge(str);
        assertEquals("any gender", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());

        str = "ani gendre";
        s.setGenderAndAge(str);
        assertEquals("ani gendre", s.getRestrictions());
        assertEquals("any gender", s.getGender());
        assertEquals("any age", s.getAgeRange());
    }
}
