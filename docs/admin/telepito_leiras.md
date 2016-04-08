# Fogadóiroda backoffice alkalmazás dokumentáció

## Techológia

Az alkalmazás két részből áll,

* egy Java nyelvben fejlesztett kliens programból, amelyben (gyári (swing, awt) komponensek használatával),
* és az adatok tárolására egy Oracle adatbázisból (verzió: 11G).

A forrás állományok fordításához és becsomagolásához a [gradle](http://gradle.org) build eszközt vettük igénybe. Ennek használatához nincs szükség semmilyen telepítésre, a projekt forrásai mellé bekerült a gradle [wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)-es megoldása is. 

## Adatbázis telepítés

### Séma létrehozás

Telepítés előtt létre kell hozni az alkalmazás sémáját, ehhez a **fogadoiroda-model** projektben, a `src/main/resources/db/system` könyvtárban található `create-user.sql` szkriptet kell lefuttatni `SYSTEM` felhasználóval.  
Az új séma neve és jelszava tetszés szerint módosítható. 

### Adatbázis objektumok létrehozása

Az adatbázis építéséhez a [liquibase](http://www.liquibase.org) nevű keretrendszert használtuk, elsősorban azt a funkcióját, hogy a fejlesztés során előállt szkripteket egy rövid utasításra a megfelelő sorrendben futtassa le.  
Első használat előtt be kell konfigurálni a kapcsolatot, ez a **fogadoiroda-model** projektben található `gradle.properties` fájlban tehető meg az alábbi paraméterek beállításával:

* *dbUrl*: a telepített Oracle adatbázis jdbc URL-je,
* *dbSchema*: az előző lépésben létrehozott séma neve,
* *dbPassword*: az előző lépésben létrehozott séma jelszava.

Következő lépésként ki kell adni parancssorból az alábbi utasítást:

Unix rendszeren:  
`./gradlew update`

Windows-on:  
`gradlew.bat update`

Ez lefuttatja a projektben a `src/main/resources/db/changelog-xml-master.xml` vezérlő fájlt, ami lényegében `src/main/resources/db/changelogs/xml` mappában található 2 xml-ből építi fel az adatbázist.  
Az xml-ek alternatívájaként készültek sql fájlok is a `src/main/resources/db/changelogs/sql` mappában, ezek futtatásához a projekt gyökerében található `build.gradle` fájlban a 34. sorban át kell írni a *runList* paraméter értékét `devSql`-re.

## Kliens alkalmazás fordítás és csomagolás

Első alkalommal itt is szükséges az adatbázis konfigurálása, erre a **fogadoiroda-admin** projekt `src/main/resources` mappában található `datasource-local.properties` van lehetőség az alábbi paraméterek beállításával:

* *jdbc.driver*: ez legyen `oracle.jdbc.driver.OracleDriver`,
* *jdbc.url*:  a telepített Oracle adatbázis jdbc URL-je,
* *jdbc.schema*: az első lépésben létrehozott séma neve,
* *jdbc.password*: az első lépésben létrehozott séma jelszava.

A fordításhoz és csomagoláshoz az alábbi gradle task futtatása szükséges:

Unix rendszeren:  
`./gradlew release`

Windows-on:  
`./gradlew release`

## Kliens futtatása

A forrás fordításához 1.8-as Java szükséges.

A **fogadoiroda-admin** projekt `release` mappájában állva az alábbi utasítást kell kiadni:

`java -jar fogadoiroda-admin.jar`

Első belépéshez szükséges felhasználó adatai:

* login: admin
* jelszó: 123