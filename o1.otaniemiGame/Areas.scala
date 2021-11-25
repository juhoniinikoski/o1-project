//package o1
//
//import o1.otaniemiGame.{Area, Item}
//
//package object otaniemiGame {
//  // Areas
//  private val metro      = new Area("Metro", "Olet metroasemalla.\nMetrot metelöivät ja ihmisiä vilisee.")
//  private val aBloc      = new Area("A Bloc", "Olet A Bloc ostoskeskuksessa.\nRuoka ja kahvi tuoksuu, nam.")
//  private val dipoli     = new Area("Dipoli", "Olet Aalto-yliopiston päärakennuksessa Dipolissa.\nTäällä ei yksikään ikkuna ole toistensa kanssa samankokoinen.")
//  private val kirjasto   = new Area("Kirjasto", "Olet kirjastossa, toiselta nimeltään Harald Herlin oppimiskeskuksessa.\nHiljainen on tunnelma ja ihmiset ovat keskittyneitä.")
//  private val alvari     = new Area("Alvarin aukio", "Olet Alvarin aukiolla. Kannattaa katsoa mihin astuu, sillä hanhet ovat olleet asialla jälleen.")
//  private val tTalo      = new Area("T-Talo", "T-Talo, täällä opiskellaan, syödään ja hengaillaan.\nTiesitkö, että toisessa kerroksessa on pienimuotoinen tietokonemuseo?")
//  private val tuas       = new Area("TUAS", "Olet TUASilla. Täällä kädet käy ja juttu lentää.\nTäällä on yksi kylän parhaista opikelijaravintoloista!")
//  private val taffa      = new Area("Täffä", "Olispa keskiviikko.\nSpagua odotellessa!")
//  private val aukio      = new Area("Aukio", "Olet aukiolla metroaseman ja Väreen edustalla.\nHuomaat kuinka jokainen ohikulkija tuijottaa puhelintaan.")
//  private val kandilafka = new Area("Kandidaattikeskus", "Olet kandidaattikeskuksessa, legendaarisen TKK:n päärakennuksessa.\nOnkohan jo kiire luennolle!?")
//
//  // Restaurants
//  private val restaurantArea = new Area("Ravintola", "Olet ravintolassa, jossa tuoksut leijailevat.")
//
//  private val blocRestaurant = restaurantArea
//  private val dipoliRestaurant = restaurantArea
//  private val tTaloRestaurant = restaurantArea
//  private val tuasRestaurant = restaurantArea
//  private val taffaRestaurant = restaurantArea
//  private val kandiRestaurant = restaurantArea
//
//  // Spaces to stydy
//  private val studyArea = new Area("Opiskelutila", "Olet opiskelutilassa, jossa ajatus lentää ja luovuus on huipussaan.")
//
//  private val tTaloStudyArea = studyArea
//  private val tuasStudyArea = studyArea
//  private val kandiStudyArea = studyArea
//
//  metro.setNeighbors(Vector("itään" -> aBloc, "etelään" -> kirjasto ))
//
//  aBloc.setNeighbors(Vector("etelään" -> aukio, "länteen" -> metro, "ravintolaan" -> blocRestaurant))
//  blocRestaurant.setNeighbors(Vector("takaisin" -> aBloc))
//
//  dipoli.setNeighbors(Vector("itään" -> kirjasto, "etelään" -> taffa, "ravintolaan" -> dipoliRestaurant))
//  dipoliRestaurant.setNeighbors(Vector("takaisin" -> dipoli))
//
//  kirjasto.setNeighbors(Vector("pohjoiseen" -> metro, "itään" -> aukio, "etelään" -> alvari, "länteen" -> dipoli))
//  aukio.setNeighbors(Vector("pohjoiseen" -> aBloc, "itään" -> tTalo, "etelään" -> kandilafka, "länteen" -> kirjasto))
//  alvari.setNeighbors(Vector("pohjoiseen" -> kirjasto, "itään" -> kandilafka, "länteen" -> taffa))
//
//  tTalo.setNeighbors(Vector("itään" -> tuas, "länteen" -> aukio, "ravintolaan" -> tTaloRestaurant, "opiskelemaan" -> tTaloStudyArea))
//  tTaloRestaurant.setNeighbors(Vector("takaisin" -> tTalo))
//  tTaloStudyArea.setNeighbors(Vector("takaisin" -> tTalo))
//
//  tuas.setNeighbors(Vector("länteen" -> tTalo, "ravintolaan" -> tuasRestaurant, "opiskelemaan" -> tuasStudyArea))
//  tuasRestaurant.setNeighbors(Vector("takaisin" -> tuas))
//  tuasStudyArea.setNeighbors(Vector("takaisin" -> tuas))
//
//  taffa.setNeighbors(Vector("pohjoiseen" -> dipoli, "itään" -> alvari, "ravintolaan" -> taffaRestaurant))
//  taffaRestaurant.setNeighbors(Vector("takaisin" -> taffa))
//
//  kandilafka.setNeighbors(Vector("pohjoiseen" -> aukio, "länteen" -> alvari, "ravintolaan" -> kandiRestaurant, "opiskelemaan" -> kandiStudyArea))
//  kandiRestaurant.setNeighbors(Vector("takaisin" -> kandilafka))
//  kandiStudyArea.setNeighbors(Vector("takaisin" -> kandilafka))
//
//
//  private val seat = new Item("seat", "Mennä opiskelutilaan tekemään päivän askareita.")
//  private val restaurant = new Item("restaurant", "Mennä lounaalle täyttämään vatsasi.")
//  private val lunch = new Item("lunch", "Syödä ravitsevan opiskelijalounaan.")
//  private val lecture = new Item("lecture", "Mennä luennolle kuuntelemaan päivän polttavaa luentoa!")
//  private val friends = new Item("friends", "Jutella kavereiden kanssa kuulumisista.")
//
//  aBloc.addItem(restaurant)
//  aBloc.addItem(new Item("coffee", "Ottaa ärrältä kahvin suhteellisen edullisesti mukaan!"))
//
//  dipoli.addItem(new Item("flyer", "Ottaa mukaan esite koskien Aallon rekrymessuja, jotka järjestetään Otahallissa!"))
//  dipoli.addItem(restaurant)
//
//  kirjasto.addItem(new Item("book", "Lainata fysiikan kirjan jo ajoissa, tarvitset sitä tenttiviikkoa ajatellen."))
//  kirjasto.addItem(seat)
//
//  tTalo.addItem(lecture)
//  tTalo.addItem(seat)
//  tTalo.addItem(restaurant)
//  tTalo.addItem(friends)
//
//  tuas.addItem(seat)
//  tuas.addItem(restaurant)
//  tuas.addItem(lecture)
//  tuas.addItem(friends)
//
//  taffa.addItem(restaurant)
//  taffa.addItem(friends)
//
//  kandilafka.addItem(seat)
//  kandilafka.addItem(friends)
//  kandilafka.addItem(restaurant)
//  kandilafka.addItem(lecture)
//}
