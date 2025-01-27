# Nem hivatalos Menetrend

Ez egy nem hivatalos menetrend alkalmazás, amely a menetrendek.hu adatait használja. Az alkalmazás lehetővé teszi a
felhasználók számára, hogy lekérdezzék az útvonalakat különböző városok között, megjelenítve az indulási és érkezési
időpontokat, az átszállások számát és az összidőt.

**Funkciók:**

* Útvonal keresése indulási és érkezési város megadásával.
* Városok automatikus kiegészítése a keresés megkönnyítése érdekében.
* Eredmények szűrése indulási és érkezési város alapján.
* Eredmények rendezése indulási hely, érkezési hely, indulási idő, érkezési idő, átszállások száma és összidő szerint.
* Az elavult járatok szürkítve jelennek meg.
* Különböző közlekedési módok jelölése színekkel:
    * Vonat: világoskék
    * Busz: halványsárga
* Könnyen áttekinthető táblázatos megjelenítés.

**Technológiák:**

* HTML
* CSS (Bootstrap)
* JavaScript

**Telepítés:**

1. Klónozd a repository-t.
2. Nyisd meg az `index.html` fájlt a böngésződben.

**Használat:**

1. Írd be az indulási és érkezési város nevét a megfelelő mezőkbe. Az automatikus kiegészítés segít a város nevének
   megtalálásában.
2. Kattints a "Küldés" gombra.
3. Az eredmények megjelennek a táblázatban.
4. A táblázat fejlécére kattintva rendezheted az eredményeket.

**Megjegyzések:**

* Az alkalmazás a menetrendek.hu nyilvános API-ját használja.
* Az alkalmazás nem hivatalos, és nem áll kapcsolatban a menetrendek.hu-val.
* Az adatok pontosságáért nem vállalunk felelősséget.

**További fejlesztési lehetőségek:**

* Dátum szerinti keresés
* További szűrési lehetőségek (pl. időpont, közlekedési eszköz)
* Térkép integráció
* Mobilbarát megjelenítés
