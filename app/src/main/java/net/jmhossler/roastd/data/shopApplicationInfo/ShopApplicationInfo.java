package net.jmhossler.roastd.data.shopApplicationInfo;

public class ShopApplicationInfo {
  private String shopName;
  private String shopNum;
  private String shopAddress;

  public ShopApplicationInfo(String shopName, String shopNum, String shopAddress) {
    this.setShopName(shopName);
    this.setShopNum(shopNum);
    this.setShopAddress(shopAddress);
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getShopNum() {
    return shopNum;
  }

  public void setShopNum(String shopNum) {
    this.shopNum = shopNum;
  }

  public String getShopAddress() {
    return shopAddress;
  }

  public void setShopAddress(String shopAddress) {
    this.shopAddress = shopAddress;
  }
}
