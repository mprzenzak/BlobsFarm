SYMULACJA AGRESJI

Cel: symulacja przedstawiająca ewolucję agresji i jej wpływ na populację w zależności od parametrów wejściowych

Język programowania: Java

Zamierzamy zasymulować życie kolonii blobków. Symulacja będzie trwała zadaną ilość dni. Blobki będą miały nadawane cechy. Na planszy generowana jest podana ilość blobków. Codziennie na planszy pojawia się pewna ilość jedzenia. Jedzenie leży parami. Blobek, który podchodzi do jedzenia decyduje w zależności od swojej cechy czy zjeść dwie jednostki jedzenia czy jedną. Jeżeli zje jedną to przeżyje do następnego dnia, jeżeli zje dwie to rozmnoży się, jeżeli nie zje nic to umrze. Jeżeli do tego samego jedzenia podejdą jednocześnie dwa blobki to zdobędą one podane ilości jednostek jedzenia:

| cecha | altruista | agresor |
| --- | --- | --- |
| altruista | 1,1 | 0.5, 1.5 |
| agresor | 0.5, 1.5 | 0, 0 |

Konfrontacja dwóch agresorów skutkuje konfliktem w wyniku którego tracą jedzenie. 0.5 jednostki jedzenia daje 50% szans na przeżycie do następnego dnia, a 1.5 jednostki daje przeżycie i 50% szans na rozmnożenie. Czasami będą się pojawiać blobki zabójcy, które w momencie konfrontacji zabiją innego blobka.

Klasy:

- Klasa blobek po której dziedziczą altruista, agresori zabójca
- Klasa &quot;pole planszy&quot; po której dziedziczą: &quot;droga&quot;, pole z jedzeniem, pułapka, bonus

Polimorfizm:

- Interfejs zawierający metody np. &quot;jedz&quot; i &quot;rozmnażaj się&quot;, który implementują wszystkie blobki poza zabójcami

parametry wejściowe:

-ilość blobków

-ilość jedzenia

-czas symulacji

-stosunek ilości blobków z danymi cechami