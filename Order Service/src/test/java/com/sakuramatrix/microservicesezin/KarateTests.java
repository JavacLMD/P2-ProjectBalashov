package com.sakuramatrix.microservicesezin;


import com.intuit.karate.junit5.Karate;

public class KarateTests {

 @Karate.Test
    Karate testAll(){return Karate.run("KarateTest").relativeTo(getClass()); }

}
