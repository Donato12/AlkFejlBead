# MiniNeptun

**Funkcionális követelmények**<br />
A program a felhasználókat, a tantárgyakat, és a kurzusokat táblázatokban tárolja. A táblák a következőképpen néznek ki:<br />
<ul>
<li>user (id, username, neptunCode, password, enabled, role)</li>
<li>subject (id, title, credit, type, recommendedSemester)</li>
<li>course (subject_id, id, teacher, day, startHour, startMinute, endHour, endMinute, maxNumberOfStudents)</li>
<li>course_users (courses_id, users_id)</li>
</ul>
A subject tábla 1-sok kapcsolattal kapcsolva van a course táblához, melyben az adott tárgyakhoz meghirdetett kurzusok találhatóak (idegen kulcs = subject_id), ezen kívül a user tábla sok-sok kapcsolattal kapcsolva van a kurzus táblához, így jön létre a course_users tábla. Ennek segítségével tudjuk lekérdezni, hogy mely hallgató mely kurzusokat vette fel, illetve hogy adott kurzusokat mely hallgatók vették fel.<br /><br />


**Nemfunkcionális követelmények:**<br />
<ul>
<li>Az általános JavaScript konvencióknak való megfelelés.</li>
<li>Gyors válaszidő, a lehető legkevesebb tárhelyfoglalás.</li>
<li>A rendszernek könnyen használhatónak, átláthatónak, és megbízhatónak kell lennie.</li>
<li>Az általános UX/UI-nak való megfelelés.</li>
</ul>

**Szakterületi fogalomjegyzék:**<br />
Kredit - egyfajta értéket ad egy tárgynak, és ha a hallgató elvégzi megkapja ezt az értéket.<br />
Ajánlott félév - a vezetőség által ajánlott félév a tárgy elvégzésére, viszont máskor is el lehet végezni.<br />
Tárgytípus - Kötelező azt jelenti, hogy a tárgyat kötelező felvenni és elvégezni, a szabadon választhatóak pedig nem kötelező elvégezni vagy felvenni.<br />
Tárgykód - A tárgyak könyebb azonosítása érdekében egyfajta ID-ként szolgáló kód.<br />

**Szerepkörök:**<br />
-Regiszrtációt nem igénylő:<br />

 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Látogató (visitor)<br />
 
-Regisztrációhoz kötött:<br />

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diák (student)<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tanár (teacher)<br />
    


**Fejlesztői környezet**<br />
A fejlesztés során használt környezet: NetBeans IDE<br />

**Alkalmazott kódolási szabályok**<br />
Az általános JavaScript konvenciókat használtuk. Illetve a project felépítése az MVC irányelveinek megfelel.<br />

**Felhasznált technológiák:**<br />
<ul>
<li>REST API</li>
<li>Spring</li>
</ul>

**Adatbázis modell:**<br />
![Adatbázis modell](https://github.com/Donato12/MiniNeptun/blob/master/MiniNeptun/Documents/DatabaseModel.JPG)

**Végpontok:**<br />
Belépéshez nem kötött: 
<ul>
<li>Főoldal</li>
<li>Regisztráció</li>
<li>Bejelentkezés</li>
<li>Kijelentkezés</li>
<li>Információk</li>
<li>Regisztráció</li>
</ul>
Belépéshez kötött:
<ul>
<li>Tantárgyak listája</li>
<li>Tantárgy szerkesztése</li>
<li>Tantárgy törlése</li>
<li>Tantárgy hozzáadása</li>
</ul>
Példa Végpont:<br />
Tantárgy törlése: Főoldal>Bejelentkezés>Tantárgyak listázása>Tantárgy törlése(Ha a felhasználó tanári szerepkörben van)<br />
<br />

**Könyvtárszerkezet:**<br />
![Könyvtárszerkezet](https://github.com/Donato12/MiniNeptun/blob/master/MiniNeptun/Documents/Konyvtarszerkezet.png)

**Drótvázterv:**<br />
![Drótvázterv](https://github.com/Donato12/MiniNeptun/blob/master/MiniNeptun/Documents/1.PNG)
![Drótvázterv](https://github.com/Donato12/MiniNeptun/blob/master/MiniNeptun/Documents/2.PNG)
![Drótvázterv](https://github.com/Donato12/MiniNeptun/blob/master/MiniNeptun/Documents/3.PNG)
![Drótvázterv](https://github.com/Donato12/MiniNeptun/blob/master/MiniNeptun/Documents/4.PNG)
![Drótvázterv](https://github.com/Donato12/MiniNeptun/blob/master/MiniNeptun/Documents/5.PNG)

**Használati eset diagramm:**<br />
![UseCase](https://github.com/Donato12/MiniNeptun/blob/master/MiniNeptun/Documents/usecase.PNG)

**Kliensoldali szolgáltatások:**<br />
Regisztráció: Új felhasználó létrehzoása, lehetőség an dákként és tanárként regisztrálni.<br />
Belépés: Sikeres regisztráció után lehetőség van belépni az alkalmazásba.<br />
Tárgyak listázása: Az alkalmazás listázza az elérhető tantárgyakat.<br />
Tárgy felvétele: Belépett hallgató vehet fel meghirdetett tantárgyakat.<br />
Tárgy leadása: Belépett hallgató leadhat tárgyakat.<br />
Tárgy hozzáadása: Tanár felahsználó új tárgyat hirdet meg a diákok számára.<br />
Tárgy szerkesztése: Belépett tanárnak lehetősége van egy tantárgy adatait megváltoztatni.<br />
Tárgy törlése: Belépett tanár meglévő tantárgyakat törölhet, ekkor a hallgatók felvett tárgyai közül is törlődik a tantárgy.<br />
Kilépés: Belépett felhasználó kilép az oldalról.<br />
