package net.jmhossler.roastd.util;

import net.jmhossler.roastd.data.bean.Bean;
import net.jmhossler.roastd.data.bean.BeanDataSource;
import net.jmhossler.roastd.data.bean.FirebaseRTBeanRepository;
import net.jmhossler.roastd.data.drink.Drink;
import net.jmhossler.roastd.data.drink.DrinkDataSource;
import net.jmhossler.roastd.data.drink.FirebaseRTDrinkRepository;
import net.jmhossler.roastd.data.shop.FirebaseRTShopRepository;
import net.jmhossler.roastd.data.shop.Shop;
import net.jmhossler.roastd.data.shop.ShopDataSource;

public class PopulateDB {

  private static BeanDataSource beanDataSource;
  private static DrinkDataSource drinkDataSource;
  private static ShopDataSource shopDataSource;

  public PopulateDB() {
    beanDataSource = FirebaseRTBeanRepository.getInstance();
    drinkDataSource = FirebaseRTDrinkRepository.getInstance();
    shopDataSource = FirebaseRTShopRepository.getInstance();

    this.addItems();
  }

  public void addItems() {

    long id = 12346844634968L;

    Shop s1 = new Shop(String.valueOf(id++), "Heritage House Coffee", "Heritage House originated " +
      "in Tuscaloosa, Alabama, in June 1994, as the first coffee house our town ever knew. " +
      "Since then, we have continued growing as a locally owned small business. " +
      "The original owners opened the store with nothing more than a couple pumps of coffee " +
      "and a few pastries shipped to the store each week. As time went on and owners changed, " +
      "the cafe continued to grow, offering gifts to their customers.", "https://www.google." +
      "com/maps/uv?hl=en&pb=!1s0x8888a85edac31a9f:0x646cf2ec30506713!2m22!2m2!1i80!2i80!3m1!2i20!16m16!" +
      "1b1!2m2!1m1!1e1!2m2!1m1!1e3!2m2!1m1!1e5!2m2!1m1!1e4!2m2!1m1!1e6!3m1!7e115!4shttps://lh5." +
      "googleusercontent.com/p/AF1QipP46l3VY6TyaAHOHBvZCUs5UR0qyfQ4XX7uJIJy%3Dw260-h175-n-k-no!5stuscaloosa" +
      "+coffee+shops+-+Google+Search&imagekey=!1e10!2sAF1QipP46l3VY6TyaAHOHBvZCUs5UR0qyfQ4XX7uJIJy",
      "700 Towncenter Blvd, " +
      "Tuscaloosa, AL 35406",
      "https://www.google.com/maps/dir/''/heritage+coffee+house/@33.2329292,-" +
        "87.6068603,12z/data=!4m8!4m7!1m0!1m5!1m1!1s0x8888a85edac31a9f:0x646cf2ec30506713!2m2!1d-87." +
        "5368202!2d33.2329488", null, null);

    Shop s2 = new Shop(String.valueOf(id++), "Monarch Espresso Bar", "tuscaloosa's craft coffee shop",
      "https://www.google.com/maps/uv?hl=en&pb=!1s0x888602e95dc6c1e1:0x9c1dea0ab307b013!2m22!" +
        "2m2!1i80!2i80!3m1!2i20!16m16!1b1!2m2!1m1!1e1!2m2!1m1!1e3!2m2!1m1!1e5!2m2!1m1!1e4!2m2!1m1!1e6!3m1!" +
        "7e115!4shttps://lh5.googleusercontent.com/p/AF1QipOFR0SIEUx_4tU6cGzfNpsDP4W4gB4MSy5ZFLho%3Dw260-h175" +
        "-n-k-no!5stuscaloosa+coffee+shops+-+Google+Search&imagekey=!1e10!2sAF1QipOFR0SIEUx_4tU6cGzfNpsDP4W4gB" +
        "4MSy5ZFLho", "714 22nd Avenue, Tuscaloosa, AL 35401", "https://www.google.com/maps/dir/''" +
      "/tuscaloosa+coffee+shops/@33.2082483,-87.6347167,12z/data=!4m8!4m7!1m0!1m5!1m1!1s0x888602e95d" +
      "c6c1e1:0x9c1dea0ab307b013!2m2!1d-87.5646766!2d33.2082679", null, null);

    Shop s3 = new Shop(String.valueOf(id++), "Edelweiss", "Since 2007 Ester Scheeff, a native " +
      "German from Stuttgart, has been serving traditional German cakes, pastries, " +
      "and lunch specials with love to all of Tuscaloosa.", "https://www.google.com/maps/uv?hl" +
      "=en&pb=!1s0x888602e86b74ab49:0xd3d2ceb9509da51f!2m22!2m2!1i80!2i80!3m1!2i20!16m16!1b1!2m2!1m1!" +
      "1e1!2m2!1m1!1e3!2m2!1m1!1e5!2m2!1m1!1e4!2m2!1m1!1e6!3m1!7e115!4shttps://lh5.googleusercontent." +
      "com/p/AF1QipMeVZTisBk09uAMnARooZ6i4r2bQhhvYOreBf9l%3Dw260-h175-n-k-no!5stuscaloosa+coffee+shops+" +
      "-+Google+Search&imagekey=!1e10!2sAF1QipMeVZTisBk09uAMnARooZ6i4r2bQhhvYOreBf9l",
      "2324 4th St. Tuscaloosa, AL 35401",
      "https://www.google.com/maps/dir/''/tuscaloosa+coffee+shops" +
        "/data=!4m5!4m4!1m0!1m2!1m1!1s0x888602e86b74ab49:0xd3d2ceb9509da51f?sa=" +
        "X&ved=2ahUKEwjY2oro19HaAhVHrFMKHfOgC9AQ9RcwAHoECAAQCQ", null, null);

    Shop s4 = new Shop(String.valueOf(id++), "OHenry's Coffees", "More Than Just Coffee... " +
      "A Community Gathering Place Since 1993", "https://www.google." +
      "com/maps/uv?hl=en&pb=!1s0x888602e645ea0777:0x1f9ea6b947ea5ae!2m22!2m2!1i80!2i80!3m1!2i20!16m16!" +
      "1b1!2m2!1m1!1e1!2m2!1m1!1e3!2m2!1m1!1e5!2m2!1m1!1e4!2m2!1m1!1e6!3m1!7e115!4shttps://lh5.googleusercontent." +
      "com/p/AF1QipNtO-UC5DZ4k5Jgn4t1IxHMEfi_RcoGDrqVG7Wk%3Dw260-h175-n-k-no!5stuscaloosa+coffee+shops+-+Google+" +
      "Search&imagekey=!1e10!2sAF1QipNtO-UC5DZ4k5Jgn4t1IxHMEfi_RcoGDrqVG7Wk", "2531 University Blvd #100, Tuscaloosa, AL 35401",
      "https://www.google.com/maps/dir/''/tuscaloosa+coffee+shops/@33.2088626,-87.6407768,12z/" +
        "data=!4m8!4m7!1m0!1m5!1m1!1s0x888602e645ea0777:0x1f9ea6b947ea5ae!2m2!1d-87.5707367!2d33.2088822", null, null);

    Drink d1 = new Drink(String.valueOf(id++), "Bama Blitz", "caramel sauce, vanilla, " +
      "caramel, roast of the day, steamed milk, topped with whip cream and caramel",
      null, null, null, s1.getUuid(), null);

    Drink d2 = new Drink(String.valueOf(id++), "Sugar Daddy", "vanilla, caramel sauce," +
      " creamy espresso, steamed milk, topped with whip cream and caramel", null,
      "espresso", null, s1.getUuid(), null);

    Drink d3 = new Drink(String.valueOf(id++), "Cafe Caramel", "caramel sauce, " +
      "roast of the day, toppped with whip cream and caramel", null, "cappucino",
      null, s1.getUuid(), null);

    Drink d4 = new Drink(String.valueOf(id++), "Classic Mocha", "chocolate, creamy espresso, " +
      "steamed milk, whip cream", null, "mocha", null, s1.getUuid(), null);

    Drink d5 = new Drink(String.valueOf(id++), "HH Special", "created by prior employees " +
      "buzzy and jennifer--chocolate, caramel, white chocolate, roast of the day, steamed milk" +
      "topped with whip cream and caramel", null, "espresso", null, s1.getUuid(), null);

    Drink d6 = new Drink(String.valueOf(id++), "Snowflake Royale", "white chocolate, butter pecan" +
      "creamy espresso, steamed milk, topped with whip cream", null, "espresso", null, s1.getUuid(), null);

    Drink d7 = new Drink(String.valueOf(id++), "Espresso", "", null,
      "espresso", 2.75, s2.getUuid(), null);

    Drink d8 = new Drink(String.valueOf(id++), "The Elizabeth Decaf", "",
      null, "filtered coffee", 2.50, s2.getUuid(), null);

    Drink d9 = new Drink(String.valueOf(id++), "Mocha Latte", "", null,
      "Latte", 3.65, s3.getUuid(), null);

    Drink d10 = new Drink(String.valueOf(id++), "Nutella Latte", "", null,
      "Latte", 4.95, s3.getUuid(), null);

    Drink d11 = new Drink(String.valueOf(id++), "Brewed Coffee", "", null,
      null, 2.40, s4.getUuid(), null);

    Drink d12 = new Drink(String.valueOf(id++), "Cool Carmello",
      "Creamy Ghirardelli Caramel and Espresso", null, "Frappachino",
      5.10, s4.getUuid(), null);

    Drink d13 = new Drink(String.valueOf(id++), "Frosted Turtle",
      "Creamy Ghirardelli Chocolate, Caramel, Hazelnut and Espresso", null,
      "Frappachino", 5.10, s4.getUuid(), null);

    Drink d14 = new Drink(String.valueOf(id++), "Snickerdoodle Mocha",
      "Ghirardelli Chocolate, Cinnamon, and Hazelnut", null,
      "Espresso", 4.95, s4.getUuid(), null);

    Drink d15 = new Drink(String.valueOf(id++), "Turtle Deluxe",
      "Ghirardelli Chocolate, Caramel, and Hazelnut", null,
      "Espresso", 4.95, s4.getUuid(), null);

    Bean b1 = new Bean(String.valueOf(id++), "OHenry's Blend", "Our #1 Coffee Blend. We roast it" +
      " to be perfectly balanced for an “any time of day” coffee. (1 lbs bag)", "https://ohenryscoffees.com/" +
      "product-category/coffee/light-mild-roast/#gallery-859", "light",
      null, s4.getUuid(), null);

    Bean b2 = new Bean(String.valueOf(id++), "Costa Rican Dark", "Deep, " +
      "engaging flavors with a trace of smoky chocolate in the finish.", "https://" +
      "ohenryscoffees.com/product-category/coffee/dark-roast/#gallery-688", "dark",
      null, s4.getUuid(), null);

    Bean b3 = new Bean(String.valueOf(id++), "African Classic", "A dark roast " +
      "blend of four of the finest origin beans grown on the African continent.  " +
      "The result is a rich, slightly smoky aroma, winey acidity and unique, complex flavors. " +
      "(Sold in 1 lbs bags)", "https://" +
      "ohenryscoffees.com/wp-content/uploads/2016/07/OHenrys-African-Classic-Coffee.jpg", "dark",
      null, s4.getUuid(), null);

    Bean b4 = new Bean(String.valueOf(id++), "Celebes Kalossi Coffee", "Celebes " +
      "Kalossi Coffee is a world class coffee. " +
      "It is an exotic, rich and unusual coffee with a hint of nutty earthiness. " +
      "It’s so good even your heart rate monitor will smile. (Sold in 1 lbs bags)",
      "https://ohenryscoffees.com/product-category/coffee/" +
        "world-class-exotic/#gallery-628", "exotic",
      null, s4.getUuid(), null);

    drinkDataSource.saveDrink(d1);
    drinkDataSource.saveDrink(d2);
    drinkDataSource.saveDrink(d3);
    drinkDataSource.saveDrink(d4);
    drinkDataSource.saveDrink(d5);
    drinkDataSource.saveDrink(d6);
    drinkDataSource.saveDrink(d7);
    drinkDataSource.saveDrink(d8);
    drinkDataSource.saveDrink(d9);
    drinkDataSource.saveDrink(d10);
    drinkDataSource.saveDrink(d11);
    drinkDataSource.saveDrink(d12);
    drinkDataSource.saveDrink(d13);
    drinkDataSource.saveDrink(d14);
    drinkDataSource.saveDrink(d15);

    beanDataSource.saveBean(b1);
    beanDataSource.saveBean(b2);
    beanDataSource.saveBean(b3);
    beanDataSource.saveBean(b4);

    s1.addItemUUID(d1.getUuid());
    s1.addItemUUID(d2.getUuid());
    s1.addItemUUID(d3.getUuid());
    s1.addItemUUID(d4.getUuid());
    s1.addItemUUID(d5.getUuid());
    s1.addItemUUID(d6.getUuid());

    s2.addItemUUID(d7.getUuid());
    s2.addItemUUID(d8.getUuid());

    s3.addItemUUID(d9.getUuid());
    s3.addItemUUID(d10.getUuid());

    s4.addItemUUID(d11.getUuid());
    s4.addItemUUID(d12.getUuid());
    s4.addItemUUID(d13.getUuid());
    s4.addItemUUID(d14.getUuid());
    s4.addItemUUID(d15.getUuid());
    s4.addItemUUID(b1.getUuid());
    s4.addItemUUID(b2.getUuid());
    s4.addItemUUID(b3.getUuid());
    s4.addItemUUID(b4.getUuid());

    shopDataSource.saveShop(s1);
    shopDataSource.saveShop(s2);
    shopDataSource.saveShop(s3);
    shopDataSource.saveShop(s4);
  }
}


