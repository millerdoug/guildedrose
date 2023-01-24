import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ProgramTest {
	//Name ends in test so will run with maven or gradle build packages
	
	@Test
	void conjuredManaCake() {
		Program p = new Program();
		p.Items = new ArrayList<Item>();
        p.Items.add(new Item("Conjured Mana Cake", 2, 48));
        p.UpdateQuality();
        Assert.assertEquals("Conjured Mana Cake quality incorrect from day 2 to day 1",46,p.Items.get(0).getQuality());
        Assert.assertEquals("Conjured Mana Cake sellin incorrected",1,p.Items.get(0).getSellIn());
        p.UpdateQuality();
        //1 to 0 is normal degrade, 0 to -1 is twice as fast degredation
        p.UpdateQuality();
        Assert.assertEquals("Conjured Mana Cake quality incorrect from day 2 to day -1",40,p.Items.get(0).getQuality());
        Assert.assertEquals("Conjured Mana Cake sellin incorrect",-1,p.Items.get(0).getSellIn());
        for (int i=1;i<25;i++) {
        	p.UpdateQuality();
        }
        Assert.assertEquals("Conjured Mana Cake minimum incorrect",0,p.Items.get(0).getQuality());
	}

	@Test
	void sulfurasQualityConstant() {
		Program p = new Program();
		p.Items = new ArrayList<Item>();
        p.Items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        p.UpdateQuality();
        Assert.assertEquals("Sulfuras quality changed",80,p.Items.get(0).getQuality());
        Assert.assertEquals("Sulfuras sellin changed",0,p.Items.get(0).getSellIn());
	}
	
	@Test
	void briQuality() {
		Program p = new Program();
		p.Items = new ArrayList<Item>();
        p.Items.add(new Item("Aged Brie", 10, 49));
        p.UpdateQuality();
        Assert.assertEquals("Brie quality did not increase with age",50,p.Items.get(0).getQuality());
        Assert.assertEquals("Brie sellin did not decrease by 1",9,p.Items.get(0).getSellIn());
        p.UpdateQuality();
        Assert.assertEquals("Brie max higher then 50",50,p.Items.get(0).getQuality());
        Assert.assertEquals("Brie sellin did not decrement correctly",8,p.Items.get(0).getSellIn());
	}

	@Test
	void sellByDateOver0() {
		Program p = new Program();
		p.Items = new ArrayList<Item>();
        p.Items.add(new Item("Elixir of the Mongoose", 10, 30));
        p.UpdateQuality();
        Assert.assertEquals("Quality did not decrease by 1",29, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrease by 1",9,p.Items.get(0).getSellIn());
	}

	@Test
	void sellByDateUnder0() {
		Program p = new Program();
		p.Items = new ArrayList<Item>();
        p.Items.add(new Item("Elixir of the Mongoose", -1, 30));
        p.UpdateQuality();
        Assert.assertEquals("Quality decrement did not double after sellin",28, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrease by 1",-2,p.Items.get(0).getSellIn());
        p.UpdateQuality();
        Assert.assertEquals("Quality decrement did not double after sellin",26, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrease by 1",-3,p.Items.get(0).getSellIn());
	}

	@Test
	void concertTicket() {
		Program p = new Program();
		p.Items = new ArrayList<Item>();
        p.Items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 30));
        p.UpdateQuality();
        Assert.assertEquals("Ticket did not increase in quality by 1 from day 11 to 10",31, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrease by 1",10,p.Items.get(0).getSellIn());
        p.UpdateQuality();
        Assert.assertEquals("Ticket did not increase in quality by 2 from day 10 to 9",33, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrease by 1",9,p.Items.get(0).getSellIn());
        p.UpdateQuality(); //9 to 8 35
        p.UpdateQuality(); //8 to 7 37
        p.UpdateQuality(); //7 to 6 39
        p.UpdateQuality(); //6 to 5 41
        p.UpdateQuality(); //5 to 4 44
        Assert.assertEquals("Ticket did not increase in quality from day 9 to 4",44, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrement as expected",4,p.Items.get(0).getSellIn());
        p.UpdateQuality(); //4 to 3
        p.UpdateQuality(); //3 to 2
        p.UpdateQuality(); //2 to 1
        Assert.assertEquals("Ticket quality should max at 50",50, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrement as expected",1,p.Items.get(0).getSellIn());
        p.UpdateQuality(); //1 to 0
        p.UpdateQuality(); //0 to -1
        Assert.assertEquals("Ticket quality should drop after sellin date of 0",0, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrement as expected",-1,p.Items.get(0).getSellIn());
        p.UpdateQuality(); //-1 to -2
        Assert.assertEquals("Ticket quality did not stay at 0",0, p.Items.get(0).getQuality());
        Assert.assertEquals("Sellin did not decrement as expected",-2,p.Items.get(0).getSellIn());
	}

}
