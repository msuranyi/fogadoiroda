# Fogadóiroda web alkalmazás dokumentáció

## Technológia

Az alkalmazás két részből áll,

* egy Java nyelvben fejlesztett J2EE alkalmazásból,
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

Az alkalmazás ugyanazt az adatbázist használja, mint a korábban kiadott backoffice alkalmazás, tehát már meglévő sémán is elvégezhető az update, csak azok a szkriptek fognak lefutni, amelyek a jelenlegi fejlesztéshez készültek.

## Szerver alkalmazás fordítás és csomagolás

A fordításához 1.8-as Java szükséges.

A projekt gyökerében kell futtatni az alábbi gradle taskot:

Unix rendszeren:  
`./gradlew build`

Windows-on:  
`./gradlew build`

Ennek eredményeképp a fogadoiroda-web/build/libs mappában elkészül a az alkalmazás war fájlja

## Szerver alkalmazás telepítése

Ehhez szükség lesz egy 8-as verziójú Apache Tomcat szerverre, amely letölthető [erről](http://xenia.sote.hu/ftp/mirrors/www.apache.org/tomcat/tomcat-8/v8.0.33/bin/apache-tomcat-8.0.33.zip) a címről. Kicsomagolás után a conf könyvtárban található context.xml fájlban fel kell venni az alkalmazáshoz tartozó adatbázisát (az url, username és password attribútumok értelemszerűen javítandóak):

```xml
    <Resource
      name="jdbc/fogadoiroda_ds"
      type="javax.sql.DataSource"
      driverClassName="oracle.jdbc.driver.OracleDriver"
      url="jdbc:oracle:thin:@localhost:1521/orcl"
      auth="Container"
      username="fogadoiroda"
      password="fogadoiroda"
      testOnCreate="true"
      testOnBorrow="true"
      validationQuery="SELECT 1 FROM DUAL"
      maxTotal="5"
      removeAbandonedOnMaintenance="true"
      removeAbandonedOnBorrow="true"
      removeAbandonedTimeout="120"/>
```

Ezen kívül a lib könyvtárba be kell másolni a projekt gyökerében található, szintén lib nevű könyvtárban lévő `ojdbc6-11.2.0.4.0.jar` fájlt.

Végső lépésként a buildelés során előállt war fájlt be kell másolni a Tomcat webapps mappájába.

Ezután a Tomcat indításával magától települ az alkalmazás, és tetszőleges böngészőből elérhető a http://localhost:8080/fogadoiroda címen.

Első belépéshez szükséges felhasználó adatai:

* login: admin
* jelszó: 123

## Web szolgáltatások tesztelése

Célszerű a tesztelés előtt pár eseményt felvinni a felületről, lehetőleg jövőbeli kezdéssel (a web szolgáltatások a nyitott események listáját tudják visszaadni).

### Rest

A legegyszerűbb egy böngészőben megadni a http://localhost:8080/fogadoiroda/rest/openEvents címet.

A válasz formázott megjelenítésére vannak rest kliensek, pl. Google Chrome App - Advanced REST client.

### SOAP

A fent említett Rest client erre is alkalmas. Ezesetben:

* URL-nek http://localhost:8080/fogadoiroda/ws/openEvents címet kell megadni, 
* Method-nak: POST
* a Raw payload mezőbe pedig ezt kell bemásolni:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
	<SOAP-ENV:Header/>
	<SOAP-ENV:Body>
		<ns3:openEvents xmlns:ns3="http://xml.fogadoiroda.oop.gdf.hu/"/>
	</SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```