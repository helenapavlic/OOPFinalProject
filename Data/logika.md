## LOGIKA

### 3 panela
1. **ITEMS PANEL**

sastoji se od subpanela Item koji se kreiraju iz enuma gdje se nalaze svi itemi u vending aparatu.

item subpanel sadrži itemId,itemName, itemPrice i itemQuantity
i sama klasa Item sadrži te iste stvari

kada se izvrši uspješna transakcija, ažurira se itemQuantity na panelu sa itemom koji se prodao
kada je item Quantity 0 onda se na mjestu gdje je item quantity ispisuje da je proizvod out of stock

2. **DISPLAY PANEL**

sastoji se od 3 podpanela   

**a)text panel**

sadrži text area za selected item i input
treba omogućiti čitanje brojeva na tim text panelima

**b) number pad panel**

sastoji se od brojeva 0-9
ima gumbe OK i DEL koji su u početku neaktivni
nakon klika na gumb OK i DEL postaju aktivni
DEL gumb broše zadnji unešeni broj sa selected item text area i ako je obrisan zadnji broj postaje neaktivan DEL i OK gumb
OK gumb pokreće kreiranje transakkcije ako je pronađen id sa idem kojeg je korisnik unio -> pročita polje selected item
ako postoji proizvod sa tim idem kreira se transakcija 
transakcija se sprema kao uspješna ako je odabrani proizvod dostupan (quantity) i ako je unešeno dovoljno novca
transakcija se sprema kao neuspješna ako je odabrani proizvod nedostupan ili je ubačeno premalo novca -> u tom slučaju je change 0.0 jer se korisniku ne vraća novac nego ga se potiče da ubaci još novca ili cancella transakciju

**c)menu bar panel**

ima gumbe ADMIN i CANCEL
gumb ADMIN pokreće dialog hza prijacu admina i nakon uspješne prijave otvara prozor za adina
gumb CANCEL prekida korisnikovu interakciju sa aparatom
ako je ubačen novac, on se vraća, id se resetira i ne sprema se transakcija

3. **COINS PANEL**

sastoji se od gumba sa vrijednostima kovanica
pritiskom na gumb se u display panelu u text panelu zbrojena vrijednost dosad pritisnutih gumbi ispisuje u polju input








    