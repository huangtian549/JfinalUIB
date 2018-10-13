package com.workshop.mvc.inventory;


import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.dto.SplitPage;
import com.platform.mvc.base.BaseController;
import java.util.Map;

@Controller({"/workshop/inventory"})
public class InventoryController
  extends BaseController
{
  private static final Log log = Log.getLog(InventoryController.class);
  private InventoryService inventoryService;
  
  public void index()
  {
    Map<String, Object> queryParam = this.splitPage.getQueryParam();
    queryParam.put("userId", getCUserIds());
    this.splitPage.setQueryParam(queryParam);
    Object typeObject = this.splitPage.getQueryParam().get("type");
    Object categoryObject = this.splitPage.getQueryParam().get("category");
    String type = (String)typeObject;
    if (type == null) {
      type = "0";
    }
    setAttr("type", type);
    String category = (String)categoryObject;
    if (category == null) {
      category = "0";
    }
    setAttr("category", category);
    paging("main", this.splitPage, "platform.baseModel.splitPageSelect", "workshop.inventory.splitPageFrom");
    render("/workshop/inventory/list.html");
  }
  
  public void showDaili()
  {
    Object typeObject = this.splitPage.getQueryParam().get("type");
    Object categoryObject = this.splitPage.getQueryParam().get("category");
    String type = (String)typeObject;
    if (type == null) {
      type = "0";
    }
    setAttr("type", type);
    String category = (String)categoryObject;
    if (category == null) {
      category = "0";
    }
    setAttr("category", category);
    paging("main", this.splitPage, "platform.baseModel.splitPageSelect", "workshop.inventory.splitPageFrom2");
    render("/workshop/inventory/list_daili.html");
  }
  
  public void show()
  {
    Object typeObject = this.splitPage.getQueryParam().get("type");
    Object categoryObject = this.splitPage.getQueryParam().get("category");
    String type = (String)typeObject;
    if (type == null) {
      type = "0";
    }
    setAttr("type", type);
    String category = (String)categoryObject;
    if (category == null) {
      category = "0";
    }
    setAttr("category", category);
    paging("main", this.splitPage, "platform.baseModel.splitPageSelect", "workshop.inventory.splitPageFrom2");
    render("/workshop/inventory/list_show.html");
  }
  
  @Before({InventoryValidator.class})
  public void save()
  {
    Inventory Inventory = (Inventory)getModel(Inventory.class);
    Inventory.setUserId(getCUserIds());
    Inventory.save(true);
    forwardAction("/workshop/inventory/backOff");
  }
  
  public void edit()
  {
    Inventory inventory = (Inventory)Inventory.dao.findById(getPara());
    setAttr("inventory", inventory);
    render("/workshop/inventory/update.html");
  }
  
  @Before({InventoryValidator.class})
  public void update()
  {
    ((Inventory)getModel(Inventory.class)).update();
    forwardAction("/workshop/inventory/backOff");
  }
  
  public void updateCount()
  {
    String ids = getPara("ids");
    String count = getPara("count");
    Inventory inventory = (Inventory)Inventory.dao.findById(ids);
    inventory.setCount(Integer.valueOf(Integer.parseInt(count)));
    inventory.update();
  }
  
  public void view()
  {
    Inventory inventory = (Inventory)Inventory.dao.findById(getPara());
    setAttr("Inventory", inventory);
    render("/workshop/inventory/view.html");
  }
  
  public void delete()
  {
    this.inventoryService.baseDelete("dg_inventory", getPara() == null ? this.ids : getPara());
    forwardAction("/workshop/inventory/backOff");
  }
}