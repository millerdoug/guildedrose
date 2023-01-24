import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public List<Item> Items;

    public static void main(String[] args)
    {
        System.out.println("OMGHAI!");

        Program app = new Program();
        app.Items = new ArrayList<Item>();
        app.Items.add(new Item("+5 Dexterity Vest", 10, 20));
        app.Items.add(new Item("Aged Brie", 2, 0));
        app.Items.add(new Item("Elixir of the Mongoose", 5, 7));
        app.Items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        app.Items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        app.Items.add(new Item("Conjured Mana Cake", 3, 6));

        app.UpdateQuality();

        System.out.println("Press enter to exit...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        scan.close();
    }

    public void UpdateQuality()
    {
        for (Item item: Items)
        {
        	
            String itemName = item.getName();

            //Quality Update
            int quality=-1;
            int maxQ=50;
        	switch (itemName) {
        		case "Sulfuras, Hand of Ragnaros": 
        			quality=0;
        			maxQ=80;
        			break;
	        	case "Aged Brie":
	        		quality=1;
	        		break;
	        	case "Backstage passes to a TAFKAL80ETC concert":
	        		quality=1;
	        		if (item.getSellIn() < 11) {quality++;}
	        		if (item.getSellIn() < 6) {quality++;}
                    break;
	        	case "Conjured Mana Cake":
	        		quality*=2;
        	}
        	if (item.getSellIn()<=0) {
        		if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
        			quality=-item.getQuality();
        		} else {
        			quality*=2;
        		}
        	}
        	item.setQuality(Math.min(maxQ, Math.max(0, item.getQuality()+quality)));

        	//Sellin update
        	if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
        		item.setSellIn(item.getSellIn() - 1);
        	}
        	
        }
    }
}