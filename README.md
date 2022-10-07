# Selenium-page-object-tasks
Page object tasks (proper usage of assertEquals, assertFalse, assertNotEquals, assertTrue, AfterSuite, BeforeSuite, implicit and explicit Wait, Utils helper)

(Task and the notes inside the java files are written in Serbian)

Java + Selenium
Template projekat se nalazi u selenium folderu.
U direktorijumu src/test/java napraviti paket selenium.parcijalni, gde se dalje nalaze paketi:
1. pagefactory (Sve klase za stranice koje opišete pomoću PageFactory)
2. pages (Sve klase koje opišete pomoću standarnih java klasa)
3. tests (Sve TestNG klase sa Selenium-om)
Zadatak rešavate pomoću TestNG i Selenium-a. Koristite chrome kao webdriver koji ćete
instancirati prilikom realizacije vaših automatskih testova.
Vaše rešenje mora imati upotrebu bar 2 CSS selektora i 2 Xpath selektora koji nisu jednostavni
(samo traže id, name, tagname, itd.).

Zadaci:
1. Opisati sve stanice pomoću jednog od pristupa za opis stranice (pagefactory ili Page
Object Model - pages).
2. Realizovati test scenario #1.

Scenario #1: 
1. Ulogovati se kao korisnik (user,user) i proveriti da ste se uspesno ulogovali
2. Dodati Nastavnika (Milan Milankovic sa zvanjem “Profesor strucnog testiranja”)
3. Potvrditi postojanje profesora u tabeli
4. Promeniti jezik korisnika pod Account>Language>Français i sacuvati promenu
5. Napraviti 3 novih studenata
a. Indeks se krece od RA1 do RA3
b. Ime se krece od ImeStudenta1 do ImeStudenta3
c. Prezime se krece od PrezimeStudenta1 do PrezimeStudenta3
6. Napraviti predmet (Fizikus, sa dodatim prethodna 3 studenta i profesora)
7. Obrisati prvog studenta (RA2) i potvrditi da ga nema vise u tabeli
8. Izlogovati se