## Követelmény jegyzék

**Bevezetés**

A szoftver egy online fogadóirodát valósít meg webes felületen. A specifikáció a szoftver első verziójához írodott, több olyan funkciót is tartalmaz, amelyek csak a további fejlesztés során kerülnek megvalósításra. A leírás nem teljes, illetve a fejlesztés alatt is módosítások áldozata lehet.

___
**Beléptetés**

A kezdőképernyőn található meg a bejelentkezési űrlap, amely kitöltése lépteti be a felhasználót a rendszerbe. A felhasználó a két jogkör egyikébe tartozhat: fogadó vagy operátor. A rendszer többi tartalmát belépés nélkül nem lehet elérni.

Amennyiben a látogató nem rendelkezik felhasználói fiókkal, a kezdőképernyőn elérheti a regisztrációs oldalt.

___
**Jogkörök**

A fogadó felhasználóknak lehetősége van lekérdezni a jelenlegi, a megtett és lezajlott fogadásoknak a listáját. Megtekintheti és megváltoztathatja az adatait, feltöltheti egyenlegét.

Az operátor jogkörű felhasználók kezelik a többi fiókot, létrehozhatnak, szerkeszthetnek és törölhetnek eseményeket. 

___
**Felhasználó kezelés**

A felhasználók megváltoztathatják saját adataikat, e-mailes jelszó változtatást vagy a felhasználó felfüggesztését kérvényezhetik.

Az operátorok lekérhetik a felhasználók listáját, a személyes adatok nem kerülnek kimutatásra. Felfüggeszthetik az egyes felhasználókat, operátorokat. Megváltoztathatják a felhasználók jogszintjét is, leszámítva a sajátukat.

___
**Események**

Egy esemény, azaz fogadás egy, vagy több tételből is állhat. A tételeknek van egy rövid leírása és egy előre meghatározott odds, ami befolyásolja a visszafizetett nyeremény összegét.

Események létrehozásakor meg kell adni egy rövid címet, leírást és egy kezdő időpontot.
Az esemény publikálása után megkezdődhet a fogadás, amely a kezdőidőpont elérésével zárul le.  

Amint minden tétel eredménye meghatározható, ezeket egy operátor rögzíti, majd az esemény fogadói kifizetésre kerülnek.

## Funkciók

### Bejelentkezés 

##### Leírás

Kikeresi a felhasználót az adatbázisból, ha talált hozzá tartozó név-jelszó párt, belépteti a felhasználót és az azonosítóját elmenti a munkamenetbe. Ha a felhasználó nem található akkor visszaküldi a megfelelő hibaüzenetet.

##### Paraméterek

Név     | Irány | Típus  | Leírás
--------|-------|--------|-------
név     | IN    | String | a felhasználó loginneve
jelszó  | IN    | String | a felhasználó jelszava
státusz | OUT   | String | a bejelentkezés eredménye 

___

### Regisztráció

Új felhasználót hoz létre, visszatérési érték az esetleges hibaüzenet.

##### Paraméterek

Név     | Irány | Típus  | Leírás
--------|-------|--------|-------
név     | IN    | String | a felhasználó loginneve
jelszó  | IN    | String | a felhasználó jelszava
email	| IN    | String | a felhasználó email címe
státusz | OUT   | String | a regisztráció eredménye

___


### Felhasználói adatok frissítése

Megváltoztatja a felhasználó egyes adatait.

##### Paraméterek

Név         | Irány | Típus  | Leírás
------------|-------|--------|-------
régi jelszó | IN    | String | a felhasználó régi jelszava
új jelszó   | IN    | String | a felhasználó új jelszava
email	    | IN    | String | a felhasználó email címe

### Kijelentkezés

Kilépteti a felhasználót, a felhasználó azonosítóját törli a munkamenetből.

##### Paraméterek

Név		  | Irány | Típus   | Leírás
----------|-------|---------|-------
azonosító | IN    | Integer | a felhasználó azonosítója

___

### Jogszint változtatása

Megváltoztatja a felhasználó jogszintjét, ez lehet vagy fogadó, vagy operátor.

##### Paraméterek

Név       | Irány | Típus   | Leírás
----------|-------|---------|-------
azonosító | IN    | Integer | a felhasználó azonosítója
változás  | IN    | Boolean | a változtatás iránya

___

### Felhasználó zárolása/engedélyezése

Letiltja vagy engedélyezi a megadott felhasználói fiókot.

##### Paraméterek

Név       | Irány | Típus   | Leírás
----------|-------|---------|-------
azonosító | IN    | Integer | a felhasználó azonosítója
változás  | IN    | Boolean | a változtatás iránya

___

### Felhasználó törlése

Törli a megadott felhasználót.

##### Paraméterek

Név       | Irány | Típus   | Leírás
----------|-------|---------|-------
azonosító | IN    | Integer | a felhasználó azonosítója

___


### Események/felhasználók lekérdezése

Visszatérő értéke a lekérdezett lista.

##### Paraméterek

Név       | Irány | Típus      | Leírás
----------|-------|------------|-------
lista     | OUT   | Collection | a lekérdezett lista

___

### Események létrehozása/szerkesztése

Új eseményt hoz létre vagy módosítja adatait. Létrehozás után minden eseményhez hozzárendeli a készítés időpontját és egy azonosítót. Módosításkor a módosítás idejét.

##### Paraméterek

Név       | Irány | Típus      | Leírás
----------|-------|------------|-------
cím       | IN    | String     | az esemény címe
leírás    | IN    | String     | az esemény leírása
zárás     | IN    | Date       | az eseményt lezáró dátum

___

### Tétel létrehozása

Új tételt hoz létre, majd hozzárendeli egy eseményhez.

##### Paraméterek

Név       | Irány | Típus      | Leírás
----------|-------|------------|-------
azonosító | IN    | Integer    | az esemény azonosítója
leírás    | IN    | String     | az tétel leírása

___

### Tétel állapotának változtatása

Megváltoztatja a tétel állapotát(nyert/nem nyert).

##### Paraméterek

Név       | Irány | Típus      | Leírás
----------|-------|------------|-------
azonosító | IN    | Integer    | az tétel azonosítója

___

### Tétel törlése

Törli a tételt.

##### Paraméterek

Név       | Irány | Típus      | Leírás
----------|-------|------------|-------
azonosító | IN    | Integer    | az tétel azonosítója

___

### Fogadás létrehozása

Új fogadást rögzít a felhasználó azonosítója és a tétel azonosítója alapján.

##### Paraméterek

Név             | Irány | Típus      | Leírás
----------------|-------|------------|-------
felh. azonosító | IN    | Integer    | a felhasználó azonosítója
tétel azonosító | IN    | Integer    | az tétel azonosítója

___